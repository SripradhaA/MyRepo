<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT LANGUAGE="JavaScript" SRC="js/Others/jquery.tablednd.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/OrderClaReArrange.js"></SCRIPT>
<style type="text/css">
#simplemodal-overlay {
background-color:#000;
}
#simplemodal-container {
background-color:#FFFFFF; 
border:6px solid ##BAB9C4; 
font-size: 9pt;
font-family:Arial;
width:650px; 
padding:12px;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
// Initialise the table;
$('#clauseDetails').tableDnD({
		onDragStart: function(table, row) {
			$(row).css('background-color', '#D8F2FF');
            $(row).contents('td').css({'border': '1px solid red', 'border-left': '1px dotted red', 'border-right': '1px dotted red'});
            $(row).contents('td:first').css('border-left', '1px solid red');
            $(row).contents('td:last').css('border-right', '1px solid red');
			},
        onDrop: function(table, row) {
        var clauseNo = $(row).contents('td:first').text();
          $(row).contents('td:first').html("");
          if(clauseNo!=""){
	          $("#clauseDetails td").filter(function() {
	          	 return $(this).text() == clauseNo;
	          	 }).parent().remove();
			  $("#clauseDetails").tableDnDUpdate();
		  	}
          var rows = table.rows;
          var result = "";
          for (var i=0; i<rows.length; i++) {
            if (result.length > 0) result += ",";
            var rowId = rows[i].id;
            if (rowId && rowId && table.tableDnDConfig && table.tableDnDConfig.serializeRegexp) {
                rowId = rowId.match(table.tableDnDConfig.serializeRegexp)[0];
            }
			
            result += rowId;
            }
          
          
          var rowIDList=result;
          document.forms["OrderSectionForm"].rowIndexList.value = rowIDList;
         }
    });
});
</script>
<div class="container" width="80%">	
	<h4 class="text-center">
		<bean:message key="Application.Screen.OrderClaReArrange" />
	</h4>
	<div class="row">
		<div class="spacer10"></div>
	</div>
	<p class="text-center"><mark>
		<bean:message key="Application.Screen.ReArrangeClauses.ReOrder" /><br/>
		The "Clause No" adjustment for marked clauses will take place after clicking the Save button.</mark>
	</p>
	<div class="row">
		<div class="spacer10"></div>
	</div>
	<div class="row">
		<div class="errorlayerhide" id="errorlayer"></div>
	</div>
	
	<!-- To display Server Side Validation Messages - Start -->
	<logic:present name="OrderSectionForm" property="messageID"
		scope="request">
			<bean:define id="messageID" name="OrderSectionForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
	</logic:present>
	<!-- To display Server Side Validation Messages - End -->

	<html:form action="/OrderClaReArrangeAction" styleClass="form-horizontal" method="post">
	
	<logic:iterate id="secdetail" name="OrderSectionForm"
		property="secDetail" type="com.EMD.LSDB.vo.common.SubSectionVO"
		scope="request" indexId="secCount">
		
		<logic:equal name="OrderSectionForm" property="orderSecSeqNo"
					value="<%=String.valueOf(secdetail.getSecSeqNo())%>">
			<logic:equal name="OrderSectionForm" property="subSecSeqNo"
			value="<%=String.valueOf(secdetail.getSubSecSeqNo())%>">			
	
			<logic:present name="secdetail" property="clauseGroup">
				<bean:size id="clauselen" name="secdetail" property="clauseGroup" />
			</logic:present>
			
			<logic:present name="OrderSectionForm" property="orderList"
				scope="request">
				<logic:iterate id="DisList" name="OrderSectionForm"
					property="orderList" type="com.EMD.LSDB.vo.common.OrderVO"
					scope="request">
					<input type="hidden" name="hdnorderno"
						value="<%=DisList.getOrderNo()%>">
					<input type="hidden" name="hdnmodseqno"
						value="<%=DisList.getModelSeqNo()%>">
					
					<div class="form-group">
						<label class="col-sm-offset-3 col-sm-2 control-label">Order Number</label>
						<div class="col-sm-4 form-control-static">
							<bean:write name="DisList" property="orderNo" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-offset-3 col-sm-2 control-label">Section</label>
						<div class="col-sm-4 form-control-static">
							<bean:write name="secdetail" property="secCode" />.
							<bean:write name="secdetail" property="secName" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-offset-3 col-sm-2 control-label">SubSection</label>
						<div class="col-sm-4 form-control-static">
							<bean:write name="secdetail" property="subSecCode" />&nbsp;
							<bean:write name="secdetail" property="subSecName" />
						</div>
					</div>
					
					
				</logic:iterate>
			</logic:present>
			</logic:equal>
		</logic:equal>
	</logic:iterate>
	<div class="row">
		<div class="spacer20"></div>
	</div>
	<logic:greaterThan name="clauselen" value="0">
		<logic:iterate id="secdetail" name="OrderSectionForm"
		property="secDetail" type="com.EMD.LSDB.vo.common.SubSectionVO"
		scope="request" indexId="secCount">
		
		<logic:equal name="OrderSectionForm" property="orderSecSeqNo"
					value="<%=String.valueOf(secdetail.getSecSeqNo())%>">
			
			<logic:equal name="OrderSectionForm" property="subSecSeqNo"
			value="<%=String.valueOf(secdetail.getSubSecSeqNo())%>">
			
			<TABLE class="table table-bordered" id="clauseDetails">
				<thead>
					<TR class="nodrop nodrag">
						<TH id="clauseNo" CLASS="text-center" WIDTH="10%" nowrap>Clause No</TH><!--Id Added for CR_121-->
						<TH CLASS="text-center" WIDTH="40%">Clause Description</TH>
						<TH CLASS="text-center" WIDTH="15%">Engineering Data</TH>
						<TH CLASS="text-center" WIDTH="15%">Component</TH>
						<TH CLASS="text-center" WIDTH="15%">Show Child Clauses</TH><%--Added for CR-127 --%>
					</TR>
				</thead>
				<tbody>
					<logic:iterate id="clause" name="secdetail" property="clauseGroup"
					type="com.EMD.LSDB.vo.common.ClauseVO" scope="page">
						<logic:equal name="clause" property="subSectionSeqNo"
						value="<%=String.valueOf(secdetail.getSubSecSeqNo())%>">
							<logic:equal name="clause" property="childFlag" value="N">						
							<TR height="35" id="<%=String.valueOf(clause.getClauseSeqNo())%>">
							
								<TD class="text-center">
									<logic:notEmpty name="clause" property="clauseNum">
										<bean:write name="clause" property="clauseNum" />
									</logic:notEmpty>		
								</TD>	
								
								<TD>
									<logic:equal name="clause" property="clauseDelFlag" value="Y">
									<logic:notEqual name="clause" property="deleteFlag" value="D">
										<bean:message key="Message.Reserved" />
									</logic:notEqual>
									</logic:equal>
									
									<logic:equal name="clause" property="clauseDelFlag" value="Y">
									<logic:equal name="clause" property="deleteFlag" value="D">
										<bean:message key="Message.Reserved" />
									</logic:equal>
									</logic:equal>
			
									<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
									<logic:notEqual name="clause" property="deleteFlag" value="D">
									 <bean:define
										name="clause" property="clauseDesc" id="clauseDesc" />
										<!-- CR-128 - Updated for Pdf issue -->
											<%String strClauseDesc =  String.valueOf(clauseDesc);
											if(strClauseDesc.startsWith("<p>")){%>
												<%=(String.valueOf(clauseDesc))%>
											<%}else{ %>	
												<%-- double space replacement with &nbsp is added to display the spaces as entered. This change is applicable for only modify spec --%>
										<%=(String.valueOf(clauseDesc)).replaceAll("  ","&nbsp;&nbsp;").replaceAll("\n","<br>")%>
											<%}%>
										<!-- CR-128 - Updated for Pdf issue Ends Here-->
													
										
									</logic:notEqual>
									</logic:notEqual>	
								
									<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
									<logic:notEqual name="clause" property="deleteFlag" value="D">
									<logic:empty name="clause" property="clauseDesc">
										<BR>
									</logic:empty>
									</logic:notEqual>
									</logic:notEqual>
									
									<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
									<logic:equal name="clause" property="deleteFlag" value="D">
									<logic:empty name="clause" property="clauseDesc">
										<BR>
									</logic:empty>
									</logic:equal>
									</logic:notEqual>
								</TD>
								
								<TD CLASS="text-center">						
								<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
								
									<logic:equal name="clause" property="selectCGCFlag" value="Y">
									<table>
										<tr>
											<td>
											<a href="javascript:fncharGrpCombntnList('<%=clause.getClauseSeqNo()%>')"
															Class="linkstyle1">
												<%=(clause.getCharEdlNo()==null? 
													(clause.getCharRefEDLNo()==null? "EDL Undeveloped":"(ref EDL "+clause.getCharRefEDLNo()+")")
															:(clause.getCharRefEDLNo()==null? "EDL "+clause.getCharEdlNo():
																"EDL "+clause.getCharEdlNo()+"<br>"+"(ref EDL "+clause.getCharRefEDLNo()+")"))%>					
													</a>																
											</td>
										</tr>
									</table>
									</logic:equal>	
								
									<logic:equal name="clause" property="selectCGCFlag" value="C">
									<logic:notEmpty name="clause" property="charEdlNo">
										<a href="javascript:fncharGrpCombntnList('<%=clause.getClauseSeqNo()%>')"
												Class="linkstyle1">
												<bean:message key="viewSpec.EDL" />&nbsp;
												<bean:write name="clause" property="charEdlNo"/>
										</a><br/>	
									</logic:notEmpty>
									<logic:notEmpty name="clause" property="charRefEDLNo">
										<a href="javascript:fncharGrpCombntnList('<%=clause.getClauseSeqNo()%>')"
												Class="linkstyle1">
												<bean:message key="viewSpec.refEDL.start" />&nbsp;
												<bean:write name="clause" property="charRefEDLNo"/>
												<bean:message key="viewSpec.refEDL.end" />
										</a><br/>	
									</logic:notEmpty>
									</logic:equal>
									
									<logic:notEmpty name="clause" property="edlNO">
										<logic:iterate id="edl" name="clause" property="edlNO">
											<bean:message key="viewSpec.EDL" />&nbsp;<bean:write name="edl" />
											<br>
										</logic:iterate>
									</logic:notEmpty>
			
									<logic:notEmpty	name="clause" property="refEDLNO">
										<logic:iterate id="refedl" name="clause" property="refEDLNO">
											<bean:message key="viewSpec.refEDL.start" />&nbsp;<bean:write
												name="refedl" />
												<bean:message key="viewSpec.refEDL.end" />
												
											<br>
										</logic:iterate>
										
									</logic:notEmpty> 
									
									 <logic:notEmpty name="clause" property="partOF">
										<logic:iterate id="partof" name="clause" property="partOF"
											type="com.EMD.LSDB.vo.common.SubSectionVO">
											<bean:message key="viewSpec.PartOf" />&nbsp;<bean:write
												name="partof" property="subSecCode" />&nbsp;
											<br>
										</logic:iterate>
									</logic:notEmpty> 
									<logic:notEmpty name="clause"
										property="dwONumber">
										<logic:notEqual name="clause" property="dwONumber" value="0">
											<bean:message key="viewSpec.DWO" />&nbsp;<bean:write
												name="clause" property="dwONumber" />
											<br>
										</logic:notEqual>
									</logic:notEmpty> <logic:notEmpty name="clause"
										property="partNumber">
										<logic:notEqual name="clause" property="partNumber" value="0">
											<bean:message key="viewSpec.PartNo" />&nbsp;<bean:write
												name="clause" property="partNumber" />
											<br>
										</logic:notEqual>
									</logic:notEmpty> <logic:notEmpty name="clause"
										property="standardEquipmentDesc">
										<bean:write name="clause" property="standardEquipmentDesc" />
										<br>
									</logic:notEmpty> <logic:notEmpty name="clause"
										property="engDataComment">
			
										<bean:define name="clause" property="engDataComment"
											id="engDataComment" />
										<%=engDataComment%>
										<br>
			
									</logic:notEmpty>&nbsp;
								</logic:notEqual>
								</TD>
								
								<TD class="text-center">
									<logic:notEmpty name="clause" property="compName">
										<!-- This part is for finding size of the components-->
										<logic:notEmpty name="clause" property="compName">
											<bean:size id="compSize" name="clause" property="compName"/>
											<bean:define id="compTotSize"
												value="<%=String.valueOf(compSize.intValue()-1)%>" />
										</logic:notEmpty> <logic:iterate id="compdesc" name="clause"
											property="compName" type="com.EMD.LSDB.vo.common.ComponentVO" indexId="counter">
											<bean:define id="count" name="counter" />
											<logic:notEqual name="counter" value="0"> ;<br>
											</logic:notEqual>
											<logic:equal name="compdesc" property="deletedFlag" value="Y">
											<font color="red"><bean:write name="compdesc" property="componentName" /></font>
											</logic:equal>
											<logic:notEqual name="compdesc" property="deletedFlag" value="Y">
											<logic:equal name="compdesc" property="compColorFlag" value="Y">
											<font color="#3D83C9">
											<b><bean:write name="compdesc" property="componentName" /></b>
											</font>
											</logic:equal>
											<logic:notEqual name="compdesc" property="compColorFlag" value="Y">
											<bean:write name="compdesc" property="componentName" />
											</logic:notEqual>
											<!-- Ends here -->
											</logic:notEqual>
			
										</logic:iterate>
									</logic:notEmpty>
									
									<logic:empty name="clause" property="compName">
										<BR>
									</logic:empty>
								</TD>
								
								<%--Added for CR-127--%>	
							<TD class="text-center v-midalign" >
								<logic:equal name="clause" property="subClaExistsFlag" value="Y">
									<a href="javascript:fnChildClauseRearranging(<%=String.valueOf(clause.getClaCode())%>,<%=String.valueOf(clause.getClauseSeqNo())%>)" data-toggle="tooltip" 
									title="View and Re-arrange Child Clauses"><img border=0 src="images/showChild.png" height="30"></a>
								</logic:equal>
								<logic:notEqual name="clause" property="subClaExistsFlag" value="Y">
										&nbsp;
								</logic:notEqual>
							</TD>
							<%--Added for CR-127 ends here--%>	
							
							</TR>					
							</logic:equal>
						</logic:equal>
					</logic:iterate>
				</tbody>
			</TABLE>
			</logic:equal>
		</logic:equal>
		</logic:iterate>
		
		<%--Added for CR-127 --%>
		<logic:iterate id="childClauses" name="OrderSectionForm"
				property="childClaList" scope="request"
				type="com.EMD.LSDB.vo.common.ClauseVO" indexId="clausecounter">
			<logic:notEqual name="parentClauseNo" value="<%=String.valueOf(childClauses.getParentClauseSeqNo())%>">	
				<logic:notEqual name="clausecounter" value="0">
				</TABLE>
						  </div>
							  <div class="modal-footer">
							  		<button class="btn btn-primary" type='button' ONCLICK="javascript:fnReArrangeClauses()">Save</button>
									<button class="btn btn-default" type='button'  data-dismiss="modal">Close</button>
							  </div>
						</div>
					</div>
				</div>				
				</logic:notEqual>
				
			<div class="modal fade modal-childCla" id="clausesChild<%=String.valueOf(childClauses.getParentClauseSeqNo())%>" tabindex="-1" role="dialog" aria-labelledby="childClauseModel">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					      	<h4 class="modal-title" id="childClauseModel">Rearrange Child Clause</h4>
					      </div>
					      <div class="modal-body">
					      		<p class="text-center"><mark><bean:message key="Application.Screen.ReArrangeClauses.ReOrder" /></mark></p>	
								<p class="text-center"><mark><bean:message key="Application.Screen.ReArrangeClauses.Save" /></mark></p>		
								<div class="spacer10"></div>			
								<TABLE class="table table-bordered" id="clauseDetails<%=String.valueOf(childClauses.getParentClauseSeqNo())%>">
									<thead>
										<TR class="nodrop nodrag">
											<TH CLASS="text-center" WIDTH="5%" nowrap>Clause No</TH>
											<TH CLASS="text-center" WIDTH="45%">Clause Description</TH>
											<TH CLASS="text-center" WIDTH="15%">Engineering Data</TH>
											<TH CLASS="text-center" WIDTH="15%">Component</TH>
										</TR>			
									</thead>	
									</logic:notEqual>
									<logic:equal name="childClauses" property="childFlag" value="Y">		
									<logic:notEqual name="clauseNo" value="<%=String.valueOf(childClauses.getClauseNum())%>">			
											<TR height="35" id="<%=String.valueOf(childClauses.getClauseSeqNo())%>">
											
												<TD class="text-center" data-error="clauseNumField">
													<logic:notEmpty name="childClauses" property="clauseNum">
														<bean:write name="childClauses" property="clauseNum" />
													</logic:notEmpty>		
												</TD>	
												
												<TD>
													<logic:equal name="childClauses" property="clauseDelFlag" value="Y">
													<logic:notEqual name="childClauses" property="deleteFlag" value="D">
														<bean:message key="Message.Reserved" />
													</logic:notEqual>
													</logic:equal>
													
													<logic:equal name="childClauses" property="clauseDelFlag" value="Y">
													<logic:equal name="childClauses" property="deleteFlag" value="D">
														<bean:message key="Message.Reserved" />
													</logic:equal>
													</logic:equal>
							
													<logic:notEqual name="childClauses" property="clauseDelFlag" value="Y">
													<logic:notEqual name="childClauses" property="deleteFlag" value="D">
													<DIV align="left" style="solid;height:60px;overflow:auto;">
													 <bean:define
														name="childClauses" property="clauseDesc" id="clauseDesc" />
															<!-- CR-128 - Updated for Pdf issue -->
															<%String strClauseDesc =  String.valueOf(clauseDesc);
															if(strClauseDesc.startsWith("<p>")){%>
																<%=(String.valueOf(clauseDesc))%>
															<%}else{ %>	
																<%-- double space replacement with &nbsp is added to display the spaces as entered. This change is applicable for only modify spec --%>
														<%=(String.valueOf(clauseDesc)).replaceAll("  ","&nbsp;&nbsp;").replaceAll("\n","<br>")%>
															<%}%>
														<!-- CR-128 - Updated for Pdf issue Ends Here-->
													</DIV>
													</logic:notEqual>
													</logic:notEqual>	
												
													<logic:notEqual name="childClauses" property="clauseDelFlag" value="Y">
													<logic:notEqual name="childClauses" property="deleteFlag" value="D">
													<logic:empty name="childClauses" property="clauseDesc">
														<BR>
													</logic:empty>
													</logic:notEqual>
													</logic:notEqual>
													
													<logic:notEqual name="childClauses" property="clauseDelFlag" value="Y">
													<logic:equal name="childClauses" property="deleteFlag" value="D">
													<logic:empty name="childClauses" property="clauseDesc">
														<BR>
													</logic:empty>
													</logic:equal>
													</logic:notEqual>
												</TD>
												
												<TD class="text-center">						
												<logic:notEqual name="childClauses" property="clauseDelFlag" value="Y">
												
													<logic:equal name="childClauses" property="selectCGCFlag" value="Y">
													<%--<table>
														<tr>
															<td align=center width="100%" bordercolor="blue">--%>
															<p class="lead">
															<a href="javascript:fncharGrpCombntnList('<%=childClauses.getClauseSeqNo()%>')"
																			Class="linkstyle1">
																<span class="label label-primary">
																<%=(childClauses.getCharEdlNo()==null? 
																	(childClauses.getCharRefEDLNo()==null? "EDL Undeveloped":"(ref EDL "+childClauses.getCharRefEDLNo()+")")
																			:(childClauses.getCharRefEDLNo()==null? "EDL "+childClauses.getCharEdlNo():
																				"EDL "+childClauses.getCharEdlNo()+"<br>"+"(ref EDL "+childClauses.getCharRefEDLNo()+")"))%>					
																</span>
															</a>
															</p>																
														<%-- 	</td>
														</tr>
													</table>--%>
													</logic:equal>	
												
													<logic:equal name="childClauses" property="selectCGCFlag" value="C">
													<logic:notEmpty name="childClauses" property="charEdlNo">
														<a href="javascript:fncharGrpCombntnList('<%=childClauses.getClauseSeqNo()%>')"
																Class="linkstyle1">
																<bean:message key="viewSpec.EDL" />&nbsp;
																<bean:write name="childClauses" property="charEdlNo"/>
														</a><br/>	
													</logic:notEmpty>
													<logic:notEmpty name="childClauses" property="charRefEDLNo">
														<a href="javascript:fncharGrpCombntnList('<%=childClauses.getClauseSeqNo()%>')"
																Class="linkstyle1">
																<bean:message key="viewSpec.refEDL.start" />&nbsp;
																<bean:write name="childClauses" property="charRefEDLNo"/>
																<bean:message key="viewSpec.refEDL.end" />
														</a><br/>	
													</logic:notEmpty>
													</logic:equal>
													
													<logic:notEmpty name="childClauses" property="edlNO">
														<logic:iterate id="edl" name="childClauses" property="edlNO">
															<bean:message key="viewSpec.EDL" />&nbsp;<bean:write name="edl" />
															<br>
														</logic:iterate>
													</logic:notEmpty>
							
													<logic:notEmpty	name="childClauses" property="refEDLNO">
														<logic:iterate id="refedl" name="childClauses" property="refEDLNO">
															<bean:message key="viewSpec.refEDL.start" />&nbsp;<bean:write
																name="refedl" />
																<bean:message key="viewSpec.refEDL.end" />
																
															<br>
														</logic:iterate>
														
													</logic:notEmpty> 
													
													 <logic:notEmpty name="childClauses" property="partOF">
														<logic:iterate id="partof" name="childClauses" property="partOF"
															type="com.EMD.LSDB.vo.common.SubSectionVO">
															<bean:message key="viewSpec.PartOf" />&nbsp;<bean:write
																name="partof" property="subSecCode" />&nbsp;
															<br>
														</logic:iterate>
													</logic:notEmpty> 
													<logic:notEmpty name="childClauses"
														property="dwONumber">
														<logic:notEqual name="childClauses" property="dwONumber" value="0">
															<bean:message key="viewSpec.DWO" />&nbsp;<bean:write
																name="childClauses" property="dwONumber" />
															<br>
														</logic:notEqual>
													</logic:notEmpty> <logic:notEmpty name="childClauses"
														property="partNumber">
														<logic:notEqual name="childClauses" property="partNumber" value="0">
															<bean:message key="viewSpec.PartNo" />&nbsp;<bean:write
																name="childClauses" property="partNumber" />
															<br>
														</logic:notEqual>
													</logic:notEmpty> <logic:notEmpty name="childClauses"
														property="standardEquipmentDesc">
														<bean:write name="childClauses" property="standardEquipmentDesc" />
														<br>
													</logic:notEmpty> <logic:notEmpty name="childClauses"
														property="engDataComment">
							
														<bean:define name="childClauses" property="engDataComment"
															id="engDataComment" />
														<%=engDataComment%>
														<br>
							
													</logic:notEmpty>&nbsp;
												</logic:notEqual>
												<logic:equal name="childClauses" property="clauseDelFlag" value="Y">
													&nbsp;
												</logic:equal>
												
												</TD>
												
												<TD CLASS="text-center">
													<logic:notEmpty name="childClauses" property="compName">
														<!-- This part is for finding size of the components-->
														<logic:notEmpty name="childClauses" property="compName">
															<bean:size id="compSize" name="childClauses" property="compName"/>
															<bean:define id="compTotSize"
																value="<%=String.valueOf(compSize.intValue()-1)%>" />
														</logic:notEmpty> <logic:iterate id="compdesc" name="childClauses"
															property="compName" type="com.EMD.LSDB.vo.common.ComponentVO" indexId="counter">
															<bean:define id="count" name="counter" />
															<logic:notEqual name="counter" value="0"> ;<br>
															</logic:notEqual>
															<logic:equal name="compdesc" property="deletedFlag" value="Y">
															<font color="red"><bean:write name="compdesc" property="componentName" /></font>
															</logic:equal>
															<logic:notEqual name="compdesc" property="deletedFlag" value="Y">
															<logic:equal name="compdesc" property="compColorFlag" value="Y">
															<font color="#3D83C9">
															<b><bean:write name="compdesc" property="componentName" /></b>
															</font>
															</logic:equal>
															<logic:notEqual name="compdesc" property="compColorFlag" value="Y">
															<bean:write name="compdesc" property="componentName" />
															</logic:notEqual>
															<!-- Ends here -->
															</logic:notEqual>
							
														</logic:iterate>
													</logic:notEmpty>
													
													<logic:empty name="childClauses" property="compName">
														<BR>
													</logic:empty>
												</TD>
												</TR>
									</logic:notEqual>
									</logic:equal>
									<bean:define id="parentClauseNo" name="childClauses" property="parentClauseSeqNo" />
								</logic:iterate>
								<logic:notEmpty name="OrderSectionForm" property="childClaList">
							</TABLE>
						  </div>
						  <div class="modal-footer">
						  		<button class="btn btn-primary" type='button' ONCLICK="javascript:fnReArrangeClauses()">Save</button>
								<button class="btn btn-default" type='button'  data-dismiss="modal">Close</button>
						  </div>
					</div>
				</div>
			</div>				
		</logic:notEmpty>
		<%--Added for CR-127 Ends here--%>			
		<div class="row">
			<div class="spacer10"></div>
		</div>	
		<div class="form-group">
			<div class="col-sm-12 text-center">
				<button class="btn btn-primary" type='button' ONCLICK="javascript:fnReArrangeClauses()">Save</button>
				<button class="btn btn-primary" type='button' ONCLICK="javascript:fnReloadClaforRearrange()">Reset Clause Selection</button>
				<button class="btn btn-primary" type='button' ONCLICK="javascript:fnModifySpec()">Return to Modify Spec</button>
			</div>
		</div>
		
	</logic:greaterThan>
	<logic:lessEqual name="clauselen" value="0">
			<script> 
              fnNoRecords();
        	</script>
        	
       	<div class="form-group">
			<div class="col-sm-12 text-center">
				<button class="btn btn-primary" type='button' id="close" ONCLICK="javascript:fnModifySpec()">Return to Modify Spec</button>
			</div>
		</div>
		
	</logic:lessEqual>
	<div class="row">
		<div class="spacer50"></div>
	</div>		
	<html:hidden property="rowIndexList"/>
	<html:hidden property="orderKey" />
	<html:hidden property="orderSecSeqNo" />
	<html:hidden property="subSecSeqNo" />
	</html:form>
	</div>