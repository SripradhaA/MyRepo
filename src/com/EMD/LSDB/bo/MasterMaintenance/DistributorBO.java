package com.EMD.LSDB.bo.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.DistributorBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.MasterMaintenance.DistributorDAO;
import com.EMD.LSDB.vo.common.DistributorVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for the Distributor
 ******************************************************************************/

public class DistributorBO implements DistributorBI {
	
	public static DistributorBO objDistributorBO = null;
	
	public synchronized static DistributorBO getInstance() {
		if (objDistributorBO == null) {
			objDistributorBO = new DistributorBO();
		}
		return objDistributorBO;
	}
	
	private DistributorBO() {
	}
	
	/***************************************************************************
	 * This Method is used to fetch the Distributor Details
	 * 
	 * @param objDistributorVo
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchDistributors(DistributorVO objDistributorVO)
	throws EMDException, Exception {
		
		ArrayList arlDistributor = null;
		try {
			LogUtil.logMessage("Entering DistributorBO.fetchDistributors");
			arlDistributor = DistributorDAO.fetchDistributors(objDistributorVO);
		} catch (BusinessException objBusEx) {
			ErrorInfo objErrorInfo = objBusEx.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in DistributorBo:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in DistributorBo:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in DistributorBo:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlDistributor;
	}
	
	/***************************************************************************
	 * This Method is used to insert a new Distributor.
	 * 
	 * @param objDistributorVo
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public int insertDistributor(DistributorVO objDistributorVO)
	throws EMDException, Exception {
		
		int intReturnStatus = 0;
		try {
			
			LogUtil.logMessage("Entering DistributorBO.insertDistributor");
			intReturnStatus = DistributorDAO
			.insertDistributor(objDistributorVO);
		} catch (BusinessException objBusEx) {
			ErrorInfo objErrorInfo = objBusEx.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in DistributorBo:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in DistributorBo:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in DistributorBo:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * This Method is used to Modify Distributor Details
	 * 
	 * @param objDistributorVo
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public int updateDistributor(DistributorVO objDistributorVO)
	throws EMDException, Exception {
		
		int intReturnStatus = 0;
		try {
			
			LogUtil.logMessage("Entering DistributorBO.updateDistributor");
			intReturnStatus = DistributorDAO
			.updateDistributor(objDistributorVO);
			
		} catch (BusinessException objBusEx) {
			ErrorInfo objErrorInfo = objBusEx.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in DistributorBo:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in DistributorBo:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in DistributorBo:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * This Method is used to Delete Distributor Details For CR-55
	 * 
	 * @param objDistributorVo
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public int deleteDistributor(DistributorVO objDistributorVO)
	throws EMDException, Exception {
		
		int intReturnStatus = 0;
		try {
			
			LogUtil.logMessage("Entering DistributorBO.deleteDistributor");
			intReturnStatus = DistributorDAO
			.deleteDistributor(objDistributorVO);
			
		} catch (BusinessException objBusEx) {
			ErrorInfo objErrorInfo = objBusEx.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in DistributorBo:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in DistributorBo:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in DistributorBo:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
}
