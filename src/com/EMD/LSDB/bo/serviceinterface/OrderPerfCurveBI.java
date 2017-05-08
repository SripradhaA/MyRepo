package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.PerformanceCurveVO;

public interface OrderPerfCurveBI {
	
	public ArrayList fetchPerfCurveImages(PerformanceCurveVO objGenArrangeVO)
	throws EMDException, Exception;
	
	public int uploadPerfCurveImage(PerformanceCurveVO objModelGenArrangeVO)
	throws EMDException, Exception;
	
	public int deletePerfCurveImage(PerformanceCurveVO objModelGenArrangeVO)
	throws EMDException, Exception;
	
	/**
	 * Added for LSDB_CR-63 on 10-Dec-08 by ps57222
	 * 
	 */
	
	public int modifyPerfCurveImageName(PerformanceCurveVO objModelGenArrangeVO)
	throws EMDException, Exception;
	
//	Added for CR_121 Rearrange Order Performance Curve
	public int reArrangePerfCurve(PerformanceCurveVO objPerformanceCurveVO)
	throws EMDException, Exception;
	
}