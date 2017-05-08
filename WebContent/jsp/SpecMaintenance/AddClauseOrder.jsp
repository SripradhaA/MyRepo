<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!-- Added for CR_88  -->
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>
<!-- Added for CR_121 Starts Here -->
<script type="text/javascript" src="js/Others/tiny_mce/jquery.tinymce.js"></script>
<!-- Added for CR_121 Ends Here -->
<!-- Added for CR-91 to remove repeated code -->
<%@ include file="/jsp/common/richTextEditor.jsp" %>

<SCRIPT type="text/javascript" SRC="js/AddClauseOrder.js"></SCRIPT>
<script>
        $(document).ready(function() { 
        	$("#sltLeadComp").select2({theme:'bootstrap'});
        	$("#sltLeadCompGrp").select2({theme:'bootstrap'});
        	$("#sltEnggData").select2({theme:'bootstrap'});   
        })
    </script>
<div class="container-fluid">
<html:form styleClass="form-horizontal" action="/orderAddClauseAction" method="post">
	
	<h4 class="text-center"><strong><bean:message
				key="Application.Screen.addClauseAtOrder" /></strong></h4>
	
	<p class="text-center"><mark> All new clauses added to the
			SubSection will be added at the bottom of the SubSection.</mark></p>
	<p class="text-center"><mark>Only use the Lead Component Group
			and Lead Component selections when needing to retain an existing
			clause number.</mark></p>
	<p class="text-center"><mark>Components Tied To Clause - Only
			use this to force a clause to be displayed when a component is
			selected.</mark></p>
	
