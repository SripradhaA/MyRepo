/**
 * 
 */
package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.admn.UserVO;

/**
 * @author VV49326
 * 
 */
public interface UserMaintBI {
	
	public ArrayList fetchUsers(UserVO objUserVO) throws EMDException,
	Exception;
	
	public int deleteUser(UserVO objUserVO) throws EMDException, Exception;
	
	public int insertUser(UserVO objUserVO) throws EMDException, Exception;
	
	public int updateUser(UserVO objUserVO) throws EMDException, Exception;
	
	//Added for CR-112
	public ArrayList fetchUserRoles(UserVO objUserVO) throws EMDException,
	Exception;
	
	//Added for Cr-113
	public int broadcastEmail(UserVO objUserVO) throws EMDException, Exception;
	
	//	Added for Cr-124
	public ArrayList fetchEmailDetails(UserVO objUserVO) throws EMDException, 
	Exception;
	public int purgeEmail(UserVO objUserVO) throws EMDException, Exception;
	
	//Added for CR-126
	public int save(UserVO objUserVO) throws EMDException, Exception;
	
	public int fetchEmailPeriod(UserVO objUserVO) throws EMDException, Exception;
	
	public int updateEmailPeriod(UserVO objUserVO) throws EMDException, Exception;
	
	//Added for Cr-128
	public ArrayList fetchActivityLog(UserVO objUserVO) throws EMDException,	Exception;
}
