<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

	<!-- Added for CR_80 CR Form Enhancements III on 23-Nov-09 by RR68151 -->
	<BR>
	<TABLE WIDTH="88%" BORDER="0" ALIGN=center>
		<TR>
			<TD WIDTH="16%" ALIGN="RIGHT" CLASS="header">New Clause :&nbsp; <logic:equal
				name="CreateChangeClauseRequestForm" property="changeTypeSeqNO"
				value="4">
				<INPUT CLASS=radcheck NAME=changeTypeSeqNO TYPE=radio value="4"
					onclick="javascript:fnEnableDisableFields(4)" checked>&nbsp;
		</TD>
			<TD WIDTH="18%" ALIGN=center CLASS="header">Modify Clause:&nbsp; <INPUT
				CLASS=radcheck NAME=changeTypeSeqNO TYPE=radio value="5"
				onclick="javascript:fnEnableDisableFields(5)"></TD>
			<TD WIDTH="21%" ALIGN=left CLASS="header" nowrap>Change Default
			Version:&nbsp; <INPUT CLASS=radcheck NAME=changeTypeSeqNO value="6"
				TYPE=radio onclick="javascript:fnEnableDisableFields(6)"> 
			<TD WIDTH="20%" ALIGN=left CLASS="header" nowrap>Delete Clause
			Version:&nbsp; <INPUT CLASS=radcheck NAME=changeTypeSeqNO value="9"
				TYPE=radio onclick="javascript:fnEnableDisableFields(9)">
			<TD WIDTH="20%" ALIGN=left CLASS="header" nowrap>Delete Clause
			:&nbsp; <INPUT CLASS=radcheck NAME=changeTypeSeqNO value="10"
				TYPE=radio onclick="javascript:fnEnableDisableFields(10)"> </logic:equal>

			<logic:equal name="CreateChangeClauseRequestForm"
				property="changeTypeSeqNO" value="5">
				<INPUT CLASS=radcheck NAME=changeTypeSeqNO TYPE=radio value="4"
					onclick="javascript:fnEnableDisableFields(4)">&nbsp;
		</TD>
			<TD WIDTH="18%" ALIGN=center CLASS="header">Modify Clause:&nbsp; <INPUT
				CLASS=radcheck NAME=changeTypeSeqNO TYPE=radio value="5"
				onclick="javascript:fnEnableDisableFields(5)" checked></TD>
			<TD WIDTH="21%" ALIGN=left CLASS="header" nowrap>Change Default
			Version:&nbsp; <INPUT CLASS=radcheck NAME=changeTypeSeqNO value="6"
				TYPE=radio onclick="javascript:fnEnableDisableFields(6)"> 
			<TD WIDTH="20%" ALIGN=left CLASS="header" nowrap>Delete Clause
			Version:&nbsp; <INPUT CLASS=radcheck NAME=changeTypeSeqNO value="9"
				TYPE=radio onclick="javascript:fnEnableDisableFields(9)">
			<TD WIDTH="20%" ALIGN=left CLASS="header" nowrap>Delete Clause
			:&nbsp; <INPUT CLASS=radcheck NAME=changeTypeSeqNO value="10"
				TYPE=radio onclick="javascript:fnEnableDisableFields(10)"></logic:equal>

			<logic:equal name="CreateChangeClauseRequestForm"
				property="changeTypeSeqNO" value="6">
				<INPUT CLASS=radcheck NAME=changeTypeSeqNO TYPE=radio value="4"
					onclick="javascript:fnEnableDisableFields(4)">&nbsp;
		</TD>
			<TD WIDTH="18%" ALIGN=center CLASS="header">Modify Clause:&nbsp; <INPUT
				CLASS=radcheck NAME=changeTypeSeqNO TYPE=radio value="5"
				onclick="javascript:fnEnableDisableFields(5)"></TD>
			<TD WIDTH="21%" ALIGN=left CLASS="header" nowrap>Change Default
			Version:&nbsp; <INPUT CLASS=radcheck NAME=changeTypeSeqNO value="6"
				TYPE=radio onclick="javascript:fnEnableDisableFields(6)" checked> 
			<TD WIDTH="20%" ALIGN=left CLASS="header" nowrap>Delete Clause
			Version:&nbsp; <INPUT CLASS=radcheck NAME=changeTypeSeqNO value="9"
				TYPE=radio onclick="javascript:fnEnableDisableFields(9)">
			<TD WIDTH="20%" ALIGN=left CLASS="header" nowrap>Delete Clause
			:&nbsp; <INPUT CLASS=radcheck NAME=changeTypeSeqNO value="10"
				TYPE=radio onclick="javascript:fnEnableDisableFields(10)"></logic:equal>
				
			<logic:equal name="CreateChangeClauseRequestForm"
				property="changeTypeSeqNO" value="9">
				<INPUT CLASS=radcheck NAME=changeTypeSeqNO TYPE=radio value="4"
					onclick="javascript:fnEnableDisableFields(4)">&nbsp;
		</TD>
			<TD WIDTH="18%" ALIGN=center CLASS="header">Modify Clause:&nbsp; <INPUT
				CLASS=radcheck NAME=changeTypeSeqNO TYPE=radio value="5"
				onclick="javascript:fnEnableDisableFields(5)"></TD>
			<TD WIDTH="21%" ALIGN=left CLASS="header" nowrap>Change Default
			Version:&nbsp; <INPUT CLASS=radcheck NAME=changeTypeSeqNO value="6"
				TYPE=radio onclick="javascript:fnEnableDisableFields(6)"> 
			<TD WIDTH="20%" ALIGN=left CLASS="header" nowrap>Delete Clause
			Version:&nbsp; <INPUT CLASS=radcheck NAME=changeTypeSeqNO value="9"
				TYPE=radio onclick="javascript:fnEnableDisableFields(9)" checked>
			<TD WIDTH="20%" ALIGN=left CLASS="header" nowrap>Delete Clause
			:&nbsp; <INPUT CLASS=radcheck NAME=changeTypeSeqNO value="10"
				TYPE=radio onclick="javascript:fnEnableDisableFields(10)"></logic:equal>
				
			<logic:equal name="CreateChangeClauseRequestForm"
				property="changeTypeSeqNO" value="10">
				<INPUT CLASS=radcheck NAME=changeTypeSeqNO TYPE=radio value="4"
					onclick="javascript:fnEnableDisableFields(4)">&nbsp;
		</TD>
			<TD WIDTH="18%" ALIGN=center CLASS="header">Modify Clause:&nbsp; <INPUT
				CLASS=radcheck NAME=changeTypeSeqNO TYPE=radio value="5"
				onclick="javascript:fnEnableDisableFields(5)"></TD>
			<TD WIDTH="21%" ALIGN=left CLASS="header" nowrap>Change Default
			Version:&nbsp; <INPUT CLASS=radcheck NAME=changeTypeSeqNO value="6"
				TYPE=radio onclick="javascript:fnEnableDisableFields(6)"> 
			<TD WIDTH="20%" ALIGN=left CLASS="header" nowrap>Delete Clause
			Version:&nbsp; <INPUT CLASS=radcheck NAME=changeTypeSeqNO value="9"
				TYPE=radio onclick="javascript:fnEnableDisableFields(9)">
			<TD WIDTH="20%" ALIGN=left CLASS="header" nowrap>Delete Clause
			:&nbsp; <INPUT CLASS=radcheck NAME=changeTypeSeqNO value="10"
				TYPE=radio onclick="javascript:fnEnableDisableFields(10)" checked></logic:equal>
			</TD>
		</TR>
	</TABLE>
