package com.EMD.LSDB.bo.SpecMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.OrderGenArrangeBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.SpecMaintenance.OrderGenArrangeDAO;
import com.EMD.LSDB.vo.common.GenArrangeVO;

public class OrderGenArrangeBO implements OrderGenArrangeBI {
	
	private OrderGenArrangeBO() {
		
	}
	
	public static OrderGenArrangeBO objOrderGenArrangeBO;
	
	public static synchronized OrderGenArrangeBO getInstance() {
		if (objOrderGenArrangeBO == null) {
			objOrderGenArrangeBO = new OrderGenArrangeBO();
		}
		
		return objOrderGenArrangeBO;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objGenArrangeVO
	 *            the object for Fetching Images
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchGenArrImages(GenArrangeVO objGenArrangeVO)
	throws EMDException, Exception {
		ArrayList arlArrayList = new ArrayList();
		LogUtil
		.logMessage("Enters into OrderGenArrangeBO:fetchGenArrImages method");
		try {
			
			arlArrayList = OrderGenArrangeDAO
			.fetchGenArrImages(objGenArrangeVO);
			
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderGenArrangeBO:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderGenArrangeBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderGenArrangeBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		LogUtil.logMessage("Leaves from OrderGenArrangeBO:fetchGenArrImages");
		return arlArrayList;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objGenArrangeVO
	 *            the object for Updating Images
	 * @return int  the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	public int updateOrdGenArgmntViewDtls(GenArrangeVO objGenArrangeVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Enters into OrderGenArrangeBO:updateOrdGenArgmntViewDtls");
		int intStatusCode = 0;
		try {
			intStatusCode = OrderGenArrangeDAO.updateOrdGenArgmntViewDtls(objGenArrangeVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil.logMessage("DataAccessException in OrderGenArrangeBO:"
					+ objErrorInfo.getMessage());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderGenArrangeBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderGenArrangeBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		LogUtil.logMessage("Leaves From OrderGenArrangeBO:updateOrdGenArgmntViewDtls");
		return intStatusCode;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objGenArrangeVO
	 *            the object for Updating Notes
	 * @return int  the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	public int updateNotes(GenArrangeVO objGenArrangeVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Enters into OrderGenArrangeBO:UpdateNotes");
		int intStatusCode = 0;
		try {
			intStatusCode = OrderGenArrangeDAO.updateNotes(objGenArrangeVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderGenArrangeBO:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderGenArrangeBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		LogUtil.logMessage("Leaves From OrderGenArrangeBO:UpdateNotes");
		
		return intStatusCode;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objGenArrangeVO
	 *            the object for deleteOrdGenArgmtView
	 * @return int the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	public int deleteOrdGenArgmtView(GenArrangeVO objGenArrangeVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Enters into OrderGenArrangeBO:deleteOrdGenArgmtView");
		int intStatusCode = 0;
		try {
			intStatusCode = OrderGenArrangeDAO.deleteOrdGenArgmtView(objGenArrangeVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil.logMessage("DataAccessException in OrderGenArrangeBO:"
					+ objErrorInfo.getMessage());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderGenArrangeBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderGenArrangeBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		LogUtil.logMessage("Leaves From OrderGenArrangeBO:deleteOrdGenArgmtView");
		return intStatusCode;
	}
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objGenArrangeVO
	 *            the object for Updating Images
	 * @return int  the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	public int uploadOrdGenArgmntViewDtls(GenArrangeVO objGenArrangeVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Enters into OrderGenArrangeBO:uploadOrdGenArgmntViewDtls");
		int intStatusCode = 0;
		try {
			intStatusCode = OrderGenArrangeDAO.uploadOrdGenArgmntViewDtls(objGenArrangeVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil.logMessage("DataAccessException in OrderGenArrangeBO:"
					+ objErrorInfo.getMessage());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderGenArrangeBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderGenArrangeBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		LogUtil.logMessage("Leaves From OrderGenArrangeBO:uploadOrdGenArgmntViewDtls");
		return intStatusCode;
	}
	
	
	
}
