/*
 * Created on Jun 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.SpecificationVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business definition methods for the Model
 ******************************************************************************/
public interface ModelSpecificationBI {
	
	public ArrayList fetchSpecificationItems(SpecificationVO objSpecificationVO)
	throws EMDException, Exception;
	
	public int insertSpecification(SpecificationVO objSpecificationVO)
	throws EMDException, Exception;
	
	public int insertSpecificationItem(SpecificationVO objSpecificationVO)
	throws EMDException, Exception;
	
	public int updateSpecificationItem(SpecificationVO objSpecificationVO)
	throws EMDException, Exception;
	
	// Added for LSDB_CR_64
	public int deleteSpecification(SpecificationVO objSpecificationVO)
	throws EMDException, Exception;
	
	public int deleteSpecificationItem(SpecificationVO objSpecificationVO)
	throws EMDException, Exception;
	
	public int updateSpecification(SpecificationVO objSpecificationVO)
	throws EMDException, Exception;
}