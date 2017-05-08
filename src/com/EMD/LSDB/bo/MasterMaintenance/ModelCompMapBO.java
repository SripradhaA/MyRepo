package com.EMD.LSDB.bo.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.ModelCompMapBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.MasterMaintenance.ModelCompMapDAO;
import com.EMD.LSDB.vo.common.ComponentVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This Action class has the business methods for the Component
 *          Mappings
 ******************************************************************************/
public class ModelCompMapBO implements ModelCompMapBI {
	
	public static ModelCompMapBO objModelCompMapBO = null;
	
	public synchronized static ModelCompMapBO getInstance() {
		if (objModelCompMapBO == null) {
			objModelCompMapBO = new ModelCompMapBO();
		}
		return objModelCompMapBO;
	}
	
	private ModelCompMapBO() {
	}
	
	/***************************************************************************
	 * This Method is used to fetch the Component Mapping Details For Selected
	 * Model,Section, SubSection and Component Group
	 * 
	 * @param objComponentVO
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchCompMap(ComponentVO objComponentVO)
	throws EMDException, Exception {
		ArrayList arlCompList = null;
		try {
			arlCompList = ModelCompMapDAO.fetchCompMap(objComponentVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelCompMapBO:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelCompMapBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ModelCompMapBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlCompList;
	}
	
	/***************************************************************************
	 * This Method is used to delete existing component mappings and to insert
	 * new Component Mappings For Selected Model,Section, SubSection and
	 * Component Group
	 * 
	 * @param objComponentVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public int updateCompMap(ArrayList objCompVoArrList) throws EMDException,
	Exception {
		int intReturnStatus = 0;
		try {
			intReturnStatus = ModelCompMapDAO.updateCompMap(objCompVoArrList);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelCompMapBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelCompMapBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ModelCompMapBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	

/***************************************************************************
	 * This Method is used to Unmap the Component Grp from Model and SubSection
	 * Added for CR-67
	 * 
	 * @param objComponentVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public int unMapComponentGrp(ComponentVO objComponentVO) throws EMDException,
	Exception {
		int intReturnStatus = 0;
		try {
			intReturnStatus = ModelCompMapDAO.unMapComponentGrp(objComponentVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelCompMapBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelCompMapBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ModelCompMapBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * This Method is used to fetch the Component and Clause Details For Selected
	 * Model,SubSection and Component Group
	 * 
	 * @param objComponentVO
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchCompClauseMapDtls(ComponentVO objComponentVO)
	throws EMDException, Exception {
		ArrayList arlCompList = null;
		try {
			arlCompList = ModelCompMapDAO.fetchCompClauseMapDtls(objComponentVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelCompMapBO:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelCompMapBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ModelCompMapBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlCompList;
	
	}
	
	/***************************************************************************
	 * Added For CR_109
	 * This Method is used to insert new component for existing component mappings
	 * 
	 * @param objComponentVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public int insertCompMap(ComponentVO objComponentVO) throws EMDException,
	Exception {
		int intReturnStatus = 0;
		try {
			intReturnStatus = ModelCompMapDAO.insertCompMap(objComponentVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelCompMapBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelCompMapBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ModelCompMapBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
}
