/*
 * Created on Jun 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.common.exception;

/**
 * @author mm57219
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

//package name
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.errorhandler.ErrorInfoDAO;
import com.EMD.LSDB.common.logger.LogLevel;
import com.EMD.LSDB.common.logger.LogUtil;

/*
 * 1. This class used to handle the LFOException and log the errors
 * into error file.
 * 2. It redirects the user into error page and shows the error message
 */
public class EMDExceptionHandler extends ExceptionHandler {
	
	/**
	 * Handle the EMDException and log the errors
	 * REDIRECT JSP - error.jsp
	 * @param exception
	 * @param config
	 * @param mapping
	 * @param formInstance
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(Exception exception, ExceptionConfig config,
			ActionMapping mapping, ActionForm formInstance,
			HttpServletRequest request, HttpServletResponse response)
	throws ServletException {
		
		LogUtil.logMessage("Inside ExceptionHandler ....");
		ActionForward forward = null;
		ActionError error = null;
		String property = null;
		
		/* Get the path for the forward either from the exception element
		 * or from the input attribute.
		 */
		String path = null;
		if (config.getPath() != null) {
			path = config.getPath();
		} else {
			path = mapping.getInput();
		}
		// Construct the forward object
		forward = new ActionForward(path);
		
		/* Figure out what type of exception has been thrown. The Struts
		 * AppException is not being used in this example.
		 */
		if (exception instanceof EMDException) {
			EMDException lEMDException = (EMDException) exception;
			
			if (lEMDException != null) {
				
				String message = null;
				ErrorInfo errorInfo = null;
				
				if (lEMDException.getException() != null
						&& lEMDException.getErrorInfo() != null) {
					
					ErrorInfoDAO errorInfoDAO = new ErrorInfoDAO();
					
					try {
						
						errorInfo = errorInfoDAO.getErrorInfo(lEMDException
								.getErrorInfo());
					} catch (DataAccessException dae) {
						StringWriter sw = new StringWriter();
						PrintWriter pw = new PrintWriter(sw);
						dae.printStackTrace(pw);
						message = sw.toString();
						LogUtil.logMessage(message, LogLevel.ERROR);
						try {
							sw.close();
							pw.close();
						} catch (IOException p_IOException) {
							LogUtil
							.logMessage("IOException in ErroUtil - logMessage() : "
									+ p_IOException);
						}
					}
					
					String applicationName = errorInfo.getLocation()
					.getApplicationName();
					String componentName = errorInfo.getLocation()
					.getComponentName();
					String fileName = errorInfo.getLocation().getClassName();
					String methodName = errorInfo.getLocation().getMethodName();
					String clientName = request.getRemoteHost();
					;
					//String serverName = "172.18.8.66";
					String errorType = errorInfo.getErrorType().toString();
					String errorMessage = errorInfo.getMessage();
					Exception e = null;
					if (lEMDException.getException() != null) {
						e = lEMDException.getException();
					}
					message = applicationName + "| " + componentName;
					message += "| " + fileName + "| " + methodName;// + "| "+ serverName;
					message += "| " + clientName + "| ";
					message += "| "
						+ getFormattedDateString(errorInfo.getTimeStamp());
					message += "| " + errorMessage;
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					//String printStack = e.getStackTrace().toString(); 
					e.printStackTrace(pw);
					message += "| " + sw.toString();
					LogLevel logLevel = LogLevel.get(errorInfo.getErrorLevel()
							.toString());

					LogUtil.logMessage(message, LogLevel.get(errorInfo
							.getErrorLevel().toString()));
					//request.setAttribute("status",errorInfo.getMessage());
					request.setAttribute("status", message);
					try {
						sw.close();
						pw.close();
					} catch (IOException p_IOException) {
						LogUtil
						.logMessage("IOException in ErroUtil - logMessage() : "
								+ p_IOException);
					}
					
				}
				
			} else if (lEMDException.getException() != null) {
				
				LogUtil.logError(lEMDException.getException());
				//LogUtil.logMessage(message,LogLevel.get(errorInfo.getErrorLevel().toString()));	
				request.setAttribute("status", lEMDException.getDescription());
			} else if (lEMDException.getErrorInfo() != null) {
				LogUtil.logError(lEMDException.getErrorInfo());
				request.setAttribute("status", lEMDException.getErrorInfo()
						.getMessage());
			}
		}
		
		else if (exception instanceof RuntimeException) {
			
			String message = null;
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			exception.printStackTrace(pw);
			message = sw.toString();
			LogUtil.logMessage(message, LogLevel.FATAL);
			//request.setAttribute("status",errorInfo.getMessage());
			request.setAttribute("status", message);
			//request.setAttribute("status","Problem accessing LFO please contact System Administrator ...");
			try {
				sw.close();
				pw.close();
			} catch (IOException p_IOException) {
				LogUtil.logMessage("IOException in ErroUtil - logMessage() : "
						+ p_IOException);
			}
		}
		
		return forward;
	}
	
	/**
	 * Format date object
	 * REDIRECT JSP - error.jsp
	 * @param p_timeStamp
	 * @return String
	 */
	private static String getFormattedDateString(Date p_timeStamp) {
		String LFormattedDate = null;
		if (p_timeStamp != null) {
			try {
				SimpleDateFormat LSimpleDateFormat = new SimpleDateFormat(
				"dd-MM-yyyy hh:mm:ss a z");
				LFormattedDate = LSimpleDateFormat.format(p_timeStamp);
			} catch (Exception PException) {
				LogUtil.logMessage("Error while formatting date string in lEMDExceptionHandler.java");
			}
		}
		return LFormattedDate;
	}
}
