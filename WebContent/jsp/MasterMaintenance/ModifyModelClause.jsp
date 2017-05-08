<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<!-- Added for CR_88  -->

<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>

	<logic:present name="ModelAddClauseForm" property="clauseList"
		scope="request">
		<bean:size name="ModelAddClauseForm" id="clsize"
			property="clauseList" />
	</logic:present>

	<logic:present name="ModelAddClauseForm"
		property="stdEquipmentList" scope="request">
		<bean:size name="ModelAddClauseForm" id="stdsize"
			property="stdEquipmentList" />
	</logic:present>

	<logic:present name="ModelAddClauseForm"
		property="clauseVersions" scope="request">
		<bean:size name="ModelAddClauseForm" id="allversize"
			property="clauseVersions" />
	</logic:present>
	
	<html:hidden property="hdnClauseVersionNo" />

	<logic:greaterThan name="allversize" value="0">
		<logic:present name="ModelAddClauseForm"
			property="clauseVersions">	
					<hr>
					<div class="row">
						<div class="col-sm-11 text-center"><h5 class="text-center"><strong>Select Clause Revision</strong></h5></div>
					</div>
					<div class="spacer10"></div>
					<div class="row" align="center">
					<TABLE class="table table-bordered" style="height:auto;width:1100px;overflow:auto;padding:0px;margin:0px;">
						<thead>
							<TR>
								<TH width="5%" class="text-center">Select</TH>
								<TH width="5%" class="text-center text-nowrap">Version No</TH>
								<TH width="25%" class="text-center">Clause Description</TH>
								<TH width="20%" class="text-center">Engineering Data</TH>
								<TH width="10%" class="text-center">Customer</TH>
								<TH width="10%" class="text-center text-nowrap">Created By</TH>
								<TH width="20%" class="text-center text-nowrap">Created Date</TH>
								<TH width="5%" class="text-center">Default</TH>
							</TR>
						</thead>
						<tbody class="text-center">	
								<logic:iterate id="clauseRev" name="ModelAddClauseForm"
											property="clauseVersions" type="com.EMD.LSDB.vo.common.ClauseVO">
						
											<TR>
												<%--Edited for CR_121 Starts here--%>
												<TD class="v-midalign" align="center"><html:radio
													value="<%=String.valueOf(clauseRev.getVersionNo())%>"
													styleClass="radcheck" property="versionNo"  styleId="versionNoRadio" />
													<%-- Modified for LSDB_CR-134 --%>
													<logic:equal name="clauseRev" property="usedVersionFlag" value="N">
														<h4>							
															<span class="label label-primary">Clause Version Unused</span>
														</h4>	                  
													</logic:equal>						
													<logic:notEqual name="clauseRev" property="userMarkFlag" value="N">													
														<logic:notEmpty name="clauseRev" property="markClaReason">
	        												<A nohref="#" data-toggle="tooltip" title="<bean:write name="clauseRev" property="markClaReason"  />">
	        											</logic:notEmpty>						
														<h5>
															<span class="label label-warning" >MARKED</span>
											          	</h5>
													</logic:notEqual>
													<%-- ends Modified for LSDB_CR-134 --%>
												</TD>
													<%--Edited for CR_121 Ends--%>
												<%-- Modified for LSDB_CR-134 --%>
												<TD ALIGN="centre" colspan="1" class="v-midalign" valign="top">
													<div><bean:write name="clauseRev"	property="versionNo" />	</div>
													<logic:equal name="clauseRev" property="userMarkFlag" value="N">												
															<A  HREF="javascript:fnShowMarkClause('<%=clauseRev.getClauseSeqNo()%>','<%=clauseRev.getVersionNo()%>','Y');" class="text-center text-nowrap" ><small>Mark Clause</small></A>
													</logic:equal>		
													<logic:notEqual name="clauseRev" property="userMarkFlag" value="N"> 
														<A HREF="javascript:fnSetUserMarker('<%=clauseRev.getClauseSeqNo()%>','<%=clauseRev.getVersionNo()%>','N')" class="text-center text-nowrap"><small>Unmark Clause</small></A>
													</logic:notEqual>
												</TD>
						
												<TD class="v-midalign" width="50%" valign="top" align="left" height="50%"><bean:define
													name="clauseRev" property="clauseDesc" id="clauseDesc" />
													<!-- CR-128 - Updated for Pdf issue -->
													<%String strClauseDesc =  String.valueOf(clauseDesc);
													if(strClauseDesc.startsWith("<p>")){%>
														<%=(String.valueOf(clauseDesc))%>
													<%}else{ %>	
														<%-- double space replacement with &nbsp is added to display the spaces as entered. This change is applicable for only modify spec --%>
														<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>							
													<%}%>
													<!-- CR-128 - Updated for Pdf issue ends here-->
											
						
													<logic:notEmpty name="clauseRev" property="tableArrayData1">
													<table class="table table-bordered">
														<logic:iterate id="outter" name="clauseRev"
															property="tableArrayData1" indexId="counter">
						
															<bean:define id="row" name="counter" />
															<!-- Added  For CR_93 -->
																	<bean:define name="clauseRev" property="tableDataColSize" id="tableDataColSize" />
															<tr>
																<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
																	<logic:equal name="row" value="0">
																		<td valign="top" width="5%"><b><font
																			color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></b></font>
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
												<TD class="v-midalign" VALIGN="top">
											<!-- 	<logic:notEmpty	name="clauseRev" property="refEDLNO">
													<logic:iterate id="refedl" name="clauseRev" property="refEDLNO">
														<bean:message key="screen.refEdl" />&nbsp;
												           <bean:write name="refedl" />
														<BR>
													</logic:iterate>
						
												</logic:notEmpty>-->
												 <logic:notEmpty name="clauseRev"
													property="edlNO">
						
													<logic:iterate id="edl" name="clauseRev" property="edlNO">
														<bean:message key="screen.edl" />&nbsp;
							           						<bean:write name="edl" />
														<BR>
													</logic:iterate>
						
												</logic:notEmpty> 
												<!-- CR 87 -->
													<logic:notEmpty	name="clauseRev" property="refEDLNO">
													<logic:iterate id="refedl" name="clauseRev" property="refEDLNO">
														<bean:message key="screen.refEdl.start" />&nbsp;
												           <bean:write name="refedl" />
														<bean:message key="screen.refEdl.end" />&nbsp;						           
														<BR>
													</logic:iterate>
						
												</logic:notEmpty>
												<logic:notEmpty name="clauseRev"
													property="partOF">
						<!-- CR 87 -->
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
												</logic:present>
											</TD>
						
												<TD class="borderbottomleft" valign="top">
													<logic:empty name="clauseRev" property="customerName">
											            &nbsp;
										            </logic:empty>
										            <logic:notEmpty name="clauseRev" property="customerName">
														<bean:write name="clauseRev" property="customerName" />
													</logic:notEmpty>
												</TD>
												<TD class="v-midalign" valign="top">
													<logic:empty name="clauseRev" property="createdBy">
											            &nbsp;
										            </logic:empty> 
										            <logic:notEmpty name="clauseRev" property="createdBy">
														<bean:write name="clauseRev" property="createdBy" />
													</logic:notEmpty>
												</TD>
												<TD class="v-midalign" valign="top">
													<logic:empty name="clauseRev" property="createdDate">
										            &nbsp;
										            </logic:empty> 
										            <logic:notEmpty name="clauseRev" property="createdDate">
														<bean:write name="clauseRev" property="createdDate" />
													</logic:notEmpty>
												</TD>
												<TD class="v-midalign">
													<bean:write name="clauseRev" property="defaultFlag" />
													 <html:hidden name="clauseRev" property="defaultFlag" /> 
													 <!-- <logic:equal name="clauseRev" property="defaultFlag" value="Y">
											                <input type="radio" name="defaultFlag" value="Y" checked class="radcheck"/>
												            </logic:equal>
												            <logic:equal name="clauseRev" property="defaultFlag" value="N">
											                 <input type="radio" name="defaultFlag" value="Y"  class="radcheck"/>
	  										              </logic:equal> -->
  										        </TD>
						
											</TR>
									</logic:iterate>
						</tbody>
					</TABLE>
					</div>	
					<div class="spacer20"></div>
					<div class="row">
						<div class="form-group">
							<div class="Col-sm-12 text-center">
					         	<button class="btn btn-primary" type='button' ONCLICK="javascript:fnApplyDefault()">Apply Default</button>
					            <button class="btn btn-primary" type='button' ONCLICK="javascript:fnDeleteVersion()">Delete Version</button>
					            <button class="btn btn-primary" type='button' ONCLICK="javascript:fnSearch('Y')">View selected Version in Orders Report</button>
					        </div>
					     </div>
					</div>	
					
					<div class="row">
						<div class="form-group">
							<div class="Col-sm-12 text-center">
					         	<button class="btn btn-primary" type='button' ONCLICK="javascript:fnDeleteClause()">Delete entire Clause</button>
					        </div>
					     </div>
					</div>	
		</logic:present>		
	</logic:greaterThan>	
	
	<logic:greaterThan name="clsize" value="0">
	<div class="form-horizontal">
		<logic:iterate id="check" name="ModelAddClauseForm"
			property="clauseList" type="com.EMD.LSDB.vo.common.ClauseVO"
			scope="request">
				<hr>
				<div class="row">
					<div class="col-sm-11 text-center"><h5 class="text-center"><strong>Add Clause Revision</strong></h5></div>
				</div>
				<div class="spacer10"></div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">New Clause Description<font color="red" valign="top">*</font></label>
					<div class="col-sm-4"> 
						<html:textarea property="clauseDescForTextArea" name="check" styleId="clauseDesc_id"
						 rows="15" cols="92" styleClass="form-control">
						</html:textarea>
						<%--Added for CR-129 for loading clauses in TinyMCE Issue  --%>
						<textarea name="hdnclauseDescForTextArea" id="hdnclauseDescForTextArea"
							style="display:none;"><bean:write name="check" property="clauseDescForTextArea"/></textarea>
						<%--Added for CR-129 for loading clauses in TinyMCE Issue  --%>	
						<input type="hidden" name="selectedVersion"
							value="<%=check.getVersionNo()%>" />
					</div>
				</div>
		<logic:empty name="check" property="tableArrayData1">
			<div class="form-group">
					<label class="col-sm-4 control-label">Table Data
						<DIV id="showmainlink" style="visibility:visible">
							<A  HREF="javascript:addTable()">Add Table</A>
						</DIV>
						<DIV id="showsublink" style="visibility:hidden">
							<A  HREF="javascript:addRow(this)">Add Row</A> |
							<A  HREF="javascript:removeRow()">Delete Row</A><br>
							<A  HREF="javascript:deleteTable()">Delete Table</A>
						</DIV>
					</label>	
					<div class="col-sm-8"> 
						<DIV ID="showgrid"></DIV>
					</div>			
			</div>
		</logic:empty>
	<logic:notEmpty name="check" property="tableArrayData1">
		<div class="form-group">
				<label class="col-sm-4 control-label">Table Data
					<DIV id="showmainlink" style="visibility:hidden">
						<A  HREF="javascript:addTable()">Add Table</A>
					</DIV>
					<DIV id="showsublink" style="visibility:visible">
						<A  HREF="javascript:addRow(this)">Add Row</A> |
						<A  HREF="javascript:removeRow()">Delete Row</A><br>
						<A  HREF="javascript:deleteTable()">Delete Table</A>
					</DIV>
				</label>	
		<div class="col-sm-8"> 
			<DIV ID="showgrid">
					<div id="tabledata" class='form-horizontal'>
						<logic:notEmpty name="check" property="tableArrayData1">
							<logic:iterate id="data" name="check" property="tableArrayData1"
								indexId="counter">
								<div class='row tablerow'>
									<logic:iterate id="data1" name="data" indexId="innerCounter">
										<bean:define id="innerSize" name="innerCounter" />
											<bean:define id="size" name="counter" /> 
													<logic:equal name="size" value="0">
														<!--This outer logic:equal check is for display the first row with bold font -->
														<logic:lessThan name="innerSize" value="1">
																	<!--This Inner logic:lessthan check is for display the first row first text box -->
														<div class='col-sm-1'></div>
														<div class='col-sm-2'>&nbsp;&nbsp;
															<input type=text name="clauseTableDataArray<%=innerCounter.intValue()+1%>"
																			class="form-control textboxbluebold" size="13" maxlength="100"
																			value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>" />
														</div>	
														</logic:lessThan>
														<logic:greaterThan name="innerSize" value="0">
																	<!-- This check is for display the rest of first row four text boxes -->
														<div class='col-sm-2'>&nbsp;&nbsp;
																<input type=text name="clauseTableDataArray<%=innerCounter.intValue()+1%>"
																				class="form-control textboxbluebold" size="13" maxlength="100"
																	value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>" />
														</div>			
														</logic:greaterThan>
													</logic:equal> 
													<logic:greaterThan name="size" value="0">
																<!--This outer logic:greaterThan check is for display the second row datas -->
																<logic:equal name="innerSize" value="0">
																	<!--This Inner logic:equal check is for display the Second row first text box with radio buttons -->
																	<div class='col-sm-1 text-right'>
																		<label class='radio-inline'>
																			<html:radio property="deleterow" value="" />
																		</label>
																	</div>
																	<div class='col-sm-2'>&nbsp;&nbsp;
																	 <input type=text name="clauseTableDataArray<%=innerCounter.intValue()+1%>"
																				class="form-control" size="13" maxlength="100"
																				value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>" />
																	</div>			
																</logic:equal>
																<!--This Inner logic:notEqual check is for display the Second row other text box without radio buttons -->
																<logic:notEqual name="innerSize" value="0">
																	<div class='col-sm-2'>&nbsp;&nbsp;
																	 <input type=text name="clauseTableDataArray<%=innerCounter.intValue()+1%>"
																				class="form-control" size="10" maxlength="100"
																				value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>" />
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
		
		<div class="form-group">
				<label class="col-sm-4 control-label">Apply New Clause As Default</label>
				<div class="col-sm-4 form-inline">
					 <strong>Yes</strong><input type="radio" name="applyToDefault" value="Y" id="applyToDefaultYes" checked> 
					 <strong>No</strong><input type="radio" name="applyToDefault" value="N" id="applyToDefaultNo">
				</div>
		</div>
		<div class="row">
			<div class="spacer20"></div>
		</div>
		
		<div class="panel panel-default col-sm-offset-1" style="width:80%;align:center;">
	   			<div class="panel-heading"><h3 class="panel-title text-center">Engineering Data <font color="red" valign="top">*</font></h3></div>
	   			<div class="panel-body">
	    				<div class="form-group">
							<label class="col-sm-4 control-label">Reference EDL Number(s)</label>
							<div>
								<logic:notEqual name="check" property="selectCGCFlag" value="Y">
									<logic:notEmpty name="check" property="refEDLNo">
										<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="refEDLNo" maxlength="20" value='<%=check.getRefEDLNo()[0]%>' /></div>
										<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="refEDLNo" maxlength="20" value='<%=check.getRefEDLNo()[1]%>' /></div>
										<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="refEDLNo" maxlength="20" value='<%=check.getRefEDLNo()[2]%>' /></div>
									</logic:notEmpty>
									<logic:empty name="check" property="refEDLNo">
									<div class="col-sm-2">
										<html:text property="refEDLNo" styleClass="form-control" size="20"
											maxlength="20" value=""></html:text>
									</div>
									<div class="col-sm-2">		
												<html:text property="refEDLNo" styleClass="form-control"
											size="20" maxlength="20" value=""></html:text>
									</div>
									<div class="col-sm-2">		
												<html:text property="refEDLNo" styleClass="form-control"
											size="20" maxlength="20" value=""></html:text>
									</div>		
									</logic:empty>	
								</logic:notEqual>
								<logic:equal name="check" property="selectCGCFlag" value="Y">
									<logic:notEmpty name="check" property="refEDLNo">
									<div class="col-sm-2">
										<html:text property="refEDLNo" styleClass="form-control" size="20"
											maxlength="20" value='<%=check.getRefEDLNo()[0]%>' disabled="true"></html:text>
									</div>
									<div class="col-sm-2">	
												<html:text property="refEDLNo" styleClass="form-control"
											size="20" maxlength="20" value='<%=check.getRefEDLNo()[1]%>' disabled="true"></html:text>
									</div>
									<div class="col-sm-2">		
												<html:text property="refEDLNo" styleClass="form-control"
											size="20" maxlength="20" value='<%=check.getRefEDLNo()[2]%>' disabled="true" />
									</div>		
									</logic:notEmpty>
									<logic:empty name="check" property="refEDLNo">
									<div class="col-sm-2">
										<html:text property="refEDLNo" styleClass="form-control" size="20"
											maxlength="20" value="" disabled="true"></html:text>
										</div>
										<div class="col-sm-2">	
												<html:text property="refEDLNo" styleClass="form-control"
											size="20" maxlength="20" value="" disabled="true"></html:text>
										</div>
										<div class="col-sm-2">	
												<html:text property="refEDLNo" styleClass="form-control"
											size="20" maxlength="20" value="" disabled="true"></html:text> 
										</div>	
									</logic:empty>
								</logic:equal>	
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-4 control-label">New EDL Number(s)</label>
							<div>
								<logic:notEqual name="check" property="selectCGCFlag" value="Y">
									<logic:notEmpty name="check" property="edlNo">
										<div class="col-sm-2">
										<html:text property="edlNo" styleClass="form-control" size="5"
											maxlength="20" value='<%=check.getEdlNo()[0]%>' ></html:text>		
										</div>	
										<div class="col-sm-2">
												<html:text property="edlNo" styleClass="form-control" size="5"
											maxlength="20" value='<%=check.getEdlNo()[1]%>'></html:text>
										</div>	
										<div class="col-sm-2">	
												<html:text property="edlNo" styleClass="form-control" size="5"
											maxlength="20" value='<%=check.getEdlNo()[2]%>' />
										</div>	
									</logic:notEmpty> 
									<logic:empty name="check" property="edlNo">
										<div class="col-sm-2"><html:text property="edlNo" styleClass="form-control" size="5"
											maxlength="20" value=""></html:text>
										</div>	
										<div class="col-sm-2"><html:text property="edlNo" styleClass="form-control" size="5"
											maxlength="20" value=""></html:text>
										</div>	
										<div class="col-sm-2"><html:text property="edlNo" styleClass="form-control" size="5"
											maxlength="20" value=""></html:text>
										</div>	
									</logic:empty>
								</logic:notEqual>
								<logic:equal name="check" property="selectCGCFlag" value="Y">
									<logic:notEmpty name="check" property="edlNo">
										<div class="col-sm-2">
											<html:text property="edlNo" styleClass="form-control" size="5"
											maxlength="20" value='<%=check.getEdlNo()[0]%>' disabled="true"></html:text>
										</div>	
										<div class="col-sm-2">	
												<html:text property="edlNo" styleClass="form-control" size="5"
											maxlength="20" value='<%=check.getEdlNo()[1]%>' disabled="true"></html:text>
										</div>	
										<div class="col-sm-2">	
												<html:text property="edlNo" styleClass="form-control" size="5"
											maxlength="20" value='<%=check.getEdlNo()[2]%>' disabled="true"/>
										</div>	
									</logic:notEmpty> 
									<logic:empty name="check" property="edlNo">
										<div class="col-sm-2">
										<html:text property="edlNo" styleClass="form-control" size="5"
											maxlength="20" value="" disabled="true"></html:text>
										</div>	
										<div class="col-sm-2">
											<html:text property="edlNo" styleClass="form-control" size="5"
											maxlength="20" value="" disabled="true"></html:text>
										</div>
										<div class="col-sm-2">	
												<html:text property="edlNo" styleClass="form-control" size="5"
											maxlength="20" value="" disabled="true"></html:text>
										</div>	
									</logic:empty>
								</logic:equal>	
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-4 control-label"><bean:message key="screen.partOf" /></label>
							<div>
								<logic:notEmpty name="check" property="partOfSeqNo">
									<logic:notEmpty name="check" property="partOfCode">
	
										<div class="col-sm-2 form-inline">
											<div class="input-group">
												<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
													<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadAllClauses(1)"><span class="glyphicon glyphicon-search"></span></button>
												</span>
												<html:text indexed="1" property="partOfCode" value='<%=(check.getPartOfCode()[0])=="0" ? "":check.getPartOfCode()[0] %>'
													styleClass="form-control" readonly="true">
												</html:text>				
												<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
													<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOfCode(1)"><span class="glyphicon glyphicon-remove"></span></button>
												</span>
											</div>
											<html:hidden property="partOfSeqNo" value='<%=String.valueOf(check.getPartOfSeqNo()[0])%>' />
											<html:hidden property="partOfclaSeqNo" value='<%=String.valueOf(check.getPartOfClaSeqNo()[0])%>' />
										</div>
										<div class="col-sm-2 form-inline">
											<div class="input-group">
												<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
													<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadAllClauses(2)"><span class="glyphicon glyphicon-search"></span></button>
												</span>
												<html:text property="partOfCode" value='<%=(check.getPartOfCode()[1])=="0" ? "":check.getPartOfCode()[1] %>'
													styleClass="form-control" readonly="true">
												</html:text>				
												<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
													<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOfCode(2)"><span class="glyphicon glyphicon-remove"></span></button>
												</span>
											</div>
											<html:hidden property="partOfSeqNo" value='<%=String.valueOf(check.getPartOfSeqNo()[1])%>' />
											<html:hidden property="partOfclaSeqNo" value='<%=String.valueOf(check.getPartOfClaSeqNo()[1])%>' />
										</div>
										<div class="col-sm-2 form-inline">
											<div class="input-group">
												<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
													<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadAllClauses(3)"><span class="glyphicon glyphicon-search"></span></button>
												</span>
												<html:text property="partOfCode" value='<%=(check.getPartOfCode()[2])=="0" ? "":check.getPartOfCode()[2] %>'
														styleClass="form-control" readonly="true">
												</html:text>					
												<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
													<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOfCode(3)"><span class="glyphicon glyphicon-remove"></span></button>
												</span>
											</div>
											<html:hidden property="partOfSeqNo"  value='<%=String.valueOf(check.getPartOfSeqNo()[2])%>' />
											<html:hidden property="partOfclaSeqNo" value='<%=String.valueOf(check.getPartOfClaSeqNo()[2])%>' />
										</div>
										<div class="col-sm-2 form-inline">
											<div class="input-group">
												<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
													<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadAllClauses(4)"><span class="glyphicon glyphicon-search"></span></button>
												</span>
												<html:text property="partOfCode" value='<%=(check.getPartOfCode()[3])=="0" ? "":check.getPartOfCode()[3] %>'
													styleClass="form-control" readonly="true">
												</html:text>				
												<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
													<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOfCode(4)"><span class="glyphicon glyphicon-remove"></span></button>
												</span>
											</div>
											<html:hidden property="partOfSeqNo" value='<%=String.valueOf(check.getPartOfSeqNo()[3])%>' />
											<html:hidden property="partOfclaSeqNo" value='<%=String.valueOf(check.getPartOfClaSeqNo()[3])%>' />
										</div>
									</logic:notEmpty>
								</logic:notEmpty>
								
								<logic:empty name="check" property="partOfCode">
										<div class="col-sm-2 form-inline">
											<div class="input-group">
												<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
													<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadAllClauses(1)"><span class="glyphicon glyphicon-search"></span></button>
												</span>
												<html:text indexed="1" property="partOfCode" value="" styleClass="form-control" readonly="true"></html:text>					
												<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
													<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOfCode(1)"><span class="glyphicon glyphicon-remove"></span></button>
												</span>
											</div>
											<html:hidden property="partOfSeqNo" value="" />
											<html:hidden property="partOfclaSeqNo" value="" />
										</div>
										<div class="col-sm-2 form-inline">
											<div class="input-group">
												<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
													<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadAllClauses(2)"><span class="glyphicon glyphicon-search"></span></button>
												</span>
												<html:text property="partOfCode" value="" styleClass="form-control" readonly="true"></html:text>					
												<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
													<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOfCode(2)"><span class="glyphicon glyphicon-remove"></span></button>
												</span>
											</div>
											<html:hidden property="partOfSeqNo" value="" />
											<html:hidden property="partOfclaSeqNo" value="" />
										</div>
										<div class="col-sm-2 form-inline">
											<div class="input-group">
												<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
													<button class="btn btn-default" type="button" data-toggle="modal" data-target="#ClauseDetails" onclick="javascript:LoadAllClauses(3)"><span class="glyphicon glyphicon-search"></span></button>
												</span>
												<html:text property="partOfCode" value="" styleClass="form-control" readonly="true"></html:text>					
												<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
													<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOfCode(3)"><span class="glyphicon glyphicon-remove"></span></button>
												</span>
											</div>
											<html:hidden property="partOfSeqNo" value="" />
											<html:hidden property="partOfclaSeqNo" value="" />
										</div>
										<div class="col-sm-2 form-inline">
											<div class="input-group">
												<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
													<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadAllClauses(4)"><span class="glyphicon glyphicon-search"></span></button>
												</span>
												<html:text property="partOfCode" value="" styleClass="form-control" readonly="true"></html:text>					
												<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
													<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOfCode(4)"><span class="glyphicon glyphicon-remove"></span></button>
												</span>
											</div>
											<html:hidden property="partOfSeqNo" value="" />
											<html:hidden property="partOfclaSeqNo" value="" />
										</div>
								</logic:empty>
							</div> 
						</div>
						
						<div class="form-group">
							<label class="col-sm-4 control-label">DWO Number</label>
							<div class="col-sm-2">
								<logic:equal name="check" property="dwONumber"
										value="0">
										<html:text property="dwONumber" styleClass="form-control"
											size="20" maxlength="20" value="" />
								</logic:equal> 
								<logic:greaterThan name="check"
										property="dwONumber" value="0">
										<html:text name="check" property="dwONumber"
											styleClass="form-control" size="20" maxlength="20" />
								</logic:greaterThan>
							</div>
						</div>
							
						<div class="form-group">
							<label class="col-sm-4 control-label">Part Number</label>
							<div class="col-sm-2">
								<logic:equal name="check" property="partNumber"
										value="0">
										<html:text property="partNumber" styleClass="form-control"
											size="20" maxlength="8" value="" />
								</logic:equal> 
								<logic:greaterThan name="check"
										property="partNumber" value="0">
										<html:text property="partNumber" name="check"
											styleClass="form-control" size="20" maxlength="8" />
								</logic:greaterThan>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-4 control-label">Price Book Number</label>
							<div class="col-sm-2">
								<logic:greaterThan name="check"
										property="priceBookNumber" value="0">
										<html:text property="priceBookNumber" name="check"
											styleClass="form-control" size="20" maxlength="4"></html:text>
								</logic:greaterThan>
								<logic:equal name="check"
										property="priceBookNumber" value="0">
										<html:text property="priceBookNumber" styleClass="form-control" styleId="priceBookNo"
											size="20" maxlength="4" value=""></html:text>
								</logic:equal>
							</div>
						</div>	
						
						<div class="form-group">
							<label class="col-sm-4 control-label">Engineering Data </label>
							<div class="col-sm-2">
								<html:select property="standardEquipmentSeqNo" styleId="sltEnggData">
										<option value="-1">--Select--</option>
										<logic:present name="ModelAddClauseForm"
											property="standardEquipmentList">
											<logic:iterate id="stdEquip" name="ModelAddClauseForm"
												property="standardEquipmentList" scope="request"
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
									</html:select>
							</div>
						</div>	
						
						<div class="form-group">
							<label class="col-sm-4 control-label">Comments</label>
							<div class="col-sm-6">
								<html:textarea styleClass="form-control" name="check" property="comments"  rows="3" cols="60" />
							</div>
						</div>
			</div>
	    </div>
	    <div class="spacer30"></div>
		<div class="form-group">
			<div class="col-sm-offset-5 col-sm-3 text-center">
				<button class="btn btn-primary" property="addButton" type='button' id="addClause" 
				onclick="javascript:fnCheckSpellnCont('fnAddClauseVersion')">Add
				</button>
			</div>
		</div>
		</logic:iterate>
		<!-- Added for CR-109 -->
		<html:hidden name="check" property="appendixExitsFlag" />
	    <!-- Added for CR-109 Ends Here--> 
	    <%-- Added for CR_114--%>
	    <logic:notEqual name="check" property="appendixExitsFlag" value="N">
		    <html:hidden name="check" property="appendixImgSeqNo" />
		    <html:hidden name="check" property="mapAppendixImg" />
	    </logic:notEqual>
		<%-- Added for CR_114 Ends--%>
		<%-- Moved the code inside clause list for CR_105 --%>
		<!-- Added for CR_92 moving spell checker pop up page into spellChecker.jsp -->
		<%-- <%@ include file="/jsp/common/spellChecker.jsp" %> --%>
		</div>
</logic:greaterThan>	

<%-- Added for CR-134 --%>				
		<div class="modal fade" id="MarkClause" tabindex="-1" role="dialog" aria-labelledby="markClaReasonModal">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					      	<h4 class="modal-title" id="markClaReasonModal">Mark Clause</h4>
					      </div>
					      <div class="modal-body">				
					      	<div class="form-horizontal">			
								<div class="form-group">
									<label class="col-sm-3 control-label">Reason</label>
									<div class="col-sm-7">
										<textarea class="form-control" rows="3" maxlength="4000" id="markClaReason" name="markClaReason"></textarea>
								</div>
								</div>
							</div>
						  </div>
						  <div class="modal-footer">
						  		<button type="button" class="btn btn-primary" id="saveClaReason">Save</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						  </div>
					</div>
			</div>
		</div>