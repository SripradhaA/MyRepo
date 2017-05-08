<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<SCRIPT LANGUAGE="JavaScript" SRC="js/Suggestions.js"></SCRIPT>
<script>
	$(document).ready(function() { 
    	$("#userID").select2({theme:'bootstrap'}); 
    	$("#sltRoles").select2({theme:'bootstrap'}); 
    });
</script>

<div class="container" width="100%">
	<!-- To get Order List from Form - Start -->
	<logic:present name="SuggestForm" property="suggestionList">
		<bean:size id="suggListLen" name="SuggestForm" property="suggestionList" />
	</logic:present>
	<!-- To get Order List from Form - End -->
	<h4 class="text-center">Suggestions for Locomotive Spec Database</h4>
	<div class="spacer20"></div>
	
	<div class="row">
		<div class="errorlayerhide" id="errorlayer"></div>
	</div>
	
	<logic:present name="SuggestForm" property="messageID" scope="request">
	<bean:define id="messageID" name="SuggestForm" property="messageID"/>
	  <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
      <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
	</logic:present>
	<!-- To display Error Messages if any from request - Ends -->
	
    <!-- Validation Message For No Records Found - Start -->
	<logic:lessThan name="suggListLen" value="1">
		<script>
		//Updated for CR-121 server side error message
			fnNoRecords();
		</script>
	</logic:lessThan>
	<div class="row">
		<div class="spacer20"></div>
	</div>
	<html:form action="/SuggestAction" method="post" styleClass="form-horizontal" >

	<div class="form-group required-field form-inline text-center">
		<div class="col-sm-6">
			<label class="control-label">Select Initiator</label>
			<html:select property="userName" tabindex="8" styleId="userID" styleClass="form-control">
				<option selected value="0">All</option>
				 <logic:present name="SuggestForm" property="initiatorList">
					<logic:iterate id="userList" name="SuggestForm" property="initiatorList" 
						scope="request" type="com.EMD.LSDB.vo.admn.UserVO" >	
						<option value="<bean:write name="userList" property="userrId" />">
						 <bean:write name="userList" property="firsttName" />, <bean:write name="userList" property="lasttName" />						
						</option>
					</logic:iterate>
				</logic:present>	
			</html:select>
			<button class="btn btn-primary" onclick="javascript:fnSearch()">Search</button>
		</div>
		<div class="col-sm-6 multipleFormFields">
			<label class="control-label">Select State</label>
			<html:select property="roleName" tabindex="8" styleId="sltRoles" styleClass="form-control partOfColFormField">
				<option selected value="-1">---Select---</option>
				<option value="0">All</option>
				<option value="1" >Pending</option>
				<option value="2" >Accepted</option>
				<option value="3" >Rejected</option>
				<option value="4" >Completed</option>
				
				<logic:present name="SuggestForm" property="userRolesList">
				<html:optionsCollection name="SuggestForm" 
						property="userRolesList" value="roleName" label="roleName" />
				</logic:present>
			</html:select>
			<button type="button" class="btn btn-primary" onclick="javascript:fnsuggestionsToExcel()">Export To Excel</button>	
		</div>
	</div>
	
	<div class="row">
 		<div class="spacer20"></div>
 	</div>
 	
 	<div class="row">
	 	<div class="col-sm-12 text-center">
	 		<nav>
	 			<ul class="pagination pagination-lg" id="suggestPages"></ul>
	 		</nav>
	 	</div>
 	</div>
 	
 	<div class="row">
 		<div class="spacer20"></div>
 	</div>
 	
 	<ul class="nav nav-tabs nav-justified" id="suggestTab">
	  <li role="presentation"><a href='javascript:fnViewSuggestions(1)'>Pending </a></li>
	  <li role="presentation"><a href='javascript:fnViewSuggestions(2)'>Accepted </a></li>
	  <li role="presentation"><a href='javascript:fnViewSuggestions(3)'>Rejected </a></li>
	  <li role="presentation"><a href='javascript:fnViewSuggestions(4)'>Completed</a></li>
	</ul>
			
	<%-- <div class="form-group"	>
		<div class="col-sm-12 text-center"><h4><strong>
			<logic:equal name="SuggestForm" property="suggestStatusCode" value="1">
				<a href='javascript:fnViewSuggestions(1)' CLASS="text-green">Pending |</a>
				<a href='javascript:fnViewSuggestions(2)'>Accepted |</a>
				<a href='javascript:fnViewSuggestions(3)'>Rejected |</a>
				<a href='javascript:fnViewSuggestions(4)'>Completed</a>
			</logic:equal>
			<logic:equal name="SuggestForm" property="suggestStatusCode" value="2">
				<a href='javascript:fnViewSuggestions(1)'>Pending |</a>
				<a href='javascript:fnViewSuggestions(2)' CLASS="text-green">Accepted |</a>
				<a href='javascript:fnViewSuggestions(3)'>Rejected |</a>
				<a href='javascript:fnViewSuggestions(4)'>Completed</a>			
			</logic:equal>
			<logic:equal name="SuggestForm" property="suggestStatusCode" value="3">
				<a href='javascript:fnViewSuggestions(1)'>Pending |</a>
				<a href='javascript:fnViewSuggestions(2)'>Accepted |</a>
				<a href='javascript:fnViewSuggestions(3)' CLASS="text-green">Rejected |</a>
				<a href='javascript:fnViewSuggestions(4)'>Completed</a>			
			</logic:equal>
				<logic:equal name="SuggestForm" property="suggestStatusCode" value="4">
				<a href='javascript:fnViewSuggestions(1)'>Pending |</a>
				<a href='javascript:fnViewSuggestions(2)'>Accepted |</a>
				<a href='javascript:fnViewSuggestions(3)'>Rejected |</a>
				<a href='javascript:fnViewSuggestions(4)' CLASS="text-green">Completed</a>			
			</logic:equal></strong></h4>
		</div>
	</div>--%>

	<TABLE WIDTH="60%" ALIGN=center>
	<TR>
      <TD ALIGN="center" ID="insertPages"></TD>
    </TR>
	</TABLE>
	<logic:equal name="SuggestForm" property="suggestStatusCode" value="1">		
		<div id="pages">		
			<logic:iterate id="suggListId" name="SuggestForm" property="suggestionList" 
				type="com.EMD.LSDB.vo.common.SuggestVO" indexId="counter">
				<div class="page">
					<div class="panel panel-info">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-6">
									Initiated by <strong>
										<logic:empty name="suggListId" property="lastName">User</logic:empty>
										<logic:notEmpty name="suggListId" property="lastName">	
											<bean:write name="suggListId" property="firstName" />
											<bean:write name="suggListId" property="lastName" />
										</logic:notEmpty>
									</strong>
								</div>
								<div class="col-sm-6 text-right">
									<strong>Unique ID #</strong><bean:write name="suggListId" property="suggestId" />
								</div>
							</div>
						</div>
						<div class="panel-body">
							<div class="form-group">
								<div class="col-sm-6">
									In <strong><bean:write name="suggListId" property="screenName" /></strong>
								</div>
								<div class="col-sm-6 text-right">
									<bean:write name="suggListId" property="suggestDate" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-12">
									<textarea class="form-control" id="divSuggest<%=counter%>" rows="6" cols="145" style="overflow:hidden"><%=(String.valueOf(suggListId.getSuggestions()))%> </textarea>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-12">
									<logic:empty name="suggListId" property="imageName">
										<Strong>Attachments</strong> : 
									</logic:empty>
									<logic:notEmpty name="suggListId" property="imageName">
										<strong>Attachments</strong> :  <a href="javascript:fnViewAttachment(<bean:write name="suggListId" property="imgSeqNo" />,'<bean:write name="suggListId" property="imageName" />');" class="linkstyleItem" data-toggle="tooltip" title="Click to view"><bean:write name="suggListId" property="imageName" /></a> 
										<a data-toggle="tooltip" title="Click to delete Attachment" href="javascript:fnDeleteAttachment(<bean:write name="suggListId" property="suggestId" />,<bean:write name="suggListId" property="imgSeqNo" />);"><span class="glyphicon glyphicon-remove text-red" aria-hidden="true"></span></a>   
									</logic:notEmpty>	
								</div>	
							</div>	
							<div class="form-group">
								<div class="col-sm-12">
									<label>Administrator Comments :</label>
								</div>
								<div class="col-sm-12">
									<TEXTAREA name="suggestComments" class="form-control" id='suggestComments<%=counter%>' rows="2" cols="145" style="overflow:hidden"><bean:write name="suggListId" property="adminComments" /></TEXTAREA>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-12 text-center">
									<button class="btn btn-primary" type="button" onclick="javascript:fnUpdateSuggestions(<bean:write name="suggListId" property="suggestId" />,<bean:write name="counter" />,'S');">Save</button>
									<button class="btn btn-primary" type="button" onclick="javascript:fnUpdateSuggestions(<bean:write name="suggListId" property="suggestId" />,<bean:write name="counter" />,'Y');">Accept</button>
									<button class="btn btn-primary" type="button" onclick="javascript:fnUpdateSuggestions(<bean:write name="suggListId" property="suggestId" />,<bean:write name="counter" />,'N');">Reject</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</logic:iterate>
		</div>
	</logic:equal>
	
	<logic:equal name="SuggestForm" property="suggestStatusCode" value="2">		
		<div id="pages">		
		<logic:iterate id="suggListId" name="SuggestForm" property="suggestionList" 
			type="com.EMD.LSDB.vo.common.SuggestVO" indexId="counter">
			<div class="page">
				<div class="panel panel-success">
					<div class="panel-heading">
						<div class="row">
							<div class="col-sm-6">
								Initiated by <strong>
									<logic:empty name="suggListId" property="lastName">User</logic:empty>
									<logic:notEmpty name="suggListId" property="lastName">	
										<bean:write name="suggListId" property="firstName" />
										<bean:write name="suggListId" property="lastName" />
									</logic:notEmpty>
								</strong>
							</div>
							<div class="col-sm-6 text-right">
								<strong>Unique ID #</strong><bean:write name="suggListId" property="suggestId" />
							</div>
						</div>
					</div>
					<div class="panel-body">
						<div class="form-group">
							<div class="col-sm-6">
								In <strong><bean:write name="suggListId" property="screenName" /></strong>
							</div>
							<div class="col-sm-6 text-right">
								<bean:write name="suggListId" property="suggestDate" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">
								<textarea class="form-control" id="divSuggest<%=counter%>" rows="6" cols="145" style="overflow:hidden" > <%=(String.valueOf(suggListId.getSuggestions()))%> </textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">
								<logic:empty name="suggListId" property="imageName">
									<Strong>Attachments</strong> : 
								</logic:empty>
								<logic:notEmpty name="suggListId" property="imageName">
									<strong>Attachments</strong> :  <a href="javascript:fnViewAttachment(<bean:write name="suggListId" property="imgSeqNo" />,'<bean:write name="suggListId" property="imageName" />');" class="linkstyleItem" data-toggle="tooltip" title="Click to view"><bean:write name="suggListId" property="imageName" /></a> 
									<a data-toggle="tooltip" title="Click to delete Attachment" href="javascript:fnDeleteAttachment(<bean:write name="suggListId" property="suggestId" />,<bean:write name="suggListId" property="imgSeqNo" />);"><span class="glyphicon glyphicon-remove text-red" aria-hidden="true"> </span></a>
								</logic:notEmpty>	
							</div>	
						</div>	
						<div class="form-group">
							<div class="col-sm-12">
								<label>Administrator Comments :</label>
							</div>
							<div class="col-sm-12">
								<TEXTAREA name="suggestComments" class="form-control" id='suggestComments<%=counter%>' rows="2" cols="145" style="overflow:hidden"><bean:write name="suggListId" property="adminComments" /></TEXTAREA>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 text-center">
								<button class="btn btn-primary" type="button" onclick="javascript:fnUpdateSuggestions(<bean:write name="suggListId" property="suggestId" />,<bean:write name="counter" />,'Y');">Save</button>
								<button class="btn btn-primary" type="button" onclick="javascript:fnUpdateSuggestions(<bean:write name="suggListId" property="suggestId" />,<bean:write name="counter" />,'C');">Completed</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</logic:iterate>
		</div>
	</logic:equal>
		
	<logic:equal name="SuggestForm" property="suggestStatusCode" value="3">	
		<div id="pages">
		<logic:iterate id="suggListId" name="SuggestForm" property="suggestionList" 
			type="com.EMD.LSDB.vo.common.SuggestVO">
			<div class="page">
				<div class="panel panel-danger">
					<div class="panel-heading">
						<div class="row">
							<div class="col-sm-6">
								Initiated by <strong>
									<logic:empty name="suggListId" property="lastName">User</logic:empty>
									<logic:notEmpty name="suggListId" property="lastName">	
										<bean:write name="suggListId" property="firstName" />
										<bean:write name="suggListId" property="lastName" />
									</logic:notEmpty>
								</strong>
							</div>
							<div class="col-sm-6 text-right">
								<strong>Unique ID #</strong><bean:write name="suggListId" property="suggestId" />
							</div>
						</div>
					</div>
					<div class="panel-body">
						<div class="form-group">
							<div class="col-sm-6">
								In <strong><bean:write name="suggListId" property="screenName" /></strong>
							</div>
							<div class="col-sm-6 text-right">
								<bean:write name="suggListId" property="suggestDate" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-11">
								<p><%=(String.valueOf(suggListId.getSuggestions()))%></p>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">
								<logic:empty name="suggListId" property="imageName">
									<Strong>Attachments</strong> : 
								</logic:empty>
								<logic:notEmpty name="suggListId" property="imageName">
									<strong>Attachments</strong> :  <a href="javascript:fnViewAttachment(<bean:write name="suggListId" property="imgSeqNo" />,'<bean:write name="suggListId" property="imageName" />');" class="linkstyleItem" data-toggle="tooltip" title="Click to view"><bean:write name="suggListId" property="imageName" /></a> 
									<a  data-toggle="tooltip" title="Click to delete Attachment" href="javascript:fnDeleteAttachment(<bean:write name="suggListId" property="suggestId" />,<bean:write name="suggListId" property="imgSeqNo" />);"><span class="glyphicon glyphicon-remove text-red" aria-hidden="true"></span></a>   
								</logic:notEmpty>	
							</div>	
						</div>	
						<div class="form-group">
							<div class="col-sm-12">
								<label>Administrator Comments :</label>
							</div>
							<div class="col-sm-11">
								<bean:write name="suggListId" property="adminComments" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</logic:iterate>
		</div>
	</logic:equal>
	<logic:equal name="SuggestForm" property="suggestStatusCode" value="4">	
		<div id="pages">
		<logic:iterate id="suggListId" name="SuggestForm" property="suggestionList" 
			type="com.EMD.LSDB.vo.common.SuggestVO">
			<div class="page">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="row">
							<div class="col-sm-6">
								Initiated by <strong>
									<logic:empty name="suggListId" property="lastName">User</logic:empty>
									<logic:notEmpty name="suggListId" property="lastName">	
										<bean:write name="suggListId" property="firstName" />
										<bean:write name="suggListId" property="lastName" />
									</logic:notEmpty>
								</strong>
							</div>
							<div class="col-sm-6 text-right">
								<strong>Unique ID #</strong><bean:write name="suggListId" property="suggestId" />
							</div>
						</div>
					</div>
					<div class="panel-body">
						<div class="form-group">
							<div class="col-sm-6">
								In <strong><bean:write name="suggListId" property="screenName" /></strong>
							</div>
							<div class="col-sm-6 text-right">
								<bean:write name="suggListId" property="suggestDate" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-11">
								<p><%=(String.valueOf(suggListId.getSuggestions()))%></p>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">
								<logic:empty name="suggListId" property="imageName">
									<Strong>Attachments : </strong>
								</logic:empty>
								<logic:notEmpty name="suggListId" property="imageName">
									<strong>Attachments</strong> :  <a href="javascript:fnViewAttachment(<bean:write name="suggListId" property="imgSeqNo" />,'<bean:write name="suggListId" property="imageName" />');" class="linkstyleItem" data-toggle="tooltip" title="Click to view"><bean:write name="suggListId" property="imageName" /></a> 
									<a data-toggle="tooltip" title="Click to delete Attachment" href="javascript:fnDeleteAttachment(<bean:write name="suggListId" property="suggestId" />,<bean:write name="suggListId" property="imgSeqNo" />);"><span class="glyphicon glyphicon-remove text-red" aria-hidden="true"></span></a>
								</logic:notEmpty>	
							</div>	
						</div>	
						<div class="form-group">
							<div class="col-sm-12">
								<label>Administrator Comments :</label>
							</div>
							<div class="col-sm-11">
								<bean:write name="suggListId" property="adminComments" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</logic:iterate>
		</div>
	</logic:equal>

<html:hidden name="SuggestForm" property="suggestId" styleId="suggestId"/>
<html:hidden name="SuggestForm" property="suggestStatusCode" styleId="suggestStatusCode"/>
<html:hidden name="SuggestForm" property="updateStatusCode" styleId="updateStatusCode"/>
<html:hidden name="SuggestForm" property="hdnAdminComments" styleId="hdnAdminComments"/>
<html:hidden name="SuggestForm" property="suggestions" styleId="suggestions"/>
<html:hidden name="SuggestForm" property="imgSeqNo" styleId="imgSeqNo"/>
<html:hidden name="SuggestForm" property="selectedInitiator" styleId="selectedInitiator"/>
</html:form>
</div>
