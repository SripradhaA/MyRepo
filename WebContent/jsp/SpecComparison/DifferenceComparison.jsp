<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html>
<HEAD>
<LINK REL="stylesheet" HREF="css/Emd_Lsdb.css" TYPE="text/css">
<SCRIPT LANGUAGE="JavaScript" SRC="js/ModifySpec.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/SpecComparison.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/error_messages.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/error.js"></SCRIPT>
</HEAD>
<body topmargin=0>
<html:form action="/compareComponentAction" method="post">
	<TABLE BORDER="0" WIDTH="60%" valign="top" ALIGN="center">
		<TR>
			<TD>&nbsp;</TD>
		</TR>
		<TR>
			<TD WIDTH="60%" CLASS="dashBoardSubHeading" ALIGN="center">
			Difference in Component Comparison</TD>
		</TR>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</TABLE>

	<!-- Validation Message Display Part Starts Here -->

	<%--Table Updated for CR-121 for server side error message tooltip --%>
	<div class="errorlayerhide" id="errorlayer">
		</div>

	<!-- Validation Message Display Part Ends Here -->



	<logic:present name="ComponentCompareForm" property="compareOrderList"
		scope="request">
		<bean:size name="ComponentCompareForm" id="compsize"
			property="compareOrderList" />
	</logic:present>

	<logic:lessThan name="compsize" value="1">
		<script>
						var id='407';
						hideErrors();
						addMsgNum(id);
						showScrollErrors();
					</script>
	</logic:lessThan>


	<TABLE WIDTH="100%" align="center" BORDER="0" BORDERCOLOR="#5780ae">
		<TR>
			<logic:iterate id="ordListId" name="ComponentCompareForm"
				property="selectedOrderList" type="com.EMD.LSDB.vo.common.OrderVO"
				indexId="count">
				<bean:define id="loopCount" name="count" />
				<!-- <TD CLASS="noborder2b" >&nbsp;</TD> -->
				<TD WIDTH="30%" align="left">
				<TABLE BORDER=1 BORDERCOLOR="#5780ae" WIDTH="100%">
					<TR>
						<TD CLASS="noborder2b" width="40%">Order Number</TD>
						<TD width="50%" CLASS="noborder2d" style="align:left"><b><bean:write
							name="ordListId" property="orderNo" /></b></TD>
						<!-- <td></td> -->
					</TR>
					<tr>
						<TD CLASS="noborder2b" width="40%">Model</TD>
						<TD CLASS="noborder2d" width="40%" style="align:left"><b><bean:write
							name="ordListId" property="modelName" /></b></TD>
						<td></td>
					</TR>
					<TR>
						<TD CLASS="noborder2b" width="40%">Customer Name</TD>
						<TD CLASS="noborder2d" width="40%" style="align:left"><b><bean:write
							name="ordListId" property="customerName" /></b></TD>
						<!-- <td></td> -->
					</tr>
					<tr>
						<TD CLASS="noborder2b" width="40%">Spec Status</TD>
						<TD CLASS="noborder2d" width="40%" style="align:left"><b><bean:write
							name="ordListId" property="specTypeName" /></b></TD>
						<!-- <td></td> -->
					</TR>
				</TABLE>
				</TD>

			</logic:iterate>

			<!--  -->
		</TR>

		<!-- <TR>
		<TD>&nbsp;</TD>
	</TR> -->
		<TR>
			<td colspan=2 align="center">&nbsp;</td>
		</TR>
		<TR>
			<td colspan=2 CLASS=noborder2 align="center"><logic:present
				name="ComponentCompareForm" property="sectionName">
				<bean:write name="ComponentCompareForm" property="sectionName" />
			</logic:present></td>
		</TR>
		<TR>
			<TD colspan=2 align="center"><logic:greaterThan name="compsize"
				value="0">
				<TABLE BORDER=1 align="center" WIDTH="100%" BORDERCOLOR="#5780ae">

					<tr>
						<TD>
						<TABLE BORDER=0 WIDTH="100%" BORDERCOLOR="#5780ae" ALIGN="center">
							<TR>
								<td CLASS="table_heading" width="16%" align="center"><b>Component
								Group</td>
								<td CLASS="table_heading" width="16%" align="center"><b>Component</td>
								<td CLASS="table_heading" width="18%" align="center"><b>Description</td>
								<TD class="noborder">&nbsp;</TD>
								<td CLASS="table_heading" width="16%" align="center"><b>Component
								Group</td>
								<td CLASS="table_heading" width="16%" align="center"><b>Component</td>
								<td CLASS="table_heading" width="16%" align="center"><b>Description</td>
							</TR>

							<logic:iterate id="componentVo" name="ComponentCompareForm"
								property="compareOrderList"
								type="com.EMD.LSDB.vo.common.ComponentVO">
								<TR>
									<td width="16%" align="center" class="borderbottomrightlight">
									<bean:write name="componentVo" property="componentGroupName" />
									</td>
									<logic:present name="componentVo"
										property="orderOneComponentName">
										<logic:notEqual name="componentVo"
											property="orderOneComponentName" value=" ">
											<td width="16%" align="center" CLASS='borderbottomleft1'><bean:write
												name="componentVo" property="orderOneComponentName" /></td>
										</logic:notEqual>
									</logic:present>
									<logic:equal name="componentVo"
										property="orderOneComponentName" value=" ">
										<td width="16%" align="center" CLASS='borderbottomleft1'><font
											color="red">Not Selected</font></TD>
									</logic:equal>
									<logic:present name="componentVo"
										property="orderOneCompDescName">
										<logic:notEqual name="componentVo"
											property="orderOneCompDescName" value=" ">
											<td width="16%" align="center" CLASS='borderbottomrightlight'>
											<bean:write name="componentVo"
												property="orderOneCompDescName" /></td>
										</logic:notEqual>
									</logic:present>
									<logic:equal name="componentVo" property="orderOneCompDescName"
										value=" ">
										<td width="16%" align="center" CLASS='borderbottomrightlight'><font
											color="red">Not Selected</font></TD>
									</logic:equal>
									<TD class="noborder">&nbsp;</TD>
									<td width="16%" align="center" CLASS='borderbottomleft1'><bean:write
										name="componentVo" property="componentGroupName" /></td>
									<logic:present name="componentVo"
										property="orderTwoComponentName">
										<logic:notEqual name="componentVo"
											property="orderTwoComponentName" value=" ">
											<td width="20%" align="center" CLASS='borderbottomleft1'><bean:write
												name="componentVo" property="orderTwoComponentName" /></td>
										</logic:notEqual>
									</logic:present>
									<logic:equal name="componentVo"
										property="orderTwoComponentName" value=" ">
										<td width="20%" align="center" CLASS='borderbottomleft1'><font
											color="red">Not Selected</font></TD>
									</logic:equal>
									<logic:present name="componentVo"
										property="orderTwoCompDescName">
										<logic:notEqual name="componentVo"
											property="orderTwoCompDescName" value=" ">
											<td width="20%" align="center" CLASS='borderbottomleft1'><bean:write
												name="componentVo" property="orderTwoCompDescName" /></td>
										</logic:notEqual>
									</logic:present>
									<logic:equal name="componentVo" property="orderTwoCompDescName"
										value=" ">
										<td width="20%" align="center" CLASS='borderbottomleft1'><font
											color="red">Not Selected</font></TD>
									</logic:equal>
								</TR>
							</logic:iterate>

						</TABLE>
						</TD>
					</TR>


				</TABLE>
			</logic:greaterThan></TD>
		</TR>
	</TABLE>

	<TABLE BORDER=0 WIDTH="100%">
		<TR>
			<TD>&nbsp;</TD>
		</TR>
		<TR>
			<TD ALIGN="center" WIDTH="50%"><INPUT TYPE="button" value="Close"
				CLASS=buttonStyle onclick="window.close()" name="btnCancel"></TD>
		</TR>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</TABLE>
</html:form>
</BODY>
</html:html>
