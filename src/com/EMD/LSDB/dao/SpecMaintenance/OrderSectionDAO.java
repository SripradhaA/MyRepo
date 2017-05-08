
package com.EMD.LSDB.dao.SpecMaintenance;



import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSet;

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
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.CompGroupVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.RevisionVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

/**
 * @author ps57222
 *  
 */
/***********************************************************************
----------------------------------------------------------------------------------------------------------
*    Date     Version   Modified by    	 Comments                              		  Remarks 
* 19/01/2010    1.0      RR68151     Added EdlIndicator flag with Clause details    Added for CR_81
* 											  
* 													 	 
* 
* --------------------------------------------------------------------------------------------------------
**************************************************************************/

public class OrderSectionDAO extends EMDDAO {
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSectionVO
	 *            The object for fetch Sections in OrderLevel
	 * @return Arraylist It has Arraylist of objSectionVO
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetchOrdSections(SectionVO objSectionVO)
	throws EMDException {
		
		LogUtil.logMessage("Enters into OrderSectionDAO:fetchOrdSections");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ArrayList arlSectionList = new ArrayList();
		ResultSet objResultSet = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		String strSectionName = null;
		String strSectionCode = null;
		String strSectionDisplay = null;
		int intLSDBErrorID = 0;
		//Added for Model Color Flag
		String strSectionColorFlag = null;
		String strModelColorFlag = null;
		String strDelColorFlag = null;
		String strCopyColorFlag=null;//Added for CR_100
		int  intViewColorsFlag = 0;
		
		int intOrderKey = 0;
		int intRevInput = 0;
		
		//Added on 27 April 09
		String strModelName = "";
		String strDataLocType = "";
		String strLogUser = "";
		try {
			strLogUser = objSectionVO.getUserID();
			//Added on 27 April 09
			strModelName = objSectionVO.getModelName();
			strDataLocType = objSectionVO.getDataLocationType();	
			objConnection = DBHelper.prepareConnection();
			LogUtil
			.logMessage("Connection in OrderSectionDAO:fetchOrdSections:"
					+ objConnection);
			LogUtil.logMessage("OrderKey in OrderSectionDAO:fetchOrdSections:"
					+ objSectionVO.getOrderKey());
			LogUtil
			.logMessage("DataLocationType in OrderSectionDAO:fetchOrdSections:"
					+ objSectionVO.getDataLocationType());
			
			intRevInput = objSectionVO.getRevisionInput();
			LogUtil.logMessage("Revision Input:"
					+ objSectionVO.getRevisionInput());
			
			if (objSectionVO != null)
				intOrderKey = objSectionVO.getOrderKey();
			
			
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_SELECT_ORDER_SECTION);
			objCallableStatement.setInt(1, objSectionVO.getOrderKey());
			objCallableStatement.setString(2, objSectionVO
					.getDataLocationType());
			objCallableStatement.setString(3, objSectionVO.getUserID());
			
			objCallableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.execute();
			
			objResultSet = (ResultSet) objCallableStatement.getObject(4);
			LogUtil
			.logMessage("ResultSet Value in OrderSectionDAO:fetchOrdSections:"
					+ objResultSet);
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
			
			while (objResultSet.next()) {
				strSectionCode = objResultSet
				.getString(DatabaseConstants.LS201_SEC_CODE);
				strSectionName = objResultSet
				.getString(DatabaseConstants.LS201_SEC_NAME);
				strSectionDisplay = strSectionCode + "." + strSectionName;
				objSectionVO = new SectionVO();
				objSectionVO.setSectionSeqNo(objResultSet
						.getInt(DatabaseConstants.LS201_SEC_SEQ_NO));
				objSectionVO.setSectionCode(objResultSet
						.getString(DatabaseConstants.LS201_SEC_CODE));
				objSectionVO.setSectionName(objResultSet
						.getString(DatabaseConstants.LS201_SEC_NAME));
				objSectionVO.setSectionDisplay(strSectionDisplay);
				/*Added & updated for CR_100 for red color section links*/
				//Added for Model Color Flag
				strSectionColorFlag = objResultSet.getString(DatabaseConstants.COLOUR_FLAG);
				strModelColorFlag = objResultSet.getString(DatabaseConstants.MDL_COLOUR_FLAG);
				strDelColorFlag = objResultSet.getString(DatabaseConstants.DEL_COLOUR_FLAG);
				strCopyColorFlag = objResultSet.getString(DatabaseConstants.CPY_COLOUR_FLAG);
				
				if ((strSectionColorFlag != null && "Y".equalsIgnoreCase(strSectionColorFlag))
						|| (strDelColorFlag != null && "Y".equalsIgnoreCase(strDelColorFlag))
						|| (strModelColorFlag != null && "Y".equalsIgnoreCase(strModelColorFlag))
						|| (strCopyColorFlag != null && "Y".equalsIgnoreCase(strCopyColorFlag))) {
					
					objSectionVO.setColourFlag("Y");
				} else {
					
					objSectionVO.setColourFlag("N");
				}
				/*CR_100 Ends here*/
				//Updated for color to view delete histroy link in the section screen CR_92
				intViewColorsFlag= objResultSet.getInt(DatabaseConstants.CURR_DEL_CLA_COUNT);
				
				if (intViewColorsFlag!=0) {
					
					objSectionVO.setLinkColourFlag("Y");
				} else {
					//By default blue in jsp
					objSectionVO.setLinkColourFlag("N");
				}
				
				/*objSectionVO.setColourFlag(objResultSet
				 .getString(DatabaseConstants.COLOUR_FLAG));
				 
				 */
				objSectionVO.setOrderKey(intOrderKey);
				objSectionVO.setRevisionInput(intRevInput);
				//Added on 27 April 09
				objSectionVO.setModelName(strModelName);
				objSectionVO.setDataLocationType(strDataLocType);
				objSectionVO.setUserID(strLogUser);
				
				LogUtil
				.logMessage("SectionCode in OrderSectionDAO:fetchOrdSections:"
						+ objSectionVO.getSectionCode());
				LogUtil
				.logMessage("SectionName in OrderSectionDAO:fetchOrdSections:"
						+ objSectionVO.getSectionName());
				LogUtil
				.logMessage("ColourFlag in OrderSectionDAO:fetchOrdSections:"
						+ objSectionVO.getColourFlag());
				arlSectionList.add(objSectionVO);
				
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
				
				DBHelper.closeSQLObjects(objResultSet, objCallableStatement,
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
		LogUtil
		.logMessage("Arraylist Value in MainFeatureInfoDAO:fetchMainFeatures:"
				+ arlSectionList);
		return arlSectionList;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSectionVO
	 *            The object for fetch Sections Details in OrderLevel
	 * @return Arraylist It has Arraylist of objSectionVO
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetchSectionDetails(SectionVO objSectionVO)
	throws EMDException {
		
		LogUtil.logMessage("Enters into OrderSectionDAO:fetchSectionDetails");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ArrayList arlSectionList = new ArrayList();
		ResultSet objResultSetSubSec = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		String strSubSectionName = null;
		String strSubSectionCode = null;
		String strSubSecDisplay = null;
		int intLSDBErrorID = 0;
		SubSectionVO objSubSectionVO = null;
		
		int intRec = 0;
		String strLogUser = "";
		try {
			strLogUser = objSectionVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			LogUtil
			.logMessage("Connection in OrderSectionDAO:fetchSectionDetails:"
					+ objConnection);
			LogUtil
			.logMessage("OrderKey in OrderSectionDAO:fetchSectionDetails:"
					+ objSectionVO.getOrderKey());
			LogUtil
			.logMessage("DataLocationType in OrderSectionDAO:fetchSectionDetails:"
					+ objSectionVO.getDataLocationType());
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_SELECT_ORDER_SUBSECTION);
			objCallableStatement.setInt(1, objSectionVO.getOrderKey());
			objCallableStatement.setString(2, objSectionVO
					.getDataLocationType());
			objCallableStatement.setInt(3, objSectionVO.getSectionSeqNo());
			
			objCallableStatement.setString(4, objSectionVO.getUserID());
			objCallableStatement.registerOutParameter(5, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.execute();
			
			objResultSetSubSec = (ResultSet) objCallableStatement.getObject(5);
			LogUtil
			.logMessage("ResultSet Value in OrderSectionDAO:fetchSectionDetails:"
					+ objResultSetSubSec);
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
			SubSectionVO objClause = null;
			while (objResultSetSubSec.next()) {
				//Added for CR_127
				LogUtil.logMessage("In While Loop objSectionVO.getSubSecSeqNo(); " + objSectionVO.getSubSecSeqNo());
				LogUtil.logMessage("objResultSetSubSec.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO):" + 
				objResultSetSubSec.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
				
				if((objSectionVO.getSubSecSeqNo() == 0) || 
						(objSectionVO.getSubSecSeqNo()!= 0 && objSectionVO.getSubSecSeqNo() 
						==  objResultSetSubSec.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO))){
							
				strSubSectionName = objResultSetSubSec
				.getString(DatabaseConstants.LS202_SUBSEC_NAME);
				strSubSectionCode = objResultSetSubSec
				.getString(DatabaseConstants.SUBSEC_CODE);
				strSubSecDisplay = strSubSectionCode + "." + strSubSectionName;
				
				objSubSectionVO = new SubSectionVO();
				
				objSubSectionVO.setNewSubSecFlag(objResultSetSubSec
						.getString(DatabaseConstants.NEW_SUBSEC_FLAG));
				
				LogUtil.logMessage("SectionVO NewSubSecFlag : " + objSectionVO.getNewSubSecFlag());
				LogUtil.logMessage("SubSectionVO NewSubSecFlag : " + objSubSectionVO.getNewSubSecFlag());
				
				//Added For CR_109 to bring New Subsection to Order
				if ("Y".equalsIgnoreCase(objSectionVO.getNewSubSecFlag()) || 
						 ("N".equalsIgnoreCase(objSectionVO.getNewSubSecFlag()) && !"Y".equalsIgnoreCase(objSubSectionVO.getNewSubSecFlag()))){
					
						SubSectionVO objSubSec = null;
						
						LogUtil
						.logMessage("objResultSetSubSec.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO)"
								+ objResultSetSubSec
								.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
						LogUtil
						.logMessage("objResultSetSubSec.getString(DatabaseConstants.LS202_SUBSEC_CODE)"
								+ objResultSetSubSec
								.getString(DatabaseConstants.SUBSEC_CODE));
						LogUtil
						.logMessage("objResultSetSubSec.getString(DatabaseConstants.LS202_SUBSEC_NAME)"
								+ objResultSetSubSec
								.getString(DatabaseConstants.LS202_SUBSEC_NAME));
						LogUtil
						.logMessage("objResultSetSubSec.getString(DatabaseConstants.NEW_FLAG)"
								+ objResultSetSubSec
								.getString(DatabaseConstants.NEW_FLAG));
						objSubSectionVO.setSecCode(objResultSetSubSec
								.getString(DatabaseConstants.LS201_SEC_CODE));
						objSubSectionVO.setSecName(objResultSetSubSec
								.getString(DatabaseConstants.LS201_SEC_NAME));
						
						objSubSectionVO.setSubSecSeqNo(objResultSetSubSec
								.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
						objSubSectionVO.setSubSecCode(objResultSetSubSec
								.getString(DatabaseConstants.SUBSEC_CODE));
						objSubSectionVO.setSubSecName(objResultSetSubSec
								.getString(DatabaseConstants.LS202_SUBSEC_NAME));
						objSubSectionVO.setSubSecDisplay(strSubSecDisplay);
						objSubSectionVO.setColourFlag(objResultSetSubSec
								.getString(DatabaseConstants.NEW_FLAG));
						
						objSubSectionVO.setOrderKey(objSectionVO.getOrderKey());
						objSubSectionVO.setSecSeqNo(objSectionVO.getSectionSeqNo());
						// objSubSectionVO.setSecName(objSectionVO.getSectionName());
						objSubSectionVO.setRevCode(objSectionVO.getRevisionInput());
						// objSubSectionVO.setUserID(objResultSetSubSec.getString(DatabaseConstants.COLOUR_FLAG));
						
						objSubSec = fetchComponents(objSubSectionVO, objSectionVO,
								objConnection,"Y");
						
						if (objSubSec != null && objSubSec.getCompGroup() != null)
							objSubSectionVO.setCompGroup(objSubSec.getCompGroup());
						
						// This part is for initially we take all the clauses for the
						// section
						if (intRec == 0) {
							LogUtil.logMessage("intRec==0 entering....");
							objClause = fetchClauses(objSectionVO, objConnection,
									DatabaseConstants.MODIFY_SPEC);
							if (objClause != null && objClause.getClauseGroup() != null){
								//If condition added for CR-127
								if("Y".equals(objSectionVO.getRearrFlag())){
									if ((objSectionVO.getSubSecSeqNo() != 0)
											&& (objSectionVO.getSubSecSeqNo() == objResultSetSubSec.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO))) {

										objSubSectionVO.setClauseGroup(objClause.getClauseGroup());
										if (objClause != null && objClause.getChildClausesList() != null)
											objSubSectionVO.setChildClausesList(objClause.getChildClausesList());
										LogUtil.logMessage("objSubSectionVO.getChildClausesList().size():"+objSubSectionVO.getChildClausesList().size());
									}
								}else{
									objSubSectionVO.setClauseGroup(objClause
										.getClauseGroup());
								}
							
							LogUtil
							.logMessage("intRec==0 entering objClause.getClauseGroup()...."
									+ objClause.getClauseGroup());
							}	
						} else {
							LogUtil.logMessage("intRec > 0 entering....");
							objSubSectionVO.setClauseGroup(objClause.getClauseGroup());
							LogUtil
							.logMessage("intRec > 0 entering objClause.getClauseGroup()...."
									+ objClause.getClauseGroup());
							
						}
						
						arlSectionList.add(objSubSectionVO);
						intRec++;
					}
				}
				//Added For CR_109 to bring New Subsection to Order - Ends here			
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
				
				DBHelper.closeSQLObjects(objResultSetSubSec,
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
		.logMessage("Arraylist Value in OrderSectionDAO:fetchSectionDetails:"
				+ arlSectionList);
		return arlSectionList;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSectionVO
	 *            The object for fetch component Details in OrderLevel
	 * @return Arraylist It has Arraylist of objSectionVO
	 * @throws EMDException
	 **************************************************************************/
	
	public static SubSectionVO fetchComponents(SubSectionVO objSubSectionVO,
			SectionVO objSectionVO, Connection objConnection,String blnConnFlag)
	throws EMDException {
		
		LogUtil.logMessage("Enters into OrderSectionDAO:fetchComponents");
		CallableStatement objCallableStatement = null;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		CompGroupVO objCompGroupVO = null;
		ComponentVO objComponentVO = null;
		
		ArrayList arlWholeComponent = new ArrayList();
		SubSectionVO objSubSecVo = new SubSectionVO();
		ResultSet objResultSetComponent = null;
		ResultSet objResultSetComp = null;
		ArrayList arlComponents = null;
		String strLogUser = "";
		try {
			strLogUser = objSectionVO.getUserID();
			/**Added for CR-68 Order New Component -  To create Connection when called from action
			 * and use same connection when called within same DAO**/
			if(objConnection==null && (blnConnFlag.equalsIgnoreCase("N"))){
				
				objConnection=DBHelper.prepareConnection();				
				
			}
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_SELECT_ORDER_COMPONENT);
			
			LogUtil.logMessage("objSectionVO.getOrderKey() :"
					+ objSectionVO.getOrderKey());
			LogUtil.logMessage("objSectionVO.getDataLocationType() :"
					+ objSectionVO.getDataLocationType());
			LogUtil.logMessage("objSubSectionVO.getSubSecSeqNo() :"
					+ objSubSectionVO.getSubSecSeqNo());
			LogUtil.logMessage("objSectionVO.getUserID() :"
					+ objSectionVO.getUserID());
			
			objCallableStatement.setInt(1, objSectionVO.getOrderKey());
			objCallableStatement.setString(2, objSectionVO
					.getDataLocationType());
			objCallableStatement.setInt(3, objSubSectionVO.getSubSecSeqNo());
			objCallableStatement.setString(4, objSectionVO.getUserID());
			objCallableStatement.registerOutParameter(5, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.execute();
			
			objResultSetComponent = (ResultSet) objCallableStatement
			.getObject(5);
			LogUtil
			.logMessage("ResultSet Value in OrderSectionDAO:fetchComponents:"
					+ objResultSetComponent);
			intLSDBErrorID = objCallableStatement.getInt(6);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(7);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
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
			
			while (objResultSetComponent.next()) {
				
				objCompGroupVO = new CompGroupVO();
				objCompGroupVO.setComponentGroupSeqNo(objResultSetComponent
						.getInt(DatabaseConstants.COMP_GRP_SEQ_NO));
				objCompGroupVO.setComponentGroupName(objResultSetComponent
						.getString(DatabaseConstants.COMP_GRP_NAME));
				objCompGroupVO.setValidFlag(objResultSetComponent
						.getString(DatabaseConstants.LS130_VALIDATION_FLAG));
				//Added For CR_81 to make Char Group Green Color Starts here
				objCompGroupVO.setCompGrpTypeSeqNo(objResultSetComponent
						.getInt(DatabaseConstants.LS131_COMP_GRP_TYP_SEQ));
				//Added For CR_81 to make Char Group Green Color Ends here
				
				objResultSetComp = (ResultSet) objResultSetComponent
				.getObject(4);
				arlComponents = new ArrayList();
				while (objResultSetComp.next()) {
					
					objComponentVO = new ComponentVO();
					objComponentVO.setComponentSeqNo(objResultSetComp
							.getInt(DatabaseConstants.COMP_SEQ_NO));
					objComponentVO.setComponentName(objResultSetComp
							.getString(DatabaseConstants.COMP_NAME));
					objComponentVO.setOrderDefaultComp(objResultSetComp
							.getString(DatabaseConstants.DEFAULT_SELECTED));
					//Added for CR_93
					objComponentVO.setOrderCompColorFlag(objResultSetComp
							.getString(DatabaseConstants.COMP_COLOR_FLAG));
					objComponentVO.setNewOrderNo(objResultSetComp
							.getString(DatabaseConstants.LS400_ORDR_NO));
					objComponentVO.setOrderCompClaDesc(objResultSetComp
							.getString(DatabaseConstants.LS301_CLA_DESC));
					// Added for CR_97
					objComponentVO.setLabelFlag(objResultSetComp
							.getString(DatabaseConstants.LABEL_FLAG));
					// Ends Here.
					arlComponents.add(objComponentVO);
					
				}
				DBHelper.closeSQLObjects(objResultSetComp, null, null);
				objCompGroupVO.setComponent(arlComponents);
				arlWholeComponent.add(objCompGroupVO);
			}
			objSubSecVo.setCompGroup(arlWholeComponent);
			
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
		} finally {
			try {
				
				/**Added for CR-68 Order New Component -  To close Connection when called from action
				 * and use same connection when called within same DAO**/
				if("N".equalsIgnoreCase(blnConnFlag)){
				DBHelper.closeSQLObjects(objResultSetComponent,
						objCallableStatement, objConnection);
				}else{
					
					DBHelper.closeSQLObjects(objResultSetComponent,
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
		
		return objSubSecVo;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSectionVO
	 *            The object for fetch component Details in OrderLevel
	 * @return Arraylist It has Arraylist of objSectionVO
	 * @throws EMDException
	 **************************************************************************/
	
	/***************************************************************************
	 * fetchClauses method is used for two puposes 1. It give the group of
	 * sucsection with all it's clauses and component details and it is used in
	 * Modify Spec screen. 2. It give the selected subsection with all it's
	 * clauses and component details and it's is used in ModifySpec part of pop
	 * up Screen and the Appendix Image Select Clause Pop-Up Screen. 3. The
	 * calling procedure of this method is varies by the third argument flag
	 * value. The Flag value is DataBaseConstatnts.MODIFY_SPEC when it is called
	 * from modify Spec Screen The Flag value is
	 * DataBaseConstatnts.APPENDIX_IMAGE when it is called from modify Spec
	 * partOf Pop-UP screen and Appendix Image Select Clause Pop-UP Screen.
	 */
	
	public static SubSectionVO fetchClauses(SectionVO objSectionVO,
			Connection objConnection, String strFlag) throws EMDException {
		
		LogUtil.logMessage("Enters into OrderSectionDAO:fetchClauses");
		CallableStatement objCallableStatement = null;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		
		ClauseVO objClauseVO = null;
		
		ArrayList arlWholeClause = new ArrayList();
		SubSectionVO objSubSecVo = new SubSectionVO();
		
		ResultSet objResultSetClause = null;
		ResultSet objResultSetRevCode = null;
		ResultSet objResultSetEDLNO = null;
		ResultSet objResultSetRefEDLNO = null;
		ResultSet objResultSetPartOf = null;
		ResultSet objResultSetComp = null;
		ResultSet objResultSetStdEquip = null;
		ResultSet objResultSetTableData = null;
//Added CR_81 for characterisitic group clasue edl and refdel values 
		
		ResultSet objResultSetCharacteristicEDLNORefEDLNo = null;
		//Ends here 
				
		ArrayList arlRevCode = null;
		ArrayList arlCompName = null;
		ArrayList arlEDLNO = null;
		ArrayList arlRefEDLNO = null;
		ArrayList arlPartOF = null;
		String strStdEquip = null;
			
		ArrayList arlTableRows = null;
				
				
		//Added for LSDB_CR-74 by KA57588 --Enabling and Disabling Revise Clause link
		String strModelDelFlag = null;
		String strOrderDelFlag = null;
		String strDelClaStagFlag = null;
		int countCol=0;
		String strLogUser = "";
		
		//Added for CR_127
		ArrayList arlParentClauses = new ArrayList();
		ArrayList arlChildClauses = new ArrayList();
		ArrayList arlMainList = new ArrayList();
		
		try {
			
			strLogUser = objSectionVO.getUserID();
			
			if (objConnection == null) {
				
				objConnection = DBHelper.prepareConnection();
			}
			
			/**  	The Clause number generation logic is changed and 
			 * 		now it's generating clause numbers for all sections to a particular order.
			 * 		so we are passing section and sub section sequence numbers as null.
			 * 		Modified for LSDB_CR-48 on 07-August-08
			 * 		by ps57222
			 *  **/


			/**
			 * Commented out Clause Number Generation Proc for LSDB_CR-74 by KA57588 -- Starts here
			*/
				
			/*objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_CLA_NUM);
						
			
			objCallableStatement.setNull(1, Types.NULL);
			
			objCallableStatement.setNull(2, Types.NULL);
			
			objCallableStatement.setInt(3, objSectionVO.getOrderKey());
			objCallableStatement.setString(4, objSectionVO
					.getDataLocationType());
			objCallableStatement.setString(5, objSectionVO.getUserID());
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			
			LogUtil.logMessage("objSectionVO.getSectionSeqNo() :"
					+ objSectionVO.getSectionSeqNo());
			LogUtil.logMessage("objSectionVO.getOrderKey() :"
					+ objSectionVO.getOrderKey());
			LogUtil.logMessage("objSectionVO.getDataLocationType() :"
					+ objSectionVO.getDataLocationType());
			
			objCallableStatement.execute();
			intLSDBErrorID = objCallableStatement.getInt(6);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(7);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
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
			*/
			//Commented out Clause Number Generation Proc for LSDB_CR-74 by KA57588 -- Ends here
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_CLA_DISP);
			
			/**
			 * Modified to get the clauses for selected subsection if subsection Sequence number is passed from front end.
			 *  
			 */
			if (objSectionVO.getSubSecSeqNo() == 0) {
				objCallableStatement.setNull(1, Types.NULL);
			} else {
				objCallableStatement.setInt(1, objSectionVO.getSubSecSeqNo());
			}
			//objCallableStatement.setNull(1, Types.NULL); 
			objCallableStatement.setInt(2, objSectionVO.getSectionSeqNo());
			objCallableStatement.setInt(3, objSectionVO.getOrderKey());
			objCallableStatement.setString(4, objSectionVO
					.getDataLocationType());
			objCallableStatement.setInt(5, objSectionVO.getRevisionInput());
			
			objCallableStatement.setInt(6, 1);// This is for Eng Spec by
			// Default
			
			objCallableStatement.setString(7, objSectionVO.getUserID());
			objCallableStatement.registerOutParameter(8, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(9, Types.INTEGER);
			objCallableStatement.registerOutParameter(10, Types.VARCHAR);
			objCallableStatement.registerOutParameter(11, Types.VARCHAR);
			objCallableStatement.execute();
			
			objResultSetClause = (ResultSet) objCallableStatement.getObject(8);
			intLSDBErrorID = objCallableStatement.getInt(9);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(10);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(11);
			
			//Added on 05 Aug 08
			
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
			
			//Ends here
			
			while (objResultSetClause.next()) {
				
				arlRevCode = null;
				arlCompName = null;
				arlEDLNO = null;
				arlRefEDLNO = null;
				arlPartOF = null;
				arlTableRows = null;
								
				objResultSetRevCode = null;
				objResultSetEDLNO = null;
				objResultSetRefEDLNO = null;
				objResultSetPartOf = null;
				objResultSetComp = null;
				objResultSetStdEquip = null;
				objResultSetTableData = null;
								
				/*
				 * tableData2=new ArrayList(); tableData3=new ArrayList();
				 * tableData4=new ArrayList(); tableData5=new ArrayList();
				 * tableData6=new ArrayList();
				 */
				strStdEquip = null;
				objClauseVO = new ClauseVO();
				objClauseVO.setClauseSeqNo(objResultSetClause
						.getInt(DatabaseConstants.LS300_CLA_SEQ_NO));
				objClauseVO.setVersionNo(objResultSetClause
						.getInt(DatabaseConstants.LS301_VERSION_NO));
				objClauseVO.setSubSectionSeqNo(objResultSetClause
						.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
				objClauseVO.setCopyIndicator(objResultSetClause
						.getString(DatabaseConstants.LS406_CPY_IND));
				objClauseVO.setModelIndicator(objResultSetClause
						.getString(DatabaseConstants.MODEL_INDICATOR));
				//Added for CR No 49
				objClauseVO.setDeleteIndicator(objResultSetClause
						.getString(DatabaseConstants.DEL_IND));
				objClauseVO.setDeleteFlag(objResultSetClause
						.getString(DatabaseConstants.LS190_CLA_SOURCE_CODE));
				//Ends
				
				//Added for LSDB_CR-74 by KA57588 --- Starts here
				objClauseVO.setDelClaStagFlag(objResultSetClause
						.getString(DatabaseConstants.CLA_EXISTS_FLAG));
				
				strModelDelFlag = objResultSetClause
				.getString(DatabaseConstants.LS190_CLA_SOURCE_CODE);
				strOrderDelFlag = objResultSetClause
				.getString(DatabaseConstants.LS406_CLA_DEL_FLAG);
				strDelClaStagFlag = objResultSetClause
				.getString(DatabaseConstants.CLA_EXISTS_FLAG);
				//This check is for enabling and disabling revise clause link -- starts here
				if(((strModelDelFlag!=null && "D".equalsIgnoreCase(strModelDelFlag))&&
					(strOrderDelFlag!=null && "Y".equalsIgnoreCase(strOrderDelFlag))&&
					(strDelClaStagFlag!=null && "Y".equalsIgnoreCase(strDelClaStagFlag))) ||
					((strModelDelFlag!=null && !"D".equalsIgnoreCase(strModelDelFlag))&&
							(strOrderDelFlag!=null && "Y".equalsIgnoreCase(strOrderDelFlag))&&
							(strDelClaStagFlag!=null && "N".equalsIgnoreCase(strDelClaStagFlag)))){
					
					objClauseVO.setReviseEnableFlag("N");
					
				}else{
					objClauseVO.setReviseEnableFlag("Y");
				}
				
				//Component Show/Hide Check
				
				if(strModelDelFlag!=null){
					
					if(("D".equalsIgnoreCase(strModelDelFlag) || !"D".equalsIgnoreCase(strModelDelFlag)) &&
						("Y".equalsIgnoreCase(strOrderDelFlag) && "Y".equalsIgnoreCase(strDelClaStagFlag))	){
						
						objClauseVO.setCompShowFlag("N");
					}else{
						objClauseVO.setCompShowFlag("Y");
					}
					
				}
				
				if(((strModelDelFlag!=null && "D".equalsIgnoreCase(strModelDelFlag))&&
						(strOrderDelFlag!=null && "Y".equalsIgnoreCase(strOrderDelFlag))&&
						(strDelClaStagFlag!=null && "Y".equalsIgnoreCase(strDelClaStagFlag))) ||
						((strModelDelFlag!=null && !"D".equalsIgnoreCase(strModelDelFlag))&&
								(strOrderDelFlag!=null && "Y".equalsIgnoreCase(strOrderDelFlag))&&
								(strDelClaStagFlag!=null && "N".equalsIgnoreCase(strDelClaStagFlag)))){
						
						objClauseVO.setReviseEnableFlag("N");
						
					}else{
						objClauseVO.setReviseEnableFlag("Y");
					}
				
				
				//Ends here
				
				objClauseVO.setUserMarkFlag(objResultSetClause
						.getString(DatabaseConstants.LS406_USR_MARKER));
				objClauseVO.setSysMarkFlag(objResultSetClause
						.getString(DatabaseConstants.LS406_SYS_MARKER));
				objClauseVO.setClauseDelFlag(objResultSetClause
						.getString(DatabaseConstants.LS406_CLA_DEL_FLAG));
				objClauseVO.setSysMarkDesc(objResultSetClause
						.getString(DatabaseConstants.SYS_MARKER_DESC));
				
				
//	 Added for characterisitic group flag  CR_81 by sd41630 on 11-Jan-10 - Starts here

				objClauseVO.setSelectCGCFlag(objResultSetClause
						.getString(DatabaseConstants.LS300_CHAR_GRP_FLAG));
				
				//Ends here
				objClauseVO.setOrderKey(objSectionVO.getOrderKey());
				objResultSetRevCode = (ResultSet) objResultSetClause
				.getObject(DatabaseConstants.REVISION_NUMBER);
				
				//Added for CR_109
				objClauseVO.setMarkClaReason(objResultSetClause
						.getString(DatabaseConstants.LS406_USR_MARKED_REASON));
				//Added for CR_109 Ends here
				
				// This is for getting the Rev Code
				arlRevCode = new ArrayList();
				while (objResultSetRevCode.next()) {
					arlRevCode.add(objResultSetRevCode
							.getString(DatabaseConstants.REVISION_NUM));
					
				}
				
				objClauseVO.setRevCode(arlRevCode);
				DBHelper.closeSQLObjects(objResultSetRevCode, null, null);
				
				objClauseVO.setClauseNum(objResultSetClause
						.getString(DatabaseConstants.LS414_CLA_NO));
				
				objClauseVO.setPriceBookNumber(objResultSetClause
						.getInt(DatabaseConstants.LS301_PRICE_BOOK_NUMBER));
				objClauseVO.setClauseDesc(objResultSetClause
						.getString(DatabaseConstants.LS301_CLA_DESC));
				
				objClauseVO.setClauseImageName(objResultSetClause
						.getString(DatabaseConstants.LS415_IMG_NAME));
				
				objClauseVO.setDwONumber(objResultSetClause
						.getInt(DatabaseConstants.LS301_DWO_NUMBER));
				objClauseVO.setPartNumber(objResultSetClause
						.getInt(DatabaseConstants.LS301_PART_NUMBER));
				objClauseVO.setEngDataComment(objResultSetClause
						.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS));
				// objClauseVO.setStandardEquipmentDesc(objResultSetClause.getString(DatabaseConstants.LS060_STD_EQP_DESC));
				
				objResultSetTableData = (ResultSet) objResultSetClause
				.getObject(DatabaseConstants.TABLE_DATE);
				ArrayList arlTableColumns = null;
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
					LogUtil.logMessage("exists Table Data");
				}
				//Added For CR_93
				countCol=ApplicationUtil.getTableDataColumnsCount(arlTableRows);
				objClauseVO.setTableDataColSize(countCol);
				LogUtil.logMessage("TableDataColSize+**************"+countCol);
				
				objClauseVO.setTableArrayData1(arlTableRows);
				
				DBHelper.closeSQLObjects(objResultSetTableData, null, null);
				/*
				 * objClauseVO.setTableArrayData2(tableData2);
				 * objClauseVO.setTableArrayData3(tableData3);
				 * objClauseVO.setTableArrayData4(tableData4);
				 * objClauseVO.setTableArrayData5(tableData5);
				 */
				arlEDLNO = new ArrayList();
				objResultSetEDLNO = (ResultSet) objResultSetClause
				.getObject(DatabaseConstants.EDLNO);
				
				while (objResultSetEDLNO.next()) {
					arlEDLNO.add(objResultSetEDLNO
							.getString(DatabaseConstants.LS302_EDL_NO));
					
				}
				objClauseVO.setEdlNO(arlEDLNO);
				
				DBHelper.closeSQLObjects(objResultSetEDLNO, null, null);
				
				arlRefEDLNO = new ArrayList();
				objResultSetRefEDLNO = (ResultSet) objResultSetClause
				.getObject(DatabaseConstants.refEDLNO);
				while (objResultSetRefEDLNO.next()) {
					arlRefEDLNO.add(objResultSetRefEDLNO
							.getString(DatabaseConstants.LS303_REF_EDL_NO));
				}
				
				objClauseVO.setRefEDLNO(arlRefEDLNO);
				
				DBHelper.closeSQLObjects(objResultSetRefEDLNO, null, null);
				
				
				arlPartOF = new ArrayList();
				objResultSetPartOf = (ResultSet) objResultSetClause
				.getObject(DatabaseConstants.PartOF);
				SubSectionVO objSubSecVO = null;
				while (objResultSetPartOf.next()) {
					
					objSubSecVO = new SubSectionVO();
					objSubSecVO.setSecSeqNo(objResultSetPartOf
							.getInt(DatabaseConstants.LS201_SEC_SEQ_NO));
					objSubSecVO.setSubSecSeqNo(objResultSetPartOf
							.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
					objSubSecVO.setSubSecCode(objResultSetPartOf
							.getString(DatabaseConstants.LS407_PART_OF_CLA_NO));
					objSubSecVO
					.setPartOfClauseSeqNo(objResultSetPartOf
							.getInt(DatabaseConstants.LS300_PART_OF_CLA_SEQ_NO));
					arlPartOF.add(objSubSecVO);
					
				}
				objClauseVO.setPartOF(arlPartOF);
				
				DBHelper.closeSQLObjects(objResultSetPartOf, null, null);
				
				objResultSetStdEquip = (ResultSet) objResultSetClause
				.getObject(DatabaseConstants.STD_EQUIP);
				if (objResultSetStdEquip.next()) {
					
					strStdEquip = objResultSetStdEquip
					.getString(DatabaseConstants.LS060_STD_EQP_DESC);
				}
				objClauseVO.setStandardEquipmentDesc(strStdEquip);
				DBHelper.closeSQLObjects(objResultSetStdEquip, null, null);
				
				arlCompName = new ArrayList();
				objResultSetComp = (ResultSet) objResultSetClause
				.getObject(DatabaseConstants.COMPONENTS);
				while (objResultSetComp.next()) {
					
					//Added for LSDB_CR_71 for deleted component in red color
					ComponentVO objComponentVO = new ComponentVO();
					objComponentVO.setComponentName(objResultSetComp
							.getString(DatabaseConstants.LS140_COMP_NAME));
					objComponentVO.setDeletedFlag(objResultSetComp
							.getString(DatabaseConstants.COMPONENT_DELETE_FLAG));
					//Added for LSDB_CR-74 by KA57588
					objComponentVO.setCompColorFlag(objResultSetComp.getString(DatabaseConstants.COLOUR_FLAG));
					//Added for CR-114
					objComponentVO.setComponentGroupName(objResultSetComp.getString(DatabaseConstants.LS130_COMP_GRP_NAME));
					objComponentVO.setCompLeadFlag(objResultSetComp
							.getString(DatabaseConstants.Italics));
					//Added for CR-114
					arlCompName.add(objComponentVO);
					// if(objResultSetComp.next())
					//Commented out for LSDB_CR_71
					//arlCompName.add(objResultSetComp
					//		.getString(DatabaseConstants.LS140_COMP_NAME));
					// else
					// arlCompName.add(objResultSetComp.getString(DatabaseConstants.LS140_COMP_NAME));
					
				}
				
				objClauseVO.setCompName(arlCompName);
				
				DBHelper.closeSQLObjects(objResultSetComp, null, null);
				
				//added CR_81 for characteristic group 

				objResultSetCharacteristicEDLNORefEDLNo = (ResultSet) objResultSetClause
				.getObject(DatabaseConstants.CHRSTC_EDL_AND_REF_EDL_NO);
				
				
				while (objResultSetCharacteristicEDLNORefEDLNo.next()) {
					
					objClauseVO.setCharEdlNo(objResultSetCharacteristicEDLNORefEDLNo.
							getString(DatabaseConstants.LS428_CHARSTC_EDL_NO));
					
					objClauseVO.setCharRefEDLNo(objResultSetCharacteristicEDLNORefEDLNo.
							getString(DatabaseConstants.LS428_CHARSTC_REF_EDL_NO));
									
				}
				
				DBHelper.closeSQLObjects(objResultSetCharacteristicEDLNORefEDLNo, null, null);
				
				objClauseVO.setEdlIndicator(objResultSetClause.
							getString(DatabaseConstants.EDLINDICATOR));
				
				//Ends here 
				/*Uncommented for CR_111 to bring back the Sales Version
				Added For CR_99*/
				//objClauseVO.setSalesVerFLAG(objResultSetClause.getString(DatabaseConstants.LS406_SALES_VERSION_FLAG));
				//Added for CR_111 to rearrange clauses at order level
				objClauseVO.setChildFlag(objResultSetClause.getString(DatabaseConstants.CHILD_FLAG));
				objClauseVO.setClaCode(objResultSetClause.getString("LEVEL"));
				//Ends here

				//Added for CR-127
				
				objClauseVO.setSubClaExistsFlag(objResultSetClause.getString(DatabaseConstants.SUB_CLAUSE_EXISTS));
				objClauseVO.setParentClauseSeqNo(objResultSetClause.getInt(DatabaseConstants.LS300_PARENT_CLA_SEQ_NO));
				if("Y".equals(objSectionVO.getRearrFlag())){
					if(objResultSetClause.getString("LEVEL").equalsIgnoreCase("1")){
						arlParentClauses.add(objClauseVO);
					}else if(objResultSetClause.getString("LEVEL").equalsIgnoreCase("2")){
						LogUtil.logMessage("objClauseVO.getClauseNum():" + objClauseVO.getClauseNum());
						LogUtil.logMessage("objClauseVO.getParentClauseSeqNo():" + objClauseVO.getParentClauseSeqNo());
						arlChildClauses.add(objClauseVO);
					}
				}
				LogUtil.logMessage("objClauseVO.getSubClaExistsFlag():" + objClauseVO.getSubClaExistsFlag());
				//Added for CR-127 Ends here

				arlWholeClause.add(objClauseVO);
				
				// objResultSetComp = null;
				
				/*
				 * arlCloseResult = new ArrayList();
				 * arlCloseResult.add(objResultSetRevCode);
				 * arlCloseResult.add(objResultSetEDLNO);
				 * arlCloseResult.add(objResultSetRefEDLNO);
				 * arlCloseResult.add(objResultSetPartOf);
				 * arlCloseResult.add(objResultSetComp);
				 * arlCloseResult.add(objResultSetStdEquip);
				 * arlCloseResult.add(objResultSetTableData);
				 * DBHelper.closeResultSets(arlCloseResult , null , null);
				 */
			}
			//Added for PART OF CR Change
			
			//Added for CR_127
			/*if("Y".equals(objSectionVO.getRearrFlag()))
			{
				//Added for getting three arraylists all, parent & child clauses
				arlMainList.add(arlWholeClause);
				arlMainList.add(arlParentClauses);
				arlMainList.add(arlChildClauses);
			}*/
			if("Y".equals(objSectionVO.getRearrFlag()))
			{
				objSubSecVo.setClauseGroup(arlWholeClause);
				objSubSecVo.setChildClausesList(arlChildClauses);
			}else{ 
				objSubSecVo.setClauseGroup(arlWholeClause);
			}
			//Added for CR_127 ends here
			//objSubSecVo.setClauseGroup(arlWholeClause);
			DBHelper.closeSQLObjects(null, objCallableStatement, null);
			
			
			/**
			 * Here SP_DELETE_TEMP_TABLE procedure is called to clear the Temp Table data's 
			 * used for the selected order.All the Transactions are happened in one Time.
			 * 
			 */

			//Commented out Clause Number Generation Proc for LSDB_CR-74 by KA57588 -- Starts here
			/*LogUtil.logMessage("OrderKey befor Delete:"
					+ objSectionVO.getOrderKey());
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_DELETE_TEMP_TABLE);
			objCallableStatement.setInt(1, objSectionVO.getOrderKey());
			objCallableStatement.setString(2, objSectionVO
					.getDataLocationType());
			objCallableStatement.setString(3, objSectionVO.getUserID());
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
			*/
			//Commented out Clause Number Generation Proc for LSDB_CR-74 by KA57588 -- Ends here
			//Ends here
			
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
		} finally {
			try {
				if (DatabaseConstants.MODIFY_SPEC.equals(strFlag)) {
					DBHelper.closeSQLObjects(objResultSetClause,
							objCallableStatement, null);
				} else if (DatabaseConstants.APPENDIX_IMAGE.equals(strFlag)) {
					DBHelper.closeSQLObjects(objResultSetClause,
							objCallableStatement, objConnection);
				}
			} catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return objSubSecVo;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objModelVO
	 *            the object for updating Order Component
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static SectionVO saveComponent(SectionVO objSectionVO) throws EMDException {
		LogUtil.logMessage("Entering OrderSectionDAO.saveComponent");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intStatus = 0;
		ArrayDescriptor arrdesc = null;
		String strLogUser = "";
		
		try {
			strLogUser = objSectionVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			
			LogUtil.logMessage("Order Key in DAO :"
					+ objSectionVO.getOrderKey());
			LogUtil.logMessage("Data Loc Type in DAO :"
					+ objSectionVO.getDataLocationType());
			LogUtil.logMessage("Compoent Group Seq No DAO :"
					+ objSectionVO.getComponentGroupSeqNo().length);
			LogUtil.logMessage("Component Seq in DAO :"
					+ objSectionVO.getCompSeqNo().length);
			LogUtil.logMessage("User ID in DAO :" + objSectionVO.getUserID());
			
			Connection dconn = ((DelegatingConnection) objConnnection).getInnermostDelegate(); //Added for CR-123
			
			arrdesc = new ArrayDescriptor(DatabaseConstants.STR_ARRAY,
					dconn);
			
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SAVE_ORDER_COMP);
			objCallableStatement.setInt(1, objSectionVO.getOrderKey());
			objCallableStatement.setString(2, objSectionVO
					.getDataLocationType());
			
			ARRAY arrCompSeqNo = new ARRAY(arrdesc, dconn,
					objSectionVO.getCompSeqNo());
			
			if (arrCompSeqNo.length() == 0) {
				
				objCallableStatement.setNull(3, Types.NULL);
			} else {
				
				objCallableStatement.setArray(3, arrCompSeqNo);
			}
			
			ARRAY arrCompGroupSeqNo = new ARRAY(arrdesc, dconn,
					objSectionVO.getComponentGroupSeqNo());
			
			if (arrCompGroupSeqNo.length() == 0) {
				
				objCallableStatement.setNull(4, Types.NULL);
			} else {
				
				objCallableStatement.setArray(4, arrCompGroupSeqNo);
			}
			
			objCallableStatement.setString(5, objSectionVO.getUserID());
			
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0 ;
			}
			
			LogUtil
			.logMessage("Inside the updateModel method of ModelMaintDAO :intStatus .."
					+ intStatus);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(6);
			strOracleCode = (String) objCallableStatement.getString(7);
			strErrorMessage = (String) objCallableStatement.getString(8);
			
			//Added for LSDB_CR_71 for Server Side Component Validation
			objSectionVO.setMessage(strErrorMessage);
			LogUtil.logMessage("strErrorMessage:" + strErrorMessage);
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
			
		} catch (DataAccessException objDatExp) {
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
						objConnnection);
			} catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return objSectionVO;
		
	}
	
	public static ArrayList fetchSubSectionDetails(SubSectionVO objSubSectionVO)
	throws EMDException {
		
		LogUtil
		.logMessage("Enters into OrderSectionDAO:fetchSubSectionDetails");
		
		Connection objConnection = null;
		PreparedStatement objPreparedStatement = null;
		ResultSet objResultSet = null;
		ArrayList arlSubSecList = new ArrayList();
		String strLogUser = "";
		
		try {
			strLogUser = objSubSectionVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			LogUtil.logMessage("Enter into OrderSectionDAO Connection:"
					+ objConnection);
			int intOrderKey = objSubSectionVO.getOrderKey();
			int intSubSecSeqNo = objSubSectionVO.getSubSecSeqNo();
			objPreparedStatement = objConnection
			.prepareStatement(EMDQueries.Query_Fetch_Order_Sections);
			objPreparedStatement.setInt(1, intSubSecSeqNo);
			objPreparedStatement.setInt(2, intOrderKey);
			objPreparedStatement.setString(3, DatabaseConstants.DATALOCATION);
			objResultSet = objPreparedStatement.executeQuery();
			
			while (objResultSet.next()) {
				objSubSectionVO.setSubSecName(objResultSet
						.getString(DatabaseConstants.LS202_SUBSEC_NAME));
				objSubSectionVO.setSubSecCode(objResultSet
						.getString(DatabaseConstants.SUBSEC_CODE));
				arlSubSecList.add(objSubSectionVO);
			}
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
				
				if (objResultSet != null) {
					objResultSet.close();
				}
				
				DBHelper.closeConnection(objConnection);
			} catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlSubSecList;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSectionVO
	 *            The object for fetch SubSections at OrderLevel
	 * @return Arraylist It has Arraylist of SubSectionVO
	 * @throws EMDException
	 **************************************************************************/
	
	/***************************************************************************
	 * Added for LSDB_CR-42 Added on 09-05-08 by ps57222
	 */
	
	public static ArrayList fetchOrdSubSections(SectionVO objSectionVO)
	throws EMDException {
		
		LogUtil.logMessage("Enters into OrderSectionDAO:fetchOrdSubSections");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ArrayList arlSubSectionList = new ArrayList();
		ResultSet objResultSetSubSec = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		String strSubSectionName = null;
		String strSubSectionCode = null;
		String strSubSecDisplay = null;
		int intLSDBErrorID = 0;
		SubSectionVO objSubSectionVO = null;
		
		String strLogUser = "";
		try {
			strLogUser = objSectionVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			LogUtil
			.logMessage("Connection in OrderSectionDAO:fetchOrdSubSections:"
					+ objConnection);
			LogUtil
			.logMessage("OrderKey in OrderSectionDAO:fetchOrdSubSections:"
					+ objSectionVO.getOrderKey());
			LogUtil
			.logMessage("DataLocationType in OrderSectionDAO:fetchOrdSubSections:"
					+ objSectionVO.getDataLocationType());
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_SELECT_ORDER_SUBSECTION);
			objCallableStatement.setInt(1, objSectionVO.getOrderKey());
			objCallableStatement.setString(2, objSectionVO
					.getDataLocationType());
			objCallableStatement.setInt(3, objSectionVO.getSectionSeqNo());
			
			objCallableStatement.setString(4, objSectionVO.getUserID());
			objCallableStatement.registerOutParameter(5, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.execute();
			
			objResultSetSubSec = (ResultSet) objCallableStatement.getObject(5);
			LogUtil
			.logMessage("ResultSet Value in OrderSectionDAO:fetchOrdSubSections:"
					+ objResultSetSubSec);
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
			while (objResultSetSubSec.next()) {
				objSubSectionVO = new SubSectionVO();
				
				objSubSectionVO.setNewSubSecFlag(objResultSetSubSec
						.getString(DatabaseConstants.NEW_SUBSEC_FLAG));
				
				//Added For CR_109 to bring New Subsection to Order
				if ("Y".equalsIgnoreCase(objSectionVO.getNewSubSecFlag()) || 
						 ("N".equalsIgnoreCase(objSectionVO.getNewSubSecFlag()) && !"Y".equalsIgnoreCase(objSubSectionVO.getNewSubSecFlag()))){					
				
				strSubSectionCode = objResultSetSubSec
				.getString(DatabaseConstants.SUBSEC_CODE);
				strSubSectionName = objResultSetSubSec
				.getString(DatabaseConstants.LS202_SUBSEC_NAME);
				strSubSecDisplay = strSubSectionCode + "." + strSubSectionName;
				objSubSectionVO.setSubSecCode(objResultSetSubSec
						.getString(DatabaseConstants.SUBSEC_CODE));
				objSubSectionVO.setSubSecName(objResultSetSubSec
						.getString(DatabaseConstants.LS202_SUBSEC_NAME));
				objSubSectionVO.setSubSecSeqNo(objResultSetSubSec
						.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
				objSubSectionVO.setSubSecDisplay(strSubSecDisplay);
				
				arlSubSectionList.add(objSubSectionVO);
				
				}
			}
			
		} catch (DataAccessException objDatExp) {
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
			} catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return arlSubSectionList;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objRevisionVO
	 *            the object containg null
	 * @return ArrayList contains the list of Revision types
	 * @throws EMDException
	 **************************************************************************/
	/***************************************************************************
	 * fetchRevision Method is used to load the dropdown values from
	 * database,which is used in Modify Spec main screen. Added on 20-May-08 by
	 * ps57222
	 *  
	 */
	public static ArrayList fetchRevision(RevisionVO objRevisionVO)
	throws EMDException {
		LogUtil.logMessage("Inside the  OrderSectionDAO:fetchRevision");
		Connection objConnnection = null;
		List arlEmptyLlist = new ArrayList();
		ArrayList arlRevLlist = new ArrayList();
		String strLogUser = "";
		try {
			strLogUser = objRevisionVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in fetchRevision :"
					+ objConnnection);
			
			RowSet objRevSet = DBHelper.executeQuery(objConnnection,
					EMDQueries.QUERRY_SELECT_REVISION, arlEmptyLlist);
			
			while (objRevSet.next()) {
				
				objRevisionVO = new RevisionVO();
				objRevisionVO.setRevCode(objRevSet
						.getInt(DatabaseConstants.LS081_REV_VIEW_SEQ_NO));
				
				objRevisionVO.setRevViewName(objRevSet
						.getString(DatabaseConstants.LS081_REV_VIEW_DESC));
				
				arlRevLlist.add(objRevisionVO);
			}
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception OrderSectionDAO:fetchRevision");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		} finally {
			try {
				DBHelper.closeConnection(objConnnection);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception OrderSectionDAO:fetchRevision");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		LogUtil.logMessage("Size of arlRevLlist in fetchRevision :"
				+ arlRevLlist.size());
		return arlRevLlist;
		
	}
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0 Added for CR-72 Proof Reading PDF
	 * @param objSectionVO
	 *            The object for generate Order ClauseNumber
	 * @return integer
	 * @throws EMDException
	 **************************************************************************/
	
	public static synchronized int generateOrdrClauseNo(SectionVO objSectionVO)
	throws EMDException {
		
		LogUtil.logMessage("Enters into OrderSectionDAO:generateOrdrClauseNo");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus=0;
		String strLogUser = "";
		try {
              strLogUser = objSectionVO.getUserID();
			
			if (objConnection == null) {
				
				objConnection = DBHelper.prepareConnection();
			}
			
			//Commented out Clause Number Generation Proc for LSDB_CR-74 by KA57588 -- Starts here

			/*objCallableStatement = objConnection.prepareCall(EMDQueries.SP_CLA_NUM);
			
			
			objCallableStatement.setNull(1, Types.NULL);
			objCallableStatement.setNull(2, Types.NULL);
			objCallableStatement.setInt(3, objSectionVO.getOrderKey());
			objCallableStatement.setString(4, objSectionVO.getDataLocationType());
			objCallableStatement.setString(5, objSectionVO.getUserID());
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			
			LogUtil.logMessage("objSectionVO.getSectionSeqNo() :"+ objSectionVO.getSectionSeqNo());
			LogUtil.logMessage("objSectionVO.getOrderKey() :"+ objSectionVO.getOrderKey());
			LogUtil.logMessage("objSectionVO.getDataLocationType() :"+ objSectionVO.getDataLocationType());
			
			intStatus= objCallableStatement.executeUpdate();
			LogUtil.logMessage("generate Status:" + intStatus);
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			intLSDBErrorID = objCallableStatement.getInt(6);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(7);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
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
			*/
			//Commented out Clause Number Generation Proc for LSDB_CR-74 by KA57588 -- Ends here
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
	 * @version 1.0 Added for CR-72 Proof Reading PDF
	 * @param objSectionVO
	 *            The object for fetching Clause Details
	 * @return Arraylist It has Arraylist ofSectionVO
	 * @throws EMDException
	 **************************************************************************/
	
	public static synchronized SubSectionVO fetchClauseDetails(SectionVO objSectionVO)
	throws EMDException {
		
		LogUtil.logMessage("Enters into OrderSectionDAO:fetchClauseDetails");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
	    String strLogUser = "";
		ResultSet objResultSetClause = null;
		ResultSet objResultSetRevCode = null;
		ResultSet objResultSetEDLNO = null;
		ResultSet objResultSetRefEDLNO = null;
		ResultSet objResultSetPartOf = null;
		ResultSet objResultSetComp = null;
		ResultSet objResultSetStdEquip = null;
		ResultSet objResultSetTableData = null;
		ResultSet objResultSetCharEDLNORefEDLNo = null; //Added For CR_81
		
		
		ArrayList arlRevCode = null;
		ArrayList arlCompName = null;
		ArrayList arlEDLNO = null;
		ArrayList arlRefEDLNO = null;
		ArrayList arlPartOF = null;
		String strStdEquip = null;
		
		ArrayList arlTableRows = null;
				
		ClauseVO objClauseVO = null;
		ArrayList arlWholeClause = new ArrayList();
		SubSectionVO objSubSecVo = new SubSectionVO();
		try {
            				
			objConnection = DBHelper.prepareConnection();
						
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_CLA_DISP);
						
			if (objSectionVO.getSubSecSeqNo() == 0) {
				objCallableStatement.setNull(1, Types.NULL);
			} else {
				objCallableStatement.setInt(1, objSectionVO.getSubSecSeqNo());
			}
			 
			objCallableStatement.setInt(2, objSectionVO.getSectionSeqNo());
			objCallableStatement.setInt(3, objSectionVO.getOrderKey());
			objCallableStatement.setString(4, objSectionVO.getDataLocationType());
			objCallableStatement.setInt(5, objSectionVO.getRevisionInput());
			//Updated for CR_99
			if (objSectionVO.getIntPdfType() != 0)
				objCallableStatement.setInt(6, objSectionVO.getIntPdfType());
			else
				objCallableStatement.setInt(6, 1);
			
			objCallableStatement.setString(7, objSectionVO.getUserID());
			objCallableStatement.registerOutParameter(8, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(9, Types.INTEGER);
			objCallableStatement.registerOutParameter(10, Types.VARCHAR);
			objCallableStatement.registerOutParameter(11, Types.VARCHAR);
			objCallableStatement.execute();
			
			objResultSetClause = (ResultSet) objCallableStatement.getObject(8);
			intLSDBErrorID = objCallableStatement.getInt(9);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(10);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(11);
			
			//Added on 05 Aug 08
			
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
			
			//Ends here
			
			while (objResultSetClause.next()) {
				
				arlRevCode = null;
				arlCompName = null;
				arlEDLNO = null;
				arlRefEDLNO = null;
				arlPartOF = null;
				arlTableRows = null;
								
				objResultSetRevCode = null;
				objResultSetEDLNO = null;
				objResultSetRefEDLNO = null;
				objResultSetPartOf = null;
				objResultSetComp = null;
				objResultSetStdEquip = null;
				objResultSetTableData = null;
				objResultSetCharEDLNORefEDLNo = null;//Added For CR_81
				
				/*
				 * tableData2=new ArrayList(); tableData3=new ArrayList();
				 * tableData4=new ArrayList(); tableData5=new ArrayList();
				 * tableData6=new ArrayList();
				 */
				strStdEquip = null;
				objClauseVO = new ClauseVO();
				objClauseVO.setClauseSeqNo(objResultSetClause
						.getInt(DatabaseConstants.LS300_CLA_SEQ_NO));
				objClauseVO.setVersionNo(objResultSetClause
						.getInt(DatabaseConstants.LS301_VERSION_NO));
				objClauseVO.setSubSectionSeqNo(objResultSetClause
						.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
				objClauseVO.setCopyIndicator(objResultSetClause
						.getString(DatabaseConstants.LS406_CPY_IND));
				objClauseVO.setModelIndicator(objResultSetClause
						.getString(DatabaseConstants.MODEL_INDICATOR));
				
				//Added for LSDB_CR-74 by KA57588 -- Starts here
				
				objClauseVO.setUserMarkFlag(objResultSetClause
						.getString(DatabaseConstants.LS406_USR_MARKER));
				objClauseVO.setSysMarkFlag(objResultSetClause
						.getString(DatabaseConstants.LS406_SYS_MARKER));
				objClauseVO.setClauseDelFlag(objResultSetClause
						.getString(DatabaseConstants.LS406_CLA_DEL_FLAG));
				objClauseVO.setSysMarkDesc(objResultSetClause
						.getString(DatabaseConstants.SYS_MARKER_DESC));
				
				//Added for Sales Spec if clause desc changed, then we need to display sys marker
				objClauseVO.setSaleSysMarker(objResultSetClause
						.getString("LS406_SALES_SYS_MARKER"));
				//Ends here

				//Added for characterisitic group flag  CR_81 by RR68151
				objClauseVO.setSelectCGCFlag(objResultSetClause
						.getString(DatabaseConstants.LS300_CHAR_GRP_FLAG));
				//Ends here
				
				//Added for CR No 49
				objClauseVO.setDeleteIndicator(objResultSetClause
						.getString(DatabaseConstants.DEL_IND));
				objClauseVO.setDeleteFlag(objResultSetClause
						.getString(DatabaseConstants.LS190_CLA_SOURCE_CODE));
				//Ends
				objClauseVO.setOrderKey(objSectionVO.getOrderKey());
				objResultSetRevCode = (ResultSet) objResultSetClause
				.getObject(DatabaseConstants.REVISION_NUMBER);
				
				// This is for getting the Rev Code
				arlRevCode = new ArrayList();
				while (objResultSetRevCode.next()) {
					arlRevCode.add(objResultSetRevCode
							.getString(DatabaseConstants.REVISION_NUM));
					
				}
				
				objClauseVO.setRevCode(arlRevCode);
				DBHelper.closeSQLObjects(objResultSetRevCode, null, null);
				
				objClauseVO.setClauseNum(objResultSetClause
						.getString(DatabaseConstants.LS414_CLA_NO));
				objClauseVO.setPriceBookNumber(objResultSetClause
						.getInt(DatabaseConstants.LS301_PRICE_BOOK_NUMBER));
				objClauseVO.setClauseDesc(objResultSetClause
						.getString(DatabaseConstants.LS301_CLA_DESC));
				
				objClauseVO.setClauseImageName(objResultSetClause
						.getString(DatabaseConstants.LS415_IMG_NAME));
				
				objClauseVO.setDwONumber(objResultSetClause
						.getInt(DatabaseConstants.LS301_DWO_NUMBER));
				objClauseVO.setPartNumber(objResultSetClause
						.getInt(DatabaseConstants.LS301_PART_NUMBER));
				objClauseVO.setEngDataComment(objResultSetClause
						.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS));
				// objClauseVO.setStandardEquipmentDesc(objResultSetClause.getString(DatabaseConstants.LS060_STD_EQP_DESC));
				
				objResultSetTableData = (ResultSet) objResultSetClause
				.getObject(DatabaseConstants.TABLE_DATE);
				ArrayList arlTableColumns = null;
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
					LogUtil.logMessage("exists Table Data");
				}
				objClauseVO.setTableArrayData1(arlTableRows);
				
				DBHelper.closeSQLObjects(objResultSetTableData, null, null);

				//Added for characterisitic group flag  CR_81 by RR68151
				objResultSetCharEDLNORefEDLNo = (ResultSet) objResultSetClause
				.getObject(DatabaseConstants.CHRSTC_EDL_AND_REF_EDL_NO);
				
				
				while (objResultSetCharEDLNORefEDLNo.next()) {
					
					objClauseVO.setCharEdlNo(objResultSetCharEDLNORefEDLNo.
							getString(DatabaseConstants.LS428_CHARSTC_EDL_NO));
					
					objClauseVO.setCharRefEDLNo(objResultSetCharEDLNORefEDLNo.
							getString(DatabaseConstants.LS428_CHARSTC_REF_EDL_NO));
									
				}
				
				DBHelper.closeSQLObjects(objResultSetCharEDLNORefEDLNo, null, null);		
				//Ends here 
				
				arlEDLNO = new ArrayList();
				objResultSetEDLNO = (ResultSet) objResultSetClause
				.getObject(DatabaseConstants.EDLNO);
				
				while (objResultSetEDLNO.next()) {
					arlEDLNO.add(objResultSetEDLNO
							.getString(DatabaseConstants.LS302_EDL_NO));
					
				}
				objClauseVO.setEdlNO(arlEDLNO);
				
				DBHelper.closeSQLObjects(objResultSetEDLNO, null, null);
				
				arlRefEDLNO = new ArrayList();
				objResultSetRefEDLNO = (ResultSet) objResultSetClause
				.getObject(DatabaseConstants.refEDLNO);
				while (objResultSetRefEDLNO.next()) {
					arlRefEDLNO.add(objResultSetRefEDLNO
							.getString(DatabaseConstants.LS303_REF_EDL_NO));
				}
				
				objClauseVO.setRefEDLNO(arlRefEDLNO);
				
				DBHelper.closeSQLObjects(objResultSetRefEDLNO, null, null);
				
				arlPartOF = new ArrayList();
				objResultSetPartOf = (ResultSet) objResultSetClause
				.getObject(DatabaseConstants.PartOF);
				SubSectionVO objSubSecVO = null;
				while (objResultSetPartOf.next()) {
					
					objSubSecVO = new SubSectionVO();
					objSubSecVO.setSecSeqNo(objResultSetPartOf
							.getInt(DatabaseConstants.LS201_SEC_SEQ_NO));
					objSubSecVO.setSubSecSeqNo(objResultSetPartOf
							.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
					objSubSecVO.setSubSecCode(objResultSetPartOf
							.getString(DatabaseConstants.LS407_PART_OF_CLA_NO));
					objSubSecVO
					.setPartOfClauseSeqNo(objResultSetPartOf
							.getInt(DatabaseConstants.LS300_PART_OF_CLA_SEQ_NO));
					arlPartOF.add(objSubSecVO);
					
				}
				objClauseVO.setPartOF(arlPartOF);
				
				DBHelper.closeSQLObjects(objResultSetPartOf, null, null);
				
				objResultSetStdEquip = (ResultSet) objResultSetClause
				.getObject(DatabaseConstants.STD_EQUIP);
				if (objResultSetStdEquip.next()) {
					
					strStdEquip = objResultSetStdEquip
					.getString(DatabaseConstants.LS060_STD_EQP_DESC);
				}
				objClauseVO.setStandardEquipmentDesc(strStdEquip);
				DBHelper.closeSQLObjects(objResultSetStdEquip, null, null);
				
				arlCompName = new ArrayList();
				objResultSetComp = (ResultSet) objResultSetClause
				.getObject(DatabaseConstants.COMPONENTS);
				while (objResultSetComp.next()) {
					
					
					ComponentVO objComponentVO = new ComponentVO();
					objComponentVO.setComponentName(objResultSetComp
							.getString(DatabaseConstants.LS140_COMP_NAME));
					objComponentVO.setDeletedFlag(objResultSetComp
							.getString(DatabaseConstants.COMPONENT_DELETE_FLAG));
					//Added for LSDB_CR-74 by KA57588
					objComponentVO.setCompColorFlag(objResultSetComp.getString(DatabaseConstants.COLOUR_FLAG));
					arlCompName.add(objComponentVO);
					
					
				}
				
				objClauseVO.setCompName(arlCompName);				
				//Added for CR_109
				objClauseVO.setMarkClaReason(objResultSetClause
						.getString(DatabaseConstants.LS406_USR_MARKED_REASON));
				//Added for CR_109 Ends here
				DBHelper.closeSQLObjects(objResultSetComp, null, null);
				
				arlWholeClause.add(objClauseVO);
				
				
			}
			
			objSubSecVo.setClauseGroup(arlWholeClause);
			
			//Added for PART OF CR Change
			DBHelper.closeSQLObjects(null, objCallableStatement, null);
			
		
			
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
		} finally {
			try {
				
					DBHelper.closeSQLObjects(objResultSetClause,
							objCallableStatement, objConnection);
				
			} catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return objSubSecVo;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0 Added for CR-72 Proof Reading PDF
	 * @param objSectionVO
	 *            The object for deleting temp Table
	 * @return integer
	 * @throws EMDException
	 **************************************************************************/
	
	public static synchronized int deleteTempTabData(SectionVO objSectionVO)
	throws EMDException {
		
		LogUtil.logMessage("Enters into OrderSectionDAO:deleteTempTabData");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ArrayList arlSectionList = new ArrayList();
		
		String strOracleCode = null;
		String strErrorMessage = null;
		int intStatus=0;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
            objConnection = DBHelper.prepareConnection();
			
			
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_DELETE_TEMP_TABLE);
			objCallableStatement.setInt(1, objSectionVO.getOrderKey());
			objCallableStatement.setString(2, objSectionVO.getDataLocationType());
			objCallableStatement.setString(3, objSectionVO.getUserID());
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
		    intStatus= objCallableStatement.executeUpdate();
			LogUtil.logMessage("Delete Status:" + intStatus);
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
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
		LogUtil
		.logMessage("Arraylist Value in generateOrderClaNO:deleteTempTabData:"
				+ arlSectionList);
		return intStatus;
		
	}
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objModelVO
	 *            the object for updating Order Component
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static SectionVO saveRequiredComponent(SectionVO objSectionVO) throws EMDException {
		LogUtil.logMessage("Entering OrderSectionDAO.saveRequiedComponent");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intStatus = 0;
		ArrayDescriptor arrdesc = null;
		String strLogUser = "";
		
		try {
			strLogUser = objSectionVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			
			LogUtil.logMessage("Order Key in DAO :"
					+ objSectionVO.getOrderKey());
			LogUtil.logMessage("Data Loc Type in DAO :"
					+ objSectionVO.getDataLocationType());
			LogUtil.logMessage("Compoent Group Seq No DAO :"
					+ objSectionVO.getComponentGroupSeqNo().length);
			LogUtil.logMessage("Component Seq in DAO :"
					+ objSectionVO.getCompSeqNo().length);
			LogUtil.logMessage("User ID in DAO :" + objSectionVO.getUserID());
			Connection dconn = ((DelegatingConnection) objConnnection).getInnermostDelegate(); //Added for CR-123
			
			arrdesc = new ArrayDescriptor(DatabaseConstants.STR_ARRAY,
					dconn);
			
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_VALIDATE_REQ_COMPGRPS);
			objCallableStatement.setInt(1, objSectionVO.getOrderKey());
			objCallableStatement.setString(2, objSectionVO
					.getDataLocationType());
			
			ARRAY arrCompSeqNo = new ARRAY(arrdesc, dconn,
					objSectionVO.getCompSeqNo());
			
			if (arrCompSeqNo.length() == 0) {
				
				objCallableStatement.setNull(3, Types.NULL);
			} else {
				
				objCallableStatement.setArray(3, arrCompSeqNo);
			}
			
			ARRAY arrCompGroupSeqNo = new ARRAY(arrdesc, dconn,
					objSectionVO.getComponentGroupSeqNo());
			
			if (arrCompGroupSeqNo.length() == 0) {
				
				objCallableStatement.setNull(4, Types.NULL);
			} else {
				
				objCallableStatement.setArray(4, arrCompGroupSeqNo);
			}
			LogUtil.logMessage("4 things SET");
			objCallableStatement.setString(5, objSectionVO.getUserID());
			
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			LogUtil.logMessage("8 things SET");
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0 ;
			}
			
			LogUtil
			.logMessage("Inside the updateModel method of ModelMaintDAO :intStatus .."
					+ intStatus);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(6);
			strOracleCode = (String) objCallableStatement.getString(7);
			strErrorMessage = (String) objCallableStatement.getString(8);
			
			//Added for LSDB_CR_71 for Server Side Component Validation
			objSectionVO.setMessage(strErrorMessage);
			LogUtil.logMessage("strErrorMessage:" + strErrorMessage);
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
			
		} catch (DataAccessException objDatExp) {
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
						objConnnection);
			} catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return objSectionVO;
		
	}	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0 Added for CR-72 Proof Reading PDF
	 * @param objSectionVO
	 *            The object for deleting temp Table
	 * @return integer
	 * @throws EMDException
	 **************************************************************************/
	
	public static synchronized int saveNewOrderComp(ComponentVO objComponentVO)
	throws EMDException {
		
		LogUtil.logMessage("Enters into OrderSectionDAO:saveNewOrderComp");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		
		String strOracleCode = null;
		String strErrorMessage = null;
		int intStatus=0;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
            objConnection = DBHelper.prepareConnection();
			
			
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_COPY_NEW_COMP_CLA);
			objCallableStatement.setInt(1, objComponentVO.getOrderKey());
			objCallableStatement.setString(2,objComponentVO.getNewOrderNo());
			objCallableStatement.setInt(3,objComponentVO.getComponentSeqNo());
			objCallableStatement.setString(4, objComponentVO.getUserID());
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
		    intStatus= objCallableStatement.executeUpdate();
			LogUtil.logMessage("int Status:" + intStatus);
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
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
	 * @version 1.0 Added for CR-109 to bring New Subsection to Order
	 * @param objSectionVO
	 * @return integer
	 * @throws EMDException
	 **************************************************************************/
	
	public static synchronized int addNewSubsecToOrder(SectionVO objSectionVO)
	throws EMDException {
		
		LogUtil.logMessage("Enters into OrderSectionDAO:addNewSubsecToOrder");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		
		String strOracleCode = null;
		String strErrorMessage = null;
		int intStatus=0;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
            objConnection = DBHelper.prepareConnection();
			
			
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_ADD_NEW_SUBSEC_TO_ORDR);
			objCallableStatement.setInt(1, objSectionVO.getOrderKey());
			objCallableStatement.setString(2,objSectionVO.getDataLocationType());
			objCallableStatement.setInt(3,objSectionVO.getSubSecSeqNo());
			objCallableStatement.setString(4, objSectionVO.getUserID());
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
		    intStatus= objCallableStatement.executeUpdate();
			LogUtil.logMessage("int Status:" + intStatus);
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
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
}