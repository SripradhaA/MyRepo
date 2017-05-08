package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.ClauseVO;

/**
 * @author AK49339
 * 
 */
public interface AcceptRejectNewClauseBI {
	
	public ArrayList fetchNewClauses(ClauseVO objClauseVO) throws EMDException,
	Exception;
	
	public int updateNewClauseChange(ClauseVO objClauseVO) throws EMDException,
	Exception;
	
}
