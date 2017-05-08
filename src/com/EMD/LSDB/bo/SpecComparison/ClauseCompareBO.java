package com.EMD.LSDB.bo.SpecComparison;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This business class is used to fetch orders and displays the
 *          clause comparison details in pop up page and excel.
 ******************************************************************************/

import java.util.ArrayList;

import com.EMD.LSDB.bo.serviceinterface.ClauseCompareBI;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.BusinessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.SpecComparison.ClauseCompareDAO;
import com.EMD.LSDB.dao.SpecMaintenance.OrderSectionDAO;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.OrderVO;
import com.EMD.LSDB.vo.common.SectionVO;
import com.EMD.LSDB.vo.common.SubSectionVO;

public class ClauseCompareBO implements ClauseCompareBI {
	
	/** private constructor for ClauseCompareBO */
	private ClauseCompareBO() {
	}
	
	/** This is a static instance used to hold the ClauseCompareBO instance */
	public static ClauseCompareBO objClauseCompareBO = null;
	
	/***************************************************************************
	 * This is a static method and it is used to return the ClauseCompareBO
	 * instance.
	 * 
	 * @param null
	 * @return ClauseCompareBO
	 **************************************************************************/
	
	public synchronized static ClauseCompareBO getInstance() {
		
		if (objClauseCompareBO == null) {
			objClauseCompareBO = new ClauseCompareBO();
		}
		return objClauseCompareBO;
	}
	
	/***************************************************************************
	 * This Method is used to fetch the list of section details.
	 * 
	 * @param java.util.ArrayList
	 * @return java.util.ArrayList
	 * @throws EMDException
	 **************************************************************************/
	
