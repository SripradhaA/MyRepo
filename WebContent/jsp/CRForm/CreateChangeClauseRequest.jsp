<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<HEAD>
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>
<SCRIPT LANGUAGE="JavaScript" SRC="js/CreateChangeRequestClause.js"></SCRIPT>
<script language="javascript" src="js/Others/tiny_mce/jquery.tinymce.js"></script>
<!-- Added for CR-91 to remove repeated code -->
<%@ include file="/jsp/common/richTextEditor.jsp" %>
<!-- Added For CR_88 -->

<script>
    function fnlocalSubmit(hiddencomponentGrpArray , hiddencomponentSeqNo,hiddencomponentGrpSeqNo)
    {

    existLength= document.forms[0].componentfrom.options.length;
    for (i=existLength;i<hiddencomponentGrpArray.length;i++)
    {
    additem = new Option();
    additem.value = hiddencomponentSeqNo[i];
    additem.text = hiddencomponentGrpArray[i];
    document.forms[0].componentfrom.options[existLength]=additem;
	document.forms[0].hdncomponentGroupSeqNo.value+=hiddencomponentGrpSeqNo[i]+"~";
    existLength++;
    }

    }

</script>
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

<html:form action="/CreateChangeReqClauseAction" method="post">

	<!-- Application PageName Display starts here-->

	<TABLE WIDTH="100%" BORDER="0" CELLPADDING="0" CELLSPACING="0">
		<TR>
			<TD>&nbsp;</TD>
		</TR>
		<TR>
			<TD WIDTH="60%" CLASS="dashBoardSubHeading" ALIGN="CENTER"><bean:message
				key="Application.Screen.CreateChangeRequest" /></TD>
		</TR>
		<TR>
			<TD>&nbsp;</TD>
		</TR>
	</TABLE>

	<!-- Application PageName Display Ends here-->

	<!-- Validation Message Display Part Starts Here -->

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

	<!-- Validation Message Display Part Ends Here -->

	<!-- Logic Tag Check For Display The Records After Add and Modify Functionality Starts Here -->

	<logic:present name="CreateChangeClauseRequestForm"
		property="requestList" scope="request">
		<bean:size name="CreateChangeClauseRequestForm" id="reqsize"
			property="requestList" />
	</logic:present>
	<!-- Added for CR_94 -->
	<logic:present name="CreateChangeClauseRequestForm" property="searchList">
		<bean:size id="searchSize" name="CreateChangeClauseRequestForm" property="searchList" />
	</logic:present>
	<!-- CR_94 Ends here -->
	<logic:present name="CreateChangeClauseRequestForm"
		property="clauseList" scope="request">
		<bean:size name="CreateChangeClauseRequestForm" id="clsize"
			property="clauseList" />
	</logic:present>

	<logic:present name="CreateChangeClauseRequestForm"
		property="reqClauseList" scope="request">
		<bean:size name="CreateChangeClauseRequestForm" id="reqclasize"
			property="reqClauseList" />
	</logic:present>

	<logic:present name="CreateChangeClauseRequestForm"
		property="messageID" scope="request">

	 <%--Added for CR-121 for server side error message tooltip --%>	
	  <bean:define id="messageID" name="CreateChangeClauseRequestForm" property="messageID"/>
	  <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
      <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">

	</logic:present>

	<logic:present name="CreateChangeClauseRequestForm"
		property="requestList">
		<logic:iterate id="reqDetails" name="CreateChangeClauseRequestForm"
			property="requestList" type="com.EMD.LSDB.vo.common.RequestVO">

			<TABLE ALIGN=center WIDTH="50%" BORDER=0 bgcolor=E6E7E8>

				<TR>
					<TD width=25% ALIGN=left CLASS="greytext"><span CLASS=header nowrap>Request
					Id :</span> <bean:write name="reqDetails" property="reqID" /> <html:hidden
						property="hdnReqID" value="<%=reqDetails.getReqID()%>" /></TD>
					<TD width=25% ALIGN=center CLASS="greytext"><span id="crStatus" CLASS=header
						nowrap>Status :</span> <!-- <font color="green"> --> <bean:define
						id="color" value="#666666" /> <logic:equal name="reqDetails"
						property="statusTypeSeqNo" value="3">
						<bean:define id="color" value="yellow" />
					</logic:equal> <logic:equal name="reqDetails"
						property="statusTypeSeqNo" value="4">
						<bean:define id="color" value="green" />
					</logic:equal> <logic:equal name="reqDetails"
						property="statusTypeSeqNo" value="5">
						<bean:define id="color" value="red" />
					</logic:equal> <font color="<%=color%>"> <bean:write
						name="reqDetails" property="statusTypeDesc" /> </font></TD>

				</TR>

			</TABLE>
			<bean:define id="requestID" name="reqDetails" property="reqID"/>
			<bean:define id="statusNo" name="reqDetails" property="statusTypeSeqNo"/>
		</logic:iterate>
	</logic:present>
	<BR>
	<TABLE width=50% ALIGN=center BORDER=0>
		<TR>
			<TD width=25% CLASS="headerbgcolor" ALIGN="left">Short Description<font
				color=red size=2>*</font></TD>
			<TD width=25% CLASS="formFieldSub" ALIGN="left"><html:textarea
				property="requestDesc" rows="3" cols="60"></html:textarea></TD>
		</TR>
	</TABLE>

	<TABLE WIDTH="60%" BORDER=0 align="center">
		<TR>
			<TD>&nbsp;</TD>
		</TR>
		<TR>
			<TD WIDTH="100%" ALIGN=center><logic:present
				name="CreateChangeClauseRequestForm" property="requestList">
				<logic:iterate id="reqDetails" name="CreateChangeClauseRequestForm"
					property="requestList" type="com.EMD.LSDB.vo.common.RequestVO">
					<logic:present name="CreateChangeClauseRequestForm"
						property="compColourFlag">
						<logic:notEqual name="CreateChangeClauseRequestForm"
							property="compColourFlag" value="Y">
							<A HREF="#"
								onclick="javascript:fnCompGrpCompView('<%=reqDetails.getReqID()%>')"
								CLASS=linkstyleItemNine> Component Group & Component </A>&nbsp;&nbsp;|&nbsp;&nbsp;
			</logic:notEqual>
						<logic:equal name="CreateChangeClauseRequestForm"
							property="compColourFlag" value="Y">
							<A HREF="#"
								onclick="javascript:fnCompGrpCompView('<%=reqDetails.getReqID()%>')"
								CLASS=linkstyleItemGreenNine>Component Group & Component</A>&nbsp;&nbsp;|&nbsp;&nbsp;
			</logic:equal>
					</logic:present>
					<logic:notPresent name="CreateChangeClauseRequestForm"
						property="compColourFlag">
						<A HREF="#"
							onclick="javascript:fnCompGrpCompView('<%=reqDetails.getReqID()%>')"
							CLASS=linkstyleItemNine> Component Group & Component </A>&nbsp;&nbsp;|&nbsp;&nbsp;
		</logic:notPresent>
					<A HREF CLASS=linkstyleItemNoUnderlineNine>Clause</A>&nbsp;&nbsp;
		
	 </logic:iterate>
			</logic:present></TD>
		</TR>
		<TR>
			<TD>&nbsp;</TD>
		</TR>
	</TABLE>

	<logic:present name="CreateChangeClauseRequestForm"
		property="reqModelSaved" scope="request">
		<logic:equal name="CreateChangeClauseRequestForm"
			property="reqModelSaved" value="Y">
			<TABLE ALIGN=center WIDTH="96%" CLASS=crFormTable1>
				<TR>
					<TD CLASS="selectstyle2" align=center>The Pulldown options on this
					selection tool bar can be unlocked for change by clicking on the "<FONT
						COLOR="red">Reset</FONT>" button at the bottom of the screen.</TD>
				</TR>
			</TABLE>
		</logic:equal>
	</logic:present>
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
				styleClass="selectstyle2" name="CreateChangeClauseRequestForm" styleId="specType"
				property="specTypeNo" onchange="javascript:fnLoadModels()">
				<option selected value="-1">---Select---</option>
				<logic:present name="CreateChangeClauseRequestForm" property="specTypeList">
					<html:optionsCollection name="CreateChangeClauseRequestForm"
						property="specTypeList" value="spectypeSeqno" label="spectypename" />
				</logic:present>
			</html:select></TD>
			<!-- Added For CR_80 Specification Drop down - Ends here-->
			
			<TD ALIGN=left class="header"><span CLASS=greytext1 nowrap>Model <font
				color=red size=2>*</font>:</span> <html:select
				name="CreateChangeClauseRequestForm" property="modelSeqNo"
				styleClass="selectstyle2" onchange="javascript:fnfetchSections()">
				<option selected value="-1">---Select---</option>
				<logic:present name="CreateChangeClauseRequestForm"
					property="modelList">
					<html:optionsCollection name="CreateChangeClauseRequestForm"
						property="modelList" value="modelSeqNo" label="modelName" />
				</logic:present>
			</html:select></TD>

			<TD ALIGN=left class="header"><span CLASS=greytext1 nowrap>Section <font
				color=red size=2>*</font>:</span> <html:select
				name="CreateChangeClauseRequestForm" property="sectionSeqNo"
				styleClass="selectstyle2" onchange="javascript:fnfetchSubSections()">
				<option selected value="-1">---Select---</option>
				<logic:present name="CreateChangeClauseRequestForm"
					property="sectionList">
					<html:optionsCollection name="CreateChangeClauseRequestForm"
						property="sectionList" value="sectionSeqNo" label="sectionDisplay" />
				</logic:present>
			</html:select></TD>
			<TD ALIGN=center><span CLASS=greytext1 nowrap>SubSection <font
				color=red size=2>*</font>:</span> <html:select
				name="CreateChangeClauseRequestForm" property="subSectionSeqNo"
				styleClass="selectstyle2">
				<option selected value="-1">---Select---</option>
				<logic:present name="CreateChangeClauseRequestForm"
					property="subSectionList">
					<html:optionsCollection name="CreateChangeClauseRequestForm"
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
						name="CreateChangeClauseRequestForm" property="applyModelFlag" />This
					change effects both ACe/M-2 Models </TD>
				</TR>
			</TABLE>
			</TD>
			<TD align=left width="7%">&nbsp;</td>
			<TD CLASS="dashBoardSubHeading" ALIGN="left">&nbsp;</TD>
		</TR>
	</TABLE>

	<TABLE ALIGN=center WIDTH="70%" BORDER=0 cellspacing=0>
		<logic:notPresent name="CreateChangeClauseRequestForm"
			property="compGrpNameVO">
			<TR>
				<BR>
				<TD>
				<TABLE BORDER="0" WIDTH="80%" ALIGN="center" bgcolor="E6E7E8">
					<TR>
						<TD width="5%">&nbsp;</TD>
						<TD align="left" width="20%"><span CLASS=greytext1>Component Group</span>
						</TD>
						<TD width="5%" align="center"><span CLASS=greytext1>:</span></TD>
						<TD width="40%">&nbsp;</TD>
					</TR>
					<TR>
						<TD width="5%">&nbsp;</TD>
						<TD align="left" width="20%"><span CLASS=greytext1>Component</span>
						</TD>
						<TD width="5%" align="center"><span CLASS=greytext1>:</span></TD>
						<TD width="40%"><!-- <html:hidden property="hdnCompSeqNo"/>
						<html:hidden property="hdnCompChnTypeSeqNo"/> --> &nbsp;</TD>
					</TR>
				</TABLE>
				</TD>
			</TR>
		</logic:notPresent>

		<logic:present name="CreateChangeClauseRequestForm"
			property="compGrpNameVO">
			<TR>
				<BR>
				<bean:define id="compVO" name="CreateChangeClauseRequestForm"
					property="compGrpNameVO"
					type="com.EMD.LSDB.vo.common.RequestModelVO" />
				<TD>
				<TABLE BORDER="0" WIDTH="80%" ALIGN="center" bgcolor="E6E7E8">
					<TR>
						<TD width="5%">&nbsp;</TD>
						<TD align="left" width="20%"><span CLASS=greytext1>Component Group</span>
						</TD>
						<TD width="5%" align="center"><span CLASS=greytext1>:</span></TD>
						<TD width="40%"><logic:notEmpty name="compVO"
							property="requestCompGrpVO">
							<%=(compVO.getRequestCompGrpVO().getNewCompGrpName()==null)? "&nbsp;":compVO.getRequestCompGrpVO().getNewCompGrpName()%>
							<input type="hidden" name="hdnCompGrpSeqNo"
								value="<%=compVO.getRequestCompGrpVO().getOldCompGrpSeqNo()%>" />
							<input type="hidden" name="hdnCompGrpChnTypeSeqNo"
								value="<%=compVO.getRequestCompGrpVO().getChangeTypeSeqNo()%>" />
						</logic:notEmpty> <logic:empty name="compVO"
							property="requestCompGrpVO">
							<html:hidden property="hdnCompGrpChnTypeSeqNo" />
							<html:hidden property="hdnCompGrpSeqNo" />
						</logic:empty></TD>
					</TR>
					<TR>
						<TD width="5%">&nbsp;</TD>
						<TD align="left" width="20%"><span CLASS=greytext1>Component</span>
						</TD>
						<TD width="5%" align="center"><span CLASS=greytext1>:</span></TD>
						<TD width="40%"><logic:notEmpty name="compVO"
							property="requestCompVO">
							<%=(compVO.getRequestCompVO().getNewCompName()==null) ? "&nbsp;":compVO.getRequestCompVO().getNewCompName()%>
							<input type="hidden" name="hdnCompSeqNo"
								value="<%=compVO.getRequestCompVO().getOldCompSeqNo()%>" />
							<input type="hidden" name="hdnCompChnTypeSeqNo"
								value="<%=compVO.getRequestCompVO().getChangeTypeSeqNo()%>" />
						</logic:notEmpty> <logic:empty name="compVO"
							property="requestCompVO">
							<html:hidden property="hdnCompSeqNo" />
							<html:hidden property="hdnCompChnTypeSeqNo" />
						</logic:empty></TD>
					</TR>
				</TABLE>
				</TD>
			</TR>
		</logic:present>

	</TABLE>

	<TABLE BORDER="0" WIDTH="70%" ALIGN="center">
		<TR>
			<BR>
			<TD CLASS="dashBoardSubHeading" ALIGN="center">Clause Change Request
			Form</TD>
		</TR>
	</TABLE>

	<Table align=center>
		<tr>
			<br>
			<TD ALIGN=center><INPUT CLASS="buttonStyle" TYPE="button"
				name="btnAdd" value="Refresh/Restore"
				onclick="javascript:fnRefresh()"></TD>
		</tr>
	</TABLE>

	<!-- Added for Keeping Change Type functions separate CR-Form Enhancements III -->
	<jsp:include page="ClauseChangeType.jsp" />

	<BR>
	<logic:greaterThan name="clsize" value="0">
		<TABLE BORDER="0" WIDTH="85%" ALIGN="center" CLASS="miniDashOut1">
			<TR>
				<TD colspan=2 ALIGN="CENTER" CLASS=headerbgcolor>Change From</TD>
			</TR>
			<TR>
				<TD ALIGN=left CLASS="headerbgcolor" nowrap>Select Clause&nbsp;</TD>
				<TD>&nbsp;<A HREF="javascript:fnLoadFromClause()" name="linkClause"
					id="searchicon"><IMG SRC="images/search.gif" alt="Search"
					BORDER="0"></A></TD>
			</TR>
			<TR>
				<TD width=10% CLASS="headerbgcolor" ALIGN="left" nowrap>Clause
				Number&nbsp;</TD>
				<TD width=90% CLASS="formFieldSub"><html:text styleClass="TEXTBOX2"
					size="20" property="clauseNo" maxlength="60" readonly="true" /></TD>
			</TR>

			<!-- Added for CR_80 CR Form Enhancements III on 23-Nov-09 by RR68151 -->
			<TR>
				<TD width=10% CLASS="headerbgcolor" ALIGN="left" nowrap>Clause
					Versions&nbsp;<A HREF="javascript:fnLoadClauseVersion('N')"
						name="linkClause" id="fromClaVersion"><IMG SRC="images/search.gif"
						alt="Search" BORDER="0"></A></TD>
				<TD width=90% CLASS="formFieldSub">
				<logic:notEqual name="CreateChangeClauseRequestForm" 
					property="changeTypeSeqNO" value="10">
						<logic:equal name="CreateChangeClauseRequestForm" 
							property="versionNo" value="0"><html:text styleClass="TEXTBOX2"
							size="20" property="versionNo" value="" readonly="true" />
						</logic:equal>
						<logic:greaterThan name="CreateChangeClauseRequestForm" 
							property="versionNo" value="0"><html:text styleClass="TEXTBOX2"
							size="20" property="versionNo" readonly="true" />
						</logic:greaterThan>
				</logic:notEqual>
				<logic:equal name="CreateChangeClauseRequestForm" 
				property="changeTypeSeqNO" value="10"><html:text styleClass="TEXTBOX2"
					size="20" property="versionNo" value="ALL" readonly="true" />
				</logic:equal><A HREF="javascript:deleteFrmClaVersion()" 
					id="frmClaVersionDel"><IMG SRC="images/delete1.gif" alt="Delete" BORDER="0"></A>
				</TD>
			</TR>
			<!-- Added for CR_80 CR Form Enhancements III on 23-Nov-09 by RR68151 - Ends here -->
			
			<logic:iterate id="check" name="CreateChangeClauseRequestForm"
				property="clauseList" type="com.EMD.LSDB.vo.common.ClauseVO"
				scope="request">
				<logic:empty name="check" property="clauseDescForTextArea">
					<TR>
						<TD WIDTH="22%" CLASS="headerbgcolor">Clause Description<font
							color="red">*</font></TD>
						<TD COLSPAN="3">

						<DIV id="fromClause" align="left"
							STYLE="float:left;height:60;width:435;overflow: auto ;"></DIV>

						</TD>

					</TR>
				</logic:empty>
				<logic:notEmpty name="check" property="clauseDescForTextArea">
					<TR>
						<TD WIDTH="22%" CLASS="headerbgcolor">Clause Description<font
							color="red">*</font></TD>
						<TD COLSPAN="3">

						<DIV id="fromClause" align="left"
							STYLE="float:left;height:60;width:435;overflow: auto ; "><!-- Clause desc display -->

						<bean:define name="check" property="clauseDesc" id="clauseDesc" />
							<!-- CR-128 - Updated for Pdf issue -->
								<%String strClauseDesc = String.valueOf(clauseDesc);
								if(strClauseDesc.startsWith("<p>")){%>
									<%=(String.valueOf(clauseDesc))%>
								<%}else{ %>	
									<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>							
								<%}%>
							<!-- CR-128 - Updated for Pdf issue Ends Here-->
							<table width="100%" BORDER="1">
							<logic:notEmpty name="check" property="tableArrayData1">
				 &nbsp;
				 <logic:iterate id="outter" name="check" property="tableArrayData1"
									indexId="counter">
									<!-- Added  For CR_93 -->
											<bean:define name="check" property="tableDataColSize" id="tableDataColSize" />
									<bean:define id="row" name="counter" />
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
						</DIV>
						<!-- Commented for CR_80 CR Form Enhancements - III
						<input type="hidden" name="selectedVersion"
							value="<%=check.getVersionNo()%>" /> --> <input type="hidden"
							name="selectedClaSeqNo" value="<%=check.getClauseSeqNo()%>" /></TD>

					</TR>
				</logic:notEmpty>
				<logic:notEmpty name="check" property="compName">
					<TR>
						<TD CLASS="headerbgcolor" ALIGN="left" nowrap>Components Tied To
						Clause</TD>
						<TD>
						<Div id="components" align="left"
							STYLE="float:left;height:60;width:435;overflow: auto ;"><logic:iterate
							id="com" name="check" property="compName">
							<bean:write name="com" />
							<br>
						</logic:iterate></Div>
						</TD>
					</TR>
				</logic:notEmpty>

				<logic:empty name="check" property="compName">
					<TR>
						<TD CLASS="headerbgcolor" ALIGN="left" nowrap>Components Tied To
						Clause</TD>
						<TD>
						<Div id="components" align="left"
							STYLE="float:left;height:60;width:435;overflow: auto ;"></Div>
						</TD>
					</TR>
				</logic:empty>
				<!-- TableData  Display Part Starts Here -->



				<TR>
					<TD colspan=2 WIDTH="88%">
					<TABLE>
						<TR>
							<TD ALIGN="center">
							<FIELDSET CLASS="fieldset5"><LEGEND ALIGN="left"
								CLASS="formSubHeading">Engineering Data <font color="red">*</font></LEGEND>
							<TABLE BORDER="0" WIDTH="100%">
								<TR>
									<TD WIDTH="30%" CLASS="headerbgcolor">Reference EDL Number(s)</TD>

									<TD COLSPAN="3"><logic:notEmpty name="check"
										property="refEDLNo">

										<html:text property="refEDLNo" styleClass="TEXTBOX2" size="20"
											readonly="true" maxlength="20"
											value='<%=check.getRefEDLNo()[0]%>'></html:text>&nbsp;&nbsp;
								<html:text property="refEDLNo" readonly="true"
											styleClass="TEXTBOX2" size="20" maxlength="20"
											value='<%=check.getRefEDLNo()[1]%>'></html:text>&nbsp;&nbsp;
								<html:text property="refEDLNo" readonly="true"
											styleClass="TEXTBOX2" size="20" maxlength="20"
											value='<%=check.getRefEDLNo()[2]%>' />

									</logic:notEmpty> <logic:empty name="check" property="refEDLNo">
										<html:text property="refEDLNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" value="" readonly="true"></html:text>&nbsp;&nbsp;
								<html:text property="refEDLNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" value="" readonly="true"></html:text>&nbsp;&nbsp;
								<html:text property="refEDLNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" value="" readonly="true"></html:text> -->
								</logic:empty></TD>

								</TR>
								<TR>
									<TD CLASS="headerbgcolor">New EDL Number(s)</TD>
									<TD COLSPAN=3><logic:notEmpty name="check" property="edlNo">

										<html:text property="edlNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" readonly="true"
											value='<%=check.getEdlNo()[0]%>'></html:text>&nbsp;&nbsp;
												<html:text property="edlNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" readonly="true"
											value='<%=check.getEdlNo()[1]%>'></html:text>&nbsp;&nbsp;
												<html:text property="edlNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" readonly="true"
											value='<%=check.getEdlNo()[2]%>' />
									</logic:notEmpty> <logic:empty name="check" property="edlNo">
										<html:text property="edlNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" readonly="true" value=""></html:text>
												&nbsp;&nbsp;
												<html:text property="edlNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" readonly="true" value=""></html:text>
												&nbsp;&nbsp;
												<html:text property="edlNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" readonly="true" value=""></html:text>
									</logic:empty></TD>
								</TR>
								<TR><!-- CR 87 -->
									<TD CLASS="headerbgcolor"><bean:message	key="screen.partOf" /></TD>
									<TD COLSPAN="5"><logic:notEmpty name="check"
										property="partOfSeqNo">
										<logic:notEmpty name="check" property="partOfCode">
											<html:text indexed="1" property="toPartOfCode"
												value='<%=(check.getPartOfCode()[0])=="0" ? "":check.getPartOfCode()[0] %>'
												styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;&nbsp;
									

									<html:text property="toPartOfCode"
												value='<%=(check.getPartOfCode()[1])=="0" ? "":check.getPartOfCode()[1] %>'
												styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;&nbsp;
								
									<html:text property="toPartOfCode"
												value='<%=(check.getPartOfCode()[2])=="0" ? "":check.getPartOfCode()[2] %>'
												styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;&nbsp;
									
									<html:text property="toPartOfCode"
												value='<%=(check.getPartOfCode()[3])=="0" ? "":check.getPartOfCode()[3] %>'
												styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;&nbsp;
									

									</logic:notEmpty>
									</logic:notEmpty> <logic:empty name="check"
										property="partOfCode">
										<html:text indexed="1" property="toPartOfCode" value=""
											styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;&nbsp;
									

									<html:text property="toPartOfCode" value=""
											styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;&nbsp;
									

									<html:text property="toPartOfCode" value=""
											styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;&nbsp;
									

									<html:text property="toPartOfCode" value=""
											styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;&nbsp;
									
									</logic:empty></TD>
								</TR>
								<TR>
									<TD CLASS="headerbgcolor" ALIGN="left">DWO Number</TD>
									<TD COLSPAN="5"><logic:equal name="check" property="dwONumber"
										value="0">
										<html:text property="dwONumber" styleClass="TEXTBOX2"
											size="20" maxlength="20" value="" readonly="true" />
									</logic:equal> <logic:greaterThan name="check"
										property="dwONumber" value="0">
										<html:text name="check" property="dwONumber"
											styleClass="TEXTBOX2" size="20" maxlength="20"
											readonly="true" />
									</logic:greaterThan></TD>
								</TR>
								<TR>
									<TD CLASS="headerbgcolor" ALIGN="left">Part Number</TD>
									<TD COLSPAN="5"><logic:equal name="check" property="partNumber"
										value="0">
										<html:text property="partNumber" styleClass="TEXTBOX2"
											size="20" maxlength="8" value="" readonly="true" />
									</logic:equal> <logic:greaterThan name="check"
										property="partNumber" value="0">
										<html:text property="partNumber" name="check"
											styleClass="TEXTBOX2" size="20" maxlength="8" readonly="true" />
									</logic:greaterThan></TD>
								</TR>

								<!-- Maxlength of Price book number is changed from 20 to 4.Modified on 29-09-08 by ps57222 -->

								<TR>
									<TD CLASS="headerbgcolor" ALIGN="left">Price Book Number</TD>
									<TD COLSPAN="5"><logic:greaterThan name="check"
										property="priceBookNumber" value="0">
										<html:text property="priceBookNumber" name="check"
											styleClass="TEXTBOX2" size="20" maxlength="4" readonly="true"></html:text>
									</logic:greaterThan> <logic:equal name="check"
										property="priceBookNumber" value="0">
										<html:text property="priceBookNumber" styleClass="TEXTBOX2"
											size="20" maxlength="4" value="" readonly="true"></html:text>
									</logic:equal></TD>
								</TR>


								<TR>
									<TD CLASS="headerbgcolor">Standard Equipment</TD>
									<TD COLSPAN="3"><html:select property="standEquipmentSeqNo"
										styleClass="selectstyle2" disabled="true">
										<option value="-1">--Select--</option>
										<logic:present name="CreateChangeClauseRequestForm"
											property="stdEquipmentList">
											<logic:iterate id="stdEquip"
												name="CreateChangeClauseRequestForm"
												property="stdEquipmentList" scope="request"
												type="com.EMD.LSDB.vo.common.StandardEquipVO">

												<logic:equal
													value="<%=String.valueOf(check.getStandardEquipmentSeqNo())%>"
													name="stdEquip" property="standardEquipmentSeqNo">
													<option value="<%=stdEquip.getStandardEquipmentSeqNo()%>"
														selected><bean:write name="stdEquip"
														property="standardEquipmentDesc" /></option>
												</logic:equal>

												<logic:notEqual
													value="<%=String.valueOf(check.getStandardEquipmentSeqNo())%>"
													name="stdEquip" property="standardEquipmentSeqNo">
													<option value="<%=stdEquip.getStandardEquipmentSeqNo()%>">
													<bean:write name="stdEquip"
														property="standardEquipmentDesc" /></option>
												</logic:notEqual>

											</logic:iterate>
										</logic:present>
									</html:select></TD>
								</TR>
								<TR>
									<TD CLASS="headerbgcolor">Comments</TD>
									<TD><html:textarea name="check" property="comments" rows="4"
										cols="60" readonly="true"></html:textarea></TD>
								</TR>
								</logic:iterate>

							</TABLE>
							</FIELDSET>
							</TD>
						</TR>
					</TABLE>
					</TD>
				</TR>
		</TABLE>
	</logic:greaterThan>

	<!-- Empty Section-->
	<logic:notPresent name="CreateChangeClauseRequestForm"
		property="clauseList" scope="request">
		<BR>
		<jsp:include page="FromClauseEmpty.jsp" />
	</logic:notPresent>

	<!--Change From Clause Ends -->

	<BR>

	<logic:notPresent name="CreateChangeClauseRequestForm"
		property="reqClauseList" scope="request">
		<jsp:include page="ToClauseEmpty.jsp" />
	</logic:notPresent>

	<logic:greaterThan name="CreateChangeClauseRequestForm" 
	property="changeTypeSeqNO" value="9">
		<logic:present name="CreateChangeClauseRequestForm"
		property="reqClauseList" scope="request">
			<jsp:include page="ToClauseEmpty.jsp" />
		</logic:present>
	</logic:greaterThan>

	<logic:greaterThan name="reqclasize" value="0">
	<logic:lessEqual name="CreateChangeClauseRequestForm" 
	property="changeTypeSeqNO" value="9">
		<TABLE BORDER="0" WIDTH="85%" ALIGN="center" CLASS="miniDashOut1">
			<TR>
				<TD colspan=2 ALIGN="CENTER" CLASS=headerbgcolor>Change To / Create
				</TD>
			</TR>
			<logic:iterate id="toCheck" name="CreateChangeClauseRequestForm"
				property="reqClauseList" type="com.EMD.LSDB.vo.common.ReqClauseVO">
				<logic:empty name="toCheck" property="toParentClauseNo">
					<TR>
						<TD width=10% CLASS="headerbgcolor" ALIGN="left" nowrap>Parent
						Clause &nbsp;<A HREF="javascript:fnLoadParentClause()"
							name="linkClause" id="toParentClaNo"><IMG SRC="images/search.gif"
							alt="Search" BORDER="0"></A></TD>
						<TD width=90% ALIGN=left><html:text
							styleClass="COMMONTEXTBOXHEIGHT1" property="toParentClauseNo"
							size="20" maxlength="50" readonly="true" />&nbsp; 
						<!-- Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 -->
						<A HREF="javascript:deletetoParentClause()" id="toParentClaNodel"><IMG
									SRC="images/delete1.gif" alt="Delete" BORDER="0"></A><BR>
						<html:hidden property="toParentClaDesc" /> <html:hidden
							property="parentClauseSeqNo" />
						<DIV id="parentclause" align="left"
							STYLE="float:left;height:60;width:435;overflow: auto ;"></DIV>

						</TD>
					</TR>
				</logic:empty>

				<logic:notEmpty name="toCheck" property="toParentClauseNo">
					<TR>
						<TD width=10% CLASS="headerbgcolor" ALIGN="left" nowrap>Parent
						Clause &nbsp;<A HREF="javascript:fnLoadParentClause()"
							name="linkClause" id="toParentClaNo"><IMG SRC="images/search.gif"
							alt="Search" BORDER="0"></A></TD>
						<TD width=90% ALIGN=left><html:text
							styleClass="COMMONTEXTBOXHEIGHT1" name="toCheck"
							property="toParentClauseNo" size="20" maxlength="50"
							readonly="true" />&nbsp; 
						<!-- Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 -->
						<A HREF="javascript:deletetoParentClause()" id="toParentClaNodel"><IMG
									SRC="images/delete1.gif" alt="Delete" BORDER="0"></A><BR>
						<html:hidden name="toCheck" property="toParentClaDesc" /> <html:hidden
							property="parentClauseSeqNo" />
						<DIV id="parentclause" align="left"
							STYLE="float:left;height:60;width:435;overflow: auto ;"><bean:define
							name="toCheck" property="toParentClaDesc" id="toParentclauseDesc" />
							<!-- CR-128 - Updated for Pdf issue -->
								<%String strClauseDesc =  String.valueOf(toParentclauseDesc);
								if(strClauseDesc.startsWith("<p>")){%>
									<%=(String.valueOf(toParentclauseDesc))%>
								<%}else{ %>	
									<%=(String.valueOf(toParentclauseDesc)).replaceAll("\n","<br>")%>							
								<%}%>
							<!-- CR-128 - Updated for Pdf issue Ends Here-->	
						</DIV>

						</TD>
					</TR>
				</logic:notEmpty>

				<logic:empty name="toCheck" property="toClaVerClauseNo">

					<TR>
						<TD width=10% CLASS="headerbgcolor" ALIGN="left" nowrap>Clause
						Versions&nbsp;<A HREF="javascript:fnLoadClauseVersion('Y')"
							name="linkClause" id="clauseVersion"><IMG SRC="images/search.gif"
							alt="Search" BORDER="0"></A></TD>
						<TD width=90% ALIGN=left valign=middle><html:text
							styleClass="COMMONTEXTBOXHEIGHT1" property="toClaVerClauseNo"
							size="20" maxlength="50" readonly="true" /> &nbsp; 
						<!-- Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 -->
						<A HREF="javascript:deleteClaVersion()" id="clauseVersiondel"><IMG
									SRC="images/delete1.gif" alt="Delete" BORDER="0"></A><BR>
						<html:hidden property="toClaVerDesc" />
						<DIV id="claVersion" align="left"
							STYLE="float:left;height:60;width:435;overflow: auto ;"></DIV>
						</TD>
					</TR>
				</logic:empty>

				<logic:notEmpty name="toCheck" property="toClaVerClauseNo">

					<TR>
						<TD width=10% CLASS="headerbgcolor" ALIGN="left" nowrap>Clause
						Versions&nbsp;<A HREF="javascript:fnLoadClauseVersion('Y')"
							name="linkClause" id="clauseVersion"><IMG SRC="images/search.gif"
							alt="Search" BORDER="0"></A></TD>
						<TD width=90% ALIGN=left valign=middle><html:text
							styleClass="COMMONTEXTBOXHEIGHT1" name="toCheck"
							property="toClaVerClauseNo" size="20" maxlength="50"
							readonly="true" /> &nbsp; 
						<!-- Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 -->
						<A HREF="javascript:deleteClaVersion()" id="clauseVersiondel"><IMG
									SRC="images/delete1.gif" alt="Delete" BORDER="0"></A><BR>
						<html:hidden name="toCheck" property="toClaVerDesc" />
						<DIV id="claVersion" align="left"
							STYLE="float:left;height:60;width:435;overflow: auto ;"><bean:define
							name="toCheck" property="toClaVerDesc" id="toClauseVerDesc" /> 
							<!-- CR-128 - Updated for Pdf issue -->
								<%String strClauseDesc =  String.valueOf(toClauseVerDesc);
								if(strClauseDesc.startsWith("<p>")){%>
									<%=(String.valueOf(toClauseVerDesc))%>
								<%}else{ %>	
									<%=(String.valueOf(toClauseVerDesc)).replaceAll("\n","<br>")%>							
								<%}%>
							<!-- CR-128 - Updated for Pdf issue Ends Here-->	
						</DIV>
						</TD>
					</TR>
				</logic:notEmpty>

				<logic:empty name="toCheck" property="toClauseNo">
					<TR>
						<TD width=10% CLASS="headerbgcolor" ALIGN="left" nowrap>New Clause
						Number&nbsp;</TD>
						<TD width=90% CLASS="formFieldSub"><html:text
							styleClass="COMMONTEXTBOXHEIGHT1" property="toClauseNo" size="20"
							maxlength="20" /></TD>
					</TR>
				</logic:empty>
				<logic:notEmpty name="toCheck" property="toClauseNo">				
					<TR>
						<TD width=10% CLASS="headerbgcolor" ALIGN="left" nowrap>New Clause
						Number&nbsp;</TD>
						<TD width=90% CLASS="formFieldSub"><html:text
							styleClass="COMMONTEXTBOXHEIGHT1" name="toCheck"
							property="toClauseNo" size="20" maxlength="20" /></TD>
					</TR>
				</logic:notEmpty>

				<logic:empty name="toCheck" property="toClauseDesc">
					<TR>
						<TD width=10% CLASS="headerbgcolor" ALIGN="left" nowrap>New Clause
						Description&nbsp;</TD>
						<TD width=90% CLASS="formFieldSub">
							<html:textarea rows="15" styleId="clauseDesc_id" styleClass="CLAUSEDESCTEXTAREA"
								cols="92" property="toClauseDesc"/>	
						</TD>
					</TR>
				</logic:empty>

				<logic:notEmpty name="toCheck" property="toClauseDesc">
						<TR>
							<TD width=10% CLASS="headerbgcolor" ALIGN="left" nowrap>New Clause
							Description&nbsp;</TD>
							<TD width=90% CLASS="formFieldSub">
								<html:textarea rows="15" styleId="clauseDesc_id" styleClass="CLAUSEDESCTEXTAREA"
									cols="92" name="toCheck" property="toClauseDesc" maxlength="2000" />
								<%--Added for CR-129 for loading clauses in TinyMCE Issue  --%>
								<textarea name="hdnclauseDescForTextArea" id="hdnclauseDescForTextArea"
			   					style="display:none;"><bean:write name="toCheck" property="toClauseDesc"/></textarea>
								<%--Added for CR-129 for loading clauses in TinyMCE Issue  --%>
							</TD>
						</TR>
				</logic:notEmpty>

				<logic:empty name="toCheck" property="tableData">
					<TR>
						<TD WIDTH="10%" CLASS="headerbgcolor">Table Data <br>
						<br>
						<DIV id="showmainlink" style="visibility:visible"><A
							CLASS='linkstyleItem' HREF="javascript:addTable()">Add Table</A>&nbsp;</DIV>
						<DIV id="showsublink" style="visibility:hidden"><A
							CLASS=linkstyle1 HREF="javascript:addRow(this)">Add Row</A>&nbsp;|&nbsp;<A
							CLASS=linkstyle1 HREF="javascript:removeRow()">Delete Row</A><br>
						<A CLASS='linkstyle1' HREF="javascript:deleteTable()">Delete Table</A>&nbsp;</DIV>
						</TD>
						<TD WIDTH="75%" COLSPAN=7>
						<DIV ID="showgrid"></DIV>
						</TD>
					</TR>
				</logic:empty>

				<logic:notEmpty name="toCheck" property="tableData">
					<TR>

						<TD WIDTH="10%" CLASS="headerbgcolor">Table Data <br>
						<br>
						<DIV id="showmainlink" style="visibility:hidden"><A
							CLASS='linkstyleItem' HREF="javascript:addTable()">Add Table</A>&nbsp;</DIV>
						<DIV id="showsublink" style="visibility:visible"><A
							CLASS=linkstyle1 HREF="javascript:addRow(this)">Add Row</A>&nbsp;|&nbsp;<A
							CLASS=linkstyle1 HREF="javascript:removeRow()">Delete Row</A><br>
						<A CLASS='linkstyle1' HREF="javascript:deleteTable()">Delete Table</A>&nbsp;</DIV>

						</TD>

						<TD>
						<DIV ID="showgrid">
						<Table id="tblGrid">

							<logic:notEmpty name="toCheck" property="tableData">
								<logic:iterate id="data" name="toCheck" property="tableData"
									indexId="counter">
									<TR>
										<logic:iterate id="data1" name="data" indexId="innerCounter">
											<bean:define id="innerSize" name="innerCounter" />

											<TD><bean:define id="size" name="counter" /> <logic:equal
												name="size" value="0">
												<!--This outer logic:equal check is for display the first row with bold font -->
												<logic:lessThan name="innerSize" value="1">
													<!--This Inner logic:lessthan check is for display the first row first text box -->
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;
										<input type=text
														name="clauseTableDataArray<%=innerCounter.intValue()+1%>"
														class="COMMONTEXTBOXTABLE" size="13" maxlength="100"
														value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>" />
												</logic:lessThan>

												<logic:greaterThan name="innerSize" value="0">
													<!-- This check is for display the rest of first row four text boxes -->
										&nbsp;&nbsp;<input type=text
														name="clauseTableDataArray<%=innerCounter.intValue()+1%>"
														class="COMMONTEXTBOXTABLE" size="13" maxlength="100"
														value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>" />
												</logic:greaterThan>
											</logic:equal> <logic:greaterThan name="size" value="0">
												<!--This outer logic:greaterThan check is for display the second row datas -->
												<logic:equal name="innerSize" value="0">
													<!--This Inner logic:equal check is for display the Second row first text box with radio buttons -->
											&nbsp;&nbsp;<html:radio property="deleterow"
														styleClass="radcheck" value="" />
											 &nbsp;<input type=text
														name="clauseTableDataArray<%=innerCounter.intValue()+1%>"
														class="COMMONTEXTBOX" size="15" maxlength="100"
														value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>" />
												</logic:equal>
												<!--This Inner logic:notEqual check is for display the Second row other text box without radio buttons -->
												<logic:notEqual name="innerSize" value="0">
											&nbsp;&nbsp;
											 <input type=text
														name="clauseTableDataArray<%=innerCounter.intValue()+1%>"
														class="COMMONTEXTBOX" size="15" maxlength="100"
														value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>" />
												</logic:notEqual>

											</logic:greaterThan></TD>

										</logic:iterate>
									</TR>
								</logic:iterate>
							</logic:notEmpty>
						</Table>
						</DIV>
						</TD>
					</TR>
				</logic:notEmpty>

				<logic:empty name="toCheck" property="claComp">
					<TR>
						<TD CLASS="headerbgcolor" ALIGN="left" nowrap>Components Tied To
						Clause</TD>
						<TD COLSPAN="2">
						<TABLE>
							<TR>
								<TD WIDTH="10%"><SELECT name="componentfrom" MULTIPLE
									CLASS="selectstyle2">
								</SELECT> <html:hidden property="toComponentSeqNo" /></TD>
								<TD width="1%"><A HREF="javascript:AddComponent()" id="toComps"><IMG
									SRC="images/search.gif" alt="Search" BORDER="0"></A></TD>
								<TD><A HREF="javascript:deleteComponent()" id="toCompsdel"><IMG
									SRC="images/delete1.gif" alt="Delete" BORDER="0"></A></TD>
							</TR>
						</TABLE>
						</TD>
					</TR>
				</logic:empty>

				<logic:notEmpty name="toCheck" property="claComp">
					<TR>
						<TD CLASS="headerbgcolor" ALIGN="left" nowrap>Components Tied To
						Clause</TD>
						<TD COLSPAN="2">
						<TABLE>
							<TR>
								<TD WIDTH="10%"><SELECT name="componentfrom" MULTIPLE
									CLASS="selectstyle2">
									<logic:present name="toCheck" property="componentSeqNo">
										<bean:define name="toCheck" property="componentSeqNo"
											id="compseqno" />
									</logic:present>

									<logic:present name="toCheck" property="claComp">
										<logic:iterate id="com" name="toCheck" property="claComp"
											indexId="compCount">
											<bean:define name="compCount" id="cmp" />
											<option value=""><bean:write name="com" /></option>
										</logic:iterate>
									</logic:present>


								</SELECT> <html:hidden name="toCheck"
									property="toComponentSeqNo" /></TD>
								<TD width="1%"><A HREF="javascript:AddComponent()" id="toComps"><IMG
									SRC="images/search.gif" alt="Search" BORDER="0"></A></TD>
								<TD><A HREF="javascript:deleteComponent()" id="toCompsdel"><IMG
									SRC="images/delete1.gif" alt="Delete" BORDER="0"></A></TD>
							</TR>
						</TABLE>
						</TD>
					</TR>
				</logic:notEmpty>
				<TR>
					<TD colspan=2 WIDTH="88%">
					<FIELDSET CLASS="fieldset8"><LEGEND ALIGN="left"
						CLASS="formSubHeading">Engineering Data</LEGEND>
					<TABLE WIDTH="100%" BORDER="0" ALIGN=center>

						<TR>
							<TD WIDTH="28%" ALIGN="left" CLASS="headerbgcolor">Reference EDL
							Number(s)</TD>
							<TD CLASS="formFieldSub"><logic:empty name="toCheck"
								property="toRefEDLNo">
								<html:text property="toRefEDLNo" styleClass="TEXTBOX2" size="20"
									maxlength="20" value="">
								</html:text>&nbsp;&nbsp;
						<html:text property="toRefEDLNo" styleClass="TEXTBOX2" size="20"
									maxlength="20" value=""></html:text>&nbsp;&nbsp;
                        <html:text property="toRefEDLNo"
									styleClass="TEXTBOX2" size="20" maxlength="20" value=""></html:text>
							</logic:empty> <logic:notEmpty name="toCheck"
								property="toRefEDLNo">
								<html:text property="toRefEDLNo" styleClass="TEXTBOX2" size="20"
									maxlength="20" value='<%=toCheck.getToRefEDLNo()[0]%>'></html:text>&nbsp;&nbsp;
						<html:text property="toRefEDLNo" styleClass="TEXTBOX2" size="20"
									maxlength="20" value='<%=toCheck.getToRefEDLNo()[1]%>'></html:text>&nbsp;&nbsp;
						<html:text property="toRefEDLNo" styleClass="TEXTBOX2" size="20"
									maxlength="20" value='<%=toCheck.getToRefEDLNo()[2]%>' />
							</logic:notEmpty></TD>

						</TR>




						<TR>
							<TD WIDTH="15%" ALIGN="left" CLASS="headerbgcolor">New EDL
							Number(s)</TD>
							<TD CLASS="formFieldSub"><logic:empty name="toCheck"
								property="toEdlNo">
								<html:text property="toEdlNo" styleClass="TEXTBOX2" size="20"
									maxlength="20" value=""></html:text>&nbsp;&nbsp;
					<html:text property="toEdlNo" styleClass="TEXTBOX2" size="20"
									maxlength="20" value=""></html:text>&nbsp;&nbsp;
                    <html:text property="toEdlNo" styleClass="TEXTBOX2"
									size="20" maxlength="20" value=""></html:text>
							</logic:empty> <logic:notEmpty name="toCheck" property="toEdlNo">
								<html:text property="toEdlNo" styleClass="TEXTBOX2" size="20"
									maxlength="20" value='<%=toCheck.getToEdlNo()[0]%>'></html:text>&nbsp;&nbsp;
					<html:text property="toEdlNo" styleClass="TEXTBOX2" size="20"
									maxlength="20" value='<%=toCheck.getToEdlNo()[1]%>'></html:text>&nbsp;&nbsp;
					<html:text property="toEdlNo" styleClass="TEXTBOX2" size="20"
									maxlength="20" value='<%=toCheck.getToEdlNo()[2]%>' />
							</logic:notEmpty></TD>
						</TR>

						<TR>
							<TD CLASS="headerbgcolor" ALIGN="left"><bean:message key="screen.partOf" /></TD>
							<TD CLASS="formFieldSub" COLSPAN="6"><logic:empty name="toCheck"
								property="partOfClaNo">
								<html:text indexed="1" property="partOfCode" value=""
									styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;<A
									HREF="javascript:LoadAllClauses(1)" name="toPartOfSearch"><IMG
									SRC="images/search.gif" alt="Search" BORDER="0"></A>
								<A HREF="javascript:deletePartOfCode(1)" name="toPartOfDelete"><IMG
									SRC="images/delete1.gif" alt="Clear" BORDER="0"></A>
								<html:hidden property="partOfSeqNo" />
								<html:hidden property="partOfclaSeqNo" />

								<html:text property="partOfCode" value="" styleClass="TEXTBOX2"
									readonly="true"></html:text>&nbsp;<A
									HREF="javascript:LoadAllClauses(2)" name="toPartOfSearch"><IMG
									SRC="images/search.gif" alt="Search" BORDER="0"></A>
								<A HREF="javascript:deletePartOfCode(2)" name="toPartOfDelete"><IMG
									SRC="images/delete1.gif" alt="Clear" BORDER="0"></A>
								<html:hidden property="partOfSeqNo" />
								<html:hidden property="partOfclaSeqNo" />

								<html:text property="partOfCode" value="" styleClass="TEXTBOX2"
									readonly="true"></html:text>&nbsp;<A
									HREF="javascript:LoadAllClauses(3)" name="toPartOfSearch"><IMG
									SRC="images/search.gif" alt="Search" BORDER="0"></A>
								<A HREF="javascript:deletePartOfCode(3)" name="toPartOfDelete"><IMG
									SRC="images/delete1.gif" alt="Clear" BORDER="0"></A>
								<html:hidden property="partOfSeqNo" />
								<html:hidden property="partOfclaSeqNo" />

								<html:text property="partOfCode" value="" styleClass="TEXTBOX2"
									readonly="true"></html:text>&nbsp;<A
									HREF="javascript:LoadAllClauses(4)" name="toPartOfSearch"><IMG
									SRC="images/search.gif" alt="Search" BORDER="0"></A>
								<A HREF="javascript:deletePartOfCode(4)" name="toPartOfDelete"><IMG
									SRC="images/delete1.gif" alt="Clear" BORDER="0"></A>
								<html:hidden property="partOfSeqNo" />
								<html:hidden property="partOfclaSeqNo" />
							</logic:empty> <logic:notEmpty name="toCheck"
								property="partOfClaNo">

								<html:text indexed="1" property="partOfCode"
									value='<%=(toCheck.getPartOfClaNo()[0])=="0" ? "":toCheck.getPartOfClaNo()[0] %>'
									styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;<A
									HREF="javascript:LoadAllClauses(1)" name="toPartOfSearch"><IMG
									SRC="images/search.gif" alt="Search" BORDER="0"></A>
								<A HREF="javascript:deletePartOfCode(1)" name="toPartOfDelete"><IMG
									SRC="images/delete1.gif" alt="Delete" BORDER="0"></A>
								<html:hidden property="partOfSeqNo"
									value='<%=(String.valueOf(toCheck.getPartOfSubSecSeqNo()[0]))=="0" ? "":String.valueOf(toCheck.getPartOfSubSecSeqNo()[0]) %>' />
								<html:hidden property="partOfclaSeqNo"
									value='<%=(String.valueOf(toCheck.getPartOfClaSeqNo()[0]))== "0" ? "" :String.valueOf(toCheck.getPartOfClaSeqNo()[0])%>' />

								<html:text property="partOfCode"
									value='<%=(toCheck.getPartOfClaNo()[1])=="0" ? "":toCheck.getPartOfClaNo()[1] %>'
									styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;<A
									HREF="javascript:LoadAllClauses(2)" name="toPartOfSearch"><IMG
									SRC="images/search.gif" alt="Search" BORDER="0"></A>
								<A HREF="javascript:deletePartOfCode(2)" name="toPartOfDelete"><IMG
									SRC="images/delete1.gif" alt="Delete" BORDER="0"></A>
								<html:hidden property="partOfSeqNo"
									value='<%=(String.valueOf(toCheck.getPartOfSubSecSeqNo()[1]))=="0" ? "":String.valueOf(toCheck.getPartOfSubSecSeqNo()[1]) %>' />
								<html:hidden property="partOfclaSeqNo"
									value='<%=(String.valueOf(toCheck.getPartOfClaSeqNo()[1]))== "0" ? "" :String.valueOf(toCheck.getPartOfClaSeqNo()[1])%>' />

								<html:text property="partOfCode"
									value='<%=(toCheck.getPartOfClaNo()[2])=="0" ? "":toCheck.getPartOfClaNo()[2] %>'
									styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;<A
									HREF="javascript:LoadAllClauses(3)" name="toPartOfSearch"><IMG
									SRC="images/search.gif" alt="Search" BORDER="0"></A>
								<A HREF="javascript:deletePartOfCode(3)" name="toPartOfDelete"><IMG
									SRC="images/delete1.gif" alt="Delete" BORDER="0"></A>
								<html:hidden property="partOfSeqNo"
									value='<%=(String.valueOf(toCheck.getPartOfSubSecSeqNo()[2]))=="0" ? "":String.valueOf(toCheck.getPartOfSubSecSeqNo()[2]) %>' />
								<html:hidden property="partOfclaSeqNo"
									value='<%=(String.valueOf(toCheck.getPartOfClaSeqNo()[2]))== "0" ? "" :String.valueOf(toCheck.getPartOfClaSeqNo()[2])%>' />

								<html:text property="partOfCode"
									value='<%=(toCheck.getPartOfClaNo()[3])=="0" ? "":toCheck.getPartOfClaNo()[3] %>'
									styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;<A
									HREF="javascript:LoadAllClauses(4)" name="toPartOfSearch"><IMG
									SRC="images/search.gif" alt="Search" BORDER="0"></A>
								<A HREF="javascript:deletePartOfCode(4)" name="toPartOfDelete"><IMG
									SRC="images/delete1.gif" alt="Delete" BORDER="0"></A>
								<html:hidden property="partOfSeqNo"
									value='<%=(String.valueOf(toCheck.getPartOfSubSecSeqNo()[3]))=="0" ? "":String.valueOf(toCheck.getPartOfSubSecSeqNo()[3]) %>' />
								<html:hidden property="partOfclaSeqNo"
									value='<%=(String.valueOf(toCheck.getPartOfClaSeqNo()[3]))== "0" ? "" :String.valueOf(toCheck.getPartOfClaSeqNo()[3])%>' />

							</logic:notEmpty></TD>
						</TR>

						<TR>
							<TD CLASS="headerbgcolor" ALIGN="left">DWO Number</TD>
							<TD CLASS="formFieldSub"><logic:equal name="toCheck"
								property="todwONumber" value="0">
								<html:text property="todwONumber" styleClass="TEXTBOX2"
									size="20" maxlength="20" value="" />
							</logic:equal> <logic:greaterThan name="toCheck"
								property="todwONumber" value="0">
								<html:text name="toCheck" property="todwONumber"
									styleClass="TEXTBOX2" size="20" maxlength="20" />
							</logic:greaterThan></TD>
						</TR>


						<TR>
							<TD CLASS="headerbgcolor" ALIGN="left">Part Number</TD>
							<TD CLASS="formFieldSub"><logic:equal name="toCheck"
								property="toPartNumber" value="0">
								<html:text property="toPartNumber" value=""
									styleClass="TEXTBOX2" size="20" maxlength="8"></html:text>
							</logic:equal> <logic:greaterThan name="toCheck"
								property="toPartNumber" value="0">
								<html:text name="toCheck" property="toPartNumber"
									styleClass="TEXTBOX2" size="20" maxlength="8"></html:text>
							</logic:greaterThan></TD>
						</TR>


						<TR>
							<TD CLASS="headerbgcolor" ALIGN="left">Price Book Number</TD>
							<TD CLASS="formFieldSub"><logic:equal name="toCheck"
								property="topriceBookNumber" value="0">
								<html:text property="topriceBookNumber" value="" styleId="priceBookNo"
									styleClass="TEXTBOX2" size="20" maxlength="4"></html:text>
							</logic:equal> <logic:greaterThan name="toCheck"
								property="topriceBookNumber" value="0">
								<html:text name="toCheck" property="topriceBookNumber" styleId="priceBookNo"
									styleClass="TEXTBOX2" size="20" maxlength="4"></html:text>
							</logic:greaterThan></TD>
						</TR>


						<TR>
							<TD CLASS="headerbgcolor" WIDTH="15%">Standard Equipment</TD>

							<TD COLSPAN="3"><html:select property="standardEquipmentSeqNo"
								styleClass="selectstyle2">
								<option value="-1">--Select--</option>
								<logic:present name="CreateChangeClauseRequestForm"
									property="stdEquipmentList">
									<logic:iterate id="stdEquip"
										name="CreateChangeClauseRequestForm"
										property="stdEquipmentList" scope="request"
										type="com.EMD.LSDB.vo.common.StandardEquipVO">
										<logic:equal
											value="<%=String.valueOf(toCheck.getToStdEquipSeqNo())%>"
											name="stdEquip" property="standardEquipmentSeqNo">
											<option value="<%=stdEquip.getStandardEquipmentSeqNo()%>"
												selected><bean:write name="stdEquip"
												property="standardEquipmentDesc" /></option>
										</logic:equal>
										<logic:notEqual
											value="<%=String.valueOf(toCheck.getToStdEquipSeqNo())%>"
											name="stdEquip" property="standardEquipmentSeqNo">
											<option value="<%=stdEquip.getStandardEquipmentSeqNo()%>"><bean:write
												name="stdEquip" property="standardEquipmentDesc" /></option>
										</logic:notEqual>
									</logic:iterate>
								</logic:present>
							</html:select></TD>
						</TR>

						<TR>
							<TD CLASS="headerbgcolor">Comments</TD>
							<TD>&nbsp; <html:textarea name="toCheck" property="toComments"
								rows="4" cols="60"></html:textarea></TD>
						</TR>

					</TABLE>
					</FIELDSET>
					</TD>
				</TR>
			</logic:iterate>
		</TABLE>
	</logic:lessEqual>
	</logic:greaterThan>
	
	<html:hidden property="hdnClauseSeqNo" />
	<html:hidden property="hdnVersionNo" />
	<html:hidden property="hdnClauseNum" />
	<!-- Added for Spec Type Drop down CR_80 -->
	<input type="hidden" name="hdnSpecTypeNo">
	<html:hidden property="hdnModelSeqNo" />
	<html:hidden property="hdnSubSectionSeqNo" />
	<html:hidden property="hdnSectionSeqNo" />
	<input type="hidden" name="hdncomponentGroupSeqNo" />
	<html:hidden property="compColourFlag" />
	<html:hidden property="hdnAdminComments" />
	<html:hidden property="roleID" />

	<!--Added for including Save function from alert-->
	<html:hidden property="alertFlag1" />
	<!--Added for CR_80 CR Form Enhancements III on 24-Nov-09 by RR68151 -->
	<html:hidden property="defaultFlag" />
	<html:hidden property="noOfClaVersion" />
	<!-- Added for enabling and disabling buttons -->
	<jsp:include page="ButtonAccess.jsp" />
	<!-- Ends here -->
	<!-- ADDING FOR cR-94 
	<TABLE BORDER="0" WIDTH="100%" ALIGN="CENTER">
		<logic:equal name="CreateChangeClauseRequestForm" property="roleID"	value="ADMN">
			<logic:notEmpty name="CreateChangeClauseRequestForm" property="searchList">
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
		<logic:notEqual name="CreateChangeClauseRequestForm" property="roleID"	value="ADMN">
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
	<logic:present name="CreateChangeClauseRequestForm"
		property="reqModelSaved" scope="request">
		<script>
	fnDisableModelDropdown('<bean:write name="CreateChangeClauseRequestForm" property="reqModelSaved"/>');
</script>
	</logic:present>
	<%-- <%@ include file="/jsp/common/spellChecker.jsp" %> --%>
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
			<logic:iterate id="objSearchList" name="CreateChangeClauseRequestForm"	property="searchList" 
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
								CLASS=linkstyleItemGreen> <bean:write name="objSearchList" property="reqID" /></A>
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
			<TR>
				<td align=center>
				<html:button property="addButton" styleClass="buttonStyleClose" value="Close"></html:button>
				</td>
			</TR>
		</TABLE>
	</div>
	</logic:greaterEqual>
	<!-- Ends here -->
	<%-- Added for CR_99 for displaying JUNK characters by RJ85495 --%>	
	<jsp:include page="/jsp/common/ClauseComparison.jsp" />
	<%-- CR_99 Ends here --%>
	
</html:form>
