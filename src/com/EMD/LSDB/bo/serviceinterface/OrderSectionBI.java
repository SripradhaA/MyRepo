/**
 * 
 */
package com.EMD.LSDB.bo.serviceinterface;

import java.sql.Connection;
import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.RevisionVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

/**
 * @author ps57222
 * 
 */
public interface OrderSectionBI {
	
	public ArrayList fetchOrdSections(SectionVO objSectionVO)
	throws EMDException, Exception;
	
	public ArrayList fetchSectionDetails(SectionVO objSectionVO)
	throws EMDException, Exception;
	
	public SectionVO saveComponent(SectionVO objSectionVO) throws EMDException,
	Exception;
	
	public ArrayList fetchSubSectionDetails(SubSectionVO objSubSectionVO)
	throws EMDException, Exception;
	
	public ArrayList fetchOrdSubSections(SectionVO objSectionVO)
	throws EMDException, Exception;
	
	/***************************************************************************
	 * fetchRevision Method is used to load the dropdown values from
	 * database,which is used in Modify Spec main screen. Added on 20-May-08 by
	 * ps57222
	 * 
	 */
	public ArrayList fetchRevision(RevisionVO objRevisionVO)
	throws EMDException, Exception;

	/**Added for CR-68 Order New Component To fetch Model Components & Order Components**/
	public SubSectionVO fetchComponents(SubSectionVO objSubSectionVO,
			SectionVO objSectionVO, Connection objConnection,String blnConnFlag)
	throws EMDException, Exception;
	
	//Added for CR-72 Proof Reading PDF
	public  int generateOrdrClauseNo(SectionVO objSectionVO)
	throws EMDException,Exception;
	
	public SubSectionVO fetchClauseDetails(SectionVO objSectionVO)
	throws EMDException,Exception;
	
	public  int deleteTempTabData(SectionVO objSectionVO)
	throws EMDException,Exception;

	//Added for CR_90
	public SectionVO saveRequiredComponent(SectionVO objSectionVO) throws EMDException,
	Exception;
	//Ends here
	
	//Added for CR_93
	public  int saveNewOrderComp(ComponentVO objComponentVO)
	throws EMDException,Exception;

	//Added for CR_109
	public  int addNewSubsecToOrder(SectionVO objSectionVO)
	throws EMDException,Exception;
}