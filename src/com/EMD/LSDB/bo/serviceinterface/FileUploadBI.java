/**
 * 
 */
package com.EMD.LSDB.bo.serviceinterface;

import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.vo.common.FileVO;

/**
 * @author PS57222
 * 
 */
public interface FileUploadBI {
	
	public FileVO downloadImage(FileVO objFileVO) throws EMDException,
	Exception;
}
