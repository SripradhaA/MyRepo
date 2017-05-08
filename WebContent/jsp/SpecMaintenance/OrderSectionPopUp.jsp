<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<style>
	.emdToolTip{ 
		background: #F6CECE; color: black; box-shadow: 0 0 7px black; border: 1px solid red;
	}
</style>
<HTML>
<!-- Added for CR_121 -->
	<LINK REL="stylesheet" TYPE="text/css" HREF="css/jquery-ui-custom.min.css">
<SCRIPT LANGUAGE="JavaScript" SRC="js/Others/jquery-ui.custom.min.js"></SCRIPT>
<!-- Added for CR_121 Ends-->
<HEAD>
<LINK REL="stylesheet" HREF="css/Emd_Lsdb.css" TYPE="text/css">
<LINK REL="stylesheet" TYPE="text/css" HREF="css/EmdMenu.css">
<SCRIPT language="JavaScript" SRC="js/OrderSectionPopUp.js"></SCRIPT>
<SCRIPT language="JavaScript" SRC="js/Emd_Lsdb.js"></SCRIPT>
<SCRIPT language="JavaScript" SRC="js/error.js"></SCRIPT>
<SCRIPT language="JavaScript" SRC="js/error_messages.js"></SCRIPT>
<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
</HEAD>
<BODY CLASS='main'>
<html:form action="/OrderPartofPopUpAction.do">
	<br>
	<TABLE WIDTH="100%" ALIGN="center">
		<TR>
			<TD CLASS='dashBoardSubHeading' ALIGN="center"><bean:write
				name="OrderSectionPopUpForm" property="subSecCode" />&nbsp; <bean:write
				name="OrderSectionPopUpForm" property="subSecName" /></TD>
		</TR>
	</TABLE>

	<br>
	<!-- Validation Message Display Part Starts Here -->
	<div class="errorlayerhide" id="errorlayer"></div>
	<!-- Validation Message Display Part Ends Here -->

	<!-- Logic Tag Check For Display The Success Message After Add Starts Here -->


	<logic:present name="OrderSectionPopUpForm" property="messageID"
		scope="request">
			<bean:define id="messageID" name="OrderSectionPopUpForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
	</logic:present>

	<!-- Logic Tag Check For Display The Success Message After Add Functionality Ends Here -->
	<!-- Logic Tag Check For Display The Error Message After Add Functionality Starts Here -->
	<logic:present name="OrderSectionPopUpForm" property="errorMessage"
		scope="request">
		<script>
                    hideErrors();
                    addMessage('<bean:write name="OrderSectionPopUpForm" property="errorMessage"/>');
                    showScrollErrors();
                </script>

	</logic:present>

	<!-- Logic Tag Check For Display The Error Message After Add Functionality Ends Here -->
	<br>
	<logic:present name="OrderSectionPopUpForm" property="clauseGroup">
		<TABLE WIDTH="90%" ALIGN="center" BORDER="1" BORDERCOLOR="#5780ae">

			<TR>
				<TD CLASS='table_heading'>Select</TD>
				<TD CLASS='table_heading'>Clause No</TD>
				<TD CLASS='table_heading'>Clause Description</TD>
				<TD CLASS='table_heading' nowrap>Engineering Data</TD>
				<TD CLASS='table_heading' WIDTH="10%">Component</TD>
			</TR>
			<logic:iterate id="clause" name="OrderSectionPopUpForm"
				property="clauseGroup" type="com.EMD.LSDB.vo.common.ClauseVO"
				scope="request">
				<tr>
					<TD class="borderbottomleft" VALIGN="TOP"><input type="radio"
						class="radcheck" name="clauseSeqNo"
						value='<%=String.valueOf(clause.getClauseSeqNo())%>' /></TD>

					<TD CLASS=borderbottomleft1><bean:write name="clause"
						property="clauseNum" /> <html:hidden property="hdnClauseNum"
						value='<%=String.valueOf(clause.getClauseNum())%>' /></TD>
					<logic:notEmpty name="clause" property="clauseDesc">
						<TD CLASS=borderbottomleft><span style="width:435px">
						<DIV STYLE="float:left;height:60;width:435;overflow: auto ; "><bean:define
							name="clause" property="clauseDesc" id="clauseDesc" /> <%=clauseDesc%>

						<table width="100%" BORDER="1" BORDERCOLOR="">
							<logic:notEmpty name="clause" property="tableArrayData1">
					&nbsp;
				 <logic:iterate id="outter" name="clause" property="tableArrayData1"
									indexId="counter">
									<bean:define id="row" name="counter" />
									<tr>
										<logic:iterate id="tabrow" name="outter">

											<logic:equal name="row" value="0">
												<td valign="top" width="5%" class="borderbottomleft"><b><font
													color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></b></font>
												</td>
											</logic:equal>

											<logic:notEqual name="row" value="0">
												<td valign="top" width="5%" class="borderbottomleft"><%=(tabrow==null)? "&nbsp;":tabrow%>
												</td>
											</logic:notEqual>


										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</table>
						</DIV>
						</span></TD>
					</logic:notEmpty>
					<logic:empty name="clause" property="clauseDesc">
						<td class="borderbottomleft" VALIGN="TOP">&nbsp;</td>
					</logic:empty>

					<!-- Eng data starts -->
					<TD CLASS=borderbottomleft nowrap VALIGN="TOP"><span
						style="width:130px">
					<DIV STYLE="float:left;height:60;width:130;overflow: auto ; ">
					<!-- <logic:notEmpty
						name="clause" property="refEDLNO">
						<logic:iterate id="refedl" name="clause" property="refEDLNO">
                                    ref: EDL&nbsp;<bean:write
								name="refedl" />
							<br>
						</logic:iterate>
					</logic:notEmpty>-->
					 <logic:notEmpty name="clause" property="edlNO">
						<logic:iterate id="edl" name="clause" property="edlNO">
                                    EDL&nbsp;<bean:write name="edl" />
							<br>
						</logic:iterate>
					</logic:notEmpty> 
					<!-- CR 87 -->
					<logic:notEmpty
						name="clause" property="refEDLNO">
						<logic:iterate id="refedl" name="clause" property="refEDLNO">
                                   <bean:message key="screen.refEdl.start" />&nbsp;
                                   <bean:write	name="refedl" />&nbsp;
                                   <bean:message key="screen.refEdl.end" />
							<br>
						</logic:iterate>
					</logic:notEmpty>
					<logic:notEmpty name="clause" property="partOF">
						<logic:iterate id="partof" name="clause" property="partOF"
							type="com.EMD.LSDB.vo.common.SubSectionVO">
							<bean:message key="viewSpec.PartOf" />&nbsp;<bean:write
								name="partof" property="subSecCode" />&nbsp;
							
							<br>
						</logic:iterate>
					</logic:notEmpty> <logic:notEmpty name="clause"
						property="dwONumber">
						<logic:notEqual name="clause" property="dwONumber" value="0">
                                    DWO&nbsp;<bean:write name="clause"
								property="dwONumber" />
							<br>
						</logic:notEqual>
					</logic:notEmpty> <logic:notEmpty name="clause"
						property="partNumber">
						<logic:notEqual name="clause" property="partNumber" value="0">
                                    Part No&nbsp;<bean:write
								name="clause" property="partNumber" />
							<br>
						</logic:notEqual>
					</logic:notEmpty> <logic:notEmpty name="clause"
						property="standardEquipmentDesc">
						<bean:write name="clause" property="standardEquipmentDesc" />
						<br>
					</logic:notEmpty> <logic:notEmpty name="clause"
						property="engDataComment">
						<bean:write name="clause" property="engDataComment" />
						<br>
					</logic:notEmpty></DIV>
					</span></TD>

					<logic:notEmpty name="clause" property="compName">

						<TD CLASS="borderbottomrightlight1" VALIGN="TOP">

						<DIV STYLE="height:60;width:120;overflow: auto ; "><logic:notEmpty
							name="clause" property="compName">
							<bean:size id="compSize" name="clause" property="compName" />
							<bean:define id="compTotSize"
								value="<%=String.valueOf(compSize.intValue()-1)%>" />
						</logic:notEmpty> <logic:iterate id="compdesc" name="clause"
							property="compName" type="com.EMD.LSDB.vo.common.ComponentVO" indexId="counter">
							<bean:define id="count" name="counter" />
							<logic:notEqual name="compTotSize" value="<%=count.toString()%>">
								<logic:equal name="compdesc" property="deletedFlag" value="Y">
								<font color="red"><bean:write name="compdesc" property="componentName" /></font>
								</logic:equal>
								<logic:notEqual name="compdesc" property="deletedFlag" value="Y">
								<bean:write name="compdesc" property="componentName" />
								</logic:notEqual>;<br>
							</logic:notEqual>
							<logic:equal name="compTotSize" value="<%=count.toString()%>">
								<logic:equal name="compdesc" property="deletedFlag" value="Y">
								<font color="red"><bean:write name="compdesc" property="componentName" /></font>
								</logic:equal>
								<logic:notEqual name="compdesc" property="deletedFlag" value="Y">
								<bean:write name="compdesc" property="componentName" />
								</logic:notEqual>
								<br>
							</logic:equal>
						</logic:iterate></DIV>
						</TD>
					</logic:notEmpty>
					<logic:empty name="clause" property="compName">
						<TD CLASS="borderbottomrightlight1"><BR>
						</TD>
					</logic:empty>
				</tr>
			</logic:iterate>

			<table>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			</logic:present>
			<TABLE BORDER=0 WIDTH="50%" ALIGN="center">
				<TR>
					<TD ALIGN="right" width="50%"><INPUT TYPE="button"
						CLASS="buttonStyle" name="btnok" value="Ok"
						onclick="javascript:Cancelpopup();javascript:savePartOf()"></TD>
					<TD COLSPAN=2 ALIGN=left><INPUT TYPE="button" CLASS="buttonStyle"
						name="btnCancel" value="Cancel" onclick="javascript:CloseWindow()"></TD>
				</TR>
			</TABLE>

			<html:hidden property="orderKey" />
			<html:hidden property="subSecSeqNo" />
			<html:hidden property="parentClauseSeqNo" />
			<html:hidden property="numClause" />
			<html:hidden property="secSeqNo" />
			<html:hidden property="subSecName" />
			<html:hidden property="subSecCode" />
			<html:hidden property="versionNo" />
			<html:hidden property="revCode" />
			<html:hidden property="parentSecSeqNo" />
			<html:hidden property="partOfClauseSeqNo" />
			</html:form>
</BODY>
</HTML>
