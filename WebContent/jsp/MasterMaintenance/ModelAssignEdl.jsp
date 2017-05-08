<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT type="text/javascript" SRC="js/ModelAssignEdl.js"></SCRIPT>
<script>
       $(document).ready(function() { 
       		var indexId = 0;
       		$(".compSeqNo").each(function( index ) {
			   $(this).prop("id","selindex"+indexId);
			   indexId=indexId+1;
			});
			$(".customselect").select2({theme:'bootstrap'});
       	});
</script>
<div class="container-fluid" id="mdlAssignEdl">
	<html:form action="/ModelAssignEdlAction" method="post">
		<h4 class="text-center"><bean:message key="Application.Screen.ModelAssignEdl" /></h4>
		<div class="spacer10"></div>
		<div class="errorlayerhide" id="errorlayer">
		</div>
		<logic:present name="ModelAssignEdlForm" property="messageID" scope="request">
			<bean:define id="messageID" name="ModelAssignEdlForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
		</logic:present>
		
		<logic:present name="ModelAssignEdlForm" property="compGroupList">
			<bean:size id="chargrpsize" name="ModelAssignEdlForm" property="compGroupList" />
		</logic:present>
		
		<logic:present name="ModelAssignEdlForm" property="charGrpCombntnList"
		scope="request">
			<bean:size name="ModelAssignEdlForm" id="chargrpcombnsize"
				property="charGrpCombntnList" />
		</logic:present>
		
		<logic:match name="ModelAssignEdlForm" property="method"
			value="fetchCharCompGrps">
			<logic:lessThan name="chargrpsize" value="1">
				<script> 
	              fnNoRecords();
	        	</script>
				
			</logic:lessThan>
		</logic:match>
		
		<div class="spacer10"></div>

		<div class="row form-inline">
			<div class="col-sm-12 text-center">
				<div class="form-group required-field">
					<label class="control-label">Specification Type</label>
						<html:select name="ModelAssignEdlForm" styleClass="form-control customselect" 
							property="specTypeNo" styleId="sltSpecType" 
							onchange="javascript:fetchModels()">
							<option selected value="-1">---Select---</option>
							<html:optionsCollection name="ModelAssignEdlForm"
								property="specTypeList" value="spectypeSeqno"
								label="spectypename" />
						</html:select>
				</div>
				
				<div class="form-group required-field">
					<label class="control-label">Model</label>
						<html:select name="ModelAssignEdlForm" styleClass="form-control customselect" styleId="sltModel"
							property="modelseqno">
							<option selected value="-1">---Select---</option>
							<logic:present name="ModelAssignEdlForm"
								property="listModels">
								<html:optionsCollection name="ModelAssignEdlForm"
									property="listModels" value="modelSeqNo" label="modelName" />
							</logic:present>
						</html:select>
				</div>
				
				<div class="form-group required-field">
					<label class="control-label">Clause Description</label>
						<A HREF="javascript:LoadClauseDesc()"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></A>
				</div>
				
				<div class="form-group text-left" id="clauseDesc">
					<logic:empty name="ModelAssignEdlForm" property="clauseDes">
					</logic:empty> 
					<logic:notEmpty name="ModelAssignEdlForm" property="clauseDes">
						<bean:define name="ModelAssignEdlForm" property="clauseDes" id="clauseDes" />
						<%=clauseDes %>
					</logic:notEmpty>
				</div>
				<div class="form-group">
				     <button class="btn btn-primary addbtn" type='button' id="searchButton" ONCLICK="javascript:fnFetchCharCompGrps()">Search</button>
				</div>
			</div>
		</div>		
		
			
		<logic:greaterThan name="chargrpsize" value="0">		
			<logic:present name="ModelAssignEdlForm" property="compGroupList">
				<hr>		
				<div class="row">
				<div class="col-sm-11 text-center"><h5 class="text-center"><strong>Characteristic Group Combination</strong></h5></div>
				</div>
				<div class="spacer10"></div>
				<table class="table table-bordered charGrpCombntn">
					    <thead>
					        <tr>
								<TH width="5%">Select</TH>
								<TH width="20%">Characteristic Group(s)</TH>
								<TH width="25%">Characteristic Component(s)</TH>
								<TH width="10%">EDL#</TH>
								<TH width="10%">RefEDL#</TH>
							</tr>	
						</thead>
					    <tbody class="text-center">	
							<!-- <TD CLASS="v-midalign" COLSPAN="3">
								<TABLE class="table-bordered"> -->
							    	<logic:iterate id="compGroupList" name="ModelAssignEdlForm"
											property="compGroupList" type="com.EMD.LSDB.vo.common.CompGroupVO"  indexId="claCount">
											<bean:size id="compSize" name="ModelAssignEdlForm" property="compGroupList" />
											<tr>
												<TD CLASS="v-midalign">											
													<input type="checkbox"
														name="charGrpCnt"
														value="<%=String.valueOf(compGroupList.getComponentGroupSeqNo())%>" 
														checked /></TD>
												<TD CLASS="v-midalign">
													<html:hidden name="ModelAssignEdlForm" property="componentGroupSeqNo" 
													value="<%=String.valueOf(compGroupList.getComponentGroupSeqNo())%>"/>
													<%=String.valueOf(compGroupList.getComponentGroupName())%></TD>
												<TD CLASS="v-midalign text-left form-group">
													<html:select name="ModelAssignEdlForm" property="compSeqNo" styleClass="customselect compSeqNo form-control">
														<option value="-1">---Select---</option>
															<logic:iterate id="compList" name="compGroupList"
																property="componentVO"
																type="com.EMD.LSDB.vo.common.ComponentVO" scope="page">
																<logic:equal name="compGroupList"
																	property="componentGroupSeqNo" 
																	value="<%=String.valueOf(compList.getComponentGroupSeqNo())%>">
																	<option value="<%=compList.getComponentSeqNo()%>"><bean:write
																		name="compList" property="componentName" /></option>
																</logic:equal>
															</logic:iterate>
													</html:select>
												</TD>
												<logic:equal name="claCount" value="0">
													<TD CLASS="v-midalign form-group" rowspan="<bean:write name="compSize"/>">
														<html:text styleClass="form-control" property="charstcEdlNo" size="7" maxlength="20" />
													</TD>
													<TD CLASS="v-midalign form-group" rowspan="<bean:write name="compSize"/>">
														<html:text styleClass="form-control" property="charstcRefEDLNo" size="7" maxlength="20" />
													</TD>	
											</logic:equal>
											</tr>
								</logic:iterate>
							<!-- </TABLE>
							</TD> -->
							
					</tbody>
				</table>
				
				<div class="spacer10"></div>
				
				<div class="form-group">
				  <div class="col-sm-12 text-center">
				       <button class="btn btn-primary mdfybtn" type='button' id="addButton" ONCLICK="javascript:fnAddCharGrpCombtn()">Add</button>
				   </div>   
			   	</div>
			   	<div class="spacer50"></div>	    
			</logic:present>	
		</logic:greaterThan>
		
		<logic:greaterThan name="chargrpcombnsize" value="0">
			<logic:present name="ModelAssignEdlForm" property="charGrpCombntnList">
				<div class="row">
				<div class="col-sm-11 text-center"><h5 class="text-center"><strong>Select Characteristic Group Combination</strong></h5></div>
				</div>
				<div class="spacer10"></div>
				<table class="table table-bordered">
					    <thead>
					        <tr>
								<TH width="5%">Select</TH>
								<TH width="25%">Characteristic Group(s)</TH>
								<TH width="25%">Characteristic Component(s)</TH>
								<TH width="20%">Clause Linking</TH>
								<TH width="10%">EDL#</TH>
								<TH width="10%">RefEDL#</TH>
							</tr>	
						</thead>
					    <tbody class="text-center">
					    	<logic:iterate id="charGrpCombntnList" name="ModelAssignEdlForm"
									property="charGrpCombntnList" type="com.EMD.LSDB.vo.common.ClauseVO" indexId="counter">
								<TR>
									<TD CLASS="v-midalign">
										<html:radio property="combntnSeqNo" value="<%=String.valueOf(charGrpCombntnList.getCombntnSeqNo())%>"/>
									</TD>
									<TD CLASS="v-midalign text-center" COLSPAN="2">		
									<TABLE class= "table table-bordered">
										<logic:iterate id="charGrpCompList" name="charGrpCombntnList"
																property="componentVO"
																type="com.EMD.LSDB.vo.common.ComponentVO" scope="page" >
											<TR>
											
												<TD CLASS="v-midalign" width="30%">
													<%=String.valueOf(charGrpCompList.getComponentGroupName())%></TD>
												<TD CLASS="v-midalign" width="30%">
													<%=String.valueOf(charGrpCompList.getComponentName())%></TD>
											
											</TR>
												
										</logic:iterate>			
									</TABLE>
									</TD>
									
									<TD>
										<div class="row">
											<div class="col-sm-10 text-left">Link Clause&nbsp; <A	HREF="javascript:fnLinkClauseDesc(<%=counter%>)">
													<span class="glyphicon glyphicon-search" aria-hidden="true"></span> </A>
											</div>											
										</div>
										<div class="row">
											<div class="spacer10"></div>
										</div>
										<div class="row">
											<DIV class="col-sm-10 col-sm-offset-1" id="linkClauseDesc<%=counter%>"></DIV>
										</div>
										<div class="row">
											<div class="spacer10"></div>
										</div>
										<div class="row">
											<DIV class="col-sm-10 text-left">
											<a id="link" href="javascript:fnViewLinkedClause(<%=String.valueOf(charGrpCombntnList.getNoOfLinks())%>,<%=String.valueOf(charGrpCombntnList.getCombntnSeqNo())%>,<%=counter%>)">View/UnLink</a> 
											</div>
										</div> 
										<div class="row">
											<div class="spacer10"></div>
										</div>
										<div class="form-group form-horizontal">
											<label class="col-sm-3 control-label text-nowrap"># of links</label>
											<div class="col-sm-4">
												<html:text styleClass="form-control" name="charGrpCombntnList" property="noOfLinks" styleId="numOfLinks" readonly="true"
												value ="<%=String.valueOf(charGrpCombntnList.getNoOfLinks())%>"/>							
											</div>
										</div> 
									</TD>
										
									<TD CLASS="v-midalign form-group">
										<html:text styleClass="form-control" name="charGrpCombntnList" property="charEdlNo" size="7" maxlength="20" />
									</TD>
									
									<TD CLASS="v-midalign form-group">
										<html:text styleClass="form-control" name="charGrpCombntnList" property="charRefEDLNo" size="7" maxlength="20" />
									</TD>	
								</TR>
									<bean:define id="row" value="<%=String.valueOf(counter.intValue()+1)%>"/>
									
					</logic:iterate>
				</tbody>
			</table>	 
			
			<div class="spacer10"></div>
				
				<div class="form-group">
				  <div class="col-sm-12 text-center">
				       <button class="btn btn-primary mdfybtn" type='button' id="saveButton" ONCLICK="javascript:fnSaveCharGrpCombtn()">Save/modify</button>
   				       <button class="btn btn-primary mdfybtn" type='button' id="deleteButton" ONCLICK="javascript:fnDeleteCharGrpCombtn()">Delete Characteristic Group Combination</button>
				   </div>   
			   	</div>
		   	<div class="spacer50"></div>	  
			   
			</logic:present>
		</logic:greaterThan>	
				
		
	<html:hidden property="subSectionSeqNo" />
	<html:hidden property="clauseSeqNo" />
	<html:hidden property="hdnModelName" />
	<html:hidden property="hdPreSelectedModel" />
	<html:hidden property="clauseDes" />
	<html:hidden property="prevSpecType" />	
	<html:hidden property="hdnCharEdlNo"/>
	<html:hidden property="hdnCharRefEdlNo"/>
	<input type="hidden" name="hdnClaChrstcFlag" value="Y"/>
	<input type="hidden" name="selClauseDesc" value="" />
	<html:hidden property="linkClaSeqNo"/>	
</html:form>		
</div>