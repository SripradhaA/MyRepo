package com.EMD.LSDB.bo.cr1058;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.ChangeRequest1058BI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.admn.UserMaintDAO;
import com.EMD.LSDB.dao.cr1058.ChangeRequest1058DAO;
import com.EMD.LSDB.vo.admn.UserVO;
import com.EMD.LSDB.vo.common.ChangeRequest1058VO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.SectionVO;


public class ChangeRequest1058BO implements ChangeRequest1058BI{
private ChangeRequest1058BO() {	}
public static ChangeRequest1058BO objChangeRequest1058BO;

public static synchronized ChangeRequest1058BO getInstance(){
	
	if (objChangeRequest1058BO == null) {
		objChangeRequest1058BO = new ChangeRequest1058BO();
	}
	return objChangeRequest1058BO;
}

/***************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0
 * @param objChangeRequest1058Vo
 *            the object for fetching OrderNo
 * @return ArrayList of fetching OrderNo
 * @throws EMDException
 **************************************************************************/

public ArrayList fetchCreate1058RequestOrderNo(ChangeRequest1058VO objChangeRequest1058Vo)
        throws EMDException, Exception {

LogUtil.logMessage("Enters into ChangeRequest1058BO:fetchCreate1058RequestOrderNo");
ArrayList arlOrderNoList = null;

try {
	arlOrderNoList = ChangeRequest1058DAO.fetchCreate1058RequestOrderNo(objChangeRequest1058Vo);
} catch (BusinessException objBusExp) {
	ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
	LogUtil
	.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
			+ objErrorInfo.getMessageID());
	 //intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
	
} catch (ApplicationException objAppExp) {
	ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
	LogUtil
	.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
			+ objErrorInfo.getMessage());
	throw new Exception(objErrorInfo.getMessage());
}
catch (Exception objExp) {
	LogUtil
	.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
			+ objExp.getMessage());
	throw new Exception(objExp.getMessage());
}

return arlOrderNoList;
}

/***************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0
 * @param objChangeRequest1058Vo
 *            the object for creating 1058 Request
 * @return ArrayList of creating 1058 Request
 * @throws EMDException
 **************************************************************************/

public int create1058LSDBRequest(ChangeRequest1058VO objChangeRequest1058Vo)
throws EMDException, Exception {

LogUtil.logMessage("Enters into ChangeRequest1058BO:create1058LSDBRequest");
int SeqNo1058 = 0;

try {
SeqNo1058 = ChangeRequest1058DAO.create1058LSDBRequest(objChangeRequest1058Vo);
} catch (BusinessException objBusExp) {
ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
LogUtil
.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
	+ objErrorInfo.getMessageID());
//intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());

} catch (ApplicationException objAppExp) {
ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
LogUtil
.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
	+ objErrorInfo.getMessage());
throw new Exception(objErrorInfo.getMessage());
}
catch (Exception objExp) {
LogUtil
.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
	+ objExp.getMessage());
throw new Exception(objExp.getMessage());
}

return SeqNo1058;
}
/***************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0
 * @param objChangeRequest1058Vo
 *            the object for Create NonLSDB Request
 * @return ArrayList of creating NonLSDB Request
 * @throws EMDException
 **************************************************************************/
public int create1058NonLSDBRequest(ChangeRequest1058VO objChangeRequest1058Vo)
throws EMDException, Exception {

LogUtil.logMessage("Enters into ChangeRequest1058BO:create1058NonLSDBRequest");
int SeqNo1058 = 0;

try {
SeqNo1058 = ChangeRequest1058DAO.create1058NonLSDBRequest(objChangeRequest1058Vo);
} catch (BusinessException objBusExp) {
ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
LogUtil
.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
	+ objErrorInfo.getMessageID());
//intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());

} catch (ApplicationException objAppExp) {
ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
LogUtil
.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
	+ objErrorInfo.getMessage());
throw new Exception(objErrorInfo.getMessage());
}
catch (Exception objExp) {
LogUtil
.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
	+ objExp.getMessage());
throw new Exception(objExp.getMessage());
}

return SeqNo1058;
}

/*******************************************************************************************
 * @author  	Satyam Computer Services Ltd  
 * @version 	1.0  
 * @param      	objChangeRequest1058VO    	    The object for fetch1058Details 
 * @return     	int			         			The Status for success or failure   
 * @throws     	EMDException
 ******************************************************************************************/

