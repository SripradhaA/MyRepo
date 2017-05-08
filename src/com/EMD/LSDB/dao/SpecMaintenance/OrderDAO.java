/**
  * Created on Apr 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/***************************************************************************
 * --------------------------------------------------------------------------------------------------------
 *     Date         Version  Modified by          	comments                              Remarks 
 * 16/03/2012        1.0      SD41630    Added for customer and distributor logs flag    Added for CR_106
 * 										
 * --------------------------------------------------------------------------------------------------------
 * **************************************************************************/
package com.EMD.LSDB.dao.SpecMaintenance;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleConnection;

import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.apache.tomcat.dbcp.dbcp2.DelegatingConnection;//Added for Tomcat & CR-123

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
import com.EMD.LSDB.vo.common.CustomerVO;
import com.EMD.LSDB.vo.common.FileLocationVO;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SpecSuppVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for the Order
 ******************************************************************************/

public class OrderDAO extends EMDDAO {
	
	private OrderDAO() {
		
	}
	
	/***************************************************************************
	 * This Method is used to fetch the Orders
	 * 
	 * @param objOrderVo
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/	
	public static ArrayList fetchOrders(OrderVO objOrderVo) throws EMDException {
		LogUtil.logMessage("Entering OrderDAO.fetchOrders");
		Connection objConnnection = null;
		ArrayList arlOrderList = new ArrayList();
		CallableStatement objCallableStatement = null;
		// Error out parameters
		ResultSet objFileLoc = null;
		ResultSet objOrdResultSet = null;
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		//Added for CR_101 to bring the multiple customers in array.
		ArrayDescriptor arrdesc = null;
		//CR_101 Ends here.
		try {
			strLogUser = objOrderVo.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("Before Wrapping ");//Added for Tomcat
			/*arrdesc = new ArrayDescriptor(DatabaseConstants.STR_ARRAY,
					objConnnection);
			
			OracleCallableStatement objCallableStatement = objConnnection
					.prepareCall(EMDQueries.SP_SELECT_ORDER);
					
					*/
			
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_ORDER);
			LogUtil.logMessage("After callable statement Wrapping ");//Added for Tomcat fron here
			
			Connection dconn = ((DelegatingConnection) objConnnection).getInnermostDelegate(); //Added for CR-123 
			
			arrdesc = new ArrayDescriptor(DatabaseConstants.STR_ARRAY,
					dconn);//Updated for CR-123
			//Added for Tomcat ends here
			LogUtil.logMessage("OrderKey Vlaue in OrderDAO:fetchOrders:"
					+ objOrderVo.getOrderKey());
			if (objOrderVo.getOrderKey() != 0) {
				objCallableStatement.setInt(1, objOrderVo.getOrderKey());
			} else {
				objCallableStatement.setNull(1, Types.NULL);
			}
			
			objCallableStatement.setString(2, objOrderVo.getDataLocTypeCode());
			
			if (objOrderVo.getSpecTypeSeqNo() > 0) {
				objCallableStatement.setInt(3, objOrderVo.getSpecTypeSeqNo());
			} else {
				objCallableStatement.setNull(3, Types.NULL);
			}
			
			// Added for Proofreading Draft CR-72
			if (objOrderVo.getOrderNo() != null) {
				objCallableStatement.setString(4, objOrderVo.getOrderNo());
			} else {
				objCallableStatement.setNull(4, Types.NULL);
			}
			
			if (objOrderVo.getSpecStatusCode() > 0) {
				objCallableStatement.setInt(5, objOrderVo.getSpecStatusCode());
			} else {
				objCallableStatement.setNull(5, Types.NULL);
			}
			
			//Modified for CR_101 - Fetching orders by Customers 
			ARRAY arrCustomerSeqnos = null;
			
			LogUtil.logMessage("INside Customer Seq " + objOrderVo.getCusSeqNo());
			LogUtil.logMessage("INside Customer Seq Array" + objOrderVo.getCustomerSeqnos());
			
			if (objOrderVo.getCusSeqNo() != 0 && objOrderVo.getCusSeqNo() != -1) {
				
				int[] arrCustSeqNo = new int[1];
				arrCustSeqNo[0] = objOrderVo.getCusSeqNo();		
				
				objOrderVo.setCustomerSeqnos(arrCustSeqNo);		
				//Modified for Tomcat from here
				arrCustomerSeqnos = new ARRAY(arrdesc, dconn,
						objOrderVo.getCustomerSeqnos());//Updated for CR-123
			}
			else if (objOrderVo.getCustomerSeqnos() != null) {
				arrCustomerSeqnos = new ARRAY(arrdesc, dconn,
						objOrderVo.getCustomerSeqnos());//Updated for CR-123
			}
			else{	
				arrCustomerSeqnos = new ARRAY(arrdesc, dconn, null);  //Updated for CR-123
			} 
			//Modified for Tomcat ends here
			objCallableStatement.setArray(6, arrCustomerSeqnos);
			
			LogUtil.logMessage("After StrArray");			
			// Commented for CR_112 by ER91220 - Multiselect Model list box - Spec History Search		
			//CR_101 Ends here.
			/*
			if (objOrderVo.getModelSeqNo() > 0) {
				objCallableStatement.setInt(7, objOrderVo.getModelSeqNo());
			} else {
				objCallableStatement.setNull(7, Types.NULL);
			}
			*/
			//Added for CR_112 - To bring the Model Names 
			ARRAY arrModelSeqnos = null;
			
			if (objOrderVo.getModelSeqNo() != 0 && objOrderVo.getModelSeqNo() != -1) {
				
				int[] arrModelSeqNo = new int[1];
				arrModelSeqNo[0] = objOrderVo.getModelSeqNo();		
				
				objOrderVo.setModelSeqnos(arrModelSeqNo);		
				//Modified for Tomcat from here
				arrModelSeqnos = new ARRAY(arrdesc, dconn,
						objOrderVo.getModelSeqnos());//Updated for CR-123
			}
			else if (objOrderVo.getModelSeqnos() != null) {
				arrModelSeqnos = new ARRAY(arrdesc, dconn,
						objOrderVo.getModelSeqnos());//Updated for CR-123
			}
			else{	
				arrModelSeqnos = new ARRAY(arrdesc, dconn, null);  //Updated for CR-123
			} 
			//Modified for Tomcat ends here
			objCallableStatement.setArray(7, arrModelSeqnos);
			//CR_112 Ends here
			// Added for LSD_CR-76
			
			 if(objOrderVo.getSortBy()!=null ||
			 !"".equals(objOrderVo.getSortBy())){
			 objCallableStatement.setString(8,objOrderVo.getSortBy()); }else{
			 objCallableStatement.setNull(8,Types.NULL); }
			
			// Ends
			objCallableStatement.setString(9, objOrderVo.getUserID());
			
			//Added a parameter in CR_108 for show latest flag in "Order Specific Clause Report"
			if (objOrderVo.getShowLatestFlag() != null)
				objCallableStatement.setString(10, objOrderVo.getShowLatestFlag());
			else
				objCallableStatement.setNull(10, Types.NULL);
			
			objCallableStatement.registerOutParameter(11, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(12, Types.VARCHAR);
			objCallableStatement.registerOutParameter(13, Types.INTEGER);
			objCallableStatement.registerOutParameter(14, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			objOrdResultSet = (ResultSet) objCallableStatement.getObject(11);
			
			LogUtil
			.logMessage("Inside the fetchOrders method of OrderDAO :resultSet"
					+ objOrdResultSet);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(12);
			strOracleCode = (String) objCallableStatement.getString(13);
			strErrorMessage = (String) objCallableStatement.getString(14);
			//CR_108 Modification of column index ends here.
			// Handled Valid Exception
			if (intLSDBErrorID != 0) {
				
				ErrorInfo objErrorInfo = new ErrorInfo();
				
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				
				throw new DataAccessException(objErrorInfo);
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {
				
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer sb = new StringBuffer();
				sb.append(strOracleCode + " ");
				sb.append(strErrorMessage);
				objErrorInfo.setMessage(sb.toString());
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
			while (objOrdResultSet.next()) {
				
				objOrderVo = new OrderVO();
				CustomerVO objCustomerVo = new CustomerVO();
				ArrayList arlCustList = new ArrayList();
				ArrayList arlfileLoc = new ArrayList();
				
				objOrderVo.setOrderKey(objOrdResultSet
						.getInt(DatabaseConstants.ORDR_KEY));
				objOrderVo.setOrderNo(objOrdResultSet
						.getString(DatabaseConstants.ORDR_NO));
				objOrderVo.setStatusDesc(objOrdResultSet
						.getString(DatabaseConstants.STATUS));
				objOrderVo.setQuantity(objOrdResultSet
						.getInt(DatabaseConstants.ORDR_QTY));
				objOrderVo.setSapCusCode(objOrdResultSet
						.getString(DatabaseConstants.LS400_SAP_CUST_CODE));
				objOrderVo.setModelName(objOrdResultSet
						.getString(DatabaseConstants.LS200_MDL_NAME));
				objOrderVo.setModelSeqNo(objOrdResultSet
						.getInt(DatabaseConstants.LS200_MDL_SEQ_NO));
				objOrderVo.setSpecStatusCode(objOrdResultSet
						.getInt(DatabaseConstants.LS080_SPEC_STATUS_CODE));
				//Added for CR_104 Published User JG101007
				objOrderVo.setPublishedUser(objOrdResultSet
						.getString(DatabaseConstants.PUBLISHED_USER));
				
				// Added for LSDB_CR_46 PM&I change
				objOrderVo.setSpecTypeSeqNo(objOrdResultSet
						.getInt(DatabaseConstants.SPEC_TYPE_SEQ_NO));
				
				// Added for LSDB_CR_42 clause image
				objOrderVo.setAppendixFlag(objOrdResultSet
						.getString(DatabaseConstants.LS400_APENDX_FLAG));
				
				objCustomerVo.setCustTypeSeqNo(objOrdResultSet
						.getInt(DatabaseConstants.CUS_TYPE_SEQ_NO));
				LogUtil.logMessage("strOracleCode:"
						+ objOrdResultSet
						.getInt(DatabaseConstants.CUS_TYPE_SEQ_NO));
				objCustomerVo.setCustomerType(objOrdResultSet
						.getString(DatabaseConstants.CUST_TYPE));
				objCustomerVo.setCustomerSeqNo(objOrdResultSet
						.getInt(DatabaseConstants.CUST_SEQ_NO));
				objCustomerVo.setCustomerName(objOrdResultSet
						.getString(DatabaseConstants.CUST_NAME));
				objOrderVo.setCustomerName(objCustomerVo.getCustomerName());
				//Added For CR_106
				objOrderVo.setCustImageSeqNo(objOrdResultSet
						.getInt(DatabaseConstants.LS050_CUST_IMG_SEQ_NO));
				objOrderVo.setDistImageSeqNo(objOrdResultSet
						.getInt(DatabaseConstants.LS070_DISTRI_IMG_SEQ_NO));
				objOrderVo.setCustLogoFlag(objOrdResultSet.getString(DatabaseConstants.LS400_CUST_LOGO_FLAG));
				objOrderVo.setDistriLogoFlag(objOrdResultSet.getString(DatabaseConstants.LS400_DISTRI_LOGO_FLAG));
							
				objOrderVo.setPublishedDate(objOrdResultSet
						.getString(DatabaseConstants.LS400_UPDT_DATE));
				objOrderVo.setDistributorName(objOrdResultSet
						.getString(DatabaseConstants.DIST_NAME));
				
				// Added for CR-72 Proof Reading Draft
				objOrderVo.setSpecTypeSeqNo(objOrdResultSet
						.getInt(DatabaseConstants.SPEC_TYPE_SEQ_NO));
				
				/** * CR 90 Adding 'View Master Spec' link bottom of the section pages */
				objOrderVo.setSpecTypeName(objOrdResultSet
						.getString(DatabaseConstants.SPEC_TYPE));
				/** * CR 90 Adding 'View Master Spec' link bottom of the section End */
				
				String strRevCode = objOrdResultSet
				.getString(DatabaseConstants.SPEC_REV_CODE);
				
				// Added for CR-79 Adding PDF Header Image RR68151
				objOrderVo.setPdfHeaderFlag(objOrdResultSet
						.getString(DatabaseConstants.LS400_PDF_HDR_IMG_FLAG));
				// Ends here
				
//				 Added for CR-86 Adding DynamicnNumOnOff                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
				objOrderVo.setDynamicNoFlag(objOrdResultSet.getString(DatabaseConstants.LS400_DYN_CLA_NUMBR_FLAG));
				
				// Added for CR_97
				objOrderVo.setChangeControlFlag(objOrdResultSet.getString(DatabaseConstants.CHANGE_CONTROL_FLAG));
				//Ends Here.
				// Ends here
				
				//Added for CR_104 Custom Model Name and General Information text
				objOrderVo.setGenInfoText(objOrdResultSet.getString(DatabaseConstants.LS400_GEN_INFO_TEXT));
				//Added for CR-118
				objOrderVo.setGenInfoTextForProofReading(objOrdResultSet.getString(DatabaseConstants.GEN_TEXT_PROOF_READING));
				objOrderVo.setCustMdlName(objOrdResultSet.getString(DatabaseConstants.LS400_CUST_MDL_NAME));
				//Ends Here.
				//Added for CR_104 - Preserve Clause Markers
				objOrderVo.setUserMarkerFlag(objOrdResultSet.getString(DatabaseConstants.LS400_USER_MARKER_FLAG));
				//Ends Here.
				//Added for CR_104 - Preserve General information Text Flag
				objOrderVo.setPresrveGenInfoFlag(objOrdResultSet.getString(DatabaseConstants.LS400_PRESRVE_GENINFO_FLAG));
				//Ends here
				/**
				 * The Below code is Modified based on the requirement of
				 * LSDB_CR-74 on 08-June-09 by ps57222 *
				 */
				
				objOrderVo.setSpecRevCode(objOrdResultSet
						.getString(DatabaseConstants.SPEC_REV_CODE));
				//Added for CR_131
				objOrderVo.setSpecSubRevCode(objOrdResultSet
						.getInt(DatabaseConstants.SPEC_SUBLVL_REV_CODE ));
				//Ends here
				objFileLoc = (ResultSet) objOrdResultSet.getObject(16);
				ArrayList arlNoRevList = new ArrayList();
				ArrayList arlAllRevList = new ArrayList();
				ArrayList arlLatestRevList = new ArrayList();
				ArrayList arlAllRevFrmOPNList = new ArrayList();//Added for CR_92
				ArrayList arlSpecList = new ArrayList();
				//Added for CR_121 for Edl History
				ArrayList arlEDLList = new ArrayList();
				//Added for CR_121 for Edl History Ends
				
				String strSpecFlag = "";
				while (objFileLoc.next()) {
					FileLocationVO objFileLocationVO = new FileLocationVO();
					objFileLocationVO.setFileDesc(objFileLoc
							.getString(DatabaseConstants.LS090_FILE_TYPE_DESC));
					objFileLocationVO.setFileLoc(objFileLoc
							.getString(DatabaseConstants.LS410_FILE_LOC));
					objFileLocationVO.setRevViewSeqNo(objFileLoc
							.getInt(DatabaseConstants.LS081_REV_VIEW_SEQ_NO));
					// objFileLocationVO.setModifiedDate(objFileLoc.getString(DatabaseConstants.LS410_UPDT_DATE));
					
					LogUtil.logMessage("File Location:"
							+ objFileLocationVO.getFileLoc());
					LogUtil.logMessage("Rev Code:" + strRevCode);
					int intFileTypeCode = objFileLoc
					.getInt(DatabaseConstants.FILE_TYPE);
					//Added for CR_106 Spec Supplement Changes JG101007
					if (intFileTypeCode == 3 || intFileTypeCode == 4){
						arlSpecList.add(objFileLocationVO);
					}
					
					//Added for CR_121 for Edl History
					if (intFileTypeCode == 5 || intFileTypeCode == 6){
						arlEDLList.add(objFileLocationVO);
					}
					//Added for CR_121 for Edl History Ends
					
					if (intFileTypeCode == 1 || intFileTypeCode == 2) {
						if (objFileLoc
								.getInt(DatabaseConstants.LS081_REV_VIEW_SEQ_NO) == 1) {
							arlNoRevList.add(objFileLocationVO);
							
						} else if (objFileLoc
								.getInt(DatabaseConstants.LS081_REV_VIEW_SEQ_NO) == 2) {
							arlLatestRevList.add(objFileLocationVO);
							
						} else if (objFileLoc
								.getInt(DatabaseConstants.LS081_REV_VIEW_SEQ_NO) == 3) {
							arlAllRevList.add(objFileLocationVO);
							
						}//Added for CR_92 for All rev from Opening Spec's 
						else if (objFileLoc
								.getInt(DatabaseConstants.LS081_REV_VIEW_SEQ_NO) == 4) {
							arlAllRevFrmOPNList.add(objFileLocationVO);
						}
						strSpecFlag = "N";
						
					} else {
						strSpecFlag = "Y";
					}
				}
				LogUtil.logMessage("Size of Spec Supplement List:"
						+ arlSpecList.size());
				arlfileLoc.add(arlNoRevList);
				arlfileLoc.add(arlLatestRevList);
				arlfileLoc.add(arlAllRevList);
				arlfileLoc.add(arlAllRevFrmOPNList);//Added fro CR_92 for All rev from opening
				arlfileLoc.add(arlSpecList);
				//Added for CR_121 for Edl History
				arlfileLoc.add(arlEDLList);
				//Added for CR_121 for Edl History Ends
				objOrderVo.setFileLoc(arlfileLoc);
				objOrderVo.setRevFlag(strSpecFlag);
				LogUtil.logMessage("SAPCustomer Code :****"
						+ objOrderVo.getSapCusCode());
				LogUtil.logMessage("OrderNo  :****" + objOrderVo.getOrderNo());
				LogUtil.logMessage("StatusDesc Code :****"
						+ objOrderVo.getStatusDesc());
				LogUtil.logMessage("ModelName Code :****"
						+ objOrderVo.getModelName());
				arlCustList.add(objCustomerVo);
				
				objOrderVo.setCustomerVo(arlCustList);
				
				arlOrderList.add(objOrderVo);
			}
			
		} catch (DataAccessException objDataEx) {
			ErrorInfo objErrorInfo = objDataEx.getErrorInfo();
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objDataEx.getErrorInfo().getMessage());
			throw new BusinessException(objDataEx, objErrorInfo);
		} catch (ApplicationException objAppEx) {
			ErrorInfo objErrorInfo = objAppEx.getErrorInfo();
			LogUtil.logMessage("Enters into AppException block in  DAO :"
					+ objErrorInfo.getMessage());
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			throw new ApplicationException(objAppEx, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(objOrdResultSet, objCallableStatement,
						objConnnection);
			} catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception block in DAO:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		LogUtil.logMessage("OrderList Size" + arlOrderList.size());
		return arlOrderList;
		
	}
	
	/***************************************************************************
	 * This Method is used to fetch the Orders
	 * 
	 * @param objOrderVo
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public static ArrayList fetchOrdersForSpecComparison(OrderVO objOrderVo)
	throws EMDException {
		
		LogUtil.logMessage("Entering OrderDAO.fetchOrdersForSpecComparison");
		Connection objConnnection = null;
		ArrayList arlOrderList = new ArrayList();
		CallableStatement objCallableStatement = null;
		// Error out parameters
		ResultSet objOrdResultSet = null;
		ResultSet objSectionResultSet = null;
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		ARRAY objArray = null;
		ArrayDescriptor arrayDescriptor = null;
		String strLogUser = "";
		try {
			strLogUser = objOrderVo.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_CLAUSE_COMPARISON);
			Connection dconn = ((DelegatingConnection) objConnnection).getInnermostDelegate(); //Added for CR-123 & Tomcat
			
			if (objOrderVo.getModelSelected().length != 0) {
				arrayDescriptor = new ArrayDescriptor(
						DatabaseConstants.STR_ARRAY, dconn);
				objArray = new ARRAY(arrayDescriptor, dconn,
						objOrderVo.getModelSelected());
				objCallableStatement.setArray(1, objArray);
				
			} else {
				objCallableStatement.setNull(2, Types.NULL);
			}
			
			if (objOrderVo.getOrderNo() != null) {
				objCallableStatement.setString(2, objOrderVo.getOrderNo());
			} else {
				objCallableStatement.setNull(2, Types.NULL);
			}
			
			if (objOrderVo.getSpecTypeSeqNo() != 0) {
				objCallableStatement.setInt(3, objOrderVo.getSpecTypeSeqNo());
			} else {
				objCallableStatement.setNull(3, Types.NULL);
			}
			objCallableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			

//			CR 87
			if (objOrderVo.getSortByFlag() != null) {
				objCallableStatement.setString(5, objOrderVo.getSortByFlag());
			} else {
				objCallableStatement.setNull(5, Types.NULL);
			}

			if (objOrderVo.getUserID() != null) {
				objCallableStatement.setString(6, objOrderVo.getUserID());
			} else {
				objCallableStatement.setNull(6, Types.NULL);
				//Changed 7 to 6 for CR_104
			}
			//Added for CR_104 for Show latest published spec
			objCallableStatement.setString(7, objOrderVo.getShowLatestFlag());
			LogUtil.logMessage("Value of Show latest Flag in DAO :" + objOrderVo.getShowLatestFlag());
			//CR_104 Ends here
			objCallableStatement.registerOutParameter(8, Types.INTEGER);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			objCallableStatement.registerOutParameter(10, Types.VARCHAR);	
			
//			objCallableStatement.registerOutParameter(6, Types.INTEGER);
//			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
//			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			objOrdResultSet = (ResultSet) objCallableStatement.getObject(4);
			
//			intLSDBErrorID = (int) objCallableStatement.getInt(6);
//			strOracleCode = (String) objCallableStatement.getString(7);
//			strErrorMessage = (String) objCallableStatement.getString(8);
			
//			CR 87
			intLSDBErrorID = (int) objCallableStatement.getInt(8);
			strOracleCode = (String) objCallableStatement.getString(9);
			strErrorMessage = (String) objCallableStatement.getString(10);
			
			// Handled Valid Exception
			if (intLSDBErrorID != 0) {
				
				ErrorInfo objErrorInfo = new ErrorInfo();
				
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				
				throw new DataAccessException(objErrorInfo);
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {
				
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer sb = new StringBuffer();
				sb.append(strOracleCode + " ");
				sb.append(strErrorMessage);
				objErrorInfo.setMessage(sb.toString());
				objConnnection.rollback();
				
				throw new ApplicationException(objErrorInfo);
			}
			
			while (objOrdResultSet.next()) {
				
				OrderVO objResultOrderVo = new OrderVO();
				objResultOrderVo.setOrderNo(objOrdResultSet
						.getString(DatabaseConstants.ORDR_NO));
				objResultOrderVo.setOrderKey(objOrdResultSet
						.getInt(DatabaseConstants.ORDR_KEY));
				CustomerVO objCustomerVo = new CustomerVO();
				objCustomerVo.setCustomerName(objOrdResultSet
						.getString(DatabaseConstants.CUST_NAME));
				objResultOrderVo.setCustomerName(objOrdResultSet
						.getString(DatabaseConstants.CUST_NAME));
				objCustomerVo.setCustomerType(objOrdResultSet
						.getString(DatabaseConstants.CUST_TYPE));
				ArrayList customerList = new ArrayList();
				customerList.add(objCustomerVo);
				objResultOrderVo.setCustomerVo(customerList);
				objResultOrderVo.setModelName(objOrdResultSet
						.getString(DatabaseConstants.MDL_NAME));
				/**
				 * * ModelSeqNo is Added For LSDB_CR-06 , Added on 15-April-08,
				 * by ps57222 ***
				 */
				
				objResultOrderVo.setModelSeqNo(objOrdResultSet
						.getInt(DatabaseConstants.MODEL_SEQ_NO));
				objResultOrderVo.setSpecTypeName(objOrdResultSet
						.getString(DatabaseConstants.SPEC_STATUS));
				objResultOrderVo.setCustMdlName(objOrdResultSet
						.getString(DatabaseConstants.LS400_CUST_MDL_NAME));
				LogUtil.logMessage("objResultOrderVo LS400_CUST_MDL_NAME:" + objResultOrderVo.getCustMdlName());
				
				//Added for CR_131 Starts
				objResultOrderVo.setSpecStatusCode(objOrdResultSet
						.getInt(DatabaseConstants.SPEC_STATUS_CODE));
				LogUtil.logMessage("objResultOrderVo SPEC_STATUS_CODE:" + objResultOrderVo.getSpecStatusCode());
				objResultOrderVo.setSpecRevCode(objOrdResultSet
						.getString(DatabaseConstants.SPEC_REV_CODE));
				LogUtil.logMessage("objResultOrderVo SPEC_REV_CODE:" + objResultOrderVo.getSpecRevCode());
				objResultOrderVo.setSpecSubRevCode(objOrdResultSet
						.getInt(DatabaseConstants.SPEC_SUBLVL_REV_CODE));
				LogUtil.logMessage("objResultOrderVo SPEC_SUBLVL_REV_CODE:" + objResultOrderVo.getSpecSubRevCode());
				//Added for CR_131 Ends
			
				objSectionResultSet = (ResultSet) objOrdResultSet.getObject(8);
				
				ArrayList arlSectionNameList = new ArrayList();
				
				while (objSectionResultSet.next()) {
					
					SectionVO objSectionVo = new SectionVO();
					objSectionVo.setSectionName(new String(objSectionResultSet
							.getString(DatabaseConstants.SECTION_NAME)));
					objSectionVo.setSectionSeqNo(objSectionResultSet
							.getInt(DatabaseConstants.LS201_SEC_SEQ_NO));
					
					arlSectionNameList.add(objSectionVo);
					
				}
				
				objResultOrderVo.setSectionName(arlSectionNameList);
				arlOrderList.add(objResultOrderVo);
			}
			
		} catch (DataAccessException objDataEx) {
			ErrorInfo objErrorInfo = objDataEx.getErrorInfo();
			LogUtil.logMessage("Enters into catch block in DAO:.."
					+ objDataEx.getErrorInfo().getMessageID());
			objDataEx.printStackTrace();
			throw new BusinessException(objDataEx, objErrorInfo);
		} catch (ApplicationException objAppEx) {
			ErrorInfo objErrorInfo = objAppEx.getErrorInfo();
			LogUtil.logMessage("Enters into AppException block in  DAO :"
					+ objErrorInfo.getMessage());
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			throw new ApplicationException(objAppEx, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		} finally {
			try {
				DBHelper.closeSQLObjects(objOrdResultSet, objCallableStatement,
						objConnnection);
			} catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception block:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
		}
		return arlOrderList;
	}
	
	/***************************************************************************
	 * This Method is used to fetch the Orders for Modify Spec
	 * 
	 * @param objOrderVo
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public static ArrayList fetchOrdersModifySpec(OrderVO objOrderVo)
	throws EMDException {
		LogUtil.logMessage("Entering OrderDAO.fetchOrdersModifySpec");
		Connection objConnnection = null;
		ArrayList arlOrderList = new ArrayList();
		CallableStatement objCallableStatement = null;
		// Error out parameters
		ResultSet objOrdResultSet = null;
		ResultSet objToOrderKey = null;
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		OrderVO objOrderTOOrderKey = null;
		//ArrayDescriptor arrdesc = null;
		try {
			strLogUser = objOrderVo.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_WORK_ORDER);
			
			if (objOrderVo.getSpecTypeSeqNo() > 0) {
				objCallableStatement.setInt(1, objOrderVo.getSpecTypeSeqNo());
				
			} else {
				objCallableStatement.setNull(1, Types.NULL);
				
			}
			
			if (!"".equals(objOrderVo.getOrderNo())
					&& objOrderVo.getOrderNo() != null) {
				objCallableStatement.setString(2, objOrderVo.getOrderNo());
			} else {
				
				objCallableStatement.setNull(2, Types.NULL);
			}
			
			if (objOrderVo.getModelSeqNo() > 0) {
				objCallableStatement.setInt(3, objOrderVo.getModelSeqNo());
				
			} else {
				objCallableStatement.setNull(3, Types.NULL);
			}
			
			if (objOrderVo.getSpecStatusCode() > 0) {
				objCallableStatement.setInt(4, objOrderVo.getSpecStatusCode());
				
			} else {
				objCallableStatement.setNull(4, Types.NULL);
			}
			
			// Added for LSDB_CR-75 on 06-May-09 by cm68219
			if (!"".equals(objOrderVo.getCustSeqNos())
					&& objOrderVo.getCustSeqNos() != null
					&& !"-1".equals(objOrderVo.getCustSeqNos())) {
				
				objCallableStatement.setString(5, objOrderVo.getCustSeqNos());
				
			} else {
				
				objCallableStatement.setNull(5, Types.NULL);
				LogUtil.logMessage("Customer Seq Nos else part..");
			}
			
			//Added for LSDb_CR-76
			 if(objOrderVo.getSortBy()!=null ||
					 !"".equals(objOrderVo.getSortBy())){
					 objCallableStatement.setString(6,objOrderVo.getSortBy()); 
			 }else{
					 objCallableStatement.setNull(6,Types.NULL); 
			 }
			objCallableStatement.registerOutParameter(7, OracleTypes.CURSOR);
			
			objCallableStatement.setString(8, objOrderVo.getUserID());
			
			objCallableStatement.registerOutParameter(9, Types.INTEGER);
			objCallableStatement.registerOutParameter(10, Types.VARCHAR);
			objCallableStatement.registerOutParameter(11, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			objOrdResultSet = (ResultSet) objCallableStatement.getObject(7);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(9);
			strOracleCode = (String) objCallableStatement.getString(10);
			strErrorMessage = (String) objCallableStatement.getString(11);
			
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
			
			while (objOrdResultSet.next()) {
				
				objOrderVo = new OrderVO();
				CustomerVO objCustomerVo = new CustomerVO();
				ArrayList arlCustList = new ArrayList();
				
				objOrderVo.setOrderKey(objOrdResultSet
						.getInt(DatabaseConstants.ORDR_KEY));
				objOrderVo.setOrderNo(objOrdResultSet
						.getString(DatabaseConstants.ORDR_NO));
				objOrderVo.setStatusDesc(objOrdResultSet
						.getString(DatabaseConstants.STATUS));
				objOrderVo.setQuantity(objOrdResultSet
						.getInt(DatabaseConstants.ORDR_QTY));
				objOrderVo.setSpecStatusCode(objOrdResultSet
						.getInt(DatabaseConstants.LS080_SPEC_STATUS_CODE));
				//Added For CR_99
				objOrderVo.setSpecSubRevCode(objOrdResultSet
						.getInt(DatabaseConstants.SPEC_SUBLVL_REV_CODE));
				//CR_99 Ends here
				//Added For CR_131
				objOrderVo.setSpecRevCode(objOrdResultSet
						.getString(DatabaseConstants.SPEC_REV_CODE));
				//CR_131 Ends here
				
				objOrderVo.setModelName(objOrdResultSet
						.getString(DatabaseConstants.LS200_MDL_NAME));
				objCustomerVo.setCustTypeSeqNo(objOrdResultSet
						.getInt(DatabaseConstants.CUS_TYPE_SEQ_NO));
				objCustomerVo.setCustomerName(objOrdResultSet
						.getString(DatabaseConstants.CUST_NAME));
				
				// Added for update SAP Customer Code CR
				objOrderVo.setSapCusCode(objOrdResultSet
						.getString(DatabaseConstants.LS400_SAP_CUST_CODE));
				arlCustList.add(objCustomerVo);
				objOrderVo.setCustomerVo(arlCustList);
				
				// Added for LSDB_CR_46 PM&I Change
				objOrderVo.setSpecTypeSeqNo(objOrdResultSet
						.getInt(DatabaseConstants.SPEC_TYPE_SEQ_NO));
				
				//Added for LSDB_CR-76
				objToOrderKey = (ResultSet) objOrdResultSet.getObject(DatabaseConstants.SS_ORDR_DETAILS);
				objOrderTOOrderKey = null;
				ArrayList arlToOrderKey = new ArrayList();
				while(objToOrderKey.next()){
					objOrderTOOrderKey = new OrderVO();
					objOrderTOOrderKey.setOrderKey(objToOrderKey.getInt(DatabaseConstants.LS400_ORDR_KEY));
					objOrderTOOrderKey.setStatusDesc(objToOrderKey.getString(DatabaseConstants.STATUS));
					arlToOrderKey.add(objOrderTOOrderKey);
					
				}
				//Added For CR_104 
				objOrderVo.setCustMdlName(objOrdResultSet
						.getString(DatabaseConstants.LS400_CUST_MDL_NAME));
				if(objOrderVo.getCustMdlName()==null)
				{
					objOrderVo.setCustMdlName("");
				}
				// Added for CR_104 - Preserve User Markers - ER91220	
				objOrderVo.setUserMarkerFlag(objOrdResultSet
						.getString(DatabaseConstants.LS400_USER_MARKER_FLAG));
				//Ends here			
				//Added for CR_104 - Preserve General Information Text Flag- ER91220  
				objOrderVo.setPresrveGenInfoFlag(objOrdResultSet
						.getString(DatabaseConstants.LS400_PRESRVE_GENINFO_FLAG));
				//Ends here
				objOrderVo.setSsOrderKeys(arlToOrderKey);
				
				DBHelper.closeSQLObjects(objToOrderKey, null, null);
				
				arlOrderList.add(objOrderVo);
			}
			
		} catch (DataAccessException objDataEx) {
			ErrorInfo objErrorInfo = objDataEx.getErrorInfo();
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objDataEx.getErrorInfo().getMessage());
			throw new BusinessException(objDataEx, objErrorInfo);
		} catch (ApplicationException objAppEx) {
			ErrorInfo objErrorInfo = objAppEx.getErrorInfo();
			LogUtil.logMessage("Enters into AppException block in  DAO :"
					+ objErrorInfo.getMessage());
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			throw new ApplicationException(objAppEx, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			objExp.printStackTrace();
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(objOrdResultSet, objCallableStatement,
						objConnnection);
			} catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception block:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlOrderList;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 *            the object for inserting Order
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public static int insertOrder(OrderVO objOrderVO) throws EMDException {
		LogUtil.logMessage("Entering OrderDAO.insertOrder");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		
		try {
			strLogUser = objOrderVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			
			LogUtil.logMessage("objOrderVO.getRevCode() :"
					+ objOrderVO.getRevCode());
			objCallableStatement = objConnection
			.prepareCall(EMDQueries.SP_CREATE_ORDER_SPECS);
			objCallableStatement.setInt(1, objOrderVO.getSpecTypeSeqNo());
			
			if (objOrderVO.getDistSeqNo() == 0) {
				
				objCallableStatement.setNull(2, Types.NULL);
			} else {
				objCallableStatement.setInt(2, objOrderVO.getDistSeqNo());
			}
			objCallableStatement.setInt(3, objOrderVO.getCusSeqNo());
			objCallableStatement.setInt(4, objOrderVO.getModelCustTypeSeqNo());
			objCallableStatement.setInt(5, objOrderVO.getModelSeqNo());
			objCallableStatement.setString(6, objOrderVO.getSapCusCode());
			objCallableStatement.setString(7, objOrderVO.getOrderNo());
			objCallableStatement.setInt(8, objOrderVO.getQuantity());
			objCallableStatement.registerOutParameter(9, Types.INTEGER);
			
			objCallableStatement.setInt(10, objOrderVO.getRevCode());// No
			// Revision
			
			objCallableStatement.setString(11, objOrderVO.getUserID());
			objCallableStatement.registerOutParameter(12, Types.INTEGER);
			objCallableStatement.registerOutParameter(13, Types.VARCHAR);
			objCallableStatement.registerOutParameter(14, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			LogUtil
			.logMessage("Inside the insertOrder method of OrderDAO :intStatus .."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(12);
			strOracleCode = (String) objCallableStatement.getString(13);
			strErrorMessage = (String) objCallableStatement.getString(14);
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
			
			if (intStatus > 0) {
				
				intStatus = 0;
				// Added for redirecting to Model Info Screen
				intStatus = (int) objCallableStatement.getInt(9);
			}
			
		}
		
		catch (DataAccessException objDatExp) {
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
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:"
					+ objExp.getMessage());
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
				LogUtil.logMessage("Enters into Exception block:");
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
	 * @param objOrderVO
	 *            the object for inserting Order
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public static int insertCopySpec(OrderVO objOrderVO) throws EMDException {
		LogUtil.logMessage("Entering OrderDAO.insertCopySpec");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
			strLogUser = objOrderVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_COPY_ORDER_SPECS);
			
			objCallableStatement.setInt(1, objOrderVO.getOrderKey());
			
			objCallableStatement.setInt(2, objOrderVO.getSpecTypeSeqNo());
			
			if (objOrderVO.getDistSeqNo() == 0) {
				
				objCallableStatement.setNull(3, Types.NULL);
			} else {
				objCallableStatement.setInt(3, objOrderVO.getDistSeqNo());
			}
			objCallableStatement.setInt(4, objOrderVO.getCusSeqNo());
			objCallableStatement.setInt(5, objOrderVO.getModelSeqNo());
			objCallableStatement.setString(6, objOrderVO.getSapCusCode());
			objCallableStatement.setString(7, objOrderVO.getOrderNo());
			objCallableStatement.setInt(8, objOrderVO.getQuantity());
			objCallableStatement.setInt(9,
					ApplicationConstants.DEFAULT_REV_CODE);// No Revision
			
			objCallableStatement.setString(10, objOrderVO.getUserID());
			//Added Parameter for Clear All Indicators as part of CR_108
			objCallableStatement.setString(11, objOrderVO.getCopyMdlIndFlag());
			
			objCallableStatement.registerOutParameter(12, Types.INTEGER);
			objCallableStatement.registerOutParameter(13, Types.VARCHAR);
			objCallableStatement.registerOutParameter(14, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the insertCopySpec method of OrderDAO :intStatus .."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(12);
			strOracleCode = (String) objCallableStatement.getString(13);
			strErrorMessage = (String) objCallableStatement.getString(14);
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
		
		catch (DataAccessException objDatExp) {
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
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:"
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
				LogUtil.logMessage("Enters into Exception block:");
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
	 * @param objOrderVO
	 *            the object for inserting Order
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public static int insertCopySpecFromModel(OrderVO objOrderVO)
	throws EMDException {
		LogUtil.logMessage("Entering OrderDAO.insertCopySpecFromModel");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
			strLogUser = objOrderVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_COPY_MDL_SPECS);
			LogUtil.logMessage("objOrderVO.getOrderKey() .."
					+ objOrderVO.getOrderKey());
			
			objCallableStatement.setInt(1, objOrderVO.getOrderKey());
			
			objCallableStatement.setInt(2, objOrderVO.getSpecTypeSeqNo());
			
			if (objOrderVO.getDistSeqNo() == 0) {
				
				objCallableStatement.setNull(3, Types.NULL);
			} else {
				objCallableStatement.setInt(3, objOrderVO.getDistSeqNo());
			}
			objCallableStatement.setInt(4, objOrderVO.getCusSeqNo());
			objCallableStatement.setInt(5, objOrderVO.getModelSeqNo());
			objCallableStatement.setString(6, objOrderVO.getSapCusCode());
			objCallableStatement.setString(7, objOrderVO.getOrderNo());
			objCallableStatement.setInt(8, objOrderVO.getQuantity());
			
			objCallableStatement.setInt(9,
					ApplicationConstants.DEFAULT_REV_CODE);// no revision
			
			objCallableStatement.setString(10, objOrderVO.getUserID());
			//Added Parameter for Clear All Indicators as part of CR_108
			objCallableStatement.setString(11, objOrderVO.getCopyMdlIndFlag());
			
			objCallableStatement.registerOutParameter(12, Types.INTEGER);
			objCallableStatement.registerOutParameter(13, Types.VARCHAR);
			objCallableStatement.registerOutParameter(14, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the insertCopySpecFromModel method of OrderDAO :intStatus .."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(12);
			strOracleCode = (String) objCallableStatement.getString(13);
			strErrorMessage = (String) objCallableStatement.getString(14);
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
		
		catch (DataAccessException objDatExp) {
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
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:"
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
				LogUtil.logMessage("Enters into Exception block:");
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
	 * @param objOrderVO
	 *            the object for Updating Order
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public static int updateOrder(OrderVO objOrderVO) throws EMDException {
		LogUtil.logMessage("Entering OrderDAO.updateOrder");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
			strLogUser = objOrderVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_UPDATE_ORDER);
			
			objCallableStatement.setString(1, objOrderVO.getDataLocTypeCode());
			objCallableStatement.setInt(2, objOrderVO.getOrderKey());
			
			objCallableStatement.setString(3, null);
			objCallableStatement.setString(4, null);
			
			if (objOrderVO.getQuantity() != 0) {
				objCallableStatement.setInt(5, objOrderVO.getQuantity());
			} else {
				objCallableStatement.setNull(5, Types.NULL);
			}
			
			objCallableStatement.setString(6, objOrderVO.getUserID());
			
			// Added for Update SAP Customer Code CR
			if (objOrderVO.getSapCusCode() != null) {
				objCallableStatement.setString(7, objOrderVO.getSapCusCode());
			} else {
				objCallableStatement.setNull(7, Types.NULL);
			}
			
			/*******************************************************************
			 * Added AppendixTurn ON/OFF flag based on the requirement of
			 * LSDB_CR-42 Added on 08-05-08 by ps57222
			 */
			
			if (objOrderVO.getAppendixFlag() != null) {
				
				objCallableStatement.setString(8, objOrderVO.getAppendixFlag());
			} else {
				
				objCallableStatement.setNull(8, Types.NULL);
			}
			
			/*******************************************************************
			 * Added PDF Header Image ON/OFF flag based on the requirement of
			 * LSDB_CR-79 Added on 29-10-09 by rr68151
			 */
			
			if (objOrderVO.getPdfHeaderFlag() != null) {
				
				objCallableStatement.setString(9, objOrderVO.getPdfHeaderFlag());
			} else {
				
				objCallableStatement.setNull(9, Types.NULL);
			}
			
			//Added For CR_104 Saving General Information Text by RR68151
			if (objOrderVO.getGenInfoText() != null) {
				objCallableStatement.setString(10, objOrderVO.getGenInfoText());
			} else {
				objCallableStatement.setNull(10, Types.NULL);
			}
			
			if (objOrderVO.getCustMdlName() != null) {
				objCallableStatement.setString(11, objOrderVO.getCustMdlName());
			} else {
				objCallableStatement.setNull(11, Types.NULL);
			}
			//Added For CR_104 Saving General Information Text by RR68151 - Ends here
			
			//Added for CR_104 for User Marker Flag - ER91220	
			if (objOrderVO.getUserMarkerFlag() != null) {
				objCallableStatement.setString(12, objOrderVO.getUserMarkerFlag());
			} else {
				objCallableStatement.setNull(12, Types.NULL);
			}
		//Added for CR_104 - ER91220	- preserve General Information Text
			if (objOrderVO.getPresrveGenInfoFlag() != null){
				objCallableStatement.setString(13,objOrderVO.getPresrveGenInfoFlag());
			} else {
				objCallableStatement.setNull(13, Types.NULL);
			}
		//CR_104 Ends here
