/*
 * Created on Jun 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.bo.factory;

import com.EMD.LSDB.bo.CRForm.ChangeRequestBO;
import com.EMD.LSDB.bo.History.HistoryEdlPopUpBO;
import com.EMD.LSDB.bo.History.RevisionChangesBO;
import com.EMD.LSDB.bo.MasterMaintenance.CustomerBO;
import com.EMD.LSDB.bo.MasterMaintenance.DistributorBO;
import com.EMD.LSDB.bo.MasterMaintenance.ModelAddClauseRevBO;
import com.EMD.LSDB.bo.MasterMaintenance.ModelAppendixBO;
import com.EMD.LSDB.bo.MasterMaintenance.ModelAssignEdlBO;
import com.EMD.LSDB.bo.MasterMaintenance.ModelBO;
import com.EMD.LSDB.bo.MasterMaintenance.ModelClauseBO;
import com.EMD.LSDB.bo.MasterMaintenance.ModelCompBO;
import com.EMD.LSDB.bo.MasterMaintenance.ModelCompGroupBO;
import com.EMD.LSDB.bo.MasterMaintenance.ModelCompMapBO;
import com.EMD.LSDB.bo.MasterMaintenance.ModelGenArrangeBO;
import com.EMD.LSDB.bo.MasterMaintenance.ModelPerfCurveBO;
import com.EMD.LSDB.bo.MasterMaintenance.ModelSelectClauseRevBO;
import com.EMD.LSDB.bo.MasterMaintenance.ModelSpecificationBO;
import com.EMD.LSDB.bo.MasterMaintenance.ModelSubSectionBO;
import com.EMD.LSDB.bo.MasterMaintenance.SectionBO;
import com.EMD.LSDB.bo.MasterMaintenance.SpecTypeBO;
import com.EMD.LSDB.bo.MasterMaintenance.StandardEquipBO;
import com.EMD.LSDB.bo.MasterMaintenance.ViewSpecByModelBO;
import com.EMD.LSDB.bo.SpecComparison.ComponentCompareBO;
import com.EMD.LSDB.bo.SpecMaintenance.AcceptRejectClauseBO;
import com.EMD.LSDB.bo.SpecMaintenance.AcceptRejectNewClauseBO;
import com.EMD.LSDB.bo.SpecMaintenance.AppendixBO;
import com.EMD.LSDB.bo.SpecMaintenance.MainFeatureInfoBO;
import com.EMD.LSDB.bo.SpecMaintenance.OrderBO;
import com.EMD.LSDB.bo.SpecMaintenance.OrderClauseBO;
import com.EMD.LSDB.bo.SpecMaintenance.OrderGenArrangeBO;
import com.EMD.LSDB.bo.SpecMaintenance.OrderPerfCurveBO;
import com.EMD.LSDB.bo.SpecMaintenance.OrderSectionBO;
import com.EMD.LSDB.bo.SpecMaintenance.OrderSectionPopUpBO;
import com.EMD.LSDB.bo.SpecMaintenance.OrderSpecificationBO;
import com.EMD.LSDB.bo.SpecMaintenance.SpecStatusBO;
import com.EMD.LSDB.bo.Suggestions.SuggestBO;
import com.EMD.LSDB.bo.admn.UserMaintBO;
import com.EMD.LSDB.bo.common.ChangePwdBO;
import com.EMD.LSDB.bo.common.FileUploadBO;
import com.EMD.LSDB.bo.common.LoginBO;
import com.EMD.LSDB.bo.cr1058.ChangeRequest1058BO;
import com.EMD.LSDB.bo.serviceinterface.AcceptRejectClauseBI;
import com.EMD.LSDB.bo.serviceinterface.AcceptRejectNewClauseBI;
import com.EMD.LSDB.bo.serviceinterface.AppendixBI;
import com.EMD.LSDB.bo.serviceinterface.ChangePwdBI;
import com.EMD.LSDB.bo.serviceinterface.ChangeRequestBI;
import com.EMD.LSDB.bo.serviceinterface.ComponentCompareBI;
import com.EMD.LSDB.bo.serviceinterface.CustomerBI;
import com.EMD.LSDB.bo.serviceinterface.DistributorBI;
import com.EMD.LSDB.bo.serviceinterface.FileUploadBI;
import com.EMD.LSDB.bo.serviceinterface.HistoryEdlPopUpBI;
import com.EMD.LSDB.bo.serviceinterface.LoginBI;
import com.EMD.LSDB.bo.serviceinterface.MainFeatureInfoBI;
import com.EMD.LSDB.bo.serviceinterface.ModelAddClauseRevBI;
import com.EMD.LSDB.bo.serviceinterface.ModelAppendixBI;
import com.EMD.LSDB.bo.serviceinterface.ModelAssignEdlBI;
import com.EMD.LSDB.bo.serviceinterface.ModelBI;
import com.EMD.LSDB.bo.serviceinterface.ModelClauseBI;
import com.EMD.LSDB.bo.serviceinterface.ModelCompBI;
import com.EMD.LSDB.bo.serviceinterface.ModelCompGroupBI;
import com.EMD.LSDB.bo.serviceinterface.ModelCompMapBI;
import com.EMD.LSDB.bo.serviceinterface.ModelGenArrangeBI;
import com.EMD.LSDB.bo.serviceinterface.ModelPerfCurveBI;
import com.EMD.LSDB.bo.serviceinterface.ModelSelectClauseRevBI;
import com.EMD.LSDB.bo.serviceinterface.ModelSpecificationBI;
import com.EMD.LSDB.bo.serviceinterface.ModelSubSectionBI;
import com.EMD.LSDB.bo.serviceinterface.OrderBI;
import com.EMD.LSDB.bo.serviceinterface.OrderClauseBI;
import com.EMD.LSDB.bo.serviceinterface.OrderGenArrangeBI;
import com.EMD.LSDB.bo.serviceinterface.OrderPerfCurveBI;
import com.EMD.LSDB.bo.serviceinterface.OrderSectionBI;
import com.EMD.LSDB.bo.serviceinterface.OrderSectionPopUpBI;
import com.EMD.LSDB.bo.serviceinterface.OrderSpecificationBI;
import com.EMD.LSDB.bo.serviceinterface.RevisionChangesBI;
import com.EMD.LSDB.bo.serviceinterface.SectionBI;
import com.EMD.LSDB.bo.serviceinterface.SpecStatusBI;
import com.EMD.LSDB.bo.serviceinterface.SpecTypeBI;
import com.EMD.LSDB.bo.serviceinterface.StandardEquipBI;
import com.EMD.LSDB.bo.serviceinterface.SuggestBI;
import com.EMD.LSDB.bo.serviceinterface.UserMaintBI;
import com.EMD.LSDB.bo.serviceinterface.ViewSpecByModelBI;
import com.EMD.LSDB.bo.serviceinterface.ChangeRequest1058BI;

/**
 * @author mm57219
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class ServiceFactory {
	
	private ServiceFactory() {
		
	}
	
	public static LoginBI getLoginBO() {
		
		return LoginBO.getInstance();
		
	}
	
	public static ModelBI getModelBO() {
		
		return ModelBO.getInstance();
		
	}
	
	public static SpecTypeBI getSpecTypeBO() {
		
		return SpecTypeBO.getInstance();
		
	}
	
	public static SectionBI getSectionBO() {
		
		return SectionBO.getInstance();
	}
	
	public static ModelSpecificationBI getModelSpecificationBO() {
		
		return ModelSpecificationBO.getInstance();
	}
	
	public static DistributorBI getDistributorBO() {
		
		return DistributorBO.getInstance();
	}
	
	public static ModelCompBI getModelCompBO() {
		
		return ModelCompBO.getInstance();
	}
	
	public static ModelCompGroupBI getModelCompGroupBO() {
		
		return ModelCompGroupBO.getInstance();
	}
	
	public static ModelSubSectionBI getSubSecMaintBO() {
		
		return ModelSubSectionBO.getInstance();
	}
	
	public static CustomerBI getCustomerBO() {
		
		return CustomerBO.getInstance();
	}
	
	public static ModelGenArrangeBI getGenArrangeBO() {
		
		return ModelGenArrangeBO.getInstance();
	}
	
	public static FileUploadBI getFileUploadBO() {
		
		return FileUploadBO.getInstance();
	}
	
	public static OrderBI getOrderBO() {
		
		return OrderBO.getInstance();
		
	}
	
	public static ModelCompMapBI getModelCompMapBO() {
		return ModelCompMapBO.getInstance();
	}
	
	public static MainFeatureInfoBI getMainfeatureInfoBO() {
		return MainFeatureInfoBO.getInstance();
	}
	
	public static ModelClauseBI getModelClauseBO() {
		return ModelClauseBO.getInstance();
	}
	
	public static StandardEquipBI getStandardEquipBO() {
		return StandardEquipBO.getInstance();
	}
	
	public static OrderSectionBI getOrderSectionBO() {
		return OrderSectionBO.getInstance();
	}
	
	public static OrderSpecificationBI getOrderSpecificationBO() {
		return OrderSpecificationBO.getInstance();
	}
	
	public static ModelSelectClauseRevBI getModelSelectClauseRevBO() {
		return ModelSelectClauseRevBO.getInstance();
	}
	
	public static OrderGenArrangeBI getOrderGenArrBO() {
		return OrderGenArrangeBO.getInstance();
	}
	
	public static ModelAddClauseRevBI getModelAddClauseRevBO() {
		
		return ModelAddClauseRevBO.getInstance();
	}
	
	public static AcceptRejectClauseBI getAcceptRejectClauseBO() {
		return AcceptRejectClauseBO.getInstance();
	}
	
	public static AcceptRejectNewClauseBI getAcceptRejectNewClauseBO() {
		return AcceptRejectNewClauseBO.getInstance();
	}
	
	public static ModelPerfCurveBI getModelPerformanceCurveBO() {
		return ModelPerfCurveBO.getInstance();
	}
	
	public static OrderPerfCurveBI getOrderPerfCurveBO() {
		return OrderPerfCurveBO.getInstance();
	}
	
	public static OrderSectionPopUpBI getOrderSectionPopUpBO() {
		return OrderSectionPopUpBO.getInstance();
	}
	
	public static SpecStatusBI getSpecStatusBO() {
		return SpecStatusBO.getInstance();
	}
	
	public static OrderClauseBI getOrderClauseBO() {
		
		return OrderClauseBO.getInstance();
	}
	
	public static ViewSpecByModelBI getViewSpecByModelBO() {
		return ViewSpecByModelBO.getInstance();
	}
	
	public static HistoryEdlPopUpBI getHistoryEdlPopUpBO() {
		
		return HistoryEdlPopUpBO.getInstance();
	}
	
	public static ChangePwdBI getChangePwdBO() {
		
		return ChangePwdBO.getInstance();
	}
	
	public static UserMaintBI getUserMaintBO() {
		return UserMaintBO.getInstance();
	}
	
	/** getAppendixBO method is Added For LSDB_CR-34 **
	 *  Added on 05-May-08
	 * 	by ps57222 
	 * 
	 * ***/
	
	public static AppendixBI getAppendixBO() {
		return AppendixBO.getInstance();
	}
	
	/** getModelAppendixBO method is Added For LSDB_CR-42 **
	 *  Added on 06-May-08
	 * 	by vv49326 
	 * 
	 * ***/
	
	public static ModelAppendixBI getModelAppendixBO() {
		
		return ModelAppendixBO.getInstance();
	}
	
	/** getChangeRequestBO method is Added For LSDB_CR-45 **
	 *
	 * ***/
	
	public static ChangeRequestBI getChangeRequestBO() {
		
		return ChangeRequestBO.getInstance();
	}
	
	/** getModelAssignEdlBO method is Added For LSDB_CR-81 **
	 *  Added On 28-Dec-09
	 *  by RR68151
	 *  
	 * ***/
	
	public static ModelAssignEdlBI getModelAssignEdlBO() {
		
		return ModelAssignEdlBO.getInstance();
	}
	
	/** getSuggestBO method is Added For LSDB_CR-100 to bring on Suggestions Module
	 *  Added On 26-May-11
	 *  by RR68151
	 * ***/
	
	public static SuggestBI getSuggestBO() {
		
		return SuggestBO.getInstance();
	}

	//Added for CR_101 - revision Changes
	public static RevisionChangesBI getRevisionChangesBO() {
		return RevisionChangesBO.getInstance();
	}
	//CR_101 Ends here
	
	//Added for CR_110 - for Request Common Details 
	public static ChangeRequest1058BI getChangeRequest1058BO() {
		return ChangeRequest1058BO.getInstance();
	}
	//CR_110 Ends here
	
	//Added for CR-128
	public static ComponentCompareBI getComponentCompareBO() {
		return ComponentCompareBO.getInstance();
	}
}