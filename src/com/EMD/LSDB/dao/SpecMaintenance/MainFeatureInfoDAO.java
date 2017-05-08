/**
 * 
 */
package com.EMD.LSDB.dao.SpecMaintenance;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.tomcat.dbcp.dbcp2.DelegatingConnection;//Added for CR-123 & Tomcat

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
import com.EMD.LSDB.dao.common.DBHelper;
import com.EMD.LSDB.dao.common.EMDDAO;
import com.EMD.LSDB.dao.common.EMDQueries;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.MainFeatureInfoVO;

/**
 * @author ps57222
 * 
 */
public class MainFeatureInfoDAO extends EMDDAO {

	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objMainFeatureInfoVO
	 *            The object for fetch MainFeatures
	 * @return Arraylist It has Arraylist of objMainFeatureInfoVO
	 * @throws EMDException
	 **************************************************************************/

	public static ArrayList fetchMainFeatures(
			MainFeatureInfoVO objMainFeatureInfoVO) throws EMDException {
		LogUtil.logMessage("Enters into MainFeatureInfoDAO:fetchMainFeatures");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ArrayList arlMainfeaList = new ArrayList();
		ResultSet objResultSet = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		/* Added for CR-74 01-06-09 */
		ResultSet rsRevMarkers = null;
		try {
			strLogUser = objMainFeatureInfoVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			LogUtil
					.logMessage("Connection in MainFeatureInfoDAO:fetchMainFeatures:"
							+ objConnection);
			LogUtil
					.logMessage("OrderKey in MainFeatureInfoDAO:fetchMainFeatures:"
							+ objMainFeatureInfoVO.getOrderKey());
			LogUtil
					.logMessage("DataLocationType in MainFeatureInfoDAO:fetchMainFeatures:"
							+ objMainFeatureInfoVO.getDataLocationType());
			objCallableStatement = objConnection
					.prepareCall(EMDQueries.SP_SELECT_COMPONENTINFO);
			objCallableStatement.setInt(1, objMainFeatureInfoVO.getOrderKey());
			objCallableStatement.setString(2, objMainFeatureInfoVO
					.getDataLocationType());
			objCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			/* Added for CR-74 01-06-09 - Starts Here */
			objCallableStatement.setInt(4, objMainFeatureInfoVO.getRevCode());

			/* Added for CR-74 01-06-09 - Ends Here */
			objCallableStatement.setString(5, objMainFeatureInfoVO.getUserID());
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.execute();

			objResultSet = (ResultSet) objCallableStatement.getObject(3);
			LogUtil
					.logMessage("ResultSet Value in MainFeatureInfoDAO:fetchMainFeatures:"
							+ objResultSet);
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

			while (objResultSet.next()) {
				objMainFeatureInfoVO = new MainFeatureInfoVO();
				// Added for CR-74 01-06-09 VV49326
				ArrayList arlRevMarkers = new ArrayList();
				objMainFeatureInfoVO.setCompinfoSeqNo(objResultSet
						.getInt(DatabaseConstants.LS403_COMP_INFO_SEQ_NO));
				objMainFeatureInfoVO.setComponentDesc(objResultSet
						.getString(DatabaseConstants.LS403_COMP_INFO_DESC));
				LogUtil
						.logMessage("CompInfoSeqNo in MainFeatureInfoDAO:fetchMainFeatures:"
								+ objMainFeatureInfoVO.getCompinfoSeqNo());
				LogUtil
						.logMessage("objResultSet.getString(DatabaseConstants.LS403_COMP_INFO_DESC)"
								+ objResultSet
										.getString(DatabaseConstants.LS403_COMP_INFO_DESC));
				/* Added for CR-74 01-06-09 - Starts Here */
				rsRevMarkers = (ResultSet) objResultSet
						.getObject(DatabaseConstants.REVISION_MARKER);

				while (rsRevMarkers.next()) {

					arlRevMarkers.add(rsRevMarkers
							.getString(DatabaseConstants.REVISION_NUM));

				}
				objMainFeatureInfoVO.setRevMarkers(arlRevMarkers);
				DBHelper.closeSQLObjects(rsRevMarkers, null, null);
				/* Added for CR-74 01-06-09 - Ends Here */

				/* Added for CR-76 06-08-09 - Starts Here */
				objMainFeatureInfoVO.setSysMarkFlag(objResultSet
						.getString(DatabaseConstants.LS403_SYS_MARKER));
				objMainFeatureInfoVO.setSysMarkDesc(objResultSet
						.getString(DatabaseConstants.SYS_MARKER_DESC));
				/* Added for CR-76 06-08-09 - Ends Here */

				LogUtil.logMessage("Rev Markers Size" + arlRevMarkers.size());
				arlMainfeaList.add(objMainFeatureInfoVO);

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

		} catch (Exception objExp) {
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
						+ arlMainfeaList);
		return arlMainfeaList;

	}

	/***************************************************************************
	 * Added for LSDB_CR-62
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objMainFeatureInfoVO
	 *            The object for fetch Model level components
	 * @return Arraylist It has Arraylist of objMainFeatureInfoVO
	 * @throws EMDException
	 **************************************************************************/

	public static ArrayList fetchModelMainFeatures(
			MainFeatureInfoVO objMainFeatureInfoVO) throws EMDException {
		LogUtil
				.logMessage("Enters into MainFeatureInfoDAO:fetchModelMainFeatures");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ArrayList arlMainfeaList = new ArrayList();
		ResultSet objResultSet = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		String strLogUser = "";
		/* Added for CR-74 01-06-09 */
		//ResultSet rsRevMarkers = null;
		try {
			strLogUser = objMainFeatureInfoVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			LogUtil
					.logMessage("Connection in MainFeatureInfoDAO:fetchModelMainFeatures:"
							+ objConnection);
			LogUtil
					.logMessage("OrderKey in MainFeatureInfoDAO:fetchModelMainFeatures:"
							+ objMainFeatureInfoVO.getOrderKey());
			LogUtil
					.logMessage("DataLocationType in MainFeatureInfoDAO:fetchModelMainFeatures:"
							+ objMainFeatureInfoVO.getDataLocationType());
			LogUtil
					.logMessage("DisplayInSpec in MainFeatureInfoDAO:fetchModelMainFeatures:"
							+ objMainFeatureInfoVO.getDisplayInSpec());

			objCallableStatement = objConnection
					.prepareCall(EMDQueries.SP_SELECT_MDL_COMP_INFO);
			objCallableStatement.setInt(1, objMainFeatureInfoVO.getOrderKey());
			objCallableStatement.setString(2, objMainFeatureInfoVO
					.getDataLocationType());
			if (objMainFeatureInfoVO.getDisplayInSpec()) {
				objCallableStatement.setString(3, "Y");
			} else {
				objCallableStatement.setNull(3, Types.NULL);
			}

			objCallableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			/** Added for CR-74 VV49326 01-06-09 - Starts Here* */
			objCallableStatement.setInt(5, objMainFeatureInfoVO.getRevCode());
			/** Added for CR-74 VV49326 01-06-09 - Starts Here* */
			objCallableStatement.setString(6, objMainFeatureInfoVO.getUserID());
			objCallableStatement.registerOutParameter(7, Types.INTEGER);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			objCallableStatement.registerOutParameter(9, Types.VARCHAR);
			objCallableStatement.execute();

			objResultSet = (ResultSet) objCallableStatement.getObject(4);
			LogUtil
					.logMessage("ResultSet Value in MainFeatureInfoDAO:fetchModelMainFeatures:"
							+ objResultSet);
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

			while (objResultSet.next()) {
				objMainFeatureInfoVO = new MainFeatureInfoVO();
				// Added for CR-74 01-06-09 VV49326
				//ArrayList arlRevMarkers = new ArrayList();
				objMainFeatureInfoVO.setCompSeqNo(objResultSet
						.getInt(DatabaseConstants.LS140_COMP_SEQ_NO));
				objMainFeatureInfoVO.setCompName(objResultSet
						.getString(DatabaseConstants.LS140_COMP_NAME));
				objMainFeatureInfoVO.setCompDesc(objResultSet
						.getString(DatabaseConstants.LS140_COMP_DESC));

				if (objResultSet
						.getString(DatabaseConstants.LS405_DISP_IN_SPEC)
						.equalsIgnoreCase("Y")) {
					objMainFeatureInfoVO.setDisplayInSpec(true);
				} else {
					objMainFeatureInfoVO.setDisplayInSpec(false);
				}

				/* Added for CR-74 01-06-09 - Starts Here */
//				rsRevMarkers = (ResultSet) objResultSet
//						.getObject(DatabaseConstants.REVISION_MARKER);
//				while (rsRevMarkers.next()) {
//
//					arlRevMarkers.add(rsRevMarkers
//							.getString(DatabaseConstants.REVISION_NUM));
//
//				}
//				objMainFeatureInfoVO.setRevMarkers(arlRevMarkers);
//				DBHelper.closeSQLObjects(rsRevMarkers, null, null);
				/* Added for CR-74 01-06-09 - Ends Here */

				/* Added for CR-76 VV49326 06-08-09 - Starts Here 
				objMainFeatureInfoVO.setSysMarkFlag(objResultSet
						.getString(DatabaseConstants.LS405_SYS_MARKER));
				objMainFeatureInfoVO.setSysMarkDesc(objResultSet
						.getString(DatabaseConstants.SYS_MARKER_DESC));
				   Added for CR-76 VV49326 06-08-09 - Ends Here */

				arlMainfeaList.add(objMainFeatureInfoVO);

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

		} catch (Exception objExp) {
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
				.logMessage("Arraylist Value in MainFeatureInfoDAO:fetchModelMainFeatures:"
						+ arlMainfeaList);
		return arlMainfeaList;

	}

	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objMainFeatureInfoVO
	 *            The object for Insert MainFeatures
	 * @return Arraylist It Returns a boolean value
	 * @throws EMDException
	 **************************************************************************/

	public static int insertMainFeatures(MainFeatureInfoVO objMainFeatureInfoVO)
			throws EMDException {
		LogUtil.logMessage("Enters into MainFeatureInfoDAO:insertMainFeatures");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ResultSet objResultSet = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		int intStatusCode;
		String strLogUser = "";
		int intCpyFrmCompSeqNo=0;
		try {

			strLogUser = objMainFeatureInfoVO.getUserID();
			objConnection = DBHelper.prepareConnection();

			LogUtil
					.logMessage("Connection in MainFeatureInfoDAO:fetchMainFeatures:"
							+ objConnection);
			objCallableStatement = objConnection
					.prepareCall(EMDQueries.SP_INSERT_COMPONENTINFO);
			LogUtil
					.logMessage("DataLocationType in MainFeatureInfoDAO:fetchMainFeatures:"
							+ objMainFeatureInfoVO.getDataLocationType());
			objCallableStatement.setString(1, objMainFeatureInfoVO
					.getDataLocationType());
			LogUtil
					.logMessage("CompDesc in MainFeatureInfoDAO:fetchMainFeatures:"
							+ objMainFeatureInfoVO.getCompDesc());
			if(objMainFeatureInfoVO.getCompDesc()==null)
			{
				objCallableStatement.setNull(2,Types.NULL);
			}
			else
			{	
			objCallableStatement.setString(2, objMainFeatureInfoVO.getCompDesc());
			}
			LogUtil.logMessage("OrderKey in MainFeatureInfoDAO:fetchMainFeatures:"
							+ objMainFeatureInfoVO.getOrderKey());
			objCallableStatement.setInt(3, objMainFeatureInfoVO.getOrderKey());
			LogUtil.logMessage("UserID in MainFeatureInfoDAO:fetchMainFeatures:"
							+ objMainFeatureInfoVO.getUserID());
			LogUtil.logMessage("compSeqNo in MainFeatureInfoDAO:fetchMainFeatures:"
					+ objMainFeatureInfoVO.getCpyFrmCompSeqNo());
			intCpyFrmCompSeqNo=objMainFeatureInfoVO.getCpyFrmCompSeqNo();
			if(intCpyFrmCompSeqNo!=0)
			{
				objCallableStatement.setInt(4,objMainFeatureInfoVO.getCpyFrmCompSeqNo());
			}
			else
			{
				objCallableStatement.setNull(4,Types.NULL);
			}
			LogUtil.logMessage("compSeqNo in MainFeatureInfoDAO:fetchMainFeatures:"
					+ intCpyFrmCompSeqNo);
			objCallableStatement.setString(5, objMainFeatureInfoVO.getUserID());
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			intStatusCode = objCallableStatement.executeUpdate();

			if (intStatusCode > 0) {
				intStatusCode = 0;
			}

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

		return intStatusCode;

	}

	/***************************************************************************
	 * Added for LSDB_CR-62
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objMainFeatureInfoVO
	 * The object for update Display in Spec
	 * @return Arraylist It Returns a boolean value
	 * @throws EMDException
	 **************************************************************************/

	public static int updateDisplayInSpec(MainFeatureInfoVO objMainFeatureInfoVO)
			throws EMDException {

		LogUtil
				.logMessage("Enters into MainFeatureInfoDAO:updateDisplayInSpec");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ResultSet objResultSet = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		int intStatusCode;
		String strLogUser = "";
		try {
			strLogUser = objMainFeatureInfoVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			LogUtil.logMessage("Order Key:"
					+ objMainFeatureInfoVO.getOrderKey());
			LogUtil
					.logMessage("CompSeqNo in MainFeatureInfoDAO:updateDisplayInSpec:"
							+ objMainFeatureInfoVO.getCompSeqNo());
			LogUtil
					.logMessage("DisplayInSpec in MainFeatureInfoDAO:updateDisplayInSpec:"
							+ objMainFeatureInfoVO.getDisplayInSpec());

			objCallableStatement = objConnection
					.prepareCall(EMDQueries.SP_UPDATE_COMP_INFO_DISP);

			objCallableStatement.setInt(1, objMainFeatureInfoVO.getOrderKey());

			objCallableStatement.setInt(2, objMainFeatureInfoVO.getCompSeqNo());

			objCallableStatement.setString(3, objMainFeatureInfoVO
					.getDataLocationType());

			if (objMainFeatureInfoVO.getDisplayInSpec()) {
				objCallableStatement.setString(4, "Y");
			} else {
				objCallableStatement.setString(4, "N");
			}

			objCallableStatement.setString(5, objMainFeatureInfoVO.getUserID());
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			intStatusCode = objCallableStatement.executeUpdate();

			if (intStatusCode > 0) {
				intStatusCode = 0;
			}

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

		return intStatusCode;

	}

	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objMainFeatureInfoVO
	 *            The object for update MainFeatures
	 * @return Arraylist It Returns a boolean value
	 * @throws EMDException
	 **************************************************************************/

	public static int updateMainFeatures(MainFeatureInfoVO objMainFeatureInfoVO)
			throws EMDException {

		LogUtil.logMessage("Enters into MainFeatureInfoDAO:updateMainFeatures");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ResultSet objResultSet = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		int intStatusCode;
		String strLogUser = "";
		try {
			strLogUser = objMainFeatureInfoVO.getUserID();
			objConnection = DBHelper.prepareConnection();
			LogUtil
					.logMessage("Connection in MainFeatureInfoDAO:fetchMainFeatures:"
							+ objConnection);
			LogUtil
					.logMessage("CompinfoSeqNo in MainFeatureInfoDAO:fetchMainFeatures:"
							+ objMainFeatureInfoVO.getCompinfoSeqNo());
			LogUtil
					.logMessage("CompDesc in MainFeatureInfoDAO:fetchMainFeatures:"
							+ objMainFeatureInfoVO.getHdCompDesc());
			objCallableStatement = objConnection
					.prepareCall(EMDQueries.SP_UPDATE_COMPONENTINFO);
			LogUtil
					.logMessage("CompinfoSeqNo in MainFeatureInfoDAO:fetchMainFeatures:"
							+ objMainFeatureInfoVO.getCompinfoSeqNo());
			objCallableStatement.setInt(1, objMainFeatureInfoVO
					.getCompinfoSeqNo());
			LogUtil
					.logMessage("CompDesc in MainFeatureInfoDAO:fetchMainFeatures:"
							+ objMainFeatureInfoVO.getHdCompDesc());
			objCallableStatement.setString(2, objMainFeatureInfoVO
					.getHdCompDesc());
			// Added for CR-74 VV49326 01-06-09- Starts Here
			objCallableStatement.setInt(3, objMainFeatureInfoVO.getOrderKey());
			objCallableStatement.setString(4, objMainFeatureInfoVO
					.getDataLocationType());
			// Added for CR-74 VV49326 01-06-09- Ends Here
			objCallableStatement.setString(5, objMainFeatureInfoVO.getUserID());
			objCallableStatement.registerOutParameter(6, Types.INTEGER);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			objCallableStatement.registerOutParameter(8, Types.VARCHAR);
			intStatusCode = objCallableStatement.executeUpdate();

			if (intStatusCode > 0) {
				intStatusCode = 0;
			}

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

		return intStatusCode;

	}

	// Added for CR display Default Components in the pop-up

	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objMainFeatureInfoVO
	 *            The object Order default Components for MainFeatures added for
	 *            CR
	 * @return Arraylist It has Arraylist of objMainFeatureInfoVO
	 * @throws EMDException
	 **************************************************************************/

	public static ArrayList fetchOrderDispComps(
			MainFeatureInfoVO objMainFeatureInfoVO) throws EMDException {
		LogUtil.logMessage("Enters into MainFeatureInfoDAO:fetchOrderDefComps");
		Connection objConnection = null;
		ArrayList objArrayList = new ArrayList();
		ArrayList arlMainfeaList = new ArrayList();
		ResultSet rsDisplayInSpec = null;

		String strLogUser = "";
		String strOrderKey = "";
		try {
			strLogUser = objMainFeatureInfoVO.getUserID();
			strOrderKey = String.valueOf(objMainFeatureInfoVO.getOrderKey());

			objConnection = DBHelper.prepareConnection();

			LogUtil
					.logMessage("OrderKey in MainFeatureInfoDAO:fetchOrderDefComps"
							+ objMainFeatureInfoVO.getOrderKey());
			LogUtil
					.logMessage("DataLocationType in MainFeatureInfoDAO:fetchOrderDefComps"
							+ objMainFeatureInfoVO.getDataLocationType());

			objArrayList.add(strOrderKey);

			rsDisplayInSpec = DBHelper.executeQuery(objConnection,
					EMDQueries.Query_OrderDefaultComps, objArrayList);

			while (rsDisplayInSpec.next()) {
				MainFeatureInfoVO objjMainFeatureInfoVO = new MainFeatureInfoVO();

				objjMainFeatureInfoVO.setCompDesc(rsDisplayInSpec
						.getString(DatabaseConstants.COMP_DESC));
				arlMainfeaList.add(objjMainFeatureInfoVO);

			}
		} catch (DataAccessException objDataExp) {
			LogUtil
					.logMessage("Enters into DataAccessException MainFeatureInfoDAO:fetchOrderDefComps");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			objErrorInfo.setMessage(objDataExp.getMessage());
			LogUtil
					.logMessage("ENters into DataAccessException block in GenArrByModelDAO:modifyGenArrNotes"
							+ objErrorInfo.getMessage());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
					.logMessage("Enters into Exception block in MainFeatureInfoDAO:fetchOrderDefComps");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			LogUtil
					.logMessage("ENters into Exception block in MainFeatureInfoDAO:fetchOrderDefComps"
							+ objErrorInfo.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}

		finally {
			try {

				DBHelper.closeConnection(objConnection);
			}

			catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}

		}

		return arlMainfeaList;

	}

	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objMainFeatureInfoVO
	 *            The object for Insert MainFeatures
	 * @return Arraylist It Returns a boolean value
	 * @throws EMDException
	 **************************************************************************/

	public static int deleteMainFeatures(MainFeatureInfoVO objMainFeatureInfoVO)
			throws EMDException {
		LogUtil.logMessage("Enters into MainFeatureInfoDAO:deleteMainFeatures");
		Connection objConnection = null;
		CallableStatement objCallableStatement = null;
		ResultSet objResultSet = null;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strMessage = null;
		int intLSDBErrorID = 0;
		int intStatusCode;
		String strLogUser = "";
		ArrayList arlMainFeature = new ArrayList();
		try {

			strLogUser = objMainFeatureInfoVO.getUserID();
			objConnection = DBHelper.prepareConnection();

			LogUtil
					.logMessage("Connection in MainFeatureInfoDAO:deleteMainFeatures:"
							+ objConnection);

			objCallableStatement = objConnection
					.prepareCall(EMDQueries.SP_DELETE_COMPONENTINFO);

			objCallableStatement.setInt(1, objMainFeatureInfoVO.getOrderKey());
			objCallableStatement.setString(2, objMainFeatureInfoVO
					.getDataLocationType());
			objCallableStatement.setInt(3, objMainFeatureInfoVO
					.getCompinfoSeqNo());
			objCallableStatement.setString(4, objMainFeatureInfoVO.getUserID());
			objCallableStatement.registerOutParameter(5, Types.INTEGER);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			objCallableStatement.registerOutParameter(7, Types.VARCHAR);
			intStatusCode = objCallableStatement.executeUpdate();

			if (intStatusCode > 0) {
				intStatusCode = 0;
			}

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
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
					.logMessage("ENters into catch block in MainFeatureInfoDAO:deleteMainFeatures"
							+ objDataExp.getMessage());
			LogUtil
					.logMessage("ENters into catch block in MainFeatureInfoDAO:deleteMainFeatures."
							+ objErrorInfo);
			LogUtil
					.logMessage("ENters into catch block in MainFeatureInfoDAO:deleteMainFeatures."
							+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
					.logMessage("ENters into catch block in MainFeatureInfoDAO:deleteMainFeatures."
							+ objErrorInfo.getMessage());
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);

		}

		catch (Exception objExp) {
			LogUtil
					.logMessage("Enters into Exception Block MainFeatureInfoDAO:deleteMainFeatures.");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}

		finally {
			try {

				DBHelper.closeConnection(objConnection);
			}

			catch (Exception objExp) {
				LogUtil
						.logMessage("Enters into Close Connection Exception MainFeatureInfoDAO:deleteMainFeatures...");
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
	 * @param objClauseVO
	 *            the object for updateRearrangeClauses
	 * @return boolean that returns True or False
	 * @throws EMDException
	 **************************************************************************/
	public static synchronized int rearrangeCompDesc(MainFeatureInfoVO objMainFeatureInfoVO)
	throws EMDException {
		LogUtil.logMessage("Enters into MainFeatureInfoDAO:rearrangeCompDesc");
		Connection objConnnection = null;
		CallableStatement objCallableStatement = null;
		int intStatus = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		int intLSDBErrorID = 0;
		ArrayDescriptor arrClaSeqNos = null;
		String strLogUser = "";
		
		try {
			strLogUser = objMainFeatureInfoVO.getUserID();
			
			objConnnection = DBHelper.prepareConnection();
			objCallableStatement = objConnnection.prepareCall(EMDQueries.SP_REARRANGE_ORDR_COMP_INFO);
			Connection dconn = ((DelegatingConnection) objConnnection).getInnermostDelegate(); //Added for CR-123 & Tomcat
			objCallableStatement.setInt(1, objMainFeatureInfoVO.getOrderKey());
			
			arrClaSeqNos = new ArrayDescriptor(DatabaseConstants.IN_ARRAY,dconn);
			LogUtil.logMessage("ArrayList ClaSeqNoList Size :"+objMainFeatureInfoVO.getCompSeqNoList().length);
			
			ARRAY arrClaSeqNo = new ARRAY(arrClaSeqNos, dconn, objMainFeatureInfoVO.getCompSeqNoList());
			
			objCallableStatement.setArray(2, arrClaSeqNo);
			objCallableStatement.setString(3, objMainFeatureInfoVO.getDataLocationType());
			objCallableStatement.setString(4,objMainFeatureInfoVO.getUserID());
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
			.logMessage("Enters into DataAccess Exception block in MainFeatureInfoDAO:updateRearrangeClauses"
					+ objDatExp.getErrorInfo().getMessageID());
			throw new BusinessException(objDatExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into AppException block in  MainFeatureInfoDAO:updateRearrangeClauses:"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in MainFeatureInfoDAO:updateRearrangeClauses:"
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
				.logMessage("Enter into Exception block in MainFeatureInfoDAO:updateRearrangeClauses:"
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
	 * Added for LSDB_CR_104
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objMainFeatureInfoVO
	 * The object for update Display in Spec
	 * @return Arraylist It Returns a boolean value
	 * @throws EMDException
	 **************************************************************************/

	public static MainFeatureInfoVO publishAndNotification(MainFeatureInfoVO objMainFeatureInfoVO)
			throws EMDException {

		LogUtil
				.logMessage("Enters into MainFeatureInfoDAO:publishAndNotification");
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
			strLogUser = objMainFeatureInfoVO.getUserID();
			objConnection = DBHelper.prepareConnection();
					objCallableStatement = objConnection
					.prepareCall(EMDQueries.SP_EMAIL_TO_1058_ADMN);
			objCallableStatement.setInt(1, objMainFeatureInfoVO.getOrderKey());
			objCallableStatement.setString(2, objMainFeatureInfoVO.getSubject());
			objCallableStatement.setString(3, objMainFeatureInfoVO.getBodyCont());
			objCallableStatement.setString(4, objMainFeatureInfoVO.getRevFlag());
			objCallableStatement.setString(5, objMainFeatureInfoVO.getFinalFlag());
			objCallableStatement.setInt(6, objMainFeatureInfoVO.getRevCode());
			objCallableStatement.registerOutParameter(7, Types.INTEGER);
			objCallableStatement.setString(8, objMainFeatureInfoVO.getUserID());
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
			objMainFeatureInfoVO.setOrderKey(intOrderKey);
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

		return objMainFeatureInfoVO;

	}
	
}