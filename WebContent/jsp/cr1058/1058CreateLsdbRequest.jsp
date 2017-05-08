<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<link href="css/bootstrap-datepicker.min.css" type="text/css" rel="stylesheet"/>
<SCRIPT type="text/javascript" SRC="js/Others/tiny_mce/jquery.tinymce.js"></script>
<SCRIPT type="text/javascript" SRC="js/Others/bootstrap.datepicker.min.js"></script>
<SCRIPT type="text/javaScript" SRC="js/1058CreateLsdbRequest.js"></SCRIPT>
<SCRIPT type="text/javaScript" SRC="js/1058RequestChanges.js"></SCRIPT>

<div class="container" width="100%">
<html:form action="/ChangeRequest1058Action" method="post" enctype="multipart/form-data" styleId="form" styleClass="form-horizontal hasColFormFields">

	<h4 class="text-center"><bean:message key="Application.Screen.RequestCommonDetails" /></h4>
	<div class="spacer10"></div>
	<!-- To display  Messages - Start -->
	<%--Table Updated for CR-121 for server side error message tooltip --%>
	<div class="row">
		<div class="errorlayerhide" id="errorlayer"></div>
	</div>
	<br>
	<!-- To display Messages - End -->



	<!-- To display Server Side Validation Messages - Start -->

	<logic:present name="ChangeRequest1058Form" property="messageID"
		scope="request">
	  <%--Added for CR-121 for server side error message tooltip --%>	
	  <bean:define id="messageID" name="ChangeRequest1058Form" property="messageID"/>
	  <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
      <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
	</logic:present>

	<!-- To display Server Side Validation Messages - End -->
	
	<logic:present name="ChangeRequest1058Form" property="claList">
		<bean:size id="claListLen" name="ChangeRequest1058Form"
			property="claList" />
			
	</logic:present>
	
	<logic:greaterThan name="ChangeRequest1058Form" property="requestDetailsList" value="0">
	<div>
    <logic:iterate id="requestCommonDetail" name="ChangeRequest1058Form" property="requestDetailsList"
				scope="request" type="com.EMD.LSDB.vo.common.ChangeRequest1058VO">
	
	<%-- Added for CR-127--%>
	<html:hidden styleId="mdlClaChangeListSize" name="ChangeRequest1058Form" property="mdlClaChangeListSize" />
	<%-- Added for CR-130--%>
	<html:hidden styleId="importSubSecListSize" name="ChangeRequest1058Form" property="importSubSecListSize" />

	<html:hidden styleId="seqNo1058" name="requestCommonDetail" property="seqNo1058" />
	<!-- Added by @rr108354 Starts here-->
	<html:hidden styleId="mdlSeqNo" name="requestCommonDetail" property="mdlSeqNo" />
	<html:hidden styleId="orderNo" name="requestCommonDetail" property="orderNo" />
	<html:hidden styleId="orderKey" name="requestCommonDetail" property="orderKey" />
	<html:hidden styleId="dataLocType" name="requestCommonDetail" property="dataLocType" />
	<html:hidden styleId="hdnSectionName" property="hdnSectionName" />
	<html:hidden styleId="hdnSubSectionName" property="hdnSubSectionName" />
	<html:hidden styleId="hdnOldComponentSeqNo" property="hdnOldComponentSeqNo" />
	<html:hidden styleId="hdnOldComponentName" property="hdnOldComponentName" />
	<html:hidden styleId="reviseClauseCheck" property="reviseClauseCheck" />
	<html:hidden styleId="checkCompChangeClauses" property="checkCompChangeClauses" />
	<html:hidden styleId="specType" name="requestCommonDetail" property="specType" />
	<!-- Added by @rr108354 ends here-->	
	<html:hidden styleId="statusSeqNo" name="requestCommonDetail" property="statusSeqNo" />		
	<html:hidden styleId="checkVersionClause" property="checkVersionClause" />
	
	<div class="panel panel-info">
		<div class="panel-heading">
			<h4 class="panel-title"><strong>Request Details</strong></h4>
		</div>
		<div class="panel-body">
			<div class="form-group">
				<label class="control-label col-sm-2">Order Number </label>
				<div class="col-sm-2">
					<INPUT TYPE="text" name="txtOrderNumber" class="form-control" disabled size="30"
						value="<bean:write name="requestCommonDetail" property="orderNo"/>">
				</div>
				<label class="control-label col-sm-5">Created On</label>
				<div class="col-sm-3 form-control-static">
					<bean:write name="requestCommonDetail" property="createdOn"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">1058#</label>
				<div class="col-sm-2 form-control-static">
					<bean:write name="requestCommonDetail" property="id1058" />
				</div>
				<label class="control-label col-sm-5">Status</label>
				<logic:equal name="requestCommonDetail" property="statusSeqNo"  value="1">
					<div class="col-sm-3 form-control-static bg-info"><strong><bean:write name="requestCommonDetail" property="statusDesc"/></strong></div>
				</logic:equal>
				<logic:equal name="requestCommonDetail" property="statusSeqNo"  value="2">
					<div class="col-sm-3 form-control-static bg-warning"><strong><bean:write name="requestCommonDetail" property="statusDesc"/></strong></div>
				</logic:equal>
				<logic:greaterThan name="requestCommonDetail" property="statusSeqNo"  value="5">
					<logic:notEqual name="requestCommonDetail" property="statusSeqNo"  value="10">
						<div class="col-sm-3 form-control-static bg-warning"><strong><bean:write name="requestCommonDetail" property="statusDesc"/></strong></div>
					</logic:notEqual>
				</logic:greaterThan>
				<logic:equal name="requestCommonDetail" property="statusSeqNo"  value="3">
					<div class="col-sm-3 form-control-static bg-success"><strong><bean:write name="requestCommonDetail" property="statusDesc"/></strong></div>
				</logic:equal>
				<logic:equal name="requestCommonDetail" property="statusSeqNo"  value="4">
					<div class="col-sm-3 form-control-static bg-danger"><strong><bean:write name="requestCommonDetail" property="statusDesc"/></strong></div>
				</logic:equal>
				<logic:equal name="requestCommonDetail" property="statusSeqNo"  value="5">
					<div class="col-sm-3 form-control-static bg-completed"><strong><bean:write name="requestCommonDetail" property="statusDesc"/></strong></div>
				</logic:equal>
				<logic:equal name="requestCommonDetail" property="statusSeqNo"  value="10">
					<div class="col-sm-3 form-control-static bg-cancelled"><strong><bean:write name="requestCommonDetail" property="statusDesc"/></strong></div>
				</logic:equal>
			</div>
			<div class="form-group required-field">
				<div class="multipleFormFields">
					<label class="control-label col-sm-2">Customer Name</label>
					<div class="col-sm-2" >
						<logic:equal name="requestCommonDetail" property="nonLsdbFlag" value="Y">
							<html:text name="requestCommonDetail" property="custName" styleClass="form-control partOfColFormField">
							</html:text>
						</logic:equal>
						<logic:notEqual name="requestCommonDetail" property="nonLsdbFlag" value="Y">
							<p class="form-control-static"><bean:write name="requestCommonDetail" property="custName"/></p>
						</logic:notEqual>
					</div>
				</div>
				<div class="multipleFormFields">
					<label class="control-label col-sm-5 required-field">Model</label>
					<div class="col-sm-2">
						<logic:equal name="requestCommonDetail" property="nonLsdbFlag" value="Y">
						<html:text name="requestCommonDetail" property="mdlName" styleClass="form-control partOfColFormField">
						</html:text>
						</logic:equal> 
						<logic:notEqual name="requestCommonDetail" property="nonLsdbFlag" value="Y">
							<p class="form-control-static"><bean:write name="requestCommonDetail" property="mdlName"/></p>
						</logic:notEqual>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="multipleFormFields">
					<label class="control-label col-sm-2 required-field">Quantity <font color="red">*</font></label>
					<div class="col-sm-2">
						<logic:equal name="requestCommonDetail" property="nonLsdbFlag" value="Y">
						<logic:greaterThan name="requestCommonDetail" property="orderQty" value="0">
							<html:text name="requestCommonDetail" property="orderQty" styleClass="form-control partOfColFormField">
							</html:text>
						</logic:greaterThan>
						<logic:equal name="requestCommonDetail" property="orderQty" value="0">
							<html:text property="orderQty" styleClass="form-control partOfColFormField" value="">
							</html:text>
						</logic:equal>
						</logic:equal> 
						<logic:notEqual name="requestCommonDetail" property="nonLsdbFlag" value="Y">
							<p class="form-control-static"><bean:write name="requestCommonDetail" property="orderQty" /></p>
						</logic:notEqual>
					</div>
				</div>
				<div class="multipleFormFields">
					<label class="control-label col-sm-5 required-field">Specification Revision</label>
					<div class="col-sm-2">
						<INPUT TYPE="text" name="specRev" CLASS="form-control partOfColFormField" size="20"
						value="<bean:write name="requestCommonDetail" property="specRev"/>">
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="panel panel-info">
		<div class="panel-heading">
			<h4 class="panel-title"><strong>User Details</strong></h4>
		</div>
		<div class="panel-body">
			<div class="form-group">
				<label class="control-label col-sm-2">Issued By</label>
				<div class="col-sm-2 form-control-static">
					<bean:write name="requestCommonDetail" property="userName"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">Phone#</label>
				<div class="col-sm-2 form-control-static">
					<bean:write name="requestCommonDetail" property="contactNo"/>
				</div>
			</div>
			
		</div>
	</div>
	<div>
	
	<!-- Added by @rr108354-->
	<jsp:include page="1058RequestDetails.jsp" />
	<bean:define id="pgScopeNonLsdbFlag" name="requestCommonDetail" property="nonLsdbFlag" />
	<logic:notEqual name="requestCommonDetail" property="nonLsdbFlag" value="Y">
		<jsp:include page="1058RequestedSpecChange.jsp" />
		<jsp:include page="1058RequestChangesDelMod.jsp" />
		<jsp:include page="1058RequestChangesAdd.jsp" />
		<jsp:include page="1058RequestChangesChngComp.jsp" />
		<jsp:include page="1058RequestChangesMdlChngs.jsp" /><%-- Added for CR-127--%>
		<jsp:include page="1058RequestChangesImportSubSec.jsp" /><%-- Added for CR-130--%>
		<jsp:include page="1058ReviseSelectedClause.jsp" />
	</logic:notEqual>
	<!--End Added by @rr108354-->
	
	<logic:equal name="requestCommonDetail" property="nonLsdbFlag" value="Y">
	<div class="panel panel-info">
		<div class="panel-heading">
			<h4 class="panel-title"><strong>Requested Specification Changes</strong></h4>
		</div>
		<div class="panel-body">
			<logic:greaterThan name="claListLen" value="0" > 
			<div id="data">
			<logic:iterate id="claDetail" name="ChangeRequest1058Form"
				property="claList" scope="request"
					type="com.EMD.LSDB.vo.common.ChangeRequest1058VO" >
					<table class="table table-bordered">
						<thead>								
							<TR>
								<TH width="5%">Select</TH>
								<TH width="10%">Description</TH>
								<TH width="10%">Specification Section</TH>
								<TH width="40%">Clause Description</TH>
								<TH width="20%">Engineering Data</TH>
							</TR>
						</thead>
						<TBODY>
							<TR>
								<TD class="v-midalign text-center" rowSpan=3>
									<INPUT type=radio name=clChRqSqNoRad class="nonLsdbClaSeq" value="<bean:write name="claDetail" property="claChangeReqSeqNo" />">
								</TD>
								<TD class="v-midalign text-center"><strong>Change From</strong></TD>
								<TD class="v-midalign text-center"><bean:write name="claDetail" property="changeFromSpec" /></TD>
								<%-- CR-130 - Added for Pdf issue --%>				
								<logic:present name="claDetail" property="changeFromClaDesc">
								<TD>
									<%if(String.valueOf(claDetail.getChangeFromClaDesc()).startsWith("<p>"))
									{%>
										<%=(String.valueOf(claDetail.getChangeFromClaDesc()))%>
									<%}else{ %>	
									<%=(String.valueOf(claDetail.getChangeFromClaDesc())).replaceAll("null","").replaceAll("\n","<br>")%>
									<%}%>
								</TD>
								</logic:present>
								<logic:notPresent name="claDetail" property="changeFromClaDesc" >
								<TD CLASS='text-center'>&nbsp;</TD>
								</logic:notPresent>
								<TD class="text-center"><bean:write name="claDetail" property="changeFromEngData" />
								</TD>
								<%-- CR-130- Added for Pdf issue Ends Here--%>
							</TR>
							<TR>
								<TD class="v-midalign text-center"><strong>Change To</strong></TD>
								<TD class="v-midalign text-center" style="word-wrap:break-word;" >&nbsp;<bean:write name="claDetail" property="changeToSpec" /></TD>
								<%-- CR-130 - Added for Pdf issue --%>
								<logic:present name="claDetail" property="changeToClaDesc">
								<TD>
								<%if(String.valueOf(claDetail.getChangeToClaDesc()).startsWith("<p>"))
								{%>
									<%=(String.valueOf(claDetail.getChangeToClaDesc()))%>
								<%}else{ %>	
								<%=(String.valueOf(claDetail.getChangeToClaDesc())).replaceAll("null","").replaceAll("\n","<br>")%>
								<%}%>
								</TD></logic:present>
								<logic:notPresent name="claDetail" property="changeToClaDesc" >
								<TD>&nbsp;</TD>
								</logic:notPresent>
								<%-- CR-130 - Added for Pdf issue Ends Here--%>
								<TD class="text-center" style="word-wrap:break-word;" >&nbsp;<bean:write name="claDetail" property="changeToEngData" />
								</TD>
							</TR>
							<TR class="text-center">
								<TD class="v-midalign"><strong>Reason</strong></TD>
								<TD class="v-midalign text-left"
									colSpan=3>&nbsp;<bean:write name="claDetail" property="reason" /></TD>
							</TR>
							
						</TBODY>
					</table>
			</logic:iterate>
			</div>
			</logic:greaterThan>
		
		<div class="form-group">
			<div class="col-sm-12 text-center">
				<button class="btn btn-primary" type='button' ONCLICK="javascript:fnAddClaEntry()">Add New Clause Entry</button>
				<logic:greaterThan name="claListLen" value="0" >
					<button class="btn btn-primary" type='button' ONCLICK="javascript:fnDeleteClause()">Delete Selected Clause</button>
					<button class="btn btn-primary" type='button' ONCLICK="javascript:fnReviseClause()">Revise Selected Clause</button>
				</logic:greaterThan>
			</div>		
		</div>
		
		<div id="add" style="display:none">
		<TABLE class="table table-bordered">
			<thead>
				<TR align="center" valign="center">
					<TH width="10%">Description</TH>
					<TH width="10%">Specification Section</TH>
					<TH width="40%">Clause Description</TH>
					<TH width="20%">Engineering Data</TH>
				</TR>
			</thead>
			<TBODY>
				<TR>
					<TD class="v-midalign text-center"><strong>Change From</strong></TD>
					<TD class="v-midalign text-center"><html:text
						name="ChangeRequest1058Form" property="changeFromSpec" styleId="changeFromSpec" styleClass="form-control" onblur="return fnCheckLength(this,100)" /></TD>
					<TD class="v-midalign"><html:textarea cols="80" rows="3"
						name="ChangeRequest1058Form" property="changeFromClaDesc" styleId="changeFromClaDesc" styleClass="form-control" onblur="return fnCheckLength(this,4000)" /></TD><!-- ID Added for CR-130 -->
					<TD class="v-midalign text-center"><html:textarea cols="30"
						rows="3" name="ChangeRequest1058Form" styleClass="form-control" 
						property="changeFromEngData" onblur="return fnCheckLength(this,4000)" /></TD>
				</TR>
				<TR>
					<TD class="v-midalign text-center"><strong>Change To</strong></TD>
					<TD class="v-midalign text-center"><html:text
						name="ChangeRequest1058Form" property="changeToSpec" onblur="return fnCheckLength(this,100)" styleClass="form-control" /></TD>
					<TD class="v-midalign"><html:textarea cols="80" rows="3"
						name="ChangeRequest1058Form" property="changeToClaDesc" styleId="changeToClaDesc" styleClass="form-control" onblur="return fnCheckLength(this,4000)" /></TD><!-- ID Added for CR-130 -->
					<TD class="v-midalign text-center"><html:textarea cols="30"
						rows="3" name="ChangeRequest1058Form" styleClass="form-control"
						property="changeToEngData" onblur="return fnCheckLength(this,4000)" /></TD>
				</TR>
				<TR class="text-center v-midalign">
					<TD class="v-midalign"><strong>Reason<font size=2 color="red">*</font></strong></TD>
					<TD class=text-left colSpan=3><html:textarea cols="50" rows="3"
						name="ChangeRequest1058Form" property="changeClaReason" onblur="return fnCheckLength(this,2000)" styleClass="form-control" /></TD>
				</TR>
			</TBODY>
		</TABLE>
		<div class="form-group">
						<div class="col-sm-12 text-center">
							<button class="btn btn-primary" type='button' id="addBtn" ONCLICK="javascript:fnAddClause()">Add</button>
							<button class="btn btn-primary" type='button' id="updateBtn" ONCLICK="javascript:fnUpdateClause()" style="display:none">Update</button>
						</div>
				</div>
		</div>
		</div>
	</div>
	</logic:equal>
	<jsp:include page="1058RepresentativeList.jsp" />
    
	<logic:greaterThan name="requestCommonDetail" property="statusSeqNo" value="1">
	
	<div class="panel panel-info">
		<div class="panel-heading">
			<h4 class="panel-title"><strong>Systems Engineer Section</strong></h4>
		</div>
		
		<div class="panel-body">
			<div class="form-group required-field">
				<label class="control-label col-sm-2">Systems Engineer</label>
				<div class="col-sm-2 form-control-static">
					<bean:write name="requestCommonDetail" property="sysEnggLastName" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">Date Received </label>
				<div class="col-sm-2 form-control-static">
					<bean:write name="requestCommonDetail" property="sysEnggDateReceived" />
				</div>
				<label class="control-label col-sm-5">Date Completed </label>
				<div class="col-sm-2 form-control-static">
					<bean:write name="requestCommonDetail" property="sysEnggDateCompleted" />
				</div>
			</div>
			<div class="form-group required-field">
				<label class="control-label col-sm-2">Systems Engineer Comments</label>
				<div class="col-sm-6">
					<html:textarea name="requestCommonDetail" styleClass="form-control" 
						property="systemEngComment" rows="4" cols="120"></html:textarea>
				</div>
			</div>
			<div class="form-group required-field">
				<div class="multipleFormFields">
					<label class="control-label col-sm-2">Change Affects WEIGHT</label>
					<div class="col-sm-3">
						<label class="radio-inline  custom-radio">
							<html:radio styleClass="partOfColFormField" name="requestCommonDetail" property="changeAffectsWeight" value="Y" ></html:radio><strong> Yes</strong>
						</label> 
						<label class="radio-inline  custom-radio">
							<html:radio styleClass="partOfColFormField" name="requestCommonDetail" property="changeAffectsWeight" value="N"></html:radio><strong> No</strong>
						</label> 
					</div>
				</div>
				<div class="multipleFormFields">
					<label class="control-label col-sm-4">Est.Design Hrs</label>
					<div class="col-sm-2">
						<html:text name="requestCommonDetail" property="designHrs" styleClass="form-control partOfColFormField"/> 
					</div>
				</div>
			</div>
			<div class="form-group required-field">
				<div class="multipleFormFields">
					<label class="control-label col-sm-2">Change Affects CLEARANCE</label>
					<div class="col-sm-3">
						<label class="radio-inline  custom-radio">
							<html:radio styleClass="partOfColFormField" name="requestCommonDetail" property="changeAffectsClear" value="Y"></html:radio><strong> Yes</strong>
						</label>					
						<label class="radio-inline custom-radio"> 
							<html:radio styleClass="partOfColFormField" name="requestCommonDetail" property="changeAffectsClear" value="N"></html:radio><strong> No</strong>
						</label>
					</div>
				</div>
				<div class="multipleFormFields">
					<label class="control-label col-sm-4">Est.Drafting Hrs</label>
					<div class="col-sm-2">
						<html:text name="requestCommonDetail" property="draftingHrs" styleClass="form-control partOfColFormField"/> 
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="multipleFormFields">
					<label class="control-label col-sm-2">Work Order(USD)<font color="red">*</font></label>
					<div class="col-sm-2">
						<div class="input-group">	
							<span class="input-group-addon">$</span>
							<html:text name="requestCommonDetail" property="workOrderUSD" styleClass="form-control partOfColFormField"/>
						</div>
					</div>
				</div>
				<label class="control-label col-sm-5">Drawing Due Date</label>
				<div class="col-sm-2">
					<div class="input-group date">
						<html:text name="requestCommonDetail" property="drawingDueDate" styleClass="form-control" styleId="drawingDueDate"/>
					    <div class="input-group-addon">
					        <span class="glyphicon glyphicon-time"></span>
					    </div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">Earliest Station Affected</label>
				<div class="col-sm-4">
					<html:textarea name="requestCommonDetail" property="stationAffected" styleClass="resizeStationAffected form-control" 
						styleId="earliestSA" onkeyup="fnSetMaxSize(this, 3999);">
					</html:textarea>
				</div>
				<label class="control-label col-sm-3">Est.Completion Date</label>
				<div class="col-sm-2">
					<div class="input-group date">
						<html:text name="requestCommonDetail" property="completionDate" styleClass="form-control" styleId="completionDate"/>
					    <div class="input-group-addon">
					        <span class="glyphicon glyphicon-time"></span>
					    </div>
					</div>
				</div>
			</div>
			<div class="form-group required-field">
				<label class="control-label col-sm-2">Part# Added</label>
				<div class="col-sm-6">
					<html:textarea name="requestCommonDetail" property="partNoAdded"
						styleClass="resizePartAddDelete form-control" styleId="partAdded" onkeyup="fnSetMaxSize(this, 3999);" />
				</div>
			</div>
			<div class="form-group required-field">
				<label class="control-label col-sm-2">Part# Deleted</label>
				<div class="col-sm-6">
					<html:textarea name="requestCommonDetail" property="partNoDeleted"
					styleClass="form-control resizePartAddDelete" styleId="partDeleted" onkeyup="fnSetMaxSize(this, 3999);" />
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-12 text-center">
					<logic:equal name="requestCommonDetail" property="statusSeqNo" value="2">
						<logic:equal name="ChangeRequest1058Form" property="roleID" value="VALID_USER">
							<button class="btn btn-primary" type='button' ONCLICK="javascript:fnApproveSysEng()">Approve</button>
							<button class="btn btn-primary" type='button' ONCLICK="javascript:fnRejectSysEng()">Reject</button>
						</logic:equal>
						<logic:notEqual name="ChangeRequest1058Form" property="roleID"	value="VALID_USER">
							<logic:equal name="requestCommonDetail" property="ownerFlag" value="Y">
								<button class="btn btn-primary" type='button' ONCLICK="javascript:fnApproveSysEng()">Approve</button>
								<button class="btn btn-primary" type='button' ONCLICK="javascript:fnRejectSysEng()">Reject</button>
							</logic:equal>
							<logic:notEqual name="requestCommonDetail" property="ownerFlag" value="Y">
								<button class="btn btn-primary" type='button' disabled ONCLICK="javascript:fnApproveSysEng()">Approve</button>
								<button class="btn btn-primary" type='button' disabled ONCLICK="javascript:fnRejectSysEng()">Reject</button>
							</logic:notEqual>
						</logic:notEqual>
					</logic:equal>
					<logic:notEqual name="requestCommonDetail" property="statusSeqNo" value="2">
						<button class="btn btn-primary" type='button' disabled>Approve</button>
						<button class="btn btn-primary" type='button' disabled>Reject</button>
					</logic:notEqual>
				</div>
			</div>
		
		</div>
	</div>
	
	<div class="panel panel-info">
		<div class="panel-heading">
			<h4 class="panel-title"><strong>Operations Section</strong></h4>
		</div>
		
		<div class="panel-body">
			<div class="form-group required-field">
				<label class="control-label col-sm-2">Material Management</label>
				<div class="col-sm-2 form-control-static">
					<bean:write name="requestCommonDetail" property="oprEnggLastName" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">Date Received</label>
				<div class="col-sm-2 form-control-static">
					<bean:write name="requestCommonDetail" property="oprEnggDateReceived" />
				</div>
				<label class="control-label col-sm-5">Date Completed </label>
				<div class="col-sm-2 form-control-static">
					<bean:write name="requestCommonDetail" property="oprEnggDateCompleted" />
				</div>
			</div>
			<div class="form-group required-field">
				<label class="control-label col-sm-2">Operation Comments</label>
				<div class="col-sm-6">
					<html:textarea name="requestCommonDetail" styleClass="form-control" 
						property="operationComments" rows="4" cols="120"></html:textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="multipleFormFields">
					<label class="control-label col-sm-2">Disposition of Surplus Material<font color="red">*</font></label>
					<div class="col-sm-4">
						<html:textarea name="requestCommonDetail" property="disposExcessMaterial" styleClass="form-control resizeStationAffected partOfColFormField" 
							styleId="dispositionSM" onkeyup="fnSetMaxSize(this, 3999);">
						</html:textarea>
					</div>
				</div>
				<div class="multipleFormFields">
					<label class="control-label col-sm-3">Supplier Affected Costs/ Fees/ Effectivity</label>
					<div class="col-sm-3">
						<html:textarea name="requestCommonDetail" property="supplierAffectedCost" styleClass="form-control resizeSupplierAffected partOfColFormField" 
							styleId="supplierACFE" onkeyup="fnSetMaxSize(this, 3999);">
					</html:textarea>
					</div>
				</div>
			</div>
			<div class="form-group required-field">
				<div class="multipleFormFields">
					<label class="control-label col-sm-2">Est.Labor Impact (Hours)</label>
					<div class="col-sm-2">
						<html:text name="requestCommonDetail" property="laborImpact" styleClass="form-control partOfColFormField">
						</html:text>
					</div>
				</div>
				<div class="multipleFormFields">
					<label class="control-label col-sm-5">Recommended Effective Delivery (Unit #)</label>
					<div class="col-sm-2">
						<html:text name="requestCommonDetail" property="recEffectivityDel" styleClass="form-control partOfColFormField">
						</html:text>
					</div>
				</div>
			</div>
			<div class="form-group required-field">
				<div class="multipleFormFields">
					<label class="control-label col-sm-2">Tooling Cost(USD)</label>
					<div class="col-sm-2">
						<div class="input-group">	
							<span class="input-group-addon">$</span>
							<html:text name="requestCommonDetail" property="toolingCostUSD" styleClass="form-control partOfColFormField"></html:text>
						</div>
					</div>
				</div>
				<div class="multipleFormFields">
					<label class="control-label col-sm-5">Est.Scrap in $'s (USD)</label>
					<div class="col-sm-2">
						<div class="input-group">	
							<span class="input-group-addon">$</span>
							<html:text name="requestCommonDetail" property="scrapCostUSD" styleClass="form-control partOfColFormField"></html:text>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group required-field">
				<div class="multipleFormFields">
					<label class="control-label col-sm-2">Rework Expense (USD)</label>
					<div class="col-sm-2">
						<div class="input-group">	
							<span class="input-group-addon">$</span>
							<html:text name="requestCommonDetail" property="reworkCost" styleClass="form-control partOfColFormField"></html:text>
						</div>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-12 text-center">
					<logic:equal name="requestCommonDetail" property="statusSeqNo" value="6">
						<logic:equal name="ChangeRequest1058Form" property="roleID"	value="VALID_USER">
							<button class="btn btn-primary" type='button' ONCLICK="javascript:fnApproveOprSec()">Approve</button>
							<button class="btn btn-primary" type='button' ONCLICK="javascript:fnRejectOprSec()">Reject</button>
						</logic:equal>
						<logic:notEqual name="ChangeRequest1058Form" property="roleID" value="VALID_USER">
							<logic:equal name="requestCommonDetail" property="ownerFlag" value="Y">
								<button class="btn btn-primary" type='button' ONCLICK="javascript:fnApproveOprSec()">Approve</button>
								<button class="btn btn-primary" type='button' ONCLICK="javascript:fnRejectOprSec()">Reject</button>
							</logic:equal>
							<logic:notEqual name="requestCommonDetail" property="ownerFlag" value="Y">
								<button class="btn btn-primary" type='button' disabled ONCLICK="javascript:fnApproveOprSec()">Approve</button>
								<button class="btn btn-primary" type='button' disabled ONCLICK="javascript:fnRejectOprSec()">Reject</button>
							</logic:notEqual>
						</logic:notEqual>
					</logic:equal>
					<logic:notEqual name="requestCommonDetail" property="statusSeqNo" value="6">
						<button class="btn btn-primary" type='button' disabled>Approve</button>
						<button class="btn btn-primary" type='button' disabled>Reject</button>				
					</logic:notEqual>	
				</div>
			</div>
		</div>
	</div>
	
	<div class="panel panel-info">
		<div class="panel-heading">
			<h4 class="panel-title"><strong>Finance Section</strong></h4>
		</div>
		
		<div class="panel-body">
			<div class="form-group">
				<label class="control-label col-sm-2">Finance Contact</label>
				<div class="col-sm-2 form-control-static">
					<bean:write name="requestCommonDetail" property="financeLastName" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">Date Received </label>
				<div class="col-sm-2 form-control-static">
					<bean:write name="requestCommonDetail" property="financeDateReceived" />
				</div>
				<label class="control-label col-sm-5">Date Completed </label>
				<div class="col-sm-2 form-control-static">
					<bean:write name="requestCommonDetail" property="financeDateCompleted" />
				</div>
			</div>
			<div class="form-group required-field">
				<label class="control-label col-sm-2">Finance Comments</label>
				<div class="col-sm-6">
					<html:textarea name="requestCommonDetail" property="financeComments" styleClass="form-control" rows="4" cols="120">
					</html:textarea>
				</div>
			</div>
			<div class="form-group required-field">
				<label class="control-label col-sm-2">Est. Product Cost Change</label>
				<div class="col-sm-6">
					<html:textarea name="requestCommonDetail" property="prodCostChange" styleClass="form-control" rows="2" cols="120"></html:textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">Est. Product Cost Change for Supplier</label>
				<div class="col-sm-6">
					<html:textarea name="requestCommonDetail" property="prodCostChangeSup" styleClass="form-control" rows="2" cols="120"></html:textarea>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-12 text-center">
					<logic:equal name="requestCommonDetail" property="statusSeqNo" value="7">
						<logic:equal name="ChangeRequest1058Form" property="roleID"	value="VALID_USER">
							<button class="btn btn-primary" type='button' ONCLICK="javascript:fnApproveFinSec()">Approve</button>
							<button class="btn btn-primary" type='button' ONCLICK="javascript:fnRejectFinSec()">Reject</button>
						</logic:equal>
						<logic:notEqual name="ChangeRequest1058Form" property="roleID"	value="VALID_USER">
							<logic:equal name="requestCommonDetail" property="ownerFlag"	value="Y">
								<button class="btn btn-primary" type='button' ONCLICK="javascript:fnApproveFinSec()">Approve</button>
								<button class="btn btn-primary" type='button' ONCLICK="javascript:fnRejectFinSec()">Reject</button>
							</logic:equal>
							<logic:notEqual name="requestCommonDetail" property="ownerFlag" value="Y">
								<button class="btn btn-primary" type='button' disabled ONCLICK="javascript:fnApproveFinSec()">Approve</button>
								<button class="btn btn-primary" type='button' disabled ONCLICK="javascript:fnRejectFinSec()">Reject</button>
							</logic:notEqual>
						</logic:notEqual>
					</logic:equal>	
					<logic:notEqual name="requestCommonDetail" property="statusSeqNo" value="7">
						<button class="btn btn-primary" type='button' disabled>Approve</button>
						<button class="btn btn-primary" type='button' disabled>Reject</button>				
					</logic:notEqual>
				</div>
			</div>
		</div>
	</div>
	
	<div class="panel panel-info">
		<div class="panel-heading">
			<h4 class="panel-title"><strong>Program Manager Section</strong></h4>
		</div>
		
		<div class="panel-body">
			<div class="form-group required-field">
				<label class="control-label col-sm-2">Program Manager Contact</label>
				<div class="col-sm-2 form-control-static">
					<bean:write name="requestCommonDetail" property="pgmMgrLastName" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">Date Received </label>
				<div class="col-sm-2 form-control-static">
					<bean:write name="requestCommonDetail" property="progManagerDateReceived" />
				</div>
				<label class="control-label col-sm-5">Date Completed </label>
				<div class="col-sm-2 form-control-static">
					<bean:write name="requestCommonDetail" property="progManagerDateCompleted" />
				</div>
			</div>
			<div class="form-group required-field">
				<label class="control-label col-sm-2">Program Manager Comments</label>
				<div class="col-sm-6">
					<html:textarea name="requestCommonDetail" property="progManagerComments" styleClass="form-control" rows="4" cols="120">
					</html:textarea>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-12 text-center">
					<logic:equal name="requestCommonDetail" property="statusSeqNo" value="8">
						<logic:equal name="ChangeRequest1058Form" property="roleID"	value="VALID_USER">
							<button class="btn btn-primary" type='button' ONCLICK="javascript:fnApproveProgMgrSec()">Approve</button>
							<button class="btn btn-primary" type='button' ONCLICK="javascript:fnRejectProgMgrSec()">Reject</button>
						</logic:equal>
						<logic:notEqual name="ChangeRequest1058Form" property="roleID" value="VALID_USER">
							<logic:equal name="requestCommonDetail" property="ownerFlag"	value="Y">
								<button class="btn btn-primary" type='button' ONCLICK="javascript:fnApproveProgMgrSec()">Approve</button>
								<button class="btn btn-primary" type='button' ONCLICK="javascript:fnRejectProgMgrSec()">Reject</button>
							</logic:equal>
							<logic:notEqual name="requestCommonDetail" property="ownerFlag" value="Y">
								<button class="btn btn-primary" type='button' disabled ONCLICK="javascript:fnApproveProgMgrSec()">Approve</button>
								<button class="btn btn-primary" type='button' disabled ONCLICK="javascript:fnRejectProgMgrSec()">Reject</button>
							</logic:notEqual>
						</logic:notEqual>
					</logic:equal>
					<logic:notEqual name="requestCommonDetail" property="statusSeqNo" value="8">
						<button class="btn btn-primary" type='button' disabled>Approve</button>
						<button class="btn btn-primary" type='button' disabled>Reject</button>				
					</logic:notEqual>
				</div>
			</div>
		</div>
	</div>
	
	<div class="panel panel-info">
		<div class="panel-heading">
			<h4 class="panel-title"><strong>Proposal Manager Section</strong></h4>
		</div>
		
		<div class="panel-body">
			<div class="form-group required-field">
				<label class="control-label col-sm-2">Proposal Manager</label>
				<div class="col-sm-2 form-control-static">
					<bean:write name="requestCommonDetail" property="propMgrLastName" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">Date Received </label>
				<div class="col-sm-2 form-control-static">
					<bean:write name="requestCommonDetail" property="propManagerDateReceived" />
				</div>
				<label class="control-label col-sm-5">Date Completed </label>
				<div class="col-sm-2 form-control-static">
					<bean:write name="requestCommonDetail" property="propManagerDateCompleted" />
				</div>
			</div>
			<div class="form-group required-field">
				<label class="control-label col-sm-2">Proposal Manager Comments</label>
				<div class="col-sm-6">
					<html:textarea name="requestCommonDetail" property="propManagerComments" styleClass="form-control" rows="4" cols="120">
					</html:textarea>
				</div>
			</div>
			<div class="form-group required-field">
				<div class="multipleFormFields">
					<label class="control-label col-sm-2">Sell Price Submitted To Customer(USD) </label>
					<div class="col-sm-2">
						<div class="input-group">	
							<span class="input-group-addon">$</span>
							<html:text name="requestCommonDetail" property="sellpriceCustomer" styleClass="form-control partOfColFormField"></html:text>
						</div>
					</div>
				</div>
				<div class="multipleFormFields">
					<label class="control-label col-sm-5">Mark Up</label>
					<div class="col-sm-2">
						<html:text name="requestCommonDetail" property="markUp" styleClass="form-control partOfColFormField"></html:text>
					</div>
				</div>
			</div>
			<div class="form-group required-field">
				<label class="control-label col-sm-2">Customer Approval</label>
				<div class="col-sm-5">
					<label class="radio-inline custom-radio">
						<html:radio name="requestCommonDetail" property="customerApprovalReq" value="Required" styleId="customerApprovalReq"/><strong> Required</strong>
					</label>
					<label class="radio-inline custom-radio">
						<html:radio name="requestCommonDetail" property="customerApprovalReq" value="Not Required" styleId="customerApprovalnReq"/><strong> Not Required</strong>
					</label>
				</div>
			</div>
			<div class="hdn">
				<div class="form-group required-field">
					<label class="control-label col-sm-2">Customer Decision</label>
					<div class="col-sm-5">
						<label class="radio-inline custom-radio">
							<html:radio name="requestCommonDetail" property="customerDecision" value="Pending" styleId="customerDecPen"/><strong> Pending</strong>
						</label>
						<label class="radio-inline custom-radio">
							<html:radio name="requestCommonDetail" property="customerDecision" value="Approved" styleId="customerDecApp"/><strong> Approved</strong>
						</label>
						<label class="radio-inline custom-radio">
							<html:radio name="requestCommonDetail" property="customerDecision" value="Rejected" styleId="customerDecRej"/><strong> Rejected</strong>
						</label>			
					</div>
				</div>
				<div class="form-group">
					<div class="multipleFormFields">
						<label class="control-label col-sm-2">Count of Days</label>
						<div class="col-sm-2">
							<html:text name="requestCommonDetail" property="countDays" styleClass="form-control partOfColFormField" disabled="true"></html:text>			
						</div>
					</div>
					<div class="multipleFormFields">
						<label class="control-label col-sm-5">Customer Decision Date</label>
						<div class="col-sm-2">
							<div class="input-group date">
								<html:text name="requestCommonDetail" property="customerDecisionDate" styleClass="form-control partOfColFormField" styleId="customerDecisionDate"/>
							    <div class="input-group-addon">
							        <span class="glyphicon glyphicon-time"></span>
							    </div>
							</div>	
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2">Customer Approval Details</label>
					<div class="col-sm-6">
						<html:textarea name="requestCommonDetail" property="custApprovalDet" styleClass="form-control" rows="4" cols="100">
						</html:textarea>			
					</div>
				</div>
			</div>
			<div class="form-group required-field">
				<label class="control-label col-sm-2">Actual Sell Price(USD)</label>
				<div class="col-sm-2">
					<div class="input-group">	
						<span class="input-group-addon">$</span>
						<html:text name="requestCommonDetail" property="actualSellPrice" styleClass="form-control"></html:text>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-12 text-center">
					<logic:equal name="requestCommonDetail" property="statusSeqNo" value="9">
						<logic:equal name="ChangeRequest1058Form" property="roleID"	value="VALID_USER">
							<button class="btn btn-primary" type='button' ONCLICK="javascript:fnApprovePropMgrSec()">Approve</button>
							<button class="btn btn-primary" type='button' ONCLICK="javascript:fnRejectPropMgrSec()">Reject</button>
						</logic:equal>
						<logic:notEqual name="ChangeRequest1058Form" property="roleID"	value="VALID_USER">
							<logic:equal name="requestCommonDetail" property="ownerFlag"	value="Y">
								<button class="btn btn-primary" type='button' ONCLICK="javascript:fnApprovePropMgrSec()">Approve</button>
								<button class="btn btn-primary" type='button' ONCLICK="javascript:fnRejectPropMgrSec()">Reject</button>
							</logic:equal>
							<logic:notEqual name="requestCommonDetail" property="ownerFlag" value="Y">
								<button class="btn btn-primary" type='button' disabled ONCLICK="javascript:fnApprovePropMgrSec()">Approve</button>
								<button class="btn btn-primary" type='button' disabled ONCLICK="javascript:fnRejectPropMgrSec()">Reject</button>
							</logic:notEqual>
						</logic:notEqual>
					</logic:equal>
					<logic:notEqual name="requestCommonDetail" property="statusSeqNo" value="9">
							<button class="btn btn-primary" type='button' disabled>Approve</button>
							<button class="btn btn-primary" type='button' disabled>Reject</button>				
					</logic:notEqual>
				</div>
			</div>
		</div>
	</div>
	</logic:greaterThan>
	
		
	<div class="panel panel-info">
		<div class="panel-heading">
			<h4 class="panel-title text-center">
				<a data-toggle="collapse" data-target="#collapseOne" href="#collapseOne" class="collapsed"><strong>ACTION RECORD</strong></a>
			</h4>
		</div>
		<div id="collapseOne" class="panel-collapse collapse ">
			<div class="panel-body">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>USER</th>
							<th>SECTION</th>
							<th>ACTION</th>
							<th>ACTION DATE</th>
						</tr>
					</thead>
					<tbody>
						<logic:iterate id="actionDetail" name="requestCommonDetail" property="actionList"
					 		type="com.EMD.LSDB.vo.common.ChangeRequest1058VO">
							<TR class="text-center v-midalign">
								<TD><bean:write name="actionDetail" property="userName" /></TD>
								<TD>
									<logic:notEmpty name="actionDetail" property="section"> 
										<bean:write name="actionDetail" property="section" /> 
									</logic:notEmpty>
									<logic:empty name="actionDetail" property="section"> 
										&nbsp;
									</logic:empty>
								</TD>
			                    <TD><bean:write name="actionDetail" property="action" /></TD>
								<TD><bean:write name="actionDetail" property="actionDate" /></TD>
							</TR>
						</logic:iterate>
					</tbody>
				</table>				
			</div>
		</div>
	</div>
		
	<div class="spacer20"></div>
			
	<div class="form-group">
		<div class="col-sm-12 text-center">
	   		<logic:equal name="ChangeRequest1058Form" property="roleID" value="VALID_USER">
				<button class="btn btn-primary" type='button' id="save" ONCLICK="javascript:fnsave()">Save</button>
			</logic:equal>
	   		<logic:notEqual name="ChangeRequest1058Form" property="roleID" value="VALID_USER">
				<logic:equal name="requestCommonDetail" property="ownerFlag" value="Y">
					<button class="btn btn-primary" type='button' id="save" ONCLICK="javascript:fnsave()">Save</button>
				</logic:equal>
				<logic:notEqual name="requestCommonDetail" property="ownerFlag" value="Y">
					<button class="btn btn-primary" type='button' id="save" ONCLICK="javascript:fnsave()" disabled>Save</button>
				</logic:notEqual>
			</logic:notEqual>
			<logic:notEqual name="requestCommonDetail" property="statusSeqNo"  value="1">
				<logic:equal name="ChangeRequest1058Form" property="roleID" value="VALID_USER">
					<button class="btn btn-primary" type='button' id="btnResetStatus" onclick="javascript:fncomplete('1');">Reset Status to Draft</button>
				</logic:equal>
				<logic:notEqual name="ChangeRequest1058Form" property="roleID" value="VALID_USER">
					<logic:equal name="requestCommonDetail" property="ownerFlag" value="Y">
						<button class="btn btn-primary" type='button' id="btnResetStatus" onclick="javascript:fncomplete('1');">Reset Status to Draft</button>
					</logic:equal>
				</logic:notEqual>
			</logic:notEqual>
		
			<button class="btn btn-primary" type='button' onclick="javascript:fnresetlastsave();">Reset To Last Saved Data</button>
			
			<logic:notEqual name="requestCommonDetail" property="statusSeqNo"  value="5">
				<logic:equal name="ChangeRequest1058Form" property="roleID"  value="VALID_USER">
					<button class="btn btn-primary" type='button' onclick="javascript:fncomplete('10');">Cancel Request</button>
				</logic:equal>
				<logic:notEqual name="ChangeRequest1058Form" property="roleID"  value="VALID_USER">
					<logic:equal name="requestCommonDetail" property="ownerFlag" value="Y">
						<button class="btn btn-primary" type='button' onclick="javascript:fncomplete('10');">Cancel Request</button>
					</logic:equal>
				</logic:notEqual>
			</logic:notEqual>
			<logic:equal name="requestCommonDetail" property="statusSeqNo"  value="5">
				<button class="btn btn-primary" type='button' disabled>Cancel Request</button>
			</logic:equal>
			
			<logic:equal name="requestCommonDetail" property="statusSeqNo"  value="3">
				<logic:equal name="ChangeRequest1058Form" property="roleID"  value="VALID_USER">
					<button class="btn btn-primary" type='button' onclick="javascript:fncomplete('5');">Complete</button>
				</logic:equal>
				<logic:notEqual name="ChangeRequest1058Form" property="roleID"  value="VALID_USER">
					<button class="btn btn-primary" type='button' disabled>Complete</button>
				</logic:notEqual>
			</logic:equal>
			<logic:notEqual name="requestCommonDetail" property="statusSeqNo"  value="3">
				<button class="btn btn-primary" type='button' disabled>Complete</button>
			</logic:notEqual>
		</div>
	</div>
</Div>
	
<html:hidden property="hdnNonLsdbFlag" value='<%=String.valueOf(requestCommonDetail.getNonLsdbFlag())%>'/>
<html:hidden property="ownerFlag" styleId="ownerFlag" value='<%=String.valueOf(requestCommonDetail.getOwnerFlag())%>'/>
<html:hidden property="id1058" styleId="id1058"  value='<%=String.valueOf(requestCommonDetail.getId1058())%>'/>
</logic:iterate>
</div>
</logic:greaterThan>
<html:hidden property="hdnSelectedChangeReqSeqNo" />
<html:hidden property="sectionStatusSeq" /> 
<html:hidden property="roleID" styleId="roleID"/>
<html:hidden property="role" styleId="role"/>
<logic:equal name="pgScopeNonLsdbFlag" value="N">
<jsp:include page="/jsp/common/ClauseComparison.jsp" />
<%-- <%@ include file="/jsp/common/spellChecker1058.jsp" %> --%>
</logic:equal>
<%--Added for CR-117 --%>
<html:hidden property="hdnSpecRev" />
</html:form>
</div>
<br/>