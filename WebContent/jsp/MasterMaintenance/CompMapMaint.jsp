<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT type="text/javascript" SRC="js/componentmap.js"></SCRIPT>
<script>
       $(document).ready(function() { 
       	$("#specTypeSeqNo").select2({theme:'bootstrap'});
       	$("#sltModel").select2({theme:'bootstrap'});
	   	$("#sltSection").select2({theme:'bootstrap'});
	   	$("#sltSubSection").select2({theme:'bootstrap'});
 	   	$("#sltCompGrpType").select2({theme:'bootstrap'});
	   	$("#sltComponent").select2({theme:'bootstrap'});
     })
</script>
<div class="container" width="100%">
   <html:form  action="/CompMapAction" method="post">
 		<h4 class="text-center"><bean:message key="Application.Screen.ComponentMappingMaintenance" /></h4>
 		<div class="spacer10"></div>
		<div class="errorlayerhide" id="errorlayer">
		</div>
		<logic:present name="CompMapMaintForm" property="messageID" scope="request">
			<bean:define id="messageID" name="CompMapMaintForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
		</logic:present>
		
		<logic:present name="CompMapMaintForm" property="compList">
			<bean:size id="compListLen" name="CompMapMaintForm" property="compList" />
		</logic:present>
		
		<logic:match name="CompMapMaintForm" property="method" value="fetchCompMap">
			<logic:lessThan name="compListLen" value="1">
				<script> 
					fnNoRecords();
				</script>
			</logic:lessThan>
		</logic:match>
		
		<div class="form-horizontal">
			<div class="form-group required-field">
					<label class="col-sm-5 control-label">Specification Type</label>
					<div class="col-sm-2">
					<html:select name="CompMapMaintForm"
						property="specTypeNo" onchange="javascript:fnLoadModels()"
						styleId="specTypeSeqNo" styleClass="form-control">
						<option selected value="-1">---Select---</option>
						<logic:present name="CompMapMaintForm" property="specTypeList">
							<html:optionsCollection name="CompMapMaintForm"
								property="specTypeList" value="spectypeSeqno"
								label="spectypename" />
						</logic:present>
					</html:select>
					</div>
			</div>
			<div class="form-group required-field">
					<label class="col-sm-5 control-label">Model</label>
					<div class="col-sm-4">
					<html:select name="CompMapMaintForm"
						property="modelSeqNo" onchange="javascript:fnLoadSections()"
						styleId="sltModel" styleClass="form-control">
						<option selected value="-1">---Select---</option>
						<logic:present name="CompMapMaintForm" property="modelList">
							<html:optionsCollection name="CompMapMaintForm"
								property="modelList" value="modelSeqNo" label="modelName" />
						</logic:present>
					</html:select>
					</div>
			</div>
			<div class="form-group required-field">
					<label class="col-sm-5 control-label">Section</label>
					<div class="col-sm-4">
					<html:select name="CompMapMaintForm"
						property="sectionSeqNo" onchange="javascript:fnLoadSubSections()"
						styleId="sltSection" styleClass="form-control">
						<option selected value="-1">---Select---</option>
						<logic:present name="CompMapMaintForm" property="sectionList">
							<html:optionsCollection name="CompMapMaintForm"
								property="sectionList" value="sectionSeqNo"
								label="sectionDisplay" />
						</logic:present>
					</html:select>
					</div>
			</div>
			<div class="form-group required-field">
					<label class="col-sm-5 control-label">SubSection</label>
					<div class="col-sm-4">
					<html:select name="CompMapMaintForm"
						property="subSectionSeqNo" styleId="sltSubSection" styleClass="form-control">
						<option selected value="-1">---Select---</option>
						<logic:present name="CompMapMaintForm" property="subSectionList">
							<html:optionsCollection name="CompMapMaintForm"
								property="subSectionList" value="subSecSeqNo"
								label="subSecDisplay" />
						</logic:present>
					</html:select>
					</div>
			</div>
			<div class="form-group">
					<label class="col-sm-5 control-label">Component Group Type</label>
					<div class="col-sm-4">
					<logic:notEmpty name="CompMapMaintForm"
							property="compGroupTypeList">
						<html:select property="compgrpCat" onchange="javascript:fnSearchCompGroups()" styleId="sltCompGrpType" styleClass="form-control">	
						<html:option value="0">All</html:option>
						<logic:present name="CompMapMaintForm"
							property="compGroupTypeList">
							<html:optionsCollection name="CompMapMaintForm"
								property="compGroupTypeList" value="compGrpTypeSeqNo"
								label="compGrpTypeName" />
						</logic:present>
					</html:select>
					</logic:notEmpty>
					<logic:empty name="CompMapMaintForm"
							property="compGroupTypeList">
						<select id="sltCompGrpType">	
						<option>---Select---</option>
						</select>		
					</logic:empty>
					</div>
			</div>
			<div class="form-group required-field">
					<label class="col-sm-5 control-label">Component Group</label>
					<div class="col-sm-4">
					<html:select name="CompMapMaintForm" property="compGrpSeqNo"
						styleId="sltComponent" styleClass="form-control">
						<option selected value="-1">---Select---</option>
						<logic:present name="CompMapMaintForm" property="compGrpList">
							<html:optionsCollection name="CompMapMaintForm"
								property="compGrpList" value="componentGroupSeqNo"
								label="componentGroupName" />
						</logic:present>
					</html:select>
					</div>
			</div>
			
			<div class="form-group">
				  <div class="col-sm-12 text-center">
				       <button class="btn btn-primary mdfybtn" type='button' id="modifyButton" ONCLICK="javascript:fnViewComponentGrpMapping('M')">View Model Component Group Mapping</button>
				       <button class="btn btn-primary deleteButton" type='button' id="deleteButton" ONCLICK="javascript:fnViewComponentGrpMapping('O')">View Order Component(s)</button>
					</div>   
			</div>
			<div class="form-group">
				  <div class="col-sm-12 text-center">
				       <button class="btn btn-primary mdfybtn" type='button' id="modifyButton" ONCLICK="javascript:fnSearchComponentMapping()">Search Model Components</button>
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
					<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Specification Type</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="CompMapMaintForm" property="specTypeNo" value="-1">
							<bean:write name="CompMapMaintForm" property="hdnSelSpecType"/>
						</logic:notEqual>
						<logic:equal name="CompMapMaintForm" property="specTypeNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				<div class="spacer10"></div>
				<div class="row">
					<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Model</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="CompMapMaintForm" property="modelSeqNo" value="-1">
							<bean:write name="CompMapMaintForm" property="hdnSelModel"/>
						</logic:notEqual>
						<logic:equal name="CompMapMaintForm" property="modelSeqNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				<div class="spacer10"></div>
				<div class="row">
					<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Section</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="CompMapMaintForm" property="sectionSeqNo" value="-1">
							<bean:write name="CompMapMaintForm" property="hdnSelSec"/>
						</logic:notEqual>
						<logic:equal name="CompMapMaintForm" property="sectionSeqNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				<div class="spacer10"></div>
				<div class="row">
					<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Sub Section</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left text-nowrap">
						<logic:notEqual name="CompMapMaintForm" property="subSectionSeqNo" value="-1">
							<bean:write name="CompMapMaintForm" property="hdnSelSubSec"/>
						</logic:notEqual>
						<logic:equal name="CompMapMaintForm" property="subSectionSeqNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				<div class="spacer10"></div>
				<div class="row">
					<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Component Group</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="CompMapMaintForm" property="compGrpSeqNo" value="-1">
							<bean:write name="CompMapMaintForm" property="hdnSelCompGrp"/>
						</logic:notEqual>
						<logic:equal name="CompMapMaintForm" property="compGrpSeqNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				
				<div class="spacer10"></div>	
				
				<table class="table table-bordered table-hover">
					    <thead>
					        <tr>
								<TH>Apply to Model</TH>
								<TH>Component/Value</TH>
								<TH>Default</TH>
							</tr>	
						</thead>
					    <tbody class="text-center">	
					    	<logic:iterate id="CompMapMaintFormId" name="CompMapMaintForm"
										property="compList" type="com.EMD.LSDB.vo.common.ComponentVO">
										<TR>
											<html:hidden property="hdnCompValues" />
											<html:hidden property="hdnDefaultFlag" />
											<html:hidden property="hdnApplyModel" />
											<TD CLASS="v-midalign"><logic:equal
												name="CompMapMaintFormId" property="applyToModelFlag"
												value="true">
												<html:checkbox name="CompMapMaintFormId"
													property="applyToModelFlag"
													value="<%=String.valueOf(CompMapMaintFormId.getComponentSeqNo())%>" />
											</logic:equal> <logic:notEqual name="CompMapMaintFormId"
												property="applyToModelFlag" value="true">
												<html:checkbox name="CompMapMaintFormId"
													property="applyToModelFlag"
													value="<%=String.valueOf(CompMapMaintFormId.getComponentSeqNo())%>" />
											</logic:notEqual></TD>
											<TD CLASS="v-midalign"><bean:write
												name="CompMapMaintFormId" property="componentName" /></TD>
											  <TD CLASS="v-midalign"><logic:equal
												name="CompMapMaintFormId" property="defaultFlag" value="true">
												<html:radio name="CompMapMaintFormId" property="defaultFlag"
													styleClass="compseq"
													value="<%=String.valueOf(CompMapMaintFormId.isDefaultFlag()) %>" />
											</logic:equal> <logic:notEqual name="CompMapMaintFormId"
												property="defaultFlag" value="true">
												<html:radio name="CompMapMaintFormId" property="defaultFlag"
													styleClass="compseq" value="" />
											</logic:notEqual></TD>
											<!--  <TD CLASS=borderbottomrightlight><INPUT TYPE="radio" id="defaultcomp" value="" checked CLASS="radcheck" name="optSelect" onclick="" ></TD>-->
											
										</TR>
							</logic:iterate>
					    </tbody>
				</table>
				<div class="form-group">
					<h5 class="col-sm-12 text-right"><INPUT TYPE="checkbox" ID="deSelect" name="" onClick ="fnDeselect()">
							De-select the default component</h6>	
				</div>    
				<div class="spacer30"></div>
				<div class="form-horizontal">
					<div class="form-group">
					  	<label class="col-sm-7 control-label">Validation Required against
							 <bean:write name="CompMapMaintForm" property="hdnSelModel"/>
						</label>  	 
						<div class="col-sm-2">	 
							 <strong>Yes</strong>
									<logic:equal name="CompMapMaintForm" property="validFlag" value="Y">
									<input type="radio" name="validFlag" value="Y" id="validFlagYes" class="radcheck" checked> 
									</logic:equal>
									<logic:notEqual name="CompMapMaintForm" property="validFlag"  value="Y">
									<input type="radio" name="validFlag" value="Y" id="validFlagYes" class="radcheck"> 
									</logic:notEqual>
								<strong>No</strong>
									<logic:equal name="CompMapMaintForm" property="validFlag"  value="N"> 
									<input type="radio" name="validFlag" value="N" id="validFlagNo" class="radcheck" checked>
									</logic:equal>
									<logic:notEqual name="CompMapMaintForm" property="validFlag"  value="N">
									<input type="radio" name="validFlag" value="N" id="validFlagNo" class="radcheck">
									</logic:notEqual>
					  	</div>			
					</div>  
					<div class="form-group">
					  	<label class="col-sm-7 control-label">Display <bean:write name="CompMapMaintForm" property="hdnSelCompGrp"/> in Customer Option Catalog
					  	</label>  
						<div class="col-sm-2">
							 <strong>Yes</strong>
									<logic:equal name="CompMapMaintForm" property="displayCOCFlag" value="Y">
									<input type="radio" name="displayCOCFlag" value="Y" class="radcheck" checked> 
									</logic:equal>
									<logic:notEqual name="CompMapMaintForm" property="displayCOCFlag" value="Y">
									<input type="radio" name="displayCOCFlag" value="Y" class="radcheck"> 
									</logic:notEqual>
								<strong>No</strong>
									<logic:equal name="CompMapMaintForm" property="displayCOCFlag" value="N"> 
									<input type="radio" name="displayCOCFlag" value="N" class="radcheck" checked>
									</logic:equal>
									<logic:notEqual name="CompMapMaintForm" property="displayCOCFlag" value="N">
									<input type="radio" name="displayCOCFlag" value="N" class="radcheck">
									</logic:notEqual>
					  </div>			
					</div> 
				
				 
				<div class="spacer10"></div>
				
				<div class="form-group">
				  <div class="col-sm-12 text-center">
				       <button class="btn btn-primary mdfybtn" type='button' id="saveButton" ONCLICK="javascript:fnSaveComponentMapping()">Save</button>
				   </div>   
			   	</div>
				<div class="spacer50"></div>
			</div>		
		</logic:greaterThan>		
		 
	<html:hidden property="compgrpCat" />
	<html:hidden property="hdnSelModel" />
	<html:hidden property="hdnSelSec" />
	<html:hidden property="hdnSelSubSec" />
	<html:hidden property="hdnSelCompGrp" />
	<html:hidden property="validFlag" />
	<html:hidden property="hdnSelSpecType" />
	<html:hidden property="compSourceFlag" />
<%--Added for CR-114 --%>	
	<input type="hidden" name="hdnSelectedRadio" id="hdnSelectedRadio"/>
</html:form>
</div>		 