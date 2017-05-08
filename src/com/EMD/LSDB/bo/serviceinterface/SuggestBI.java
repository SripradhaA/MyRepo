/*
 * Created on Jun 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.SuggestVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business definition methods for the Model
 ******************************************************************************/
public interface SuggestBI {
	
	public int submitSuggestion(SuggestVO objSuggestVO) throws EMDException, Exception;
	
	public ArrayList fetchSuggestions(SuggestVO objSuggestVO) throws EMDException, Exception;
	
	public ArrayList fetchSuggestionStatus(SuggestVO objSuggestVO) throws EMDException, Exception;
	
	public int updateSuggestion(SuggestVO objSuggestVO) throws EMDException, Exception;
	
	public int deleteAttachment(SuggestVO objSuggestVO) throws EMDException, Exception;
}