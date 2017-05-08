<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page contentType="application/vnd.ms-excel"%>
<% response.setHeader("Content-Disposition", "inline; filename=\"ClauseComparison.xls\""); %>

<html:html>
<HEAD>
<TITLE>Clause Comparison</TITLE>
<BODY>
<html:form action="/compareSpecAction" method="post">
	<br>
	<br>
	<logic:equal name="ClauseCompareForm" property="orderListSize"
		value="2">
		<TABLE BORDER=0 WIDTH="100%" BORDERCOLOR="#5780ae" ALIGN="center">
			<TR>
				<TD WIDTH="100%" CLASS="dashBoardSubHeading" ALIGN="center"
					colspan="14"><font size="2"><b>Clause Comparison</b></font></TD>
			</TR>
			<TR>
		</TABLE>
	</logic:equal>
	<logic:equal name="ClauseCompareForm" property="orderListSize"
		value="3">
		<TABLE BORDER=0 WIDTH="100%" BORDERCOLOR="#5780ae" ALIGN="center">
			<TR>
				<TD WIDTH="100%" CLASS="dashBoardSubHeading" ALIGN="center"
					colspan="21"><font size="2"><b>Clause Comparison</b></font></TD>
			</TR>
			<TR>
		</TABLE>
	</logic:equal>
	<TABLE WIDTH="100%" BORDER="0" BORDERCOLOR="#5780ae">


		<TR>
			<logic:iterate id="ordListId" name="ClauseCompareForm"
				property="selectedOrderList">
				<!-- <TD CLASS="noborder2b" >&nbsp;</TD> -->
				<TD WIDTH="20%" colspan="7">
				<TABLE BORDER=1 BORDERCOLOR="#5780ae" WIDTH="100%">
					<TR>
						<TD CLASS="noborder2b" colspan="1" width="40%"><font size="2"><b>Order
						Number</b></font></TD>
						<TD CLASS="noborder2c" colspan="7" align="left"><font size="2"><bean:write
							name="ordListId" property="orderNo" /></font></TD>
					</TR>
					<tr>
						<TD CLASS="noborder2b" colspan="1" width="40%"><font size="2"><b>Model</b></font></TD>
						<TD CLASS="noborder2c" colspan="7"><font size="2"><bean:write
							name="ordListId" property="modelName" /></font></TD>
					</TR>
					<TR>
						<TD CLASS="noborder2b" width="40%" colspan="1"><font size="2"><b>Customer
						Name</b></font></TD>
						<TD CLASS="noborder2c" colspan="7"><font size="2"><bean:write
							name="ordListId" property="customerName" /></font></TD>
					</tr>
					<tr>
						<TD CLASS="noborder2b" width="40%" colspan="1"><font size="2"><b>Spec
						Status</b></font></TD>
						<TD CLASS="noborder2c" colspan="7"><font size="2"><bean:write
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
		<logic:iterate id="componentCompareId" name="ClauseCompareForm"
			property="compareOrderList" indexId="count">
			<tr>
				<td colspan="12">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="12">&nbsp;</td>
			</tr>
			<TR>
				<!-- <TD CLASS="noborder2b" >&nbsp;</TD> -->
				<logic:iterate id="sectionId" name="componentCompareId">
					<TD WIDTH="5%" style="align:top"><logic:present name="sectionId"
						property="subSecName">
						<TABLE BORDER=1 BORDERCOLOR="#5780ae" WIDTH="100%">
							</logic:present>
							<logic:notPresent name="sectionId" property="subSecName">
								<TABLE BORDER=0 WIDTH="100%">
									</logic:notPresent>
									<logic:equal name="count" value="0">
										<TR>

											<TD colspan="8" height="10%" CLASS=lightblue ALIGN="center"><font
												size="2"><b><bean:write name="sectionId" property="secName" /></b></font></TD>
										</TR>
									</logic:equal>
									<TR>
										<TD colspan="8" height="10%" CLASS=noborder2 ALIGN="center"><font
											size="2"><b><bean:write name="sectionId"
											property="subSecName" /></b></font></TD>
									</TR>
									<logic:notEmpty name="sectionId" property="clauseGroup">
										<TR>
											<TD WIDTH="10%" height="10%" CLASS="table_heading"><font
												size="2"><b>Clause Number</b></font></TD>
											<TD WIDTH="30%" colspan="6" height="10%"
												CLASS="table_heading"><font size="2"><b>Description</b></font></TD>
												<TD WIDTH="30%"  height="10%"
												CLASS="table_heading"><font size="2"><b>Engineering Data</b></font></TD>
										</TR>
										<logic:iterate name="sectionId" id="componentDescId"
											property="clauseGroup">
											<TR>
												<TD WIDTH="30%" height="10%" align="top"
													CLASS="borderbottomleft2"><bean:write
													name="componentDescId" property="clauseNum" /></TD>
												<TD WIDTH="30%" height="10%" align="left" colspan="6"
													CLASS="borderbottomleft2">
										  
										  <!--Added for CR-74 VV49326 16-06-09-->					           
									      <logic:equal name="componentDescId" property="clauseDelFlag" value="Y">
										  <bean:message key="Message.Reserved" />
										  </logic:equal>												
									
										  <logic:notEqual name="componentDescId" property="clauseDelFlag" value="Y">
																					
								            <!--Added for CR-74 VV49326 16-06-09-->
												<table width="100%" height="100%">
													<tr>
														<td colspan="4"><bean:define name="componentDescId"
															property="clauseDesc" id="clauseDesc" />
															<!-- CR-128 - Updated for Pdf issue -->
																<%String strClauseDesc =  String.valueOf(clauseDesc);
																if(strClauseDesc.startsWith("<p>")){%>
																	<%=(String.valueOf(clauseDesc))%>
																<%}else{ %>	
														<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>
																<%}%>
															<!-- CR-128 - Updated for Pdf issue Ends Here-->
														</TD>
													</tr>
													<tr>
														<td colspan="5"><logic:notEmpty name="componentDescId"
															property="tableArrayData1">
															<table width="100%" height="100%" border="1">
																<logic:iterate id="outter" name="componentDescId"
																	property="tableArrayData1" indexId="counter">
																	<bean:define id="row" name="counter" />
																	<tr>
																		<logic:iterate id="tabrow" name="outter">
																			<logic:equal name="row" value="0">
																				<td valign="top" width="5%" colspan="1"
																					CLASS='borderbottomleft' align="left"><b><font
																					color="navy"><%=(tabrow == null) ? "&nbsp;"
																	: tabrow%></b></font></td>
																			</logic:equal>
																			<logic:notEqual name="row" value="0">
																				<td valign="top" width="5%" colspan="1"
																					CLASS='borderbottomleft' align="left"><%=(tabrow == null) ? "&nbsp;"
																	: tabrow%></td>
																			</logic:notEqual>
																		</logic:iterate>
																	</tr>
																</logic:iterate>
															</table>
														</logic:notEmpty></td>
													</tr>
												</table>
												<!--Added for CR-74 16-06-09-->
												</logic:notEqual>
												</TD>
												
												<!-- Added  Engg data as per CR 75 by cm68219    -->
								   <!--Added for CR-74 for Engg Data 16-06-09 -->
									<logic:equal name="componentDescId" property="clauseDelFlag" value="Y">
									<TD width="30%" CLASS=borderbottomleft nowrap align="left">&nbsp;</TD>
									</logic:equal>
									
									<logic:notEqual name="componentDescId" property="clauseDelFlag" value="Y">
									<!--Added for CR-74 16-06-09 -->
									<TD CLASS=borderbottomleft nowrap align="left">
									<!-- Added For CR_81 to bring Charstc Edl and Refedl Starts -->
									<logic:equal name="componentDescId" property="selectCGCFlag" value="Y">
											<!--<logic:notEmpty	name="componentDescId" property="charRefEDLNo">
													ref: EDL&nbsp;
													<bean:write name="componentDescId" property="charRefEDLNo" />
													<br>	
											</logic:notEmpty>-->
											<logic:notEmpty	name="componentDescId" property="charEdlNo">
													EDL&nbsp;
													<bean:write name="componentDescId" property="charEdlNo" />
													<br>
											</logic:notEmpty>
											<!-- CR 87 -->
											<logic:notEmpty	name="componentDescId" property="charRefEDLNo">
													<bean:message key="screen.refEdl.start" />&nbsp;
													<bean:write name="componentDescId" property="charRefEDLNo" />&nbsp;
													<bean:message key="screen.refEdl.end" />
													<br>	
											</logic:notEmpty>
											<logic:empty name="componentDescId" property="charEdlNo">	
											<logic:empty name="componentDescId" property="charRefEDLNo">			
													EDL Undeveloped
													<br>
											</logic:empty>
											</logic:empty>
									</logic:equal>
									<!-- Added For CR_81 to bring Charstc Edl and Refedl Ends -->
									 
									 <!-- <logic:notEmpty  name="componentDescId" property="refEDLNO">
										<logic:iterate id="refedl" name="componentDescId"
											property="refEDLNO">
		  	                                ref: EDL&nbsp;<bean:write name="refedl" />
											<br>
										</logic:iterate>
									</logic:notEmpty> 
									-->
									<logic:notEmpty name="componentDescId" property="edlNO">
									  <logic:iterate id="edl" name="componentDescId" property="edlNO">
		  	                               EDL&nbsp;<bean:write name="edl" />
											<br>
										</logic:iterate>
									</logic:notEmpty> 
									<!-- 87 -->
									<logic:notEmpty  name="componentDescId" property="refEDLNO">
										<logic:iterate id="refedl" name="componentDescId"
											property="refEDLNO">
		  	                               <bean:message key="screen.refEdl.start" /> &nbsp;
		  	                               <bean:write name="refedl" /> &nbsp;
		  	                               <bean:message key="screen.refEdl.end" />
											<br>
										</logic:iterate>
									</logic:notEmpty> 
									<logic:notEmpty name="componentDescId"	property="partOF">
										<logic:iterate id="partof" name="componentDescId"
											property="partOF" type="com.EMD.LSDB.vo.common.SubSectionVO">
		  	                               <bean:message key="screen.partOf" />&nbsp;<bean:write name="partof" property="subSecCode" />
											<br>
										</logic:iterate>
									</logic:notEmpty> 
									<logic:notEmpty name="componentDescId"  property="dwONumber">
										<logic:notEqual name="componentDescId" property="dwONumber"	value="0">
		  	                              DWO&nbsp;<bean:write name="componentDescId" property="dwONumber" />
											<br>
										</logic:notEqual>
									</logic:notEmpty>
								 <logic:notEmpty name="componentDescId"	property="partNumber">
									<logic:notEqual name="componentDescId" property="partNumber" value="0">
		  	                             Part No&nbsp;<bean:write name="componentDescId"	property="partNumber" />
											<br>
										</logic:notEqual>
									</logic:notEmpty> 
									<logic:notEmpty name="componentDescId"	property="standardEquipmentDesc">
										<bean:write name="componentDescId"	property="standardEquipmentDesc" />
										<br>
									</logic:notEmpty>
									 <logic:notEmpty name="componentDescId" property="engDataComment">
										<bean:define name="componentDescId" property="engDataComment"
											id="engData" />
										<%=engData%>
										<br>
									</logic:notEmpty>
									</TD>
									<!--Added for CR-74 16-06-09-->
									</logic:notEqual>
									<!-- Added Successafully -->
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
