/**
 * 
 */
package com.EMD.LSDB.bo.SpecMaintenance;

import java.sql.Connection;
import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.OrderSectionBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.SpecMaintenance.OrderSectionDAO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.RevisionVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

/**
 * @author ps57222
 * 
 */
public class OrderSectionBO implements OrderSectionBI {
	
	private OrderSectionBO() {
		
	}
	
	public static OrderSectionBO objOrderSectionBO;
	
	public static synchronized OrderSectionBO getInstance() {
		
		if (objOrderSectionBO == null) {
			objOrderSectionBO = new OrderSectionBO();
		}
		return objOrderSectionBO;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSectionVO
	 *            The object for fetch Sections in OrderLevel
	 * @return Arraylist It has Arraylist of objSectionVO
	 * @throws EMDException
	 **************************************************************************/
	
	public ArrayList fetchOrdSections(SectionVO objSectionVO)
	throws EMDException, Exception {
		
		LogUtil.logMessage("Enters into OrderSectionBO:fetchOrdSections");
		ArrayList arlSectionList = null;
		
		try {
			arlSectionList = OrderSectionDAO.fetchOrdSections(objSectionVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:BusinessException"
					+ objErrorInfo.getMessageID());
			// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlSectionList;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSectionVO
	 *            The object for fetch Section details in OrderLevel
	 * @return Arraylist It has Arraylist of objSectionVO
	 * @throws EMDException
	 **************************************************************************/
	
	public ArrayList fetchSectionDetails(SectionVO objSectionVO)
	throws EMDException, Exception {
		
		LogUtil.logMessage("Enters into OrderSectionBO:fetchSectionDetails");
		ArrayList arlSectionList = null;
		
		try {
			arlSectionList = OrderSectionDAO.fetchSectionDetails(objSectionVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:BusinessException"
					+ objErrorInfo.getMessageID());
			// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlSectionList;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objModelVO
	 *            the object for updating the Order Component
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public SectionVO saveComponent(SectionVO objSectionVO) throws EMDException,
	Exception {
		
		int intReturnStatus = 0;
		//SectionVO objSection = null;
		try {
			LogUtil.logMessage("Entering OrderSectionBO.saveComponent");
			objSectionVO = OrderSectionDAO.saveComponent(objSectionVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		//Added for LSDB_CR_71 for server Side component Validation
		objSectionVO.setMessageID(intReturnStatus);
		return objSectionVO;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSubSectionVO
	 *            the object for Fetching the SubSectionDetails
	 * @return ArrayList of SubSectionDetails
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchSubSectionDetails(SubSectionVO objSubSectionVO)
	throws EMDException, Exception {
		LogUtil.logMessage("Enters into OrderSectionBO:fetchSubSectionDetails");
		ArrayList arlSubSecList = null;
		
		try {
			arlSubSecList = OrderSectionDAO
			.fetchSubSectionDetails(objSubSectionVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:BusinessException"
					+ objErrorInfo.getMessageID());
			// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlSubSecList;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSectionVO
	 *            The object for fetch SubSections at OrderLevel
	 * @return Arraylist It has Arraylist of objSubSectionVO
	 * @throws EMDException
	 **************************************************************************/
	
	public ArrayList fetchOrdSubSections(SectionVO objSectionVO)
	throws EMDException, Exception {
		
		LogUtil.logMessage("Enters into OrderSectionBO:fetchOrdSubSections");
		ArrayList arlSubSectionList = null;
		
		try {
			arlSubSectionList = OrderSectionDAO
			.fetchOrdSubSections(objSectionVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:BusinessException"
					+ objErrorInfo.getMessageID());
			// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlSubSectionList;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSectionVO
	 *            The object for fetch SubSections at OrderLevel
	 * @return Arraylist It has Arraylist of objSubSectionVO
	 * @throws EMDException
	 **************************************************************************/
	/***************************************************************************
	 * fetchRevision Method is used to load the dropdown values from
	 * database,which is used in Modify Spec main screen. Added on 20-May-08 by
	 * ps57222
	 * 
	 */
	public ArrayList fetchRevision(RevisionVO objRevisionVO)
	throws EMDException, Exception {
		
		LogUtil.logMessage("Enters into OrderSectionBO:fetchRevision");
		ArrayList arlRevisionList = null;
		
		try {
			arlRevisionList = OrderSectionDAO.fetchRevision(objRevisionVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:BusinessException"
					+ objErrorInfo.getMessageID());
			// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlRevisionList;
	}

	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSubSectionVO
	 *            The object for fetch Order Components and Model Components
	 * @return objSubSectionVO
	 * @throws EMDException
	 **************************************************************************/
	
	public SubSectionVO fetchComponents(SubSectionVO objSubSectionVO,
			SectionVO objSectionVO, Connection objConnection,String blnConnFlag)
	throws EMDException, Exception {
		
		LogUtil.logMessage("Enters into OrderSectionBO:fetchComponents");
		SubSectionVO objSubSecVO=null;
		
		try {
			objSubSecVO = OrderSectionDAO.fetchComponents(objSubSectionVO,
					objSectionVO,objConnection,blnConnFlag);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:BusinessException"
					+ objErrorInfo.getMessageID());
			// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return objSubSecVO;
	}
	
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0 Added for CR-72 Proof Reading PDF
	 * @param objSectionVO
	 *            the object for fetching Clauses
	 * @return SubSectionVO
	 * @throws EMDException
	 **************************************************************************/
	
	public SubSectionVO fetchClauseDetails(SectionVO objSectionVO) throws EMDException,
	Exception {
		
		int intReturnStatus = 0;
		SubSectionVO objSubSection = null;
		try {
			LogUtil.logMessage("Entering OrderSectionBO.fetchClauseDetails");
			objSubSection = OrderSectionDAO.fetchClauseDetails(objSectionVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		
		
		return objSubSection;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0 Added for CR-72 Proof Reading PDF
	 * @param objSectionVO
	 *            the object generating Order Clause No
	 * @return integer
	 * @throws EMDException
	 **************************************************************************/
	
	public int generateOrdrClauseNo(SectionVO objSectionVO) throws EMDException, Exception {
		
		int intReturnStatus = 0;
		try {
			LogUtil.logMessage("Entering OrderSectionBO.generateOrdrClauseNo");
			intReturnStatus = OrderSectionDAO.generateOrdrClauseNo(objSectionVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd 
	 * @version 1.0 Added for CR-72 Proof Reading PDF
	 * @param objSectionVO
	 *            the object for deleting the temp table data
	 * @return integer
	 * @throws EMDException
	 **************************************************************************/
	
	public int deleteTempTabData(SectionVO objSectionVO) throws EMDException, Exception {
		
		int intReturnStatus = 0;
		try {
			LogUtil.logMessage("Entering OrderSectionBO.deleteTempTabData");
			intReturnStatus = OrderSectionDAO.deleteTempTabData(objSectionVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objModelVO
	 *            the object for updating the Order Component
	 * @return boolean the flag for success or failure
	 * @throws EMDException
	 **************************************************************************/
	
	public SectionVO saveRequiredComponent(SectionVO objSectionVO) throws EMDException,
	Exception {
		
		int intReturnStatus = 0;
		try {
			LogUtil.logMessage("Entering OrderSectionSaveBO.saveRequiredComponent");
			objSectionVO = OrderSectionDAO.saveRequiredComponent(objSectionVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionSaveBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionSaveBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderSectionSaveBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		//Added for LSDB_CR_71 for server Side component Validation
		objSectionVO.setMessageID(intReturnStatus);
		return objSectionVO;
	}
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0 Added for CR-72 Proof Reading PDF
	 * @param objSectionVO
	 *            the object generating Order Clause No
	 * @return integer
	 * @throws EMDException
	 **************************************************************************/
	
	public int saveNewOrderComp(ComponentVO objComponentVO) throws EMDException, Exception {
		
		int intReturnStatus = 0;
		try {
			LogUtil.logMessage("Entering OrderSectionBO.saveNewOrderComp");
			intReturnStatus = OrderSectionDAO.saveNewOrderComp(objComponentVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}

	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0 Added for CR-109 to bring new subsection to Order
	 * @param objSectionVO
	 * @return integer
	 * @throws EMDException
	 **************************************************************************/
	
	public int addNewSubsecToOrder(SectionVO objSectionVO) throws EMDException, Exception {
		
		int intReturnStatus = 0;
		try {
			LogUtil.logMessage("Entering OrderSectionBO.addNewSubsecToOrder");
			intReturnStatus = OrderSectionDAO.addNewSubsecToOrder(objSectionVO);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in OrderSectionBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
	}
}