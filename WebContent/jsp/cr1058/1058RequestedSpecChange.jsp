<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>

<% int i=0; int j=0;%><%-- J variable added for CR-130 --%>
<logic:present name="ChangeRequest1058Form" property="reqSpecChngClauseList" scope="request">


			<bean:size id="ReqSpecChangeSize" name="ChangeRequest1058Form" property="reqSpecChngClauseList" 
			scope="request"/> 
		
<logic:iterate id="ReqSpecChange" name="ChangeRequest1058Form" property="reqSpecChngClauseList"
				scope="request" indexId="eeid">
				
			
				
				<logic:iterate id="ReqSpecChangeinside" indexId="eid" name="ReqSpecChange" type="com.EMD.LSDB.vo.common.ClauseVO">
						<logic:equal name="eid" value="0">
							
								<% if(i==0){%>
								<div class="panel panel-info">
									<div class="panel-heading">
										<h4 class="panel-title"><strong>Requested Specification Change(s)</strong></h4>
									</div>
									
								<% i=1;
								}%>
							
							
						</logic:equal>
				</logic:iterate>
				<div class="panel-body">	
					<TABLE class="table table-bordered">
						<thead>
							<TR>
								<TH WIDTH="5%" CLASS=table_heading>Select</TH>
								<TH WIDTH="10%" CLASS=table_heading>Description</TH>
								<TH WIDTH="10%" CLASS=table_heading>Specification Section</TH>
								<TH WIDTH="40%" CLASS=table_heading>Clause Description</TH>
								<TH WIDTH="20%" CLASS=table_heading>Engineering Data</TH>
							</TR>
						</thead>
						<tbody>
						<TR>
							<logic:iterate id="ReqSpecChangeinside" indexId="eid" name="ReqSpecChange" type="com.EMD.LSDB.vo.common.ClauseVO">
								<logic:equal name="eid" value="0">
									<TD rowspan="3" class="text-center v-midalign">
									<%if(ReqSpecChangeinside.getClauseUpdatedToSpec()=="N" || "N".equalsIgnoreCase(ReqSpecChangeinside.getClauseUpdatedToSpec())){ %>									
									<INPUT TYPE="radio" CLASS="reqSpecChkRadio" name="reqSpecChkRadio" onclick=""
									value='<bean:write name="ReqSpecChangeinside" property="clauseChangeReqSeqNo"/>' />
									<%}else{ %>
										<img src="images/tick.png"  id="tick" height="15" width="15" align="center"/><%-- Image added for CR-130 --%>
									<%} %>
									</TD>
								</logic:equal>
							</logic:iterate>
							<logic:iterate id="ReqSpecChangeinside" indexId="eid" name="ReqSpecChange" type="com.EMD.LSDB.vo.common.ClauseVO">
								<logic:equal name="eid" value="1">
									<TD class="v-midalign"><strong>Change From</strong></TD>
									<TD CLASS="text-center v-midalign">
										<bean:write name="ReqSpecChangeinside" property="changeFromClaNo"/>&nbsp;
									</TD>
								<logic:notEmpty name="ReqSpecChangeinside" property="strPastClauseDesc" >
									<TD>
									<%-- CR-128 - Updated for Pdf issue --%>
										<%if(String.valueOf(ReqSpecChangeinside.getStrPastClauseDesc()).startsWith("<p>"))
										{%>
											<%=(String.valueOf(ReqSpecChangeinside.getStrPastClauseDesc()))%>
										<%}else{ %>	
											<%=(String.valueOf(ReqSpecChangeinside.getStrPastClauseDesc())).replaceAll("null","").replaceAll("\n","<br>")%>
										<%}%>
									<%-- CR-128 - Updated for Pdf issue Ends Here--%>
										<!-- Added for CR-127 starts here -->
										<logic:notEmpty name="ReqSpecChangeinside" property="tableArrayData1" >
											<A HREF="javascript:fnSeeTableData()">Click to see table data</A>
										</logic:notEmpty>
									</TD>
								</logic:notEmpty>
								<logic:empty name="ReqSpecChangeinside" property="strPastClauseDesc">
									<TD>&nbsp;</TD>
								</logic:empty>
								<!-- Added for CR-127 ends here -->
								<TD CLASS="text-center v-midalign">
									<%=(String.valueOf(ReqSpecChangeinside.getStrEngData())).replaceAll("null","").replaceAll("\n","<br>")%>
									&nbsp;
								</TD>
							</logic:equal>
						</logic:iterate>
					</TR>
				
					<TR>
						<TD CLASS="v-midalign"><strong>Change To</strong></TD>
						<logic:iterate id="ReqSpecChangeinside" indexId="eid" name="ReqSpecChange" type="com.EMD.LSDB.vo.common.ClauseVO">
							<logic:equal name="eid" value="2">
								<TD CLASS="text-center v-midalign">
									<bean:write name="ReqSpecChangeinside" property="changeToClaNo"/>&nbsp;
								</TD>
							<logic:notEmpty name="ReqSpecChangeinside" property="strPreClauseDesc" >
							<TD>
							<%-- CR-128 - Updated for Pdf issue --%>
								<%if(String.valueOf(ReqSpecChangeinside.getStrPreClauseDesc()).startsWith("<p>"))
								{%>
									<%=(String.valueOf(ReqSpecChangeinside.getStrPreClauseDesc()))%>
								<%}else{ %>	
									<%=(String.valueOf(ReqSpecChangeinside.getStrPreClauseDesc())).replaceAll("null","").replaceAll("\n","<br>")%>
								<%}%>
							<%-- CR-128 - Updated for Pdf issue Ends Here--%>
								<!-- Added for CR-127 starts here -->
								<logic:notEmpty name="ReqSpecChangeinside" property="tableArrayData1" >
									<A HREF="javascript:fnSeeTableData()">Click to see table data</A>
								</logic:notEmpty>
							</TD>
							</logic:notEmpty>
							<!-- Updated for CR-128 starts here -->
							<logic:empty name="ReqSpecChangeinside" property="strPastClauseDesc">
								<logic:empty name="ReqSpecChangeinside" property="changeToClaNo">
									<TD>&nbsp;</TD>
								</logic:empty>	
							</logic:empty>
							<logic:empty name="ReqSpecChangeinside" property="strPastClauseDesc">
								<logic:notEmpty name="ReqSpecChangeinside" property="changeToClaNo">
									<TD><bean:message key="Message.Reserved" /></TD>
								</logic:notEmpty>
							</logic:empty>
							<!-- Updated for CR-128 Ends here -->
							<!-- Added for CR-127 ends here -->
							<TD CLASS="text-center v-midalign">
							<%=(String.valueOf(ReqSpecChangeinside.getStrEngData())).replaceAll("null","").replaceAll("\n","<br>")%>
							&nbsp;
							</TD>
						</logic:equal>
					</logic:iterate>
				</TR>
				
				<TR>
					<td CLASS=headerbgcolor><strong>Reason</strong></td>
					<td colspan="3">
						<logic:iterate id="ReqSpecChangeinside" indexId="eid" name="ReqSpecChange" type="com.EMD.LSDB.vo.common.ClauseVO">
							<logic:equal name="eid" value="0">
								<bean:write name="ReqSpecChangeinside" property="strPreReason"/>
							</logic:equal>
						</logic:iterate>&nbsp;
					</td>
				</TR>
			</tbody>		
		</TABLE>
	</div>

