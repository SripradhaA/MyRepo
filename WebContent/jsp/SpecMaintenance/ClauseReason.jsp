<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<HTML>
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
<SCRIPT LANGUAGE="JavaScript" SRC="js/OrderSectionView.js"></SCRIPT>
<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
</HEAD>

<BODY onload="javascript:fnPopUpClose()">
<DIV CLASS="container-fluid">
<html:form styleClass="form-horizontal" action="/OrderSection" method="post"
	enctype="multipart/form-data" >
			<H4 class="text-center"><bean:message key="Application.Screen.Reason" /></H4>
	
	<div class="errorlayerhide" id="errorlayer">
	</div>

	<div class="row">
		<div class="spacer30"></div>
	</div>
	
	<div class="panel panel-default col-sm-offset-2" style="width:70%;align:center;">
	   	<div class="panel-heading"><h3 class="panel-title text-center">Spec Supplement</h3></div>
   		<div class="panel-body">
   			<div class="form-group">
   				<label class="col-sm-2 control-label">Reason
    				<logic:greaterEqual name="OrderSectionForm" property="currentSpecStatus" value="3">
						<font color="red" valign="top">*</font>
					</logic:greaterEqual>
    			</label>
   				<logic:present name="OrderSectionForm" property="clauseDetail">
					<logic:iterate id="clause" name="OrderSectionForm" property="clauseDetail">
						<div class="col-sm-8">
							<html:textarea styleClass="form-control" name="clause" property="reason" rows="3" cols="60" />
						</div>
					</logic:iterate>
				</logic:present>
			</div>
   		</div>
	</div>
			
	<div class="form-group">
		<div class="col-sm-12 text-center">
			<button class="btn btn-primary" type='button' name="btnApplyDefault" ONCLICK="javascript:updateReason('<bean:write  name="flag" scope="request" />')">OK</button>
			<button class="btn btn-primary" type='button' name="cancel" ONCLICK="window.close()">Cancel</button>
		</div>
	</div>

			
<html:hidden property="orderKey" />
<html:hidden property="orderSecSeqNo" />
<html:hidden property="subSecSeqNo" />
<html:hidden property="clauseSeqNo" />
<html:hidden property="clauseVersionNo" />
<html:hidden property="orderSecCode" />
<html:hidden property="orderSecName" />
<html:hidden property="revCode" />
<html:hidden property="messageID" />
<!-- Added For CR-79 Removing Reason validation -->
<html:hidden property="currentSpecStatus" />
</html:form>
</DIV>
</BODY>
</HTML>
