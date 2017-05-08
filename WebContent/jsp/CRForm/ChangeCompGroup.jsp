<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<HEAD>
<SCRIPT LANGUAGE="JavaScript" SRC="js/ComponentGroupChangeRequest.js"></SCRIPT>
<script>
function fnGetRequest(requestId)
{
	document.forms[0].action="searchRequestComponentGroup.do?method=fetchComponentGroupDetails&reqID="+requestId;
	document.forms[0].submit();
}
function fnShowReqId()
{
$("#hiddenSearchList").modal({
	minHeight:100,
	minWidth:400,
	escClose:true,
onOpen: function (dialog) {
		dialog.overlay.fadeIn('fast', function () {
		dialog.data.hide();
		dialog.container.fadeIn('fast', function () {
		dialog.data.slideDown('fast');
		});
	});
}}); 
}
function fnReturnToModifyCRForm()
{
	document.forms[0].action="ModifyChangeAction.do?method=initLoad&screenid=32";
	document.forms[0].submit();
}
</script>
</HEAD>

<html:form action="/ComponentGroupRequest" method="post">

	<TABLE WIDTH="60%" BORDER="0" CELLPADDING="0" CELLSPACING="0"
		align=center>
		<TR>
			<TD>&nbsp;</TD>
		</TR>
		<TR>
			<TD WIDTH="60%" CLASS="dashBoardSubHeading" ALIGN="center"><bean:message
				key="Application.Screen.CreateChangeRequest" /></TD>
		</TR>

		<TR>
			<TD>&nbsp;</TD>
		</TR>

	</TABLE>

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
	<br>
	<logic:present name="ComponentGroupRequestForm" property="messageID"
		scope="request">
	  <%--Added for CR-121 for server side error message tooltip --%>	
	  <bean:define id="messageID" name="ComponentGroupRequestForm" property="messageID"/>
	  <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
      <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
	</logic:present>
	<!-- Added for CR_94 -->
	<logic:present name="ComponentGroupRequestForm" property="searchList">
		<bean:size id="searchSize" name="ComponentGroupRequestForm" property="searchList" />
	</logic:present>
	<!-- CR_94 Ends here -->

	<logic:notEmpty name="ComponentGroupRequestForm" property="tableID"
		scope="request">
		<bean:define id="compmov" name="ComponentGroupRequestForm"
			property="tableID" />
		<logic:equal name="ComponentGroupRequestForm" property="tableID"
			value="Component">
				<script>
					document.location.href="#Component";
				</script>
		</logic:equal>
	</logic:notEmpty>



	<logic:iterate id="objRequest" name="ComponentGroupRequestForm"
		property="requestDetails" type="com.EMD.LSDB.vo.common.RequestVO"
		scope="request">
		<html:hidden property="hdnReqID" value="<%=objRequest.getReqID()%>" />
		<TABLE ALIGN=center WIDTH="50%" BORDER=0 bgcolor=E6E7E8>

			<TR>
				<TD width=25% ALIGN=left CLASS="greytext"><span CLASS=header nowrap>Request
				Id :</span><%=objRequest.getReqID()%></TD>
				<TD width=25% ALIGN=center CLASS="greytext"><span id="crStatus" CLASS=header
					nowrap>Status :</span> <bean:define id="color" value="#666666" />
				<logic:equal name="objRequest" property="statusTypeSeqNo" value="3">
					<bean:define id="color" value="yellow" />
				</logic:equal> <logic:equal name="objRequest"
					property="statusTypeSeqNo" value="4">
					<bean:define id="color" value="green" />
				</logic:equal> <logic:equal name="objRequest"
					property="statusTypeSeqNo" value="5">
					<bean:define id="color" value="red" />
				</logic:equal> <font color="<%=color%>"><%=objRequest.getStatusTypeDesc()%></font>
				</TD>
			</TR>
		</TABLE>
		<bean:define id="requestID" name="objRequest" property="reqID"/>
		<bean:define id="statusNo" name="objRequest" property="statusTypeSeqNo"/>
	</logic:iterate>
	<BR>

	<TABLE width=50% ALIGN=center BORDER=0>
		<TR>
			<TD width=25% CLASS="headerbgcolor" ALIGN="left">Short Description<font
				color=red size=2>*</font></TD>
			<TD width=25% CLASS="formFieldSub" ALIGN="left"><html:textarea
				name="ComponentGroupRequestForm" property="requestDesc" rows="3"
				cols="60"></html:textarea></TD>
		</TR>
	</TABLE>
	<TABLE WIDTH="60%" BORDER=0 align="center">
		<TR>
			<TD>&nbsp;</TD>
		</TR>
		<TR>
			<TD WIDTH="100%" ALIGN=center><A HREF
				CLASS=linkstyleItemNoUnderlineNine>Component Group & Component</A>&nbsp;&nbsp;|&nbsp;&nbsp;
			<logic:iterate id="objRequest" name="ComponentGroupRequestForm"
				property="requestDetails" type="com.EMD.LSDB.vo.common.RequestVO"
				scope="request">
				<logic:present name="ComponentGroupRequestForm"
					property="clauseColorFlag">
					<logic:equal name="ComponentGroupRequestForm"
						property="clauseColorFlag" value="Y">
						<!-- Modified for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151 -->
						<A HREF="#" 
							onclick="javascript:clauseView('<%=objRequest.getReqID()%>')"
							CLASS=linkstyleItemGreenNine>Clause</A>
						<!-- <A HREF='CreateChangeReqClauseAction.do?method=initLoad&reqID=<%=objRequest.getReqID()%>'  CLASS=linkstyleItemGreen>Clause</A> -->&nbsp;&nbsp;</logic:equal>
						<logic:notEqual name="ComponentGroupRequestForm"
						property="clauseColorFlag" value="Y">
							<A HREF="#" 
								onclick="javascript:clauseView('<%=objRequest.getReqID()%>')"
								CLASS=linkstyleItemNine>Clause</A>
						<!-- <A HREF='CreateChangeReqClauseAction.do?method=initLoad&reqID=<%=objRequest.getReqID()%>' CLASS=linkstyleItem>Clause</A> -->&nbsp;&nbsp;</logic:notEqual>
				</logic:present>
			</logic:iterate></TD>
		</TR>
		<TR>
			<TD>&nbsp;</TD>
		</TR>
	</TABLE>

	<logic:equal name="ComponentGroupRequestForm" property="reqModelSaved"
		value="Y">

		<TABLE ALIGN=center WIDTH="96%" CLASS=crFormTable1>

			<TR>
				<TD CLASS="selectstyle2" align=center>The Pulldown options on this
				selection tool bar can be unlocked for change by clicking on the "<FONT
					COLOR="red">Reset</FONT>" button at the bottom of the screen.</TD>
			</TR>
		</TABLE>
	</logic:equal>
	<TABLE>
		<TR>
			<TD>&nbsp;</TD>
		</TR>
	</TABLE>

	<TABLE ALIGN=center WIDTH="98%" BORDER=0 bgcolor=#D2DDF9>
		<TR>
			<!-- Added For CR_80 Specification Drop down -->
			<TD ALIGN=left class="header"><span CLASS=greytext1 nowrap> Specification Type <font
				color=red size=2>*</font>:</span> <html:select
				styleClass="selectstyle2" name="ComponentGroupRequestForm"
				property="specTypeNo" onchange="javascript:fnLoadModels()">
				<option selected value="-1">---Select---</option>
				<logic:present name="ComponentGroupRequestForm" property="specTypeList">
					<html:optionsCollection name="ComponentGroupRequestForm"
						property="specTypeList" value="spectypeSeqno" label="spectypename" />
				</logic:present>
			</html:select></TD>
			<!-- Added For CR_80 Specification Drop down - Ends here-->
			
			<TD ALIGN=left class="header"><span CLASS=greytext1 nowrap> Model <font
				color=red size=2>*</font>:</span> <html:select
				styleClass="selectstyle2" name="ComponentGroupRequestForm"
				property="modelSeqNo" onchange="javacript:loadSection()">
				<option selected value="-1">---Select---</option>
				<logic:present name="ComponentGroupRequestForm" property="modelList">
					<html:optionsCollection name="ComponentGroupRequestForm"
						property="modelList" label="modelName" value="modelSeqNo" />
				</logic:present>
			</html:select></TD>

			<TD ALIGN=left class="header"><span CLASS=greytext1 nowrap>Section <font
				color=red size=2>*</font>:</span> <html:select
				name="ComponentGroupRequestForm" property="sectionSeqNo"
				styleClass="selectstyle2" onchange="javacript:loadSubSection()">
				<option selected value="-1">---Select---</option>
				<logic:present name="ComponentGroupRequestForm"
					property="listSections">
					<html:optionsCollection name="ComponentGroupRequestForm"
						property="listSections" value="sectionSeqNo"
						label="sectionDisplay" />
				</logic:present>
			</html:select></TD>

			<TD ALIGN=center><span CLASS=greytext1 nowrap>SubSection <font
				color=red size=2>*</font>:</span> <html:select
				name="ComponentGroupRequestForm" property="subSectionSeqNo"
				styleClass="selectstyle2"
				onchange="javacript:loadAvailableCompGroups()">
				<option selected value="-1">---Select---</option>
				<logic:present name="ComponentGroupRequestForm"
					property="subSectionList">
					<html:optionsCollection name="ComponentGroupRequestForm"
						property="subSectionList" value="subSecSeqNo"
						label="subSecDisplay" />
				</logic:present>
			</html:select></TD>
		</TR>
	</TABLE>
	<BR>
	<TABLE BORDER="0" WIDTH="96%" ALIGN="center">
		<TR>
			<TD align=left width="30%">
			<TABLE WIDTH="100%" class=crFormTable>
				<TR>
					<TD><span CLASS=greytext1 nowrap> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:checkbox
						name="ComponentGroupRequestForm" property="applyModelFlag" />This
					change effects both ACe/M-2 Models </TD>
				</TR>
			</TABLE>
			</TD>
			<TD align=left width="7%">&nbsp;</td>

			<TD CLASS="dashBoardSubHeading" ALIGN="left"><bean:message
				key="Application.Screen.ComponentGroup" /></TD>
		</TR>

	</TABLE>

	<Table align=center>
		<tr>
			<TD ALIGN=center><INPUT CLASS="buttonStyle" TYPE="button"
				name="btnAdd" value="Refresh/Restore"
				onclick="javascript:fnRefresh()"></TD>
		</tr>
	</TABLE>

	<BR>

	<TABLE ALIGN="CENTER" WIDTH="50%" BORDER="0" CELLPADDING="0"
		CELLSPACING="0">
		<TR>
			<!-- <TD WIDTH="4%">&nbsp;</TD>  -->

			<logic:equal name="ComponentGroupRequestForm"
				property="compGroupChangeTypeSeqNo" value="1">
				<!-- Added for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 -->
				<TD WIDTH="11%" CLASS=header>Not Required : <input type="radio"
					name="compGroupChangeTypeSeqNo" Class="RADCHECK" value="7"
					onclick="javascript:EnableDisableCompGroup()"></TD>
				<!-- Added for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 - Ends here -->	
				<TD WIDTH="7%" CLASS=header>New : <input type="radio"
					name="compGroupChangeTypeSeqNo" Class="RADCHECK" value="1"
					onclick="javascript:EnableDisableCompGroup()" checked>&nbsp;</TD>
				<TD WIDTH="8%" CLASS=header>Existing : <input type="radio"
					name="compGroupChangeTypeSeqNo" CLASS="RADCHECK" value="2"
					onclick="javascript:EnableDisableCompGroup()">&nbsp;</TD>

				<TD WIDTH="8%" CLASS=header>Revise : <input type="radio"
					name="compGroupChangeTypeSeqNo" CLASS="RADCHECK" value="3"
					onclick="javascript:EnableDisableCompGroup()"></TD>
				<!-- Added for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 -->
				<TD WIDTH="8%" CLASS=header>Delete : <input type="radio"
					name="compGroupChangeTypeSeqNo" Class="RADCHECK" value="8"
					onclick="javascript:EnableDisableCompGroup()"></TD>
				<!-- Added for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 - Ends here -->	
				
			</logic:equal>

			<logic:equal name="ComponentGroupRequestForm"
				property="compGroupChangeTypeSeqNo" value="2">
				<!-- Added for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 -->
				<TD WIDTH="11%" CLASS=header>Not Required : <input type="radio"
					name="compGroupChangeTypeSeqNo" Class="RADCHECK" value="7"
					onclick="javascript:EnableDisableCompGroup()"></TD>
				<!-- Added for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 - Ends here -->	
				<TD WIDTH="7%" CLASS=header>New : <input type="radio"
					name="compGroupChangeTypeSeqNo" Class="RADCHECK" value="1"
					onclick="javascript:EnableDisableCompGroup()">&nbsp;</TD>
				<TD WIDTH="8%" CLASS=header>Existing : <input type="radio"
					name="compGroupChangeTypeSeqNo" CLASS="RADCHECK" value="2"
					onclick="javascript:EnableDisableCompGroup()" checked>&nbsp;</TD>

				<TD WIDTH="8%" CLASS=header>Revise : <input type="radio"
					name="compGroupChangeTypeSeqNo" CLASS="RADCHECK" value="3"
					onclick="javascript:EnableDisableCompGroup()"></TD>
				<!-- Added for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 -->
				<TD WIDTH="8%" CLASS=header>Delete : <input type="radio"
					name="compGroupChangeTypeSeqNo" Class="RADCHECK" value="8"
					onclick="javascript:EnableDisableCompGroup()"></TD>
				<!-- Added for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 - Ends here -->	
			</logic:equal>

			<logic:equal name="ComponentGroupRequestForm"
				property="compGroupChangeTypeSeqNo" value="3">
				<!-- Added for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 -->
				<TD WIDTH="11%" CLASS=header>Not Required : <input type="radio"
					name="compGroupChangeTypeSeqNo" Class="RADCHECK" value="7"
					onclick="javascript:EnableDisableCompGroup()"></TD>
				<!-- Added for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 - Ends here -->	
				<TD WIDTH="7%" CLASS=header>New : <input type="radio"
					name="compGroupChangeTypeSeqNo" Class="RADCHECK" value="1"
					onclick="javascript:EnableDisableCompGroup()">&nbsp;</TD>
				<TD WIDTH="8%" CLASS=header>Existing : <input type="radio"
					name="compGroupChangeTypeSeqNo" CLASS="RADCHECK" value="2"
					onclick="javascript:EnableDisableCompGroup()">&nbsp;</TD>

				<TD WIDTH="8%" CLASS=header>Revise : <input type="radio"
					name="compGroupChangeTypeSeqNo" CLASS="RADCHECK" value="3"
					onclick="javascript:EnableDisableCompGroup()" checked></TD>
				<!-- Added for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 -->
				<TD WIDTH="8%" CLASS=header>Delete : <input type="radio"
					name="compGroupChangeTypeSeqNo" Class="RADCHECK" value="8"
					onclick="javascript:EnableDisableCompGroup()"></TD>
				<!-- Added for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 - Ends here -->	
			</logic:equal>

			<logic:equal name="ComponentGroupRequestForm"
				property="compGroupChangeTypeSeqNo" value="7">
				<TD WIDTH="11%" CLASS=header>Not Required : <input type="radio"
					name="compGroupChangeTypeSeqNo" Class="RADCHECK" value="7"
					onclick="javascript:EnableDisableCompGroup()" checked></TD>
				<!-- Added for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 - Ends here -->	
				<TD WIDTH="7%" CLASS=header>New : <input type="radio"
					name="compGroupChangeTypeSeqNo" Class="RADCHECK" value="1"
					onclick="javascript:EnableDisableCompGroup()">&nbsp;</TD>
				<TD WIDTH="8%" CLASS=header>Existing : <input type="radio"
					name="compGroupChangeTypeSeqNo" CLASS="RADCHECK" value="2"
					onclick="javascript:EnableDisableCompGroup()">&nbsp;</TD>
				<TD WIDTH="8%" CLASS=header>Revise : <input type="radio"
					name="compGroupChangeTypeSeqNo" CLASS="RADCHECK" value="3"
					onclick="javascript:EnableDisableCompGroup()"></TD>
				<TD WIDTH="8%" CLASS=header>Delete : <input type="radio"
					name="compGroupChangeTypeSeqNo" Class="RADCHECK" value="8"
					onclick="javascript:EnableDisableCompGroup()"></TD>				
			</logic:equal>
			
			<logic:equal name="ComponentGroupRequestForm"
				property="compGroupChangeTypeSeqNo" value="8">
				<TD WIDTH="11%" CLASS=header>Not Required : <input type="radio"
					name="compGroupChangeTypeSeqNo" Class="RADCHECK" value="7"
					onclick="javascript:EnableDisableCompGroup()"></TD>
				<!-- Added for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 - Ends here -->	
				<TD WIDTH="7%" CLASS=header>New : <input type="radio"
					name="compGroupChangeTypeSeqNo" Class="RADCHECK" value="1"
					onclick="javascript:EnableDisableCompGroup()">&nbsp;</TD>
				<TD WIDTH="8%" CLASS=header>Existing : <input type="radio"
					name="compGroupChangeTypeSeqNo" CLASS="RADCHECK" value="2"
					onclick="javascript:EnableDisableCompGroup()">&nbsp;</TD>
				<TD WIDTH="8%" CLASS=header>Revise : <input type="radio"
					name="compGroupChangeTypeSeqNo" CLASS="RADCHECK" value="3"
					onclick="javascript:EnableDisableCompGroup()"></TD>
				<TD WIDTH="8%" CLASS=header>Delete : <input type="radio"
					name="compGroupChangeTypeSeqNo" Class="RADCHECK" value="8"
					onclick="javascript:EnableDisableCompGroup()" checked></TD>				
			</logic:equal>
			
		</TR>
		<TR>
			<TD COLSPAN=5>&nbsp;</TD>
		</TR>
	</TABLE>
	<TABLE ALIGN="CENTER" WIDTH="30%" BORDER="0" CELLPADDING="0"
		CELLSPACING="0">
		<TR>
			<TD CLASS="header" nowrap>Available Component Group(s)</TD>
			<TD align=left>&nbsp; <logic:equal name="ComponentGroupRequestForm"
				property="compGroupChangeTypeSeqNo" value="1">
				<html:select name="ComponentGroupRequestForm"
					property="compGrpSeqNo" styleClass="selectstyle2"
					onchange="javascript:loadAvailableComponents()" disabled="true">
					<option selected value="-1">---Select---</option>
					<logic:present name="ComponentGroupRequestForm"
						property="compGrpList">
						<html:optionsCollection name="ComponentGroupRequestForm"
							property="compGrpList" value="componentGroupSeqNo"
							label="componentGroupName" />
					</logic:present>
				</html:select>
			</logic:equal> <logic:notEqual name="ComponentGroupRequestForm"
				property="compGroupChangeTypeSeqNo" value="1">
				<!-- Modified for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151 -->
				<logic:equal name="ComponentGroupRequestForm"
				property="compGroupChangeTypeSeqNo" value="7">
				<html:select name="ComponentGroupRequestForm"
					property="compGrpSeqNo" styleClass="selectstyle2"
					onchange="javascript:loadAvailableComponents()" disabled="true">
					<option selected value="-1">---Select---</option>
					<logic:present name="ComponentGroupRequestForm"
						property="compGrpList">
						<html:optionsCollection name="ComponentGroupRequestForm"
							property="compGrpList" value="componentGroupSeqNo"
							label="componentGroupName" />
					</logic:present>
				</html:select>
			</logic:equal>
			<logic:notEqual name="ComponentGroupRequestForm"
				property="compGroupChangeTypeSeqNo" value="7">
				<html:select name="ComponentGroupRequestForm"
					property="compGrpSeqNo" styleClass="selectstyle2"
					onchange="javascript:loadAvailableComponents()">
					<option selected value="-1">---Select---</option>
					<logic:present name="ComponentGroupRequestForm"
						property="compGrpList">
						<html:optionsCollection name="ComponentGroupRequestForm"
							property="compGrpList" value="componentGroupSeqNo"
							label="componentGroupName" />
					</logic:present>
				</html:select>
			</logic:notEqual>
			</logic:notEqual></TD>
			<logic:equal name="ComponentGroupRequestForm"
				property="compGroupChangeTypeSeqNo" value="8">
				<TD WIDTH="5%" ALIGN="center">&nbsp;&nbsp;&nbsp;<INPUT CLASS="buttonStyle"
					TYPE="button" name="btnEffItemsComGrp" value="View Affected Items"
					onclick="javascript:fnViewCompMapping()"></TD>
			</logic:equal>
			<logic:notEqual name="ComponentGroupRequestForm"
				property="compGroupChangeTypeSeqNo" value="8">
				<TD WIDTH="5%" ALIGN="center">&nbsp;&nbsp;&nbsp;<INPUT CLASS="buttonStyle"
					TYPE="button" name="btnEffItemsComGrp" value="View Affected Items"
					onclick="javascript:fnViewCompMapping()" disabled></TD>
			</logic:notEqual>
		</TR>
	</TABLE>
	<div class="miniDashOut2">
	<TABLE WIDTH="100%" BORDER="0" ALIGN=center>


		<TR>
			<TD WIDTH="50%" colspan=2 ALIGN="center">

			<TABLE WIDTH="100%" BORDER="1" ALIGN=center>
				<TR>
					<TD colspan=2 CLASS="headerbgcolor" valign=top>
					<TABLE WIDTH="100%" BORDER="0" ALIGN=center>
						<TR>
							<TD valign=top WIDTH="15%" colspan=2 ALIGN="center"
								CLASS="headerbgcolor">Change From</TD>
						</TR>
					</TABLE>
					</TD>
				</TR>
				<TR>
					<TD width="35%" CLASS="headerbgcolor" ALIGN="left">Component Group
					Name</TD>
					<TD CLASS="formFieldSub"><html:text property="oldCompGrpName"
						name="ComponentGroupRequestForm" styleClass="COMMONTEXTBOXHEIGHT3"
						size="54" disabled="true" maxlength="100" /></TD>
				</TR>

				<TR>
					<TD CLASS="headerbgcolor" ALIGN="left">Characterization</TD>

					<logic:present name="ComponentGroupRequestForm"
						property="oldCompGrpChacFlag">
						<TD CLASS="formFieldSub"><logic:equal
							name="ComponentGroupRequestForm" property="oldCompGrpChacFlag"
							value="Y">
							<B>Yes</B>
							<input type="radio" Class="radcheck" name="oldCompGrpChacFlag"
								checked value="Y" disabled>
							<B>No</B>
							<input type="radio" Class="radcheck" name="oldCompGrpChacFlag"
								value="N" disabled>
						</logic:equal> <logic:equal name="ComponentGroupRequestForm"
							property="oldCompGrpChacFlag" value="N">
							<B>Yes</B>
							<input type="radio" Class="radcheck" name="oldCompGrpChacFlag"
								value="Y" disabled>
							<B>No</B>
							<input type="radio" Class="radcheck" name="oldCompGrpChacFlag"
								checked value="N" disabled>
						</logic:equal></TD>
					</logic:present>

					<logic:notPresent name="ComponentGroupRequestForm"
						property="oldCompGrpChacFlag">
						<TD CLASS="formFieldSub"><B>Yes</B><input type="radio"
							Class="radcheck" name="oldCompGrpChacFlag" value="Y" disabled> <B>No</B><input
							type="radio" Class="radcheck" name="oldCompGrpChacFlag" value="N"
							disabled></TD>
					</logic:notPresent>


				</TR>
				<TR>
					<TD nowrap CLASS="headerbgcolor" ALIGN="left">Required Component
					Group</TD>
					<logic:present name="ComponentGroupRequestForm"
						property="oldCompGrpValidFlag">
						<TD CLASS="formFieldSub"><logic:equal
							name="ComponentGroupRequestForm" property="oldCompGrpValidFlag"
							value="Y">
							<B>Yes</B>
							<input type="radio" Class="radcheck" name="oldCompGrpValidFlag"
								checked value="Y" disabled>
							<B>No</B>
							<input type="radio" Class="radcheck" name="oldCompGrpValidFlag"
								value="N" disabled>
						</logic:equal> <logic:equal name="ComponentGroupRequestForm"
							property="oldCompGrpValidFlag" value="N">
							<B>Yes</B>
							<input type="radio" Class="radcheck" name="oldCompGrpValidFlag"
								value="Y" disabled>
							<B>No</B>
							<input type="radio" Class="radcheck" name="oldCompGrpValidFlag"
								checked value="N" disabled>
						</logic:equal></TD>
					</logic:present>

					<logic:notPresent name="ComponentGroupRequestForm"
						property="oldCompGrpValidFlag">
						<TD CLASS="formFieldSub"><B>Yes</B><input type="radio"
							Class="radcheck" name="oldCompGrpValidFlag" value="Y" disabled> <B>No</B><input
							type="radio" Class="radcheck" name="oldCompGrpValidFlag"
							value="N" disabled></TD>
					</logic:notPresent>
				</TR>
			</TABLE>
			</TD>

			<TD WIDTH="" ALIGN="center">&nbsp;</TD>
			<TD WIDTH="50%" colspan=2 ALIGN="center">
			<TABLE WIDTH="100%" BORDER="1" ALIGN=center>
				<TR>
					<TD colspan=2 CLASS="headerbgcolor">
					<TABLE WIDTH="100%" BORDER="0" ALIGN=center>
						<TR>
							<TD WIDTH="15%" valign=top colspan=2 ALIGN="center"
								CLASS="headerbgcolor">Change To / Create</TD>
						</TR>
					</TABLE>
					</TD>

				</TR>

				<TR>
					<TD width="35%" CLASS="headerbgcolor" ALIGN="left">Component Group
					Name</TD>
					<TD CLASS="formFieldSub"><logic:equal
						name="ComponentGroupRequestForm"
						property="compGroupChangeTypeSeqNo" value="2">
						<html:text property="newCompGrpName"
							styleClass="COMMONTEXTBOXHEIGHT3" size="54" disabled="true"
							maxlength="100" />
					</logic:equal> <logic:notEqual name="ComponentGroupRequestForm"
						property="compGroupChangeTypeSeqNo" value="2">
					<!-- Modified for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 -->
						<logic:greaterThan
						name="ComponentGroupRequestForm" property="compGroupChangeTypeSeqNo"
						value="6">
							<input type="text" name="newCompGrpName" 
							class="COMMONTEXTBOXHEIGHT3" size="54" disabled="true"/>
						</logic:greaterThan>
						<logic:lessThan
						name="ComponentGroupRequestForm" property="compGroupChangeTypeSeqNo"
						value="6">
							<html:text property="newCompGrpName"
								styleClass="COMMONTEXTBOXHEIGHT3" size="54" disabled="false"
								maxlength="100" />
						</logic:lessThan>		
					</logic:notEqual></TD>
				</TR>

				<TR>
					<TD CLASS="headerbgcolor" ALIGN="left">Characterization</TD>
					<logic:equal name="ComponentGroupRequestForm"
						property="compGroupChangeTypeSeqNo" value="2">
						<logic:present name="ComponentGroupRequestForm"
							property="newCompGrpChacFlag">
							<TD CLASS="formFieldSub"><logic:equal
								name="ComponentGroupRequestForm" property="newCompGrpChacFlag"
								value="Y">
								<B>Yes</B>
								<input type="radio" Class="radcheck" name="newCompGrpChacFlag"
									checked value="Y" disabled>
								<B>No</B>
								<input type="radio" Class="radcheck" name="newCompGrpChacFlag"
									value="N" disabled>
							</logic:equal> <logic:equal name="ComponentGroupRequestForm"
								property="newCompGrpChacFlag" value="N">
								<B>Yes</B>
								<input type="radio" Class="radcheck" name="newCompGrpChacFlag"
									value="Y" disabled>
								<B>No</B>
								<input type="radio" Class="radcheck" name="newCompGrpChacFlag"
									checked value="N" disabled>
							</logic:equal></TD>
						</logic:present>
					</logic:equal>

					<logic:equal name="ComponentGroupRequestForm"
						property="compGroupChangeTypeSeqNo" value="2">
						<logic:notPresent name="ComponentGroupRequestForm"
							property="newCompGrpChacFlag">
							<TD CLASS="formFieldSub"><B>Yes</B><input type="radio"
								Class="radcheck" name="newCompGrpChacFlag" value="Y" disabled> <B>No</B><input
								type="radio" Class="radcheck" name="newCompGrpChacFlag"
								value="N" checked disabled></TD>
						</logic:notPresent>
					</logic:equal>

					<logic:notEqual name="ComponentGroupRequestForm"
						property="compGroupChangeTypeSeqNo" value="2">
						<!-- Modified for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 -->
						<logic:greaterThan
						name="ComponentGroupRequestForm" property="compGroupChangeTypeSeqNo"
						value="6">
							<logic:present name="ComponentGroupRequestForm"
								property="newCompGrpChacFlag">
								<TD CLASS="formFieldSub"><B>Yes</B><input type="radio"
								Class="radcheck" name="newCompGrpChacFlag" value="Y" disabled> <B>No</B><input
								type="radio" Class="radcheck" name="newCompGrpChacFlag"
								value="N" disabled></TD>
							</logic:present>
						</logic:greaterThan>	
						<logic:lessThan
						name="ComponentGroupRequestForm" property="compGroupChangeTypeSeqNo"
						value="6">	
							<logic:present name="ComponentGroupRequestForm"
								property="newCompGrpChacFlag">
								<TD CLASS="formFieldSub"><logic:equal
									name="ComponentGroupRequestForm" property="newCompGrpChacFlag"
									value="Y">
									<B>Yes</B>
									<input type="radio" Class="radcheck" name="newCompGrpChacFlag"
										checked value="Y">
									<B>No</B>
									<input type="radio" Class="radcheck" name="newCompGrpChacFlag"
										value="N">
								</logic:equal> <logic:equal name="ComponentGroupRequestForm"
									property="newCompGrpChacFlag" value="N">
									<B>Yes</B>
									<input type="radio" Class="radcheck" name="newCompGrpChacFlag"
										value="Y">
									<B>No</B>
									<input type="radio" Class="radcheck" name="newCompGrpChacFlag"
										checked value="N">
								</logic:equal></TD>
							</logic:present>
						</logic:lessThan>
					</logic:notEqual>

					<logic:notEqual name="ComponentGroupRequestForm"
						property="compGroupChangeTypeSeqNo" value="2">
						<!-- Modified for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 -->
						<logic:greaterThan
						name="ComponentGroupRequestForm" property="compGroupChangeTypeSeqNo"
						value="6">
							<logic:notPresent name="ComponentGroupRequestForm"
							property="newCompGrpChacFlag">
								<TD CLASS="formFieldSub"><B>Yes</B><input type="radio"
									Class="radcheck" name="newCompGrpChacFlag" value="Y" disabled> <B>No</B><input
									type="radio" Class="radcheck" name="newCompGrpChacFlag"
									value="N" disabled></TD>
							</logic:notPresent>					
						</logic:greaterThan>	
						<logic:lessThan
						name="ComponentGroupRequestForm" property="compGroupChangeTypeSeqNo"
						value="6">	
							<logic:notPresent name="ComponentGroupRequestForm"
							property="newCompGrpChacFlag">
								<TD CLASS="formFieldSub"><B>Yes</B><input type="radio"
									Class="radcheck" name="newCompGrpChacFlag" value="Y"> <B>No</B><input
									type="radio" Class="radcheck" name="newCompGrpChacFlag"
									value="N" checked></TD>
							</logic:notPresent>
						</logic:lessThan>
					</logic:notEqual>

				</TR>
				<TR>
					<TD nowrap CLASS="headerbgcolor" ALIGN="left">Required Component
					Group</TD>

					<logic:equal name="ComponentGroupRequestForm"
						property="compGroupChangeTypeSeqNo" value="2">
						<logic:present name="ComponentGroupRequestForm"
							property="newCompGrpValidFlag">
							<TD CLASS="formFieldSub"><logic:equal
								name="ComponentGroupRequestForm" property="newCompGrpValidFlag"
								value="Y">
								<B>Yes</B>
								<input type="radio" Class="radcheck" name="newCompGrpValidFlag"
									checked value="Y" disabled>
								<B>No</B>
								<input type="radio" Class="radcheck" name="newCompGrpValidFlag"
									value="N" disabled>
							</logic:equal> <logic:equal name="ComponentGroupRequestForm"
								property="newCompGrpValidFlag" value="N">
								<B>Yes</B>
								<input type="radio" Class="radcheck" name="newCompGrpValidFlag"
									value="Y" disabled>
								<B>No</B>
								<input type="radio" Class="radcheck" name="newCompGrpValidFlag"
									checked value="N" disabled>
							</logic:equal></TD>
						</logic:present>
					</logic:equal>


					<logic:equal name="ComponentGroupRequestForm"
						property="newCompGrpValidFlag" value="2">
						<logic:notPresent name="ComponentGroupRequestForm"
							property="newCompGrpValidFlag">
							<TD CLASS="formFieldSub"><B>Yes</B><input type="radio"
								Class="radcheck" name="newCompGrpValidFlag" value="Y" disabled>
							<B>No</B><input type="radio" Class="radcheck"
								name="newCompGrpValidFlag" value="N" checked disabled></TD>
						</logic:notPresent>
					</logic:equal>



					<logic:notEqual name="ComponentGroupRequestForm"
						property="compGroupChangeTypeSeqNo" value="2">
						<!-- Modified for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 -->
						<logic:greaterThan
						name="ComponentGroupRequestForm" property="compGroupChangeTypeSeqNo"
						value="6">
						<logic:present name="ComponentGroupRequestForm"
						property="newCompGrpValidFlag">
							<TD CLASS="formFieldSub"><B>Yes</B><input type="radio"
								Class="radcheck" name="newCompGrpValidFlag" value="Y" disabled> <B>No</B><input
								type="radio" Class="radcheck" name="newCompGrpValidFlag"
								value="N" disabled></TD>
						</logic:present>
						</logic:greaterThan>	
						<logic:lessThan
						name="ComponentGroupRequestForm" property="compGroupChangeTypeSeqNo"
						value="6">	
							<logic:present name="ComponentGroupRequestForm"
								property="newCompGrpValidFlag">
								<TD CLASS="formFieldSub"><logic:equal
									name="ComponentGroupRequestForm" property="newCompGrpValidFlag"
									value="Y">
									<B>Yes</B>
									<input type="radio" Class="radcheck" name="newCompGrpValidFlag"
										checked value="Y">
									<B>No</B>
									<input type="radio" Class="radcheck" name="newCompGrpValidFlag"
										value="N">
								</logic:equal> <logic:equal name="ComponentGroupRequestForm"
									property="newCompGrpValidFlag" value="N">
									<B>Yes</B>
									<input type="radio" Class="radcheck" name="newCompGrpValidFlag"
										value="Y">
									<B>No</B>
									<input type="radio" Class="radcheck" name="newCompGrpValidFlag"
										checked value="N">
								</logic:equal></TD>
							</logic:present>
						</logic:lessThan>
					</logic:notEqual>


					<logic:notEqual name="ComponentGroupRequestForm"
						property="newCompGrpValidFlag" value="2">
						<!-- Modified for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 -->
						<logic:greaterThan
						name="ComponentGroupRequestForm" property="compGroupChangeTypeSeqNo"
						value="6">
							<logic:notPresent name="ComponentGroupRequestForm"
								property="newCompGrpValidFlag">
								<TD CLASS="formFieldSub"><B>Yes</B><input type="radio"
									Class="radcheck" name="newCompGrpValidFlag" value="Y" disabled> <B>No</B><input
									type="radio" Class="radcheck" name="newCompGrpValidFlag"
									value="N" disabled></TD>
							</logic:notPresent>
						</logic:greaterThan>	
						<logic:lessThan
						name="ComponentGroupRequestForm" property="compGroupChangeTypeSeqNo"
						value="6">
							<logic:notPresent name="ComponentGroupRequestForm"
								property="newCompGrpValidFlag">
								<TD CLASS="formFieldSub"><B>Yes</B><input type="radio"
									Class="radcheck" name="newCompGrpValidFlag" value="Y"> <B>No</B><input
									type="radio" Class="radcheck" name="newCompGrpValidFlag"
									value="N" checked></TD>
							</logic:notPresent>
						</logic:lessThan>
					</logic:notEqual>
				</TR>
			</TABLE>
			</TD>
		</TR>

	</TABLE>
	</DIV>
	<!-- </TD>
