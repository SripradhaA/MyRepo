/* AK49339
 * Created on Aug 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package com.EMD.LSDB.bo.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.CustomerBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.MasterMaintenance.CustomerDAO;
import com.EMD.LSDB.vo.common.CustomerVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for the Customer
 ******************************************************************************/

public class CustomerBO implements CustomerBI {
	public static CustomerBO objCustomerBO = null;
	
	public synchronized static CustomerBO getInstance() {
		if (objCustomerBO == null) {
			objCustomerBO = new CustomerBO();
		}
		return objCustomerBO;
	}
	
	private CustomerBO() {
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objCustMaintVO
	 *            The object for fetch Customers
	 * @return Arraylist It has Arraylist of CustMaintVO
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchCustomers(CustomerVO objCustomerVO)
	throws EMDException, Exception {
		ArrayList arlCustomer = null;
		try {
			LogUtil
			.logMessage("Inside the fetchCustomers method of CustomerBo");
			arlCustomer = CustomerDAO.fetchCustomers(objCustomerVO);
		}
		
		catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in CustomerBo:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in CustomerBo:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in CustomerBo:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlCustomer;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSectionVO
	 *            The Object for Insert Customer
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public int insertCustomer(CustomerVO objCustomerVO) throws EMDException,
	Exception {
		int intReturnStatus = 0;
		
		try {
			LogUtil
			.logMessage("Inside the insertCustomer method of CustomerBo");
			intReturnStatus = CustomerDAO.insertCustomer(objCustomerVO);
		}
		
		catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in CustomerBo:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in CustomerBo:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in CustomerBo:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objCustMaintVO
	 *            The Object for Modify Customer
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	public int updateCustomer(CustomerVO objCustomerVO) throws EMDException,
	Exception {
		int intReturnStatus = 0;
		try {
			LogUtil
			.logMessage("Inside the updateCustomer method of CustomerBo");
			intReturnStatus = CustomerDAO.updateCustomer(objCustomerVO);
		}
		
		catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in CustomerBo:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in CustomerBo:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in CustomerBo:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
}
