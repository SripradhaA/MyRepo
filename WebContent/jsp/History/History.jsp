<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<SCRIPT type="text/JavaScript" SRC="js/History.js"></SCRIPT>
<SCRIPT type="text/JavaScript">
function fnOpenPDF(url)
{

	window.open(url,"",""); 
}
</SCRIPT>
<script>
    $(document).ready(function() { 
    	$.fn.dataTable.moment( 'DD-MMM-YYYY HH:mm:ss' ); 
    	$("#sltSpecType").select2({theme:'bootstrap'}); 
    	$("#sltSpecStatus").select2({theme:'bootstrap'}); 
	    $('#tbHistoryTable').DataTable({
	    	 paging:   false,
	         ordering: true,
	         info:     false,
	         order: [[ 9, 'desc' ]],
	         columnDefs: [
                       { "orderable": false, "targets": [1,2,3,4,5,8] },
                       { "searchable": false, "targets": [1,2,3,4,5,8] },
                       { "type": "natural", "targets": [0] }
                 ]
	   		});
    	})
</script>

<div class = "container-fluid">

<html:form action="/HistoryAction" method="post">

	<h4 class ="text-center">
		<bean:message key="Application.Screen.History" />
	</h4>

	<div class="spacer10"></div>
	
	<p class="text-center"><mark><bean:message key="Message.WildcardSearch"/></mark></p>
	
	<p class="text-center"><mark>Information given in <i>Italics</i> is additional detail</mark></p>
	
	<p class="text-center"><mark>Press SHIFT/CTRL for selecting multiple Models/Customers</mark></p>
								
	<div class="spacer20"></div>

	<div class="errorlayerhide" id="errorlayer">
	</div>
	
	<logic:present name="HistoryForm" property="orderList">
		<bean:size id="ordListLen" name="HistoryForm" property="orderList" />
	</logic:present>
	
	<logic:match name="HistoryForm" property="method" value="fetchOrders">
		<logic:lessThan name="ordListLen" value="1">
			<script>
			//Updated for CR-121 server side error message
			fnNoRecords();
		</script>
		</logic:lessThan>
	</logic:match>
	
	<div class="form-inline">
	
		<div class="row">
			<div class="col-sm-11 text-center">
				<div class="form-group">
					<label class="control-label">Specification Type</label>
						<html:select
							name="HistoryForm" property="specTypeSeqno" styleId="sltSpecType"
							styleClass="form-control" onchange="javascript:fnLoadCustomers()">
							<option selected value="-1">---Select---</option>
							<html:optionsCollection name="HistoryForm" property="specTypeList"
								value="spectypeSeqno" label="spectypename" />
						</html:select>
						
				</div>
				
				<div class="form-group">
					<label class="control-label">Model</label>
						<html:select name="HistoryForm" property="modelSeqnos" size="6"
							styleClass="form-control" multiple="true" styleId="sltModel">
							<html:optionsCollection name="HistoryForm" property="modelList" value="modelSeqNo" label="modelName" />
						</html:select>
				</div>
				
				<div class="form-group">
					<label class="control-label">Spec Status</label>
						<html:select name="HistoryForm" styleId="sltSpecStatus"
							styleClass="form-control" property="specStatusSeqNo">
							<html:option value="-1">---Select---</html:option>
							<html:option value="1">All</html:option>
							<html:option value="5">Final</html:option>
						</html:select>
				</div>
				
				<div class="form-group">
					<label class=" control-label">Customer</label>
						<html:select name="HistoryForm" property="customerSeqnos" size="6" multiple="true" styleId="sltCustomer"
							styleClass="form-control">
						<html:optionsCollection name="HistoryForm" property="customerList" value="customerSeqNo" label="customerName" />
						</html:select>
				</div>
				
			</div>
		</div>
		
		<div class="row">	
			<div class="spacer20"></div>
		</div>
		
		<div class="row">
			<div class="col-sm-11 text-center">
				<div class="form-group">
					<label class="control-label">Order Number <font color="red">*</font></label>
					<html:text property="orderNum" styleClass="form-control" size="10"
					maxlength="15" onkeypress="return keyHandler()" />
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="spacer20"></div>
		</div>
		
		<div class="row">
			<div class="col-sm-11 text-center">
		              <button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnSearchOrders()">Search</button>
	        </div>
	   	</div>
   	
   	</div>
   	
	<html:hidden property="hdnSelectedCustNames" />
	<html:hidden property="hdnSpecTypeNme" />
	<html:hidden property="hdnSpecStatus" />
	<html:hidden property="sortByFlag"/>
	<html:hidden property="columnheader"/>
	<html:hidden property="hdnSelectedModelNames"/>
	
	<logic:notEmpty name="HistoryForm" property="orderList">
		<HR>
		<div class="row">
			<div class="col-sm-11 text-center"><h5 class="text-center"><strong>Search critera</strong></h5></div>
		</div>
		<div class="row">
			<div class="spacer10"></div>
		</div>
		
		<div class="row">
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Specification
				Type</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<logic:notEqual name="HistoryForm" property="specTypeSeqno"
					value="-1">
					<bean:write name="HistoryForm" property="hdnSpecTypeNme" />
				</logic:notEqual>
			</div>
		</div>
		<div class="row">
			<div class="spacer10"></div>
		</div>
		<div class="row">	  
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Model</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<logic:notEqual name="HistoryForm" property="modelSeqnos"
					value="-1">
					<bean:write name="HistoryForm" property="hdnSelectedModelNames" />
				</logic:notEqual>
			</div>
		</div>
		<div class="row">
			<div class="spacer10"></div>
		</div>
		<div class="row">	  
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Spec Status</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<logic:notEqual name="HistoryForm" property="specStatusSeqNo"
					value="-1">
					<bean:write name="HistoryForm" property="hdnSpecStatus" />
				</logic:notEqual>
			</div>
		</div>
		<div class="row">
			<div class="spacer10"></div>
		</div>
		<div class="row">	
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Customer</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<logic:notEqual name="HistoryForm" property="customerSeqnos"
					value="-1">
					<bean:write name="HistoryForm" property="hdnSelectedCustNames" />
				</logic:notEqual>
			</div>
		</div>
		<div class="row">
			<div class="spacer10"></div>
		</div>
		<div class="row" >
			<div class="col-sm-2 col-sm-offset-3 text-right required-field"><strong>Order No</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<bean:write name="HistoryForm" property="orderNum" />
			</div>
		</div>
				
		<div class="spacer20"></div>
		
		<TABLE class="table table-bordered table-hover" id="tbHistoryTable">
			<thead>
				<TR>
					<TH WIDTH="5%" nowrap="nowrap">Order Number<br/>Spec Status</TH>
					<TH WIDTH="9%">No Rev</TH>
					<TH WIDTH="9%">Latest Rev</TH>
					<TH WIDTH="8%">All Rev</TH>
					<TH WIDTH="9%">All Rev from Baseline</TH>
					<TH WIDTH="9%">Spec Supplement</TH>
					<TH WIDTH="12%" >Customer</TH>
					<TH WIDTH="8%">Model</TH>
					<TH WIDTH="6%">EDL Listings</TH>
					<TH WIDTH="13%">Published Date</TH>
					<TH WIDTH="13%">Published User</TH>
				</TR>
			</thead>
			
			<tbody>
				<logic:iterate id="ordListId" name="HistoryForm" property="orderList"
				type="com.EMD.LSDB.vo.common.OrderVO" indexId="orderCount">
					<TR>
						<logic:equal name="ordListId" property="specStatusCode" value="2">
								<logic:match name="ordListId" property="statusDesc" value="Proposal">
									<TD nowrap CLASS='text-center v-midalign' data-sort="<bean:write name="ordListId" property="orderNo" />-<bean:write name='ordListId' property='specStatusCode'/>-2-<bean:write name='ordListId' property='specRevCode'/>-<bean:write name='ordListId' property='specSubRevCode'/>">
								</logic:match>
								<logic:notMatch name="ordListId" property="statusDesc" value="Proposal">
									<TD nowrap CLASS='text-center v-midalign' data-sort="<bean:write name="ordListId" property="orderNo" />-<bean:write name='ordListId' property='specStatusCode'/>-1-<bean:write name='ordListId' property='specRevCode'/>-<bean:write name='ordListId' property='specSubRevCode'/>">
								</logic:notMatch>
							</logic:equal>
							<logic:notEqual name="ordListId" property="specStatusCode" value="2">
								<logic:equal name="ordListId" property="specStatusCode" value="3">
									<logic:match name="ordListId" property="statusDesc" value="Baseline">
										<TD nowrap CLASS='text-center v-midalign' data-sort="<bean:write name="ordListId" property="orderNo" />-<bean:write name='ordListId' property='specStatusCode'/>-2-<bean:write name='ordListId' property='specRevCode'/>-<bean:write name='ordListId' property='specSubRevCode'/>">
									</logic:match>
									<logic:notMatch name="ordListId" property="statusDesc" value="Baseline">
										<TD nowrap CLASS='text-center v-midalign' data-sort="<bean:write name="ordListId" property="orderNo" />-<bean:write name='ordListId' property='specStatusCode'/>-1-<bean:write name='ordListId' property='specRevCode'/>-<bean:write name='ordListId' property='specSubRevCode'/>">
									</logic:notMatch>
								</logic:equal>
								<logic:notEqual name="ordListId" property="specStatusCode" value="3">
									<TD nowrap CLASS="text-center v-midalign" data-sort="<bean:write name="ordListId" property="orderNo" />-<bean:write name='ordListId' property='specStatusCode'/>-<bean:write name='ordListId' property='specRevCode'/>-<bean:write name='ordListId' property='specSubRevCode'/>">
								</logic:notEqual>
							</logic:notEqual>					
							<html:hidden name="ordListId" property="orderKey" />
							<bean:write name="ordListId" property="orderNo" /> <html:hidden name="ordListId" property="orderNo" />
							<br/>
							<bean:write name="ordListId" property="statusDesc" /> 
							<html:hidden name="ordListId" property="statusDesc" />
						</TD>	
							<logic:present name="ordListId" property="fileLoc">
							<logic:iterate id="fileLoc" name="ordListId" property="fileLoc" indexId="count">
								<bean:define id="size" name="count"/>
							
									<logic:equal name="size" value="0">
										<TD CLASS='text-center v-midalign'>
											<logic:notEmpty  name="fileLoc">
											<logic:iterate id="RevList" name="fileLoc">
											<logic:notEqual name="RevList" property="fileDesc"
												value="Spec Supplement">
											<logic:equal name="RevList" property="revViewSeqNo" value="1">
												 	 <a href='<bean:write name="RevList" property="fileLoc"/>'
														CLASS='linkstyle1' target="_blank"><bean:write name="RevList"
														property="fileDesc" /></a>		
													<BR>
											</logic:equal>
											</logic:notEqual>
											</logic:iterate>
											</logic:notEmpty>
											<logic:empty  name="fileLoc">
												&nbsp;
											</logic:empty>
										</TD>
									</logic:equal>

									<logic:equal name="size" value="1">
										<TD CLASS='text-center v-midalign'>
											<logic:notEmpty  name="fileLoc">
											<logic:iterate id="RevList" name="fileLoc">
											<logic:notEqual name="RevList" property="fileDesc"
												value="Spec Supplement">
											<logic:equal name="RevList" property="revViewSeqNo" value="2">
												<a href='<bean:write name="RevList" property="fileLoc"/>'
												CLASS='linkstyle1' target="_blank"><bean:write name="RevList"
												property="fileDesc" /></a>	
												<BR>
											</logic:equal>
											</logic:notEqual>
											</logic:iterate>
											</logic:notEmpty>
											<logic:empty  name="fileLoc">
												&nbsp;
											</logic:empty>
										</TD>
									</logic:equal>

									<logic:equal name="size" value="2">
										<TD CLASS='text-center v-midalign'>
											<logic:notEmpty  name="fileLoc">
											<logic:iterate id="RevList" name="fileLoc">
											<logic:notEqual name="RevList" property="fileDesc"
												value="Spec Supplement">
											<logic:equal name="RevList" property="revViewSeqNo" value="3">
											<a href='<bean:write name="RevList" property="fileLoc"/>'
											CLASS='linkstyle1' target="_blank"><bean:write name="RevList"
											property="fileDesc" /></a>		
											<BR>
											</logic:equal>
											</logic:notEqual>
											</logic:iterate>
											</logic:notEmpty>
											<logic:empty  name="fileLoc">
											&nbsp;
											</logic:empty>
										</TD>
									</logic:equal>
										
									<logic:equal name="size" value="3">
										<TD  CLASS='text-center v-midalign'>
											<logic:notEmpty  name="fileLoc">
											<logic:iterate id="RevList" name="fileLoc">
											<logic:notEqual name="RevList" property="fileDesc"
												value="Spec Supplement">
											<logic:equal name="RevList" property="revViewSeqNo" value="4">
											<a href='<bean:write name="RevList" property="fileLoc"/>'
											CLASS='linkstyle1' target="_blank"><bean:write name="RevList"
											property="fileDesc" /></a>
											<BR>
											</logic:equal>
											</logic:notEqual>
											</logic:iterate>
											</logic:notEmpty>
											<logic:empty  name="fileLoc">
											&nbsp;
											</logic:empty>
										</TD>
									</logic:equal>
										
									<logic:equal name="size" value="4">
										<TD CLASS='text-center v-midalign'>
											<logic:iterate id="RevList" name="fileLoc">
											<logic:equal name="RevList" property="revViewSeqNo" value="1">
											<a href='<bean:write name="RevList" property="fileLoc"/>'
											CLASS='linkstyle1' target="_blank"><bean:write name="RevList"
											property="fileDesc" /></a>		
											<BR>
											</logic:equal>
											
											</logic:iterate>
											<logic:empty  name="fileLoc">
											&nbsp;
											</logic:empty>
										</TD>
									</logic:equal>
						</logic:iterate>
						</logic:present>
								
						<TD CLASS='text-center v-midalign'>
							<bean:write name="ordListId" property="customerName" />
						</TD>
	
						<TD CLASS='text-center v-midalign' nowrap="nowrap" data-sort="<bean:write name="ordListId" property="modelName" />">
							<logic:equal name="ordListId" property="custMdlName" value="<%= String.valueOf(ordListId.getModelName())%>">
								<bean:write name="ordListId" property="modelName" />
								<html:hidden name="ordListId" property="modelName" />
							</logic:equal>
							<logic:notEqual name="ordListId" property="custMdlName" value="<%= String.valueOf(ordListId.getModelName())%>">
								<bean:write name="ordListId" property="modelName" />
								<html:hidden name="ordListId" property="modelName" />
								 <br/>
								<i><bean:write name="ordListId" property="custMdlName" /></i>
								<html:hidden name="ordListId" property="custMdlName" />
							</logic:notEqual>
						</TD>
	
						<TD CLASS='text-center v-midalign' >
							<logic:iterate id="ordListId2" name="HistoryForm" property="orderList"
								type="com.EMD.LSDB.vo.common.OrderVO" indexId="edlOrderCount">
									<logic:present name="ordListId" property="fileLoc">
										<%if(Integer.parseInt(orderCount.toString()) == Integer.parseInt(edlOrderCount.toString())){%>
												<logic:iterate id="fileLoc" name="ordListId2" property="fileLoc" indexId="count">
													<logic:equal name="count" value="5">
														<logic:iterate id="edlList" name="fileLoc"  indexId="countEdl">
																<logic:equal name="countEdl" value="0">
																		<A name="searchimg" target="_blank" HREF="<bean:write name="edlList"	property="fileLoc" />" CLASS='linkstyle1' alt="revision"><bean:write name="edlList"	property="fileDesc" /></A>
																</logic:equal>
																<logic:equal name="countEdl" value="1">
																		<A name="searchimg" target="_blank" HREF="<bean:write name="edlList"	property="fileLoc" />" CLASS='linkstyle1' alt="revision"><bean:write name="edlList"	property="fileDesc" /></A>	
																</logic:equal>
														</logic:iterate>
													</logic:equal>
												</logic:iterate>
										<%}%>
									</logic:present>
							</logic:iterate>
						</TD>
						<TD CLASS='text-center v-midalign' data-sort="<bean:write name="ordListId" property="publishedDate" />">
							<bean:write name="ordListId" property="publishedDate" />
						</TD>
						<TD CLASS='text-center v-midalign'>
							<bean:write name="ordListId" property="publishedUser" />&nbsp;
							<html:hidden name="ordListId" property="publishedUser" />
						</TD>
					</TR>
				</logic:iterate>
			</tbody>
		</TABLE>
	</logic:notEmpty>
		
	<div class="spacer50"></div>
	
	<input type="hidden" name="flag" value="Y">

</html:form>

</div>
