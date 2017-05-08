package com.EMD.LSDB.common.customTags; 
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.net.InetAddress;
import com.EMD.LSDB.common.constant.ApplicationConstants;

public class AppServerIPTagHandler extends TagSupport {

public int doStartTag() throws JspException { 
	try {   
			InetAddress thisIp =InetAddress.getLocalHost();
			String strIPAddess=thisIp.getHostAddress();
			//Modified for CR_106 JG101007
			//Updated for CR-121
			if(strIPAddess.equalsIgnoreCase(ApplicationConstants.SERVER_IP_PROD))
				strIPAddess=ApplicationConstants.SERVER_PROD;
			else if(strIPAddess.equalsIgnoreCase(ApplicationConstants.SERVER_IP_QA))
				strIPAddess=ApplicationConstants.SERVER_QA;
			else
				strIPAddess=ApplicationConstants.SERVER_DEV;
			//Updated for CR-121 ends here
			
			pageContext.getOut().write(strIPAddess);

		} catch (IOException e) {   
			e.printStackTrace();         
		}   
		return SKIP_BODY;     
	}
}