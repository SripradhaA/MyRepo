package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.StandardEquipVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business definition methods for
 *          StandardEquipment
 ******************************************************************************/
public interface StandardEquipBI {
	public ArrayList fetchStandardEquipment(StandardEquipVO objStandardEquipVO)
	throws EMDException, Exception;
	
	public int insertStandardEquipment(StandardEquipVO objStandardEquipVO)
	throws EMDException, Exception;
	
	public int modifyStandardEquipment(StandardEquipVO objStandardEquipVO)
	throws EMDException, Exception;
	
	public int deleteStandardEquipment(StandardEquipVO objStandardEquipVO)
	throws EMDException, Exception;
}