/*
 * Created on Aug 14, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.CompGroupVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business definition methods for the Component
 *          Groups
 ******************************************************************************/
public interface ModelCompGroupBI {
	
	public ArrayList fetchCompGroups(CompGroupVO objCompGroupVO)
	throws EMDException, Exception;
	
	public int insertCompGroup(CompGroupVO objCompGroupVO) throws EMDException,
	Exception;
	
	public int updateCompGroup(CompGroupVO objCompGroupVO) throws EMDException,
	Exception;
	
	//Added for LSDB_CR_67 by ka57588
	public int deleteCompGroup(CompGroupVO objCompGroupVO) throws EMDException,
	Exception;
	
	// Added for CR-26 Component Group Component Report
	public ArrayList fetchCompGrpReport(CompGroupVO objCompGrpVO)
	throws EMDException, Exception;
	
	//Added For CR_81 on 24-Dec-09 by RR68151
	public ArrayList fetchCompGrpTypes(CompGroupVO objCompGroupVo)
	throws EMDException, Exception;
	
	//Added for CR-121
	public ArrayList viewCompInOrdMap(CompGroupVO objCompGroupVo)
	throws EMDException, Exception;
	
	public ArrayList viewCompGrpInOrdMap(CompGroupVO objCompGroupVo)
	throws EMDException, Exception;
}
