<!DOCTYPE HTML>
<HTML>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<link rel="shortcut icon" href="images/favicon.ico" />
<link href="css/bootstrap.css" TYPE="text/css" rel="stylesheet"/>
<link href="css/EMDCustom.css" rel="stylesheet"/>
<script type="text/javascript" src="js/Others/jquerylatest.js"></script>
<script type="text/javascript" src="js/Others/jquerymigrate.js"></script>
<script type="text/javascript" src="js/Others/bootstrap.min.js"></script>
<SCRIPT language="JavaScript" SRC="js/Emd_Lsdb.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/error.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/error_messages.js"></SCRIPT>
<SCRIPT type="text/JavaScript" SRC="js/ChangePassword.js"></SCRIPT>
<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
<HEAD>
<script>
	function chkPswLength(){
			var newPsw = $("#newPassword").val().length;
			var cnfPsw = $("#confirmPassword").val().length;
			var selector = "#newPassword";
			if(newPsw==12)
				var selector = "#newPassword";
			else if(cnfPsw==12)
				var selector = "#confirmPassword";
			
			if(newPsw==12 || cnfPsw==12)
		   	{
		     	var msg = "Password should not be more than twelve characters";
		     	
		     	
		      	if (!$(selector).hasClass('validated')) { //If the class is already validated, do not remove the alerts
							
					  $(".validated").tooltip('destroy');
					  $(".form-group").removeClass("has-success has-error has-feedback");
					  $(".validated").removeClass('validated');
				} 
			  
			  	$(selector).closest(".form-group").addClass("has-error has-feedback");
			  	$(selector).prop('title', '<span class="glyphicon glyphicon-remove"></span> ' + msg);
				
				$(selector).tooltip({
					template : '<div role="tooltip" class="tooltip"><div class="tooltip-inner noWidth alert-message error"></div></div>',
					html : true,
					container: "body"
				});
				
				$(selector).addClass('validated');
				$(selector).tooltip('fixTitle').tooltip('show');
				//$(selector).val('');
		   } else {
		   		if ($(selector).hasClass('validated')) { //If the length has reduced, remove the alerts
							
					  $(".validated").tooltip('destroy');
					  $(".form-group").removeClass("has-success has-error has-feedback");
					  $(".validated").removeClass('validated');
				} 
		   }
		}
</script>
</HEAD>
<div class="container-fluid">

	<html:form styleClass="form-horizontal" action="/ChangePassword" method="post">
		<div class="spacer100"></div>
		<h4 class="text-center"><bean:message key="Application.Screen.ChangePassword" /></h4>
		<div class="spacer10"></div>
		<div class="row">
			<div class="errorlayerhide" id="errorlayer"></div>
		</div>
		
		<logic:present name="ChangePwdForm" property="messageID" scope="request">
		    <%--Added for CR-121 for server side error message tooltip --%>	
		    <bean:define id="messageID" name="ChangePwdForm" property="messageID"/>
		    <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
	        <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
	    </logic:present>
	    
	    <div class="spacer30"></div>
	    <div class="form-group required-field">
				<label class="col-sm-6 control-label">New Password</label>
				<div class="col-sm-2">
	                         <html:password property="newPassword" styleClass="form-control" onkeydown="return chkPswLength()" size="" maxlength="12" value="" styleId="newPassword" />
	            </div>
	     </div>       
			
		<div class="form-group required-field">
				<label class="col-sm-6 control-label">Confirm Password</label>
				<div class="col-sm-2">
	                         <html:password property="confirmPassword" styleClass="form-control" onkeydown="return chkPswLength()" size="" maxlength="12" styleId="confirmPassword" />
	            </div>
	     </div>
	     
	     <div class="spacer10"></div>
	     
	     <div class="form-group">
				<div class=" col-sm-12 text-center">
					<button class="btn btn-primary addbtn" type='button' id="createButton" ONCLICK="javascript:fnChangePwd()">Save</button>
				</div>
		 </div>
	
</html:form>
</html>