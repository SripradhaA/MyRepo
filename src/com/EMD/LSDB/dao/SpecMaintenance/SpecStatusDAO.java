/*
 * Created on Apr 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.dao.SpecMaintenance;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSet;

import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.common.DBHelper;
import com.EMD.LSDB.dao.common.EMDDAO;
import com.EMD.LSDB.dao.common.EMDQueries;
import com.EMD.LSDB.vo.common.OrderVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for the Spec Type
 ******************************************************************************/

public class SpecStatusDAO extends EMDDAO {
	
	private SpecStatusDAO() {
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSpecTypeVo
	 *            the object containg null
	 * @return ArrayList contains the list of spec types
	 * @throws EMDException
	 **************************************************************************/
	
	public static ArrayList fetchSpecNextStatus(OrderVO objOrderVO)
	throws EMDException {
		LogUtil
		.logMessage("Inside the fetchSpecNextStatus method of SpecStatusDAO");
		Connection objConnnection = null;
		List objAlSpecStatus = new ArrayList();
		ArrayList objSpecStatus = new ArrayList();
		RowSet objRsSpecStatus = null;
		String strLogUser = "";
		
		try {
			strLogUser = objOrderVO.getUserID();
			objConnnection = DBHelper.prepareConnection();
			LogUtil.logMessage("objConnnection in fetchSpecNextStatus :"
					+ objConnnection);
			
			LogUtil.logMessage("Spec Status Code :"
					+ objOrderVO.getSpecStatusCode());
			// Same for three input parameter
			objAlSpecStatus.add(new Integer(objOrderVO.getSpecStatusCode()));
			objAlSpecStatus.add(new Integer(objOrderVO.getSpecStatusCode()));
			objAlSpecStatus.add(new Integer(objOrderVO.getSpecStatusCode()));
			objRsSpecStatus = DBHelper.executeQuery(objConnnection,
					EMDQueries.spec_status, objAlSpecStatus);
			
			LogUtil.logMessage(" fetchSpecNextStatus :" + objRsSpecStatus);
			
			while (objRsSpecStatus.next()) {
				LogUtil
				.logMessage("spec status code :"
						+ objRsSpecStatus
						.getInt(DatabaseConstants.LS080_SPEC_STATUS_CODE));
				LogUtil.logMessage("spec status Name :"
						+ objRsSpecStatus.getString(DatabaseConstants.STATUS));
				
				objOrderVO = new OrderVO();
				
				objOrderVO.setSpecStatusCode(objRsSpecStatus
						.getInt(DatabaseConstants.LS080_SPEC_STATUS_CODE));
				objOrderVO.setStatusDesc(objRsSpecStatus
						.getString(DatabaseConstants.STATUS));
				
				objSpecStatus.add(objOrderVO);
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
				DBHelper.closeSQLObjects(objRsSpecStatus, null, objConnnection);
				
			} catch (Exception objExp) {
				LogUtil.logMessage("Enters into Exception exception...");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER
						+ strLogUser + "-" + objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		LogUtil.logMessage("objSpecStatus in fetchSpecNextStatus :"
				+ objSpecStatus);
		return objSpecStatus;
		
	}
	
}