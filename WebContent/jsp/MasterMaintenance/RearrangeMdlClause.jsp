<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<SCRIPT LANGUAGE="JavaScript" SRC="js/Others/jquery.tablednd.js"></SCRIPT>
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
          document.forms['ModelAddClauseForm'].rowIndexList.value = rowIDList;
         }
    });
});
</script>
<logic:equal name="ModelAddClauseForm" property="childFlag" value="N">
	<logic:present name="ModelAddClauseForm" property="parentClaList">
		<bean:size id="clasize" name="ModelAddClauseForm" property="parentClaList" />
	</logic:present>
	<logic:present name="ModelAddClauseForm" property="childClaList">
		<bean:size id="childclasize" name="ModelAddClauseForm" property="childClaList" />
	</logic:present>
</logic:equal>
<DIV id="RearrangeClause" style="display:block;" align="center">
	<logic:equal name="ModelAddClauseForm" property="childFlag" value="N">
		<logic:greaterEqual name="clasize" value="1">	
			<p class="text-center"><mark class="text-green"><bean:message key="Application.Screen.ReArrangeClauses.Collapse" /></mark></p>
	 		<p class="text-center"><mark><bean:message key="Application.Screen.ReArrangeClauses.ReOrder" /></mark></p>
	 		<p class="text-center"><mark class="text-red">Important !!</mark><mark><bean:message 
	 							key="Application.Screen.ReArrangeClauses.DropSameClauseNo" /></mark></p>
	 		<p class="text-center"><mark><bean:message key="Application.Screen.ReArrangeClauses.Save" /></mark></p>
	 		<div class="spacer30"></div>
	 		<div class="row">	
				<div class="col-sm-offset-1 col-sm-10" id="divRearrangeClause">
		 		<TABLE class="table table-bordered" id="clauseDetails">
					<thead>
						<TR class="nodrop nodrag">
							<TH id="clauseNo" CLASS="text-center text-nowrap" WIDTH="10%">Clause No</TH><!-- Id Added for CR_121 -->
							<TH CLASS="text-center" WIDTH="40%">Clause Description</TH>
							<TH CLASS="text-center" WIDTH="15%">Engineering Data</TH>
							<TH CLASS="text-center" WIDTH="15%">Component</TH>
							<TH CLASS="text-center text-nowrap" WIDTH="15%">Show Child Clauses</TH>
						</TR>
					</thead>
					<tbody>
						<logic:iterate id="compParentNoChild" name="ModelAddClauseForm"
									property="parentClaList" scope="request"
									type="com.EMD.LSDB.vo.common.ClauseVO" indexId="clausecount">			
							<logic:equal name="compParentNoChild" property="childFlag" value="N">	
							<TR height="35" id="<%=String.valueOf(compParentNoChild.getClauseSeqNo())%>">
							<TD class="text-center" valign="top"><logic:present name="compParentNoChild"
								property="clauseNum">
								<bean:write name="compParentNoChild" property="clauseNum" />
							</logic:present>
							</TD>
							<html:hidden name="compParentNoChild" property="versionNo" />
							<TD CLASS="v-midalign" valign="top">
							<%-- CR-128 - Updated for Pdf issue --%>
									<%if(String.valueOf(compParentNoChild.getClauseDesc()).startsWith("<p>"))
									{%>
										<%=(String.valueOf(compParentNoChild.getClauseDesc()))%>
									<%}else{ %>	
								 <%=(String.valueOf(compParentNoChild.getClauseDesc())).replaceAll("\n","<br>")%>
									<%}%>
							</TD>
							<TD CLASS="v-midalign text-center" valign="top">
							 <logic:present name="compParentNoChild"
										property="edlNO">
										<logic:iterate id="engDataEdlNo" name="compParentNoChild"
											property="edlNO">
											<bean:message key="screen.edl" />
											<bean:write name="engDataEdlNo" />
											<br>
										</logic:iterate>
									</logic:present> 
									<!--  CR 87 -->
									<logic:present name="compParentNoChild" property="refEDLNO">
	
										<logic:iterate id="engDataRefEdlNo" name="compParentNoChild"
											property="refEDLNO">
											<bean:message key="screen.refEdl.start" />
											<bean:write name="engDataRefEdlNo" />
											<bean:message key="screen.refEdl.end" />
											<br>
										</logic:iterate>
									</logic:present>
									<logic:present name="compParentNoChild"
										property="partOF">
										<logic:iterate id="engPartOfCd" name="compParentNoChild"
											property="partOF">
											<logic:notEqual name="engPartOfCd" value="0">
												<bean:message key="screen.partOf" />
												<bean:write name="engPartOfCd" />
												<br>
											</logic:notEqual>
										</logic:iterate>
									</logic:present> <logic:present name="compParentNoChild"
										property="dwONumber">
										<logic:notEqual name="compParentNoChild" property="dwONumber"
											value="0">
											<bean:message key="screen.dwoNo" />
											<bean:write name="compParentNoChild" property="dwONumber" />
											<br>
										</logic:notEqual>
									</logic:present> <logic:present name="compParentNoChild"
										property="partNumber">
										<logic:notEqual name="compParentNoChild" property="partNumber"
											value="0">
											<bean:message key="screen.partNo" />
											<bean:write name="compParentNoChild" property="partNumber" />
											<br>
										</logic:notEqual>
									</logic:present> <!--Price Book Number is added in the Engineering data Section, modified on 30-09-08 by ps5722-->
									<logic:present name="compParentNoChild" property="priceBookNumber">
										<logic:notEqual name="compParentNoChild" property="priceBookNumber"
											value="0">
											<bean:message key="screen.priceBookNo" />&nbsp; <bean:write
												name="compParentNoChild" property="priceBookNumber" />
											<br>
										</logic:notEqual>
									</logic:present> <logic:present name="compParentNoChild"
										property="standardEquipmentDesc">
										<bean:write name="compParentNoChild" property="standardEquipmentDesc" />
										<br>
									</logic:present> <logic:present name="compParentNoChild"
										property="engDataComment">
										<bean:define id="engDatCmnt" name="compParentNoChild"
											property="engDataComment" />
										<%=engDatCmnt %>
										<br>
									</logic:present>
							</TD>
							<logic:notEmpty name="compParentNoChild" property="compGroupVO">
								<TD class="v-midalign text-center" valign="top"><logic:iterate
									id="compList" name="compParentNoChild" property="compGroupVO"
									type="com.EMD.LSDB.vo.common.CompGroupVO" scope="page"
									indexId="compntIndex">
									<logic:present name="compList" property="compVO">
										<bean:define id="comp" name="compList" property="compVO" />
										<logic:notEqual name="compntIndex" value="0"> ;<br>
										</logic:notEqual>
										<bean:write name="comp" property="componentName" />
									</logic:present>
								</logic:iterate></TD>
							</logic:notEmpty>
							<logic:empty name="compParentNoChild" property="compGroupVO">
								<td class="v-midalign">&nbsp;</td>
							</logic:empty>
							<TD align="center">
								<logic:equal name="compParentNoChild" property="noOfChildClauses" value="0">
									&nbsp;
								</logic:equal>
								<logic:greaterThan name="compParentNoChild" property="noOfChildClauses" value="0">
									<a href="javascript:fnAllowSubClause(<%=String.valueOf(compParentNoChild.getClaHrchyLevel())%>,<%=String.valueOf(compParentNoChild.getClauseSeqNo())%>);"
									class="vtip" title="View and Re-arrange Child Clauses">
									<img src="images/showChild.png" height="32" width="32" border=0></a>
								</logic:greaterThan>
							</TD>
						</TR>
							</logic:equal>
						</logic:iterate>	
					</tbody>
				</TABLE>
				</div>
			</div>							
		</logic:greaterEqual>
		
		<html:hidden property="rowIndexList"/>
		<logic:greaterEqual name="clasize" value="1">
			<div class="form-group">
				<div class="col-sm-12 text-center">
					<button class="btn btn-primary" type='button' ONCLICK="javascript:fnReSetClauses()">Save</button>
					<button class="btn btn-primary" type='button' ONCLICK="javascript:fnRearrangeClausesPage('R')">Reset</button>
				</div>
			</div>
		</logic:greaterEqual>
			<%--CR_94 Child clause Rearranging --%>
	<logic:greaterEqual name="childclasize" value="1">
		<% int i =1;%>		
		<logic:iterate id="compChild" name="ModelAddClauseForm"  property="childClaList" scope="request"
				type="com.EMD.LSDB.vo.common.ClauseVO" indexId="clausecounter">					
		<logic:notEqual name="parentClauseNo" value="<%=String.valueOf(compChild.getParentClauseSeqNo())%>">	
			<div class="modal fade modal-childCla" id="clausesChild<%=String.valueOf(compChild.getParentClauseSeqNo())%>" style="display:none;">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content">
						 <div class="modal-header">		
							 <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	 				      	 <h4 class="modal-title" id="childClauseModel"><bean:message key="Application.Screen.ReArrangeChildClauses" /></h4>
						 </div>
			 	 		 <div class="modal-body">
			 	 		 	<p class="text-center"><mark><bean:message key="Application.Screen.ReArrangeClauses.ReOrder" /></mark></p>
					 		<p class="text-center"><mark><bean:message key="Application.Screen.ReArrangeClauses.Save" /></mark></p>
					 		<div class="spacer10"></div>
				 			<TABLE class="table table-bordered" id="clauseDetails<%=String.valueOf(compChild.getParentClauseSeqNo())%>">
							<thead>
								<TR class="nodrop nodrag">
									<TH CLASS="text-center" WIDTH="5%" nowrap>Clause No</TH>
									<TH CLASS="text-center" WIDTH="25%">Clause Description</TH>
									<TH CLASS="text-center" WIDTH="9%">Engineering Data</TH>
									<TH CLASS="text-center" WIDTH="8%">Component</TH>
								</TR>			
							</thead>
							</logic:notEqual>		
							<logic:equal name="compChild" property="childFlag" value="Y">		
							<logic:notEqual name="clauseNo" value="<%=String.valueOf(compChild.getClauseNum())%>">			
							<TR height="40" id="<%=String.valueOf(compChild.getClauseSeqNo())%>">
							<TD class="text-center">
							<logic:present name="compChild" property="clauseNum">
									<bean:write name="compChild" property="clauseNum" />
							</logic:present>
							</TD>
							<html:hidden name="compChild" property="versionNo" />
							<TD align="left">
								<%-- CR-128 - Updated for Pdf issue --%>
										<%if(String.valueOf(compChild.getClauseDesc()).startsWith("<p>"))
										{%>
											<%=(String.valueOf(compChild.getClauseDesc()))%>
										<%}else{ %>	
									 <%=(String.valueOf(compChild.getClauseDesc())).replaceAll("\n","<br>")%>
										<%}%>
								<!-- CR-128 - Updated for Pdf issue Ends Here-->
									 
							</TD>
							<TD CLASS="v-midalign" valign="top">
								 <logic:present name="compChild"
											property="edlNO">
											<logic:iterate id="engDataEdlNo" name="compChild"
												property="edlNO">
												<bean:message key="screen.edl" />
												<bean:write name="engDataEdlNo" />
												<br>
											</logic:iterate>
										</logic:present> 
										<!--  CR 87 -->
										<logic:present name="compChild" property="refEDLNO">
		
											<logic:iterate id="engDataRefEdlNo" name="compChild"
												property="refEDLNO">
												<bean:message key="screen.refEdl.start" />
												<bean:write name="engDataRefEdlNo" />
												<bean:message key="screen.refEdl.end" />
												<br>
											</logic:iterate>
										</logic:present>
										<logic:present name="compChild"
											property="partOF">
											<logic:iterate id="engPartOfCd" name="compChild"
												property="partOF">
												<logic:notEqual name="engPartOfCd" value="0">
													<bean:message key="screen.partOf" />
													<bean:write name="engPartOfCd" />
													<br>
												</logic:notEqual>
											</logic:iterate>
										</logic:present> <logic:present name="compChild"
											property="dwONumber">
											<logic:notEqual name="compChild" property="dwONumber"
												value="0">
												<bean:message key="screen.dwoNo" />
												<bean:write name="compChild" property="dwONumber" />
												<br>
											</logic:notEqual>
										</logic:present> <logic:present name="compChild"
											property="partNumber">
											<logic:notEqual name="compChild" property="partNumber"
												value="0">
												<bean:message key="screen.partNo" />
												<bean:write name="compChild" property="partNumber" />
												<br>
											</logic:notEqual>
										</logic:present> <!--Price Book Number is added in the Engineering data Section, modified on 30-09-08 by ps5722-->
										<logic:present name="compChild" property="priceBookNumber">
											<logic:notEqual name="compChild" property="priceBookNumber"
												value="0">
												<bean:message key="screen.priceBookNo" />&nbsp; <bean:write
													name="compChild" property="priceBookNumber" />
												<br>
											</logic:notEqual>
										</logic:present> <logic:present name="compChild"
											property="standardEquipmentDesc">
											<bean:write name="compChild" property="standardEquipmentDesc" />
											<br>
										</logic:present> <logic:present name="compChild"
											property="engDataComment">
											<bean:define id="engDatCmnt" name="compChild"
												property="engDataComment" />
											<%=engDatCmnt %>
											<br>
										</logic:present>
								</TD>
								<logic:notEmpty name="compChild" property="compGroupVO">
									<TD class="v-midalign" valign="top">
										<logic:iterate id="compList" name="compChild" property="compGroupVO"
											type="com.EMD.LSDB.vo.common.CompGroupVO" scope="page"
											indexId="compntIndex">
											<logic:present name="compList" property="compVO">
												<bean:define id="comp" name="compList" property="compVO" />
												<logic:notEqual name="compntIndex" value="0"> ;<br>
												</logic:notEqual>
												<bean:write name="comp" property="componentName" />
											</logic:present>
										</logic:iterate>
									</TD>
								</logic:notEmpty>
								<logic:empty name="compChild" property="compGroupVO">
									<td class="v-midalign">&nbsp;</td>
								</logic:empty>
							</TR>
							<bean:define id="clauseNo" name="compChild" property="clauseNum" />
							<bean:define id="parentClauseNo" name="compChild" property="parentClauseSeqNo" />
							</logic:notEqual>
							</logic:equal>
							<%if(i==compChild.getNoOfChildForParent()){ %>
							</TABLE>
							</div>
					  		<div class="modal-footer">		
								<logic:equal name="ModelAddClauseForm" property="childFlag" value="N">
									<logic:greaterEqual name="childclasize" value="1">
										<button class="btn btn-primary" type='button' ONCLICK="javascript:fnSaveChildClauses()">Save</button>
										<button class="btn btn-default" type='button'  data-dismiss="modal">Close</button>
									</logic:greaterEqual>
								</logic:equal>
							</div>
						</div>
					</div>
				</div>			
					<%i=1;}else i++;%>
		</logic:iterate>
	</logic:greaterEqual>		
	</logic:equal>
</DIV>	