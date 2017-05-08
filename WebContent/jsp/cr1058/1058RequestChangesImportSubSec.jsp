<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>

<logic:notPresent name="ChangeRequest1058Form" property="reviseClauseDetailsList" scope="request">	
<DIV id="divImportSubSection" >
	<div class="panel panel-info">
		<div class="panel-heading">
			<h4 class="panel-title"><strong>Import New Subsections from Model</strong></h4>
		</div>
		<div class="panel-body">
			<logic:present name="ChangeRequest1058Form" property="importSubSecList">
				<bean:size id="importSubSecListSize" name="ChangeRequest1058Form"
				property="importSubSecList" />
			</logic:present>
	
			<logic:greaterThan name="importSubSecListSize" value="0">
				<logic:iterate id="subSecClauseDetail" name="ChangeRequest1058Form" property="importSubSecList" scope="request"
							type="com.EMD.LSDB.vo.common.ChangeRequest1058VO" >
							<TABLE class="table table-bordered">
								<thead>
									<TR>
										<TD WIDTH="5%" class="text-center">
										<logic:notEqual name="subSecClauseDetail" property="subSecExistsFlag" value="Y">
											<input class="seqNoChkbox secSeqNum"
											type="checkbox" name="subSecSeqNo" id="subSecSeqNo"
											value="<bean:write name="subSecClauseDetail" property="subSectionSeqNo"/>" /><Strong> Select</Strong><br/>
											<input class="seqNoChkbox secChangeTypeSeqNo"
											type="checkbox" name="secChangeTypeSeqNo" 
											value="<bean:write name="subSecClauseDetail" property="changeTypeSeqNo"/>" style="display:none;" />  
										</logic:notEqual>	
										<logic:equal name="subSecClauseDetail" property="subSecExistsFlag" value="Y">
										<!-- <span class="glyphicon glyphicon-ok text-center" id="tick" aria-hidden="true"></span> -->
											<img src="images/tick.png"  id="tick" height="15" width="15" align="center"/>
										</logic:equal>
										</TD>
										<TH WIDTH="10%" CLASS=table_heading align="center" colspan="2">
											<bean:write name="subSecClauseDetail" property="subSecCode"/>.
											<bean:write name="subSecClauseDetail" property="subSecName"/>
										</TH>
									</TR>
									
									<TR>
										<TH WIDTH="10%" nowrap>Clause Number</TH>
										<TH WIDTH="50%" nowrap>Clause Description</TH>
										<TH WIDTH="20%" nowrap>Engineering Data</TH>
									</TR>
								</thead>
								<tbody>
									<logic:iterate id="claDetail" name="subSecClauseDetail" property="claDetailList"
												 type="com.EMD.LSDB.vo.common.ChangeRequest1058VO">		
										<TR>
											<TD>&nbsp;</TD>
											<TD>
											<%-- CR-128 - Updated for Pdf issue --%>
												<%if(String.valueOf(claDetail.getChangeFromClaDesc()).startsWith("<p>"))
												{%>
													<%=(String.valueOf(claDetail.getChangeFromClaDesc()))%>
													&nbsp;
												<%}else{ %>	
													<%=(String.valueOf(claDetail.getChangeFromClaDesc())).replaceAll("null","").replaceAll("\n","<br>")%>
													&nbsp;
												<%}%>
											<%-- CR-128 - Updated for Pdf issue Ends Here--%>
											</TD>
											<TD CLASS='text-center v-midalign'>
												
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
								</tbody>
							</TABLE>
					</logic:iterate>
					<div class="spacer10"></div>
				   	<div class="form-group">
				   		<label class="col-sm-1 col-sm-offset-3">Reason <font size="2" color="red">*</font></label>
			   			<div class="col-sm-5">
			   				<html:textarea styleId="reasonSubSec" property="reasonSubSec" rows="4" cols="80" styleClass="form-control" />
			   			</div>
				   	</div>
				   	<div class="form-group">
				   		<div class="col-sm-12 text-center">
				   			<button class="btn btn-primary" type="button" id="addImpSubSecChange" name="btnImpSubSecChange" onclick="javascript:fnSaveImpSubSecClaChanges(0,this)" >Add Subsection change(s) to 1058 Request</button>
				   			<button class="btn btn-primary" type="button" id="impSubSecChangeClose">Cancel Clause Change</button>
				   		</div>
				   	</div>
				</logic:greaterThan>
			</div>
		</div>
	</DIV>
</logic:notPresent>