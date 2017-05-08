package com.EMD.LSDB.bo.MasterMaintenance;

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.ViewSpecByModelBI;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.MasterMaintenance.ModelDAO;
import com.EMD.LSDB.dao.MasterMaintenance.ViewSpecByModelDAO;
import com.EMD.LSDB.vo.common.ModelVo;
import com.EMD.LSDB.vo.common.SubSectionVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business method implementation for getting
 *          the clauses of the Model
 ******************************************************************************/
/***************************************************************************
 * ------------------------------------------------------------------------------------------------------
 *     Date         version  create by   modify by             comments                              Remarks 
 * 19/01/2010        1.0      SD41630                 Added new mehtod for view characterisitic     Added for CR_81
 * 													   group Report by model.   
 * 													 	 
 * 
 * --------------------------------------------------------------------------------------------------------
  **************************************************************************/
public class ViewSpecByModelBO implements ViewSpecByModelBI {
	
	public static ViewSpecByModelBO objViewSpecByModelBO = null;
	
	public synchronized static ViewSpecByModelBO getInstance() {
		if (objViewSpecByModelBO == null) {
			objViewSpecByModelBO = new ViewSpecByModelBO();
		}
		return objViewSpecByModelBO;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSubSectionVO
	 *            The object for searching clause details for a model
	 * @return Arraylist It has Arraylist of clauses of the model
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList fetchSpecByModel(SubSectionVO objSubSectionVO)
	throws EMDException, Exception {
		
		try {
			LogUtil
			.logMessage("Inside the fetchSpecByModel method of ViewSpecByModelBO");
			return ViewSpecByModelDAO.fetchSpecByModel(objSubSectionVO);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ViewSpecByModelBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSubSectionVO
	 *            The object for searching clause details for a model
	 * @return Arraylist It has Arraylist of clauses of the model
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList viewMasterSpecByModel(SubSectionVO objSubSectionVO)
	throws EMDException, Exception {
		
		try {
			LogUtil
			.logMessage("Inside the viewMasterSpecByModel method of ViewSpecByModelBO");
			return ViewSpecByModelDAO.viewMasterSpecByModel(objSubSectionVO);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in viewMasterSpecByModel:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * Added for LSDB_CR-77
	 * @version 1.0
	 * @param objSubSectionVO
	 * The object for searching clause details for a model
	 * @return Arraylist It has Arraylist of clauses of the model
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList viewCustomerOptionCatalog(ModelVo objModelVo)
	throws EMDException, Exception {
		
		try {
			LogUtil
			.logMessage("Inside the viewCustomerOptionCatalog method of ViewSpecByModelBO");
			return ViewSpecByModelDAO.viewCustomerOptionCatalog(objModelVo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in viewCustomerOptionCatalog:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
	}
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objSubSectionVO
	 * The object for view Characterisitic group report by model For CR_81
	 * @return Arraylist It has Arraylist of clauses of the model
	 * @throws EMDException
	 **************************************************************************/
	public ArrayList viewCharactersiticGrpRpt(SubSectionVO objSubSectionVO)
	throws EMDException, Exception {
		
		try {
			LogUtil
			.logMessage("Inside the ViewCharactersiticGrpRptBO method");
			return ViewSpecByModelDAO.viewCharactersiticGrpRpt(objSubSectionVO);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ViewCharactersiticGrpRptBO:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
	}
	
	//Added for CR_118

	public ArrayList editCompGroupInCOC(ModelVo objModelVo) throws EMDException, Exception {
		try {
			LogUtil
			.logMessage("Inside the editCompGroupInCOC method of ViewSpecByModelBO");
			return ViewSpecByModelDAO.editCompGroupInCOC(objModelVo);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in editCompGroupInCOC:Exception"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
	}
	
	public int updateCompGroupsInCOC(ModelVo objModelVo) throws EMDException, Exception {
		int intReturnStatus = 0;
		try {
			LogUtil.logMessage("Entering ModelBO.updateModel");
			intReturnStatus = ViewSpecByModelDAO.updateCompGroupsInCOC(objModelVo);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelBo:BusinessException"
					+ objErrorInfo.getMessage());
			intReturnStatus = Integer.parseInt(objErrorInfo.getMessageID());
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ModelBo:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		} catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ModelBo:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return intReturnStatus;
		
		
	}
	
	//Added for CR_118 Ends
	
	
}
