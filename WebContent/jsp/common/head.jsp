	<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
    <div class="navbar-header">
	 	  <!-- For smaller displays -->
	 	  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#lsdb-submenu" aria-expanded="false">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	 	  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#lsdb-mainmenu" aria-expanded="false">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand"><img alt="Brand" height="28" src="images/header.png"></a>
    </div>    
   	<div class="collapse navbar-collapse" id="lsdb-mainmenu">
 			<ul class="nav navbar-nav navbar-right">
		    <li class="dropdown"><a href="LogoutAction.do?method=homePage&screenid=24">Home</a></li>
		    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Spec Maintenance <span class="caret"></span></a>
				<ul class="dropdown-menu">
		            <li><a href="CreateSpecAction.do?method=initLoad&screenid=1">Create Spec</a></li>
		            <li><a href="ModifySpecAction.do?method=initLoad&screenid=2">Modify Spec</a></li>
		            <li><a href="CopySpecAction.do?method=initLoad&screenid=3">Copy Spec</a></li>
					<li><a href="DeleteSpecAction.do?method=initLoad&screenid=26">Delete Spec</a></li>
		        </ul>
			</li>
		    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">1058 <span class="caret"></span></a>
				<ul class="dropdown-menu">
		            <li><a href="ChangeRequest1058Action.do?method=initLoad&screenid=47" >Create 1058 Request</a></li>
		            <li><a href="SearchRequest1058Action.do?method=initLoad&screenid=47" >Search/Modify 1058 Request</a></li>
		            <li><a href="SearchRequest1058Action.do?method=initLoad&screenid=51" >Reports</a></li>
				</ul>
			</li>
			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Spec Comparison <span class="caret"></span></a>
				<ul class="dropdown-menu">
		            <li><a href="compareSpecAction.do?method=initLoad&screenid=5" >Clause Comparison</a></li>
		            <li><a href="compareComponentAction.do?method=initLoad&screenid=6" >Component Comparison/EDL Comparision</a></li>
		            <li><a href="OndemandSpecSupplement.do?method=initLoad&screenid=40" >On Demand Spec Supplement</a></li>
				</ul>
			</li>
			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Master Maintenance <span class="caret"></span></a>
		        <ul class="dropdown-menu">
		            <li><a href="SpecTypeAction.do?method=fetchSpecTypeDetails&screenid=36">Specification Type Maintenance</a></li>
		            <li role="separator" class="divider"></li>
		            <li class="dropdown-header">Model Maintenance</li>
					<li><a href="ModelAction.do?method=initLoad&screenid=7">Model Maintenance</a></li>
					<li><a href="CopyModelAction.do?method=initLoad&screenid=33">Copy Model</a></li>
					<li role="separator" class="divider"></li>
		            <li><a href="CustAction.do?method=initLoad&screenid=8">Customer Maintenance</a></li>
					<li><a href="DistAction.do?method=fetchDistributors&screenid=9" >Distributor Maintenance</a></li>
		            <li><a href="SectionMaintenance.do?method=initLoad&screenid=10">Section Maintenance</a></li>
		            <li><a href="SubSectionAction.do?method=initLoad&screenid=11" >SubSection Maintenance</a></li>
					<li><a href="CompGroupAction.do?method=initLoadCompGroup&screenid=12" >Component Group Maintenance</a></li>
		            <li><a href="CompAction.do?method=initLoad&screenid=13" >Component Maintenance</a></li>
		            <li><a href="CompMapAction.do?method=initLoad&screenid=14" >Component Mapping Maintenance</a></li>
					<li><a href="EnggDataMaintenance.do?method=initLoad&screenid=28">Engineering Data Maintenance</a></li>
		            <li><a href="AppendixMaintenance.do?method=initLoad&screenid=30">Appendix Maintenance</a></li>
		            <li role="separator" class="divider"></li>
		            <li class="dropdown-header">Clause Maintenance</li>
					<li><a href="modelAddClauseAction.do?method=initLoad&screenid=15">Add/Modify Clause</a></li>
					<li><a href="ModelAssignEdlAction.do?method=initLoad&screenid=35">Assign EDL#/Manage Links</a></li>
					<li role="separator" class="divider"></li>
					<li class="dropdown-header">General Info Maintenance</li>						
					<li><a href="ModelSpecificationAction.do?method=initLoad&screenid=17">Specification Maintenance</a></li>
					<li><a href="GenArrByModel.do?method=initLoad&screenid=18">General Arrangement</a></li>
					<li><a href="GenInfoMaintByModel.do?method=initLoad&screenid=41">General Info Text maintenance</a></li>
					<li role="separator" class="divider"></li>
		            <li><a href="PerfCurveModelAction.do?method=initLoad&screenid=19">Performance Curve Maintenance</a></li>
		        </ul>
		 
		    </li>
			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">CR Form <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="CreateRequestIDAction.do?method=initLoad&screenid=31" class="documents">Create Change Request Form</a>
					<li><a href="ModifyChangeAction.do?method=initLoad&screenid=32" class="messages">Modify Change Request Form</a>
				</ul>	
			</li>
		    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">History <span class="caret"></span></a>
		    	<ul class="dropdown-menu">
		    		<li><a href="HistoryAction.do?method=initLoad&screenid=21" >Spec History</a></li>
		    		<li><a href="RevisionChangesAction.do?method=fetchRevisions&screenid=39">LSDB Revision Changes</a></li>
		    	</ul>
		    </li>
		    
		    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Administration <span class="caret"></span></a>
			    <ul class="dropdown-menu">
			    	<li><a href="RegenerateSpecAction.do?method=initLoad&screenid=37">Regenerate Spec for PDFs</a></li>
			    	<li><a href="ResetSpecAction.do?method=initLoad&screenid=34">Reset Spec Status</a></li>
			    	<li><a href="UserMaintenanceAction.do?method=fetchUsers&screenid=22">User Maintenance</a></li>
			    	<li><a href="UserMaintenanceAction.do?method=fetchUsers&screenid=52">Manage Email Subscriptions</a></li>
			    	<li><a href="UserMaintenanceAction.do?method=fetchUsers&screenid=45">Broadcast emails</a></li>
			    	<li><a href="UserMaintenanceAction.do?method=fetchUsers&screenid=50">Pending/Invalid Emails</a></li>
			    	<li><a href="ResetPasswordAction.do?method=initLoad&screenid=23">Reset Password</a></li>
			    	<li><a href="SearchRequest1058Action.do?method=initLoad&screenid=48">Add 1058 Legacy Report</a></li>
			    	<li><a href="SuggestAction.do?method=fetchSuggestions&screenid=38">Suggestions</a></li>
			    	<li><a href="UserMaintenanceAction.do?method=fetchActivityLog&screenid=54">Master Maintenance Activity Log</a></li>
			    </ul>
		    </li>
		    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Reports <span class="caret"></span></a>
		    	<ul class="dropdown-menu dropdown-menu-right">
		    		<li><a href="MasterSpecByMdlAction.do?method=initLoad&screenid=27">Master Spec By Model</a></li>
		    		<li><a href="CustOptCatAction.do?method=initLoad&screenid=49">Customer Option Catalog Report</a></li>
		    		<li><a href="ClaByCompsAction.do?method=initLoad&screenid=44">Clauses By Components Report</a></li>
		    		<li><a href="OrderSpecificClauseAction.do?method=initLoad&screenid=43">Order Specific Clause Report</a></li>
		    		<li><a href="CompGroupAction.do?method=initLoadSpecTypesAndModels&screenid=29">Component Group/Component</a></li>
		    		<li><a href="CompGroupAction.do?method=initLoadCompInOrder&screenid=42">Component in Orders Report</a></li>
		    		<li><a href="modelAddClauseAction.do?method=initLoad&screenid=46">Clause Version In Orders Report</a></li>
		    		<li><a href="MasterMaintReportAction.do?method=initLoad&screenid=20">Master Spec With all Clause Versions</a></li>
		    	</ul>
		    </li>
 		</ul>
   	</div>
   	<div class="spacer10"></div>
	<div class="collapse navbar-collapse" id="lsdb-submenu">
		<p class="navbar-text navbar-left">Welcome, <strong><%						
						String LastName=((String)session.getAttribute("LastName")==null)? "":(String)session.getAttribute("LastName");
						String FirstName=((String)session.getAttribute("FirstName")==null)?"":(String)session.getAttribute("FirstName");
						out.println(FirstName);out.println(LastName);
					%></strong></p>
		<h4 class="text-center navbar-text" style="padding-left:180px;"><strong>Locomotive Spec Database<LSDB:AppServerIP/></strong></h4>
		<ul class="nav navbar-nav navbar-right">
	        <li><a href="javascript:fnShowSuggestion();">Suggestion Box</a></li>
	        <li><a href="ChangePassword.do?method=initLoad&screenid=25">Change Password</a></li>
	        <li><a href="mailto:LSDB_OffshoreSupport@progressrail.com">Contact Us</a></li>
	        <li><a href="LogoutAction.do?method=logout" class="text-uppercase"><strong><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Logout</strong></a></li>
    	</ul>
	</div>