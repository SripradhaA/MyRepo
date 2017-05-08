<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page contentType="application/vnd.ms-excel"%>
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
<html:form action="/MasterSpecByMdlReport.do" method="post">
	<TBODY CLASS='main'>

		<TABLE BORDER=0 WIDTH="100%" ALIGN=center>
			<TR>
				<TD WIDTH="10%">&nbsp;</TD>
			</TR>
			<TR>
				<TD WIDTH="500" colspan=6 align="center">
				<center>View Characteristic Group Report</center>
				</DIV>
				</TD>
			</TR>
			<TR>
				<TD>&nbsp;</TD>
			</TR>
		</TABLE>

		<TABLE ALIGN=center WIDTH="50%" BORDER=0>
			<TR>

				<TD WIDTH="500" colspan=6 align="center">Specification Type </span>&nbsp;:<bean:write
					name="MasterSpecByMdlForm" property="hdnSelSpecType" />&nbsp;&nbsp;</TD>
			</TR>
			<TR>
				<TD WIDTH="500" colspan=6 align="center">Model </span>&nbsp;:<bean:write
					name="MasterSpecByMdlForm" property="modelName" /></TD>
			</TR>
		</TABLE>
		<BR>
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
					<TD colspan=6 align=center class="navycolor">All the Clauses shown below are Characteristic Core Clauses</TD>
				</TR>
			</TABLE>
		</logic:present>

		<%
