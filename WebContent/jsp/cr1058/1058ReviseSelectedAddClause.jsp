<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>

<logic:iterate id="revClaData" name="ChangeRequest1058Form" property="reviseClauseDetailsList" scope="request" type="com.EMD.LSDB.vo.common.ClauseVO">
<logic:equal name="revClaData" property="clauseChangeType" value="1">
	<input type="hidden" id="clauseChangeType" name="clauseChangeType" value="1"/>
	<html:hidden styleId="clauseChangeRequestSeqNo" name="ChangeRequest1058Form" property="clauseChangeRequestSeqNo" />
	<!-- Add Clause For Revise -->

	<DIV id="divAddClause">
		<div class="panel panel-info">
			<div class="panel-heading">
				<h4 class="panel-title"><strong>Add Clause</strong></h4>
			</div>
			<div class="panel-body">
				<div class="form-group required-field">
					<label class="col-sm-4 control-label">Section</label>
					<div class="col-sm-4">
						<SELECT name="sections" id="sltSection" CLASS="form-control" disabled="disabled">
							<OPTION value='<bean:write name="revClaData" property="sectionSeqNo"/>' selected>
							<bean:write name="revClaData" property="secCode"/>. <bean:write name="revClaData" property="sectionName"/></OPTION>
						</SELECT>
					</div>
				</div>
				<div class="form-group required-field">
					<label class="col-sm-4 control-label">SubSection</label>
					<div class="col-sm-4">
						<SELECT name="subSections" id="sltSubSection" CLASS="form-control" disabled="disabled">
							<OPTION value='<bean:write name="revClaData" property="subSectionSeqNo"/>' selected>
							<bean:write name="revClaData" property="subSecCode"/><bean:write name="revClaData" property="subSectionName"/></OPTION>
						</SELECT>	
					</div>
				</div>
				<div class="form-group required-field">
					<label class="col-sm-4 control-label">Select Lead Component Group</label>
					<div class="col-sm-4">
						<SELECT name="componentGroup" id="sltLeadCompGroup" CLASS="form-control" disabled="disabled">
							<OPTION value='<bean:write name="revClaData" property="leadCompGrpSeqNo"/>' selected><bean:write name="revClaData" property="leadComponentGrpName"/></OPTION>
						</SELECT>
						<input type="hidden" id="componentGrpSeqNoinAdd" name="componentGrpSeqNoinAdd" value='<bean:write name="revClaData" property="leadCompGrpSeqNo"/>'/>
					</div>
				</div>
				<div class="form-group required-field">
					<label class="col-sm-4 control-label">Select Lead Component</label>
					<div class="col-sm-4">
						 <SELECT name="components" id="sltLeadComp" CLASS="form-control" disabled="disabled">
							<OPTION value='<bean:write name="revClaData" property="leadComponentSeqNo"/>' selected><bean:write name="revClaData" property="leadComponentName"/></OPTION>
						</SELECT>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Parent Clause</label>
					<div class="col-sm-4" >
						<input type="hidden" id="hdnParentClauseSeqNo" name="hdnParentClauseSeqNo" value='<bean:write name="revClaData" property="parentClauseSeqNo"/>'>
						<DIV id="parentclause" style="solid;height:60px;overflow:auto;">
							<%-- CR-128 - Updated for Pdf issue --%>
							<%if(String.valueOf(revClaData.getParentClauseDesc()).startsWith("<p>"))
							{%>
								<%=(String.valueOf(revClaData.getParentClauseDesc()))%>&nbsp;
							<%}else{ %>	
								<%=(String.valueOf(revClaData.getParentClauseDesc())).replaceAll("null","").replaceAll("\n","<br>")%>&nbsp;
							<%}%>
							<%-- CR-128 - Updated for Pdf issue Ends Here--%>
						</DIV>
					</div>
				</div>
				<div class="form-group required-field">
					<label class="col-sm-4 control-label">Clause Description</label>
					<div class="col-sm-4">
						<TEXTAREA name="addClauseDescription" id="addClauseDescription" rows="9" cols="60" class="form-control tinymceClaDesc" value="">
							<%=(String.valueOf(revClaData.getClauseDescForTextArea())).replaceAll("null","").replaceAll("\n","<br>")%></TEXTAREA>
						<textarea name="hdnclauseDescForTextArea" id="hdnclauseDescForTextArea"
							style="display:none;"><bean:write name="revClaData" property="clauseDescForTextArea"/></textarea>
					</div>
				</div>
                <logic:empty name="revClaData" property="tableArrayData1">
					<div class="form-group">
						<label class="col-sm-4 control-label">Table Data
							<DIV   name="showmainlink"><A
								CLASS='linkstyleItem' HREF="javascript:addTable('divAddClause',0)">Add Table</A>&nbsp;
							</DIV>
							<DIV  name="showsublink"  style="display:none;"><A
								CLASS=linkstyle1 HREF="javascript:addRow(this,'divAddClause')">Add Row</A>&nbsp;|&nbsp;<A
								CLASS=linkstyle1 HREF="javascript:removeRow('divAddClause')">Delete Row</A><br>
								<A CLASS='linkstyle1' HREF="javascript:deleteTable('divAddClause')">Delete Table</A>&nbsp;
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
							<DIV   name="showmainlink" style="visibility:hidden"><A
								CLASS='linkstyleItem' HREF="javascript:addTable('divAddClause',1)">Add Table</A>&nbsp;
							</DIV>
							<DIV  name="showsublink"  style="visibility:visible"><A
								CLASS=linkstyle1 HREF="javascript:addRow(this,'divAddClause')">Add Row</A>&nbsp;|&nbsp;<A
								CLASS=linkstyle1 HREF="javascript:removeRow('divAddClause')">Delete Row</A><br>
								<A CLASS='linkstyle1' HREF="javascript:deleteTable('divAddClause')">Delete Table</A>&nbsp;
							</DIV>
						</label>	
						<div class="col-sm-8"> 
							<DIV name="showgrid">
								<div id="tblGridAdd" class='form-horizontal'>
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
															name="clauseTableDataArray<%=innerCounter.intValue()+1%>Add"
															class="form-control textboxbluebold" size="13" maxlength="100"
															value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>"
															maxlength="100" /></div>
													</logic:lessThan>

													<logic:greaterThan name="innerSize" value="0">
														<!-- This check is for display the rest of first row four text boxes -->
															<div class='col-sm-2'>&nbsp;&nbsp;<input type=text
															name="clauseTableDataArray<%=innerCounter.intValue()+1%>Add"
															class="form-control textboxbluebold" size="13" maxlength="100"
															value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>"
															maxlength="100" /></div>
													</logic:greaterThan>
												</logic:equal><logic:greaterThan name="size" value="0">
													<!--This outer logic:greaterThan check is for display the second row datas -->
													<logic:equal name="innerSize" value="0">
														<!--This Inner logic:equal check is for display the Second row first text box with radio buttons -->
															<div class='col-sm-1 text-right'><label class='radio-inline'><html:radio property="deleterow" styleClass="radcheck"
																				value="" /></label></div><div class='col-sm-2'>&nbsp;&nbsp;
															 <input type=text
															name="clauseTableDataArray<%=innerCounter.intValue()+1%>Add"
															class="form-control" size="15" maxlength="100"
															value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>"
															maxlength="100" /></div>
													</logic:equal>
													<!--This Inner logic:notEqual check is for display the Second row other text box without radio buttons -->
													<logic:notEqual name="innerSize" value="0">
														 <div class='col-sm-2'>&nbsp;&nbsp;
														 <input type=text
															name="clauseTableDataArray<%=innerCounter.intValue()+1%>Add"
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
						</div>
					</div>
				</div>
			 </logic:notEmpty>
				<div class="form-group">
					<label class="col-sm-4 control-label">Component Tied To Clause</label>
					<div class="col-sm-4 form-inline">
						<SELECT name="component"  CLASS="form-control" multiple="true">
							<logic:present name="revClaData" property="componentVO">
								<logic:iterate name="revClaData" property="componentVO" type="com.EMD.LSDB.vo.common.ComponentVO" id="compTied">
							 		<option value='<bean:write name="compTied" property="componentSeqNo"/>'><bean:write name="compTied" property="componentName"/></option>
							 	</logic:iterate>
							</logic:present>
						</SELECT>
						<a HREF="javascript:AddComponent()"><span class="glyphicon glyphicon-search" aria-hidden="true" alt="search"></span></A>
						<a href="javascript:deleteComponent()"><span class="glyphicon glyphicon-remove text-red" aria-hidden="true" alt="remove"></a>
					</div>
				</div>	
				<div class="panel panel-default col-sm-offset-1" style="width:90%;align:center;">
		   			<div class="panel-heading"><h3 class="panel-title text-center">Engineering Data <font color="red" valign="top">*</font></h3></div>
	   				<div class="panel-body">
	   					<div class="form-group">
							<label class="col-sm-3 control-label">Reference EDL Number(s)</label>
							<div>
								<div class="col-sm-2"><input type="text" class="form-control" size="20" name="refEdlNoAdd" maxlength="20" value='<%=revClaData.getRefEDLNo()[0]%>'/></div>
								<div class="col-sm-2"><input type="text" class="form-control" size="20" name="refEdlNoAdd" maxlength="20" value='<%=revClaData.getRefEDLNo()[1]%>'/></div>
								<div class="col-sm-2"><input type="text" class="form-control" size="20" name="refEdlNoAdd" maxlength="20" value='<%=revClaData.getRefEDLNo()[2]%>'/></div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">New EDL Number(s)</label>
							<div>		
								<div class="col-sm-2"><input type="text" class="form-control" size="20" name="newEdlNoAdd" maxlength="20" value='<%=revClaData.getEdlNo()[0]%>'/></div>
								<div class="col-sm-2"><input type="text" class="form-control" size="20" name="newEdlNoAdd" maxlength="20" value='<%=revClaData.getEdlNo()[1]%>'/></div>
								<div class="col-sm-2"><input type="text" class="form-control" size="20" name="newEdlNoAdd" maxlength="20" value='<%=revClaData.getEdlNo()[2]%>'/></div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Part Of</label>
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
							<label class="col-sm-3 control-label">DWO Number </label>
							<div class="col-sm-2">
								<logic:equal name="revClaData" property="dwONumber" value="0">
									<input type="text" class="form-control" size="20" maxlength="20" name="dwoNoAdd" value=''/>
								</logic:equal>
								<logic:notEqual name="revClaData" property="dwONumber" value="0">
									<input type="text" class="form-control" size="20" maxlength="20" name="dwoNoAdd" value='<bean:write name="revClaData" property="dwONumber"/>'/>
								</logic:notEqual>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Part Number </label>
							<div class="col-sm-2">
								<logic:equal name="revClaData" property="partNumber" value="0">
									<input type="text" class="form-control" size="20" maxlength="8" name="partNoAdd" value=''/>
								</logic:equal>
								<logic:notEqual name="revClaData" property="partNumber" value="0">
									<input type="text" class="form-control" size="20" maxlength="8" name="partNoAdd" value='<bean:write name="revClaData" property="partNumber"/>'/>
								</logic:notEqual>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Price Book Number</label>
							<div class="col-sm-2">
								<logic:equal name="revClaData" property="priceBookNumber" value="0">	
									<input type="text" class="form-control" size="20" maxlength="4" name="priceBookNoAdd" value=''/>
								</logic:equal>
								<logic:notEqual name="revClaData" property="priceBookNumber" value="0">
									<input type="text" class="form-control" size="20" maxlength="4" name="priceBookNoAdd" value='<bean:write name="revClaData" property="priceBookNumber"/>'/>
								</logic:notEqual>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Standard Equipment</label>
							<div class="col-sm-4">
								<html:select styleClass="form-control" property="standardEquipmentSeqNoAdd" styleId="standardEquipmentSeqNoAdd">
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
								<textarea name="commentsAdd" rows="3" cols="60" class="form-control"><bean:write name="revClaData" property="engDataComment"/></textarea>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group required-field">
					<label class="col-sm-4 control-label">Reason</label>
					<div class="col-sm-4">
						 <textarea name="reasonAdd" styleId="reasonAdd" rows="3" cols="60" class="form-control"><bean:write name="revClaData" property="reason"/></textarea>
					</div>
				</div>
				
				<div class="spacer10"></div>
				
				<div class="form-group"	>
					<div class="col-sm-12 text-center" >
						<button class="btn btn-primary" type='button' id="addClause" ONCLICK="javascript:fnCheckSpellnCont('fnSaveAddClause(1,\'divAddClause\')','addClauseDescription')">Save</button>
						<button class="btn btn-primary" type='button' id="addClose">Cancel Clause Change</button>
					</div>
				</div>	
			</div>
		</div>	
	</DIV>		
</logic:equal>
</logic:iterate>