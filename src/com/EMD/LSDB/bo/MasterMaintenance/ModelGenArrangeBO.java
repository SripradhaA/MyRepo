/**
 * 
 */
package com.EMD.LSDB.bo.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.ModelGenArrangeBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.MasterMaintenance.ModelGenArrangeDAO;
import com.EMD.LSDB.vo.common.GenArrangeVO;

/**
 * @author PS57222
 * 
 */
public class ModelGenArrangeBO implements ModelGenArrangeBI {
	
	private ModelGenArrangeBO() {
		
	}
	
	public static ModelGenArrangeBO objModelGenArrangeBO;
	
	public static synchronized ModelGenArrangeBO getInstance() {
		if (objModelGenArrangeBO == null) {
			objModelGenArrangeBO = new ModelGenArrangeBO();
		}
		
		return objModelGenArrangeBO;
	}
	///modify method nema  for CR_101 From uploadimage toUpdateMdlGenArgmntView  
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objModelGenArrangeVO
	 *            This object is used for UpdateMdlGenArgmntView
	 * @return boolean It returns a flag true or False
	 * @throws EMDException
	 **************************************************************************/
	
	public int updateMdlGenArgmntViewDtls(GenArrangeVO objModelGenArrangeVO)
	throws EMDException, Exception {
		LogUtil.logMessage("Enters into ModelGenArrangeBO:updateMdlGenArgmntView method");
		int intStatusCode = 0;
		try {
			intStatusCode = ModelGenArrangeDAO
			.updateMdlGenArgmntViewDtls(objModelGenArrangeVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil.logMessage("DataAccessException in ModelGenArrangeBO:"
					+ objErrorInfo.getMessage());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelGenArrangeBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ModelGenArrangeBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		LogUtil.logMessage("Leaves From ModelGenArrangeBO:updateMdlGenArgmntView method");
		return intStatusCode;
		
	}
	
//	/modify method nema  for CR_101 From uploadimage toUpdateMdlGenArgmntView  
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objModelGenArrangeVO
	 *            This object is used for UpdateMdlGenArgmntView
	 * @return boolean It returns a flag true or False
	 * @throws EMDException
	 **************************************************************************/
	
	public int deleteMdlGenArgmtView(GenArrangeVO objModelGenArrangeVO)
	throws EMDException, Exception {
		LogUtil.logMessage("Enters into ModelGenArrangeBO:deleteMdlGenArgmtView method");
		int intStatusCode = 0;
		try {
			intStatusCode = ModelGenArrangeDAO
			.deleteMdlGenArgmtView(objModelGenArrangeVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil.logMessage("DataAccessException in ModelGenArrangeBO:"
					+ objErrorInfo.getMessage());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelGenArrangeBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ModelGenArrangeBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		LogUtil.logMessage("Leaves From ModelGenArrangeBO:deleteMdlGenArgmtView method");
		return intStatusCode;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objGenArrangeVO
	 *            This object is used for FetchGenArrImages
	 * @return boolean It returns ArrayList of GenArrangeVO
	 * @throws EMDException
	 **************************************************************************/
	
	public ArrayList fetchGenArrImages(GenArrangeVO objGenArrangeVO)
	throws EMDException, Exception {
		ArrayList arlArrayList = new ArrayList();
		LogUtil
		.logMessage("Enters into ModelGenArrangeBO:fetchGenArrImages method");
		try {
			
			arlArrayList = ModelGenArrangeDAO
			.fetchGenArrImages(objGenArrangeVO);
			
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelGenArrangeBO:BusinessException"
					+ objErrorInfo.getMessage());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelGenArrangeBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ModelGenArrangeBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		LogUtil
		.logMessage("Leaves from ModelGenArrangeBO:fetchGenArrImages method");
		return arlArrayList;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objGenArrangeVO
	 *            This object is used for ModifyGenArrNotes
	 * @return boolean It returns a Flag True or false
	 * @throws EMDException
	 **************************************************************************/
	
	public int modifyGenArrNotes(GenArrangeVO objGenArrangeVO)
	throws EMDException, Exception {
		LogUtil
		.logMessage("Enters into ModelGenArrangeBO:modifyGenArrNotes method");
		int intStatusCode = 0;
		try {
			intStatusCode = ModelGenArrangeDAO
			.modifyGenArrNotes(objGenArrangeVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelGenArrangeBO:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelGenArrangeBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil.logMessage("ENters into catch block in DAO:.."
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		LogUtil
		.logMessage("Leaves From ModelGenArrangeBO:modifyGenArrNotes method");
		
		return intStatusCode;
	}
//	/Added for CR_101 From uploadMdlGenArgmntViewDtls 
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objModelGenArrangeVO
	 *            This object is used for UpdateMdlGenArgmntView
	 * @return boolean It returns a flag true or False
	 * @throws EMDException
	 **************************************************************************/
	
	public int uploadMdlGenArgmntViewDtls(GenArrangeVO objModelGenArrangeVO)
	throws EMDException, Exception {
		LogUtil.logMessage("Enters into ModelGenArrangeBO:uploadMdlGenArgmntViewDtls method");
		int intStatusCode = 0;
		try {
			intStatusCode = ModelGenArrangeDAO
			.uploadMdlGenArgmntViewDtls(objModelGenArrangeVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil.logMessage("DataAccessException in ModelGenArrangeBO:"
					+ objErrorInfo.getMessage());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelGenArrangeBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ModelGenArrangeBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		LogUtil.logMessage("Leaves From ModelGenArrangeBO:deleteMdlGenArgmtView method");
		return intStatusCode;
		
	}
	
}
