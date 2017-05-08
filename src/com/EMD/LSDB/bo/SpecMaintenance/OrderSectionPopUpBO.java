package com.EMD.LSDB.bo.SpecMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.OrderSectionPopUpBI;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.SpecMaintenance.OrderSectionDAO;
import com.EMD.LSDB.dao.SpecMaintenance.OrderSectionPopUpDAO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business methods for the OrderSectionPopUp
 ******************************************************************************/
public class OrderSectionPopUpBO implements OrderSectionPopUpBI {
	
	public static OrderSectionPopUpBO objOrderSectionPopUpBO = null;
	
	public synchronized static OrderSectionPopUpBO getInstance() {
		if (objOrderSectionPopUpBO == null) {
			objOrderSectionPopUpBO = new OrderSectionPopUpBO();
		}
		return objOrderSectionPopUpBO;
	}
	
	private OrderSectionPopUpBO() {
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objClauseVO
	 *            the object for inserting PartOF
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	public int savePartOf(ClauseVO objClauseVO) throws EMDException, Exception {
		int intReturnStatus = 0;
		try {
			LogUtil.logMessage("Inside the objOrderSectionPopUpBO:savePartOf");
			intReturnStatus = OrderSectionPopUpDAO.savePartOf(objClauseVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionPopUpBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionPopUpBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderSectionPopUpBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSectionVO
	 *            the object for fetching clause
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	/***************************************************************************
	 * This Method is used to get the clause details for the selectd subsection
	 * at order Level Here the FetchClauses method from OrderSectionDAO is
	 * reused to get the details. OrderSectionDAO.fetchClauses method is a local
	 * method for OrderSectionDAO it uses the local connection but here we pass
	 * the connection as null and create a new connection in
	 * OrderSectionDAO.fetchClauses and close the new connection once the
	 * operation is done.befor the new is connection is closed there is a check
	 * condition if the fetch clauses method Flag argument is
	 * DatabaseConstants.APPENDIX_IMAGE then the fetchclause method closes the
	 * connection at the end.If the Flag value is DatabaseConstants.MODIFY_SPEC
	 * then the connection is not closed at the end.
	 */
	
	public ArrayList fetchClauses(SectionVO objSectionVO) throws EMDException,
	Exception {
		
		ArrayList arlClauseGroup = new ArrayList();
		SubSectionVO objSubSecVO;
		
		try {
			LogUtil.logMessage("Inside the OrderSectionPopUpBO :fetchClauses ");
			objSubSecVO = OrderSectionDAO.fetchClauses(objSectionVO, null,
					DatabaseConstants.APPENDIX_IMAGE);
			LogUtil.logMessage("In Bo getClauseGroup Size"
					+ objSubSecVO.getClauseGroup().size());
			arlClauseGroup = objSubSecVO.getClauseGroup();
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionPopUpBO:BusinessException"
					+ objErrorInfo.getMessage());
		}
		
		catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionPopUpBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderSectionPopUpBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlClauseGroup;
	}
	
}