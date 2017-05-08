package com.EMD.LSDB.bo.common;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.LoginBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.common.LoginDAO;
import com.EMD.LSDB.vo.common.EMDVO;
import com.EMD.LSDB.vo.common.LoginVO;

public class LoginBO extends EMDBO implements LoginBI {
	public static LoginBO objLoginBO = null;
	
	public synchronized static LoginBO getInstance() {
		LogUtil.logMessage("Inside the getInstance method of LoginBO");
		if (objLoginBO == null) {
			objLoginBO = new LoginBO();
		}
		return objLoginBO;
	}
	
	private LoginBO() {
	}
	
	public boolean findUser(LoginVO objLoginVo) throws EMDException, Exception {
		try {
			LogUtil.logMessage("Inside the findUser method of LoginBO");
			if (LoginDAO.findUser(objLoginVo) != null) {
				return true;
			}
			
			return false;
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in LoginBo:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil.logMessage("enters into catch block in LoginBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		
	}
	
//	Added for CR-112 to fetch Message from DB
	
	/*****************************************************************************************
	 * This Method is used to fetch the Message from DB
	 * @param objEMDVO
	 * @return ArrayList
	 * @throws EMDException
	 ****************************************************************************************/
	public ArrayList fetchMessage(LoginVO objLoginVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Entering LoginBO.fetchMessage");
		
		ArrayList arlMessage = null;
		try {
			arlMessage = LoginDAO.fetchMessage(objLoginVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in LoginBO:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in LoginBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in LoginBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlMessage;
	}
	
//	Added for CR-112 to Add Message Of the Day in home screen
	/*******************************************************************************************
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	objUSErtVO    	     The object for insert Message
	 * @return     	int			         The Status for success or failure   
	 * @throws     	EMDException
	 ******************************************************************************************/
	public int insertMessage(EMDVO objEMDVO) throws EMDException, Exception {
		
		int intReturnStatus = 0;
		try {
			LogUtil.logMessage("Entering UserMaintBO.insertMessage");
			intReturnStatus = LoginDAO.insertMessage(objEMDVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in UserMaintBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in UserMaintBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in UserMaintBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	//CR_112 Ends here
}
