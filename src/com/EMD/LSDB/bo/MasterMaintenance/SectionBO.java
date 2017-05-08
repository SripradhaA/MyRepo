/**
 * 
 */
package com.EMD.LSDB.bo.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.SectionBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.MasterMaintenance.SectionDAO;
import com.EMD.LSDB.vo.common.SectionVO;

/**
 * @author PS57222
 * 
 */
public class SectionBO implements SectionBI {
	
	private SectionBO() {
		
	}
	
	public static SectionBO objSectionBO;
	
	public static synchronized SectionBO getInstance() {
		if (objSectionBO == null) {
			objSectionBO = new SectionBO();
			
		}
		return objSectionBO;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSectionVO
	 *            The object for fetch Section
	 * @return Arraylist It has Arraylist of SectionVO
	 * @throws EMDException
	 **************************************************************************/
	
	public ArrayList fetchSections(SectionVO objSectionVO) throws EMDException,
	Exception {
		ArrayList arlSectionList = null;
		LogUtil.logMessage("Enters into fetchSectionBO");
		try {
			arlSectionList = SectionDAO.fetchSections(objSectionVO);
		}
		
		catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in SectionBo:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in SectionBo:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in SectionBo:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		
		return arlSectionList;
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSectionVO
	 *            The Object for Insert Section
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public synchronized int insertSection(SectionVO objSectionVO)
	throws EMDException, Exception {
		LogUtil.logMessage("Enters into insertSectionBO");
		int intStatusCode;
		try {
			intStatusCode = SectionDAO.insertSection(objSectionVO);
			
		}
		
		catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in SectionBo:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in SectionBo:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in SectionBo:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intStatusCode;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSectionVO
	 *            The Object for Modify Section
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public int updateSection(SectionVO objSectionVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Enters into updateSectionBO");
		int intStatusCode;
		try {
			intStatusCode = SectionDAO.updateSection(objSectionVO);
			
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in SectionBo:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in SectionBo:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in SectionBo:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intStatusCode;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSectionVO
	 *            The Object for Delete Section Added for Delete Section CR-4
	 * @return boolean The Flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public synchronized int deleteSection(SectionVO objSectionVO)
	throws EMDException, Exception {
		LogUtil.logMessage("Enters into deleteSectionBO");
		int intStatusCode;
		try {
			intStatusCode = SectionDAO.deleteSection(objSectionVO);
			
		}
		
		catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in SectionBo:BusinessException"
					+ objErrorInfo.getMessageID());
			intStatusCode = Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in SectionBo:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in SectionBo:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intStatusCode;
	}
}
