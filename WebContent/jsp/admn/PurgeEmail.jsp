<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<SCRIPT type="text/JavaScript" SRC="js/UserMaintenance.js"></SCRIPT>
<script type="text/JavaScript">
$(document).ready(function() { 
	  $.fn.dataTable.moment('DD-MMM-YY HH:mm:ss');   
	  $('#tbPurgeEMail').DataTable({
	    	 paging:   false,
	         ordering: true,
	         info:     false,
	         order: [[ 3, 'desc' ]],
	         columnDefs: [
	                       { "orderable": false, "targets": [0,1,2] },
	                       { "searchable": false, "targets": [0] }
	                 ]
	  });
 });
</script>

<div class="container" width="80%">
	<html:form action="/UserMaintenanceAction" method="post">
	
	<h4 class="text-center"><bean:message key="Application.Screen.PurgeEmail" /></h4>
		
	<logic:present name="UserMaintForm" property="emailDetails">
		<bean:size id="emailDetailsLen" name="UserMaintForm"
			property="emailDetails" />
	</logic:present>
		
	<!-- To display  Messages - Start -->
	<%--Table Updated for CR-121 for error message tooltip --%>
	<div class="errorlayerhide" id="errorlayer">
	</div>
	<!-- To display Messages - End -->
		
	<logic:present name="UserMaintForm" property="messageID"
		scope="request">
	<%--Added for CR-121 for server side error message tooltip --%>	
	  <bean:define id="messageID" name="UserMaintForm" property="messageID"/>
	  <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
      <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
	</logic:present>
	
	<!-- Validation Message For No Records Found - Start -->
	<logic:match name="UserMaintForm" property="method"
		value="fetchUsers">
		<logic:lessThan name="emailDetailsLen" value="1">
			<script>
			//Updated for CR-121 server side error message
			fnNoRecords();
			</script>
		</logic:lessThan>
	</logic:match>
	<!-- Validation Message For No Records Found - End -->
	<div class="spacer50"></div>
	<logic:greaterThan name="emailDetailsLen" value="0">
		<DIV class="col-sm-5 text-left"><a id="chk" href="#chk">Check All</a>&nbsp;|&nbsp;<a id="unchk" href="#unchk">Clear All</a></DIV>
		<TABLE class="table table-bordered" id="tbPurgeEMail">
			<thead>
				<TR>
					<TH WIDTH="5%" CLASS='text-center'>Select</TH>
					<TH WIDTH="20%" CLASS='text-center'>To Email Address</TH>
					<TH WIDTH="20%" CLASS='text-center'>Subject</TH>
					<TH WIDTH="15%" CLASS='text-center'>Last Attempt to Send</TH>
				</TR>
			</thead>
			
			<tbody>
				<html:hidden property="hdnSeqNo" />
				<logic:iterate id="email" name="UserMaintForm" property="emailDetails"
					scope="request" type="com.EMD.LSDB.vo.admn.UserVO">
					<TR>
					
						<TD width="5%" CLASS='text-center'>
							<input type="checkbox"	name="emailID" class="emailIDChkbox" 
							value="<bean:write name="email" property="seqNo"/>" />
						</TD>
	
						<TD width="20%" CLASS='text-center'><bean:write name="email"
							property="toEmailId" /> 
						</TD>
	
						<TD width="20%" CLASS='text-center'><bean:write name="email"
							property="emailSubject" /></TD>
						
						<logic:present name="email" property="sentTime">
						<TD width="15%" CLASS='text-center'><bean:write name="email"
						property="sentTime" /></TD>
						</logic:present>
						<logic:notPresent name="email" property="sentTime">
						<TD width="10%" CLASS='text-center'></TD>
						</logic:notPresent>
					</TR>
				</logic:iterate>
			</tbody>
		</TABLE>
		<div class="spacer10"></div>
		<div class="form-group">
				<div class="col-sm-12 text-center">
					<button class="btn btn-primary" type='button' id="btnPurge" ONCLICK="javascript:fnpurgeEmail()">Purge Email</button>
				</div>
	 	</div>
	</logic:greaterThan>	
	</html:form>
<div class="spacer50"></div>
</div>
	