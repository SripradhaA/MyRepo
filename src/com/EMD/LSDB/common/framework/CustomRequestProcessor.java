/*
 * Created on Jun 14, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.common.framework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.RequestProcessor;

/**
 * @author MM57219
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CustomRequestProcessor extends RequestProcessor {
	
	protected boolean processPreprocess(HttpServletRequest request,
			HttpServletResponse response) {
		
		HttpSession session = request.getSession(false);
		
		if (request.getServletPath().equals("/Login.do"))
			return true;
		//Check if userName attribute is there is session. If yes then that means user has allready loggedIn
		if (session != null && session.getAttribute("UserVO") != null)
			return true;
		else {
			try {
				//If no redirect user to login Page
				request.getRequestDispatcher("/jsp/common/login.jsp").forward(
						request, response);
			} catch (Exception ex) {
			}
		}
		return false;
	}
	
	protected void processContent(HttpServletRequest request,
			HttpServletResponse response) {
		//Check if user is requesting ContactImageAction
		// if yes then set image/gif as content type
		
		super.processContent(request, response);
	}
	
}
