package com.EMD.LSDB.bo.History;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.HistoryEdlPopUpBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.History.HistoryEdlPopUpDAO;
import com.EMD.LSDB.vo.common.ClauseVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for the History Edl Pop Up
 ******************************************************************************/
public class HistoryEdlPopUpBO implements HistoryEdlPopUpBI {
	
	public static HistoryEdlPopUpBO objHistoryEdlPopUpBO = null;
	
	public synchronized static HistoryEdlPopUpBO getInstance() {
		if (objHistoryEdlPopUpBO == null) {
			objHistoryEdlPopUpBO = new HistoryEdlPopUpBO();
		}
		return objHistoryEdlPopUpBO;
	}
	
	private HistoryEdlPopUpBO() {
		
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            the object for fetching details of History Edl Pop Up
	 * @return ArrayList of History Edl details
	 * @throws EMDException
	 **************************************************************************/
	
	public ArrayList fetchEdlNo(ClauseVO objClauseVO) throws EMDException,
	Exception {
		ArrayList arlEdlNo = null;
		try {
			LogUtil.logMessage("Inside the HistoryEdlPopUpBO:fetchEdlNo");
			arlEdlNo = HistoryEdlPopUpDAO.fetchEdlNo(objClauseVO);
		}
		
		catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in HistoryEdlPopUpBO:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in HistoryEdlPopUpBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in HistoryEdlPopUpBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlEdlNo;
	}
	
}
