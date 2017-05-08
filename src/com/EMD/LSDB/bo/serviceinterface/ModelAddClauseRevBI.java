/**
 * 
 */
package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.ClauseVO;

/**
 * @author ps57222
 * 
 */
public interface ModelAddClauseRevBI {
	
	public ArrayList fetchClauses(ClauseVO objClauseVO) throws EMDException,
	Exception;
	
	public int insertClause(ClauseVO objClauseVO) throws EMDException,
	Exception;
	
}
