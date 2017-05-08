package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.DistributorVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business definition methods for the
 *          Distributor
 ******************************************************************************/

public interface DistributorBI {
	
	public ArrayList fetchDistributors(DistributorVO objDistributorVO)
	throws EMDException, Exception;
	
	public int insertDistributor(DistributorVO objDistributorVO)
	throws EMDException, Exception;
	
	public int updateDistributor(DistributorVO objDistributorVO)
	throws EMDException, Exception;
	
	// Added for CR-55 on 19/09/08
	
	public int deleteDistributor(DistributorVO objDistributorVO)
	throws EMDException, Exception;
}
