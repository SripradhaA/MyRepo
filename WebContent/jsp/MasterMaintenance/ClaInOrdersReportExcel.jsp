<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page contentType="application/vnd.ms-excel"%>
<% response.setHeader("content-disposition","attachment;filename=\"ClauseVersionInOrdersReport.xls\"");%>
<HEAD>
</HEAD>
<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
<BODY CLASS='main'>
<html:form action="/modelAddClauseAction" method="post">
	<TABLE WIDTH="100%" BORDER="0"  align="center">
		<TR>
			<TD colspan="8" ALIGN="center"><B>Clause Version In Orders Report</B></TD>
		</TR>
	</TABLE>
	<BR/>
	
	<logic:present name="ModelAddClauseForm" property="orderList">
		<bean:size name="ModelAddClauseForm" id="ordsize"
			property="orderList" />
	</logic:present>
	
	<logic:notEmpty name="ModelAddClauseForm" property="orderList">
	<logic:iterate id="orderDetail" name="ModelAddClauseForm"
					property="orderList" type="com.EMD.LSDB.vo.common.ClauseVO">
				<Table width="100%" border="0" align="center">
					<TR>
						<TD>&nbsp;</TD>
						<TD width="80%" colspan="2" ALIGN="left"><b>Specification Type</TD>
						<td width="60%" ALIGN="center"  colspan="2"> :</b> </td>
						<td width="95%" colspan="3" ALIGN="left"><bean:write name="ModelAddClauseForm"
							property="hdnSelectedSpecType" /></TD>
					</TR>
					<TR>
						<TD>&nbsp;</TD>
						<TD colspan="2" ALIGN=left><b>Model</td>
						<td align="center" colspan="2"> : </b></td>
						<td colspan="3" align="left"><bean:write name="ModelAddClauseForm"
							property="hdnSelectedModel" /></TD>
					</TR>
					<TR>
						<TD>&nbsp;</TD>
						<TD colspan="2" ALIGN=left><b>Section</td>
						<td align="center" colspan="2"> : </b></td>
						<td colspan="3" align="left"><bean:write name="ModelAddClauseForm"
							property="hdnSelectedSection" /></TD>
					</TR>
					<TR>
						<TD>&nbsp;</TD>
						<TD colspan="2" ALIGN=left><b>SubSection</td>
						<td align="center" colspan="2"> : </b></td>
						<td colspan="3" align="left"><bean:write name="ModelAddClauseForm"
							property="hdnSelectedSubSection" /></TD>
					</TR>
					<TR>
						<TD>&nbsp;</TD>
						<TD colspan="2" VALIGN="top" ALIGN=left><b>Clause Description</b></td>
						<td align="center" VALIGN="top" colspan="2"> : </td>
						<td colspan="3" align="left"><%=(String.valueOf(orderDetail.getClauseDesc())).replaceAll("\n","<br>")%></TD>
					</TR>
					<!-- Added for CR-113 QA fix  -->
					<TR>
						<TD>&nbsp;</TD>
						<TD colspan="2" ALIGN=left><b>Show latest published Specs Only</td>
						<td align="center" colspan="2"> : </b></td>
						<td colspan="3" align="left"><bean:write name="ModelAddClauseForm"
							property="hdnShowLatSpecFlag" /></TD>
					</TR>
					<!-- Added for CR-113 QA fix  Ends Here-->
				</Table>
		<BR/>
		<BR/>
				<TABLE align="center" BORDER="1" bordercolor="#5780ae" width="100%">
				<TR>
					<TD align="center" colspan="3" width="80%"><b>Order Number</b></TD>
					<TD align="center" width="60%" colspan="2"><b>Spec Status</b></TD>
					<TD align="center" colspan="3" width="95%"><b>Customer Name</b></TD>
				</TR>
					
					<logic:iterate id="Orderused" name="orderDetail" 
					property="orderList" type="com.EMD.LSDB.vo.common.OrderVO">

					<TR>
					<TD colspan="3" align="center" >
							   <bean:write name="Orderused" property="orderNo" /> 
					</TD>
					<TD align="center" colspan="2">
							   <bean:write name="Orderused" property="statusDesc" /> 
					</TD>
					<TD colspan="3" align="center" nowrap>
							   <bean:write name="Orderused" property="customerName" /> 
					</TD>
					</TR>
					
				</logic:iterate>
			</TABLE>
			</logic:iterate>
	</logic:notEmpty>		
</html:form>
</BODY>

