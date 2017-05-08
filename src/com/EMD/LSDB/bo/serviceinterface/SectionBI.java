/**
 * 
 */
package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.SectionVO;

/**
 * @author PS57222
 * 
 */
public interface SectionBI {
	
	public ArrayList fetchSections(SectionVO objSectionVO) throws EMDException,
	Exception;
	
	public int insertSection(SectionVO objSectionVO) throws EMDException,
	Exception;
	
	public int updateSection(SectionVO objSectionVO) throws EMDException,
	Exception;
	
	// Added for CR-4 Delete Section and Sub-Section
	public int deleteSection(SectionVO objSectionVO) throws EMDException,
	Exception;
	
}
