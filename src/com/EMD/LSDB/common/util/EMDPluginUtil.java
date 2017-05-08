/*
 * Created on Jun 14, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.common.util;

import java.util.Properties;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

import com.EMD.LSDB.common.logger.LogUtil;

/**
 * @author MM57219
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class EMDPluginUtil implements PlugIn {
	
	public EMDPluginUtil() {
		
	}
	
	public void init(ActionServlet servlet, ModuleConfig moduleconfig) {
		String strErrorListPath;
		String strPath;
		String strTableHeader;
		
		Properties properties = null;
		try {
			
			/*
			 * strPath =
			 * servlet.getServletConfig().getInitParameter("ApplicationProperty");
			 * LogUtil.logMessage("obsolute Path:"+strPath);
			 * 
			 * strErrorListPath =
			 * servlet.getServletContext().getRealPath(strPath);
			 * 
			 * 
			 * LogUtil.logMessage("Contexname
			 * :"+servlet.getServletContext().getServletContextName());
			 * 
			 * ApplicationUtil objapplicationutil =
			 * ApplicationUtil.getInstance();
			 * objapplicationutil.setRealpath(servlet.getServletContext().getRealPath("/"));
			 * objapplicationutil.setErrorpath(strErrorListPath);
			 * LogUtil.logMessage("Real
			 * Path:"+objapplicationutil.getErrorpath());
			 */
			
		} catch (Exception e) {
			LogUtil.logMessage("Error in IO " + e.getMessage());
		}
		
	}
	
	public void destroy() {
		
	}
	
}