/*
 * Created on Jun 14, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.RowSet;

import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.constant.DatabaseConstants;
import com.EMD.LSDB.common.exception.ApplicationException;
import com.EMD.LSDB.common.exception.DataAccessException;
import com.EMD.LSDB.common.exception.EMDException;
import com.EMD.LSDB.common.logger.LogUtil;
import com.EMD.LSDB.dao.common.DBHelper;
import com.EMD.LSDB.dao.common.EMDQueries;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;

/**
 * @author MM57219
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ApplicationUtil {
	private static ApplicationUtil uniqueInstance = null;
	
	private String realpath = null;
	
	private String errorpath;
	
	private String tableheader;
	
	/**
	 * @return Returns the tableheader.
	 */
	public String getTableheader() {
		return tableheader;
	}
	
	/**
	 * @param tableheader
	 *            The tableheader to set.
	 */
	public void setTableheader(String tableheader) {
		this.tableheader = tableheader;
	}
	
	/**
	 * @return Returns the errorpath.
	 */
	public String getErrorpath() {
		return errorpath;
	}
	
	/**
	 * @param errorpath
	 *            The errorpath to set.
	 */
	public void setErrorpath(String errorpath) {
		this.errorpath = errorpath;
	}
	
	private Properties properties = null;
	
	public static ApplicationUtil getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new ApplicationUtil();
		return uniqueInstance;
	}
	
	public void setRealpath(String realpath) {
		this.realpath = realpath;
	}
	
	public String getRealPath() {
		return this.realpath;
	}
	
	public static String trim(String strFieldName) {
		
		if (strFieldName != null && !"".equals(strFieldName)) {
			
			strFieldName = strFieldName.trim();
		}
		return strFieldName;
	}
	
	public static Timestamp getCurrentTimeStamp() throws DataAccessException,
	ParseException {
		
		Connection connection = null;
		RowSet rowSet = null;
		List parameters = new ArrayList();
		String currentDate = null;
		java.text.SimpleDateFormat objSimpleDateFormat = new SimpleDateFormat(
		"MM/dd/yyyy hh:mm:ss a");
		try {
			/* get Database connection */
			connection = DBHelper.prepareConnection();
			rowSet = DBHelper.executeQuery(connection, EMDQueries.GET_SYS_DATE,
					parameters);
			if (rowSet.next()) {
				currentDate = rowSet
				.getString((DatabaseConstants.CURRENT_DATE ));
			}
			rowSet.close();
		} catch (SQLException p_sqlException) {
			throw new DataAccessException();
		} catch (ApplicationException AppExp) {
			throw new DataAccessException();
		} catch (Exception Exp) {
			throw new DataAccessException();
		}
		
		finally {
			DBHelper.closeConnection(connection);
		}
		java.util.Date objDate = objSimpleDateFormat.parse(currentDate);
		Timestamp objSqlDate = new java.sql.Timestamp(objDate.getTime());
		return objSqlDate;
	}
	
	public static String convertStringArrayToString(String[] strInput) {
		String strinString = "";
		String strToString = "";
		for (int counter = 0; counter < strInput.length; counter++) {
			if (strInput[counter] != null) {
				if (counter == 0) {
					strinString = strInput[counter];
					strToString = strinString;
				} else {
					strinString = strToString + ApplicationConstants.DELIMITER
					+ strInput[counter];
					strToString = strinString;
				}
			}
		}
		return strToString;
	}
	
  //Added for CR_88 by Sd41630 
	public static String strConvertToHTMLFormat(String strClauseDesc) throws EMDException,
	Exception {
		LogUtil.logMessage("Enters into ApplicationUtil:strConvertToHTMLFormat");
		String clausedesc = strClauseDesc;
		String fileName="Sample.html";
		/*
		String clausedescP1 = clausedesc.replaceAll("<span style=\"text-decoration: underline;\">","<u>");
		String clausedescP2 = clausedescP1.replaceAll("</span>","</u>");*/										
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
			out.write(clausedesc);
			out.close();
	         }catch (Exception objExp) {
	 			LogUtil
				.logMessage("Enters into catch block in ApplicationUtil:strConvertToHTMLFormat"
						+ objExp.getMessage());
				throw new Exception(objExp.getMessage());
			}	
		return fileName;
	}
	
	//Added for CR_88 by Sd41630 - Ends here
