/**
 * 
 */
package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.GenArrangeVO;

/**
 * @author PS57222
 * 
 */
public interface ModelGenArrangeBI {
	//Added the name  UpdateMdlGenArgmntView
	public int updateMdlGenArgmntViewDtls(GenArrangeVO objModelGenArrangeVO)
	throws EMDException, Exception;
//	DELETE the MdlGenArgmntView
	public int deleteMdlGenArgmtView(GenArrangeVO objModelGenArrangeVO)
	throws EMDException, Exception;
	public int uploadMdlGenArgmntViewDtls(GenArrangeVO objModelGenArrangeVO)
	throws EMDException, Exception;
	
	public ArrayList fetchGenArrImages(GenArrangeVO objGenArrangeVO)
	throws EMDException, Exception;
	
	public int modifyGenArrNotes(GenArrangeVO objModelGenArrangeVO)
	throws EMDException, Exception;
}
