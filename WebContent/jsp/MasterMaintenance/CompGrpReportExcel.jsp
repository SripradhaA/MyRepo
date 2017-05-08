<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page contentType="application/vnd.ms-excel"%>
<html:html>
<HEAD>
</HEAD>

<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
<BODY CLASS='main'>
<html:form action="/CompGroupAction" method="post">
	<br>
	<br>
	<br>
	<br>
	<TABLE width="100%" border="0" align="left">
		<TR>
			<TD align="center" colspan="7">
			<TABLE BORDER=0 WIDTH="100%" ALIGN="center">
				<TR>
					<TD>&nbsp;</TD>
				</TR>

				<TR>
					<TD align="center" colspan="7"><b>Component Group / Component
					Report</b></TD>
				</TR>
				<TR>
					<TD>&nbsp;</TD>
				</TR>
			</TABLE>


			<logic:notPresent name="CompGroupMaintForm" property="compgroupList">
				<TABLE ALIGN="center" WIDTH="100%" BORDER="0">
					<TR>
						<TD colspan="7" ALIGN="center"><br>
						<bean:message key="Application.Screen.NoRecords" /></TD>
					</TR>
				</TABLE>
			</logic:notPresent> 
			
			<logic:present name="CompGroupMaintForm" property="compgroupList">

				<TABLE WIDTH="98%" ALIGN="center" BORDER="1" BORDERCOLOR="#5780ae">

					<TR>
						<TD width="13%" align="center"><B>Component Group<B></TD>
						<TD width="18%" align="center"><B>Component Group Identifier</B></TD>
						<TD width="18%" align="center"><B>Component Group Type</B></TD>

						<TD width="18%" align="center"><B>Component Value</B></TD>
						<TD width="16%" align="center"><B>Component Value Identifier</B></TD>
						<TD width="12%" align="center"><B>Models Affected</B></TD>
						<TD width="16%" align="center"><B>Model Default</B></TD>
					</TR>

					<logic:iterate id="compgrp" name="CompGroupMaintForm"
						property="compgroupList" type="com.EMD.LSDB.vo.common.CompGroupVO">
						<TR>

							<TD style="text-align:center;vertical-align:middle"><bean:write
								name="compgrp" property="componentGroupName" /></TD>

							<td style="text-align:center;vertical-align:middle"><bean:write
								name="compgrp" property="componentGroupIdentifier" /></TD>

							<TD style="text-align:center;vertical-align:middle"><bean:write 
								name="compgrp" property="compGrpTypeName" /></TD>
							
							<td colspan=4>

							<table width="100%" border=1 BORDERCOLOR="#5780ae">

								<logic:notEmpty name="compgrp" property="componentVO">

									<logic:iterate id="comp" name="compgrp" property="componentVO">

										<tr>
											<td style="text-align:center;vertical-align:middle">
									<logic:notEmpty	name="comp" property="componentName">
									<%-- Added for CR_101 to display the order components in RED --%>
									
									<logic:equal name="comp" property="compSourceFlag" value="O"> 
										<font color="red">
											<bean:write name="comp" property="componentName" />
										</font>
									</logic:equal>	
									<logic:equal name="comp" property="compSourceFlag" value="M"> 
										<bean:write name="comp" property="componentName" />
									</logic:equal>	
									
									<%-- CR_101 Ends here--%>	
									</logic:notEmpty> 
									<logic:empty name="comp" property="componentName">
	&nbsp;
	</logic:empty></td>

											<td style="text-align:center;vertical-align:middle"><logic:notEmpty
												name="comp" property="componentIdentifier">
												<bean:write name="comp" property="componentIdentifier" />
											</logic:notEmpty> <logic:empty name="comp"
												property="componentIdentifier">
	&nbsp;
	</logic:empty></td>

											<td style="text-align:center;vertical-align:middle"><logic:notEmpty
												name="comp" property="modelsAffected">

												<logic:iterate id="model" name="comp"
													property="modelsAffected">
													<bean:write name="model" />
													<BR>
												</logic:iterate>

											</logic:notEmpty> <logic:empty name="comp"
												property="modelsAffected">
												<BR>
											</logic:empty></td>

											<td style="text-align:center;vertical-align:middle"><logic:notEmpty
												name="comp" property="modelDefault">

												<logic:iterate id="modelname" name="comp"
													property="modelDefault">
													<bean:write name="modelname" />
													<BR>
												</logic:iterate>

											</logic:notEmpty> <logic:empty name="comp"
												property="modelDefault">
												<BR>
											</logic:empty></td>

										</tr>
									</logic:iterate>

								</logic:notEmpty>

								<logic:empty name="compgrp" property="componentVO">
									<tr>
										<td>&nbsp;</td>

										<td>&nbsp;</td>

										<td>&nbsp;</td>

										<td>&nbsp;</td>

									</tr>
								</logic:empty>


							</table>

							</td>
						</TR>
					</logic:iterate>
				</TABLE>


			</logic:present> </html:form>
</BODY>
</html:html>
