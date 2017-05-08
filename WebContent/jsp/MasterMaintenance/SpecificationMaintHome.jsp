<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT LANGUAGE="JavaScript" SRC="js/Model_Specification.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/Others/jquery.tablednd.js"></SCRIPT>
<script>
 $(document).ready(function() { 
       	$("#sltSpecType").select2({theme:'bootstrap'});
       	$("#sltModel").select2({theme:'bootstrap'});
       	});
</script>       	
<div class="container" WIDTH="100%" >
<html:form action="/ModelSpecificationAction" method="post">
	<h4 class="text-center"><bean:message key="Application.Screen.SpecificationMaintenanceHome" /></h4>
	
	<logic:present name="ModelSpecificationForm" property="modSpecList">
		<bean:size id="modspeclen" name="ModelSpecificationForm"
			property="modSpecList" />
	</logic:present>
	
	<br>
	<div class="errorlayerhide" id="errorlayer">
	</div>


	<logic:present name="ModelSpecificationForm" property="messageID"
		scope="request">

		<bean:define id="messageID" name="ModelSpecificationForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
	</logic:present>

	<br>
	<logic:match name="ModelSpecificationForm" property="method"
		value="fetchSpecificationItems">
		<logic:lessThan name="modspeclen" value="1">
			<script> 
              fnNoRecords();
       		</script>
		</logic:lessThan>
	</logic:match>
	
		<div class="spacer30"></div>

		<div class="row form-inline">
			<div class="col-sm-12 text-center">
				<div class="form-group required-field">
					<label class="control-label">Specification Type</label>
					<html:select styleClass="form-control" styleId="sltSpecType"
							name="ModelSpecificationForm" property="specTypeNo"
							onchange="javascript:fnLoadModels()">
							<option selected value="-1">---Select---</option>
							<html:optionsCollection name="ModelSpecificationForm"
								property="specTypeList" value="spectypeSeqno" label="spectypename" />
					</html:select>
				</div>
				
				<div class="form-group required-field">
					<label class="control-label">Model</label>
						<html:select styleClass="form-control" styleId="sltModel"
							name="ModelSpecificationForm" property="modelSeqNo">
							<option selected value="-1">---Select---</option>
							<logic:present name="ModelSpecificationForm" property="modelList">
								<html:optionsCollection name="ModelSpecificationForm"
									property="modelList" value="modelSeqNo" label="modelName" />
							</logic:present>
						</html:select>
				</div>
				
				<div class="form-group">
					<button class="btn btn-primary" type='button' ONCLICK="javascript:searchSpecificationItems();">Search</button>
				</div>
				<div class="form-group">
					<button class="btn btn-primary" type='button' ONCLICK="javascript:initLoadSpecification();">Add Description</button>
				</div>
			</div>		
		</div>
		
		<logic:greaterThan name="modspeclen" value="0">		
		<hr>
			<div class="row">
				<div class="col-sm-11 text-center"><h5 class="text-center"><strong>Search critera</strong></h5></div>
			</div>
			<div class="row">
				<div class="spacer10"></div>
			</div>
			<div class="row">
				<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Specification Type</strong></div>
				<div class="col-sm-1 text-center">:</div>
				<div class="col-sm-2 text-left">
					<logic:notEqual name="ModelSpecificationForm" property="specTypeNo" value="-1">
						<bean:write name="ModelSpecificationForm" property="hdnSelSpecType"/>
					</logic:notEqual>
					<logic:equal name="ModelSpecificationForm" property="specTypeNo" value="-1">
						&nbsp;
					</logic:equal>
				</div>
			</div>
			<div class="row">
				<div class="spacer10"></div>
			</div>
			<div class="row">	  
				<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Model</strong></div>
				<div class="col-sm-1 text-center">:</div>
				<div class="col-sm-2 text-left">
					<logic:notEqual name="ModelSpecificationForm" property="modelSeqNo" value="-1">
						<bean:write name="ModelSpecificationForm" property="hdnModelName" />
					</logic:notEqual>
					<logic:equal name="ModelSpecificationForm" property="modelSeqNo" value="-1">
						&nbsp;
					</logic:equal>
				</div>
			</div>
			<div class="spacer10"></div>
			
			<div class="highlightDark text-center">
				<strong>Specifications</strong>
			</div>
			<table class="table table-bordered">
				<thead>
			    	<tr>
			            <th WIDTH="5%">Select</th>
			            <th WIDTH="75%">Description</th>
			            <th WIDTH="20%">Value</th>
			        </tr>
			    </thead>
			</table>

			
					<logic:iterate id="objspec" name="ModelSpecificationForm"
						property="modSpecList" type="com.EMD.LSDB.vo.common.SpecificationVO" scope="request" >
						<table class="table table-bordered">
						<tr>
				            <td colspan="3" class="bg-info">
							<div class="row">
								<div class="col-sm-offset-2 col-sm-6 text-center push-text-down">
									<strong>
										<bean:write name="objspec" property="specName" />
									</strong>
								</div>
								<div class="col-sm-4 text-right">
									<button class="btn btn-primary btn-sm" type='button' ONCLICK="javascript:initLoadSpecificationItem('<%=objspec.getSpecName()%>','<%=objspec.getSpecSeqNo()%>')" >Add Line</button>
									<button class="btn btn-primary btn-sm" type='button' ONCLICK="javascript:displayModifiedSpecification('<%=objspec.getSpecName()%>','<%=objspec.getSpecSeqNo()%>')">Modify Heading</button>
									<button class="btn btn-primary btn-sm" type='button' ONCLICK="javascript:deleteSpecification('<%=objspec.getSpecSeqNo()%>')">Delete Entire Section</button>
								</div>
							</div>
							</td>			    	
				    	</tr>
							<logic:iterate id="objspecitem" name="objspec" property="specItem"
								type="com.EMD.LSDB.vo.common.SpecificationItemVO" scope="page">
								<TR>
									<TD CLASS="v-midalign text-center" WIDTH="5%"><html:radio
										value="<%=String.valueOf(objspecitem.getModSpecItemSeqNo())%>"
										property="modSpecItemSeqNo" /></TD>
									<TD CLASS="v-midalign" WIDTH="75%"><html:text name="objspecitem"
										property="modSpecItemDesc" styleClass="form-control" size="120"
										maxlength="2000" /></TD>
									<TD CLASS="v-midalign" WIDTH="20%"><html:text name="objspecitem"
										property="modSpecItemValue" styleClass="form-control" size="25"
										maxlength="100" /></TD>
								</TR>
							</logic:iterate>
						</table>
						 <div class="spacer10"></div>
				</logic:iterate>
		 <div class="spacer10"></div>
				<div class="form-group">
				  <div class="col-sm-12 text-center">
				       <button class="btn btn-primary mdfybtn" type='button' id="saveButton" ONCLICK="javascript:modifySpecItem()">Save/modify</button>
   				       <button class="btn btn-primary mdfybtn" type='button' id="deleteButton" ONCLICK="javascript:deleteSpecificationItem()">Delete</button>
   				       <button class="btn btn-primary mdfybtn" type='button' id="searchButton" ONCLICK="javascript:previewSpecification()">Preview Specifications PDF</button>
				   </div>   
			   	</div>
	</logic:greaterThan>
		
	<html:hidden property="hdnModelName" />
	<html:hidden property="hdnSpecName" />
	<html:hidden property="hdnSpecSeqNo" />
	<html:hidden property="hdnSpecItemDesc" />
	<html:hidden property="hdnSpecItemValue" />
	<html:hidden property="hdnPrevSelModel" />
	<html:hidden property="hdnSelSpecType" />
</html:form>
</div>	
<div class="row">
	<div class="spacer50"></div>
</div>