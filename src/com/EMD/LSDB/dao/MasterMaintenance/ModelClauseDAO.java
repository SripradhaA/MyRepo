package com.EMD.LSDB.dao.MasterMaintenance;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.tomcat.dbcp.dbcp2.DelegatingConnection;

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
import com.EMD.LSDB.vo.common.ClauseTableDataVO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.CompGroupVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.StandardEquipVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for the Clause
 ******************************************************************************/
/***************************************************************************
 * ------------------------------------------------------------------------------------------------------
 *     Date         version  create by   modify by             comments                              Remarks 
 * 19/01/2010        1.0      SD41630                 Updated insertClause mehtod for      Added for CR_81
 * 													   characterisitic group  flag. 
 * 11/05/2010        1.1      SD41630                 Updated insertClause mehtod for      Added for CR_85
 * 													   pass no of link and comntnseqno .   
 * 													 	 
 *02/07/2010        1.2      SD41630                 Added new updateRearrangeClauses method 
 *   												 Updated clause postions                  Added for CR_88
 * --------------------------------------------------------------------------------------------------------
  **************************************************************************/


public class ModelClauseDAO extends EMDDAO {
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            the object for Inserting clause
	 * @return boolean that returns True or False
	 * @throws EMDException
	 **************************************************************************/
	public static synchronized int insertClause(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil.logMessage("Inside the ModelClauseDAO:insertClause");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		ArrayDescriptor arrdesc = null;
		ArrayDescriptor arrIntdesc = null;
		ArrayList arlStandardEquipmentList = new ArrayList();
		ARRAY arr = null;
		ClauseTableDataVO objTableDataVO = null;
		ArrayList arlTableList;
		int intClauseSeqNo = 0;
		int intClauseVersionNo = 0;
		int intNewClauseSeqNo = 0; //added for CR-121
		ARRAY tableDataArr1, tableDataArr2, tableDataArr3, tableDataArr4, tableDataArr5 = null;
		String strLogUser = "";
		try {
			strLogUser = objClauseVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.insert_Clause);
			objCallableStatement.setInt(1, objClauseVO.getModelSeqNo());
			objCallableStatement.setInt(2, objClauseVO.getSubSectionSeqNo());
			if (objClauseVO.getCustomerSeqNo() <= 0) {
				objCallableStatement.setNull(3, Types.NULL);
			} else {
				objCallableStatement.setInt(3, objClauseVO.getCustomerSeqNo());
			}
			objCallableStatement.setInt(4, objClauseVO.getClauseSeqNo());
			if (objClauseVO.getParentClauseSeqNo() <= 0) {
				objCallableStatement.setNull(5, Types.NULL);
			} else {
				objCallableStatement.setInt(5, objClauseVO
						.getParentClauseSeqNo());
			}
			objCallableStatement.setString(6, objClauseVO.getClauseDesc());
			Connection dconn = ((DelegatingConnection) objConnnection).getInnermostDelegate(); //Added for CR-123 & Tomcat
			arrdesc = new ArrayDescriptor(DatabaseConstants.STR_ARRAY,
					dconn);
			arr = new ARRAY(arrdesc, dconn,
					processComponentVO(objClauseVO));
			objCallableStatement.setArray(7, arr);
			
			ARRAY arrEdlno = new ARRAY(arrdesc, dconn, objClauseVO
					.getEdlNo());
			if (arrEdlno.length() == 0) {
				objCallableStatement.setNull(8, Types.NULL);
			} else {
				objCallableStatement.setArray(8, arrEdlno);
			}
			ARRAY arrRefEDLNO = new ARRAY(arrdesc, dconn, objClauseVO
					.getRefEDLNo());
			
			if (objClauseVO.getRefEDLNo() == null) {
				arrRefEDLNO = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(9, arrRefEDLNO);
			} else {
				objCallableStatement.setArray(9, arrRefEDLNO);
			}
			
			ARRAY arrPartOfSeqNO = new ARRAY(arrdesc, dconn,
					objClauseVO.getPartOfSeqNo());
			if (arrPartOfSeqNO.length() == 0) {
				
				objCallableStatement.setNull(10, Types.NULL);
			} else {
				
				objCallableStatement.setArray(10, arrPartOfSeqNO);
			}
			
			if (objClauseVO.getDwONumber() == 0) {
				objCallableStatement.setNull(11, Types.NULL);
			} else {
				objCallableStatement.setInt(11, objClauseVO.getDwONumber());
			}
			if (objClauseVO.getPartNumber() == 0) {
				objCallableStatement.setNull(12, Types.NULL);
			} else {
				objCallableStatement.setInt(12, objClauseVO.getPartNumber());
			}
			if (objClauseVO.getPriceBookNumber() == 0) {
				objCallableStatement.setNull(13, Types.NULL);
			} else {
				objCallableStatement.setInt(13, objClauseVO
						.getPriceBookNumber());
			}
			if (objClauseVO.getObjStandardEquipVO() == null) {
				objCallableStatement.setNull(14, Types.NULL);
			} else {
				arlStandardEquipmentList = objClauseVO.getObjStandardEquipVO();
				StandardEquipVO objStandardEquipVO = (StandardEquipVO) arlStandardEquipmentList
				.get(0);
				objCallableStatement.setInt(14, objStandardEquipVO
						.getStandardEquipmentSeqNo());
			}
			if (objClauseVO.getComments() == null) {
				objCallableStatement.setNull(15, Types.NULL);
			} else {
				objCallableStatement.setString(15, objClauseVO.getComments());
			}
			objCallableStatement.setString(16, objClauseVO.getReason());
			
			arlTableList = objClauseVO.getTableDataVO();
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(0);
			
			tableDataArr1 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData1());
			
