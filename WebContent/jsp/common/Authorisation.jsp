
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<html:form action="/LogoutAction" method="post">
	<TABLE BORDER=0 WIDTH="100%" CELLPADDING="0" CELLSPACING="0">

		<TR height="350">
			<TD WIDTH="25%" ALIGN=center CLASS="formSubHeading"><%
						String ScreenName=(String)request.getAttribute("ScreenName");
						System.out.println("ScreenName:"+ScreenName);
						// Added for CR-112
						if(ScreenName == null) { ScreenName = "Suggestion"; }
						//Added for CR-112 Ends here
						out.println("You are not authorised to view" +" "+ ScreenName +" "+ "Screen. Please contact EMDAdministrator");
				%></TD>
		</TR>
	</TABLE>
</html:form>






