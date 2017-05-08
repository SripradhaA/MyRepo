/**
 * 
 */
package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;

/**
 * @author PS57222
 * 
 */
public interface EMDBI {
	
	public boolean screenAccess(ArrayList objArrayList) throws EMDException;
	
}
