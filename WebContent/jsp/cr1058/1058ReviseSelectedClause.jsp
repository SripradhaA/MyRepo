<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>


<logic:present name="ChangeRequest1058Form" property="reviseClauseDetailsList" scope="request">
	<input type="hidden" id="hdncomponentGroupSeqNo" name="hdncomponentGroupSeqNo" />
	<input type="hidden" id="hdnComponentName" name="hdnComponentName" />
	<logic:iterate id="revClaData" name="ChangeRequest1058Form" property="reviseClauseDetailsList" scope="request" type="com.EMD.LSDB.vo.common.ClauseVO">
		<input type="hidden" id="componentSeqNo" name="componentSeqNo" value='<bean:write name="revClaData" property="strComps"/>'>
		<input type="hidden" id="hdnLeadComponentSeqNoinAdd" name="hdnLeadComponentSeqNoinAdd" value='<bean:write name="revClaData" property="leadComponentSeqNo"/>'/>
		<input type="hidden" id="hdnLeadComponentNameinAdd" name="hdnLeadComponentNameinAdd" value='<bean:write name="revClaData" property="leadComponentName"/>'/>
		<input type="hidden" id="componentGrpSeqNoinAdd" name="componentGrpSeqNoinAdd" value='<bean:write name="revClaData" property="leadCompGrpSeqNo"/>'/>
		<input type="hidden" id="sectionSeqNo" name="sectionSeqNo" value='<bean:write name="revClaData" property="sectionSeqNo"/>'/>
		<input type="hidden" id="subSectionSeqNo" name="subSectionSeqNo" value='<bean:write name="revClaData" property="subSectionSeqNo"/>'/>
		<A name="clauseChngType" id="clauseChngType"></A>
			
				<div class="form-group">
					<label class="control-label col-sm-2">Change Type</label>
					<div class="col-sm-4">
						<logic:present name="ChangeRequest1058Form" property="clauseChangeTypes" scope="request">
							<logic:iterate id="ClauseChangeType" name="ChangeRequest1058Form" property="clauseChangeTypes"
								type="com.EMD.LSDB.vo.common.RequestClauseChangeTypes1058VO" scope="request">
								<logic:equal name="ClauseChangeType" property="changeTypeSeqNo" value="<%=String.valueOf(revClaData.getClauseChangeType())%>"> 
									<div class="radio">
										<label>
											<INPUT TYPE="radio" name="" ID ='ClauseChange<bean:write name="ClauseChangeType" property="changeTypeSeqNo"/>' value='<bean:write name="ClauseChangeType" property="changeTypeSeqNo"/>' CLASS="ClauseChangeTypes" checked="true" disabled="true"><bean:write name="ClauseChangeType" property="changeTypeName" /></INPUT>
										</label>
									</div>
								</logic:equal>
								<logic:notEqual name="ClauseChangeType" property="changeTypeSeqNo" value="<%=String.valueOf(revClaData.getClauseChangeType())%>"> 
									<div class="radio">
										<label>
											<INPUT disabled="true" TYPE="radio" name="" ID ='ClauseChange<bean:write name="ClauseChangeType" property="changeTypeSeqNo"/>' value='<bean:write name="ClauseChangeType" property="changeTypeSeqNo"/>' CLASS="ClauseChangeTypes"><bean:write name="ClauseChangeType" property="changeTypeName"/></INPUT>
										</label>
									</div>
								</logic:notEqual>
							</logic:iterate>
						</logic:present>
					</div>
				</div>
	
	<logic:equal name="revClaData" property="clauseChangeType" value="3">
		<input type="hidden" id="clauseChangeType" name="clauseChangeType" value="3">
		<html:hidden styleId="clauseChangeRequestSeqNo" name="ChangeRequest1058Form" property="clauseChangeRequestSeqNo" />
				
		<DIV id="divDelClause">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h4 class="panel-title"><strong>Delete Clause</strong></h4>
				</div>
				<div class="panel-body">
					<div class="form-group required-field">
						<label class="control-label col-sm-4">Select Clause</label>
						<div class="col-sm-2">
							<input type="text" name="clauseToDelete" id="clauseToDelete" disabled="disabled" class="form-control" value='<bean:write name="revClaData" property="changeFromClaNo"/>'/>
							<input type="hidden" id="hdnClauseToDelete" name="hdnClauseToDelete" value='<bean:write name="revClaData" property="changeFromClaNo"/>'/>
							<input type="hidden" id="hdnClauseToDeleteSeqNo" name="hdnClauseToDeleteSeqNo" value='<bean:write name="revClaData" property="changeFromClaSeqNo"/>'/>
						</div>
					</div>
                    <div class="form-group required-field">
						<label class="control-label col-sm-4">Reason</label>
						<div class="col-sm-3">
							<textarea name="reasonDel" styleId="reasonDel" rows="3" cols="60" /><bean:write name="revClaData" property="reason"/></textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12 text-center">
							<button class="btn btn-primary" type='button' id="deleteClause" ONCLICK="javascript:fnSaveDeleteClause(1,this)">Save</button>
							<button class="btn btn-primary" type='button' id="delClose">Cancel Clause Change</button>
						</div>
					</div>       
				</div>
			</div>
		</DIV>	
	</logic:equal>
			
	<jsp:include page="1058ReviseSelectedAddClause.jsp" />
			
	<logic:equal name="revClaData" property="clauseChangeType" value="2">
		<input type="hidden" id="clauseChangeType" name="clauseChangeType" value="2"/>
		<html:hidden styleId="clauseChangeRequestSeqNo" name="ChangeRequest1058Form" property="clauseChangeRequestSeqNo" />
		
		<DIV id="divModClause">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h4 class="panel-title"><strong>Modify Clause</strong></h4>
				</div>
				<div class="panel-body">
					<div class="form-group required-field">
						<label class="col-sm-4 control-label">Select Clause</label>
						<div class="col-sm-2">
							<input type="text" name="clauseToModify" id="clauseToModify" disabled="true" class="form-control" value='<bean:write name="revClaData" property="changeFromClaNo"/>'/>
							<input type="hidden" name="hdnClauseToModify" id="hdnClauseToModify" value='<bean:write name="revClaData" property="changeFromClaNo"/>'/>
							<input type="hidden" name="hdnClauseToModifySeqNo" id="hdnClauseToModifySeqNo" value='<bean:write name="revClaData" property="changeFromClaSeqNo"/>'/>
						</div>
					</div>
                       <div class="form-group required-field">
						<label class="col-sm-4 control-label">Clause Versions
							<A HREF="javascript:fnLoadClauseVersion()">
							<span class="glyphicon glyphicon-search" id="modSelect" aria-hidden="true" alt="search"></span></A>
						</label>
						<div class="col-sm-2">
							<logic:notPresent name="ChangeRequest1058Form" scope="request" property="versionClauseDetails">
								<input type="text" name="versionNoMod" id="versionNoMod" disabled="true" class="form-control" value='<bean:write name="revClaData" property="changeToClaVerNo"/>'/>
								<input type="hidden" id="hdnVersionNoMod" name="hdnVersionNoMod" value='<bean:write name="revClaData" property="changeToClaVerNo"/>'/>
							</logic:notPresent>
							<logic:present name="ChangeRequest1058Form" scope="request" property="versionClauseDetails">
						 		<logic:iterate id="verClaDetails" name="ChangeRequest1058Form" scope="request" property="versionClauseDetails" type="com.EMD.LSDB.vo.common.ClauseVO">
							 		<logic:greaterThan name="verClaDetails" property="versionNo" value="0">
							 			<input type="text" name="versionNoMod" id="versionNoMod" disabled="true" class="form-control" value='<bean:write name="verClaDetails" property="versionNo"/>'/>
							 		</logic:greaterThan>
							 		<logic:equal name="verClaDetails" property="versionNo" value="0">
							 			<input type="text" name="versionNoMod" id="versionNoMod" disabled="true" class="form-control" value=""/>
							 		</logic:equal>
						 			<input type="hidden" id="hdnVersionNoMod" name="hdnVersionNoMod" value='<bean:write name="verClaDetails" property="versionNo"/>'/>
						 		</logic:iterate>
						  </logic:present>
						</div>
					</div>
					<div class="form-group required-field">
						<label class="col-sm-4 control-label">New Clause Description</label>
						<div class="col-sm-4">
							<logic:notPresent name="ChangeRequest1058Form" scope="request" property="versionClauseDetails">
					 			<textarea name="clauseDescriptionMod" rows="3" cols="60" id="clauseDescriptionMod" class="form-control tinymceClaDesc"><%=(String.valueOf(revClaData.getClauseDescForTextArea())).replaceAll("null","").replaceAll("\n","<br>")%></textarea>
					 			<%--Added for CR-129 for loading clauses in TinyMCE Issue  --%>
									<textarea name="hdnclauseDescForTextArea" id="hdnclauseDescForTextArea"
									style="display:none;"><bean:write name="revClaData" property="clauseDescForTextArea"/></textarea>
								<%--Added for CR-129 for loading clauses in TinyMCE Issue  --%>	
				 			</logic:notPresent>
				 			<logic:present name="ChangeRequest1058Form" scope="request" property="versionClauseDetails">
				 				<logic:iterate id="verClaDetails" name="ChangeRequest1058Form" scope="request" property="versionClauseDetails" type="com.EMD.LSDB.vo.common.ClauseVO">
				 					<textarea name="clauseDescriptionMod" rows="3" cols="60" id="clauseDescriptionMod" class="form-control tinymceClaDesc"><%=(String.valueOf(verClaDetails.getClauseDesc())).replaceAll("  ","&nbsp;&nbsp;").replaceAll("\n","<br>")%></textarea>
				 					<%--Added for CR-129 for loading clauses in TinyMCE Issue  --%>
									<textarea name="hdnclauseDescForTextArea" id="hdnclauseDescForTextArea"
									style="display:none;"><bean:write name="verClaDetails" property="clauseDesc"/></textarea>
									<%--Added for CR-129 for loading clauses in TinyMCE Issue  --%>	
				 				</logic:iterate>
				 			</logic:present>
						</div>
					</div>
					
					<logic:notPresent name="ChangeRequest1058Form" scope="request" property="versionClauseDetails">
						<logic:empty name="revClaData" property="tableArrayData1">
							<div class="form-group">
								<label class="col-sm-4 control-label">Table Data
									<DIV name="showmainlink"><A
										CLASS='linkstyleItem' HREF="javascript:addTable('divModClause',0)">Add Table</A>&nbsp;</DIV>
									<DIV name="showsublink"  style="display:none;"><A
										CLASS=linkstyle1 HREF="javascript:addRow(this,'divModClause')">Add Row</A>&nbsp;|&nbsp;<A
										CLASS=linkstyle1 HREF="javascript:removeRow('divModClause')">Delete Row</A><br>
									<A CLASS='linkstyle1' HREF="javascript:deleteTable('divModClause')">Delete Table</A>&nbsp;</DIV>
								</label>	
								<div class="col-sm-8"> 
									<DIV name="showgrid"></DIV>
								</div>			
							</div>
						</logic:empty>
						<logic:notEmpty name="revClaData" property="tableArrayData1">
							<div class="form-group">
								<label class="col-sm-4 control-label">Table Data
									<DIV name="showmainlink" style="visibility:hidden"><A
										CLASS='linkstyleItem' HREF="javascript:addTable('divModClause',1)">Add Table</A>&nbsp;</DIV>
									<DIV name="showsublink"  style="visibility:visible"><A
										CLASS=linkstyle1 HREF="javascript:addRow(this,'divModClause')">Add Row</A>&nbsp;|&nbsp;<A
										CLASS=linkstyle1 HREF="javascript:removeRow('divModClause')">Delete Row</A><br>
									<A CLASS='linkstyle1' HREF="javascript:deleteTable('divModClause')">Delete Table</A>&nbsp;</DIV>
								</label>
								<div class="col-sm-8"> 
									<DIV name="showgrid">
										<div id="tblGridMod" class='form-horizontal'>
											<logic:notEmpty name="revClaData" property="tableArrayData1">
												<logic:iterate id="data" name="revClaData"
													property="tableArrayData1" indexId="counter">
													<div class='row tablerow'>
														<logic:iterate id="data1" name="data" indexId="innerCounter">
															<bean:define id="innerSize" name="innerCounter" />
		
															<bean:define id="size" name="counter" /> <logic:equal
																name="size" value="0">
																<!--This outer logic:equal check is for display the first row with bold font -->
																<logic:lessThan name="innerSize" value="1">
																	<!--This Inner logic:lessthan check is for display the first row first text box -->
																	<div class='col-sm-1'></div>
																	<div class='col-sm-2'>&nbsp;&nbsp;
																	<input type=text
																		name="clauseTableDataArray<%=innerCounter.intValue()+1%>Mod"
																		class="form-control textboxbluebold" size="13" maxlength="100"
																		value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>"
																		maxlength="100" /></div>
																</logic:lessThan>
		
																<logic:greaterThan name="innerSize" value="0">
																	<!-- This check is for display the rest of first row four text boxes -->
																		<div class='col-sm-2'>&nbsp;&nbsp;<input type=text
																		name="clauseTableDataArray<%=innerCounter.intValue()+1%>Mod"
																		class="form-control textboxbluebold" size="13" maxlength="100"
																		value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>"
																		maxlength="100" /></div>
																</logic:greaterThan>
															</logic:equal> <logic:greaterThan name="size" value="0">
																<!--This outer logic:greaterThan check is for display the second row datas -->
																<logic:equal name="innerSize" value="0">
																	<!--This Inner logic:equal check is for display the Second row first text box with radio buttons -->
																	<div class='col-sm-1 text-right'><label class='radio-inline'><html:radio property="deleterow" styleClass="radcheck"
																		value="" /></label></div><div class='col-sm-2'>&nbsp;&nbsp;
																		 <input type=text
																		name="clauseTableDataArray<%=innerCounter.intValue()+1%>Mod"
																		class="form-control" size="15" maxlength="100"
																		value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>"
																		maxlength="100" /></div>
																</logic:equal>
																<!--This Inner logic:notEqual check is for display the Second row other text box without radio buttons -->
																<logic:notEqual name="innerSize" value="0">
																<div class='col-sm-2'>&nbsp;&nbsp;
																<input type=text
																		name="clauseTableDataArray<%=innerCounter.intValue()+1%>Mod"
																		class="form-control" size="15" maxlength="100"
																		value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>"
																		maxlength="100" /></div>
																</logic:notEqual>
		
															</logic:greaterThan>
		
														</logic:iterate>
													</div>
												</logic:iterate>
											</logic:notEmpty>
										</DIV>
									</DIV>
								</div>
							</div>
						</logic:notEmpty>	
					</logic:notPresent>
					<logic:present name="ChangeRequest1058Form" property="versionClauseDetails" scope="request">
						<logic:iterate id="verClaDetails" name="ChangeRequest1058Form" scope="request" property="versionClauseDetails" type="com.EMD.LSDB.vo.common.ClauseVO">
							<logic:empty name="verClaDetails" property="tableArrayData1">
								<div class="form-group">
									<label class="col-sm-4 control-label">Table Data
										<DIV name="showmainlink"><A
											CLASS='linkstyleItem' HREF="javascript:addTable('divModClause',0)">Add Table</A>&nbsp;</DIV>
										<DIV name="showsublink"  style="display:none;"><A
											CLASS=linkstyle1 HREF="javascript:addRow(this,'divModClause')">Add Row</A>&nbsp;|&nbsp;<A
											CLASS=linkstyle1 HREF="javascript:removeRow('divModClause')">Delete Row</A><br>
										<A CLASS='linkstyle1' HREF="javascript:deleteTable('divModClause')">Delete Table</A>&nbsp;</DIV>
									</label>
									<div class="col-sm-8"> 
										<DIV name="showgrid"></DIV>
									</div>
								</div>
							</logic:empty>
							<logic:notEmpty name="verClaDetails" property="tableArrayData1">
								<div class="form-group">
									<label class="col-sm-4 control-label">Table Data
										<DIV   name="showmainlink" style="visibility:hidden"><A
											CLASS='linkstyleItem' HREF="javascript:addTable('divModClause',1)">Add Table</A>&nbsp;</DIV>
										<DIV  name="showsublink"  style="visibility:visible"><A
											CLASS=linkstyle1 HREF="javascript:addRow(this,'divModClause')">Add Row</A>&nbsp;|&nbsp;<A
											CLASS=linkstyle1 HREF="javascript:removeRow('divModClause')">Delete Row</A><br>
										<A CLASS='linkstyle1' HREF="javascript:deleteTable('divModClause')">Delete Table</A>&nbsp;</DIV>
									</label>
									<div class="col-sm-8"> 
										<DIV name="showgrid">
											<div id="tblGridMod" class='form-horizontal'>
												<logic:notEmpty name="verClaDetails" property="tableArrayData1">
													<logic:iterate id="data" name="verClaDetails"
														property="tableArrayData1" indexId="counter">
														<div class='row tablerow'>
															<logic:iterate id="data1" name="data" indexId="innerCounter">
																<bean:define id="innerSize" name="innerCounter" />
			
																<TD><bean:define id="size" name="counter" /> <logic:equal
																	name="size" value="0">
																	<!--This outer logic:equal check is for display the first row with bold font -->
																	<logic:lessThan name="innerSize" value="1">
																		<!--This Inner logic:lessthan check is for display the first row first text box -->
																	<div class='col-sm-1'></div>
																		<div class='col-sm-2'>&nbsp;&nbsp;
																		<input type=text
																			name="clauseTableDataArray<%=innerCounter.intValue()+1%>Mod"
																			class="form-control textboxbluebold" size="13" maxlength="100"
																			value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>"
																			maxlength="100" /></div>
																	</logic:lessThan>
			
																	<logic:greaterThan name="innerSize" value="0">
																		<!-- This check is for display the rest of first row four text boxes -->
																		<div class='col-sm-2'>&nbsp;&nbsp;<input type=text
																			name="clauseTableDataArray<%=innerCounter.intValue()+1%>Mod"
																			class="form-control textboxbluebold" size="13" maxlength="100"
																			value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>"
																			maxlength="100" /></div>
																	</logic:greaterThan>
																</logic:equal> <logic:greaterThan name="size" value="0">
																	<!--This outer logic:greaterThan check is for display the second row datas -->
																	<logic:equal name="innerSize" value="0">
																		<!--This Inner logic:equal check is for display the Second row first text box with radio buttons -->
																		<div class='col-sm-1 text-right'><label class='radio-inline'><html:radio property="deleterow" styleClass="radcheck"
																			value="" /></label></div><div class='col-sm-2'>&nbsp;&nbsp;
														 <input type=text
																			name="clauseTableDataArray<%=innerCounter.intValue()+1%>Mod"
																			class="form-control" size="15" maxlength="100"
																			value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>"
																			maxlength="100" /></div>
																	</logic:equal>
																	<!--This Inner logic:notEqual check is for display the Second row other text box without radio buttons -->
																	<logic:notEqual name="innerSize" value="0">
																		<div class='col-sm-2'>&nbsp;&nbsp;
																		<input type=text
																			name="clauseTableDataArray<%=innerCounter.intValue()+1%>Mod"
																			class="form-control" size="15" maxlength="100"
																			value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>"
																			maxlength="100" /></div>
																	</logic:notEqual>
			
																</logic:greaterThan>
			
															</logic:iterate>
														</div>
													</logic:iterate>
												</logic:notEmpty>
												</tbody>
											</Table>
										</DIV>
									</div>
								</div>
							</div>
						</logic:notEmpty>		
					</logic:iterate>
				</logic:present>

						<div class="panel panel-default col-sm-offset-1" style="width:90%;align:center;">
			   			<div class="panel-heading"><h3 class="panel-title text-center">Engineering Data <font color="red" valign="top">*</font></h3></div>
		   				<div class="panel-body">
			   				<div class="form-group">
								<label class="col-sm-3 control-label">Reference EDL Number(s)</label>
								<div>
									<logic:notPresent name="ChangeRequest1058Form" scope="request" property="versionClauseDetails">
										<div class="col-sm-2"><input type="text" class="form-control" size="20" name="refEdlNoMod" maxlength="20" value='<%=revClaData.getRefEDLNo()[0]%>'/></div>
										<div class="col-sm-2"><input type="text" class="form-control" size="20" name="refEdlNoMod" maxlength="20" value='<%=revClaData.getRefEDLNo()[1]%>'/></div>
										<div class="col-sm-2"><input type="text" class="form-control" size="20" name="refEdlNoMod" maxlength="20" value='<%=revClaData.getRefEDLNo()[2]%>'/></div>
									</logic:notPresent>
									<logic:present name="ChangeRequest1058Form" property="versionClauseDetails" scope="request">
										<logic:iterate id="verClaDetails" name="ChangeRequest1058Form" scope="request" property="versionClauseDetails" type="com.EMD.LSDB.vo.common.ClauseVO">
											<div class="col-sm-2"><input type="text" class="form-control" size="20" name="refEdlNoMod" maxlength="20" value='<%=verClaDetails.getRefEDLNo()[0]%>'/></div>
											<div class="col-sm-2"><input type="text" class="form-control" size="20" name="refEdlNoMod" maxlength="20" value='<%=verClaDetails.getRefEDLNo()[1]%>'/></div>
											<div class="col-sm-2"><input type="text" class="form-control" size="20" name="refEdlNoMod" maxlength="20" value='<%=verClaDetails.getRefEDLNo()[2]%>'/></div>
										</logic:iterate>
									</logic:present>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">New EDL Number(s)</label>
								<div>
									<logic:notPresent name="ChangeRequest1058Form" scope="request" property="versionClauseDetails">
										<div class="col-sm-2"><input type="text" class="form-control" size="20" name="newEdlNoMod" maxlength="20" value='<%=revClaData.getEdlNo()[0]%>'/></div>
										<div class="col-sm-2"><input type="text" class="form-control" size="20" name="newEdlNoMod" maxlength="20" value='<%=revClaData.getEdlNo()[1]%>'/></div>
										<div class="col-sm-2"><input type="text" class="form-control" size="20" name="newEdlNoMod" maxlength="20" value='<%=revClaData.getEdlNo()[2]%>'/></div>
									</logic:notPresent>
									<logic:present name="ChangeRequest1058Form" property="versionClauseDetails" scope="request">
										<logic:iterate id="verClaDetails" name="ChangeRequest1058Form" scope="request" property="versionClauseDetails" type="com.EMD.LSDB.vo.common.ClauseVO">
											<div class="col-sm-2"><input type="text" class="form-control" size="20" name="newEdlNoMod" maxlength="20" value='<%=verClaDetails.getEdlNo()[0]%>'/></div>
											<div class="col-sm-2"><input type="text" class="form-control" size="20" name="newEdlNoMod" maxlength="20" value='<%=verClaDetails.getEdlNo()[1]%>'/></div>
											<div class="col-sm-2"><input type="text" class="form-control" size="20" name="newEdlNoMod" maxlength="20" value='<%=verClaDetails.getEdlNo()[2]%>'/></div>
										</logic:iterate>
									</logic:present>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">Part Of </label>
									<logic:notPresent name="ChangeRequest1058Form" scope="request" property="versionClauseDetails">
										<div class="col-sm-2 form-inline">
											<div class="input-group">
												<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
													<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(1)"><span class="glyphicon glyphicon-search"></span></button>
												</span>
												<input type="text" class="form-control" name="partOf" readonly="true" value='<%=revClaData.getPartOfCode()[0]%>'/>
												<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
													<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(1)"><span class="glyphicon glyphicon-remove"></span></button>
												</span>
											</div>
											<input type="hidden" name="partOfSeqNo" value='<%=revClaData.getPartOfSeqNo()[0]%>'/>				
											<input type="hidden" name="clauseSeqNum" value='<%=revClaData.getClauseSeqNum()[0]%>'/>
										</div>	
										<div class="col-sm-2 form-inline">
											<div class="input-group">
												<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
													<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(2)"><span class="glyphicon glyphicon-search"></span></button>
												</span>
												<input type="text" class="form-control" name="partOf" readonly="true" value='<%=revClaData.getPartOfCode()[1]%>'/>
												<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
													<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(2)"><span class="glyphicon glyphicon-remove"></span></button>
												</span>
											</div>
											<input type="hidden" name="partOfSeqNo" value='<%=revClaData.getPartOfSeqNo()[1]%>'/>				
											<input type="hidden" name="clauseSeqNum" value='<%=revClaData.getClauseSeqNum()[1]%>'/>
										</div>	
										<div class="col-sm-2 form-inline">
											<div class="input-group">
												<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
													<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(3)"><span class="glyphicon glyphicon-search"></span></button>
												</span>
												<input type="text" class="form-control" name="partOf" readonly="true" value='<%=revClaData.getPartOfCode()[2]%>'/>
												<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
													<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(3)"><span class="glyphicon glyphicon-remove"></span></button>
												</span>
											</div>
											<input type="hidden" name="partOfSeqNo" value='<%=revClaData.getPartOfSeqNo()[2]%>'/>				
											<input type="hidden" name="clauseSeqNum" value='<%=revClaData.getClauseSeqNum()[2]%>'/>
										</div>	
										<div class="col-sm-2 form-inline">
											<div class="input-group">
												<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
													<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(4)"><span class="glyphicon glyphicon-search"></span></button>
												</span>
												<input type="text" class="form-control" name="partOf" readonly="true" value='<%=revClaData.getPartOfCode()[3]%>'/>
												<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
													<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(4)"><span class="glyphicon glyphicon-remove"></span></button>
												</span>
											</div>
											<input type="hidden" name="partOfSeqNo" value='<%=revClaData.getPartOfSeqNo()[3]%>'/>				
											<input type="hidden" name="clauseSeqNum" value='<%=revClaData.getClauseSeqNum()[3]%>'/>
										</div>	
									</logic:notPresent>
									<logic:present name="ChangeRequest1058Form" property="versionClauseDetails" scope="request">
										<logic:iterate id="verClaDetails" name="ChangeRequest1058Form" scope="request" property="versionClauseDetails" type="com.EMD.LSDB.vo.common.ClauseVO">
											<div class="col-sm-2 form-inline">
												<div class="input-group">
													<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
														<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(1)"><span class="glyphicon glyphicon-search"></span></button>
													</span>
													<input type="text" class="form-control" name="partOf" readonly="true" value='<%=verClaDetails.getPartOfCode()[0]%>'/>
													<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
														<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(1)"><span class="glyphicon glyphicon-remove"></span></button>
													</span>
												</div>
												<input type="hidden" name="partOfSeqNo" value='<%=verClaDetails.getPartOfSeqNo()[0]%>'/>
												<input type="hidden" name="clauseSeqNum" value='<%=verClaDetails.getPartOfClaSeqNo()[0]%>'/>
											</div>
											<div class="col-sm-2 form-inline">
												<div class="input-group">
													<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
														<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(2)"><span class="glyphicon glyphicon-search"></span></button>
													</span>
													<input type="text" class="form-control" name="partOf" readonly="true" value='<%=verClaDetails.getPartOfCode()[1]%>'/>
													<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
														<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(2)"><span class="glyphicon glyphicon-remove"></span></button>
													</span>
												</div>
												<input type="hidden" name="partOfSeqNo" value='<%=verClaDetails.getPartOfSeqNo()[1]%>'/>
												<input type="hidden" name="clauseSeqNum" value='<%=verClaDetails.getPartOfClaSeqNo()[1]%>'/>
											</div>
											<div class="col-sm-2 form-inline">
												<div class="input-group">
													<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
														<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(3)"><span class="glyphicon glyphicon-search"></span></button>
													</span>
													<input type="text" class="form-control" name="partOf" readonly="true" value='<%=verClaDetails.getPartOfCode()[2]%>'/>
													<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
														<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(3)"><span class="glyphicon glyphicon-remove"></span></button>
													</span>
												</div>
												<input type="hidden" name="partOfSeqNo" value='<%=verClaDetails.getPartOfSeqNo()[2]%>'/>
												<input type="hidden" name="clauseSeqNum" value='<%=verClaDetails.getPartOfClaSeqNo()[2]%>'/>
											</div>
											<div class="col-sm-2 form-inline">
												<div class="input-group">
													<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
														<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(4)"><span class="glyphicon glyphicon-search"></span></button>
													</span>
													<input type="text" class="form-control" name="partOf" readonly="true" value='<%=verClaDetails.getPartOfCode()[3]%>'/>
													<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
														<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(4)"><span class="glyphicon glyphicon-remove"></span></button>
													</span>
												</div>
												<input type="hidden" name="partOfSeqNo" value='<%=verClaDetails.getPartOfSeqNo()[3]%>'/>
												<input type="hidden" name="clauseSeqNum" value='<%=verClaDetails.getPartOfClaSeqNo()[3]%>'/>
											</div>																						
										</logic:iterate>
									</logic:present>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">DWO Number </label>
									<div class="col-sm-2">
		                                <logic:notPresent name="ChangeRequest1058Form" scope="request" property="versionClauseDetails">
											<logic:equal name="revClaData" property="dwONumber" value="0">
												<input type="text" class="form-control" size="20" maxlength="20" name="dwoNoMod" value=''/>
											</logic:equal>
											<logic:notEqual name="revClaData" property="dwONumber" value="0">
												<input type="text" class="form-control" size="20" maxlength="20" name="dwoNoMod" value='<bean:write name="revClaData" property="dwONumber"/>'/>
											</logic:notEqual>
										</logic:notPresent>
										<logic:present name="ChangeRequest1058Form" property="versionClauseDetails" scope="request">
											<logic:iterate id="verClaDetails" name="ChangeRequest1058Form" scope="request" property="versionClauseDetails" type="com.EMD.LSDB.vo.common.ClauseVO">
												<logic:equal name="verClaDetails" property="dwONumber" value="0">
								 				<input type="text" class="form-control" size="20" maxlength="20" name="dwoNoMod" value=''/>
												</logic:equal>
												<logic:notEqual name="verClaDetails" property="dwONumber" value="0">
								 					<input type="text" class="form-control" size="20" maxlength="20" name="dwoNoMod" value='<bean:write name="verClaDetails" property="dwONumber"/>'/>
												</logic:notEqual>
											</logic:iterate>
										</logic:present>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">Part Number </label>
									<div class="col-sm-2">
		                                <logic:notPresent name="ChangeRequest1058Form" scope="request" property="versionClauseDetails">
											<logic:equal name="revClaData" property="partNumber" value="0">
												<input type="text" name="partNoMod" maxlength="8" size="20" class="form-control" value=''/>
											</logic:equal>
											<logic:notEqual name="revClaData" property="partNumber" value="0">
												<input type="text" name="partNoMod" maxlength="8" size="20" class="form-control" value='<bean:write name="revClaData" property="partNumber"/>'/>
											</logic:notEqual>
										</logic:notPresent>
										<logic:present name="ChangeRequest1058Form" property="versionClauseDetails" scope="request">
											<logic:iterate id="verClaDetails" name="ChangeRequest1058Form" scope="request" property="versionClauseDetails" type="com.EMD.LSDB.vo.common.ClauseVO">
												<logic:equal name="verClaDetails" property="partNumber" value="0">
								 					<input type="text" class="form-control" size="20" maxlength="8" name="partNoMod" value=''/>
												</logic:equal>
												<logic:notEqual name="verClaDetails" property="partNumber" value="0">
													<input type="text" class="form-control" size="20" maxlength="8" name="partNoMod" value='<bean:write name="verClaDetails" property="partNumber"/>'/>
												</logic:notEqual>
											</logic:iterate>
										</logic:present>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">Price Book Number</label>
									<div class="col-sm-2">
										<logic:notPresent name="ChangeRequest1058Form" scope="request" property="versionClauseDetails">	
											<logic:equal name="revClaData" property="priceBookNumber" value="0">
												<input type="text" id="priceBookNo" name="priceBookNoMod" maxlength="4" class="form-control" size="20" value=''/>
											</logic:equal>
											<logic:notEqual name="revClaData" property="priceBookNumber" value="0">
												<input type="text" id="priceBookNo" name="priceBookNoMod" maxlength="4" class="form-control" size="20" value='<bean:write name="revClaData" property="priceBookNumber"/>'/>
											</logic:notEqual>
										</logic:notPresent>
										<logic:present name="ChangeRequest1058Form" property="versionClauseDetails" scope="request">
											<logic:iterate id="verClaDetails" name="ChangeRequest1058Form" scope="request" property="versionClauseDetails" type="com.EMD.LSDB.vo.common.ClauseVO">
												<logic:equal name="verClaDetails" property="priceBookNumber" value="0">
													<input type="text" class="form-control" size="20" maxlength="4" name="priceBookNoMod" value=''/>
												</logic:equal>
												<logic:notEqual name="verClaDetails" property="priceBookNumber" value="0">
													<input type="text" class="form-control" size="20" maxlength="4" name="priceBookNoMod" value='<bean:write name="verClaDetails" property="priceBookNumber"/>'/>
												</logic:notEqual>
											</logic:iterate>
										</logic:present>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">Standard Equipment</label>
									<div class="col-sm-4">
										<logic:notPresent name="ChangeRequest1058Form" scope="request" property="versionClauseDetails">	
										<html:select styleClass="form-control" property="standardEquipmentSeqNoMod" styleId="standardEquipmentSeqNoMod">
										<option value="-1">--Select--</option>
										<logic:present name="ChangeRequest1058Form" property="standardEquipList" scope="request">
										<logic:iterate id="stdEquip" name="ChangeRequest1058Form" property="standardEquipList" scope="request" type="com.EMD.LSDB.vo.common.StandardEquipVO">
											<logic:equal name="stdEquip" property="standardEquipmentSeqNo" value="<%=String.valueOf(revClaData.getStandardEquipmentSeqNo())%>">
												<option value='<bean:write name="stdEquip" property="standardEquipmentSeqNo"/>' selected><bean:write name="stdEquip" property="standardEquipmentDesc"/></option>
											</logic:equal>
											<logic:notEqual name="stdEquip" property="standardEquipmentSeqNo" value="<%=String.valueOf(revClaData.getStandardEquipmentSeqNo())%>">
												<option value='<bean:write name="stdEquip" property="standardEquipmentSeqNo"/>'><bean:write name="stdEquip" property="standardEquipmentDesc"/></option>
											</logic:notEqual>
										</logic:iterate>
										</logic:present>
										</html:select>
										</logic:notPresent>
										<logic:present name="ChangeRequest1058Form" property="versionClauseDetails" scope="request">
											 <logic:notPresent name="ChangeRequest1058Form" scope="request" property="standardEquipmentSeqNoMod">
												<html:select styleClass="form-control"
													property="standardEquipmentSeqNoMod" styleId="standardEquipmentSeqNoMod">
													<html:option value="-1">---Select---</html:option>
												</html:select>
												<logic:present name="ChangeRequest1058Form" property="standardEquipList" scope="request">
												<logic:iterate id="stdEquip" name="ChangeRequest1058Form" property="standardEquipList" scope="request" type="com.EMD.LSDB.vo.common.StandardEquipVO">
													<logic:equal name="stdEquip" property="standardEquipmentSeqNo" value="<%=String.valueOf(revClaData.getStandardEquipmentSeqNo())%>">
														<option value='<bean:write name="stdEquip" property="standardEquipmentSeqNo"/>' selected><bean:write name="stdEquip" property="standardEquipmentDesc"/></option>
													</logic:equal>
													<logic:notEqual name="stdEquip" property="standardEquipmentSeqNo" value="<%=String.valueOf(revClaData.getStandardEquipmentSeqNo())%>">
														<option value='<bean:write name="stdEquip" property="standardEquipmentSeqNo"/>'><bean:write name="stdEquip" property="standardEquipmentDesc"/></option>
													</logic:notEqual>
												</logic:iterate>
												</logic:present>
											</logic:notPresent>
											
											<logic:present name="ChangeRequest1058Form" scope="request" property="standardEquipmentSeqNoMod">
												<html:select styleClass="form-control" property="standardEquipmentSeqNoMod" styleId="standardEquipmentSeqNoMod">
												<option value='-1' selected>--Select--</option>
													<logic:present name="ChangeRequest1058Form" property="standardEquipList" scope="request">
													<logic:iterate id="stdEquip" name="ChangeRequest1058Form" property="standardEquipList" scope="request" type="com.EMD.LSDB.vo.common.StandardEquipVO">
														<logic:equal name="ChangeRequest1058Form" property="standardEquipmentSeqNoMod" value="<%=String.valueOf(stdEquip.getStandardEquipmentSeqNo())%>">
															<option value='<bean:write name="stdEquip" property="standardEquipmentSeqNo"/>' selected><bean:write name="stdEquip" property="standardEquipmentDesc"/></option>
														</logic:equal>
														<logic:notEqual name="ChangeRequest1058Form" property="standardEquipmentSeqNoMod" value="<%=String.valueOf(stdEquip.getStandardEquipmentSeqNo())%>">
															<option value='<bean:write name="stdEquip" property="standardEquipmentSeqNo"/>'><bean:write name="stdEquip" property="standardEquipmentDesc"/></option>
														</logic:notEqual>
													</logic:iterate>
													</logic:present>
												</html:select>
											</logic:present>
										</logic:present>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">Comments</label>
									<div class="col-sm-4">
										<logic:notPresent name="ChangeRequest1058Form" property="versionClauseDetails" scope="request">
											<textarea name="commentsMod" rows="3" cols="60" class="form-control"><bean:write name="revClaData" property="engDataComment"/></textarea>
										</logic:notPresent>
										<logic:present name="ChangeRequest1058Form" property="versionClauseDetails" scope="request">
											<logic:iterate id="verClaDetails" name="ChangeRequest1058Form" scope="request" property="versionClauseDetails" type="com.EMD.LSDB.vo.common.ClauseVO">
												<textarea name="commentsMod" rows="3" cols="60" class="form-control"><bean:write name="verClaDetails" property="comments"/></textarea>
											</logic:iterate>
							  			</logic:present>
							  		</div>
							  	</div>
							</div>
						</div>


						<div class="form-group required-field">
							<label class="col-sm-4 control-label">Reason</label>
							<div class="col-sm-4">
								<textarea name="reasonMod" styleId="reasonMod" rows="3" cols="60" class="form-control"><bean:write name="revClaData" property="reason"/></textarea>
							</div>
						</div>
						<div class="spacer10"></div>
						<div class="form-group"	>
							<div class="col-sm-12 text-center" >
								<button class="btn btn-primary" type='button' id="modifyClause" ONCLICK="javascript:fnCheckSpellnCont('fnSaveModifyClause(1,\'divModClause\')','clauseDescriptionMod')">Save</button>
								<button class="btn btn-primary" type='button' name="close" id="modClose">Cancel Clause Change</button>
							</div>
						</div>
					</div>
				</div>
			</DIV>
		</logic:equal>
			
		<logic:equal name="revClaData" property="clauseChangeType" value="4">
			<input type="hidden" id="clauseChangeType" name="clauseChangeType" value="4"/>
			<html:hidden styleId="clauseChangeRequestSeqNo" name="ChangeRequest1058Form" property="clauseChangeRequestSeqNo" />
			<input type="hidden" name="clauseToVerNo" value='<bean:write name="revClaData" property="changeToClaVerNo"/>'/>
			<input type="hidden" name="clauseToSeqNo" value='<bean:write name="revClaData" property="changeToClaSeqNo"/>'/>
				
	<DIV id="divChangeComp">
		<div class="panel panel-info">
			<div class="panel-heading">
				<h4 class="panel-title"><strong>Change Component</strong></h4>
			</div>
			<div class="panel-body">
				<div class="form-group required-field">
					<label class="col-sm-4 control-label">Section</label>
					<div class="col-sm-4">
				 		<SELECT name="sections" id="sltSectionChngComp" CLASS="form-control" disabled="disabled">
							<OPTION value='<bean:write name="revClaData" property="sectionSeqNo"/>' selected><bean:write name="revClaData" property="secCode"/>. <bean:write name="revClaData" property="sectionName"/></OPTION>
						</SELECT>
					</div>
				</div>
				<div class="form-group required-field">
					<label class="col-sm-4 control-label">SubSection</label>
					<div class="col-sm-4">
						<SELECT name="subSections" id="sltSubSectionChngComp" CLASS="form-control" disabled="disabled">
							<OPTION value='<bean:write name="revClaData" property="subSectionSeqNo"/>' selected><bean:write name="revClaData" property="subSecCode"/> <bean:write name="revClaData" property="subSectionName"/></OPTION>
						</SELECT>	
					</div>
				</div>
				<div class="form-group required-field">
					<label class="col-sm-4 control-label">Component Group</label>
					<div class="col-sm-4">	
						<SELECT name="componentGroup" id="sltLeadCompGroupChngComp" CLASS="form-control" disabled="disabled">
							<OPTION value='<bean:write name="revClaData" property="leadCompGrpSeqNo"/>' selected><bean:write name="revClaData" property="leadComponentGrpName"/></OPTION>
						</SELECT>	
						<html:hidden styleId="componentGrpSeqNoinAdd" property="componentGrpSeqNoinAdd" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Old Component</label>
					<div class="col-sm-4">
						<SELECT name="componentGroup" id="sltLeadCompChngCompOld" CLASS="form-control" disabled="disabled">
							<OPTION value='<bean:write name="revClaData" property="oldComponentSeqNo"/>' selected><bean:write name="revClaData" property="oldComponentName"/></OPTION>
						</SELECT>
					</div>	
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">New Component</label>
					<div class="col-sm-4">
						<SELECT name="components" id="sltLeadCompChngCompNew" CLASS="form-control" disabled="disabled" tabindex="8">
							<OPTION value='<bean:write name="revClaData" property="leadComponentSeqNo"/>' selected><bean:write name="revClaData" property="leadComponentName"/></OPTION>
						</SELECT>						
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">New Clause Description</label>
					<div class="col-sm-4">
						<textarea name="clauseDescriptionChngComp" rows="3" cols="60" id="clauseDescriptionChngComp" class="form-control tinymceClaDesc"><%=(String.valueOf(revClaData.getClauseDescForTextArea())).replaceAll("null","").replaceAll("\n","<br>")%></textarea>
					    <%--Added for CR-129 for loading clauses in TinyMCE Issue  --%>
						<textarea name="hdnclauseDescForTextArea" id="hdnclauseDescForTextArea"
						style="display:none;"><bean:write name="revClaData" property="clauseDescForTextArea"/></textarea>
					    <%--Added for CR-129 for loading clauses in TinyMCE Issue  --%>	
					</div>
				</div>
				<logic:empty name="revClaData" property="tableArrayData1">
	 				<div class="form-group">
						<label class="col-sm-4 control-label">Table Data
							<DIV   name="showmainlink"><A
								CLASS='linkstyleItem' HREF="javascript:addTable('divChangeComp',0)">Add Table</A>&nbsp;</DIV>
							<DIV  name="showsublink"  style="display:none;"><A
								CLASS=linkstyle1 HREF="javascript:addRow(this,'divChangeComp')">Add Row</A>&nbsp;|&nbsp;<A
								CLASS=linkstyle1 HREF="javascript:removeRow('divChangeComp')">Delete Row</A><br>
								<A CLASS='linkstyle1' HREF="javascript:deleteTable('divChangeComp')">Delete Table</A>&nbsp;
							</DIV>
						</label>
						<div class="col-sm-8"> 
							<DIV name="showgrid"></DIV>
						</div>
					</div>
				</logic:empty>
				<logic:notEmpty name="revClaData" property="tableArrayData1">
					<div class="form-group">
						<label class="col-sm-4 control-label">Table Data
							<DIV name="showmainlink" style="visibility:hidden"><A
								CLASS='linkstyleItem' HREF="javascript:addTable('divChangeComp',1)">Add Table</A>&nbsp;</DIV>
							<DIV name="showsublink"  style="visibility:visible"><A
								CLASS=linkstyle1 HREF="javascript:addRow(this,'divChangeComp')">Add Row</A>&nbsp;|&nbsp;<A
								CLASS=linkstyle1 HREF="javascript:removeRow('divChangeComp')">Delete Row</A><br>
							<A CLASS='linkstyle1' HREF="javascript:deleteTable('divChangeComp')">Delete Table</A>&nbsp;</DIV>
						</label>
						<div class="col-sm-8"> 
							<DIV name="showgrid">
							<div id="tblGridChngComp" class='form-horizontal'>
								<logic:notEmpty name="revClaData" property="tableArrayData1">
									<logic:iterate id="data" name="revClaData"
										property="tableArrayData1" indexId="counter">
										<div class='row tablerow'>
											<logic:iterate id="data1" name="data" indexId="innerCounter">
												<bean:define id="innerSize" name="innerCounter" />

												<bean:define id="size" name="counter" /> <logic:equal
													name="size" value="0">
													
													<!--This outer logic:equal check is for display the first row with bold font -->
													<logic:lessThan name="innerSize" value="1">
														<!--This Inner logic:lessthan check is for display the first row first text box -->
														<div class='col-sm-1'></div>
														<div class='col-sm-2'>&nbsp;&nbsp;
														<input type=text
																name="clauseTableDataArray<%=innerCounter.intValue()+1%>ChngComp"
																class="form-control textboxbluebold" size="13" maxlength="100"
																value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>"
																maxlength="100" /></div>
													</logic:lessThan>

													<logic:greaterThan name="innerSize" value="0">
														<!-- This check is for display the rest of first row four text boxes -->
														<div class='col-sm-2'>&nbsp;&nbsp;<input type=text
															name="clauseTableDataArray<%=innerCounter.intValue()+1%>ChngComp"
															class="form-control textboxbluebold" size="13" maxlength="100"
															value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>"
															maxlength="100" /></div>
													</logic:greaterThan>
													
												</logic:equal> <logic:greaterThan name="size" value="0">
													<!--This outer logic:greaterThan check is for display the second row datas -->
													<logic:equal name="innerSize" value="0">
														<!--This Inner logic:equal check is for display the Second row first text box with radio buttons -->
														<div class='col-sm-1 text-right'><label class='radio-inline'><html:radio property="deleterow" 
															value="" /></label></div><div class='col-sm-2'>&nbsp;&nbsp;
										 				<input type=text
															name="clauseTableDataArray<%=innerCounter.intValue()+1%>ChngComp"
															class="form-control" size="15" maxlength="100"
															value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>"
															maxlength="100" /></div>
													</logic:equal>
													<!--This Inner logic:notEqual check is for display the Second row other text box without radio buttons -->
													<logic:notEqual name="innerSize" value="0">
													<div class='col-sm-2'>&nbsp;&nbsp;
													 <input type=text
															name="clauseTableDataArray<%=innerCounter.intValue()+1%>ChngComp"
															class="form-control" size="15" maxlength="100"
															value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>"
															maxlength="100" />
													</div>
													</logic:notEqual>

												</logic:greaterThan>

											</logic:iterate>
										</div>
									</logic:iterate>
								</logic:notEmpty>
								</div>
							</DIV>
						</div>
					</div>
				</logic:notEmpty>	

				<div class="panel panel-default col-sm-offset-1" style="width:90%;align:center;">
		   			<div class="panel-heading"><h3 class="panel-title text-center">Engineering Data <font color="red" valign="top">*</font></h3></div>
	   				<div class="panel-body">
						<div class="form-group">
							<label class="col-sm-3 control-label">Reference EDL Number(s)</label>
							<div>
								<div class="col-sm-2"><input type="text" class="form-control" size="20" name="refEdlNoChngComp" maxlength="20" value='<%=revClaData.getRefEDLNo()[0]%>'/></div>
								<div class="col-sm-2"><input type="text" class="form-control" size="20" name="refEdlNoChngComp" maxlength="20" value='<%=revClaData.getRefEDLNo()[1]%>'/></div>
								<div class="col-sm-2"><input type="text" class="form-control" size="20" name="refEdlNoChngComp" maxlength="20" value='<%=revClaData.getRefEDLNo()[2]%>'/></div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">New EDL Number(s)</label>
							<div>
								<div class="col-sm-2"><input type="text" class="form-control" size="20" name="newEdlNoChngComp" maxlength="20" value='<%=revClaData.getEdlNo()[0]%>'/></div>
								<div class="col-sm-2"><input type="text" class="form-control" size="20" name="newEdlNoChngComp" maxlength="20" value='<%=revClaData.getEdlNo()[1]%>'/></div>
								<div class="col-sm-2"><input type="text" class="form-control" size="20" name="newEdlNoChngComp" maxlength="20" value='<%=revClaData.getEdlNo()[2]%>'/></div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Part Of </label>
							<div class="col-sm-2 form-inline">
								<div class="input-group">
									<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
										<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(1)"><span class="glyphicon glyphicon-search"></span></button>
									</span>
									<input type="text" class="form-control" name="partOf" readonly="true" value='<%=revClaData.getPartOfCode()[0]%>'/>			
									<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
										<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(1)"><span class="glyphicon glyphicon-remove"></span></button>
									</span>
								</div>
								<input type="hidden" name="partOfSeqNo" value='<%=revClaData.getPartOfSeqNo()[0]%>'/>				
								<input type="hidden" name="clauseSeqNum" value='<%=revClaData.getClauseSeqNum()[0]%>'/>	
							</div>
							<div class="col-sm-2 form-inline">
								<div class="input-group">
									<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
										<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(2)"><span class="glyphicon glyphicon-search"></span></button>
									</span>
									<input type="text" class="form-control" name="partOf" readonly="true" value='<%=revClaData.getPartOfCode()[1]%>'/>			
									<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
										<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(2)"><span class="glyphicon glyphicon-remove"></span></button>
									</span>
								</div>
								<input type="hidden" name="partOfSeqNo" value='<%=revClaData.getPartOfSeqNo()[1]%>'/>				
								<input type="hidden" name="clauseSeqNum" value='<%=revClaData.getClauseSeqNum()[1]%>'/>	
							</div>
							<div class="col-sm-2 form-inline">
								<div class="input-group">
									<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
										<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(3)"><span class="glyphicon glyphicon-search"></span></button>
									</span>
									<input type="text" class="form-control" name="partOf" readonly="true" value='<%=revClaData.getPartOfCode()[2]%>'/>			
									<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
										<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(3)"><span class="glyphicon glyphicon-remove"></span></button>
									</span>
								</div>
								<input type="hidden" name="partOfSeqNo" value='<%=revClaData.getPartOfSeqNo()[2]%>'/>				
								<input type="hidden" name="clauseSeqNum" value='<%=revClaData.getClauseSeqNum()[2]%>'/>	
							</div>
							<div class="col-sm-2 form-inline">
								<div class="input-group">
									<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
										<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(4)"><span class="glyphicon glyphicon-search"></span></button>
									</span>
									<input type="text" class="form-control" name="partOf" readonly="true" value='<%=revClaData.getPartOfCode()[3]%>'/>			
									<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
										<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(4)"><span class="glyphicon glyphicon-remove"></span></button>
									</span>
								</div>
								<input type="hidden" name="partOfSeqNo" value='<%=revClaData.getPartOfSeqNo()[3]%>'/>				
								<input type="hidden" name="clauseSeqNum" value='<%=revClaData.getClauseSeqNum()[3]%>'/>	
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">DWO Number</label>
                               <div class="col-sm-2">
								<logic:equal name="revClaData" property="dwONumber" value="0"> 
									<input type="text" class="form-control" size="20" maxlength="20" name="dwoNoChngComp" value=''/>
								</logic:equal>
								<logic:notEqual name="revClaData" property="dwONumber" value="0"> 
									<input type="text" class="form-control" size="20" maxlength="20" name="dwoNoChngComp" value='<bean:write name="revClaData" property="dwONumber"/>'/>
								</logic:notEqual>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Part Number</label>
                               <div class="col-sm-2">
								<logic:equal name="revClaData" property="partNumber" value="0"> 	
									<input type="text" name="partNoChngComp" maxlength="8" size="20" class="form-control" value=''/>
								</logic:equal>
								<logic:notEqual name="revClaData" property="partNumber" value="0"> 
									<input type="text" name="partNoChngComp" maxlength="8" size="20" class="form-control" value='<bean:write name="revClaData" property="partNumber"/>'/>
								</logic:notEqual>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Price Book Number</label>
                               <div class="col-sm-2">
								<logic:equal name="revClaData" property="priceBookNumber" value="0"> 	
									<input type="text" id="priceBookNo" name="priceBookChngComp" maxlength="4" class="form-control" size="20" value=''/>
								</logic:equal>
								<logic:notEqual name="revClaData" property="priceBookNumber" value="0"> 
									<input type="text" id="priceBookNo" name="priceBookChngComp" maxlength="4" class="form-control" size="20" value='<bean:write name="revClaData" property="priceBookNumber"/>'/>
								</logic:notEqual>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Standard Equipment</label>
							<div class="col-sm-4">
								<html:select styleClass="form-control" property="standardEquipmentSeqNoChngComp" styleId="standardEquipmentSeqNoChngComp">
								<option value="-1">--Select--</option>
								<logic:present name="ChangeRequest1058Form" property="standardEquipList" scope="request">
								<logic:iterate id="stdEquip" name="ChangeRequest1058Form" property="standardEquipList" scope="request" type="com.EMD.LSDB.vo.common.StandardEquipVO">
									<logic:equal name="stdEquip" property="standardEquipmentSeqNo" value="<%=String.valueOf(revClaData.getStandardEquipmentSeqNo())%>">
										<option value='<bean:write name="stdEquip" property="standardEquipmentSeqNo"/>' selected><bean:write name="stdEquip" property="standardEquipmentDesc"/></option>
									</logic:equal>
									<logic:notEqual name="stdEquip" property="standardEquipmentSeqNo" value="<%=String.valueOf(revClaData.getStandardEquipmentSeqNo())%>">
										<option value='<bean:write name="stdEquip" property="standardEquipmentSeqNo"/>'><bean:write name="stdEquip" property="standardEquipmentDesc"/></option>
									</logic:notEqual>
								</logic:iterate>
								</logic:present>
								</html:select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Comments</label>
							<div class="col-sm-4">
								 <textarea name="commentsChngComp" rows="3" cols="60" class="form-control"><bean:write name="revClaData" property="engDataComment"/></textarea>
							</div>
						</div>
					</div>
				</div>

				<div class="form-group required-field">
					<label class="col-sm-4 control-label">Reason</label>
					<div class="col-sm-4">
	 					<textarea name="reasonChngComp" styleId="reasonChngComp" rows="3" cols="60" class="form-control"><bean:write name="revClaData" property="reason"/></textarea>
	 				</div>
	 			</div>
	 			<div class="spacer10"></div>
				<div class="form-group"	>
					<div class="col-sm-12 text-center" >
						<button class="btn btn-primary" type='button' id="componentClause" ONCLICK="javascript:fnCheckSpellnCont('fnSaveChangeComponent(1,\'divChangeComp\')','clauseDescriptionChngComp')">Save</button>
						<button class="btn btn-primary" type='button' id="compClose">Cancel Clause Change</button>
					</div>
				</div>
			</div>
		</div>
	</DIV>
</logic:equal>
</logic:iterate>
</logic:present>
</div></div>	
		