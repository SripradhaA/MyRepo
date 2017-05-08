package com.EMD.LSDB.dao.SpecComparison;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This DAO class is used to fetch section details for the selected
 *          order numbers.
 ******************************************************************************/

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
import com.EMD.LSDB.dao.common.DBHelper;
import com.EMD.LSDB.dao.common.EMDDAO;
import com.EMD.LSDB.dao.common.EMDQueries;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.OrderVO;

public class ComponentCompareDAO extends EMDDAO {
	
	private ComponentCompareDAO() {
		
	}
	
	/***************************************************************************
	 * This Method is used to fetch the list of section details for the selected
	 * orders.
	 * 
	 * @param java.util.ArrayList
	 * @return java.util.ArrayList
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetchSectionsAndSubSections(ArrayList objOrderList)
	throws EMDException {
		LogUtil
		.logMessage("Entering ComponentCompareDAO:fetchSectionsAndSubSections");
		Connection objConnnection = null;
		ArrayList arlFinalList = new ArrayList();
		ArrayList arlSections = new ArrayList();
		
		/** Error out parameters */
		ResultSet objOrdResultSet = null;
		CallableStatement objCallableStatement = null;
		ResultSet objComponentResultSet = null;
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		
		try {
			int orderListSize = objOrderList.size();
			
			/**Changed after Component Comparison Report Issue **/
			/** Connection is obtained. */
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			
			
			/** To fetch section detais of the orders. */
			for (int orderListlength = 0; orderListlength < orderListSize; orderListlength++) {
				
				String sectionName1 = "";
				String sectionName2 = "";
				
				
				
				/** To procedure is called. */
				objCallableStatement = objConnnection
				.prepareCall(EMDQueries.SP_COMPONENT_COMPARISON);
				
				/**
				 * OrderVO which contains order information is obtained from the
				 * arraylist..
				 */
				OrderVO objOrderVo = (OrderVO) objOrderList
				.get(orderListlength);
				
				LogUtil
				.logMessage("OrderKey Vlaue in ComponentCompareDAO:fetchSectionsAndSubSections:"
						+ objOrderVo.getOrderKey());
				
				/**
				 * Th values obtained from the ObjectVO is set to the procedure
				 * input.
				 */
				if (objOrderVo.getOrderKey() != 0) {
					objCallableStatement.setInt(1, objOrderVo.getOrderKey());
					
					LogUtil.logMessage("Order Key No in DAO"
							+ objOrderVo.getOrderKey());
				} else {
					objCallableStatement.setNull(1, Types.NULL);
				}
				
				if (objOrderVo.getDataLocTypeCode() != null) {
					objCallableStatement.setString(2, objOrderVo
							.getDataLocTypeCode());
					
					LogUtil.logMessage("Datalocation type Code in DAO"
							+ objOrderVo.getDataLocTypeCode());
				} else {
					objCallableStatement.setNull(2, Types.NULL);
				}
				
				if (objOrderVo.getSelectedSectionSeqNo() > 0) {
					objCallableStatement.setInt(3, objOrderVo
							.getSelectedSectionSeqNo());
					LogUtil.logMessage("Section Seq No in DAO"
							+ objOrderVo.getSelectedSectionSeqNo());
					
				} else {
					objCallableStatement.setNull(3, Types.NULL);
				}
				
				if (objOrderVo.getUserID() != null) {
					objCallableStatement.setString(4, objOrderVo.getUserID());
					
					LogUtil.logMessage("User ID" + objOrderVo.getUserID());
				} else {
					objCallableStatement.setNull(4, Types.NULL);
				}
				
				objCallableStatement
				.registerOutParameter(5, OracleTypes.CURSOR);
				
				objCallableStatement.registerOutParameter(6, Types.INTEGER);
				objCallableStatement.registerOutParameter(7, Types.VARCHAR);
				objCallableStatement.registerOutParameter(8, Types.VARCHAR);
				
				/** Procedure is executed.. */
				objCallableStatement.execute();
				
				/** ResultSet is obtained. */
				objOrdResultSet = (ResultSet) objCallableStatement.getObject(5);
				
				LogUtil
				.logMessage("Inside the fetchOrders method of ComponentCompareDAO :resultSet"
						+ objOrdResultSet);
				
				/** Error information is obtained if present. */
				intLSDBErrorID = (int) objCallableStatement.getInt(6);
				strOracleCode = (String) objCallableStatement.getString(7);
				strErrorMessage = (String) objCallableStatement.getString(8);
				
				/** Error Handling is done here based on the error type. */
				if (intLSDBErrorID != 0) {
					ErrorInfo objErrorInfo = new ErrorInfo();
					objErrorInfo.setMessageID("" + intLSDBErrorID);
					throw new DataAccessException(objErrorInfo);
					
				} else if (strOracleCode != null && !"0".equals(strOracleCode)) {
					
					LogUtil.logMessage("strOracleCode:" + strOracleCode);
					ErrorInfo objErrorInfo = new ErrorInfo();
					StringBuffer errorBuffer = new StringBuffer();
					errorBuffer.append(strOracleCode + " ");
					errorBuffer.append(strErrorMessage);
					objErrorInfo.setMessage(errorBuffer.toString());
					objConnnection.rollback();
					throw new ApplicationException(objErrorInfo);
				}
				
				ArrayList arlSubSectionList = new ArrayList();
				
				/** ResultSet is iterated to obtain the section details. */
				while (objOrdResultSet.next()) {
					
					ClauseVO objClauseVo = new ClauseVO();
					
					/** The obtained results are set in the ClauseVO. */
					
					sectionName2 = objOrdResultSet
					.getString(DatabaseConstants.SECTION_NAME);
					if (!(sectionName1.equals(sectionName2))) {
						
						objClauseVo.setSectionName(sectionName2);
						
					}
					
					sectionName1 = sectionName2;
					
					String subSectionName = objOrdResultSet.getString(3)
					+ "."
					+ objOrdResultSet
					.getString(DatabaseConstants.SUB_SEC_NAME);
					objClauseVo.setSubSectionName(subSectionName);
					
					/** ResultSet for component information is obtained. */
					objComponentResultSet = (ResultSet) objOrdResultSet
					.getObject(5);
					ArrayList arlComponent = new ArrayList();
					
					/** ResultSet is iterated to obtain the component details. */
					while (objComponentResultSet.next()) {
						ComponentVO objComponent = new ComponentVO();
						
						/**Added for CR-58 Component Comparison/Report by VV49326 18-Nov-08 - Starts Here**/
						
						objComponent
						.setComponentGroupSeqNo(objComponentResultSet
								.getInt(DatabaseConstants.COMP_GRP_SEQ_NO));
						
						/**Added for CR-58 Component Comparison/Report by VV49326 18-Nov-08 - Ends Here**/
						
						/** The obtained results are set in the ComponentVO. */
						objComponent.setComponentName(objComponentResultSet
								.getString(DatabaseConstants.COMP_NAME));
						
						objComponent
						.setComponentDescription(objComponentResultSet
								.getString(DatabaseConstants.COMP_DESC));
						/** The ComponentVO is added to the list. */
						
						/**Added for CR-58 Component Comparison/Report by VV49326 18-Nov-08 - Starts Here**/
						objComponent
						.setComponentIdentifier(objComponentResultSet
								.getString(DatabaseConstants.LS140_COMP_IDENTIFIER));
						
						objComponent
						.setComponentGroupName(objComponentResultSet
								.getString(DatabaseConstants.LS130_COMP_GRP_NAME));
						
						objComponent.setComponentGrpDesc(objComponentResultSet
								.getString(DatabaseConstants.COMP_GRP_DESC));
						
						objComponent.setCharFlag(objComponentResultSet
								.getString(DatabaseConstants.CHARZ_FLAG));
						
						objComponent
						.setCompGrpIdentifier(objComponentResultSet
								.getString(DatabaseConstants.COMP_GRP_IDENTIFIER));
						
						objComponent
						.setCompGrpValdFlag(objComponentResultSet
								.getString(DatabaseConstants.LS130_VALIDATION_FLAG));
						
						/**Added for CR-58 Component Comparison/Report by VV49326 18-Nov-08 - Ends Here**/
						
						arlComponent.add(objComponent);
						
					}
					
					/** The component list set in the ClauseVO. */
					objClauseVo.setComponentVO(arlComponent);
					
					/** The list is added to the final list. */
					arlSubSectionList.add(objClauseVo);
					
					/** The ResultSets are closed. */
					if (objComponentResultSet != null) {
						objComponentResultSet.close();
					}
				}
				if (objOrdResultSet != null) {
					objOrdResultSet.close();
				}
				
				if(objCallableStatement != null){
					objCallableStatement.close();
				}
				
				arlSections.add(arlSubSectionList);
			}
			
			/**
			 * This logic is to set the values obtained for each orders in to a
			 * list which contains the subsection details sequence wise.
			 */
			
			//int orderSize = arlSections.size();
			ClauseVO objEmptyVo = new ClauseVO();
			
			//Added for LSDB_CR-65 by ka57588
			/*** This will handle n > 1 orders, where n is the order number  ***/
			LogUtil
			.logMessage("More than three orders entering###################");
			LogUtil.logMessage("arlSections.size()###################"
					+ arlSections.size());
			int maxsize = 0;
			for (int k = 0; k < arlSections.size(); k++) {
				ArrayList arlsec = (ArrayList) arlSections.get(k);
				LogUtil.logMessage("arlsec.size()###################"
						+ arlsec.size());
				
				if (arlsec.size() > maxsize) {
					maxsize = arlsec.size();
				}
				
			}
			LogUtil.logMessage("maxsize###################:" + maxsize);
			for (int count = 0; count < maxsize; count++) {
				ArrayList arlSubsections = new ArrayList();
				LogUtil.logMessage("arlSections.size():" + arlSections.size());
				for (int k1 = 0; k1 < arlSections.size(); k1++) {
					ArrayList subsecsize = (ArrayList) arlSections.get(k1);
					if (count < subsecsize.size()) {
						arlSubsections.add(subsecsize.get(count));
					} else {
						arlSubsections.add(objEmptyVo);
					}
					
				}
				arlFinalList.add(arlSubsections);
			}
			
			LogUtil.logMessage("arlFinalList:" + arlFinalList);
			
			//Ends
			//Commented for LSDB_CR-65 by ka57588	
			
			/*if (orderSize == 1) {
			 ArrayList orderOne = (ArrayList) arlSections.get(0);
			 
			 int orderOneSize = orderOne.size();
			 
			 
			 *//** Maximum of the two list is obtained. */
			/*
			 int maximumSize = orderOneSize;
			 
			 
			 *//**
			 * This loop creates maximum number of list which will contains
			 * sequence wise subsection details.
			 */
			/*
			 for (int count = 0; count < maximumSize; count++) {
			 ArrayList arlSubsections = new ArrayList();
			 if (count < orderOneSize) {
			 arlSubsections.add(orderOne.get(count));
			 } else {
			 arlSubsections.add(objEmptyVo);
			 }
			 
			 arlFinalList.add(arlSubsections);
			 }
			 }
			 
			 if (orderSize == 2) {
			 ArrayList orderOne = (ArrayList) arlSections.get(0);
			 ArrayList orderTwo = (ArrayList) arlSections.get(1);
			 int orderOneSize = orderOne.size();
			 int orderTwoSize = orderTwo.size();
			 
			 *//** Maximum of the two list is obtained. */
			/*
			 int maximumSize = orderOneSize > orderTwoSize
			 ? orderOneSize
			 : orderTwoSize;
			 
			 *//**
			 * This loop creates maximum number of list which will contains
			 * sequence wise subsection details.
			 */
			/*
			 for (int count = 0; count < maximumSize; count++) {
			 ArrayList arlSubsections = new ArrayList();
			 if (count < orderOneSize) {
			 arlSubsections.add(orderOne.get(count));
			 } else {
			 arlSubsections.add(objEmptyVo);
			 }
			 if (count < orderTwoSize) {
			 arlSubsections.add(orderTwo.get(count));
			 } else {
			 arlSubsections.add(objEmptyVo);
			 }
			 arlFinalList.add(arlSubsections);
			 }
			 }
			 
			 if (orderSize == 3) {
			 ArrayList orderOne = (ArrayList) arlSections.get(0);
			 ArrayList orderTwo = (ArrayList) arlSections.get(1);
			 ArrayList orderThree = (ArrayList) arlSections.get(2);
			 int orderOneSize = orderOne.size();
			 int orderTwoSize = orderTwo.size();
			 int orderThreeSize = orderThree.size();
			 
			 *//** Maximum of the three list is obtained. */
			/*
			 int maximumSize = orderOneSize > orderTwoSize
			 ? (orderOneSize > orderThreeSize
			 ? orderOneSize
			 : orderThreeSize)
			 : (orderTwoSize > orderThreeSize
			 ? orderTwoSize
			 : orderThreeSize);
			 
			 *//**
			 * This loop creates maximum number of list which will contains
			 * sequence wise subsection details.
			 */
			/*
			 for (int count = 0; count < maximumSize; count++) {
			 ArrayList arlSubsections = new ArrayList();
			 if (count < orderOneSize) {
			 arlSubsections.add(orderOne.get(count));
			 } else {
			 arlSubsections.add(objEmptyVo);
			 }
			 if (count < orderTwoSize) {
			 arlSubsections.add(orderTwo.get(count));
			 } else {
			 arlSubsections.add(objEmptyVo);
			 }
			 if (count < orderThreeSize) {
			 arlSubsections.add(orderThree.get(count));
			 } else {
			 arlSubsections.add(objEmptyVo);
			 }
			 arlFinalList.add(arlSubsections);
			 }
			 }*/
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException ComponentCompareDAO:fetchSectionsAndSubSections");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in SectionDAO:fetchSections"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException ComponentCompareDAO:fetchSectionsAndSubSections");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in ComponentCompareDAO:fetchSectionsAndSubSections"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception ComponentCompareDAO:fetchSectionsAndSubSections");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		} finally {
			try {
				
				DBHelper.closeSQLObjects(objOrdResultSet, objCallableStatement,
						objConnnection);
			} catch (SQLException sqlex) {
				LogUtil
				.logMessage("Enters into Exception ComponentCompareDAO:fetchSectionsAndSubSections");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + sqlex.getMessage());
				throw new ApplicationException(sqlex, objErrorInfo);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception ComponentCompareDAO:fetchSectionsAndSubSections");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlFinalList;
		
	}
	
