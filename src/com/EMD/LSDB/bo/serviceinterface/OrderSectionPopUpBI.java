package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.SectionVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business definition methods for the
 *          OrderSectionPopUp
 ******************************************************************************/
public interface OrderSectionPopUpBI {
	
	public int savePartOf(ClauseVO objClauseVO) throws EMDException, Exception;
	
	public ArrayList fetchClauses(SectionVO objSectionVO) throws EMDException,
	Exception;
}
