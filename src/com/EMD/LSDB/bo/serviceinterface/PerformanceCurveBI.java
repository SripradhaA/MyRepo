/**
 * 
 */
package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.PerformanceCurveVO;

/**
 * @author VV49326
 * 
 */
public interface PerformanceCurveBI {
	
	public int uploadPerfCurveImage(PerformanceCurveVO objModelGenArrangeVO)
	throws EMDException;
	
	public ArrayList fetchPerfCurveImages(PerformanceCurveVO objGenArrangeVO)
	throws EMDException;
	
	public int deletePerfCurveImage(PerformanceCurveVO objModelGenArrangeVO)
	throws EMDException;
	
}