int versionrow=0;
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

										<TABLE BORDER=0 WIDTH="96%" ALIGN="center">
											<TR>
												<TH align="center" colspan=6><b><bean:write name="section"
													property="sectionCode" />.<bean:write name="section"
													property="sectionName" /></b></TH>
											</TR>
										</TABLE>
									</logic:equal>
								</logic:iterate>
							</logic:equal>

							<!---------------------- !-->

							<TABLE WIDTH="100%" BORDER="0" ALIGN="center">
								<TR>
									<TD align="center" colspan=6 WIDTH="100%"><bean:write
										name="subsection" property="subSecCode" />.<bean:write
										name="subsection" property="subSecName" /> <bean:define
										id="subSectionNo" name="subsection" property="subSecSeqNo" />
									</TD>
								</TR>
							</TABLE>
                        
							<TABLE WIDTH="96%" ALIGN="center" BORDER="1"
								BORDERCOLOR="#5780ae">
                   
								<TR>
									<TD align="center" width="10%"><font color="#000000"><b>Characterstic
									Component Group</b></font></TD>
									<TD align="center" width="15%"><font color="#000000"><b>Characterstic
									Component</b></font></TD>
									<TD align="center" width="5%"><font color="#000000"><b>Characterstic
									Combination EDl #</b></font></TD>
									<TD align="center" width="9%"><font color="#000000"><b>Clause
									No</b></font></TD>
									<TD align="center" width="50%"><font color="#000000"><b>Clause
									Description</b></font></TD>
									<TD align="center" width="10%"><font color="#000000"><b>Engineering
									Data</b></font></TD>

								</TR>


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
										
										<logic:present name="spec" property="compGroupVO">
											<!-- getting count of component group-->
											<bean:size id="comsize" name="spec" property="compGroupVO" />
										</logic:present>
										
										
										<logic:equal name="color" value="0">
											<TD bgcolor="#E9E9E9" COLSPAN=2 >
										</logic:equal>

										<logic:notEqual name="color" value="0">
											<TD bgcolor="#CDCDCD" COLSPAN=2 >
										</logic:notEqual>

										<logic:lessThan name="comsize" value="1">
											<TABLE WIDTH="100%" border=0 BORDERCOLOR="#5780ae">
												</logic:lessThan>
												<logic:greaterThan name="comsize" value="0">
													<TABLE WIDTH="100%" border=1 BORDERCOLOR="#5780ae">
														</logic:greaterThan>
											<logic:equal name="color" value="0">
											<TD bgcolor="#E9E9E9" COLSPAN=2 >
										</logic:equal>

										<logic:notEqual name="color" value="0">
											<TD bgcolor="#CDCDCD" COLSPAN=2 >
										</logic:notEqual>

											<logic:iterate id="compGroup" name="spec"
												property="compGroupVO"
												type="com.EMD.LSDB.vo.common.CompGroupVO" scope="page">

												<bean:define id="comp" name="compGroup" property="compVO" />
												<TR>
													<TD CLASS=borderbottomleft11 width="50%"><logic:equal
														name="compGroup" property="validFlag" value="Y">
														<font color="red">* </font>
													</logic:equal> <bean:write name="comp"
														property="componentGroupName" /></TD>

													<TD CLASS=borderbottomleft11 width="50%"><bean:write
														name="comp" property="componentName" /></TD>
												</TR>
										</logic:iterate>
										
														<logic:lessThan name="comsize" value="1">
															<TR>
																<TD rowspan=2 align=center width="28%">&nbsp;</TD>
								

															</TR>

														</logic:lessThan>
														</TD>
										</TABLE>
										</TD>

										<TD CLASS=borderbottomleft11>
										<!-- <logic:notEmpty name="spec"
											property="charRefEDLNo">
											<bean:message key="screen.refEdl" />
											<bean:write name="spec"
											property="charRefEDLNo" />
										</logic:notEmpty> -->
										<logic:notEmpty name="spec" property="charEdlNo">
											<bean:message key="screen.edl" />
											<bean:write name="spec" property="charEdlNo" />
										</logic:notEmpty> 
										<logic:empty name="spec" property="charEdlNo">&nbsp;</logic:empty>
										<br />
										<logic:empty name="spec"
											property="charRefEDLNo">
														&nbsp;
																</logic:empty>
										<logic:notEmpty name="spec"
											property="charRefEDLNo">
											<bean:message key="screen.refEdl.start" />
											<bean:write name="spec"	property="charRefEDLNo" />
											<bean:message key="screen.refEdl.end" />											
										</logic:notEmpty>	
										
										</TD>

										<TD><bean:write name="spec" property="clauseNum" /></TD>
													<TD width="50%"><bean:define name="spec"
														property="clauseDesc" id="clauseDesc" /> 
														<!-- CR-128 - Updated for Pdf issue -->
															<%String strClauseDesc =  String.valueOf(clauseDesc);
															if(strClauseDesc.startsWith("<p>")){%>
																<%=(String.valueOf(clauseDesc))%>
															<%}else{ %>	
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
																				align="center"><b><font color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></b></font>
																			</td>
																		</logic:equal>
																		<logic:notEqual name="row" value="0">
																			<td valign="top" CLASS='borderbottomleft1'
																				align="center"><%=(tabrow==null)? "&nbsp;":tabrow%>
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

										<TD CLASS=borderbottomrightlight11
											style="word-wrap: break-word;width:100;right:0">
									<!--	<logic:present name="spec" property="refEDLNO">
											<logic:iterate id="engDataRefEdlNo" name="spec"
												property="refEDLNO">
												<bean:message key="screen.refEdl" />
												<bean:write name="engDataRefEdlNo" />
												<br>
											</logic:iterate>
										</logic:present> -->
										<logic:present name="spec" property="edlNO">

											<logic:iterate id="engDataEdlNo" name="spec" property="edlNO">

												<bean:message key="screen.edl" />
												<bean:write name="engDataEdlNo" />
												<br>

											</logic:iterate>

										</logic:present> 
										<!--CR 87 -->
										<logic:present name="spec" property="refEDLNO">
											<logic:iterate id="engDataRefEdlNo" name="spec"
												property="refEDLNO">
												<bean:message key="screen.refEdl.start" />
												<bean:write name="engDataRefEdlNo" />
												<bean:message key="screen.refEdl.end" />
												<br>
											</logic:iterate>
										</logic:present>
										<logic:present name="spec" property="partOF">

											<logic:iterate id="engPartOfCd" name="spec" property="partOF">

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

											<logic:notEqual name="spec" property="dwONumber" value="0">

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
											<%=engDatCmnt %>
											<br>

										</logic:present>
										</TD>
								</TR>
									</logic:equal>
								</logic:iterate>
							</TABLE>
							<br>
						</logic:iterate>
					</logic:equal>
				</logic:iterate>
			</logic:iterate>
		</logic:present>

		<TABLE WIDTH="100%" BORDER=0 ALIGN="center">
			<TR>
				<TD>&nbsp;</TD>
			</TR>
			<TR>
				<TD ALIGN="center" WIDTH="50%"><INPUT TYPE="button" value="Print"
					name="btnPrint" CLASS=buttonStyle onclick="Print()">&nbsp;&nbsp;<INPUT
					TYPE="button" value="Cancel" name="btnCancel" CLASS=buttonStyle
					onclick="javascript:window.close();"></TD>
			</TR>
		</TABLE>

		<html:hidden property="hdnSelSpecType" />
</html:form>
</BODY>
</html:html>
