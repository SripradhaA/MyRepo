/**
 * 
 */
package com.EMD.LSDB.dao.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.struts.action.ActionMapping;

import java.sql.Blob;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.vo.common.FileVO;

/**
 * @author PS57222
 * 
 */

public class FileUploadDAO extends EMDDAO {
	
	/***************************************************************************
	 * * * * This Method is used to DownLoad the images from the LSDB170_IMAGES
	 * table ImageSeqNo is passed from jsp it takes that as key and retrive the
	 * images from the table.
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param objFileVO
	 *            FileVO Contains imageSeqNo
	 * @return FileVO
	 * @throws EMDException
	 * 
	 **************************************************************************/
	
	public static FileVO downloadImage(FileVO objFileVO) throws EMDException {
		LogUtil.logMessage("Enters into FileUploadDAO:downloadImage");
		
		Connection objConnection = null;
		PreparedStatement objPreparedStatement = null;
		ResultSet objResultSet = null;
		Blob image = null;//Added for CR_91
		LogUtil.logMessage("ImageSeqNo in FileUploadDAO:downloadImage"
				+ objFileVO.getImageSeqNo());
		
		try {
			objConnection = DBHelper.prepareConnection();
			LogUtil.logMessage("Enter into FileUploadDAO Connection:"
					+ objConnection);
			
			objPreparedStatement = objConnection
			.prepareStatement(EMDQueries.Query_DisplayImage);
			objPreparedStatement.setInt(1, objFileVO.getImageSeqNo());
			objResultSet = objPreparedStatement.executeQuery();
			
			LogUtil
			.logMessage("ResultSet Value in FileUploadDAO:downloadImage:"
					+ objResultSet);
			//Added for CR_91 to give user the option to save image
			if (objResultSet.next())
			{
				
				image = objResultSet.getBlob(1);
				objFileVO.setContentType(objResultSet.getString(2));
				//Bought into if condition for CR - 135 PDF Attachments
				objFileVO.setFileStream(image.getBinaryStream());
				int len = (int) image.length();
				objFileVO.setFileLength(len);
				LogUtil.logMessage("length of image... "+ len);
			}
			//ENDs here
			/*
			 if (objResultSet.next()) {
				
				objFileVO = new FileVO();
				LogUtil.logMessage("Enters into resultset loop:.....");
				objFileVO.setFileStream(objResultSet
						.getBinaryStream(DatabaseConstants.LS170_IMG));
				objFileVO.setContentType(objResultSet
						.getString(DatabaseConstants.LS170_IMG_CONTENT_TYPE));
				
			}
			*/
			
		}
		
		catch (Exception objExp) {
			LogUtil
			.logMessage("Enters into FileUploadDAO:downloadImage exception block");
			ErrorInfo objErrorInfo = new ErrorInfo();
			objErrorInfo.setMessage(ApplicationConstants.LOG_USER + "-"
					+ objExp.getMessage());
			throw new ApplicationException(objExp, objErrorInfo);
			/*//Added for CR-135 PDF Attachments
			ActionMapping objActionMapping;
			String strForwardKey = ApplicationConstants.FAILURE;
			return objActionMapping.findForward(strForwardKey);*/

		}
		
		finally {
			try {
				if (objPreparedStatement != null) {
					objPreparedStatement.close();
				}
				DBHelper.closeSQLObjects(objResultSet, null, objConnection);
			}
			
			catch (Exception objExp) {
				LogUtil
				.logMessage("Enters into FileUploadDAO:downloadImage exception block");
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(ApplicationConstants.LOG_USER + "-"
						+ objExp.getMessage());
				throw new ApplicationException(objExp, objErrorInfo);
			}
			
		}
		
		return objFileVO;
		
	}
}
