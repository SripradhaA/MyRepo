/**
 * 
 */
package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.SubSectionVO;

/**
 * @author vv49326
 * 
 */
public interface ModelSubSectionBI {
	
	public ArrayList fetchSubSections(SubSectionVO objSubSecMaintVO)
	throws EMDException, Exception;
	
	public int insertSubSection(SubSectionVO objSubSecMaintVO)
	throws EMDException, Exception;
	
	public int updateSubSection(SubSectionVO objSubSecMaintVO)
	throws EMDException, Exception;
	
	// Added for CR-4 Delete Section and Sub-Section
	public int deleteSubSection(SubSectionVO objSubSecMaintVO)
	throws EMDException, Exception;
	
	//Added for CR_92
	public ArrayList fetchModelSubSectionDetails(SubSectionVO objSubSecMaintVO)
	throws EMDException, Exception;
}