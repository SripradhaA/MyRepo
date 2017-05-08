<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="com.EMD.LSDB.vo.common.ClauseVO"%>
<%@page import="com.EMD.LSDB.vo.common.CompGroupVO"%>
<HTML>
<HEAD>
<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
<!-- Added for CR_121 -->
<SCRIPT LANGUAGE="JavaScript" SRC="js/Others/jquerymin.js"></SCRIPT>
<LINK REL="stylesheet" TYPE="text/css" HREF="css/jquery-ui-custom.min.css">
<SCRIPT LANGUAGE="JavaScript" SRC="js/Others/jquery-ui.custom.min.js"></SCRIPT>
<!-- Added for CR_121 Ends-->

</HEAD>
<LINK REL="stylesheet" HREF="css/Emd_Lsdb.css" TYPE="text/css">
<LINK REL="stylesheet" TYPE="text/css" HREF="css/EmdMenu.css">
<SCRIPT language="JavaScript" SRC="js/CreateChangeRequestClause.js"></SCRIPT>
<SCRIPT language="JavaScript" SRC="js/Emd_Lsdb.js"></SCRIPT>
<SCRIPT language="JavaScript" SRC="js/error.js"></SCRIPT>
<SCRIPT language="JavaScript" SRC="js/error_messages.js"></SCRIPT>
<BODY CLASS='main'>
<html:form action="/CreateChangeReqClauseAction.do">
	<TABLE BORDER=0 WIDTH="100%" CELLPADDING="0" CELLSPACING="0">
		<TR>
			<TD>&nbsp;</TD>
		</TR>
		<TR>
			<TD align="center" CLASS="dashBoardSubHeading" width="60%">CR Form -
			Select Clause</TD>
		</TR>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<TR>
			<TD><!-- Validation Message Display Part Starts Here -->
			<%--Table Updated for CR-121 for server side error message tooltip --%>
			<table border="0" cellpadding="0" cellspacing="0" width="20%" align="center">
				   <tr>
			          <td nowrap="nowrap">
						<div class="errorlayerhide" id="errorlayer"
							style="position:relative; overflow: auto; height:0; visibility:hidden; z-index: 2">
						</div>
					  </td>
				   </tr>
			</table>
			<!-- Validation Message Display Part Ends Here --> <!-- Logic Tag Check For Display The Success Message After Add Starts Here -->


			<logic:present name="CreateChangeClauseRequestForm"
				property="messageID" scope="request">
				<%--Added for CR-121 for server side error message tooltip --%>	
				  <bean:define id="messageID" name="CreateChangeClauseRequestForm" property="messageID"/>
				  <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
			      <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
			</logic:present> <!-- Logic Tag Check For Display The Success Message After Add Functionality Ends Here -->
			<!-- Logic Tag Check For Display The Error Message After Add Functionality Starts Here -->
			<logic:present name="CreateChangeClauseRequestForm"
				property="errorMessage" scope="request">
				<script>
                    hideErrors();
                    addMessage('<bean:write name="CreateChangeClauseRequestForm" property="errorMessage"/>');
                    showErrors();
                </script>

			</logic:present> <logic:present name="CreateChangeClauseRequestForm"
				property="componentVO">
				<bean:size name="CreateChangeClauseRequestForm" id="clasize"
					property="componentVO" />
			</logic:present> <!-- Logic Tag Check For Display The Error Message After Add Functionality Ends Here -->

			<br>
			<TABLE WIDTH="30%" ALIGN=CENTER>

				<TR>
					<TD WIDTH="15%" ALIGN="left" CLASS=headerbgcolor nowrap>Model</TD>
					<TD WIDTH="20%" nowrap CLASS="navycolor"><bean:write
						name="CreateChangeClauseRequestForm" property="modelName"
						scope="request" /></TD>
				</TR>
				<TR>
					<TD WIDTH="15%" ALIGN="left" CLASS=headerbgcolor nowrap>Section</TD>
					<TD WIDTH="20%" nowrap CLASS="navycolor"><bean:write
						name="CreateChangeClauseRequestForm" property="sectionName" /></TD>
				</TR>
				<TR>
					<TD WIDTH="20%" ALIGN="left" CLASS=headerbgcolor nowrap>SubSection</TD>
					<TD WIDTH="20%" nowrap CLASS="navycolor"><bean:write
						name="CreateChangeClauseRequestForm" property="subSectionName" />
					</TD>
				</TR>
				<TR>
					<TD>&nbsp;</TD>
				</TR>
			</TABLE>
			</TD>
		</TR>
		<TR>
			<TD>&nbsp;</TD>
		</TR>
		<TR>
			<logic:equal name="clasize" value="0">
				<TABLE WIDTH="20%" ALIGN=center>
					<TR>
						<TD ALIGN=center><INPUT CLASS="buttonStyle"
							TYPE="button" name="Cancel" value="Close" onclick="fnCancel()"></TD>
					</TR>
				</TABLE>
			</logic:equal>
			<logic:greaterThan name="clasize" value="0">
				<TD ALIGN=center>
				<TABLE BORDERCOLOR="#5780ae" class="borderbottomrightlight" BORDER=1
					WIDTH="85%" CELLSPACING=1 CELLPADDING=1
					name="sessionScope.addClauseForm.componentVO">
					<TR>
						<TD WIDTH="2%" CLASS=table_heading>Select</TD>
						<TD WIDTH="5%" CLASS=table_heading>Clause No</TD>
						<TD WIDTH="15%" CLASS=table_heading>Clause Description</TD>
						<TD WIDTH="5%" CLASS=table_heading>Engineering Data</TD>
						<TD WIDTH="5%" CLASS=table_heading>Component</TD>
					</TR>

					<logic:iterate id="compParent" name="CreateChangeClauseRequestForm"
						property="componentVO" scope="request"
						type="com.EMD.LSDB.vo.common.ClauseVO" indexId="clausecount">
						<TR class="borderbottomrightlight">
							<TD CLASS=borderbottomrightlight><html:radio
								property="clauseSeqNo" styleClass="radcheck"
								value='<%=String.valueOf(compParent.getClauseSeqNo())%>' /> <html:hidden
								name="compParent" property="versionNo" /></TD>

							<TD CLASS=borderbottomrightlight><logic:present name="compParent"
								property="clauseNum">
								<bean:write name="compParent" property="clauseNum" />
								<html:hidden name="compParent" property="clauseNum" />
							</logic:present></TD>

							<TD CLASS=borderbottomrightlight><span style="width:435px">
							<div id="clauseID<%=clausecount%>" align="left"
								STYLE="float:left;height:60;width:435;overflow: auto ; "><bean:define
								name="compParent" property="clauseDesc" id="clauseDesc" /> 
								<!-- CR-128 - Updated for Pdf issue -->
							<%String strClauseDesc =  String.valueOf(clauseDesc);
							if(strClauseDesc.startsWith("<p>")){%>
								<%=(String.valueOf(clauseDesc))%>
							<%}else{ %>	
								<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>							
							<%}%>
							<!-- CR-128 - Updated for Pdf issue Ends Here-->
							<table width="100%" BORDER="1" BORDERCOLOR="">
								<logic:notEmpty name="compParent" property="tableArrayData1">
										&nbsp;

				 						<logic:iterate id="outter" name="compParent"
										property="tableArrayData1" indexId="counter">

										<bean:define id="row" name="counter" />
										<!-- Added  For CR_93 -->
											<bean:define name="compParent" property="tableDataColSize" id="tableDataColSize" />
										<tr>
											<logic:iterate id="tabrow" name="outter" length="tableDataColSize">

												<logic:equal name="row" value="0">
													<td valign="top" width="5%" class="borderbottomleft1"><b><font
														color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></b></font>
													</td>
												</logic:equal>

												<logic:notEqual name="row" value="0">
													<td valign="top" width="5%" class="borderbottomleft1"><%=(tabrow==null)? "&nbsp;":tabrow%>
													</td>
												</logic:notEqual>


											</logic:iterate>
										</tr>
									</logic:iterate>
								</logic:notEmpty>
							</table>

							<bean:define id="cla" name="compParent" property="clauseDesc" />
							<bean:define id="tab" name="compParent"
								property="tableArrayData1" /> <input type="hidden"
								name="clauseDescTmp" value="<%=cla%>"> <input type="hidden"
								name="tableArrayData1Tmp" value="<%=tab%>"> <!-- <html:hidden  name="compParent" property="clauseDesc"/> 	
					   			<html:hidden name="compParent" property="tableArrayData1"/> -->
							</div>
							</span></TD>

							<TD CLASS=borderbottomleft1>
							<DIV
								STYLE="height:63;width:120;overflow-Y: auto ; height:63;width:120;overflow-X: auto ; ">

							<table align="left">
								<tr>
									<td>
									<!--<logic:present name="compParent" property="refEDLNO">

										<logic:iterate id="engDataRefEdlNo" name="compParent"
											property="refEDLNO">

											<!--  <logic:notEqual name="engDataRefEdlNo" value="0"> -->

										<!-- 	<bean:message key="screen.refEdl" />
											<bean:write name="engDataRefEdlNo" />
											<br>

											<!--  </logic:notEqual> -->

									<!--  	</logic:iterate>

									</logic:present> -->
									
									<logic:present name="compParent"
										property="edlNO">

										<logic:iterate id="engDataEdlNo" name="compParent"
											property="edlNO">

											<!--   <logic:notEqual name="engDataEdlNo" value="0"> -->

											<bean:message key="screen.edl" />
											<bean:write name="engDataEdlNo" />
											<br>

											<!--  </logic:notEqual> -->

										</logic:iterate>

									</logic:present> 
									<!-- CR 87 -->
									<logic:present name="compParent" property="refEDLNO">

										<logic:iterate id="engDataRefEdlNo" name="compParent"
											property="refEDLNO">

											<!--  <logic:notEqual name="engDataRefEdlNo" value="0"> -->

											<bean:message key="screen.refEdl.start" />
											<bean:write name="engDataRefEdlNo" />
											<bean:message key="screen.refEdl.end" />
											<br>

											<!--  </logic:notEqual> -->

										</logic:iterate>

									</logic:present> 
									
									<logic:present name="compParent"
										property="partOF">

										<logic:iterate id="engPartOfCd" name="compParent"
											property="partOF">

											<logic:notEqual name="engPartOfCd" value="0">

												<bean:message key="screen.partOf" />
												<bean:write name="engPartOfCd" />
												<br>

											</logic:notEqual>

										</logic:iterate>

									</logic:present> <logic:present name="compParent"
										property="dwONumber">

										<logic:notEqual name="compParent" property="dwONumber"
											value="0">

											<bean:message key="screen.dwoNo" />
											<bean:write name="compParent" property="dwONumber" />
											<br>

										</logic:notEqual>

									</logic:present> <logic:present name="compParent"
										property="partNumber">
										<!--  Modified since it was checking dWonumber instead of part number -->
										<logic:notEqual name="compParent" property="partNumber"
											value="0">

											<bean:message key="screen.partNo" />
											<bean:write name="compParent" property="partNumber" />
											<br>

										</logic:notEqual>

									</logic:present> <!--Price Book Number is added in the Engineering data Section, modified on 30-09-08 by ps5722-->

									<logic:present name="compParent" property="priceBookNumber">

										<logic:notEqual name="compParent" property="priceBookNumber"
											value="0">

											<bean:message key="screen.priceBookNo" /> &nbsp;<bean:write
												name="compParent" property="priceBookNumber" />
											<br>

										</logic:notEqual>

									</logic:present> <logic:present name="compParent"
										property="standardEquipmentDesc">

										<bean:write name="compParent" property="standardEquipmentDesc" />
										<br>

									</logic:present> <logic:present name="compParent"
										property="comments">
										<bean:define id="engDatCmnt" name="compParent"
											property="comments" />
										<%=engDatCmnt %>
										<br>

									</logic:present>
							</table>

							</DIV>
							</TD>

							<!-- Starts-->
							<logic:notEmpty name="compParent" property="compName">
								<TD class="borderbottomrightlight1" valign="top"><logic:iterate
									id="compList" name="compParent" property="compName"
									scope="page" indexId="compntIndex">

									<bean:write name="compList" />
									<br>

								</logic:iterate></TD>
							</logic:notEmpty>

							<logic:empty name="compParent" property="compName">
								<td class="borderbottomrightlight">&nbsp;</td>
							</logic:empty>

							<!-- Ends-->

						</TR>
					</logic:iterate>

				</TABLE>

				<br>
				<TABLE ALIGN=center>
					<TR>
						<TD>&nbsp;</TD>
						<TD COLSPAN=4 ALIGN=center><INPUT CLASS="buttonStyle"
							TYPE="button" name="Apply" value="OK"
							onclick="javascript:fnSelectClauses()"></TD>

						<TD COLSPAN=4 ALIGN=center><INPUT CLASS="buttonStyle"
							TYPE="button" name="Cancel" value="Cancel" onclick="fnCancel()"></TD>
					</TR>
				</TABLE>
			</logic:greaterThan>
		</TBODY>
		<html:hidden property="hdnClauseSeqNo" />
		<html:hidden property="hdnVersionNo" />
		<html:hidden property="hdnClauseNum" />
		<html:hidden property="hdnModelSeqNo" />
		<html:hidden property="hdnSubSectionSeqNo" />
		<html:hidden property="changeTypeSeqNO" />
		</html:form>
</BODY>
</HTML>
