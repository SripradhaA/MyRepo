/*
 * Created on May 5, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.AppendixVO;

/**
 * @author PS57222
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface AppendixBI {
	
	public ArrayList fetchImage(AppendixVO objAppendixVO) throws EMDException,
	Exception;
	
	public int addImage(AppendixVO objAppendixVO) throws EMDException,
	Exception;
	
	public int modifyImageName(AppendixVO objAppendixVO) throws EMDException,
	Exception;
	
	public int deleteImage(AppendixVO objAppendixVO) throws EMDException,
	Exception;
	
	public int saveMappings(AppendixVO objAppendixVO) throws EMDException,
	Exception;
	
	public ArrayList fetchModelAppendixImages(AppendixVO objAppendixVO)
	throws EMDException, Exception;
}