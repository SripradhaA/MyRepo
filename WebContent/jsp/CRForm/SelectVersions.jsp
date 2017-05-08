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
			Clause Versions</TD>
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
			</logic:present> <logic:present name="CreateChangeClauseRequestForm"
				property="clauseVersions" scope="request">
				<bean:size name="CreateChangeClauseRequestForm" id="allversize"
					property="clauseVersions" />
			</logic:present> <!-- Logic Tag Check For Display The Success Message After Add Functionality Ends Here -->
			<!-- Logic Tag Check For Display The Error Message After Add Functionality Starts Here -->
			<logic:present name="CreateChangeClauseRequestForm"
				property="errorMessage" scope="request">
				<script>
                    hideErrors();
                    addMessage('<bean:write name="CreateChangeClauseRequestForm" property="errorMessage"/>');
                    showErrors();
                </script>

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
			<logic:greaterThan name="allversize" value="0">
				<logic:present name="CreateChangeClauseRequestForm"
					property="clauseVersions">
					<TD ALIGN=center>
					<input type="hidden" name="noOfClaVersion" value="<bean:write name="allversize" />">
					<TABLE align=center BORDER="1" bordercolor="#5780ae" width="95%">
						<BR>
						<TR>
							<TD class="table_heading">Select</TD>
							<TD class="table_heading">Version No</TD>
							<TD class="table_heading">Clause Description</TD>
							<TD class="table_heading">Engineering Data</TD>
							<TD class='table_heading'>Default</TD>
						</TR>

						<logic:iterate id="clauseRev" name="CreateChangeClauseRequestForm"
							property="clauseVersions" type="com.EMD.LSDB.vo.common.ClauseVO"
							indexId="clausecount">

							<TR>


								<TD class="borderbottomleft1"><html:radio
									value="<%=String.valueOf(clauseRev.getVersionNo())%>"
									styleClass="radcheck" property="versionNo" /></TD>
								<TD class="borderbottomleft1"><bean:write name="clauseRev"
									property="versionNo" /></TD>

								<TD class="borderbottomleft"><span style="width:435px">
								<DIV id="clauseID<%=clausecount%>"
									STYLE="float:left;height:60;width:435;overflow: auto ; "><bean:define
									name="clauseRev" property="clauseDesc" id="clauseDesc" /> 
									<!-- CR-128 - Updated for Pdf issue -->
										<%String strClauseDesc = String.valueOf(clauseDesc);
										if(strClauseDesc.startsWith("<p>")){%>
											<%=(String.valueOf(clauseDesc))%>
										<%}else{ %>	
											<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>							
										<%}%>
									<!-- CR-128 - Updated for Pdf issue Ends Here-->
								<table width="100%" BORDER="1" BORDERCOLOR="">

									<logic:notEmpty name="clauseRev" property="tableArrayData1">
				&nbsp;
				<logic:iterate id="outter" name="clauseRev"
											property="tableArrayData1" indexId="counter">

											<bean:define id="row" name="counter" />
											<!-- Added  For CR_93 -->
											<bean:define name="clauseRev" property="tableDataColSize" id="tableDataColSize" />
											<tr>
												<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
													<logic:equal name="row" value="0">
														<td valign="top" width="5%" CLASS='borderbottomleft1'><b><font
															color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></b></font>
														</td>
													</logic:equal>
													<logic:notEqual name="row" value="0">
														<td valign="top" width="5%" CLASS='borderbottomleft1'><%=(tabrow==null)? "&nbsp;":tabrow%>
														</td>
													</logic:notEqual>
												</logic:iterate>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
								<bean:define id="cla" name="clauseRev" property="clauseDesc" />
								<bean:define id="tab" name="clauseRev"
									property="tableArrayData1" /> <input type="hidden"
									name="clauseDescTmp" value="<%=cla%>"> <input type="hidden"
									name="tableArrayData1Tmp" value="<%=tab%>"></DIV>
								</span></TD>
								<TD class="borderbottomleft" VALIGN="top" nowrap><span
									style="width:130px">
								<DIV STYLE="float:left;height:60;width:130;overflow: auto ; ">
								<!--<logic:notEmpty
									name="clauseRev" property="refEDLNO">

									<logic:iterate id="refedl" name="clauseRev" property="refEDLNO">
										<bean:message key="screen.refEdl" />&nbsp;
							           <bean:write name="refedl" />
										<BR>
									</logic:iterate>

								</logic:notEmpty>-->
								 <logic:notEmpty name="clauseRev"
									property="edlNO">

									<logic:iterate id="edl" name="clauseRev" property="edlNO">
										<bean:message key="screen.edl" />&nbsp;
	           <bean:write name="edl" />
										<BR>
									</logic:iterate>

								</logic:notEmpty> 
								<!-- CR 87 -->
								<logic:notEmpty
									name="clauseRev" property="refEDLNO">

									<logic:iterate id="refedl" name="clauseRev" property="refEDLNO">
										<bean:message key="screen.refEdl.start" />&nbsp;
							           <bean:write name="refedl" />
							           <bean:message key="screen.refEdl.end" />
										<BR>
									</logic:iterate>

								</logic:notEmpty>
								
								<logic:notEmpty name="clauseRev"
									property="partOF">

									<logic:iterate id="code" name="clauseRev" property="partOF">
										<bean:message key="screen.partOf" />&nbsp;
	           <bean:write name="code" />
										<BR>
									</logic:iterate>

								</logic:notEmpty> <logic:greaterThan name="clauseRev"
									property="dwONumber" value="0">
									<bean:message key="screen.dwoNo" />  &nbsp;
	           <bean:write name="clauseRev" property="dwONumber" />
									<BR>
								</logic:greaterThan> <logic:greaterThan name="clauseRev"
									property="partNumber" value="0">
									<bean:message key="screen.partNo" /> &nbsp;
	            <bean:write name="clauseRev" property="partNumber" />
									<BR>
								</logic:greaterThan> <logic:greaterThan name="clauseRev"
									property="priceBookNumber" value="0">
									<bean:message key="screen.priceBookNo" /> &nbsp;
	            <bean:write name="clauseRev" property="priceBookNumber" />
									<BR>
								</logic:greaterThan> <logic:present name="clauseRev"
									property="standardEquipmentDesc">
									<bean:write name="clauseRev" property="standardEquipmentDesc" />
									<BR>
								</logic:present> <logic:present name="clauseRev"
									property="comments">
									<bean:write name="clauseRev" property="comments" />
									<BR>
								</logic:present></DIV>
								</span></TD>

								<TD class="borderbottomrightlight"><bean:write name="clauseRev"
									property="defaultFlag" /> <html:hidden name="clauseRev"
									property="defaultFlag" /> <!-- <logic:equal name="clauseRev" property="defaultFlag" value="Y">
                <input type="radio" name="defaultFlag" value="Y" checked class="radcheck"/>
	            </logic:equal>
	            <logic:equal name="clauseRev" property="defaultFlag" value="N">
                 <input type="radio" name="defaultFlag" value="Y"  class="radcheck"/>
	            </logic:equal> --></TD>

							</TR>
						</logic:iterate>

					</TABLE>
				</logic:present>
			</logic:greaterThan>
			<br>
			<TABLE ALIGN=center>
				<TR>
					<TD>&nbsp;</TD>
					<TD COLSPAN=4 ALIGN=center><INPUT CLASS="buttonStyle" TYPE="button"
						name="Apply" value="OK" onclick="javascript:fnClauseVersion()"></TD>

					<TD COLSPAN=4 ALIGN=center><INPUT CLASS="buttonStyle" TYPE="button"
						name="Cancel" value="Cancel" onclick="fnCancel()" id="btnCancel"></TD>
				</TR>
			</TABLE>
		</TBODY>
		<html:hidden property="hdnClauseSeqNo" />
		<html:hidden property="hdnVersionNo" />
		<html:hidden property="hdnClauseNum" />
		<html:hidden property="hdnModelSeqNo" />
		<html:hidden property="hdnSectionSeqNo" />
		<html:hidden property="hdnSubSectionSeqNo" />
		<html:hidden property="chngToFlag"/>
		</html:form>
</BODY>
</HTML>
