package com.EMD.LSDB.bo.SpecMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.OrderPerfCurveBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.SpecMaintenance.OrderPerfCurveDAO;
import com.EMD.LSDB.vo.common.PerformanceCurveVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for the OrderPerfCurve
 ******************************************************************************/
public class OrderPerfCurveBO implements OrderPerfCurveBI {
	
	private OrderPerfCurveBO() {
		
	}
	
	public static OrderPerfCurveBO objOrderPerfCurveBO;
	
	public static synchronized OrderPerfCurveBO getInstance() {
		if (objOrderPerfCurveBO == null) {
			objOrderPerfCurveBO = new OrderPerfCurveBO();
		}
		
		return objOrderPerfCurveBO;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objPerformanceCurveVO
	 *            the object for fetching PerfCurveImages
	 * @return ArrayList containing the image details
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchPerfCurveImages(
			PerformanceCurveVO objPerformanceCurveVO) throws EMDException,
			Exception {
		
		ArrayList arlArrayList = new ArrayList();
		LogUtil
		.logMessage("Enters into PerformanceCurveBO:fetchGenArrImages method");
		try {
			
			arlArrayList = OrderPerfCurveDAO
			.fetchPerfCurveImages(objPerformanceCurveVO);
			LogUtil
			.logMessage("Enters into OrderPerformanceCurveBO:fetchGenArrImages method:"
					+ arlArrayList);
			
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderPerformanceCurveBO:BusinessException"
					+ objErrorInfo.getMessage());
			
			// throw new BusinessException(objBusExp,objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderPerformanceCurveBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderPerformanceCurveBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		LogUtil
		.logMessage("Leaves from OrderPerformanceCurveBO:fetchPerformance Curve Images method");
		return arlArrayList;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objPerformanceCurveVO
	 *            the object for uploading PerfCurveImages
	 * @return int containing the result of updation
	 * @throws EMDException
	 **************************************************************************/
	public int uploadPerfCurveImage(PerformanceCurveVO objPerformanceCurveVO)
	throws EMDException, Exception {
		
		LogUtil
		.logMessage("Enters into OrderPerformanceCurveBO:uploadImage method");
		int intImageUploaded = 0;
		try {
			intImageUploaded = OrderPerfCurveDAO
			.uploadPerfCurveImage(objPerformanceCurveVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("DataAccessException in OrderPerformanceCurveBO:"
					+ objErrorInfo.getMessage());
			intImageUploaded = Integer.parseInt(objErrorInfo.getMessageID());
			throw new BusinessException(objBusExp, objErrorInfo);
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderPerformanceCurveBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderPerformanceCurveBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		LogUtil
		.logMessage("Leaves From OrderPerformanceCurveBO:uploadImage method");
		return intImageUploaded;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objPerformanceCurveVO
	 *            the object for deleting PerfCurveImages
	 * @return int containing the result of deletion
	 * @throws EMDException
	 **************************************************************************/
	public int deletePerfCurveImage(PerformanceCurveVO objPerformanceCurveVO)
	throws EMDException, Exception {
		
		ArrayList arlArrayList = new ArrayList();
		int intDeleteImg = 0;
		LogUtil
		.logMessage("Enters into OrderPerformanceCurveBO:delete Images method");
		try {
			
			intDeleteImg = OrderPerfCurveDAO
			.deletePerfCurveImage(objPerformanceCurveVO);
			LogUtil
			.logMessage("Enters into OrderPerformanceCurveBO:fetchGenArrImages method:"
					+ arlArrayList);
			
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderPerformanceCurveBO:BusinessException"
					+ objErrorInfo.getMessage());
			intDeleteImg = Integer.parseInt(objErrorInfo.getMessageID());
			throw new BusinessException(objBusExp, objErrorInfo);
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderPerformanceCurveBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderPerformanceCurveBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		LogUtil
		.logMessage("Leaves from OrderPerformanceCurveBO:DeletePerfCurveImage method");
		return intDeleteImg;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objPerformanceCurveVO
	 *            the object for upadte PerfCurve Image Name
	 * @return int containing the result of deletion
	 * @throws EMDException
	 **************************************************************************/
	public int modifyPerfCurveImageName(PerformanceCurveVO objPerformanceCurveVO)
	throws EMDException, Exception {
		
		int intUpdateImg = 0;
		LogUtil
		.logMessage("Enters into OrderPerformanceCurveBO:modifyPerfCurveImageName");
		try {
			
			intUpdateImg = OrderPerfCurveDAO
			.modifyPerfCurveImageName(objPerformanceCurveVO);
			
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderPerformanceCurveBO:BusinessException"
					+ objErrorInfo.getMessage());
			intUpdateImg = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderPerformanceCurveBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderPerformanceCurveBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		LogUtil
		.logMessage("Leaves from OrderPerformanceCurveBO:modifyPerfCurveImageName");
		return intUpdateImg;
	}
	
//	Added for CR_121 Rearrange Order Performance Curve
	/***************************************************************************
	 * * * * This Method is used to Rearrange Order Performance Curve 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objPerformanceCurveVO
	 *            This object is used for save Performance Curve Images
	 * @return int status of save
	 * @throws EMDException
	 **************************************************************************/
	public int reArrangePerfCurve(PerformanceCurveVO objPerformanceCurveVO)
		throws EMDException, Exception {
			
			int intUpdateImg = 0;
			LogUtil
			.logMessage("Enters into OrderPerformanceCurveBO:reArrangePerfCurvee");
			try {
				
				intUpdateImg = OrderPerfCurveDAO
				.reArrangePerfCurve(objPerformanceCurveVO);
				
			} catch (BusinessException objBusExp) {
				ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
				LogUtil
				.logMessage("enters into catch block in OrderPerformanceCurveBO:BusinessException"
						+ objErrorInfo.getMessage());
				intUpdateImg = Integer.parseInt(objErrorInfo.getMessageID());
				
			} catch (ApplicationException objAppExp) {
				ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
				LogUtil
				.logMessage("enters into catch block in OrderPerformanceCurveBO:ApplicationException"
						+ objErrorInfo.getMessage());
				throw new Exception(objErrorInfo.getMessage());
			} catch (Exception objExp) {
				LogUtil
				.logMessage("enters into catch block in OrderPerformanceCurveBO:ApplicationException"
						+ objExp.getMessage());
				throw new Exception(objExp.getMessage());
			}
			LogUtil
			.logMessage("Leaves from OrderPerformanceCurveBO:reArrangePerfCurvee");
			return intUpdateImg;
		}
	
}