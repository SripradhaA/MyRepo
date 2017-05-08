package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business definition methods for the Component
 *  * /***************************************************************************
 * ------------------------------------------------------------------------------------------------------
 *     Date            version       modify by                 comments                              Remarks 
 * 15/03/2011           1.0               SD41630           Added viewLeadComponentClausesByModel                	Added for CR_97
 * 										
 * 										 
  **************************************************************************/
 

public interface ModelCompBI {
	
	public ArrayList fetchComps(ComponentVO objComponentVO)
	throws EMDException, Exception;
	
	public ArrayList fetchModelComps(ComponentVO objComponentVO)
	throws EMDException, Exception;
	
	public int insertComp(ComponentVO objComponentVO) throws EMDException,
	Exception;
	
	public int updateComp(ComponentVO objComponentVO) throws EMDException,
	Exception;
	
	public int deleteComp(ComponentVO objComponentVO) throws EMDException,
	Exception;
	
	//Added For CR_93
	public int copyCompOrdrToMdl(ComponentVO objComponentVO) throws EMDException,
	Exception;
//	Added For CR_97 on 15 maarch 2011 by SD41630 
	public abstract ArrayList viewLeadComponentClausesByModel(ComponentVO objComponentVO)
	throws EMDException, Exception;
}
