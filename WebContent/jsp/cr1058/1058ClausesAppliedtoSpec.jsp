<!DOCTYPE HTML>
<HTML>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<HEAD>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
	<link href="css/bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/bootstrap-table.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/EMDCustom.css" rel="stylesheet"/>
	<link rel="shortcut icon" href="images/favicon.ico" />
	<script type="text/javascript" src="js/Others/jquerylatest.js"></script>
	<script type="text/javascript" src="js/Others/jquerymigrate.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap-table.min.js"></script>
	<SCRIPT language="JavaScript" SRC="js/Emd_Lsdb.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/error.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/error_messages.js"></SCRIPT>
</HEAD>

<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>

<div class="container-fluid">

<html:form action="/SearchRequest1058Action" method="post" styleClass="form-horizontaL">

	<h4 class="text-center">Clauses Applied to Spec from unapproved 1058's Report</h4>
	
	<div class="spacer20"></div>
	
	<div class="errorlayerhide" id="errorlayer">
	</div>
	
	<logic:present name="SearchRequest1058Form" property="clausesAppliedtoSpec">
		<bean:size id="ReportDetailsLen" name="SearchRequest1058Form"
			property="clausesAppliedtoSpec" />
	</logic:present>

	<logic:present name="SearchRequest1058Form" property="messageID"
		scope="request">
	  <bean:define id="messageID" name="SearchRequest1058Form" property="messageID"/>
	  <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
      <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
	</logic:present>

	<logic:match name="SearchRequest1058Form" property="method"
		value="view1058ClauseAppliedtoSpec">
		<logic:lessThan name="ReportDetailsLen" value="1">
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
	
	<html:hidden property="orderbyFlag"/>
	<html:hidden property="columnheader"/>

	<logic:greaterThan name="ReportDetailsLen" value="0">
		<TABLE class="table table-bordered" id="tableClausesApplied">
			<thead>
				<TR>
					<TH width="10%" rowspan="2">1058 Number</TH>
					<TH width="10%" rowspan="2">Status</TH>
					<TH width="10%" rowspan="2">Spec Revision</TH>
					<TH width="12%" rowspan="2">Customer name</TH>
					<TH width="8%" rowspan="2">Issued By</TH>
					<TH width="50%" colspan="4">Clause Details</TH>
				</TR>	
				<TR>
					<TH width="6%">Type</TH>
					<TH width="4%">Clause Number</TH>
					<TH width="30%">Clause Description</TH>
					<TH width="10%">Engineering Data</TH>
				</TR>
			</thead>
			<tbody>
				<logic:iterate id="repDetails" name="SearchRequest1058Form"
					property="clausesAppliedtoSpec" scope="request" >
					<TR>
						
						<TD CLASS='text-center v-midalign' rowspan="3">
		  	              <bean:write name="repDetails" property="number1058" />                     					
						</TD>
						
						<TD CLASS='text-center v-midalign' rowspan="3">
		  	              <bean:write name="repDetails" property="statusDesc" />                     					
						</TD>
						
						<logic:present name="repDetails" property="specRev">
							<TD CLASS='text-center v-midalign' rowspan="3">
								<bean:write name="repDetails" property="specRev" />
							</TD>
						</logic:present>
						<logic:notPresent name="repDetails" property="specRev">
							<TD CLASS='text-center v-midalign' rowspan="3">&nbsp;</TD>
						</logic:notPresent>
						
						<TD CLASS='text-center v-midalign' rowspan="3">
		  	              <bean:write name="repDetails" property="custName" />                     					
						</TD>
						
						<TD CLASS='text-center v-midalign' rowspan="3">
		  	              <bean:write name="repDetails" property="issuedBy" />                     					
						</TD>
						
						<TD CLASS='text-center v-midalign' style="font-weight: bold;">Change From</TD>
						
						<logic:present name="repDetails" property="changeFromClaNo">
							<TD CLASS='text-center v-midalign'>
								<bean:write name="repDetails" property="changeFromClaNo" />                     					
							</TD>
						</logic:present>
						<logic:notPresent name="repDetails" property="changeFromClaNo">
							<TD CLASS='text-center v-midalign'>&nbsp;</TD>
						</logic:notPresent>
						
						<logic:present name="repDetails" property="changeFromClaDesc">
							<TD>
								<bean:define name="repDetails" property="changeFromClaDesc" 
									id="changeFromClaDesc" />
										<%String strClauseDesc =  String.valueOf(changeFromClaDesc);
										if(strClauseDesc.startsWith("<p>")){%>
											<%=(String.valueOf(changeFromClaDesc))%>
										<%}else{ %>	
											<%=(String.valueOf(changeFromClaDesc)).replaceAll("\n","<br>")%> 							
										<%}%>
							</TD>
						</logic:present>
						<logic:notPresent name="repDetails" property="changeFromClaDesc">
							<TD>&nbsp;</TD>
						</logic:notPresent>
						
						<TD CLASS='text-center'>
							<logic:notEmpty name="repDetails" property="changeFromEdlNo">
								<bean:define name="repDetails" property="changeFromEdlNo" id="changeFromEdlNo"/>
								<%=(String.valueOf(changeFromEdlNo).replaceAll("\n","<br>"))%>
							</logic:notEmpty>
							
							<logic:notEmpty name="repDetails" property="changeFromRefEdlNo">
								<bean:define name="repDetails" property="changeFromRefEdlNo" id="changeFromRefEdlNo"/>
								<%=(String.valueOf(changeFromRefEdlNo).replaceAll("\n","<br>"))%>
							</logic:notEmpty >
							
							<logic:notEmpty name="repDetails" property="changeFromPartOfNo">
								<bean:define name="repDetails" property="changeFromPartOfNo" id="changeFromPartOfNo"/>
								<%=(String.valueOf(changeFromPartOfNo).replaceAll("\n","<br>"))%>
							</logic:notEmpty>
							
							<logic:notEmpty name="repDetails" property="changeFromDwoNo">
								<bean:message key="screen.dwoNo"/>
								<bean:write name="repDetails" property="changeFromDwoNo"/><br/>
							</logic:notEmpty>
							
							<logic:notEmpty name="repDetails" property="changeFromPartNo">
								<bean:message key="screen.partNo"/>
								<bean:write name="repDetails" property="changeFromPartNo"/><br/>
							</logic:notEmpty>
							
							<logic:notEmpty name="repDetails" property="changeFromPriceBookNo">
								<bean:message key="screen.priceBookNo"/>
								<bean:write name="repDetails" property="changeFromPriceBookNo"/><br/>
							</logic:notEmpty>
							
							<logic:notEmpty name="repDetails" property="changeFromEquip">
								<bean:write name="repDetails" property="changeFromEquip"/><br/>
							</logic:notEmpty>
							
							<logic:notEmpty name="repDetails" property="changeFromEngiComments">
								<bean:write name="repDetails" property="changeFromEngiComments"/>
							</logic:notEmpty>
							&nbsp;
						</TD>
					</TR>
						
					<TR>
						<TD CLASS='text-center v-midalign' style="font-weight: bold;">Change To</TD>
						
						<logic:present name="repDetails" property="changeToClaNo">
							<TD CLASS='text-center v-midalign'>
								<bean:write name="repDetails" property="changeToClaNo" />             
							</TD>
						</logic:present>
						<logic:notPresent name="repDetails" property="changeToClaNo">
							<TD CLASS='text-center v-midalign'>&nbsp;</TD>
						</logic:notPresent>
						
						<logic:present name="repDetails" property="changeToClaDesc">
							<TD>
								<bean:define name="repDetails" property="changeToClaDesc" id="changeToClaDesc"/>
								<%String strClauseDesc =  String.valueOf(changeToClaDesc);
								if(strClauseDesc.startsWith("<p>")){%>
									<%=(String.valueOf(changeToClaDesc))%>
								<%}else{ %>	
									<%=(String.valueOf(changeToClaDesc)).replaceAll("\n","<br>")%> 							
								<%}%>
							</TD>
						</logic:present>
						<logic:notPresent name="repDetails" property="changeToClaDesc">
							<TD>&nbsp;</TD>
						</logic:notPresent>
						
						<TD CLASS='text-center'>
							<logic:notEmpty name="repDetails" property="changeToEdlNo">
								<bean:define name="repDetails" property="changeToEdlNo" id="changeToEdlNo"/>
								<%=(String.valueOf(changeToEdlNo).replaceAll("\n","<br>"))%>
							</logic:notEmpty>
							
							<logic:notEmpty name="repDetails" property="changeToRefEdlNo">
								<bean:define name="repDetails" property="changeToRefEdlNo" id="changeToRefEdlNo"/>
								<%=(String.valueOf(changeToRefEdlNo).replaceAll("\n","<br>"))%>
							</logic:notEmpty >
							
							<logic:notEmpty name="repDetails" property="changeToPartOfNo">
								<bean:define name="repDetails" property="changeToPartOfNo" id="changeToPartOfNo"/>
								<%=(String.valueOf(changeToPartOfNo).replaceAll("\n","<br>"))%>
							</logic:notEmpty>
							
							<logic:notEmpty name="repDetails" property="changeToDwoNo">
								<bean:message key="screen.dwoNo"/>
								<bean:write name="repDetails" property="changeToDwoNo"/><br/>
							</logic:notEmpty>
							
							<logic:notEmpty name="repDetails" property="changeToPartNo">
								<bean:message key="screen.partNo"/>
								<bean:write name="repDetails" property="changeToPartNo"/><br/>
							</logic:notEmpty>
							
							<logic:notEmpty name="repDetails" property="changeToPriceBookNo">
								<bean:message key="screen.priceBookNo"/>
								<bean:write name="repDetails" property="changeToPriceBookNo"/><br/>
							</logic:notEmpty>
							
							<logic:notEmpty name="repDetails" property="changeToEquip">
								<bean:write name="repDetails" property="changeToEquip"/><br/>
							</logic:notEmpty>
							
							<logic:notEmpty name="repDetails" property="changeToEngiComments">
								<bean:write name="repDetails" property="changeToEngiComments"/>
							</logic:notEmpty>
							&nbsp;
						</TD>
					</TR>
						
					<TR>
						<TD CLASS='text-center v-midalign' style="font-weight: bold;">Reason</TD>
						<logic:present name="repDetails" property="reason">
							<TD colspan="3"><bean:write name="repDetails" property="reason" /></TD>
						</logic:present>
						<logic:notPresent name="repDetails" property="reason">
							<TD colspan="3">&nbsp;</TD>
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
