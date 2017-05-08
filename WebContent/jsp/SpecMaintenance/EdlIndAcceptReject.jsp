<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<SCRIPT LANGUAGE="JavaScript" SRC="js/EdlIndAcceptReject.js"></SCRIPT>

<div class="container" width="100%">
  <html:form action="/EdlIndAcceptRejectAction" method="post" styleClass="form-horizontal">

	<!-- Application PageName Display starts here-->
	<h4 class="text-center"><bean:message key="Application.Screen.AccRejNewEDL" /></h4>
	<!-- Application PageName Display ends here-->

	<div class="spacer10"></div>
	<!-- Validation Message Display Part Starts Here -->
	<div class="row">
		<div class="errorlayerhide" id="errorlayer"></div>
	</div>
	<!-- Validation Message Display Part Ends Here -->
	<div class="spacer10"></div>

	<logic:present name="AcceptRejectClauseForm" property="clauseVersions"
		scope="request">
		<bean:size id="Clauselen" name="AcceptRejectClauseForm"
			property="clauseVersions" />
	</logic:present>
	<!-- Logic Tag Check For Display The No Records Found Message  Functionality Starts Here -->
	<logic:present name="AcceptRejectClauseForm" property="messageID"
		scope="request">
			<bean:define id="messageID" name="AcceptRejectClauseForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>

	</logic:present>
	<!-- Logic Tag Check For Display The No Records Found Message  Functionality Ends Here -->
	<html:hidden property="orderKey" />
	<html:hidden property="secSeq" />
	<html:hidden property="revCode" />
	<html:hidden property="subSecSeqNo"/>
	<html:hidden property="specStatusCode" />
	<html:hidden property="clauseSeqNo" />
	<html:hidden property="flag" />

	<logic:equal name="AcceptRejectClauseForm" property="method" 
		value="fetchClauseEdlChanges" scope="request">
		<logic:lessThan name="Clauselen" value="1">
			<div class="form-group">
				<div class="col-sm-12 text-center">
					<button class="btn btn-primary" type='button' ONCLICK="javascript:fnModifySpec()"><bean:message key="screen.returnmodifyspec" /></button>
				</div>
			</div>
		</logic:lessThan>
	</logic:equal>

	<logic:greaterThan name="Clauselen" value="0">
		<logic:present name="AcceptRejectClauseForm" property="clauseVersions">
			<TABLE class="table table-bordered">
				<thead>
					<TR>
						<TH colspan="6">EDL/Reference EDL Number Changes</TH>
						<TH rowspan="3" class="border-all">Modified By</TH>
						<TH rowspan="3">Modified Date</TH>
	
					</TR>
					
					<logic:iterate id="clauseRev" name="AcceptRejectClauseForm"
						property="clauseVersions" type="com.EMD.LSDB.vo.common.ClauseVO">					
					<TR>
						<logic:notEqual name="clauseRev" property="claEdlChngType" value="4">
							<TH colspan="3">Old</TH>
							<TH colspan="3">New</TH>
						</logic:notEqual>
						<logic:equal name="clauseRev" property="claEdlChngType" value="4">
							<TH colspan="3">Current Selection</TH>
							<TH colspan="3">Copy From Order Selection</TH>
						</logic:equal>
					</TR>
	
								
					<TR>
						<TH>Characteristic Components</TH>
						<TH>EDL#</TH>
						<TH>RefEDL#</TH>
						<TH>Characteristic Components</TH>
						<TH>EDL#</TH>
						<TH>RefEDL#</TH>
					</TR>
				</thead>
				<tbody>	
					<TR>				
						<logic:notEqual name="clauseRev" property="claEdlChngType" value="1">	
							
						<TD>
						<TABLE class="table table-bordered">
							<logic:iterate id="OldCompList" name="clauseRev"
													property="componentVO"
													type="com.EMD.LSDB.vo.common.ComponentVO" scope="page" >
								<TR>
									<TD class="text-center">
										<%=String.valueOf(OldCompList.getComponentName())%></TD>
								</TR>
							</logic:iterate>
						</TABLE>
						</TD>
						
						<TD class="v-midalign text-center">
							<logic:notEmpty name="clauseRev" property="componentVO">
								<logic:notEmpty name="clauseRev" property="oldCharEdlNo">
									<bean:write name="clauseRev" property="oldCharEdlNo" />
								</logic:notEmpty>
							</logic:notEmpty>
						</TD>
						
						<TD class="v-midalign text-center">
							<logic:notEmpty name="clauseRev" property="componentVO">
								<logic:notEmpty name="clauseRev" property="oldCharRefEDLNo">	
									<bean:write name="clauseRev" property="oldCharRefEDLNo" />
								</logic:notEmpty>
							</logic:notEmpty>
						</TD>
						</logic:notEqual>

						<logic:equal name="clauseRev" property="claEdlChngType" value="1">	
							<TD class="borderbottomrightlight">&nbsp;</TD>
							<TD class="borderbottomrightlight">&nbsp;</TD>
							<TD class="borderbottomrightlight">&nbsp;</TD>
						</logic:equal>	
						
						<logic:notEqual name="clauseRev" property="claEdlChngType" value="3">	
						
						<TD>
						<TABLE class="table table-bordered">
							<logic:iterate id="NewCompList" name="clauseRev"
													property="componentList" scope="page" >
								<TR>
									<TD class="text-center">
										<%=NewCompList%></TD>
								</TR>
							</logic:iterate>
						</TABLE>
						</TD>
						
						<TD class="v-midalign text-center">
							<logic:notEmpty name="clauseRev" property="componentList">
								<logic:notEmpty name="clauseRev" property="charEdlNo">
									<bean:write name="clauseRev" property="charEdlNo" />
								</logic:notEmpty>
							</logic:notEmpty>
						</TD>

						<TD class="v-midalign text-center">
							<logic:notEmpty name="clauseRev" property="componentList">
								<logic:notEmpty name="clauseRev" property="charRefEDLNo">	
									<bean:write name="clauseRev" property="charRefEDLNo" />
								</logic:notEmpty>
							</logic:notEmpty>
						</TD>
						</logic:notEqual>
						
						<logic:equal name="clauseRev" property="claEdlChngType" value="3">	
							<TD class="borderbottomrightlight">&nbsp;</TD>
							<TD class="borderbottomrightlight">&nbsp;</TD>
							<TD class="borderbottomrightlight">&nbsp;</TD>
						</logic:equal>	
						
						<TD class="v-midalign text-center">
							<bean:write name="clauseRev" property="modifiedBy" />
						</TD>
						
						<TD class="v-midalign text-center">
							<bean:write name="clauseRev" property="modifiedDate" />
						</TD>
						
					</TR>
				</tbody>
				</logic:iterate>
			</TABLE>
			
			<div class="form-group">
				<div class="col-sm-6 col-sm-offset-3">
	  				<fieldset class="scheduler-border">
	    			<legend class="scheduler-border">Spec Supplement</legend>
	    				<label class="col-sm-2 control-label">Reason
		    				<logic:greaterEqual name="AcceptRejectClauseForm" property="specStatusCode" value="3">
		    					<font color="red" valign="top">*</font>
		    				</logic:greaterEqual>
	    				</label>
	    				<div class="col-sm-8">
							<textarea class="form-control" name="reason" rows="3" cols="60"></textarea>
						</div>
	    			</fieldset>
    			</div>
    		</div>
			
			<div class="form-group">
				<div class="col-sm-12 text-center">
					<button class="btn btn-primary" type='button' id="acceptBtn" ONCLICK="javascript:fnAcceptEdlChange()">Accept</button>
					<button class="btn btn-primary" type='button' id="rejectBtn" ONCLICK="javascript:fnRejectEdlChange()">Reject</button>
					<button class="btn btn-primary" type='button' id="retunMdfyScrn" ONCLICK="javascript:fnModifySpec()">Return To Modify Spec</button>
				</div>
			</div>
			</logic:present>
	</logic:greaterThan>
</html:form>
<div class="spacer30"></div>
</div>