package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.ComponentVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This Action class has the business methods for the Component
 *          Mappings
 ******************************************************************************/
public interface ModelCompMapBI {
	
	public ArrayList fetchCompMap(ComponentVO objComponentVO)
	throws EMDException, Exception;
	
	public int updateCompMap(ArrayList objCompVoArrList) throws EMDException,
	Exception;
	
    //Added for CR-67 Unmap Component Grp
	public int unMapComponentGrp(ComponentVO objComponentVO) throws EMDException,
	Exception;
	
	public ArrayList fetchCompClauseMapDtls(ComponentVO objComponentVO) throws EMDException,
	Exception;
	
	//Added for CR-109 Import Component and Clause to Order
	public int insertCompMap(ComponentVO objComponentVO) throws EMDException,
	Exception;
}
