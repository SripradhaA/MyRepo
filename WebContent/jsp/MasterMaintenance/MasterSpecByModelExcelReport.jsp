<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page contentType="application/vnd.ms-excel"%>
<html:html>
<HEAD>

</HEAD>
<%!public String replace(String clause) {

		if (clause != null && !"".equals(clause)) {

			clause.replaceAll("&lt", "<").replaceAll("&gt", ">");

		}
		return clause;
	}

	%>

<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
<BODY CLASS='main'>
<html:form action="/MasterSpecByMdlReport.do" method="post">
	<TBODY CLASS='main'>

		<TABLE BORDER=0 WIDTH="500" ALIGN=center>

			<TR>
				<TD WIDTH="500" colspan=6 align="center">
					<center>View Report</center>
				</TD>
			</TR>
		</TABLE>
		<br>
		<TABLE ALIGN=center WIDTH="50%" BORDER=0>
			<TR>

				<TD WIDTH="500" colspan=3 align=right>Specification Type :</span>&nbsp;<bean:write
					name="MasterSpecByMdlForm" property="hdnSelSpecType" />&nbsp;&nbsp;</TD>
				<TD WIDTH="500" colspan=3 align=left>Model :</span>&nbsp;<bean:write
					name="MasterSpecByMdlForm" property="modelName" /></TD>
			</TR>

		</TABLE>
		<br>
		<!-- Validation Message Display Part Starts Here -->

		<table border="0" cellpadding="0" cellspacing="0" width="20%"
			align="center">
			<tr>
				
				<td nowrap="nowrap"> 
				<div class="errorlayerhide" id="errorlayer">
		</div></td>
			</tr>
		</table>
		<!-- Validation Message Display Part ends Here -->
		<!-- No records found message starts---->
		<logic:notPresent name="MasterSpecByMdlForm"
			property="modelSubSectionList">
			
		<script> 
              fnNoRecords();
        </script>

		</logic:notPresent>

		<!-- No records found message ends---->


		<logic:present name="MasterSpecByMdlForm"
			property="modelSubSectionList">
			<TABLE ALIGN=center WIDTH="70%" BORDER=0>

				<TR>
					<TD colspan=6 align=center class="navycolor">Master Clauses : All
					the Clauses shown below are Master Clauses.</TD>
				</TR>
				<TR>
					<TD colspan=6 align=center class="navycolor"><!-- <font color="#130B80"> -->Version
					<font color="red">*</font>: Denotes the Master version/Default
					version used in the corresponding Order.</TD>
				</TR>

				<TR>
					<TD colspan=6 align=center class="navycolor">Components in ITALICS:
					Component names in ITALICS denotes that it is not a lead Component.
					</TD>
				</TR>

				<TR>
					<TD colspan=6 align=center class="navycolor">Tool Tip : Clause
					Description for each individual order shall be shown as a Tool Tip
					on mouse over of Clause Version.</TD>
				</TR>

			</TABLE>
		</logic:present>
		<br>
		<%int versionrow = 0;
					String strrows = "0";
