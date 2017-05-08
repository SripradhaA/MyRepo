<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<script type="text/JavaScript">
$(document).ready(function() {  
	  $.fn.dataTable.moment( 'DD-MMM-YYYY HH:mm:ss' ); 
	  $('#tbActivityLog').DataTable({
	 	 	 /*Pagination added for CR-135*/	  
	  		 aLengthMenu: [[50, 100, 150,200, -1], [50, 100, 150, 200, "All"]],
		 	 paging:   true,
	    	 pageLength: 200,
	         ordering: true,
	         info:     false,
	         order: [[ 1, 'desc' ]]
	  });
 });
</script>

<div class="container" width="80%">
       <html:form action="/UserMaintenanceAction" method="post">
       
              <h4 class="text-center"><bean:message key="Application.Screen.ActivityReport" /></h4>
              
              <div class="spacer30"></div>
              
              <logic:present name="UserMaintForm" property="activityLog">
                     <bean:size id="ActivityDetleng" name="UserMaintForm"
                           property="activityLog" />
              </logic:present>
              
              <div class="errorlayerhide" id="errorlayer">
			  </div>
                     
                     <logic:present name="UserMaintForm" property="messageID"
                           scope="request">
                       <bean:define id="messageID" name="UserMaintForm" property="messageID"/>
                       <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
                    <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
                     </logic:present>
                     
                     <logic:match name="UserMaintForm" property="method"
                           value="fetchActivityLog">
                           <logic:lessThan name="ActivityDetleng" value="1">
                                  <script>
                                  //Updated for CR-121 server side error message
                                  fnNoRecords();
                                  </script>
                           </logic:lessThan>
                     </logic:match>
                     
              <logic:greaterThan name="ActivityDetleng" value="0">
              
              
               <TABLE class="table table-bordered" id="tbActivityLog">
                    <thead>
                           <tr>
                                  <TH WIDTH="10%" CLASS='text-center'>Action Taken By</TH>
                                  <TH WIDTH="10%" CLASS='text-center'>Action Taken On</TH>
                                  <TH WIDTH="10%" CLASS='text-center'>Entity</TH>
                                  <TH WIDTH="10%" CLASS='text-center'>Action</TH>
                                  <TH WIDTH="18%" CLASS='text-center'>Modified For</TH> 
                                  <TH WIDTH="15%" CLASS='text-center'>Value Before Action</TH>
                                  <TH WIDTH="15%" CLASS='text-center'>Value After Action</TH>
                           </tr>               
                     </thead>
                    
                     <tbody>
                           <logic:iterate id="actLog" name="UserMaintForm" property="activityLog"
                                         scope="request" type="com.EMD.LSDB.vo.admn.UserVO">
                                  <TR>
                                                
                                                <TD width="15%" CLASS='text-center'><bean:write name="actLog"
                                                       property="actionBy" /></TD>
                                                       
                                                <TD width="15%" CLASS='text-center' data-sort="<bean:write name="actLog"
                                                       property="actionOn" />"><bean:write name="actLog"
                                                       property="actionOn" /></TD>
              
                                                <TD width="10%" CLASS='text-center'><bean:write name="actLog"
                                                       property="eventType" /> 
                                                </TD>
              
                                                <TD width="10%" CLASS='text-center'><bean:write name="actLog"
                                                       property="activityType" /></TD>
                                                
                                                <TD width="18%" CLASS='text-center'>
                                                       <logic:notEmpty name="actLog" property="modifiedFor">    
                                                              <bean:write name="actLog" property="modifiedFor" />
                                                       </logic:notEmpty>&nbsp;
                                             </TD>
                                                
                                                       
                                                <TD width="15%" CLASS='text-center'>
                                                       <logic:notEmpty name="actLog" property="eventFrom">
                                                              <bean:write name="actLog" property="eventFrom" />
                                                       </logic:notEmpty>&nbsp;
                                                </TD>
                                                
                                                <TD width="15%" CLASS='text-center'>
                                                       <logic:notEmpty name="actLog" property="eventTo">       
                                                              <bean:write name="actLog" property="eventTo" />
                                                       </logic:notEmpty>&nbsp;
                                                </TD>
                                                
                                         </TR>  
                           </logic:iterate>           
                    
                     </tbody>
              </TABLE>
	   
	   </logic:greaterThan>
       <div class="spacer50"></div>
       </html:form>
</div>
