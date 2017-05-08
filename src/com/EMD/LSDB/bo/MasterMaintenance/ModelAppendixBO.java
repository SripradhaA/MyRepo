package com.EMD.LSDB.bo.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.ModelAppendixBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.MasterMaintenance.ModelAppendixDAO;
import com.EMD.LSDB.vo.common.ModelAppendixVO;

public class ModelAppendixBO implements ModelAppendixBI {
	
	private ModelAppendixBO() {
		
	}
	
	public static ModelAppendixBO objModelAppendixBO;
	
	public static synchronized ModelAppendixBO getInstance() {
		if (objModelAppendixBO == null) {
			objModelAppendixBO = new ModelAppendixBO();
		}
		
		return objModelAppendixBO;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objGenArrangeVO
	 *            This object is used for Fetch Appendix Images
	 * @return boolean It returns ArrayList of ModelAppendixVO
	 * @throws EMDException
	 **************************************************************************/
	
	public ArrayList fetchAppendixImages(ModelAppendixVO objModelAppendixVO)
	throws EMDException {
		ArrayList arlArrayList = new ArrayList();
		LogUtil
		.logMessage("Enters into ModelAppendixBO:fetchAppendixImages method");
		try {
			
			arlArrayList = ModelAppendixDAO
			.fetchAppendixImages(objModelAppendixVO);
			LogUtil
			.logMessage("Enters into ModelAppendixBO:fetchAppendixImages  method:"
					+ arlArrayList);
			
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelAppendixBO:BusinessException"
					+ objErrorInfo.getMessage());
			
			// throw new BusinessException(objBusExp,objErrorInfo);
		} catch (Exception objExp) {
			ErrorInfo objErrorInfo = new ErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelAppendix:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		LogUtil
		.logMessage("Leaves from ModelAppendixBO:fetchAppendix Images method");
		return arlArrayList;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objModelGenArrangeVO
	 *            This object is used for UploadImage
	 * @return boolean It returns a flag true or False
	 * @throws EMDException
	 **************************************************************************/
	
	public int uploadAppendixImage(ModelAppendixVO objModelAppendixVO)
	throws EMDException {
		LogUtil
		.logMessage("Enters into ModelAppendixBO:uploadAppendixImage method");
		int intImageUploaded = 0;
		try {
			intImageUploaded = ModelAppendixDAO
			.uploadAppendixImage(objModelAppendixVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil.logMessage("DataAccessException in ModelAppendixBO:"
					+ objErrorInfo.getMessage());
			intImageUploaded = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (Exception objExp) {
			ErrorInfo objErrorInfo = new ErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelAppendixBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		LogUtil
		.logMessage("Leaves From ModelAppendixBO:uploadAppendixImage method");
		return intImageUploaded;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objModelGenArrangeVO
	 *            This object is used for delete Appendix image
	 * @return boolean It returns a flag true or False
	 * @throws EMDException
	 **************************************************************************/
	
	public int deleteAppendixImage(ModelAppendixVO objModelAppendixVO)
	throws EMDException {
		LogUtil
		.logMessage("Enters into ModelAppendixBO:deleteAppendixImage method");
		int intImageUploaded = 0;
		try {
			intImageUploaded = ModelAppendixDAO
			.deleteAppendixImage(objModelAppendixVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil.logMessage("DataAccessException in ModelAppendixBO:"
					+ objErrorInfo.getMessage());
			intImageUploaded = Integer.parseInt(objErrorInfo.getMessageID());
			throw new BusinessException(objBusExp, objErrorInfo);
			
		} catch (Exception objExp) {
			ErrorInfo objErrorInfo = new ErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelAppendixBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		LogUtil
		.logMessage("Leaves From ModelAppendixBO:deleteAppendixImage method");
		return intImageUploaded;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objModelGenArrangeVO
	 *            This object is used for update Appendix image
	 * @return boolean It returns a flag true or False
	 * @throws EMDException
	 **************************************************************************/
	
	public int updateAppendixImage(ModelAppendixVO objModelAppendixVO)
	throws EMDException {
		LogUtil
		.logMessage("Enters into ModelAppendixBO:updateAppendixImage method");
		int intImageUploaded = 0;
		try {
			intImageUploaded = ModelAppendixDAO
			.updateAppendixImage(objModelAppendixVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil.logMessage("DataAccessException in ModelAppendixBO:"
					+ objErrorInfo.getMessage());
			intImageUploaded = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (Exception objExp) {
			ErrorInfo objErrorInfo = new ErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelAppendixBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		LogUtil
		.logMessage("Leaves From ModelAppendixBO:updateAppendixImage method");
		return intImageUploaded;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objModelGenArrangeVO
	 *            This object is used for update Appendix image
	 * @return boolean It returns a flag true or False
	 * @throws EMDException
	 **************************************************************************/
	
	public int saveModelClaMappings(ModelAppendixVO objModelAppendixVO)
	throws EMDException {
		LogUtil
		.logMessage("Enters into ModelAppendixBO:saveModelClaMappings method");
		int intclauseInserted = 0;
		try {
			intclauseInserted = ModelAppendixDAO.saveModelClaMappings(objModelAppendixVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelAppendixBO:BusinessException"
					+ objErrorInfo.getMessage());
			intclauseInserted = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (Exception objExp) {
			ErrorInfo objErrorInfo = new ErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelAppendixBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		LogUtil
		.logMessage("Leaves From ModelAppendixBO:saveModelClaMappings method");
		return intclauseInserted;
		
	}

			
}
