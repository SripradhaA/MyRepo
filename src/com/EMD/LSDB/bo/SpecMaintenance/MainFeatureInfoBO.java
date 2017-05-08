/**
 * 
 */
package com.EMD.LSDB.bo.SpecMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.MainFeatureInfoBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.MasterMaintenance.ModelClauseDAO;
import com.EMD.LSDB.dao.SpecMaintenance.MainFeatureInfoDAO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.MainFeatureInfoVO;

/**
 * @author ps57222
 * 
 */
public class MainFeatureInfoBO implements MainFeatureInfoBI {
	
	private MainFeatureInfoBO() {
		
	}
	
	public static MainFeatureInfoBO objMainFeatureInfoBO;
	
	public static synchronized MainFeatureInfoBO getInstance() {
		
		if (objMainFeatureInfoBO == null) {
			objMainFeatureInfoBO = new MainFeatureInfoBO();
		}
		
		return objMainFeatureInfoBO;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objMainFeatureInfoVO
	 *            The object for fetch MainFeatures
	 * @return Arraylist It has Arraylist of objMainFeatureInfoVO
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchMainFeatures(MainFeatureInfoVO objMainFeatureInfoVO)
	throws EMDException, Exception {
		LogUtil.logMessage("Enters into MainFeatureInfoBO:fetchMainFeatures");
		ArrayList arlMainFeaList = null;
		try {
			arlMainFeaList = MainFeatureInfoDAO
			.fetchMainFeatures(objMainFeatureInfoVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:BusinessException"
					+ objErrorInfo.getMessageID());
			// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		
		return arlMainFeaList;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd Added for LSDB_CR-62 by ka57588
	 * @version 1.0
	 * @param objMainFeatureInfoVO
	 *            The object for fetch the components from Model level into
	 *            Order level
	 * @return Arraylist It has Arraylist of objMainFeatureInfoVO
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchModelMainFeatures(
			MainFeatureInfoVO objMainFeatureInfoVO) throws EMDException,
			Exception {
		LogUtil
		.logMessage("Enters into MainFeatureInfoBO:fetchModelMainFeatures");
		ArrayList arlMainFeaList = null;
		try {
			arlMainFeaList = MainFeatureInfoDAO
			.fetchModelMainFeatures(objMainFeatureInfoVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:BusinessException"
					+ objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		
		return arlMainFeaList;
	}
	
	/***************************************************************************
	 * Added for LSDB_CR-62
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objMainFeatureInfoVO
	 *            The object for update Display In Spec
	 * @return Arraylist It Returns a boolean value
	 * @throws EMDException
	 **************************************************************************/
	public int updateDisplayInSpec(MainFeatureInfoVO objMainFeatureInfoVO)
	throws EMDException, Exception {
		LogUtil.logMessage("Enters into MainFeatureInfoBO:updateDisplayInSpec");
		int intStatusCode = 0;
		
		try {
			intStatusCode = MainFeatureInfoDAO
			.updateDisplayInSpec(objMainFeatureInfoVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intStatusCode;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objMainFeatureInfoVO
	 *            The object for Insert MainFeatures
	 * @return Arraylist It Returns a boolean value
	 * @throws EMDException
	 **************************************************************************/
	public int insertMainFeatures(MainFeatureInfoVO objMainFeatureInfoVO)
	throws EMDException, Exception {
		LogUtil.logMessage("Enters into MainFeatureInfoBO:insertMainFeatures");
		int intStatusCode = 0;
		try {
			intStatusCode = MainFeatureInfoDAO
			.insertMainFeatures(objMainFeatureInfoVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intStatusCode;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objMainFeatureInfoVO
	 *            The object for update MainFeatures
	 * @return Arraylist It Returns a boolean value
	 * @throws EMDException
	 **************************************************************************/
	public int updateMainFeatures(MainFeatureInfoVO objMainFeatureInfoVO)
	throws EMDException, Exception {
		LogUtil.logMessage("Enters into MainFeatureInfoBO:updateMainFeatures");
		int intStatusCode = 0;
		
		try {
			intStatusCode = MainFeatureInfoDAO
			.updateMainFeatures(objMainFeatureInfoVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intStatusCode;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objMainFeatureInfoVO
	 *            The object for fetch Order Components added for CR
	 * @return Arraylist It has Arraylist of objMainFeatureInfoVO
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchOrderDispComps(MainFeatureInfoVO objMainFeatureInfoVO)
	throws EMDException, Exception {
		LogUtil.logMessage("Enters into MainFeatureInfoBO:fetchOrderDefComps");
		ArrayList arlMainFeaList = null;
		try {
			arlMainFeaList = MainFeatureInfoDAO
			.fetchOrderDispComps(objMainFeatureInfoVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:BusinessException"
					+ objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		
		return arlMainFeaList;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objMainFeatureInfoVO
	 *            The object for update MainFeatures
	 * @return int It Returns a int value
	 * @throws EMDException
	 **************************************************************************/
	
	/***************************************************************************
	 * deleteMainFeatures is added For LSDB_CR-32 Added on 07-April-08 by
	 * ps57222
	 * 
	 */
	public int deleteMainFeatures(MainFeatureInfoVO objMainFeatureInfoVO)
	throws EMDException, Exception {
		LogUtil.logMessage("Enters into MainFeatureInfoBO:deleteMainFeatures");
		int intStatusCode = 0;
		
		try {
			intStatusCode = MainFeatureInfoDAO
			.deleteMainFeatures(objMainFeatureInfoVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intStatusCode;
		
	}
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            the object for updateRearrangeClauses
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public synchronized int rearrangeCompDesc(MainFeatureInfoVO objMainFeatureInfoVO)
	throws EMDException, Exception {
		int intReturnStatus = 0;
		try {
			LogUtil.logMessage("Enters into MainFeatureInfoBO:rearrangeCompDesc");
			intReturnStatus = MainFeatureInfoDAO.rearrangeCompDesc(objMainFeatureInfoVO);
		}
		
		catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
		
	}	
	
	
	//Addded For CR_104
	/***************************************************************************
	 * Added for LSDB_CR-62
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objMainFeatureInfoVO
	 *            The object for update Display In Spec
	 * @return Arraylist It Returns a boolean value
	 * @throws EMDException
	 **************************************************************************/
	public MainFeatureInfoVO publishAndNotification(MainFeatureInfoVO objMainFeatureInfoVO)
	throws EMDException, Exception {
		LogUtil.logMessage("Enters into MainFeatureInfoBO:updateDisplayInSpec");
		int intStatusCode = 0;
		MainFeatureInfoVO  objMainFeatureInfo=null;
		try {
			objMainFeatureInfo = MainFeatureInfoDAO
			.publishAndNotification(objMainFeatureInfoVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in MainFeatureInfoBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return objMainFeatureInfo;
		
	}
	
}