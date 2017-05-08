<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<HEAD>
<SCRIPT LANGUAGE="JavaScript" SRC="js/CreateChangeRequest.js"></SCRIPT>
</HEAD>

<html:form action="/CreateRequestIDAction" method="post">

	<TABLE WIDTH="100%" BORDER="0" CELLPADDING="0" CELLSPACING="0">
		<TR>
			<TD>&nbsp;</TD>
		</TR>
		<TR>
			<TD WIDTH="60%" CLASS="dashBoardSubHeading" ALIGN="center"><bean:message
				key="Application.Screen.CreateChangeRequest" /></TD>
		</TR>

		<TR>
			<TD>&nbsp;</TD>
		</TR>

	</TABLE>

	<%--Table Updated for CR-121 for server side error message tooltip --%>
	<table border="0" cellpadding="0" cellspacing="0" width="20%" align="center">
		   <tr>
	          <td nowrap="nowrap">
				<div class="errorlayerhide" id="errorlayer"
					style="position:relative; overflow: auto; height:0; visibility:hidden; z-index: 2">
				</div>
			  </td>
		   </tr>
	</table>

	<TABLE WIDTH="100%" BORDER="0" CELLPADDING="0" CELLSPACING="0">

		<TR>
			<TD>
			<TABLE ALIGN="center" BORDER="0" WIDTH="50%">
				<TR><!-- Modified For Alignment CR_80 by RR68151  -->
					<TD width="10%">&nbsp;</TD>
					<TD width="30%">&nbsp;</TD>
					<TD COLSPAN="6">&nbsp;</TD>
				</TR>
				<TR>
					<TD>&nbsp;</TD>
					<TD nowrap CLASS="headerbgcolor">Short Description&nbsp;<font
						size=2 color="red">*</font></TD>
					<TD COLSPAN="6">&nbsp;<html:textarea property="shortDescription"
						rows="2" cols="48" /></TD>
				</TR>
			</TABLE>
			</TD>
		</TR>
	</TABLE>

	<TABLE BORDER="0" WIDTH="30%" ALIGN="CENTER">
		<TR>
			<TD>&nbsp;</TD>
		</TR>
		<TR>
			<TD>&nbsp;</TD>
			<TD WIDTH="12%" ALIGN="center"><INPUT CLASS="buttonStyle"
				TYPE="button" value="Create CR Form"
				onclick="javascript:fnCreateChangeRequestID()"></TD>
			<TD WIDTH="12%">&nbsp;</TD>
			<TD>&nbsp;</TD>
		</TR>
	</TABLE>
	<TABLE BORDER="0" WIDTH="30%" ALIGN="CENTER" height="60%">
		<TR>
			<TD>&nbsp;</TD>
		</TR>
		<TR>
			<TD>&nbsp;</TD>

		</TR>
	</TABLE>


</html:form>
