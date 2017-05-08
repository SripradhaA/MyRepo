
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>



<HEAD>
<SCRIPT LANGUAGE="JavaScript" SRC="js/ModelSelectClauseRev.js"></SCRIPT>

</HEAD>


<html:form action="/ModelSelectClauseAction" method="post">

	<!-- Application PageName Display starts here-->

	<TABLE WIDTH="100%" BORDER="0" CELLPADDING="0" CELLSPACING="0"
		VALIGN="TOP">
		<BR>
		<TR>
			<TD WIDTH="60%" CLASS="dashBoardSubHeading" ALIGN="center"><bean:message
				key="Application.Screen.ModifyClause" /></TD>
		</TR>
	</TABLE>

	<!-- Application PageName Display Ends here-->

	<!-- Validation Message Display Part Starts Here -->

	<table border="0" cellpadding="0" cellspacing="0" width="20%"
		align="center">
		<tr>
			<BR>
			<td nowrap="nowrap"> 
			<div class="errorlayerhide" id="errorlayer">
			</div></td>
		</tr>
	</table>

	<!-- Validation Message Display Part Ends Here -->

	<!-- Logic Tag Check For Display The Success Message After Upload and Delete Functionality Starts Here -->

	<logic:present name="ModelSelectClauseRevForm" property="messageID"
		scope="request">

		<bean:define id="messageID" name="ModelSelectClauseRevForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>



	</logic:present>
	<!-- Logic Tag Check For Display The Success Message After Upload and Delete Functionality Starts Here -->

	<!-- -->


	<!-- -->
	<logic:present name="ModelSelectClauseRevForm" property="clauseList"
		scope="request">
		<bean:size name="ModelSelectClauseRevForm" id="clsize"
			property="clauseList" />
	</logic:present>

	<logic:present name="ModelSelectClauseRevForm"
		property="stdEquipmentList" scope="request">
		<bean:size name="ModelSelectClauseRevForm" id="stdsize"
			property="stdEquipmentList" />
	</logic:present>

	<logic:present name="ModelSelectClauseRevForm"
		property="clauseVersions" scope="request">
		<bean:size name="ModelSelectClauseRevForm" id="allversize"
			property="clauseVersions" />
	</logic:present>

	<logic:match name="ModelSelectClauseRevForm" property="method"
		value="fetchClauses">
		<logic:lessThan name="clsize" value="1">
			<script> 
              fnNoRecords();
        	</script>
		</logic:lessThan>
	</logic:match>

	<TABLE BORDER="0" WIDTH="75%" CELLSPACING="1" CELLPADDING="1"
		ALIGN="center">
		<BR>
		<BR>
		<TR>
			<TD>
			<TABLE ALIGN="right" WIDTH="80%" BORDER="0">
				<TR>
					<!-- Added for LSDB_CR_46 -->
					<TD WIDTH="8%" ALIGN="left" CLASS="headerbgcolor" nowrap>Specification
					Type&nbsp;<font size=2 color="red">*</font></TD>
					<TD WIDTH="8%"><html:select name="ModelSelectClauseRevForm"
						property="specTypeNo" styleClass="selectstyle2"
						onchange="javascript:fetchModels()">
						<option selected value="-1">---Select---</option>
						<html:optionsCollection name="ModelSelectClauseRevForm"
							property="specTypeList" value="spectypeSeqno"
							label="spectypename" />
					</html:select></TD>

					<TD WIDTH="35%" ALIGN="left" CLASS="headerbgcolor" nowrap>Model<Font
						color="red">&nbsp;*</Font></TD>

					<TD WIDTH="10%"><html:select name="ModelSelectClauseRevForm"
						property="modelseqno" styleClass="selectstyle2">
						<option selected value="-1">---Select---</option>
						<logic:present name="ModelSelectClauseRevForm"
							property="listModels">
							<html:optionsCollection name="ModelSelectClauseRevForm"
								property="listModels" value="modelSeqNo" label="modelName" />
						</logic:present>
					</html:select></TD>
					<TD WIDTH=30%"></TD>
					<TD COLSPAN="2" WIDTH="10%" ALIGN="LEFT" CLASS="headerbgcolor"
						NOWRAP HEIGHT="20">Clause Description<font color="red">&nbsp;*</font>&nbsp;<A
						HREF="javascript:LoadClauseDesc()"><IMG SRC="images/search.gif"
						alt="Search" BORDER="0"></A></TD>
				</TR>
			</TABLE>
			</TD>
			<!-- Clause description div width is reduced from 400 to 350
				Reason: To avoid the horizontal scroll bar.
				Modified on 29-09-08 by ps57222
			-->
			<TD>
			<TABLE ALIGN="LEFT" WIDTH="70%" BORDER="0">
				<TR>

					<TD WIDTH="20%"><span style="width:350px">
					<DIV id="clauseDesc"
						STYLE="float:left;height:60;width:350;overflow: auto ; "><logic:empty
						name="ModelSelectClauseRevForm" property="clauseDes">
					</logic:empty> <logic:notEmpty name="ModelSelectClauseRevForm"
						property="clauseDes">
						<bean:define name="ModelSelectClauseRevForm" property="clauseDes"
							id="clauseDes" />
						<%=clauseDes %>
					</logic:notEmpty></DIV>
					</span></TD>
					<TD WIDTH="5%"></TD>
					<TD WIDTH="10%"><html:button property="method"
						styleClass="buttonStyle"
						onclick="javascript:fnFetchClauseversions()">
	            Search
	            </html:button></TD>
				</TR>

			</TABLE>
			</TD>


		</TR>
	</TABLE>

	<html:hidden property="subSectionSeqNo" />
	<html:hidden property="clauseSeqNo" />
	<html:hidden property="hdnModelName" />
	<html:hidden property="hdnClauseVersionNo" />
	<!-- <html:hidden property="versionNo"/> -->
	<html:hidden property="hdPreSelectedModel" />
	<html:hidden property="clauseDes" />
	<html:hidden property="prevSpecType" />

	<logic:greaterThan name="allversize" value="0">
		<logic:present name="ModelSelectClauseRevForm"
			property="clauseVersions">
			<HR>
			<TABLE align=center BORDER="0" width="80%">
				<TR>
					<TD WIDTH="60%" CLASS="dashBoardSubHeading" ALIGN="center">Select
					Clause Revision</TD>
				</TR>
				<TR>
					<TD>&nbsp;</TD>
				</TR>
			</TABLE>
			<TABLE align=center BORDER="1" bordercolor="#5780ae" width="95%" class= "table table-bordered">
				<BR>
				<TR>
					<TD class="table_heading">Select</TD>
					<TD class="table_heading">Version No</TD>
					<TD class="table_heading">Clause Description</TD>
					<TD class="table_heading">Engineering Data</TD>
					<TD class='table_heading'>Customer</TD>
					<TD class='table_heading'>Modified By</TD>
					<TD class='table_heading'>Modified Date</TD>
					<TD class='table_heading'>Default</TD>
					<!--  <TD class="table_heading">Default</TD>-->
				</TR>

				<logic:iterate id="clauseRev" name="ModelSelectClauseRevForm"
					property="clauseVersions" type="com.EMD.LSDB.vo.common.ClauseVO">

					<TR>
						<!-- <html:hidden name="clauseRev" property="versionNo"/> -->

						<TD class="borderbottomleft1"><html:radio
							value="<%=String.valueOf(clauseRev.getVersionNo())%>"
							styleClass="radcheck" property="versionNo" /></TD>
						<TD class="borderbottomleft1"><bean:write name="clauseRev"
							property="versionNo" /></TD>

						<TD class="borderbottomleft"><span style="width:435px">
						<DIV STYLE="float:left;height:60;width:435;overflow: auto ; "><bean:define
							name="clauseRev" property="clauseDesc" id="clauseDesc" />
							<!-- CR-128 - Updated for Pdf issue -->
								<%String strClauseDesc =  String.valueOf(clauseDesc);
								if(strClauseDesc.startsWith("<p>")){%>
									<%=(String.valueOf(clauseDesc))%>
								<%}else{ %>	
									<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>							
								<%}%>
							<!-- CR-128 - Updated for Pdf issue Ends Here-->
						
						

							<logic:notEmpty name="clauseRev" property="tableArrayData1">
								<table width="100%" BORDER="1" BORDERCOLOR="" class= "table table-bordered">
								<logic:iterate id="outter" name="clauseRev"
									property="tableArrayData1" indexId="counter">

									<bean:define id="row" name="counter" />
									<tr>
										<logic:iterate id="tabrow" name="outter">
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
								</table>
							</logic:notEmpty>

						</DIV>
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

						</logic:notEmpty> -->
						<logic:notEmpty name="clauseRev"
							property="edlNO">

							<logic:iterate id="edl" name="clauseRev" property="edlNO">
								<bean:message key="screen.edl" />&nbsp;
	           <bean:write name="edl" />
								<BR>
							</logic:iterate>

						</logic:notEmpty> 
						<!-- CR 87 -->
						<logic:notEmpty name="clauseRev" property="refEDLNO">
							<logic:iterate id="refedl" name="clauseRev" property="refEDLNO">
								<bean:message key="screen.refEdl.start" />&nbsp;
						           <bean:write name="refedl" />
			           			<bean:message key="screen.refEdl.end" />&nbsp;
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


						<TD class="borderbottomleft" valign="top"><logic:empty
							name="clauseRev" property="customerName">
	            &nbsp;
	            </logic:empty> <logic:notEmpty name="clauseRev"
							property="customerName">
							<bean:write name="clauseRev" property="customerName" />
						</logic:notEmpty></TD>


						<TD class="borderbottomleft" valign="top"><logic:empty
							name="clauseRev" property="modifiedBy">
	            &nbsp;
	            </logic:empty> <logic:notEmpty name="clauseRev"
							property="modifiedBy">
							<bean:write name="clauseRev" property="modifiedBy" />
						</logic:notEmpty></TD>


						<TD class="borderbottomleft" valign="top"><logic:empty
							name="clauseRev" property="modifiedDate">
	            &nbsp;
	            </logic:empty> <logic:notEmpty name="clauseRev"
							property="modifiedDate">
							<bean:write name="clauseRev" property="modifiedDate" />
						</logic:notEmpty></TD>
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
			<TABLE WIDTH="95%" CELLSPACING="1" CELLPADDING="1" ALIGN="center"
				BORDER=0>
				<BR>

				<TR>

					<TD Align="right" width="10%">&nbsp;</TD>

					<TD Align="center" width="80%"><html:button property="method"
						styleClass="buttonStyle" onclick="javascript:fnApplyDefault()">
						Apply Default
					</html:button> &nbsp; <html:button property="method"
						styleClass="buttonStyle" onclick="javascript:fnDeleteVersion()">
					Delete Version
					</html:button> <!--&nbsp;
					<html:button property="method" styleClass="buttonStyle"  onclick="javascript:fnLoadClauseVersion()">
					Load Clause
					</html:button> --> <!-- LSDB_CR-53 UPDATE CLAUSE CR IS COMMENTED OUT -->
					</TD>
					<TD Align="right" width="5%"><html:button property="method"
						styleClass="buttonStyle" onclick="javascript:fnDeleteClause()">
						Delete Clause
					</html:button></TD>
				</TR>
			</TABLE>
		</logic:present>
	</logic:greaterThan>

	<logic:greaterThan name="clsize" value="0">
		<logic:iterate id="check" name="ModelSelectClauseRevForm"
			property="clauseList" type="com.EMD.LSDB.vo.common.ClauseVO"
			scope="request">

			<HR>
			<TABLE align=center BORDER="0" width="80%">
				<TR>
					<TD WIDTH="60%" CLASS="dashBoardSubHeading" ALIGN="center">Add
					Clause Revision</TD>
				</TR>
				<TR>
					<TD>&nbsp;</TD>
				</TR>
			</TABLE>
			<TABLE BORDER="0" WIDTH="85%" ALIGN=center>
				<TR>
					<TD WIDTH="22%" CLASS="headerbgcolor">New Clause Description <font
						color="red">*</font></TD>
					<TD COLSPAN="3"><html:textarea property="clauseDescForTextArea"
						name="check" rows="10" cols="92" styleClass="CLAUSEDESCTEXTAREA">

					</html:textarea> <input type="hidden" name="selectedVersion"
						value="<%=check.getVersionNo()%>" /></TD>

				</TR>
				<!-- TableData  Display Part Starts Here -->

				<logic:empty name="check" property="tableArrayData1">
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
				<logic:notEmpty name="check" property="tableArrayData1">
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

							<logic:notEmpty name="check" property="tableArrayData1">
								<logic:iterate id="data" name="check" property="tableArrayData1"
									indexId="counter">
									<TR>
										<logic:iterate id="data1" name="data" indexId="innerCounter">
											<bean:define id="innerSize" name="innerCounter" />

											<TD><bean:define id="size" name="counter" /> <logic:equal
												name="size" value="0">
												<!--This outer logic:equal check is for display the first row with bold font -->
												<logic:lessThan name="innerSize" value="1">
													<!--This Inner logic:lessthan check is for display the first row first text box -->
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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

				<!-- TableData  Display Part Ends Here -->
				<TR>
					<TD WIDTH="10%" CLASS="headerbgcolor">Apply New Clause As Default</TD>
					<TD><B>Yes</B><input type="radio" class="radcheck"
						name="applyToDefault" value="Y"> <B>No</B><input type="radio"
						class="radcheck" name="applyToDefault" value="N" checked></TD>
				</TR>
			</TABLE>
			<TABLE ALIGN="CENTER" BORDER="0" WIDTH="100%">
				<TR>
					<TD WIDTH="6%">
					<TABLE>
						<TR>
							<TD>&nbsp;</TD>
						</TR>
					</TABLE>
					</TD>
					<TD WIDTH="90%">
					<TABLE>
						<TR>
							<TD ALIGN="center">
							<FIELDSET CLASS="fieldset5"><LEGEND ALIGN="left"
								CLASS="formSubHeading">Engineering Data <font color="red">*</font></LEGEND>
							<TABLE BORDER="0" WIDTH="100%">
								<TR>
									<TD WIDTH="30%" CLASS="headerbgcolor">Reference EDL Number(s)</TD>
				<!-- Added For CR_81 on 24-Dec-09 by ------- -->						
