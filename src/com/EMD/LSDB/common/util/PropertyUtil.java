/*
 * Created on Jun 14, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.EMD.LSDB.common.logger.LogUtil;

/**
 * @author MM57219
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class PropertyUtil {
	public static ApplicationUtil applicationconfig = ApplicationUtil
	.getInstance();
	
	public static Properties objErrorProperties;
	
	public static Properties objTableHeaderProperties;
	
	/*
	 * public static String getQuery(String key){ String query=""; try{
	 * Properties properties=new Properties(); FileInputStream io=new
	 * FileInputStream(applicationconfig.getDbqueriespath());
	 * properties.load(io); query=properties.getProperty(key); io.close(); }
	 * catch(IOException e){ LogUtil.logMessage("IO Exception"+e.getMessage()); }
	 * catch(Exception e){ LogUtil.logMessage("General
	 * Exception"+e.getMessage()); } return query; }
	 */
	
	public static String getErrorMessage(String strKey) {
		if (objErrorProperties == null) {
			objErrorProperties = LoadProperties(ApplicationUtil.getInstance()
					.getErrorpath(), objErrorProperties);
		}
		return objErrorProperties.getProperty(strKey);
		
	}
	
	public static Properties LoadProperties(String strPropertiesPath,
			Properties objProperty) {
		try {
			objProperty = new Properties();
			FileInputStream objFileInputStream = new FileInputStream(
					strPropertiesPath);
			objProperty.load(objFileInputStream);
			objFileInputStream.close();
		}
		
		catch (IOException e) {
			LogUtil.logMessage("IO Exception" + e.getMessage());
		} catch (Exception objException) {
			LogUtil.logMessage("General Exception" + objException.getMessage());
		}
		
		return objProperty;
	}
}