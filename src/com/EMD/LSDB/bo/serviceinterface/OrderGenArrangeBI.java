package com.EMD.LSDB.bo.serviceinterface;

import java.util.ArrayList;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.GenArrangeVO;

/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the business definition methods for the Order
 *          GeneralArrangement
 ******************************************************************************/
public interface OrderGenArrangeBI {
	
	public ArrayList fetchGenArrImages(GenArrangeVO objGenArrangeVO)
	throws EMDException, Exception;
	//commited by CR_101
	//public int updateImage(GenArrangeVO objGenArrangeVO) throws EMDException,Exception;
	
	public int updateNotes(GenArrangeVO objGenArrangeVO) throws EMDException,
	Exception;
	//Added For CR_101
	//Added the name  UpdateMdlGenArgmntView
	public int updateOrdGenArgmntViewDtls(GenArrangeVO objModelGenArrangeVO)
	throws EMDException, Exception;
//	DELETE the MdlGenArgmntView
	public int deleteOrdGenArgmtView(GenArrangeVO objModelGenArrangeVO)
	throws EMDException, Exception;
	public int uploadOrdGenArgmntViewDtls(GenArrangeVO objModelGenArrangeVO)
	throws EMDException, Exception;
	
}
