<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<HEAD>
<SCRIPT LANGUAGE="JavaScript" SRC="js/ModifyChangeRequestForm.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/date-picker.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/Emd_Lsdb.js"></SCRIPT>

<script>
function fnGetRequest(requestId)
{
	//var s =eval(requestId);
	
	document.forms[0].action="searchRequestComponentGroup.do?method=fetchComponentGroupDetails&reqID="+requestId;
	document.forms[0].submit();
	
}

</script>



</HEAD>

<html:form action="/ModifyChangeAction" method="post">





	<logic:present name="ModifyChangeRequestForm" property="requestList">
		<bean:size id="reqSize" name="ModifyChangeRequestForm"
			property="requestList" />
	</logic:present>



	<TABLE WIDTH="60%" ALIGN=center>
		<TR>
			<TD>&nbsp;</TD>
		</TR>
		<TR>
			<TD WIDTH="100%" CLASS="dashBoardSubHeading" ALIGN="center">Request
			Form - Modify Change Request Form</TD>
		</TR>
		<TR>
			<TD>&nbsp;</TD>
		</TR>
		<TR>
			<TD class="navycolor" ALIGN="center">NOTE - Please select at least
			one item for Search Criteria</TD>
		</TR>
		<TR>
			<TD class="navycolor" ALIGN="center">NOTE - From Date, To Date are
			the action dates performed by Administrator/Master Spec Engineer</TD>
		</TR>
	</TABLE>
	&nbsp;
	<!-- To display  Messages - Start -->
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
	<!-- To display Messages - End -->

	<!-- To Display No record found -->
	<logic:match name="ModifyChangeRequestForm" property="method"
		value="REQUEST">
		<logic:lessThan name="reqSize" value="1">
			<script>
						var id='16';
						hideErrors();
						addMsgNum(id);
						showErrors();
					</script>
		</logic:lessThan>
	</logic:match>
	<!-- To Display No record found -->
	
	<!-- Added For CR_80 CR Form Enhancements III by RR68151 -->
	<logic:present name="ModifyChangeRequestForm" property="messageID"
		scope="request">
		<%--Added for CR-121 for server side error message tooltip --%>	
	  <bean:define id="messageID" name="ModifyChangeRequestForm" property="messageID"/>
	  <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
      <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
	</logic:present>
	<!-- Added For CR_80 CR Form Enhancements III by RR68151 - Ends here -->

	<TABLE WIDTH="20%" BORDER="0" CELLPADDING="1" CELLSPACING="1"
		ALIGN="center">
		<TR>
			<TD>&nbsp;</TD>
		</TR>
	</TABLE>
	<!-- Validation Message For No Records Found - Start -->


	<!-- Validation Message For No Records Found - End -->

	<TABLE WIDTH="50%" BORDER="0" ALIGN="center">
		<TR>


			<TD WIDTH="10%" CLASS='headerbgcolor' ALIGN="left" nowrap>Status</TD>
			<TD WIDTH="5%" ALIGN="left" CLASS='navycolor1'><html:select
				name="ModifyChangeRequestForm" property="statusSeqNo"
				styleClass="selectstyle2">

				<option selected value="-1">---Select---</option>
				<logic:notEmpty name="ModifyChangeRequestForm" property="statusList">
					<logic:present name="ModifyChangeRequestForm" property="statusList">

						<html:optionsCollection name="ModifyChangeRequestForm"
							property="statusList" value="statusTypeSeqNo" label="statusDesc" />

					</logic:present>
				</logic:notEmpty>
			</html:select></TD>



			<TD WIDTH="2%"></TD>
			<td CLASS='headerbgcolor' WIDTH="13%" nowrap>Last Name&nbsp;<font
				size=2 color="red"></font></TD>
			<td width='5%'><html:select name="ModifyChangeRequestForm"
				property="lastName" styleClass="selectstyle2">

				<option  value="-1">---Select---</option>
				<logic:equal name="ModifyChangeRequestForm" property="roleID" value="ADMN">
					<option value="All" selected>All</option>
				</logic:equal>

				<logic:notEqual name="ModifyChangeRequestForm" property="roleID"
					value="ADMN">
					<option value="All">All</option>
				</logic:notEqual>	
				<logic:notEmpty name="ModifyChangeRequestForm" property="lastList">
					<logic:present name="ModifyChangeRequestForm" property="lastList">
						<html:optionsCollection name="ModifyChangeRequestForm"
							property="lastList" value="lasttName" label="lasttName" />
					</logic:present>
				</logic:notEmpty>
			</html:select></td>

			<TD CLASS='headerbgcolor' WIDTH="13%" nowrap>Request ID&nbsp;<font
				size=2 color="red"></font></TD>

			<TD WIDTH="5%"><html:text property="requestId" maxlength="5" /></TD>



			<TD WIDTH="1%"></TD>
			<!-- Added For CR_80 CR Form Enhancements III by RR68151 -->
			<logic:empty name="ModifyChangeRequestForm" property="fromDate">
				<TD CLASS='headerbgcolor' WIDTH="10%" nowrap valign=center>From
				Date&nbsp;<font size=2 color="red"></font></TD>
				<TD width="18%"><INPUT TYPE="text" name="fromDate" CLASS="TEXTBOX2"
					WIDTH="5%" readonly /><A
					href="javascript:show_calendar('forms[0].fromDate');"
					onmouseover="window.status='Date Picker';return true;"
					onmouseout="window.status='';return true;" tabindex="-1"><img
					src="images/calendaricon.gif" width="22" height="16" border="0"></A></TD>
				<TD><A HREF="javascript:deleteFromCalender()"><IMG
					SRC="images/delete1.gif" alt="Clear" BORDER="0"></A></TD>
			</logic:empty>
			
			<logic:notEmpty name="ModifyChangeRequestForm" property="fromDate">
				<TD CLASS='headerbgcolor' WIDTH="10%" nowrap valign=center>From
				Date&nbsp;<font size=2 color="red"></font></TD>
				<TD width="18%"><INPUT TYPE="text" name="fromDate" CLASS="TEXTBOX2"
					WIDTH="5%" readonly value="<bean:write name="ModifyChangeRequestForm" property="fromDate"/>"/><A
					href="javascript:show_calendar('forms[0].fromDate');"
					onmouseover="window.status='Date Picker';return true;"
					onmouseout="window.status='';return true;" tabindex="-1"><img
					src="images/calendaricon.gif" width="22" height="16" border="0"></A></TD>
				<TD><A HREF="javascript:deleteFromCalender()"><IMG
					SRC="images/delete1.gif" alt="Clear" BORDER="0"></A></TD>
			</logic:notEmpty>
					
			<TD WIDTH="1%"></TD>
			<logic:empty name="ModifyChangeRequestForm" property="toDate">
				<TD CLASS='headerbgcolor' WIDTH="10%" nowrap>To Date&nbsp;<font
					size=2 color="red"></font></TD>
				<TD width="42%"><INPUT TYPE="text" name="toDate" CLASS="TEXTBOX2"
					WIDTH="5%" readonly /><A
					href="javascript:show_calendar('forms[0].toDate');"
					onmouseover="window.status='Date Picker';return true;"
					onmouseout="window.status='';return true;" tabindex="-1"><img
					src="images/calendaricon.gif" width="22" height="16" border="0"></A></TD>
				<TD><A HREF="javascript:deleteToCalender()"><IMG
					SRC="images/delete1.gif" alt="Clear" BORDER="0"></A></TD>
			</logic:empty>

			<logic:notEmpty name="ModifyChangeRequestForm" property="toDate">
				<TD CLASS='headerbgcolor' WIDTH="10%" nowrap>To Date&nbsp;<font
					size=2 color="red"></font></TD>
				<TD width="42%"><INPUT TYPE="text" name="toDate" CLASS="TEXTBOX2"
					WIDTH="5%" readonly value="<bean:write name="ModifyChangeRequestForm" property="toDate"/>"/><A
					href="javascript:show_calendar('forms[0].toDate');"
					onmouseover="window.status='Date Picker';return true;"
					onmouseout="window.status='';return true;" tabindex="-1"><img
					src="images/calendaricon.gif" width="22" height="16" border="0"></A></TD>
				<TD><A HREF="javascript:deleteToCalender()"><IMG
					SRC="images/delete1.gif" alt="Clear" BORDER="0"></A></TD>
			</logic:notEmpty>
			<TD WIDTH="18%"></TD>


		</TR>

		<TABLE WIDTH="60%" ALIGN="center" BORDER="0">
			<TR>
				<TD>&nbsp;</TD>
			</TR>
			<TR>
				<TD ALIGN="center"><html:button property="method"
					styleClass="buttonStyle" value="Search"
					onclick="javascript:fnSearch()">

				</html:button> <html:hidden property="hdnsearch" /> <html:hidden
					property="hdStatus" /> <html:hidden property="hdnLastName" /> <html:hidden
					property="hdnRequestId" /> <html:hidden property="hdnFromDate" />
				<html:hidden property="hdnToDate" /> <html:hidden property="hdnID" />
				</TD>
			</TR>
		</TABLE>


		<logic:greaterThan name="reqSize" value="0">
			<HR>
			<%-- Modified width to accomodate Admin Comments for CR_108 --%>
			<TABLE ALIGN=center WIDTH="97%" BORDER=0 bgcolor=#D2DDF9>
				<TR>
					<TD width="85%" ALIGN=center CLASS=greytext1>Search Criteria</TD>
				</TR>
				<TR>
					<TD>
					<Table width="85%" border="0" bgcolor=#D2DDF9 align="center">
						<TR>
							<td width="20%">&nbsp;</td>
							<TD width="20%" ALIGN="left"><span CLASS=greytext1>Status </span></TD>
							<td width="2%" ALIGN="center"><span CLASS=greytext1>:</span></td>
							<td width="30%" ALIGN="left"><bean:write
								name="ModifyChangeRequestForm" property="hdStatus" /></TD>
						</TR>
						<TR>
							<td width="20%">&nbsp;</td>
							<TD width="15%" ALIGN=left><span CLASS=greytext1>Last Name</span></td>

							<td width="3%" align="center"><span CLASS=greytext1> : </span></td>
							<logic:notEqual name="ModifyChangeRequestForm"
								property="hdnLastName" value="-1">
								<td width="50%" align="left"><bean:write
									name="ModifyChangeRequestForm" property="hdnLastName" /></TD>
							</logic:notEqual>


							<logic:equal name="ModifyChangeRequestForm"
								property="hdnLastName" value="-1">
								<td width="50%" align="left">&nbsp;</TD>
							</logic:equal>
						</TR>
						<tr>
							<td width="20%">&nbsp;</td>
							<TD width="10%" ALIGN=left><span CLASS=greytext1>Request ID</span></td>

							<td width="3%" align="center"><span CLASS=greytext1> : </span></td>
							<logic:greaterThan name="ModifyChangeRequestForm"
								property="hdnRequestId" value="0">
								<td width="50%" align="left"><bean:write
									name="ModifyChangeRequestForm" property="hdnRequestId" /></TD>
							</logic:greaterThan>
						</TR>
						<tr>
							<td width="20%">&nbsp;</td>
							<TD width="10%" ALIGN=left><span CLASS=greytext1>From Date</span></td>

							<td width="3%" align="center"><span CLASS=greytext1> : </span></td>
							<td width="50%" align="left"><bean:write
								name="ModifyChangeRequestForm" property="hdnFromDate" /></TD>
						</TR>
						<tr>
							<td width="20%">&nbsp;</td>
							<TD width="10%" ALIGN=left><span CLASS=greytext1>To Date</span></td>

							<td width="3%" align="center"><span CLASS=greytext1> : </span></td>
							<td width="50%" align="left"><bean:write
								name="ModifyChangeRequestForm" property="hdnToDate" /></TD>
						</TR>
					</Table>
					</TD>
				</TR>
			</TABLE>


			<TABLE width="97%" border=0 align=center>
				<TR>
					<TD><a CLASS=linkstyleItem href="#" onclick="javascript:checkAll()">Check
					All</a>&nbsp;&nbsp; <a CLASS=linkstyleItem href="#"
						onclick="javascript:clearAll()">Clear All</a></TD>
				</TR>
			</TABLE>
			<TABLE WIDTH="97%" ALIGN="center" BORDER="1" BORDERCOLOR="#5780ae">
				<TR>
					<TH width="10%" CLASS='table_heading'>Select</TH>
					<TH width="10%" CLASS='table_heading' nowrap>Request ID</TH>
					<TH width="10%" CLASS='table_heading' nowrap>Action By</TH>
					<TH width="20%" CLASS='table_heading' nowrap>Short Description</TH>
					<TH width="10%" CLASS='table_heading' nowrap>Status</TH>
					<TH width="25%" CLASS='table_heading' nowrap>Admin Comments</TH>
					<TH width="10%" CLASS='table_heading'>Action Date</TH>
					<TH width="20%" CLASS='table_heading'>Re-assign To</TH>
				</TR>

				<logic:iterate id="requestVO" name="ModifyChangeRequestForm"
					property="requestList" type="com.EMD.LSDB.vo.common.RequestVO">
					<bean:define id="requestId" name="requestVO" property="reqID" />
					<TR>

						<TD CLASS='borderbottomleft1'><input type="checkbox" name="reqCnt"
							value='<%=String.valueOf(requestVO.getReqID())%>' />
						</TD>
						
						<%-- Modified for CR_101 for Completed CR Hyperlinks --%>
						<TD CLASS='borderbottomleft1'>
						<logic:equal name="requestVO" property="statusTypeSeqNo" value="6">
						<A HREF="javascript:fnCompletedReqPDF('<%=String.valueOf(requestId)%>')"
							CLASS='linkstyle2' style="color:green;" > <bean:write name="requestVO" property="reqID" /></A>
							<%-- CR_101 Ends here --%>
						</logic:equal>
						
                        
						 <logic:notEqual name="requestVO" property="statusTypeSeqNo" value="6">
                          <A HREF="javascript:fnGetRequest('<%=String.valueOf(requestId)%>')"
							CLASS='linkstyle2' > <bean:write name="requestVO" property="reqID" /></A>

						 </logic:notEqual>

						</TD>

						<TD CLASS='borderbottomleft1'><logic:notEmpty name="requestVO"
							property="reqByUserLN">
							<bean:write name="requestVO" property="reqByUserLN" />
						</logic:notEmpty> <logic:empty name="requestVO"
							property="reqByUserLN">
			&nbsp;
			</logic:empty></TD>
						<TD CLASS='borderbottomleft1'><bean:write name="requestVO"
							property="requestDesc" /></TD>
						<TD CLASS='borderbottomleft1'><bean:write name="requestVO"
							property="statusTypeDesc" /></TD>
							<%-- Added For CR_108  --%>
							
						<TD WIDTH="25%" CLASS='borderbottomleft1'><logic:notEmpty
							name="requestVO" property="adminComments">
							<bean:write name="requestVO" property="adminComments" />
						</logic:notEmpty> <logic:empty name="requestVO"
							property="adminComments">
			&nbsp;
			</logic:empty></TD>

						<TD WIDTH="10%" CLASS='borderbottomleft1'><logic:notEmpty
							name="requestVO" property="masterSpecChangedDate">
							<bean:write name="requestVO" property="masterSpecChangedDate" />
						</logic:notEmpty> <logic:empty name="requestVO"
							property="masterSpecChangedDate">
			&nbsp;
			</logic:empty></TD>
						<!-- Added for CR_80 CR Form Enhancements III on 26-Nov-09 by RR68151 -->
						<TD WIDTH="20%" CLASS='borderbottomleft1'>
						<html:select name="ModifyChangeRequestForm"
							property="assigneeId" styleClass="selectstyle2">	
							<option selected value="-1">---Select---</option>
								<logic:present name="ModifyChangeRequestForm" property="lastList">
									<html:optionsCollection name="ModifyChangeRequestForm"
										property="lastList" value="userrId" label="lasttName" />
								</logic:present>
						</html:select></TD>
					</TR>
				</logic:iterate>
			</Table>

			<TABLE BORDER="0" WIDTH="30%" ALIGN="CENTER">
				<TR>
					<TD>&nbsp;</TD>
					<TD>&nbsp;</TD>
				</TR>
				<TR>

					<TD ALIGN="center"><INPUT CLASS="buttonStyle" TYPE="button"
						name="btnAdd" value="Create PDF"
						onclick="javascript:fnCreatePDF()"></TD>
					<!-- Added for CR_80 CR Form Enhancements III on 26-Nov-09 by RR68151 -->
					<TD ALIGN="center"><html:button property="method"
					styleClass="buttonStyle" value="Re-assign CR Form"
					onclick="javascript:fnReassignCR()"/></TD>
					<!-- Added for CR_80 CR Form Enhancements III on 26-Nov-09 by RR68151 - Ends here -->
				</TR>
			</TABLE>

		</logic:greaterThan>
    <!-- Added for CR_80 CR Form Enhancements III on 26-Nov-09 by RR68151 -->
	<html:hidden property="hdnAssigneeId"/>
		</html:form>
		<TABLE BORDER="0" WIDTH="30%" ALIGN="CENTER">
				<TR>
					<TD>&nbsp;</TD>
					<TD>&nbsp;</TD>
				</TR>
		</TABLE>