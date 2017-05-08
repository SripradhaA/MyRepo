/**
 * 
 */
package com.EMD.LSDB.bo.admn;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.UserMaintBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.admn.UserMaintDAO;
import com.EMD.LSDB.vo.admn.UserVO;

/**
 * @author VV49326
 *
 */
public class UserMaintBO implements UserMaintBI {
	
	public static UserMaintBO objUserMaintBO = null;
	
	public synchronized static UserMaintBO getInstance() {
		
		if (objUserMaintBO == null) {
			objUserMaintBO = new UserMaintBO();
		}
		return objUserMaintBO;
	}
	
	private UserMaintBO() {
	}
	
	/*****************************************************************************************
	 * This Method is used to fetch the User Details
	 * @param objUserVO
	 * @return ArrayList
	 * @throws EMDException
	 ****************************************************************************************/
	public ArrayList fetchUsers(UserVO objUserVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Entering UserMaintBO.fetchUsers");
		System.out.println("Entering UserMaintBO.fetchUsers");
		
		ArrayList arlUsers = null;
		try {
			arlUsers = UserMaintDAO.fetchUsers(objUserVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in UserMaintBO:BusinessException"
					+ objErrorInfo.getMessage());
			System.out.println("Enters into catch block in UserMaintBO:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in UserMaintBO:ApplicationException"
					+ objErrorInfo.getMessage());
			System.out.println("enters into catch block in UserMaintBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in UserMaintBO:Exception"
					+ objExp.getMessage());
			System.out.println("Enters into catch block in UserMaintBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlUsers;
	}
	
	/*******************************************************************************************
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	objUSErtVO    	     The object for Delete User
	 * @return     	int			         The Status for success or failure   
	 * @throws     	EMDException
	 ******************************************************************************************/
	public int deleteUser(UserVO objUserVO) throws EMDException, Exception {
		
		int intReturnStatus = 0;
		try {
			LogUtil.logMessage("Entering UserMaintBO.deleteUser");
			System.out.println("Entering UserMaintBO.deleteUser");
			intReturnStatus = UserMaintDAO.deleteUser(objUserVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in UserMaintBO:BusinessException"
					+ objErrorInfo.getMessage());
			System.out.println("Enters into catch block in UserMaintBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in UserMaintBO:ApplicationException"
					+ objErrorInfo.getMessage());
			System.out.println("enters into catch block in UserMaintBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in UserMaintBO:Exception"
					+ objExp.getMessage());
			System.out.println("Enters into catch block in UserMaintBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/*******************************************************************************************
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	objUSErtVO    	     The object for insert User
	 * @return     	int			         The Status for success or failure   
	 * @throws     	EMDException
	 ******************************************************************************************/
	public int insertUser(UserVO objUserVO) throws EMDException, Exception {
		
		int intReturnStatus = 0;
		try {
			LogUtil.logMessage("Entering UserMaintBO.insertUser");
			intReturnStatus = UserMaintDAO.insertUser(objUserVO);
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
	
	/*******************************************************************************************
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	objUSErtVO    	     The object for update User
	 * @return     	int			         The Status for success or failure   
	 * @throws     	EMDException
	 ******************************************************************************************/
	public int updateUser(UserVO objUserVO) throws EMDException, Exception {
		
		int intReturnStatus = 0;
		try {
			LogUtil.logMessage("Entering UserMaintBO.updateUser");
			intReturnStatus = UserMaintDAO.updateUser(objUserVO);
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
	//Added for CR_112 to fetch the user roles.
	/*****************************************************************************************
	 * This Method is used to fetch the User Roles
	 * @param objUserVO
	 * @return ArrayList
	 * @throws EMDException
	 ****************************************************************************************/
	public ArrayList fetchUserRoles(UserVO objUserVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Entering UserMaintBO.fetchUserRoles");
		
		ArrayList arlUserRoles = null;
		try {
			arlUserRoles = UserMaintDAO.fetchUserRoles(objUserVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in UserMaintBO:BusinessException"
					+ objErrorInfo.getMessage());
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
		return arlUserRoles;
	}
	
	//CR_112 Ends here 

	//Added for CR-113
	/*******************************************************************************************
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	objUSErtVO    	     The object for broadcastEmail 
	 * @return     	int			         The Status for success or failure   
	 * @throws     	EMDException
	 ******************************************************************************************/
	public int broadcastEmail(UserVO objUserVO) throws EMDException, Exception {
		
		int intReturnStatus = 0;
		try {
			LogUtil.logMessage("Entering UserMaintBO.broadcastEmail");
			intReturnStatus = UserMaintDAO.broadcastEmail(objUserVO);
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
	
//	Added for CR-124
	/*******************************************************************************************
	 * @author  	Satyam Computer Services Ltd  
	 * @version 	1.0  
	 * @param      	objUSErtVO    	     The object for purgeEmail 
	 * @return     	int			         The Status for success or failure   
	 * @throws     	EMDException
	 ******************************************************************************************/
	public ArrayList fetchEmailDetails(UserVO objUserVO) throws EMDException, Exception {
		
		ArrayList arlEmailDet = null;
		try {
			LogUtil.logMessage("Entering UserMaintBO.fetchEmailDetails");
			arlEmailDet = UserMaintDAO.fetchEmailDetails(objUserVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in UserMaintBO:BusinessException"
					+ objErrorInfo.getMessage());
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
		return arlEmailDet;
	}
	
	public int purgeEmail(UserVO objUserVO) throws EMDException, Exception {
		
		int intReturnStatus = 0;
		try {
			LogUtil.logMessage("Entering UserMaintBO.PurgeEmail");
			intReturnStatus = UserMaintDAO.purgeEmail(objUserVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in UserMaintBO:BusinessException"
					+ objErrorInfo.getMessage());
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
//	Added for CR-124 ends here
	
//	Added for CR-126	
	public int save(UserVO objUserVO) throws EMDException, Exception {
		
		int intReturnStatus = 0;
		try {
			LogUtil.logMessage("Entering UserMaintBO.save");
			intReturnStatus = UserMaintDAO.save(objUserVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in UserMaintBO:BusinessException"
					+ objErrorInfo.getMessage());
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
	
	public int fetchEmailPeriod(UserVO objUserVO) throws EMDException, Exception {
		
		int intEmailPeriod = 0;
		try {
			LogUtil.logMessage("Entering UserMaintBO.fetchEmailPeriod");
			intEmailPeriod = UserMaintDAO.fetchEmailPeriod(objUserVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in UserMaintBO:BusinessException"
					+ objErrorInfo.getMessage());
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
		return intEmailPeriod;
	}
	
	public int updateEmailPeriod(UserVO objUserVO) throws EMDException, Exception {
		
		int nUpdateEmailPeriod = 0;
		try {
			LogUtil.logMessage("Entering UserMaintBO.updateEmailPeriod");
			nUpdateEmailPeriod = UserMaintDAO.updateEmailPeriod(objUserVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in UserMaintBO:BusinessException"
					+ objErrorInfo.getMessage());
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
		return nUpdateEmailPeriod;
	}
//	Added for CR-126 ends here	

	//Added for CR-128 starts here
	public ArrayList fetchActivityLog(UserVO objUserVO) throws EMDException, Exception {
LogUtil.logMessage("Entering UserMaintBO.fetchActivityLog");
		
		ArrayList arlActivityLog = null;
		try {
			arlActivityLog = UserMaintDAO.fetchActivityLog(objUserVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in UserMaintBO:BusinessException"
					+ objErrorInfo.getMessage());
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
		return arlActivityLog;
	}
//	Added for CR-128 ends here	
	
}
