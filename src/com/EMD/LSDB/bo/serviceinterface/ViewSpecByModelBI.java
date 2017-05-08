package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.SubSectionVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business definition methods for getting the
 *          clauses of the Model
 ******************************************************************************/
/***************************************************************************
 * ------------------------------------------------------------------------------------------------------
 *     Date         version  create by   modify by             comments                              Remarks 
 * 19/01/2010        1.0      SD41630                 Added new mehtod for view characterisitic     Added for CR_81
 * 													   group Report by model.   
 * 													 	 
 * 
 * --------------------------------------------------------------------------------------------------------
  **************************************************************************/
public interface ViewSpecByModelBI {
	public abstract ArrayList fetchSpecByModel(SubSectionVO objSubSectionVO)
	throws EMDException, Exception;
	
	public abstract ArrayList viewMasterSpecByModel(SubSectionVO objSubSectionVO)
	throws EMDException, Exception;
	
	//Change for LSDB_CR-77
	public abstract ArrayList viewCustomerOptionCatalog(ModelVo objModelVo) 
	throws EMDException, Exception;
	//CR_81 added for View charactrisitic Group Report
	public abstract ArrayList viewCharactersiticGrpRpt(SubSectionVO objSubSectionVO) 
	throws EMDException, Exception;
	
	//Added for CR_118
	
	public abstract ArrayList editCompGroupInCOC(ModelVo objModelVo) 
	throws EMDException, Exception;
	
	public abstract int updateCompGroupsInCOC(ModelVo objModelVO)
	throws EMDException, Exception;
	//Added for CR_118 Ends

	
}
