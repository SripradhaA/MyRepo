<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<SCRIPT type="text/JavaScript" SRC="js/UserMaintenance.js"></SCRIPT>
<script type="text/JavaScript" src="js/Others/tiny_mce/jquery.tinymce.js"></script>
<%@ include file="/jsp/common/richTextEditor.jsp" %>

<script>
		$(document).ready(function() {
			$("#sltRoles").change(function() {
		            $(".userIDChkbox").attr('checked',false);							  
					 SetRole($("#sltRoles").val())
			});
			function SetRole(role){
			var i=0;
			
				$('#tbBroadcastEmails tr').each(function() {
						
					if(role == "All but Disabled"){			
					<!--Added  for CR-114 by vb106565-->	
					if(i==0){
					<!-- CR-114 ends here-->
					$(".userIDChkbox").attr('checked',true);	 
					}
					<!--Added  for CR-114 by vb106565-->
						if($.trim($(this).find('td:last').text()) == "Disabled"){
							$(this).find('td:first').find("input").attr('checked',false);
						}
					i++;
					<!-- Added  for CR-114 ends here-->
					return;
					}else if($.trim($(this).find('td:last').text()) == role){ 
					$(this).find('td:first').find("input").attr('checked',true);
					}else return;
	 			});
			}
	     
	      $("#sltRoles").select2({theme:'bootstrap'});   
		  $('#tbBroadcastEmails').DataTable({
		    	 paging:   false,
		         ordering: true,
		         info:     false,
		         order: [[ 2, 'asc' ]],
		         columnDefs: [
	                       { "orderable": false, "targets": [0,5] },
	                       { "searchable": false, "targets": [0] }
	                 ]
		  });
      });
</script>

<div class = "container" width="90%">

<html:form action="/UserMaintenanceAction" method="post" styleClass="form-horizontal">

	<h4 class ="text-center">
		<bean:message key="Application.Screen.BroadcastEmail" />
	</h4>
	
	<div class="spacer10"></div>
	
	<div class="errorlayerhide" id="errorlayer">
	</div>

	<logic:present name="UserMaintForm" property="userDetails"
		scope="request">
		<bean:size id="userlist" name="UserMaintForm" property="userDetails" />
	</logic:present>

	<logic:present name="UserMaintForm" property="messageID"
		scope="request">
	  <bean:define id="messageID" name="UserMaintForm" property="messageID"/>
	  <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
      <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
	</logic:present>
	
	<div>
		<div class="spacer20"></div>
		
		<div class="form-group">
			<label class="col-sm-5 control-label">Select Roles</label>
			<div class="col-sm-2">
				 <html:select property="roleName" styleClass='form-control' tabindex="8" styleId="sltRoles">
					<option selected value="-1">---Select---</option>
					<option value="All but Disabled">All but Disabled</option>
					<logic:present name="UserMaintForm" property="userRolesList">
					<html:optionsCollection name="UserMaintForm" 
							property="userRolesList" value="roleName" label="roleName" />
					</logic:present>
				</html:select>	
			</div>
		</div>
		
		<div class="form-group required-field">
			<label class="col-sm-5 control-label">Subject</label>
			<div class="col-sm-2">
				<html:text property="subject" styleClass="form-control" size="46" maxlength="2000" tabindex="3" />
			</div>
		</div>
		
		<div class="form-group required-field">
			<label class="col-sm-5 control-label">Body</label>
			<div class="col-sm-2">
				<html:textarea styleId="clauseDesc_id" name="UserMaintForm" property="body" 
					rows="4" cols="50" styleClass="form-control"></html:textarea>
				<textarea name="clauseDesc" id="hdnclauseDescForTextArea"
					style="display:none;"><bean:write name="UserMaintForm" property="body"/></textarea>
			</div>
		</div>
		
 		<div class="spacer10"></div>
	</div>
	
	<html:hidden property="hdnUserID" />
	<html:hidden property="hdnFirstName" />
	<html:hidden property="hdnLastName" />
	<html:hidden property="hdnLocation" />
	<html:hidden property="hdnEmail" />
	<html:hidden property="hdnUserRole" />
	<html:hidden property="orderbyFlag"/>
	<html:hidden property="columnheader"/>


	<logic:greaterThan name="userlist" value="0">
		<HR>
		<TABLE class="table table-bordered table-hover" id="tbBroadcastEmails">
			<thead>
				<TR>
					<TH WIDTH="5%" CLASS='text-center'>Select</TH>
					<TH WIDTH="10%" CLASS='text-center'>UserId</TH>
					<TH WIDTH="10%" CLASS='text-center'>First Name</TH>
					<TH WIDTH="10%" CLASS='text-center'>Last Name</TH>
					<TH WIDTH="10%" CLASS='text-center'>Location</TH>
					<TH WIDTH="15%" CLASS='text-center'>Email Address</TH>
					<TH WIDTH="15%" CLASS='text-center'>User Role</TH>
				</TR>
			</thead>
			
			<tbody>
				<logic:iterate id="user" name="UserMaintForm" property="userDetails"
					scope="request" type="com.EMD.LSDB.vo.admn.UserVO">
					<TR>
						<TD width="5%" CLASS='text-center v-midalign'>
							<input type="checkbox"	name="userID" class="userIDChkbox" 
							value="<bean:write name="user" property="userrId"/>" />
						</TD>
	
						<TD width="5%" CLASS='text-center v-midalign'><bean:write name="user"
							property="userrId" /> 
						</TD>
	
						<TD width="10%" CLASS='text-center v-midalign'><bean:write name="user"
							property="firsttName" /></TD>
	
						<TD width="10%" CLASS='text-center v-midalign'><bean:write name="user"
							property="lasttName" /></TD>
	
						<TD width="10%" CLASS='text-center v-midalign'><bean:write name="user"
							property="location" /></TD>
	
						<TD width="10%" CLASS='text-center v-midalign'><bean:write name="user"
							property="emaillId" /></TD>
	
						<TD width="10%" CLASS='text-center v-midalign'><bean:write name="user"
							property="roleName" />
						</TD>
					</TR>
				</logic:iterate>
			</tbody>

		</TABLE>
		<div class="spacer20"></div>
		
		<div class="form-group">
			<div class="col-sm-12 text-center">
				<button class="btn btn-primary" type='button' id="Send" ONCLICK="javascript:fnbroadcastEmail()">Send</button>
			</div>
	 	</div>
	 	
	</logic:greaterThan>

</html:form>
</div>
<div class="spacer50"></div>
