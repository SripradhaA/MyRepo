/*
 * Created on Apr 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.bo.CRForm;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.ChangeRequestBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.CRForm.ChangeRequestDAO;
import com.EMD.LSDB.vo.common.RequestModelVO;
import com.EMD.LSDB.vo.common.RequestVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for the Change Request
 ******************************************************************************/

public class ChangeRequestBO implements ChangeRequestBI {
	
	public static ChangeRequestBO objChangeRequestBO = null;
	
	public synchronized static ChangeRequestBO getInstance() {
		
		if (objChangeRequestBO == null) {
			objChangeRequestBO = new ChangeRequestBO();
		}
		return objChangeRequestBO;
	}
	
	private ChangeRequestBO() {
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objRequestVO
	 *            the object for fetching Status
	 * @return ArrayList of status
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchStatus(RequestVO objRequestVO) throws EMDException,
	Exception {
		try {
			LogUtil
			.logMessage("Inside the fetchSpecTypes method of ChangeRequestBO");
			return ChangeRequestDAO.fetchStatus(objRequestVO);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objModelVO
	 *            the object for Creating a new request ID
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public int createRequestID(RequestVO objRequestVO) throws EMDException,
	Exception {
		
		int intReturnStatus = 0;
		
		try {
			LogUtil.logMessage("Entering ChangeRequestBO.insertModel");
			intReturnStatus = ChangeRequestDAO.createRequestID(objRequestVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objRequestVO
	 *            The object for fetch Request Details
	 * @return Arraylist It has Arraylist of objAppendixVO
	 * @throws EMDException
	 *             This Method is used to fetch the Appendix Images in
	 *             orderlevel based on the order key and datalocation type.
	 **************************************************************************/
	public ArrayList fetchRequestDetails(RequestVO objRequestVO)
	throws EMDException, Exception {
		LogUtil.logMessage("Inside ChangeRequestBO:fetchImage");
		ArrayList objRequestList = null;
		try {
			objRequestList = ChangeRequestDAO.fetchRequestDetails(objRequestVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:BusinessException"
					+ objErrorInfo.getMessageID());
			// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return objRequestList;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param RequestVO
	 *            The object for fetch Request Model Details
	 * @return Arraylist It has Arraylist of RequestVO
	 * @throws EMDException
	 * 
	 **************************************************************************/
	public RequestModelVO fetchReqModelDetails(RequestVO objRequestVO)
	throws EMDException, Exception {
		LogUtil.logMessage("Inside ChangeRequestBO:fetchReqModelDetails");
		RequestModelVO objRequestModelVO = null;
		try {
			objRequestModelVO = ChangeRequestDAO
			.fetchReqModelDetails(objRequestVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:BusinessException"
					+ objErrorInfo.getMessageID());
			// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return objRequestModelVO;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param RequestVO
	 *            The object for fetch Request Model Comp Grp & Comp Details
	 * @return Arraylist It has Arraylist of RequestVO
	 * @throws EMDException
	 * 
	 **************************************************************************/
	public RequestModelVO fetchReqCompGrpDetails(RequestVO objRequestVO)
	throws EMDException, Exception {
		LogUtil.logMessage("Inside ChangeRequestBO:fetchReqCompGrpDetails");
		RequestModelVO objRequestModelVO = null;
		try {
			objRequestModelVO = ChangeRequestDAO
			.fetchReqCompGrpDetails(objRequestVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:BusinessException"
					+ objErrorInfo.getMessageID());
			// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return objRequestModelVO;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param RequestVO
	 *            The object for fetch Request Clause Details
	 * @return Arraylist It has Arraylist of RequestVO
	 * @throws EMDException
	 * 
	 **************************************************************************/
	public RequestModelVO fetchReqClauseDetails(RequestVO objRequestVO)
	throws EMDException, Exception {
		LogUtil.logMessage("Inside ChangeRequestBO:fetchReqClauseDetails");
		RequestModelVO objRequestModelVO = null;
		try {
			objRequestModelVO = ChangeRequestDAO
			.fetchReqClauseDetails(objRequestVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:BusinessException"
					+ objErrorInfo.getMessageID());
			// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return objRequestModelVO;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objRequestVO
	 *            The object for fetch Request Details
	 * @return Arraylist It has Arraylist of objAppendixVO
	 * @throws EMDException
	 *             This Method is used to fetch the Appendix Images in
	 *             orderlevel based on the order key and datalocation type.
	 **************************************************************************/
	public int insertClaReqDetails(RequestModelVO objRequestModelVO)
	throws EMDException, Exception {
		LogUtil.logMessage("Inside ChangeRequestBO:insertClaReqDetails");
		int intStatusCode = 0;
		try {
			intStatusCode = ChangeRequestDAO
			.insertClaReqDetails(objRequestModelVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intStatusCode;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objRequestModelVO
	 * @return the status
	 * @throws EMDException
	 *             This Method is used to save Component Group and Component
	 *             Info
	 **************************************************************************/
	public int insertCompGrpReqDetails(RequestModelVO objRequestModelVO)
	throws EMDException, Exception {
		LogUtil.logMessage("Inside ChangeRequestBO:insertCompGrpReqDetails");
		int intStatusCode = 0;
		try {
			intStatusCode = ChangeRequestDAO
			.insertCompGrpReqDetails(objRequestModelVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intStatusCode;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objRequestModelVO
	 * @return the status
	 * @throws EMDException
	 *             This Method is used to delete the request
	 **************************************************************************/
	public int resetRequest(RequestVO objRequestVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Inside ChangeRequestBO:resetRequest");
		int intStatusCode = 0;
		try {
			intStatusCode = ChangeRequestDAO.resetRequest(objRequestVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intStatusCode;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objRequestModelVO
	 * @return the status
	 * @throws EMDException
	 *             This Method is used to update the request status
	 **************************************************************************/
	public int saveRequestStatus(RequestVO objRequestVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Inside ChangeRequestBO:saveRequestStatus");
		int intStatusCode = 0;
		try {
			intStatusCode = ChangeRequestDAO.saveRequestStatus(objRequestVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intStatusCode;
		
	}

	//Added for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objRequestVO
	 * @return ArrayList of Clauses Effected
	 * @throws EMDException
	 *             This Method is used to fetch the Effected Clauses
	 **************************************************************************/
	public ArrayList fetchEffectedClauses(RequestVO objRequestVO) throws EMDException,
	Exception	{
		LogUtil.logMessage("Inside ChangeRequestBO:fetchEffectedClauses");
		ArrayList objClauseList = null;
		try {
			objClauseList = ChangeRequestDAO.fetchEffectedClauses(objRequestVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:BusinessException"
					+ objErrorInfo.getMessageID());
			//intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return objClauseList;
		
	}

	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objRequestModelVO
	 * @return the status
	 * @throws EMDException
	 *             This Method is used to update the request status
	 **************************************************************************/
	public int reAssignChangeRequest(RequestVO objRequestVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Inside ChangeRequestBO:reAssignChangeRequest");
		int intStatusCode = 0;
		try {
			intStatusCode = ChangeRequestDAO.reAssignChangeRequest(objRequestVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ChangeRequestBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intStatusCode;
		
	}

	//Added for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151 - Ends Here

}