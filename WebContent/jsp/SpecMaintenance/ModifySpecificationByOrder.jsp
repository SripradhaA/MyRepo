<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT LANGUAGE="JavaScript" SRC="js/OrderSpec.js"></SCRIPT>

<html:form styleClass="form-horizontal" action="/OrderSpecAction" method="post">
	<div class="container-fluid" >
		<h4 class="text-center"><bean:message key="Application.Screen.AddOrderSpecification" /></h4>
				
	<div class="errorlayerhide" id="errorlayer"></div>
	<BR>
	<logic:present name="OrderSpecificationForm" property="messageID"
		scope="request">
			<bean:define id="messageID" name="OrderSpecificationForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
	</logic:present>

		<div class="row">
			<div class="spacer10"></div>
		</div>
		<div class="form-group">
			<label class="col-sm-5 control-label">Order Number</label>
				<div class="col-sm-4 form-control-static">
					<bean:write	name="OrderSpecificationForm" property="orderNo" />
					<html:hidden property="orderNo" /> 
				</div>
		</div>
		<div class="form-group required-field">
			<label class="col-sm-5 control-label">Title</label>
				<div class="col-sm-2">
					<html:text styleClass="form-control" property="orderSpecName" 
						size="30" maxlength="2000" />
				</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-5 col-sm-4">
				<button class="btn btn-primary " type='button' id="mdfyButton" ONCLICK="javascript:updateSpecification()">Modify</button>&nbsp;
				<button class="btn btn-primary " type='button' id="hmePgButton" ONCLICK="javascript:orderspecHomePage()">General Info Maintenance</button>
			</div>
		</div>
		<div class="row">
			<div class="spacer30"></div>
		</div>
</div>	

	<html:hidden property="orderKey" />
	<html:hidden property="hdnSpecSeqNo" />

	<html:hidden property="hdnRevViewCode" />
</html:form>


