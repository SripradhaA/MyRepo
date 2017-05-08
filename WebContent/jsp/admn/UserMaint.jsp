<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<SCRIPT type="text/JavaScript" SRC="js/UserMaintenance.js"></SCRIPT>

<script>
        $(document).ready(function() { 
       		$.fn.dataTable.moment( 'DD-MMM-YYYY HH:mm:ss' ); 
        	$("#sltRoleID").select2({theme:'bootstrap'});  
		    $('#tbUserMaint').DataTable({
		    	 paging:   false,
		         ordering: true,
		         info:     false,
		         order: [[ 2, 'asc' ]],
		         columnDefs: [
	                       { "orderable": false, "targets": [0,5,6] },
	                       { "searchable": false, "targets": [0] }
	                 ]
		   		});
        })
</script>

<div class="container-fluid">

	<html:form action="/UserMaintenanceAction" method="post" styleClass = "form-horizontal">
	
	<h4 class="text-center"><bean:message key="Application.Screen.UserMaintenance" /></h4>
	
	<!-- To display  Messages - Start -->
		<%--Table Updated for CR-121 for server side error message tooltip --%>
		<div class="row">
		<div class="errorlayerhide" id="errorlayer">
		</div></div>
		<!-- To display Messages - End -->
	
		<!-- To get User List Size Form - Start Here-->
	
		<logic:present name="UserMaintForm" property="userDetails"
			scope="request">
			<bean:size id="userlist" name="UserMaintForm" property="userDetails" />
		</logic:present>
	
		<!-- To get User List Size Form - Ends Here -->
	
		<!-- To display Server Side Validation Messages - Start -->
	
		<logic:present name="UserMaintForm" property="messageID"
			scope="request">
		  <%--Added for CR-121 for server side error message tooltip --%>	
		  <bean:define id="messageID" name="UserMaintForm" property="messageID"/>
		  <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
	      <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
		</logic:present>
	
		<!-- To display Server Side Validation Messages - End -->
		
		<div class="spacer30"></div>
		
		<div>
				<div class="form-group required-field">
					<div class="multipleFormFields">
						<label class="col-sm-offset-1 col-sm-2 control-label">User ID</label>
						<div class="col-sm-2">
							<html:text property="userId" styleClass="form-control partOfColFormField" maxlength="10" tabindex="1" />
						</div>
					</div>
					<div class="multipleFormFields">
						<label class="col-sm-offset-1 col-sm-2 control-label">Email Address</label>
						<div class="col-sm-2">
							<html:text property="emailId" styleClass="form-control partOfColFormField" maxlength="40" tabindex="5" />
						</div>
					</div>
				</div>
				
				<div class="form-group required-field">
					<div class="multipleFormFields">
						<label class="col-sm-offset-1 col-sm-2 control-label">First Name</label>
						<div class="col-sm-2">
							<html:text property="firstName" styleClass="form-control partOfColFormField" maxlength="50" tabindex="2" />
						</div>
					</div>
					<div class="multipleFormFields">
						<label class="col-sm-offset-1 col-sm-2 control-label">Location</label>
						<div class="col-sm-2">
							<html:text property="locationName" styleClass="form-control partOfColFormField" maxlength="20" tabindex="6" />
						</div>
					</div>
				</div>
				
				<div class="form-group required-field">
					<div class="multipleFormFields">
						<label class="col-sm-offset-1 col-sm-2 control-label">Last Name</label>
						<div class="col-sm-2"><html:text property="lastName" styleClass="form-control partOfColFormField" maxlength="50" tabindex="3" /></div>
					</div>
					<div class="multipleFormFields">
						<label class="col-sm-offset-1 col-sm-2 control-label">Contact Phone Number</label>
						<div class="col-sm-2"><html:text property="contactNo" styleClass="form-control partOfColFormField" maxlength="50" tabindex="7" /></div>
					</div>
				</div>
				
				<div class="form-group required-field">
					<div class="multipleFormFields">
						<label class="col-sm-offset-1 col-sm-2 control-label">Password</label>
						<div class="col-sm-2"><html:password property="password" styleClass="form-control partOfColFormField" maxlength="12" tabindex="4" /></div>
					</div>
					<div class="multipleFormFields">
						<label class="col-sm-offset-1 col-sm-2 control-label">User Role</label>
						<div class="col-sm-2">
								<html:select styleId="sltRoleID" styleClass="form-control partOfColFormField"
											name="UserMaintForm" property="roleid">
											<OPTION value="-1">--Select--</OPTION>
											<html:optionsCollection name="UserMaintForm"
												property="userRolesList" value="roleID" label="roleName" />
							  </html:select>
						</div>
					</div>
				</div>
			</div>
			
			<div class="spacer10"></div>
		
			<div class="form-group">
					<div class="col-sm-12 text-center">
						<button class="btn btn-primary addbtn" type='button' id="addbtn" ONCLICK="javascript:fnAddUser()">Add User</button>
					</div>
			 </div>
			 
			 <div class="spacer10"></div>
		
	
		<html:hidden property="hdnUserID" />
		<html:hidden property="hdnFirstName" />
		<html:hidden property="hdnLastName" />
		<html:hidden property="hdnLocation" />
		<html:hidden property="hdnEmail" />
		<html:hidden property="hdnContactNo" />
		<html:hidden property="hdnUserRole" />
		<html:hidden property="orderbyFlag"/>
		<html:hidden property="columnheader"/>
	
		<logic:greaterThan name="userlist" value="0">
			<div class="row">
				<HR>
			</div>
			<div class="spacer10"></div>
			<TABLE class="table table-bordered table-hover" id="tbUserMaint">
				<thead>
				<TR>
					<TH WIDTH="5%" CLASS='text-center'>Select</TH>
					<TH WIDTH="10%" CLASS='text-center'>UserId</TH>
					<TH WIDTH="10%" CLASS='text-center'>First Name</TH>
					<TH WIDTH="10%" CLASS='text-center'>Last Name</TH>
					<TH WIDTH="10%" CLASS='text-center'>Location</TH>
					<TH WIDTH="15%" CLASS='text-center'>Email Address</TH>
					<TH WIDTH="15%" CLASS='text-center' nowrap>Contact Phone Number</TH>
					<TH WIDTH="15%" CLASS='text-center'>User Role</TH>
					<TH WIDTH="10%" CLASS='text-center' nowrap>Last Logged In</TH>
				</TR>
				</thead>
				
				<tbody>
					<logic:iterate id="user" name="UserMaintForm" property="userDetails"
					scope="request" type="com.EMD.LSDB.vo.admn.UserVO">
					<TR>	
						<TD CLASS='text-center v-midalign'><html:radio
							value="" styleClass="userRadio" property="userSeqNo" /></TD>
						<TD CLASS='text-center v-midalign'><bean:write name="user"
							property="userrId" /><html:hidden name="user" property="userrId" /></TD>	
						<TD CLASS='text-center v-midalign' data-sort="<bean:write name="user" property="firsttName" />" data-search="<bean:write name="user" property="firsttName" />">
							<html:text name="user"
							property="firsttName" styleClass="form-control" size="15" maxlength="50" /></TD>	
						<TD CLASS='text-center v-midalign' data-sort="<bean:write name="user" property="lasttName" />" data-search="<bean:write name="user" property="lasttName" />">
							<html:text name="user"
							property="lasttName" styleClass="form-control" size="15" maxlength="50" /></TD>	
						<TD CLASS='text-center v-midalign' data-sort="<bean:write name="user" property="location" />" data-search="<bean:write name="user" property="location" />">
							<html:text name="user"
							property="location" styleClass="form-control" size="15" maxlength="20" /></TD>	
						<TD CLASS='text-center v-midalign' data-search="<bean:write name="user" property="emaillId" />"><html:text name="user"
							property="emaillId" styleClass="form-control" maxlength="40" /></TD>	
						<TD CLASS='text-center v-midalign' data-search="<bean:write name="user" property="contacttNo" />"><html:text name="user"
							property="contacttNo" size="13" styleClass="form-control" /></TD>	
						<TD CLASS='text-center v-midalign' data-sort="<bean:write name="user" property="roleName" />" data-search="<bean:write name="user" property="roleName" />">
							<html:select name="user" style="width:150px;" property="role" styleClass='form-control'>
								<option selected value="-1">---Select---</option>
								<logic:present name="UserMaintForm" property="userRolesList">
									<html:optionsCollection name="UserMaintForm" property="userRolesList" value="roleID" label="roleName" />
								</logic:present>
							</html:select>
						</TD>				
						<TD CLASS='text-center v-midalign' data-sort="<bean:write name='user' property='prevLoggedIn' />" data-search="<bean:write name='user' property='prevLoggedIn' />">
							<bean:write name="user"	property="prevLoggedIn" /><html:hidden name="user" property="prevLoggedIn" />
						</TD>	
					</TR>
	
					</logic:iterate>
				</tbody>
			</TABLE>
			
			<div class="spacer10"></div>
			
			<div class="form-group">
				<div class="col-sm-12 text-center">
					<button class="btn btn-primary" type='button' id="ModUser" ONCLICK="javascript:fnModifyUser()">Modify User</button>
					<button class="btn btn-primary" type='button' id="DelUser" ONCLICK="javascript:fnDeleteUser()">Delete User</button>
				</div>
			</div>
			<div class="spacer10"></div>	
			<div class="form-group">
				<div class="col-sm-12 text-center">
					<button class="btn btn-primary" type='button' id="ExpToExcel" ONCLICK="javascript:fnExportDetailsToExcel()">Export all user details to Excel</button>
				</div>
		 	</div>
	 	
	 	
	 	
	 	
		</logic:greaterThan>
	<div class="spacer100"></div>
	</html:form>
</div>	
