/*
 * Created on Apr 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.bo.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.MasterMaintenance.SpecTypeDAO;
import com.EMD.LSDB.vo.common.SpecTypeVo;

/**
 * @author MM57219
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SpecTypeBO implements SpecTypeBI {
	
	public static SpecTypeBO objSpecTypeBO = null;
	
	public synchronized static SpecTypeBO getInstance() {
		if (objSpecTypeBO == null) {
			objSpecTypeBO = new SpecTypeBO();
		}
		return objSpecTypeBO;
	}
	
	private SpecTypeBO() {
	}
	//Modified for CR_84 for adding new Specification Types
	public ArrayList fetchSpecTypes(SpecTypeVo objSpecTypeVo)
	throws EMDException, Exception {
		ArrayList arlSpecTypeList = null;
		try {
			LogUtil
			.logMessage("Inside the fetchSpecTypes method of SpecTypeBO");
			arlSpecTypeList = SpecTypeDAO.fetchSpecTypes(objSpecTypeVo);
		} 	catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in SpecTypeBO:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in SpecTypeBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in SpecTypeBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlSpecTypeList;
	}

	//CR_84 Added for New Specification Type Details 
	public int insertSpecTypeDetails(SpecTypeVo objSpecTypeVo)
	throws EMDException, Exception {
		int intStatusCode;
		try {
			LogUtil
			.logMessage("Inside the insertSpecTypeMapDetails method of insertSpecTypeDetails");
			intStatusCode = SpecTypeDAO.insertSpecTypeDetails(objSpecTypeVo);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in SpecTypeBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in SpecTypeBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in SpecTypeBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intStatusCode;
	}

	public int updateSpecTypeDetails(SpecTypeVo objSpecTypeVo)
	throws EMDException, Exception {
		int intStatusCode;
		try {
			LogUtil
			.logMessage("Inside the updateSpecTypeMapDetails method of SpecTypeBO");
			intStatusCode = SpecTypeDAO.updateSpecTypeDetails(objSpecTypeVo);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in SpecTypeBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in SpecTypeBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in SpecTypeBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intStatusCode;
		
	}
	//CR_84 Added for New Specification Type Details - ends here
	
}