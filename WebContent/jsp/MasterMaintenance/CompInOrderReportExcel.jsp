<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page contentType="application/vnd.ms-excel"%>
<% response.setHeader("content-disposition","attachment;filename=\"ComponentInOrdersReport.xls\"");%>
<HEAD>
</HEAD>
<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
<BODY CLASS='main'>
<html:form action="/CompGroupAction" method="post">
	<TABLE WIDTH="100%" BORDER="0" CELLPADDING="0" CELLSPACING="0">
		<TR>
			<TD colspan="15" ALIGN="center"><B>Component in Orders Report</B></TD>
		</TR>
	</TABLE>
	<BR/>
	<logic:present name="CompGroupMaintForm" property="compgroupList">
		<bean:size id="CompGrpListLen" name="CompGroupMaintForm"
			property="compgroupList" />
	</logic:present>
	
	<logic:notEmpty name="CompGroupMaintForm" property="compInOrdList">
	
	<TABLE WIDTH="98%" ALIGN="center" BORDER="1" BORDERCOLOR="#5780ae">
		<TR>
			<TD colspan="3" align="center"><B>Component Group</B></TD>
			<TD colspan="3" align="center"><B>Component</B></TD>
			<TD colspan="3" align="center"><B>Order Number</B></TD>
			<TD colspan="3" align="center"><B>Spec Status</B></TD>
			<%-- Added for CR_112 To bring the Customer Name--%>
			<TD colspan="3" align="center"><B>Customer Name</B></TD>
			<%-- CR_112 Ends here--%>
		</TR>
		<logic:iterate id="compgrp" name="CompGroupMaintForm"
			property="compInOrdList" type="com.EMD.LSDB.vo.common.CompGroupVO">
			
			<TR>
				<TD colspan="3" style="text-align:center;vertical-align:middle"><bean:write name="compgrp" 
					property="componentGroupName" />
				</TD>
			<%-- Changed the colspan to 12--%>
				<TD colspan="12">

				<TABLE width="100%" border=1 BORDERCOLOR="#5780ae">

					<logic:notEmpty name="compgrp" property="componentVO">

						<logic:iterate id="comp" name="compgrp" property="componentVO">

							<TR>
								<td colspan="3" style="text-align:center;vertical-align:middle">
									<logic:notEmpty name="comp"	property="componentName">
									<logic:equal name="comp" property="compSourceFlag" value="O"> 
										<font color="red">
											<bean:write name="comp" property="componentName" />
										</font>
									</logic:equal>	
									<logic:equal name="comp" property="compSourceFlag" value="M"> 
										<bean:write name="comp" property="componentName" />
									</logic:equal>							
								</logic:notEmpty> 
								<logic:empty name="comp" property="componentName">
									&nbsp;
								</logic:empty></td>
								<%-- Changed the colspan to 9--%>
								<TD colspan="9">
									<logic:notEmpty name="comp" property="ordersUsed">
										<TABLE width="100%" border=1 BORDERCOLOR="#5780ae">
										<TR>									
										<logic:iterate id="orders" name="comp" property="ordersUsed" indexId="count">
										<bean:define id="row" value="<%= String.valueOf((count.intValue()) % 3) %>" />
										<logic:notEqual name="count" value="0">
											<logic:equal name="row" value="0">
												</TR>
												<TR>
											</logic:equal>
										</logic:notEqual>
											<TD colspan="3" style="text-align:center;vertical-align:middle">
												<logic:notEmpty name="orders">
													<bean:write name="orders" />
												</logic:notEmpty>
												<logic:empty name="orders">
													&nbsp;
												</logic:empty>
											</TD>
										</logic:iterate>
										</TABLE>
									</logic:notEmpty> 
									<logic:empty name="comp" property="ordersUsed">
									<TABLE width="100%" border=1 BORDERCOLOR="#5780ae">
										<TR>									
										<TD colspan="3" style="text-align:center;vertical-align:middle">&nbsp;</TD>
										<TD colspan="3" style="text-align:center;vertical-align:middle">&nbsp;</TD>
										<%-- Added one more column for Customer name - CR_112--%>
										<TD colspan="3" style="text-align:center;vertical-align:middle">&nbsp;</TD>										
										</TR>		
									</TABLE>
									</logic:empty>
								</TD>
							</TR>
						</logic:iterate>

					</logic:notEmpty>

					<logic:empty name="compgrp" property="componentVO">
						<tr>
							<td colspan="3" style="text-align:center;vertical-align:middle">&nbsp;</td>

							<td colspan="3" style="text-align:center;vertical-align:middle">&nbsp;</td>

							<td colspan="3" style="text-align:center;vertical-align:middle">&nbsp;</td>
							<%-- Added one more column for Customer name - CR_112--%>
							<td colspan="3" style="text-align:center;vertical-align:middle">&nbsp;</td>
						</tr>
					</logic:empty>

				</TABLE>
				</TD>
			</TR>			
		</logic:iterate>
	</TABLE>
	</logic:notEmpty>
		
</html:form>

</BODY>