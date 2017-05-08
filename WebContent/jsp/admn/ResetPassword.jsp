<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<SCRIPT type="text/JavaScript" SRC="js/ResetPassword.js"></SCRIPT>

	<script>
        $(document).ready(function() { 
        	$("#sltUserID").select2({theme:'bootstrap'});
        	$(function() {
		        $("ul.nav li.dropdown").hover(
		            function(){ $(this).addClass('open') },
		            function(){ $(this).removeClass('open') }
		        );
		    });  
        })
    </script>

<div class="container-fluid">

	<html:form styleClass="form-horizontal" action="/ResetPasswordAction" method="post">
	
		<!-- Application PageName Display starts here-->
	
		<h4 class="text-center"><bean:message key="Application.Screen.ResetPassword"/></h4>
		<div class="spacer10"></div>
		<div class="row">
			<div class="errorlayerhide" id="errorlayer"></div>
		</div>
		<!-- Application PageName Display Ends here-->
		
		<!-- Validation Message Display Part Starts Here -->
		
		
		<!-- Validation Message Display Part Ends Here -->
		<!-- To display Validation Messages - Start -->
		<logic:present name="ResetPasswordForm" property="messageID" scope="request">
		 	
		  <bean:define id="messageID" name="ResetPasswordForm" property="messageID"/>
		  <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>"/>
	      <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
	
		</logic:present>
		<!-- To display Validation Messages - End -->
		
		<div class="spacer30"></div>
	    
	    <div class="form-group required-field">
				<label class="col-sm-5 control-label">User ID</label>
				<div class="col-sm-4">
				
				        <html:select styleId="sltUserID"
							name="ResetPasswordForm" property="userId" styleClass="form-control">
							<OPTION value="-1">--Select--</OPTION>
							<html:optionsCollection name="ResetPasswordForm"
								property="userDetails" value="userrId" label="name" />
						</html:select>
		
				</div>
		</div>
		
		<div class="spacer10"></div>
		
		<div class="form-group">
				<div class="col-sm-12 text-center">
					<button class="btn btn-primary addbtn" type='button' id="resetbtn" ONCLICK="javascript:fnResetPassword()">Reset Password</button>
				</div>
		 </div>
		
		
</html:form>
</div>
