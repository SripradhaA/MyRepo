/*AK49339
 * Created on Aug 28, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.dao.SpecMaintenance;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
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
import com.EMD.LSDB.dao.common.DBHelper;
import com.EMD.LSDB.dao.common.EMDDAO;
import com.EMD.LSDB.dao.common.EMDQueries;
import com.EMD.LSDB.vo.common.SpecificationItemVO;
import com.EMD.LSDB.vo.common.SpecificationVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for the Order Specifications
 ******************************************************************************/

public class OrderSpecificationDAO extends EMDDAO {
	
	private OrderSpecificationDAO() {
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objModelVO
	 *            the object for inserting spec item
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int insertSpecificationItem(
			SpecificationVO objSpecificationVO, Connection objConnnection,
			boolean blnFlag) throws EMDException {
		LogUtil
		.logMessage("Entering OrderSpecificationDAO.insertSpecificationItem");
		CallableStatement objCallableStatement = null;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intStatus = 0;
		String strLogUser = "";
		try {
			strLogUser = objSpecificationVO.getUserID();
			if (blnFlag) {
				objConnnection = DBHelper.prepareConnection();
			}
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_INSERT_ORDER_SPEC_ITEM);
			objCallableStatement.setString(1, objSpecificationVO
					.getDataLocationType());
			objCallableStatement.setInt(2, objSpecificationVO.getOrderKey());
			objCallableStatement.setInt(3, objSpecificationVO.getSpecSeqNo());
			
			LogUtil.logMessage("objSpecificationVO.getSpecSeqNo() .."
					+ objSpecificationVO.getSpecSeqNo());
			objCallableStatement.setString(4, objSpecificationVO
					.getSpecificationItem().getItemDesc());
			LogUtil
			.logMessage("objSpecificationVO.getSpecificationItem().getItemDesc() .."
					+ objSpecificationVO.getSpecificationItem()
					.getItemDesc());
			
			objCallableStatement.setString(5, objSpecificationVO
					.getSpecificationItem().getItemValue());
			
			LogUtil
			.logMessage("objSpecificationVO.getSpecificationItem().getItemValue() .."
					+ objSpecificationVO.getSpecificationItem()
					.getItemValue());
			
			objCallableStatement.setString(6, objSpecificationVO.getUserID());
			objCallableStatement.registerOutParameter(7, Types.INTEGER);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			intStatus = objCallableStatement.executeUpdate();
			
			LogUtil
			.logMessage("Inside the insertSpecificationItem method of OrderSpecificationDAO :intStatus .."
					+ intStatus);
			if (intStatus > 0) {
				intStatus = 0;
				
			}
			
			intLSDBErrorID = (int) objCallableStatement.getInt(7);
			strOracleCode = (String) objCallableStatement.getString(8);
			strErrorMessage = (String) objCallableStatement.getString(9);
			
			if (intLSDBErrorID != 0) {
				
				LogUtil.logMessage("Error ID:" + intLSDBErrorID);
				
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
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into DataAccess Exception block in DAO:.."
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil.logMessage("Enters into AppException block in  DAO :"
					+ objErrorInfo.getMessage());
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:"
					+ objExp.getMessage());
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				
				if (blnFlag)
					DBHelper.closeSQLObjects(null, objCallableStatement,
							objConnnection);
				else
					DBHelper.closeSQLObjects(null, objCallableStatement, null);
				
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
	 * @version 1.0
	 * @param objModelVO
	 *            the object for inserting spec
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int insertSpecification(SpecificationVO objSpecificationVO)
	throws EMDException {
		LogUtil
		.logMessage("Entering OrderSpecificationDAO.insertSpecification");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		boolean blnStatus = false;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intSpecItem = 0;
		String strLogUser = "";
		try {
			strLogUser = objSpecificationVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_INSERT_ORDER_SPEC);
			objCallableStatement.setString(1, objSpecificationVO
					.getDataLocationType());
			objCallableStatement.setInt(2, objSpecificationVO.getOrderKey());
			objCallableStatement.setString(3, objSpecificationVO.getSpecName());
			objCallableStatement.setString(4, objSpecificationVO.getUserID());
			
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			intStatus = objCallableStatement.executeUpdate();
			
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
			
			int intOrderSpecSeqNo = (int) objCallableStatement.getInt(5);
			
			objSpecificationVO.setSpecSeqNo(intOrderSpecSeqNo);
			intSpecItem = insertSpecificationItem(objSpecificationVO,
					objConnnection, false);
			
			if (intSpecItem == 0) {
				intStatus = 0;
			}
			
		}
		
		catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into DataAccess Exception block in DAO:.."
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil.logMessage("Enters into AppException block in  DAO :"
					+ objErrorInfo.getMessage());
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
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
	 * @version 1.0
	 * @param
	 * @return @throws
	 *         EMDException
	 **************************************************************************/
	
	public static ArrayList fetchSpecificationItems(
			SpecificationVO objSpecificationVO) throws EMDException {
		LogUtil
		.logMessage("Entering OrderSpecificationDAO.fetchSpecificationItems");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		//Error out parameters
		ResultSet objResultSetSpec = null;
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		SpecificationItemVO objSpecificationitemVO = null;
		SpecificationVO objSpecVO = null;
		ArrayList arlSpecWholeList = new ArrayList();
		ArrayList arlSpecItemList = new ArrayList();
		ResultSet rsSpecItem = null;
		// Added for CR-74 VV49326 02-06-09
		ResultSet rsHPRevMarker = null;
		ResultSet rsSpecItemMarker = null;
		String strLogUser = "";
		try {
			strLogUser = objSpecificationVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			
			objCallableStatement = objConnnection
			.prepareCall(EMDQueries.SP_SELECT_ORDER_SPEC_ITEM);
			objCallableStatement.setString(1, objSpecificationVO
					.getDataLocationType());
			objCallableStatement.setInt(2, objSpecificationVO.getOrderKey());
			objCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			// Added for CR-74 02-06-09 VV49326
			objCallableStatement.setInt(4, objSpecificationVO.getRevCode());

			objCallableStatement.setString(5, objSpecificationVO.getUserID());

			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.execute();
			
			objResultSetSpec = (ResultSet) objCallableStatement.getObject(3);
			LogUtil
			.logMessage("Inside the fetchSpecificationItems method of OrderSpecificationDAO :resultSet"
					+ objResultSetSpec);
			
			intLSDBErrorID = (int) objCallableStatement.getInt(6);
			strOracleCode = (String) objCallableStatement.getString(7);
			strErrorMessage = (String) objCallableStatement.getString(8);
			
			//Handled Valid Exception
			if (intLSDBErrorID != 0) {
				
				ErrorInfo objErrorInfo = new ErrorInfo();
				
				objErrorInfo.setMessageID("" + intLSDBErrorID);
				
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
			
			while (objResultSetSpec.next()) {
				LogUtil.logMessage("Inside result Set");
				objSpecVO = new SpecificationVO();
				arlSpecItemList = new ArrayList();
				// Added for CR-74 VV49326 02-06-09
				ArrayList arlHpMarkers = new ArrayList();
				ArrayList arlSpecItemMarker = new ArrayList();
				objSpecVO.setSpecSeqNo(objResultSetSpec
						.getInt(DatabaseConstants.LS401_ORDR_SPEC_SEQ_NO));
				LogUtil.logMessage("seq no...." + objSpecVO.getSpecSeqNo());
				objSpecVO.setSpecName(objResultSetSpec
						.getString(DatabaseConstants.LS401_SPEC_DESC));
				LogUtil.logMessage("seq desc...." + objSpecVO.getSpecName());
				objSpecVO.setHpDesc(objResultSetSpec
						.getString(DatabaseConstants.LS400_HP_DESC));
				LogUtil.logMessage("hp Desc" + objSpecVO.getHpDesc());
				/** Added for CR-74 VV49326 02-06-09 - Starts Here* */
				rsHPRevMarker = (ResultSet) objResultSetSpec
						.getObject(DatabaseConstants.HP_REVISION_MARKER);
				while (rsHPRevMarker.next()) {

					arlHpMarkers.add(rsHPRevMarker
							.getString(DatabaseConstants.REVISION_NUM));

				}
				objSpecVO.setHpRatingMarkers(arlHpMarkers);
				LogUtil.logMessage("HP Markers" + arlHpMarkers.size());
				DBHelper.closeSQLObjects(rsHPRevMarker, null, null);
				/** Added for CR-74 VV49326 02-06-09 - Ends Here* */
				rsSpecItem = (ResultSet) objResultSetSpec
						.getObject(DatabaseConstants.ITEMS);
								
				while (rsSpecItem.next()) {
					arlSpecItemMarker = new ArrayList();
					objSpecificationitemVO = new SpecificationItemVO();
					objSpecificationitemVO.setItemSeqNo(rsSpecItem
							.getInt(DatabaseConstants.LS402_ORDR_ITEM_SEQ_NO));
					objSpecificationitemVO.setItemDesc(rsSpecItem
							.getString(DatabaseConstants.LS402_ITEM_DESC));
					objSpecificationitemVO.setItemValue(rsSpecItem
							.getString(DatabaseConstants.LS402_ITEM_VALUE));
					/** Added for CR-74 VV49326 02-06-09 - Starts Here* */
					rsSpecItemMarker = (ResultSet) rsSpecItem
							.getObject(DatabaseConstants.REVISION_MARKER);
					while (rsSpecItemMarker.next()) {
						
						arlSpecItemMarker.add(rsSpecItemMarker
								.getString(DatabaseConstants.REVISION_NUM));

					}
					objSpecificationitemVO.setItemMarker(arlSpecItemMarker);
					
					/* Added for CR-76 VV49326 - Starts Here*/
					objSpecificationitemVO.setSysMarkFlag(rsSpecItem
							 .getString(DatabaseConstants.LS402_SYS_MARKER));
					objSpecificationitemVO.setSysMarkDesc(rsSpecItem
							 .getString(DatabaseConstants.SYS_MARKER_DESC));
					/* Added for CR-76 VV49326 - Ends Here*/
					
					
					/** Added for CR-74 VV49326 02-06-09 - Ends Here* */
					arlSpecItemList.add(objSpecificationitemVO);
					
				}
				DBHelper.closeSQLObjects(rsSpecItemMarker, null, null);
				LogUtil.logMessage("Spec Item List Size"+arlSpecItemList.size());
				
				/* Added for CR-76 VV49326 - Starts Here*/
				objSpecVO.setHpSysMarkFlag(objResultSetSpec
						.getString(DatabaseConstants.HP_DESC_SYS_MARKER));
				LogUtil.logMessage("Hp Rating System Marker"+objSpecVO.getHpSysMarkFlag());
				objSpecVO.setHpSysMarkDesc(objResultSetSpec
						.getString(DatabaseConstants.HP_SYS_MARKER_DESC));
				LogUtil.logMessage("Hp Rating Sys Marker Desc"
						+objSpecVO.getHpSysMarkDesc());
				/* Added for CR-76 VV49326 - Ends Here*/
				
				
				objSpecVO.setSpecItem(arlSpecItemList);
				arlSpecWholeList.add(objSpecVO);
			}
			LogUtil.logMessage("objSpecWholeList in DAO :"
					+ arlSpecWholeList.size());
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into DataAccess Exception block in DAO:.."
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil.logMessage("Enters into AppException block in  DAO :"
					+ objErrorInfo.getMessage());
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:"
					+ objExp.getMessage());
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			try {
				if (rsSpecItem != null)
					rsSpecItem.close();
				DBHelper.closeSQLObjects(objResultSetSpec,
						objCallableStatement, objConnnection);
			} catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return arlSpecWholeList;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param the
	 *            object for inserting spec item
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int updateSpecificationItem(SpecificationVO objSpecificationVO)
	throws EMDException {
		LogUtil
		.logMessage("Entering OrderSpecificationDAO.updateSpecificationItem");
		Connection objConnnection = null;
		CallableStatement callableStatement = null;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intStatus = 0;
		String strLogUser = "";
		try {
			strLogUser = objSpecificationVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			
			callableStatement = objConnnection
			.prepareCall(EMDQueries.SP_UPDATE_ORDER_SPEC_ITEM);
			
			callableStatement.setString(1, objSpecificationVO
					.getDataLocationType());
			callableStatement.setInt(2, objSpecificationVO.getOrderKey());
			callableStatement.setInt(3, objSpecificationVO
					.getSpecificationItem().getItemSeqNo());
			callableStatement.setString(4, objSpecificationVO
					.getSpecificationItem().getItemDesc());
			callableStatement.setString(5, objSpecificationVO
					.getSpecificationItem().getItemValue());
			callableStatement.setString(6, objSpecificationVO.getUserID());
			
			callableStatement.registerOutParameter(7, Types.INTEGER);
			callableStatement.registerOutParameter(8, Types.VARCHAR);
			callableStatement.registerOutParameter(9, Types.VARCHAR);
			intStatus = callableStatement.executeUpdate();
			
			LogUtil
			.logMessage("Inside the updateSpecificationItem method of OrderSpecificationDAO :intStatus .."
					+ intStatus);
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			intLSDBErrorID = (int) callableStatement.getInt(7);
			strOracleCode = (String) callableStatement.getString(8);
			strErrorMessage = (String) callableStatement.getString(9);
			
			if (intLSDBErrorID != 0) {
				
				LogUtil.logMessage("Error ID:" + intLSDBErrorID);
				
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
				StringBuffer sb = new StringBuffer();
				sb.append(strOracleCode + " ");
				sb.append(strErrorMessage);
				objErrorInfo.setMessage(sb.toString());
				throw new ApplicationException(objErrorInfo);
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into DataAccess Exception block in DAO:.."
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil.logMessage("Enters into AppException block in  DAO :"
					+ objErrorInfo.getMessage());
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:"
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
	 * @version 1.0
	 * @param SpecificationItemVO
	 *            the object for inserting spec item
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public static int updateHpRating(SpecificationVO objSpecificationVO)
	throws EMDException {
		LogUtil.logMessage("Entering OrderSpecificationDAO.updateHpRating");
		Connection objConnnection = null;
		CallableStatement callableStatement = null;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intStatus = 0;
		String strLogUser = "";
		try {
			
			strLogUser = objSpecificationVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			
			callableStatement = objConnnection
			.prepareCall(EMDQueries.SP_UPDATE_ORDER);
			
			callableStatement.setString(1, objSpecificationVO
					.getDataLocationType());
			callableStatement.setInt(2, objSpecificationVO.getOrderKey());
			callableStatement.setString(3, objSpecificationVO.getHpDesc());
			callableStatement.setString(4, objSpecificationVO.getGenArgNotes());
			
			//Modified for LSDB_CR-74 by ka57588 --starts here
			if(objSpecificationVO.getQuantity() != 0){
				callableStatement.setInt(5, objSpecificationVO.getQuantity());
			}else{
				callableStatement.setNull(5, Types.NULL);
			}
			//Ends here
			callableStatement.setString(6, objSpecificationVO.getUserID());
			callableStatement.setNull(7, Types.NULL);
			/*******************************************************************
			 * Added AppendixTurn ON/OFF flag based on the requirement of
			 * LSDB_CR-42 Added on 08-05-08 by ps57222
			 */
			callableStatement.setNull(8, Types.NULL);
			
			/*******************************************************************
			 * Added PDF Header Image ON/OFF flag based on the requirement of
			 * LSDB_CR-79 Added on 29-10-09 by rr68151
			 */	
			callableStatement.setNull(9, Types.NULL);
			callableStatement.setNull(10, Types.NULL);
			callableStatement.setNull(11, Types.NULL);
			callableStatement.setNull(12, Types.NULL);
			callableStatement.setNull(13, Types.NULL);
			callableStatement.setNull(14, Types.NULL);
			callableStatement.setNull(15, Types.NULL);
			callableStatement.registerOutParameter(16, Types.VARCHAR);
			callableStatement.registerOutParameter(17, Types.INTEGER);
			callableStatement.registerOutParameter(18, Types.VARCHAR);
			
			intStatus = callableStatement.executeUpdate();
			
			LogUtil
			.logMessage("Inside the updateSpecificationItem method of OrderSpecificationDAO :intStatus .."
					+ intStatus);
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			intLSDBErrorID = (int) callableStatement.getInt(16);
			strOracleCode = (String) callableStatement.getString(17);
			strErrorMessage = (String) callableStatement.getString(18);
			
			if (intLSDBErrorID != 0) {
				LogUtil.logMessage("Error ID:" + intLSDBErrorID);
				
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
				StringBuffer sb = new StringBuffer();
				sb.append(strOracleCode + " ");
				sb.append(strErrorMessage);
				objErrorInfo.setMessage(sb.toString());
				throw new ApplicationException(objErrorInfo);
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into DataAccess Exception block in DAO:.."
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil.logMessage("Enters into AppException block in  DAO :"
					+ objErrorInfo.getMessage());
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:"
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
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return intStatus;
		
	}
	
	/*******************************************************************************************
	 * @author  ka57588
	 * Added for LSDB_CR-64 
	 * This method is used for updating specification description
	 * @version 1.0  
	 * @param      objSpecificationVO    the object for specification   
	 * @return     boolean  the flag for success or failure   
	 * @throws  EMDException 
	 ******************************************************************************************/
	
	public static int updateSpecification(SpecificationVO objSpecificationVO)
	throws EMDException {
		LogUtil
		.logMessage("Entering OrderSpecificationDAO.updateSpecification");
		Connection objConnnection = null;
		CallableStatement callableStatement = null;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intStatus = 0;
		String strLogUser = "";
		try {
			strLogUser = objSpecificationVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			
			callableStatement = objConnnection
			.prepareCall(EMDQueries.SP_ORDR_UPDATE_SPEC);
			
			callableStatement.setString(1, objSpecificationVO
					.getDataLocationType());
			callableStatement.setInt(2, objSpecificationVO.getOrderKey());
			callableStatement.setInt(3, objSpecificationVO.getSpecSeqNo());
			callableStatement.setString(4, objSpecificationVO.getSpecName());
			callableStatement.setString(5, objSpecificationVO.getUserID());
			
			callableStatement.registerOutParameter(6, Types.INTEGER);
			callableStatement.registerOutParameter(7, Types.VARCHAR);
			callableStatement.registerOutParameter(8, Types.VARCHAR);
			intStatus = callableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			intLSDBErrorID = (int) callableStatement.getInt(6);
			strOracleCode = (String) callableStatement.getString(7);
			strErrorMessage = (String) callableStatement.getString(8);
			
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
				throw new ApplicationException(objErrorInfo);
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into DataAccess Exception block in DAO:.."
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil.logMessage("Enters into AppException block in  DAO :"
					+ objErrorInfo.getMessage());
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:"
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
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		return intStatus;
		
	}
	
	/*******************************************************************************************
	 * @author  ka57588
	 * Added for LSDB_CR-64 
	 * This method is used for deleting description
	 * @version 1.0  
	 * @param      objSpecificationVO    the object for specification   
	 * @return     boolean  the flag for success or failure   
	 * @throws  EMDException 
	 ******************************************************************************************/
	public static int deleteSpecification(SpecificationVO objSpecificationVO)
	throws EMDException {
		LogUtil
		.logMessage("Entering OrderSpecificationDAO.deleteSpecification");
		Connection objConnnection = null;
		CallableStatement callableStatement = null;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intStatus = 0;
		String strLogUser = "";
		try {
			strLogUser = objSpecificationVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			
			callableStatement = objConnnection
			.prepareCall(EMDQueries.SP_ORDR_DELETE_SPEC);
			
			callableStatement.setString(1, objSpecificationVO
					.getDataLocationType());
			callableStatement.setInt(2, objSpecificationVO.getOrderKey());
			callableStatement.setInt(3, objSpecificationVO.getSpecSeqNo());
			callableStatement.setString(4, objSpecificationVO.getUserID());
			
			callableStatement.registerOutParameter(5, Types.INTEGER);
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			callableStatement.registerOutParameter(7, Types.VARCHAR);
			intStatus = callableStatement.executeUpdate();
			
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			intLSDBErrorID = (int) callableStatement.getInt(5);
			strOracleCode = (String) callableStatement.getString(6);
			strErrorMessage = (String) callableStatement.getString(7);
			
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
				throw new ApplicationException(objErrorInfo);
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into DataAccess Exception block in DAO:.."
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil.logMessage("Enters into AppException block in  DAO :"
					+ objErrorInfo.getMessage());
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:"
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
	 * @version 1.0
	 * @param the
	 *            object for deleting specification item
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	public static int deleteSpecificationItem(SpecificationVO objSpecificationVO)
	throws EMDException {
		LogUtil
		.logMessage("Entering OrderSpecificationDAO.deleteSpecificationItem");
		Connection objConnnection = null;
		CallableStatement callableStatement = null;
		// Error out parameters
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		int intStatus = 0;
		String strLogUser = "";
		try {
			strLogUser = objSpecificationVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			
			callableStatement = objConnnection
			.prepareCall(EMDQueries.SP_DELETE_ORDER_SPEC_ITEM);
			
			callableStatement.setString(1, objSpecificationVO
					.getDataLocationType());
			callableStatement.setInt(2, objSpecificationVO.getOrderKey());
			callableStatement.setInt(3, objSpecificationVO
					.getSpecificationItem().getItemSeqNo());
			callableStatement.setString(4, objSpecificationVO.getUserID());
			
			callableStatement.registerOutParameter(5, Types.INTEGER);
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			callableStatement.registerOutParameter(7, Types.VARCHAR);
			intStatus = callableStatement.executeUpdate();
			
			LogUtil
			.logMessage("Inside the DeleteSpecificationItem method of OrderSpecificationDAO :intStatus .."
					+ intStatus);
			if (intStatus > 0) {
				intStatus = 0;
			}
			
			intLSDBErrorID = (int) callableStatement.getInt(5);
			strOracleCode = (String) callableStatement.getString(6);
			strErrorMessage = (String) callableStatement.getString(7);
			
			if (intLSDBErrorID != 0) {
				
				LogUtil.logMessage("Error ID:" + intLSDBErrorID);
				
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
				StringBuffer sb = new StringBuffer();
				sb.append(strOracleCode + " ");
				sb.append(strErrorMessage);
				objErrorInfo.setMessage(sb.toString());
				throw new ApplicationException(objErrorInfo);
			}
			
		} catch (DataAccessException objDataExp) {
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
			.logMessage("Enters into DataAccess Exception block in DAO:.."
					+ objDataExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil.logMessage("Enters into AppException block in  DAO :"
					+ objErrorInfo.getMessage());
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into Exception block in DAO:"
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