	public ArrayList fetchSectionsAndSubSections(ArrayList arlOrderList,
			String[] sectionNames) throws EMDException, Exception {
		
		ArrayList arlFinalList = new ArrayList();
		ArrayList arlSectionList = new ArrayList();
		try {
			
			LogUtil
			.logMessage("Entering ClauseCompareBO.fetchSectionsAndSubSections");
			int orderListSize = arlOrderList.size();
			
			/**
			 * The iteration is for fetching the section details for the
			 * selected order numbers.
			 */
			for (int orderListlength = 0; orderListlength < orderListSize; orderListlength++) {
				
				/** SectionVO instance is created. */
				SectionVO objSectionVO = new SectionVO();
				ArrayList arlSecSeqNo = (ArrayList)arlOrderList.get(orderListlength);
				
				/* Added in order to know Section sequence number size by cm68219 */
				int intSecSize = arlSecSeqNo.size();
				LogUtil.logMessage("arlSecSeqNo size :"+arlSecSeqNo.size());
				
				ArrayList arlWholeSections = new ArrayList();
				ArrayList arlSubSections = new ArrayList();
				if(arlSecSeqNo != null && arlSecSeqNo.size() > 0){
					
					for(int intSecSeqNo = 0 ; intSecSeqNo <  arlSecSeqNo.size(); intSecSeqNo++){
					
						objSectionVO = (SectionVO)arlSecSeqNo.get(intSecSeqNo);
				
				/** SectionVO instance is obtained from the ArrayList. commented by cm68219*/
				//objSectionVO = (SectionVO) arlOrderList.get(orderListlength);
				
				/**
				 * The complete section detail for the particular selected order
				 * is obtained as a ArrayList
				 */
				arlSubSections = OrderSectionDAO
				.fetchSectionDetails(objSectionVO);
				int subSectionListSize = arlSubSections.size();
				
				/** This iteration is for setting the clauses subsection wise.*/
				for (int count = 0; count < subSectionListSize; count++) {
					SubSectionVO objSubsectionVo = (SubSectionVO) arlSubSections
					.get(count);
					int subSectionSeqNo = objSubsectionVo.getSubSecSeqNo();
					
					/**The subsection name is formed along with the subsection code.*/
					String subSectionName = objSubsectionVo.getSubSecCode()
					+ " " + objSubsectionVo.getSubSecName();
					
					objSubsectionVo.setSubSecName(subSectionName);
					
					/*Commented for CR 75 on 27 April 09*
                     * String sectionName = sectionNames[orderListlength];
					 * objSubsectionVo.setSecName(sectionName);*/
					
					/*Added to differentiate between AllSections and Individual 
					         Section to print Section name on 27 April 09*/
					
					if(count==0){
					
					if(intSecSize ==ApplicationConstants.SECTION_SEQ_SIZE){
						objSubsectionVo.setSecName(sectionNames[orderListlength]);
					}else{
						String sectionName = objSectionVO.getSectionCode()+"."+objSectionVO.getSectionName();
						LogUtil.logMessage("Section Name with Code:"+sectionName);
						/*Commented by cm68219*/
						//String sectionName = sectionNames[orderListlength];
						objSubsectionVo.setSecName(sectionName);
					}
					
					}else
					{
						objSubsectionVo.setSecName("");
					}
					
					ArrayList clauseGroups = objSubsectionVo.getClauseGroup();
					int clauseGroupSize = clauseGroups.size();
					ArrayList subSectionClauseGroup = new ArrayList();
					
					/**this iteration is for getting all the clauses for a section and setting it to the respective subsections.*/
					for (int clauseCount = 0; clauseCount < clauseGroupSize; clauseCount++) {
						ClauseVO objClauseVo = (ClauseVO) clauseGroups
						.get(clauseCount);
						if (subSectionSeqNo == objClauseVo.getSubSectionSeqNo()) {
							subSectionClauseGroup.add(objClauseVo);
						}
					}
					objSubsectionVo.setClauseGroup(subSectionClauseGroup);
					
					/*We are differentiating Sections above itself no need of remove method
					*arlSubSections.remove(count);
					*arlSubSections.add(count, objSubsectionVo);
					*arlSubSections.add(objSubsectionVo);*/
					arlWholeSections.add(objSubsectionVo);
				}
					
				}
					arlSectionList.add(arlWholeSections);
				}
			
				
			}
			
			int orderSize = arlSectionList.size();
			SubSectionVO objEmptyVo = new SubSectionVO();
			
			if (orderSize == 2) {
				ArrayList orderOne = (ArrayList) arlSectionList.get(0);
				ArrayList orderTwo = (ArrayList) arlSectionList.get(1);
				int orderOneSize = orderOne.size();
				int orderTwoSize = orderTwo.size();
				
				/** Maximum of the two list is obtained. */
				int maximumSize = orderOneSize > orderTwoSize ? orderOneSize
						: orderTwoSize;
				/**
				 * This loop creates maximum number of list which will contains
				 * sequence wise subsection details.
				 */
				for (int count = 0; count < maximumSize; count++) {
					ArrayList arlSubsections = new ArrayList();
					if (count < orderOneSize) {
						arlSubsections.add(orderOne.get(count));
					} else {
						arlSubsections.add(objEmptyVo);
					}
					if (count < orderTwoSize) {
						arlSubsections.add(orderTwo.get(count));
					} else {
						arlSubsections.add(objEmptyVo);
					}
					arlFinalList.add(arlSubsections);
				}
			}
			
			if (orderSize == 3) {
				ArrayList orderOne = (ArrayList) arlSectionList.get(0);
				ArrayList orderTwo = (ArrayList) arlSectionList.get(1);
				ArrayList orderThree = (ArrayList) arlSectionList.get(2);
				int orderOneSize = orderOne.size();
				int orderTwoSize = orderTwo.size();
				int orderThreeSize = orderThree.size();
				
				/** Maximum of the three list is obtained. */
				int maximumSize = orderOneSize > orderTwoSize ? (orderOneSize > orderThreeSize ? orderOneSize
						: orderThreeSize)
						: (orderTwoSize > orderThreeSize ? orderTwoSize
								: orderThreeSize);
				
				/**
				 * This loop creates maximum number of list which will contains
				 * sequence wise subsection details.
				 */
				for (int count = 0; count < maximumSize; count++) {
					ArrayList arlSubsections = new ArrayList();
					if (count < orderOneSize) {
						arlSubsections.add(orderOne.get(count));
					} else {
						arlSubsections.add(objEmptyVo);
					}
					if (count < orderTwoSize) {
						arlSubsections.add(orderTwo.get(count));
					} else {
						arlSubsections.add(objEmptyVo);
					}
					if (count < orderThreeSize) {
						arlSubsections.add(orderThree.get(count));
					} else {
						arlSubsections.add(objEmptyVo);
					}
					arlFinalList.add(arlSubsections);
				}
			}
			
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentCompareBO:BusinessException"
					+ objErrorInfo.getMessage());
		}
		
		catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ComponentCompareBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ComponentCompareBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		
		return arlFinalList;
	}
	
	//Added for CR-135 starts here
	public ArrayList fetchOrderVsModelDetails(OrderVO objOrder) throws EMDException, Exception {
		LogUtil.logMessage("Enters into ClauseCompareBO:fetchOrderVsModelDetails");
		ArrayList arlOrderModelDet = null;
		
		try {
			arlOrderModelDet = ClauseCompareDAO.fetchOrderVsModelDetails(objOrder);
		} catch (BusinessException objBusExp) {
			ErrorInfo objErrorInfo = objBusExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ClauseCompareBO:BusinessException"
					+ objErrorInfo.getMessageID());
			// intStatusCode=Integer.parseInt(objErrorInfo.getMessageID());
			
		} catch (ApplicationException objAppExp) {
			ErrorInfo objErrorInfo = objAppExp.getErrorInfo();
			LogUtil
			.logMessage("enters into catch block in ClauseCompareBO:ApplicationException"
					+ objErrorInfo.getMessage());
			throw new Exception(objErrorInfo.getMessage());
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("enters into catch block in ClauseCompareBO:ApplicationException"
					+ objExp.getMessage());
			throw new Exception(objExp.getMessage());
		}
		return arlOrderModelDet;
	}
	//Added for Cr-135 ends here
	
}
