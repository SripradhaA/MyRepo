/**
 * 
 */
package com.EMD.LSDB.dao.History;

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
import com.EMD.LSDB.dao.common.EMDQueries;
import com.EMD.LSDB.vo.common.RevisionChangesVO;

/**
 * @author ER91220
 * 
 */
public class RevisionChangesDAO{
	
	private RevisionChangesDAO() {
		
	}
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objRevisionChangesVO
	 *           the object for searching revisions
	 * @return ArrayList the list contains the Revision changes
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetchRevisions(RevisionChangesVO objRevisionChangesVO) throws EMDException {
		LogUtil.logMessage("Entering RevisionChangesDAO.fetchRevisions");
		Connection objConnnection = null;
		ArrayList arlRevHistoryDetails = new ArrayList();
		ResultSet rsRevHistoryDetails = null;
		ResultSet rsRevHistoryItemDetails = null;
		CallableStatement objCallableStatement = null;
		
			
		
		// Error out parameters
		int intLSDBErrorID = 0;
		String strOracleCode = null;
		String strErrorMessage = null;
		String strLogUser = "";
		try {
			
			strLogUser = objRevisionChangesVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			
			LogUtil.logMessage("objConnnection in DAO :" + objConnnection);
			objCallableStatement = objConnnection.prepareCall(EMDQueries.SP_SELECT_REVISION_HISTORY);
			if (objRevisionChangesVO.getRevisionID() != 0)
				objCallableStatement.setInt(1, objRevisionChangesVO.getRevisionID());
			else
				objCallableStatement.setNull(1, Types.NULL);
			objCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			objCallableStatement.setString(3, objRevisionChangesVO.getUserID());
			objCallableStatement.registerOutParameter(4, Types.INTEGER);
			objCallableStatement.registerOutParameter(5, Types.VARCHAR);
			objCallableStatement.registerOutParameter(6, Types.VARCHAR);
			
			objCallableStatement.execute();
			
			rsRevHistoryDetails = (ResultSet) objCallableStatement.getObject(2);
						
			intLSDBErrorID = (int) objCallableStatement.getInt(4);
			strOracleCode = (String) objCallableStatement.getString(5);
			strErrorMessage = (String) objCallableStatement.getString(6);

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
			
			while (rsRevHistoryDetails.next()) {
				ArrayList arlRevisionItem = new ArrayList();
				RevisionChangesVO objRevisChangesVO = new RevisionChangesVO();
				LogUtil.logMessage("Inside outer ResultSet");
				objRevisChangesVO.setRevisionID(rsRevHistoryDetails.getInt(DatabaseConstants.LS700_REVISION_ID));
				objRevisChangesVO.setRevisionDesc(rsRevHistoryDetails.getString(DatabaseConstants.LS700_REVISION_DESC));
				//Added for CR_101 QA testing comments
				objRevisChangesVO.setRevisionRelDate(rsRevHistoryDetails.getString(DatabaseConstants.LS700_REV_RELEASE_DATE));
				
				LogUtil.logMessage("Format of Date ------>"+objRevisChangesVO.getRevisionRelDate());
				
				rsRevHistoryItemDetails = (ResultSet) rsRevHistoryDetails.getObject(DatabaseConstants.REVISION_ITEM_DESC);
				
						while (rsRevHistoryItemDetails.next()) {
								RevisionChangesVO objRevisionVO = new RevisionChangesVO();
								LogUtil.logMessage("Inside inner ResultSet " + objRevisionVO.getRevisionItemDesc());
								objRevisionVO.setRevisionItemID(rsRevHistoryItemDetails.getInt(DatabaseConstants.LS701_REVISION_ITEM_ID));
								objRevisionVO.setRevisionItemDesc(rsRevHistoryItemDetails.getString(DatabaseConstants.LS701_REVISION_ITEM_DESC));
								
								arlRevisionItem.add(objRevisionVO);
						}
				objRevisChangesVO.setArlRevisionItem(arlRevisionItem);
				DBHelper.closeSQLObjects(rsRevHistoryItemDetails, null, null);
				
				arlRevHistoryDetails.add(objRevisChangesVO);	 
			}
			
		} catch (DataAccessException objDataExp) {
			LogUtil
			.logMessage("Enters into DataAccessException RevisionChangestDAO.fetchRevisions");
			ErrorInfo objErrorInfo = objDataExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in RevisionChangestDAO.fetchRevisions"
					+ objErrorInfo.getMessageID());
			throw new BusinessException(objDataExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			LogUtil
			.logMessage("Enters into ApplicationException RevisionChangestDAO.fetchRevisions");
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objErrorInfo.getMessage());
			LogUtil
			.logMessage("Enters into catch block in RevisionChangestDAO.fetchRevisions"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objAppExp, objErrorInfo);
		}
		catch (Exception objExp) {
			LogUtil.logMessage("Enters into catch block in RevisionChangestDAO.fetchRevisions");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + strLogUser
					+ "-" + objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		finally {
			try {
				
				DBHelper.closeSQLObjects(rsRevHistoryDetails, objCallableStatement,
						objConnnection);
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception RevisionChangestDAO.fetchRevisions");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
		}
		return arlRevHistoryDetails;
	}
}
