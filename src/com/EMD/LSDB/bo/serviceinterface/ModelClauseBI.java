package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.ComponentVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business definition methods for the Clause
 *          Insertion
 ******************************************************************************/
public interface ModelClauseBI {
	public int insertClause(ClauseVO objClauseVO) throws EMDException,
	Exception;
	
	public ArrayList fetchParentClause(ClauseVO objClauseVO)
	throws EMDException, Exception;
	//Added for CR_88 on 2July10 by sd41630
	public int updateRearrangeClauses(ClauseVO objClauseVO) throws EMDException,
	Exception;
	/* Added for CR_99 by RJ85495  */
	public ArrayList validateClauseDescription(ClauseVO objClauseVO)
	throws EMDException,Exception;
	/* Added for CR_109 by ER91220 
	* Clauses By Components Report */	
	public ArrayList fetchClauses(ComponentVO objComponentVo)
	throws EMDException, Exception;
	//CR_109 Ends here
	//	Added for LSDB_CR-134 
	public int updateUserMarker(ClauseVO objClauseVO) throws EMDException,
	Exception;
	//CR-134 ends here
}
