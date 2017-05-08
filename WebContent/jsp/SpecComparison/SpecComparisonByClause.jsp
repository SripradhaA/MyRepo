<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<SCRIPT type="text/javascript" SRC="js/ModifySpec.js"></SCRIPT>
<SCRIPT type="text/javascript" SRC="js/SpecComparison.js"></SCRIPT>
<script>
       $(document).ready(function() { 
       	$("#sltSpecType").select2({theme:'bootstrap'}); 
       	$("#sltCustomer").select2({theme:'bootstrap'});
	    $('#tbOnDemandSpecSupp').DataTable({
	    	 paging:   false,
	         ordering: true,
	         info:     false,
	         order: [[ 5, 'asc' ]],
	         columnDefs: [
                       { "orderable": false, "targets": [0,7] },
                       { "searchable": false, "targets": [0,7] },
                       { "type": "natural", "targets": [1,4] }
                 ]
	    }); 
       })
</script>

<div class = "container-fluid">

<html:form action="/compareSpecAction" method="post" styleClass="form-inline">
	
		<div class="spacer10"></div>
		
		<h4 class ="text-center">Clause Comparison</h4>
		
		<div class="spacer10"></div>

		<p class="text-center"><mark><bean:message key="Message.WildcardSearch" /></mark></p>

		<p class="text-center"><mark>Press SHIFT/CTRL for selecting multiple Models.</mark></p>
		
		<p class="text-center"><mark>NOTE: Only one order can be selected for "View Selected Order vs Master Model Delta"</mark></p>
		
		<div class="errorlayerhide" id="errorlayer">
		</div>
		
		<div class="spacer10"></div>
		
		<logic:present name="ClauseCompareForm" property="orderList">
			<bean:size id="ordListLen" name="ClauseCompareForm"
				property="orderList" />
		</logic:present>

		<logic:present name="ClauseCompareForm" property="messageID"
			scope="request">
			 <%--Added for CR-121 for server side error message tooltip --%>	
		  <bean:define id="messageID" name="ClauseCompareForm" property="messageID"/>
		  <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
	      <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
		</logic:present>

		<logic:match name="ClauseCompareForm" property="method"
			value="fetchOrders">
			<logic:lessThan name="ordListLen" value="1">
				<script>
				//Updated for CR-121 server side error message
				fnNoRecords();
			</script>
			</logic:lessThan>
		</logic:match>
		
		<div class="row">
			<div class="col-sm-12 text-center">
				<div class="form-group required-field">
					<label class="control-label">Specification Type</label>
					<html:select
						name="ClauseCompareForm" property="spectypeSeqno"
						styleClass="form-control" styleId="sltSpecType"
						onchange="javascript:fnLoadModelsForComparison()">
						<option selected value="-1">---Select---</option>
						<html:optionsCollection name="ClauseCompareForm"
							property="specTypeList" value="spectypeSeqno" label="spectypename" />
					</html:select>
				</div>
				
				<div class="form-group required-field">
					<label class=" control-label">Model</label>
					<html:select 
						name="ClauseCompareForm" property="modelSeqNos"
						styleClass="form-control" styleId="sltModel" multiple="true" size="6">
						<logic:present name="ClauseCompareForm" property="modelList">
							<html:optionsCollection name="ClauseCompareForm"
								property="modelList" value="modelSeqNo" label="modelName" />
						</logic:present>
					</html:select>
				</div>
				
				<div class="form-group required-field">
					<label class="control-label">Order Number</label>
						<html:text property="orderNo" styleClass="form-control" styleId="orderNo" size="10" maxlength="15"
							onkeypress="return keyHandle()" />
				</div>
			</div>
		</div>
		
		<div class="spacer30"></div>
		
		<div class="row">
			<div class="col-sm-12 text-center">
	              <div class="form-group">
					<label class="control-label">Show latest published Specs Only</label>
					<html:checkbox property="clauseShowLatFlag" styleClass="form-control"/>
				</div>
	        </div>
	   	</div>
	   	
	   	<div class="spacer20"></div>
		
		<div class="row">
			<div class="col-sm-12 text-center">
              	<button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnSearchOrdersForClauseComparison()"><bean:message key="screen.search" /></button>
	        </div>
	   	</div>

	<logic:greaterThan name="ordListLen" value="0">
		<HR>
		
		<div class="row">
			<div class="col-sm-11 text-center"><h5 class="text-center"><strong>Search Criteria</strong></h5></div>
		</div>
		<div class="spacer10"></div>
		<div class="row">
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Specification Type</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<bean:write name="ClauseCompareForm" property="hdnSelSpecTypeName" />
			</div>
		</div>
		<div class="spacer10"></div>
		<div class="row">	  
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Model(s)</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-4 text-left">
				<bean:write name="ClauseCompareForm" property="hdnSelectedMdlNames" />
			</div>
		</div>
		<div class="spacer10"></div>
		<div class="row">
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Order Number</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<bean:write name="ClauseCompareForm" property="hdnorderNo" />
			</div>
		</div>
		<div class="spacer10"></div>
		
		<div class="row">
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Show latest published Specs Only</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<bean:write name="ClauseCompareForm" property="clauseShowLatONOFF" />
			</div>
		</div>
				
		<div class="spacer20"></div>
		
		<TABLE class="table table-bordered table-hover" id="tbOnDemandSpecSupp">
			<thead>
				<TR>
					<TH WIDTH="5%">Select</TH>
					<TH WIDTH="10%">Order Number</TH>
					<TH WIDTH="10%">Model</TH>
					<TH WIDTH="10%">Custom Model Name</TH>
					<TH WIDTH="15%">Spec Status</TH>
					<TH WIDTH="10%">Customer Name</TH>
					<TH WIDTH="15%">Domestic/Export</TH>
					<TH WIDTH="15%">Section</TH>
				</TR>
			</thead>
			
			<tbody>
				<logic:iterate id="ordListId" name="ClauseCompareForm"
					property="orderList" type="com.EMD.LSDB.vo.common.OrderVO">
					<TR>
						<TD CLASS='text-center v-midalign' width="5%">
							<input class="ordNoChkbox" type="checkbox"
							name="orderKeyCheckBoxId" 
							value="<bean:write name="ordListId" property="orderKey"/>" /><%-- Modified for CR_131 <bean:write name="ordListId" property="orderNo"/>" id="orderKeyCheckBoxId"--%>
						</TD>
						<TD CLASS='text-center v-midalign' width="15%"><bean:write
							name="ordListId" property="orderNo" /></TD>
						<TD CLASS='text-center v-midalign' width="15%"><bean:write
							name="ordListId" property="modelName" /></TD>
						<TD CLASS='text-center v-midalign' width="15%"><bean:write
							name="ordListId" property="custMdlName" /></TD>
						<logic:equal name="ordListId" property="specStatusCode" value="2">
							<logic:match name="ordListId" property="specTypeName" value="Proposal">
								<TD CLASS='text-center v-midalign' width="13%" data-sort="<bean:write name='ordListId' property='specStatusCode'/>-2-<bean:write name='ordListId' property='specRevCode'/>-<bean:write name='ordListId' property='specSubRevCode'/>">
							</logic:match>
							<logic:notMatch name="ordListId" property="specTypeName" value="Proposal">
								<TD CLASS='text-center v-midalign' width="13%" data-sort="<bean:write name='ordListId' property='specStatusCode'/>-1-<bean:write name='ordListId' property='specRevCode'/>-<bean:write name='ordListId' property='specSubRevCode'/>">
							</logic:notMatch>
						</logic:equal>
						<logic:notEqual name="ordListId" property="specStatusCode" value="2">
							<logic:equal name="ordListId" property="specStatusCode" value="3">
								<logic:match name="ordListId" property="specTypeName" value="Baseline">
									<TD CLASS='text-center v-midalign' width="13%" data-sort="<bean:write name='ordListId' property='specStatusCode'/>-2-<bean:write name='ordListId' property='specRevCode'/>-<bean:write name='ordListId' property='specSubRevCode'/>">
								</logic:match>
								<logic:notMatch name="ordListId" property="specTypeName" value="Baseline">
									<TD CLASS='text-center v-midalign' width="13%" data-sort="<bean:write name='ordListId' property='specStatusCode'/>-1-<bean:write name='ordListId' property='specRevCode'/>-<bean:write name='ordListId' property='specSubRevCode'/>">
								</logic:notMatch>
							</logic:equal>
							<logic:notEqual name="ordListId" property="specStatusCode" value="3">
								<TD CLASS='text-center v-midalign' width="13%" data-sort="<bean:write name='ordListId' property='specStatusCode'/>-<bean:write name='ordListId' property='specRevCode'/>-<bean:write name='ordListId' property='specSubRevCode'/>">
							</logic:notEqual>
						</logic:notEqual><bean:write
							name="ordListId" property="specTypeName" /></TD>
						<logic:iterate id="cusListId" name="ordListId"
							property="customerVo">
							<TD CLASS='text-center v-midalign' width="15%"><bean:write
								name="cusListId" property="customerName" /></TD>
							<TD CLASS='text-center v-midalign' width="10%"><bean:write
								name="cusListId" property="customerType" /></TD>
						</logic:iterate>
						<TD CLASS='text-center v-midalign' width="28%">
							<html:select name="ClauseCompareForm" property="modelSeqNo"
								styleClass="form-control" styleId="sltSection" style="min-width: 100%;">
								<option value="-2">All Sections</option>
								<logic:iterate id="sectionType" name="ordListId"
									property="sectionName">
									<option
										value="<bean:write name="sectionType" property="sectionSeqNo"/>"
										title="<bean:write name="sectionType" property="sectionName" />">
										<bean:write name="sectionType" property="sectionName" />
									</option>
								</logic:iterate>
							</html:select>
						</TD>
					</TR>
				</logic:iterate>
			</tbody>
		</TABLE>
		
		<div class="spacer10"></div>
		
		<div class="row">
			<div class="col-sm-12 text-center">
				<button class="btn btn-primary" type='button' id="btnCompareClause" ONCLICK="javascript:fnCompareClauseComparison()">Compare</button>
				<button class="btn btn-primary" type='button' id="btnExportToExcel" ONCLICK="javascript:fnClauseComponentComparisonForExcel()">Export to Excel</button>
				<!--Added for Cr-135 -->
				<button class="btn btn-primary" type='button' id="btnOrderVsMOdel" ONCLICK="javascript:fnOrderVsModelDelta()">View Selected Order vs Master Model Delta</button>
			</div>
		</div>
		
		<div class="spacer50"></div>

	</logic:greaterThan>
	<html:hidden property="hdnSelSpecType" />
	<html:hidden property="hdnSelModels" />
	<html:hidden property="hdnorderNo" />
	<html:hidden property="hdnQty" />
	<html:hidden property="hdnSelectedMdlNames" />
	<html:hidden property="hdnSelSpecTypeName" />
	<input type="hidden" name="screen" value="Clause" />
	<input type="hidden" name="flag" value="Y">
		<!-- Added for LSDB_CR-87 --->
	<html:hidden property="sortByFlag"/>
	<html:hidden property="columnheader"/>
</html:form>
</div>








