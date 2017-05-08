<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<LINK REL="stylesheet" HREF="css/Emd_Lsdb.css" TYPE="text/css">
<LINK REL="stylesheet" TYPE="text/css" HREF="css/EmdMenu.css">
<SCRIPT language="JavaScript" SRC="js/AddClause.js"></SCRIPT>
<SCRIPT language="JavaScript" SRC="js/Emd_Lsdb.js"></SCRIPT>
<SCRIPT language="JavaScript" SRC="js/error.js"></SCRIPT>
<SCRIPT language="JavaScript" SRC="js/error_messages.js"></SCRIPT>
<script>
    function fnlocalSubmit(hiddencomponentGrpArray , hiddencomponentSeqNo,hiddencomponentGrpSeqNo)
    {
	//Added to solve Components tied to clause Issue    
    document.forms['ModelAddClauseForm'].hdncomponentGroupSeqNo.value = "";
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

    function deleteComponent()
    {
    if (document.forms[0].componentfrom.options.length!=0)
    {
    var del=confirm("Are you sure you want to clear all the components");
	if(del == true){
    document.forms[0].componentfrom.options.length=0;
	document.forms[0].hdncomponentGroupSeqNo.value="";
	document.forms[0].componentSeqNo.value="";
    }
    }
    }
</script>

<html:form action="/modelAddClauseAction.do">
	<html:hidden property="headerFlag" />
	<html:hidden property="rowIndex" />

	<TABLE BORDER="0" WIDTH="60%" ALIGN="center">
		<tr></tr>
		<tr></tr>
		<TR>
			<TD WIDTH="60%" CLASS="dashBoardSubHeading" ALIGN="center">Clause
			Maintenance - Add Clause</TD>
		</TR>
	</TABLE>
	<br>
	<TABLE ALIGN=center WIDTH="70%" BORDER=0>
		<TR>
			<TD align=center class="navycolor">All new clauses added to the
			SubSection will be added at the bottom of the SubSection.</TD>
		</TR>
		<TR>
			<TD align=center class="navycolor">Only use the Lead Component Group
			and Lead Component selections when needing to retain an existing
			clause number.</TD>
		</TR>
		<TR>
			<TD align=center class="navycolor">Components Tied To Clause - Only
			use this to force a clause to be displayed when a component is
			selected.</TD>
		</TR>
	</TABLE>

	<br>
	<!-- Validation Message Display Part Starts Here -->
	<div class="errorlayerhide" id="errorlayer">
	</div>
	<!-- Validation Message Display Part Ends Here -->

	<!-- Logic Tag Check For Display The Success Message After Add Starts Here -->


	<logic:present name="ModelAddClauseForm" property="messageID"
		scope="request">
	
    <bean:define id="messageID" name="ModelAddClauseForm" property="messageID"/>
           	<input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
                
	</logic:present>

	<!-- Logic Tag Check For Display The Success Message After Add Functionality Ends Here -->
	<!-- Logic Tag Check For Display The Error Message After Add Functionality Starts Here -->
	<logic:present name="ModelAddClauseForm" property="errorMessage"
		scope="request">
		<script>
                    hideErrors();
                    addMessage('<bean:write name="ModelAddClauseForm" property="errorMessage"/>');
                    showScrollErrors();
                </script>

	</logic:present>

	<!-- Logic Tag Check For Display The Error Message After Add Functionality Ends Here -->
	<logic:present name="ModelAddClauseForm" property="modelList">
		<bean:size id="modelSeqNo" name="ModelAddClauseForm"
			property="modelList" />
	</logic:present>
	<logic:present name="ModelAddClauseForm" property="subSectionList">
		<bean:size id="subSectionSeqNo" name="ModelAddClauseForm"
			property="subSectionList" />
	</logic:present>
	<logic:present name="ModelAddClauseForm" property="sectionList">
		<bean:size id="sectionSeqNo" name="ModelAddClauseForm"
			property="sectionList" />
	</logic:present>

	<TABLE WIDTH="100%" BORDER="0" CELLPADDING="0" CELLSPACING="0">

		<TR>
			<TD>
			<TABLE ALIGN="center" BORDER="0" WIDTH="90%">
				<TR>
					<TD WIDTH="5%">
					<TABLE>
						<TR>
							<TD>&nbsp;</TD>
						</TR>
					</TABLE>

					</TD>


					<TD WIDTH="81%">
					<TABLE ALIGN="center" BORDER="0" WIDTH="100%">

						<TR>
							<TD>&nbsp;</TD>
						</TR>

						<!-- Added for LSDB_CR_46 (PM&I Change)-->
						<TR>
							<TD WIDTH="10%" ALIGN="left" CLASS="headerbgcolor" nowrap>Specification
							Type&nbsp;<font size=2 color="red">*</font></TD>
							<TD WIDTH="8%">&nbsp; <html:select name="ModelAddClauseForm"
								property="specTypeNo" styleClass="selectstyle2"
								onchange="javascript:fetchModels()">
								<option selected value="-1">---Select---</option>
								<html:optionsCollection name="ModelAddClauseForm"
									property="specTypeList" value="spectypeSeqno"
									label="spectypename" />
							</html:select></TD>
							<TD CLASS="headerbgcolor">Customer</TD>
							<TD>&nbsp; <html:select styleClass="selectstyle2"
								name="ModelAddClauseForm" property="customerSeqNo">
								<html:option value="-1">---Select---</html:option>
								<logic:present name="ModelAddClauseForm" property="customerList">
									<html:optionsCollection name="ModelAddClauseForm"
										property="customerList" label="customerName"
										value="customerSeqNo" />
								</logic:present>
							</html:select></TD>

						</TR>

						<TR>
							<TD ALIGN="left" CLASS="headerbgcolor" WIDTH="18%"><b> Model</b>
							<font color="red">*</font></TD>

							<TD>&nbsp; <html:select styleClass="selectstyle2"
								name="ModelAddClauseForm" property="modelSeqNo"
								onchange="javascript:fnLoadSection()">

								<html:option value="-1">---Select---</html:option>

								<logic:present name="ModelAddClauseForm" property="modelList">
									<html:optionsCollection name="ModelAddClauseForm"
										property="modelList" label="modelName" value="modelSeqNo" />
								</logic:present>

							</html:select></TD>

						</TR>
						<TR>
							<TD ALIGN="LEFT" CLASS="headerbgcolor">Section <font color="red">*</font></TD>
							<!-- Modified for Alignment by RR68151 -->
							<TD COLSPAN=6>&nbsp; <html:select styleClass="selectstyle2"
								name="ModelAddClauseForm" property="sectionSeqNo"
								onchange="javascript:fnLoadSubSection()">

								<logic:equal property="sectionSeqNo" name="ModelAddClauseForm"
									value="">
									<html:option value="-1">---Select---</html:option>
								</logic:equal>
								<html:option value="-1">---Select---</html:option>

								<logic:present name="ModelAddClauseForm" property="sectionList">
									<html:optionsCollection name="ModelAddClauseForm"
										property="sectionList" label="sectionDisplay"
										value="sectionSeqNo" />
								</logic:present>


							</html:select></TD>
						</TR>

						<TR>
							<TD CLASS="headerbgcolor" NOWRAP>SubSection <font color="red">*</font></TD>
							<TD COLSPAN="6">&nbsp; <html:select styleClass="selectstyle2"
								name="ModelAddClauseForm" property="subSectionSeqNo"
								onchange="javascript:fnLoadCompGroup()">
								<logic:equal property="subSectionSeqNo"
									name="ModelAddClauseForm" value="">
									<html:option value="-1">---Select---</html:option>
								</logic:equal>
								<html:option value="-1">---Select---</html:option>
								<logic:present name="ModelAddClauseForm"
									property="subSectionList">
									<html:optionsCollection name="ModelAddClauseForm"
										property="subSectionList" label="subSecDisplay"
										value="subSecSeqNo" />
								</logic:present>
							</html:select></TD>
						</TR>

					<!-- Added For CR_81 on 24-Dec-09 by ------- -->
	   <TR>
			<TD  ALIGN="left" CLASS="headerbgcolor">Characteristic Group Clause</TD>
			<TD COLSPAN="6" >&nbsp;<html:checkbox  name="ModelAddClauseForm" property="selectCGCFlag" onclick="javascript:fnCharacteristicCompGroup()"  /></TD>
			
		</TR>
		<!-- Added For CR_81 on 24-Dec-09 by ------- - Ends here-->

						<!--- CR Change Starts here -->
						<TR>
							<TD CLASS="headerbgcolor">Select Lead ComponentGroup</TD>
							<TD COLSPAN="6">&nbsp; <html:select styleClass="selectstyle2"
								name="ModelAddClauseForm" property="compGroupSeqNo"
								onchange="javascript:fnLoadComponents()">
								<html:option value="-1">---Select---</html:option>
								<logic:present name="ModelAddClauseForm"
									property="compGroupList">
									<html:optionsCollection name="ModelAddClauseForm"
										property="compGroupList" label="componentGroupName"
										value="componentGroupSeqNo" />
								</logic:present>
							</html:select></TD>
						</TR>

						<TR>
							<TD CLASS="headerbgcolor">Select Lead Component</TD>
							<TD COLSPAN="6">&nbsp; <html:select styleClass="selectstyle2"
								name="ModelAddClauseForm" property="leadComponentSeqNo">


								<html:option value="-1">---Select---</html:option>

								<logic:present name="ModelAddClauseForm"
									property="compGroupList">
									<logic:iterate id="compGroupList" name="ModelAddClauseForm"
										property="compGroupList"
										type="com.EMD.LSDB.vo.common.CompGroupVO">

										<logic:equal name="ModelAddClauseForm"
											property="compGroupSeqNo"
											value="<%=String.valueOf(compGroupList.getComponentGroupSeqNo())%>">

											<logic:iterate id="compList" name="compGroupList"
												property="componentVO"
												type="com.EMD.LSDB.vo.common.ComponentVO" scope="page">

												<logic:equal name="ModelAddClauseForm"
													property="compGroupSeqNo"
													value="<%=String.valueOf(compList.getComponentGroupSeqNo())%>">

													<option value="<%=compList.getComponentSeqNo()%>"><bean:write
														name="compList" property="componentName" /></option>
												</logic:equal>

											</logic:iterate>
										</logic:equal>
									</logic:iterate>
								</logic:present>

							</html:select></TD>
						</TR>
						<!-- Ends here -->

						<TR>
							<TD ALIGN="left" CLASS="headerbgcolor">Parent Clause&nbsp;<A
								HREF="javascript:LoadParentClauses()"><IMG
								SRC="images/search.gif" alt="Search" BORDER="0"></A> <A
								HREF="javascript:deleteParentClause()"><IMG
								SRC="images/delete1.gif" alt="Clear" BORDER="0"></A></TD>
							<TD WIDTH="15%" COLSPAN="2">&nbsp;
							<DIV id="parentclause"
								STYLE="height:60;width:435;overflow: auto ; ">
							<table>
								<logic:notEmpty name="ModelAddClauseForm"
									property="tableClauseData">

									<logic:iterate id="outter" name="ModelAddClauseForm"
										property="tableClauseData" indexId="counter">

										<table width="100%" BORDER="1" BORDERCOLOR="#5780ae">
											<bean:define id="row" name="counter" />
											<tr>
												<logic:iterate id="tabrow" name="outter" scope="page">

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
										</table>
									</logic:iterate>
								</logic:notEmpty>
							</table>



							</DIV>
							</TD>

						</TR>
						<TR>
							<TD CLASS="headerbgcolor">Clause Description <font color="red">*</font></TD>
							<TD WIDTH="43%" COLSPAN=6>&nbsp; <html:textarea
								property="clauseDesc" name="ModelAddClauseForm" rows="10"
								cols="92" styleClass="CLAUSEDESCTEXTAREA"></html:textarea></TD>
						</TR>

						<TR>

							<TD WIDTH="10%" CLASS="headerbgcolor">Table Data <br>
							<br>
							<DIV id="showmainlink" style="visibility:visible"><A
								CLASS='linkstyleItem' HREF="javascript:addTable()">Add Table</A>&nbsp;</DIV>
							<DIV id="showsublink" style="visibility:hidden"><A
								CLASS=linkstyle1 HREF="javascript:addRow(this)">Add Row</A>&nbsp;|&nbsp;<A
								CLASS=linkstyle1 HREF="javascript:removeRow()">Delete Row</A><br>
							<A CLASS='linkstyle1' HREF="javascript:deleteTable()">Delete
							Table</A>&nbsp;</DIV>


							</TD>


							<TD WIDTH="75%" COLSPAN=7>
							<DIV ID="showgrid"></DIV>

							</TD>

						</TR>




						<TR>
							<TD WIDTH="12%" ALIGN="left" CLASS="headerbgcolor">Component Tied
							To Clause</TD>
							<TD COLSPAN="2">
							<TABLE>
								<TR>
									<TD WIDTH="20%"><SELECT name="componentfrom" MULTIPLE
										CLASS="selectstyle2">

									</SELECT></TD>
<!-- Added id property as addCompToCla For CR_81 on 24-Dec-09 by satish ------- -->
									<TD width="1%"><A HREF="javascript:AddComponent()" id="addCompToCla"><IMG
										SRC="images/search.gif" alt="Search" BORDER="0"></A></TD>
									<TD><A HREF="javascript:deleteComponent()"> <IMG
										SRC="images/delete1.gif" alt="Clear" BORDER="0"></TD>

								</TR>
								
									
							</TABLE>
							</TD>
						</TR>


					</TABLE>
					</TD>
				</TR>

			</TABLE>
			<TABLE ALIGN="CENTER" BORDER="0" WIDTH="100%">
				<TR>
					<TD WIDTH="10%">&nbsp;</TD>
					<TD WIDTH="90%">
					<FIELDSET CLASS="fieldset5"><LEGEND ALIGN="left"
						CLASS="formSubHeading">Engineering Data <font color="red">*</font></LEGEND>

					<TABLE WIDTH="100%" BORDER="0" ALIGN=center>
						<TR>
							<TD WIDTH="28%" ALIGN="left" CLASS="headerbgcolor">Reference EDL
							Number(s)</TD>
							<TD CLASS="formFieldSub"><html:text property="refEDLNo"
								styleClass="TEXTBOX2" size="20" maxlength="20" value=""></html:text>
							&nbsp;&nbsp;<html:text property="refEDLNo" styleClass="TEXTBOX2"
								size="20" maxlength="20" value=""></html:text>&nbsp;&nbsp; <html:text
								property="refEDLNo" styleClass="TEXTBOX2" size="20"
								maxlength="20" value=""></html:text></TD>
							<TD>&nbsp;</TD>
							<TD>&nbsp;</TD>
						</TR>
						<TR>
							<TD WIDTH="15%" ALIGN="left" CLASS="headerbgcolor">New EDL
							Number(s)</TD>

							<TD CLASS="formFieldSub"><html:text property="edlNo"
								styleClass="TEXTBOX2" size="20" maxlength="20" value=""></html:text>
							&nbsp;&nbsp;<html:text property="edlNo" styleClass="TEXTBOX2"
								size="20" maxlength="20" value=""></html:text>&nbsp;&nbsp; <html:text
								property="edlNo" styleClass="TEXTBOX2" size="20" maxlength="20"
								value=""></html:text></TD>

						</TR>

						<TR><!-- CR 87 -->
							<TD CLASS="headerbgcolor" ALIGN="left"><bean:message key="screen.partOf" /></TD>

							<TD CLASS="formFieldSub" COLSPAN="6"><html:text indexed="1"
								property="partOfCode" value="" styleClass="TEXTBOX2"
								readonly="true"></html:text>&nbsp;<A
								HREF="javascript:LoadAllClauses(1)"><IMG SRC="images/search.gif"
								alt="Search" BORDER="0"></A> <A
								HREF="javascript:deletePartOfCode(1)"><IMG
								SRC="images/delete1.gif" alt="Clear" BORDER="0"></A> <html:hidden
								property="partOfSeqNo" value="" /> <html:hidden
								property="partOfclaSeqNo" value="" /> <html:text
								property="partOfCode" value="" styleClass="TEXTBOX2"
								readonly="true"></html:text>&nbsp;<A
								HREF="javascript:LoadAllClauses(2)"><IMG SRC="images/search.gif"
								alt="Search" BORDER="0"></A> <A
								HREF="javascript:deletePartOfCode(2)"><IMG
								SRC="images/delete1.gif" alt="Clear" BORDER="0"></A> <html:hidden
								property="partOfSeqNo" value="" /> <html:hidden
								property="partOfclaSeqNo" value="" /> <html:text
								property="partOfCode" value="" styleClass="TEXTBOX2"
								readonly="true"></html:text>&nbsp;<A
								HREF="javascript:LoadAllClauses(3)"><IMG SRC="images/search.gif"
								alt="Search" BORDER="0"></A> <A
								HREF="javascript:deletePartOfCode(3)"><IMG
								SRC="images/delete1.gif" alt="Clear" BORDER="0"></A> <html:hidden
								property="partOfSeqNo" value="" /> <html:hidden
								property="partOfclaSeqNo" value="" /> <html:text
								property="partOfCode" value="" styleClass="TEXTBOX2"
								readonly="true"></html:text>&nbsp;<A
								HREF="javascript:LoadAllClauses(4)"><IMG SRC="images/search.gif"
								alt="Search" BORDER="0"></A> <A
								HREF="javascript:deletePartOfCode(4)"><IMG
								SRC="images/delete1.gif" alt="Clear" BORDER="0"></A> <html:hidden
								property="partOfSeqNo" value="" /> <html:hidden
								property="partOfclaSeqNo" value="" /></TD>
						</TR>
						<TR>
							<TD CLASS="headerbgcolor" ALIGN="left">DWO Number</TD>
							<TD CLASS="formFieldSub"><html:text property="dwONumber" value=""
								styleClass="TEXTBOX2" size="20" maxlength="20"></html:text></TD>
						</TR>
						</TR>
						<TR>
							<TD CLASS="headerbgcolor" ALIGN="left">Part Number</TD>
							<TD CLASS="formFieldSub"><html:text property="partNumber"
								value="" styleClass="TEXTBOX2" size="20" maxlength="8"></html:text>
							</TD>
						</TR>

						<!-- Maxlength of Price book number is changed from 20 to 4.Modified on 29-09-08 by ps57222 -->

						<TR>
							<TD CLASS="headerbgcolor" ALIGN="left">Price Book Number</TD>
							<TD CLASS="formFieldSub"><html:text property="priceBookNumber"
								value="" styleClass="TEXTBOX2" size="20" maxlength="4"></html:text>
							</TD>
						</TR>
						<TR>
							<TD CLASS="headerbgcolor" WIDTH="15%">Engineering Data</TD>
							<TD>&nbsp; <html:select styleClass="selectstyle2"
								name="ModelAddClauseForm" property="standardEquipmentSeqNo">
								<html:option value="-1">---Select---</html:option>
								<logic:present name="ModelAddClauseForm"
									property="standardEquipmentList">
									<html:optionsCollection name="ModelAddClauseForm"
										property="standardEquipmentList" label="standardEquipmentDesc"
										value="standardEquipmentSeqNo" />
								</logic:present>
							</html:select></TD>
						</TR>
						<TR>
							<TD CLASS="headerbgcolor">Comments</TD>
							<TD>&nbsp; <textarea name="comments" rows="4" cols="60"></textarea>

							</TD>
						</TR>
					</TABLE>
					</FIELDSET>
					</TD>
				</TR>
				<TR>
					<TD>&nbsp;</TD>
					<TD><!--  <FIELDSET CLASS="fieldset5">
                                    <LEGEND  ALIGN=left CLASS="formSubHeading">Spec Supplement</LEGEND>
                                    <TABLE BORDER=0 WIDTH="100%" ALIGN=CENTER>
                                        <TR>
                                            <TD WIDTH="28%" ALIGN=LEFT CLASS=headerbgcolor>Reason <font color="red">*</font></TD>
                                            <TD>&nbsp;                                               
                                                <textarea name="reason" rows="4" cols="60" ></textarea>
                                            </TD>
                                        </TR>
                                    </TABLE>
									
                                </FIELDSET> --></TD>
				</TR>
			</TABLE>
			</TD>
		</TR>
	</TABLE>


	<TABLE BORDER="0" WIDTH="30%" ALIGN="CENTER">
		<TR>
			<TD>&nbsp;</TD>
		</TR>
		<TR>
			<TD>&nbsp;</TD>
			<TD WIDTH="12%" ALIGN="center"><html:button property="addButton"
				styleClass="buttonStyle" value="Add"
				onclick="javascript:fnAddClause()"></html:button>
			<TD WIDTH="12%">&nbsp;</TD>
			<TD>&nbsp;</TD>
		</TR>
	</TABLE>
	<html:hidden property="parentClauseSeqNo" />
	<html:hidden property="componentSeqNo" />
	<input type="hidden" name="hdncomponentGroupSeqNo" />
	<!-- Added For CR_81 on 24-Dec-09 by ------- -->
	<script>
		fnCharacteristicCompGroup();
	</script>
	
	<!-- Added For CR_81 on 24-Dec-09 by ------- - Ends here-->
	<br>
</html:form>

