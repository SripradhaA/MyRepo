/* AK49339
 * Created on Aug 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.CustomerVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business definition methods for the Customer
 ******************************************************************************/
public interface CustomerBI {
	
	public ArrayList fetchCustomers(CustomerVO objCustMaintVO)
	throws EMDException, Exception;
	
	public int insertCustomer(CustomerVO objCustMaintVO) throws EMDException,
	Exception;
	
	public int updateCustomer(CustomerVO objCustMaintVO) throws EMDException,
	Exception;
}