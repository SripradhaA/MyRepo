<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT type="text/javascript" SRC="js/SpecTypeMaint.js"></SCRIPT>
   <div class="container-fluid">
   <html:form action="/SpecTypeAction" method="post">
   		<h4 class="text-center"><bean:message key="Application.Screen.SpecificationMaintenance" /></h4>
		<div class="spacer10"></div>
		<div class="errorlayerhide" id="errorlayer">
		</div>
		<logic:present name="SpecTypeMaintForm" property="messageID" scope="request">
			<bean:define id="messageID" name="SpecTypeMaintForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
		</logic:present>
		
		<logic:present name="SpecTypeMaintForm" property="specTypeList">
			<bean:size id="specTypesListLen" name="SpecTypeMaintForm" property="specTypeList" />
		</logic:present>
		<div class="spacer10"></div>
		<div id="spectype" class="form-horizontal">
			<div class="form-group required-field">
				<label class="col-sm-5 control-label"><bean:message key="specificationtype" /> </label>
				<div class="col-sm-2">					
					<html:text styleClass="form-control" property="specTypeName" maxlength="100" styleId="specTypeName"/>
				</div>
			</div>
			<div class="form-group">			
				<label class="col-sm-5 control-label"><bean:message key="spectypedesc" /> </label>
				<div class="col-sm-2">					
					<html:textarea styleClass="form-control" property="specTypeDesc" styleId="specTypeDesc"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-1">
					<button class="btn btn-primary" type='button' id="addButton" ONCLICK="javascript:fnAddSpecType()"><bean:message key="spectype.Add" /></button>
				</div>
			</div>
		</div>
		<logic:greaterThan name="specTypesListLen" value="0">	
		<hr>		
		<table class="table table-bordered table-hover" id="tbSpecTypeMaint" >
			    <thead>
			        <tr>
			        	<th class="text-center">Select</th>
						<th class="text-center">Specification Type</th>
						<th class="text-center">Customer Maintenance</th>
						<th class="text-center">Distributor Maintenance</th>
		    			<th class="text-center">General Arrangement Maintenance</th>	
		    			<th class="text-center">Performance Curve Maintenance</th>
		    			<th class="text-center">Comments</th>
					</tr>
				</thead>
			    <tbody class="text-center">	
			    	<logic:iterate id="SpecTypeMaintFormId" name="SpecTypeMaintForm"
						property="specTypeList" type="com.EMD.LSDB.vo.common.SpecTypeVo">
						<TR>
							<TD CLASS="text-center v-midalign"><html:radio
								value="<%=String.valueOf(SpecTypeMaintFormId.getSpectypeSeqno())%>"
								styleClass="radcheck" property="spectypeSeqno" /></TD>
							<TD CLASS="text-center v-midalign"><html:text
								name="SpecTypeMaintFormId" property="spectypename"
								styleClass="form-control" maxlength="100" /></TD>
							<TD CLASS="text-center v-midalign"><logic:equal
								name="SpecTypeMaintFormId" property="custMaintDisPlayFlag" value="true">
									<html:checkbox name="SpecTypeMaintFormId"
									property="custMaintDisPlayFlag" />
							</logic:equal> <logic:notEqual name="SpecTypeMaintFormId"
								property="custMaintDisPlayFlag" value="true">
								<html:checkbox name="SpecTypeMaintFormId"
									property="custMaintDisPlayFlag" />
							</logic:notEqual></TD>
							
							<TD CLASS="text-center v-midalign"><logic:equal
								name="SpecTypeMaintFormId" property="distMaintDisPlayFlag" value="true">
								<html:checkbox name="SpecTypeMaintFormId"
									property="distMaintDisPlayFlag" />
							</logic:equal> <logic:notEqual name="SpecTypeMaintFormId"
								property="distMaintDisPlayFlag" value="true">
								<html:checkbox name="SpecTypeMaintFormId"
									property="distMaintDisPlayFlag" />
							</logic:notEqual></TD>
							
							<TD CLASS="text-center v-midalign"><logic:equal
								name="SpecTypeMaintFormId" property="genArrMaintDisPlayFlag" value="true">
								<html:checkbox name="SpecTypeMaintFormId"
									property="genArrMaintDisPlayFlag" />
							</logic:equal> <logic:notEqual name="SpecTypeMaintFormId"
								property="genArrMaintDisPlayFlag" value="true">
								<html:checkbox name="SpecTypeMaintFormId"
									property="genArrMaintDisPlayFlag" />
							</logic:notEqual></TD>
							
							<TD CLASS="text-center v-midalign"><logic:equal
								name="SpecTypeMaintFormId" property="perfCurveMaintDisPlayFlag" value="true">
								<html:checkbox name="SpecTypeMaintFormId"
									property="perfCurveMaintDisPlayFlag" />
							</logic:equal> <logic:notEqual name="SpecTypeMaintFormId"
								property="perfCurveMaintDisPlayFlag" value="true">
								<html:checkbox name="SpecTypeMaintFormId"
									property="perfCurveMaintDisPlayFlag" />
							</logic:notEqual></TD>
													
							<TD CLASS="text-center v-midalign"><html:textarea
								name="SpecTypeMaintFormId" styleClass="form-control" property="comments">
								<bean:write name="SpecTypeMaintFormId" property="comments" />
							</html:textarea></TD>
							
						</TR>
					</logic:iterate>
			    </tbody>
		</table>
		
		<div class="row">
				<div class="spacer10"></div>
			</div>
			
			<div class="row">
				<div class="form-group">
			         <div class="col-sm-11 text-center">
			            <button class="btn btn-primary mdfybtn" type='button' id="modifyButton" ONCLICK="javascript:fnModifySpecType()">Modify</button>
			        </div>
		     	</div>
		    </div>
		    
		    <div class="row">
				<div class="spacer30"></div>
			</div>
		</logic:greaterThan>		    
		
		<html:hidden property="hdnSelSpecTypeSeqNo" />
		<html:hidden property="hdnSelSpectypeName" />
		<html:hidden property="hdnSpecDesc" />
		<html:hidden property="hdnSelCustFlag" />
		<html:hidden property="hdnSelDistFlag" />
		<html:hidden property="hdnSelGenArrFlag" />
		<html:hidden property="hdnSelPerfCurveFlag" />
   	</html:form>
	<div class="spacer50"></div>
 </div>