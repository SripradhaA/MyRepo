package com.EMD.LSDB.bo.common;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.EMDBI;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.common.EMDDAO;

public class EMDBO implements EMDBI {
	
	public boolean screenAccess(ArrayList objArrayList) throws EMDException {
		LogUtil.logMessage("Inside ScreenAccess Method in EMDBO");
		if (EMDDAO.screenAccess(objArrayList)) {
			return true;
		}
		return false;
	}
	
}
