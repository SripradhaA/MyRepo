/**
 * 
 */
package com.EMD.LSDB.bo.common;

import com.EMD.LSDB.bo.serviceinterface.FileUploadBI;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.common.FileUploadDAO;
import com.EMD.LSDB.vo.common.FileVO;

/**
 * @author PS57222
 * 
 */
public class FileUploadBO implements FileUploadBI {
	
	private FileUploadBO() {
		
	}
	
	public static FileUploadBO objFileUploadBO;
	
	public static FileUploadBO getInstance() {
		
		if (objFileUploadBO == null) {
			objFileUploadBO = new FileUploadBO();
		}
		return objFileUploadBO;
	}
	
	/***************************************************************************
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objFileVO
	 *            This object is used for Download Image From Database
	 * @return FileVO It has File contentType and Image
	 * @throws EMDException
	 **************************************************************************/
	public FileVO downloadImage(FileVO objFileVO) throws EMDException,
	Exception {
		
		LogUtil.logMessage("Enters Into FileUploadBO:downloadImage");
		
		try {
			
			objFileVO = FileUploadDAO.downloadImage(objFileVO);
		} catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into FileUploadBO:downloadImageException ..."
					+ objExp.getMessage());
			
			throw new Exception(objExp.getMessage());
		}
		return objFileVO;
	}
	
}
