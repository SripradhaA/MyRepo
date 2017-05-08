<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<SCRIPT type="text/JavaScript" SRC="js/UserMaintenance.js"></SCRIPT>

<script>
$(document).ready(function() {  
	  $('#tblManageEmail').DataTable({
	    	 paging:   false,
	         ordering: true,
	         info:     false,
	         order: [[ 0, 'asc' ]],
	         columnDefs: [
                    { "orderable": false, "targets": [3,4,5] },
                    { "searchable": false, "targets": [3,4,5] }
              ]
	  }); 
 });
</script>

<div class="container" width="80%">

       <html:form styleClass="form-horizontal" action="/UserMaintenanceAction" method="post">

           <h4 class="text-center"><bean:message key="Application.Screen.ManageEmailSubscriptions" /></h4>
                     
              <div class="spacer30"></div>
                           
              <div class="errorlayerhide" id="errorlayer">
			  </div>
       
                     <logic:present name="UserMaintForm" property="userDetails"
                           scope="request">
                           <bean:size id="userlist" name="UserMaintForm" property="userDetails" />
                     </logic:present>
              
                     <logic:present name="UserMaintForm" property="messageID"
                           scope="request">
                       <%--Added for CR-121 for server side error message tooltip --%>    
                       <bean:define id="messageID" name="UserMaintForm" property="messageID"/>
                       <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
                    <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
                     </logic:present>
              
                
                     <!-- Added for CR-112 for Sorting -->
                     <html:hidden property="orderbyFlag"/>
                     <html:hidden property="columnheader"/>
                     <!-- Added for CR-112 for Sorting Ends Here-->
                     <div class="spacer10"></div>
                           
                           <TABLE class="table table-bordered" id="tblManageEmail">
                                         <thead>
                                                <tr>
                                                       <TH  WIDTH="15%" CLASS='text-center' rowspan="2" >First Name</TH>
                                                       <TH  WIDTH="15%" CLASS='text-center' rowspan="2" >Last Name</TH>
                                                       <TH  WIDTH="20%" CLASS='text-center' rowspan="2" >User Role</TH>      
                                                       <TH  WIDTH="15%" CLASS='text-center'>1058 Action Notices</TH>
                                                       <TH  WIDTH="15%" CLASS='text-center'>1058 Information Notices</TH>   
                                                       <TH  WIDTH="15%" CLASS='text-center'>1058 CC Notices</TH>
                                         
                                                </TR>
                                         
                                                <TR>
                                                       <TD CLASS='text-center' nowrap ><a href="#" onclick="checkAllAction()" >Check All</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a CLASS=linkstyleItem href="#" onclick="clearAllAction()">Clear All</a></TD>
                                                       <TD CLASS='text-center' nowrap ><a href="#" onclick="checkAllInformation()" >Check All</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a CLASS=linkstyleItem href="#" onclick="clearAllInformation()">Clear All</a></TD>
                                                       <TD CLASS='text-center' nowrap ><a href="#" onclick="checkAllCCNotice()" >Check All</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a CLASS=linkstyleItem href="#" onclick="clearAllCCNotice()">Clear All</a></TD>
                                                </TR>
                                         </thead>
		                                  <logic:iterate id="user" name="UserMaintForm" property="userDetails"
		                                         scope="request" type="com.EMD.LSDB.vo.admn.UserVO">
                                         <TR class="rowid">                                  
                                                <TD CLASS='text-center'>
			                                         <logic:notEqual name="user" property="roleName" value="Disabled">
			                                                <html:hidden name="user" property="userrId" />
			                                         </logic:notEqual>    
                                                       <bean:write name="user"    property="firsttName" /></TD>
              
                                                <TD CLASS='text-center'>
                                                       <bean:write name="user"    property="lasttName" /></TD>
              
                                                <TD CLASS='text-center' id="roleName">
                                                       <bean:write name="user" property="roleName" /></TD>
                                                       
                                                <TD CLASS='text-center' >
                                                <logic:notEqual name="user" property="roleName" value="Disabled">
                                                       <logic:equal name="user" property="actionNoticeFlag" value="Y">
                                                              <input type="checkbox" name="actionNoticeFlag" checked="checked" />   
                                                       </logic:equal>
                                                       <logic:equal name="user" property="actionNoticeFlag" value="N">
                                                              <input type="checkbox" name="actionNoticeFlag"/> 
                                                       </logic:equal>
                                                </logic:notEqual>
                                                <logic:equal name="user" property="roleName" value="Disabled">
                                                       <input type="checkbox" name="dsbActionNoticeFlag" disabled="true" /> 
                                                </logic:equal>
                                                       
                                                </TD>
                                                       
                                                <TD CLASS='text-center' >
                                                <logic:notEqual name="user" property="roleName" value="Disabled">
                                                       <logic:equal name="user" property="informationNoticeFlag" value="Y">
                                                              <input type="checkbox" name="informationNoticeFlag" checked="checked" />   
                                                       </logic:equal>
                                                       <logic:equal name="user" property="informationNoticeFlag" value="N">
                                                              <input type="checkbox" name="informationNoticeFlag" />
                                                       </logic:equal>
                                                </logic:notEqual>
                                                <logic:equal name="user" property="roleName" value="Disabled">
                                                       <input type="checkbox" name="dsbInformationNoticeFlag" disabled="true"/> 
                                                </logic:equal>       
                                                </TD>  
                                                <TD CLASS='text-center'>
                                                <logic:notEqual name="user" property="roleName" value="Disabled">
                                                       <logic:equal name="user" property="ccNoticeFlag" value="Y">
                                                              <input type="checkbox" name="ccNoticeFlag" checked="checked" />
                                                       </logic:equal>
                                                       <logic:equal name="user" property="ccNoticeFlag" value="N">
                                                              <input type="checkbox" name="ccNoticeFlag" />
                                                       </logic:equal>       
                                                </logic:notEqual>
                                                <logic:equal name="user" property="roleName" value="Disabled">
                                                       <input type="checkbox" name="dsbCcNoticeFlag" disabled="true"/> 
                                                </logic:equal>       
                                                </TD>  
              
                                         </TR>
              
                                  </logic:iterate>
              
                           </TABLE>
                           
                           <div class="spacer10"></div>
                           
                           <div class="form-group">
	                           <logic:equal name="UserMaintForm" property="emailPeriod" value="0">
						  			<div class="col-sm-offset-3 col-sm-3 text-right custom-checkbox">
						  				<label><input type="checkbox" id="chkScheduletime"></label>
						   				&nbsp;&nbsp;Send reminder emails for every
							   		</div>
							   		<div class="col-sm-1 text-left" style="display:inline;">
							   			<html:text size="2" name="UserMaintForm" styleClass="form-control" property="emailPeriod" styleId="emailPeriod" maxlength="3" disabled="true"/>
							   		</div>
							   		<span style="display:inline;line-height:25px">Hours</span>
							   	</logic:equal>
							   	<logic:notEqual name="UserMaintForm" property="emailPeriod" value="0">
							   		<div class="col-sm-offset-3 col-sm-3 text-right custom-checkbox">
						  				<label><input type="checkbox" id="chkScheduletime" checked></label>
						   			 	&nbsp;&nbsp;Send reminder emails for every
							   		</div>
							   		<div class="col-sm-1 text-left" style="display:inline;">
							   			<html:text size="2" name="UserMaintForm" styleClass="form-control" property="emailPeriod" styleId="emailPeriod" maxlength="3" />
							   		</div>
							   		<span style="display:inline;line-height:25px">&nbsp;Hours</span>
							   	</logic:notEqual>
					  		</div>
                           
                           <div class="spacer10"></div>
                           
                           <div class="form-group">
                                  <div class="col-sm-12 text-center">
                                         <button class="btn btn-primary addbtn" type='button' id="SaveEmailSubscr" ONCLICK="javascript:fnSave()">Save</button>
                                  </div>
                           </div>
                           
                     <html:hidden property="hdnActionNoticeFlag" />
                     <html:hidden property="hdnInformationNoticeFlag" />
                     <html:hidden property="hdnCCNoticeFlag" />
                           
       </html:form>
</div>

<div class="spacer100"></div>


