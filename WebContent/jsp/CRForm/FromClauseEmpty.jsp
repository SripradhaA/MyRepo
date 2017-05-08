<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<TABLE BORDER="0" WIDTH="85%" ALIGN="center" CLASS="miniDashOut1">
	<TR>
		<TD colspan=2 ALIGN="CENTER" CLASS=headerbgcolor>Change From</TD>
	</TR>
	<TR>
		<TD ALIGN=left CLASS="headerbgcolor" nowrap>Select Clause&nbsp;</TD>
		<TD>&nbsp;<A HREF="javascript:fnLoadFromClause()" name="linkClause"
			id="searchicon"><IMG SRC="images/search.gif" alt="Search" BORDER="0"></A>
		</TD>
	</TR>
	<TR>
		<TD width=10% CLASS="headerbgcolor" ALIGN="left" nowrap>Clause
		Number&nbsp;</TD>
		<TD width=90% CLASS="formFieldSub"><html:text styleClass="TEXTBOX2"
			size="20" property="clauseNo" maxlength="60" readonly="true" /> <!-- <input
			type="hidden" name="selectedVersion" value="" />  --><input
			type="hidden" name="selectedClaSeqNo" value="" /></TD>
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

			
	<TR>
		<TD WIDTH="22%" CLASS="headerbgcolor">Clause Description<font
			color="red">*</font></TD>
		<TD COLSPAN="3">

		<DIV id="fromClause" align="left"
			STYLE="float:left;height:60;width:435;overflow:auto;"></DIV>
		<!-- <input type="hidden" name="selectedVersion"/> --></TD>

	</TR>
	<TR>
		<TD CLASS="headerbgcolor" ALIGN="left" nowrap>Components Tied To
		Clause</TD>
		<TD CLASS="formFieldSub">
		<Div id="components" align="left"
			STYLE="float:left;height:60;width:435;overflow: auto ;"></Div>
		</TD>
	</TR>

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

						<TD COLSPAN="3"><html:text property="refEDLNo"
							styleClass="TEXTBOX2" size="20" maxlength="20" value=""
							readonly="true"></html:text>&nbsp;&nbsp; <html:text
							property="refEDLNo" styleClass="TEXTBOX2" size="20"
							maxlength="20" value="" readonly="true"></html:text>&nbsp;&nbsp;
						<html:text property="refEDLNo" styleClass="TEXTBOX2" size="20"
							maxlength="20" value="" readonly="true"></html:text></TD>

					</TR>
					<TR>
						<TD CLASS="headerbgcolor">New EDL Number(s)</TD>
						<TD COLSPAN=3><html:text property="edlNo" readonly="true"
							styleClass="TEXTBOX2" size="20" maxlength="20" value=""></html:text>
						&nbsp; <html:text property="edlNo" readonly="true"
							styleClass="TEXTBOX2" size="20" maxlength="20" value=""></html:text>
						&nbsp; <html:text property="edlNo" readonly="true"
							styleClass="TEXTBOX2" size="20" maxlength="20" value=""></html:text>
						</TD>
					</TR>
					<TR><!-- CR 87 -->
						<TD CLASS="headerbgcolor"><bean:message key="screen.partOf" /></TD>
						<TD COLSPAN="5"><html:text indexed="1" property="toPartOfCode"
							value="" styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;&nbsp;


						<html:text property="toPartOfCode" value="" styleClass="TEXTBOX2"
							readonly="true"></html:text>&nbsp;&nbsp; <html:text
							property="toPartOfCode" value="" styleClass="TEXTBOX2"
							readonly="true"></html:text>&nbsp;&nbsp; <html:text
							property="toPartOfCode" value="" styleClass="TEXTBOX2"
							readonly="true"></html:text></TD>
					</TR>
					<TR>
						<TD CLASS="headerbgcolor" ALIGN="left">DWO Number</TD>
						<TD COLSPAN="5"><html:text property="dwONumber" readonly="true"
							styleClass="TEXTBOX2" size="20" maxlength="20" value="" /></TD>
					</TR>
					<TR>
						<TD CLASS="headerbgcolor" ALIGN="left">Part Number</TD>
						<TD COLSPAN="5"><html:text property="partNumber" readonly="true"
							styleClass="TEXTBOX2" size="20" maxlength="8" value="" /></TD>
					</TR>

					<!-- Maxlength of Price book number is changed from 20 to 4.Modified on 29-09-08 by ps57222 -->

					<TR>
						<TD CLASS="headerbgcolor" ALIGN="left">Price Book Number</TD>
						<TD COLSPAN="5"><html:text property="priceBookNumber"
							styleClass="TEXTBOX2" size="20" maxlength="4" value=""
							readonly="true"></html:text></TD>
					</TR>


					<TR>
						<TD CLASS="headerbgcolor">Standard Equipment</TD>
						<TD COLSPAN="3"><html:select name="CreateChangeClauseRequestForm"
							property="standEquipmentSeqNo" styleClass="selectstyle2"
							disabled="true">
							<option selected value="-1">--Select--</option>
							<logic:present name="CreateChangeClauseRequestForm"
								property="stdEquipmentList">
								<html:optionsCollection name="CreateChangeClauseRequestForm"
									property="stdEquipmentList" value="standardEquipmentSeqNo"
									label="standardEquipmentDesc" />
							</logic:present>
						</html:select></TD>
					</TR>
					<TR>
						<TD CLASS="headerbgcolor">Comments</TD>
						<TD><html:textarea property="comments" rows="4" cols="60"
							readonly="true"></html:textarea></TD>
					</TR>


				</TABLE>
				</FIELDSET>
				</TD>
			</TR>
		</TABLE>
		</TD>
	</TR>
</TABLE>
