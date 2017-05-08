/**
 * 
 */
package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.ClauseVO;

/**
 * @author RR68151
 * 
 */
public interface ModelAssignEdlBI {
	public ArrayList fetchCharGrpCombntn(ClauseVO objClauseVO)
	throws EMDException, Exception;

	public int insertCharGrpCombntn(ClauseVO objClauseVO) 
	throws EMDException, Exception;
	
	public int updateCharGrpCombntn(ClauseVO objClauseVO)
	throws EMDException, Exception;
	
	public int deleteCharGrpCombntn(ClauseVO objClauseVO)
	throws EMDException, Exception;
}