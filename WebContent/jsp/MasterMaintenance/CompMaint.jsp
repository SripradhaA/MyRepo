<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT type="text/javascript" SRC="js/component.js"></SCRIPT>
<script>
       $(document).ready(function() { 
       	$("#specTypeSeqNo").select2({theme:'bootstrap'});
       	$("#cmpGroup").select2({theme:'bootstrap'});
	   	$("#cmpGroupChr").select2({theme:'bootstrap'});
     })
</script>
<div class="container" width="100%">
   <html:form  action="/CompAction" method="post">
 		<h4 class="text-center"><bean:message key="Application.Screen.ComponentMaintenance" /></h4>
 		<p class="text-center"><mark>NOTE:<bean:message	key="DeleteComponent" /></mark></p>
		<div class="spacer10"></div>
		<div class="errorlayerhide" id="errorlayer">
		</div>
		<logic:present name="CompMaintForm" property="messageID" scope="request">
			<bean:define id="messageID" name="CompMaintForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
		</logic:present>
		
		<logic:present name="CompMaintForm" property="compList">
			<bean:size id="compListLen" name="CompMaintForm" property="compList" />
		</logic:present>
		
		<logic:match name="CompMaintForm" property="method" value="fetchComps">
			<logic:lessThan name="compListLen" value="1">
				<script> 
					fnNoRecords();
				</script>
			</logic:lessThan>
		</logic:match>
		
		 <div class="spacer10"></div>
		 
		 <div class="form-horizontal">
			<div class="form-group">
				<label class="col-sm-5 control-label">Specification Type</label>
				<div class="col-sm-1">
				<html:select name="CompMaintForm" property="specTypeNo" onchange="javascript:fnLoadComponent()" 
					styleClass="form-control" styleId="specTypeSeqNo">
					<option selected value="-1">---Select---</option>
					<logic:present name="CompMaintForm" property="specTypeList">
						<html:optionsCollection name="CompMaintForm"
							property="specTypeList" value="spectypeSeqno"
							label="spectypename" />
					</logic:present>
				</html:select>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-5 control-label">Component Group  Type</label>
				<div class="col-sm-6">
					<html:select styleId="cmpGroup"  styleClass="form-control"
								 property="compgrpCat" onchange="javascript:fnSearchCompGroups()">	
							<html:option value="0">All</html:option>
							<logic:present name="CompMaintForm"
								property="compGroupTypeList">
								<html:optionsCollection name="CompMaintForm"
									property="compGroupTypeList" value="compGrpTypeSeqNo"
									label="compGrpTypeName" />
							</logic:present>
					</html:select>
				</div>
			</div>
			
			<div class="form-group required-field">			
				<label class="col-sm-5 control-label">Component Group/Characteristic Group/Characterization</label>
				<div class="col-sm-6 form-inline">					
					<html:select styleClass="form-control" name="CompMaintForm" property="compGrpSeqNo" styleId="cmpGroupChr">
						<option selected value="-1">---Select---</option>
						<logic:present name="CompMaintForm" property="compGrpList">
							<html:optionsCollection name="CompMaintForm"
								property="compGrpList" value="componentGroupSeqNo"
								label="componentGroupName" />
						</logic:present>
					</html:select> 
					<button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnSearchComponent()">Search</button>
				</div>
			</div>
			
			
			<div class="form-group required-field">			
				<label class="col-sm-5 control-label">Component/Value</label>
				<div class="col-sm-2">					
					<html:text styleClass="form-control" property="component" maxlength="100" styleId="component"/>
				</div>
			</div>
			
			<div class="form-group required-field">			
				<label class="col-sm-5 control-label">Component Identifier</label>
				<div class="col-sm-2">					
					<html:text styleClass="form-control" property="compIdentifier" maxlength="100" styleId="compIdentifier"/>
				</div>
			</div>
			
			<div class="form-group required-field">			
				<label class="col-sm-5 control-label">Component Description</label>
				<div class="col-sm-2">					
					<html:textarea styleClass="form-control" property="compDesc" styleId="compgrpDesc" rows="2" cols="48" />
				</div>
			</div>
			
			<div class="form-group">			
				<label class="col-sm-5 control-label">Display in Spec for main feature section</label>
				<div class="col-sm-2">					
					<html:checkbox property="displaySpec" styleId="displaySpec" />
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-4">
					<button class="btn btn-primary" type='button' id="addButton" ONCLICK="javascript:fnAddComponent()"><bean:message key="screen.add" /></button>
				</div>
			</div>
		</div>	
		
		<logic:greaterThan name="compListLen" value="0">		
			
				<hr>		
				<div class="row">
					<div class="col-sm-11 text-center"><h5 class="text-center"><strong>Search Criteria</strong></h5></div>
				</div>
				<div class="spacer10"></div>
				
				<div class="row">
					<div class="col-sm-4 col-sm-offset-1 text-right"><strong>Specification Type</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="CompMaintForm" property="specTypeNo" value="-1">
							<bean:write name="CompMaintForm" property="hdnSelSpecType"/>
						</logic:notEqual>
						<logic:equal name="CompMaintForm" property="specTypeNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				<div class="spacer10"></div>
				<div class="row">
					<div class="col-sm-4 col-sm-offset-1 text-right"><strong>Component Group Type</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="CompMaintForm" property="compgrpCat" value="-1">
							<bean:write name="CompMaintForm" property="hdnCompGrpCat"/>
						</logic:notEqual>
						<logic:equal name="CompMaintForm" property="compgrpCat" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				<div class="spacer10"></div>
				<div class="row">
					<div class="col-sm-4 col-sm-offset-1 text-right"><strong>Component Group/Characteristic Group/Characterization</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="CompMaintForm" property="compGrpSeqNo" value="-1">
							<bean:write name="CompMaintForm" property="hdnSelCompGrp"/>
						</logic:notEqual>
						<logic:equal name="CompMaintForm" property="compGrpSeqNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				<div class="spacer10"></div>	
				
				<table class="table table-bordered table-hover">
					    <thead>
					        <tr>
								<TH>Select</TH>
								<TH>Component/Value</TH>
								<TH>Component Identifier</TH>
								<TH>Component Description</TH>
								<TH>Display in Spec for main feature section</TH>
							</tr>	
						</thead>
					    <tbody class="text-center">	
					    	<logic:iterate id="compMaintFormId" name="CompMaintForm"
								property="compList" type="com.EMD.LSDB.vo.common.ComponentVO">
								<TR>
									<TD CLASS="v-midalign"><html:radio
										value="<%=String.valueOf(compMaintFormId.getComponentSeqNo())%>"
										property="componentSeqNo" /></TD>
									<TD CLASS="v-midalign"><html:text
										name="compMaintFormId" property="componentName"
										styleClass="form-control" size="40" maxlength="100" /></TD>
									<TD CLASS="v-midalign"><html:text
										name="compMaintFormId" property="componentIdentifier"
										styleClass="form-control" size="40" maxlength="100" /></TD>
									<TD CLASS="v-midalign"><html:textarea styleClass="form-control"
										name="compMaintFormId" property="comments" rows="2" cols="40">
										<bean:write name="compMaintFormId" property="comments" />
									</html:textarea></TD>
									<TD CLASS="v-midalign"><logic:equal
										name="compMaintFormId" property="displayInSpecFlag" value="true">
										<html:checkbox name="compMaintFormId"
											property="displayInSpecFlag" />
									</logic:equal> <logic:notEqual name="compMaintFormId"
										property="displayInSpecFlag" value="true">
										<html:checkbox name="compMaintFormId"
											property="displayInSpecFlag" />
									</logic:notEqual></TD>
								</TR>
							</logic:iterate>
					    </tbody>
				</table>	
				<div class="spacer10"></div>
				
				<div class="form-group">
				  <div class="col-sm-11 text-center">
				       <button class="btn btn-primary mdfybtn" type='button' id="modifyButton" ONCLICK="javascript:fnModifyComponent()"><bean:message key="CompGroup.Modify" /></button>
				        <button class="btn btn-primary deleteButton" type='button' id="deleteButton" ONCLICK="javascript:fnDeleteComponent()"><bean:message key="screen.delete" /></button>
					</div>   
			   	</div>
				<div class="spacer50"></div>
	</logic:greaterThan>			
	<html:hidden property="hdnSelCompGrp" />
	<html:hidden property="hdnCompName" />
	<html:hidden property="hdnCompDesc" />
	<html:hidden property="hdnDispSpec" />
	<html:hidden property="hdnCompIdentifier" />
	<!-- Added For CR_81 by RR68151 -->
	<html:hidden property="hdnCompGrpCat" />
	<html:hidden property="hdnSelSpecType" />
	</html:form>
</div>		 