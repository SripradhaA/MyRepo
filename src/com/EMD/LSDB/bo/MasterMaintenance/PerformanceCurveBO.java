/**
 * 
 */
package com.EMD.LSDB.bo.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.PerformanceCurveBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.MasterMaintenance.ModelPerfCurveDAO;
import com.EMD.LSDB.vo.common.PerformanceCurveVO;

/**
 * @author VV49326
 * 
 */
public class PerformanceCurveBO implements PerformanceCurveBI {
	
	private PerformanceCurveBO() {
		
	}
	
	public static PerformanceCurveBO objPerformanceCurveBO;
	
	public static synchronized PerformanceCurveBO getInstance() {
		if (objPerformanceCurveBO == null) {
			objPerformanceCurveBO = new PerformanceCurveBO();
		}
		
		return objPerformanceCurveBO;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objModelGenArrangeVO
	 *            This object is used for UploadImage
	 * @return boolean It returns a flag true or False
	 * @throws EMDException
	 **************************************************************************/
	
	public int uploadPerfCurveImage(PerformanceCurveVO objPerformanceCurveVO)
	throws EMDException {
		LogUtil.logMessage("Enters into PerformanceCurveBO:uploadImage method");
		int intImageUploaded = 0;
		try {
			intImageUploaded = ModelPerfCurveDAO
			.uploadPerfCurveImage(objPerformanceCurveVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil.logMessage("DataAccessException in PerformanceCurveBO:"
					+ objErrorInfo.getMessage());
			intImageUploaded = Integer.parseInt(objErrorInfo.getMessageID());
			throw new BusinessException(objBusExp, objErrorInfo);
			
		} catch (Exception objExp) {
			ErrorInfo objErrorInfo = new ErrorInfo();
			LogUtil
			.logMessage("enters into catch block in PerformanceCurveBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		LogUtil.logMessage("Leaves From PerformanceCurveBO:uploadImage method");
		return intImageUploaded;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objGenArrangeVO
	 *            This object is used for Fetch Performance Curve Images
	 * @return boolean It returns ArrayList of GenArrangeVO
	 * @throws EMDException
	 **************************************************************************/
	
	public ArrayList fetchPerfCurveImages(
			PerformanceCurveVO objPerformanceCurveVO) throws EMDException {
		ArrayList arlArrayList = new ArrayList();
		LogUtil
		.logMessage("Enters into PerformanceCurveBO:fetchGenArrImages method");
		try {
			
			arlArrayList = ModelPerfCurveDAO
			.fetchPerfCurveImages(objPerformanceCurveVO);
			LogUtil
			.logMessage("Enters into PerformanceCurveBO:fetchGenArrImages method:"
					+ arlArrayList);
			
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in PerformanceCurveBO:BusinessException"
					+ objErrorInfo.getMessage());
			
			// throw new BusinessException(objBusExp,objErrorInfo);
		} catch (Exception objExp) {
			ErrorInfo objErrorInfo = new ErrorInfo();
			LogUtil
			.logMessage("enters into catch block in PerformanceCurveBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		LogUtil
		.logMessage("Leaves from PerformanceCurveBO:fetchPerformance Curve Images method");
		return arlArrayList;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objGenArrangeVO
	 *            This object is used for delete Performance Curve Images
	 * @return boolean It returns Boolean
	 * @throws EMDException
	 **************************************************************************/
	
	public int deletePerfCurveImage(PerformanceCurveVO objPerformanceCurveVO)
	throws EMDException {
		ArrayList arlArrayList = new ArrayList();
		int intDeleteImg = 0;
		LogUtil
		.logMessage("Enters into PerformanceCurveBO:delete Images method");
		try {
			
			intDeleteImg = ModelPerfCurveDAO
			.deletePerfCurveImage(objPerformanceCurveVO);
			LogUtil
			.logMessage("Enters into PerformanceCurveBO:fetchGenArrImages method:"
					+ arlArrayList);
			
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in PerformanceCurveBO:BusinessException"
					+ objErrorInfo.getMessage());
			intDeleteImg = Integer.parseInt(objErrorInfo.getMessageID());
			throw new BusinessException(objBusExp, objErrorInfo);
		} catch (Exception objExp) {
			ErrorInfo objErrorInfo = new ErrorInfo();
			LogUtil
			.logMessage("enters into catch block in PerformanceCurveBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
		}
		LogUtil
		.logMessage("Leaves from PerformanceCurveBO:fetchGenArrImages method");
		return intDeleteImg;
		
	}
	
}