<logic:equal name="check" property="selectCGCFlag" value="N">

									<TD COLSPAN="3"><logic:notEmpty name="check"
										property="refEDLNo">

										<html:text property="refEDLNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" value='<%=check.getRefEDLNo()[0]%>'></html:text>&nbsp;&nbsp;
												<html:text property="refEDLNo" styleClass="TEXTBOX2"
											size="20" maxlength="20" value='<%=check.getRefEDLNo()[1]%>'></html:text>&nbsp;&nbsp;
												<html:text property="refEDLNo" styleClass="TEXTBOX2"
											size="20" maxlength="20" value='<%=check.getRefEDLNo()[2]%>' />

									</logic:notEmpty> <logic:empty name="check" property="refEDLNo">
										<html:text property="refEDLNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" value=""></html:text>&nbsp;&nbsp;
												<html:text property="refEDLNo" styleClass="TEXTBOX2"
											size="20" maxlength="20" value=""></html:text>&nbsp;&nbsp;
												<html:text property="refEDLNo" styleClass="TEXTBOX2"
											size="20" maxlength="20" value=""></html:text> -->
										</logic:empty></TD>
</logic:equal>


<logic:equal name="check" property="selectCGCFlag" value="Y">

									<TD COLSPAN="3"><logic:notEmpty name="check"
										property="refEDLNo">

										<html:text property="refEDLNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" value='<%=check.getRefEDLNo()[0]%>' disabled="true"></html:text>&nbsp;&nbsp;
												<html:text property="refEDLNo" styleClass="TEXTBOX2"
											size="20" maxlength="20" value='<%=check.getRefEDLNo()[1]%>' disabled="true"></html:text>&nbsp;&nbsp;
												<html:text property="refEDLNo" styleClass="TEXTBOX2"
											size="20" maxlength="20" value='<%=check.getRefEDLNo()[2]%>' disabled="true" />

									</logic:notEmpty> <logic:empty name="check" property="refEDLNo">
										<html:text property="refEDLNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" value="" disabled="true"></html:text>&nbsp;&nbsp;
												<html:text property="refEDLNo" styleClass="TEXTBOX2"
											size="20" maxlength="20" value="" disabled="true"></html:text>&nbsp;&nbsp;
												<html:text property="refEDLNo" styleClass="TEXTBOX2"
											size="20" maxlength="20" value="" disabled="true"></html:text> -->
										</logic:empty></TD>
