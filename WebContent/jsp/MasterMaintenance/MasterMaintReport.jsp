<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<SCRIPT type="text/JavaScript" SRC="js/MasterMaintReport.js"></SCRIPT>

<script>
    $(document).ready(function() { 
    	$("#selectSpecType").select2({theme:'bootstrap'}); 
    	$("#selectModel").select2({theme:'bootstrap'});
    })
</script>

<div class = "container" width="100">

<html:form action="/MasterMaintReportAction.do?method=initLoad" method="post" styleClass="form-horizontal">

	<h4 class ="text-center">
		<bean:message key="Application.Screen.MaintReport" />
	</h4>
	
	<div class="errorlayerhide" id="errorlayer">
	</div>
	<div class="spacer20"></div>
				
	<logic:present name="MasterMaintForm" property="messageID"
		scope="request">
		<bean:define id="messageID" name="MasterMaintForm" property="messageID"/>
        <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
        <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
	</logic:present>
	
	<div>
	
		<div class="form-group required-field">
			<label class="col-sm-5 control-label">Specification Type</label>
			<div class="col-sm-3">
				<html:select name="MasterMaintForm"
					property="specTypeNo" styleClass="form-control"  styleId="selectSpecType" 
					onchange="javascript:fnLoadModels()">
					<html:option value="-1">--Select--</html:option>
					<html:optionsCollection name="MasterMaintForm"
						property="specTypeList" value="spectypeSeqno" label="spectypename" />
				</html:select>
			</div>
		</div>
				
			
		<div class="form-group required-field">
			<label class="col-sm-5 control-label"><bean:message key="master.model" /></label>
			<div class="col-sm-3">
				<html:select name="MasterMaintForm"
					property="modelSeqNo" styleClass="form-control" styleId="selectModel">
					<html:option value="-1">--Select--</html:option>
					<logic:present name="MasterMaintForm" property="modelList">
						<html:optionsCollection name="MasterMaintForm" property="modelList"
							label="modelName" value="modelSeqNo" />
					</logic:present>
				</html:select>
			</div>
		</div>
			
		<div class="form-group">
			<label class="col-sm-5 control-label">Show Deleted Clauses</label>
			<div class="col-sm-3 checkbox-inline">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<html:checkbox name="MasterMaintForm" property="chkBoxStatus"/>
			</div>
		</div>
		
		<div class="col-sm-12 text-center">
			<div class="form-group">
	              <button class="btn btn-primary" type='button' id="ViewMasterSpec" ONCLICK="fnViewSpecByModel()"><bean:message key="master.ViewSpec" /></button>
	              <button class="btn btn-primary" type='button' id="ExportToExcel" ONCLICK="fnViewSpecByModelExcel()"><bean:message key="master.ExportToExcel" /></button>
	       </div>
		</div>
		
	</div>
		
<html:hidden property="modelName" />
<html:hidden property="hdnSelSpecType" />
</html:form>

</div>
