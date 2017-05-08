<%-- Created for CR_109 - Clauses By Components Report in Excel--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page contentType="application/vnd.ms-excel"%>
<% response.setHeader("content-disposition","attachment;filename=\"ClausesByCompsReport.xls\"");%>
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
<html:form action="/ClaByCompsAction.do" method="post">
	<TBODY CLASS='main'>

		<TABLE BORDER=0 ALIGN=center>
			<TR>
				<TD colspan=6 align="center">
				<center>Clauses By Components Report</center>
				</TD>
			</TR>
			<TR> <TD>&nbsp;</TD></TR>
		</TABLE>
		

		<TABLE ALIGN=center BORDER=0>
				<TR>
					<TD colspan=6 align=center>
						<b>Component Group :</b>&nbsp;
						<bean:write name="ClaByCompsForm" property="hdnSelCompGrp"/> &nbsp;&nbsp;
						<b>Component :</b>&nbsp;</span>
						<bean:write name="ClaByCompsForm" property="hdnCompName"/> &nbsp;&nbsp;
					</TD>
				</TR>
			<TR>
				<TD>&nbsp;</TD>
			</TR>
					
		</TABLE>
		
	<logic:present name="ClaByCompsForm" property="claList"> 
	<% String strrows = "0";%>
	
	<TABLE WIDTH="100%" ALIGN="center" BORDER="1" BORDERCOLOR="#5780ae">
			<TR>
				<TD style="text-align:center;vertical-align:middle"> <font color="#000000"> <b> Model Name </b> </font> </TD>
				<TD style="text-align:center;vertical-align:middle"> <font color="#000000"> <b> Component Group </b> </font> </TD>
				<TD style="text-align:center;vertical-align:middle"> <font color="#000000"> <b> Component </b> </font> </TD>
				<TD style="text-align:center;vertical-align:middle" colspan="2"> <font color="#000000"> <b> Clause Description </b> </font> </TD>
				<TD style="text-align:center;vertical-align:middle"> <font color="#000000"> <b> Engineering Data </b> </font> </TD>
			</TR>
			<logic:iterate id="clauseListId" name="ClaByCompsForm" property="claList" 
				type="com.EMD.LSDB.vo.common.ClauseVO" indexId="counter">
				<bean:define id="color"	value="<%= String.valueOf((counter.intValue()) % 2) %>" />
				<logic:equal name="color" value="0">
					<TR bgcolor="#E9E9E9">
				</logic:equal>
				<logic:notEqual name="color" value="0">
					<TR bgcolor="#CDCDCD">
				</logic:notEqual>
						<TD style="text-align:center;vertical-align:middle"><bean:write name="clauseListId" property="modelName"/> </TD>
						<TD colspan="2" style="text-align:center;vertical-align:middle">
						
						<logic:present name="clauseListId" property="componentList">
							<!-- getting count of component group-->
							<bean:size id="comsize" name="clauseListId" property="componentList" />
						</logic:present>
						
						<logic:lessThan name="comsize" value="1">
							<TABLE WIDTH="100%" border=0 BORDERCOLOR="#5780ae">
						</logic:lessThan>
						<logic:greaterThan name="comsize" value="0">
							<TABLE WIDTH="100%" border=1 BORDERCOLOR="#5780ae">
						</logic:greaterThan>
						
						<logic:iterate id="compGroup" name="clauseListId" property="componentList"
							type="com.EMD.LSDB.vo.common.ComponentVO">	
							<logic:equal name="comsize" value="1">
								<%strrows = "2";%>
							</logic:equal>

							<logic:greaterThan name="comsize" value="1">
								<%strrows = "1";%>
							</logic:greaterThan>		
							<TR>			

							<logic:notEmpty name="compGroup" property="componentGroupName">
								<TD rowspan="<%=strrows%>" style="text-align:center;vertical-align:middle">
									<logic:equal name="compGroup" property="validationFlag" value="Y">
										<font color="red">* </font>
									</logic:equal>									
									<logic:equal name="compGroup" property="compLeadFlag" value="Y">
									<i>
									</logic:equal>
										<bean:write name="compGroup" property="componentGroupName" />
									<logic:equal name="compGroup" property="compLeadFlag" value="Y">
									</i>
									</logic:equal>
								</TD>
							
								<TD  rowspan="<%=strrows%>" style="text-align:center;vertical-align:middle">									
									<logic:equal name="compGroup" property="compLeadFlag" value="Y">
									<i>
									</logic:equal>
										<bean:write name="compGroup" property="componentName" />
									<logic:equal name="compGroup" property="compLeadFlag" value="Y">
									</i>
									</logic:equal>
								</TD>
							</logic:notEmpty>
							</TR>
						</logic:iterate>
						<logic:lessThan name="comsize" value="1">
							<TR>
								<TD rowspan=2 align=center>&nbsp;</TD>
								<TD rowspan=2 align=center>&nbsp;</TD>
							</TR>
						</logic:lessThan>
						</TABLE>
					</TD>

					
						<TD style="vertical-align:middle" colspan="2" width="100%">
							<bean:define name="clauseListId" property="clauseDesc" id="clauseDesc" /> 
							<!-- CR-128 - Updated for Pdf issue -->
							<%String strClauseDesc =  String.valueOf(clauseDesc);
							if(strClauseDesc.startsWith("<p>")){%>
								<%=(String.valueOf(clauseDesc))%>
							<%}else{ %>	
							<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>
							<%}%>
							<!-- CR-128 - Updated for Pdf issue Ends Here--> 
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
							</logic:notEmpty>
						</TD>
					
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
	
	<html:hidden property="hdnSelCompGrp" />
	<html:hidden property="hdnCompName" />

</html:form>
</BODY>
</html:html>

