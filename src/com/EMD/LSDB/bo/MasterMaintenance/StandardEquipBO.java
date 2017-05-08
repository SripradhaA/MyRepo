package com.EMD.LSDB.bo.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.StandardEquipBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.MasterMaintenance.StandardEquipDAO;
import com.EMD.LSDB.vo.common.StandardEquipVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for the StandardEquipment
 ******************************************************************************/
public class StandardEquipBO implements StandardEquipBI {
	public static StandardEquipBO objStandardEquipBO = null;
	
	public synchronized static StandardEquipBO getInstance() {
		if (objStandardEquipBO == null) {
			objStandardEquipBO = new StandardEquipBO();
		}
		return objStandardEquipBO;
	}
	
	private StandardEquipBO() {
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objStandardEquipVO
	 *            the object for fetching StandardEquipment
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public ArrayList fetchStandardEquipment(StandardEquipVO objStandardEquipVO)
	throws EMDException, Exception {
		ArrayList arlStdEqpList = new ArrayList();
		try {
			LogUtil
			.logMessage("Enters into  the StandardEquipBO:fetchStandard Equipment");
			arlStdEqpList = StandardEquipDAO
			.fetchStandardEquipment(objStandardEquipVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in StandardEquipBO:BusinessException"
					+ objErrorInfo.getMessageID());
			// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in StandardEquipBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in StandardEquipBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlStdEqpList;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objStandardEquipVO
	 *            the object for insert StandardEquipment
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public int insertStandardEquipment(StandardEquipVO objStandardEquipVO)
	throws EMDException, Exception {
		int intStatusCode;
		try {
			LogUtil
			.logMessage("Enters into  the StandardEquipBO:insertStandardEquipment Equipment");
			intStatusCode = StandardEquipDAO
			.insertStandardEquipment(objStandardEquipVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in StandardEquipBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in StandardEquipBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in StandardEquipBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intStatusCode;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objStandardEquipVO
	 *            the object for modify StandardEquipment
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public int modifyStandardEquipment(StandardEquipVO objStandardEquipVO)
	throws EMDException, Exception {
		int intStatusCode;
		try {
			LogUtil
			.logMessage("Enters into  the StandardEquipBO:modifyStandardEquipment Equipment");
			intStatusCode = StandardEquipDAO
			.modifyStandardEquipment(objStandardEquipVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in StandardEquipBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in StandardEquipBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in StandardEquipBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intStatusCode;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objStandardEquipVO
	 *            the object for Delete StandardEquipment
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public int deleteStandardEquipment(StandardEquipVO objStandardEquipVO)
	throws EMDException, Exception {
		int intStatusCode;
		try {
			LogUtil
			.logMessage("Enters into  the StandardEquipBO:deleteStandardEquipment");
			intStatusCode = StandardEquipDAO
			.deleteStandardEquipment(objStandardEquipVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in StandardEquipBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in StandardEquipBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in StandardEquipBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intStatusCode;
	}
	
}