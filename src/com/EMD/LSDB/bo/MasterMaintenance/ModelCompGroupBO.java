/*AK49339
 * Created on Aug 14, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.bo.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.ModelCompGroupBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.MasterMaintenance.ModelCompGroupDAO;
import com.EMD.LSDB.vo.common.CompGroupVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for the Component Groups
 ******************************************************************************/

public class ModelCompGroupBO implements ModelCompGroupBI {
	public static ModelCompGroupBO objModelCompGroupBO = null;
	
	public synchronized static ModelCompGroupBO getInstance() {
		if (objModelCompGroupBO == null) {
			objModelCompGroupBO = new ModelCompGroupBO();
		}
		return objModelCompGroupBO;
	}
	
	private ModelCompGroupBO() {
	}
	
	/***************************************************************************
	 * This Method is used to fetch the Component Group Details
	 * 
	 * @param objCompGroupVO
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchCompGroups(CompGroupVO objCompGroupVO)
	throws EMDException, Exception {
		ArrayList arlCompGrps = null;
		try {
			LogUtil.logMessage("Entering ModelCompGroupBO.fetchCompGroups");
			return ModelCompGroupDAO.fetch(objCompGroupVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentGroup:BusinessException"
					+ objErrorInfo.getMessage());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentGroup:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ComponentGroup:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlCompGrps;
	}
	
	/***************************************************************************
	 * This Method is used to insert a new Component Group.
	 * 
	 * @param objCompGroupVO
	 * @return boolean
	 * @throws EMDException
	 **************************************************************************/
	public int insertCompGroup(CompGroupVO objCompGroupVO) throws EMDException,
	Exception {
		int intReturnStatus = 0;
		try {
			LogUtil
			.logMessage("Inside the insertCompGroup method of ComponentGroupBO");
			intReturnStatus = ModelCompGroupDAO.insertCompGroup(objCompGroupVO);
		}
		
		catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentGroupBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentGroup:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ComponentGroupBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * This Method is used to Modify Component Group Details
	 * 
	 * @param objCompGroupVO
	 * @return boolean
	 * @throws EMDException
	 **************************************************************************/
	public int updateCompGroup(CompGroupVO objCompGroupVO) throws EMDException,
	Exception {
		
		int intReturnStatus = 0;
		try {
			LogUtil
			.logMessage("Inside the updateCompGroup method of ModelCompGroupBO");
			intReturnStatus = ModelCompGroupDAO.updateCompGroup(objCompGroupVO);
		}
		
		catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelCompGroupBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentGroup:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ModelCompGroupBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * Added for LSDB_CR_67
	 * This Method is used to Delete Component Group Details
	 * @param objCompGroupVO
	 * @return boolean
	 * @throws EMDException
	 **************************************************************************/
	public int deleteCompGroup(CompGroupVO objCompGroupVO) throws EMDException,
	Exception {
		
		int intReturnStatus = 0;
		try {
			LogUtil
			.logMessage("Inside the deleteCompGroup method of ModelCompGroupBO");
			intReturnStatus = ModelCompGroupDAO.deleteCompGroup(objCompGroupVO);
		}
		
		catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelCompGroupBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentGroup:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ModelCompGroupBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * This Method is used to fetch the Component Group Details for Component
	 * Group Report Added for CR-26 Component Group Component Report
	 * 
	 * @param objCompGroupVO
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchCompGrpReport(CompGroupVO objCompGroupVO)
	throws EMDException, Exception {
		ArrayList arlCompGrps = null;
		try {
			LogUtil.logMessage("Entering ModelCompGroupBO.fetchCompGrpReport");
			return ModelCompGroupDAO.fetchCompGrpReport(objCompGroupVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentGroup:BusinessException"
					+ objErrorInfo.getMessage());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentGroup:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ComponentGroup:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlCompGrps;
	}

	//Added For CR_81 on 24-Dec-09 by RR68151
	/***************************************************************************
	 * This Method is used to fetch the Component Group Types
	 * Added for CR-81 Locomotive ad PowerProducts Enhancements
	 * 
	 * @param objCompGroupVo
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchCompGrpTypes(CompGroupVO objCompGroupVo)
	throws EMDException, Exception {
		ArrayList arlCompGrpTypes = null;
		try {
			LogUtil.logMessage("Entering ModelCompGroupBO.fetchCompGrpTypes");
			return ModelCompGroupDAO.fetchCompGrpTypes(objCompGroupVo);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentGroup:BusinessException"
					+ objErrorInfo.getMessage());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentGroup:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ComponentGroup:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlCompGrpTypes;
	}
	//Added For CR_81 on 24-Dec-09 by RR68151 - Ends here
	
	//Added for CR-121
	
	/***************************************************************************
	 * This Method is used to fetch the Component detail in orders map
	 * 
	 * 
	 * @param objCompGroupVo
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList viewCompGrpInOrdMap(CompGroupVO objCompGroupVo)
	throws EMDException, Exception {
		ArrayList arlCompGrpTypes = null;
		try {
			LogUtil.logMessage("Entering ModelCompGroupBO.viewCompGrpInOrdMap");
			return ModelCompGroupDAO.viewCompGrpInOrdMap(objCompGroupVo);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentGroup:BusinessException"
					+ objErrorInfo.getMessage());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentGroup:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ComponentGroup:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlCompGrpTypes;
	}
	
	
	/***************************************************************************
	 * This Method is used to fetch the Component detail in orders map
	 * 
	 * 
	 * @param objCompGroupVo
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList viewCompInOrdMap(CompGroupVO objCompGroupVo)
	throws EMDException, Exception {
		ArrayList arlCompGrpTypes = null;
		try {
			LogUtil.logMessage("Entering ModelCompGroupBO.viewCompInOrdMap");
			return ModelCompGroupDAO.viewCompInOrdMap(objCompGroupVo);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentGroup:BusinessException"
					+ objErrorInfo.getMessage());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentGroup:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ComponentGroup:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlCompGrpTypes;
	}
}

