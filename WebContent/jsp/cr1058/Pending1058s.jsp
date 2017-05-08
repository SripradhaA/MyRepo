<!DOCTYPE HTML>
<HTML>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<HEAD> 
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
</HEAD>

<script>  	
	$(document).ready(function() {
	    $('#tablePending').DataTable({
	    	 paging:   false,
	         ordering: true,
	         info:     false,
	         columnDefs: [
                       { "type": "natural", "targets": [0] }
                 ]
	    });
       })
</script>

<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>

<div class="container" width="100%">

<html:form action="/SearchRequest1058Action" method="post" styleClass="form-horizontal">

	<h4 class="text-center">Open/Pending 1058s</h4>
	
	<div class="spacer20"></div>
	
	<div class="errorlayerhide" id="errorlayer">
	</div>
	
	<logic:present name="SearchRequest1058Form" property="pending1058sList">
			<bean:size id="ReportDetLen" name="SearchRequest1058Form"
			property="pending1058sList" />
	</logic:present>

	<logic:present name="SearchRequest1058Form" property="messageID"
		scope="request">
	  <bean:define id="messageID" name="SearchRequest1058Form" property="messageID"/>
	  <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
      <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
	</logic:present>
	
	<logic:match name="SearchRequest1058Form" property="method"
		value="viewPending1058s">
		<logic:lessThan name="ReportDetLen" value="1">
			<script>
			fnNoRecords();
			</script>			
			<div class="form-group">
				<div class="col-sm-12 text-center">
					<button class="btn btn-primary" type='button' ONCLICK="window.close();">Close</button>
				</div>
			</div>
		</logic:lessThan>
	</logic:match>
	
	<logic:greaterThan name="ReportDetLen" value="0">
		<TABLE class="table table-bordered" id="tablePending">
			<thead>
				<TR>
					<TH width="15%">1058 number</TH>
					<TH width="15%">Customer name</TH>
					<TH width="15%">System engineer name</TH>	
					<TH width="20%">1058 status</TH>
					<TH width="15%">Awaiting an action from staff members</TH>
					<TH width="15%">Number of days since last transitions</TH>
				</TR>	
			</thead>
			<tbody>
				<logic:iterate id="repDetails" name="SearchRequest1058Form"
					property="pending1058sList" scope="request" >
					<TR>
						<TD CLASS='text-center v-midalign'>
		  	              <bean:write name="repDetails" property="id1058" />                     					
						</TD>
						
		  	            <logic:present name="repDetails" property="custName">
							<TD CLASS='text-center v-midalign'>
								<bean:write name="repDetails" property="custName" />
							</TD>
						</logic:present>
						<logic:notPresent name="repDetails" property="custName">
							<TD CLASS='text-center v-midalign'>&nbsp;</TD>
						</logic:notPresent>
						
						<logic:present name="repDetails" property="sysEngineerName">
							<TD CLASS='text-center v-midalign'>
								<bean:write name="repDetails" property="sysEngineerName" />
							</TD>
						</logic:present>
						<logic:notPresent name="repDetails" property="sysEngineerName">
							<TD CLASS='text-center v-midalign'>&nbsp;</TD>
						</logic:notPresent>
						    <TD CLASS='text-center v-midalign'>
			  	              <bean:write name="repDetails" property="statusDesc" />                     					
							</TD>
						<logic:present name="repDetails" property="awaitingActionOnName">
							<TD CLASS='text-center v-midalign'>
								<bean:write name="repDetails" property="awaitingActionOnName" />
							</TD>
						</logic:present>
						<logic:notPresent name="repDetails" property="awaitingActionOnName">
							<TD CLASS='text-center v-midalign'>&nbsp;</TD>
						</logic:notPresent>
						<logic:present name="repDetails" property="lastTransc">
							<TD CLASS='text-center v-midalign' data-sort="<bean:write name="repDetails" property="lastTransc" />">
								<bean:write name="repDetails" property="lastTransc" />
							</TD>
						</logic:present>
						<logic:notPresent name="repDetails" property="lastTransc">
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