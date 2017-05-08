/**
 * 
 */
package com.EMD.LSDB.bo.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.ModelSubSectionBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.MasterMaintenance.ModelSubSectionDAO;
import com.EMD.LSDB.vo.common.SubSectionVO;

/**
 * @author vv49326
 * 
 */
public class ModelSubSectionBO implements ModelSubSectionBI {
	
	public static ModelSubSectionBO objSubSecMaintBO = null;
	
	public synchronized static ModelSubSectionBO getInstance() {
		if (objSubSecMaintBO == null) {
			objSubSecMaintBO = new ModelSubSectionBO();
		}
		return objSubSecMaintBO;
	}
	
	private ModelSubSectionBO() {
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSubSecMaintVO
	 *            The object for fetch SubSections
	 * @return Arraylist It has Arraylist of SubSecMaintVO
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchSubSections(SubSectionVO objSubSecMaintVO)
	throws EMDException, Exception {
		
		ArrayList arlSubSection = null;
		try {
			LogUtil.logMessage("Entering ModelSubSectionBO.fetchSubSections");
			arlSubSection = ModelSubSectionDAO
			.fetchSubSections(objSubSecMaintVO);
			
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in ModelSubSectionBO:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelSubSectionBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in ModelSubSectionBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlSubSection;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSubSecMaintVO
	 *            The object for Insert SubSections
	 * @return Boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	public synchronized int insertSubSection(SubSectionVO objSubSecMaintVO)
	throws EMDException, Exception {
		
		int intReturnStatus = 0;
		try {
			LogUtil.logMessage("Entering ModelSubSecMaintBO.insertSubSection");
			intReturnStatus = ModelSubSectionDAO
			.insertSubSection(objSubSecMaintVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in ModelSubSectionBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelSubSectionBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in ModelSubSectionBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSubSecMaintVO
	 *            The object for Modify SubSections
	 * @return Boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public int updateSubSection(SubSectionVO objSubSecMaintVO)
	throws EMDException, Exception {
		
		int intReturnStatus = 0;
		try {
			LogUtil.logMessage("Entering ModelSubSectionBO.updateSubSection");
			intReturnStatus = ModelSubSectionDAO
			.updateSubSection(objSubSecMaintVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in ModelSubSectionBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelSubSectionBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in ModelSubSectionBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		
		return intReturnStatus;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSubSecMaintVO
	 *            The object for Delete SubSections for CR-4
	 * @return Boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	public synchronized int deleteSubSection(SubSectionVO objSubSecMaintVO)
	throws EMDException, Exception {
		
		int intReturnStatus = 0;
		try {
			LogUtil.logMessage("Entering ModelSubSecMaintBO.deleteSubSection");
			intReturnStatus = ModelSubSectionDAO
			.deleteSubSection(objSubSecMaintVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in ModelSubSectionBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelSubSectionBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in ModelSubSectionBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSubSecMaintVO
	 *            The object for fetch sec name and SubSection name for CR_92
	 * @return Arraylist It has Arraylist of SubSecMaintVO
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchModelSubSectionDetails(SubSectionVO objSubSecMaintVO)
	throws EMDException, Exception {
		
		ArrayList arlSubSection = null;
		try {
			LogUtil.logMessage("Entering ModelSubSectionBO.fetchSubSections");
			arlSubSection = ModelSubSectionDAO
			.fetchSubSectionDetails(objSubSecMaintVO);
					
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in ModelSubSectionBO:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelSubSectionBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in ModelSubSectionBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlSubSection;
	}
	
}
