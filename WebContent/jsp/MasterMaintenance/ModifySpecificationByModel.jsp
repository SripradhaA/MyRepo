<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT LANGUAGE="JavaScript" SRC="js/Model_Specification.js"></SCRIPT>
<div class="container" WIDTH="100%" >
<html:form action="/ModelSpecificationAction" method="post">
	<h4 class="text-center"><bean:message key="Application.Screen.AddSpecification" /></h4>
	<br>
	<div class="errorlayerhide" id="errorlayer">
	</div>
	<BR>
	<logic:present name="ModelSpecificationForm" property="messageID"
		scope="request">
		
	<bean:define id="messageID" name="ModelSpecificationForm" property="messageID"/>
           <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
	</logic:present>
	 <div class="spacer10"></div>
		 
		 <div class="form-horizontal">
			<div class="form-group">
				<label class="col-sm-5 control-label">Specification Type</label>
					<div class="col-sm-2 form-control-static">
						<bean:write name="ModelSpecificationForm" property="hdnSelSpecType" />
					</div>
			</div>
			<div class="form-group">
				<label class="col-sm-5 control-label">Model</label>
					<div class="col-sm-3 form-control-static text-nowrap">
						<bean:write name="ModelSpecificationForm" property="hdnModelName" />
					</div>
			</div>
			<div class="form-group required-field">
				<label class="col-sm-5 control-label">Title</label>
					<div class="col-sm-3">
						<html:text property="modSpecName" styleClass="form-control" maxlength="2000" />
					</div>
			</div>
		</div>
		<div class="spacer10"></div>
			<div class="form-group">
				<div class="col-sm-12 text-center">
				       <button class="btn btn-primary" type='button' id="modifyButton" ONCLICK="javascript:updateSpecification()">Modify</button>
				        <button class="btn btn-primary" type='button' id="btnGenInfoMaint" ONCLICK="javacript:specHomePage()">General Info Maintenance</button>
				</div>   
			</div>
		<div class="spacer50"></div>	
		
	<html:hidden property="modelSeqNo" />
	<html:hidden property="hdnModelName" />
	<html:hidden property="hdnSelSpecType" />
	<html:hidden property="specTypeNo" />
	<html:hidden property="hdnSpecSeqNo" />

</html:form>
</div>