	/***************************************************************************
	 * This Method is used to fetch the list of Components which differs for the
	 * selected orders.
	 * 
	 * @param java.util.ArrayList
	 * @return java.util.ArrayList
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetchDifferenceComponent(OrderVO objOrderVO)
	throws EMDException {
		LogUtil
		.logMessage("Enter into ComponentCompareDAO:fetchDifferenceComponent");
		
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ArrayList objResultList = new ArrayList();
		ResultSet objresultSet = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strMessage = null;
		String strLogUser = "";
		ArrayDescriptor arrdesc = null;
		ARRAY arr = null;
		try {
			strLogUser = objOrderVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			
			LogUtil.logMessage("ModelSequenceNO:" + objOrderVO.getModelSeqNo());
			
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_COMPARE_ORDR_DIFF_COMP);
			Connection dconn = ((DelegatingConnection) objConnection).getInnermostDelegate(); //Added for CR-123 & Tomcat
			
			arrdesc = new ArrayDescriptor(DatabaseConstants.STR_ARRAY,
					dconn);//Modified for Tomcat
			arr = new ARRAY(arrdesc, dconn, objOrderVO.getOrderKeys());
			
			objCallableStatement.setArray(1, arr);
			
			objCallableStatement
			.setInt(2, objOrderVO.getSelectedSectionSeqNo());
			objCallableStatement.setInt(3, objOrderVO.getModelSeqNo());
			objCallableStatement.setString(4, objOrderVO.getUserID());
			objCallableStatement.registerOutParameter(5, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			objresultSet = (ResultSet) objCallableStatement.getObject(5);
			
			intLSDBErrorID = objCallableStatement.getInt(6);
			
			strOracleCode = objCallableStatement.getString(7);
			LogUtil.logMessage("OracleCode" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(8);
			
			LogUtil.logMessage("ErrorMessage" + strErrorMessage);
			
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
			
			while (objresultSet.next()) {
				LogUtil.logMessage("Enters into ResultSet Loop");
				
				ComponentVO objComponentVO = new ComponentVO();
				objComponentVO.setComponentGroupName(objresultSet
						.getString(DatabaseConstants.COMP_GRP_NAME));
				objComponentVO.setOrderOneComponentName(objresultSet
						.getString(DatabaseConstants.COMP_OK1));
				objComponentVO.setOrderOneCompDescName(objresultSet
						.getString(DatabaseConstants.COMP_DESC_OK1));
				objComponentVO.setOrderTwoComponentName(objresultSet
						.getString(DatabaseConstants.COMP_OK2));
				objComponentVO.setOrderTwoCompDescName(objresultSet
						.getString(DatabaseConstants.COMP_DESC_OK2));
				/*LogUtil.logMessage("CompGrpName:"
				 + objComponentVO.getComponentGroupName());
				 LogUtil.logMessage("CompNameone:"
				 + objComponentVO.getOrderOneComponentName());
				 LogUtil.logMessage("CompNameTwo:"
				 + objComponentVO.getOrderTwoComponentName());
				 LogUtil.logMessage("CompDescNameone:"
				 + objComponentVO.getOrderOneCompDescName());
				 LogUtil.logMessage("CompDescNameTwo:"
				 + objComponentVO.getOrderTwoCompDescName());*/
				objResultList.add(objComponentVO);
			}
		}
		
		catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException ComponentCompareDAO:fetchDifferenceComponent");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("ENters into catch block in ComponentCompareDAO:fetchDifferenceComponent"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException ComponentCompareDAO:fetchDifferenceComponent");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("ENters into catch block in ComponentCompareDAO:fetchDifferenceComponent"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception ComponentCompareDAO:fetchDifferenceComponent");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(objresultSet, objCallableStatement,
						objConnection);
			}
			
			catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception ComponentCompareDAO:fetchDifferenceComponent");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		LogUtil
		.logMessage("Leaves from ComponentCompareDAO:fetchDifferenceComponent ");
		LogUtil
		.logMessage("ArrayList value in ComponentCompareDAO:fetchDifferenceComponent:"
				+ objResultList);
		return objResultList;
		
	}
	
}