/**
 * 
 */
package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.MainFeatureInfoVO;
import com.EMD.LSDB.vo.common.OrderVO;

/**
 * @author ps57222
 * 
 */
public interface MainFeatureInfoBI {
	
	public ArrayList fetchMainFeatures(MainFeatureInfoVO objMainFeatureInfoVO)
	throws EMDException, Exception;
	
	// Added for LSDB_CR-62
	public ArrayList fetchModelMainFeatures(
			MainFeatureInfoVO objMainFeatureInfoVO) throws EMDException,
			Exception;
	
	public int updateDisplayInSpec(MainFeatureInfoVO objMainFeatureInfoVO)
	throws EMDException, Exception;
	
	public int insertMainFeatures(MainFeatureInfoVO objMainFeatureInfoVO)
	throws EMDException, Exception;
	
	public int updateMainFeatures(MainFeatureInfoVO objMainFeatureInfoVO)
	throws EMDException, Exception;
	
	// Added for CR fetch Order Components to display in Main Features
	public ArrayList fetchOrderDispComps(MainFeatureInfoVO objMainFeatureInfoVO)
	throws EMDException, Exception;
	
	/***************************************************************************
	 * deleteMainFeatures is added For LSDB_CR-32 Added on 07-April-08 by
	 * ps57222
	 * 
	 * 
	 */
	public int deleteMainFeatures(MainFeatureInfoVO objMainFeatureInfoVO)
	throws EMDException, Exception;
	public int rearrangeCompDesc(MainFeatureInfoVO objMainFeatureInfoVO) throws EMDException,
	Exception;
	//Adeed For CR_104
	public MainFeatureInfoVO publishAndNotification(MainFeatureInfoVO objMainFeatureInfoVO)
	throws EMDException, Exception;
		
}