//	Added For CR_93
	/***************************************************************************
	 * This method is for getting Clause Table Data Column Count
	 * 
	 * @author Satyam Computer Services Ltd
	 * @version 1.0
	 * @param ArrayList
	 * @return ArrayList
	 * @throws Exception
	 * @author SD41630
	 **************************************************************************/
	public static  int getTableDataColumnsCount(ArrayList arlTableCol)
			throws Exception {

		int colCount = 0;
		int intCnt = 0;
		try {

			for (int x = 0; x < arlTableCol.size(); x++) {
				colCount = 0;
				ArrayList arlData = (ArrayList) arlTableCol.get(x);

				for (int i = 0; i < arlData.size(); i++) {

					if (arlData.get(i) != null && !("".equals(arlData.get(i)))) {

						colCount = i;
						// colCount = colCount + 1;

					}

				}
				if (intCnt < colCount) {
					intCnt = colCount;
				}

			}
						
			colCount=intCnt+1;

		} catch (Exception objExp) {

			throw objExp;

		} finally {
				
			try {
				// closeSQLObjects(rsTableCol, null, null);
			} catch (Exception objExp) {
				LogUtil.logMessage("ApplicationUtil.java+getTableDataColumnsCount");
				throw objExp;
			}
		}

		return colCount;
	}
	/***************************************************************************
	 * This method is for getting fileName without extension
	 * 
	 * @version 1.0
	 * @param ArrayList
	 * @return ArrayList
	 * @throws Exception
	 * @author RJ85495
	 * gets filename without extension Added for fixing pre-existing bug: 08-03-2011
	 **************************************************************************/

	public static String getFileName(String fileName) throws Exception{ 
		LogUtil.logMessage("in ApplicationUtil.getFileName method");
	    int dot = fileName.lastIndexOf('.');
	    return fileName.substring(0, dot);
	  }
	
//	Added for Production issue dated 08-July-2014 	
	/*********************************************************************************************
	 * From CLOB to String
	 * @return string representation of clob
	 *********************************************************************************************/
	public static String clobToString(java.sql.Clob data)throws Exception
	{
	    final StringBuffer sb = new StringBuffer();

	    try
	    {
	        final Reader         reader = data.getCharacterStream();
	        final BufferedReader br     = new BufferedReader(reader);

	        int b;
	        while(-1 != (b = br.read()))
	        {
	            sb.append((char)b);
	        }

	        br.close();
	    }
	    catch (SQLException e)
	    {
	    	LogUtil.logMessage("SQL. Could not convert CLOB to string"+e);
	        return e.toString();
	    }
	    catch (IOException e)
	    {
	    	LogUtil.logMessage("IO. Could not convert CLOB to string"+e);
	        return e.toString();
	    }

	    return sb.toString();
	}
	

	//Added for CR_126 to bring HTML clause descriptions to Excel
	/***************************************************************************
	 * This method is used to get the Clause Description using HTML Parser
	 * 
	 * @author Tech Mahindra Ltd
	 * @version 1.0
	 * @param String
	 * @return String
	 * @throws Exception
	 **************************************************************************/
	
	public static String getRefinedClauseDesc(String strClauseDesc) throws Exception
	{
		String strRefinedClauseDesc = "";
		Paragraph paraClauseDesc = new Paragraph();
	    paraClauseDesc.setKeepTogether(true);
		if (!strClauseDesc.startsWith("<p>"))
			strClauseDesc = strClauseDesc.replaceAll("\n","<br/>");
		strRefinedClauseDesc=ApplicationUtil.strConvertToHTMLFormat(strClauseDesc);
		StyleSheet styles = new StyleSheet();
	    styles.loadTagStyle("p","size","12px");
	    styles.loadTagStyle("p","face","Times");
	    styles.loadTagStyle("strong","font-weight", "bold"); 
	    styles.loadTagStyle("em","font-style", "italic");
		ArrayList p = HTMLWorker.parseToList(new FileReader(strRefinedClauseDesc), styles);
	    for (int k1 = 0; k1 < p.size(); ++k1) 
			{
	    	paraClauseDesc.add((Element) p.get(k1));
		    }
	    strRefinedClauseDesc = paraClauseDesc.getContent();
		LogUtil.logMessage("returning from strhtmlclauseconvert :");
		return strRefinedClauseDesc;
	}
}