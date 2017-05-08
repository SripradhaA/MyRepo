/**
 * 
 */
package com.EMD.LSDB.bo.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.ModelPerfCurveBI;
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
public class ModelPerfCurveBO implements ModelPerfCurveBI {
	
	private ModelPerfCurveBO() {
		
	}
	
	public static ModelPerfCurveBO objPerformanceCurveBO;
	
	public static synchronized ModelPerfCurveBO getInstance() {
		if (objPerformanceCurveBO == null) {
			objPerformanceCurveBO = new ModelPerfCurveBO();
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
	throws EMDException, Exception {
		LogUtil
		.logMessage("Entering ModelPerformanceCurveBO:uploadPerfCurveImage ");
		int intImageUploaded = 0;
		try {
			intImageUploaded = ModelPerfCurveDAO
			.uploadPerfCurveImage(objPerformanceCurveVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in ModelPerformanceCurveBO:BusinessException"
					+ objErrorInfo.getMessage());
			intImageUploaded = Integer.parseInt(objErrorInfo.getMessageID());
			throw new BusinessException(objBusExp, objErrorInfo);
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelPerformanceCurveBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in ModelPerformanceCurveBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		
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
			PerformanceCurveVO objPerformanceCurveVO) throws EMDException,
			Exception {
		ArrayList arlArrayList = new ArrayList();
		LogUtil
		.logMessage("Entering ModelPerformanceCurveBO:fetchPerfCurveImages");
		try {
			
			arlArrayList = ModelPerfCurveDAO
			.fetchPerfCurveImages(objPerformanceCurveVO);
			
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in ModelPerformanceCurveBO:BusinessException"
					+ objErrorInfo.getMessage());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelPerformanceCurveBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in ModelPerformanceCurveBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		
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
	throws EMDException, Exception {
		int intDeleteImg = 0;
		LogUtil
		.logMessage("Enters into ModelPerformanceCurveBO:deletePerfCurveImage");
		try {
			
			intDeleteImg = ModelPerfCurveDAO
			.deletePerfCurveImage(objPerformanceCurveVO);
			
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in ModelPerformanceCurveBO:BusinessException"
					+ objErrorInfo.getMessage());
			intDeleteImg = Integer.parseInt(objErrorInfo.getMessageID());
			throw new BusinessException(objBusExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelPerformanceCurveBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in ModelPerformanceCurveBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		LogUtil
		.logMessage("Leaves from PerformanceCurveBO:fetchGenArrImages method");
		return intDeleteImg;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param PerformanceCurveVO
	 *            This object is used for Modify Performance Curve Image name
	 * @return int It returns int value
	 * @throws EMDException
	 **************************************************************************/
	
	public int modifyPerfCurveImageName(PerformanceCurveVO objPerformanceCurveVO)
	throws EMDException, Exception {
		int intUpdateImg = 0;
		LogUtil
		.logMessage("Enters into ModelPerformanceCurveBO:deletePerfCurveImage");
		try {
			
			intUpdateImg = ModelPerfCurveDAO
			.modifyPerfCurveImageName(objPerformanceCurveVO);
			
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in ModelPerformanceCurveBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intUpdateImg = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelPerformanceCurveBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in ModelPerformanceCurveBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		LogUtil
		.logMessage("Leaves from PerformanceCurveBO:fetchGenArrImages method");
		return intUpdateImg;
		
	}
	//Added for CR_121 for Model Performance Curve Rearrange
	
	/***************************************************************************
	 * * * * This Method is used to Rearrange Model Performance Curve 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objPerformanceCurveVO
	 *            This object is used for save Performance Curve Images
	 * @return int status of save
	 * @throws EMDException
	 **************************************************************************/

	public int saveRearrangedPerfCurve(PerformanceCurveVO objPerformanceCurveVO)
	throws EMDException, Exception {
		int intUpdateImg = 0;
		LogUtil
		.logMessage("Enters into ModelPerformanceCurveBO:saveRearrangedPerfCurve");
		try {
			
			intUpdateImg = ModelPerfCurveDAO
			.saveRearrangedPerfCurve(objPerformanceCurveVO);
			
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in ModelPerformanceCurveBO:BusinessException"
					+ objErrorInfo.getMessageID());
			intUpdateImg = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelPerformanceCurveBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in ModelPerformanceCurveBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		LogUtil
		.logMessage("Leaves from PerformanceCurveBO:saveRearrangedPerfCurve method");
		return intUpdateImg;
		
	}
}