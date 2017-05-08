/*
 * Created on Jun 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.SpecTypeVo;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business definition methods for the Spec Type
 ******************************************************************************/

public interface SpecTypeBI {
	
	public ArrayList fetchSpecTypes(SpecTypeVo objSpecTypeVo)
	throws EMDException, Exception;
//	CR_84 lines started here 
	public int insertSpecTypeDetails(SpecTypeVo objSpecTypeVo)
	throws EMDException, Exception;
	public int updateSpecTypeDetails(SpecTypeVo objSpecTypeVo)
	throws EMDException, Exception;
//	CR_84 ends  here 
}