<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT LANGUAGE="JavaScript" SRC="js/MdlGenInfoTexMaint.js"></SCRIPT>
<script>
       $(document).ready(function() { 
       	$("#sltSpecType").select2({theme:'bootstrap'});
       	$("#sltModel").select2({theme:'bootstrap'});
       });	
</script>
<div class="container" width="100%">
	<html:form action="/GenInfoMaintByModel" enctype="multipart/form-data">
 		<h4 class="text-center"><bean:message key="Application.Screen.GeneralInfoMaintenance" /></h4>
 		<p class="text-center"><mark><bean:message	key="Application.Screen.GeneralInfoMaintenanceNote" /></mark></p>
		<div class="spacer10"></div>
		<div class="errorlayerhide" id="errorlayer">
		</div>
		<logic:present name="MdlGenInfoTexMaintForm" property="modSpecList">
		<bean:size id="geninfotext" name="MdlGenInfoTexMaintForm"
			property="modSpecList" />
		</logic:present>
		<logic:present name="MdlGenInfoTexMaintForm" property="messageID" scope="request">

		<bean:define id="messageID" name="MdlGenInfoTexMaintForm" property="messageID"/>
	            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
	            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
		</logic:present>
	
		<logic:match name="MdlGenInfoTexMaintForm" property="method"
			value="fetchModels">
			
			<script> 
	              fnNoRecords();
	        </script>
		</logic:match>
		
		<div class="spacer10"></div>
		 
			<div class="row form-inline">
			<div class="col-sm-12 text-center">
				<div class="form-group required-field">
					<label class="control-label">Specification Type</label>
					
						<html:select styleClass="form-control" styleId="sltSpecType"
								name="MdlGenInfoTexMaintForm" property="specTypeNo"
								onchange="javascript:fnLoadModels()">
								<option selected value="-1">---Select---</option>
								<html:optionsCollection name="MdlGenInfoTexMaintForm"
									property="specTypeList" value="spectypeSeqno" label="spectypename" />
						</html:select>
				</div>
			<div class="form-group required-field">
					<label class="control-label">Model</label>
					<html:select  styleClass="form-control" styleId="sltModel"
								name="MdlGenInfoTexMaintForm" property="modelSeqNo">
								<option selected value="-1">---Select---</option>
								<logic:present name="MdlGenInfoTexMaintForm" property="modelList">
									<html:optionsCollection name="MdlGenInfoTexMaintForm"
										property="modelList" value="modelSeqNo" label="modelName" />
								</logic:present>
						</html:select>
			</div>
			<div class="form-group">
				     <button class="btn btn-primary addbtn" type='button' id="searchButton" ONCLICK="javascript:fetchGenInfoTextItems()">Search</button>
			</div>
		</div>	
		</div>
		
		<logic:greaterThan name="geninfotext" value="0">		
				<hr>
				<div class="spacer10"></div>
				<div class="row">
				<div class="col-sm-11 text-center"><h5 class="text-center"><strong>Search Criteria</strong></h5></div>
				</div>
				<div class="spacer10"></div>
				
				<div class="row">
					<div class="col-sm-4 col-sm-offset-1 text-right"><strong>Specification Type</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="MdlGenInfoTexMaintForm" property="specTypeNo" value="-1">
							<bean:write name="MdlGenInfoTexMaintForm" property="hdnSelSpecType"/>
						</logic:notEqual>
						<logic:equal name="MdlGenInfoTexMaintForm" property="specTypeNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				<div class="spacer10"></div>
				<div class="row">
					<div class="col-sm-4 col-sm-offset-1 text-right"><strong>Model</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="MdlGenInfoTexMaintForm" property="modelSeqNo" value="-1">
							<bean:write name="MdlGenInfoTexMaintForm" property="hdnModelName"/>
						</logic:notEqual>
						<logic:equal name="MdlGenInfoTexMaintForm" property="modelSeqNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				<div class="spacer20"></div>
				<logic:iterate id="DisList" name="MdlGenInfoTexMaintForm"
			    		  property="modSpecList" type="com.EMD.LSDB.vo.common.ModelVo" scope="request" indexId="count">
			          <logic:equal name="MdlGenInfoTexMaintForm" property="hdnModelSeqNo" value="<%=String.valueOf(DisList.getModelSeqNo())%>" >
			          
						<div class="panel panel-info">
							<div class="panel-heading">
								<h5 class="panel-title text-center">
									General Information Text for Draft
								</h5>
							</div>
						<div class="panel-body">	
					 			<html:textarea styleClass="form-control" property="genInfoTextDraft" name="DisList" rows="5"/>
								<div class="spacer10"></div>
								<div class="form-group">
									<div class="col-sm-12 text-center">
										<button class="btn btn-primary savebtn" type='button' ONCLICK="javascript:fnSaveGenInfoTextDraftAndOpen('D')"><bean:message key="screen.save" /></button>
									</div>
								</div>
						</div>
						</div>
						<div class="spacer10"></div>
						<div class="panel panel-info">
							<div class="panel-heading">
								<h5 class="panel-title text-center">
									General Information Text for Baseline
								</h5>
							</div>
						<div class="panel-body">	
					 			<html:textarea styleClass="form-control" property="genInfoTextOpen" name="DisList" rows="5"/>
								<div class="spacer10"></div>
								<div class="form-group">
									<div class="col-sm-12 text-center">
										<button class="btn btn-primary savebtn" type='button' ONCLICK="javascript:fnSaveGenInfoTextDraftAndOpen('O')"><bean:message key="screen.save" /></button>
									</div>
								</div>
						</div>
						</div>

					</logic:equal>
				</logic:iterate>	
		</logic:greaterThan>		
		<div class="spacer50"></div>	
	<html:hidden property="hdnModelName" />
	<html:hidden property="hdnModelSeqNo" />
	
	<html:hidden property="hdnSpecSeqNo" />
	<html:hidden property="hdnPrevSelModel" />
	<html:hidden property="hdnSelSpecType" />
	</html:form>
</div>	