/*
 * Created on Jun 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SpecSuppVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business definition methods for the Model
 ******************************************************************************/
public interface OrderBI {
	
	public ArrayList fetchOrders(OrderVO objOrderVO) throws EMDException,
	Exception;
	
	public ArrayList fetchOrdersModifySpec(OrderVO objOrderVO)
	throws EMDException, Exception;
	
	public int insertOrder(OrderVO objOrderVO) throws EMDException, Exception;
	
	public int insertCopySpec(OrderVO objOrderVO) throws EMDException,
	Exception;
	
	public int insertCopySpecFromModel(OrderVO objOrderVO) throws EMDException,
	Exception;
	
	public int updateOrders(OrderVO objOrderVO) throws EMDException, Exception;
	
	public OrderVO publishOrder(OrderVO objOrderVO) throws EMDException,
	Exception;
	
	// Added for Delete spec CR
	public int deleteOrders(OrderVO objOrderVO) throws EMDException, Exception;
	
	// Added for CR-51 Alert System Engineer for Add Clause Order/Model
	public int fetchOrderSpecType(OrderVO objOrderVO) throws EMDException,
	Exception;
	
	//Added for LSDB_CR-76
	public int resetSpecStatus(OrderVO objOrderVO) throws EMDException, Exception;
//	Added for LSDB_CR_86 
	public int turnDynamicNumOnOff(OrderVO objOrderVO) throws EMDException, Exception;
	
	//Added for CR_90	
	public OrderVO validatePublishSpec(OrderVO objOrderVO) throws EMDException,
	Exception;
	
//	Added for CR_91	
	public SpecSuppVO getVersions(OrderVO objOrderVO) throws EMDException,
	Exception;
	
	public ArrayList fetchSpecSupplementDetails(OrderVO objOrderVO) throws EMDException,
	Exception;
	
	//Added for CR_99 for Regeneration of Spec
	public int regenerateSpec(OrderVO objOrderVO) throws EMDException,
	Exception;
	//CR_99 Ends here.
	
	
	//Added For CR_104
//	Added for CR_90	
	public OrderVO ordSecPublishAndNotification(OrderVO objOrderVO) throws EMDException,
	Exception;
	
// Added for CR_121
	public ArrayList fetchMultipleOrders(OrderVO objOrderVo) throws EMDException,
	Exception;
	
//Added for CR-135
	public ArrayList fetchPrevOrders(OrderVO objOrderVO) throws EMDException,
	Exception;
	
	public SpecSuppVO getPrevVersions(OrderVO objOrderVO) throws EMDException,
	Exception;

}