<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<SCRIPT type="text/JavaScript" SRC="js/CustOptCat.js"></SCRIPT>

<script>
    $(document).ready(function() { 
    	$("#selectedCust").select2({theme:'bootstrap'}); 
    	$("#selectedModel").select2({theme:'bootstrap'}); 
    })
</script>

<div class = "container" width="100">

<html:form action="/CustOptCatAction.do?method=initLoad" method="post" styleClass="form-inline">

	<div class="spacer10"></div>

	<h4 class ="text-center">
		<bean:message key="Application.Screen.CustomerCatalog" />
	</h4>

	<div class="spacer30"></div>

	<div class="errorlayerhide" id="errorlayer">
	</div>

	<logic:present name="CustOptCatForm" property="messageID"
		scope="request">

			<bean:define id="messageID" name="CustOptCatForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>

	</logic:present>
	
	<div class="row">
			<div class="col-sm-12 text-center">
				<div class="form-group required-field">
					<label class="control-label">Specification Type</label>
					
					<html:select name="CustOptCatForm"
						property="specTypeNo" styleClass="form-control"
						styleId="selectedCust" onchange="javascript:fnLoadModels()">
						<html:option value="-1">--Select--</html:option>
						<html:optionsCollection name="CustOptCatForm"
							property="specTypeList" value="spectypeSeqno" label="spectypename" />
					</html:select>	
				</div>
				
				<div class="form-group required-field">
					&nbsp;
				</div>
				
				<div class="form-group required-field">
					<label class="control-label"><bean:message key="master.model" /></label>
						<html:select name="CustOptCatForm"
							property="modelSeqNo" styleClass="form-control"
							styleId="selectedModel">
							<html:option value="-1">--Select--</html:option>
							<logic:present name="CustOptCatForm" property="modelList">
								<html:optionsCollection name="CustOptCatForm" property="modelList"
									label="modelName" value="modelSeqNo" />
							</logic:present>
						</html:select>
						<span class="alertmsg"></span>
				</div>
				
				<div class="form-group required-field">
					&nbsp;
				</div>
				
				<div class="form-group">
		              <button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:showCustomerOptionCatalog(0)">View Customer Option Catalog Report</button>
		       </div>
			</div>
		</div>
		
		<div class="spacer30"></div>
		
		<div class="row">
			<div class="col-sm-12 text-center">
				<div class="form-group">
		              <button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnEditCompGrpInCOC()">Display/Edit COC settings per component group</button>
		       </div>
			</div>
		</div>
		
		<div class="spacer30"></div>
		
		<logic:present name="CustOptCatForm" property="compGroupList">
	
			<TABLE class="table table-bordered table-hover" id="tbCustOptCatlog">
			
				<thead>
					<TR>
						<TH WIDTH="5%" CLASS='text-center'>Component Group</TH>
						<TH WIDTH="5%" CLASS='text-center'>Display In COC</TH>
					</TR>
				</thead>
		
				<logic:iterate name="CustOptCatForm" id="compGrpList" property="compGroupList" scope="request"
				type="com.EMD.LSDB.vo.common.CompGroupVO">
				
					<TR>
					
						<TD CLASS="text-center v-midalign">
						<bean:write name="compGrpList" property="componentGroupName" />
						</TD>
						
						<TD CLASS="text-center v-midalign">
							<logic:equal name="compGrpList" property="dispInCOC" value="Y">
								<input type="checkbox" class="dispInCOCCheckBox" name="dispInCOC" value='<bean:write name="compGrpList" property="dispInCOC" />' checked/>
								<input type="hidden" name="hdnCompGroupSeqNo" value='<bean:write name="compGrpList" property="componentGroupSeqNo" />'/>
								<input type="hidden" name="hdnDispInCOC" value='<bean:write name="compGrpList" property="dispInCOC" />'/>
								
							</logic:equal>
							<logic:notEqual name="compGrpList" property="dispInCOC" value="Y">
								<input type="checkbox" class="dispInCOCCheckBox" name="dispInCOC" value='<bean:write name="compGrpList" property="dispInCOC" />'/>
								<input type="hidden" name="hdnCompGroupSeqNo" value='<bean:write name="compGrpList" property="componentGroupSeqNo" />'/>
								<input type="hidden" name="hdnDispInCOC" value='<bean:write name="compGrpList" property="dispInCOC" />'/>
								
							</logic:notEqual>
						</TD>
						
					</TR>
					
				</logic:iterate>
				
			</table>
			
			<div class="spacer30"></div>
		
			<div class="row">
				<div class="col-sm-12 text-center">
					<div class="form-group">
			              <button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:showCustomerOptionCatalog(1)">Save COC settings and view the COC report</button>
			       </div>
				</div>
			</div>
			
			<div class="spacer50"></div>
			
	</logic:present>
	
	
	<html:hidden property="modelName" />
	<html:hidden property="hdnSelSpecType" />
	<%--Added for CR_118--%>
	<input type="hidden" name="changeInDispInCOCSection" />
	<%--Added for CR_118 Ends--%>
		
</html:form>

</div>
