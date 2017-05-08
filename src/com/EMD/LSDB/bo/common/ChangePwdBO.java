/* AK49339
 * Created on Aug 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.bo.common;

import com.EMD.LSDB.bo.serviceinterface.ChangePwdBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.common.ChangePwdDAO;
import com.EMD.LSDB.vo.common.PasswordVO;

/*******************************************************************************************
 * @author  Satyam Computer Services Ltd  
 * @version 1.0  
 * This class has the business methods for the Change Password
 ******************************************************************************************/

public class ChangePwdBO implements ChangePwdBI {
	public static ChangePwdBO objChangePwdBO = null;
	
	public synchronized static ChangePwdBO getInstance() {
		if (objChangePwdBO == null) {
			objChangePwdBO = new ChangePwdBO();
		}
		return objChangePwdBO;
	}
	
	private ChangePwdBO() {
		
	}
	
	/*******************************************************************************************
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	objCustMaintVO    The Object for Modify Customer  
	 * @return     	boolean  		The Flag for success or failure   
	 * @throws     	EMDException
	 ******************************************************************************************/
	public int updatePwd(PasswordVO objPasswordVO) throws EMDException,
	Exception {
		int intReturnStatus = 0;
		try {
			LogUtil.logMessage("Inside the updatePwd method of ChangePwdBo");
			intReturnStatus = ChangePwdDAO.updatePwd(objPasswordVO);
		}
		
		catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ChangePwdBo:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ChangePwdBo:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ChangePwdBo:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
}
