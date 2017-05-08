<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<HEAD>
<SCRIPT LANGUAGE="JavaScript" SRC="js/SpecSupplement.js">
</SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
function fnOpenPDF(url)
{
	window.open(url,"",""); 
}
</SCRIPT>

</HEAD>
<html:form action="/SpecSupplement" method="post">

	<BR>
	<TABLE WIDTH="60%" ALIGN=center>

		<TR>
			<TD WIDTH="100%" CLASS="dashBoardSubHeading" ALIGN="center"><bean:message
				key="Application.Screen.SpecSupplement" /></TD>
		</TR>
		<%-- Added Note for CR_104   --%>
		<TR>
			<TD WIDTH="100%" CLASS="navycolor" ALIGN="center"><bean:message
				key="Message.WildcardSearch" /></TD>
		</TR>
		
		<TR>
			<TD>&nbsp;</TD>
		</TR>
	</TABLE>


	<!-- To display  Messages - Start -->
	<%--Table Updated for CR-121 for server side error message tooltip --%>
	<div class="errorlayerhide" id="errorlayer">
	</div>
	<BR>
	<!-- To display Messages - End -->


	<!-- To get Order List from Form - Start -->
	<logic:present name="SpecSupplementForm" property="orderList">
		<bean:size id="ordListLen" name="SpecSupplementForm"
			property="orderList" />
	</logic:present>
	<!-- To get Order List from Form - End -->

	<logic:present name="SpecSupplementForm" property="messageID"
		scope="request">
	<%--Added for CR-121 for server side error message tooltip --%>	
	  <bean:define id="messageID" name="SpecSupplementForm" property="messageID"/>
	  <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
      <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
	</logic:present>

	<!-- Validation Message For No Records Found - Start -->
	<logic:match name="SpecSupplementForm" property="method"
		value="fetchOrders">
		<logic:lessThan name="ordListLen" value="1">
			<script>
			//Updated for CR-121 server side error message
			fnNoRecords();
		</script>
		</logic:lessThan>
	</logic:match>

	<TABLE WIDTH="50%" BORDER="0" ALIGN="center">
		<TR>
			<TD WIDTH="10%" CLASS='headerbgcolor' ALIGN="left" nowrap><bean:message
				key="copySpecSpecification" /> <!-- **
				 **	Mandatory Fields red star mark is Comment out based on requirement of 		LSDB_CR-19 
				 **  Changes Done on 21-April-08
				 **  By ps57222
			--> <!-- <font color="red" valign="top">&nbsp;*</font> --></TD>
			<TD WIDTH="15%" ALIGN="left" CLASS='navycolor1'><html:select
				name="SpecSupplementForm" property="spectypeSeqno"
				styleClass="selectstyle2" onchange="javascript:fnLoadCustomers()">
				<option selected value="-1">---Select---</option>
				<html:optionsCollection name="SpecSupplementForm"
					property="specTypeList" value="spectypeSeqno" label="spectypename" />
			</html:select></TD>
			<TD WIDTH="7%"></TD>
			<TD CLASS='headerbgcolor' WIDTH="10%" nowrap><bean:message
				key="copySpecCustomer" /> <!-- **
				 **	Mandatory Fields red star mark is Comment out based on requirement of 		LSDB_CR-19 
				 **  Changes Done on 21-April-08
				 **  By ps57222
			--> <!-- <font color="red" valign="top">&nbsp;*</font> --></TD>
			<TD WIDTH="28%"><html:select name="SpecSupplementForm"
				property="custSeqNo" styleClass="selectstyle2">

				<option selected value="-1">---Select---</option>

				<logic:present name="SpecSupplementForm" property="custList">
					<html:optionsCollection name="SpecSupplementForm"
						property="custList" value="customerSeqNo" label="customerName" />
				</logic:present>

			</html:select></TD>
			<TD WIDTH="2%"></TD>
			<TD CLASS='headerbgcolor' WIDTH="15%" nowrap><bean:message
				key="copySpecOrderNo" /><font color="red" valign="top">&nbsp;*</font>
			</TD>
			<TD><html:text property="orderNo" styleClass="TEXTBOX2" size="5"
				maxlength="15" onkeypress="return keyHandler()" /></TD>
			<TD WIDTH="18%"></TD>

		</TR>
	</TABLE>



	<TABLE WIDTH="60%" ALIGN="center" BORDER="0" BORDERCOLOR="#5780ae">
		<TR>
			<TD>&nbsp;</TD>
		</TR>
		<TR>
			<TD ALIGN="center"><html:button property="method"
				styleClass="buttonStyle" onclick="javascript:fnSearchOrders()">
				<bean:message key="screen.search" />
			</html:button></TD>
		</TR>
	</TABLE>
	<logic:greaterThan name="ordListLen" value="0">
		<HR>
		<TABLE ALIGN=center WIDTH="80%" BORDER="0" bgcolor=#D2DDF9>
			<TR>
				<TD ALIGN=center><span CLASS=greytext1>Search Criteria</span></TD>
			</TR>
			<TR>
		</TABLE>

		<TABLE ALIGN=center WIDTH="80%" BORDER="0" bgcolor=#D2DDF9>
			<TR>
				<TD WIDTH="36%" /></TD>
				<TD ALIGN="LEFT" WIDTH="15%"><span CLASS=greytext1>Specification
				Type </span></TD>
				<TD WIDTH="3%"><span CLASS=greytext1>:</span></TD>
				<TD><logic:notEqual name="SpecSupplementForm"
					property="spectypeSeqno" value="-1">
					<bean:write name="SpecSupplementForm" property="specType" />
				</logic:notEqual></TD>
			</TR>

			<TR>
				<TD /></TD>
				<TD ALIGN="LEFT"><span CLASS=greytext1>Customer </span></TD>
				<TD><span CLASS=greytext1>:</span></TD>
				<TD><logic:notEqual name="SpecSupplementForm" property="custSeqNo"
					value="-1">
					<bean:write name="SpecSupplementForm" property="custName" />
				</logic:notEqual></TD>
			</TR>
			<TR>
				<TD /></TD>
				<TD ALIGN="LEFT"><span CLASS=greytext1>Order Number </span></TD>
				<TD><span CLASS=greytext1>:</span></TD>
				<TD><bean:write name="SpecSupplementForm" property="orderNo" /></TD>
			</TR>
		</TABLE>
		<!-- Based on the requirement of LSDB_CR-74 the below table grid is modified, on 08-June-09 by ps57222-->
		<TABLE WIDTH="80%" ALIGN="center" BORDER="1" BORDERCOLOR="#5780ae">
			<TR>
				<TH CLASS='table_heading' align="center"><bean:message
					key="copySpecStatus" /></TH>
				<TH CLASS='table_heading' align="center"><bean:message
					key="copySpecOrderNo" /></TH>
				<TH CLASS='table_heading' align="center"><bean:message
					key="copySpecModel" /></TH>
				<TH CLASS='table_heading' align="center"><bean:message
					key="copySpecCustModelName" /></TH>
			</TR>

			<logic:iterate id="ordListId" name="SpecSupplementForm"
				property="orderList" type="com.EMD.LSDB.vo.common.OrderVO">

				<TR>

					<TD CLASS='borderbottomleft1'>
						<logic:iterate id="fileLoc" name="ordListId" property="fileLoc" indexId="count">
							<bean:define id="size" name="count"/>
							
							<logic:equal name="size" value="4">
								<logic:notEmpty name="fileLoc">
								<logic:iterate id="RevList" name="fileLoc" type="com.EMD.LSDB.vo.common.FileLocationVO" indexId="count">
								
									<logic:equal name="RevList" property="fileDesc"				value="Spec Supplement">
									<logic:notEmpty name="RevList" property="fileLoc">
									<%-- Commented to fix the "Open in New tab issue" --%>
										<%--a href="#"
												onClick="window.open('<bean:write name='RevList' property='fileLoc'/>')"
												CLASS='linkstyle1'> <bean:write name="ordListId"
												property="statusDesc" /> </a--%>
									<%-- Comment Ends here --%>
									<%-- Added to disable the right click properties(Open in new tab) 25-Aug Modified --%>
										<a href="javascript:fnOpenPDF('<bean:write name="RevList" property="fileLoc"/>')"		
												CLASS='linkstyle1'><bean:write name="ordListId"
												property="statusDesc" /> </a>
									<%-- Ends here --%>
															<BR>
									 </logic:notEmpty>
									 <logic:empty name="RevList" property="fileLoc">
										<bean:write name="ordListId" property="statusDesc"/><BR>
									 </logic:empty>
									 
									 </logic:equal>
									 <bean:define id="size" name="count"/>
									 <logic:equal name="size" value="0">
										<logic:notEqual name="RevList" property="fileDesc"				value="Spec Supplement">
										<logic:notEqual name="ordListId" property="revFlag" value="Y">
											<bean:write name="ordListId" property="statusDesc"/><BR>
										</logic:notEqual>
										</logic:notEqual>
									</logic:equal>
									</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="fileLoc">
										<bean:write name="ordListId" property="statusDesc"/><BR>
									</logic:empty>
								
							</logic:equal>
							
						</logic:iterate>
					</TD>
					<TD CLASS='borderbottomleft1'>
						<bean:write name="ordListId" property="orderNo" />
					</TD>
					<TD CLASS='borderbottomleft1'>
						<bean:write name="ordListId" property="modelName" />
					</TD>
					<TD CLASS='borderbottomleft1'>
						<bean:write name="ordListId" property="custMdlName" />
					</TD>
				</TR>

			</logic:iterate>

		</TABLE>
		<BR>
	</logic:greaterThan>
	<html:hidden property="specType" />
	<html:hidden property="custName" />
	<input type="hidden" name="flag" value="Y">
</html:form>
