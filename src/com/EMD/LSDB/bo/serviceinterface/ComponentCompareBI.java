package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.OrderVO;

public interface ComponentCompareBI {
	
	public ArrayList fetchSectionsAndSubSections(ArrayList objOrderList)
	throws EMDException, Exception;
	
	/***************************************************************************
	 * fetchDifferencecomponent method is added for LSDB_CR-06 Added on
	 * 15-April-08 By ps57222
	 * 
	 */
	
	public ArrayList fetchDifferencecomponent(OrderVO objOrderVO)
	throws EMDException, Exception;
	
}