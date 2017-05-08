<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>

<script language="javascript" src="js/Others/tiny_mce/jquery.tinymce.js"></script>

<%@ include file="/jsp/common/richTextEditor.jsp" %>
<HEAD>
<SCRIPT LANGUAGE="JavaScript" SRC="js/AddClauseRev.js"></SCRIPT>
<script>
     $(document).ready(function() { 
     	$("#sltEngData").select2({theme:'bootstrap'}); 
     })
 </script>
</HEAD>
<div class="container-fluid">
<html:form action="/AddClauseRev" method="post" styleClass="form-horizontal">

	<h4 class="text-center"><bean:message key="Application.Screen.AddClauseRevision" /></h4>
	
	<div class="row">
		<div class="errorlayerhide" id="errorlayer">
		</div>
	</div>

	<logic:present name="ClauseRevForm" property="messageID"
		scope="request">
			<bean:define id="messageID" name="ClauseRevForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>

	</logic:present>

	<logic:notEmpty name="ClauseRevForm" property="clauseList"
		scope="request">
		<bean:size id="clsize" name="ClauseRevForm" property="clauseList" />
	</logic:notEmpty>


	<logic:notEmpty name="ClauseRevForm" property="stdEquipmentList"
		scope="request">
		<bean:size id="stdsize" name="ClauseRevForm"
			property="stdEquipmentList" />
	</logic:notEmpty>

	<h5 class="text-center"><html:link
				href="#" onclick="javascript:fnSelectClauseRevSpec(); return false;"
				styleClass="linkstyleItem">Select Clause Revision
		</html:link>&nbsp;| Add Clause Revision</h5>
		
	<html:hidden property="clauseSeqNo" />

	<html:hidden property="orderKey" />
	<html:hidden property="secSeq" />
	<html:hidden property="revCode" />
	<html:hidden property="customerSeqNo" />


	<logic:present name="ClauseRevForm" property="clauseList">
		<logic:iterate id="check" name="ClauseRevForm" property="clauseList"
			type="com.EMD.LSDB.vo.common.ClauseVO" scope="request">
			
			<div class="form-group">
				<label class="col-sm-4 control-label"><strong>Order Number</strong></label>
				<div class="col-sm-4">
					<p class="form-control-static"><strong><bean:write name="check" property="orderNo" /></strong></p>
					<html:hidden name="check" property="orderNo" />
					<html:hidden name="check" property="modelSeqNo" />
					<html:hidden name="check" property="specStatusCode" />
					<html:hidden name="check" property="specTypeNo" styleId="specTypeNo"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label "><strong>Section</strong></label>
				<div class="col-sm-4">
					<p class="form-control-static"><strong><bean:write name="check" property="sectionName" /></strong></p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label "><strong>SubSection</strong></label>
				<div class="col-sm-4">
					<p class="form-control-static"><strong><bean:write name="check" property="subSectionName" /></strong></p>
					<html:hidden name="check" property="subSectionSeqNo" />
				</div>
			</div>
			<div class="form-group required-field">
				<label class="col-sm-4 control-label">New Clause Description</label>
				<div class="col-sm-4"> 
					<html:textarea styleId="clauseDesc_id" name="check"
								property="clauseDesc" rows="15" cols="92"
								styleClass="form-control"></html:textarea>
								<%--Added for CR-129 for loading clauses in TinyMCE Issue  --%>
								<textarea name="clauseDesc" id="hdnclauseDescForTextArea"
								style="display:none;"><bean:write name="check" property="clauseDesc"/></textarea>
				</div>
			</div>
			<logic:empty name="check" property="tableArrayData1">
				<div class="form-group">
					<label class="col-sm-4 control-label">Table Data
						<DIV id="showmainlink" style="visibility:visible">
							<A  HREF="javascript:addTable()">Add Table</A>
						</DIV>
						<DIV id="showsublink" style="visibility:hidden">
							<A  HREF="javascript:addTableRow(this)">Add Row</A> |
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
						<A  HREF="javascript:addTableRow(this)">Add Row</A> |
						<A  HREF="javascript:removeRow()">Delete Row</A><br>
						<A  HREF="javascript:deleteTable()">Delete Table</A>
					</DIV>
				</label>	
			<div class="col-sm-8"> 
				<DIV ID="showgrid">
					<div id="tabledata" class='form-horizontal'>
						<logic:notEmpty name="check" property="tableArrayData1">
							<logic:iterate id="data" name="check"
								property="tableArrayData1" indexId="counter">
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
																class="form-control textboxbluebold" size="13" MAXLENGTH="100"
																value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>" />
														</div>
														</logic:lessThan>
														<logic:greaterThan name="innerSize" value="0">
															<!-- This check is for display the rest of first row four text boxes -->
														<div class='col-sm-2'>&nbsp;&nbsp;
															<input type=text name="clauseTableDataArray<%=innerCounter.intValue()+1%>"
																class="form-control textboxbluebold" size="13" MAXLENGTH="100"
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
																			class="form-control" size="15" MAXLENGTH="100"
																			value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>" />
																	</div>
														</logic:equal>
														<!--This Inner logic:notEqual check is for display the Second row other text box without radio buttons -->
														<logic:notEqual name="innerSize" value="0">
															<div class='col-sm-2'>&nbsp;&nbsp;
																<input type=text name="clauseTableDataArray<%=innerCounter.intValue()+1%>"
																class="form-control" size="15" MAXLENGTH="100"
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
			
			<div class="spacer20"></div>
			
			<div class="panel panel-default col-sm-offset-1" style="width:80%;align:center;">
	   			<div class="panel-heading"><h3 class="panel-title text-center">Engineering Data <font color="red" valign="top">*</font></h3></div>
	   			<div class="panel-body">
    				<div class="form-group">
						<label class="col-sm-4 control-label">Reference EDL Number(s)</label>
							<div>
							<logic:notEqual name="check" property="selectCGCFlag" value="Y">
								<logic:notEmpty name="check" property="refEDLNo">
									<div class="col-sm-2"><html:text styleClass="form-control" size="5" 
										property="refEDLNo" maxlength="20" value='<%=check.getRefEDLNo()[0]%>' /></div>
									<div class="col-sm-2"><html:text styleClass="form-control" size="5" 
										property="refEDLNo" maxlength="20" value='<%=check.getRefEDLNo()[1]%>' /></div>
									<div class="col-sm-2"><html:text styleClass="form-control" size="5" 
										property="refEDLNo" maxlength="20" value='<%=check.getRefEDLNo()[2]%>' /></div>
								</logic:notEmpty> 
								<logic:empty name="check" property="refEDLNo">
									<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="refEDLNo" maxlength="20" value="" /></div>
									<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="refEDLNo" maxlength="20" value="" /></div>
									<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="refEDLNo" maxlength="20" value="" /></div>
								</logic:empty>
							</logic:notEqual>
							<logic:equal name="check" property="selectCGCFlag" value="Y">
								<div class="col-sm-2"><html:text styleClass="form-control"  size="5" property="refEDLNo" maxlength="20" value="" /></div>
								<div class="col-sm-2"><html:text styleClass="form-control"  size="5" property="refEDLNo" maxlength="20" value="" /></div>
								<div class="col-sm-2"><html:text styleClass="form-control"  size="5" property="refEDLNo" maxlength="20" value="" /></div>
							</logic:equal>
						</div>
					</div>
    				<div class="form-group">
						<label class="col-sm-4 control-label">New EDL Number(s)</label>
							<div>
								<logic:notEqual name="check" property="selectCGCFlag" value="Y">
									<logic:notEmpty name="check" property="edlNo">
										<div class="col-sm-2"><html:text styleClass="form-control" size="5" 
											property="edlNo" maxlength="20" value='<%=check.getEdlNo()[0]%>' /></div>
										<div class="col-sm-2"><html:text styleClass="form-control" size="5" 
											property="edlNo" maxlength="20" value='<%=check.getEdlNo()[1]%>' /></div>
										<div class="col-sm-2"><html:text styleClass="form-control" size="5" 
											property="edlNo" maxlength="20" value='<%=check.getEdlNo()[2]%>' /></div>
									</logic:notEmpty>
									<logic:empty name="check" property="edlNo">
										<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="edlNo" maxlength="20" value="" /></div>
										<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="edlNo" maxlength="20" value="" /></div>
										<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="edlNo" maxlength="20" value="" /></div>
									</logic:empty>
								</logic:notEqual>
								<logic:equal name="check" property="selectCGCFlag" value="Y">
									<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="edlNo" maxlength="20" value="" /></div>
									<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="edlNo" maxlength="20" value="" /></div>
									<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="edlNo" maxlength="20" value="" /></div>
								</logic:equal>
							</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">Part of</label>
						<div>
							<div class="col-sm-2 form-inline">
								<div class="input-group">
									<span class="input-group-btn" data-toggle="tooltip" title="Load Part of">
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
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-4 control-label">DWO Number</label>
						<div class="col-sm-2">
							<logic:equal name="check" property="dwONumber" value="0">
								<html:text property="dwONumber" styleClass="form-control" size="20" maxlength="20" value="" />
							</logic:equal>
							<logic:greaterThan name="check" property="dwONumber" value="0">
								<html:text property="dwONumber" name="check" styleClass="form-control" size="20" maxlength="20" value="" />
							</logic:greaterThan>
						</div>
					</div>
						
					<div class="form-group">
						<label class="col-sm-4 control-label">Part Number</label>
						<div class="col-sm-2">
							<logic:equal name="check" property="partNumber" value="0">
									<html:text property="partNumber" styleClass="form-control" size="20" maxlength="8" value="" />	
							</logic:equal>
							<logic:greaterThan name="check"	property="partNumber" value="0">
									<html:text property="partNumber" name="check" styleClass="form-control" size="20" maxlength="8" value="" />	
							</logic:greaterThan>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-4 control-label">Price Book Number</label>
						<div class="col-sm-2">
							<logic:greaterThan name="check" property="priceBookNumber" value="0">
								<html:text property="priceBookNumber" name="check" styleId="priceBookNo"
										styleClass="form-control" size="20" maxlength="4">
								</html:text>
							</logic:greaterThan> 
							<logic:equal name="check" property="priceBookNumber" value="0">
									<html:text property="priceBookNumber" styleClass="form-control" styleId="priceBookNo"
										size="20" maxlength="4" value="">
									</html:text>
							</logic:equal>
						</div>
					</div>	
					
					<div class="form-group">
						<label class="col-sm-4 control-label">Engineering Data</label>
						<div class="col-sm-2">
							<html:select property="standardEquipmentSeqNo" styleClass="form-control" styleId="sltEngData">
								<option value="-1">---Select---</option>
								<logic:present name="ClauseRevForm" property="stdEquipmentList">
									<logic:iterate id="stdEquip" name="ClauseRevForm" property="stdEquipmentList" scope="request"
										type="com.EMD.LSDB.vo.common.StandardEquipVO">
										<logic:equal value="<%=String.valueOf(check.getStandardEquipmentSeqNo())%>"
											name="stdEquip" property="standardEquipmentSeqNo">
											<option value="<%=stdEquip.getStandardEquipmentSeqNo()%>" selected>
												<bean:write name="stdEquip" property="standardEquipmentDesc" /></option>
										</logic:equal>
										<logic:notEqual value="<%=String.valueOf(check.getStandardEquipmentSeqNo())%>"
											name="stdEquip" property="standardEquipmentSeqNo">
											<option value="<%=stdEquip.getStandardEquipmentSeqNo()%>">
												<bean:write name="stdEquip" property="standardEquipmentDesc" /></option>
										</logic:notEqual>
									</logic:iterate>
								</logic:present>
							</html:select>
						</div>
					</div>	
					
					<div class="row form-group">
						<label class="col-sm-4 control-label">Comments</label>
						<div class="col-sm-6">
							<html:textarea name="check" styleClass="form-control" property="comments" rows="3" cols="60" />
						</div>
					</div>
	    		</div>
	    	</div>
		
			<div class="panel panel-default col-sm-offset-1" style="width:80%;align:center;">
	   			<div class="panel-heading"><h3 class="panel-title text-center">Spec Supplement</h3></div>
	   			<div class="panel-body">
	    			<div class="row form-group">
	    				<label class="col-sm-4 control-label">Reason
	    				<logic:greaterEqual name="check" property="specStatusCode" value="3">
							<font color="red" valign="top">*</font>
						</logic:greaterEqual>
	    				</label>
						<div class="col-sm-6">
							<html:textarea styleClass="form-control" name="check" property="reason" rows="3" cols="60" />
						</div>
					</div>
		    	</div>
	    	</div>
	    
		
		<div class="form-group">
			<div class="col-sm-12 text-center">
				<button class="btn btn-primary" type='button' id="addClause" ONCLICK="javascript:fnCheckSpellnCont('fnAddClause')">Add</button>
				<button class="btn btn-primary" type='button' ONCLICK="javascript:ModifySpec()">Return to Modify Spec</button>
			</div>
		</div>	
		<div class="row">
			<div class="spacer50"></div>
		</div>		

			<!-- TableData  Display Part Ends Here
			
			<br>
			<TABLE BORDER=0 WIDTH="55%" ALIGN=CENTER>
				<TR>
					<TD WIDTH="25%">&nbsp;</TD> -->
					 <!-- Added for CR-109 -->
					   <html:hidden name="check" property="appendixExitsFlag" />
				  	 <!-- Added for CR-109 Ends Here--> 
				  	 
				  	 <%-- Added for CR_114--%>
					    <logic:notEqual name="check" property="appendixExitsFlag" value="N">
					    <html:hidden name="check" property="appendixImgSeqNo" />
					    <html:hidden name="check" property="mapAppendixImg" />
					    </logic:notEqual>
						<%-- Added for CR_114 Ends--%>
				  	 
				  	 
					<%-- Modified Add function to perform Spell Check for CR_104 
					</TR>
			</TABLE>--%>

		</logic:iterate>
	</logic:present>
	<%-- Added for CR_99 for displaying JUNK characters by RJ85495 --%>	
	<jsp:include page="/jsp/common/ClauseComparison.jsp" />
	<%-- CR_99 Ends here --%>
	<!-- Added for CR_92 moving spell checker pop up page into spellChecker.jsp -->
	<%--<%@ include file="/jsp/common/spellChecker.jsp" %> --%>
</html:form>
</div>