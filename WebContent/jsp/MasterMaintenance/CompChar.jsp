<!DOCTYPE HTML>
<HTML>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="com.EMD.LSDB.vo.common.ClauseVO"%>
<%@page import="com.EMD.LSDB.vo.common.CompGroupVO"%>
<HEAD>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
	<link rel="shortcut icon" href="images/favicon.ico" />
	<link href="css/bootstrap.css" TYPE="text/css" rel="stylesheet"/>
    <link href="css/select2.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/select2-bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/EMDCustom.css" rel="stylesheet"/>
	<link href="css/dataTables.bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="js/Others/jquerylatest.js"></script>
	<script type="text/javascript" src="js/Others/jquerymigrate.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/Others/select2.js"></script>
	<SCRIPT type="text/javascript" SRC="js/Emd_Lsdb.js"></SCRIPT>
	<SCRIPT type="text/javascript" SRC="js/error.js"></SCRIPT>
	<SCRIPT type="text/javascript" SRC="js/error_messages.js"></SCRIPT>
	<script type="text/javascript" src="js/Others/jquery.bootbox.min.js"></script>
	<SCRIPT language="JavaScript" SRC="js/AddClause.js"></SCRIPT>
	<script>
 	   $(document).ready(function() { 
        	$("#sltCompCharGrp").select2({theme:'bootstrap'});
       })
	</script>
<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
</HEAD>
<BODY>
	<div class="container" width="80%">
		<html:form action="/compSearchAction.do">
			<h4 class="text-center">Component/Characteristic/Characterization</h4>
				<div class="spacer10"></div>
				<div class="row">
					<div class="errorlayerhide" id="errorlayer">
					</div>
				</div>
			<!-- Logic Tag Check For Display The Success Message After Add Starts Here -->

		
			<logic:present name="CompSearchForm" property="messageID"
				scope="request">
			
		                <bean:define id="messageID" name="CompSearchForm" property="messageID"/>
					<input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" ><html:hidden name="CompSearchForm" styleId="messageID" property="messageID"/>
		            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
			</logic:present>
		
			<!-- Logic Tag Check For Display The Success Message After Add Functionality Ends Here -->
			<!-- Logic Tag Check For Display The Error Message After Add Functionality Starts Here -->
			<logic:present name="CompSearchForm" property="errorMessage"
				scope="request">
				<script>
		                    hideErrors();
		                    addMessage('<bean:write name="CompSearchForm" property="errorMessage"/>');
		                    showScrollErrors();
		                </script>
		
			</logic:present>
		
			<!-- Logic Tag Check For Display The Error Message After Add Functionality Ends Here -->
		
			<logic:present name="CompSearchForm" property="compList">
				<bean:size id="comp" name="CompSearchForm" property="compList" />
			</logic:present>
			
			<div class="spacer10"></div>
	
			<div class="row" >
				<div class="text-center form-inline">
						Component/Characteristic/Characterization
						<html:select styleClass="form-control" styleId="sltCompCharGrp" property="compGroupTypeSeqNo">	
								<option value="0" selected>All</option>
								<html:option value="1">Component</html:option>
								<html:option value="2">Characteristic</html:option>
								<html:option value="3">Characterization</html:option>
						</html:select>
						<button class="btn btn-primary addbtn" type='button' id="searchButton" ONCLICK="javascript:fetchComponent()">Search</button>
				</div>
			</div>		

		<logic:greaterThan name="comp" value="0">		
				<hr>
				<table class="table table-bordered table-hover">
					    <thead>
					        <tr>
								<TH CLASS="text-center" WIDTH="2%">Select</TH>
								<TH CLASS="text-center text-nowrap"	WIDTH="40%">Component/Characteristic/Characterization</TH>
								<TH CLASS="text-center text-nowrap"	WIDTH="30%">Component Group</TH>
							</tr>	
						</thead>
					    <tbody class="text-center">	
					    	<logic:iterate id="compGroupDets" name="CompSearchForm"
									property="compList" scope="request"
									type="com.EMD.LSDB.vo.common.CompGroupVO">

								<logic:iterate id="compDets" name="compGroupDets"
									property="componentVO" scope="page"
									type="com.EMD.LSDB.vo.common.ComponentVO">
			
									<logic:equal name="compGroupDets" property="componentGroupSeqNo"
										value="<%=String.valueOf(compDets.getComponentGroupSeqNo())%>">
											<TR>
													<TD CLASS="v-midalign">
														<html:checkbox property="componentSeqNo"
															value='<%=String.valueOf(compDets.getComponentSeqNo())%>'></html:checkbox>
														<html:hidden property="componentName"
															value='<%=String.valueOf(compDets.getComponentName())%>'></html:hidden>
													</TD>
				
													<TD CLASS="v-midalign">
														<bean:write name="compDets" property="componentName" />
													</TD>
													<TD CLASS="v-midalign"><bean:write
														name="compGroupDets" property="componentGroupName" />
														<html:hidden name="compGroupDets" property="componentGroupName" />
														<html:hidden name="compGroupDets" property="componentGroupSeqNo" />
													</TD>
											</TR>
									</logic:equal>
								</logic:iterate>
							</logic:iterate>		
				</tbody>
			</table>
			
			<div class="spacer10"></div>
				
				<div class="form-group">
				  <div class="col-sm-12 text-center">
				       <button class="btn btn-primary" type='button' id="okButton" ONCLICK="javascript:fnComponentSubmit()">OK</button>
   				       <button class="btn btn-primary" type='button' id="cancelButton" ONCLICK="javascript:window.close();">Cancel</button>
					</div>   
			   	</div>
			<div class="spacer50"></div>	    		
		</logic:greaterThan>	
		<html:hidden property="modelSeqNo" />			
		</html:form>
	</div>
</BODY>
</HTML>				