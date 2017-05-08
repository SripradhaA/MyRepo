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
	<link rel="shortcut icon" href="images/favicon.ico" />
	<link href="css/bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/select2.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/select2-bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/EMDCustom.css" rel="stylesheet"/>
	<script type="text/javascript" src="js/Others/jquerylatest.js"></script>
	<script type="text/javascript" src="js/Others/jquerymigrate.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/Others/select2.js"></script>
	<SCRIPT language="JavaScript" SRC="js/Emd_Lsdb.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/error.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/error_messages.js"></SCRIPT>
	<SCRIPT language="JavaScript" SRC="js/AddClauseOrder.js"></SCRIPT>
	<script>
	        $(document).ready(function() { 
	        	$("#sltCompCharGrp").select2({theme:'bootstrap'});
	        })
    </script>
	<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
</HEAD>

<BODY>

<div class="container" width="80%" >
	<html:form action="/orderCompSearchAction.do" styleClass="form-inline">
	
		<h4 class="text-center">Component/Characteristic/Characterization</h4>
		
		<!-- Validation Message Display Part Starts Here -->
		<div class="row">
		<div class="errorlayerhide" id="errorlayer"></div>
		</div>
		<!-- Validation Message Display Part Ends Here -->
	
		<!-- Logic Tag Check For Display The Success Message After Add Starts Here -->
	
	
		<logic:present name="OrderCompSearchForm" property="messageID"
			scope="request">
				<bean:define id="messageID" name="OrderCompSearchForm" property="messageID"/>
	            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
	            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
		</logic:present>
	
		<!-- Logic Tag Check For Display The Success Message After Add Functionality Ends Here -->
		<!-- Logic Tag Check For Display The Error Message After Add Functionality Starts Here -->
		<logic:present name="OrderCompSearchForm" property="errorMessage"
			scope="request">
			<script>
	                    hideErrors();
	                    addMessage('<bean:write name="OrderCompSearchForm" property="errorMessage"/>');
	                    showScrollErrors();
	                </script>
	
		</logic:present>
	
		<!-- Logic Tag Check For Display The Error Message After Add Functionality Ends Here -->
	
	
	
		<logic:present name="OrderCompSearchForm" property="compList">
			<bean:size id="comp" name="OrderCompSearchForm" property="compList" />
		</logic:present>
		
		<div class="spacer20"></div>
		
		<div class="row" >
			<div class="text-center form-inline">
					Component/Characteristic/Characterization
					<html:select styleClass="form-control" styleId="sltCompCharGrp" property="compGroupTypeSeqNo">	
							<option value="0" selected>All</option>
							<html:option value="1">Component</html:option>
							<html:option value="2">Characteristic</html:option>
							<html:option value="3">Characterization</html:option>
					</html:select>
					<button class="btn btn-primary" name="Find" ID="search" type='button' ONCLICK="fetchComponent()">Search</button>
			</div>
		</div>		
		
		<html:hidden property="modelSeqNo" />
		<div class="row" >
			<div class="spacer20"></div>
		</div>
		<logic:greaterThan name="comp" value="0">
				<TABLE class="table table-bordered">
					<thead>
						<TR>
							<TH WIDTH="2%" CLASS="text-center">Select</TH>
							<!-- Modified for CR_81 by RR68151 -->
							<TH WIDTH="40%" CLASS="text-center">Component/Characteristic/Characterization</TH>
							<TH WIDTH="30%" CLASS="text-center">Component Group</TH>
						</TR>
					</thead>
					<tbody>
					<logic:iterate id="compGroupDets" name="OrderCompSearchForm"
						property="compList" scope="request"
						type="com.EMD.LSDB.vo.common.CompGroupVO">
	
						<logic:iterate id="compDets" name="compGroupDets"
							property="componentVO" scope="page"
							type="com.EMD.LSDB.vo.common.ComponentVO">
	
							<logic:equal name="compGroupDets" property="componentGroupSeqNo"
								value="<%=String.valueOf(compDets.getComponentGroupSeqNo())%>">
								<TR>
									<TD class="text-center v-midalign">
										<label>
										  	<html:checkbox property="componentSeqNo" value='<%=String.valueOf(compDets.getComponentSeqNo())%>'></html:checkbox>
										</label>
										<html:hidden property="componentName" value='<%=String.valueOf(compDets.getComponentName())%>'></html:hidden>
									</TD>
	
									<TD class="text-center v-midalign">
										<bean:write name="compDets"	property="componentName" />
									</TD>
									<TD class="text-center v-midalign">
										<bean:write	name="compGroupDets" property="componentGroupName" /> 
										<html:hidden name="compGroupDets" property="componentGroupName" />
										<html:hidden name="compGroupDets" property="componentGroupSeqNo" />
									</TD>
								</TR>
							</logic:equal>
						</logic:iterate>
					</logic:iterate>
				</tbody>
				</TABLE>
			</logic:greaterThan>
				
			<div class="row">
				<div class="spacer30"></div>
				<div class="col-sm-12 text-center">
					<button class="btn btn-primary" type='button' ONCLICK="javascript:fnComponentSubmit()">OK</button>
					<button class="btn btn-primary" type='button' ONCLICK="fnCancel()">Cancel</button>
				</div>
			</div>
			
			<div class="row">
				<div class="spacer30"></div>
			</div>
	</html:form>
</div>
</BODY>
</HTML>