//			Added for CR_106 - customer logo flag
			if (objOrderVO.getCustLogoFlag() != null){
				objCallableStatement.setString(14,objOrderVO.getCustLogoFlag());
			} else {
				objCallableStatement.setNull(14, Types.NULL);
			}
		//CR_104 Ends here
//			Added for CR_106 - distributor logo flag
			if (objOrderVO.getDistriLogoFlag() != null){
				objCallableStatement.setString(15,objOrderVO.getDistriLogoFlag());
			} else {
				objCallableStatement.setNull(15, Types.NULL);
			}
			
			LogUtil.logMessage(" in the order dao"+objOrderVO.getDistriLogoFlag());
		//CR_104 Ends here
			objCallableStatement.registerOutParameter(16, Types.VARCHAR);
			
			objCallableStatement.registerOutParameter(17, Types.INTEGER);
			
			objCallableStatement.registerOutParameter(18, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the updateOrder method of OrderDAO :intStatus .."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(16);
			strOracleCode = (String) objCallableStatement.getString(17);
			strErrorMessage = (String) objCallableStatement.getString(18);
			
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
		
		catch (DataAccessException objDatExp) {
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
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:"
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
				LogUtil.logMessage("Enters into Exception block:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return intStatus;
		
	}
	
	/*******************************************************************
	 * Added AppendixTurn ON/OFF flag based on the requirement of
	 * LSDB_CR-86 Added by SD41630
	 ******************************************************************/
//CR_86
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 *            the object for turnDynamicNumOnOff
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public static int turnDynamicNumOnOff(OrderVO objOrderVO) throws EMDException {
		LogUtil.logMessage("Entering OrderDAO.turnDynamicNumOnOff");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
			strLogUser = objOrderVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_ONOFF_DYNMIC_CLAUSE_NO);
			
			objCallableStatement.setInt(1, objOrderVO.getOrderKey());
			objCallableStatement.setString(2, objOrderVO.getDataLocTypeCode());
			objCallableStatement.setString(3,objOrderVO.getDynamicNoFlag());
			objCallableStatement.setString(4,objOrderVO.getUserID());
		
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the updateOrder method of OrderDAO :intStatus .."
					+ intStatus);
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
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
		}
		
		catch (DataAccessException objDatExp) {
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
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:"
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
				LogUtil.logMessage("Enters into Exception block:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return intStatus;
		
	}
	
	//CR_86 Ends here
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 *            the object for publishing the Order
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public static OrderVO prePublishOrder(OrderVO objOrderVO)
	throws EMDException {
		LogUtil.logMessage("Entering OrderDAO.prePublishOrder");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		
		try {
			strLogUser = objOrderVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_PREPUBLISH_ORDER);
			
			LogUtil.logMessage("objOrderVO.getOrderKey() :"
					+ objOrderVO.getOrderKey());
			LogUtil.logMessage("objOrderVO.getRevFlag() :"
					+ objOrderVO.getRevFlag());
			LogUtil.logMessage("objOrderVO.getFinalFlag() :"
					+ objOrderVO.getFinalFlag());
			LogUtil.logMessage("objOrderVO.getUserID() :"
					+ objOrderVO.getUserID());
			LogUtil.logMessage("objOrderVO.getUserID() :"
					+ objOrderVO.getRevCode());
					objOrderVO.setRevCode(1);//CR_90 default no revision
			
			objCallableStatement.setInt(1, objOrderVO.getOrderKey());
			objCallableStatement.setString(2, objOrderVO.getRevFlag());
			objCallableStatement.setString(3, objOrderVO.getFinalFlag());
			objCallableStatement.setInt(4, objOrderVO.getRevCode());// All Rev
			// or Latest
			// 0r No Rev
			
			// Added for CR
			objCallableStatement.setInt(5, objOrderVO.getModelSeqNo());
			// Ends
			
			objCallableStatement.setString(6, objOrderVO.getUserID());
			objCallableStatement.registerOutParameter(7, Types.INTEGER);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.registerOutParameter(9, Types.INTEGER);
			objCallableStatement.registerOutParameter(10, Types.VARCHAR);
			
			int intStatus = objCallableStatement.executeUpdate();
			
			LogUtil.logMessage("After Pre Publish isStatus:" + intStatus);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(8);
			strOracleCode = (String) objCallableStatement.getString(9);
			strErrorMessage = (String) objCallableStatement.getString(10);
			
			// Added for CR
			LogUtil.logMessage("Message :" + strErrorMessage);
			objOrderVO.setMessage(strErrorMessage);
			// Ends
			
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
			
			int intOrderKey = (int) objCallableStatement.getInt(7);
			
			LogUtil.logMessage("intOrderKey :" + intOrderKey);
			
			objOrderVO.setOrderKey(intOrderKey);
			
		}
		
		catch (DataAccessException objDatExp) {
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
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:"
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
				LogUtil.logMessage("Enters into Exception block:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return objOrderVO;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 *            the object for deleting Order added for delete Spec CR
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public static int deleteOrders(OrderVO objOrderVO) throws EMDException {
		LogUtil.logMessage("Entering OrderDAO.deleteOrders");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
			strLogUser = objOrderVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_DELETE_ORDER);
			
			objCallableStatement.setString(1, objOrderVO.getOrderNo());
			objCallableStatement.setString(2, objOrderVO.getUserID());
			
			System.out.println(objOrderVO.getOrderNo());
			objCallableStatement.registerOutParameter(3, Types.VARCHAR);
			
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the updateOrder method of OrderDAO :intStatus .."
					+ intStatus);
			intLSDBErrorID = (int) objCallableStatement.getInt(3);
			strOracleCode = (String) objCallableStatement.getString(4);
			strErrorMessage = (String) objCallableStatement.getString(5);
			
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
		
		catch (DataAccessException objDatExp) {
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
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:"
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
				LogUtil.logMessage("Enters into Exception block:");
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
	 * @param objOrderVO
	 *            The Object used for Fetch Order Specification Type Added for
	 *            CR-51
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public static int fetchOrderSpecType(OrderVO objOrderVO)
	throws EMDException {
		LogUtil.logMessage("Inside the  OrderDAO:fetchOrderSpecType");
		Connection objConnnection = null;
		ResultSet objRevSet = null;
		List arlOrderList = new ArrayList();
		int intOrderSpecType;
		String strLogUser = "";
		try {
			strLogUser = objOrderVO.getUserID();
			arlOrderList.add(new Integer(objOrderVO.getOrderKey()));
			LogUtil.logMessage("OrderKey" + objOrderVO.getOrderKey());
			
			objConnnection = DBHelper.prepareConnection();
			LogUtil
			.logMessage("objConnnection in OrderDAO:fetchOrderSpecType :"
					+ objConnnection);
			
			objRevSet = DBHelper.executeQuery(objConnnection,
					EMDQueries.QUERRY_FETCH_ORDR_SPEC_TYPE, arlOrderList);
			LogUtil.logMessage("RowSet" + objRevSet.getRow());
			
			while (objRevSet.next()) {
				objOrderVO = new OrderVO();
				objOrderVO.setSpecTypeSeqNo(Integer.parseInt(objRevSet
						.getString(DatabaseConstants.SPEC_TYPE_SEQ_NO)));
				LogUtil.logMessage("Spec type Seq No VALUE"
						+ objOrderVO.getSpecTypeSeqNo());
			}
			
			intOrderSpecType = objOrderVO.getSpecTypeSeqNo();
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception OrderDAO:fetchOrderSpecType");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		} finally {
			try {
				DBHelper.closeSQLObjects(objRevSet, null, objConnnection);
				DBHelper.closeConnection(objConnnection);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception OrderDAO:fetchOrderSpecType");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		LogUtil.logMessage("OrderSpec Type OrderDAO:fetchOrderSpecType :"
				+ intOrderSpecType);
		return intOrderSpecType;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 * the object for resetting the spec status
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public static int resetSpecStatus(OrderVO objOrderVO) throws EMDException {
		LogUtil.logMessage("Entering OrderDAO.resetSpecStatus");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
			strLogUser = objOrderVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_RESET_SPEC_STATUS);
			
			objCallableStatement.setInt(1, objOrderVO.getFromOrderKey());
			objCallableStatement.setInt(2, objOrderVO.getToOrderkey());
			objCallableStatement.setString(3, objOrderVO.getUserID());
			
			
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			LogUtil
			.logMessage("Inside the updateOrder method of OrderDAO :intStatus .."
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
		
		catch (DataAccessException objDatExp) {
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
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:"
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
				LogUtil.logMessage("Enters into Exception block:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return intStatus;
		
	}
	//Added For CR_90 by Rahul
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 *            the object for publishing the Order
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public static OrderVO validatePublishSpec(OrderVO objOrderVO)
	throws EMDException {
		LogUtil.logMessage("Entering OrderDAO.validatePublishSpec");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		
		try {
			strLogUser = objOrderVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_VALIDATE_PUBLISH_SPEC);
			
			LogUtil.logMessage("objOrderVO.getOrderKey() :"
					+ objOrderVO.getOrderKey());
			LogUtil.logMessage("objOrderVO.getUserID() :"
					+ objOrderVO.getUserID());
			
			objCallableStatement.setInt(1, objOrderVO.getOrderKey());
			objCallableStatement.setInt(2, objOrderVO.getModelSeqNo());
			objCallableStatement.setString(3, objOrderVO.getUserID());
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			
			int intStatus = objCallableStatement.executeUpdate();
			
			LogUtil.logMessage("After Pre Publish isStatus:" + intStatus);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(4);
			strOracleCode = (String) objCallableStatement.getString(5);
			strErrorMessage = (String) objCallableStatement.getString(6);
			
			LogUtil.logMessage("Message :" + strErrorMessage);
			objOrderVO.setMessage(strErrorMessage);
			
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
		
		catch (DataAccessException objDatExp) {
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
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:"
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
				LogUtil.logMessage("Enters into Exception block:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return objOrderVO;
		
	}

	//Added For CR_91 by Sekar
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 *            the object for publishing the Order
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public static SpecSuppVO getVersionsSpecSupp(OrderVO objOrderVO) 
	throws EMDException {
		LogUtil.logMessage("Entering OrderDAO.getVersionsSpecSupp");
		SpecSuppVO objSpecSuppVO=new SpecSuppVO();
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		
		try {
			strLogUser = objOrderVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection.prepareCall(EMDQueries.SP_ORDER_PRE_SPEC_SUPPLIMENT);
			LogUtil.logMessage("objOrderVO.getOrderKey()"+objOrderVO.getOrderKey());
			objCallableStatement.setInt(1, objOrderVO.getOrderKey());
			
			LogUtil.logMessage("strLogUser"+strLogUser);
			objCallableStatement.setString(2, strLogUser);
			objCallableStatement.registerOutParameter(3, Types.VARCHAR);
			objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			 //Added For CR_106 - On demand Spec Supplement
			if (objOrderVO.getPrevOrderKey() != 0)
				objCallableStatement.setString(10, DatabaseConstants.DATALOCATION_SNAP_SHOT);	
			else
				objCallableStatement.setString(10, DatabaseConstants.DATALOCATION);	//Added For CR_91
			if (objOrderVO.getPrevOrderKey() != 0)
				objCallableStatement.setInt(11,objOrderVO.getPrevOrderKey());
			else
				objCallableStatement.setNull(11,Types.NULL);
			 //Added For CR_106 - Ends here
			objCallableStatement.registerOutParameter(12, Types.INTEGER);
			objCallableStatement.registerOutParameter(13, Types.VARCHAR);
			objCallableStatement.registerOutParameter(14, Types.VARCHAR);
			objCallableStatement.execute();
			
			objSpecSuppVO.setStrPastRevCode((String)objCallableStatement.getString(3));			
			objSpecSuppVO.setStrPresentRevCode((String)objCallableStatement.getString(4));
			objSpecSuppVO.setStrPublishDate((String)objCallableStatement.getString(5));
			objSpecSuppVO.setStrPastSpecStatusDesc((String)objCallableStatement.getString(6));
			objSpecSuppVO.setStrPresentSpecStatusDesc((String)objCallableStatement.getString(7));
			objSpecSuppVO.setStrQtyInWords((String) objCallableStatement.getString(8));
			objSpecSuppVO.setStrOpeningDate((String) objCallableStatement.getString(9));

			String[] stropen = objSpecSuppVO.getStrOpeningDate().split(",");
			LogUtil.logMessage("stropen"+stropen);
			
			objSpecSuppVO.setStrOpeningDate(stropen[0] + "," + " " + stropen[1]);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(12);
			strOracleCode = (String) objCallableStatement.getString(13);
			strErrorMessage = (String) objCallableStatement.getString(14);
			
			LogUtil.logMessage("Message :" + strErrorMessage);
			objOrderVO.setMessage(strErrorMessage);
			
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
		
		catch (DataAccessException objDatExp) {
			ErrorInfo objErrorInfo = objDatExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into getVersionsSpecSupp DataAccess Exception block in DAO:.."
					+ objDatExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDatExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil.logMessage("Enters into getVersionsSpecSupp AppException block in  DAO :"
					+ objErrorInfo.getMessage());
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into getVersionsSpecSupp Exception block in DAO:"
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
				LogUtil.logMessage("Enters into Exception block:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return objSpecSuppVO;
		
	}

	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 *            the object for publishing the Order
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetchSpecSupplementDetails(OrderVO objOrderVo) throws EMDException {
		LogUtil.logMessage("Entering OrderDAO.fetchSpecSupplementDetails");
		
		Connection objConnnection = null;
		ArrayList arlSpecSupplementList = new ArrayList();
		CallableStatement objCallableStatement = null;
		
		// Error out parameters
		ResultSet objFileLoc = null;
		ResultSet objOrdResultSet = null;
		int prevOrderKey = 0;
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		int orderKey;
		try {
			strLogUser = objOrderVo.getUserID();
			orderKey= objOrderVo.getOrderKey();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			objCallableStatement = objConnnection
			.prepareCall("{call PK_LSDB_ORDER_SPECS.SP_ORDER_SPEC_SUPPLIMENT(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			
			objCallableStatement.setInt(1, orderKey);
			objCallableStatement.setString(2, strLogUser);
			objCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(5, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(6, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(7, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(8, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(9, OracleTypes.CURSOR);
			if (objOrderVo.getPrevOrderKey() != 0)
				objCallableStatement.setString(10, DatabaseConstants.DATALOCATION_SNAP_SHOT);	
			else
				objCallableStatement.setString(10, DatabaseConstants.DATALOCATION);					
			objCallableStatement.registerOutParameter(11, Types.INTEGER);
			if (objOrderVo.getPrevOrderKey() != 0)
				objCallableStatement.setInt(11,objOrderVo.getPrevOrderKey());
			//Added For CR_106 Issue with Save Component Clauses not getting displayed
			else
				objCallableStatement.setInt(11,Types.NULL);
			objCallableStatement.registerOutParameter(12, Types.INTEGER);
			objCallableStatement.registerOutParameter(13, Types.VARCHAR);
			objCallableStatement.registerOutParameter(14, Types.VARCHAR);
			objCallableStatement.execute();
			
			ResultSet objHPRatingRS = (ResultSet) objCallableStatement.getObject(3);
			
			ResultSet objSpecificationsRS = (ResultSet) objCallableStatement.getObject(4);
			
			ResultSet objGnrlArrNotesRS = (ResultSet) objCallableStatement.getObject(5);
			
			ResultSet objGnrlArngmntRS = (ResultSet) objCallableStatement.getObject(6);
			
			ResultSet objPerfCurveRS = (ResultSet) objCallableStatement.getObject(7);
			
			ResultSet objClauseRS = (ResultSet) objCallableStatement.getObject(8);
			
			ResultSet objMainFetInfoRS = (ResultSet) objCallableStatement.getObject(9);
			
			prevOrderKey = objCallableStatement.getInt(11);
			intLSDBErrorID = (int) objCallableStatement.getInt(12);
			strOracleCode = (String) objCallableStatement.getString(13);
			strErrorMessage = (String) objCallableStatement.getString(14);
			LogUtil.logMessage("prevOrderKey + " + prevOrderKey);
			
			
			/**
			 * Create ArrayList for MainFeatture 
			 */
			SpecSuppVO objSpecSuppVO;
			ArrayList arlMainFetInfo = new ArrayList();
			ArrayList arlHpRating = new ArrayList();
			ArrayList arlSpecSupp = new ArrayList();
			ArrayList arlGnrlArrNotes = new ArrayList();
			ArrayList arlGnrlArngmnt = new ArrayList();
			ArrayList arlSpecSuppPerfCurves = new ArrayList();
			
			if (objMainFetInfoRS != null) {
				
				LogUtil.logMessage("Order DAO : objMainFetInfoRS");	
				
				while (objMainFetInfoRS.next()) {
					objSpecSuppVO = new SpecSuppVO();
					
					String strFromMainInfo = "";
					String strToMainInfo = "";
					String strReason = "";
					if (objMainFetInfoRS.getString("FROM_MAIN_INFO") != null) {
						strFromMainInfo = objMainFetInfoRS.getString("FROM_MAIN_INFO");
						objSpecSuppVO.setFromMainInfo(strFromMainInfo);
					}
					if (objMainFetInfoRS.getString("TO_MAIN_INFO") != null) {
						strToMainInfo = objMainFetInfoRS.getString("TO_MAIN_INFO");
						objSpecSuppVO.setToMainInfo(strToMainInfo);
					}
					strReason = objMainFetInfoRS.getString("REASON");
				
					objSpecSuppVO.setReason(strReason);
					
					arlMainFetInfo.add(objSpecSuppVO);
					
				}
			}
			arlSpecSupplementList.add(arlMainFetInfo);

			//HP Rating Details
			
			if (objHPRatingRS != null) {
				
				LogUtil.logMessage("Order DAO : objHPRatingRS");	
				
				while (objHPRatingRS.next()) {
					objSpecSuppVO = new SpecSuppVO();
					
					String strFromHpRating = "";
					String strToHpRating = "";
					String strReason = "";
					
					strFromHpRating = objHPRatingRS.getString("HP_FROM");
					strToHpRating = objHPRatingRS.getString("hp_TO");
					strReason = objHPRatingRS.getString("REASON");
					
					objSpecSuppVO.setStrFromHpRating(strFromHpRating);
					objSpecSuppVO.setStrToHpRating(strToHpRating);
					objSpecSuppVO.setStrReason(strReason);
					
					arlHpRating.add(objSpecSuppVO);
					
				}
			}
			arlSpecSupplementList.add(arlHpRating);
			
			if (objSpecificationsRS != null) {
				
				LogUtil.logMessage("Order DAO :Creation of objSpecificationsRS");	
				
				while (objSpecificationsRS.next()) {
					objSpecSuppVO = new SpecSuppVO();
					
					String strFromSpecItemDesc = "";
					String strToSpecItemDesc = "";
					String strFromSpecItemValue = "";
					String strToSpecItemValue = "";
					String strReason = "";
					if (objSpecificationsRS.getString("FROM_DESC") != null) {
						strFromSpecItemDesc = objSpecificationsRS
						.getString("FROM_DESC");
					}
					if (objSpecificationsRS.getString("FROM_VALUE") != null) {
						strFromSpecItemValue = objSpecificationsRS
						.getString("FROM_VALUE");
					}
					if (objSpecificationsRS.getString("TO_DESC") != null) {
						strToSpecItemDesc = objSpecificationsRS
						.getString("TO_DESC");
					}
					if (objSpecificationsRS.getString("TO_VALUE") != null) {
						strToSpecItemValue = objSpecificationsRS
						.getString("TO_VALUE");
					}
					strReason = objSpecificationsRS.getString("REASON");
					
					objSpecSuppVO.setStrFromSpecItemDesc(strFromSpecItemDesc);
					objSpecSuppVO.setStrToSpecItemDesc(strToSpecItemDesc);
					objSpecSuppVO.setStrFromSpecItemValue(strFromSpecItemValue);					
					objSpecSuppVO.setStrToSpecItemValue(strToSpecItemValue);
					objSpecSuppVO.setStrReason(strReason);
					
					
					
					arlSpecSupp.add(objSpecSuppVO);
					
				}
			}
			arlSpecSupplementList.add(arlSpecSupp);
			
			if (objGnrlArrNotesRS != null) {
				
				LogUtil.logMessage("Order DAO : objGnrlArrNotesRS");	
				
				while (objGnrlArrNotesRS.next()) {
					objSpecSuppVO = new SpecSuppVO();
					
					String strFromGenArrNotes = "";
					String strToGenArrNotes = "";
					String strReason = "";
					
					strFromGenArrNotes = objGnrlArrNotesRS.getString("GNOTES_FROM");
					strToGenArrNotes = objGnrlArrNotesRS.getString("GNOTES_TO");
					strReason = objGnrlArrNotesRS.getString("REASON");
					
					
					objSpecSuppVO.setStrFromGenArrNotes(strFromGenArrNotes);
					objSpecSuppVO.setStrToGenArrNotes(strToGenArrNotes);
					objSpecSuppVO.setStrReason(strReason);
					
					arlGnrlArrNotes.add(objSpecSuppVO);
					
				}
			}
			arlSpecSupplementList.add(arlGnrlArrNotes);
			
			if (objGnrlArngmntRS != null) {
				
				LogUtil.logMessage("Order DAO : objGnrlArrNotesRS");	
				
				while (objGnrlArngmntRS.next()) {
					objSpecSuppVO = new SpecSuppVO();
					
					String strFromView = "";
					String strToView = "";
					String strReason = "";
					
					strFromView = objGnrlArngmntRS.getString("FROM_VIEW");
					strToView = objGnrlArngmntRS.getString("TO_VIEW");
					strReason = objGnrlArngmntRS.getString("REASON");

					objSpecSuppVO.setStrFromView(strFromView);
					objSpecSuppVO.setStrToView(strToView);
					objSpecSuppVO.setStrReason(strReason);
					
					arlGnrlArngmnt.add(objSpecSuppVO);
					
				}
			}
			arlSpecSupplementList.add(arlGnrlArngmnt);
	
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
			ResultSet objResultSetCharEdlRefEdl = null;
			
			ArrayList arlTableRows = new ArrayList();			
			ArrayList allFromToClause= new ArrayList();
			ArrayList allClauseGroup= new ArrayList();
			
			ClauseVO objFromClauseVO = new ClauseVO();
			ClauseVO objToClauseVO = new ClauseVO();
			ClauseVO objCommonClausVO = new ClauseVO();
			
			if (objClauseRS != null) {
				
				LogUtil.logMessage("Order DAO : objClauseRS");
			
			while(objClauseRS.next()){
				
				allFromToClause = new ArrayList();
				objCommonClausVO = new ClauseVO();
				objFromClauseVO = new ClauseVO();
				objToClauseVO = new ClauseVO();
				
				strPreReason = objClauseRS.getString("LS406_CLA_REASON");
				objCommonClausVO.setStrPreReason(strPreReason);
				
				String strCharstcGrpFlag = objClauseRS.getString("LS300_CHAR_GRP_FLAG");
				objCommonClausVO.setSelectCGCFlag(objClauseRS.getString(DatabaseConstants.LS300_CHAR_GRP_FLAG));
				//Added For CR_106 to bring changes for Sales Supplement
				objCommonClausVO.setSaleSysMarker(objClauseRS.getString(DatabaseConstants.LS406_SALES_SYS_MARKER));
				objCommonClausVO.setSecCode(objClauseRS.getString(DatabaseConstants.LS201_SEC_CODE));
				//Ends here for CR_106
				allFromToClause.add(objCommonClausVO);
				ResultSet objFromClauseRS = (ResultSet) objClauseRS.getObject("CLA_FROM");
				
				while (objFromClauseRS.next()) {
					LogUtil.logMessage("while looop objFromClauseRS");
					
					strPastClauseNo = objFromClauseRS.getString("LS406_CLA_NUM");
					strPastClauseDesc = objFromClauseRS.getString("LS301_CLA_DESC");
					String strOrderDelFlag = objFromClauseRS.getString("LS406_CLA_DEL_FLAG");
					String strModDelFlag = objFromClauseRS.getString("LS301_DELETE_FLAG");
					
					objFromClauseVO.setStrPastClauseNo(strPastClauseNo);
					objFromClauseVO.setStrPastClauseDesc(strPastClauseDesc);
					objFromClauseVO.setClauseDelFlag(strOrderDelFlag);
					objFromClauseVO.setStrModDelFlag(strModDelFlag);
					
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
					
					if ("Y".equals(strCharstcGrpFlag))	{
							
						objResultSetCharEdlRefEdl = (ResultSet) objFromClauseRS
														.getObject("CHRSTC_EDL_AND_REF_EDL_NO");
						
						String strCharEdlRefEdlNo = "";
						while (objResultSetCharEdlRefEdl.next()) {
						
							if (objResultSetCharEdlRefEdl.getString("LS428_CHARSTC_EDL_NO") != null
									&&	!"".equals(objResultSetCharEdlRefEdl.getString("LS428_CHARSTC_EDL_NO")))
								strCharEdlRefEdlNo += ApplicationConstants.EDL + objResultSetCharEdlRefEdl.
														getString("LS428_CHARSTC_EDL_NO") + "\n";
							
								
								
							if (objResultSetCharEdlRefEdl.getString("LS428_CHARSTC_REF_EDL_NO") != null
									&&	!"".equals(objResultSetCharEdlRefEdl.getString("LS428_CHARSTC_REF_EDL_NO")))
								strCharEdlRefEdlNo += ApplicationConstants.REF_EDL + objResultSetCharEdlRefEdl.
														getString("LS428_CHARSTC_REF_EDL_NO")  + ApplicationConstants.REF_EDL_END//CR-87
														+ "\n";
							   
						}
						
						DBHelper.closeSQLObjects(objResultSetCharEdlRefEdl, null, null);
						
						if (strCharEdlRefEdlNo != null
								&& !"".equals(strCharEdlRefEdlNo)) 
							strEngData += strCharEdlRefEdlNo;
						else
							strEngData += "EDL Undeveloped" + "\n";
					
					}
					
					if ("C".equals(strCharstcGrpFlag))	{

						objResultSetCharEdlRefEdl = (ResultSet) objFromClauseRS
						.getObject("CHRSTC_EDL_AND_REF_EDL_NO");

						String strCharEdlRefEdlNo = "";
						
						while (objResultSetCharEdlRefEdl.next()) {
							
							if (objResultSetCharEdlRefEdl.getString("LS428_CHARSTC_EDL_NO") != null
									&&	!"".equals(objResultSetCharEdlRefEdl.getString("LS428_CHARSTC_EDL_NO")))
								
								strCharEdlRefEdlNo += ApplicationConstants.EDL + objResultSetCharEdlRefEdl.
														getString("LS428_CHARSTC_EDL_NO") + "\n";
							
							if (objResultSetCharEdlRefEdl.getString("LS428_CHARSTC_REF_EDL_NO") != null
									&&	!"".equals(objResultSetCharEdlRefEdl.getString("LS428_CHARSTC_REF_EDL_NO")))
								
								strCharEdlRefEdlNo += ApplicationConstants.REF_EDL + objResultSetCharEdlRefEdl.
														getString("LS428_CHARSTC_REF_EDL_NO") + ApplicationConstants.REF_EDL_END//CR-87
														+ "\n";
						}
						
						DBHelper.closeSQLObjects(objResultSetCharEdlRefEdl, null, null);	
						
						if (strCharEdlRefEdlNo != null
								&& !"".equals(strCharEdlRefEdlNo)) 
							strEngData += strCharEdlRefEdlNo;
											
					}				
					
					objResultSetEDLNO = (ResultSet) objFromClauseRS
					.getObject("EDLNO");
					
					while (objResultSetEDLNO.next()) {
						strEngData += ApplicationConstants.EDL
						+ objResultSetEDLNO
						.getString("LS302_EDL_NO");
						strEngData += "\n";
					}
					
					DBHelper.closeSQLObjects(objResultSetEDLNO, null, null);

					objResultSetRefEDLNO = (ResultSet) objFromClauseRS
					.getObject("refEDLNO");
					
					while (objResultSetRefEDLNO.next()) {
						strEngData += ApplicationConstants.REF_EDL
						+ objResultSetRefEDLNO
						.getString("LS303_REF_EDL_NO") + ApplicationConstants.REF_EDL_END;//CR-87
						strEngData += "\n";
					}
					DBHelper.closeSQLObjects(objResultSetRefEDLNO, null, null);
					
					objResultSetPartOf = (ResultSet) objFromClauseRS
					.getObject("PartOF");
					
					while (objResultSetPartOf.next()) {
						
						strEngData += ApplicationConstants.PARTOF
						+ objResultSetPartOf
						.getString("LS407_PART_OF_CLA_NO");
						
						strEngData += "\n";
						
					}
					
					DBHelper.closeSQLObjects(objResultSetPartOf, null, null);
					
					if (objFromClauseRS.getInt("LS301_DWO_NUMBER") != 0) {
						strEngData += ApplicationConstants.DWO
						+ objFromClauseRS.getInt("LS301_DWO_NUMBER");
						strEngData += "\n";
						
					}
					
					if (objFromClauseRS.getInt("LS301_PART_NUMBER") != 0) {
						strEngData += ApplicationConstants.PART_NUMBER
						+ objFromClauseRS.getInt("LS301_PART_NUMBER");
						strEngData += "\n";
						
					}
					
					objResultSetStdEquip = (ResultSet) objFromClauseRS
					.getObject("STD_EQUIP");
					
					if (objResultSetStdEquip.next()) {
						strEngData += objResultSetStdEquip
						.getString("LS060_STD_EQP_DESC");
						strEngData += "\n";
						
					}
					DBHelper.closeSQLObjects(objResultSetStdEquip, null, null);
					
					String strEngDataComments = objFromClauseRS
					.getString("LS301_ENGG_DATA_COMMENTS");
					if (strEngDataComments != null
							&& !"".equals(strEngDataComments)) {
						strEngData += strEngDataComments;
						strEngData += "\n";
						
					}
					
					objFromClauseVO.setStrEngData(strEngData);						
				}
				DBHelper.closeSQLObjects(objFromClauseRS, null, null);	
				
				allFromToClause.add(objFromClauseVO);
											
				ResultSet objToClauseRS = (ResultSet) objClauseRS.getObject("CLA_TO");
			
				while (objToClauseRS.next()) {
					
					LogUtil.logMessage("OrderDao:::While loop objToClasueRs ");	
					
					strPreClauseNo = objToClauseRS.getString("LS406_CLA_NUM");
					strPreClauseDesc = objToClauseRS.getString("LS301_CLA_DESC");
					String strOrderDelFlag = objToClauseRS.getString("LS406_CLA_DEL_FLAG");
					String strModDelFlag = objToClauseRS.getString("LS301_DELETE_FLAG");
					
					objToClauseVO.setStrPreClauseNo(strPreClauseNo);
					objToClauseVO.setStrPreClauseDesc(strPreClauseDesc);
					objToClauseVO.setClauseDelFlag(strOrderDelFlag);
					objToClauseVO.setStrModDelFlag(strModDelFlag);
					
					objResultSetTableData = (ResultSet) objToClauseRS.getObject("TABLE_DATE");
					
					arlTableRows = new ArrayList();
					ArrayList arlTableColumns = new ArrayList();
					
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
					DBHelper.closeSQLObjects(objResultSetTableData, null, null);
					
					LogUtil.logMessage("arlTableRows : " + arlTableRows.size());
					
					if (arlTableRows.size() > 0)
						objToClauseVO.setTableArrayData1(arlTableRows);
					
					String strEngData = "";
					
					if ("Y".equals(strCharstcGrpFlag))	{

						objResultSetCharEdlRefEdl = (ResultSet) objToClauseRS.getObject("CHRSTC_EDL_AND_REF_EDL_NO");
						
						String strCharEdlRefEdlNo = "";
						
						while (objResultSetCharEdlRefEdl.next()) {
					
							if (objResultSetCharEdlRefEdl.getString("LS428_CHARSTC_EDL_NO") != null
									&&	!"".equals(objResultSetCharEdlRefEdl.getString("LS428_CHARSTC_EDL_NO")))
								
								strCharEdlRefEdlNo += ApplicationConstants.EDL + objResultSetCharEdlRefEdl.
														getString("LS428_CHARSTC_EDL_NO") + "\n";	
							
							if (objResultSetCharEdlRefEdl.getString("LS428_CHARSTC_REF_EDL_NO") != null
									&&	!"".equals(objResultSetCharEdlRefEdl.getString("LS428_CHARSTC_REF_EDL_NO")))
								
								strCharEdlRefEdlNo += ApplicationConstants.REF_EDL + objResultSetCharEdlRefEdl.
														getString("LS428_CHARSTC_REF_EDL_NO") + ApplicationConstants.REF_EDL_END//CR-87
														+ "\n";
						}
						
						DBHelper.closeSQLObjects(objResultSetCharEdlRefEdl, null, null);
						
						if (strCharEdlRefEdlNo != null
								&& !"".equals(strCharEdlRefEdlNo)) 
							strEngData += strCharEdlRefEdlNo;
						else
							strEngData += "EDL Undeveloped" + "\n";
					}
					
					if ("C".equals(strCharstcGrpFlag))	{

						objResultSetCharEdlRefEdl = (ResultSet) objToClauseRS.getObject("CHRSTC_EDL_AND_REF_EDL_NO");

						String strCharEdlRefEdlNo = "";
						
						while (objResultSetCharEdlRefEdl.next()) {
							
							if (objResultSetCharEdlRefEdl.getString("LS428_CHARSTC_EDL_NO") != null
									&&	!"".equals(objResultSetCharEdlRefEdl.getString("LS428_CHARSTC_EDL_NO")))
								
								strCharEdlRefEdlNo += ApplicationConstants.EDL + objResultSetCharEdlRefEdl.
														getString("LS428_CHARSTC_EDL_NO") + "\n";
							
							if (objResultSetCharEdlRefEdl.getString("LS428_CHARSTC_REF_EDL_NO") != null
									&&	!"".equals(objResultSetCharEdlRefEdl.getString("LS428_CHARSTC_REF_EDL_NO")))
								
								strCharEdlRefEdlNo += ApplicationConstants.REF_EDL + objResultSetCharEdlRefEdl.
														getString("LS428_CHARSTC_REF_EDL_NO") + ApplicationConstants.REF_EDL_END//CR-87
														+ "\n";
						}
						
						DBHelper.closeSQLObjects(objResultSetCharEdlRefEdl, null, null);	
						
						if (strCharEdlRefEdlNo != null
								&& !"".equals(strCharEdlRefEdlNo)) 
							strEngData += strCharEdlRefEdlNo;
					}
					
						objResultSetEDLNO = (ResultSet) objToClauseRS.getObject("EDLNO");
						
						while (objResultSetEDLNO.next()) {
							strEngData += ApplicationConstants.EDL
							+ objResultSetEDLNO.getString("LS302_EDL_NO");
							strEngData += "\n";
						}
						DBHelper.closeSQLObjects(objResultSetEDLNO, null, null);
						
						objResultSetRefEDLNO = (ResultSet) objToClauseRS.getObject("refEDLNO");
			
						
						while (objResultSetRefEDLNO.next()) {
							strEngData += ApplicationConstants.REF_EDL
							+ objResultSetRefEDLNO
							.getString("LS303_REF_EDL_NO") + ApplicationConstants.REF_EDL_END;//CR-87
							strEngData += "\n";
							
						}
						DBHelper.closeSQLObjects(objResultSetRefEDLNO, null, null);
					
					objResultSetPartOf = (ResultSet) objToClauseRS.getObject("PartOF");
					
					while (objResultSetPartOf.next()) {
						
						strEngData += ApplicationConstants.PARTOF
						+ objResultSetPartOf
						.getString("LS407_PART_OF_CLA_NO");
						
						strEngData += "\n";
						
					}
					
					DBHelper.closeSQLObjects(objResultSetPartOf, null, null);
					if (objToClauseRS.getInt("LS301_DWO_NUMBER") != 0) {
						strEngData += ApplicationConstants.DWO
						+ objToClauseRS.getInt("LS301_DWO_NUMBER");
						strEngData += "\n";
						
					}
					
					if (objToClauseRS.getInt("LS301_PART_NUMBER") != 0) {
						strEngData += ApplicationConstants.PART_NUMBER
						+ objToClauseRS.getInt("LS301_PART_NUMBER");
						strEngData += "\n";
						
					}
					
					objResultSetStdEquip = (ResultSet) objToClauseRS
					.getObject("STD_EQUIP");
					
					if (objResultSetStdEquip.next()) {
						strEngData += objResultSetStdEquip
						.getString("LS060_STD_EQP_DESC");
						strEngData += "\n";						
						}
					
					DBHelper.closeSQLObjects(objResultSetStdEquip, null, null);
					
					String strEngDataComments = objToClauseRS
					.getString("LS301_ENGG_DATA_COMMENTS");
					if (strEngDataComments != null
							&& !"".equals(strEngDataComments)) {
						strEngData += strEngDataComments;
						strEngData += "\n";
						
					}					
					
					objToClauseVO.setStrEngData(strEngData);					
					
				}
				
				DBHelper.closeSQLObjects(objToClauseRS, null, null);
				allFromToClause.add(objToClauseVO);
				
				LogUtil.logMessage("allFromToClause :" + allFromToClause.size());
				
				allClauseGroup.add(allFromToClause);
				
				LogUtil.logMessage("allClauseGroup :" + allClauseGroup.size());
				}
				
			}
			arlSpecSupplementList.add(allClauseGroup);			
			
			if (objPerfCurveRS != null) {
				
				LogUtil.logMessage("Order DAO : objPerfCurveRS");	
				
				while (objPerfCurveRS.next()) {
					objSpecSuppVO = new SpecSuppVO();
					
					String strFromPerfCurve = "";
					String strToPerfCurve = "";
					String strReason = "";
					
					if (objPerfCurveRS.getString("FROM_DESC") != null) {
						strFromPerfCurve = objPerfCurveRS.getString("FROM_DESC");
					}
					if (objPerfCurveRS.getString("TO_DESC") != null) {
						strToPerfCurve = objPerfCurveRS.getString("TO_DESC");
					}
					strReason = objPerfCurveRS.getString("REASON");
					
					objSpecSuppVO.setStrFromPerfCurve(strFromPerfCurve);
					objSpecSuppVO.setStrToPerfCurve(strToPerfCurve);
					objSpecSuppVO.setStrReason(strReason);
					
					arlSpecSuppPerfCurves.add(objSpecSuppVO);
					
				}
			}
			arlSpecSupplementList.add(arlSpecSuppPerfCurves);
			
			
			
			if (intLSDBErrorID != 0) {
				
				ErrorInfo objErrorInfo = new ErrorInfo();
				
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				
				throw new DataAccessException(objErrorInfo);
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {
				
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer sb = new StringBuffer();
				sb.append(strOracleCode + " ");
				sb.append(strErrorMessage);
				objErrorInfo.setMessage(sb.toString());
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
			
			
			
			
			
		} catch (DataAccessException objDataEx) {
			ErrorInfo objErrorInfo = objDataEx.getErrorInfo();
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objDataEx.getErrorInfo().getMessage());
			throw new BusinessException(objDataEx, objErrorInfo);
		} catch (ApplicationException objAppEx) {
			ErrorInfo objErrorInfo = objAppEx.getErrorInfo();
			LogUtil.logMessage("Enters into AppException block in  DAO :"
					+ objErrorInfo.getMessage());
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			throw new ApplicationException(objAppEx, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(objOrdResultSet, objCallableStatement,
						objConnnection);
			} catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception block in DAO:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		LogUtil.logMessage("OrderList Size" + arlSpecSupplementList.size());
		return arlSpecSupplementList;
		
	}
	
	
	
	//Added for CR_99 for Regeneration of PDFS 
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 * The Object for Regeneration of PDFs.
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public static int regenerateSpec(OrderVO objOrderVO) throws EMDException {
		LogUtil.logMessage("Entering OrderDAO.regenerateSpec");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		try {
			strLogUser = objOrderVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_PDF_RESTART_JOB);
			
			objCallableStatement.setInt(1, objOrderVO.getToOrderkey());
			
			objCallableStatement.setString(2, objOrderVO.getUserID());
						
			objCallableStatement.registerOutParameter(3, Types.INTEGER);
			
			objCallableStatement.registerOutParameter(4, Types.VARCHAR);
			
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			
			intLSDBErrorID = (int) objCallableStatement.getInt(3);
			strOracleCode = (String) objCallableStatement.getString(4);
			strErrorMessage = (String) objCallableStatement.getString(5);
		
		
			if (intLSDBErrorID != 0) {
				
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				
				throw new DataAccessException(objErrorInfo);
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {// Un
				// handled
				// exception
				
				
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer sbErrorMessage = new StringBuffer();
				sbErrorMessage.append(strOracleCode + " ");
				sbErrorMessage.append(strErrorMessage);
				objErrorInfo.setMessage(sbErrorMessage.toString());
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
		}
		
		catch (DataAccessException objDatExp) {
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
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:"
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
				LogUtil.logMessage("Enters into Exception block:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return intStatus;
		
	}
	
	// CR_99 Ends here.
	/***************************************************************************
	 * Added for LSDB_CR_104
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objMainFeatureInfoVO
	 * The object for update Display in Spec
	 * @return Arraylist It Returns a boolean value
	 * @throws EMDException
	 **************************************************************************/

	public static OrderVO ordSecPublishAndNotification(OrderVO objOrderVO)
			throws EMDException {

		LogUtil
				.logMessage("Enters into OrderDAO:ordSecPublishAndNotification");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ResultSet objResultSet = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		int intStatusCode;
		int intOrderKey=0;
		String strLogUser = "";
		try {
			LogUtil.logMessage("bjorderVO.getSubject():" + objOrderVO.getSubject());
			LogUtil.logMessage(" objoderVO.getBodyCont():" +  objOrderVO.getBodyCont());
			LogUtil.logMessage("intOrderKey:" + intOrderKey);
			LogUtil.logMessage("objOrderVO.getRevFlag():" + objOrderVO.getRevFlag());
			LogUtil.logMessage("objOrderVO.getFinalFlag():" + objOrderVO.getFinalFlag());
			LogUtil.logMessage("objOrderVO.getRevCode():" + objOrderVO.getRevCode());
					
			strLogUser = objOrderVO.getUserID();
			objConnection = DBHelper.prepareConnection();
					objCallableStatement = objConnection
					.prepareCall(EMDQueries.SP_EMAIL_TO_1058_ADMN);
			objCallableStatement.setInt(1, objOrderVO.getOrderKey());
			objCallableStatement.setString(2, objOrderVO.getSubject());
			objCallableStatement.setString(3, objOrderVO.getBodyCont());
			objCallableStatement.setString(4, objOrderVO.getRevFlag());
			objCallableStatement.setString(5, objOrderVO.getFinalFlag());
			if(objOrderVO.getRevCode()==0)
			{
				objOrderVO.setRevCode(1);//No revision for default value
			}
			objCallableStatement.setInt(6, objOrderVO.getRevCode());
			objCallableStatement.registerOutParameter(7, Types.INTEGER);
			objCallableStatement.setString(8, objOrderVO.getUserID());
			objCallableStatement.registerOutParameter(9, Types.INTEGER);
			objCallableStatement.registerOutParameter(10, Types.VARCHAR);
			objCallableStatement.registerOutParameter(11, Types.VARCHAR);
			
			intStatusCode = objCallableStatement.executeUpdate();

			if (intStatusCode > 0) {
				intStatusCode = 0;
			}
			
			intOrderKey=objCallableStatement.getInt(7);
			LogUtil.logMessage("intOrderKey:" + intOrderKey);
			intLSDBErrorID = objCallableStatement.getInt(9);
			LogUtil.logMessage("LSDBErrorID:" + intLSDBErrorID);
			strOracleCode = objCallableStatement.getString(10);
			LogUtil.logMessage("OracleErrorCode:" + strOracleCode);
			strErrorMessage = (String) objCallableStatement.getString(11);

			LogUtil.logMessage("ErrorMessage:" + strErrorMessage);
			objOrderVO.setOrderKey(intOrderKey);
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

		return objOrderVO;

	}
	
	//Added for CR-121
	/***************************************************************************
	 * This Method is used to Select Multiple Orders
	 * 
	 * @param objOrderVo
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/	
	
		public static ArrayList fetchMultipleOrders(OrderVO objOrderVo) throws EMDException {
		LogUtil.logMessage("Entering OrderDAO.multipleOrders");
		Connection objConnnection = null;
		ArrayList arlMultipleOrderList = new ArrayList();
		CallableStatement objCallableStatement = null;
		ResultSet objMultOrdResultSet = null;
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		ArrayDescriptor arrdesc = null;
		ARRAY objArray=null;
		
		try {
			strLogUser = objOrderVo.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_MLTPLE_ORDRS);
			Connection dconn = ((DelegatingConnection) objConnnection).getInnermostDelegate(); //Added for CR-123
			
			if (objOrderVo.getOrderKeys().length !=0) {
				arrdesc = new ArrayDescriptor(
						DatabaseConstants.IN_ARRAY, dconn);
				objArray = new ARRAY(arrdesc, dconn,objOrderVo.getOrderKeys());
				objCallableStatement.setArray(1, objArray);
			} else{
				objCallableStatement.setNull(1, Types.NULL);
			}
			
			if (objOrderVo.getDataLocTypeCode()!=null) {
				objCallableStatement.setString(2, objOrderVo.getDataLocTypeCode());
			} else {
				objCallableStatement.setNull(2, Types.NULL);
			}
			
			if (objOrderVo.getUserID()!= null) {
				objCallableStatement.setString(3, objOrderVo.getUserID());
			} else {
				objCallableStatement.setNull(3, Types.NULL);
			}
			
			objCallableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			objMultOrdResultSet = (ResultSet) objCallableStatement.getObject(4);
			LogUtil
			.logMessage("Inside the fetchMultipleOrders method of OrderDAO :resultSet"
					+ objMultOrdResultSet);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(5);
			strOracleCode = (String) objCallableStatement.getString(6);
			strErrorMessage = (String) objCallableStatement.getString(7);
			
			//CR_108 Modification of column index ends here.
			// Handled Valid Exception
			if (intLSDBErrorID != 0) {
				
				ErrorInfo objErrorInfo = new ErrorInfo();
				
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				
				throw new DataAccessException(objErrorInfo);
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {
				
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer sb = new StringBuffer();
				sb.append(strOracleCode + " ");
				sb.append(strErrorMessage);
				objErrorInfo.setMessage(sb.toString());
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			
			while (objMultOrdResultSet.next()) {
				
				objOrderVo = new OrderVO();
				
								
				objOrderVo.setOrderNo(objMultOrdResultSet
						.getString(DatabaseConstants.LS400_ORDR_NO));
				objOrderVo.setStatusDesc(objMultOrdResultSet
						.getString(DatabaseConstants.SPEC_STATUS));
				objOrderVo.setCustomerName(objMultOrdResultSet
						.getString(DatabaseConstants.CUST_NAME));
				objOrderVo.setModelName(objMultOrdResultSet
						.getString(DatabaseConstants.MDL_NAME));
				objOrderVo.setCustMdlName(objMultOrdResultSet
						.getString(DatabaseConstants.LS400_CUST_MDL_NAME));
				objOrderVo.setDistributorName(objMultOrdResultSet
						.getString(DatabaseConstants.DIST_NAME));
				
				arlMultipleOrderList.add(objOrderVo);
				
				
		}
			
		}catch (DataAccessException objDataEx) {
			ErrorInfo objErrorInfo = objDataEx.getErrorInfo();
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objDataEx.getErrorInfo().getMessage());
			throw new BusinessException(objDataEx, objErrorInfo);
		} catch (ApplicationException objAppEx) {
			ErrorInfo objErrorInfo = objAppEx.getErrorInfo();
			LogUtil.logMessage("Enters into AppException block in  DAO :"
					+ objErrorInfo.getMessage());
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			throw new ApplicationException(objAppEx, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(objMultOrdResultSet, objCallableStatement,
						objConnnection);
			} catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception block in DAO:");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		LogUtil.logMessage("OrderList Size" + arlMultipleOrderList.size());
		return arlMultipleOrderList;
		}
		//Added for CR-135 starts here
		
				public static ArrayList fetchPrevOrders(OrderVO objOrderVo) throws EMDException {
					LogUtil.logMessage("Entering OrderDAO.fetchPrevOrders");
					Connection objConnnection = null;
					ArrayList arlPrevOrderList = new ArrayList();
					CallableStatement objCallableStatement = null;
					// Error out parameters
					ResultSet objFileLoc = null;
					ResultSet objOrdResultSet = null;
					int intLSDBErrorID = 0;
					String strOracleCode = null;
					String strErrorMessage = null;
					String strLogUser = "";
					//Added for CR_101 to bring the multiple customers in array.
					ArrayDescriptor arrdesc = null;
					//CR_101 Ends here.

		                      
					try {
						strLogUser = objOrderVo.getUserID();
						objConnnection = DBHelper.prepareConnection();
		                                Connection dconn = ((DelegatingConnection) objConnnection).getInnermostDelegate(); //Added for CR-123 
						arrdesc = new ArrayDescriptor(DatabaseConstants.STR_ARRAY,
								dconn);//Added for 123
						LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
						objCallableStatement = objConnnection
						.prepareCall(EMDQueries.SP_SELECT_ORDER);
						LogUtil.logMessage("OrderKey Vlaue in OrderDAO:fetchOrders:"
								+ objOrderVo.getOrderKey());
						if (objOrderVo.getOrderKey() != 0) {
							objCallableStatement.setInt(1, objOrderVo.getPrevOrderKey());
						} else {
							objCallableStatement.setNull(1, Types.NULL);
						}
						
						objCallableStatement.setString(2, objOrderVo.getDataLocTypeCode());
						
						if (objOrderVo.getSpecTypeSeqNo() > 0) {
							objCallableStatement.setInt(3, objOrderVo.getSpecTypeSeqNo());
						} else {
							objCallableStatement.setNull(3, Types.NULL);
						}
						
						// Added for Proofreading Draft CR-72
						if (objOrderVo.getOrderNo() != null) {
							objCallableStatement.setString(4, objOrderVo.getOrderNo());
						} else {
							objCallableStatement.setNull(4, Types.NULL);
						}
						
						if (objOrderVo.getSpecStatusCode() > 0) {
							objCallableStatement.setInt(5, objOrderVo.getSpecStatusCode());
						} else {
							objCallableStatement.setNull(5, Types.NULL);
						}
						
						//Modified for CR_101 - Fetching orders by Customers 
						ARRAY arrCustomerSeqnos = null;
						
						LogUtil.logMessage("INside Customer Seq " + objOrderVo.getCusSeqNo());
						LogUtil.logMessage("INside Customer Seq Array" + objOrderVo.getCustomerSeqnos());
						
						if (objOrderVo.getCusSeqNo() != 0 && objOrderVo.getCusSeqNo() != -1) {
							
							int[] arrCustSeqNo = new int[1];
							arrCustSeqNo[0] = objOrderVo.getCusSeqNo();		
							
							objOrderVo.setCustomerSeqnos(arrCustSeqNo);		
							
							arrCustomerSeqnos = new ARRAY(arrdesc, dconn,
									objOrderVo.getCustomerSeqnos());
						}
						else if (objOrderVo.getCustomerSeqnos() != null) {
							arrCustomerSeqnos = new ARRAY(arrdesc, dconn,
									objOrderVo.getCustomerSeqnos());
						}
						else{	
							arrCustomerSeqnos = new ARRAY(arrdesc, dconn, null);  
						} 
						
						objCallableStatement.setArray(6, arrCustomerSeqnos);
						
						LogUtil.logMessage("After StrArray");			
						// Commented for CR_112 by ER91220 - Multiselect Model list box - Spec History Search		
						//CR_101 Ends here.
						/*
						if (objOrderVo.getModelSeqNo() > 0) {
							objCallableStatement.setInt(7, objOrderVo.getModelSeqNo());
						} else {
							objCallableStatement.setNull(7, Types.NULL);
						}
						*/
						//Added for CR_112 - To bring the Model Names 
						ARRAY arrModelSeqnos = null;
						
						if (objOrderVo.getModelSeqNo() != 0 && objOrderVo.getModelSeqNo() != -1) {
							
							int[] arrModelSeqNo = new int[1];
							arrModelSeqNo[0] = objOrderVo.getModelSeqNo();		
							
							objOrderVo.setModelSeqnos(arrModelSeqNo);		
							
							arrModelSeqnos = new ARRAY(arrdesc, dconn,
									objOrderVo.getModelSeqnos());
						}
						else if (objOrderVo.getModelSeqnos() != null) {
							arrModelSeqnos = new ARRAY(arrdesc, dconn,
									objOrderVo.getModelSeqnos());
						}
						else{	
							arrModelSeqnos = new ARRAY(arrdesc, dconn, null);  
						} 
						
						objCallableStatement.setArray(7, arrModelSeqnos);
						//CR_112 Ends here
						// Added for LSD_CR-76
						
						 if(objOrderVo.getSortBy()!=null ||
						 !"".equals(objOrderVo.getSortBy())){
						 objCallableStatement.setString(8,objOrderVo.getSortBy()); }else{
						 objCallableStatement.setNull(8,Types.NULL); }
						
						// Ends
						objCallableStatement.setString(9, objOrderVo.getUserID());
						
						//Added a parameter in CR_108 for show latest flag in "Order Specific Clause Report"
						if (objOrderVo.getShowLatestFlag() != null)
							objCallableStatement.setString(10, objOrderVo.getShowLatestFlag());
						else
							objCallableStatement.setNull(10, Types.NULL);
						
						objCallableStatement.registerOutParameter(11, OracleTypes.CURSOR);
						objCallableStatement.registerOutParameter(12, Types.VARCHAR);
						objCallableStatement.registerOutParameter(13, Types.INTEGER);
						objCallableStatement.registerOutParameter(14, Types.VARCHAR);
						
						objCallableStatement.execute();
						
						objOrdResultSet = (ResultSet) objCallableStatement.getObject(11);
						
						LogUtil
						.logMessage("Inside the fetchOrders method of OrderDAO :resultSet"
								+ objOrdResultSet);
						
						intLSDBErrorID = (int) objCallableStatement.getInt(12);
						strOracleCode = (String) objCallableStatement.getString(13);
						strErrorMessage = (String) objCallableStatement.getString(14);
						//CR_108 Modification of column index ends here.
						// Handled Valid Exception
						if (intLSDBErrorID != 0) {
							
							ErrorInfo objErrorInfo = new ErrorInfo();
							
							objErrorInfo.setMessageID("" + intLSDBErrorID);
							
							throw new DataAccessException(objErrorInfo);
						} else if (strOracleCode != null && !"0".equals(strOracleCode)) {
							
							LogUtil.logMessage("strOracleCode:" + strOracleCode);
							ErrorInfo objErrorInfo = new ErrorInfo();
							StringBuffer sb = new StringBuffer();
							sb.append(strOracleCode + " ");
							sb.append(strErrorMessage);
							objErrorInfo.setMessage(sb.toString());
							objConnnection.rollback();
							throw new ApplicationException(objErrorInfo);
						}
						
						while (objOrdResultSet.next()) {
							
							objOrderVo = new OrderVO();
							
							objOrderVo.setPrevOrderKey(objOrdResultSet
									.getInt(DatabaseConstants.ORDR_KEY));
							objOrderVo.setPrevOrderNo(objOrdResultSet
									.getString(DatabaseConstants.ORDR_NO));
							objOrderVo.setPrevCustomerName(objOrdResultSet
									.getString(DatabaseConstants.CUST_NAME));
							objOrderVo.setPrevModelName(objOrdResultSet
									.getString(DatabaseConstants.LS200_MDL_NAME));
							objOrderVo.setPrevQuantity(objOrdResultSet
									.getString(DatabaseConstants.ORDR_QTY));
							objOrderVo.setPrevCustCode(objOrdResultSet
									.getString(DatabaseConstants.LS400_SAP_CUST_CODE));				
							arlPrevOrderList.add(objOrderVo);
						}
						
					} catch (DataAccessException objDataEx) {
						ErrorInfo objErrorInfo = objDataEx.getErrorInfo();
						LogUtil.logMessage("ENters into catch block in DAO:.."
								+ objDataEx.getErrorInfo().getMessage());
						throw new BusinessException(objDataEx, objErrorInfo);
					} catch (ApplicationException objAppEx) {
						ErrorInfo objErrorInfo = objAppEx.getErrorInfo();
						LogUtil.logMessage("Enters into AppException block in  DAO :"
								+ objErrorInfo.getMessage());
						objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
								+ "-" + objErrorInfo.getMessage());
						throw new ApplicationException(objAppEx, objErrorInfo);
					} catch (Exception objExp) {
						LogUtil.logMessage("Enters into Exception block in DAO:");
						ErrorInfo objErrorInfo = new ErrorInfo();
						objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
								+ "-" + objExp.getMessage());
						throw new ApplicationException(objExp, objErrorInfo);
					}
					
					finally {
						try {
							
							DBHelper.closeSQLObjects(objOrdResultSet, objCallableStatement,
									objConnnection);
						} catch (Exception objExp) {
							LogUtil.logMessage("Enters into Exception block in DAO:");
							ErrorInfo objErrorInfo = new ErrorInfo();
							objErrorInfo.setMessage(ApplicationConstants.LOG_USER
									+ strLogUser + "-" + objExp.getMessage());
							throw new ApplicationException(objExp, objErrorInfo);
						}
						
					}
					LogUtil.logMessage("OrderList Size" + arlPrevOrderList.size());
					return arlPrevOrderList;
					
				}
				public static SpecSuppVO getPrevVersionsSpecSupp(OrderVO objOrderVO) 
						throws EMDException {
							LogUtil.logMessage("Entering OrderDAO.getPrevVersionsSpecSupp");
							SpecSuppVO objSpecSuppVO=new SpecSuppVO();
							Connection objConnnection = null;
							CallableStatement objCallableStatement = null;
							String strOracleCode = null;
							String strErrorMessage = null;
							int intLSDBErrorID = 0;
							String strLogUser = "";
							
							try {
								strLogUser = objOrderVO.getUserID();
								objConnnection = DBHelper.prepareConnection();
								objCallableStatement = objConnnection.prepareCall(EMDQueries.SP_ORDER_PRE_SPEC_SUPPLIMENT);
								LogUtil.logMessage("objOrderVO.getPrevOrderKey()"+objOrderVO.getPrevOrderKey());
								objCallableStatement.setInt(1, objOrderVO.getPrevOrderKey());
								
								LogUtil.logMessage("strLogUser"+strLogUser);
								objCallableStatement.setString(2, strLogUser);
								objCallableStatement.registerOutParameter(3, Types.VARCHAR);
								objCallableStatement.registerOutParameter(4, Types.VARCHAR);
								objCallableStatement.registerOutParameter(5, Types.VARCHAR);
								objCallableStatement.registerOutParameter(6, Types.VARCHAR);
								objCallableStatement.registerOutParameter(7, Types.VARCHAR);
								objCallableStatement.registerOutParameter(8, Types.VARCHAR);
								objCallableStatement.registerOutParameter(9, Types.VARCHAR);
								 //Added For CR_106 - On demand Spec Supplement
								if (objOrderVO.getPrevOrderKey() != 0)
									objCallableStatement.setString(10, DatabaseConstants.DATALOCATION_SNAP_SHOT);	
								else
									objCallableStatement.setString(10, DatabaseConstants.DATALOCATION);	//Added For CR_91
								if (objOrderVO.getPrevOrderKey() != 0)
									objCallableStatement.setInt(11,objOrderVO.getPrevOrderKey());
								else
									objCallableStatement.setNull(11,Types.NULL);
								 //Added For CR_106 - Ends here
								objCallableStatement.registerOutParameter(12, Types.INTEGER);
								objCallableStatement.registerOutParameter(13, Types.VARCHAR);
								objCallableStatement.registerOutParameter(14, Types.VARCHAR);
								objCallableStatement.execute();
								
								objSpecSuppVO.setStrPastRevCode((String)objCallableStatement.getString(3));			
								objSpecSuppVO.setStrPresentRevCode((String)objCallableStatement.getString(4));
								objSpecSuppVO.setStrPublishDate((String)objCallableStatement.getString(5));
								objSpecSuppVO.setStrPastSpecStatusDesc((String)objCallableStatement.getString(6));
								objSpecSuppVO.setStrPresentSpecStatusDesc((String)objCallableStatement.getString(7));
								objSpecSuppVO.setStrQtyInWords((String) objCallableStatement.getString(8));
								LogUtil.logMessage("objSpecSuppVO.getStrPresentSpecStatusDesc()" +objSpecSuppVO.getStrPresentSpecStatusDesc());
								objSpecSuppVO.setStrOpeningDate((String) objCallableStatement.getString(9));

								String[] stropen = objSpecSuppVO.getStrOpeningDate().split(",");
								LogUtil.logMessage("stropen"+stropen);
								
								objSpecSuppVO.setStrOpeningDate(stropen[0] + "," + " " + stropen[1]);
								
								intLSDBErrorID = (int) objCallableStatement.getInt(12);
								strOracleCode = (String) objCallableStatement.getString(13);
								strErrorMessage = (String) objCallableStatement.getString(14);
								
								LogUtil.logMessage("Message :" + strErrorMessage);
								objOrderVO.setMessage(strErrorMessage);
								
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
							
							catch (DataAccessException objDatExp) {
								ErrorInfo objErrorInfo = objDatExp.getErrorInfo();
								LogUtil
								.logMessage("Enters into getVersionsSpecSupp DataAccess Exception block in DAO:.."
										+ objDatExp.getErrorInfo().getMessageID());
								throw new BusinessException(objDatExp, objErrorInfo);
							} catch (ApplicationException objAppExp) {
								ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
								LogUtil.logMessage("Enters into getVersionsSpecSupp AppException block in  DAO :"
										+ objErrorInfo.getMessage());
								objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
										+ "-" + objErrorInfo.getMessage());
								throw new ApplicationException(objAppExp, objErrorInfo);
							} catch (Exception objExp) {
								LogUtil.logMessage("Enters into getVersionsSpecSupp Exception block in DAO:"
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
									LogUtil.logMessage("Enters into Exception block:");
									ErrorInfo objErrorInfo = new ErrorInfo();
									objErrorInfo.setMessage(ApplicationConstants.LOG_USER
											+ strLogUser + "-" + objExp.getMessage());
									throw new ApplicationException(objExp, objErrorInfo);
								}
								
							}
							return objSpecSuppVO;
							
						}
//						Added for CR-135 ends here
				}