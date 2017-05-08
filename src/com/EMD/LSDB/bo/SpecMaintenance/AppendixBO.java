/*
 * Created on May 5, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.bo.SpecMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.AppendixBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.SpecMaintenance.AppendixDAO;
import com.EMD.LSDB.vo.common.AppendixVO;

/**
 * @author PS57222
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AppendixBO implements AppendixBI {
	private AppendixBO() {
		
	}
	
	public static AppendixBO objAppendixBO;
	
	public static synchronized AppendixBO getInstance() {
		
		if (objAppendixBO == null) {
			objAppendixBO = new AppendixBO();
		}
		return objAppendixBO;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objAppendixVO
	 *            The object for fetch Appendix Images in OrderLevel
	 * @return Arraylist It has Arraylist of objAppendixVO
	 * @throws EMDException
	 *             This Method is used to fetch the Appendix Images in
	 *             orderlevel based on the order key and datalocation type.
	 **************************************************************************/
	public ArrayList fetchImage(AppendixVO objAppendixVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Inside AppendixBO:fetchImage");
		ArrayList objImageList = null;
		try {
			objImageList = AppendixDAO.fetchImage(objAppendixVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in AppendixBO:BusinessException"
					+ objErrorInfo.getMessageID());
			// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in AppendixBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in AppendixBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return objImageList;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objAppendixVO
	 *            The object for add Appendix Images at OrderLevel
	 * @return Returns an integer value with the status of the transaction. It
	 *         returns the LSDBERROR code from oracle if image name already
	 *         exists in the database.
	 * @throws EMDException
	 *             This Method is used to add Appendix Images in orderlevel
	 *             based on the order key and datalocation type.
	 **************************************************************************/
	public int addImage(AppendixVO objAppendixVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Inside AppendixBO:addImage");
		int intStatusCode = 0;
		try {
			intStatusCode = AppendixDAO.addImage(objAppendixVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in AppendixBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in AppendixBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in AppendixBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intStatusCode;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objAppendixVO
	 *            The object for modify Appendix Image Name and Description at
	 *            OrderLevel
	 * @return Returns an integer value with the status of the transaction. It
	 *         returns the LSDBERROR code from oracle if image name already
	 *         exists in the database.
	 * @throws EMDException
	 *             This Method is used to modify the Appendix Image Name at
	 *             orderlevel based on the order key and datalocation type.
	 **************************************************************************/
	public int modifyImageName(AppendixVO objAppendixVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Inside AppendixBO:modifyImageName");
		int intStatusCode = 0;
		try {
			intStatusCode = AppendixDAO.modifyImageName(objAppendixVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in AppendixBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in AppendixBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in AppendixBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intStatusCode;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objAppendixVO
	 *            The object for modify Appendix Image Name and Description at
	 *            OrderLevel
	 * @return returns an integer value with the status of the transaction.
	 * @throws EMDException
	 *             This Method is used to delete the Appendix Image at
	 *             orderlevel based on the imageSeqno,order key and datalocation
	 *             type.
	 **************************************************************************/
	public int deleteImage(AppendixVO objAppendixVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Inside AppendixBO:deleteImage");
		int intStatusCode = 0;
		try {
			intStatusCode = AppendixDAO.deleteImage(objAppendixVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in AppendixBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in AppendixBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in AppendixBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intStatusCode;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objAppendixVO
	 *            The object for modify Appendix Image Name and Description at
	 *            OrderLevel
	 * @return returns an integer value with the status of the transaction.
	 * @throws EMDException
	 *             This Method is used to delete the Appendix Image at
	 *             orderlevel based on the imageSeqno,order key and datalocation
	 *             type.
	 **************************************************************************/
	public int saveMappings(AppendixVO objAppendixVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Inside AppendixBO:saveMappings");
		int intStatusCode = 0;
		try {
			intStatusCode = AppendixDAO.saveMappings(objAppendixVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in AppendixBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in AppendixBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in AppendixBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intStatusCode;
		
	}
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objAppendixVO
	 *            This object is used for Fetch Appendix Images
	 * @return boolean It returns ArrayList of AppendixVO
	 * @throws EMDException
	 **************************************************************************/
	
	public ArrayList fetchModelAppendixImages(AppendixVO objAppendixVO)
	throws EMDException {
		ArrayList arlArrayList = new ArrayList();
		LogUtil
		.logMessage("Enters into AppendixBO:fetchModelAppendixImages method");
		try {
			
			arlArrayList = AppendixDAO
			.fetchModelAppendixImages(objAppendixVO);
			LogUtil
			.logMessage("Enters into AppendixBO:fetchModelAppendixImages method:"
					+ arlArrayList);
			
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in AppendixBO:BusinessException"
					+ objErrorInfo.getMessage());
			
			// throw new BusinessException(objBusExp,objErrorInfo);
		} catch (Exception objExp) {
			ErrorInfo objErrorInfo = new ErrorInfo();
			LogUtil
			.logMessage("enters into catch block in Appendix:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		LogUtil
		.logMessage("Leaves from AppendixBO:fetchModelAppendixImages method");
		return arlArrayList;
		
	}
}