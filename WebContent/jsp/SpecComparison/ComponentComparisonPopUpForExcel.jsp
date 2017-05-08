<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page contentType="application/vnd.ms-excel"%>
<% response.setHeader("Content-Disposition", "inline; filename=\"ComponentComparison.xls\""); %>
<html:html>
<HEAD>
<TITLE>Component Comparison/Report</TITLE>
<BODY>
<html:form action="/compareComponentAction" method="post">
	<br>
	<br>
	<!-- Changed for Heading display based on the selection of orders-->
	<bean:define id="ordrsize" name="ComponentCompareForm" property="orderListSize"/>
	
	
		
		<TABLE BORDER=0 WIDTH="100%" BORDERCOLOR="#5780ae" ALIGN="center">
			<TR>
				<TD WIDTH="100%" CLASS="dashBoardSubHeading" ALIGN="center"
					colspan="<%=(Integer.valueOf(String.valueOf(ordrsize))).intValue()*4%>"><font size="2"><b>Component Comparison/Report</b></font></TD>
			</TR>
			<TR>
		</TABLE>
	
	
	<TABLE WIDTH="100%" BORDER="0" BORDERCOLOR="#5780ae">


		<TR>
			<logic:iterate id="ordListId" name="ComponentCompareForm"
				property="selectedOrderList" type="com.EMD.LSDB.vo.common.OrderVO">
				<!-- <TD CLASS="noborder2b" >&nbsp;</TD> -->
				<TD WIDTH="20%">
				<TABLE BORDER=1 BORDERCOLOR="#5780ae" WIDTH="100%">
					<TR>
						<TD CLASS="noborder2b" width="40%"><font size="2"><b>Order Number</b></font></TD>
						<TD CLASS="noborder2c" colspan="3" align="left"><font size="2"><bean:write
							name="ordListId" property="orderNo" /></font></TD>
					</TR>
					<tr>
						<TD CLASS="noborder2b" width="40%"><font size="2"><b>Model</b></font></TD>
						<TD CLASS="noborder2c" colspan="3"><font size="2"><bean:write
							name="ordListId" property="modelName" /></font></TD>
					</TR>
					<TR>
						<TD CLASS="noborder2b" width="40%"><font size="2"><b>Customer Name</b></font></TD>
						<TD CLASS="noborder2c" colspan="3"><font size="2"><bean:write
							name="ordListId" property="customerName" /></font></TD>
					</tr>
					<tr>
						<TD CLASS="noborder2b" width="40%"><font size="2"><b>Spec Status</b></font></TD>
						<TD CLASS="noborder2c" colspan="3"><font size="2"><bean:write
							name="ordListId" property="specTypeName" /></font></TD>
					</TR>
				</TABLE>
				</TD>
			</logic:iterate>
		</TR>

		<TR>
			<TD>&nbsp;</TD>
		</TR>
	</TABLE>
	<TABLE BORDER=0 WIDTH="100%%">

		<logic:iterate id="componentCompareId" name="ComponentCompareForm"
			property="compareOrderList" indexId="count">
			<TR>
				
				<logic:iterate id="sectionId" name="componentCompareId">
					<TD WIDTH="5%" style="align:top">
					<!--<logic:notPresent name="sectionId"
						property="sectionName">
						<TABLE BORDER=0 WIDTH="100%">
							</logic:notPresent>-->
							<logic:present name="sectionId" property="subSectionName">
								<TABLE BORDER=1 BORDERCOLOR="#5780ae" WIDTH="100%">
									</logic:present>
<%-- added For CR_106--%>
										   <logic:equal name ="count" value="0"> 
													<TR>
														<TD WIDTH="10%" height="10%" CLASS="table_heading"><font
															size="2"><b>Component Group</b></font></TD>
														<TD WIDTH="30%" colspan="3" height="10%"
															CLASS="table_heading"><font size="2"><b>Component</b></font></TD>
													</TR>
													</logic:equal>

														<logic:present name="sectionId" property="sectionName">
															<TR>
																<TD colspan="4" height="10%" CLASS=lightblue ALIGN="center"><font
																	size="2"><b><bean:write name="sectionId"
																	property="sectionName" /></b></font></TD>
															</TR>
														</logic:present>


															<TR>
																<TD colspan="4" height="10%" CLASS=noborder2 ALIGN="center"><font
																	size="2"><b><bean:write name="sectionId"
																	property="subSectionName" /></b></font></TD>
															</TR>
											<logic:notEmpty name="sectionId" property="componentVO">
											
														<logic:iterate name="sectionId" id="componentDescId"
															property="componentVO">
															<TR>
				
																<TD WIDTH="10%" height="10%" CLASS="borderbottomleft1"><bean:write
																	name="componentDescId" property="componentGroupName" /></TD>
																<TD WIDTH="30%" colspan="3" height="10%"
																	CLASS="borderbottomleft1"><bean:write
																	name="componentDescId" property="componentName" /></TD>
															</TR>
														</logic:iterate>
											</logic:notEmpty>
								</TABLE>
							</TD>
					</logic:iterate>
				</TR>
			</logic:iterate>
		</TABLE>
						</html:form>
</BODY>
</html:html>
