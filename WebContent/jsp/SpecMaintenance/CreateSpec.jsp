<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT LANGUAGE="JavaScript" SRC="js/CreateSpec.js"></SCRIPT>
<script>
      $(document).ready(function() { 
      	$("#sltSpecType").select2({theme:'bootstrap'});
      	$("#sltModel").select2({theme:'bootstrap'});
      	$("#sltDistributor").select2({theme:'bootstrap'});   
      	$("#sltCustomer").select2({theme:'bootstrap'}); 
      })
</script>
<div class="container-fluid">
<html:form styleClass="form-horizontal" action="/CreateSpecAction" method="post">	
		<h4 class="text-center"><bean:message key="Application.Screen.CreateSpec" /></h4>
		<div class="errorlayerhide" id="errorlayer"></div>
		<logic:present name="CreateSpecForm" property="messageID" scope="request">
			<bean:define id="messageID" name="CreateSpecForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value='<bean:write name="messageID"/>'>
            <input type="hidden" name="errorElement" id="errorelement" value='<bean:message name="messageID" bundle="ServerMessageBundle"/>'/>
		</logic:present>
		<div class="spacer30"></div>
		<div id="spectype">
			<div class="form-group required-field">
				<label class="col-sm-5 control-label">Specification Type</label>
				<div class="col-sm-4">
					<html:select name="CreateSpecForm" styleClass="form-control" styleId="sltSpecType"
						property="specTypeNo" onchange="javascript:changeSpecType()">
						<option selected value="-1">---Select---</option>
						<html:optionsCollection name="CreateSpecForm"
							property="specTypeList" value="spectypeSeqno" label="spectypename" />
					</html:select>
				</div>
			</div>
			<div class="form-group">			
				<label class="col-sm-5 control-label">Customer Type</label>
				<div class="col-sm-4">
					<logic:notEqual name="CreateSpecForm" property="distributorFlag" value="Y">		
						  <label class="radio-inline custom-radio control-label ">
							<html:radio	property="modCustTypeSeqNo" styleId="modCustTypeSeqNo1" value="1"
							name="CreateSpecForm" onclick="javascript:populate()" />Domestic
						  </label>
						  <label class="radio-inline control-label">
							<html:radio	property="modCustTypeSeqNo" styleId="modCustTypeSeqNo2" value="2" name="CreateSpecForm"
							onclick="javascript:populate()" />Export
						  </label>
					</logic:notEqual>
					<logic:equal name="CreateSpecForm" property="distributorFlag" value="Y">
						  <label class="radio-inline disabled control-label">
							<html:radio property="modCustTypeSeqNo" value="1" styleId="modCustTypeSeqNo1" name="CreateSpecForm" 
							disabled="true" />Domestic
						  </label>
						  <label class="radio-inline control-label disabled">
							<html:radio property="modCustTypeSeqNo" styleId="modCustTypeSeqNo2" value="2" name="CreateSpecForm"
							disabled="true" />Export
						  </label>
					</logic:equal>
				</div>
			</div>
			<div class="form-group required-field">
				<label class="col-sm-5 control-label">Distributor</label>
				<div class="col-sm-4">					
					<logic:notEqual name="CreateSpecForm"
						property="distributorFlag" value="Y">
						<html:select name="CreateSpecForm" property="distSeqNo" styleClass="form-control" styleId="sltDistributor"
							disabled="true">
							<option selected value="-1">---Select---</option>
						</html:select>
					</logic:notEqual>
					<logic:equal name="CreateSpecForm"
						property="distributorFlag" value="Y">
						<html:select name="CreateSpecForm" property="distSeqNo" styleClass="form-control" styleId="sltDistributor">
							<option selected value="-1">---Select---</option>
							<logic:present name="CreateSpecForm" property="distributorList">
								<html:optionsCollection name="CreateSpecForm"
									property="distributorList" value="distributorSeqNo"
									label="distributorName" />
							</logic:present>
						</html:select>
					</logic:equal>
				</div>
			</div>
			<div class="form-group required-field">
				<label class="col-sm-5 control-label">Customer</label>
				<div class="col-sm-4">					
					<html:select name="CreateSpecForm" property="cusSeqNo" styleClass="form-control" styleId="sltCustomer">
						<option selected value="-1">---Select---</option>
						<logic:present name="CreateSpecForm" property="customerList">
							<html:optionsCollection name="CreateSpecForm"
								property="customerList" value="customerSeqNo" label="customerName" />
						</logic:present>
					</html:select>
				</div>
			</div>
			<div class="form-group required-field">
				<label class="col-sm-5 control-label">Model</label>
				<div class="col-sm-4">					
					<html:select name="CreateSpecForm" property="modelSeqNo" styleClass="form-control" styleId="sltModel">
						<option selected value="-1">---Select---</option>
						<logic:present name="CreateSpecForm" property="modelList">
							<html:optionsCollection name="CreateSpecForm" property="modelList"
								value="modelSeqNo" label="modelName" />
						</logic:present>
					</html:select>
				</div>
			</div>
			<div class="form-group required-field">
				<label class="col-sm-5 control-label">SAP Customer Code</label>
				<div class="col-sm-2">					
					<html:text styleClass="form-control" property="sapCusCode" maxlength="10" styleId="sapCusCode"/>
				</div>
			</div>
			<div class="form-group required-field">
				<label class="col-sm-5 control-label">Order Number</label>
				<div class="col-sm-2">					
					<html:text styleClass="form-control" property="orderNo" maxlength="15"  styleId="orderNo"/>
				</div>
			</div>
			<div class="form-group required-field">
				<label class="col-sm-5 control-label">Quantity</label>
				<div class="col-sm-2">					
					<html:text styleClass="form-control" property="quantity" maxlength="10" value="" styleId="quantity"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-4">
					<button class="btn btn-primary" type='button' id="createButton" ONCLICK="javascript:createSpec()">Create Spec</button>
				</div>
			</div>
		</div>
	
	<input type="hidden" name="flag" value="Y">
	<html:hidden property="distributorFlag"/>
</html:form>
<div class="spacer100"></div>
</div>