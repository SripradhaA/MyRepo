<!DOCTYPE HTML>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link rel="shortcut icon" href="images/favicon.ico" />
	<link href="css/bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/EMDCustom.css" TYPE="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="js/Others/jquerylatest.js"></script>
	<script type="text/javascript" src="js/Others/jquerymigrate.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap.min.js"></script>
	<SCRIPT type="text/javascript" SRC="js/Emd_Lsdb.js"></SCRIPT>
	<TITLE>LSDB<LSDB:AppServerIP/>, Electro Motive Diesel Inc</TITLE>
	
	<SCRIPT type="text/javascript">
		var currentTime = new Date();
		var year = currentTime.getFullYear();
		$(document).ready(function()
		{
			if ($('#errormsg').val() != "")	{
				$(".alert").alert('close');
				$("#errorlayer").append('<div class="alert alert-message error fade in"><span class="glyphicon glyphicon-remove" aria-hidden="true"> </span><button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button> ' + $('#errormsg').val() + '</div>');
			}
			$('#userID').focus();
			var emailFlag = getParameterByName('EmailFlag');
			if(emailFlag == "true"){
				document.forms[0].emailFlag.value="true";
			}else{
				document.forms[0].emailFlag.value="false";
			}
			  
			$('#loginForm').on('submit', function(e) {
				
				 e.preventDefault();

				 if ($('.alert').length > 0) {
						$('.alert').on('closed.bs.alert', function () {
							if ($('.validated').length > 0)
								$('.tooltip').tooltip('show');
						 });
						$(".alert").alert('close');
				 }
			 
				 var selector = "";
				 var msg = "";
				 
				 if ($('#userID').val().trim() == "")	{
				  selector = "#userID";
				  msg = "Please enter Username";
				 }
				 else if ($('#password').val().trim() == "")	{
				  selector = "#password";
				  msg = "Please enter password";
				 }  
				 
				 if (selector != "") {
					 
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
					$('#userID').val($('#userID').val().trim());
					return false;
					
				 } else {
				  
					  $('#userID').val($('#userID').val().trim());
					  document.forms[0].submit();
				 }
			  
				});
		});
	
		function fnReset(){
			$('#userID').val("");
			$('#password').val("");
			$(".validated").tooltip('destroy');
		    $(".form-group").removeClass("has-success has-error has-feedback");
		    $(".validated").removeClass('validated');
			if ($('.alert').length > 0) {
					$('.alert').on('closed.bs.alert', function () {
						if ($('.validated').length > 0)
							$('.tooltip').tooltip('show');
					 });
					$(".alert").alert('close');
			 }
		}
		
		function chkPswLength(){
			var psw = $("#password").val().length;
			var selector = "#password";

			if(psw==12)
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
	</SCRIPT>
	
</head>
<body style="background-color: #31708f;">
	<bean:define id="username">root</bean:define>
	<div class="container-fluid">
		<html:form action="/LoginAction.do?method=findUser" method="post" styleId="loginForm">
		<div class="modal show" id="login" tabindex="-1" role="dialog" aria-labelledby="loginModal" style="margin-top: 45px;overflow: auto;">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">				
					<div class="modal-header">
				      	<IMG src="<%=request.getContextPath()%>/images/loginheader.png" class="img-responsive center-block"/>
			     	</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-sm-6">
								<IMG src="<%=request.getContextPath()%>/images/EMD.jpg" class="img-responsive img-thumbnail"/>
							</div>
							<div class="col-sm-6">
								<div class="spacer20"></div>
								<div class="errorlayerhide" id="errorlayer"></div>
								<div class="spacer20"></div>
							</div>
							<div class="col-sm-6">
					      		<div class="form-group required-field">
						      		<label class="control-label"><bean:message key="username" /></label>
									<input type="text" name="userID" id="userID" class="form-control input-lg"
											value='<bean:write name="LoginForm" property="userID" />' />
								</div>
								<div class="form-group required-field">
									<label class="control-label"><bean:message key="password" /></label>
									<input type="password" name="password" id="password" onkeydown="return chkPswLength()" maxlength="12" class="form-control input-lg" />
								</div>
								<div class="text-center">
									<button type="submit" class="btn btn-primary btn-lg"><bean:message key="login.submit" /></button>
									<button type="button" class="btn btn-lg btn-default" onclick="fnReset();">Reset</button>
								</div>						
							</div>
							<html:hidden property="emailFlag" styleId="emailFlag" />
							<input type="hidden" id="errormsg" value="<html:errors />"/>
						</div>
					</div>
					<div class="modal-footer">
						<blockquote>
							<p class="text-justify">													
								Power, performance and innovation.For over 80 years
								these qualities have driven us to be the world's
								premier provider of locomotive technology. What's
								been true from the start is still true today. Our
								products,our people and our worldwide affiliates
								enable us to lead the industry as we improve
								productivity and safety standards around the
								world.
							</p>
						</blockquote>
						<p class="text-muted text-center">Copyright &copy; <script type="text/javascript">document.write(year)</script> Electro-Motive Diesel, Inc</p>
				 	</div>					
				</div>
			</div>
		</div>
		</html:form>
	</div>
</body>
</html>