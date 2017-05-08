
package com.EMD.LSDB.bo.Suggestions;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.AcceptRejectClauseBI;
import com.EMD.LSDB.bo.serviceinterface.SuggestBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.SpecMaintenance.AcceptRejectClauseDAO;
import com.EMD.LSDB.dao.Suggestions.SuggestDAO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.SuggestVO;

/**
 * @author VV49326
 * 
 */
/***********************************************************************
----------------------------------------------------------------------------------------------------------
*    Date     Version   Modified by    	 Comments                              		Remarks 
* 19/01/2010    1.0      RR68151       Added two new methods for Edl Indicator     Added for CR_81
* 											Screen.  
* 													 	 
* 
* --------------------------------------------------------------------------------------------------------
**************************************************************************/


public class SuggestBO implements SuggestBI {
	
	public static SuggestBO objSuggestBO = null;
	
	public synchronized static SuggestBO getInstance() {
		
		if (objSuggestBO == null) {
			objSuggestBO = new SuggestBO();
		}
		return objSuggestBO;
	}
	
	private SuggestBO() {
	}
	
	/***************************************************************************
	 * This Method is used to Submit the Suggestions 
	 * 
	 * @param objSuggestVO
	 * @return Boolean
	 * @throws EMDException
	 **************************************************************************/
	public int submitSuggestion(SuggestVO objSuggestVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Entering SuggestBO.submitSuggestion");
		int intReturnStatus = 0;
		try {
			intReturnStatus = SuggestDAO.submitSuggestion(objSuggestVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in SuggestBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in SuggestBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in SuggestBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * This Method is used to fetch all the Suggestions 
	 * 
	 * @param objSuggestVO
	 * @return Boolean
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchSuggestions(SuggestVO objSuggestVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Entering SuggestBO.fetchSuggestions");
		ArrayList arlSuggestions = new ArrayList();
		try {
			arlSuggestions = SuggestDAO.fetchSuggestions(objSuggestVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in SuggestBO:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in SuggestBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in SuggestBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlSuggestions;
	}
	
	/***************************************************************************
	 * This Method is used to fetch all the Suggestion Status
	 * 
	 * @param objSuggestVO
	 * @return Boolean
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchSuggestionStatus(SuggestVO objSuggestVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Entering SuggestBO.fetchSuggestionStatus");
		ArrayList arlSuggStatus = new ArrayList();
		try {
			arlSuggStatus = SuggestDAO.fetchSuggestionStatus(objSuggestVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in SuggestBO:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in SuggestBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in SuggestBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlSuggStatus;
	}
	
	/***************************************************************************
	 * This Method is used to update the Suggestions 
	 * 
	 * @param objSuggestVO
	 * @return Boolean
	 * @throws EMDException
	 **************************************************************************/
	public int updateSuggestion(SuggestVO objSuggestVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Entering SuggestBO.updateSuggestion");
		int intReturnStatus = 0;
		try {
			intReturnStatus = SuggestDAO.updateSuggestion(objSuggestVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in SuggestBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in SuggestBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in SuggestBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * This Method is used to delete the attachment tied to Suggestions 
	 * 
	 * @param objSuggestVO
	 * @return Boolean
	 * @throws EMDException
	 **************************************************************************/
	public int deleteAttachment(SuggestVO objSuggestVO) throws EMDException,
	Exception {
		LogUtil.logMessage("Entering SuggestBO.deleteAttachment");
		int intReturnStatus = 0;
		try {
			intReturnStatus = SuggestDAO.deleteAttachment(objSuggestVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("Enters into catch block in SuggestBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in SuggestBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into catch block in SuggestBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
}