			if (objTableDataVO.getTableArrayData1() == null) {
				tableDataArr1 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(17, tableDataArr1);
			} else {
				objCallableStatement.setArray(17, tableDataArr1);
			}
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(1);
			tableDataArr2 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData2());
			
			if (objTableDataVO.getTableArrayData2() == null) {
				tableDataArr2 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(18, tableDataArr2);
			} else {
				objCallableStatement.setArray(18, tableDataArr2);
			}
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(2);
			tableDataArr3 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData3());
			
			if (objTableDataVO.getTableArrayData3() == null) {
				tableDataArr3 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(19, tableDataArr3);
			} else {
				objCallableStatement.setArray(19, tableDataArr3);
			}
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(3);
			tableDataArr4 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData4());
			
			if (objTableDataVO.getTableArrayData4() == null) {
				tableDataArr4 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(20, tableDataArr4);
			} else {
				objCallableStatement.setArray(20, tableDataArr4);
			}
			
			objTableDataVO = (ClauseTableDataVO) arlTableList.get(4);
			tableDataArr5 = new ARRAY(arrdesc, dconn, objTableDataVO
					.getTableArrayData5());
			if (objTableDataVO.getTableArrayData5() == null) {
				tableDataArr5 = new ARRAY(arrdesc, dconn, null);
				objCallableStatement.setArray(21, tableDataArr5);
			} else {
				objCallableStatement.setArray(21, tableDataArr5);
			}
			LogUtil.logMessage("Apply Default Flag in ModelClauseDAO:"
					+ objClauseVO.getDefaultFlag());
			if ("Y".equals(objClauseVO.getDefaultFlag())) {
				objCallableStatement
				.setString(22, objClauseVO.getDefaultFlag());
				LogUtil.logMessage("Apply Default Flag in ModelClauseDAO:"
						+ objClauseVO.getDefaultFlag());
			} else {
				objCallableStatement.setNull(22, Types.NULL);
			}
			
			/*******************************************************************
			 * one more input parameter clauseSource is added based on
			 * LSDB_CR-35 * Added on 04-April-08 by ps57222
			 *  
			 ******************************************************************/
			
			objCallableStatement.setString(23, objClauseVO.getClauseSource());
			
			ARRAY arrPartOfClaSeqNo = new ARRAY(arrdesc, dconn,
					objClauseVO.getPartOfClaSeqNo());
			
			if (arrPartOfClaSeqNo.length() == 0) {
				objCallableStatement.setNull(24, Types.NULL);
			} else {
				objCallableStatement.setArray(24, arrPartOfClaSeqNo);
			}
			
			ARRAY arrPartOfSeqCode = new ARRAY(arrdesc, dconn,
					objClauseVO.getPartOfCode());
			
			if (arrPartOfSeqCode.length() == 0) {
				objCallableStatement.setNull(25, Types.NULL);
			} else {
				objCallableStatement.setArray(25, arrPartOfSeqCode);
			}
			
			/**Added FOR CR-74 not to show validation if Clause is added from Model Level**/
			//if this parameter is not null validation will be performed
			objCallableStatement.setNull(26,Types.NULL);
			/**Added FOR CR-74 not to show validation if Clause is added from Model Level**/
//			 Added For CR_81 on 24-Dec-09 by SD41630 ------- -->
			LogUtil.logMessage("Inside ModelAddClauseDAO :InsertClause  + Characteristic Group Clause value is setting before exc ");
			objCallableStatement.setString(27,objClauseVO.getSelectCGCFlag());
//			 Added For CR_81 on 24-Dec-09 by  sd 41630-------  Ends here-->
			/* Added for Attach Clause CR-End */

			// Added For CR_85
/*			if (objClauseVO.getCombntnSeqNo() == 0) {
				objCallableStatement.setNull(28, Types.NULL);
			} else {
				objCallableStatement.setInt(28, objClauseVO.getCombntnSeqNo());
			}*/
			
			
			//CR 88
			arrIntdesc = new ArrayDescriptor(DatabaseConstants.IN_ARRAY,
					dconn);
			
			ARRAY arrCombntnSeqNo = new ARRAY(arrIntdesc, dconn,
					objClauseVO.getCombntnSeqNoVO());
			
						
			if (arrCombntnSeqNo.length() == 0) {
				arrCombntnSeqNo = new ARRAY(arrIntdesc, dconn, null);
				objCallableStatement.setArray(28, arrCombntnSeqNo);
				//objCallableStatement.setNull(28, Types.NULL);
			} else {
				objCallableStatement.setArray(28, arrCombntnSeqNo);
			}

			if (objClauseVO.getLinkClaSeqNo() == 0) {
				objCallableStatement.setNull(29, Types.NULL);
			} else {
				objCallableStatement.setInt(29, objClauseVO.getLinkClaSeqNo());
			}	
			
