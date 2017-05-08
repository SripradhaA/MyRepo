/**
 * 
 */
package com.EMD.LSDB.bo.SpecMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.OrderClauseBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.SpecMaintenance.OrderClauseDAO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.OrderVO;

/**
 * @author ps57222
 * 
 */
public class OrderClauseBO implements OrderClauseBI {
	
	private OrderClauseBO() {
		
	}
	
	public static OrderClauseBO objOrderClauseBO = null;
	
	public synchronized static OrderClauseBO getInstance() {
		if (objOrderClauseBO == null) {
			objOrderClauseBO = new OrderClauseBO();
		}
		return objOrderClauseBO;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            The object for fetch clause Details in OrderLevel
	 * @return Arraylist It has Arraylist of objSectionVO
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchClauses(ClauseVO objClauseVO) throws EMDException,
	Exception {
		ArrayList arlClauseList = null;
		
		try {
			arlClauseList = OrderClauseDAO.fetchClauses(objClauseVO);
		}
		
		catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:BusinessException"
					+ objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		
		return arlClauseList;
	}
	
	/***************************************************************************
	 * This Method is used to insert a new Clause.
	 * 
	 * @param objClauseVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public synchronized int insertClause(ClauseVO objClauseVO)
	throws EMDException, Exception {
		
		int intReturnStatus = 0;
		//int intClauseDescErrorID=904;//Commented for CR-121
		try {
			LogUtil.logMessage("Entering OrderClauseBO.insertClause");
			/* Commented for CR-121
			if(objClauseVO.getClauseDesc().length() > 4000)
			{
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessageID("" + intClauseDescErrorID);
				LogUtil.logMessage("Error message in ErrorInfo:"
						+ objErrorInfo.getMessageID());
				throw new BusinessException(objErrorInfo);
			}*/
			intReturnStatus = OrderClauseDAO.insertClause(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * This Method is used to delete a Clause.
	 * 
	 * @param objClauseVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public int deleteClause(ClauseVO objClauseVO) throws EMDException,
	Exception {
		
		int intReturnStatus = 0;
		try {
			
			LogUtil.logMessage("Entering OrderClauseBO.deleteClause");
			intReturnStatus = OrderClauseDAO.deleteClause(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	
	/***************************************************************************
	 * This Method is used to update user level marker.
	 * Added for LSDB_CR-74 by ka57588
	 * @param objClauseVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public int updateUserMarker(ClauseVO objClauseVO) throws EMDException,
	Exception {
		
		int intReturnStatus = 0;
		try {
			
			LogUtil.logMessage("Entering OrderClauseBO.updateUserMarker");
			intReturnStatus = OrderClauseDAO.updateUserMarker(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * This Method is used to update Order level Clause reason.
	 * Added for LSDB_CR-74 by ka57588
	 * @param objClauseVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public int updateClauseReason(ClauseVO objClauseVO) throws EMDException,
	Exception {
		
		int intReturnStatus = 0;
		try {
			
			LogUtil.logMessage("Entering OrderClauseBO.updateClauseReason");
			intReturnStatus = OrderClauseDAO.updateClauseReason(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	
	/***************************************************************************
	 * This Method is used to display Order Clause reason.
	 * Added for LSDB_CR-74 by ka57588
	 * @param objClauseVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList displayClauseReason(ClauseVO objClauseVO) throws EMDException,
	Exception {
		
		ArrayList arlClauseList = null;
		try {
			
			LogUtil.logMessage("Entering OrderClauseBO.displayClauseReason");
			arlClauseList = OrderClauseDAO.displayClauseReason(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:BusinessException"
					+ objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlClauseList;
	}
	
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objComponentVO Added for CR-68 Order New Component
	 *            
	 * @return boolean
	 * @throws EMDException
	 **************************************************************************/
	
	public boolean validateOrderComponent(ComponentVO objComponentVO) throws EMDException, Exception {
		boolean blnValid=false;
		
		try {
			LogUtil.logMessage("Entering OrderBO.validateOrderComponent");
			blnValid = OrderClauseDAO.validateOrderComponent(objComponentVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderBO:BusinessException"
					+ objErrorInfo.getMessage());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		
		return blnValid;
	}
	/***************************************************************************
	 * This Method is used to update user level marker.
	 * Added for LSDB_CR-92 by RJ85495
	 * @param objClauseVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public int updateSysMarker(ClauseVO objClauseVO) throws EMDException,
	Exception {
		
		int intReturnStatus = 0;
		try {
			
			LogUtil.logMessage("Entering OrderClauseBO.updateSysMarker");
			intReturnStatus = OrderClauseDAO.updateSysMarker(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	/***************************************************************************
	 * This Method is used to update user level marker.
	 * Added for LSDB_CR-92 by RJ85495
	 * @param objClauseVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList deletedClausesHistoy(ClauseVO objClauseVO) throws EMDException,
	Exception {
		
		ArrayList arlClauseList = null;
		try {
			
			LogUtil.logMessage("Entering OrderClauseBO.deletedClausesHistoy");
			arlClauseList = OrderClauseDAO.deletedClausesHistoy(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:BusinessException"
					+ objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		
		return arlClauseList;
	}
	/***************************************************************************
	 * This Method is used to insert a new Clause.
	 * 
	 * @param objClauseVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public synchronized int retrieveDeletedClause(ClauseVO objClauseVO)
	throws EMDException, Exception {
		
		int intReturnStatus = 0;
		try {
			
			LogUtil.logMessage("Entering OrderClauseBO.retrieveDeletedClause");
			intReturnStatus = OrderClauseDAO.retrieveDeletedClause(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * This Method is used to addDelSalesVerEnggSpec.
	 * Added for LSDB_CR-99 by SD41630
	 * @param objClauseVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public int addDelSalesVerEnggSpec(ClauseVO objClauseVO) throws EMDException,
	Exception {
		
		int intReturnStatus = 0;
		try {
			
			LogUtil.logMessage("Entering OrderClauseBO.addDelSalesVerEnggSpecDAO");
			intReturnStatus = OrderClauseDAO.addDelSalesVerEnggSpecDAO(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO+addDelSalesVerEnggSpecDAO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBOaddDelSalesVerEnggSpecDAO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO+addDelSalesVerEnggSpecDAO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * CR_108 for Order Specific CLause Report
	 * @version 1.0
	 * @param objOrderVO
	 * The object to fetch the clause created at OrderLevel
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchOrdrSpecificClauses(OrderVO objOrderVO) throws EMDException,
	Exception {
		ArrayList arlClauseList = null;
		
		try {
			arlClauseList = OrderClauseDAO.fetchOrdrSpecificClauses(objOrderVO);
		}
		
		catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:BusinessException"
					+ objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		
		return arlClauseList;
	}
	
	/***************************************************************************
	 * @author Mahindra Satyam
	 * CR_111 for Order Clause Rearranging
	 * @version 1.0
	 * @param objClauseVO
	 * The object to save Clause arranging
	 * @throws EMDException
	 **************************************************************************/
	public int saveOrderClaReArrange(ClauseVO objClauseVO) throws EMDException,
	Exception {
		int intReturnStatus = 0;		
		try {
			intReturnStatus = OrderClauseDAO.saveOrderClaReArrange(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO+saveOrderClaReArrange:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBOsaveOrderClaReArrange:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO+saveOrderClaReArrange:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * This Method is used to populate report which are the Clauses with Indicators
	 * Added for LSDB_CR-113 
	 * @param objClauseVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList clausesWithIndicators(ClauseVO objClauseVO) throws EMDException,
	Exception {
		
		ArrayList arlClauseList = null;
		try {
			
			LogUtil.logMessage("Entering OrderClauseBO.clausesWithIndicators");
			arlClauseList = OrderClauseDAO.clausesWithIndicators(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:BusinessException"
					+ objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		
		return arlClauseList;
	}
	//Added for CR-121
	/***************************************************************************
	 * This Method is used to delete a OptionalClause.
	 * 
	 * @param objClauseVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	public int removeOptionalClauses(ClauseVO objClauseVO) throws EMDException,
	Exception {
		
		int intReturnStatus = 0;
		try {
			
			LogUtil.logMessage("Entering OrderClauseBO.removeOptionalClauses");
			intReturnStatus = OrderClauseDAO.removeOptionalClauses(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderClauseBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
}
