<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>

<logic:notPresent name="ChangeRequest1058Form" property="reviseClauseDetailsList" scope="request">	
<DIV id="divAddMdlClaChanges" >
	<div class="panel panel-info">
		<div class="panel-heading">
			<h4 class="panel-title"><strong>Import Clause changes from Model</strong></h4>
		</div>
		<div class="panel-body">
			<logic:present name="ChangeRequest1058Form" property="mdlClaChangesList">
				<bean:size id="mdlClaChngListSize" name="ChangeRequest1058Form"
				property="mdlClaChangesList" />
			</logic:present>
	
			<logic:greaterThan name="mdlClaChngListSize" value="0">
				<logic:iterate id="mdlClaDetail" name="ChangeRequest1058Form" property="mdlClaChangesList" scope="request"
					type="com.EMD.LSDB.vo.common.ChangeRequest1058VO" >
							
					<TABLE class="table table-bordered">
						<thead>
							<TR>
								<TH WIDTH="5%" nowrap>Select</TH>
								<TH WIDTH="10%" nowrap>Description</TH>
								<TH WIDTH="10%">Specification Section</TH>
								<TH WIDTH="50%" nowrap>Clause Description</TH>
								<TH WIDTH="20%" nowrap>Engineering Data</TH>
							</TR>
						</thead>
						<tbody>
							<TR>
								<TD rowspan="2" class="text-center v-midalign">
									<logic:notEqual name="mdlClaDetail" property="claExistsFlag" value="Y">
										<input class="seqNoChkbox claSeqNum"
												type="checkbox" name="mdlClaSeqNo" id="mdlClaSeqNo"
												value="<bean:write name="mdlClaDetail" property="mdlClaSeqNo"/>" /><br/>
										<input class="seqNoChkbox changeTypeSeqNo"
												type="checkbox" name="changeTypeSeqNo" 
												value="<bean:write name="mdlClaDetail" property="changeTypeSeqNo"/>" style="display:none;" />
									</logic:notEqual>
									<logic:equal name="mdlClaDetail" property="claExistsFlag" value="Y">
										<!-- <span class="glyphicon glyphicon-ok text-center" id="tick" aria-hidden="true"></span> -->
										<img src="images/tick.png"  id="tick" height="15" width="15" align="center"/> <%-- Image added for CR-130 --%>
									</logic:equal>
								</TD>
								
								<TD class="text-center v-midalign"><strong>Change From</strong></TD>
								<TD class="text-center v-midalign">
									<bean:write name="mdlClaDetail" property="clauseNumber"/>&nbsp;
								</TD>
								<TD>
								<%-- CR-128 - Updated for Pdf issue --%>
									<%if(String.valueOf(mdlClaDetail.getChangeFromClaDesc()).startsWith("<p>"))
									{%>
										<%=(String.valueOf(mdlClaDetail.getChangeFromClaDesc()))%>
										&nbsp;
									<%}else{ %>	
										<%=(String.valueOf(mdlClaDetail.getChangeFromClaDesc())).replaceAll("null","").replaceAll("\n","<br>")%>
										&nbsp;
									<%}%>
								<%-- CR-128 - Updated for Pdf issue Ends Here--%>
								</TD>
								<TD CLASS='text-center v-midalign'>
									<logic:notEmpty name="mdlClaDetail" property="changeFromEdlNo">
									<bean:define name="mdlClaDetail" property="changeFromEdlNo" id="changeFromEdlNo"/>
									<%=(String.valueOf(changeFromEdlNo).replaceAll("\n","<br>"))%>
									<%--<bean:write name="repDetails" property="changeFromEdlNo"/>--%>
									</logic:notEmpty>
									
									<logic:notEmpty name="mdlClaDetail" property="changeFromRefEdlNo">
									<bean:define name="mdlClaDetail" property="changeFromRefEdlNo" id="changeFromRefEdlNo"/>
									<%=(String.valueOf(changeFromRefEdlNo).replaceAll("\n","<br>"))%>
									<%--<bean:write name="repDetails" property="changeFromRefEdlNo"/>--%>
									</logic:notEmpty >
									
									<logic:notEmpty name="mdlClaDetail" property="changeFromPartOfNo">
									<bean:define name="mdlClaDetail" property="changeFromPartOfNo" id="changeFromPartOfNo"/>
									<%=(String.valueOf(changeFromPartOfNo).replaceAll("\n","<br>"))%>
									<%--<bean:write name="repDetails" property="changeFromPartOfNo"/>--%>
									</logic:notEmpty>
									
									<logic:notEmpty name="mdlClaDetail" property="changeFromDwoNo">
									<bean:message key="screen.dwoNo"/>
									<bean:write name="mdlClaDetail" property="changeFromDwoNo"/><br/>
									</logic:notEmpty>
									
									<logic:notEmpty name="mdlClaDetail" property="changeFromPartNo">
									<bean:message key="screen.partNo"/>
									<bean:write name="mdlClaDetail" property="changeFromPartNo"/><br/>
									</logic:notEmpty>
									
									<logic:notEmpty name="mdlClaDetail" property="changeFromPriceBookNo">
									<bean:message key="screen.priceBookNo"/>
									<bean:write name="mdlClaDetail" property="changeFromPriceBookNo"/><br/>
									</logic:notEmpty>
									
									<logic:notEmpty name="mdlClaDetail" property="changeFromEquip">
									<bean:write name="mdlClaDetail" property="changeFromEquip"/><br/>
									</logic:notEmpty>
									
									<logic:notEmpty name="mdlClaDetail" property="changeFromEngiComments">
									<bean:write name="mdlClaDetail" property="changeFromEngiComments"/>
									</logic:notEmpty>
									&nbsp;
									</TD>
								</TR> 
								
								<TR>
									<TD class="text-center v-midalign"><strong>Change To</strong></TD>
									<TD CLASS="text-center v-midalign">
										<bean:write name="mdlClaDetail" property="clauseNumber"/>&nbsp;
									</TD>
									<TD>
									<%-- CR-128 - Updated for Pdf issue --%>
										<%if(String.valueOf(mdlClaDetail.getChangeToClaDesc()).startsWith("<p>"))
										{%>
											<%=(String.valueOf(mdlClaDetail.getChangeToClaDesc()))%>
										<%}else{ %>	
											<%=(String.valueOf(mdlClaDetail.getChangeToClaDesc())).replaceAll("null","").replaceAll("\n","<br>")%>
										<%}%>
									<%-- CR-128 - Updated for Pdf issue Ends Here--%>
									</TD>
									<TD CLASS='text-center v-midalign'>
										<logic:notEmpty name="mdlClaDetail" property="changeToEdlNo">
										<bean:define name="mdlClaDetail" property="changeToEdlNo" id="changeToEdlNo"/>
										<%=(String.valueOf(changeToEdlNo).replaceAll("\n","<br>"))%>
										<%--<bean:write name="repDetails" property="changeToEdlNo"/>--%>
										</logic:notEmpty>
										
										<logic:notEmpty name="mdlClaDetail" property="changeToRefEdlNo">
										<bean:define name="mdlClaDetail" property="changeToRefEdlNo" id="changeToRefEdlNo"/>
										<%=(String.valueOf(changeToRefEdlNo).replaceAll("\n","<br>"))%>
										<%--<bean:write name="repDetails" property="changeToRefEdlNo"/>--%>
										</logic:notEmpty >
										
										<logic:notEmpty name="mdlClaDetail" property="changeToPartOfNo">
										<bean:define name="mdlClaDetail" property="changeToPartOfNo" id="changeToPartOfNo"/>
										<%=(String.valueOf(changeToPartOfNo).replaceAll("\n","<br>"))%>
										<%--<bean:write name="repDetails" property="changeToPartOfNo"/>--%>
										</logic:notEmpty>
										
										<logic:notEmpty name="mdlClaDetail" property="changeToDwoNo">
										<bean:message key="screen.dwoNo"/>
										<bean:write name="mdlClaDetail" property="changeToDwoNo"/><br/>
										</logic:notEmpty>
										
										<logic:notEmpty name="mdlClaDetail" property="changeToPartNo">
										<bean:message key="screen.partNo"/>
										<bean:write name="mdlClaDetail" property="changeToPartNo"/><br/>
										</logic:notEmpty>
										
										<logic:notEmpty name="mdlClaDetail" property="changeToPriceBookNo">
										<bean:message key="screen.priceBookNo"/>
										<bean:write name="mdlClaDetail" property="changeToPriceBookNo"/><br/>
										</logic:notEmpty>
										
										<logic:notEmpty name="mdlClaDetail" property="changeToEquip">
										<bean:write name="mdlClaDetail" property="changeToEquip"/><br/>
										</logic:notEmpty>
										
										<logic:notEmpty name="mdlClaDetail" property="changeToEngiComments">
										<bean:write name="mdlClaDetail" property="changeToEngiComments"/>
										</logic:notEmpty>
										&nbsp;
									</TD>
								</TR>
							</tbody>
						</TABLE>
					</logic:iterate>
					<div class="spacer10"></div>
					<div class="form-group">
							<label class="col-sm-1 col-sm-offset-3">Reason <font size="2" color="red">*</font></label>
							<div class="col-sm-5">
								<html:textarea styleId="reasonMdl" property="reasonMdl" rows="4" cols="80" styleClass="form-control" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12 text-center">
							<button class="btn btn-primary" type='button' id="addMdlClaChngs" ONCLICK="javascript:fnSaveMdlClaChanges(0,this)">Add Clause Change(s) to 1058 Request</button>
							<button class="btn btn-primary" type='button' id="addMdlChangesClose">Cancel Clause Change</button>
						</div>
					</div>
				</logic:greaterThan>
			</div>
		</div>
</DIV>
<html:hidden styleId="clauseNumber" property="clauseNumber" />
</logic:notPresent>
