<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<SCRIPT type="text/javaScript" SRC="js/OndemandSpecSupplement.js"></SCRIPT>
<SCRIPT type="text/javaScript">function fnOpenPDF(url)
{
	window.open(url,"",""); 
}

$(document).ready(function() { 
		$('.ordNoChkbox').click( function(){
			$(this).parent().parent().toggleClass('highlight');
			});
		$("#sltSpecType").select2({theme:'bootstrap'});
		$("#sltCustomer").select2({theme:'bootstrap'});
	    $('#tbOnDemandSpecSupp').DataTable({
	    	 paging:   false,
	         ordering: true,
	         info:     false,
	         order: [[ 3, 'asc' ]],
	         columnDefs: [
                     { "orderable": false, "targets": [0] },
                     { "searchable": false, "targets": [0] },
                     { "type": "natural", "targets": [1,2] }
               ]
	    }); 
	});
</SCRIPT>

<div class = "container-fluid">

<html:form action="/OndemandSpecSupplement" method="post" styleClass="form-inline">

		<div class="spacer10"></div>
	
		<h4 class ="text-center">
			<bean:message key="Application.Screen.OndemandSpecSupplement" />
		</h4>
		
		<div class="spacer10"></div>

		<p class="text-center"><mark><bean:message key="Message.WildcardSearch" /></mark></p>
		
		<p class="text-center"><mark>Only two selections allowed, the Change From / To arrangement selection based on publication date.</mark></p>
		
		<div class="errorlayerhide" id="errorlayer">
		</div>
			
		<div class="spacer20"></div>
		
		<logic:present name="OndemandSpecSupplementForm" property="orderList">
			<bean:size id="ordListLen" name="OndemandSpecSupplementForm"
				property="orderList" />
		</logic:present>

		<logic:present name="OndemandSpecSupplementForm" property="messageID"
			scope="request">
		  <bean:define id="messageID" name="OndemandSpecSupplementForm" property="messageID"/>
		  <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
	      <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
		</logic:present>
	
		<logic:match name="OndemandSpecSupplementForm" property="method"
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
				<div class="form-group">
					<label class="control-label"><bean:message key="copySpecSpecification" /></label>
					<html:select name="OndemandSpecSupplementForm" property="spectypeSeqno"
						styleClass="form-control" styleId="sltSpecType" onchange="javascript:fnLoadModelsForComparison()">
							<option selected value="-1">---Select---</option>
						<html:optionsCollection name="OndemandSpecSupplementForm"
							property="specTypeList" value="spectypeSeqno" label="spectypename" />
					</html:select>	
				</div>
				
				
				<div class="form-group">
					<label class=" control-label">Model</label>
					<html:select 
						name="OndemandSpecSupplementForm" property="modelSeqNos"
						styleClass="form-control" size="6" styleId="sltModel" multiple="true">
						<logic:present name="OndemandSpecSupplementForm" property="modelList">
							<html:optionsCollection name="OndemandSpecSupplementForm"
								property="modelList" value="modelSeqNo" label="modelName" />
						</logic:present>
					</html:select>
				</div>
				
				<div class="form-group">
					<label class=" control-label"><bean:message key="copySpecCustomer" /></label>
					<html:select name="OndemandSpecSupplementForm"
						property="custSeqNo" styleClass="form-control" styleId="sltCustomer">
							<option selected value="-1">---Select---</option>
						<logic:present name="OndemandSpecSupplementForm" property="custList">
							<html:optionsCollection name="OndemandSpecSupplementForm"
								property="custList" value="customerSeqNo" label="customerName" />
						</logic:present>
					</html:select>
				</div>
				
				<div class="form-group required-field">
					<label class="control-label"><bean:message key="copySpecOrderNo" /></label>
					<html:text property="orderNo" styleClass="form-control" size="5" styleId="orderNo"
						maxlength="15" onkeypress="return keyHandler()" />
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="spacer30"></div>
		</div>
		
		<div class="row">
			<div class="col-sm-12 text-center">
	              <button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnSearchOrders()"><bean:message key="screen.search" /></button>
	        </div>
	   	</div>
	
		<logic:greaterThan name="ordListLen" value="0">
			<HR>
			
			<div class="row">
				<div class="col-sm-11 text-center"><h5 class="text-center"><strong>Search critera</strong></h5></div>
			</div>
				<div class="spacer10"></div>
			<div class="row">
				<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Specification Type</strong></div>
				<div class="col-sm-1 text-center">:</div>
				<div class="col-sm-2 text-left">
					<logic:notEqual name="OndemandSpecSupplementForm" property="spectypeSeqno" value="-1">
						<bean:write name="OndemandSpecSupplementForm" property="specType" />
					</logic:notEqual>
				</div>
			</div>
			
			<div class="spacer10"></div>
		<div class="row">	  
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Model(s)</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-4 text-left">
				<bean:write name="OndemandSpecSupplementForm" property="hdnSelectedMdlNames" />
			</div>
		</div>
			
				<div class="spacer10"></div>
			<div class="row">	  
				<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Customer</strong></div>
				<div class="col-sm-1 text-center">:</div>
				<div class="col-sm-2 text-left">
					<logic:notEqual name="OndemandSpecSupplementForm" property="custSeqNo" value="-1">
						<bean:write name="OndemandSpecSupplementForm" property="custName" />
					</logic:notEqual>
				</div>
			</div>
				<div class="spacer10"></div>
			<div class="row">
				<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Order No</strong></div>
				<div class="col-sm-1 text-center">:</div>
				<div class="col-sm-2 text-left">
					<bean:write name="OndemandSpecSupplementForm" property="orderNo" />
				</div>
			</div>
			<div class="spacer20"></div>
	
			<TABLE class="table table-bordered table-hover" id="tbOnDemandSpecSupp">
				<thead>
					<TR>
						<TH WIDTH="5%">Select</TH>
						<TH WIDTH="10%">Spec Status</TH>
						<TH WIDTH="10%">Order Number</TH>
						<TH WIDTH="10%">Model</TH>
						<TH WIDTH="10%">Custom Model Name</TH>
						<TH WIDTH="15%">Customer Name</TH>
					</TR>
				</thead>
				
				<tbody>
				
					<logic:iterate id="ordListId" name="OndemandSpecSupplementForm"
						property="orderList" type="com.EMD.LSDB.vo.common.OrderVO">
		
						<TR>
							
							<TD CLASS='text-center v-midalign'>
								<input type="checkbox" name="orderKey" class="ordNoChkbox" 
								value="<bean:write name="ordListId" property="orderKey"/>" />
							</TD>
							
							<logic:equal name="ordListId" property="specStatusCode" value="2">
								<logic:match name="ordListId" property="statusDesc" value="Proposal">
									<TD CLASS='text-center v-midalign' data-sort="<bean:write name='ordListId' property='specStatusCode'/>-2-<bean:write name='ordListId' property='specRevCode'/>-<bean:write name='ordListId' property='specSubRevCode'/>">
								</logic:match>
								<logic:notMatch name="ordListId" property="statusDesc" value="Proposal">
									<TD CLASS='text-center v-midalign' data-sort="<bean:write name='ordListId' property='specStatusCode'/>-1-<bean:write name='ordListId' property='specRevCode'/>-<bean:write name='ordListId' property='specSubRevCode'/>">
								</logic:notMatch>
							</logic:equal>
							<logic:notEqual name="ordListId" property="specStatusCode" value="2">
								<logic:equal name="ordListId" property="specStatusCode" value="3">
									<logic:match name="ordListId" property="statusDesc" value="Baseline">
										<TD CLASS='text-center v-midalign' data-sort="<bean:write name='ordListId' property='specStatusCode'/>-2-<bean:write name='ordListId' property='specRevCode'/>-<bean:write name='ordListId' property='specSubRevCode'/>">
									</logic:match>
									<logic:notMatch name="ordListId" property="statusDesc" value="Baseline">
										<TD CLASS='text-center v-midalign' data-sort="<bean:write name='ordListId' property='specStatusCode'/>-1-<bean:write name='ordListId' property='specRevCode'/>-<bean:write name='ordListId' property='specSubRevCode'/>">
									</logic:notMatch>
								</logic:equal>
								<logic:notEqual name="ordListId" property="specStatusCode" value="3">
									<TD CLASS="text-center v-midalign" data-sort="<bean:write name='ordListId' property='specStatusCode'/>-<bean:write name='ordListId' property='specRevCode'/>-<bean:write name='ordListId' property='specSubRevCode'/>">
								</logic:notEqual>
							</logic:notEqual>							
								<bean:write name="ordListId" property="statusDesc" />
							</TD>
							
							<TD CLASS='text-center v-midalign'>
								<bean:write name="ordListId" property="orderNo" />
							</TD>
							<TD CLASS='text-center v-midalign'>
								<bean:write name="ordListId" property="modelName" />
							</TD>
							<TD CLASS='text-center v-midalign'>
								<bean:write name="ordListId" property="custMdlName" />
							</TD>
							<TD CLASS='text-center v-midalign'>
								<bean:write name="ordListId" property="customerName" />
							</TD>
						</TR>
		
					</logic:iterate>
				</tbody>
			</TABLE>
		
			<div class="spacer20"></div>
			
			<div class="row">
				<div class="col-sm-12 text-center">
					<button class="btn btn-primary" type='button' id="btnEngrSupplement" ONCLICK="javascript:fnViewEngrSupp()">View Engr Supplement</button>
					<button class="btn btn-primary" type='button' id="btnSalesSupplement" ONCLICK="javascript:fnViewSalesSupp()">View Sales Supplement</button>
				</div>
			</div>
			
			<div class="spacer50"></div>
		
	</logic:greaterThan>
	<html:hidden property="hdnSelSpecType" />	
	<html:hidden property="specType" />
	<html:hidden property="custName" />
	<html:hidden property="sortByFlag"/>
	<html:hidden property="columnheader"/>
	<html:hidden property="hdnorderNo" />
	<html:hidden property="hdnSelectedMdlNames" />
		
	<input type="hidden" name="flag" value="Y">
</html:form>
</div>