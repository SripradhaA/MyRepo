<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<SCRIPT type="text/JavaScript" SRC="js/OrderSpecificClauseReport.js"></SCRIPT>

<script>
$(document).ready(function() { 
	$("#selectSpectype").select2({theme:'bootstrap'}); 
	$("#selectModel").select2({theme:'bootstrap'}); 
	$("#selectCustomer").select2({theme:'bootstrap'}); 
})
</script>

<div class = "container" width="100">

<html:form action="/OrderSpecificClauseAction" method="post" styleClass="form-inline">

	<div class="spacer10"></div>

	<h4 class ="text-center">
		<bean:message key="Application.Screen.OrderSpecificClauseReport" />
	</h4>
	
	<p class="text-center"><mark><bean:message key="Message.WildcardSearch" /></mark></p>

	<div class="spacer30"></div>
	
	<div class="errorlayerhide" id="errorlayer"></div>
	<logic:present name="OrderSpecificClauseForm" property="orderList">
		<bean:size id="ordListLen" name="OrderSpecificClauseForm" property="orderList" />
	</logic:present>

	<logic:present name="OrderSpecificClauseForm" property="messageID" scope="request">
			<bean:define id="messageID" name="OrderSpecificClauseForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
	</logic:present>

	<logic:match name="OrderSpecificClauseForm" property="method" value="fetchOrders">
		<logic:lessThan name="ordListLen" value="1">
			<script> 
              fnNoRecords();
        	</script>
		</logic:lessThan>
	</logic:match>
	
	<div class="row">
		<div class="col-sm-12 text-center">
			<div class="form-group required-field">
				<label class="control-label">Specification Type</label>
				<html:select
					name="OrderSpecificClauseForm" property="spectypeSeqno" styleId="selectSpectype"
					styleClass="form-control" onchange="javascript:fnLoadModels()">
					<option selected value="-1">---Select---</option>
						<html:optionsCollection name="OrderSpecificClauseForm" property="specTypeList"
						value="spectypeSeqno" label="spectypename" />
				</html:select>
			</div>
			
			<div class="form-group">
				&nbsp;
			</div>
			
			<div class="form-group required-field">
				<label class="control-label">Model</label>
					<html:select name="OrderSpecificClauseForm" styleId="selectModel"
						property="modelSeqNo" styleClass="form-control">
						<option selected value="-1">---Select---</option>
						<logic:present name="OrderSpecificClauseForm" property="modelList">
							<html:optionsCollection name="OrderSpecificClauseForm" property="modelList"
								value="modelSeqNo" label="modelName" />
						</logic:present>
					</html:select>
			</div>
			
			<div class="form-group">
				&nbsp;
			</div>
			
			<div class="form-group">
				<label class="control-label">Customer</label>
					<html:select name="OrderSpecificClauseForm" styleId="selectCustomer"
					 property="customerSeqNO" styleClass="form-control" >
						<option selected value="-1">---Select---</option>
						<logic:present name="OrderSpecificClauseForm" property="custList">
							<html:optionsCollection name="OrderSpecificClauseForm"
								property="custList" value="customerSeqNo" label="customerName" />
						</logic:present>
					</html:select>
			</div>
			
			<div class="form-group">
				&nbsp;
			</div>
			
			<div class="form-group required-field">
				<label class="control-label">Order Number</label>
				<html:text property="orderNo" styleClass="form-control" size="10"
					maxlength="15" onkeypress="return keyHandler()" />
			</div>
		</div>
	</div>

	<div class="spacer30"></div>
	
	<div class="col-sm-12 text-center">
		<div class="form-group">
			<label class="control-label">Show latest published Specs Only</label>
			&nbsp;&nbsp;&nbsp;&nbsp;<html:checkbox property="showLatestFlag"/>
		</div>
	</div>
	
	<div class="spacer50"></div>
	
	<div class="col-sm-12 text-center">
		<div class="form-group">
              <button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnSearchOrders()"><bean:message key="screen.search" /></button>
       </div>
	</div>
	
	<div class="spacer50"></div>

	<logic:greaterThan name="ordListLen" value="0">
	
		<HR>
		
			<div class="row">
				<div class="col-sm-11 text-center">
					<div class="form-group">
						<h5 class ="text-center"><strong>Search Criteria</strong></h5>
			       </div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-sm-12">
					<div class="spacer10"></div>
				</div>
			</div>
				
			<div class="row">
				<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Specification Type</strong></div>
				<div class="col-sm-1 text-center">:</div>
				<div class="col-sm-2 text-left">
					<bean:write name="OrderSpecificClauseForm"
						property="hdnSelSpecType" />
				</div>
			</div>
			
			<div class="row">	
				<div class="col-sm-12">
					<div class="spacer10"></div>
				</div>
			</div>
				
			<div class="row">
				<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Model</strong></div>
				<div class="col-sm-1 text-center">:</div>
				<div class="col-sm-2 text-left">
					<bean:write name="OrderSpecificClauseForm"
						property="hdnSelModel" />
				</div>
			</div>
			
			<div class="row">	
				<div class="col-sm-12">
					<div class="spacer10"></div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Customer</strong></div>
				<div class="col-sm-1 text-center">:</div>
				<div class="col-sm-2 text-left">
					<logic:notEqual name="OrderSpecificClauseForm"
						property="customerSeqNO" value="-1">
						<bean:write name="OrderSpecificClauseForm"
							property="hdnSelectedCustomers" /></logic:notEqual>
				</div>
			</div>
			
			<div class="row">	
				<div class="col-sm-12">
					<div class="spacer10"></div>
				</div>
			</div>
				
			<div class="row">
				<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Order Number</strong></div>
				<div class="col-sm-1 text-center">:</div>
				<div class="col-sm-2 text-left">
					<bean:write name="OrderSpecificClauseForm"
						property="hdnOrderNo" />
				</div>
			</div>
			
			<div class="row">	
				<div class="col-sm-12">
					<div class="spacer10"></div>
				</div>
			</div>
				
			<div class="row">
				<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Show latest published Specs Only</strong></div>
				<div class="col-sm-1 text-center">:</div>
				<div class="col-sm-2 text-left">
					<bean:write name="OrderSpecificClauseForm"
						property="hdnLatestFlag" />
				</div>
			</div>
				
			<div class="row">	
				<div class="col-sm-12">
					<div class="spacer20"></div>
				</div>
			</div>
			
			<div class="row">	
				<div class="col-sm-12">
					<a CLASS=linkstyleItem href="javascript:checkAll()">Check All</a>
						&nbsp;&nbsp; 
					<a CLASS=linkstyleItem href="javascript:clearAll()">Clear All</a>
				</div>
			</div>	

			<TABLE class="table table-bordered" id="tbOrderSpecificClaReport">
				<thead>
					<TR>
						<TH CLASS='text-center' align="center">
							<bean:message key="copySpecSel" />
						</TH>
						<TH CLASS='text-center' align="center">
							<bean:message key="copySpecOrderNo" />
						</TH>
						<TH CLASS='text-center' align="center">
							<bean:message key="copySpecStatus" />
						</TH>
						<TH CLASS='text-center' align="center">
							<bean:message key="copySpecCustomer" />
						</TH>
					</TR>
				</thead>
				<tbody>
					<logic:iterate id="ordListId" name="OrderSpecificClauseForm"
						property="orderList" type="com.EMD.LSDB.vo.common.OrderVO">
						<TR>
							<TD CLASS="text-center v-midalign">
							<input type="checkbox" name="orderKey" class="ordNoChkbox" 
							value="<bean:write name="ordListId" property="orderKey"/>" />
							</TD>
							
							<TD CLASS="text-center v-midalign">
								<bean:write name="ordListId" property="orderNo" />
							</TD>					
		
							<TD CLASS="text-center v-midalign">
								<bean:write name="ordListId" property="statusDesc" />
							</TD>
							<logic:iterate id="cusListId" name="ordListId"
										property="customerVo">
							<TD CLASS="text-center v-midalign">
								<bean:write name="cusListId" property="customerName" />
							</TD>
							</logic:iterate>
						</TR>
					</logic:iterate>
				</tbody>
			</TABLE>
		
		<div class="row">	
			<div class="col-sm-12">
				<div class="spacer20"></div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-12 text-center">
	              <button class="btn btn-primary" type='button' id="ViewOrderSpecificClauses" ONCLICK="javascript:fnViewOrderSpecificClauses()">View Order Specific Clauses</button>
	              <button class="btn btn-primary" type='button' id="ExportToExcel" ONCLICK="javascript:fnViewOrderSpecificClausesExcel()">Export To Excel</button>
			</div>
		</div>
		
		<div class="spacer20"></div>
		
	</logic:greaterThan>
    <!-- Added hidden parameter for Customer drop down by cm68219 -->
    <html:hidden property="hdnPreSelectedCustomer" />
    <html:hidden property="hdnSelectedCustomers" />
	<html:hidden property="hdnSelSpecType" />
	<html:hidden property="hdnSelModel" />
	<html:hidden property="hdPreSelectedModel" />
	<input type="hidden" name="flag" value="Y">
	<html:hidden property="distributorFlag"/>
	<html:hidden property="hnOrderKey" />
	<html:hidden property="hdnOrderNo" />
	<html:hidden property="hdnLatestFlag" />
</html:form>
</div>
