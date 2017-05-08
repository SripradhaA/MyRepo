/*
 * Created on Jun 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.ClauseVO;
import com.EMD.LSDB.vo.common.ModelVo;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business definition methods for the Model
 ******************************************************************************/
public interface ModelBI {
	
	public ArrayList fetchModels(ModelVo objModelVO) throws EMDException,
	Exception;
	
	public int insertModel(ModelVo objModelVO) throws EMDException, Exception;
	
	public int updateModel(ModelVo objModelVO) throws EMDException, Exception;

	public int copyModel(ModelVo objModelVO) throws EMDException, Exception;
	
	//Added For CR-113 
	public ArrayList search(ClauseVO objClauseVO) throws EMDException,
	Exception;
}