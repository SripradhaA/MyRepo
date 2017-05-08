<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page contentType="application/vnd.ms-excel"%>
<% response.setHeader("Content-Disposition", "inline; filename=\"EDLComparison.xls\""); %>
<html:html>
<HEAD>
<TITLE>EDL Comparison/Report</TITLE>
<BODY>
<html:form action="/compareComponentAction" method="post">
	<br>
	<br>
	<!-- Changed for Heading display based on the selection of orders-->
	<bean:define id="ordrsize" name="ComponentCompareForm" property="orderListSize"/>
		<TABLE BORDER=0 WIDTH="100%" BORDERCOLOR="#5780ae" ALIGN="center">
			<TR> <%-- Modified title for LSDB_CR-105 on 05-Jan-12 by RR68151 --%>
				<TD WIDTH="100%" CLASS="dashBoardSubHeading" ALIGN="center"
					colspan="<%=(Integer.valueOf(String.valueOf(ordrsize))).intValue()*4%>"><font size="2"><b>EDL Comparison/Report</b></font></TD>
			</TR>
			<TR>
				<TD WIDTH="100%" CLASS="dashBoardSubHeading" ALIGN="center"
					colspan="<%=(Integer.valueOf(String.valueOf(ordrsize))).intValue()*4%>">&nbsp;</TD>
			</TR>
		</TABLE>
	
	<TABLE WIDTH="100%" BORDER="0" BORDERCOLOR="#5780ae">
		<TR>
			<TD>
			<TABLE WIDTH="100%" BORDER="0" BORDERCOLOR="#5780ae">
				<TR>	
					<TD width="10%" style="align:top">
						<TABLE BORDER=0 WIDTH="100%" height="100%">
						<TR>	
							<TD width="10%" style="align:top">
								<TABLE BORDER=0 WIDTH="100%" height="100%">
								<TR>
								<logic:iterate id="ordListId" name="ComponentCompareForm"
									property="selectedOrderList" type="com.EMD.LSDB.vo.common.ClauseVO">
									<TD width="10%" style="align:top">
									<TABLE BORDER=1 BORDERCOLOR="#5780ae" height="100%" WIDTH="100%">
										<TR>
											<%-- Modified for CR_106 to accomodate RefEDL by RR68151 --%>
											<TD CLASS="noborder2b" colspan="2">Order Number</TD>
											<TD CLASS="noborder2d" align="left" colspan="2"><b><bean:write
												name="ordListId" property="orderNo" /></b></TD>
										</TR>
										<tr>
											<TD CLASS="noborder2b" colspan="2">Model</TD>
											<TD CLASS="noborder2d" align="left" colspan="2"><b><bean:write
												name="ordListId" property="modelName" /></b></TD>
										</TR>
										<TR>
											<TD CLASS="noborder2b" colspan="2">Customer Name</TD>
											<TD CLASS="noborder2d" align="left" colspan="2"><b><bean:write
												name="ordListId" property="customerName" /></b></TD>
										</tr>
										<tr>
											<TD CLASS="noborder2b" colspan="2">Spec Status</TD>
											<TD CLASS="noborder2d" align="left" colspan="2"><b><bean:write
												name="ordListId" property="specTypeName" /></b></TD>
										</TR>
									</TABLE>
									</TD>
								</logic:iterate>
								</TR>
								</TABLE>
							</TD>
						</TR>
						</TABLE>
					</TD>
				</TR>
			</TABLE>
			</TD>
		</TR>
		
	 <TR>
		<TD>
			<TABLE BORDER=0 WIDTH="100%">
				<TR>
					<TD>&nbsp;</TD>
				</TR>
				<logic:iterate id="componentCompareId" name="ComponentCompareForm"
					property="compareOrderList"   indexId="count">
			  	<TR>
				<logic:iterate id="sectionId" name="componentCompareId" indexId="seccount" type="com.EMD.LSDB.vo.common.SectionVO">
					<logic:notEmpty name="sectionId" property="subSec">
				    <TD width="10%" style="align:top">
						<TABLE BORDER=0 WIDTH="100%" height="100%" class="sectable">
			 			<logic:iterate id="subSecList" name="sectionId" property="subSec" indexId="subseccount">
			 						<logic:notEmpty name="subSecList">
									<TR>
									<logic:iterate id="subSec" name="subSecList" type="com.EMD.LSDB.vo.common.SubSectionVO">
										<logic:notEmpty name="subSec" property="clauseGroup">
										<TD width="10%" VALIGN="TOP">
											<TABLE BORDER=0 WIDTH="100%" class="subsectable">
											<logic:iterate id="clauseList" name="subSec" property="clauseGroup" indexId="subcount">
											<logic:notEmpty name="clauseList">
											<TR>
												<logic:iterate id="clauseId" name="clauseList" type="com.EMD.LSDB.vo.common.ClauseVO" indexId="clacount">
													<TD>
													<TABLE BORDER=1 BORDERCOLOR="#5780ae" WIDTH="100%" height="100%">
													<logic:equal name="count" value="0">
														<logic:equal name="subseccount" value="0">
														<logic:equal name="subcount" value="0">
														<TR>
															<TD width="15%" CLASS="table_heading">Clause Number</TD>
															<TD width="50%" CLASS="table_heading">Component /<font style="font-style:italic">Clause Description</font></TD>
															<TD width="17%" CLASS="table_heading">EDL No</TD>
															<TD width="18%" CLASS="table_heading">Ref EDL No</TD>
														</TR>
														</logic:equal>
														</logic:equal>
													</logic:equal>
													<logic:equal name="subseccount" value="0">
														<logic:equal name="subcount" value="0">														
														<logic:notEmpty name="sectionId" property="secNames">
														<logic:iterate id="secName" name="sectionId" property="secNames" type="java.lang.String" 
															offset="clacount" length="1">
															<logic:notEmpty name="secName">
															<TR>
																<TD colspan="4" width="100%" CLASS=lightblue ALIGN="center">
																	<b> <bean:write name="secName"/></b>
																</TD>
															</TR>
															</logic:notEmpty>															
															<logic:empty name="secName">
															<TR>
																<TD width="10%" colspan="4" height="10%">&nbsp;</TD>
															</TR>
															</logic:empty>
														</logic:iterate>
														</logic:notEmpty>
														</logic:equal>
													</logic:equal>
													<logic:equal name="subcount" value="0">
														<logic:notEmpty name="subSec" property="subSecNames">
														<logic:iterate id="subSecName" name="subSec" property="subSecNames" type="java.lang.String" 
															offset="clacount" length="1">
														<logic:notEmpty name="subSecName">
														<TR>
															<TD colspan="4" width="100%" height="10%" CLASS="noborder2" ALIGN="center">
																<b> <bean:write name="subSecName"/></b>
															</TD>
														</TR>
														</logic:notEmpty>													
														<logic:empty name="subSecName">
														<TR>
															<TD width="10%" colspan="4" height="10%">&nbsp;</TD>
														</TR>
														</logic:empty>
														</logic:iterate>
														</logic:notEmpty>
													</logic:equal>
													<logic:notEmpty name="clauseId" property="clauseNum">
													<TR>
													    <TD CLASS=borderbottomleft1 width="15%" align="center" style="vertical-align:middle;">
														    <bean:write name="clauseId" property="clauseNum"/><BR>
													     </TD>
														<logic:notEmpty name="clauseId" property="componentName">
													    <TD CLASS=borderbottomleft1 width="50%" style="word-wrap: break-word;
													            width:1000px;right:0;OVERFLOW-X:hidden;CURSOR:text;word-break:break-all;vertical-align:middle;">
														   <bean:write name="clauseId" property="componentName"/><BR>
													    </TD>
														</logic:notEmpty>
														<logic:empty name="clauseId" property="componentName">
													    <TD CLASS=borderbottomleft1 width="50%" style="font-style:italic;vertical-align:middle;">
														   <bean:define name="clauseId" property="clauseDesc" id="clauseDesc" /> 
													 		<!-- CR-128 - Updated for Pdf issue -->
																<%String strClauseDesc =  String.valueOf(clauseDesc);
																if(strClauseDesc.startsWith("<p>")){%>
																	<%=(String.valueOf(clauseDesc))%><BR>
																<%}else{ %>	
													 		<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%><BR>
																<%}%>
															<!-- CR-128 - Updated for Pdf issue Ends Here-->
													 		
													 		
													    </TD>
														</logic:empty>
														<!-- Modififed for LSDB_CR-75 on 22-May-09 by ps57222 Starts Here-->
														<logic:equal name="clauseId" property="flag" value="Y">
														<TD CLASS=borderbottomleft1 width="17%" bgcolor="#00CC33" align="center" style="mso-number-format:0;vertical-align:middle;"> 
															   <font color="black"><bean:write name="clauseId" property="edlNum"/></font><BR>
														</TD>
														<%-- Added for CR_106 --%>											
														<TD CLASS=borderbottomleft1 width="18%" align="center" style="mso-number-format:0;vertical-align:middle;">
															<bean:write name="clauseId" property="refEdlNum"/><BR>
														 </TD>
														</logic:equal>
														<logic:notEqual name="clauseId" property="flag" value="Y">
														<TD CLASS=borderbottomleft1 width="17%" align="center" style="mso-number-format:0;vertical-align:middle;">
															<bean:write name="clauseId" property="edlNum"/><BR>
														 </TD>
														<%-- Added for CR_106 --%>	
														<TD CLASS=borderbottomleft1 width="18%" align="center" style="mso-number-format:0;vertical-align:middle;">
															<bean:write name="clauseId" property="refEdlNum"/><BR>
														 </TD>
														</logic:notEqual>
													</TR>
													</logic:notEmpty>
													<logic:empty name="clauseId" property="clauseNum">
													<TR>
														<TD width="100%" colspan="4">&nbsp;</TD>
													</TR>
													</logic:empty>
														<!-- Modififed for LSDB_CR-75 on 22-May-09 by ps57222 Ends Here-->
													</TABLE>
													</TD>
												</logic:iterate>
												</TR>
												</logic:notEmpty>
											</logic:iterate>
											</TABLE>
											</TD>
										</logic:notEmpty>
										<logic:empty name="subSec" property="clauseGroup">
											<logic:notEmpty name="subSec" property="subSecNames">
											<TD width="10%" VALIGN="TOP">
											<TABLE BORDER=1 BORDERCOLOR="#5780ae" WIDTH="100%" class="subsectable">
												<TR>
											<logic:notEmpty name="sectionId" property="secNames">
											<logic:equal name="subseccount" value="0">
												<logic:iterate id="secName" name="sectionId" property="secNames" type="java.lang.String" >
													<logic:notEmpty name="secName">
														<TD width="10%" height="10%" CLASS="lightblue" ALIGN="center">
															 <b><bean:write name="secName"/></b>
														</TD>
													</logic:notEmpty>												
													<logic:empty name="secName">
														<TD width="10%" height="10%">&nbsp;</TD>
													</logic:empty>
												</logic:iterate>
												</TR>
												<TR>
											</logic:equal>
											</logic:notEmpty>
											<logic:notEmpty name="subSec" property="subSecNames">
											<logic:iterate id="subSecName" name="subSec" property="subSecNames" type="java.lang.String" >
												<logic:notEmpty name="subSecName">
													<TD width="10%" height="10%" CLASS="noborder2"  ALIGN="center">
														 <b><bean:write name="subSecName"/></b>
													</TD>
												</logic:notEmpty>
												<logic:empty name="subSecName">
													<TD width="10%" height="10%">&nbsp;</TD>
												</logic:empty>
											</logic:iterate>
											</logic:notEmpty>
											</TR>
											</TABLE>
											</TD>
											</logic:notEmpty>
										</logic:empty>
									</logic:iterate>
									</TR>
									</logic:notEmpty>
						</logic:iterate>
						</TABLE>
					</TD>
					</logic:notEmpty>
			  	</logic:iterate>
		
			  </TR>

			</logic:iterate>
	 </TABLE>
	</TD>
  </TR>
</TABLE>
			
			
	</html:form>
</BODY>
</html:html>
			