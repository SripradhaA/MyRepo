/**
 * 
 */
package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.RevisionChangesVO;

/**
 * @author ER91220
 * 
 */
public interface RevisionChangesBI {
	
	public ArrayList fetchRevisions(RevisionChangesVO objRevisionChangesVO) throws EMDException,
	Exception;
		
}
