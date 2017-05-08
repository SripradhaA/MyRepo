/**
 * 
 */
package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.OrderVO;

/**
 * @author ps57222
 * 
 */
public interface OrderClauseBI {
	
	public ArrayList fetchClauses(ClauseVO objClauseVO) throws EMDException,
	Exception;
	
	public int insertClause(ClauseVO objClauseVO) throws EMDException,
	Exception;
	
	public int deleteClause(ClauseVO objClauseVO) throws EMDException,
	Exception;
	
	//Added for LSDB_CR-74 by ka57588
	public int updateUserMarker(ClauseVO objClauseVO) throws EMDException,
	Exception;
	
	public ArrayList displayClauseReason(ClauseVO objClauseVO) throws EMDException,
	Exception;

	public int updateClauseReason(ClauseVO objClauseVO) throws EMDException,
	Exception;	
	//Added for CR-68 Order New Component
	public boolean validateOrderComponent(ComponentVO objComponentVO) throws EMDException,
	Exception;
//	Added for LSDB_CR-92 by RJ85495
	public int updateSysMarker(ClauseVO objClauseVO) throws EMDException,
	Exception;
	public ArrayList deletedClausesHistoy(ClauseVO objClauseVO) throws EMDException,
	Exception;
	public int retrieveDeletedClause(ClauseVO objClauseVO) throws EMDException,
	Exception;
	//Added For CR_99
	public int addDelSalesVerEnggSpec(ClauseVO objClauseVO) throws EMDException,
	Exception;
	//Added for CR_108 - Order Specific Clause report 
	public ArrayList fetchOrdrSpecificClauses(OrderVO objOrderVO) throws EMDException,
	Exception;
	//CR_108 Ends here
	//Added for CR_111 - Order Clause Rearranging 
	public int saveOrderClaReArrange(ClauseVO objClauseVO) throws EMDException,
	Exception;
	//Added for CR-113 - Clauses With Indicators
	public ArrayList clausesWithIndicators(ClauseVO objClauseVO) throws EMDException,
	Exception;
	//Added for CR-121
	public int removeOptionalClauses(ClauseVO objClauseVO) throws EMDException,
	Exception;
	
}
