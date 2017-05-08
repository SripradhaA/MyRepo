package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.ModelAppendixVO;

public interface ModelAppendixBI {
	
	public ArrayList fetchAppendixImages(ModelAppendixVO objModelAppendixVO)
	throws EMDException, Exception;
	
	public int uploadAppendixImage(ModelAppendixVO objModelAppendixVO)
	throws EMDException, Exception;
	
	public int deleteAppendixImage(ModelAppendixVO objModelAppendixVO)
	throws EMDException, Exception;
	
	public int updateAppendixImage(ModelAppendixVO objModelAppendixVO)
	throws EMDException, Exception;
	public int saveModelClaMappings(ModelAppendixVO objModelAppendixVO)
	throws EMDException, Exception;
	
}