public ArrayList fetch1058Details(ChangeRequest1058VO objChangeRequest1058VO)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:fetch1058Details");
	ArrayList arlRequest1058List = null;
	
	try {
		arlRequest1058List = ChangeRequest1058DAO.fetch1058Details(objChangeRequest1058VO);
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
		
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return arlRequest1058List;
}

/*******************************************************************************************
 * @author  	Satyam Computer Services Ltd  
 * @version 	1.0  
 * @param      	objChangeRequest1058VO    	    The object for fetchRepresentativeDetails 
 * @return     	int			         			The Status for success or failure   
 * @throws     	EMDException
 ******************************************************************************************/

public ArrayList fetchRepresentativeDetails(UserVO objUserVO)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:fetchRepresentativeDetails");
	ArrayList arlRepresentativeList = null;
	
	try {
		arlRepresentativeList = ChangeRequest1058DAO.fetchRepresentativeDetails(objUserVO);
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
		
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return arlRepresentativeList;
}


/*******************************************************************************************
 * @author  	Satyam Computer Services Ltd  
 * @version 	1.0  
 * @param      	objChangeRequest1058VO    	     The object for insertRepresentative 
 * @return     	int			         			The Status for success or failure   
 * @throws     	EMDException
 ******************************************************************************************/
public int insertRepresentative(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception {
	
	int intReturnStatus = 0;
	try {
		LogUtil.logMessage("Entering ChangeRequest1058BO.insertRepresentative");
		intReturnStatus = ChangeRequest1058DAO.insertRepresentative(objChangeRequest1058VO);
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("Enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessage());
		intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	} catch (Exception objExp) {
		LogUtil
		.logMessage("Enters into catch block in ChangeRequest1058BO:Exception"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return intReturnStatus;
}

/*******************************************************************************************
 * @author  	Satyam Computer Services Ltd  
 * @version 	1.0  
 * @param      	objChangeRequest1058VO    	     The object for insertRepresentative 
 * @return     	int			         			The Status for success or failure   
 * @throws     	EMDException
 ******************************************************************************************/
public int complete(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception {
	
	int intComplete = 0;
	try {
		LogUtil.logMessage("Entering ChangeRequest1058BO.complete");
		intComplete = ChangeRequest1058DAO.complete(objChangeRequest1058VO);
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("Enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessage());
		intComplete = Integer.parseInt(objErrorInfo.getMessageID());
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	} catch (Exception objExp) {
		LogUtil
		.logMessage("Enters into catch block in ChangeRequest1058BO:Exception"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return intComplete;
}

/*******************************************************************************************
 * @author  	Satyam Computer Services Ltd  
 * @version 	1.0  
 * @param      	objChangeRequest1058VO    	     The object for fetch1058Status 
 * @return     	int			         			The Status for success or failure   
 * @throws     	EMDException
 ******************************************************************************************/

//Added for 1058 search
public ArrayList fetch1058Status( ChangeRequest1058VO  objChangeRequest1058VO) throws EMDException, Exception {
	ArrayList arl1058Status= null;
	try {
		LogUtil
		.logMessage("Inside the fetch1058Request method of ChangeRequest1058BO");
		arl1058Status=ChangeRequest1058DAO.fetch1058Status(objChangeRequest1058VO);
	}
	
	catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058Bo:BusinessException"
				+ objErrorInfo.getMessage());
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058Bo:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	} catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in CustomerBo:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	
	return arl1058Status;
}

/*******************************************************************************************
 * @author  	Satyam Computer Services Ltd  
 * @version 	1.0  
 * @param      	objChangeRequest1058VO    	    The object for fetchLsdbClauseDetails 
 * @return     	int			         			The Status for success or failure   
 * @throws     	EMDException
 ******************************************************************************************/

public ArrayList fetchLsdbClauseDetails(ChangeRequest1058VO objChangeRequest1058VO)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:fetchLsdbClauseDetails");
	ArrayList arlLsdbClauseList = null;
	
	try {
		arlLsdbClauseList = ChangeRequest1058DAO.fetchLsdbClauseDetails(objChangeRequest1058VO);
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
		
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return arlLsdbClauseList;
}

/*******************************************************************************************
 * @author  	Satyam Computer Services Ltd  
 * @version 	1.0  
 * @param      	objChangeRequest1058VO    	    The object for fetchNLsdbClauseDetails 
 * @return     	int			         			The Status for success or failure   
 * @throws     	EMDException
 ******************************************************************************************/

public ArrayList fetchNLsdbClauseDetails(ChangeRequest1058VO objChangeRequest1058VO)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:fetchNLsdbClauseDetails");
	ArrayList arlNLsdbClauseList = null;
	
	try {
		arlNLsdbClauseList = ChangeRequest1058DAO.fetchNLsdbClauseDetails(objChangeRequest1058VO);
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
		
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return arlNLsdbClauseList;
}

//----@cr110---@108447---start
public int addClauseNonLsdb(ChangeRequest1058VO objChangeRequest1058Vo)
		throws EMDException, Exception {

	LogUtil.logMessage("Enters into ChangeRequest1058BO:addClauseNonLsdb");
	int intAddClause = 0;
	try {
		intAddClause = ChangeRequest1058DAO.addClauseNonLsdb(objChangeRequest1058Vo);
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
				.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
						+ objErrorInfo.getMessageID());
		intAddClause = Integer.parseInt(objErrorInfo.getMessageID());

	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
				.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
						+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	} catch (Exception objExp) {
		LogUtil
				.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
						+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return intAddClause;
}

public int deleteClauseNonLsdb(ChangeRequest1058VO objChangeRequest1058Vo)
		throws EMDException, Exception {

	LogUtil.logMessage("Enters into ChangeRequest1058BO:addClauseNonLsdb");
	int intDeleteClause = 0; 
	try {
		intDeleteClause = ChangeRequest1058DAO.deleteClauseNonLsdb(objChangeRequest1058Vo);
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
				.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
						+ objErrorInfo.getMessageID());
		intDeleteClause = Integer.parseInt(objErrorInfo.getMessageID());

	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
				.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
						+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	} catch (Exception objExp) {
		LogUtil
				.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
						+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return intDeleteClause;
}

public int updateClauseNonLsdb(ChangeRequest1058VO objChangeRequest1058Vo)
		throws EMDException, Exception {

	LogUtil.logMessage("Enters into ChangeRequest1058BO:updateClauseNonLsdb");
	int intUpdateClause = 0;

	try {
		intUpdateClause = ChangeRequest1058DAO.updateClauseNonLsdb(objChangeRequest1058Vo);
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
				.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
						+ objErrorInfo.getMessageID());
		intUpdateClause = Integer.parseInt(objErrorInfo.getMessageID());

	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
				.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
						+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	} catch (Exception objExp) {
		LogUtil
				.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
						+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return intUpdateClause;
}

public ArrayList selectClauseNonLsdb(ChangeRequest1058VO objChangeRequest1058VO)
		throws EMDException, Exception {
	ArrayList arlClaList = null;
	try {
		LogUtil
				.logMessage("Inside the selectClauseNonLsdb method of ChangeRequest1058BO");
		arlClaList = ChangeRequest1058DAO
				.selectClauseNonLsdb(objChangeRequest1058VO);
	}

	catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
				.logMessage("enters into catch block in ChangeRequest1058Bo:BusinessException"
						+ objErrorInfo.getMessage());
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
				.logMessage("enters into catch block in ChangeRequest1058Bo:ApplicationException"
						+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	} catch (Exception objExp) {
		LogUtil
				.logMessage("enters into catch block in CustomerBo:ApplicationException"
						+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}

	return arlClaList;
}
//----@cr110---@108447---end

//Added by @vb106565 for CR-110 Starts here
/*******************************************************************************************
 * @author  	Satyam Computer Services Ltd  
 * @version 	1.0  
 * @param      	objChangeRequest1058VO    	     The object for System Engineer Section Details
 * @return     	int			         			The Status for success or failure   
 * @throws     	EMDException
 ******************************************************************************************/
public int sysEngrSecDetails(ChangeRequest1058VO objChangeRequest1058VO)
throws EMDException, Exception {
 
LogUtil.logMessage("Enters into ChangeRequest1058BO:sysEngrSecDetails");
int intStatus=0;
try {
intStatus =ChangeRequest1058DAO.sysEngrSecDetails(objChangeRequest1058VO);
                               
} catch (BusinessException objBusExp) {
                ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
                LogUtil
                 .logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
                		 + objErrorInfo.getMessageID());
                intStatus = Integer.parseInt(objErrorInfo.getMessageID());
} catch (ApplicationException objAppExp) {
                ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
                LogUtil
                                .logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"+ objErrorInfo.getMessage());
                throw new Exception(objErrorInfo.getMessage());
}
catch (Exception objExp) {
                LogUtil
                                .logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"+ objExp.getMessage());
                throw new Exception(objExp.getMessage());
}
return intStatus;
}
/*******************************************************************************************
 * @author  	Satyam Computer Services Ltd  
 * @version 	1.0  
 * @param      	objChangeRequest1058VO    	     The object forOperations Section Details
 * @return     	int			         			The Status for success or failure   
 * @throws     	EMDException
 ******************************************************************************************/

public int operationsSecDetails(ChangeRequest1058VO objChangeRequest1058Vo)
throws EMDException, Exception{

LogUtil.logMessage("Enters into ChangeRequest1058BO:operationsSecDetails");
int intStatus=0;
try {
	intStatus=ChangeRequest1058DAO.operationsSecDetails(objChangeRequest1058Vo);
 	
} catch (BusinessException objBusExp) {
	ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
	LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"+ objErrorInfo.getMessageID());
	intStatus = Integer.parseInt(objErrorInfo.getMessageID());
} catch (ApplicationException objAppExp) {
	ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
	LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"+ objErrorInfo.getMessage());
	throw new Exception(objErrorInfo.getMessage());
}
catch (Exception objExp) {
	LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"+ objExp.getMessage());
	throw new Exception(objExp.getMessage());
}
return intStatus;
}
/*******************************************************************************************
 * @author  	Satyam Computer Services Ltd  
 * @version 	1.0  
 * @param      	objChangeRequest1058VO    	     The object for Finance Section Details 
 * @return     	int			         			The Status for success or failure   
 * @throws     	EMDException
 ******************************************************************************************/

public int financeSecDetails(ChangeRequest1058VO objChangeRequest1058Vo)
throws EMDException, Exception {

LogUtil.logMessage("Enters into ChangeRequest1058BO:financeSecDetails");
int intStatus=0;
try {
	intStatus=ChangeRequest1058DAO.financeSecDetails(objChangeRequest1058Vo);
 	
} catch (BusinessException objBusExp) {
	ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
	LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"+ objErrorInfo.getMessageID());
	intStatus = Integer.parseInt(objErrorInfo.getMessageID());
} catch (ApplicationException objAppExp) {
	ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
	LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"+ objErrorInfo.getMessage());
	throw new Exception(objErrorInfo.getMessage());
}
catch (Exception objExp) {
	LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"+ objExp.getMessage());
	throw new Exception(objExp.getMessage());
}
return intStatus;
}
/*******************************************************************************************
 * @author  	Satyam Computer Services Ltd  
 * @version 	1.0  
 * @param      	objChangeRequest1058VO    	     The object for Program Manager Section Details 
 * @return     	int			         			The Status for success or failure   
 * @throws     	EMDException
 ******************************************************************************************/


public int programMgrSecDetails(ChangeRequest1058VO objChangeRequest1058Vo)
throws EMDException, Exception {

LogUtil.logMessage("Enters into ChangeRequest1058BO:programMgrSecDetails");
int intStatus=0;

try {
intStatus=ChangeRequest1058DAO.programMgrSecDetails(objChangeRequest1058Vo);
 	
} catch (BusinessException objBusExp) {
	ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
	LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"+ objErrorInfo.getMessageID());
	intStatus = Integer.parseInt(objErrorInfo.getMessageID());
} catch (ApplicationException objAppExp) {
	ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
	LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"+ objErrorInfo.getMessage());
	throw new Exception(objErrorInfo.getMessage());
}
catch (Exception objExp) {
	LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"+ objExp.getMessage());
	throw new Exception(objExp.getMessage());
}
return  intStatus;
}

/*******************************************************************************************
 * @author  	Satyam Computer Services Ltd  
 * @version 	1.0  
 * @param      	objChangeRequest1058VO    	     The object for Proposal Manager Section Details 
 * @return     	int			         			The Status for success or failure   
 * @throws     	EMDException
 ******************************************************************************************/

public int proposalMgrSecDetails(ChangeRequest1058VO objChangeRequest1058Vo)
throws EMDException, Exception {

LogUtil.logMessage("Enters into ChangeRequest1058BO:proposalMgrSecDetails");
int intStatus=0;
try {
	intStatus=ChangeRequest1058DAO.proposalMgrSecDetails(objChangeRequest1058Vo);
 	
} catch (BusinessException objBusExp) {
	ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
	LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"+ objErrorInfo.getMessageID());
	intStatus = Integer.parseInt(objErrorInfo.getMessageID());
} catch (ApplicationException objAppExp) {
	ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
	LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"+ objErrorInfo.getMessage());
	throw new Exception(objErrorInfo.getMessage());
}
catch (Exception objExp) {
	LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"+ objExp.getMessage());
	throw new Exception(objExp.getMessage());
}
return intStatus;
}
//Added by @vb106565 for CR-110 Ends here

//Added by @rr108354 for CR-110 Ends here

public ArrayList fetch1058RequestCategories(LoginVO objLoginVo)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:fetch1058RequestCategories");
	ArrayList arlCategories = null;
	
	
	

	try {
		arlCategories = ChangeRequest1058DAO.fetch1058RequestCategories(objLoginVo);
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
		
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	
	
	return arlCategories;
}


public ArrayList fetch1058RequestTypes(LoginVO objLoginVo)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:fetch1058RequestTypes");
	ArrayList arlRequestTypes = null;
	
	
	

	try {
		arlRequestTypes = ChangeRequest1058DAO.fetch1058RequestTypes(objLoginVo);
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
		
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	
	
	return arlRequestTypes;
}


public ArrayList fetch1058ClauseChangeTypes(LoginVO objLoginVo)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:fetch1058ClauseChangeTypes");
	ArrayList arlClauseChangeTypes = null;
	
	
	

	try {
		arlClauseChangeTypes = ChangeRequest1058DAO.fetch1058ClauseChangeTypes(objLoginVo);
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
		
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	
	
	return arlClauseChangeTypes;
}




/***************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0
 * @param objChangeRequest1058VO
 *            The object for uploading and fetching attachment of 1058 Request
 * @return Arraylist It has Arraylist of objChangeRequest1058AttachmentsVO
 * @throws EMDException
 **************************************************************************/

public ArrayList uploadAttachment(ChangeRequest1058VO objChangeRequest1058VO)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:uploadAttachment");
	ArrayList arlAttachmentReturned=null;

	try {
		arlAttachmentReturned = ChangeRequest1058DAO.uploadAttachment(objChangeRequest1058VO);
		
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return arlAttachmentReturned;
	
}


/***************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0
 * @param objChangeRequest1058VO
 *            The object for deleting and fetching attachment of 1058 Request
 * @return Arraylist It has Arraylist of objChangeRequest1058AttachmentsVO
 * @throws EMDException
 **************************************************************************/

public ArrayList deleteAttachment(ChangeRequest1058VO objChangeRequest1058VO)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:deleteAttachment");
	ArrayList arlAttachmentReturned=null;
	try {
		arlAttachmentReturned = ChangeRequest1058DAO.deleteAttachment(objChangeRequest1058VO);
		
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
			
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return arlAttachmentReturned;
}

public ArrayList fetchComponentGrpMap1058(ComponentVO objComponentVO)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:fetchComponentGrpMap1058");
	ArrayList arlCompGrpList=null;
	try {
		
		arlCompGrpList = ChangeRequest1058DAO.fetchComponentGrpMap1058(objComponentVO);
		
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
			
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return arlCompGrpList;
}

public ArrayList fetchOrderSections1058(SectionVO objSectionVo)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:fetchOrderSections1058");
	ArrayList arlSectionList=null;
	try {
		arlSectionList = ChangeRequest1058DAO.fetchOrderSections1058(objSectionVo);
		
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
			
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return arlSectionList;
}

public ArrayList fetchOrderSubSections1058(SectionVO objSectionVo)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:fetchOrderSubSections1058");
	ArrayList arlSubSectionList=null;
	try {
		arlSubSectionList = ChangeRequest1058DAO.fetchOrderSubSections1058(objSectionVo);
		
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
			
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return arlSubSectionList;
}

public int saveClause(ClauseVO objClauseVO)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:saveClause");
	int insertStatus =0;
	try {
		insertStatus = ChangeRequest1058DAO.saveClause(objClauseVO);
		
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		insertStatus = Integer.parseInt(objErrorInfo.getMessageID());	
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return insertStatus;
}


public int saveDeleteClause(ClauseVO objClauseVO)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:saveDeleteClause");
	int insertStatus =0;
	try {
		insertStatus = ChangeRequest1058DAO.saveDeleteClause(objClauseVO);
		
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		insertStatus = Integer.parseInt(objErrorInfo.getMessageID());	
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return insertStatus;
}


public int deleteSelectedClause(ChangeRequest1058VO objChangeRequest1058VO)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:deleteSelectedClause");
	int deleteStatus =0;
	try {
		deleteStatus = ChangeRequest1058DAO.deleteSelectedClause(objChangeRequest1058VO);
		
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		deleteStatus = Integer.parseInt(objErrorInfo.getMessageID());	
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return deleteStatus;
}


public ArrayList reviseSelectedClause(ChangeRequest1058VO objChangeRequest1058VO)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:reviseSelectedClause");
	LogUtil.logMessage("Sequence number in BO -- reviseSelectedClause"+objChangeRequest1058VO.getSeqNo1058());
	ArrayList arlReviseClauseDetails = null;
	try {
		arlReviseClauseDetails = ChangeRequest1058DAO.reviseSelectedClause(objChangeRequest1058VO);
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	
	
	return arlReviseClauseDetails;
}

public int updateClause(ClauseVO objClauseVO)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:updateClause");
	int updateStatus =0;
	try {
		updateStatus = ChangeRequest1058DAO.updateClause(objClauseVO);
		
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		updateStatus = Integer.parseInt(objErrorInfo.getMessageID());	
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return updateStatus;
}


public int updateDeleteClause(ClauseVO objClauseVO)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:updateDeleteClause");
	int insertStatus =0;
	try {
		insertStatus = ChangeRequest1058DAO.updateDeleteClause(objClauseVO);
		
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		insertStatus = Integer.parseInt(objErrorInfo.getMessageID());			
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return insertStatus;
}



public int updateClausesToSpec(ChangeRequest1058VO objChangeRequest1058VO)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:updateClausesToSpec");
	int updateStatus =0;
	try {
		updateStatus = ChangeRequest1058DAO.updateClausesToSpec(objChangeRequest1058VO);
		
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		updateStatus = Integer.parseInt(objErrorInfo.getMessageID());	
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return updateStatus;
}

//Added by @rr108354 for CR-110 Ends here

public int update1058details(ChangeRequest1058VO objChangeRequest1058VO)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:update1058details");
	int updateStatus =0;
	try {
		updateStatus = ChangeRequest1058DAO.update1058details(objChangeRequest1058VO);
		
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		updateStatus = Integer.parseInt(objErrorInfo.getMessageID());	
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return updateStatus;
}

public int insert1058ActionRecord(ChangeRequest1058VO objChangeRequest1058VO)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:insert1058ActionRecord");
	int updateStatus =0;
	try {
		updateStatus = ChangeRequest1058DAO.insert1058ActionRecord(objChangeRequest1058VO);
		
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		updateStatus = Integer.parseInt(objErrorInfo.getMessageID());	
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return updateStatus;
}

//Added for CR-117
public int uploadLegacyReport(ChangeRequest1058VO objChangeRequest1058VO)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:uploadLegacyReport");
	int intUploadLegacyReportStatus =0;
	try {
		intUploadLegacyReportStatus = ChangeRequest1058DAO.uploadLegacyReport(objChangeRequest1058VO);
		
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		intUploadLegacyReportStatus = Integer.parseInt(objErrorInfo.getMessageID());	
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return intUploadLegacyReportStatus;
}

public int delete1058Request(ChangeRequest1058VO objChangeRequest1058VO)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:delete1058Request");
	int intDelete1058Request =0;
	try {
		intDelete1058Request = ChangeRequest1058DAO.delete1058Request(objChangeRequest1058VO);
		
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		intDelete1058Request = Integer.parseInt(objErrorInfo.getMessageID());	
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return intDelete1058Request;
}
//Added for CR-117 Ends here

//Added for CR-120 
/*******************************************************************************************
 * @author  	Satyam Computer Services Ltd  
 * @version 	1.0  
 * @param      	objChangeRequest1058VO    	    The object for fetchIssuedByUserlist 
 * @return     	int			         			The Status for success or failure   
 * @throws     	EMDException
 ******************************************************************************************/

public ArrayList fetchIssuedByUserList(UserVO objUserVO)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:fetchIssuedByUserList");
	ArrayList arlFetchIssuedByUserList = null;
	
	try {
		arlFetchIssuedByUserList = ChangeRequest1058DAO.fetchIssuedByUserList(objUserVO);
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
		
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return arlFetchIssuedByUserList;
}
//Added for CR-120 Ends here

//Added for CR-126
public int updateLegacyReport(ChangeRequest1058VO objChangeRequest1058VO)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:updateLegacyReport");
	int intUpdateLegacyReportStatus =0;
	try {
		intUpdateLegacyReportStatus = ChangeRequest1058DAO.updateLegacyReport(objChangeRequest1058VO);
		
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		intUpdateLegacyReportStatus = Integer.parseInt(objErrorInfo.getMessageID());	
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return intUpdateLegacyReportStatus;
}

public ArrayList fetch1058ReportDetails(ChangeRequest1058VO objChangeRequest1058VO)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:fetch1058ReportDetails");
	ArrayList arlReportDetails= null;
	
	try {
		arlReportDetails = ChangeRequest1058DAO.fetch1058ReportDetails(objChangeRequest1058VO);
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
		
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return arlReportDetails;
}
//Added for CR-126 Ends here

//Added for CR-127 starts here
public ArrayList fetch1058PendingReportDetails(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception {
	LogUtil.logMessage("Enters into ChangeRequest1058BO:fetch1058PendingReportDetails");
	ArrayList arlReportDetails= null;
	
	try {
		arlReportDetails = ChangeRequest1058DAO.fetch1058PendingReportDetails(objChangeRequest1058VO);
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
		
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return arlReportDetails;
}


public ArrayList fetch1058MdlClaChanges(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception {
	LogUtil.logMessage("Enters into ChangeRequest1058BO:fetch1058MdlClaChanges");
	ArrayList arlReportDetails= null;
	
	try {
		arlReportDetails = ChangeRequest1058DAO.fetch1058MdlClaChanges(objChangeRequest1058VO);
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
		
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return arlReportDetails;
}

public int importModelClaChanges(ChangeRequest1058VO objChangeRequest1058VO)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:importModelClaChanges");
	int updateStatus =0;
	try {
		updateStatus = ChangeRequest1058DAO.importModelClaChanges(objChangeRequest1058VO);
		
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		updateStatus = Integer.parseInt(objErrorInfo.getMessageID());	
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return updateStatus;
}
//Added for CR-127 ends here
//Added  for CR-130 Starts
public ArrayList fetchSubSectionList(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception {
	LogUtil.logMessage("Enters into ChangeRequest1058BO:fetchSubSectionList");
	ArrayList arlImportSubSecList = null;
	
	try {
		arlImportSubSecList = ChangeRequest1058DAO.fetchSubSectionList(objChangeRequest1058VO);
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
		
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return arlImportSubSecList;
}

public int importModelSubSectionChanges(ChangeRequest1058VO objChangeRequest1058VO)
throws EMDException, Exception {
	
	LogUtil.logMessage("Enters into ChangeRequest1058BO:importModelSubSectionChanges");
	int updateStatus =0;
	try {
		updateStatus = ChangeRequest1058DAO.importModelSubSectionChanges(objChangeRequest1058VO);
		
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		updateStatus = Integer.parseInt(objErrorInfo.getMessageID());	
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return updateStatus;
}

public ArrayList fetch1058SubSectionList(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception {
	LogUtil.logMessage("Enters into ChangeRequest1058BO:fetch1058SubSectionList");
	ArrayList arl1058SubSecList = null;
	
	try {
		arl1058SubSecList = ChangeRequest1058DAO.fetch1058SubSectionList(objChangeRequest1058VO);
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
		
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return arl1058SubSecList;
}
//Added for CR-130 ends here

//Added for CR-134 starts here
public ArrayList fetch1058TransitionReportDetails(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception {
	LogUtil.logMessage("Enters into ChangeRequest1058BO:fetch1058TransitionReportDetails");
	ArrayList arlReportDetails= null;
	
	try {
		arlReportDetails = ChangeRequest1058DAO.fetch1058TransitionReportDetails(objChangeRequest1058VO);
	} catch (BusinessException objBusExp) {
		ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:BusinessException"
				+ objErrorInfo.getMessageID());
		// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
		
	} catch (ApplicationException objAppExp) {
		ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objErrorInfo.getMessage());
		throw new Exception(objErrorInfo.getMessage());
	}
	
	catch (Exception objExp) {
		LogUtil
		.logMessage("enters into catch block in ChangeRequest1058BO:ApplicationException"
				+ objExp.getMessage());
		throw new Exception(objExp.getMessage());
	}
	return arlReportDetails;
}


}
	