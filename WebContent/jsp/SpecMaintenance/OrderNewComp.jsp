<!DOCTYPE HTML>
<HTML>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<HEAD>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
	<link href="css/bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/EMDCustom.css" rel="stylesheet"/>
	<link rel="shortcut icon" href="images/favicon.ico" />
	<script type="text/javascript" src="js/Others/jquerylatest.js"></script>
	<script type="text/javascript" src="js/Others/jquerymigrate.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap.min.js"></script>
	<SCRIPT language="JavaScript" SRC="js/Emd_Lsdb.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/error.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/error_messages.js"></SCRIPT>
	<SCRIPT language="JavaScript" SRC="js/AddClauseOrder.js"></SCRIPT>
	<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
</HEAD>
<BODY onload="javascript:fnValidatedComp()" >
<div class="container-fluid">
	<html:form action="/orderAddClauseAction" method="post" styleClass="form-horizontal" enctype="multipart/form-data" >
	
	<h4 class="text-center"><bean:message key="Application.Screen.NewComponent" /></h4>
	
	<div class="row">
		<div class="spacer20"></div>
	</div>
		
	<TABLE BORDER=0 WIDTH="80%" ALIGN=center>
		<TR>
			<TD valign=top width="40%" ALIGN=center>

					<TABLE BORDER=0 WIDTH="80%" ALIGN=center>
						<TR>
							<TD>
							<!-- To display  Messages - Start -->
							<div class="errorlayerhide" id="errorlayer"></div>
						<!-- To display Messages - End -->


						<logic:notEmpty name="AddClauseOrderForm" property="hdnOrdrNewComp">
						<logic:equal name="AddClauseOrderForm" property="hdnOrdrNewComp" value="N">
						<script>
												var id='179';
												hideErrors();
												addMsgNum(id);
												showScrollErrors();
						</script>
						</logic:equal>
						
						
						</logic:notEmpty>
	                 </TD>
						</TR>
					</TABLE>

			</TD>
		</TR>
	</TABLE>	
		
	<div class="row">
		<div class="spacer30"></div>
	</div>
		
	<div class="form-group required-field">
		<label class="col-sm-5 control-label">New Component</label>
		<div class="col-sm-4">					
			<html:text name="AddClauseOrderForm" property="componentName" 
			styleClass="form-control" size="10"  maxlength="60" onkeypress="return keyHandler()"/>
		</div>
	</div>	
	
	<div class="row">
		<div class="spacer30"></div>
	</div>
		
	<div class="row">
		<div class="col-sm-12 text-center">
			<button class="btn btn-primary" type='button' ONCLICK="javascript:fnValidateCompName()">OK</button>
			<button class="btn btn-primary" type='button' ONCLICK="javascript:fnClosePopUp()">Cancel</button>
		</div>
	</div>
			
	<html:hidden property="hdnModelSeqNo" />
     <html:hidden property="hdnSubSecSeqNo" />
     <html:hidden property="hdnOrderNumber" />
     <html:hidden property="compGroupSeqNo" />
     <html:hidden property="hdnOrdrNewComp"/>
     <!-- added by cm68219 for key handling-->
     <input type="hidden" name="flag" value="Y">
                   
						
	
			<!--Outer Table Ends Here--> 
		</html:form>
</div>
</BODY>
</HTML>

