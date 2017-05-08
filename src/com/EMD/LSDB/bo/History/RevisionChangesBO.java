/**
 * 
 */
package com.EMD.LSDB.bo.History;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.RevisionChangesBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.History.RevisionChangesDAO;
import com.EMD.LSDB.vo.common.RevisionChangesVO;

/**
 * @author ER91220
 *
 */
public class RevisionChangesBO implements RevisionChangesBI {
	
	public static RevisionChangesBO objRevisionChangesBO = null;
	
	public synchronized static RevisionChangesBO getInstance() {
		
		if (objRevisionChangesBO == null) {
			objRevisionChangesBO = new RevisionChangesBO();
		}
		return objRevisionChangesBO;
	}
	
	private RevisionChangesBO() {
	}
	
	/*****************************************************************************************
	 * This Method is used to fetch the Revision Details
	 * @param RevisionChangesVO
	 * @return ArrayList
	 * @throws EMDException
	 ****************************************************************************************/
	public ArrayList fetchRevisions(RevisionChangesVO objRevisionChangesVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Entering RevisionChangesBO.fetchRevisions");
		
		ArrayList arlRevisionChanges = null;
		try {
			arlRevisionChanges = RevisionChangesDAO.fetchRevisions(objRevisionChangesVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in RevisionChangesBO:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in RevisionChangesBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in RevisionChangesBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlRevisionChanges;
	}
	
}
