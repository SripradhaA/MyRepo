/*
 * Created on Apr 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.bo.SpecMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.OrderBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.SpecMaintenance.OrderDAO;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SpecSuppVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for the Orders
 ******************************************************************************/
/***************************************************************************
 * ------------------------------------------------------------------------------------------------------
 *     Date         version  modified by        comments                              Remarks 
 * 03/05/2010        1.0      SD41630        Used to Turn ON/OFF Dynamic numbering        Added for CR_86
 * 											 button for remove the reserved clauses 
 * 											 into perticular that order		
 * ----------------------------------------------------------------------------------------------------------
 **************************************************************************/

public class OrderBO implements OrderBI {
	
	public static OrderBO objOrderBO = null;
	
	public synchronized static OrderBO getInstance() {
		
		if (objOrderBO == null) {
			objOrderBO = new OrderBO();
		}
		return objOrderBO;
	}
	
	private OrderBO() {
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 *            the object for searching Orders
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	
	public ArrayList fetchOrders(OrderVO objOrderVO) throws EMDException,
	Exception {
		
		ArrayList arlOrderList = null;
		try {
			LogUtil.logMessage("Entering ModelBO.fetchModels");
			arlOrderList = OrderDAO.fetchOrders(objOrderVO);
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
		
		return arlOrderList;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 *            the object for searching Orders for Modify Spec Screen
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchOrdersModifySpec(OrderVO objOrderVO)
	throws EMDException, Exception {
		ArrayList arlOrderList = null;
		try {
			LogUtil.logMessage("Entering ModelBO.fetchOrdersModifySpec");
			arlOrderList = OrderDAO.fetchOrdersModifySpec(objOrderVO);
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
		
		return arlOrderList;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 *            the object for searching Orders
	 * @return ArrayList
	 * @throws EMDException
	 **************************************************************************/
	
	public ArrayList fetchOrdersForSpecComparison(OrderVO objOrderVO)
	throws EMDException, Exception {
		
		ArrayList arlOrderList = null;
		try {
			LogUtil.logMessage("Entering ModelBO.fetchModels");
			arlOrderList = OrderDAO.fetchOrdersForSpecComparison(objOrderVO);
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
		
		return arlOrderList;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 *            the object for inserting new order
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public int insertOrder(OrderVO objOrderVO) throws EMDException, Exception {
		
		int intReturnStatus = 0;
		try {
			LogUtil.logMessage("Entering OrderBO.insertOrder");
			intReturnStatus = OrderDAO.insertOrder(objOrderVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderBo:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderBo:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 *            the object for inserting new order
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public int insertCopySpec(OrderVO objOrderVO) throws EMDException,
	Exception {
		
		int intReturnStatus = 0;
		
		try {
			LogUtil.logMessage("Entering OrderBO.insertCopySpec");
			intReturnStatus = OrderDAO.insertCopySpec(objOrderVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderBo:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderBo:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 *            the object for inserting new order
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public int insertCopySpecFromModel(OrderVO objOrderVO) throws EMDException,
	Exception {
		
		int intReturnStatus = 0;
		
		try {
			LogUtil.logMessage("Entering OrderBO.insertCopySpecFromModel");
			intReturnStatus = OrderDAO.insertCopySpecFromModel(objOrderVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderBo:BusinessException"
					+ objErrorInfo.getMessageID());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderBo:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 *            the object for updating Order
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public int updateOrders(OrderVO objOrderVO) throws EMDException, Exception {
		int intUpdStatus = 0;
		
		try {
			LogUtil.logMessage("Entering OrderBO.updateOrders");
			intUpdStatus = OrderDAO.updateOrder(objOrderVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderBO:BusinessException"
					+ objErrorInfo.getMessage());
			intUpdStatus = Integer.parseInt(objErrorInfo.getMessageID());
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
		
		return intUpdStatus;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 *            the object for inserting new order
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public OrderVO publishOrder(OrderVO objOrderVO) throws EMDException,
	Exception {
		
		int intReturnStatus = 0;
		OrderVO objOrder = null;
		try {
			LogUtil.logMessage("Entering OrderBO.publishOrder");
			objOrder = OrderDAO.prePublishOrder(objOrderVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderBo:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderBo:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return objOrder;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 *            the object for deleting Order Added for Delete Spec CR
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public int deleteOrders(OrderVO objOrderVO) throws EMDException, Exception {
		int intDelStatus = 0;
		
		try {
			LogUtil.logMessage("Entering OrderBO.deleteOrders");
			intDelStatus = OrderDAO.deleteOrders(objOrderVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderBO:BusinessException"
					+ objErrorInfo.getMessage());
			intDelStatus = Integer.parseInt(objErrorInfo.getMessageID());
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
		
		return intDelStatus;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 *            the object for fetching Order Spec Type
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public int fetchOrderSpecType(OrderVO objOrderVO) throws EMDException,
	Exception {
		int intDelStatus = 0;
		
		try {
			LogUtil.logMessage("Entering OrderBO.fetchOrderSpecType");
			intDelStatus = OrderDAO.fetchOrderSpecType(objOrderVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderBO:BusinessException"
					+ objErrorInfo.getMessage());
			intDelStatus = Integer.parseInt(objErrorInfo.getMessageID());
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
		
		return intDelStatus;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 * the object for resetting the spec
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public int resetSpecStatus(OrderVO objOrderVO) throws EMDException, Exception {
		int intUpdStatus = 0;
		
		try {
			LogUtil.logMessage("Entering OrderBO.resetSpecStatus");
			intUpdStatus = OrderDAO.resetSpecStatus(objOrderVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderBO:BusinessException"
					+ objErrorInfo.getMessage());
			intUpdStatus = Integer.parseInt(objErrorInfo.getMessageID());
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
		
		return intUpdStatus;
	}
	//Added CR_86 for Used to Turn ON/OFF Dynamic numbering
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 *            the object for updating dynamic NO/Off
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public int turnDynamicNumOnOff(OrderVO objOrderVO) throws EMDException, Exception {
		int intUpdStatus = 0;
		
		try {
			LogUtil.logMessage("Entering OrderBO.turnDynamicNoOff");
			intUpdStatus = OrderDAO.turnDynamicNumOnOff(objOrderVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderBO:BusinessException"
					+ objErrorInfo.getMessage());
			intUpdStatus = Integer.parseInt(objErrorInfo.getMessageID());
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
		
		return intUpdStatus;
	}
	//Added For CR_90 by Rahul
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 *            the object for inserting new order
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public OrderVO validatePublishSpec(OrderVO objOrderVO) throws EMDException,
	Exception {
		
		int intReturnStatus = 0;
		OrderVO objOrder = null;
		try {
			LogUtil.logMessage("Entering OrderBO.validatePublishSpec");
			objOrder = OrderDAO.validatePublishSpec(objOrderVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderBo:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderBo:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return objOrder;
	}

	//Added For CR_91 by Sekar
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/

	public SpecSuppVO getVersions(OrderVO objOrderVO) throws EMDException, Exception {
		int intReturnStatus = 0;
		SpecSuppVO objSpecSuppVO = null;
		try {
			LogUtil.logMessage("Entering OrderBO.getVersions");
			objSpecSuppVO = OrderDAO.getVersionsSpecSupp(objOrderVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderBo:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderBo:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return objSpecSuppVO;
	}

	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public ArrayList fetchSpecSupplementDetails(OrderVO objOrderVO) throws EMDException, Exception {
		ArrayList arlSpecSupplement = null;
		try {
			LogUtil.logMessage("Entering ModelBO.fetchSpecSupplementDetails");
			arlSpecSupplement = OrderDAO.fetchSpecSupplementDetails(objOrderVO);
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
		
		return arlSpecSupplement;
	}

//	Added for CR_91	Ends here.

	
	//Added for CR_99 for Regeneration of Spec
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 * The Object for Regeneration of Spec for PDFs
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public int regenerateSpec(OrderVO objOrderVO) throws EMDException, Exception {
		int intregenerateSpec = 0;
		
		try {
			LogUtil.logMessage("Entering OrderBO.regenerateSpec");
			intregenerateSpec = OrderDAO.regenerateSpec(objOrderVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderBO:BusinessException"
					+ objErrorInfo.getMessage());
			intregenerateSpec = Integer.parseInt(objErrorInfo.getMessageID());
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
		
		return intregenerateSpec;
	}
	
	// CR_99 Ends here

	
	//Added For CR_104 
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 *            the object for inserting new order
	 * @return int
	 * @throws EMDException
	 **************************************************************************/
	
	public OrderVO ordSecPublishAndNotification(OrderVO objOrderVO) throws EMDException,
	Exception {
		
		int intReturnStatus = 0;
		OrderVO objOrder = null;
		try {
			LogUtil.logMessage("Entering OrderBO.publishOrder");
			objOrder = OrderDAO.ordSecPublishAndNotification(objOrderVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderBo:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderBo:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return objOrder;
	}
	
	//Added for CR-121
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objOrderVO
	 *            the object for inserting new order
	 * @return int
	 * @throws EMDException
	 *************************************************************************/

	public ArrayList fetchMultipleOrders(OrderVO objOrderVo)
	throws EMDException, Exception {
		
		ArrayList arlMultipleOrderList = null;
		try {
			LogUtil.logMessage("Entering OrderBO.fetchMultipleOrders");
			arlMultipleOrderList = OrderDAO.fetchMultipleOrders(objOrderVo);
			
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
		
		return arlMultipleOrderList;
	}

	//Added for Cr-135 starts here
		public ArrayList fetchPrevOrders(OrderVO objOrderVO) throws EMDException,
		Exception {
			
			ArrayList arlPrevOrderList = null;
			try {
				LogUtil.logMessage("Entering OrderBO.fetchPrevOrders");
				arlPrevOrderList = OrderDAO.fetchPrevOrders(objOrderVO);
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
			
			return arlPrevOrderList;
		}
		public SpecSuppVO getPrevVersions(OrderVO objOrderVO) throws EMDException, Exception {
			int intReturnStatus = 0;
			SpecSuppVO objSpecSuppVO = null;
			try {
				LogUtil.logMessage("Entering OrderBO.getVersions");
				objSpecSuppVO = OrderDAO.getPrevVersionsSpecSupp(objOrderVO);
			} catch (BusinessException objBusExp) {
				ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
				LogUtil
				.logMessage("enters into catch block in OrderBo:BusinessException"
						+ objErrorInfo.getMessage());
				intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
			} catch (ApplicationException objAppExp) {
				ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
				LogUtil
				.logMessage("enters into catch block in OrderBO:ApplicationException"
						+ objErrorInfo.getMessage());
				throw new Exception(objErrorInfo.getMessage());
			} catch (Exception objExp) {
				LogUtil
				.logMessage("enters into catch block in OrderBo:ApplicationException"
						+ objExp.getMessage());
				throw new Exception(objExp.getMessage());
			}
			return objSpecSuppVO;
		}
		//	Added for Cr-135 ends here

	
}