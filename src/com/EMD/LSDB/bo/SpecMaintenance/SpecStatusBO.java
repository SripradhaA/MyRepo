/**
 * 
 */
package com.EMD.LSDB.bo.SpecMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.SpecStatusBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.SpecMaintenance.SpecStatusDAO;
import com.EMD.LSDB.vo.common.OrderVO;

/**
 * @author ps57222
 * 
 */
public class SpecStatusBO implements SpecStatusBI {
	
	private SpecStatusBO() {
		
	}
	
	public static SpecStatusBO objSpecStatusBO;
	
	public static SpecStatusBO getInstance() {
		
		if (objSpecStatusBO == null) {
			objSpecStatusBO = new SpecStatusBO();
		}
		return objSpecStatusBO;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSectionVO
	 *            The object for fetch Sections in OrderLevel
	 * @return Arraylist It has Arraylist of objSectionVO
	 * @throws EMDException
	 **************************************************************************/
	
	public ArrayList fetchSpecNextStatus(OrderVO objOrderVO)
	throws EMDException, Exception {
		
		LogUtil.logMessage("Enters into SpecStatusBO:fetchSpecNextStatus");
		ArrayList arlSpecNextStatus = null;
		
		try {
			arlSpecNextStatus = SpecStatusDAO.fetchSpecNextStatus(objOrderVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in SpecStatusBO:BusinessException"
					+ objErrorInfo.getMessageID());
			// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in SpecStatusBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in SpecStatusBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlSpecNextStatus;
	}
	
}
