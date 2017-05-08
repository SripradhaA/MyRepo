package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.OrderVO;

public interface ClauseCompareBI {
	
	public ArrayList fetchSectionsAndSubSections(ArrayList objOrderList,
			String[] sectionNames) throws EMDException, Exception;
	/*Added for CR-135 */
	public ArrayList fetchOrderVsModelDetails(OrderVO objOrderVO) throws EMDException,
	Exception;
	
}
