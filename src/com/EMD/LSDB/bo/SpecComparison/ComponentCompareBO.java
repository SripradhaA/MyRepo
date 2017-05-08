package com.EMD.LSDB.bo.SpecComparison;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This Business class has the business methods for the component
 *          comparsions by order wise.
 ******************************************************************************/

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.ComponentCompareBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.SpecComparison.ComponentCompareDAO;
import com.EMD.LSDB.vo.common.OrderVO;

public class ComponentCompareBO implements ComponentCompareBI {
	
	private ComponentCompareBO() {
		
	}
	
	public static ComponentCompareBO objComponentCompareBO = null;
	
	/***************************************************************************
	 * This Method is used to get the instance of ComponentCompareBO.
	 * 
	 * @return ComponentCompareBO
	 **************************************************************************/
	
	public synchronized static ComponentCompareBO getInstance() {
		
		if (objComponentCompareBO == null) {
			objComponentCompareBO = new ComponentCompareBO();
		}
		return objComponentCompareBO;
	}
	
	/***************************************************************************
	 * This Method is used to fetch the list of section details for the selected
	 * orders.
	 * 
	 * @param java.util.ArrayList
	 * @return java.util.ArrayList
	 * @throws EMDException
	 **************************************************************************/
	
	public ArrayList fetchSectionsAndSubSections(ArrayList arlOrderList)
	throws EMDException, Exception {
		
		ArrayList arlSectionList = null;
		try {
			LogUtil
			.logMessage("Entering ComponentCompareBO.fetchSectionsAndSubSections");
			
			/** To fetch section detais of the selected order numbers. */
			arlSectionList = ComponentCompareDAO
			.fetchSectionsAndSubSections(arlOrderList);
			LogUtil
			.logMessage("Ending ComponentCompareBO.fetchSectionsAndSubSections");
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentCompareBO:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentCompareBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ComponentCompareBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		
		return arlSectionList;
	}
	
	/***************************************************************************
	 * This Method is used to fetch the list of Components which differs for the
	 * selected orders.
	 * 
	 * @param objOrderVO
	 * @return java.util.ArrayList
	 * @throws EMDException
	 **************************************************************************/
	
	public ArrayList fetchDifferencecomponent(OrderVO objOrderVO)
	throws EMDException, Exception {
		ArrayList arlCompList = null;
		try {
			LogUtil
			.logMessage("Entering ComponentCompareBO.fetchDifferencecomponent");
			
			/** To fetch section detais of the selected order numbers. */
			arlCompList = ComponentCompareDAO
			.fetchDifferenceComponent(objOrderVO);
			
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentCompareBO:BusinessException"
					+ objErrorInfo.getMessage());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentCompareBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ComponentCompareBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		
		return arlCompList;
	}
	
}