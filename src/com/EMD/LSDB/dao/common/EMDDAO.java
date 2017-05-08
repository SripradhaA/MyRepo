package com.EMD.LSDB.dao.common;

import java.sql.Connection;
import java.util.ArrayList;

import javax.sql.RowSet;

import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;

public class EMDDAO {
	
	public static boolean screenAccess(ArrayList objArrayList)
	throws EMDException {
		
		LogUtil.logMessage("Enters into ScreenAccess Method in EMDDAO");
		Connection objConnnection = null;
		boolean isValidUser = false;
		RowSet objResultSet = null;
		
		try {
			objConnnection = DBHelper.prepareConnection();
			
			objResultSet = DBHelper.executeQuery(objConnnection,
					EMDQueries.Query_ScreenAccess, objArrayList);
			while (objResultSet.next()) {
				isValidUser = true;
			}
			
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into Exception block in EMDDAO:screenAccess"
					+ objExp.getMessage());
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + "-"
					+ objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		
		finally {
			
			try {
				if (objResultSet != null) {
					objResultSet.close();
				}
				if (objConnnection != null) {
					
					DBHelper.closeConnection(objConnnection);
				}
				
			} catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into Exception block in EMDDAO:screenAccess"
						+ objExp.getMessage());
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER + "-"
						+ objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
		}
		return isValidUser;
	}
}
