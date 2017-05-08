<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<link href="css/bootstrap-datepicker.min.css" type="text/css" rel="stylesheet"/>
<SCRIPT type="text/javascript" SRC="js/Others/bootstrap.datepicker.min.js"></script>
<SCRIPT type="text/JavaScript" SRC="js/1058SearchRequest.js"></SCRIPT>
<script>  	
	$(document).ready(function() {
		$.fn.dataTable.moment( 'DD-MMM-YYYY HH:mm:ss' ); 
	    $('#1058TransitionedSummary').DataTable({
	    	 paging:   false,
	         ordering: true,
	         info:     false,
	         columnDefs: [
                       {"type": "natural", "targets": [0]}
                 ]
	    });
       })
</script>
<div class="container-fluid">
<html:form action="/SearchRequest1058Action.do" styleClass="form-horizontal">

	<h4 class="text-center"><bean:message key="Application.Screen.1058Reports" /></h4>
	<div class="row">
	<div class="errorlayerhide" id="errorlayer">
		</div>
	</div>
	<logic:present name="SearchRequest1058Form" property="transitionSummary1058List">
			<bean:size id="ReportLen" name="SearchRequest1058Form"
			property="transitionSummary1058List" />
	</logic:present>
	
	<logic:match name="SearchRequest1058Form" property="method" value="showSummary1058Transitioned">
		<logic:lessThan name="ReportLen" value="1">
			<script>
			fnNoRecords();
			</script>
		</logic:lessThan>
	</logic:match>
	
	<div class="spacer10"></div>
	<div class="panel panel-info">
		<div class="panel-heading">
			<h4 class="panel-title"><strong>List of unapproved 1058s which have clauses applied to order</strong></h4>
		</div>
		
		<div class="panel-body">
			<ul>
				<li><A HREF="javascript:fnViewUnapproved1058Report()">View Report</A></li>
				<BR>
				<li><A HREF="javascript:fnExportUnapproved1058Report()">Export Report to Excel</A></li>
			</ul>
		</div>
	</div>
	
	<div class="spacer10"></div>
	
	<div class="panel panel-info">
		<div class="panel-heading">
			<h4 class="panel-title"><strong>Detailed listing of clauses applied to order specs for unapproved 1058s</strong></h4>
		</div>
		
		<div class="panel-body">
			<ul> 
				<li><A HREF="javascript:fnView1058ClauseAppliedtoSpec()">View Report</A></li>
				<BR>
				<li><A HREF="javascript:fnExport1058ClauseAppliedtoSpec()">Export Report to Excel</A></li>
			</ul>
		</div>
	</div>
	
	<div class="spacer10"></div>
	
	<div class="panel panel-info">
		<div class="panel-heading">
			<h4 class="panel-title"><strong>Open/Pending 1058s</strong></h4>
		</div>
		
		<div class="panel-body">
			<ul> 
				<li><A HREF="javascript:fnViewPending1058s()">View Report</A></li>
				<BR>
				<li><A HREF="javascript:fnExportPending1058s()">Export Report to Excel</A></li>
			</ul>
		</div>
	</div>
	
	<div class="spacer10"></div>
	
	<div class="panel panel-info">
		<div class="panel-heading">
			<h4 class="panel-title"><strong>Summary of 1058's Transitioned during the selected period</strong></h4>
		</div>
		
		<div class="panel-body">
			<div class="form-group">
				<div class="multipleFormFields">
					<label class="control-label col-sm-1">From Date</label>
					<div class="col-sm-2">
						<div class="input-group date">
							<html:text name="SearchRequest1058Form" property="fromDate" styleClass="form-control partOfColFormField" styleId="1058FrmDate"/>
						    <div class="input-group-addon">
						        <span class="glyphicon glyphicon-time"></span>
						    </div>
						</div>
					</div>
				</div>
				
				<div class="multipleFormFields">	
					<label class="control-label col-sm-1" styleId="partOfColFormField">To Date</label>
					<div class="col-sm-2">
						<div class="input-group date">
							<html:text name="SearchRequest1058Form" property="toDate" styleClass="form-control partOfColFormField" styleId="1058ToDate"/>
						    <div class="input-group-addon">
						        <span class="glyphicon glyphicon-time"></span>
						    </div>
						</div>
					</div>
				</div>
				
				<button class="btn btn-primary" type='button' id="openRep" ONCLICK="javascript:fnShow1058Transitioned()">Search</button>
			</div>
			<div class="spacer30"></div>
			
			<logic:greaterThan name="ReportLen" value="0">
			<div id="showTable">
				<Table class="table table-bordered" id="1058TransitionedSummary">
					<thead>
						<TR>
							<TH>1058 Number</TH>
							<TH>Status</TH>
							<TH>User</TH>
							<TH>Date of Transition</TH>
						</TR>
					</thead>
					<tbody>
						<logic:iterate id="transitionRepDetails" name="SearchRequest1058Form"
							property="transitionSummary1058List" scope="request" >
							<TR>
								<TD CLASS='text-center v-midalign'>
				  	              <bean:write name="transitionRepDetails" property="number1058" />                     					
								</TD>
								
								<TD CLASS='text-center v-midalign'>
				  	              <bean:write name="transitionRepDetails" property="statusDesc" />                     					
								</TD>
								
								<TD CLASS='text-center v-midalign'>
				  	              <bean:write name="transitionRepDetails" property="actionUser" />                     					
								</TD>
								
								<TD CLASS='text-center v-midalign' data-sort="<bean:write name='transitionRepDetails' property='transitionDate' />">
				  	              <bean:write name="transitionRepDetails" property="transitionDate" />                     					
								</TD>
				  	       	</TR>
						</logic:iterate>
					</tbody>
				</Table>
				<div class="spacer20"></div>
				<div class="form-group">
					<div class="col-sm-12 text-center">
						<button class="btn btn-primary" type="button" id="hideRep" class="clear" onclick="javascript:fnHide1058Transitioned()">Close</button>
					</div>
				</div>
			</div>
			</logic:greaterThan>
		</div>
	</div>
	
<div class="spacer20"></div>
</html:form>
</div>