</TR>

<TR>
	<TD WIDTH="50%" colspan=2 ALIGN="right" CLASS="header">&nbsp;</TD>
		
	</TD>
	
	<TD WIDTH="50%" colspan=2 ALIGN="left" CLASS="formFieldSub">
		&nbsp;&nbsp;
	</TD>
</TR>
</TABLE>
</TR>
</TABLE> -->
	<BR>
	<BR>
	<A name="#Component"></A>
	<TABLE BORDER="0" WIDTH="70%" ALIGN="center">
		<TR>
			<BR>
			<TD CLASS="dashBoardSubHeading" ALIGN="center"><bean:message
				key="Application.Screen.Component" /></TD>
		</TR>

	</TABLE>
	<BR>
	<TABLE ALIGN="CENTER" WIDTH="50%" BORDER="0" CELLPADDING="0"
		CELLSPACING="0">
		<TR>
			<!-- <TD WIDTH="4%">&nbsp;</TD> -->

			<logic:equal name="ComponentGroupRequestForm"
				property="compGroupChangeTypeSeqNo" value="7">
				<logic:equal name="ComponentGroupRequestForm"
					property="compChangeTypeSeqNo" value="7">
					<!-- Added for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 -->
					<TD WIDTH="11%" CLASS=header>Not Required : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="7"
						onclick="javascript:EnableDisableComp()" checked></TD>
					<!-- Added for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 - Ends here -->	
					<TD WIDTH="7%" CLASS=header>New : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="1"
						onclick="javascript:EnableDisableComp()" disabled>&nbsp;</TD>
					<TD WIDTH="8%" CLASS=header>Existing : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="2"
						onclick="javascript:EnableDisableComp()" disabled>&nbsp;</TD>
					<TD WIDTH="8%" CLASS=header>Revise : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="3"
						onclick="javascript:EnableDisableComp()" disabled></TD>
					<!-- Added for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 -->					
					<!-- Modified for CR_94 : Renamed Delete to Un-map for Components all over this page -->
					<TD WIDTH="8%" CLASS=header>Un-map : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="8"
						onclick="javascript:EnableDisableComp()" disabled></TD>
					<!-- Added for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 - Ends here -->	
				</logic:equal>
			</logic:equal>

			<logic:equal name="ComponentGroupRequestForm"
				property="compGroupChangeTypeSeqNo" value="1">
				<logic:equal name="ComponentGroupRequestForm"
				property="compChangeTypeSeqNo" value="7">
					<TD WIDTH="11%" CLASS=header>Not Required : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="7"
						onclick="javascript:EnableDisableComp()" checked></TD>
					<TD WIDTH="7%" CLASS=header>New : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="1"
						onclick="javascript:EnableDisableComp()" >&nbsp;</TD>
					<TD WIDTH="8%" CLASS=header>Existing : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="2"
						onclick="javascript:EnableDisableComp()" disabled>&nbsp;</TD>
					<TD WIDTH="8%" CLASS=header>Revise : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="3"
						onclick="javascript:EnableDisableComp()" disabled></TD>
					<TD WIDTH="8%" CLASS=header>Un-map : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="8"
						onclick="javascript:EnableDisableComp()" disabled></TD>
				</logic:equal>
				<logic:equal name="ComponentGroupRequestForm"
				property="compChangeTypeSeqNo" value="1">
					<TD WIDTH="11%" CLASS=header>Not Required : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="7"
						onclick="javascript:EnableDisableComp()" ></TD>
					<TD WIDTH="7%" CLASS=header>New : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="1"
						onclick="javascript:EnableDisableComp()" checked>&nbsp;</TD>
					<TD WIDTH="8%" CLASS=header>Existing : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="2"
						onclick="javascript:EnableDisableComp()" disabled>&nbsp;</TD>
					<TD WIDTH="8%" CLASS=header>Revise : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="3"
						onclick="javascript:EnableDisableComp()" disabled></TD>	
					<TD WIDTH="8%" CLASS=header>Un-map : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="8"
						onclick="javascript:EnableDisableComp()" disabled></TD>
				</logic:equal>
			</logic:equal>

			<logic:equal name="ComponentGroupRequestForm"
			property="compGroupChangeTypeSeqNo" value="8">
				<TD WIDTH="11%" CLASS=header>Not Required : <input type="radio"
					name="compChangeTypeSeqNo" Class="RADCHECK" value="7"
					onclick="javascript:EnableDisableComp()" disabled></TD>
				<TD WIDTH="7%" CLASS=header>New : <input type="radio"
					name="compChangeTypeSeqNo" Class="RADCHECK" value="1"
					onclick="javascript:EnableDisableComp()" disabled>&nbsp;</TD>
				<TD WIDTH="8%" CLASS=header>Existing : <input type="radio"
					name="compChangeTypeSeqNo" Class="RADCHECK" value="2"
					onclick="javascript:EnableDisableComp()" disabled>&nbsp;</TD>
				<TD WIDTH="8%" CLASS=header>Revise : <input type="radio"
					name="compChangeTypeSeqNo" Class="RADCHECK" value="3"
					onclick="javascript:EnableDisableComp()" disabled></TD>
				<TD WIDTH="8%" CLASS=header>Un-map : <input type="radio"
					name="compChangeTypeSeqNo" Class="RADCHECK" value="8"
					onclick="javascript:EnableDisableComp()" disabled></TD>
			</logic:equal>
			
			<logic:equal name="ComponentGroupRequestForm"
			property="compGroupChangeTypeSeqNo" value="3">
				<logic:equal name="ComponentGroupRequestForm"
					property="compChangeTypeSeqNo" value="7">
					<!-- Added for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 -->
					<TD WIDTH="11%" CLASS=header>Not Required : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="7"
						onclick="javascript:EnableDisableComp()" checked></TD>
					<!-- Added for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 - Ends here -->	
					<TD WIDTH="7%" CLASS=header>New : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="1"
						onclick="javascript:EnableDisableComp()">&nbsp;</TD>
					<TD WIDTH="8%" CLASS=header>Existing : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="2"
						onclick="javascript:EnableDisableComp()">&nbsp;</TD>
					<TD WIDTH="8%" CLASS=header>Revise : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="3"
						onclick="javascript:EnableDisableComp()"></TD>
					<TD WIDTH="8%" CLASS=header>Un-map : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="8"
						onclick="javascript:EnableDisableComp()"></TD>
				</logic:equal>
				<logic:equal name="ComponentGroupRequestForm"
					property="compChangeTypeSeqNo" value="1">
					<TD WIDTH="11%" CLASS=header>Not Required : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="7"
						onclick="javascript:EnableDisableComp()"></TD>
					<TD WIDTH="7%" CLASS=header>New : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="1"
						onclick="javascript:EnableDisableComp()" checked>&nbsp;</TD>
					<TD WIDTH="8%" CLASS=header>Existing : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="2"
						onclick="javascript:EnableDisableComp()">&nbsp;</TD>
					<TD WIDTH="8%" CLASS=header>Revise : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="3"
						onclick="javascript:EnableDisableComp()"></TD>
					<TD WIDTH="8%" CLASS=header>Un-map : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="8"
						onclick="javascript:EnableDisableComp()"></TD>
				</logic:equal>
				<logic:equal name="ComponentGroupRequestForm"
					property="compChangeTypeSeqNo" value="2">
					<TD WIDTH="11%" CLASS=header>Not Required : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="7"
						onclick="javascript:EnableDisableComp()" disabled></TD>	
					<TD WIDTH="7%" CLASS=header>New : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="1"
						onclick="javascript:EnableDisableComp()" disabled>&nbsp;</TD>
					<TD WIDTH="8%" CLASS=header>Existing : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="2"
						onclick="javascript:EnableDisableComp()" checked>&nbsp;</TD>
					<TD WIDTH="8%" CLASS=header>Revise : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="3"
						onclick="javascript:EnableDisableComp()"></TD>
					<TD WIDTH="8%" CLASS=header>Un-map : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="8"
						onclick="javascript:EnableDisableComp()"></TD>	
				</logic:equal>
	
				<logic:equal name="ComponentGroupRequestForm"
					property="compChangeTypeSeqNo" value="3">
					<TD WIDTH="11%" CLASS=header>Not Required : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="7"
						onclick="javascript:EnableDisableComp()"></TD>	
					<TD WIDTH="7%" CLASS=header>New : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="1"
						onclick="javascript:EnableDisableComp()">&nbsp;</TD>
					<TD WIDTH="8%" CLASS=header>Existing : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="2"
						onclick="javascript:EnableDisableComp()">&nbsp;</TD>
					<TD WIDTH="8%" CLASS=header>Revise : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="3"
						onclick="javascript:EnableDisableComp()" checked></TD>
					<TD WIDTH="8%" CLASS=header>Un-map : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="8"
						onclick="javascript:EnableDisableComp()"></TD>	
				</logic:equal>
				<logic:equal name="ComponentGroupRequestForm"
					property="compChangeTypeSeqNo" value="8">
					<TD WIDTH="11%" CLASS=header>Not Required : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="7"
						onclick="javascript:EnableDisableComp()"></TD>
					<TD WIDTH="7%" CLASS=header>New : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="1"
						onclick="javascript:EnableDisableComp()">&nbsp;</TD>
					<TD WIDTH="8%" CLASS=header>Existing : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="2"
						onclick="javascript:EnableDisableComp()">&nbsp;</TD>
					<TD WIDTH="8%" CLASS=header>Revise : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="3"
						onclick="javascript:EnableDisableComp()"></TD>
					<TD WIDTH="8%" CLASS=header>Un-map : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="8"
						onclick="javascript:EnableDisableComp()" checked></TD>
				</logic:equal>
		</logic:equal>	
		
		<logic:equal name="ComponentGroupRequestForm"
		property="compGroupChangeTypeSeqNo" value="2">
				<logic:equal name="ComponentGroupRequestForm"
					property="compChangeTypeSeqNo" value="1">
					<TD WIDTH="11%" CLASS=header>Not Required : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="7"
						onclick="javascript:EnableDisableComp()" disabled></TD>
					<TD WIDTH="7%" CLASS=header>New : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="1"
						onclick="javascript:EnableDisableComp()" checked>&nbsp;</TD>
					<TD WIDTH="8%" CLASS=header>Existing : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="2"
						onclick="javascript:EnableDisableComp()">&nbsp;</TD>
					<TD WIDTH="8%" CLASS=header>Revise : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="3"
						onclick="javascript:EnableDisableComp()"></TD>
					<TD WIDTH="8%" CLASS=header>Un-map : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="8"
						onclick="javascript:EnableDisableComp()"></TD>
				</logic:equal>
				<logic:equal name="ComponentGroupRequestForm"
					property="compChangeTypeSeqNo" value="2">
					<TD WIDTH="11%" CLASS=header>Not Required : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="7"
						onclick="javascript:EnableDisableComp()" disabled></TD>	
					<TD WIDTH="7%" CLASS=header>New : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="1"
						onclick="javascript:EnableDisableComp()">&nbsp;</TD>
					<TD WIDTH="8%" CLASS=header>Existing : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="2"
						onclick="javascript:EnableDisableComp()" checked>&nbsp;</TD>
					<TD WIDTH="8%" CLASS=header>Revise : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="3"
						onclick="javascript:EnableDisableComp()"></TD>
					<TD WIDTH="8%" CLASS=header>Un-map : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="8"
						onclick="javascript:EnableDisableComp()"></TD>	
				</logic:equal>
	
				<logic:equal name="ComponentGroupRequestForm"
					property="compChangeTypeSeqNo" value="3">
					<TD WIDTH="11%" CLASS=header>Not Required : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="7"
						onclick="javascript:EnableDisableComp()" disabled></TD>	
					<TD WIDTH="7%" CLASS=header>New : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="1"
						onclick="javascript:EnableDisableComp()">&nbsp;</TD>
					<TD WIDTH="8%" CLASS=header>Existing : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="2"
						onclick="javascript:EnableDisableComp()">&nbsp;</TD>
					<TD WIDTH="8%" CLASS=header>Revise : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="3"
						onclick="javascript:EnableDisableComp()" checked></TD>
					<TD WIDTH="8%" CLASS=header>Un-map : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="8"
						onclick="javascript:EnableDisableComp()"></TD>	
				</logic:equal>
				<logic:equal name="ComponentGroupRequestForm"
					property="compChangeTypeSeqNo" value="8">
					<TD WIDTH="11%" CLASS=header>Not Required : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="7"
						onclick="javascript:EnableDisableComp()" disabled></TD>
					<TD WIDTH="7%" CLASS=header>New : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="1"
						onclick="javascript:EnableDisableComp()">&nbsp;</TD>
					<TD WIDTH="8%" CLASS=header>Existing : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="2"
						onclick="javascript:EnableDisableComp()">&nbsp;</TD>
					<TD WIDTH="8%" CLASS=header>Revise : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="3"
						onclick="javascript:EnableDisableComp()"></TD>
					<TD WIDTH="8%" CLASS=header>Un-map : <input type="radio"
						name="compChangeTypeSeqNo" Class="RADCHECK" value="8"
						onclick="javascript:EnableDisableComp()" checked></TD>
				</logic:equal>
		</logic:equal>
		</TR>
		<TR>
			<TD COLSPAN=5>&nbsp;</TD>
		</TR>
	</TABLE>

	<TABLE ALIGN="CENTER" WIDTH="30%" BORDER="0" CELLPADDING="0"
		CELLSPACING="0">

		<TR>
			<TD CLASS="header" nowrap>Available
			Component(s)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
			<TD align=left>&nbsp; <logic:equal name="ComponentGroupRequestForm"
				property="compChangeTypeSeqNo" value="1">
				<html:select name="ComponentGroupRequestForm" property="compSeqNo"
					styleClass="selectstyle2"
					onchange="javascript:loadSelectedComponent()" disabled="true">
					<option selected value="-1">---Select---</option>
					<logic:present name="ComponentGroupRequestForm" property="compList">
						<html:optionsCollection name="ComponentGroupRequestForm"
							property="compList" value="componentSeqNo" label="componentName" />
					</logic:present>
				</html:select>
			</logic:equal> 
			<logic:equal name="ComponentGroupRequestForm"
					property="compGroupChangeTypeSeqNo" value="8">
				<html:select name="ComponentGroupRequestForm" property="compSeqNo"
					styleClass="selectstyle2"
					onchange="javascript:loadSelectedComponent()" disabled="true">
					<option selected value="-1">---Select---</option>
					<logic:present name="ComponentGroupRequestForm" property="compList">
						<html:optionsCollection name="ComponentGroupRequestForm"
							property="compList" value="componentSeqNo" label="componentName" />
					</logic:present>
				</html:select>
			</logic:equal>
			<logic:notEqual name="ComponentGroupRequestForm"
					property="compGroupChangeTypeSeqNo" value="8">
				<logic:notEqual name="ComponentGroupRequestForm"
						property="compChangeTypeSeqNo" value="1">
						<logic:equal name="ComponentGroupRequestForm"
						property="compChangeTypeSeqNo" value="7">
						<html:select name="ComponentGroupRequestForm" property="compSeqNo"
							styleClass="selectstyle2"
							onchange="javascript:loadSelectedComponent()" disabled="true">
							<option selected value="-1">---Select---</option>
							<logic:present name="ComponentGroupRequestForm" property="compList">
								<html:optionsCollection name="ComponentGroupRequestForm"
									property="compList" value="componentSeqNo" label="componentName" />
							</logic:present>
						</html:select>
					</logic:equal>
					<logic:notEqual name="ComponentGroupRequestForm"
						property="compChangeTypeSeqNo" value="7">
						<html:select name="ComponentGroupRequestForm" property="compSeqNo"
							styleClass="selectstyle2"
							onchange="javascript:loadSelectedComponent()">
							<option selected value="-1">---Select---</option>
							<logic:present name="ComponentGroupRequestForm" property="compList">
								<html:optionsCollection name="ComponentGroupRequestForm"
									property="compList" value="componentSeqNo" label="componentName" />
							</logic:present>
						</html:select>
					</logic:notEqual>
				</logic:notEqual>
			</logic:notEqual></TD>
			<logic:equal name="ComponentGroupRequestForm"
				property="compChangeTypeSeqNo" value="8">
				<TD WIDTH="5%" ALIGN="center">&nbsp;&nbsp;&nbsp;<INPUT CLASS="buttonStyle"
					TYPE="button" name="btnEffItemsComp" value="View Affected Items"
					onclick="javascript:fnViewCompMapping()"></TD>
			</logic:equal>
			<logic:notEqual name="ComponentGroupRequestForm"
				property="compChangeTypeSeqNo" value="8">
				<TD WIDTH="5%" ALIGN="center">&nbsp;&nbsp;&nbsp;<INPUT CLASS="buttonStyle"
					TYPE="button" name="btnEffItemsComp" value="View Affected Items"
					onclick="javascript:fnViewCompMapping()" disabled></TD>
			</logic:notEqual>
		</TR>
	</TABLE>

	<div class="miniDashOut2">
	<TABLE WIDTH="100%" BORDER="0" ALIGN=center>

		<TR>
			<TD WIDTH="50%" colspan=2 ALIGN="center">

			<TABLE WIDTH="100%" BORDER="1" ALIGN=center>
				<TR>
					<TD colspan=2 CLASS="headerbgcolor" valign=top>
					<TABLE WIDTH="100%" BORDER="0" ALIGN=center>
						<TR>
							<TD valign=top WIDTH="15%" colspan=2 ALIGN="center"
								CLASS="headerbgcolor">Change From</TD>
						</TR>
					</TABLE>
					</TD>
				</TR>

				<TR>
					<TD width="35%" CLASS="headerbgcolor" ALIGN="left">Components/Value</TD>
					<TD CLASS="formFieldSub"><html:text property="oldCompName"
						name="ComponentGroupRequestForm" styleClass="COMMONTEXTBOXHEIGHT3"
						size="54" disabled="true" maxlength="100" /></TD>
				</TR>

				<TR>
					<TD CLASS="headerbgcolor" ALIGN="left">Default Component</TD>
					<logic:present name="ComponentGroupRequestForm"
						property="oldCompDefaultFlag">
						<TD CLASS="formFieldSub"><logic:equal
							name="ComponentGroupRequestForm" property="oldCompDefaultFlag"
							value="true">
							<B>Yes</B>
							<input type="radio" Class="radcheck" name="oldCompDefaultFlag"
								checked value="Y" disabled>
							<B>No</B>
							<input type="radio" Class="radcheck" name="oldCompDefaultFlag"
								value="N" disabled></TD>
						</logic:equal>

						<logic:equal name="ComponentGroupRequestForm"
							property="oldCompDefaultFlag" value="false">
							<B>Yes</B>
							<input type="radio" Class="radcheck" name="oldCompDefaultFlag"
								value="Y" disabled>
							<B>No</B>
							<input type="radio" Class="radcheck" name="oldCompDefaultFlag"
								checked value="N" disabled>
						</logic:equal>
						</TD>
					</logic:present>

					<logic:notPresent name="ComponentGroupRequestForm"
						property="oldCompDefaultFlag">
						<TD CLASS="formFieldSub"><B>Yes</B><input type="radio"
							Class="radcheck" name="oldCompDefaultFlag" value="Y" disabled> <B>No</B><input
							type="radio" Class="radcheck" name="oldCompDefaultFlag" value="N"
							disabled></TD>
					</logic:notPresent>


				</TR>
			</TABLE>
			</TD>


			<TD WIDTH="" ALIGN="center">&nbsp;</TD>
			<TD WIDTH="50%" colspan=2 ALIGN="center">
			<TABLE WIDTH="100%" BORDER="1" ALIGN=center>
				<TR>
					<TD colspan=2 CLASS="headerbgcolor">
					<TABLE WIDTH="100%" BORDER="0" ALIGN=center>
						<TR>
							<TD WIDTH="15%" valign=top colspan=2 ALIGN="center"
								CLASS="headerbgcolor">Change To / Create</TD>
						</TR>
					</TABLE>
					</TD>

				</TR>
				<TR>
					<TD width="40%" CLASS="headerbgcolor" ALIGN="left">Components/Value</TD>
					<TD CLASS="formFieldSub"><logic:equal
						name="ComponentGroupRequestForm" property="compChangeTypeSeqNo"
						value="2">
						<html:text property="newCompName" name="ComponentGroupRequestForm"
							styleClass="COMMONTEXTBOXHEIGHT3" size="54" disabled="true"
							maxlength="100" />
					</logic:equal> 
					<logic:equal name="ComponentGroupRequestForm"
						property="compGroupChangeTypeSeqNo" value="8">
						<html:text property="newCompName" name="ComponentGroupRequestForm"
							styleClass="COMMONTEXTBOXHEIGHT3" size="54" disabled="true"
							maxlength="100" />
					</logic:equal>
					<logic:notEqual name="ComponentGroupRequestForm"
						property="compGroupChangeTypeSeqNo" value="8">
					<logic:notEqual name="ComponentGroupRequestForm"
						property="compChangeTypeSeqNo" value="2">
						<!-- Modified for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 -->
						<logic:greaterThan
						name="ComponentGroupRequestForm" property="compChangeTypeSeqNo"
						value="6">
							<input type="text" name="newCompName" 
							CLASS="COMMONTEXTBOXHEIGHT3" size="54" disabled="true"/>
						</logic:greaterThan>
						<logic:lessThan 
						name="ComponentGroupRequestForm" property="compChangeTypeSeqNo"
						value="6">
						<html:text property="newCompName" name="ComponentGroupRequestForm"
							styleClass="COMMONTEXTBOXHEIGHT3" size="54" disabled="false"
							maxlength="100" />
						</logic:lessThan>	
					</logic:notEqual>
					</logic:notEqual>
					</TD>
				</TR>

				<TR>
					<TD CLASS="headerbgcolor" ALIGN="left">Default Component</TD>

					<logic:equal name="ComponentGroupRequestForm"
						property="compChangeTypeSeqNo" value="2">
						<logic:present name="ComponentGroupRequestForm"
							property="newCompDefaultFlag">
							<TD CLASS="formFieldSub"><logic:equal
								name="ComponentGroupRequestForm" property="newCompDefaultFlag"
								value="true">
								<B>Yes</B>
								<input type="radio" Class="radcheck" name="newCompDefaultFlag"
									checked value="Y" disabled>
								<B>No</B>
								<input type="radio" Class="radcheck" name="newCompDefaultFlag"
									value="N" disabled />
							</logic:equal> <logic:equal name="ComponentGroupRequestForm"
								property="newCompDefaultFlag" value="false">
								<B>Yes</B>
								<input type="radio" Class="radcheck" name="newCompDefaultFlag"
									value="Y" disabled>
								<B>No</B>
								<input type="radio" Class="radcheck" name="newCompDefaultFlag"
									checked value="N" disabled />
							</logic:equal></TD>
						</logic:present>
					</logic:equal>

					<logic:equal name="ComponentGroupRequestForm"
						property="compGroupChangeTypeSeqNo" value="8">
							<TD CLASS="formFieldSub"><B>Yes</B><input type="radio"
								Class="radcheck" name="newCompDefaultFlag" value="Y" disabled> <B>No</B><input
								type="radio" Class="radcheck" name="newCompDefaultFlag" disabled
								value="N"></TD>
					</logic:equal>
					<logic:notEqual name="ComponentGroupRequestForm"
						property="compGroupChangeTypeSeqNo" value="8">
						<logic:notEqual name="ComponentGroupRequestForm"
							property="compChangeTypeSeqNo" value="2">
							<!-- Modified for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 -->
							<logic:greaterThan
							name="ComponentGroupRequestForm" property="compChangeTypeSeqNo"
							value="6">
								<logic:present name="ComponentGroupRequestForm"
									property="newCompDefaultFlag">
									<TD CLASS="formFieldSub"><B>Yes</B><input type="radio"
										Class="radcheck" name="newCompDefaultFlag" value="Y" disabled> <B>No</B><input
										type="radio" Class="radcheck" name="newCompDefaultFlag" disabled
										value="N"></TD>
								</logic:present>
							</logic:greaterThan>
							<logic:lessThan 
							name="ComponentGroupRequestForm" property="compChangeTypeSeqNo"
							value="6">
								<logic:present name="ComponentGroupRequestForm"
									property="newCompDefaultFlag">
									<TD CLASS="formFieldSub"><logic:equal
										name="ComponentGroupRequestForm" property="newCompDefaultFlag"
										value="true">
										<B>Yes</B>
										<input type="radio" Class="radcheck" name="newCompDefaultFlag"
											checked value="Y">
										<B>No</B>
										<input type="radio" Class="radcheck" name="newCompDefaultFlag"
											value="N" />
									</logic:equal> <logic:equal name="ComponentGroupRequestForm"
										property="newCompDefaultFlag" value="false">
										<B>Yes</B>
										<input type="radio" Class="radcheck" name="newCompDefaultFlag"
											value="Y">
										<B>No</B>
										<input type="radio" Class="radcheck" name="newCompDefaultFlag"
											checked value="N" />
									</logic:equal></TD>
								</logic:present>
							</logic:lessThan>
						</logic:notEqual>

						<logic:notEqual name="ComponentGroupRequestForm"
							property="compChangeTypeSeqNo" value="2">
							<!-- Modified for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 -->
							<logic:greaterThan
							name="ComponentGroupRequestForm" property="compChangeTypeSeqNo"
							value="6">
								<logic:notPresent name="ComponentGroupRequestForm"
									property="newCompDefaultFlag">
									<TD CLASS="formFieldSub"><B>Yes</B><input type="radio"
										Class="radcheck" name="newCompDefaultFlag" value="Y" disabled> <B>No</B><input
										type="radio" Class="radcheck" name="newCompDefaultFlag" disabled
										value="N"></TD>
								</logic:notPresent>
							</logic:greaterThan>
							<logic:lessThan 
							name="ComponentGroupRequestForm" property="compChangeTypeSeqNo"
							value="6">
								<logic:notPresent name="ComponentGroupRequestForm"
									property="newCompDefaultFlag">
									<TD CLASS="formFieldSub"><B>Yes</B><input type="radio"
										Class="radcheck" name="newCompDefaultFlag" value="Y"> <B>No</B><input
										type="radio" Class="radcheck" name="newCompDefaultFlag" checked
										value="N"></TD>
								</logic:notPresent>
							</logic:lessThan>
						</logic:notEqual>
	
						<logic:equal name="ComponentGroupRequestForm"
							property="compChangeTypeSeqNo" value="2">
							<logic:notPresent name="ComponentGroupRequestForm"
								property="newCompDefaultFlag">
								<TD CLASS="formFieldSub"><B>Yes</B><input type="radio"
									Class="radcheck" name="newCompDefaultFlag" value="Y" disabled> <B>No</B><input
									type="radio" Class="radcheck" name="newCompDefaultFlag" checked
									value="N" disabled></TD>
							</logic:notPresent>
						</logic:equal>
					</logic:notEqual>



				</TR>
			</TABLE>
			</TD>
		</TR>

	</TABLE>

	</TD>
	</TR>
	</TABLE>
	</DIV>
	<TABLE align=center>
		<TR>
			<TD colspan=4>&nbsp;</TD>
		</TR>
		<TR>
			<TD CLASS="headerbgcolor" ALIGN="left">Reason for change<font
				color=red size=2>*</font></TD>
			<TD CLASS="formFieldSub"><html:textarea property="reason" rows="3"
				cols="60" /></TD>
			<TD CLASS="headerbgcolor" ALIGN="left">Administrator comments</TD>
			<TD CLASS="formFieldSub"><logic:iterate id="reqDetails"
				name="ComponentGroupRequestForm" property="requestDetails"
				type="com.EMD.LSDB.vo.common.RequestVO" scope="request">
				<logic:equal name="ComponentGroupRequestForm" property="roleID"
					value="ADMN">
					<logic:equal name="reqDetails" property="statusTypeSeqNo" value="2">
						<logic:present name="ComponentGroupRequestForm"
							property="adminComments">
							<html:textarea name="ComponentGroupRequestForm"
								property="adminComments" rows="3" cols="50"></html:textarea>

						</logic:present>
						<logic:notPresent name="ComponentGroupRequestForm"
							property="adminComments">
							<html:textarea property="adminComments" rows="3" cols="50"></html:textarea>

						</logic:notPresent>
					</logic:equal>

					<logic:equal name="reqDetails" property="statusTypeSeqNo" value="4">
						<logic:present name="ComponentGroupRequestForm"
							property="adminComments">
							<html:textarea name="ComponentGroupRequestForm"
								property="adminComments" rows="3" cols="50"></html:textarea>

						</logic:present>
						<logic:notPresent name="ComponentGroupRequestForm"
							property="adminComments">
							<html:textarea property="adminComments" rows="3" cols="50"></html:textarea>

						</logic:notPresent>
					</logic:equal>

					<logic:equal name="reqDetails" property="statusTypeSeqNo" value="5">
						<logic:present name="ComponentGroupRequestForm"
							property="adminComments">
							<html:textarea name="ComponentGroupRequestForm"
								property="adminComments" rows="3" cols="50"></html:textarea>

						</logic:present>
						<logic:notPresent name="ComponentGroupRequestForm"
							property="adminComments">
							<html:textarea property="adminComments" rows="3" cols="50"></html:textarea>

						</logic:notPresent>
					</logic:equal>

					<logic:equal name="reqDetails" property="statusTypeSeqNo" value="6">
						<logic:present name="ComponentGroupRequestForm"
							property="adminComments">
							<html:textarea name="ComponentGroupRequestForm"
								property="adminComments" rows="3" cols="50" disabled="true"></html:textarea>

						</logic:present>
						<logic:notPresent name="ComponentGroupRequestForm"
							property="adminComments">
							<html:textarea property="adminComments" rows="3" cols="50"
								disabled="true"></html:textarea>

						</logic:notPresent>
					</logic:equal>

					<logic:equal name="reqDetails" property="statusTypeSeqNo" value="3">
						<logic:present name="ComponentGroupRequestForm"
							property="adminComments">
							<html:textarea name="ComponentGroupRequestForm"
								property="adminComments" rows="3" cols="50" disabled="true"></html:textarea>

						</logic:present>
						<logic:notPresent name="ComponentGroupRequestForm"
							property="adminComments">
							<html:textarea property="adminComments" rows="3" cols="50"
								disabled="true"></html:textarea>

						</logic:notPresent>
					</logic:equal>

					<logic:equal name="reqDetails" property="statusTypeSeqNo" value="1">
						<logic:present name="ComponentGroupRequestForm"
							property="adminComments">
							<html:textarea name="ComponentGroupRequestForm"
								property="adminComments" rows="3" cols="50" disabled="true"></html:textarea>

						</logic:present>
						<logic:notPresent name="ComponentGroupRequestForm"
							property="adminComments">
							<html:textarea property="adminComments" rows="3" cols="50"
								disabled="true"></html:textarea>

						</logic:notPresent>
					</logic:equal>

					<!-- Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151 -->
					<logic:equal name="reqDetails" property="statusTypeSeqNo" value="7">
						<logic:present name="ComponentGroupRequestForm"
							property="adminComments">
							<html:textarea name="ComponentGroupRequestForm"
								property="adminComments" rows="3" cols="50"></html:textarea>

						</logic:present>
						<logic:notPresent name="ComponentGroupRequestForm"
							property="adminComments">
							<html:textarea property="adminComments" rows="3" cols="50"
								disabled="true"></html:textarea>

						</logic:notPresent>
					</logic:equal>
					<!-- Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151 - Ends here -->
				</logic:equal>

				<logic:notEqual name="ComponentGroupRequestForm" property="roleID"
					value="ADMN">
					<logic:present name="ComponentGroupRequestForm"
						property="adminComments">
						<html:textarea name="ComponentGroupRequestForm"
							property="adminComments" rows="3" cols="50" disabled="true"></html:textarea>

					</logic:present>
					<logic:notPresent name="ComponentGroupRequestForm"
						property="adminComments">
						<html:textarea property="adminComments" rows="3" cols="50"
							disabled="true"></html:textarea>

					</logic:notPresent>
				</logic:notEqual>
			</logic:iterate></TD>
		</TR>
	</TABLE>
	<!-- Added for enabling and disabling buttons -->

	<TABLE BORDER="0" WIDTH="100%" ALIGN="CENTER">
		<TR>
			<TD>&nbsp;</TD>
		</TR>
		<TR>
			<TD width="30%">&nbsp;</TD>
			<logic:iterate id="objRequest" name="ComponentGroupRequestForm"
				property="requestDetails" type="com.EMD.LSDB.vo.common.RequestVO"
				scope="request">
				<input type="hidden" name="hdnStatusTypeSeqNo"
					value=<%=objRequest.getStatusTypeSeqNo()%>>
					
				<logic:equal name="objRequest" property="statusTypeSeqNo" value="1">
					<logic:notEqual name="ComponentGroupRequestForm"
						property="reqModelSaved" value="Y">
						<logic:equal name="ComponentGroupRequestForm" property="roleID"
							value="ADMN">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=objRequest.getReqID()%>')"
								disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify"
								onclick="javascript:saveRequestComponentGroup()"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Submit"
								onclick="javascript:saveRequestStatus(2)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyleYellow"
								TYPE="button" name="btnAdd" value="Onhold"
								onclick="javascript:saveRequestStatus(3)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT
								style='background-color:green' CLASS="buttonStyleCommon"
								TYPE="button" name="btnAdd" value="Approve"
								onclick="javascript:saveRequestStatus(4)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT style='background-color:red'
								CLASS="buttonStyleCommon" TYPE="button" name="btnAdd"
								value="Reject" onclick="javascript:saveRequestStatus(5)"
								disabled></TD>
								<%--Edited for CR_121 --%>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Complete"
								onclick="javascript:saveRequestStatus(6)" disabled id="btnComplete"></TD>
								<%--Edited for CR_121 Ends--%>
							<TD WIDTH="30%">&nbsp;</TD>
							<!-- Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 -->
							<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()"></TD>
						</logic:equal>

						<logic:notEqual name="ComponentGroupRequestForm" property="roleID"
							value="ADMN">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=objRequest.getReqID()%>')"
								disabled></TD>

							<logic:equal name="ComponentGroupRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Save/Modify"
									onclick="javascript:saveRequestComponentGroup()"></TD>
							</logic:equal>
							<logic:notEqual name="ComponentGroupRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Save/Modify"
									onclick="javascript:saveRequestComponentGroup()" disabled></TD>
							</logic:notEqual>

							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Submit"
								onclick="javascript:saveRequestStatus(2)" disabled></TD>

							<!--  <TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyleYellow" TYPE="button" name="btnAdd" value="Onhold" onclick="javascript:saveRequestStatus(3)" disabled ></TD>
		<TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:green' CLASS="buttonStyleCommon" TYPE="button" name="btnAdd" value="Approve" onclick="javascript:saveRequestStatus(4)" disabled></TD>
		<TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:red' CLASS="buttonStyleCommon" TYPE="button" name="btnAdd" value="Reject" onclick="javascript:saveRequestStatus(5)" disabled></TD>
		<TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Complete" onclick="javascript:saveRequestStatus(6)" disabled></TD>-->
							<TD WIDTH="30%">&nbsp;</TD>
							<!-- Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 -->
							<logic:equal name="ComponentGroupRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()"></TD>
							</logic:equal>
							<logic:notEqual name="ComponentGroupRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()" disabled></TD>
							</logic:notEqual>
						</logic:notEqual>
					</logic:notEqual>

					<!-- Saved -->

					<logic:equal name="ComponentGroupRequestForm"
						property="reqModelSaved" value="Y">
						<logic:equal name="ComponentGroupRequestForm" property="roleID"
							value="ADMN">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=objRequest.getReqID()%>')"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify"
								onclick="javascript:saveRequestComponentGroup()"></TD>

							<logic:equal name="ComponentGroupRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Submit"
									onclick="javascript:saveRequestStatus(2)"></TD>
							</logic:equal>

							<logic:notEqual name="ComponentGroupRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Submit"
									onclick="javascript:saveRequestStatus(2)" disabled></TD>
							</logic:notEqual>

							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyleYellow"
								TYPE="button" name="btnAdd" value="Onhold"
								onclick="javascript:saveRequestStatus(3)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT
								style='background-color:green' CLASS="buttonStyleCommon"
								TYPE="button" name="btnAdd" value="Approve"
								onclick="javascript:saveRequestStatus(4)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT style='background-color:red'
								CLASS="buttonStyleCommon" TYPE="button" name="btnAdd"
								value="Reject" onclick="javascript:saveRequestStatus(5)"
								disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Complete"
								onclick="javascript:saveRequestStatus(6)" disabled></TD>
							<TD WIDTH="30%">&nbsp;</TD>

							<logic:equal name="ComponentGroupRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
									CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
									onclick="javascript:resetRequest()"></TD>
							</logic:equal>

							<logic:notEqual name="ComponentGroupRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
									CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
									onclick="javascript:resetRequest()" disabled></TD>
							</logic:notEqual>

						</logic:equal>

						<logic:notEqual name="ComponentGroupRequestForm" property="roleID"
							value="ADMN">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=objRequest.getReqID()%>')"></TD>

							<logic:equal name="ComponentGroupRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Save/Modify"
									onclick="javascript:saveRequestComponentGroup()"></TD>
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Submit"
									onclick="javascript:saveRequestStatus(2)"></TD>
							</logic:equal>

							<logic:notEqual name="ComponentGroupRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Save/Modify"
									onclick="javascript:saveRequestComponentGroup()" disabled></TD>
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Submit"
									onclick="javascript:saveRequestStatus(2)" disabled></TD>
							</logic:notEqual>


							<!--  	<TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyleYellow" TYPE="button" name="btnAdd" value="Onhold" onclick="javascript:saveRequestStatus(3)" disabled ></TD>
		<TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:green' CLASS="buttonStyleCommon" TYPE="button" name="btnAdd" value="Approve" onclick="javascript:saveRequestStatus(4)" disabled></TD>
		<TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:red' CLASS="buttonStyleCommon" TYPE="button" name="btnAdd" value="Reject" onclick="javascript:saveRequestStatus(5)" disabled></TD>
		<TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Complete" onclick="javascript:saveRequestStatus(6)" disabled></TD>-->
							<TD WIDTH="30%">&nbsp;</TD>

							<logic:equal name="ComponentGroupRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
									CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
									onclick="javascript:resetRequest()"></TD>
							</logic:equal>
							<logic:notEqual name="ComponentGroupRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
									CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
									onclick="javascript:resetRequest()" disabled></TD>
							</logic:notEqual>
						</logic:notEqual>
					</logic:equal>
					<!-- ENds -->

				</logic:equal>

					<logic:notEqual name="ComponentGroupRequestForm" property="roleID"
						value="ADMN">
						<logic:equal name="objRequest" property="statusTypeSeqNo"
							value="2">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=objRequest.getReqID()%>')"></TD>
								<%-- Modified for CR_106 to allow  Form Owner to Save/Modify in Submitted State by JG101007 --%>
								<logic:equal name="ComponentGroupRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Save/Modify"
									onclick="javascript:saveRequestComponentGroup()"></TD>
							</logic:equal>
							<logic:notEqual name="ComponentGroupRequestForm" property="userownRequest" value="Y">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify"
								onclick="javascript:saveRequestComponentGroup()" disabled></TD>
								</logic:notEqual>
								<%--CR_106 ends here --%>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Submit"
								onclick="javascript:saveRequestStatus(2)" disabled></TD>

							<!--  <TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyleYellow" TYPE="button" name="btnAdd" value="Onhold" onclick="javascript:saveRequestStatus(3)" disabled ></TD>
		<TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:green' CLASS="buttonStyleCommon" TYPE="button" name="btnAdd" value="Approve" onclick="javascript:saveRequestStatus(4)" disabled></TD>
		<TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:red' CLASS="buttonStyleCommon" TYPE="button" name="btnAdd" value="Reject" onclick="javascript:saveRequestStatus(5)" disabled></TD>
		<TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Complete" onclick="javascript:saveRequestStatus(6)" disabled></TD>-->
							<TD WIDTH="30%">&nbsp;</TD>
							<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()" disabled></TD>
						</logic:equal>

						<logic:equal name="objRequest" property="statusTypeSeqNo"
							value="3">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=objRequest.getReqID()%>')"></TD>

							<logic:equal name="ComponentGroupRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Save/Modify"
									onclick="javascript:saveRequestComponentGroup()"></TD>
							</logic:equal>
							<logic:notEqual name="ComponentGroupRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Save/Modify"
									onclick="javascript:saveRequestComponentGroup()" disabled></TD>
							</logic:notEqual>

							<logic:equal name="ComponentGroupRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Submit"
									onclick="javascript:saveRequestStatus(2)"></TD>
							</logic:equal>

							<logic:notEqual name="ComponentGroupRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Submit"
									onclick="javascript:saveRequestStatus(2)" disabled></TD>
							</logic:notEqual>

							<!--  <TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyleYellow" TYPE="button" name="btnAdd" value="Onhold" onclick="javascript:saveRequestStatus(3)" disabled ></TD>
		<TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:green' CLASS="buttonStyleCommon" TYPE="button" name="btnAdd" value="Approve" onclick="javascript:saveRequestStatus(4)" disabled></TD>
		<TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:red' CLASS="buttonStyleCommon" TYPE="button" name="btnAdd" value="Reject" onclick="javascript:saveRequestStatus(5)" disabled></TD>
		<TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Complete" onclick="javascript:saveRequestStatus(6)" disabled></TD>-->
							<TD WIDTH="30%">&nbsp;</TD>
							<!-- Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 -->
							<logic:equal name="ComponentGroupRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()"></TD>
							</logic:equal>
							<logic:notEqual name="ComponentGroupRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
									CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
									onclick="javascript:resetRequest()" disabled></TD>
							</logic:notEqual>
						</logic:equal>
						<logic:equal name="objRequest" property="statusTypeSeqNo"
							value="4">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=objRequest.getReqID()%>')"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify"
								onclick="javascript:saveRequestComponentGroup()" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Submit"
								onclick="javascript:saveRequestStatus(2)" disabled></TD>

							<!--  <TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyleYellow" TYPE="button" name="btnAdd" value="Onhold" onclick="javascript:saveRequestStatus(3)" disabled ></TD>
		<TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:green' CLASS="buttonStyleCommon" TYPE="button" name="btnAdd" value="Approve" onclick="javascript:saveRequestStatus(4)" disabled></TD>
		<TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:red' CLASS="buttonStyleCommon" TYPE="button" name="btnAdd" value="Reject" onclick="javascript:saveRequestStatus(5)" disabled></TD>
		<TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Complete" onclick="javascript:saveRequestStatus(6)" disabled></TD>-->
							<TD WIDTH="30%">&nbsp;</TD>
							<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()" disabled></TD>
						</logic:equal>

						<logic:equal name="objRequest" property="statusTypeSeqNo"
							value="5">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=objRequest.getReqID()%>')"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify"
								onclick="javascript:saveRequestComponentGroup()" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Submit"
								onclick="javascript:saveRequestStatus(2)" disabled></TD>

							<!--  <TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyleYellow" TYPE="button" name="btnAdd" value="Onhold" onclick="javascript:saveRequestStatus(3)" disabled ></TD>
		<TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:green' CLASS="buttonStyleCommon" TYPE="button" name="btnAdd" value="Approve" onclick="javascript:saveRequestStatus(4)" disabled></TD>
		<TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:red' CLASS="buttonStyleCommon" TYPE="button" name="btnAdd" value="Reject" onclick="javascript:saveRequestStatus(5)" disabled></TD>
		<TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Complete" onclick="javascript:saveRequestStatus(6)" disabled></TD>-->
							<TD WIDTH="30%">&nbsp;</TD>
							<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()" disabled></TD>
						</logic:equal>

						<logic:equal name="objRequest" property="statusTypeSeqNo"
							value="6">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=objRequest.getReqID()%>')"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify"
								onclick="javascript:saveRequestComponentGroup()" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Submit"
								onclick="javascript:saveRequestStatus(2)" disabled></TD>

							<!--  <TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyleYellow" TYPE="button" name="btnAdd" value="Onhold" onclick="javascript:saveRequestStatus(3)" disabled ></TD>
		<TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:green' CLASS="buttonStyleCommon" TYPE="button" name="btnAdd" value="Approve" onclick="javascript:saveRequestStatus(4)" disabled></TD>
		<TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:red' CLASS="buttonStyleCommon" TYPE="button" name="btnAdd" value="Reject" onclick="javascript:saveRequestStatus(5)" disabled></TD>
		<TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Complete" onclick="javascript:saveRequestStatus(6)" disabled></TD>-->
							<TD WIDTH="30%">&nbsp;</TD>
							<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()" disabled></TD>
						</logic:equal>
			
						<!-- Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 -->
						<logic:equal name="objRequest" property="statusTypeSeqNo" value="7">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=objRequest.getReqID()%>')"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify"
								onclick="javascript:saveRequestComponentGroup()" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Submit"
								onclick="javascript:saveRequestStatus(2)" disabled></TD>
							<TD WIDTH="30%">&nbsp;</TD>
							
							<logic:equal name="ComponentGroupRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
									CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
									onclick="javascript:resetRequest()"></TD>
							</logic:equal>
							<logic:notEqual name="ComponentGroupRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
									CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
									onclick="javascript:resetRequest()" disabled></TD>
							</logic:notEqual>
						</logic:equal>
						<!-- Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 - Ends here -->
					</logic:notEqual>
				<!-- Modified for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151 -->
                       <%-- Modified for CR_106 CR Form Enhancements by JG101007 --%>
					<logic:equal name="ComponentGroupRequestForm" property="roleID"
						value="ADMN">
						<logic:equal name="objRequest" property="statusTypeSeqNo"
							value="2">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=objRequest.getReqID()%>')"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify"
								onclick="javascript:saveRequestComponentGroup()"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Submit"
								onclick="javascript:saveRequestStatus(2)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyleYellow"
								TYPE="button" name="btnAdd" value="Onhold"
								onclick="javascript:saveRequestStatus(3)"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT
								style='background-color:green' CLASS="buttonStyleCommon"
								TYPE="button" name="btnAdd" value="Approve"
								onclick="javascript:saveRequestStatus(4)"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT style='background-color:red'
								CLASS="buttonStyleCommon" TYPE="button" name="btnAdd"
								value="Reject" onclick="javascript:saveRequestStatus(5)"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Complete"
								onclick="javascript:saveRequestStatus(0)"></TD>
							<TD WIDTH="30%">&nbsp;</TD>
							<!-- Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 -->
							<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()"></TD>
						</logic:equal>
						
						<logic:equal name="objRequest" property="statusTypeSeqNo"
							value="3">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=objRequest.getReqID()%>')"></TD>
							<%--CR_106 change to allow Save/Modify by Admin when in OnHold by JG101007 --%>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify"
								onclick="javascript:saveRequestComponentGroup()"></TD>

							<logic:equal name="ComponentGroupRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Submit"
									onclick="javascript:saveRequestStatus(2)"></TD>
							</logic:equal>

							<logic:notEqual name="ComponentGroupRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Submit"
									onclick="javascript:saveRequestStatus(2)" disabled></TD>
							</logic:notEqual>

							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyleYellow"
								TYPE="button" name="btnAdd" value="Onhold"
								onclick="javascript:saveRequestStatus(3)" disabled></TD>
							<%--CR_106 change to allow approval by Admin when in OnHold by JG101007 --%>
							<TD WIDTH="5%" ALIGN="center"><INPUT
								style='background-color:green' CLASS="buttonStyleCommon"
								TYPE="button" name="btnAdd" value="Approve"
								onclick="javascript:saveRequestStatus(4)" ></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT style='background-color:red'
								CLASS="buttonStyleCommon" TYPE="button" name="btnAdd"
								value="Reject" onclick="javascript:saveRequestStatus(5)"
								disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Complete"
								onclick="javascript:saveRequestStatus(6)" disabled></TD>
							<TD WIDTH="30%">&nbsp;</TD>
							<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()"></TD>
						</logic:equal>
						<%--CR_106 change to allow Only Completed by Admin when in Approved by JG101007 --%>
						<logic:equal name="objRequest" property="statusTypeSeqNo"
							value="4">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=objRequest.getReqID()%>')"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify"
								onclick="javascript:saveRequestComponentGroup()"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Submit"
								onclick="javascript:saveRequestStatus(2)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyleYellow"
								TYPE="button" name="btnAdd" value="Onhold"
								onclick="javascript:saveRequestStatus(3)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT
								style='background-color:green' CLASS="buttonStyleCommon"
								TYPE="button" name="btnAdd" value="Approve"
								onclick="javascript:saveRequestStatus(4)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT style='background-color:red'
								CLASS="buttonStyleCommon" TYPE="button" name="btnAdd"
								value="Reject" onclick="javascript:saveRequestStatus(5)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Complete"
								onclick="javascript:saveRequestStatus(6)"></TD>
							<TD WIDTH="30%">&nbsp;</TD>
							<!-- Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 -->
							<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()"></TD>
						</logic:equal>
						<%--CR_106 change to allow changes by Admin when in Rejected by JG101007 --%>
						<logic:equal name="objRequest" property="statusTypeSeqNo"
							value="5">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=objRequest.getReqID()%>')"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify"
								onclick="javascript:saveRequestComponentGroup()" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Submit"
								onclick="javascript:saveRequestStatus(2)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyleYellow"
								TYPE="button" name="btnAdd" value="Onhold"
								onclick="javascript:saveRequestStatus(3)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT
								style='background-color:green' CLASS="buttonStyleCommon"
								TYPE="button" name="btnAdd" value="Approve"
								onclick="javascript:saveRequestStatus(4)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT style='background-color:red'
								CLASS="buttonStyleCommon" TYPE="button" name="btnAdd"
								value="Reject" onclick="javascript:saveRequestStatus(5)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Complete"
								onclick="javascript:saveRequestStatus(0)" disabled></TD>
							<TD WIDTH="30%">&nbsp;</TD>
							<!-- Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 -->
							<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()"></TD>
						</logic:equal>
							<%--CR_106 changes end here --%>
						<logic:equal name="objRequest" property="statusTypeSeqNo"
							value="6">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=objRequest.getReqID()%>')"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify"
								onclick="javascript:saveRequestComponentGroup()" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Submit"
								onclick="javascript:saveRequestStatus(2)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyleYellow"
								TYPE="button" name="btnAdd" value="Onhold"
								onclick="javascript:saveRequestStatus(3)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT
								style='background-color:green' CLASS="buttonStyleCommon"
								TYPE="button" name="btnAdd" value="Approve"
								onclick="javascript:saveRequestStatus(4)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT style='background-color:red'
								CLASS="buttonStyleCommon" TYPE="button" name="btnAdd"
								value="Reject" onclick="javascript:saveRequestStatus(5)"
								disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Complete"
								onclick="javascript:saveRequestStatus(6)" disabled></TD>
							<TD WIDTH="30%">&nbsp;</TD>
							<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()" disabled></TD>
						</logic:equal>
		
						<!-- Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 -->
						<logic:equal name="objRequest" property="statusTypeSeqNo"
							value="7">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=objRequest.getReqID()%>')"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify"
								onclick="javascript:saveRequestComponentGroup()"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Submit"
								onclick="javascript:saveRequestStatus(2)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyleYellow"
								TYPE="button" name="btnAdd" value="Onhold"
								onclick="javascript:saveRequestStatus(3)"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT
								style='background-color:green' CLASS="buttonStyleCommon"
								TYPE="button" name="btnAdd" value="Approve"
								onclick="javascript:saveRequestStatus(4)"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT style='background-color:red'
								CLASS="buttonStyleCommon" TYPE="button" name="btnAdd"
								value="Reject" onclick="javascript:saveRequestStatus(5)"
								disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Complete"
								onclick="javascript:saveRequestStatus(6)" disabled></TD>
							<TD WIDTH="30%">&nbsp;</TD>
							<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()"></TD>
						</logic:equal>
						<!-- Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 - Ends here-->
					</logic:equal>
			</logic:iterate>
		</TR>
		</TABLE>
		<!-- ADDING FOR cR-94 
		<TABLE BORDER="0" WIDTH="100%" ALIGN="CENTER">
		<logic:equal name="ComponentGroupRequestForm" property="roleID"	value="ADMN">
			<logic:notEmpty name="ComponentGroupRequestForm" property="searchList">
			<logic:equal name="searchSize" value="1">
				<logic:notEqual name="statusNo" value="2">
				<BR/>
				<HR/><BR/>
				<TR>
					<TD COLSPAN=9 align=center><A HREF="javascript:fnShowReqId()" style="cursor:default">
						<img src="images/ViewOtherSubmittedCRForms.jpg" width="378" border="0" height="20"></img>
					</A></TD>
				</TR>
				</logic:notEqual>
			</logic:equal>
			<logic:greaterThan name="searchSize" value="1">
				<BR/>
				<HR/><BR/>
				<TR>
					<TD COLSPAN=9 align=center><A HREF="javascript:fnShowReqId()" style="cursor:default">
						<img src="images/ViewOtherSubmittedCRForms.jpg" width="378" border="0" height="20"></img>
					</A></TD>
				</TR>
			</logic:greaterThan>
			</logic:notEmpty>
		</logic:equal>
		<logic:notEqual name="ComponentGroupRequestForm" property="roleID"	value="ADMN">
			<BR/>
			<HR/><BR/>
			<TR>
				<TD COLSPAN=9 ALIGN=CENTER>
					<html:button property="method"	styleClass="buttonStyle" value="Return to Modify CR Form"
						onclick="javascript:fnReturnToModifyCRForm()">
					</html:button>
				</TD>
			</TR>
		</logic:notEqual>
		</TABLE>
		<!-- ENDS HERE CR_94 -->
	<!-- Ends here -->
	<logic:present name="ComponentGroupRequestForm"
		property="reqModelSaved">
		<script>
  enableDisableRequestModel('<bean:write name="ComponentGroupRequestForm" property="reqModelSaved"/>');
