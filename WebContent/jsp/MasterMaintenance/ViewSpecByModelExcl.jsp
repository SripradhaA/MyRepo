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
<html:form action="/MasterMaintReportAction.do" method="post">
	<br>
	<br>
	<br>
	<br>
	<TABLE width="100%" border="0" align="left">
		<TR>
			<TD align="center" colspan="9">
			<TABLE BORDER=0 WIDTH="100%" ALIGN="center">
				<TR>
					<TD>&nbsp;</TD>
				</TR>

				<TR>
					<TD align="center" colspan="9"><b><bean:message
						key="Application.Screen.MaintReport" /></b></TD>
				</TR>
				<TR>
					<TD align="center" colspan="9">&nbsp;</TD>
				</TR>
			<!--Added for CR-111 QA Fixes to differentiate the default and deleted clauses-->
				<TR>
					<TD align=center colspan="9" ><!---  Added for CR-113 @vipul to turn on/off deleted clause versions  ---->

					<logic:equal name="MasterMaintForm" property="showDeletedClauses"
						value="Y">
						<bean:message key="DeletedClauseVersionMsg" />
						<br />
					</logic:equal> <bean:message key="DefaultClauseVersionMsg" /></TD>
					<!---end @vipul------->
				</TR>
				<TR>
					<TD align="center" colspan="9">&nbsp;</TD>
				</TR>
			<!--Added for CR-111 QA Fixes to differentiate the default and deleted clauses Ends Here-->
			</TABLE>

			<TABLE ALIGN="center" WIDTH="100%" BORDER="0">
				<TR>

					<TD colspan="9" ALIGN="center"><b>Specification Type :</b>&nbsp; <bean:write
						name="MasterMaintForm" property="hdnSelSpecType" />&nbsp;&nbsp;<b><bean:message key="master.model" />
					:</b>&nbsp; <bean:write name="MasterMaintForm" property="modelName" /></TD>
				<TR>
					<TD>&nbsp;</TD>
				</TR>
			</TABLE>
			<logic:notPresent name="MasterMaintForm"
				property="modelSubSectionList">
				<TABLE ALIGN="center" WIDTH="100%" BORDER="0">
					<TR>
						<TD colspan="9" ALIGN="center"><br>
						<bean:message key="Application.Screen.NoRecords" /></TD>
					</TR>
				</TABLE>
			</logic:notPresent> <logic:present name="MasterMaintForm"
				property="modelSubSectionList">
				<logic:iterate id="modelsubsection" name="MasterMaintForm"
					property="modelSubSectionList" indexId="subsectionCount">
					<logic:iterate id="modelspecs" name="MasterMaintForm"
						property="modelSpecList" indexId="specCount">
						<bean:define id="speccount" name="specCount"></bean:define>
						<logic:equal name="subsectionCount"
							value="<%=String.valueOf(speccount)%>">

							<logic:iterate id="subsection" name="modelsubsection"
								indexId="subSectionIndex">

								<logic:equal name="subSectionIndex" value="0">
									<logic:iterate id="section" name="MasterMaintForm"
										property="sectionList" indexId="sectionCount">
										<logic:equal name="sectionCount"
											value="<%=String.valueOf(speccount)%>">
											<TABLE BORDER="0" WIDTH="100%" ALIGN="center">
												<TR>
													<TH colspan="9" align="center" bgcolor="#BAB9C4"><bean:write
														name="section" property="sectionCode" />.<bean:write
														name="section" property="sectionName" /></TH>
												</TR>
											</TABLE>
										</logic:equal>
									</logic:iterate>
								</logic:equal>
								<TABLE WIDTH="100%" BORDER="0" ALIGN="center">
									<TR>
										<TD ALIGN="center" colspan="9"><b> <bean:write
											name="subsection" property="subSecCode" />.<bean:write
											name="subsection" property="subSecName" /></b> <bean:define
											id="subSectionNo" name="subsection" property="subSecSeqNo" />
										</TD>
									</TR>
								</TABLE>

								<TABLE WIDTH="90%" ALIGN="center" BORDER="1">
									<TR>
									<!------  Added for CR-113 @vipul --->
										<TH align="center" width="10%">LSDB Unique Clause Number</TH>
										<TH align="center" width="10%">Clause Version</TH>
										<!--Commented in 113 @vipul-<TH align="center" width="10%">Price Book No</TH>-->
										<!---Ends here-->
										<TH align="center" width="30%">Clause Description</TH>
										<TH align="center" width="10%">Engineering Data</TH>
										<TH align="center" width="10%">Component</TH>
										<!------  Added for CR-113 @vipul --->
										<TH align="center" width="10%">Component Group</TH>
										<!---Ends here-->
										<TH align="center" width="10%">Customer</TH>
										<TH align="center" width="10%">Modified By</TH>
										<TH align="center" width="10%">Modified Date</TH>
									</TR>
									<logic:iterate id="spec" name="modelspecs">
										<logic:equal name="spec" property="subSectionSeqNo"
											value="<%=String.valueOf(subSectionNo)%>">
											<!--Added for CR-111 QA Fixes to differentiate the default and deleted clauses-->
											<logic:equal name="spec" property="clauseDelFlag" value="Y">
												<!----  Added for CR-113 @vipul to turn on/off deleted clause versions  ------>
												<logic:equal name="MasterMaintForm"
													property="showDeletedClauses" value="Y">
													<TR height="100%" style="color:red">
												</logic:equal>
												<logic:notEqual name="MasterMaintForm"
													property="showDeletedClauses" value="Y">
													<TR height="100%" style="display:none ">
												</logic:notEqual>
											</logic:equal>
											
											<logic:notEqual name="spec" property="clauseDelFlag"
												value="Y">
												<logic:equal name="spec" property="defaultFlag" value="Y">
													<TR height="100%" style="font-weight: bold;">
												</logic:equal>
												<logic:notEqual name="spec" property="defaultFlag" value="Y">
													<TR height="100%">
												</logic:notEqual>
											</logic:notEqual>
											
											<!--Added for CR-111 QA Fixes to differentiate the default and deleted clauses Ends Here-->
											
												<!--commented on cr-113 @vipul---<TD align="center" valign="top"><logic:notEqual name="spec"
													property="priceBookNumber" value="0">
													<bean:write name="spec" property="priceBookNumber" />
												</logic:notEqual>&nbsp;</TD>-->
												<TD align="center" valign="top"><bean:write name="spec"
												property="clauseSeqNo" /></TD>
											<TD align="center" valign="top"><bean:write name="spec"
												property="versionNo" /></TD>
												<!------end @vipul----->
											
												<TD valign="top"><bean:define name="spec"
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

													<table BORDER="1" align="center">
														<logic:iterate id="outter" name="spec"
															property="tableArrayData1" indexId="counter">
															<bean:define id="row" name="counter" />
															<!--Added for CR-111 QA Fixes to differentiate the default and deleted clauses-->
															<logic:equal name="spec" property="clauseDelFlag"
															value="Y">
															<!-----  Added for CR-113 @vipul to turn on/off deleted clause versions  ----->

															<logic:equal name="MasterMaintForm"
																property="showDeletedClauses" value="Y">
																<TR height="100%" style="color:red">
															</logic:equal>
															<logic:notEqual name="MasterMaintForm"
																property="showDeletedClauses" value="Y">
																<TR height="100%" style="display:none ">
															</logic:notEqual>
														</logic:equal>

														<logic:notEqual name="spec" property="clauseDelFlag"
															value="Y">
															<logic:equal name="spec" property="defaultFlag" value="Y">
																<TR style="font-weight: bold;">
															</logic:equal>
															<logic:notEqual name="spec" property="defaultFlag"
																value="Y">
																<TR>
															</logic:notEqual>
														</logic:notEqual>
														<!-----Added for CR-113 @vipul to turn on/off deleted clause versions ends here---->

														
														<!--Added for CR-111 QA Fixes to differentiate the default and deleted clauses Ends Here-->
											
																<logic:iterate id="tabrow" name="outter">
																	<logic:equal name="row" value="0">
																		<th align="center" width="5%"><%=(tabrow==null)? "&nbsp;":tabrow%>
																		</th>
																	</logic:equal>
																	<logic:notEqual name="row" value="0">
																		<td align="center" width="5%"><%=(tabrow==null)? "&nbsp;":tabrow%>
																		</td>
																	</logic:notEqual>

																</logic:iterate>
															<!--Added for CR-111 QA Fixes to differentiate the default and deleted clauses-->
															</TR>
														<!--Added for CR-111 QA Fixes to differentiate the default and deleted clauses Ends Here-->
															
														</logic:iterate>

													</table>

												</logic:notEmpty></TD>
												<TD align="left">
												<!--<logic:present name="spec"
													property="refEDLNO">

													<logic:iterate id="engDataRefEdlNo" name="spec"
														property="refEDLNO">

														<logic:notEqual name="engDataRefEdlNo" value="0">

															<bean:message key="screen.refEdl" />
															<bean:write name="engDataRefEdlNo" />
															<br>

														</logic:notEqual>

													</logic:iterate>

												</logic:present>-->
												 <logic:present name="spec" property="edlNO">
													<logic:iterate id="engDataEdlNo" name="spec"
														property="edlNO">

														<logic:notEqual name="engDataEdlNo" value="0">

															<bean:message key="screen.edl" />
															<bean:write name="engDataEdlNo" />
															<br>

														</logic:notEqual>

													</logic:iterate>

												</logic:present>
												<!--CR 87 -->
												<logic:present name="spec"
													property="refEDLNO">

													<logic:iterate id="engDataRefEdlNo" name="spec"
														property="refEDLNO">

														<logic:notEqual name="engDataRefEdlNo" value="0">

															<bean:message key="screen.refEdl.start" />&nbsp;
															<bean:write name="engDataRefEdlNo" />&nbsp;
															<bean:message key="screen.refEdl.end" />															
															<br>

														</logic:notEqual>

													</logic:iterate>

												</logic:present>
												 <logic:present name="spec"
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

													<logic:notEqual name="spec" property="dwONumber" value="0">

														<bean:message key="screen.partNo" />
														<bean:write name="spec" property="partNumber" />
														<br>

													</logic:notEqual>

												</logic:present> <logic:present name="spec"
													property="standardEquipmentDesc">

													<bean:write name="spec" property="standardEquipmentDesc" />
													<br>

												</logic:present> <logic:present name="spec"
													property="engDataComment">

													<bean:write name="spec" property="engDataComment" />
													<br>

												</logic:present></TD>
												<TD><logic:present name="spec" property="componentVO">
													<logic:iterate id="component" name="spec"
														property="componentVO" indexId="compntIndex">
														<logic:notEqual name="compntIndex" value="0">
															<br>
														</logic:notEqual>
														<bean:write name="component" property="componentName" />
													</logic:iterate>
												</logic:present></TD>
												<!-------------------  Added for CR-113 @vipul ------------->
											
											
											<TD><logic:present name="spec" property="componentVO">
												<logic:iterate id="component" name="spec"
													property="componentVO" indexId="compntIndex">
													<logic:notEqual name="compntIndex" value="0">
														<br>
													</logic:notEqual>
													<bean:write name="component" property="componentGroupName" />
												</logic:iterate>
											</logic:present></TD>
												<!------------Added for CR-113 @vipul ends here-------------->						
					
												<TD align="center" valign="top"><bean:write name="spec"
													property="customerName" />&nbsp;</TD>
												<TD align="center" valign="top"><bean:write name="spec"
													property="modifiedBy" /></TD>
												<TD align="center" valign="top"><bean:write name="spec"
													property="modifiedDate" /></TD>
											<!--Added for CR-111 QA Fixes to differentiate the default and deleted clauses-->
											</TR>
											<!--Added for CR-111 QA Fixes to differentiate the default and deleted clauses Ends Here-->
	
										</logic:equal>
									</logic:iterate>
								</TABLE>
								<br>
								<br>
								<br>
							</logic:iterate>
						</logic:equal>
					</logic:iterate>
				</logic:iterate>
			</logic:present> </html:form>
</BODY>
</html:html>
