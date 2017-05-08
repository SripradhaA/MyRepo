package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.EMDVO;
import com.EMD.LSDB.vo.common.LoginVO;

public interface LoginBI {
	public boolean findUser(LoginVO objLoginVO) throws EMDException, Exception;
	
	public boolean screenAccess(ArrayList objArrayList) throws EMDException;
	
	//Added for CR-112 to Add Message Of the Day in home screen
	public ArrayList fetchMessage(LoginVO objLoginVO) throws EMDException,
	Exception;
	
	public int insertMessage(EMDVO objEMDVO) throws EMDException, Exception;
	//CR_112 Ends here
}