<!-- To display  Messages - Start -->
	<div class="row">
		<div class="errorlayerhide" id="errorlayer">
		</div>
	</div>
	<!-- To display Messages - End -->

	<logic:present name="AddClauseOrderForm" property="messageID"
		scope="request">
		<bean:define id="messageID" name="AddClauseOrderForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
	</logic:present>
	
	<!-- Added For CR_109 -->
	<logic:present name="AddClauseOrderForm" property="modelCompVO"
		scope="request">
		<bean:size name="AddClauseOrderForm" id="compMapsize" property="modelCompVO" />
	</logic:present>
	<logic:present name="AddClauseOrderForm" property="leadClauseVO"
		scope="request">
		<bean:size name="AddClauseOrderForm" id="clasize" property="leadClauseVO" />
	</logic:present>
	<!-- Added For CR_109 - Ends here -->
	<div class="spacer20"></div>
	
	<div class="form-group">
		<label class="col-sm-4 control-label"><strong>Order Number</strong></label>
		<div class="col-sm-4">
			<p class="form-control-static"><strong><bean:write	name="AddClauseOrderForm" property="orderNo" /></strong></p>
			<html:hidden name="AddClauseOrderForm" property="specTypeSeqNo"  styleId="specTypeNo"/>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-4 control-label"><strong>Section</strong></label>
		<div class="col-sm-4">
			<p class="form-control-static"><strong><bean:write	name="AddClauseOrderForm" property="section" /></strong></p>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-4 control-label "><strong>SubSection</strong></label>
		<div class="col-sm-4">
			<p class="form-control-static"><strong><bean:write	name="AddClauseOrderForm" property="subSection" /></strong></p>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-4 control-label "><strong>Select Lead Component Group</strong></label>
		<div class="col-sm-4">					
			<html:select styleId="sltLeadCompGrp" styleClass="form-control"
				name="AddClauseOrderForm" property="compGroupSeqNo"
				onchange="javascript:fnLoadComponents1()">
				<html:option value="-1">---Select---</html:option>
				<logic:present name="AddClauseOrderForm" property="compGroupList">
					<html:optionsCollection name="AddClauseOrderForm"
						property="compGroupList" label="componentGroupName"
						value="componentGroupSeqNo" />
				</logic:present>
			</html:select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-4 control-label "><strong>Select Lead Component</strong></label>
		<div class="col-sm-3 form-inline">					
			<html:select styleId="sltLeadComp" name="AddClauseOrderForm" styleClass="form-control"
				 property="leadComponentSeqNo" onchange="javascript:checkNewComp()">

				<html:option value="-1">---Select---</html:option>
				<html:option value="-2">New Component</html:option>
				
				<logic:present name="AddClauseOrderForm" property="compGroupList">
					<logic:iterate id="compGroupList" name="AddClauseOrderForm"
						property="compGroupList"
						type="com.EMD.LSDB.vo.common.CompGroupVO">

						<logic:equal name="AddClauseOrderForm" property="compGroupSeqNo"
							value="<%=String.valueOf(compGroupList.getComponentGroupSeqNo())%>">

							<logic:iterate id="compList" name="compGroupList"
								property="component"
								type="com.EMD.LSDB.vo.common.ComponentVO" scope="page" indexId="compcnt">

								<logic:equal name="AddClauseOrderForm"
									property="compGroupSeqNo"
									value="<%=String.valueOf(compGroupList.getComponentGroupSeqNo())%>">
                                          <!--Changed for CR-74 VV49326 04-06-09-->
                                          <logic:equal name="AddClauseOrderForm"
									property="leadComponentSeqNo" value='<%=String.valueOf(compList.getComponentSeqNo())%>'>
									
									<option value='<%=compList.getComponentSeqNo()%>' selected><bean:write
										name="compList" property="componentName" /></option>
								     </logic:equal>
								     
								     <logic:notEqual name="AddClauseOrderForm"
									property="leadComponentSeqNo" value='<%=String.valueOf(compList.getComponentSeqNo())%>'>
									<%-- Added during CR_97 to remove other order components from this page
											to avoid undesired actions --%>
									<logic:equal name="compList" property="orderCompColorFlag" value="N">
										<option value="<%=String.valueOf(compList.getComponentSeqNo())%>">
										<bean:write name="compList" property="componentName" /></option>
									</logic:equal>
									
									<%-- <option value='<%=compList.getComponentSeqNo()%>'><bean:write
										name="compList" property="componentName" /></option> --%>
								     </logic:notEqual>
								    <!--Changed for CR-74 VV49326 04-06-09-->
								    <bean:define id="compCount" name="compcnt"/>
								</logic:equal>
							</logic:iterate>
						</logic:equal>
					</logic:iterate>
				</logic:present>
			</html:select>
			<logic:present name="AddClauseOrderForm" property="modelCompVO">
				<logic:lessEqual name="compCount" value="<bean:write name='compMapsize'/>">
					<A id="viewAllCompClauses" HREF="javascript:fnLoadAllComponents()" style="display: inline;" data-toggle="tooltip" title="Click to see all available Components & Clauses across Models">
                         <span class="glyphicon glyphicon glyphicon-search" aria-hidden="true"></span>
                    </A>
				</logic:lessEqual>				
				<input type="hidden" name="compCount" id="compCount" value="<bean:write name='compCount'/>"/>
				<input type="hidden" name="compMapsize" id="compMapsize" value="<bean:write name='compMapsize'/>"/>
				<%-- Added for CR-128 for Component Validation Flag--%>
				<input type="hidden" name="hdnValidFlag" value="<bean:write name="compGroupList" property="validFlag"/>"/>
				<%-- Added for CR-128 for Component Validation Flag ends here--%>
			</logic:present>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-4 control-label">New Component</label>
		<div class="col-sm-4 form-inline">	
			<html:text styleClass="form-control" property="validCompName" maxlength="10" styleId="sapCusCode" readonly="true" />
			<html:link href="javascript:deleteNewComponent()">
					<span class="glyphicon glyphicon-remove-circle text-danger" aria-hidden="true"></span> 
				</html:link>
			
		</div>
	</div>
	<logic:notPresent name="AddClauseOrderForm" property="leadClauseVO">
		<div class="form-group">
			<label class="col-sm-4 control-label">New Clause Description<font color="red" valign="top">*</font></label>
			<div class="col-sm-4"> 
				<html:textarea styleId="clauseDesc_id" name="AddClauseOrderForm" property="newClauseDesc"
						rows="15" cols="92" styleClass="form-control"></html:textarea>
					<%--Added for CR-129 for loading clauses in TinyMCE Issue  --%>
					<textarea name="clauseDesc" id="hdnclauseDescForTextArea"
					style="display:none;"><bean:write name="AddClauseOrderForm" property="newClauseDesc"/></textarea>
					<%--Added for CR-129 for loading clauses in TinyMCE Issue  --%>
			</div>
		</div>
		<logic:empty name="AddClauseOrderForm" property="arlClauseVO">
			<div class="form-group">
				<label class="col-sm-4 control-label">Table Data
					<DIV id="showmainlink" style="visibility:visible">
						<A CLASS='linkstyleItem' HREF="javascript:addTable()">Add Table</A>&nbsp;
					</DIV>
					<DIV id="showsublink" style="visibility:hidden">
						<A CLASS=linkstyle1 HREF="javascript:addRow(this)">Add Row</A>&nbsp;|&nbsp;
						<A CLASS=linkstyle1 HREF="javascript:removeRow()">Delete Row</A><br>
						<A CLASS='linkstyle1' HREF="javascript:deleteTable()">Delete Table</A>&nbsp;
					</DIV>
				</label>
				<div class="col-sm-8"> 
					<DIV ID="showgrid"></DIV>
				</div>
			</div>
		</logic:empty>
		<logic:notEmpty name="AddClauseOrderForm" property="arlClauseVO">
			<logic:iterate id="check" name="AddClauseOrderForm" property="arlClauseVO" type="com.EMD.LSDB.vo.common.ClauseVO"
			           scope="request">
				<logic:empty name="check" property="tableArrayData1">
					<div class="form-group">
						<label class="col-sm-4 control-label">Table Data
							<DIV id="showmainlink" style="visibility:visible">
								<A CLASS='linkstyleItem' HREF="javascript:addTable()">Add Table</A>&nbsp;
							</DIV>
							<DIV id="showsublink" style="visibility:hidden">
								<A CLASS=linkstyle1 HREF="javascript:addRow(this)">Add Row</A>&nbsp;|&nbsp;
								<A CLASS=linkstyle1 HREF="javascript:removeRow()">Delete Row</A><br>
								<A CLASS='linkstyle1' HREF="javascript:deleteTable()">Delete Table</A>&nbsp;
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
								<A CLASS='linkstyleItem' HREF="javascript:addTable()">Add Table</A>&nbsp;
							</DIV>
							<DIV id="showsublink" style="visibility:visible">
								<A CLASS=linkstyle1 HREF="javascript:addRow(this)">Add Row</A>&nbsp;|&nbsp;
								<A CLASS=linkstyle1 HREF="javascript:removeRow()">Delete Row</A><br>
								<A CLASS='linkstyle1' HREF="javascript:deleteTable()">Delete Table</A>&nbsp;
							</DIV>
						</label>
						<div class="col-sm-8"> 
							<DIV ID="showgrid">
								<div id="tblGrid" class='form-horizontal'>
									<logic:notEmpty name="check" property="tableArrayData1">
										<logic:iterate id="data" name="check" property="tableArrayData1"
											indexId="counter">
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
																name="clauseTableDataArray<%=innerCounter.intValue()+1%>"
																class="form-control" size="13" maxlength="100"
																value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>" />
															</div>
														</logic:lessThan>
		
														<logic:greaterThan name="innerSize" value="0">
															<!-- This check is for display the rest of first row four text boxes -->
															<div class='col-sm-2'>&nbsp;&nbsp;<input type=text
																name="clauseTableDataArray<%=innerCounter.intValue()+1%>"
																class="form-control" size="13" maxlength="100"
																value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>" />
															</div>
														</logic:greaterThan>
													</logic:equal> <logic:greaterThan name="size" value="0">
														<!--This outer logic:greaterThan check is for display the second row datas -->
														<logic:equal name="innerSize" value="0">
															<!--This Inner logic:equal check is for display the Second row first text box with radio buttons -->
																<div class='col-sm-1 text-right'><label class='radio-inline'><html:radio property="deleterow" styleClass="radcheck"
																value="" /></label></div><div class='col-sm-2'>&nbsp;&nbsp;
																	<input type=text
																	name="clauseTableDataArray<%=innerCounter.intValue()+1%>"
																	class="form-control" size="13" maxlength="100"
																	value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>" />
																</div>
														</logic:equal>
														<!--This Inner logic:notEqual check is for display the Second row other text box without radio buttons -->
														<logic:notEqual name="innerSize" value="0">
														<div class='col-sm-2'>&nbsp;&nbsp;
													 		<input type=text
																name="clauseTableDataArray<%=innerCounter.intValue()+1%>"
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
			</logic:iterate>
		</logic:notEmpty>
		
		<div class="row">
			<div class="spacer20"></div>
		</div>
		
		 <logic:empty name="AddClauseOrderForm" property="componentVO">
		 	<div class="form-group">
				<label class="col-sm-4 control-label">Components Tied To Clause</label>
				<div class="col-sm-4 form-inline">
					<html:select property="component" multiple="true" styleClass="form-control"></html:select>
					<html:link href="javascript:AddComponent()"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></html:link>
					<html:link href="javascript:deleteComponent()"><span class="glyphicon glyphicon-remove-circle text-danger" aria-hidden="true"></span></html:link>
				</div>
			</div>
		 </logic:empty>
		 <logic:notEmpty name="AddClauseOrderForm" property="componentVO">
		 	<div class="form-group">
				<label class="col-sm-4 control-label">Components Tied To Clause</label>
				<div class="col-sm-4 form-inline">
					<html:select property="component" multiple="true" styleClass="form-control">
						<logic:present name="AddClauseOrderForm" property="componentVO">
							  <logic:iterate id="comp" name="AddClauseOrderForm" property="componentVO" type="com.EMD.LSDB.vo.common.ComponentVO">
									<option value='<%=comp.getComponentSeqNo()%>'><bean:write name="comp"
									        property="componentDescription"/></option>
							   </logic:iterate>			
							</logic:present>
					</html:select>
					<html:link href="javascript:AddComponent()"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></html:link>
					<html:link href="javascript:deleteComponent()"><span class="glyphicon glyphicon-remove-circle text-danger" aria-hidden="true"></span></html:link>
				</div>
			</div>
		 </logic:notEmpty>
		 
		 
		 <div class="panel panel-default col-sm-offset-1" style="width:80%;align:center;">
	    	<div class="panel-heading"><h3 class="panel-title text-center">Engineering Data <font color="red" valign="top">*</font></h3></div>
	   		<div class="panel-body">
   				<div class="form-group">
					<label class="col-sm-4 control-label">Reference EDL Number(s)</label>
					<div>
						<logic:empty name="AddClauseOrderForm" property="arlClauseVO">
							<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="refEdlNo" maxlength="20" value="" /></div>
							<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="refEdlNo" maxlength="20" value="" /></div>
							<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="refEdlNo" maxlength="20" value="" /></div>
						</logic:empty>
						<logic:notEmpty name="AddClauseOrderForm" property="arlClauseVO">
							<logic:iterate id="check" name="AddClauseOrderForm" property="arlClauseVO" type="com.EMD.LSDB.vo.common.ClauseVO"
					           scope="request">
					           	<div class="col-sm-2">
					           		<html:text styleClass="form-control" size="5" property="refEdlNo" maxlength="20" value='<%=check.getRefEDLNo()[0]%>' />
					           	</div>
								<div class="col-sm-2">
					           		<html:text styleClass="form-control" size="5" property="refEdlNo" maxlength="20" value='<%=check.getRefEDLNo()[1]%>' />
					           	</div>
					           	<div class="col-sm-2">
					           		<html:text styleClass="form-control" size="5" property="refEdlNo" maxlength="20" value='<%=check.getRefEDLNo()[2]%>' />
					           	</div>
							</logic:iterate>
						</logic:notEmpty>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">New EDL Number(s)</label>
					<div>
						<logic:empty name="AddClauseOrderForm" property="arlClauseVO">
							<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="newEdlNo" maxlength="20" value="" /></div>
							<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="newEdlNo" maxlength="20" value="" /></div>
							<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="newEdlNo" maxlength="20" value="" /></div>
						</logic:empty>
						<logic:notEmpty name="AddClauseOrderForm" property="arlClauseVO">
							<logic:iterate id="check" name="AddClauseOrderForm" property="arlClauseVO" type="com.EMD.LSDB.vo.common.ClauseVO"
					           scope="request">
					           	<div class="col-sm-2">
					           		<html:text styleClass="form-control" size="5" property="newEdlNo" maxlength="20" value='<%=check.getEdlNo()[0]%>' />
					           	</div>
								<div class="col-sm-2">
					           		<html:text styleClass="form-control" size="5" property="newEdlNo" maxlength="20" value='<%=check.getEdlNo()[1]%>' />
					           	</div>
					           	<div class="col-sm-2">
					           		<html:text styleClass="form-control" size="5" property="newEdlNo" maxlength="20" value='<%=check.getEdlNo()[2]%>' />
					           	</div>
							</logic:iterate>
						</logic:notEmpty>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">Part of</label>
					<div>
						<logic:empty name="AddClauseOrderForm" property="arlClauseVO">
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
										<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(2)"><span class="glyphicon glyphicon-remove"></span></button>
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
										<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(4)"><span class="glyphicon glyphicon-remove"></span></button>
									</span>
								</div>
								<html:hidden property="partOfSeqNo" value="" /> 
								<html:hidden property="clauseSeqNum" value="" />
							</div>
						</logic:empty>
						
						<logic:notEmpty name="AddClauseOrderForm" property="arlClauseVO">
							<logic:iterate id="check" name="AddClauseOrderForm" property="arlClauseVO" type="com.EMD.LSDB.vo.common.ClauseVO"
	         					scope="request">
	         					<div class="col-sm-2 form-inline">
									<div class="input-group">
										<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
											<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(1)"><span class="glyphicon glyphicon-search"></span></button>
										</span>
										<html:text styleClass="form-control" styleId="partOf1" property="partOf" readonly="true" value='<%=check.getClauseNoArray()[0]%>' />					
										<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
											<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(1)"><span class="glyphicon glyphicon-remove"></span></button>
										</span>
									</div>
									<html:hidden property="partOfSeqNo" value='<%=String.valueOf(check.getPartOfSeqNo()[0])%>' /> 
									<html:hidden property="clauseSeqNum" value='<%=check.getClauseSeqNoArray()[0]%>' />
								</div>
								<div class="col-sm-2 form-inline">
									<div class="input-group">
										<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
											<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(2)"><span class="glyphicon glyphicon-search"></span></button>
										</span>
										<html:text styleClass="form-control" styleId="partOf2" property="partOf" readonly="true" value='<%=check.getClauseNoArray()[1]%>' />					
										<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
											<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(2)"><span class="glyphicon glyphicon-remove"></span></button>
										</span>
									</div>
									<html:hidden property="partOfSeqNo" value='<%=String.valueOf(check.getPartOfSeqNo()[1])%>' /> 
									<html:hidden property="clauseSeqNum" value='<%=check.getClauseSeqNoArray()[1]%>' />
								</div>
								<div class="col-sm-2 form-inline">
									<div class="input-group">
										<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
											<button class="btn btn-default" type="button" data-toggle="modal" data-target="#ClauseDetails" onclick="javascript:LoadClauses(3)"><span class="glyphicon glyphicon-search"></span></button>
										</span>
										<html:text styleClass="form-control" styleId="partOf3" property="partOf" readonly="true" value='<%=check.getClauseNoArray()[2]%>' />					
										<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
											<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(3)"><span class="glyphicon glyphicon-remove"></span></button>
										</span>
									</div>
									<html:hidden property="partOfSeqNo" value='<%=String.valueOf(check.getPartOfSeqNo()[2])%>' /> 
									<html:hidden property="clauseSeqNum" value='<%=check.getClauseSeqNoArray()[2]%>' />
								</div>
								<div class="col-sm-2 form-inline">
									<div class="input-group">
										<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
											<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(4)"><span class="glyphicon glyphicon-search"></span></button>
										</span>
										<html:text styleClass="form-control" styleId="partOf4" property="partOf" readonly="true" value='<%=check.getClauseNoArray()[3]%>' />					
										<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
											<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(4)"><span class="glyphicon glyphicon-remove"></span></button>
										</span>
									</div>
									<html:hidden property="partOfSeqNo" value='<%=String.valueOf(check.getPartOfSeqNo()[3])%>' /> 
									<html:hidden property="clauseSeqNum" value='<%=check.getClauseSeqNoArray()[3]%>' />
								</div>
	         					</logic:iterate>
						</logic:notEmpty>
					</div> 
				</div>
			
				<div class="form-group">
					<label class="col-sm-4 control-label">DWO Number</label>
					<div class="col-sm-2">
						<html:text styleClass="form-control" size="5" property="dwoNo" maxlength="20"/>
					</div>
				</div>
					
				<div class="form-group">
					<label class="col-sm-4 control-label">Part Number</label>
					<div class="col-sm-4 control-label">
						<html:text styleClass="form-control" size="5" property="partNo" maxlength="20"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">Price Book Number</label>
					<div class="col-sm-4 control-label">
						<html:text styleClass="form-control" size="5" property="priceBookNo" maxlength="20" styleId="priceBookNo"/>
					</div>
				</div>	
				
				<div class="form-group">
					<label class="col-sm-4 control-label">Engineering Data </label>
					<div class="col-sm-2">
						<html:select styleId="sltEnggData" name="AddClauseOrderForm" styleClass="form-control" property="standardEquipmentSeqNo">
							<html:option value="-1">---Select---</html:option>
							<logic:present name="AddClauseOrderForm" property="standardEquipmentList">
								<html:optionsCollection name="AddClauseOrderForm"
									property="standardEquipmentList" label="standardEquipmentDesc"
									value="standardEquipmentSeqNo" />
							</logic:present>
						</html:select>
					</div>
				</div>	
				
				<div class="form-group">
					<label class="col-sm-4 control-label">Comments</label>
					<div class="col-sm-6">
						<html:textarea styleClass="form-control" name="AddClauseOrderForm" property="comments" rows="3" cols="60" />
					</div>
				</div>
			</div>
	    </div>
	</logic:notPresent>	
	
	<logic:present name="AddClauseOrderForm" property="leadClauseVO">		
	<logic:greaterThan name="clasize" value="0">
	<logic:iterate id="check" name="AddClauseOrderForm"
		property="leadClauseVO" type="com.EMD.LSDB.vo.common.ClauseVO"
		scope="request">
			<div class="form-group required-field">
				<label class="col-sm-4 control-label">New Clause Description</label>
				<div class="col-sm-4"> 
					<html:textarea styleId="clauseDesc_id" name="AddClauseOrderForm" property="newClauseDesc"
					rows="15" cols="92" styleClass="form-control" value="<%=String.valueOf(check.getClauseDesc())%>">					
					</html:textarea>
					
				</div>
			</div>
            
            <logic:empty name="check" property="tableArrayData1">
				<div class="form-group">
					<label class="col-sm-4 control-label">Table Data 
						<DIV id="showmainlink" style="visibility:visible">
							<A CLASS='linkstyleItem' HREF="javascript:addTable()">Add Table</A>&nbsp;
						</DIV>
						<DIV id="showsublink" style="visibility:hidden">
							<A CLASS=linkstyle1 HREF="javascript:addRow(this)">Add Row</A>&nbsp;|&nbsp;
							<A CLASS=linkstyle1 HREF="javascript:removeRow()">Delete Row</A><br>
							<A CLASS='linkstyle1' HREF="javascript:deleteTable()">Delete Table</A>&nbsp;
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
							<A CLASS='linkstyleItem' HREF="javascript:addTable()">Add Table</A>&nbsp;
						</DIV>
						<DIV id="showsublink" style="visibility:visible">
							<A CLASS=linkstyle1 HREF="javascript:addRow(this)">Add Row</A>&nbsp;|&nbsp;
							<A CLASS=linkstyle1 HREF="javascript:removeRow()">Delete Row</A><br>
							<A CLASS='linkstyle1' HREF="javascript:deleteTable()">Delete Table</A>&nbsp;
						</DIV>
					</label>
					<div class="col-sm-8"> 
						<DIV ID="showgrid">
							<div id="tblGrid" class='form-horizontal'>
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
																<input type=text
																name="clauseTableDataArray<%=innerCounter.intValue()+1%>"
																class="form-control textboxbluebold" size="13" maxlength="100"
																value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>" />
															</div>
														</logic:lessThan>
		
														<logic:greaterThan name="innerSize" value="0">
															<!-- This check is for display the rest of first row four text boxes -->
															<div class='col-sm-2'>&nbsp;&nbsp;<input type=text
																name="clauseTableDataArray<%=innerCounter.intValue()+1%>"
																class="form-control textboxbluebold" size="13" maxlength="100"
																value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>" />
															</div>
														</logic:greaterThan>
													</logic:equal> 
													<logic:greaterThan name="size" value="0">
														<!--This outer logic:greaterThan check is for display the second row datas -->
														<logic:equal name="innerSize" value="0">
															<!--This Inner logic:equal check is for display the Second row first text box with radio buttons -->
															<div class='col-sm-1 text-right'><label class='radio-inline'><html:radio property="deleterow" styleClass="radcheck"
																value="" /></label></div><div class='col-sm-2'>&nbsp;&nbsp;
																<input type=text
																name="clauseTableDataArray<%=innerCounter.intValue()+1%>"
																class="form-control" size="15" maxlength="100"
																value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>" /></div>
														</logic:equal>
													<!--This Inner logic:notEqual check is for display the Second row other text box without radio buttons -->
													<logic:notEqual name="innerSize" value="0">
													<div class='col-sm-2'>&nbsp;&nbsp;
												 		<input type=text
															name="clauseTableDataArray<%=innerCounter.intValue()+1%>"
															class="form-control" size="15" maxlength="100"
															value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>" /></div>
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
		    
		    <div class="row">
				<div class="spacer20"></div>
			</div>
		    <div class="form-group">
				<label class="col-sm-4 control-label">Components Tied To Clause</label>
				<div class="col-sm-4 form-inline">
					<html:select property="component" multiple="true" styleClass="form-control"></html:select>
					<html:link href="javascript:AddComponent()"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></html:link>
					<html:link href="javascript:deleteComponent()"><span class="glyphicon glyphicon-remove-circle text-danger" aria-hidden="true"></span></html:link>
				</div>
			</div>
		 

			<div class="panel panel-default col-sm-offset-1" style="width:80%;align:center;">
		    	<div class="panel-heading"><h3 class="panel-title text-center">Engineering Data <font color="red" valign="top">*</font></h3></div>
		   		<div class="panel-body">
    				<div class="form-group">
						<label class="col-sm-4 control-label">Reference EDL Number(s)</label>
						<div>
							<logic:empty name="check" property="refEDLNo">
								<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="refEdlNo" maxlength="20" value="" /></div>
								<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="refEdlNo" maxlength="20" value="" /></div>
								<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="refEdlNo" maxlength="20" value="" /></div>
							</logic:empty>
							<logic:notEmpty name="check" property="refEDLNo">
									<div class="col-sm-2">
						           		<html:text styleClass="form-control" size="5" property="refEdlNo" maxlength="20" value='<%=check.getRefEDLNo()[0]%>' />
						           	</div>
									<div class="col-sm-2">
						           		<html:text styleClass="form-control" size="5" property="refEdlNo" maxlength="20" value='<%=check.getRefEDLNo()[1]%>' />
						           	</div>
						           	<div class="col-sm-2">
						           		<html:text styleClass="form-control" size="5" property="refEdlNo" maxlength="20" value='<%=check.getRefEDLNo()[2]%>' />
						           	</div>
							</logic:notEmpty>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-4 control-label">New EDL Number(s)</label>
						<div>
							<logic:empty name="check" property="edlNo">
								<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="newEdlNo" maxlength="20" value="" /></div>
								<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="newEdlNo" maxlength="20" value="" /></div>
								<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="newEdlNo" maxlength="20" value="" /></div>
							</logic:empty>
							<logic:notEmpty name="check" property="edlNo">
								<div class="col-sm-2">
						           		<html:text styleClass="form-control" size="5" property="newEdlNo" maxlength="20" value='<%=check.getEdlNo()[0]%>' />
						           	</div>
									<div class="col-sm-2">
						           		<html:text styleClass="form-control" size="5" property="newEdlNo" maxlength="20" value='<%=check.getEdlNo()[1]%>' />
						           	</div>
						           	<div class="col-sm-2">
						           		<html:text styleClass="form-control" size="5" property="newEdlNo" maxlength="20" value='<%=check.getEdlNo()[2]%>' />
						           	</div>
							</logic:notEmpty>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-4 control-label">Part of</label>
						<div>
							<logic:empty name="check" property="partOfCode">
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
											<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(2)"><span class="glyphicon glyphicon-remove"></span></button>
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
											<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(4)"><span class="glyphicon glyphicon-remove"></span></button>
										</span>
									</div>
									<html:hidden property="partOfSeqNo" value="" /> 
									<html:hidden property="clauseSeqNum" value="" />
								</div>
							</logic:empty>
							
							<logic:notEmpty name="check" property="partOfCode">
								<div class="col-sm-2 form-inline">
									<div class="input-group">
										<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
											<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(1)"><span class="glyphicon glyphicon-search"></span></button>
										</span>
										<html:text styleClass="form-control" styleId="partOf1" property="partOf" readonly="true" value='<%=(check.getPartOfCode()[0])=="0" ? "":check.getPartOfCode()[0] %>' />
										<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
											<button class="clearbtn btn btn-default" type="button" onclick="javascript:deletePartOf(1)"><span class="glyphicon glyphicon-remove"></span></button>
										</span>
									</div>
									<html:hidden property="partOfSeqNo" value='<%=String.valueOf(check.getPartOfSeqNo()[0])%>' /> 
									<html:hidden property="clauseSeqNum" value='<%=String.valueOf(check.getPartOfClaSeqNo()[0])%>' />
								</div>
								<div class="col-sm-2 form-inline">
									<div class="input-group">
										<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
											<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(2)"><span class="glyphicon glyphicon-search"></span></button>
										</span>
										<html:text styleClass="form-control" styleId="partOf2" property="partOf" readonly="true" value='<%=(check.getPartOfCode()[1])=="0" ? "":check.getPartOfCode()[1] %>' />					
										<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
											<button class="clearbtn btn btn-default" type="button" onclick="javascript:deletePartOf(2)"><span class="glyphicon glyphicon-remove"></span></button>
										</span>
									</div>
									<html:hidden property="partOfSeqNo" value='<%=String.valueOf(check.getPartOfSeqNo()[1])%>' /> 
									<html:hidden property="clauseSeqNum" value='<%=String.valueOf(check.getPartOfClaSeqNo()[1])%>' />
								</div>
								<div class="col-sm-2 form-inline">
									<div class="input-group">
										<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
											<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(3)"><span class="glyphicon glyphicon-search"></span></button>
										</span>
										<html:text styleClass="form-control" styleId="partOf2" property="partOf" readonly="true" value='<%=(check.getPartOfCode()[2])=="0" ? "":check.getPartOfCode()[2] %>' />
										<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
											<button class="clearbtn btn btn-default" type="button" onclick="javascript:deletePartOf(3)"><span class="glyphicon glyphicon-remove"></span></button>
										</span>
									</div>
									<html:hidden property="partOfSeqNo" value='<%=String.valueOf(check.getPartOfSeqNo()[2])%>' /> 
									<html:hidden property="clauseSeqNum" value='<%=String.valueOf(check.getPartOfClaSeqNo()[2])%>' />
								</div>
								<div class="col-sm-2 form-inline">
									<div class="input-group">
										<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
											<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(4)"><span class="glyphicon glyphicon-search"></span></button>
										</span>
										<html:text styleClass="form-control" styleId="partOf4" property="partOf" readonly="true" value='<%=(check.getPartOfCode()[3])=="0" ? "":check.getPartOfCode()[3] %>' />
										<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
											<button class="clearbtn btn btn-default" type="button" onclick="javascript:deletePartOf(4)"><span class="glyphicon glyphicon-remove"></span></button>
										</span>
									</div>
									<html:hidden property="partOfSeqNo" value='<%=String.valueOf(check.getPartOfSeqNo()[3])%>' /> 
									<html:hidden property="clauseSeqNum" value='<%=String.valueOf(check.getPartOfClaSeqNo()[3])%>' />
								</div>
							</logic:notEmpty>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-4 control-label">DWO Number</label>
						<div class="col-sm-2">
							<logic:equal name="check" property="dwONumber" value="0">
								<html:text styleClass="form-control" size="5" property="dwoNo" maxlength="20"/>
							</logic:equal>
							<logic:greaterThan name="check" property="dwONumber" value="0">
								<html:text styleClass="form-control" size="5" property="dwoNo" name="check" maxlength="20" />
							</logic:greaterThan>
						</div>
					</div>
						
					<div class="form-group">
						<label class="col-sm-4 control-label">Part Number</label>
						<div class="col-sm-2">
							<logic:equal name="check" property="dwONumber" value="0">
								<html:text styleClass="form-control" size="5" property="partNo" maxlength="20"/>
							</logic:equal>
							<logic:greaterThan name="check"	property="dwONumber" value="0">
								<html:text styleClass="form-control" size="5" property="partNo" maxlength="8" name="check" />
							</logic:greaterThan>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-4 control-label">Price Book Number</label>
						<div class="col-sm-2">
							<logic:equal name="check" property="priceBookNumber" value="0">
								<html:text styleClass="form-control" size="5" property="priceBookNo" maxlength="20" styleId="priceBookNo"/>
							</logic:equal>
							<logic:greaterThan name="check"	property="priceBookNumber" value="0">
								<html:text styleClass="form-control" size="20" styleId="priceBookNo"
									property="priceBookNo" maxlength="4" name="check" />
							</logic:greaterThan>
						</div>
					</div>	
					
					<div class="form-group">
						<label class="col-sm-4 control-label">Engineering Data</label>
						<div class="col-sm-2">
							<html:select styleId="sltEnggData" styleClass="form-control"
								name="AddClauseOrderForm" property="standardEquipmentSeqNo">
								<option value="-1">---Select---</option>
								<logic:present name="AddClauseOrderForm"
									property="standardEquipmentList">
									
									<logic:iterate id="stdEquip" name="AddClauseOrderForm"
										property="standardEquipmentList" scope="request"
										type="com.EMD.LSDB.vo.common.StandardEquipVO">							
									
										<logic:equal value="<%=String.valueOf(check.getStandardEquipmentSeqNo())%>"
											name="stdEquip" property="standardEquipmentSeqNo">
											<option value="<%=stdEquip.getStandardEquipmentSeqNo()%>"
												selected><bean:write name="stdEquip"
												property="standardEquipmentDesc" /></option>
										</logic:equal>
										
										<logic:notEqual	value="<%=String.valueOf(check.getStandardEquipmentSeqNo())%>"
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
							<html:textarea styleClass="form-control" name="check" property="comments" rows="3" cols="60" />
						</div>
					</div>
				</div>
	    	</div>	
		</logic:iterate>
	</logic:greaterThan>
