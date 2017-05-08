<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT LANGUAGE="JavaScript" SRC="js/OrderSpec.js"></SCRIPT>

<html:form styleClass="form-horizontal" action="/OrderSpecAction" method="post">
	<div class="container-fluid" >
		<h4 class="text-center"><bean:message key="Application.Screen.AddOrderSpecificationItem" /></h4>
				
	<div class="errorlayerhide" id="errorlayer">
	</div>
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
			<p class="col-sm-5 text-right"><strong>Order Number</strong></p>
			<p class="col-sm-4"><bean:write name="OrderSpecificationForm" property="orderNo" /></p>
			<html:hidden property="orderNo" /> 
		</div>
		<div class="form-group">
			<p class="col-sm-5 text-right"><strong>Title</strong></p>
			<p class="col-sm-4"><bean:write name="OrderSpecificationForm" property="hdnSpecName" /></p>
		</div>
		<div class="form-group required-field">
			<label class="col-sm-5 control-label">Description</label>
				<div class="col-sm-2">
					<html:text styleClass="form-control" property="orderSpecItemDesc" 
						size="30" maxlength="2000" />
					<span class="alertmsg"></span>
				</div>
		</div>
		<div class="form-group">
			<label class="col-sm-5 control-label">Value</label>
				<div class="col-sm-2">
					<html:text styleClass="form-control" property="orderSpecItemValue" 
						size="15" maxlength="100" />
					<span class="alertmsg"></span>
				</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-5 col-sm-4">
				<button class="btn btn-primary " type='button' id="saveButton" ONCLICK="javascript:insertSpecificationItem()">Save</button>&nbsp;
				<button class="btn btn-primary " type='button' id="hmePgButton" ONCLICK="javascript:orderspecHomePage()">General Info Maintenance</button>
			</div>
		</div>
		<div class="row">
			<div class="spacer30"></div>
		</div>
</div>	
	<html:hidden property="hdndataLocType" />
	<html:hidden property="orderKey" />
	<html:hidden property="hdnSpecName" />
	<html:hidden property="hdnSpecSeqNo" />
	<html:hidden property="hdnRevViewCode" />
	
</html:form>

