/*
 * Created on Apr 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.bo.SpecMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.OrderSpecificationBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.SpecMaintenance.OrderSpecificationDAO;
import com.EMD.LSDB.vo.common.SpecificationVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for the Model Specification
 ******************************************************************************/

public class OrderSpecificationBO implements OrderSpecificationBI {
	
	public static OrderSpecificationBO objOrderSpecificationBO = null;
	
	public synchronized static OrderSpecificationBO getInstance() {
		
		if (objOrderSpecificationBO == null) {
			objOrderSpecificationBO = new OrderSpecificationBO();
		}
		return objOrderSpecificationBO;
	}
	
	private OrderSpecificationBO() {
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objModelVO
	 *            the object for inserting specification
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public int insertSpecification(SpecificationVO objSpecificationVO)
	throws EMDException, Exception {
		
		int intReturnStatus = 0;
		
		try {
			LogUtil
			.logMessage("Entering OrderSpecificationBO.insertSpecification");
			intReturnStatus = OrderSpecificationDAO
			.insertSpecification(objSpecificationVO);
		}
		
		catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSpecificationBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSpecificationBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderSpecificationBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objModelVO
	 *            the object for inserting specification item
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public int insertSpecificationItem(SpecificationVO objSpecificationVO)
	throws EMDException, Exception {
		
		int intReturnStatus = 0;
		
		try {
			LogUtil
			.logMessage("Entering OrderSpecificationBO.insertSpecificationItem");
			intReturnStatus = OrderSpecificationDAO.insertSpecificationItem(
					objSpecificationVO, null, true);
		}
		
		catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSpecificationBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSpecificationBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderSpecificationBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objModelVO
	 *            the object for searching specifications
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public ArrayList fetchSpecificationItems(SpecificationVO objSpecificationVO)
	throws EMDException, Exception {
		
		ArrayList arlSpecItem = null;
		try {
			LogUtil
			.logMessage("Entering OrderSpecificationBO.fetchSpecificationItems");
			arlSpecItem = OrderSpecificationDAO
			.fetchSpecificationItems(objSpecificationVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSpecificationBO:BusinessException"
					+ objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSpecificationBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderSpecificationBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlSpecItem;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objModelVO
	 *            the object for updating spec items
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public int updateSpecificationItem(SpecificationVO objSpecificationVO)
	throws EMDException, Exception {
		
		int intReturnStatus = 0;
		
		try {
			LogUtil
			.logMessage("Entering OrderSpecificationBO.updateSpecificationItem");
			intReturnStatus = OrderSpecificationDAO
			.updateSpecificationItem(objSpecificationVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSpecificationBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSpecificationBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderSpecificationBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objModelVO
	 *            the object for updating spec items
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public int updateHpRating(SpecificationVO objSpecificationVO)
	throws EMDException, Exception {
		
		int intReturnStatus = 0;
		
		try {
			LogUtil.logMessage("Entering OrderSpecificationBO.updateHpRating");
			intReturnStatus = OrderSpecificationDAO
			.updateHpRating(objSpecificationVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSpecificationBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSpecificationBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderSpecificationBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * @author ka57588 Added for LSDB_CR-64 This method is used for updating
	 *         specification description
	 * @version 1.0
	 * @param objSpecificationVO
	 *            the object for specification
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public int updateSpecification(SpecificationVO objSpecificationVO)
	throws EMDException, Exception {
		
		int intReturnStatus = 0;
		
		try {
			LogUtil
			.logMessage("Entering OrderSpecificationBO.updateSpecification");
			intReturnStatus = OrderSpecificationDAO
			.updateSpecification(objSpecificationVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSpecificationBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSpecificationBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderSpecificationBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * @author ka57588 Added for LSDB_CR-64 This method is used for deleting
	 *         description
	 * @version 1.0
	 * @param objSpecificationVO
	 *            the object for specification
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public int deleteSpecification(SpecificationVO objSpecificationVO)
	throws EMDException, Exception {
		
		int intReturnStatus = 0;
		
		try {
			LogUtil
			.logMessage("Entering OrderSpecificationBO.deleteSpecification");
			intReturnStatus = OrderSpecificationDAO
			.deleteSpecification(objSpecificationVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSpecificationBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSpecificationBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderSpecificationBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSpecificationVO
	 *            the object for specification item
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public int deleteSpecificationItem(SpecificationVO objSpecificationVO)
	throws EMDException, Exception {
		
		int intReturnStatus = 0;
		
		try {
			LogUtil
			.logMessage("Entering OrderSpecificationBO.updateSpecificationItem");
			intReturnStatus = OrderSpecificationDAO
			.deleteSpecificationItem(objSpecificationVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSpecificationBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSpecificationBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderSpecificationBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
}
