package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.admn.UserVO;
import com.EMD.LSDB.vo.common.ChangeRequest1058VO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.ComponentVO;
import com.EMD.LSDB.vo.common.LoginVO;
import com.EMD.LSDB.vo.common.SectionVO;

public interface ChangeRequest1058BI {
	
	//for createrequest1058
	public ArrayList fetchCreate1058RequestOrderNo(ChangeRequest1058VO objChangeRequest1058Vo) throws EMDException, Exception;
	
	public int create1058LSDBRequest(ChangeRequest1058VO objChangeRequest1058Vo) throws EMDException, Exception;
	
	public int create1058NonLSDBRequest(ChangeRequest1058VO objChangeRequest1058Vo) throws EMDException, Exception;
	
	//for createNon-Lsdb1058request
	public int addClauseNonLsdb(ChangeRequest1058VO objChangeRequest1058Vo)throws EMDException, Exception;
	
	public int deleteClauseNonLsdb(ChangeRequest1058VO objChangeRequest1058Vo)throws EMDException, Exception;
	
	public int updateClauseNonLsdb(ChangeRequest1058VO objChangeRequest1058Vo)throws EMDException, Exception;
	
	public ArrayList selectClauseNonLsdb(ChangeRequest1058VO objChangeRequest1058VO)throws EMDException, Exception;
	
    //for request common details
	public ArrayList fetch1058Details(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception;
	
	public ArrayList fetchRepresentativeDetails(UserVO objUserVO) throws EMDException, Exception;
	
	public int insertRepresentative(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception;
	
	public int complete(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception;
	
	//for approve and reject all sections
	
	public int sysEngrSecDetails(ChangeRequest1058VO objChangeRequest1058Vo) throws EMDException, Exception;
	
	public int operationsSecDetails(ChangeRequest1058VO objChangeRequest1058Vo) throws EMDException, Exception;
	
	public int financeSecDetails(ChangeRequest1058VO objChangeRequest1058Vo) throws EMDException, Exception;
	
	public int programMgrSecDetails(ChangeRequest1058VO objChangeRequest1058Vo) throws EMDException, Exception;
	
	public int proposalMgrSecDetails(ChangeRequest1058VO objChangeRequest1058Vo) throws EMDException, Exception;

	// for searchrequest1058
	public ArrayList fetch1058Status(ChangeRequest1058VO  objChangeRequest1058VO) throws EMDException, Exception;
	
	public ArrayList fetchLsdbClauseDetails(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception;
	
	public ArrayList fetchNLsdbClauseDetails(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception;
	
	
	//Added by @rr108354 starts here
	//for Requested Deatils,Requested Specification Section,Requested Clause Change
	public ArrayList fetch1058RequestCategories(LoginVO objLoginVo) throws EMDException, Exception;
	
	public ArrayList fetch1058RequestTypes(LoginVO objLoginVo) throws EMDException, Exception;
	
	public ArrayList fetch1058ClauseChangeTypes(LoginVO objLoginVo) throws EMDException, Exception;
	
	public ArrayList uploadAttachment(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception;
	
	public ArrayList deleteAttachment(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception;
	
	public ArrayList fetchOrderSubSections1058(SectionVO objSectionVo) throws EMDException, Exception;
	
	public ArrayList fetchOrderSections1058(SectionVO objSectionVo) throws EMDException, Exception;
	
	public ArrayList fetchComponentGrpMap1058(ComponentVO objComponentVO) throws EMDException, Exception;
	
	public int saveClause(ClauseVO objClauseVO) throws EMDException, Exception;
	
	public int updateClause(ClauseVO objClauseVO) throws EMDException, Exception;
	
	public int saveDeleteClause(ClauseVO objClauseVO) throws EMDException, Exception;
	
	public int updateDeleteClause(ClauseVO objClauseVO) throws EMDException, Exception;
		
	public int deleteSelectedClause(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception;
	
	public int updateClausesToSpec(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception;
	
	public ArrayList reviseSelectedClause(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception;
	
	//Added by @rr108354 ends here
	
	public int update1058details(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception;
	
	public int insert1058ActionRecord(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception;
	
	//Added for CR-117
	
	public int uploadLegacyReport(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception;
	
	public int delete1058Request(ChangeRequest1058VO objChangeRequest1058Vo)throws EMDException, Exception;
	
	//Added for CR-120
	public ArrayList fetchIssuedByUserList(UserVO objUserVO) throws EMDException, Exception;
	
	//Added for CR-126
	
	public int updateLegacyReport(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception;

	
	public ArrayList fetch1058ReportDetails(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception;
	//Added for CR_127
	public ArrayList fetch1058PendingReportDetails(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception;
	
	public ArrayList fetch1058MdlClaChanges(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception;
	
	public int importModelClaChanges(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception;
	
	//Added for CR_130
	public ArrayList fetchSubSectionList(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception;
	
	public int importModelSubSectionChanges(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception;
	
	public ArrayList fetch1058SubSectionList(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception;
	
	//Added for CR-134
	public ArrayList fetch1058TransitionReportDetails(ChangeRequest1058VO objChangeRequest1058VO) throws EMDException, Exception;
}
