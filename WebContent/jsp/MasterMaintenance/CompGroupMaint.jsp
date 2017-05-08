<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT type="text/javascript" SRC="js/CompGroup.js"></SCRIPT>
<script>
       $(document).ready(function() { 
       	$("#specTypeSeqNo").select2({theme:'bootstrap'});
       	$("#cmpGroup").select2({theme:'bootstrap'});
       })
</script>
<div class="container" width="100%">
   <html:form  action="/CompGroupAction" method="post">
 		<h4 class="text-center"><bean:message key="Application.Screen.ComponentGroupMaintenance" /></h4>
 		<p class="text-center"><mark><bean:message key="Application.screen.CompGroup.Delete" /></mark></p>
		<div class="spacer10"></div>
		<div class="errorlayerhide" id="errorlayer">
		</div>
		<logic:present name="CompGroupMaintForm" property="messageID" scope="request">
			<bean:define id="messageID" name="CompGroupMaintForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
		</logic:present>
		
		<logic:present name="CompGroupMaintForm" property="compgroupList">
			<bean:size id="CompGrpListLen" name="CompGroupMaintForm" property="compgroupList" />
		</logic:present>
		
		<logic:match name="CompGroupMaintForm" property="method" value="fetchCompGroups">
			<logic:lessThan name="CompGrpListLen" value="1">
				<script> 
					fnNoRecords();
				</script>
			</logic:lessThan>
		</logic:match>
		
		 <div class="spacer10"></div>
		 
		 <div class="form-horizontal">
			<div class="form-group required-field">
				<label class="col-sm-5 control-label">Specification Type</label>
				<div class="col-sm-1">
				<html:select styleClass="form-control" name="CompGroupMaintForm"
					property="specTypeNo" styleId="specTypeSeqNo">
					<option selected value="-1">---Select---</option>
					<logic:present name="CompGroupMaintForm" property="specTypeList">
						<html:optionsCollection name="CompGroupMaintForm"
							property="specTypeList" value="spectypeSeqno" label="spectypename" />
					</logic:present>
				</html:select>
				</div>
			</div>
			
			<div class="form-group required-field">
				<label class="col-sm-5 control-label">Component Group/Characteristic Group/Characterization</label>
				<div class="col-sm-6 form-inline">
					<html:select styleClass="form-control" styleId="cmpGroup"
						property="compgrpCat">				
						<html:option value="-1">---Select---</html:option>
						<!-- Modified For CR_81 on 24-Dec-09 by RR68151 - Value changed to 0 -->
						<html:option value="0">All</html:option>
						<!--<html:option value="N">Component Group</html:option>
						<html:option value="Y">Characterization</html:option>-->
						<logic:present name="CompGroupMaintForm"
							property="compGroupTypeList">
							<html:optionsCollection name="CompGroupMaintForm"
								property="compGroupTypeList" value="compGrpTypeSeqNo"
								label="compGrpTypeName" />
						</logic:present>
						<!-- Modified For CR_81 on 24-Dec-09 by RR68151 - Ends here -->
					</html:select>
					<button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnSearchCompGroups()"><bean:message key="CompGroup.Search" /></button>
				</div>
			</div>
			
			<div class="form-group required-field">			
				<label class="col-sm-5 control-label">Component Group</label>
				<div class="col-sm-2">					
					<html:text styleClass="form-control" property="compgrpName" styleId="compgrpName"/>
				</div>
			</div>
			
			<div class="form-group required-field">			
				<label class="col-sm-5 control-label">Component Group Identifier</label>
				<div class="col-sm-2">					
					<html:text styleClass="form-control" property="compgrpIdentifier" styleId="compgrpIdentifier"/>
				</div>
			</div>
			
			<div class="form-group">			
				<label class="col-sm-5 control-label">Comments</label>
				<div class="col-sm-2">					
					<html:textarea styleClass="form-control" property="compgrpDesc" styleId="compgrpDesc" rows="2" cols="48" />
				</div>
			</div>
			
			<div class="form-group">			
				<label class="col-sm-5 control-label">Component Group Type<font size=2 color="red">*</font></label>
				<div class="col-sm-2">		
					<div class="radio">
						<label>
							<input type="radio" name="compGroupTypeSeqNo" value="1"><strong>Component Group</strong>
						</label>
					</div>
					<div class="radio">
						<label>
							<input type="radio" name="compGroupTypeSeqNo" value="2"><strong>Characteristic Group</strong>
						</label>
					</div>
					<div class="radio">
						<label>
							<input type="radio" name="compGroupTypeSeqNo" value="3"><strong>Characterization</strong>
						</label>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-4">
					<button class="btn btn-primary" type='button' id="addButton" ONCLICK="javascript:fnInsertCompGroup()"><bean:message key="screen.add" /></button>
				</div>
			</div>
		</div>	
		
		<logic:greaterThan name="CompGrpListLen" value="0">		
			
				<hr>		
				<div class="row">
				<div class="col-sm-11 text-center"><h5 class="text-center"><strong>Search critera</strong></h5></div>
				</div>
				<div class="spacer10"></div>
				
				
				<div class="row">
					<div class="col-sm-4 col-sm-offset-1 text-right"><strong>Specification Type</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="CompGroupMaintForm" property="specTypeNo" value="-1">
							<bean:write name="CompGroupMaintForm" property="hdnSelSpecType"/>
						</logic:notEqual>
						<logic:equal name="CompGroupMaintForm" property="specTypeNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				<div class="spacer10"></div>
				<div class="row">
					<div class="col-sm-4 col-sm-offset-1 text-right"><strong>Component Group/Characteristic Group/Characterization</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="CompGroupMaintForm" property="compgrpCat" value="-1">
							<bean:write name="CompGroupMaintForm" property="hdncompgrpCat"/>
						</logic:notEqual>
						<logic:equal name="CompGroupMaintForm" property="compgrpCat" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				
				<div class="spacer10"></div>	
				
				<table class="table table-bordered table-hover">
					    <thead>
					        <tr>
								<TH>Select</TH>
								<TH>Component Group</TH>
								<TH>Component Group Identifier</TH>
								<TH>Comments</TH>
								<TH>Component Group Type</TH>
							</tr>	
						</thead>
					    <tbody class="text-center">	
						   	<% int i = 1;%>
							<logic:iterate id="CompGroupDets" name="CompGroupMaintForm"
								property="compgroupList" scope="request"
								type="com.EMD.LSDB.vo.common.CompGroupVO">
								<tr>
									<TD class="v-midalign"><html:radio
										value="<%=String.valueOf(CompGroupDets.getComponentGroupSeqNo()) %>"
										styleClass="radcheck" property="componentGroupSeqNo" /></TD>
									<TD class="v-midalign"><html:text name="CompGroupDets"
										property="componentGroupName" styleClass="form-control"
										size="25" maxlength="100" /></TD>
									<TD class="v-midalign"><html:text name="CompGroupDets"
										property="componentGroupIdentifier"
										styleClass="form-control" size="25" maxlength="100" /></TD>
									<TD class="v-midalign"><html:textarea styleClass="form-control" 
										name="CompGroupDets" property="comments" rows="2" cols="30"/></TD>
									<TD class="v-midalign"><!-- Removed for CR_81 <logic:equal
										name="CompGroupDets" property="characterisationFlag" value="Y">
										Yes<input type="radio" name="charRadio<%=i%>" Class="radcheck"
											value="Y" checked>			
									    No<input type="radio" name="charRadio<%=i%>" Class="radcheck"
											value="N">
									</logic:equal> <logic:equal name="CompGroupDets"
										property="characterisationFlag" value="N">
										Yes<input type="radio" name="charRadio<%=i%>" Class="radcheck"
											value="Y">					
										No<input type="radio" name="charRadio<%=i%>" Class="radcheck"
											value="N" checked>
									</logic:equal> -->
									<!-- Added For CR_81 by RR68151 -->	
									<logic:equal name="CompGroupDets" property="compGrpTypeSeqNo" value="1">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Component Group<input type="radio" 
										name="charRadio<%=i%>" value="1" checked><br>				
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Characteristic Group<input type="radio" 
										name="charRadio<%=i%>" value="2"> <br>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Characterization<input type="radio" 
										name="charRadio<%=i%>" value="3">
									</logic:equal>
									<logic:equal name="CompGroupDets" property="compGrpTypeSeqNo" value="2">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Component Group<input type="radio" 
										name="charRadio<%=i%>" value="1"><br>				
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Characteristic Group<input type="radio" 
										name="charRadio<%=i%>" value="2" checked><br>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Characterization<input type="radio" 
										name="charRadio<%=i%>" value="3">
									</logic:equal>					
									<logic:equal  name="CompGroupDets" property="compGrpTypeSeqNo" value="3">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Component Group<input type="radio" 
										name="charRadio<%=i%>" value="1"><br>				
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Characteristic Group<input type="radio" 
										name="charRadio<%=i%>" value="2"><br>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Characterization<input type="radio" 
										name="charRadio<%=i%>" value="3" checked>
									</logic:equal>	
									<!-- Added For CR_81 by RR68151 - Ends here -->			
									</TD>
									<%--Commented for CR-127 --%>
									<%--<TD class="borderbottomrightlight"><logic:equal
										name="CompGroupDets" property="validFlag" value="Y">
										Yes<input type="radio" name="validRadio<%=i%>" Class="radcheck"
											value="Y" checked>			
									    No<input type="radio" name="validRadio<%=i%>" Class="radcheck"
											value="N">
									</logic:equal> <logic:equal name="CompGroupDets"
										property="validFlag" value="N">
										Yes<input type="radio" name="validRadio<%=i%>" Class="radcheck"
											value="Y">					
										No<input type="radio" name="validRadio<%=i%>" Class="radcheck"
											value="N" checked>
									</logic:equal></TD>--%>
									<%--Commented for CR-127 Ends here--%>
									<!-- Added for LSDB_CR-77 -->
									<%--Commented for CR_118 --%>
									<%-- 
									<TD CLASS="borderbottomrightlight">
										<logic:equal
												name="CompGroupDets" property="displayInCOCFlag" value="true">
												<html:checkbox name="CompGroupDets"
													property="displayInCOCFlag" />
											</logic:equal> <logic:notEqual name="CompGroupDets"
												property="displayInCOCFlag" value="true">
												<html:checkbox name="CompGroupDets"
													property="displayInCOCFlag" />
											</logic:notEqual>
										</TD>
										
									--%>	
									<%--Commented for CR_118 Ends--%>	
								</tr>
								<%
							 	i++;
							 %>
							</logic:iterate>
					    </tbody>
				</table>
				
				<div class="spacer10"></div>
				
				<div class="form-group">
				  <div class="col-sm-12 text-center">
				       <button class="btn btn-primary mdfybtn" type='button' id="modifyButton" ONCLICK="javascript:fnUpdateCompGroup()"><bean:message key="CompGroup.Modify" /></button>
				        <button class="btn btn-primary deleteButton" type='button' id="deleteButton" ONCLICK="javascript:fnDeleteCompGroup()"><bean:message key="screen.delete" /></button>
					</div>   
			   	</div>
				<div class="spacer50"></div>
					    
		</logic:greaterThan>	
		
		
	<!-- Hidden fields to hold modified CompGroup values starts-->

	<html:hidden property="hdnSelSpecType" />
	<html:hidden property="specTypeNo" />
	<html:hidden property="hdncompgrpName" />
	<html:hidden property="hdncompgrpDesc" />
	<html:hidden property="hdncharzFlag" />
	<html:hidden property="hdncharacterisationFlag" />
	<html:hidden property="hdncompgrpCat" />
	<html:hidden property="hdncompgrpIdentifier" />
	<html:hidden property="hdnValidFlag" />
	<html:hidden property="hdnDisplayInCOC" />
	<html:hidden property="hdncompGroupTypeSeqNo"/>
	<!-- Hidden fields to hold modified CompGroup values ends -->
	</html:form>
</div>	