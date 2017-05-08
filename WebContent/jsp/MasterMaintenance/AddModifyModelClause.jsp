<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!-- Added for CR_88  -->
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>
<!-- Added for CR_121 Starts Here -->
<script language="javascript" src="js/Others/tiny_mce/jquery.tinymce.js"></script>
<!-- Added for CR_121 Ends Here -->
<%-- Added for CR-91 to remove repeated code --%>
<%@ include file="/jsp/common/richTextEditor.jsp" %>
<SCRIPT type="text/javaScript" SRC="js/AddClause.js"></SCRIPT>
<SCRIPT type="text/javaScript" SRC="js/ClaInOrdersReport.js"></SCRIPT>

<div class="container" width="100%">
	<html:form action="/modelAddClauseAction" method="post">
	
			<h4 class="text-center"><strong><bean:message
						key="Application.Screen.AddModifyClause" /></strong></h4>
			
			<div class="spacer10"></div>
			<p class="text-center"><mark> All new clauses added to the
					SubSection will be added at the bottom of the SubSection.</mark></p>
			<p class="text-center"><mark>Only use the Lead Component Group
					and Lead Component selections when needing to retain an existing
					clause number.</mark></p>
			<p class="text-center"><mark>Components Tied To Clause - Only
					use this to force a clause to be displayed when a component is
					selected.</mark></p>
			
		<!-- To display  Messages - Start -->
			<div class="spacer10"></div>
			<div class="errorlayerhide" id="errorlayer"></div> 
			<!-- To display Messages - End -->
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

	<logic:present name="ModelAddClauseForm" property="clauseList"
		scope="request">
		<bean:size name="ModelAddClauseForm" id="clsize"
			property="clauseList" />
	</logic:present>
		
	<logic:match name="ModelAddClauseForm" property="method"
		value="fetchClauses">
		<logic:lessThan name="clsize" value="1">
		<script> 
              fnNoRecords();
        </script>

		</logic:lessThan>
	</logic:match>

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
	<logic:present name="ModelAddClauseForm" property="componentVO">
		<bean:size id="noOfClauses" name="ModelAddClauseForm"
			property="componentVO" />
	</logic:present>

	<logic:match name="ModelAddClauseForm" property="method" value="updateReInsertClause">
		<logic:lessThan name="noOfClauses" value="1">
			<script>
				var id='888';
				addMsgNum(id);
				showScrollErrors();
			</script>
		</logic:lessThan>
	</logic:match >
		
	<div class="spacer10"></div>
	
		<div class="form-horizontal">
			<div class="form-group required-field">
					<label class="col-sm-5 control-label">Specification Type</label>
					<div class="col-sm-2">
					<html:select name="ModelAddClauseForm" styleClass="form-control"
						property="specTypeNo" styleId="sltSpecType"
						onchange="javascript:fetchModels()" >
						<option selected value="-1">---Select---</option>
						<html:optionsCollection name="ModelAddClauseForm"
							property="specTypeList" value="spectypeSeqno"
							label="spectypename" styleClass="form-control" />
					</html:select>
					</div>
			</div>
			<div class="form-group required-field">
					<label class="col-sm-5 control-label">Model</label>
					<div class="col-sm-1">
						<html:select styleId="sltModel" name="ModelAddClauseForm" property="modelSeqNo"
							onchange="javascript:fnLoadSection()" styleClass="form-control">
							<html:option value="-1">---Select---</html:option>
							<logic:present name="ModelAddClauseForm" property="modelList">
								<html:optionsCollection name="ModelAddClauseForm"
									property="modelList" label="modelName" value="modelSeqNo" />
							</logic:present>
						</html:select>
					</div>
			</div>
			<div class="form-group required-field">
					<label class="col-sm-5 control-label">Section</label>
					<div class="col-sm-1">
						<html:select styleId="sltSection" name="ModelAddClauseForm" property="sectionSeqNo"
								 styleClass="form-control" onchange="javascript:fnLoadSubSection()">
								<logic:equal property="sectionSeqNo" name="ModelAddClauseForm" value="">
									<html:option value="-1">---Select---</html:option>
								</logic:equal>
								<html:option value="-1">---Select---</html:option>
								<logic:present name="ModelAddClauseForm" property="sectionList">
									<html:optionsCollection name="ModelAddClauseForm"
										property="sectionList" label="sectionDisplay"
										value="sectionSeqNo" />
								</logic:present>
						</html:select>
					</div>
			</div>
			<div class="form-group required-field">
					<label class="col-sm-5 control-label">SubSection</label>
					<div class="col-sm-1">
						<html:select styleId="sltSubSection" name="ModelAddClauseForm" property="subSectionSeqNo"
							 styleClass="form-control" onchange="javascript:fnLoadCompGroup('N')"> <!-- Modified for CR_88 -->
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
						</html:select>
					</div>
			</div>
		</div>	
		<div class="spacer30"></div>
 	
	 	<ul class="nav nav-tabs nav-justified" id="clausesTab">
		  <li role="presentation"><a href="javascript:fnLoadAddModifyPage('N')">Add Clause</a></li>
		  <li role="presentation"><a href="javascript:fnLoadAddModifyPage('Y')">Modify Clause</a></li>
		  <li role="presentation"><a href="javascript:fnRearrangeClausesPage('N')">Clause Rearranging</a></li>
		</ul>		
		
		<logic:greaterEqual name="noOfClauses" value="1">
		<div class="spacer30"></div>
		
		<div class="row">
			<div class="form-group">
				<div class="col-sm-12 text-center">					
					<a href="javascript:fntoggleDiv();"> <bean:message 
						key="Application.Screen.AddModifySelectClause" /></a>
				</div>
			</div>
		</div>	
		
		<div class="spacer20"></div>
		
		<div class="row">	
			<div class="col-sm-offset-1 col-sm-10 well" id="divAddModify">
				<TABLE class="table table-bordered table-hover" name="sessionScope.ModelAddClauseForm.componentVO" id="tbAddModify">
					<thead>
						<TR>
							<TH>Select</TH>
							<TH class="text-nowrap">Clause No</TH>
							<TH>Clause Description</TH>
							<TH class="text-nowrap">Engineering Data</TH>
							<TH class="text-nowrap"><font color="#000080"><I>Component Group</I></font> - Component</TH><%--Updated for CR-128--%>
						</TR>
					</thead>
					<tbody>
					<logic:iterate id="compParent" name="ModelAddClauseForm"
						property="componentVO" scope="request"
						type="com.EMD.LSDB.vo.common.ClauseVO" indexId="clausecount">
						<TR>
							<TD CLASS="v-midalign text-center">
								<logic:notEqual name="ModelAddClauseForm" property="modifyClauseFlag" value="Y">
									<html:radio
									property="clauseSeqNo" disabled="true"
									value='<%=String.valueOf(compParent.getClauseSeqNo())%>' 
									onclick="javascript:fnFetchClauseversions()" />
								</logic:notEqual>
								<logic:equal name="ModelAddClauseForm" property="modifyClauseFlag" value="Y">
									<html:radio
									property="clauseSeqNo"
									value='<%=String.valueOf(compParent.getClauseSeqNo())%>' 
									onclick="javascript:fnFetchClauseversions()" />
								</logic:equal>
								</TD>
							<TD CLASS="v-midalign text-center"><logic:present name="compParent"
								property="clauseNum">
								<bean:write name="compParent" property="clauseNum" />
							</logic:present></TD>
							<html:hidden name="compParent" property="versionNo" />
							<TD>
							<%-- CR-128 - Updated for Pdf issue --%>
								<%if(String.valueOf(compParent.getClauseDesc()).startsWith("<p>"))
								{%>
									<%=(String.valueOf(compParent.getClauseDesc()))%>
								<%}else{ %>	
								 <%=(String.valueOf(compParent.getClauseDesc())).replaceAll("\n","<br>")%>
								<%}%>
							<%-- CR-128 - Updated for Pdf issue Ends Here--%>
								<logic:notEmpty name="compParent" property="tableArrayData1">
								<table class="table table-bordered text-center">
				 						<logic:iterate id="outter" name="compParent"
										property="tableArrayData1" indexId="counter">
										<!-- Added  For CR_93 -->
											<bean:define name="compParent" property="tableDataColSize" id="tableDataColSize" />
										<bean:define id="row" name="counter" />
										<tr>
											<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
												<logic:equal name="row" value="0">
													<td valign="top" width="5%" style="border-top: 1px solid #dddddd;" class="text-center v-midalign"><b><font
														color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></font></b>
													</td>
												</logic:equal>
												<logic:notEqual name="row" value="0">
													<td valign="top" width="5%"><%=(tabrow==null)? "&nbsp;":tabrow%>
													</td>
												</logic:notEqual>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</table>
								</logic:notEmpty>
							</TD>
						<TD CLASS="v-midalign text-center">
						<!--<logic:present name="compParent" property="refEDLNO">
	
							<logic:iterate id="engDataRefEdlNo" name="compParent"
								property="refEDLNO">
								<bean:message key="screen.refEdl" />
								<bean:write name="engDataRefEdlNo" />
								<br>
							</logic:iterate>
						</logic:present>-->
						 <logic:present name="compParent"
							property="edlNO">
							<logic:iterate id="engDataEdlNo" name="compParent"
								property="edlNO">
								<bean:message key="screen.edl" />
								<bean:write name="engDataEdlNo" />
								<br>
							</logic:iterate>
						</logic:present> 
						<!--  CR 87 -->
						<logic:present name="compParent" property="refEDLNO">
	
							<logic:iterate id="engDataRefEdlNo" name="compParent"
								property="refEDLNO">
								<bean:message key="screen.refEdl.start" />
								<bean:write name="engDataRefEdlNo" />
								<bean:message key="screen.refEdl.end" />
								<br>
							</logic:iterate>
						</logic:present>
						<logic:present name="compParent"
							property="partOF">
							<logic:iterate id="engPartOfCd" name="compParent"
								property="partOF">
								<logic:notEqual name="engPartOfCd" value="0">
									<bean:message key="screen.partOf" />
									<bean:write name="engPartOfCd" />
									<br>
								</logic:notEqual>
							</logic:iterate>
						</logic:present> <logic:present name="compParent"
							property="dwONumber">
							<logic:notEqual name="compParent" property="dwONumber"
								value="0">
								<bean:message key="screen.dwoNo" />
								<bean:write name="compParent" property="dwONumber" />
								<br>
							</logic:notEqual>
						</logic:present> <logic:present name="compParent"
							property="partNumber">
							<logic:notEqual name="compParent" property="partNumber"
								value="0">
								<bean:message key="screen.partNo" />
								<bean:write name="compParent" property="partNumber" />
								<br>
							</logic:notEqual>
						</logic:present> <!--Price Book Number is added in the Engineering data Section, modified on 30-09-08 by ps5722-->
						<logic:present name="compParent" property="priceBookNumber">
							<logic:notEqual name="compParent" property="priceBookNumber"
								value="0">
								<bean:message key="screen.priceBookNo" />&nbsp; <bean:write
									name="compParent" property="priceBookNumber" />
								<br>
							</logic:notEqual>
						</logic:present> <logic:present name="compParent"
							property="standardEquipmentDesc">
							<bean:write name="compParent" property="standardEquipmentDesc" />
							<br>
						</logic:present> <logic:present name="compParent"
							property="engDataComment">
							<bean:define id="engDatCmnt" name="compParent"
								property="engDataComment" />
							<%=engDatCmnt %>
							<br>
						</logic:present>
				</TD>
				<logic:notEmpty name="compParent" property="compGroupVO">
					<TD class="v-midalign text-center"><logic:iterate
						id="compList" name="compParent" property="compGroupVO"
						type="com.EMD.LSDB.vo.common.CompGroupVO" scope="page"
						indexId="compntIndex">
						<logic:present name="compList" property="compVO">
							<bean:define id="comp" name="compList" property="compVO" />
							<logic:notEqual name="compntIndex" value="0"> ;<br>
							</logic:notEqual>
							<%--Added for CR-128--%>
							<font color="#000080"><I><bean:write name="compList" property="componentGroupName" /></I></font> - 
							<%--Added for CR-128 Ends here--%>
								<bean:write name="comp" property="componentName" />
							</logic:present>
						</logic:iterate></TD>
				</logic:notEmpty>
				<logic:empty name="compParent" property="compGroupVO">
					<td class="v-midalign">&nbsp;</td>
				</logic:empty>
				</TR>
				</logic:iterate>
				</tbody>
				</TABLE>
				<br/>
			</div>			
			<div class="spacer10"></div>
		</div>
		</logic:greaterEqual>							
		<html:hidden property="headerFlag" />
		<html:hidden property="rowIndex" />
		<html:hidden property="hdnClauseSeqNo" styleId="hdnClauseSeqNo"/>
		<html:hidden property="modifyClauseFlag" styleId="modifyClauseFlag"/>
		<html:hidden property="childFlag" styleId="childFlag"/>
		<!-- Added for CR-121 -->
		<html:hidden property="hdnSelectedSpecType" styleId="hdnSelectedSpecType"/>
		<html:hidden property="hdnSelectedModel" styleId="hdnSelectedModel"/> 
		<html:hidden property="hdnSelectedSection" styleId="hdnSelectedSection"/>
		<html:hidden property="hdnSelectedSubSection" styleId="hdnSelectedSubSection"/>
		<html:hidden name="ModelAddClauseForm" property="intScreenId" styleId="intScreenId"/>
	   	<!-- Added for CR-121 Ends here-->	
		
		<!-- Added for CR-118 by vb106565 -->
		<html:hidden property="specTypeSelectFlag"/>
		<!-- Added for CR-118 by vb106565 ends here-->
		 <logic:notEqual name="ModelAddClauseForm" property="childFlag"  value="N">
		 	<logic:equal name="ModelAddClauseForm" property="modifyClauseFlag" value="N">
				<jsp:include page="AddModelClause.jsp" />
			</logic:equal>
		
			<logic:equal name="ModelAddClauseForm" property="modifyClauseFlag" value="Y">
				<jsp:include page="ModifyModelClause.jsp" />
			</logic:equal>
		</logic:notEqual>
		
		<logic:equal name="ModelAddClauseForm" property="childFlag" value="N"> 
			<jsp:include page="RearrangeMdlClause.jsp" />
		</logic:equal>
		<%-- Added for CR_99 for displaying JUNK characters by RJ85495 --%>	
			<jsp:include page="/jsp/common/ClauseComparison.jsp" />
		<%-- CR_99 Ends here --%>
			<%--Added for CR-121 --%>
		<html:hidden name="ModelAddClauseForm" property="intScreenId" />
	</html:form>
</div>	
	
	
	