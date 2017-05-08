<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT type="text/javascript" SRC="js/SubSection.js"></SCRIPT>
<script>
       $(document).ready(function() { 
       	$("#sltSpecType").select2({theme:'bootstrap'});
       	$("#sltModel").select2({theme:'bootstrap'});
       	$("#sltSection").select2({theme:'bootstrap'});
       })
</script>
<div class="container" width="100%">
   <html:form  action="/SubSectionAction" method="post">
 		<h4 class="text-center"><bean:message key="Application.Screen.SubSectionMaintenance" /></h4>
		<div class="spacer10"></div>
		<div class="errorlayerhide" id="errorlayer">
		</div>
		<logic:present name="SubSecMaintForm" property="messageID" scope="request">
			<bean:define id="messageID" name="SubSecMaintForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
		</logic:present>
		
		<logic:present name="SubSecMaintForm" property="subsections">
			<bean:size id="subSecListLen" name="SubSecMaintForm" property="subsections" />
		</logic:present>
		
		<logic:match name="SubSecMaintForm" property="method" value="SubSections">
			<logic:lessThan name="subSecListLen" value="1">
				<script> 
					fnNoRecords();
				</script>
			</logic:lessThan>
		</logic:match>
		
		 <div class="spacer10"></div>
		
		 <div class="form-horizontal">
			<div class="form-group required-field">
					<label class="col-sm-5 control-label">Specification Type</label>
					<div class="col-sm-3">
					<html:select styleClass="form-control" name="SubSecMaintForm" styleId="sltSpecType"
							property="specTypeNo" onchange="javascript:fnLoadModels()">
							<option selected value="-1">---Select---</option>
							<html:optionsCollection name="SubSecMaintForm"
								property="specTypeList" value="spectypeSeqno" label="spectypename" />
					</html:select>
					</div>
			</div>
			
			<div class="form-group required-field">			
					<label class="col-sm-5 control-label">Model</label>
					<div class="col-sm-3">
						<html:select styleClass="form-control" name="SubSecMaintForm" styleId="sltModel"
								property="modelseqno" onchange="javascript:fnLoadSections()">
								<option selected value="-1">---Select---</option>
								<logic:present name="SubSecMaintForm" property="listModels">
									<html:optionsCollection name="SubSecMaintForm"
										property="listModels" value="modelSeqNo" label="modelName" />
								</logic:present>
						</html:select>
					</div>
			</div>
			
			<div class="form-group required-field">
					<label class="col-sm-5 control-label">Section</label>
					
					<div class="col-sm-7 form-inline">
						
						<html:select styleClass="form-control" name="SubSecMaintForm" styleId="sltSection"
								property="sectionseqno">
								<option selected value="-1">---Select---</option>
								<logic:present name="SubSecMaintForm" property="listSections">
									<html:optionsCollection name="SubSecMaintForm"
										property="listSections" value="sectionSeqNo" label="sectionDisplay" />
								</logic:present>
						</html:select>
					
						
							<button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnSearchSubSections()"><bean:message key="screen.search" /></button>
					</div>	
					
			</div>
			
			<div class="form-group required-field">			
				<label class="col-sm-5 control-label">SubSection</label>
				<div class="col-sm-4">					
					<html:text styleClass="form-control" property="subsecname" styleId="subsecname"/>
				</div>
			</div>
			
			<div class="form-group">			
				<label class="col-sm-5 control-label">Comments</label>
				<div class="col-sm-4">					
					<html:textarea styleClass="form-control" property="comments" styleId="comments" rows="2" cols="48" />
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-4">
					<button class="btn btn-primary" type='button' id="addButton" ONCLICK="javascript:fnInsertSubSections()"><bean:message key="screen.add" /></button>
				</div>
			</div>
		</div>
		
			<logic:greaterThan name="subSecListLen" value="0">		
			
				<hr>		
				<div class="row">
				<div class="col-sm-11 text-center"><h5 class="text-center"><strong>Search critera</strong></h5></div>
				</div>
				<div class="spacer10"></div>
				
				
				<div class="row">
					<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Specification Type</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="SubSecMaintForm" property="specTypeNo" value="-1">
							<bean:write name="SubSecMaintForm" property="hdSelectedSpecType"/>
						</logic:notEqual>
						<logic:equal name="SubSecMaintForm" property="specTypeNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				<div class="spacer10"></div>
				<div class="row">
					<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Model</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="SubSecMaintForm" property="modelseqno" value="-1">
							<bean:write name="SubSecMaintForm" property="hdnModel"/>
						</logic:notEqual>
						<logic:equal name="SubSecMaintForm" property="modelseqno" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				<div class="spacer10"></div>
				<div class="row">
					<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Section</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="SubSecMaintForm" property="sectionseqno" value="-1">
							<bean:write name="SubSecMaintForm" property="hdnSection"/>
						</logic:notEqual>
						<logic:equal name="SubSecMaintForm" property="sectionseqno" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				
				<div class="spacer10"></div>	
				
				<table class="table table-bordered table-hover">
					    <thead>
					        <tr>
								<TH>Select</TH>
								<TH>SubSection No</TH>
								<TH>SubSection</TH>
								<TH>Comments</TH>
							</tr>	
						</thead>
					    <tbody class="text-center">	
					    	<logic:iterate id="subsection" name="SubSecMaintForm"
								property="subsections" scope="request"
								type="com.EMD.LSDB.vo.common.SubSectionVO">
				
								<TR>
									<TD CLASS="v-midalign"><html:radio
										value="<%=String.valueOf(subsection.getSubSecSeqNo())%>"
										styleClass="radcheck" property="subsecseqno" /></TD>
				
									<TD CLASS="v-midalign"><bean:write name="subsection"
										property="subSecCode" /></TD>
				
									<TD CLASS="v-midalign"><html:text name="subsection"
										property="subSecName" styleClass="form-control" size="40"
										maxlength="100" /></TD>
									<TD CLASS="v-midalign"><html:textarea name="subsection" styleClass="form-control"
										property="subSecDesc" rows="2" cols="40" ></html:textarea></TD>
								</TR>
				
							</logic:iterate>
					    </tbody>
				</table>	
				<div class="spacer10"></div>
				
				<div class="form-group">
				  <div class="col-sm-12 text-center">
				       <button class="btn btn-primary mdfybtn" type='button' id="modifyButton" ONCLICK="javascript:fnModifySubSections()"><bean:message key="screen.modify" /></button>
				        <button class="btn btn-primary deleteButton" type='button' id="deleteButton" ONCLICK="javascript:fnDeleteSubSections()"><bean:message key="screen.delete" /></button>
					</div>   
			   	</div>
				<div class="spacer50"></div>
				    
			</logic:greaterThan>	
			
			<html:hidden property="hdnsearch" /> 
			<!-- Hidden Fields to display Selected Model and Section Down - Starts Here -->
			<html:hidden property="hdnModel" /> 
			<html:hidden property="hdnSection" /> 
			<html:hidden property="hdnSelectedSec" />
			<!-- Hidden Fields to display Selected Model and Section Down - Ends Here  -->
			<!-- Logic Tags to Display Records After Search,Add,Modify - Ends Here -->
			<!-- Hidden Fields to hold the modified Section Name and Comments - Starts Here -->

			<html:hidden property="hdnsectionName" />
			<html:hidden property="hdnSectionComments" />
			<html:hidden property="hdSelectedSpecType" />
		
			<!-- Hidden Fields to hold the modified Section Name and Comments - Ends Here -->
			
</html:form>
</div>