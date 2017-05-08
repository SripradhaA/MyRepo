<%-- Created for CR_109 --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<SCRIPT type="text/JavaScript" SRC="js/ClaByCompsReport.js"></SCRIPT>

 <script>
    $(document).ready(function() { 
    	$("#SltCompgrp").select2({theme:'bootstrap'}); 
    	$("#SltComp").select2({theme:'bootstrap'});
    	<%--$('#tbClaByCompReport').DataTable({
	    	paging:   false,
	        ordering: true,
	        info:     false,
	        order: [[ 0, 'asc' ]],
	    	columnDefs: [
                      { "orderable": false, "targets": [1,3,4] },
                      { "searchable": false, "targets": [1,3,4] },
                ]
	    });--%>
    })
</script>

<div class = "container" width="100">

<html:form action="/ClaByCompsAction" method="post">

	<div class="spacer10"></div>
		
	<h4 class ="text-center"><bean:message key="Application.Screen.ClaByCompsReport" /></h4>
	
	<div class="spacer10"></div>
		
	<p class="text-center"><mark>Note : Component names in <i>ITALICS</i> denotes that it is not a lead Component.</mark></p>
	<div class="spacer20"></div>
	<div class="row">
		<div class="errorlayerhide" id="errorlayer">
		</div>
	</div>
	
	
	<logic:present name="ClaByCompsForm" property="compList">
		<bean:size id="compListLen" name="ClaByCompsForm" property="compList" />
	</logic:present>
	<!-- To get Component List from Form - End -->

	<logic:present name="ClaByCompsForm" property="messageID" scope="request">

	<bean:define id="messageID" name="ClaByCompsForm" property="messageID"/>
        <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
        <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
	</logic:present>

	<logic:notEqual name="ClaByCompsForm" property="compGrpSeqNo" value="-1">
	<logic:match name="ClaByCompsForm" property="method" value="fetchComps">
		<logic:lessThan name="compListLen" value="1">
			
		<script> 
              fnNoRecords();
        </script>

		</logic:lessThan>
	</logic:match>
	</logic:notEqual>
	
	<logic:present name="ClaByCompsForm" property="claList">
		<bean:size id="claListLen" name="ClaByCompsForm" property="claList" />
	</logic:present>
	
	<logic:match name="ClaByCompsForm" property="method" value="fetchClauses">
		<logic:lessThan name="claListLen" value="1">
			
		<script> 
              fnNoRecords();
        </script>
		</logic:lessThan>
	</logic:match>
	
	<div class="form-horizontal">
	
		<div class="form-group required-field">
			<label class="col-sm-5 control-label">Component Group</label>
			<div class="col-sm-3">
				<html:select name="ClaByCompsForm" property="compGrpSeqNo" styleId="SltCompgrp"
						styleClass="form-control" onchange="javascript:fnLoadComponents()">
					<option selected value="-1">---Select---</option>
						<logic:present name="ClaByCompsForm" property="compGrpList">
							<html:optionsCollection name="ClaByCompsForm" property="compGrpList" value="componentGroupSeqNo"
								label="componentGroupName" />
						</logic:present>
				</html:select>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-5 control-label">Component</label>
			<div class="col-sm-3">
				<html:select name="ClaByCompsForm" property="componentSeqNo" styleId="SltComp"
					styleClass="form-control">
					<option selected value="-1">---Select---</option>
						<logic:present name="ClaByCompsForm" property="compList">
							<html:optionsCollection name="ClaByCompsForm"
								property="compList" value="componentSeqNo" label="componentName" />
						</logic:present>
				</html:select>	
			</div>
		</div>
	</div>
	
	<div class="col-sm-12 text-center">
		<div class="form-group">
              <button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnClaByCompsReport()"><bean:message key="screen.search" /></button>
       </div>
	</div>
	
	
	
	<logic:present name="ClaByCompsForm" property="claList">
	
	<div class="col-sm-12">
		<hr/>
	</div>
	
	<div>
		<div class="col-sm-11 text-center">
			<div class="form-group">
				<h5 class ="text-center"><strong>Search Criteria</strong></h5>
	       </div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-12">
				<div class="spacer10"></div>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Component Group</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<bean:write name="ClaByCompsForm"
						property="hdnSelCompGrp" />
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-12">
				<div class="spacer10"></div>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Component</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<bean:write name="ClaByCompsForm"
						property="hdnCompName" />
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-12">
				<div class="spacer20"></div>
			</div>
		</div>
		
	</div>
	
	<TABLE class="table table-bordered" id="tbClaByCompReport">
		<thead>
			<TR>
				<TH class="text-center" width="15%">
					<logic:equal name="ClaByCompsForm" property="sortByFlag" value="3">
						<a href="#tbClaByCompReport" class="text-black" style="text-decoration:none" onclick="javascript:fnSort(4)">Model Name<p class="pull-right text-primary"><span class="glyphicon glyphicon glyphicon-sort-by-attributes" aria-hidden="true"></span></p></a>
					</logic:equal>
					<logic:notEqual name="ClaByCompsForm" property="sortByFlag" value="3">
						<logic:equal name="ClaByCompsForm" property="sortByFlag" value="4">
							<a href="#tbClaByCompReport" class="text-black" style="text-decoration:none" onclick="javascript:fnSort(3)">Model Name<p class="pull-right text-primary"><span class="glyphicon glyphicon glyphicon-sort-by-attributes-alt" aria-hidden="true"></span></p></a>
						</logic:equal>
						<logic:notEqual name="ClaByCompsForm" property="sortByFlag" value="4">
							<a href="#tbClaByCompReport" class="text-black" style="text-decoration:none" onclick="javascript:fnSort(3)">Model Name<p class="pull-right text-primary"><span class="glyphicon glyphicon glyphicon-sort" aria-hidden="true"></span></p></a>
						</logic:notEqual>
					</logic:notEqual>
				</TH>
				
				<TH width="15%">Component Group</TH>
					
				<TH class="text-center" width="15%">
					<logic:equal name="ClaByCompsForm" property="sortByFlag" value="1">
						<a href="#tbClaByCompReport" class="text-black" style="text-decoration:none" onclick="javascript:fnSort(2)">Component<p class="pull-right text-primary"><span class="glyphicon glyphicon glyphicon-sort-by-attributes" aria-hidden="true"></span></p></a>
					</logic:equal>
					<logic:notEqual name="ClaByCompsForm" property="sortByFlag" value="1">
						<logic:equal name="ClaByCompsForm" property="sortByFlag" value="2">
							<a href="#tbClaByCompReport" class="text-black" style="text-decoration:none" onclick="javascript:fnSort(1)">Component<p class="pull-right text-primary"><span class="glyphicon glyphicon glyphicon-sort-by-attributes-alt" aria-hidden="true"></span></p></a>
						</logic:equal>
						<logic:notEqual name="ClaByCompsForm" property="sortByFlag" value="2">
							<a href="#tbClaByCompReport" class="text-black" style="text-decoration:none" onclick="javascript:fnSort(1)">Component<p class="pull-right text-primary"><span class="glyphicon glyphicon glyphicon-sort" aria-hidden="true"></span></p></a>
						</logic:notEqual>
					</logic:notEqual>
				</TH>
				
				<TH CLASS='table_heading' width="45%">Clause Description</TH>
				<TH CLASS='table_heading' width="10%">Engineering Data</TH>
				
			</TR>
		</thead>
		<tbody>
			
			<logic:iterate id="clauseListId" name="ClaByCompsForm" property="claList" 
				type="com.EMD.LSDB.vo.common.ClauseVO" indexId="counter">
				<bean:define id="color"	value="<%= String.valueOf((counter.intValue()) % 2) %>" />
				<logic:equal name="color" value="0">
					<TR bgcolor="#E9E9E9">
				</logic:equal>
				<logic:notEqual name="color" value="0">
					<TR bgcolor="#CDCDCD">
				</logic:notEqual>
				<bean:size id="componentListLen" name="clauseListId" property="componentList" />
				<TD rowspan="<%= componentListLen %>" CLASS="text-center v-midalign">
						<bean:write name="clauseListId" property="modelName"/>
				</TD>
				<logic:iterate id="compGroup" name="clauseListId" property="componentList"
						type="com.EMD.LSDB.vo.common.ComponentVO" indexId="count" length="1">	
						<bean:define id="row" value="<%= String.valueOf(count.intValue() % 2) %>"/>
						<logic:notEmpty name="compGroup" property="componentGroupName">
							<TD CLASS="text-center v-midalign">
								<logic:equal name="compGroup" property="validationFlag" value="Y">
									<font color="red">* </font>
								</logic:equal>									
								<logic:equal name="compGroup" property="compLeadFlag" value="Y">
								<em>
								</logic:equal>
									<bean:write name="compGroup" property="componentGroupName" />
								<logic:equal name="compGroup" property="compLeadFlag" value="Y">
								</em>
								</logic:equal>
							</TD>
						
							<TD CLASS="text-center v-midalign">									
								<logic:equal name="compGroup" property="compLeadFlag" value="Y">
								<em>
								</logic:equal>
									<bean:write name="compGroup" property="componentName" />
								<logic:equal name="compGroup" property="compLeadFlag" value="Y">
								</em>
								</logic:equal>
							</TD>
						</logic:notEmpty>
						<logic:empty name="compGroup" property="componentGroupName">
							<TD CLASS="text-center v-midalign">&nbsp;</TD>
							<TD CLASS="text-center v-midalign">&nbsp;</TD>
						</logic:empty>
					</logic:iterate>
					
				<TD rowspan="<%= componentListLen %>"><bean:define name="clauseListId"
											property="clauseDesc" id="clauseDesc" /> 
					<!-- CR-128 - Updated for Pdf issue -->
					<%String strClauseDesc =  String.valueOf(clauseDesc);
					if(strClauseDesc.startsWith("<p>")){%>
						<%=(String.valueOf(clauseDesc))%>
					<%}else{ %>	
						<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>							
					<%}%>
					<!-- CR-128 - Updated for Pdf issue Ends Here-->
					
				<logic:notEmpty name="clauseListId" property="tableArrayData1">&nbsp;
					<TABLE class="table table-bordered">
						<logic:iterate id="outter" name="clauseListId" property="tableArrayData1"
							indexId="counter">
							<bean:define id="row" name="counter" />
							<bean:define name="clauseListId" property="tableDataColSize" id="tableDataColSize" />
								<tr>
									<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
										<logic:equal name="row" value="0">
											<td valign="top" CLASS="text-center v-midalign"><b><font
												color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></font></b>
											</td>
										</logic:equal>
										<logic:notEqual name="row" value="0">
											<td valign="top" CLASS="text-center v-midalign"><%=(tabrow==null)? "&nbsp;":tabrow%>
											</td>
										</logic:notEqual>
									</logic:iterate>
								</tr>
						</logic:iterate>
					</TABLE>
					</logic:notEmpty>
				</TD>
										
				<TD rowspan="<%= componentListLen %>" CLASS="text-center v-midalign"	style="word-wrap: break-word;width:100;right:0">
					<logic:present name="clauseListId" property="edlNO">
						<logic:iterate id="engDataEdlNo" name="clauseListId" property="edlNO">
							<bean:message key="screen.edl" />
							<bean:write name="engDataEdlNo" />
							<br>
						</logic:iterate>
					</logic:present> 
					<logic:notPresent name="clauseListId" property="edlNO">
					&nbsp;
					</logic:notPresent>
					<logic:present name="clauseListId" property="refEDLNO">
						<logic:iterate id="engDataRefEdlNo" name="clauseListId"	property="refEDLNO">

							<bean:message key="screen.refEdl.start" />
							<bean:write name="engDataRefEdlNo" />
							<bean:message key="screen.refEdl.end" />
							<br>
						</logic:iterate>
					</logic:present> 
					<logic:present name="clauseListId" property="partOF">
						<logic:iterate id="engPartOfCd" name="clauseListId"	property="partOF">
							<logic:notEqual name="engPartOfCd" value="0">

								<bean:message key="screen.partOf" />
								<bean:write name="engPartOfCd" />
								<br>

							</logic:notEqual>

						</logic:iterate>

					</logic:present> <logic:present name="clauseListId"
						property="dwONumber">

						<logic:notEqual name="clauseListId" property="dwONumber" value="0">

							<bean:message key="screen.dwoNo" />
							<bean:write name="clauseListId" property="dwONumber" />
							<br>

						</logic:notEqual>

					</logic:present> <logic:present name="clauseListId"
						property="partNumber">

						<logic:notEqual name="clauseListId" property="partNumber" value="0">

							<bean:message key="screen.partNo" />
							<bean:write name="clauseListId" property="partNumber" />
							<br>

						</logic:notEqual>

					</logic:present>

					<logic:present name="clauseListId" property="priceBookNumber">

						<logic:notEqual name="clauseListId" property="priceBookNumber"
							value="0">

							<bean:message key="screen.priceBookNo" />&nbsp;<bean:write
								name="clauseListId" property="priceBookNumber" />
							<br>

						</logic:notEqual>

					</logic:present> <logic:present name="clauseListId"
						property="standardEquipmentDesc">

						<bean:write name="clauseListId" property="standardEquipmentDesc" />
						<br>

					</logic:present> <logic:present name="clauseListId"
						property="comments">
						<bean:define id="engDatCmnt" name="clauseListId"
							property="comments" />
						<%=engDatCmnt %>
						<br>
					</logic:present></TD>
										
				<logic:greaterThan name="componentListLen" value="1">
					<logic:equal name="color" value="0">
						</TR><TR bgcolor="#E9E9E9">
					</logic:equal>
					<logic:notEqual name="color" value="0">
						</TR><TR bgcolor="#CDCDCD">
					</logic:notEqual>
					<logic:iterate id="compGroup" name="clauseListId" property="componentList"
							type="com.EMD.LSDB.vo.common.ComponentVO" indexId="count" offset="1">	
							<logic:notEmpty name="compGroup" property="componentGroupName">
								<TD CLASS="text-center v-midalign">
									<logic:equal name="compGroup" property="validationFlag" value="Y">
										<font color="red">* </font>
									</logic:equal>									
									<logic:equal name="compGroup" property="compLeadFlag" value="Y">
									<em>
									</logic:equal>
										<bean:write name="compGroup" property="componentGroupName" />
									<logic:equal name="compGroup" property="compLeadFlag" value="Y">
									</em>
									</logic:equal>
								</TD>
							
								<TD CLASS="text-center v-midalign">									
									<logic:equal name="compGroup" property="compLeadFlag" value="Y">
									<em>
									</logic:equal>
										<bean:write name="compGroup" property="componentName" />
									<logic:equal name="compGroup" property="compLeadFlag" value="Y">
									</em>
									</logic:equal>
								</TD>
								<logic:notEqual name="componentListLen" value="<%= String.valueOf(count.intValue() + 1) %>">
									<logic:equal name="color" value="0">
										</TR><TR bgcolor="#E9E9E9">
									</logic:equal>
									<logic:notEqual name="color" value="0">
										</TR><TR bgcolor="#CDCDCD">
									</logic:notEqual>
								</logic:notEqual>
							</logic:notEmpty>
						</logic:iterate>
					</logic:greaterThan>
				</TR>
		</logic:iterate>
	</tbody>
</TABLE>
	
	<div class="spacer20"></div>
	
	<div class="col-sm-12 text-center">
		<div class="form-group">
              <button class="btn btn-primary" type='button' id="ExportToExcel" ONCLICK="javascript:fnClaByCompsReportExcel()">Export To Excel</button>
       </div>
	</div>
	
	
	</logic:present>
	<html:hidden property="hdnSelCompGrp" />
	<html:hidden property="hdnCompName" />
	<%-- Added for sorting the report - CR_109 Comments --%>
	<html:hidden property="sortByFlag" />
	<%-- CR_109 Comments Ends here--%>	 
	
<div class="spacer50"></div>
	 
</html:form>

</div>
