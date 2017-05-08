package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.ClauseVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business definition methods for the History
 *          Edl Pop Up
 ******************************************************************************/
public interface HistoryEdlPopUpBI {
	
	public ArrayList fetchEdlNo(ClauseVO objClauseVO) throws EMDException,
	Exception;
}