</logic:present>
	
	<div class="panel panel-default col-sm-offset-1" style="width:80%;align:center;">
    	<div class="panel-heading"><h3 class="panel-title text-center">Spec Supplement</h3></div>
   		<div class="panel-body">
   			<div class="form-group">
   				<label class="col-sm-4 control-label">Reason
   				<logic:greaterEqual name="AddClauseOrderForm" property="specStatusCode" value="3">
					<font color="red" valign="top">*</font>
				</logic:greaterEqual>
   				</label>
				<div class="col-sm-6">
					<html:textarea styleClass="form-control" name="AddClauseOrderForm" property="reason" rows="3" cols="60" />
				</div>
			</div>
    	</div>
    </div>
	
	<div class="form-group">
		<div class="col-sm-offset-4 col-sm-3 text-center">
			<button class="btn btn-primary" type='button' ONCLICK="javascript:fnCheckSpellnCont('fnAddClauseOrder')">Add</button>
			<button class="btn btn-primary" type='button' ONCLICK="javascript:fnModifySpec()">Return to Modify Spec</button>
		</div>
		<div class="col-sm-offset-3 col-sm-2 text-right">
			<logic:equal name="AddClauseOrderForm" property="changeControlFlag" value="N">
				<button class="btn btn-danger" type='button' name="btnAddClauseOrder/model" ONCLICK="javascript:fnAddClauseModel()">Add To Order/Model</button>
			</logic:equal>
		</div>
	</div>
	
	<html:hidden property="componentSeqNo" />
	<html:hidden property="hdnSection" />
	<html:hidden property="hdnSubsection" />
	<html:hidden property="hdnModelSeqNo" />
	<html:hidden property="hdnSubSecSeqNo" />
	<html:hidden property="hdnOrderKey" />
	<html:hidden property="hdnCustSeqNo" />
	<html:hidden property="hdnsecSeq" />
	<html:hidden property="hdnsecCode" />
	<html:hidden property="hdnSecName" />
	<html:hidden property="hdnRevCode" />
	<html:hidden property="hdnOrderNo" />
	<html:hidden property="specStatusCode" />
	<input type="hidden" name="hdncomponentGroupSeqNo" />
	<!-- Added For LSDB_CR-35-->
	<html:hidden property="clauseSource" />

	<!-- Added for CR- 51-->
	<html:hidden property="roleID" />
	<html:hidden property="specTypeSeqNo" />
	<!--Added for CR-74 VV49326 04-06-09-->
	<input type="hidden" name="hdnComponentName"/>
	<%-- Added for CR_97 RJ85495 14-03-11--%>
	<html:hidden property="newCRFlag"/>
	<%-- CR_97 Ends here--%>
<!-- Added for CR_97 -->
	<html:hidden property="changeControlFlag" />
	<!-- CR_97 Ends Here-->
	<br>
	<%-- Added for CR_99 for displaying JUNK characters by RJ85495 --%>	
	<jsp:include page="/jsp/common/ClauseComparison.jsp" />
	<%-- CR_99 Ends here --%>
	<!-- Added for CR_92 moving spell checker pop up page into spellChecker.jsp -->
	<%-- <%@ include file="/jsp/common/spellChecker.jsp" %> --%>
</html:form>
</div>
