<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<SCRIPT type="text/javaScript" SRC="js/1058CreateRequest.js"></SCRIPT>
<SCRIPT type="text/javaScript" SRC="js/Others/jquery.typeahead.bundle.min.js"></SCRIPT>

<div class="container-fluid">
<html:form action="/ChangeRequest1058Action" method="post" styleClass="form-horizontal">
	<!-- Application PageName Display starts here-->

	<h4 class="text-center"><bean:message key="Application.Screen.Create1058Requests" /></h4>
	<p class="text-center"><mark>NOTE: The order numbers with red color font are of NON-LSDB type.</mark></p> 
			
	<!-- Application PageName Display Ends here-->

	 <!-- Validation Message Display Part Starts Here -->

      <%--Table Updated for CR-121 for server side error message tooltip --%>
		<div class="errorlayerhide" id="errorlayer">
		</div>

      <!-- Validation Message Display Part Ends Here -->
      <!-- To display Validation Messages - Start -->
      <logic:present name="ChangeRequest1058Form" property="messageID"
            scope="request">
            <%--Added for CR-121 for server side error message tooltip --%>	
			  <bean:define id="messageID" name="ChangeRequest1058Form" property="messageID"/>
			  <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
		      <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
      </logic:present>
      <!-- To display Validation Messages - End -->
      	<div class="spacer30"></div>
      
      	<div class="form-group required-field">
			<label class="col-sm-5 control-label">Order Number</label>
			<div class="col-sm-4 input-group orderNoDiv">		
				<span class="input-group-btn loadOrders"><span class="btn btn-default disabled">Fetching Orders&nbsp;&nbsp;&nbsp;<i class="glyphicon glyphicon-refresh gly-spin"></i></span></span>
				<html:text name="ChangeRequest1058Form" style="display:none" property="orderNo" styleClass="typeahead form-control" size="46" styleId="orderNo" maxlength="100"/>
			</div>
		</div>
		
		<div class="spacer30"></div>
		
	  	<div class="form-group">
			<div class="col-sm-12 text-center">
				<button class="btn btn-primary" type='button' id="createLsdb" ONCLICK="javascript:fnCreateLSDBRequest()">
					<bean:message key="Application.Screen.Create1058Requests" />
				</button>
				<button class="btn btn-primary" type='button' id="createNLsdb" ONCLICK="javascript:fnCreateNonLSDBRequest()">
					<bean:message key="Application.Screen.Create1058Nonlsdb" />
				</button>
			</div>
		</div>
<html:hidden name="ChangeRequest1058Form" styleId="orderKey" property="orderKey"/>
</html:form>
</div>