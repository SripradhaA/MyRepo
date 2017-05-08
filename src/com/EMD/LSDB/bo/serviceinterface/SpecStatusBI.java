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

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business definition methods for the Model
 ******************************************************************************/
public interface SpecStatusBI {
	
	public ArrayList fetchSpecNextStatus(OrderVO objOrderVO)
	throws EMDException, Exception;
	
}