</script>
	</logic:present>

	<!-- These hidden fields are maintained for disabled fields-->
	<input type="hidden" name="hdnCompGrpSeqNo">
	<input type="hidden" name="hdnOldCompGrpName">
	<input type="hidden" name="hdnOldCompGrpChacFlag">
	<input type="hidden" name="hdnOldCompGrpValidFlag">
	<input type="hidden" name="hdnNewCompGrpName">
	<input type="hidden" name="hdnNewCompGrpChacFlag">
	<input type="hidden" name="hdnNewCompGrpValidFlag">
	<input type="hidden" name="hdnCompSeqNo">
	<input type="hidden" name="hdnOldCompName">
	<input type="hidden" name="hdnOldCompDefaultFlag">
	<input type="hidden" name="hdnNewCompName">
	<input type="hidden" name="hdnNewCompDefaultFlag">
	<!-- Added for Spec Type Drop down CR_80 -->
	<input type="hidden" name="hdnSpecTypeNo">
	<input type="hidden" name="hdnModelSeqNo">
	<input type="hidden" name="hdnSecSeqNo">
	<input type="hidden" name="hdnSubSecSeqNo">
	<html:hidden property="hdnAdminComments" />
	<html:hidden property="clauseColorFlag"
		name="ComponentGroupRequestForm" />
	<html:hidden property="roleID" />

	<!--Added to take back the user to the same work area-->
	<html:hidden property="tableID" />

	<!--Added for including Save function from alert-->
	<html:hidden property="alertFlag" />
	<!-- Adding for CR_94 -->
	<logic:greaterEqual name="searchSize" value="1">
	<div id="hiddenSearchList" style="display:none">
	<TABLE ALIGN="CENTER">
		<tr>
			<TD CLASS="dashBoardSubHeading">
				<bean:message key="Application.Screen.SubmittedChangeRequests" />
			</TD>
		</tr>
	</TABLE><BR/>
		<TABLE ALIGN="CENTER">
			<logic:iterate id="objSearchList" name="ComponentGroupRequestForm"	property="searchList" 
				type="com.EMD.LSDB.vo.common.RequestVO"	scope="request" indexId="countForSearchList">
				<bean:define id="sizecheck"	value="<%=String.valueOf((countForSearchList.intValue())%7 )%>" />
				<logic:greaterEqual name="countForSearchList" value="0">
					<logic:equal name="sizecheck" value="0">
						<tr>
					</logic:equal>
				</logic:greaterEqual>
				<logic:notEqual name="requestID" value="<%=String.valueOf(objSearchList.getReqID())%>">
					<TD>
						<A HREF="javascript:fnGetRequest('<%=String.valueOf(objSearchList.getReqID())%>')"
								CLASS='linkstyleItemGreen'> <bean:write name="objSearchList" property="reqID" /></A>
						&nbsp;&nbsp;
					</TD>
				</logic:notEqual>				
				<logic:notEqual name="countForSearchList" value="0">
					<logic:equal name="sizecheck" value="6">
						</tr>
					</logic:equal>
				</logic:notEqual>
			</logic:iterate>	
		</TABLE>
		<TABLE ALIGN="CENTER">		
			<tr>			
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align=center>
				<html:button property="addButton" styleClass="buttonStyleClose" value="Close"></html:button>
				</td>
			</tr>
		</TABLE>
	</div>
	</logic:greaterEqual>
	<!-- Ends here -->
</html:form>
