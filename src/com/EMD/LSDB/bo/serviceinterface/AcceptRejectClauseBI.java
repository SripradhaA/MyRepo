
package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.ClauseVO;

/**
 * @author VV49326
 * 
 */
/***********************************************************************
----------------------------------------------------------------------------------------------------------
*    Date     Version   Modified by    	 Comments                              		Remarks 
* 19/01/2010    1.0      RR68151       Added two new methods for Edl Indicator     Added for CR_81
* 											Screen.  
* 													 	 
* 
* --------------------------------------------------------------------------------------------------------
**************************************************************************/

public interface AcceptRejectClauseBI {
	
	public ArrayList fetchClauseVersions(ClauseVO objClauseVO)
	throws EMDException, Exception;
	
	public int updateClauseChange(ClauseVO objClauseVO) throws EMDException,
	Exception;
	
	public ArrayList fetchDeletedClauseVersions(ClauseVO objClauseVO)
	throws EMDException, Exception;
	
	public int updateDeleteClause(ClauseVO objClauseVO) throws EMDException,
	Exception;

	//Added for CR_81 EDL Change Indicator by RR68151 on 13-Jan-10 - Starts here
	
	public ArrayList fetchClauseEdlChanges(ClauseVO objClauseVO) throws EMDException,
	Exception;
	
	public int updateClauseEdlChange(ClauseVO objClauseVO) throws EMDException,
	Exception;
	
	//Added for CR_81 EDL Change Indicator by RR68151 on 13-Jan-10 - Ends here
}
