<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<HEAD>
<SCRIPT LANGUAGE="JavaScript" SRC="js/ModelAddClauseRev.js"></SCRIPT>
</HEAD>

<html:form action="/ModAddClauseRev" method="post">

	<!-- Application PageName Display starts here-->

	<TABLE WIDTH="100%" BORDER="0" CELLPADDING="0" CELLSPACING="0">
		<TR>
			<TD>&nbsp;</TD>
		</TR>
		<TR>
			<TD WIDTH="60%" CLASS="dashBoardSubHeading" ALIGN="CENTER"><bean:message
				key="Application.Screen.clausemaint" /></TD>
		</TR>
	</TABLE>

	<!-- Application PageName Display Ends here -->

	<!-- Validation Message Display Part Starts Here -->

	<table border="0" cellpadding="0" cellspacing="0" width="20%"
		align="center">
		<TR>
			<TD>&nbsp;</TD>
		</TR>
		<tr>
			<td nowrap="nowrap"> 
			<div class="errorlayerhide" id="errorlayer">
			</div><!--<img src="images/spacer.gif" height="1" width="1">--></div>
			</td>
		</tr>
	</table>

	<!-- Validation Message Display Part Ends Here -->

	<logic:present name="ModAddClauseRevForm" property="messageID"
		scope="request">
			<bean:define id="messageID" name="ModAddClauseRevForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>

	</logic:present>

	<logic:present name="ModAddClauseRevForm" property="clauseList"
		scope="request">
		<bean:size name="ModAddClauseRevForm" id="clsize"
			property="clauseList" />
	</logic:present>

	<logic:match name="ModAddClauseRevForm" property="method"
		value="fetchClauses">
		<logic:lessThan name="clsize" value="1">
					<script> 
              			fnNoRecords();
        			</script>
					
		</logic:lessThan>
	</logic:match>




	<logic:present name="ModAddClauseRevForm" property="stdEquipmentList"
		scope="request">
		<bean:size name="ModAddClauseRevForm" id="stdsize"
			property="stdEquipmentList" />
	</logic:present>

	<TABLE WIDTH="100%" ALIGN="CENTER" BORDER="0" CELLPADDING="0"
		CELLSPACING="0">
		<TR>
			<TD>&nbsp;</TD>
		</TR>
		<TR>

			<TD WIDTH="60%" CLASS="dashBoardSubHeading" ALIGN="center"><html:link
				page="/ModelSelectClauseAction.do?method=initLoad"
				styleClass="linkstyleItem">
			Select Clause Revision
		</html:link>&nbsp;| Add Clause Revision</TD>
		</TR>
		<TR>
			<TD>&nbsp;</TD>
		</TR>
	</TABLE>
	<html:hidden property="clauseSeqNo" />
	<html:hidden property="versionNo" />
	<html:hidden property="subSectionSeqNo" />
	<html:hidden property="hdSelectedModel" />
	<html:hidden property="hdPreSelectedModel" />
	<input type="hidden" name="clauseDes" />
	<TABLE ALIGN="center" BORDER="0" WIDTH="70%">
		<TR>
			<TD>
			<TABLE ALIGN="center" BORDER="0" WIDTH="95%">
				<TR>
					<TD>
					<TABLE ALIGN="center" BORDER="0" WIDTH="100%">
						<TR>
							<TD ALIGN="left" WIDTH="22%" CLASS="headerbgcolor" nowrap><b>
							Model </b><Font color="red">*</Font></TD>
							<TD WIDTH="8%"><html:select styleClass="selectstyle2"
								name="ModAddClauseRevForm" property="modelSeqNo" onchange="">
								<option selected value="-1">---Select---</option>
								<logic:present name="ModAddClauseRevForm" property="modelList">
									<html:optionsCollection name="ModAddClauseRevForm"
										property="modelList" label="modelName" value="modelSeqNo" />
								</logic:present>
							</html:select></TD>
							<TD WIDTH="3%"></TD>
							<TD COLSPAN="2" WIDTH="10%" ALIGN="LEFT" CLASS="headerbgcolor"
								NOWRAP HEIGHT="20">Clause Description&nbsp;<Font color="red">*</Font>&nbsp;&nbsp;<A
								HREF="javascript:LoadClauseDesc()"><IMG SRC="images/search.gif"
								alt="Search" BORDER="0"></A></TD>
						</TR>
					</TABLE>
					</TD>
					<TD WIDTH="20%"><span style="width:435px">
					<DIV id="clauseDesc" STYLE="height:60;width:435;overflow: auto ; ">&nbsp;
					<logic:notEmpty name="ModAddClauseRevForm" property="clauseDesc">
						<bean:define name="ModAddClauseRevForm" property="clauseDesc"
							id="clauseDesc" />
						<%=clauseDesc %>
					</logic:notEmpty></DIV>
					</span></TD>

					<TD><html:button property="method" styleClass="buttonStyle"
						onclick="javascript:fnSearch()">
						<bean:message key="screen.search" />
					</html:button></TD>
				</TR>
			</TABLE>
			</TD>
		</TR>
	</TABLE>

	<logic:greaterThan name="clsize" value="0">
		<logic:iterate id="check" name="ModAddClauseRevForm"
			property="clauseList" type="com.EMD.LSDB.vo.common.ClauseVO"
			scope="request">

			<HR>

			<TABLE BORDER="0" WIDTH="85%" ALIGN=center>
				<TR>
					<TD WIDTH="22%" CLASS="headerbgcolor">New Clause Description<font
						color="red">*</font></TD>
					<TD COLSPAN="3"><html:textarea property="clauseDescForTextArea"
						name="check" rows="4" cols="60" styleClass="CLAUSEDESCTEXTAREA">

					</html:textarea></TD>
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

								</TR>
								<TR>
									<TD CLASS="headerbgcolor">New EDL Number(s)</TD>
									<TD COLSPAN=3><logic:notEmpty name="check" property="edlNo">

										<html:text property="edlNo" styleClass="TEXTBOX2" size="20"
											maxlength="20" value='<%=check.getEdlNo()[0]%>'></html:text>&nbsp;&nbsp;
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

											<html:text property="partOfCode"
												value='<%=(check.getPartOfCode()[1])=="0" ? "":check.getPartOfCode()[1] %>'
												styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;<A
												HREF="javascript:LoadAllClauses(2)"><IMG
												SRC="images/search.gif" alt="Search" BORDER="0"></A>
											<A HREF="javascript:deletePartOfCode(2)"><IMG
												SRC="images/delete1.gif" alt="Delete" BORDER="0"></A>
											<html:hidden property="partOfSeqNo"
												value='<%=String.valueOf(check.getPartOfSeqNo()[1])%>' />




											<html:text property="partOfCode"
												value='<%=(check.getPartOfCode()[2])=="0" ? "":check.getPartOfCode()[2] %>'
												styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;<A
												HREF="javascript:LoadAllClauses(3)"><IMG
												SRC="images/search.gif" alt="Search" BORDER="0"></A>
											<A HREF="javascript:deletePartOfCode(3)"><IMG
												SRC="images/delete1.gif" alt="Delete" BORDER="0"></A>
											<html:hidden property="partOfSeqNo"
												value='<%=String.valueOf(check.getPartOfSeqNo()[2])%>' />

											<html:text property="partOfCode"
												value='<%=(check.getPartOfCode()[3])=="0" ? "":check.getPartOfCode()[3] %>'
												styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;<A
												HREF="javascript:LoadAllClauses(4)"><IMG
												SRC="images/search.gif" alt="Search" BORDER="0"></A>
											<A HREF="javascript:deletePartOfCode(4)"><IMG
												SRC="images/delete1.gif" alt="Delete" BORDER="0"></A>
											<html:hidden property="partOfSeqNo"
												value='<%=String.valueOf(check.getPartOfSeqNo()[3])%>' />
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

										<html:text property="partOfCode" value=""
											styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;<A
											HREF="javascript:LoadAllClauses(2)"><IMG
											SRC="images/search.gif" alt="Search" BORDER="0"></A>
										<A HREF="javascript:deletePartOfCode(2)"><IMG
											SRC="images/delete1.gif" alt="Delete" BORDER="0"></A>
										<html:hidden property="partOfSeqNo" />

										<html:text property="partOfCode" value=""
											styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;<A
											HREF="javascript:LoadAllClauses(3)"><IMG
											SRC="images/search.gif" alt="Search" BORDER="0"></A>
										<A HREF="javascript:deletePartOfCode(3)"><IMG
											SRC="images/delete1.gif" alt="Delete" BORDER="0"></A>
										<html:hidden property="partOfSeqNo" />

										<html:text property="partOfCode" value=""
											styleClass="TEXTBOX2" readonly="true"></html:text>&nbsp;<A
											HREF="javascript:LoadAllClauses(4)"><IMG
											SRC="images/search.gif" alt="Search" BORDER="0"></A>
										<A HREF="javascript:deletePartOfCode(4)"><IMG
											SRC="images/delete1.gif" alt="Delete" BORDER="0"></A>
										<html:hidden property="partOfSeqNo" />
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
								<TR>
									<TD CLASS="headerbgcolor" ALIGN="left">Price Book Number</TD>
									<TD COLSPAN="5"><logic:greaterThan name="check"
										property="priceBookNumber" value="0">
										<html:text property="priceBookNumber" name="check"
											styleClass="TEXTBOX2" size="20" maxlength="20"></html:text>
									</logic:greaterThan> <logic:equal name="check"
										property="priceBookNumber" value="0">
										<html:text property="priceBookNumber" styleClass="TEXTBOX2"
											size="20" maxlength="20" value=""></html:text>
									</logic:equal></TD>
								</TR>


								<TR>
									<TD CLASS="headerbgcolor">Engineering Data</TD>
									<TD COLSPAN="3"><html:select property="standardEquipmentSeqNo"
										styleClass="selectstyle2">
										<option value="-1">--Select--</option>
										<logic:present name="ModAddClauseRevForm"
											property="stdEquipmentList">
											<logic:iterate id="stdEquip" name="ModAddClauseRevForm"
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
				<TD>
				<TABLE BORDER=0 ALIGN="CENTER" WIDTH="100%">
					<TR>
						<TD WIDTH="6%"></TD>
						<TD WIDTH="90%"><!-- <FIELDSET CLASS="fieldset5">
					<LEGEND  ALIGN=left CLASS="formSubHeading">Spec Supplement</LEGEND>
						<TABLE BORDER=0 WIDTH="99%" ALIGN=CENTER>
							<TR>
								<TD WIDTH="30%" ALIGN=LEFT CLASS=headerbgcolor>Reason  <font color="red">*</font></TD>
								<TD>&nbsp;
									<html:textarea name="check" property="reason" rows="4" cols="60"></html:textarea>
								</TD>
							</TR>
						</TABLE>
					</FIELDSET> --></TD>
					</TR>
				</TABLE>
				</TD>
			</TR>
			</TABLE>
			<TABLE WIDTH="97%">
				<TR>
					<TD>&nbsp;</TD>
				</TR>
				<TR>
					<TD ALIGN="center"><html:button property="addButton"
						styleClass="buttonStyle" value="Add"
						onclick="javascript:fnAddClause()">
					</html:button></TD>
				</TR>
			</TABLE>

		</logic:iterate>
	</logic:greaterThan>
</html:form>
