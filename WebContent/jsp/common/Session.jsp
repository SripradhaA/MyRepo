<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<html:form action="/LogoutAction" method="post">

	<TABLE BORDER=0 WIDTH="100%" align="center" CELLPADDING="0"
		CELLSPACING="0">

		<TR height="350">
			<TD WIDTH="25%" ALIGN="center">

			<TABLE BORDER=1 bgcolor="#F8F8F8" bordercolor="#5780ae" WIDTH="60%"
				ALIGN=CENTER CELLPADDING="0" CELLSPACING="0">
				<TR height="100">
					<TD WIDTH="28%" ALIGN=center CLASS="formSubHeading">Your Session
					has expired. Please login again.<BR>
					<BR>
					<BR>

					<html:button property="method" styleClass="buttonStyle"
						value="Login" onclick="javascript:sessionOutLogin()" />&nbsp;&nbsp;&nbsp;
					<html:button property="method" styleClass="buttonStyle"
						value="Close" onclick="javascript:fnclose()" /></TD>
				</TR>
			</TABLE>

			</TD>
		</TR>
	</TABLE>
</html:form>