</logic:equal>
<!-- Added For CR_81 on 24-Dec-09 by ------- - Ends here-->
								</TR>
								<TR>
									<TD CLASS="headerbgcolor">New EDL Number(s)</TD>
									<TD COLSPAN=3>
		<!-- Added For CR_81 on 24-Dec-09 by ------- -->						
<logic:equal name="check" property="selectCGCFlag" value="N">
									<logic:notEmpty name="check" property="edlNo">

										<html:text property="edlNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" value='<%=check.getEdlNo()[0]%>' ></html:text>&nbsp;&nbsp;
												<html:text property="edlNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" value='<%=check.getEdlNo()[1]%>'></html:text>&nbsp;&nbsp;
												<html:text property="edlNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" value='<%=check.getEdlNo()[2]%>' />
									</logic:notEmpty> <logic:empty name="check" property="edlNo">
										<html:text property="edlNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" value=""></html:text>
												&nbsp;&nbsp;
												<html:text property="edlNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" value=""></html:text>
												&nbsp;&nbsp;
												<html:text property="edlNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" value=""></html:text>
									</logic:empty></TD>
									
									</logic:equal>
							<logic:equal name="check" property="selectCGCFlag" value="Y">
									<logic:notEmpty name="check" property="edlNo">

										<html:text property="edlNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" value='<%=check.getEdlNo()[0]%>' disabled="true"></html:text>&nbsp;&nbsp;
												<html:text property="edlNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" value='<%=check.getEdlNo()[1]%>' disabled="true"></html:text>&nbsp;&nbsp;
												<html:text property="edlNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" value='<%=check.getEdlNo()[2]%>' disabled="true"/>
									</logic:notEmpty> <logic:empty name="check" property="edlNo">
										<html:text property="edlNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" value="" disabled="true"></html:text>
												&nbsp;&nbsp;
												<html:text property="edlNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" value="" disabled="true"></html:text>
												&nbsp;&nbsp;
												<html:text property="edlNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" value="" disabled="true"></html:text>
									</logic:empty></TD>
									
									</logic:equal>
									
									<!-- Added For CR_81 on 24-Dec-09 by sd41630------- - Ends here-->
								</TR>
								<TR><!-- CR 87 -->
									<TD CLASS="headerbgcolor"><bean:message key="screen.partOf" /></TD>
									<TD COLSPAN="5"><logic:notEmpty name="check"
										property="partOfSeqNo">
										<logic:notEmpty name="check" property="partOfCode">

											<html:text indexed="1" property="partOfCode"
												value='<%=(check.getPartOfCode()[0])=="0" ? "":check.getPartOfCode()[0] %>'
												styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;<A
												HREF="javascript:LoadAllClauses(1)"><IMG
												SRC="images/search.gif" alt="Search" BORDER="0"></A>
											<A HREF="javascript:deletePartOfCode(1)"><IMG
												SRC="images/delete1.gif" alt="Delete" BORDER="0"></A>




											<html:hidden property="partOfSeqNo"
												value='<%=String.valueOf(check.getPartOfSeqNo()[0])%>' />

											<html:hidden property="partOfclaSeqNo"
												value='<%=String.valueOf(check.getPartOfClaSeqNo()[0])%>' />

											<html:text property="partOfCode"
												value='<%=(check.getPartOfCode()[1])=="0" ? "":check.getPartOfCode()[1] %>'
												styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;<A
												HREF="javascript:LoadAllClauses(2)"><IMG
												SRC="images/search.gif" alt="Search" BORDER="0"></A>
											<A HREF="javascript:deletePartOfCode(2)"><IMG
												SRC="images/delete1.gif" alt="Delete" BORDER="0"></A>
											<html:hidden property="partOfSeqNo"
												value='<%=String.valueOf(check.getPartOfSeqNo()[1])%>' />

											<html:hidden property="partOfclaSeqNo"
												value='<%=String.valueOf(check.getPartOfClaSeqNo()[1])%>' />


											<html:text property="partOfCode"
												value='<%=(check.getPartOfCode()[2])=="0" ? "":check.getPartOfCode()[2] %>'
												styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;<A
												HREF="javascript:LoadAllClauses(3)"><IMG
												SRC="images/search.gif" alt="Search" BORDER="0"></A>
											<A HREF="javascript:deletePartOfCode(3)"><IMG
												SRC="images/delete1.gif" alt="Delete" BORDER="0"></A>
											<html:hidden property="partOfSeqNo"
												value='<%=String.valueOf(check.getPartOfSeqNo()[2])%>' />

											<html:hidden property="partOfclaSeqNo"
												value='<%=String.valueOf(check.getPartOfClaSeqNo()[2])%>' />

											<html:text property="partOfCode"
												value='<%=(check.getPartOfCode()[3])=="0" ? "":check.getPartOfCode()[3] %>'
												styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;<A
												HREF="javascript:LoadAllClauses(4)"><IMG
												SRC="images/search.gif" alt="Search" BORDER="0"></A>
											<A HREF="javascript:deletePartOfCode(4)"><IMG
												SRC="images/delete1.gif" alt="Delete" BORDER="0"></A>
											<html:hidden property="partOfSeqNo"
												value='<%=String.valueOf(check.getPartOfSeqNo()[3])%>' />

											<html:hidden property="partOfclaSeqNo"
												value='<%=String.valueOf(check.getPartOfClaSeqNo()[3])%>' />

										</logic:notEmpty>
									</logic:notEmpty> <logic:empty name="check"
										property="partOfCode">
										<html:text indexed="1" property="partOfCode" value=""
											styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;<A
											HREF="javascript:LoadAllClauses(1)"><IMG
											SRC="images/search.gif" alt="Search" BORDER="0"></A>
										<A HREF="javascript:deletePartOfCode(1)"><IMG
											SRC="images/delete1.gif" alt="Delete" BORDER="0"></A>
										<html:hidden property="partOfSeqNo" />
										<html:hidden property="partOfclaSeqNo" />

										<html:text property="partOfCode" value=""
											styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;<A
											HREF="javascript:LoadAllClauses(2)"><IMG
											SRC="images/search.gif" alt="Search" BORDER="0"></A>
										<A HREF="javascript:deletePartOfCode(2)"><IMG
											SRC="images/delete1.gif" alt="Delete" BORDER="0"></A>
										<html:hidden property="partOfSeqNo" />
										<html:hidden property="partOfclaSeqNo" />

										<html:text property="partOfCode" value=""
											styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;<A
											HREF="javascript:LoadAllClauses(3)"><IMG
											SRC="images/search.gif" alt="Search" BORDER="0"></A>
										<A HREF="javascript:deletePartOfCode(3)"><IMG
											SRC="images/delete1.gif" alt="Delete" BORDER="0"></A>
										<html:hidden property="partOfSeqNo" />
										<html:hidden property="partOfclaSeqNo" />

										<html:text property="partOfCode" value=""
											styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;<A
											HREF="javascript:LoadAllClauses(4)"><IMG
											SRC="images/search.gif" alt="Search" BORDER="0"></A>
										<A HREF="javascript:deletePartOfCode(4)"><IMG
											SRC="images/delete1.gif" alt="Delete" BORDER="0"></A>
										<html:hidden property="partOfSeqNo" />
										<html:hidden property="partOfclaSeqNo" />
									</logic:empty></TD>
								</TR>
								<TR>
									<TD CLASS="headerbgcolor" ALIGN="left">DWO Number</TD>
									<TD COLSPAN="5"><logic:equal name="check" property="dwONumber"
										value="0">
										<html:text property="dwONumber" styleClass="TEXTBOX2"
											size="20" maxlength="20" value="" />
									</logic:equal> <logic:greaterThan name="check"
										property="dwONumber" value="0">
										<html:text name="check" property="dwONumber"
											styleClass="TEXTBOX2" size="20" maxlength="20" />
									</logic:greaterThan></TD>
								</TR>
								<TR>
									<TD CLASS="headerbgcolor" ALIGN="left">Part Number</TD>
									<TD COLSPAN="5"><logic:equal name="check" property="partNumber"
										value="0">
										<html:text property="partNumber" styleClass="TEXTBOX2"
											size="20" maxlength="8" value="" />
									</logic:equal> <logic:greaterThan name="check"
										property="partNumber" value="0">
										<html:text property="partNumber" name="check"
											styleClass="TEXTBOX2" size="20" maxlength="8" />
									</logic:greaterThan></TD>
								</TR>

								<!-- Maxlength of Price book number is changed from 20 to 4.Modified on 29-09-08 by ps57222 -->

								<TR>
									<TD CLASS="headerbgcolor" ALIGN="left">Price Book Number</TD>
									<TD COLSPAN="5"><logic:greaterThan name="check"
										property="priceBookNumber" value="0">
										<html:text property="priceBookNumber" name="check"
											styleClass="TEXTBOX2" size="20" maxlength="4"></html:text>
									</logic:greaterThan> <logic:equal name="check"
										property="priceBookNumber" value="0">
										<html:text property="priceBookNumber" styleClass="TEXTBOX2"
											size="20" maxlength="4" value=""></html:text>
									</logic:equal></TD>
								</TR>


								<TR>
									<TD CLASS="headerbgcolor">Engineering Data</TD>
									<TD COLSPAN="3"><html:select property="standardEquipmentSeqNo"
										styleClass="selectstyle2">
										<option value="-1">--Select--</option>
										<logic:present name="ModelSelectClauseRevForm"
											property="stdEquipmentList">
											<logic:iterate id="stdEquip" name="ModelSelectClauseRevForm"
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
										cols="60"></html:textarea></TD>
								</TR>
							</TABLE>
							</FIELDSET>
							</TD>
						</TR>
					</TABLE>
					</TD>
				</TR>
			</TABLE>
			<TR>
				<TD>&nbsp;</TD>
			</TR>
			</TABLE>
			<TABLE WIDTH="100%">
				<TR>
					<TD WIDTH="50%">&nbsp;</TD>
					<TD WIDTH="50%">&nbsp;</TD>
				</TR>
				<TR>
					<TD ALIGN="right"><html:button property="addButton"
						styleClass="buttonStyle" value="Add"
						onclick="javascript:fnAddClause()">
					</html:button></TD>
					<!-- LSDB_CR-53 UPDATE CLAUSE CR IS COMMENTED OUT -->
					<!-- <TD Align="left">
			<html:button property="modifyButton" styleClass="buttonStyle" value="Modify" onclick="javascript:fnUpdateClause()">
			</html:button>
		</TD> -->
				</TR>
			</TABLE>

		</logic:iterate>
	</logic:greaterThan>
</html:form>


