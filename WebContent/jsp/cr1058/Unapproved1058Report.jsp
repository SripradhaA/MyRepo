<!DOCTYPE HTML>
<HTML>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link rel="shortcut icon" href="images/favicon.ico" />
	<link href="css/bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/datatables.min.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/EMDCustom.css" rel="stylesheet"/>
	<script type="text/javascript" src="js/Others/jquerylatest.js"></script>
	<script type="text/javascript" src="js/Others/jquerymigrate.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/Others/datatables.min.js"></script>
	<script type="text/javascript" src="js/Others/datatables.plugins.js"></script>
	<SCRIPT type="text/javascript" SRC="js/Emd_Lsdb.js"></SCRIPT>
	<SCRIPT type="text/javascript" SRC="js/error.js"></SCRIPT>
	<SCRIPT type="text/javascript" SRC="js/error_messages.js"></SCRIPT>
	<script>
		$(document).ready(function() {
		    $('#table1058Unapproved').DataTable({
		    	 paging:   false,
		         ordering: true,
		         info:     false,
		         columnDefs: [
                       { "type": "natural", "targets": [0] }
                 ]
		    });
		});
	</script>
</head>
<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>

<div class="container" width="100%">

<html:form action="/SearchRequest1058Action" method="post">

	<h4 class="text-center">Unapproved 1058's with Clauses Applied</h4>
	
	<div class="spacer20"></div>
	
	<div class="errorlayerhide" id="errorlayer">
	</div>
	
	<logic:present name="SearchRequest1058Form" property="unapproved1058ReportDetails">
			<bean:size id="RepDetailsLen" name="SearchRequest1058Form"
			property="unapproved1058ReportDetails" />
	</logic:present>

	<logic:present name="SearchRequest1058Form" property="messageID"
		scope="request">
	  <bean:define id="messageID" name="SearchRequest1058Form" property="messageID"/>
	  <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
      <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
	</logic:present>

	<logic:match name="SearchRequest1058Form" property="method"
		value="viewUnapproved1058withClausesApplied">
		<logic:lessThan name="RepDetailsLen" value="1">
			<script>
			fnNoRecords();
			</script>			
		</logic:lessThan>
	</logic:match>
	
	<logic:equal name="RepDetailsLen" value="0">	
		<div class="form-group">
			<div class="col-sm-12 text-center">
				<button class="btn btn-primary" type='button' ONCLICK="window.close();">Close</button>
			</div>
		</div>
	</logic:equal>
	

	<logic:greaterThan name="RepDetailsLen" value="0">
		<TABLE class="table table-bordered" id="table1058Unapproved">
			<thead>
				<TR>
					<TH width="15%">1058 Number</TH>
					<TH width="15%">1058 Status</TH>	
					<TH width="20%">Customer Name</TH>
					<TH width="15%">System Engineer Name</TH>
				</TR>
			</thead>
			
			<tbody>
				<logic:iterate id="repDetails" name="SearchRequest1058Form"
					property="unapproved1058ReportDetails" scope="request" >
					<TR>
						<TD CLASS='text-center v-midalign'>
		  	              <bean:write name="repDetails" property="number1058" />                     					
						</TD>
						<TD CLASS='text-center v-midalign'>
		  	              <bean:write name="repDetails" property="statusDesc" />                     					
						</TD>
					    <TD CLASS='text-center v-midalign'>
		  	              <bean:write name="repDetails" property="custName" />                     					
						</TD>
						<logic:present name="repDetails" property="sysEngineerName">
							<TD CLASS='text-center v-midalign'>
								<bean:write name="repDetails" property="sysEngineerName" />
							</TD>
						</logic:present>
						<logic:notPresent name="repDetails" property="sysEngineerName">
							<TD CLASS='text-center v-midalign'>&nbsp;</TD>
						</logic:notPresent>
					</TR>
				</logic:iterate>
			</tbody>
		</TABLE>
		
		<div class="spacer10"></div>
		
		<div class="form-group">
			<div class="col-sm-12 text-center">
				<button class="btn btn-primary" type='button' id="btnPrint" ONCLICK="window.print();">Print</button>
				<button class="btn btn-primary" type='button' id="btnCancel" ONCLICK="window.close();">Cancel</button>
			</div>
		</div>
	</logic:greaterThan>	
	
	<div class="spacer50"></div>
		
</html:form>
</div>
</html>