</logic:iterate>
</logic:present>

	<!-- Added for CR-130 starts here -->		
		<bean:size id="ReqSpecChangeSize" name="ChangeRequest1058Form" property="reqSpecChngClauseList" 
			scope="request"/> 
		<bean:size id="importSubSecListSize" name="ChangeRequest1058Form"
			property="subSec1058List" /> 		
			<logic:present name="ChangeRequest1058Form" property="subSec1058List">	
				<logic:iterate id="subSecClauseDetail" name="ChangeRequest1058Form" property="subSec1058List" scope="request"
					type="com.EMD.LSDB.vo.common.ChangeRequest1058VO"  indexId="subSectionCount" >
		
               		<logic:equal name="ReqSpecChangeSize" value="0">
							<logic:equal name="subSectionCount" value="0">
									<% if(j==0){%>
									<div class="panel panel-info">
									<div class="panel-heading">
										<h4 class="panel-title"><strong>Requested Specification Change(s)</strong></h4>
									</div>
									<% j=1;
									}%>
							</logic:equal>
					</logic:equal>
					<div class="panel-body">	
						<TABLE class="table table-bordered">
							<thead>
								<TR>
									<TD WIDTH="5%" class="text-center">
									<logic:notEqual name="subSecClauseDetail" property="updatedToSpecFlag" value="Y">
										<input TYPE="radio"  CLASS="radcheck2 reqSpecChkRadio" name="reqSpecChkRadio" id="reqSubSecChkRadio" entity="subSec" onclick=""
										value='<bean:write name="subSecClauseDetail" property="subSecChngReqSeqNo"/>' /><b>Select</b>
									</logic:notEqual>	
									<logic:equal name="subSecClauseDetail" property="updatedToSpecFlag" value="Y">
										<img src="images/tick.png"  id="tick" height="15" width="15" align="center"/>
									</logic:equal>	
									</TD>
									<TH CLASS=table_heading align="center" colspan="4">
										<bean:write name="subSecClauseDetail" property="subSecCode"/>.
										<bean:write name="subSecClauseDetail" property="subSecName"/>
									</TH>
								</TR>
								
								<TR>
									<TH WIDTH="10%" CLASS=table_heading nowrap colspan="2">Clause Number</TH>
									<TH WIDTH="50%" CLASS=table_heading nowrap colspan="2">Clause Description</TH>
									<TH WIDTH="20%" CLASS=table_heading nowrap>Engineering Data</TH>
								</TR>
							</thead>
							<tbody>
								<logic:iterate id="claDetail" name="subSecClauseDetail" property="claDetailList"
											 type="com.EMD.LSDB.vo.common.ChangeRequest1058VO">				
									<TR>
									<TD CLASS="text-center v-midalign" colspan="2">
										<bean:write name="claDetail" property="clauseNumber"/>
									</TD>
									<TD colspan="2">
										<%if(String.valueOf(claDetail.getChangeFromClaDesc()).startsWith("<p>"))
										{%>
											<%=(String.valueOf(claDetail.getChangeFromClaDesc()))%>
											&nbsp;
										<%}else{ %>	
											<%=(String.valueOf(claDetail.getChangeFromClaDesc())).replaceAll("null","").replaceAll("\n","<br>")%>
											&nbsp;
										<%}%>
									<logic:notEmpty name="claDetail" property="tableArrayData1" >
											<A HREF="javascript:fnSeeTableData()">Click to see table data</A>
									</logic:notEmpty>
									</TD>
									<TD class="text-center v-midalign">
										
										<logic:notEmpty name="claDetail" property="changeFromEdlNo">
										<bean:define name="claDetail" property="changeFromEdlNo" id="changeFromEdlNo"/>
										<%=(String.valueOf(changeFromEdlNo).replaceAll("\n","<br>"))%>
										<%--<bean:write name="repDetails" property="changeFromEdlNo"/>--%>
										</logic:notEmpty>
										
										<logic:notEmpty name="claDetail" property="changeFromRefEdlNo">
										<bean:define name="claDetail" property="changeFromRefEdlNo" id="changeFromRefEdlNo"/>
										<%=(String.valueOf(changeFromRefEdlNo).replaceAll("\n","<br>"))%>
										<%--<bean:write name="repDetails" property="changeFromRefEdlNo"/>--%>
										</logic:notEmpty >
										
										<logic:notEmpty name="claDetail" property="changeFromPartOfNo">
										<bean:define name="claDetail" property="changeFromPartOfNo" id="changeFromPartOfNo"/>
										<%=(String.valueOf(changeFromPartOfNo).replaceAll("\n","<br>"))%>
										<%--<bean:write name="repDetails" property="changeFromPartOfNo"/>--%>
										</logic:notEmpty>
									
									<logic:notEmpty name="claDetail" property="changeFromDwoNo">
									<bean:message key="screen.dwoNo"/>
									<bean:write name="claDetail" property="changeFromDwoNo"/><br/>
									</logic:notEmpty>
									
									<logic:notEmpty name="claDetail" property="changeFromPartNo">
									<bean:message key="screen.partNo"/>
									<bean:write name="claDetail" property="changeFromPartNo"/><br/>
									</logic:notEmpty>
									
									<logic:notEmpty name="claDetail" property="changeFromPriceBookNo">
									<bean:message key="screen.priceBookNo"/>
									<bean:write name="claDetail" property="changeFromPriceBookNo"/><br/>
									</logic:notEmpty>
									
									<logic:notEmpty name="claDetail" property="changeFromEquip">
									<bean:write name="claDetail" property="changeFromEquip"/><br/>
									</logic:notEmpty>
									
									<logic:notEmpty name="claDetail" property="changeFromEngiComments">
									<bean:write name="claDetail" property="changeFromEngiComments"/>
									</logic:notEmpty>
									&nbsp;
									</TD>
									</TR> 
								</logic:iterate>
								<TR>
									<TH WIDTH="10%" colspan="2">Reason</TH>
									<TD colspan="3">
										<logic:notEmpty name="subSecClauseDetail" property="reasonSubSec">
											<bean:write name="subSecClauseDetail" property="reasonSubSec"/>
										</logic:notEmpty>&nbsp;
									</TD>
								</TR>
							</tbody>
						</TABLE>
					</div>
		</logic:iterate>	
	</logic:present>
	
