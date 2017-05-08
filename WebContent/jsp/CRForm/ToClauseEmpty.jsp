<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<TABLE BORDER="0" WIDTH="85%" ALIGN="center" CLASS="miniDashOut1">
	<TR>
		<TD colspan=2 ALIGN="CENTER" CLASS=headerbgcolor>Change To / Create</TD>
	</TR>
	<TR>
		<TD width=10% CLASS="headerbgcolor" ALIGN="left" nowrap>Parent Clause
		&nbsp;<A HREF="javascript:fnLoadParentClause()" name="linkClause"
			id="toParentClaNo"><IMG SRC="images/search.gif" alt="Search"
			BORDER="0"></A></TD>
		<TD width=90% ALIGN=left><html:text styleClass="COMMONTEXTBOXHEIGHT1"
			property="toParentClauseNo" size="20" maxlength="50" readonly="true" />&nbsp;
		<!-- Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 -->
		<A HREF="javascript:deletetoParentClause()" id="toParentClaNodel"><IMG
					SRC="images/delete1.gif" alt="Delete" BORDER="0"></A><BR>
		<html:hidden property="toParentClaDesc" /> <html:hidden
			property="parentClauseSeqNo" />
		<DIV id="parentclause" STYLE="height:60;width:435;overflow: auto ; ">
		</DIV>

		</TD>
	</TR>
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
		<DIV id="claVersion" STYLE="height:60;width:435;overflow: auto ; "></DIV>
		</TD>
	</TR>
	<TR>
		<TD width=10% CLASS="headerbgcolor" ALIGN="left" nowrap>New Clause
		Number&nbsp;</TD>
		<TD width=90% CLASS="formFieldSub"><html:text
			styleClass="COMMONTEXTBOXHEIGHT1" property="toClauseNo" size="20"
			maxlength="20" /></TD>
	</TR>
	<TR>
		<TD width=10% CLASS="headerbgcolor" ALIGN="left" nowrap>New Clause
		Description&nbsp;</TD>
		<TD width=90% CLASS="formFieldSub"><html:textarea styleId="clauseDesc_id" styleClass="CLAUSEDESCTEXTAREA" 
		rows="15" cols="92" property="toClauseDesc"/></TD>
	</TR>
	<TR>
		<TD WIDTH="10%" CLASS="headerbgcolor">Table Data <br>
		<br>
		<DIV id="showmainlink" style="visibility:visible"><A
			CLASS='linkstyleItem' HREF="javascript:addTable()">Add Table</A>&nbsp;</DIV>
		<DIV id="showsublink" style="visibility:hidden"><A CLASS=linkstyle1
			HREF="javascript:addRow(this)">Add Row</A>&nbsp;|&nbsp;<A
			CLASS=linkstyle1 HREF="javascript:removeRow()">Delete Row</A><br>
		<A CLASS='linkstyle1' HREF="javascript:deleteTable()">Delete Table</A>&nbsp;</DIV>
		</TD>
		<TD WIDTH="75%" COLSPAN=7>
		<DIV ID="showgrid"></DIV>
		</TD>
	</TR>
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
	<!-- Removed for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
	
	<TR>
		<TD CLASS="headerbgcolor" ALIGN="left" nowrap>Default Clause &nbsp;</TD>
		<TD CLASS="formFieldSub"><B>Yes</B><input type="radio"
			class="radcheck" name="toDefaultFlag" value="Y"> <B>No</B><input
			type="radio" class="radcheck" name="toDefaultFlag" value="N" checked>
		</TD>
	</TR> -->
	<TR>
		<TD colspan=2 WIDTH="88%">
		<FIELDSET CLASS="fieldset8"><LEGEND ALIGN="left"
			CLASS="formSubHeading">Engineering Data</LEGEND>
		<TABLE WIDTH="100%" BORDER="0" ALIGN=center>
			<TR>
				<TD WIDTH="28%" ALIGN="left" CLASS="headerbgcolor">Reference EDL
				Number(s)</TD>
				<TD CLASS="formFieldSub"><html:text property="toRefEDLNo"
					styleClass="TEXTBOX2" size="20" maxlength="20" value="">
				</html:text>&nbsp;&nbsp; <html:text property="toRefEDLNo"
					styleClass="TEXTBOX2" size="20" maxlength="20" value=""></html:text>&nbsp;&nbsp;
				<html:text property="toRefEDLNo" styleClass="TEXTBOX2" size="20"
					maxlength="20" value=""></html:text></TD>
				<TD>&nbsp;</TD>
				<TD>&nbsp;</TD>
			</TR>
			<TR>
				<TD WIDTH="15%" ALIGN="left" CLASS="headerbgcolor">New EDL Number(s)</TD>
				<TD CLASS="formFieldSub"><html:text property="toEdlNo"
					styleClass="TEXTBOX2" size="20" maxlength="20" value=""></html:text>&nbsp;&nbsp;
				<html:text property="toEdlNo" styleClass="TEXTBOX2" size="20"
					maxlength="20" value=""></html:text>&nbsp;&nbsp; <html:text
					property="toEdlNo" styleClass="TEXTBOX2" size="20" maxlength="20"
					value=""></html:text></TD>
			</TR>
			<TR><!-- CR 87 -->
				<TD CLASS="headerbgcolor" ALIGN="left"><bean:message key="screen.partOf" /></TD>
				<TD CLASS="formFieldSub" COLSPAN="6"><html:text indexed="1"
					property="partOfCode" value="" styleClass="TEXTBOX2"
					readonly="true"></html:text>&nbsp;<A
					HREF="javascript:LoadAllClauses(1)" name="toPartOfSearch"><IMG
					SRC="images/search.gif" alt="Search" BORDER="0"></A> <A
					HREF="javascript:deletePartOfCode(1)" name="toPartOfDelete"><IMG
					SRC="images/delete1.gif" alt="Clear" BORDER="0"></A> <html:hidden
					property="partOfSeqNo" /> <html:hidden property="partOfclaSeqNo" />

				<html:text property="partOfCode" value="" styleClass="TEXTBOX2"
					readonly="true"></html:text>&nbsp;<A
					HREF="javascript:LoadAllClauses(2)" name="toPartOfSearch"><IMG
					SRC="images/search.gif" alt="Search" BORDER="0"></A> <A
					HREF="javascript:deletePartOfCode(2)" name="toPartOfDelete"><IMG
					SRC="images/delete1.gif" alt="Clear" BORDER="0"></A> <html:hidden
					property="partOfSeqNo" /> <html:hidden property="partOfclaSeqNo" />

				<html:text property="partOfCode" value="" styleClass="TEXTBOX2"
					readonly="true"></html:text>&nbsp;<A
					HREF="javascript:LoadAllClauses(3)" name="toPartOfSearch"><IMG
					SRC="images/search.gif" alt="Search" BORDER="0"></A> <A
					HREF="javascript:deletePartOfCode(3)" name="toPartOfDelete"><IMG
					SRC="images/delete1.gif" alt="Clear" BORDER="0"></A> <html:hidden
					property="partOfSeqNo" /> <html:hidden property="partOfclaSeqNo" />

				<html:text property="partOfCode" value="" styleClass="TEXTBOX2"
					readonly="true"></html:text>&nbsp;<A
					HREF="javascript:LoadAllClauses(4)" name="toPartOfSearch"><IMG
					SRC="images/search.gif" alt="Search" BORDER="0"></A> <A
					HREF="javascript:deletePartOfCode(4)" name="toPartOfDelete"><IMG
					SRC="images/delete1.gif" alt="Clear" BORDER="0"></A> <html:hidden
					property="partOfSeqNo" /> <html:hidden property="partOfclaSeqNo" />

				</TD>
			</TR>
			<TR>
				<TD CLASS="headerbgcolor" ALIGN="left">DWO Number</TD>
				<TD CLASS="formFieldSub"><html:text property="todwONumber" value=""
					styleClass="TEXTBOX2" size="20" maxlength="20"></html:text></TD>
			</TR>
			</TR>
			<TR>
				<TD CLASS="headerbgcolor" ALIGN="left">Part Number</TD>
				<TD CLASS="formFieldSub"><html:text property="toPartNumber" value=""
					styleClass="TEXTBOX2" size="20" maxlength="8"></html:text></TD>
			</TR>
			<TR>
				<TD CLASS="headerbgcolor" ALIGN="left">Price Book Number</TD>
				<TD CLASS="formFieldSub"><html:text property="topriceBookNumber" styleId="priceBookNo"
					value="" styleClass="TEXTBOX2" size="20" maxlength="4"></html:text>
				</TD>
			</TR>
			<TR>
				<TD CLASS="headerbgcolor" WIDTH="15%">Standard Equipment</TD>

				<TD COLSPAN="3"><html:select name="CreateChangeClauseRequestForm"
					property="standardEquipmentSeqNo" styleClass="selectstyle2">
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
				<TD>&nbsp; <textarea name="toComments" rows="4" cols="60"
					wrap="hard"></textarea></TD>
			</TR>
		</TABLE>
		</FIELDSET>
		</TD>
	</TR>
</TABLE>
