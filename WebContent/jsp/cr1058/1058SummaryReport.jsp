<!DOCTYPE HTML>
<HTML>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
	<link rel="shortcut icon" href="images/favicon.ico" />
	<link href="css/bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/EMDCustom.css" rel="stylesheet"/>
	<script type="text/javascript" src="js/Others/jquerylatest.js"></script>
	<script type="text/javascript" src="js/Others/jquerymigrate.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap.min.js"></script>
	<SCRIPT type="text/javascript" SRC="js/Emd_Lsdb.js"></SCRIPT>
	<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
</head>
<body>
<div class="container-fluid">
	<html:form action="/SearchRequest1058Action" method="post" styleClass="form-horizontal">
		<h4 class="text-center">View 1058 Summary Report</h4>
		<!-- To display  Messages - Start -->
		<div class="spacer30"></div>
		<%--Table Updated for CR-121 for server side error message tooltip --%>
		<div class="errorlayerhide" id="errorlayer">
		</div>
		<!-- To display Messages - End -->
	
		<!-- Validation Message For No Records Found - Start -->
	
		<!-- To get Order List from Form - Start -->
	
		<logic:present name="SearchRequest1058Form" property="requestList">
			<bean:size id="reqListLen" name="SearchRequest1058Form"
				property="requestList" />
		</logic:present>
	
		<!-- To get Order List from Form - End -->
	
		<logic:present name="SearchRequest1058Form" property="messageID"
			scope="request">
		  <%--Added for CR-121 for server side error message tooltip --%>	
		  <bean:define id="messageID" name="SearchRequest1058Form" property="messageID"/>
		  <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
	      <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
		</logic:present>
	
		<!-- Validation Message For No Records Found - Start -->
		<logic:match name="SearchRequest1058Form" property="method"
			value="fetchDetails">
			<logic:lessThan name="reqListLen" value="1">
				<script>
				//Updated for CR-121 server side error message
				fnNoRecords();
				</script>
			</logic:lessThan>
		</logic:match>

		<!-- Validation Message For No Records Found - End -->


		<TABLE class="table table-bordered table-hover">
		<!-- Added For CR_117 Starts -->
		<thead>
		<!-- Added For CR_117 Ends -->
			<TR>
				<TH width="15%" CLASS='table_heading'>Order Number</TH>
				<TH width="15%" CLASS='table_heading'>1058 Number</TH>
				<TH width="15%" CLASS='table_heading'>Customer Name</TH>
				<TH width="15%" CLASS='table_heading'>Status</TH>
				<TH width="10%" CLASS='table_heading'>Specification Revision</TH>
				<TH width="20%" CLASS='table_heading'>Model</TH>				
				<TH width="50%" CLASS='table_heading' nowrap>General Description</TH>
				<TH width="20%" CLASS='table_heading'>Est.Design Hrs</TH>
				<TH width="20%" CLASS='table_heading'>Est.Drafting Hrs</TH>
				<TH width="25%" CLASS='table_heading'>Work Order(USD)</TH>
			</TR>
			
		</thead>
			<tbody class="text-center">
			<logic:iterate id="reqListId1058" name="SearchRequest1058Form"
				property="requestList" scope="request" >
					<logic:iterate id="reqListId" name="reqListId1058">
				<TR>
					<TD CLASS='v-midalign'>
	  	              <bean:write name="reqListId" property="orderNo" />                     					
					</TD>
					<TD CLASS='v-midalign'>
						<bean:write name="reqListId" property="id1058" />
					</TD>
					<TD CLASS='v-midalign'>
					<logic:notEmpty name="reqListId" property="custName" >
					  <bean:write name="reqListId" property="custName" />
					 </logic:notEmpty> 
	     			  <logic:empty name="reqListId" property="custName" >
						   &nbsp;
					  </logic:empty>
					</TD>
				    <TD CLASS='v-midalign'>
						<logic:notEmpty name="reqListId" property="statusDesc" >
						   <bean:write name="reqListId" property="statusDesc" />
						</logic:notEmpty>
						<logic:empty name="reqListId" property="statusDesc" >
						   &nbsp;
						</logic:empty>
					</TD> 
					<TD CLASS='v-midalign'>
						<logic:notEmpty name="reqListId" property="specRev" >
						   <bean:write name="reqListId" property="specRev" />
						</logic:notEmpty>
						<logic:empty name="reqListId" property="specRev" >
						   &nbsp;
						</logic:empty>
					</TD>
					<TD CLASS='v-midalign'>
					<logic:notEmpty name="reqListId" property="mdlName" >
					  <bean:write name="reqListId" property="mdlName" />
					 </logic:notEmpty> 
					  <logic:empty name="reqListId" property="mdlName" >
						   &nbsp;
          			  </logic:empty>
					</TD>
					<TD CLASS='v-midalign'>
						<logic:notEmpty name="reqListId" property="genDesc" >
						   <bean:define name="reqListId" property="genDesc" id="genDesc" />
						   <%=(String.valueOf(genDesc)).replaceAll("\n","<br>")%>
						</logic:notEmpty>
						<logic:empty name="reqListId" property="genDesc" >
						   &nbsp;
						</logic:empty>
					</TD>
					<TD CLASS='v-midalign'>
						<logic:notEmpty name="reqListId" property="designHrs" >
						  <bean:write name="reqListId" property="designHrs" />
  	                    </logic:notEmpty>
						<logic:empty name="reqListId" property="designHrs" >
						  &nbsp;
						 </logic:empty> 
					</TD>
					<TD CLASS='v-midalign'>
					<logic:notEmpty name="reqListId" property="draftingHrs" >
					  <bean:write name="reqListId" property="draftingHrs" />
					 </logic:notEmpty> 
					  <logic:empty name="reqListId" property="draftingHrs" >
						  &nbsp;
					 </logic:empty> 
					</TD>
					<TD CLASS='v-midalign'>
					   <logic:notEmpty name="reqListId" property="workOrderUSD" >
					    <bean:define id="chkMinus" name="reqListId" property="workOrderUSD" />
					   	    <%= (String.valueOf(chkMinus).substring(0,1).equalsIgnoreCase("-")) 
						   	    	? "-$"+(String.valueOf(chkMinus).substring(1)):"$"+chkMinus%>
					   </logic:notEmpty>
						<logic:empty name="reqListId" property="workOrderUSD" >
						   &nbsp;
						</logic:empty>
					</TD>
				</TR>
				</logic:iterate>
			</logic:iterate>
			</tbody>
		</TABLE>
		
		 <div class="col-sm-12 text-center">
			    <div class="form-group">
		            <button class="btn btn-primary" type='button' id="btnPrint" ONCLICK="window.print();">Print</button>&nbsp;
		            <button class="btn btn-primary" type='button' id="btnCancel" ONCLICK="window.close();">Close</button>
		        </div>
	     	</div>
	  
		<div class="spacer50"></div>
		
</html:form>
</div>
</body>
</HTML>