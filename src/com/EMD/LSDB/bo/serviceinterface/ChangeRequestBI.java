/*
 * Created on Jun 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.RequestModelVO;
import com.EMD.LSDB.vo.common.RequestVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business definition methods for the Spec Type
 ******************************************************************************/

public interface ChangeRequestBI {
	
	public int createRequestID(RequestVO objRequestVO) throws EMDException,
	Exception;
	
	public ArrayList fetchRequestDetails(RequestVO objRequestVO)
	throws EMDException, Exception;
	
	public RequestModelVO fetchReqModelDetails(RequestVO objRequestVO)
	throws EMDException, Exception;
	
	public RequestModelVO fetchReqCompGrpDetails(RequestVO objRequestVO)
	throws EMDException, Exception;
	
	public RequestModelVO fetchReqClauseDetails(RequestVO objRequestVO)
	throws EMDException, Exception;
	
	public int insertClaReqDetails(RequestModelVO objRequestModelVO)
	throws EMDException, Exception;
	
	public int insertCompGrpReqDetails(RequestModelVO objRequestModelVO)
	throws EMDException, Exception;
	
	public int resetRequest(RequestVO objRequestVO) throws EMDException,
	Exception;
	
	public int saveRequestStatus(RequestVO objRequestVO) throws EMDException,
	Exception;
	
	public ArrayList fetchStatus(RequestVO objRequestVO) throws EMDException,
	Exception;
	
	//Added for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151
	public ArrayList fetchEffectedClauses(RequestVO objRequestVO) throws EMDException,
	Exception;	
	
	public int reAssignChangeRequest(RequestVO objRequestVO) throws EMDException,
	Exception;
	//Added for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151 - Ends Here
}