%>

		<logic:present name="MasterSpecByMdlForm"
			property="modelSubSectionList">
			<logic:iterate id="modelsubsection" name="MasterSpecByMdlForm"
				property="modelSubSectionList" indexId="subsectionCount">
				<logic:iterate id="modelspecs" name="MasterSpecByMdlForm"
					property="modelSpecList" indexId="specCount">
					<bean:define id="speccount" name="specCount"></bean:define>
					<logic:equal name="subsectionCount"
						value="<%=String.valueOf(speccount)%>">
						<logic:iterate id="subsection" name="modelsubsection"
							indexId="subSectionIndex">
							<logic:equal name="subSectionIndex" value="0">
								<logic:iterate id="section" name="MasterSpecByMdlForm"
									property="sectionList" indexId="sectionCount">
									<logic:equal name="sectionCount"
										value="<%=String.valueOf(speccount)%>">

										<TABLE BORDER=0 WIDTH="100%" ALIGN="center">
											<TR>
												<TH align="center" colspan=6><b> <bean:write name="section"
													property="sectionCode" />.<bean:write name="section"
													property="sectionName" /></b></TH>
											</TR>
										</TABLE>
									</logic:equal>
								</logic:iterate>
							</logic:equal>
							<TABLE WIDTH="100%" BORDER="0" ALIGN="center">
								<TR>
									<TD align="center" colspan=6 WIDTH="100%"><bean:write
										name="subsection" property="subSecCode" />.<bean:write
										name="subsection" property="subSecName" /> <bean:define
										id="subSectionNo" name="subsection" property="subSecSeqNo" /></TD>
								</TR>
							</TABLE>
							<TABLE WIDTH="96%" ALIGN="center" BORDER="1"
								BORDERCOLOR="#5780ae">
								<TR>
									<TD align="center" width="10%"><font color="#000000"><b>Component
									Group</b></font></TD>
									<TD align="center" width="15%"><font color="#000000"><b>Component</b></font></TD>
									<TD align="center" width="5%"><font color="#000000"><b>Default
									Component</b></font></TD>
									<TD align="center" width="9%"><font color="#000000"><b>Clause
									No</b></font></TD>
									<TD align="center" width="50%"><font color="#000000"><b>Clause
									Description</b></font></TD>
									<TD align="center" width="10%"><font color="#000000"><b>Engineering
									Data</b></font></TD>
								</TR>

								<logic:present name="MasterSpecByMdlForm" property="hnOrderKey">
									<logic:notEmpty name="MasterSpecByMdlForm"
										property="hnOrderKey">
										<TR>
											<TD align="center" width="5%" colspan=2><font color="#000000"><b>Order
											No</b></font></TD>
											<TD align="center" width="5%"><font color="#000000"><b>Spec
											Status</b></font></TD>
											<TD align="center" width="7%" colspan=2><font color="#000000"><b>Customer</b></font></TD>
											<TD align="center" width="7%"><font color="#000000"><b>Version</b></font></TD>
										</TR>
									</logic:notEmpty>
								</logic:present>

								<logic:iterate id="spec" name="modelspecs" indexId="rowcount">
									<logic:equal name="spec" property="subSectionSeqNo"
										value="<%=String.valueOf(subSectionNo)%>">
										<bean:define id="color"
											value="<%= String.valueOf((rowcount.intValue()) % 2) %>" />
										<logic:equal name="color" value="0">
											<TR bgcolor="#E9E9E9">
										</logic:equal>
										<logic:notEqual name="color" value="0">
											<TR bgcolor="#CDCDCD">
										</logic:notEqual>

										<logic:equal name="color" value="0">
											<TD bgcolor="#E9E9E9" COLSPAN=3>
										</logic:equal>

										<logic:notEqual name="color" value="0">
											<TD bgcolor="#CDCDCD" COLSPAN=3>
										</logic:notEqual>



										<logic:present name="spec" property="compGroupVO">
											<!-- getting count of component group-->
											<bean:size id="comsize" name="spec" property="compGroupVO" />
										</logic:present>


										<logic:lessThan name="comsize" value="1">
											<TABLE WIDTH="100%" border=0 BORDERCOLOR="#5780ae">
												</logic:lessThan>
												<logic:greaterThan name="comsize" value="0">
													<TABLE WIDTH="100%" border=1 BORDERCOLOR="#5780ae">
														</logic:greaterThan>

														<logic:empty name="spec" property="compGroupVO">
															<TR>
																<TD CLASS=borderbottomrightlight3 width="28%">&nbsp;</TD>
																<TD CLASS=borderbottomrightlight3 width="47%">&nbsp;</TD>
																<TD CLASS=borderbottomrightlight4 width="25%">&nbsp;</TD>
															</TR>
														</logic:empty>


														<logic:present name="spec" property="compGroupVO">

															<logic:iterate id="compGroup" name="spec"
																property="compGroupVO"
																type="com.EMD.LSDB.vo.common.CompGroupVO" scope="page">
																<logic:equal name="compGroup" property="leadFlag"
																	value="Y">

																	<logic:equal name="comsize" value="1">
																		<%strrows = "2";

															%>
																	</logic:equal>

																	<logic:greaterThan name="comsize" value="1">
																		<%strrows = "1";

															%>
																	</logic:greaterThan>

																	<TR>
																		<TD rowspan="<%=strrows%>" align="center" width="28%">
																		<logic:equal name="compGroup" property="validFlag"
																			value="Y">
																			<i><font color="red">* </font></i>
																		</logic:equal> <i><bean:write name="compGroup"
																			property="componentGroupName" /></i></TD>
																		<logic:empty name="compGroup"
																			property="componentGroupName">
																			<TD rowspan="<%=strrows%>" align="center" width="28%">&nbsp;</TD>
																		</logic:empty>
																		<bean:define id="comp" name="compGroup"
																			property="compVO" />
																		<TD rowspan="<%=strrows%>" align="center" width="47%"><i><bean:write
																			name="comp" property="componentName" /></i></TD>
																		<logic:empty name="comp" property="componentName">
																			<TD rowspan="<%=strrows%>" align="center"
																				CLASS=borderbottomrightlight3 width="47%">&nbsp;</TD>
																		</logic:empty>

																		<logic:equal name="comp" property="defaultFlag"
																			value="true">
																			<TD rowspan="<%=strrows%>" align="center" width="25%"><i>X</i></TD>
																		</logic:equal>
																		<logic:notEqual name="comp" property="defaultFlag"
																			value="true">
																			<TD rowspan="<%=strrows%>" align="center" width="25%">&nbsp;</TD>
																		</logic:notEqual>
																	</TR>
																</logic:equal>

																<!-- Not Italics Component group-->
																<logic:notEqual name="compGroup" property="leadFlag"
																	value="Y">

																	<logic:equal name="comsize" value="1">
																		<%strrows = "2";

															%>
																	</logic:equal>

																	<logic:greaterThan name="comsize" value="1">
																		<%strrows = "1";

															%>
																	</logic:greaterThan>


																	<TR>
																		<TD rowspan="<%=strrows%>" align="center" width="28%">
																		<logic:equal name="compGroup" property="validFlag"
																			value="Y">
																			<font color="red">* </font>
																		</logic:equal> <bean:write name="compGroup"
																			property="componentGroupName" /></TD>
																		<logic:empty name="compGroup"
																			property="componentGroupName">
																			<TD rowspan="<%=strrows%>" align="center" width="28%">&nbsp;</TD>
																		</logic:empty>
																		<bean:define id="comp" name="compGroup"
																			property="compVO" />
																		<TD rowspan="<%=strrows%>" align="center" width="47%"><bean:write
																			name="comp" property="componentName" /></TD>
																		<logic:empty name="comp" property="componentName">
																			<TD rowspan="<%=strrows%>" align="center" width="47%">&nbsp;</TD>
																		</logic:empty>
																		<logic:equal name="comp" property="defaultFlag"
																			value="true">
																			<TD rowspan="<%=strrows%>" align="center" width="25%">X</TD>
																		</logic:equal>
																		<logic:notEqual name="comp" property="defaultFlag"
																			value="true">
																			<TD rowspan="<%=strrows%>" align="center" width="25%">&nbsp;</TD>
																		</logic:notEqual>
																	</TR>
																</logic:notEqual>
															</logic:iterate>
														</logic:present>

														<logic:lessThan name="comsize" value="1">
															<TR>
																<TD rowspan=2 align=center width="28%">&nbsp;</TD>

																<TD rowspan=2 align=center width="28%">&nbsp;</TD>

																<TD rowspan=2 align=center width="25%">&nbsp;</TD>

															</TR>

														</logic:lessThan>



													</TABLE>
													</TD>

													<TD><bean:write name="spec" property="clauseNum" /></TD>
													<TD width="50%"><bean:define name="spec"
														property="clauseDesc" id="clauseDesc" /> 
														<!-- CR-128 - Updated for Pdf issue -->
															<%String strClauseDesc =  String.valueOf(clauseDesc);
															if(strClauseDesc.startsWith("<p>")){%>
																<%=(String.valueOf(clauseDesc))%>
															<%}else{ %>	
																<%-- double space replacement with &nbsp is added to display the spaces as entered. This change is applicable for only modify spec --%>
																<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>							
															<%}%>
													   <!-- CR-128 - Updated for Pdf issue Ends Here-->
														
														
													<logic:notEmpty name="spec" property="tableArrayData1">
														<table width="100%" BORDER="1" bordercolor="#5780ae">
															<logic:iterate id="outter" name="spec"
																property="tableArrayData1" indexId="counter">
																<bean:define id="row" name="counter" />
																<tr>
																	<logic:iterate id="tabrow" name="outter">
																		<logic:equal name="row" value="0">
																			<td valign="top" CLASS='borderbottomleft1'
																				align="center"><b><font color="navy"><%=(tabrow == null) ? "&nbsp;"
																				: tabrow%></b></font>
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
													</logic:notEmpty> <logic:empty name="spec"
														property="tableArrayData1">
														<table width="100%">
															<tr>
																<td colspan=5>&nbsp;</td>
															</tr>
														</table>
													</logic:empty></TD>

													<TD align="center"
														style="word-wrap: break-word;width:100;right:0">
														<!-- <logic:present name="spec" property="refEDLNO">
														<logic:iterate id="engDataRefEdlNo" name="spec"
															property="refEDLNO">
															<bean:message key="screen.refEdl" />
															<bean:write name="engDataRefEdlNo" />
															<br>
														</logic:iterate>
													</logic:present> --> 
													<logic:present name="spec"
														property="edlNO">

														<logic:iterate id="engDataEdlNo" name="spec"
															property="edlNO">

															<bean:message key="screen.edl" />
															<bean:write name="engDataEdlNo" />
															<br>

														</logic:iterate>

													</logic:present> <!-- CR 87 --> <logic:present name="spec"
														property="refEDLNO">
														<logic:iterate id="engDataRefEdlNo" name="spec"
															property="refEDLNO">
															<bean:message key="screen.refEdl.start" />
															<bean:write name="engDataRefEdlNo" />
															<bean:message key="screen.refEdl.end" />
															<br>
														</logic:iterate>
													</logic:present> <logic:present name="spec"
														property="partOF">

														<logic:iterate id="engPartOfCd" name="spec"
															property="partOF">

															<logic:notEqual name="engPartOfCd" value="0">

																<bean:message key="screen.partOf" />
																<bean:write name="engPartOfCd" />
																<br>

															</logic:notEqual>

														</logic:iterate>

													</logic:present> <logic:present name="spec"
														property="dwONumber">

														<logic:notEqual name="spec" property="dwONumber" value="0">

															<bean:message key="screen.dwoNo" />
															<bean:write name="spec" property="dwONumber" />
															<br>

														</logic:notEqual>

													</logic:present> <logic:present name="spec"
														property="partNumber">
														<%-- Modified the property from DWO number to Part Number  in CR_108 --%>
														<logic:notEqual name="spec" property="partNumber" value="0">
														<%-- Modification ends here --%>
															<bean:message key="screen.partNo" />
															<bean:write name="spec" property="partNumber" />
															<br>

														</logic:notEqual>

													</logic:present> <!--Price Book Number is added in the Engineering data Section, modified on 30-09-08 by ps5722-->

													<logic:present name="spec" property="priceBookNumber">

														<logic:notEqual name="spec" property="priceBookNumber"
															value="0">

															<bean:message key="screen.priceBookNo" />&nbsp;<bean:write
																name="spec" property="priceBookNumber" />
															<br>

														</logic:notEqual>

													</logic:present> <logic:present name="spec"
														property="standardEquipmentDesc">

														<bean:write name="spec" property="standardEquipmentDesc" />
														<br>

													</logic:present> <logic:present name="spec"
														property="engDataComment">
														<bean:define id="engDatCmnt" name="spec"
															property="engDataComment" />
														<%=engDatCmnt%>
														<br>

													</logic:present></TD>

													</TR>

													<logic:present name="MasterSpecByMdlForm"
														property="hnOrderKey">
														<logic:notEmpty name="MasterSpecByMdlForm"
															property="hnOrderKey">

															<logic:present name="spec" property="orderVO">
																<logic:iterate id="order" name="spec" property="orderVO"
																	type="com.EMD.LSDB.vo.common.OrderVO" scope="page">

																	<logic:equal name="color" value="0">
																		<TR bgcolor="#E9E9E9">
																	</logic:equal>

																	<logic:notEqual name="color" value="0">
																		<TR bgcolor="#CDCDCD">
																	</logic:notEqual>

																	<TD align=center colspan=2><bean:write name="order"
																		property="orderNo" /></TD>
																	<TD align=center><bean:write name="order"
																		property="statusDesc" /></TD>
																	<TD align=center colspan=2><bean:write name="order"
																		property="customerName" /></TD>
																	<TD align=center>V<bean:write name="order"
																		property="versionNo" /><logic:equal name="order"
																		property="versionIndicator" value="Y">*</logic:equal>
																	</TD>

																	</TR>
																	<%versionrow++;

															%>
																</logic:iterate>
															</logic:present>

														</logic:notEmpty>
													</logic:present>


													</logic:equal>
													</logic:iterate>
											</TABLE>
											<br>
								</logic:iterate>
								</logic:equal>
								</logic:iterate>
								</logic:iterate>
								</logic:present>

								<html:hidden property="hdnSelSpecType" />
								</html:form>
</BODY>
</html:html>
