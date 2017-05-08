/*
 * Created on Jun 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.common.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionServlet;

import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.framework.notificationservice.ScheduleController;
import com.EMD.LSDB.common.logger.LogLevel;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.common.resourceloader.EMDEnvLoader;

/**
 * @author mm57219
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class EMDServlet extends ActionServlet {
	
	public void init(ServletConfig  obj) throws ServletException {
		super.init(obj);
		
		try {
			
			EMDEnvLoader objEnvLoader = EMDEnvLoader.getInstance();
			
			objEnvLoader.loadEnvEntries();
			//ScheduleController emdScheduler = new ScheduleController();
			//Added For CR_108 -title name changed in the tab
			String strHeaderEnvInfo = (String) objEnvLoader.getEnvEntryValue(ApplicationConstants.HEADER_ENV_INFO);
			obj.getServletContext().setAttribute("HEADER",strHeaderEnvInfo);
			
		} catch (ApplicationException objApplicationException) {
			ErrorInfo errorInfo = objApplicationException.getErrorInfo();
			StringWriter objStringWriter = new StringWriter();
			PrintWriter objPrintWriter = new PrintWriter(objStringWriter);
			objApplicationException.printStackTrace(objPrintWriter);
			String message = objStringWriter.toString();
			LogUtil.logMessage(message, LogLevel.ERROR);
			try {
				objStringWriter.close();
				objPrintWriter.close();
			} catch (IOException objIOException) {
				LogUtil.logMessage("IOException in ErroUtil - logMessage() : "
						+ objIOException);
			}
			
		}
		
		/*
		 * catch ( DataAccessException me ) { me.printStackTrace ();
		 * StringWriter sw = new StringWriter (); PrintWriter pw = new
		 * PrintWriter ( sw ); me.printStackTrace ( pw ); String message =
		 * sw.toString (); LogUtil.logMessage ( message, LogLevel.ERROR ); try {
		 * sw.close (); pw.close (); } catch ( IOException p_IOException ) {
		 * LogUtil.logMessage ( "IOException in ErroUtil - logMessage() : " +
		 * p_IOException ); } }
		 */
		
		catch (Exception objException) {
			objException.printStackTrace();
			StringWriter objStringWriter = new StringWriter();
			PrintWriter objPrintWriter = new PrintWriter(objStringWriter);
			objException.printStackTrace(objPrintWriter);
			String message = objStringWriter.toString();
			LogUtil.logMessage(message, LogLevel.ERROR);
			try {
				objStringWriter.close();
				objPrintWriter.close();
			} catch (IOException objIOException) {
				LogUtil.logMessage("IOException in ErroUtil - logMessage() : "
						+ objIOException);
			}
		}
	}
	
	public void doGet(HttpServletRequest objRequest,
			HttpServletResponse objResponse) throws ServletException,
			IOException {
		
		doPost(objRequest, objResponse);
	}
	
	public void doPost(HttpServletRequest objRequest,
			HttpServletResponse objResponse) throws ServletException,
			IOException {
		
		super.doPost(objRequest, objResponse);
	}
	
	/*
	 * Added for calling destroy while undeploying
	 * 
	 */
	
	 public void destroy(){
		try{
			
			ScheduleController.shutdownScheduler(false);
		
		}catch(Exception objException){
				
			objException.printStackTrace();
		}
	}
	
}