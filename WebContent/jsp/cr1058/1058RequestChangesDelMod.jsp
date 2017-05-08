<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>

<A name="clauseChngType" id="clauseChngType"></A>
	<div class="panel panel-info">
		<div class="panel-heading">
			<h4 class="panel-title"><strong>Request Clause Change</strong></h4>
		</div>
		<div class="panel-body">
			<logic:notPresent name="ChangeRequest1058Form" property="reviseClauseDetailsList" scope="request">
				<html:hidden styleId="clauseChangeType" property="clauseChangeType" />
				<html:hidden styleId="hdnLeadComponentSeqNoinAdd" property="hdnLeadComponentSeqNoinAdd" />
				<html:hidden styleId="hdnLeadComponentNameinAdd" property="hdnLeadComponentNameinAdd" />
				<html:hidden styleId="componentGrpSeqNoinAdd" property="componentGrpSeqNoinAdd" />
				<html:hidden styleId="sectionSeqNo" property="sectionSeqNo" />
				<html:hidden styleId="subSectionSeqNo" property="subSectionSeqNo" />
				<!-- for component tied to clause -->
				<html:hidden styleId="hdncomponentGroupSeqNo" property="hdncomponentGroupSeqNo" />
				<html:hidden styleId="hdnComponentName" property="hdnComponentName" />
				<html:hidden styleId="componentSeqNo" property="componentSeqNo" />
				
				<div class="form-group">
					<label class="control-label col-sm-2">Change Type</label>
					<div class="col-sm-4">
						<logic:present name="ChangeRequest1058Form" property="clauseChangeTypes" scope="request">
							<logic:iterate id="ClauseChangeType" name="ChangeRequest1058Form" property="clauseChangeTypes"
								type="com.EMD.LSDB.vo.common.RequestClauseChangeTypes1058VO" scope="request">
									<div class="radio">
									  <label>
									    <INPUT TYPE="radio" name="clauseChangeType" ID ='ClauseChange<bean:write name="ClauseChangeType" property="changeTypeSeqNo"/>' value='<bean:write name="ClauseChangeType" property="changeTypeSeqNo"/>' CLASS="ClauseChangeTypes"><bean:write name="ClauseChangeType" property="changeTypeName"/></INPUT>
									  </label>
									</div>
							</logic:iterate>
						</logic:present>
					</div>
				</div>
		
	<DIV id="divDelClause">
		<div class="panel panel-info">
			<div class="panel-heading">
				<h4 class="panel-title"><strong>Delete Clause</strong></h4>
			</div>
			<div class="panel-body">
				<div class="form-group required-field">
					<label class="control-label col-sm-4">Select Clause
						<A HREF="javascript:LoadClauses(9998)" id="selectClauseToDeleteIcon">
						<span class="glyphicon glyphicon-search" aria-hidden="true" id="delSelect" alt="Search"></span></A>
					</label>
					<div class="col-sm-2">
						<html:text property="clauseToDelete" styleId="clauseToDelete" disabled="true" styleClass="form-control"/>
						<html:hidden styleId="hdnClauseToDelete" property="hdnClauseToDelete" />
						<html:hidden styleId="hdnClauseToDeleteSeqNo" property="hdnClauseToDeleteSeqNo" />
					</div>
				</div>
				<div class="form-group required-field">
					<label class="control-label col-sm-4">Reason</label>
					<div class="col-sm-3">
						<html:textarea  styleId="reasonDel" property="reasonDel" rows="3" cols="60" styleClass="form-control"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-12 text-center">
						<button class="btn btn-primary" type='button' id="deleteClause" ONCLICK="javascript:fnSaveDeleteClause(0,this)">Save</button>
						<button class="btn btn-primary" type='button' id="delClose">Cancel Clause Change</button>
					</div>
				</div>
			</div>
		</div>
	</DIV>

	<DIV id="divModClause">
		<div class="panel panel-info">
			<div class="panel-heading">
				<h4 class="panel-title"><strong>Modify Clause</strong></h4>
			</div>
			<div class="panel-body">
				<div class="form-group required-field">
					<label class="col-sm-4 control-label">Select Clause
						<A HREF="javascript:LoadClauses(9999)" id="selectClauseToModifyIcon">
						<span class="glyphicon glyphicon-search" id="modSelect" aria-hidden="true" alt="search"></span></A>
					</label>
					<div class="col-sm-2">
						<html:text property="clauseToModify" styleId="clauseToModify" disabled="true" styleClass="form-control"/>
						<html:hidden styleId="hdnClauseToModify" property="hdnClauseToModify" />
						<html:hidden styleId="hdnClauseToModifySeqNo" property="hdnClauseToModifySeqNo" />
					</div>
				</div>
				<div class="form-group required-field">
					<label class="col-sm-4 control-label">Clause Versions
						<A HREF="javascript:fnLoadClauseVersion()">
						<span class="glyphicon glyphicon-search" id="modSelect" aria-hidden="true" alt="search"></span></A>
					</label>
					<div class="col-sm-2">
						<logic:notPresent name="ChangeRequest1058Form" scope="request" property="versionClauseDetails">
							<html:text property="versionNoMod" styleId="versionNoMod" disabled="true" styleClass="form-control" value=""/>
							<html:hidden styleId="hdnVersionNoMod" property="hdnVersionNoMod" />
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
	 						<html:textarea property="clauseDescriptionMod" rows="3" cols="60" styleId="clauseDescriptionMod" styleClass="form-control tinymceClaDesc"/>
						</logic:notPresent>
						<logic:present name="ChangeRequest1058Form" scope="request" property="versionClauseDetails">
	 						<logic:iterate id="verClaDetails" name="ChangeRequest1058Form" scope="request" property="versionClauseDetails" type="com.EMD.LSDB.vo.common.ClauseVO">
	 							<textarea name="clauseDescriptionMod" rows="3" cols="60" id="clauseDescriptionMod" class="form-control CLAUSEDESCTEXTAREA"><%=(String.valueOf(verClaDetails.getClauseDesc())).replaceAll("  ","&nbsp;&nbsp;").replaceAll("\n","<br>")%></textarea>
	 							<%--Added for CR-129 for loading clauses in TinyMCE Issue  --%>
	 							<textarea name="hdnclauseDescForTextArea" id="hdnclauseDescForTextArea" style="display:none;">
	 								<bean:write name="verClaDetails" property="clauseDesc"/>
	 							</textarea>
	 							<%--Added for CR-129 for loading clauses in TinyMCE Issue  --%>	
							 </logic:iterate>
						 </logic:present>
					</div>
				</div>
				<logic:notPresent name="ChangeRequest1058Form" scope="request" property="versionClauseDetails">
					<div class="form-group">
						<label class="col-sm-4 control-label">Table Data
							<div name="showmainlink">
								<A  HREF="javascript:addTable('divModClause',0)">Add Table</A>
							</div>
							<div name="showsublink" style="display:none;">
								<A  HREF="javascript:addRow(this,'divModClause')">Add Row</A> |
								<A  HREF="javascript:removeRow('divModClause')">Delete Row</A><br>
								<A  HREF="javascript:deleteTable('divModClause')">Delete Table</A>
							</div>
						</label>	
						<div class="col-sm-8"> 
							<DIV name="showgrid"></DIV>
						</div>			
					</div>
				</logic:notPresent>
				<logic:present name="ChangeRequest1058Form" property="versionClauseDetails" scope="request">
		 			<logic:iterate id="verClaDetails" name="ChangeRequest1058Form" scope="request" property="versionClauseDetails" type="com.EMD.LSDB.vo.common.ClauseVO">
						<logic:empty name="verClaDetails" property="tableArrayData1">
							<div class="form-group">
								<label class="col-sm-4 control-label">Table Data
									<div name="showmainlink">
										<A  HREF="javascript:addTable('divModClause',0)">Add Table</A>
									</div>
									<div name="showsublink" style="display:none;">
										<A  HREF="javascript:addRow(this,'divModClause')">Add Row</A> |
										<A  HREF="javascript:removeRow('divModClause')">Delete Row</A><br>
										<A  HREF="javascript:deleteTable('divModClause')">Delete Table</A>
									</div>
								</label>	
								<div class="col-sm-8"> 
									<DIV name="showgrid"></DIV>
								</div>			
							</div>
						</logic:empty>
						<logic:notEmpty name="verClaDetails" property="tableArrayData1">
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
																		class="form-control" size="13" maxlength="100"
																		value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>"
																		maxlength="100" /></div>
																</logic:lessThan>
		
																<logic:greaterThan name="innerSize" value="0">
																	<!-- This check is for display the rest of first row four text boxes -->
																	<div class='col-sm-2'>&nbsp;&nbsp;<input type=text
																		name="clauseTableDataArray<%=innerCounter.intValue()+1%>Mod"
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
								<logic:notPresent name="ChangeRequest1058Form" scope="request" property="versionClauseDetails">
									<div class="col-sm-2"><html:text styleClass="form-control" size="20" property="refEdlNoMod" maxlength="20" value="" /></div>
									<div class="col-sm-2"><html:text styleClass="form-control" size="20" property="refEdlNoMod" maxlength="20" value="" /></div>
									<div class="col-sm-2"><html:text styleClass="form-control" size="20" property="refEdlNoMod" maxlength="20" value="" /></div>
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
									<div class="col-sm-2"><html:text styleClass="form-control" size="20" property="newEdlNoMod" maxlength="20" value="" /></div>
									<div class="col-sm-2"><html:text styleClass="form-control" size="20" property="newEdlNoMod" maxlength="20" value="" /></div>
									<div class="col-sm-2"><html:text styleClass="form-control" size="20" property="newEdlNoMod" maxlength="20" value="" /></div>
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
										<html:text styleClass="form-control" styleId="partOf1" property="partOf" readonly="true" value="" />				
										<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
											<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(1)"><span class="glyphicon glyphicon-remove"></span></button>
										</span>
									</div>
									<html:hidden property="partOfSeqNo" value="" /> 
									<html:hidden property="clauseSeqNum" value="" />
								</div>
								<div class="col-sm-2 form-inline">
									<div class="input-group">
										<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
											<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(2)"><span class="glyphicon glyphicon-search"></span></button>
										</span>
										<html:text styleClass="form-control" styleId="partOf2" property="partOf" readonly="true" value="" />				
										<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
											<button class="clearbtn btn btn-default" type="button" id="clearpartOf2" onclick="javascript:deletePartOf(2)"><span class="glyphicon glyphicon-remove"></span></button>
										</span>
									</div>
									<html:hidden property="partOfSeqNo" value="" /> 
									<html:hidden property="clauseSeqNum" value="" />
								</div>
								<div class="col-sm-2 form-inline">
									<div class="input-group">
										<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
											<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(3)"><span class="glyphicon glyphicon-search"></span></button>
										</span>
										<html:text styleClass="form-control" styleId="partOf3" property="partOf" readonly="true" value="" />				
										<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
											<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(3)"><span class="glyphicon glyphicon-remove"></span></button>
										</span>
									</div>
									<html:hidden property="partOfSeqNo" value="" /> 
									<html:hidden property="clauseSeqNum" value="" />
								</div>
								<div class="col-sm-2 form-inline">
									<div class="input-group">
										<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
											<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(4)"><span class="glyphicon glyphicon-search"></span></button>
										</span>
										<html:text styleClass="form-control" styleId="partOf4" property="partOf" readonly="true" value="" />				
										<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
											<button class="clearbtn btn btn-default" type="button" id="clearpartOf4" onclick="javascript:deletePartOf(4)"><span class="glyphicon glyphicon-remove"></span></button>
										</span>
									</div>
									<html:hidden property="partOfSeqNo" value="" /> 
									<html:hidden property="clauseSeqNum" value="" />
								</div>
							</logic:notPresent>	
							<logic:present name="ChangeRequest1058Form" property="versionClauseDetails" scope="request">
								<logic:iterate id="verClaDetails" name="ChangeRequest1058Form" scope="request" property="versionClauseDetails" type="com.EMD.LSDB.vo.common.ClauseVO">
									<div class="col-sm-2 form-inline">
										<div class="input-group">
											<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
												<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(1)"><span class="glyphicon glyphicon-search"></span></button>
											</span>
											<html:text styleClass="form-control" styleId="partOf1" property="partOf" readonly="true" value='<%=verClaDetails.getPartOfCode()[0]%>' />				
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
											<html:text styleClass="form-control" styleId="partOf2" property="partOf" readonly="true" value='<%=verClaDetails.getPartOfCode()[1]%>' />				
											<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
												<button class="clearbtn btn btn-default" type="button" id="clearpartOf2" onclick="javascript:deletePartOf(2)"><span class="glyphicon glyphicon-remove"></span></button>
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
											<html:text styleClass="form-control" styleId="partOf3" property="partOf" readonly="true" value='<%=verClaDetails.getPartOfCode()[2]%>' />				
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
											<html:text styleClass="form-control" styleId="partOf4" property="partOf" readonly="true" value='<%=verClaDetails.getPartOfCode()[3]%>' />				
											<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
												<button class="clearbtn btn btn-default" type="button" id="clearpartOf4" onclick="javascript:deletePartOf(4)"><span class="glyphicon glyphicon-remove"></span></button>
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
									<html:text styleClass="form-control" size="20" property="dwoNoMod" maxlength="20" />
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
									<html:text styleClass="form-control" size="20" property="partNoMod" maxlength="20" />
								</logic:notPresent>
								<logic:present name="ChangeRequest1058Form" property="versionClauseDetails" scope="request">
									<logic:iterate id="verClaDetails" name="ChangeRequest1058Form" scope="request" property="versionClauseDetails" type="com.EMD.LSDB.vo.common.ClauseVO">
										<logic:equal name="verClaDetails" property="partNumber" value="0">
											<input type="text" class="form-control" size="20" maxlength="20" name="partNoMod" value=''/>
										</logic:equal>
										<logic:notEqual name="verClaDetails" property="partNumber" value="0">
					 						<input type="text" class="form-control" size="20" maxlength="20" name="partNoMod" value='<bean:write name="verClaDetails" property="partNumber"/>'/>
					 					</logic:notEqual>
									</logic:iterate>
								</logic:present>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Price Book Number</label>
							<div class="col-sm-2">
								<logic:notPresent name="ChangeRequest1058Form" scope="request" property="versionClauseDetails">
									<html:text styleClass="form-control" size="20" styleId="priceBookNo" property="priceBookNoMod" maxlength="4" />
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
								<logic:notPresent name="ChangeRequest1058Form" scope="request" property="standardEquipmentSeqNoMod">
									<html:select styleClass="form-control" property="standardEquipmentSeqNoMod" styleId="standardEquipmentSeqNoMod">
										<html:option value="-1">---Select---</html:option>
									</html:select>
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
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Comments</label>
							<div class="col-sm-4">
								<logic:notPresent name="ChangeRequest1058Form" scope="request" property="versionClauseDetails">
									<html:textarea property="commentsMod" rows="3" cols="60" styleClass="form-control" />
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
		   		<div class="spacer10"></div>
				<div class="form-group required-field">
					<label class="col-sm-4 control-label">Reason</label>
					<div class="col-sm-4">
						<logic:notPresent name="ChangeRequest1058Form" scope="request" property="versionClauseDetails">
			 				<html:textarea  property="reasonMod" styleId="reasonMod" rows="3" cols="60" styleClass="form-control" />
						</logic:notPresent>
						<logic:present name="ChangeRequest1058Form" property="versionClauseDetails" scope="request">
							<logic:iterate id="verClaDetails" name="ChangeRequest1058Form" scope="request" property="versionClauseDetails" type="com.EMD.LSDB.vo.common.ClauseVO">
								<textarea name="reasonMod" styleId="reasonMod" rows="3" cols="60" class="form-control"><bean:write name="verClaDetails" property="reason"/></textarea>
							</logic:iterate>
						</logic:present>
					</div>
				</div>
				<div class="spacer10"></div>
				<div class="form-group"	>
					<div class="col-sm-12 text-center" >
						<button class="btn btn-primary" type='button' id="modifyClause" ONCLICK="javascript:fnCheckSpellnCont('fnSaveModifyClause(0,\'divModClause\')','clauseDescriptionMod')">Save</button>
						<button class="btn btn-primary" type='button' id="modClose">Cancel Clause Change</button>
					</div>
				</div>
		
		</div>
	</div>
 </DIV>
	</logic:notPresent>
 	
 	
 	