/**Added FOR CR-74 not to show validation if Clause is added from Model Level**/
			
			objCallableStatement.setString(30, objClauseVO.getUserID());
			
			/* Added for Attach Clause CR-Begin */
			if (objClauseVO.getLeadCompGrpSeqNo() == 0) {
				objCallableStatement.setNull(31, Types.NULL);
			} else {
				objCallableStatement.setInt(31, objClauseVO
						.getLeadCompGrpSeqNo());
			}
			objCallableStatement.registerOutParameter(32, Types.INTEGER);
			objCallableStatement.registerOutParameter(33, Types.VARCHAR);
			objCallableStatement.registerOutParameter(34, Types.VARCHAR);
			objCallableStatement.registerOutParameter(35, Types.INTEGER);
			objCallableStatement.registerOutParameter(36, Types.INTEGER);
			objConnnection.setAutoCommit(false);
			
			intStatus = objCallableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
				
			}
			
			intLSDBErrorID = (int) objCallableStatement.getInt(32);
			strOracleCode = (String) objCallableStatement.getString(33);
			strErrorMessage = (String) objCallableStatement.getString(34);
			intClauseSeqNo = (int) objCallableStatement.getInt(35);
			intClauseVersionNo = (int) objCallableStatement.getInt(36);
			objClauseVO.setClauseSeqNo(intClauseSeqNo);
			objClauseVO.setVersionNo(intClauseVersionNo);
			
			//Added for CR_114
			if(objClauseVO.isMapAppendixImg()){
				objCallableStatement = objConnnection
				.prepareCall(EMDQueries.SP_MAP_MDL_CLA_APNDX_IMG);
				LogUtil.logMessage("Mapping the appendix image to the clause"
						+ objClauseVO.getSubSectionSeqNo());
				
				if(objClauseVO.getModelSeqNo()!=0){				
				objCallableStatement.setInt(1, objClauseVO.getModelSeqNo());
				}else{
					objCallableStatement.setNull(1, Types.NULL);
				}
				
				if(objClauseVO.getAppendixImgSeqNo()!= 0){
					objCallableStatement.setInt(2, objClauseVO.getAppendixImgSeqNo());
				}else{
					objCallableStatement.setNull(2, Types.NULL);
				}
				
				if(objClauseVO.getClauseSeqNo() != 0){
					objCallableStatement.setInt(3, objClauseVO.getClauseSeqNo());
				}else{
					objCallableStatement.setNull(3, Types.NULL);
				}
				
				if(objClauseVO.getVersionNo() != 0){
					objCallableStatement.setInt(4, objClauseVO.getVersionNo());
				}else{
					objCallableStatement.setNull(4, Types.NULL);
				}
				
				objCallableStatement.setString(5, objClauseVO.getUserID());
				
				objCallableStatement.registerOutParameter(6, Types.INTEGER);
				objCallableStatement.registerOutParameter(7, Types.VARCHAR);
				objCallableStatement.registerOutParameter(8, Types.VARCHAR);
				
				intStatus = objCallableStatement.executeUpdate();
				
				
				if (intStatus > 0) {
					intStatus = 0;
					LogUtil.logMessage("intStatus before reset to 0===" + intStatus);
				}
				
				LogUtil.logMessage("intStatus .." + intStatus);
				intLSDBErrorID = (int) objCallableStatement.getInt(6);
				strOracleCode = (String) objCallableStatement.getString(7);
				strErrorMessage = (String) objCallableStatement.getString(8);
				if (intLSDBErrorID != 0) {
					LogUtil.logMessage("Error ID:" + intLSDBErrorID);
					LogUtil.logMessage("strOracleCode:" + strOracleCode);
					
				}
				if (intLSDBErrorID != 0) {
					LogUtil.logMessage("Enters into Error Loop:");
					ErrorInfo objErrorInfo = new ErrorInfo();
					
					objErrorInfo.setMessageID(String.valueOf(intLSDBErrorID));
					LogUtil.logMessage("Error message in ErrorInfo:"
							+ objErrorInfo.getMessageID());
					throw new DataAccessException(objErrorInfo);
					
				} else if (strOracleCode != null && !"0".equals(strOracleCode)) {// Un
					// handled
					// exception
					LogUtil.logMessage("enters into oracle error code block:"
							+ strOracleCode);
					ErrorInfo objErrorInfo = new ErrorInfo();
					StringBuffer strBuffer = new StringBuffer();
					strBuffer.append(strOracleCode + " ");
					strBuffer.append(strErrorMessage);
					objErrorInfo.setMessage(strBuffer.toString());
					objConnnection.rollback();
					throw new ApplicationException(objErrorInfo);
				}
				
			}
			//Added for CR_114 Ends Here
			
			//Added for CR_121
			if(objClauseVO.getSubClaExistsFlag() == null)objClauseVO.setSubClaExistsFlag("N");
			if(objClauseVO.getSubClaExistsFlag().equalsIgnoreCase("Y")){
				objCallableStatement = objConnnection
				.prepareCall(EMDQueries.SP_COPY_CLA_DETAILS);
				/*LogUtil.logMessage("objClauseVO.getSubClaExistsFlag():"
						+ objClauseVO.getSubClaExistsFlag());*/
				
				objCallableStatement.setInt(1, objClauseVO.getModelSeqNo());
				objCallableStatement.setInt(2, objClauseVO.getSubSectionSeqNo());
				objCallableStatement.setInt(3, objClauseVO.getLeadCompGrpSeqNo());	
				objCallableStatement.setInt(4, objClauseVO.getLeadComponentSeqNo());
				
				/*LogUtil.logMessage("objClauseVO.getModelSeqNo():"
						+objClauseVO.getModelSeqNo());
				LogUtil.logMessage("objClauseVO.getSubSectionSeqNo():"
						+objClauseVO.getSubSectionSeqNo());
				LogUtil.logMessage("objClauseVO.getLeadCompGrpSeqNo():"
						+objClauseVO.getLeadCompGrpSeqNo());
				LogUtil.logMessage("objClauseVO.getLeadComponentSeqNo():"
						+objClauseVO.getLeadComponentSeqNo());
				LogUtil.logMessage("objClauseVO.getClauseSeqNo():"
						+objClauseVO.getClauseSeqNo());
				LogUtil.logMessage("objClauseVO.getHdnClauseSeqNo():"
						+objClauseVO.getHdnClauseSeqNo());
				LogUtil.logMessage("objClauseVO.getUserID():"
						+objClauseVO.getUserID());*/
				
				if(objClauseVO.getClauseSeqNo() != 0){
					objCallableStatement.setInt(5, objClauseVO.getClauseSeqNo());
				}else{
					objCallableStatement.setNull(5, Types.NULL);
				}
				
				objCallableStatement.setInt(6,objClauseVO.getHdnClauseSeqNo());
				objCallableStatement.setString(7, "M");
				objCallableStatement.registerOutParameter(8, Types.INTEGER);
				objCallableStatement.setString(9, "Y");
				objCallableStatement.setString(10, objClauseVO.getUserID());
				objCallableStatement.registerOutParameter(11, Types.INTEGER);
				objCallableStatement.registerOutParameter(12, Types.VARCHAR);
				objCallableStatement.registerOutParameter(13, Types.VARCHAR);
				
				intStatus = objCallableStatement.executeUpdate();
				
				intNewClauseSeqNo = (int) objCallableStatement.getInt(8);
				LogUtil.logMessage("intNewClauseSeqNo:"
						+ intNewClauseSeqNo);
				
				if (intStatus > 0) {
					intStatus = 0;
					LogUtil.logMessage("intStatus before reset to 0===" + intStatus);
				}
				
				LogUtil.logMessage("intStatus .." + intStatus);
				intLSDBErrorID = (int) objCallableStatement.getInt(11);
				strOracleCode = (String) objCallableStatement.getString(12);
				strErrorMessage = (String) objCallableStatement.getString(13);
				if (intLSDBErrorID != 0) {
					LogUtil.logMessage("Error ID:" + intLSDBErrorID);
					LogUtil.logMessage("strOracleCode:" + strOracleCode);
					
				}
				if (intLSDBErrorID != 0) {
					LogUtil.logMessage("Enters into Error Loop:");
					ErrorInfo objErrorInfo = new ErrorInfo();
					
					objErrorInfo.setMessageID(String.valueOf(intLSDBErrorID));
					LogUtil.logMessage("Error message in ErrorInfo:"
							+ objErrorInfo.getMessageID());
					throw new DataAccessException(objErrorInfo);
					
				} else if (strOracleCode != null && !"0".equals(strOracleCode)) {// Un
					// handled
					// exception
					LogUtil.logMessage("enters into oracle error code block:"
							+ strOracleCode);
					ErrorInfo objErrorInfo = new ErrorInfo();
					StringBuffer strBuffer = new StringBuffer();
					strBuffer.append(strOracleCode + " ");
					strBuffer.append(strErrorMessage);
					objErrorInfo.setMessage(strBuffer.toString());
					objConnnection.rollback();
					throw new ApplicationException(objErrorInfo);
				}
				
			}
			//Added for CR-121 ends here
			
			
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
			.logMessage("Enters into DataAccess Exception block in ModelClauseDAO:insertClause"
					+ objDatExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDatExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ModelClauseDAO:insertClause:"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelClauseDAO:insertClause:"
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
				.logMessage("Enter into Exception block in ModelClauseDAO:insertClause:"
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
	 * @param objClauseVO
	 *            the object for processing Components
	 * @return integer Array that returns component Sequence No's
	 * @throws EMDException
	 **************************************************************************/
	private static int[] processComponentVO(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil
		.logMessage("Inside the Inside the ModelClauseDAO:processComponentVO ");
		
		int intComponentSeqNo[];
		
		ArrayList componentSeqArray = objClauseVO.getComponentVO();
		intComponentSeqNo = new int[componentSeqArray.size()];
		if (componentSeqArray != null && componentSeqArray.size() > 0) {
			for (int counter = 0; counter < componentSeqArray.size(); counter++) {
				intComponentSeqNo[counter] = Integer.parseInt(componentSeqArray
						.get(counter).toString());
			}
		}
		
		LogUtil.logMessage("Leaving processcomponentVO");
		return intComponentSeqNo;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            the object for fetching Parent Clause
	 * @return ArrayList containing the parent clause
	 * @throws EMDException
	 **************************************************************************/
	public static ArrayList fetchParentClause(ClauseVO objClauseVO)
	throws EMDException {
		
		LogUtil
		.logMessage("Inside the Inside the ModelClauseDAO:fetchParentClause");
		Connection objConnnection = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		ArrayList arlParentClauseList = new ArrayList();
		ArrayList arlComponentList = new ArrayList();
		ResultSet clauseResultSet = null;
		ResultSet componentResultSet = null;
		ResultSet edlResultSet = null;
		ResultSet refEdlResultSet = null;
		ResultSet partOfResultSet = null;
		ResultSet tableResultSet = null;
		ResultSet stdEquipResultSet = null;
		CallableStatement objCallableStatement = null;
		
		String strLogUser = "";
		ArrayList arlTableRows = null;
		try {
			strLogUser = objClauseVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_FETCH_PARENT_CLAUSE);
			
			LogUtil.logMessage("objClauseVO.getSubSectionSeqNo()...."
					+ objClauseVO.getSubSectionSeqNo());
			objCallableStatement.setNull(1, Types.NULL);
			objCallableStatement.setInt(2, objClauseVO.getSubSectionSeqNo());
			objCallableStatement.setNull(3, Types.NULL);
			objCallableStatement.setNull(4, Types.NULL);
			objCallableStatement.setString(5, DatabaseConstants.YES_FLAG);
			objCallableStatement.registerOutParameter(6, OracleTypes.CURSOR);
			objCallableStatement.setString(7, objClauseVO.getUserID());
			
			objCallableStatement.registerOutParameter(8, Types.INTEGER);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			objCallableStatement.registerOutParameter(10, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			clauseResultSet = (ResultSet) objCallableStatement.getObject(6);
			
			intLSDBErrorID = objCallableStatement.getInt(8);
			strOracleCode = objCallableStatement.getString(9);
			strErrorMessage = objCallableStatement.getString(10);
			
			LogUtil.logMessage("intLSDBErrorID ...." + intLSDBErrorID);
			LogUtil.logMessage("strOracleCode ...." + strOracleCode);
			LogUtil.logMessage("strErrorMessage ...." + strErrorMessage);
			
			while (clauseResultSet.next()) {
				objClauseVO = new ClauseVO();
				arlComponentList = new ArrayList();
				objClauseVO.setClauseSeqNo(clauseResultSet
						.getInt(DatabaseConstants.LS300_CLA_SEQ_NO));
				objClauseVO.setClauseDesc(clauseResultSet
						.getString(DatabaseConstants.LS301_CLA_DESC));
				objClauseVO.setVersionNo(clauseResultSet
						.getInt(DatabaseConstants.LS301_VERSION_NO));
				edlResultSet = (ResultSet) clauseResultSet.getObject(8);
				refEdlResultSet = (ResultSet) clauseResultSet.getObject(9);
				partOfResultSet = (ResultSet) clauseResultSet.getObject(10);
				tableResultSet = (ResultSet) clauseResultSet.getObject(11);
				ArrayList arlTableColumns = null;
				arlTableRows = new ArrayList();
				while (tableResultSet.next()) {
					arlTableColumns = new ArrayList();
					arlTableColumns.add(tableResultSet
							.getString(DatabaseConstants.LS306_TBL_DATA_COL_1));
					arlTableColumns.add(tableResultSet
							.getString(DatabaseConstants.LS306_TBL_DATA_COL_2));
					arlTableColumns.add(tableResultSet
							.getString(DatabaseConstants.LS306_TBL_DATA_COL_3));
					arlTableColumns.add(tableResultSet
							.getString(DatabaseConstants.LS306_TBL_DATA_COL_4));
					arlTableColumns.add(tableResultSet
							.getString(DatabaseConstants.LS306_TBL_DATA_COL_5));
					arlTableRows.add(arlTableColumns);
					LogUtil.logMessage("exists Table Data");
				}
				objClauseVO.setTableArrayData1(arlTableRows);
				
				stdEquipResultSet = (ResultSet) clauseResultSet.getObject(15);
				componentResultSet = (ResultSet) clauseResultSet.getObject(18);
				while (componentResultSet.next()) {
					CompGroupVO objCompGroupVO = new CompGroupVO();
					objCompGroupVO.setComponentGroupName(componentResultSet
							.getString(DatabaseConstants.LS140_COMP_NAME));
					arlComponentList.add(objCompGroupVO);
				}
				objClauseVO.setComponentVO(arlComponentList);
				arlParentClauseList.add(objClauseVO);
				DBHelper.closeSQLObjects(edlResultSet, null, null);
				DBHelper.closeSQLObjects(refEdlResultSet, null, null);
				DBHelper.closeSQLObjects(partOfResultSet, null, null);
				DBHelper.closeSQLObjects(tableResultSet, null, null);
				DBHelper.closeSQLObjects(stdEquipResultSet, null, null);
				DBHelper.closeSQLObjects(componentResultSet, null, null);
				
			}
			if (intLSDBErrorID != 0) {
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
			
		} catch (DataAccessException objDatExp) {
			ErrorInfo objErrorInfo = objDatExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into DataAccess Exception block in ModelClauseDAO:fetchParentClause"
					+ objDatExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDatExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ModelClauseDAO:fetchParentClause:"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelClauseDAO:fetchParentClause:"
					+ objExp.getMessage());
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				DBHelper.closeSQLObjects(clauseResultSet, objCallableStatement,
						objConnnection);
			}
			
			catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in ModelClauseDAO:fetchParentClause:"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		LogUtil.logMessage("arlParentClauseList size..."
				+ arlParentClauseList.size());
		return arlParentClauseList;
	}
	
	//Added For CR_88 on 2july10 by sd41630
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            the object for updateRearrangeClauses
	 * @return boolean that returns True or False
	 * @throws EMDException
	 **************************************************************************/
	public static synchronized int updateRearrangeClauses(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil.logMessage("Inside the ModelClauseDAO:updateRearrangeClauses");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		ArrayDescriptor arrClaSeqNos = null;
		String strLogUser = "";
		
		try {
			strLogUser = objClauseVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_REARRANGE_MDL_CLAUSES);
			Connection dconn = ((DelegatingConnection) objConnnection).getInnermostDelegate(); //Added for CR-123 & Tomcat
			objCallableStatement.setInt(1, objClauseVO.getModelSeqNo());
			objCallableStatement.setInt(2, objClauseVO.getSubSectionSeqNo());
			arrClaSeqNos = new ArrayDescriptor(DatabaseConstants.IN_ARRAY,
					dconn);
			LogUtil.logMessage("ArrayList ClaSeqNoList Size :"+objClauseVO.getClaSeqNoList().length);
			ARRAY arrClaSeqNo = new ARRAY(arrClaSeqNos, dconn, objClauseVO.getClaSeqNoList());
			objCallableStatement.setArray(3, arrClaSeqNo);
			objCallableStatement.setString(4, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objConnnection.setAutoCommit(false);
			intStatus = objCallableStatement.executeUpdate();
			LogUtil.logMessage("***** intStatus******** :"+intStatus);
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
				
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {//Un
							
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
			.logMessage("Enters into DataAccess Exception block in ModelClauseDAO:updateRearrangeClauses"
					+ objDatExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDatExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ModelClauseDAO:updateRearrangeClauses:"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelClauseDAO:updateRearrangeClauses:"
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
				.logMessage("Enter into Exception block in ModelClauseDAO:updateRearrangeClauses:"
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
	 * Added for CR_99 to validate clause Description
	 * @author Satyam Computer Services Ltd
	 * @version 1.0 by RJ85495
	 * @param objClauseVO
	 *            the object for validateClauseDescription
	 * @return boolean that returns True or False
	 * @throws EMDException
	 **************************************************************************/
	public static synchronized ArrayList validateClauseDescription(ClauseVO objClauseVO)
	throws EMDException {
		LogUtil.logMessage("Inside the ModelClauseDAO:validateClauseDescription");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		ArrayList arlClauseDescResult = null;
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		try {
			strLogUser=objClauseVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection.prepareCall("{call PK_LSDB_UTIL.SP_REMOVE_JUNK_CHAR(?,?,?,?,?,?,?)}");
			objCallableStatement.setString(1, objClauseVO.getClauseDesc());
			objCallableStatement.registerOutParameter(2, Types.VARCHAR);
			objCallableStatement.registerOutParameter(3, Types.VARCHAR);
			objCallableStatement.setString(4, objClauseVO.getUserID());
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			arlClauseDescResult= new ArrayList();
			objClauseVO = new ClauseVO();
			objClauseVO.setNewClauseDesc(objCallableStatement.getString(2));
			objClauseVO.setClaDiffFlag(objCallableStatement.getString(3));
			
			intLSDBErrorID = (int) objCallableStatement.getInt(5);
			strOracleCode = (String) objCallableStatement.getString(6);
			strErrorMessage = (String) objCallableStatement.getString(7);
			

			if (intLSDBErrorID != 0) {
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessageID("" + intLSDBErrorID);
			
				throw new DataAccessException(objErrorInfo);
			} else if (strOracleCode != null && !"0".equals(strOracleCode)) {// Un
				LogUtil.logMessage("strOracleCode:" + strOracleCode);
				ErrorInfo objErrorInfo = new ErrorInfo();
				StringBuffer sb = new StringBuffer();
				sb.append(strOracleCode + " ");
				sb.append(strErrorMessage);
				objErrorInfo.setMessage(sb.toString());
				objConnnection.rollback();
				throw new ApplicationException(objErrorInfo);
			}
			arlClauseDescResult.add(objClauseVO);
		}
		catch (DataAccessException objDatExp) {
			ErrorInfo objErrorInfo = objDatExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into DataAccess Exception block in ModelClauseDAO:validateClauseDescription"
					+ objDatExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDatExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  ModelClauseDAO:validateClauseDescription:"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in ModelClauseDAO:validateClauseDescription:"
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
				.logMessage("Enter into Exception block in ModelClauseDAO:validateClauseDescription:"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlClauseDescResult;
	}
	
	
	
	/***************************************************************************
	 * Created to fetch clauses for Clauses By Components Report  - CR_109
	 * @author Satyam Computer Services Ltd
	 * @version 1.0 by ER91220
	 * @param objComponentVO
	 *            the object for fetching Clauses by components
	 * @return ArrayList containing the clause details
	 * @throws EMDException
	 **************************************************************************/
	public static ArrayList fetchClauses(ComponentVO objComponentVO)
	throws EMDException {
		LogUtil.logMessage("Enters into ModelClauseDAO - fetchClauses for Clauses By Components Report");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		int countCol=0;
		
		ArrayList arlComponentList = new ArrayList();
		
		ResultSet objClauseResultSet = null;
		ResultSet objEDLNoResultSet = null;
		ResultSet objRefEDLNoResultSet = null;
		ResultSet objSubSecResultSet = null;
		ResultSet objTbDataResultSet = null;
		ResultSet objStdEqpResultSet = null;
		ResultSet objCompResultSet = null;
		ArrayList arlClauseList = new ArrayList();
		ClauseVO objClauseVO = null;
		String strLogUser = "";
		
		try {
			strLogUser = objComponentVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_CLA_BY_COMPS);
			
			objCallableStatement.setInt(1,objComponentVO.getComponentGroupSeqNo());
			
			if ((objComponentVO.getComponentSeqNo()==0)||(objComponentVO.getComponentSeqNo()==-1))
			{
				objCallableStatement.setNull(2,Types.NULL );
			}
			else
			{
				objCallableStatement.setInt(2,objComponentVO.getComponentSeqNo());
			}
			objCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			objCallableStatement.setString(4, objComponentVO.getUserID());
			//Added and modified for CR_109 Comments - To sort the report  
			objCallableStatement.setInt(5,objComponentVO.getSortByflag());
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.execute();
			objClauseResultSet = (ResultSet) objCallableStatement.getObject(3);

			intLSDBErrorID = objCallableStatement.getInt(6);
			strOracleCode = objCallableStatement.getString(7);
			strErrorMessage = (String) objCallableStatement.getString(8);
			//Modification Ends here
			
			if (intLSDBErrorID != 0) {
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
			
			while (objClauseResultSet.next()) {
				
				int cntEDL = 0;
				int cntRefEDl = 0;
				int cntPartOf = 0;
				int[] arlPartSubSecSeqNo = new int[4];
				ArrayList arlEDLNos = new ArrayList();
				ArrayList arlRefEDLNos = new ArrayList();
				ArrayList arlpartSubSecCode = new ArrayList();
				int[] arlClauseSeqNo = new int[4];
				ArrayList arlTableRows = new ArrayList();
				ArrayList arlTableColumns = null;
				int partofLeadCompGrp = 0;
				
				 
				objClauseVO = new ClauseVO();

				objClauseVO.setModelName(objClauseResultSet.getString(DatabaseConstants.LS200_MDL_NAME ));
				
				objClauseVO.setModelSeqNo(objClauseResultSet.getInt(DatabaseConstants.LS200_MDL_SEQ_NO));
				
				objClauseVO.setSubSectionSeqNo(objClauseResultSet.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO));
				
				objClauseVO.setClauseSeqNo(objClauseResultSet.getInt(DatabaseConstants.LS300_CLA_SEQ_NO));
				
				objClauseVO.setVersionNo(objClauseResultSet.getInt(DatabaseConstants.LS301_VERSION_NO));
				
				objClauseVO.setClauseDesc(objClauseResultSet.getString(DatabaseConstants.LS301_CLA_DESC));
				
				objClauseVO.setDwONumber(objClauseResultSet.getInt(DatabaseConstants.LS301_DWO_NUMBER));
				
				objClauseVO.setComments(objClauseResultSet.getString(DatabaseConstants.LS301_ENGG_DATA_COMMENTS));
				
				objClauseVO.setPartNumber(objClauseResultSet.getInt(DatabaseConstants.LS301_PART_NUMBER));
				
				objClauseVO.setPriceBookNumber(objClauseResultSet.getInt(DatabaseConstants.LS301_PRICE_BOOK_NUMBER));
				
				objClauseVO.setClaReason(objClauseResultSet.getString(DatabaseConstants.LS301_CLA_REASON));
				
				
				//Cursor of EDL Number
				objEDLNoResultSet = (ResultSet) objClauseResultSet.getObject("EDLNO");
				while (objEDLNoResultSet.next()) 
				{
					arlEDLNos.add(objEDLNoResultSet.getString(DatabaseConstants.LS302_EDL_NO));
					cntEDL++;
				}
				objClauseVO.setEdlNO(arlEDLNos);
				DBHelper.closeSQLObjects(objEDLNoResultSet, null, null);
				
				
				// Cursor of Ref EDL Number				
				objRefEDLNoResultSet = (ResultSet) objClauseResultSet.getObject("refEDLNO");
				while (objRefEDLNoResultSet.next()) {
					arlRefEDLNos.add(objRefEDLNoResultSet.getString(DatabaseConstants.LS303_REF_EDL_NO));
					cntRefEDl++;
				}
								
				objClauseVO.setRefEDLNO(arlRefEDLNos);
				DBHelper.closeSQLObjects(objRefEDLNoResultSet, null, null);
								
				// Cursor of PART OF
				objSubSecResultSet = (ResultSet) objClauseResultSet.getObject("PartOF");
				while (objSubSecResultSet.next()) {
									
					arlPartSubSecSeqNo[cntPartOf] = objSubSecResultSet.getInt(DatabaseConstants.LS202_SUBSEC_SEQ_NO);
					arlpartSubSecCode.add(objSubSecResultSet.getString(DatabaseConstants.LS304_SUBSEC_NO));
					arlClauseSeqNo[cntPartOf] = objSubSecResultSet.getInt(DatabaseConstants.LS304_PART_OF_CLA_SEQ_NO);
					partofLeadCompGrp=objSubSecResultSet.getInt(DatabaseConstants.LS304_PART_OF_LEAD_CMP_GRP);
					cntPartOf++;
				}
				
				objClauseVO.setPartOF(arlpartSubSecCode);
				objClauseVO.setPartOfSeqNo(arlPartSubSecSeqNo);
				objClauseVO.setClauseSeqNum(arlClauseSeqNo);
				objClauseVO.setPartofLeadCompGrp(partofLeadCompGrp);
				
				DBHelper.closeSQLObjects(objSubSecResultSet, null, null);
				
				//Cursor for Table Data
				objTbDataResultSet = (ResultSet) objClauseResultSet.getObject("CLA_TBL_DATA");
				while (objTbDataResultSet.next()) {
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
				
				// Cursor for Standard Equipment
				objStdEqpResultSet = (ResultSet) objClauseResultSet.getObject("STD_EQUIP");
				while (objStdEqpResultSet.next()) {
					objClauseVO.setStandardEquipmentDesc(objStdEqpResultSet.getString(DatabaseConstants.LS060_STD_EQP_DESC));
					objClauseVO.setStandardEquipmentSeqNo(objStdEqpResultSet.getInt(DatabaseConstants.LS060_STD_EQP_SEQ_NO));
					
				}
				DBHelper.closeSQLObjects(objStdEqpResultSet, null, null);
				
				//Cursor For Components
				objCompResultSet = (ResultSet) objClauseResultSet.getObject("CLA_COMP");
				arlComponentList = new ArrayList();
				while (objCompResultSet.next()){
					ComponentVO objCompVO = new ComponentVO();
					objCompVO.setComponentGroupSeqNo(objCompResultSet.getInt(DatabaseConstants.LS130_COMP_GRP_SEQ_NO));
					objCompVO.setComponentGroupName(objCompResultSet.getString(DatabaseConstants.LS130_COMP_GRP_NAME));
					objCompVO.setValidationFlag(objCompResultSet.getString(DatabaseConstants.LS130_VALIDATION_FLAG));
					objCompVO.setComponentSeqNo(objCompResultSet.getInt(DatabaseConstants.COMP_SEQ_NO));
					objCompVO.setComponentName(objCompResultSet.getString(DatabaseConstants.COMP_NAME));
					objCompVO.setCompDefFlag(objCompResultSet.getString(DatabaseConstants.LS204_DEFAULT_FLAG));
					objCompVO.setCompLeadFlag(objCompResultSet.getString(DatabaseConstants.Italics));
					arlComponentList.add(objCompVO);
					}
				objClauseVO.setComponentList(arlComponentList);
				DBHelper.closeSQLObjects(objCompResultSet, null, null);
				arlClauseList.add(objClauseVO);
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil.logMessage("Enters into catch block in DAO:.."
					+ objDataExp.getMessage());
			LogUtil.logMessage("Enters into catch block in DAO:.."
					+ objErrorInfo);
			LogUtil.logMessage("Enters into catch block in DAO:.."
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil.logMessage("Enters into catch block in DAO:.."
					+ objErrorInfo);
			LogUtil.logMessage("Enters into catch block in DAO:.."
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
				
				DBHelper.closeSQLObjects(objClauseResultSet,
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
		return arlClauseList;
		
	}
	
	/***************************************************************************
	 * This method is for updating user level marker
	 * Added for LSDB_CR-134 by Anu
	 * @version 1.0
	 * @param objClauseVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public static int updateUserMarker(ClauseVO objClauseVO) throws EMDException {
		LogUtil.logMessage("Inside the ModelClauseDAO:updateUserMarker");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		
		try {
			strLogUser = objClauseVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			objCallableStatement = objConnection.prepareCall(EMDQueries.SP_MARK_MDL_CLAUSE_VER);
			
			objCallableStatement.setInt(1, objClauseVO.getClauseSeqNo());
			LogUtil.logMessage("ClauseSeqNo()"+objClauseVO.getClauseSeqNo());
			
			objCallableStatement.setInt(2, objClauseVO.getVersionNo());
			LogUtil.logMessage("VersionNo()"+objClauseVO.getVersionNo());
			
			objCallableStatement.setString(3, objClauseVO.getUserMarkFlag());
			LogUtil.logMessage("getFlag()"+objClauseVO.getUserMarkFlag());
			
			objCallableStatement.setString(4, objClauseVO.getMarkClaReason());
			LogUtil.logMessage("getMarkClaReason()"+objClauseVO.getMarkClaReason());
			objCallableStatement.setString(5, objClauseVO.getUserID());
			LogUtil.logMessage("getUserID()"+objClauseVO.getUserID());
			
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			LogUtil.logMessage("Status:" + intStatus);
			intStatus = objCallableStatement.executeUpdate();
			
			LogUtil.logMessage("Status:" + intStatus);
			if (intStatus > 0) {
				intStatus = 0;
				
			}
			
			intLSDBErrorID = (int) objCallableStatement.getInt(6);
			strOracleCode = (String) objCallableStatement.getString(7);
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
			} catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
		}
		LogUtil.logMessage("@@@Status:" + intStatus);
		return intStatus;
	}
	
	
	
}