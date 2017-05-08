<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT type="text/javascript" SRC="js/SectionMaint.js"></SCRIPT>
<script>
       $(document).ready(function() { 
	       	$("#sltSpecType").select2({theme:'bootstrap'});
	       	$("#sltModel").select2({theme:'bootstrap'});
       })
</script>
<div class="container" width="100%">
   <html:form  action="/SectionMaintenance" method="post">
 		<h4 class="text-center"><bean:message key="Application.Screen.SectionMaintenance" /></h4>
		<div class="spacer10"></div>
		<div class="errorlayerhide" id="errorlayer">
		</div>
		<logic:present name="SectionMaintenanceForm" property="messageID" scope="request">
			<bean:define id="messageID" name="SectionMaintenanceForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
		</logic:present>
		
		<logic:present name="SectionMaintenanceForm" property="sectionList">
			<bean:size id="secsize" name="SectionMaintenanceForm" property="sectionList" />
		</logic:present>
		
		<logic:match name="SectionMaintenanceForm" property="method" value="fetchSections">
			<logic:lessThan name="secsize" value="1">
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
					<html:select styleClass="form-control" name="SectionMaintenanceForm" styleId="sltSpecType"
							property="specTypeNo" onchange="javascript:fnLoadModels()">
							<option selected value="-1">---Select---</option>
							<html:optionsCollection name="SectionMaintenanceForm"
								property="specTypeList" value="spectypeSeqno" label="spectypename" />
					</html:select>
				</div>
			</div>
			
			<div class="form-group required-field">
				<label class="col-sm-5 control-label">Model</label>
				<div class="col-sm-3 form-inline">
					<html:select styleClass="form-control" name="SectionMaintenanceForm" property="modelSeqNo" styleId="sltModel">
							<option selected value="-1">---Select---</option>
							<logic:present name="SectionMaintenanceForm" property="modelList">
								<html:optionsCollection name="SectionMaintenanceForm" property="modelList"
									value="modelSeqNo" label="modelName" />
							</logic:present>
					</html:select>
					<button class="btn btn-primary addbtn" type='button' id="searchButton" ONCLICK="javascript:fnSearch()"><bean:message key="screen.search" /></button>
				</div>
			</div>
				
	     	<div class="form-group required-field">			
				<label class="col-sm-5 control-label">Section</label>
				<div class="col-sm-4">					
					<html:text styleClass="form-control" property="secName" styleId="secName"/>
				</div>
			</div>
			
			<div class="form-group">			
				<label class="col-sm-5 control-label">Comments</label>
				<div class="col-sm-4">					
					<html:textarea styleClass="form-control" property="secComments" styleId="secComments" rows="2" cols="48" />
					<span class="alertmsg"></span>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-4">
					<button class="btn btn-primary" type='button' id="addButton" ONCLICK="javascript:fnAdd()">	<bean:message key="screen.add" /></button>
				</div>
			</div>
			
		</div>
		
		<logic:greaterThan name="secsize" value="0">		
			
				<hr>		
				<div class="row">
				<div class="col-sm-11 text-center"><h5 class="text-center"><strong>Search critera</strong></h5></div>
				</div>
				<div class="spacer10"></div>
				
				
				<div class="row">
					<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Specification Type</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="SectionMaintenanceForm" property="specTypeNo" value="-1">
							<bean:write name="SectionMaintenanceForm" property="hdSelectedSpecType"/>
						</logic:notEqual>
						<logic:equal name="SectionMaintenanceForm" property="specTypeNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				<div class="spacer10"></div>
				<div class="row">
					<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Model</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="SectionMaintenanceForm" property="modelSeqNo" value="-1">
							<bean:write name="SectionMaintenanceForm" property="hdSelectedModel"/>
						</logic:notEqual>
						<logic:equal name="SectionMaintenanceForm" property="modelSeqNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				<div class="spacer10"></div>	
				
				<table class="table table-bordered table-hover">
					    <thead>
					        <tr>
								<TH>Select</TH>
								<TH>SectionNo</TH>
								<TH>Section</TH>
								<TH>Comments</TH>
							</tr>	
						</thead>
					    <tbody class="text-center">	
					    	<logic:iterate id="Sectionvo" name="SectionMaintenanceForm"
						property="sectionList" type="com.EMD.LSDB.vo.common.SectionVO"
						scope="request">

						<TR>


							<TD CLASS="v-midalign"><html:radio
								 property="sectionSeqNo"
								value='<%= String.valueOf(Sectionvo.getSectionSeqNo())%>' /></TD>
							<TD CLASS="v-midalign"><bean:write name="Sectionvo"
								property="sectionCode" /> <html:hidden name="Sectionvo"
								property="sectionCode" /></TD>
							<TD CLASS="v-midalign"><html:text
								styleClass="form-control" name="Sectionvo"
								property="sectionName" size="36" maxlength="50" /></TD>
							<TD CLASS="v-midalign"><html:textarea styleClass="form-control" 
								name="Sectionvo" property="sectionComments" rows="2" cols="48" />
							</TD>


						</TR>

					</logic:iterate>
					    </tbody>
				</table>
				
				<div class="spacer10"></div>
				
				<div class="form-group">
				  <div class="col-sm-12 text-center">
				       <button class="btn btn-primary mdfybtn" type='button' id="modifyButton" ONCLICK="javascript:fnModify()"><bean:message key="screen.modify" /></button>
				        <button class="btn btn-primary deleteButton" type='button' id="deleteButton" ONCLICK="javascript:fnDelete()"><bean:message key="screen.delete" /></button>
					</div>   
			   	</div>
				<div class="spacer50"></div>		    
		</logic:greaterThan>	
			<!-- Hidden variables hdSelectedSpecType and Search Criteria Specificationtype 
					is also added in the table grid.
					are added based on LSDB_CR-46 on 28-Aug-08 by ps57222
			-->

	<html:hidden property="hdsection" />
	<html:hidden property="hdSecComments" />
	<html:hidden property="hdSelectedModel" />
	<html:hidden property="hdPreSelectedModel" />
	<html:hidden property="hdSelectedSpecType" />	
	</html:form>
</div>		