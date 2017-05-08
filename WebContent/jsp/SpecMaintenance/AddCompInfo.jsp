<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT type="text/JavaScript" SRC="js/MainFeatureInfo.js"></SCRIPT>

<div class="container-fluid" >
<html:form styleClass="form-horizontal" action="/MainFeatureInfo" method="post">
	<h4 class="text-center"><bean:message key="Application.Screen.ComponentInfo" /></h4>
				
	<!-- Validation Message Display Part Starts Here -->
	<div class="spacer10"></div>
	<div class="errorlayerhide" id="errorlayer">
	</div>

	<!-- Validation Message Display Part Ends Here -->


	<logic:present name="MainFeatureForm" property="messageID"
		scope="request">
			<bean:define id="messageID" name="MainFeatureForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
	</logic:present>
	<html:hidden property="orderKey" />
	<html:hidden property="orderNo" />
	<html:hidden property="hdnRevViewCode" />
	
		<div class="form-group">
			<label class="col-sm-5 control-label">Order Number</label>
			<p class="col-sm-4 form-control-static"><bean:write name="MainFeatureForm" property="orderNo" /></p>
		</div>
		<div class="form-group required-field">
			<label class="col-sm-5 control-label">Component Description</label>
				<div class="col-sm-2">
					<html:text styleClass="form-control" name="MainFeatureForm"
					property="compDesc" size="30" maxlength="2000" />
					<span class="alertmsg"></span>
				</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-5 col-sm-4">
				<button class="btn btn-primary" type='button' id="saveButton" ONCLICK="javascript:fnSave()">Save</button>&nbsp;
				<button class="btn btn-primary" type='button' id="mdfyButton" ONCLICK="javascript:fnModifySpec()">General Info Maintenance</button>
			</div>
		</div>
		<div class="row">
			<div class="spacer30"></div>
		</div>
</html:form>
</div>

