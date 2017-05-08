<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>

<logic:notPresent name="ChangeRequest1058Form" property="reviseClauseDetailsList" scope="request">
	<DIV id="divChangeComp">
		<div class="panel panel-info">
			<div class="panel-heading">
				<h4 class="panel-title"><strong>Change Component</strong></h4>
			</div>
			<div class="panel-body">
				<div class="form-group required-field">
					<label class="col-sm-4 control-label">Section</label>
					<div class="col-sm-4">
						<logic:notPresent name="ChangeRequest1058Form" scope="request" property="sectionSeqNo">
							 <html:select property="sections" styleClass='form-control' tabindex="8" styleId="sltSectionChngComp"></html:select>
						</logic:notPresent>
						<logic:present name="ChangeRequest1058Form" scope="request" property="sectionSeqNo">
			 				<select name="sections" class="form-control" tabindex="8" id="sltSectionChngComp">
			 				<logic:present name="ChangeRequest1058Form" scope="request" property="sectionList">
			 					<logic:iterate id="sections" name="ChangeRequest1058Form" scope="request" property="sectionList" type="com.EMD.LSDB.vo.common.SectionVO">
			 						<logic:equal name="ChangeRequest1058Form" property="sectionSeqNo" value='<%=String.valueOf(sections.getSectionSeqNo())%>'>
			 							<option value='<bean:write name="sections" property="sectionSeqNo"/>' selected><bean:write name="sections" property="sectionDisplay"/></option>
			 						</logic:equal>
			 						<logic:notEqual name="ChangeRequest1058Form" property="sectionSeqNo" value='<%=String.valueOf(sections.getSectionSeqNo())%>'>
			 							<option value='<bean:write name="sections" property="sectionSeqNo"/>'><bean:write name="sections" property="sectionDisplay"/></option>
			 						</logic:notEqual>
			 					</logic:iterate>
			 				</logic:present>
			 				</select>
			 			</logic:present>
					</div>
				</div>
				<div class="form-group required-field">
					<label class="col-sm-4 control-label">SubSection</label>
					<div class="col-sm-4">
						<logic:notPresent name="ChangeRequest1058Form" scope="request" property="subSectionSeqNo">
						 	<html:select property="subSections" styleClass='form-control' tabindex="8" styleId="sltSubSectionChngComp">
							</html:select>
						</logic:notPresent>
						<logic:present name="ChangeRequest1058Form" scope="request" property="subSectionSeqNo">
			 				<select name="subSections" class="form-control" tabindex="8" id="sltSubSectionChngComp">
			 				<logic:present name="ChangeRequest1058Form" scope="request" property="subSectionList">
			 					<logic:iterate id="subSections" name="ChangeRequest1058Form" scope="request" property="subSectionList" type="com.EMD.LSDB.vo.common.SubSectionVO">
			 						
			 						<logic:equal name="ChangeRequest1058Form" property="subSectionSeqNo" value='<%=String.valueOf(subSections.getSubSecSeqNo())%>'>
			 						<option value='<bean:write name="subSections" property="subSecSeqNo"/>' selected><bean:write name="subSections" property="subCode"/> <bean:write name="subSections" property="subSecName"/></option>
			 						</logic:equal>
			 						<logic:notEqual name="ChangeRequest1058Form" property="subSectionSeqNo" value='<%=String.valueOf(subSections.getSubSecSeqNo())%>'>
			 						<option value='<bean:write name="subSections" property="subSecSeqNo"/>'><bean:write name="subSections" property="subCode"/> <bean:write name="subSections" property="subSecName"/></option>
			 						</logic:notEqual>
			 					</logic:iterate>
			 				</logic:present>
			 				</select>
			 			</logic:present>
					</div>
				</div>
				<div class="form-group required-field">
					<label class="col-sm-4 control-label">Component Group</label>
					<div class="col-sm-4">
						<logic:notPresent name="ChangeRequest1058Form" scope="request" property="componentGrpSeqNoinAdd">
							<html:select property="componentGroup" styleClass='form-control' tabindex="8" styleId="sltLeadCompGroupChngComp">
							</html:select>
						</logic:notPresent>
						<logic:present name="ChangeRequest1058Form" scope="request" property="componentGrpSeqNoinAdd">
							<select name="componentGroup" class="form-control" tabindex="8" id="sltLeadCompGroupChngComp">
							<logic:present name="ChangeRequest1058Form" scope="request" property="componentGrpList">
			 					<logic:iterate id="componentGrps" name="ChangeRequest1058Form" scope="request" property="componentGrpList" type="com.EMD.LSDB.vo.common.CompGroupVO">
			 						
			 						<logic:equal name="ChangeRequest1058Form" property="componentGrpSeqNoinAdd" value='<%=String.valueOf(componentGrps.getComponentGroupSeqNo())%>'>
			 						<option value='<bean:write name="componentGrps" property="componentGroupSeqNo"/>' selected><bean:write name="componentGrps" property="componentGroupName"/></option>
			 						</logic:equal>
			 						<logic:notEqual name="ChangeRequest1058Form" property="componentGrpSeqNoinAdd" value='<%=String.valueOf(componentGrps.getComponentGroupSeqNo())%>'>
			 						<option value='<bean:write name="componentGrps" property="componentGroupSeqNo"/>'><bean:write name="componentGrps" property="componentGroupName"/></option>
			 						</logic:notEqual>
			 					</logic:iterate>
			 				</logic:present>
			 				</select>
			 			</logic:present>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Old Component</label>
					<div class="col-sm-4">
						<logic:notPresent name="ChangeRequest1058Form" scope="request" property="hdnLeadComponentSeqNoinAdd">
							 <html:select property="components" styleClass='form-control' tabindex="8" styleId="sltLeadCompChngCompOld" disabled="true">
							</html:select>
						</logic:notPresent>
						<logic:present name="ChangeRequest1058Form" scope="request" property="hdnLeadComponentSeqNoinAdd">
							<select name="components" class="form-control" tabindex="8" id="sltLeadCompChngCompOld" disabled="true">
								<logic:present name="ChangeRequest1058Form" scope="request" property="componentList">
								<logic:iterate id="components" name="ChangeRequest1058Form" scope="request" property="componentList" type="com.EMD.LSDB.vo.common.ComponentVO">
			 					<logic:equal name="components" property="selectedComponent" value="Y">
			 						<option value='<bean:write name="components" property="componentSeqNo"/>' selected> <bean:write name="components" property="componentName"/></option>
			 					</logic:equal>
			 				</logic:iterate>
			 					</logic:present>
			 				</select>
						</logic:present>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">New Component</label>
					<div class="col-sm-4">
						<logic:notPresent name="ChangeRequest1058Form" scope="request" property="hdnLeadComponentSeqNoinAdd">
							<html:select property="components" styleClass='form-control' tabindex="8" styleId="sltLeadCompChngCompNew">
							</html:select>
						</logic:notPresent>
						<logic:present name="ChangeRequest1058Form" scope="request" property="hdnLeadComponentSeqNoinAdd">
							<select name="components" class="form-control" tabindex="8" id="sltLeadCompChngCompNew">
							<logic:present name="ChangeRequest1058Form" scope="request" property="componentList">
									<option value="-1">---Select---</option> <%-- Added for CR_128 to make Change Component Clause to empty  --%>	
			 					<logic:iterate id="components" name="ChangeRequest1058Form" scope="request" property="componentList" type="com.EMD.LSDB.vo.common.ComponentVO">
			 						<logic:equal name="ChangeRequest1058Form" property="hdnLeadComponentSeqNoinAdd" value='<%=String.valueOf(components.getComponentSeqNo())%>'>
			 						<option value='<bean:write name="components" property="componentSeqNo"/>' selected><bean:write name="components" property="componentName"/></option>
			 						</logic:equal>
			 						<logic:notEqual name="ChangeRequest1058Form" property="hdnLeadComponentSeqNoinAdd" value='<%=String.valueOf(components.getComponentSeqNo())%>'>
			 						<option value='<bean:write name="components" property="componentSeqNo"/>'><bean:write name="components" property="componentName"/></option>
			 						</logic:notEqual>
			 					</logic:iterate>
			 				</logic:present>
			 				</select>
			 			</logic:present>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">New Clause Description</label>
					<div class="col-sm-4">
						<logic:notPresent name="ChangeRequest1058Form" scope="request" property="changeComponentClause">
							<html:textarea property="clauseDescriptionChngComp" rows="3" cols="60" styleId="clauseDescriptionChngComp" styleClass="form-control tinymceClaDesc"/>
					 	</logic:notPresent>
						<logic:present name="ChangeRequest1058Form" scope="request" property="changeComponentClause">
							<logic:iterate id="chngCompDetails" name="ChangeRequest1058Form" scope="request" property="changeComponentClause" type="com.EMD.LSDB.vo.common.ClauseVO">
							  <textarea name="clauseDescriptionChngComp" rows="3" cols="60" id="clauseDescriptionChngComp" class="form-control"><%=(String.valueOf(chngCompDetails.getClauseDesc())).replaceAll("  ","&nbsp;&nbsp;").replaceAll("\n","<br>").replaceAll("<p>","")%></textarea>
							  <%--Added for CR-129 for loading clauses in TinyMCE Issue  --%>
							  <textarea name="hdnclauseDescForTextArea" id="hdnclauseDescForTextArea"
							  style="display:none;"><bean:write name="chngCompDetails" property="clauseDesc"/></textarea>
							  <%--Added for CR-129 for loading clauses in TinyMCE Issue  --%>	
							</logic:iterate>
						</logic:present>
					</div>
				</div>
				<logic:notPresent name="ChangeRequest1058Form" property="changeComponentClause" scope="request">
					<div class="form-group">
						<label class="col-sm-4 control-label">Table Data
							<DIV   name="showmainlink">
								<A CLASS='linkstyleItem' HREF="javascript:addTable('divChangeComp',0)">Add Table</A>&nbsp;
							</DIV>
							<DIV  name="showsublink"  style="display:none;">
								<A CLASS=linkstyle1 HREF="javascript:addRow(this,'divChangeComp')">Add Row</A>&nbsp;|&nbsp;
								<A CLASS=linkstyle1 HREF="javascript:removeRow('divChangeComp')">Delete Row</A><br>
								<A CLASS='linkstyle1' HREF="javascript:deleteTable('divChangeComp')">Delete Table</A>&nbsp;
							</DIV>
						</label>	
						<div class="col-sm-8"> 
							<DIV name="showgrid"></DIV>
						</div>			
					</div>
				</logic:notPresent>
				<logic:present name="ChangeRequest1058Form" property="changeComponentClause" scope="request">
					<logic:iterate id="chngCompDetails" name="ChangeRequest1058Form" scope="request" property="changeComponentClause" type="com.EMD.LSDB.vo.common.ClauseVO">
						<logic:empty name="chngCompDetails" property="tableArrayData1">
							<div class="form-group">
								<label class="col-sm-4 control-label">Table Data
									<DIV   name="showmainlink">
										<A CLASS='linkstyleItem' HREF="javascript:addTable('divChangeComp',0)">Add Table</A>&nbsp;
									</DIV>
									<DIV  name="showsublink"  style="display:none;">
										<A CLASS=linkstyle1 HREF="javascript:addRow(this,'divChangeComp')">Add Row</A>&nbsp;|&nbsp;
										<A CLASS=linkstyle1 HREF="javascript:removeRow('divChangeComp')">Delete Row</A><br>
										<A CLASS='linkstyle1' HREF="javascript:deleteTable('divChangeComp')">Delete Table</A>&nbsp;
									</DIV>
								</label>	
								<div class="col-sm-8"> 
									<DIV name="showgrid"></DIV>
								</div>			
							</div>
						</logic:empty>
						<logic:notEmpty name="chngCompDetails" property="tableArrayData1">
							<div class="form-group">
								<label class="col-sm-2 control-label">Table Data
									<DIV   name="showmainlink" style="visibility:hidden"><A
										CLASS='linkstyleItem' HREF="javascript:addTable('divChangeComp',1)">Add Table</A>&nbsp;</DIV>
									<DIV  name="showsublink"  style="visibility:visible"><A
										CLASS=linkstyle1 HREF="javascript:addRow(this,'divChangeComp')">Add Row</A>&nbsp;|&nbsp;<A
										CLASS=linkstyle1 HREF="javascript:removeRow('divChangeComp')">Delete Row</A><br>
									<A CLASS='linkstyle1' HREF="javascript:deleteTable('divChangeComp')">Delete Table</A>&nbsp;</DIV>
								</label>
								<div class="col-sm-8"> 
									<DIV name="showgrid">
										<div id="tblGridMod" class='form-horizontal'>
											<logic:notEmpty name="chngCompDetails" property="tableArrayData1">
													<logic:iterate id="data" name="chngCompDetails"
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
																			name="clauseTableDataArray<%=innerCounter.intValue()+1%>ChngComp"
																			class="form-control" size="13" maxlength="100"
																			value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>"
																			maxlength="100" /></div>
																	</logic:lessThan>
			
																	<logic:greaterThan name="innerSize" value="0">
																		<!-- This check is for display the rest of first row four text boxes -->
																		<div class='col-sm-2'>&nbsp;&nbsp;<input type=text
																			name="clauseTableDataArray<%=innerCounter.intValue()+1%>ChngComp"
																			class="form-control" size="13" maxlength="100"
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
																			maxlength="100" /></div>
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
					</logic:iterate>
				</logic:present>
			
				<div class="panel panel-default col-sm-offset-1" style="width:90%;align:center;">
		   			<div class="panel-heading"><h3 class="panel-title text-center">Engineering Data <font color="red" valign="top">*</font></h3></div>
	   				<div class="panel-body">
						<div class="form-group">
							<label class="col-sm-3 control-label">Reference EDL Number(s)</label>
							<div>
								<logic:notPresent name="ChangeRequest1058Form" scope="request" property="changeComponentClause">
									<div class="col-sm-2"><html:text styleClass="form-control" size="20" property="refEdlNoChngComp" maxlength="20" value="" /></div>
									<div class="col-sm-2"><html:text styleClass="form-control" size="20" property="refEdlNoChngComp" maxlength="20" value="" /></div>
									<div class="col-sm-2"><html:text styleClass="form-control" size="20" property="refEdlNoChngComp" maxlength="20" value="" /></div>
								</logic:notPresent>
					 			<logic:present name="ChangeRequest1058Form" scope="request" property="changeComponentClause">
					 				<logic:iterate id="chngCompDetails" name="ChangeRequest1058Form" scope="request" property="changeComponentClause" type="com.EMD.LSDB.vo.common.ClauseVO">
										<div class="col-sm-2"><input type="text" class="form-control" size="20" name="refEdlNoChngComp" maxlength="20" value='<%=chngCompDetails.getRefEDLNo()[0]%>'/></div>
										<div class="col-sm-2"><input type="text" class="form-control" size="20" name="refEdlNoChngComp" maxlength="20" value='<%=chngCompDetails.getRefEDLNo()[1]%>'/></div>
										<div class="col-sm-2"><input type="text" class="form-control" size="20" name="refEdlNoChngComp" maxlength="20" value='<%=chngCompDetails.getRefEDLNo()[2]%>'/></div>
									</logic:iterate>
					  			</logic:present>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">New EDL Number(s)</label>
							<div>
								<logic:notPresent name="ChangeRequest1058Form" scope="request" property="changeComponentClause">
									<div class="col-sm-2"><html:text styleClass="form-control" size="20" property="newEdlNoChgComp" maxlength="20" value="" /></div>
									<div class="col-sm-2"><html:text styleClass="form-control" size="20" property="newEdlNoChngComp" maxlength="20" value="" /></div>
									<div class="col-sm-2"><html:text styleClass="form-control" size="20" property="newEdlNoChngComp" maxlength="20" value="" /></div>
								</logic:notPresent>
								<logic:present name="ChangeRequest1058Form" scope="request" property="changeComponentClause">
					 				<logic:iterate id="chngCompDetails" name="ChangeRequest1058Form" scope="request" property="changeComponentClause" type="com.EMD.LSDB.vo.common.ClauseVO">
										<div class="col-sm-2"><input type="text" class="form-control" size="20" name="newEdlNoChngComp" maxlength="20" value='<%=chngCompDetails.getEdlNo()[0]%>'/></div>
										<div class="col-sm-2"><input type="text" class="form-control" size="20" name="newEdlNoChngComp" maxlength="20" value='<%=chngCompDetails.getEdlNo()[1]%>'/></div>
										<div class="col-sm-2"><input type="text" class="form-control" size="20" name="newEdlNoChngComp" maxlength="20" value='<%=chngCompDetails.getEdlNo()[2]%>'/></div>
									</logic:iterate>
								</logic:present>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Part Of </label>
								<logic:notPresent name="ChangeRequest1058Form" scope="request" property="changeComponentClause">
									<div class="col-sm-2 form-inline">
										<div class="input-group">
											<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
												<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(9)"><span class="glyphicon glyphicon-search"></span></button>
											</span>
											<html:text styleClass="form-control" property="partOf" readonly="true" value="" />				
											<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
												<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(9)"><span class="glyphicon glyphicon-remove"></span></button>
											</span>
										</div>
										<html:hidden property="partOfSeqNo" value="" /> 
										<html:hidden property="clauseSeqNum" value="" />
									</div>
									<div class="col-sm-2 form-inline">
										<div class="input-group">
											<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
												<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(10)"><span class="glyphicon glyphicon-search"></span></button>
											</span>
											<html:text styleClass="form-control" property="partOf" readonly="true" value="" />				
											<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
												<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(10)"><span class="glyphicon glyphicon-remove"></span></button>
											</span>
										</div>
										<html:hidden property="partOfSeqNo" value="" /> 
										<html:hidden property="clauseSeqNum" value="" />
									</div>
									<div class="col-sm-2 form-inline">
										<div class="input-group">
											<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
												<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(11)"><span class="glyphicon glyphicon-search"></span></button>
											</span>
											<html:text styleClass="form-control" property="partOf" readonly="true" value="" />				
											<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
												<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(11)"><span class="glyphicon glyphicon-remove"></span></button>
											</span>
										</div>
										<html:hidden property="partOfSeqNo" value="" /> 
										<html:hidden property="clauseSeqNum" value="" />
									</div>
									<div class="col-sm-2 form-inline">
										<div class="input-group">
											<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
												<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(12)"><span class="glyphicon glyphicon-search"></span></button>
											</span>
											<html:text styleClass="form-control" property="partOf" readonly="true" value="" />				
											<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
												<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(12)"><span class="glyphicon glyphicon-remove"></span></button>
											</span>
										</div>
										<html:hidden property="partOfSeqNo" value="" /> 
										<html:hidden property="clauseSeqNum" value="" />
									</div>
								</logic:notPresent>
								<logic:present name="ChangeRequest1058Form" scope="request" property="changeComponentClause">
					 				<logic:iterate id="chngCompDetails" name="ChangeRequest1058Form" scope="request" property="changeComponentClause" type="com.EMD.LSDB.vo.common.ClauseVO">
										<div class="col-sm-2 form-inline">
										<div class="input-group">
											<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
												<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(9)"><span class="glyphicon glyphicon-search"></span></button>
											</span>
											<html:text styleClass="form-control" property="partOf" readonly="true" value="<%=chngCompDetails.getPartOfCode()[0]%>" />				
											<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
												<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(9)"><span class="glyphicon glyphicon-remove"></span></button>
											</span>
										</div>
										<input type="hidden" name="partOfSeqNo" value='<%=chngCompDetails.getPartOfSeqNo()[0]%>'/>				
										<input type="hidden" name="clauseSeqNum" value='<%=chngCompDetails.getClauseSeqNum()[0]%>'/>	
									</div>
									<div class="col-sm-2 form-inline">
										<div class="input-group">
											<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
												<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(10)"><span class="glyphicon glyphicon-search"></span></button>
											</span>
											<html:text styleClass="form-control" property="partOf" readonly="true" value="<%=chngCompDetails.getPartOfCode()[1]%>" />				
											<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
												<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(10)"><span class="glyphicon glyphicon-remove"></span></button>
											</span>
										</div>
										<input type="hidden" name="partOfSeqNo" value='<%=chngCompDetails.getPartOfSeqNo()[1]%>'/>				
										<input type="hidden" name="clauseSeqNum" value='<%=chngCompDetails.getClauseSeqNum()[1]%>'/>	
									</div>
									<div class="col-sm-2 form-inline">
										<div class="input-group">
											<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
												<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(11)"><span class="glyphicon glyphicon-search"></span></button>
											</span>
											<html:text styleClass="form-control" property="partOf" readonly="true" value="<%=chngCompDetails.getPartOfCode()[2]%>" />				
											<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
												<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(11)"><span class="glyphicon glyphicon-remove"></span></button>
											</span>
										</div>
										<input type="hidden" name="partOfSeqNo" value='<%=chngCompDetails.getPartOfSeqNo()[2]%>'/>				
										<input type="hidden" name="clauseSeqNum" value='<%=chngCompDetails.getClauseSeqNum()[2]%>'/>	
									</div>
									<div class="col-sm-2 form-inline">
										<div class="input-group">
											<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
												<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(12)"><span class="glyphicon glyphicon-search"></span></button>
											</span>
											<html:text styleClass="form-control" property="partOf" readonly="true" value="<%=chngCompDetails.getPartOfCode()[3]%>" />				
											<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
												<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(12)"><span class="glyphicon glyphicon-remove"></span></button>
											</span>
										</div>
										<input type="hidden" name="partOfSeqNo" value='<%=chngCompDetails.getPartOfSeqNo()[3]%>'/>				
										<input type="hidden" name="clauseSeqNum" value='<%=chngCompDetails.getClauseSeqNum()[3]%>'/>	
									</div>
								</logic:iterate>
							</logic:present>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">DWO Number</label>
                               <div class="col-sm-2">
                               	<logic:notPresent name="ChangeRequest1058Form" scope="request" property="changeComponentClause">
									<html:text styleClass="form-control" size="20" property="dwoNoChngComp" maxlength="20" />
								</logic:notPresent>
								<logic:present name="ChangeRequest1058Form" scope="request" property="changeComponentClause">
					 				<logic:iterate id="chngCompDetails" name="ChangeRequest1058Form" scope="request" property="changeComponentClause" type="com.EMD.LSDB.vo.common.ClauseVO">
					 					<logic:equal name="chngCompDetails" property="dwONumber" value="0">
					 						<input type="text" class="form-control" size="20" maxlength="20" name="dwoNoChngComp" value=''/>
										</logic:equal>
										<logic:notEqual name="chngCompDetails" property="dwONumber" value="0">
					 						<input type="text" class="form-control" size="20" maxlength="20" name="dwoNoChngComp" value='<bean:write name="chngCompDetails" property="dwONumber"/>'/>
										</logic:notEqual>
									</logic:iterate>
								</logic:present>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Part Number</label>
                               <div class="col-sm-2">
                               	<logic:notPresent name="ChangeRequest1058Form" scope="request" property="changeComponentClause">
									<html:text styleClass="form-control" size="20" property="partNoChngComp" maxlength="8" />
								</logic:notPresent>
								<logic:present name="ChangeRequest1058Form" scope="request" property="changeComponentClause">
					 				<logic:iterate id="chngCompDetails" name="ChangeRequest1058Form" scope="request" property="changeComponentClause" type="com.EMD.LSDB.vo.common.ClauseVO">
										<logic:equal name="chngCompDetails" property="partNumber" value="0">
					 						<input type="text" class="form-control" size="20" maxlength="8" name="partNoChngComp" value=''/>
										</logic:equal>
										<logic:notEqual name="chngCompDetails" property="partNumber" value="0">
											<input type="text" class="form-control" size="20" maxlength="8" name="partNoChngComp" value='<bean:write name="chngCompDetails" property="partNumber"/>'/>
										</logic:notEqual>
									</logic:iterate>
								</logic:present>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Price Book Number</label>
                               <div class="col-sm-2">
                               	<logic:notPresent name="ChangeRequest1058Form" scope="request" property="changeComponentClause">
									<html:text styleClass="form-control" size="20" styleId="priceBookNo" property="priceBookChngComp" maxlength="4" />
								</logic:notPresent>
								<logic:present name="ChangeRequest1058Form" scope="request" property="changeComponentClause">
					 				<logic:iterate id="chngCompDetails" name="ChangeRequest1058Form" scope="request" property="changeComponentClause" type="com.EMD.LSDB.vo.common.ClauseVO">
										<logic:equal name="chngCompDetails" property="priceBookNumber" value="0">
					 						<input type="text" class="form-control" size="20" maxlength="4" name="priceBookChngComp" value=''/>
										</logic:equal>
										<logic:notEqual name="chngCompDetails" property="priceBookNumber" value="0">
											<input type="text" class="form-control" size="20" maxlength="4" name="priceBookChngComp" value='<bean:write name="chngCompDetails" property="priceBookNumber"/>'/>
										</logic:notEqual>
									</logic:iterate>
								</logic:present>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Standard Equipment</label>
							<div class="col-sm-4">
								<logic:notPresent name="ChangeRequest1058Form" scope="request" property="standardEquipmentSeqNoChngComp">
									<html:select styleClass="form-control" property="standardEquipmentSeqNoChngComp" styleId="standardEquipmentSeqNoChngComp">
										<html:option value="-1">---Select---</html:option>
									</html:select>
								</logic:notPresent>
								<logic:present name="ChangeRequest1058Form" scope="request" property="standardEquipmentSeqNoChngComp">
					 				<html:select styleClass="form-control" property="standardEquipmentSeqNoChngComp" styleId="standardEquipmentSeqNoChngComp">
										<option value='-1' selected>--Select--</option>
										<logic:present name="ChangeRequest1058Form" property="standardEquipList" scope="request">
											<logic:iterate id="stdEquip" name="ChangeRequest1058Form" property="standardEquipList" scope="request" type="com.EMD.LSDB.vo.common.StandardEquipVO">
												<logic:equal name="ChangeRequest1058Form" property="standardEquipmentSeqNoChngComp" value="<%=String.valueOf(stdEquip.getStandardEquipmentSeqNo())%>">
													<option value='<bean:write name="stdEquip" property="standardEquipmentSeqNo"/>' selected><bean:write name="stdEquip" property="standardEquipmentDesc"/></option>
												</logic:equal>
												<logic:notEqual name="ChangeRequest1058Form" property="standardEquipmentSeqNoChngComp" value="<%=String.valueOf(stdEquip.getStandardEquipmentSeqNo())%>">
													<option value='<bean:write name="stdEquip" property="standardEquipmentSeqNo"/>'><bean:write name="stdEquip" property="standardEquipmentDesc"/></option>
												</logic:notEqual>
											</logic:iterate>
										</logic:present>
									</html:select>
								</logic:present>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Comments</label>
							<div class="col-sm-4">
								<logic:notPresent name="ChangeRequest1058Form" scope="request" property="changeComponentClause">
									<html:textarea property="commentsChngComp" rows="3" cols="60" styleClass="form-control" />
								</logic:notPresent>
								<logic:present name="ChangeRequest1058Form" scope="request" property="changeComponentClause">
					 				<logic:iterate id="chngCompDetails" name="ChangeRequest1058Form" scope="request" property="changeComponentClause" type="com.EMD.LSDB.vo.common.ClauseVO">
										<textarea name="commentsChngComp" rows="3" cols="60" class="form-control" ><bean:write name="chngCompDetails" property="engDataComment"/></textarea>
									</logic:iterate>
								</logic:present>
							</div>
						</div>
					</div>
				</div>
				
				<div class="spacer10"></div>
				<div class="form-group required-field">
					<label class="col-sm-4 control-label">Reason</label>
					<div class="col-sm-4">
	 					 <logic:notPresent name="ChangeRequest1058Form" scope="request" property="changeComponentClause">
	 					 	<html:textarea  property="reasonChngComp" styleId="reasonChngComp" rows="3" cols="60" styleClass="form-control" />
	 					 </logic:notPresent>
	  					<logic:present name="ChangeRequest1058Form" scope="request" property="changeComponentClause">
					 		<logic:iterate id="chngCompDetails" name="ChangeRequest1058Form" scope="request" property="changeComponentClause" type="com.EMD.LSDB.vo.common.ClauseVO">
					 			<textarea name="reasonChngComp" styleId="reasonChngComp" rows="3" cols="60" class="form-control"><bean:write name="chngCompDetails" property="reason"/></textarea>
					 		</logic:iterate>
						</logic:present>
					</div>
				</div>
	 
 					<logic:present name="ChangeRequest1058Form" scope="request" property="changeComponentClause">
					 <logic:iterate id="chngCompDetails" name="ChangeRequest1058Form" scope="request" property="changeComponentClause" type="com.EMD.LSDB.vo.common.ClauseVO">
					 	<input type="hidden" name="clauseToVerNo" id="clauseToVerNo" value='<bean:write name="chngCompDetails" property="versionNo"/>'>
					 	<input type="hidden" name="clauseToSeqNo" id="clauseToSeqNo" value='<bean:write name="chngCompDetails" property="clauseSeqNo"/>'>
					 </logic:iterate>
				</logic:present>
				<div class="spacer10"></div>
				<div class="form-group"	>
					<div class="col-sm-12 text-center" >
						<button class="btn btn-primary" type='button' id="componentClause" ONCLICK="javascript:fnCheckSpellnCont('fnSaveChangeComponent(0,\'divChangeComp\')','clauseDescriptionChngComp')">Save</button>
						<button class="btn btn-primary" type='button' id="compClose">Cancel Clause Change</button>
					</div>
				</div>
			</div>
		</div>
	</DIV>
</logic:notPresent>