<!-- Added for CR-130 ends here -->

<% if(i!=0 || j != 0){%>

	<div class="form-group">
         <div class="col-sm-12 text-center">
            <button class="btn btn-primary btnSpecChngAction" type='button' ONCLICK="javascript:fnDeleteSelectedClause()">Delete Selected Clause</button>
            <button class="btn btn-primary btnSpecChngAction" type='button' id="revCla" ONCLICK="javascript:fnReviseSelectedClause()">Revise Selected Clause</button>
            <button class="btn btn-primary btnSpecChngAction" type='button' ONCLICK="javascript:fnUpdateClausetoSpec()">Update selected clause to Order Spec</button>
            <button class="btn btn-primary btnSpecChngAction" type='button' ONCLICK="javascript:fnUpdateAllClausestoSpec()">Update all clauses to Order Spec</button>
        </div>
   	</div>

<!-- <table  WIDTH="100%">
				
				<TR>
				<TD colspan=5 align="center">
					<INPUT CLASS="buttonStyle btnSpecChngAction" TYPE="button" name="btnAdd" value="Delete Selected Clause" style="font-family:
'Arial',Roman,serif;" onclick="javascript:fnDeleteSelectedClause()">	
					<INPUT CLASS="buttonStyle btnSpecChngAction" TYPE="button" name="btnAdd" value="Revise Selected Clause" id="revCla" style="font-family:
'Arial',Roman,serif;" onclick="javascript:fnReviseSelectedClause()">
					<INPUT CLASS="buttonStyle btnSpecChngAction" TYPE="button" name="btnAdd" value="Update selected clause to Order Spec" style="font-family:
'Arial',Roman,serif;" onclick="javascript:fnUpdateClausetoSpec()"/>
					<INPUT CLASS="buttonStyle btnSpecChngAction" TYPE="button" name="btnAdd" value="Update all clauses to Order Spec" style="font-family:
'Arial',Roman,serif;" onclick="javascript:fnUpdateAllClausestoSpec()"/>
				</TD>
			</TR>
			<TR><TD ALIGN="center" COLSPAN="5">&nbsp;</TD></TR>
		</table> -->
		
		</div>
	</div>
	<%} %>
