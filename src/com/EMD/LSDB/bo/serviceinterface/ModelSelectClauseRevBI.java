/**
 * 
 */
package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.ClauseVO;

/**
 * @author VV49326
 * 
 */
public interface ModelSelectClauseRevBI {
	public ArrayList fetchClauseVersions(ClauseVO objClauseVO)
	throws EMDException, Exception;
	
	public int updateApplyDefaultClause(ClauseVO objClauseVO)
	throws EMDException, Exception;
	
	public int deleteClause(ClauseVO objClauseVO) throws EMDException,
	Exception;
	
	public int deleteVersion(ClauseVO objClauseVO) throws EMDException,
	Exception;
	
	public int updateClauseVersion(ClauseVO objClauseVO) throws EMDException,
	Exception;
	
	/** Added for LSDB_CR-61 on 3-Dec-08 * */
	
	public ArrayList fetchClauseForLeadComp(ClauseVO objClauseVO)
	throws EMDException, Exception;
	
}