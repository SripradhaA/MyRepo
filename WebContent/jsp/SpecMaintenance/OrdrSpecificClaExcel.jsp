<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page contentType="application/vnd.ms-excel"%>
<% response.setHeader("content-disposition","attachment;filename=\"OrderSpecificClausesReport.xls\"");%>
<html:html>
<HEAD>
</HEAD>
<%!
	public String replace(String clause){
		if(clause != null && !"".equals(clause)){
			clause.replaceAll("&lt","<").replaceAll("&gt",">");
		}
		return clause; 
}
%>
<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>

<BODY CLASS='main'>
<html:form action="/OrderSpecificClauseAction.do" method="post">
	<TBODY CLASS='main'>

		<TABLE BORDER=0 ALIGN=center>
			<TR>
				<TD colspan=7 align="center">
				<center>View Order Specific Clauses Report</center>
				</TD>
			</TR>
			<TR> <TD>&nbsp;</TD></TR>
		</TABLE>
		<TABLE ALIGN=center BORDER=0>
			<TR>
				<TD colspan=7 align=center>All the Clauses below are only added to selected Order.</TD>
			</TR>
			<TR>
				<TD>&nbsp;</TD>
			</TR>
		</TABLE>

		<TABLE ALIGN=center BORDER=0>
				<TR>
					<TD colspan=7 align=center>
						<b>Specification Type :</b>&nbsp;
						<bean:write name="OrderSpecificClauseForm" property="hdnSelSpecType"/> &nbsp;&nbsp;
						<b>Model :</b>&nbsp;</span>
						<bean:write name="OrderSpecificClauseForm" property="hdnSelModel"/> &nbsp;&nbsp;
						<b>Order Number(s) :</b>&nbsp;</span>
						<bean:write name="OrderSpecificClauseForm" property="orderNumberResult"/> &nbsp;&nbsp;
					</TD>
				</TR>
			<TR>
				<TD>&nbsp;</TD>
			</TR>
					
		</TABLE>
		
	<logic:present name="OrderSpecificClauseForm" property="arlClauseList"> 
	
	<TABLE WIDTH="96%" ALIGN="center" BORDER="1" BORDERCOLOR="#5780ae">
			<TR>
				<TD style="text-align:center;vertical-align:middle"> <font color="#000000"> <b> Order Number </b> </font> </TD>
				<TD style="text-align:center;vertical-align:middle"> <font color="#000000"> <b> Spec Status </b> </font> </TD>
				<TD style="text-align:center;vertical-align:middle"> <font color="#000000"> <b> Clause No </b> </font> </TD>
				<TD style="text-align:center;vertical-align:middle" colspan=3> <font color="#000000"> <b> Clause Description </b> </font> </TD>
				<TD style="text-align:center;vertical-align:middle"> <font color="#000000"> <b> Engineering Data </b> </font> </TD>
			</TR>
			<logic:iterate id="clauseListId" name="OrderSpecificClauseForm" property="arlClauseList" 
				type="com.EMD.LSDB.vo.common.ClauseVO" indexId="counter">
				<bean:define id="color"	value="<%= String.valueOf((counter.intValue()) % 2) %>" />
				<logic:equal name="color" value="0">
					<TR bgcolor="#E9E9E9">
				</logic:equal>
				<logic:notEqual name="color" value="0">
					<TR bgcolor="#CDCDCD">
				</logic:notEqual>
						<TD style="text-align:center;vertical-align:middle"><bean:write name="clauseListId" property="orderNo"/> </TD>
						<TD style="text-align:center;vertical-align:middle"><bean:write name="clauseListId" property="status"/> </TD>
						<TD style="text-align:center;vertical-align:middle"><bean:write name="clauseListId" property="clauseNum"/> </TD>
					
						<TD style="vertical-align:middle" colspan=3><bean:define name="clauseListId" property="clauseDesc" id="clauseDesc" /> 
							<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>
							<logic:notEmpty name="clauseListId" property="tableArrayData1">
								<table width="100%" BORDER="1" bordercolor="#5780ae">
									<logic:iterate id="outter" name="clauseListId"
										property="tableArrayData1" indexId="counter">
										<bean:define id="row" name="counter" />
										<tr>
											<logic:iterate id="tabrow" name="outter">
												<logic:equal name="row" value="0">
													<td valign="top" CLASS='borderbottomleft1' align="center"><b>
													<font color="navy"><%=(tabrow == null) ? "&nbsp;": tabrow%></b></font>
													</td>
												</logic:equal>
												<logic:notEqual name="row" value="0">
													<td valign="top" CLASS='borderbottomleft1'
														align="center"><%=(tabrow == null) ? "&nbsp;"
														: tabrow%>
													</td>
												</logic:notEqual>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</table>
							</logic:notEmpty></TD>
					
					<TD style="text-align:center;vertical-align:middle;word-wrap: break-word;width:100;right:0">
						<logic:present name="clauseListId" property="edlNO">
							<logic:iterate id="engDataEdlNo" name="clauseListId" property="edlNO">
								<bean:message key="screen.edl" />
								<bean:write name="engDataEdlNo" />
								<br>
							</logic:iterate>
						</logic:present> 
						<logic:present name="clauseListId" property="refEDLNO">
							<logic:iterate id="engDataRefEdlNo" name="clauseListId"	property="refEDLNO">

								<bean:message key="screen.refEdl.start" />
								<bean:write name="engDataRefEdlNo" />
								<bean:message key="screen.refEdl.end" />
								<br>
							</logic:iterate>
						</logic:present> 
						<logic:present name="clauseListId" property="partOF">
							<logic:iterate id="engPartOfCd" name="clauseListId"	property="partOF">
								<logic:notEqual name="engPartOfCd" value="0">

									<bean:message key="screen.partOf" />
									<bean:write name="engPartOfCd" />
									<br>

								</logic:notEqual>

							</logic:iterate>

						</logic:present> <logic:present name="clauseListId"
							property="dwONumber">

							<logic:notEqual name="clauseListId" property="dwONumber" value="0">

								<bean:message key="screen.dwoNo" />
								<bean:write name="clauseListId" property="dwONumber" />
								<br>

							</logic:notEqual>

						</logic:present> <logic:present name="clauseListId"
							property="partNumber">

							<logic:notEqual name="clauseListId" property="partNumber" value="0">

								<bean:message key="screen.partNo" />
								<bean:write name="clauseListId" property="partNumber" />
								<br>

							</logic:notEqual>

						</logic:present> 

						<logic:present name="clauseListId" property="priceBookNumber">

							<logic:notEqual name="clauseListId" property="priceBookNumber"
								value="0">

								<bean:message key="screen.priceBookNo" />&nbsp;<bean:write
									name="clauseListId" property="priceBookNumber" />
								<br>

							</logic:notEqual>

						</logic:present> <logic:present name="clauseListId"
							property="standardEquipmentDesc">

							<bean:write name="clauseListId" property="standardEquipmentDesc" />
							<br>

						</logic:present> <logic:present name="clauseListId"
							property="comments">
							<bean:define id="engDatCmnt" name="clauseListId"
								property="comments" />
							<%=engDatCmnt %>
							<br>
						</logic:present></TD>
				</TR>
		</logic:iterate>
	</TABLE>			
	</logic:present>
	
<html:hidden property="hdnSelSpecType" />
<html:hidden property="hdnSelModel"/>

</html:form>
</BODY>
</html:html>

