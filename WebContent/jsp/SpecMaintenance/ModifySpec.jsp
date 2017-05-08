<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT type="text/javascript" SRC="js/ModifySpec.js"></SCRIPT>
<script>
       $(document).ready(function() { 
	       	$("#sltSpecType").select2({theme:'bootstrap'});
	       	$("#sltModel").select2({theme:'bootstrap'});
	       	$("#sltDistributor").select2({theme:'bootstrap'});   
	       	$("#sltCustomer").select2({theme:'bootstrap'}); 
		    $('#tbMdfySpec').DataTable({
		    	 paging:   false,
		         ordering: true,
		         info:     false,
		         order: [[ 0, 'desc' ]],
		         columnDefs: [
	                        { "orderable": false, "targets": [8] },
	                        { "searchable": false, "targets": [8] },
	                        { "type": "natural", "targets": [0,1] }
                      ]
		    }); 
	    }); 
   </script>
   <div class="container-fluid">
	<html:form  action="/ModifySpecAction" method="post">
		<h4 class="text-center"><bean:message key="Application.Screen.ModifySpec" /></h4>
		<p class="text-center"><mark><bean:message key="Message.WildcardSearch" /></mark></p>
		<div class="spacer10"></div>
		<div class="errorlayerhide" id="errorlayer">
		</div>
		<logic:present name="ModifySpecForm" property="messageID" scope="request">
			<bean:define id="messageID" name="ModifySpecForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
		</logic:present>
		
		<logic:present name="ModifySpecForm" property="orderList">
			<bean:size id="ordListLen" name="ModifySpecForm" property="orderList" />
		</logic:present>
		
		<logic:match name="ModifySpecForm" property="method" value="fetchOrders">
			<logic:lessThan name="ordListLen" value="1">
				<script>
				fnNoRecords();
				</script>
			</logic:lessThan>
		</logic:match>		
		
		<div class="spacer10"></div>

		<div class="row form-inline">
			<div class="col-sm-12 text-center">
				<div class="form-group">
					<label class="control-label">Specification Type</label>
					<html:select name="ModifySpecForm" styleId="sltSpecType"
							property="spectypeSeqno" onchange="javascript:fnLoadModels()" styleClass="form-control">
							<option selected value="-1">---Select---</option>
							<html:optionsCollection name="ModifySpecForm"
								property="specTypeList" value="spectypeSeqno" label="spectypename" />
						</html:select>
				</div>
				<div class="form-group">
					<label class="control-label">Model</label>
						<html:select name="ModifySpecForm" property="modelSeqNo" styleId="sltModel" styleClass="form-control">
							<option selected value="-1">---Select---</option>
							<logic:present name="ModifySpecForm" property="modelList">
								<html:optionsCollection name="ModifySpecForm" property="modelList"
									value="modelSeqNo" label="modelName" />
							</logic:present>
						</html:select>
				</div>
				
				<div class="form-group">
					<label class="control-label">Customer</label>
						<html:select name="ModifySpecForm" property="custSeqNo" styleId="sltCustomer" styleClass="form-control">
							<option selected value="-1">---Select---</option>
							<logic:present name="ModifySpecForm" property="customerList">
								<html:optionsCollection name="ModifySpecForm"
									property="customerList" value="customerSeqNo" label="customerName" />
							</logic:present>
						</html:select>
				</div>
			
				<div class="form-group required-field">
					<label class="control-label">Order Number</label>
						<html:text styleClass="form-control" property="orderNo" maxlength="15" styleId="orderNo"/>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="spacer30"></div>
		</div>
		
		<div class="row">
			<div class="form-group">
		        <div class="col-sm-offset-5 col-sm-3">
		              <button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnSearchOrders()">Search</button>
		        </div>
	     	</div>
	    </div>
			
		
		<logic:greaterThan name="ordListLen" value="0">		
		<div class="container-fluid">
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
					<logic:notEqual name="ModifySpecForm" property="spectypeSeqno" value="-1">
						<bean:write name="ModifySpecForm" property="hdnSelSpecType"/>
					</logic:notEqual>
					<logic:equal name="ModifySpecForm" property="spectypeSeqno" value="-1">
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
					<logic:notEqual name="ModifySpecForm" property="modelSeqNo" value="-1">
						<bean:write name="ModifySpecForm" property="hdnSelModel" />
					</logic:notEqual>
					<logic:equal name="ModifySpecForm" property="modelSeqNo" value="-1">
						&nbsp;
					</logic:equal>
				</div>
			</div>
			<div class="row">
				<div class="spacer10"></div>
			</div>
			<div class="row">	
				<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Customer</strong></div>
				<div class="col-sm-1 text-center">:</div>
				<div class="col-sm-2 text-left">
					<logic:notEqual name="ModifySpecForm" property="custSeqNo" value="-1">
						<bean:write name="ModifySpecForm" property="hdnSelectedCustomers" />
					</logic:notEqual>
					<logic:equal name="ModifySpecForm" property="custSeqNo" value="-1">
						&nbsp;
					</logic:equal>
				</div>
			</div>
			<div class="row">
				<div class="spacer10"></div>
			</div>
			<div class="row">
				<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Order No</strong></div>
				<div class="col-sm-1 text-center">:</div>
				<div class="col-sm-2 text-left">
					<bean:write name="ModifySpecForm" property="orderNo" />
				</div>
			</div>
			<div class="row">
				<div class="spacer10"></div>
			</div>
					
			<table class="table table-bordered" id="tbMdfySpec">
			    <thead>
			        <tr>
			            <th class="text-center">Order Number</th>
			            <th class="text-center">Spec Status</th>
			            <th class="text-center">Model</th>
			            <th class="text-center">Customer Name</th>
			            <th class="text-center">Domestic/Export</th>
			            <th class="text-center">Custom Model Name</th>
			            <th class="text-center">Quantity</th>
			            <th class="text-center">SAP Customer Code</th>
			            <th class="text-center">Select</th>
			        </tr>
			    </thead>
			    
			    <tbody class="text-center">
				    <logic:iterate id="ordListId" name="ModifySpecForm"
					property="orderList" type="com.EMD.LSDB.vo.common.OrderVO">
				        <tr>
				            <td class="v-midalign" data-sort="<bean:write name='ordListId' property='orderNo' />" data-search="<bean:write name='ordListId' property='orderNo' />"><a href="MainFeatureInfo.do?method=fetchComponentDesc&orderKeyId=<%=ordListId.getOrderKey()%>&specCode=<%=ordListId.getSpecStatusCode()%>">	
							<bean:write name="ordListId" property="orderNo" /> </a></td>
				            
				            <logic:equal name="ordListId" property="specStatusCode" value="2">
								<logic:match name="ordListId" property="statusDesc" value="Proposal">
									<TD CLASS='v-midalign' data-sort="<bean:write name='ordListId' property='specStatusCode'/>-2-<bean:write name='ordListId' property='specRevCode'/>-<bean:write name='ordListId' property='specSubRevCode'/>">
								</logic:match>
								<logic:notMatch name="ordListId" property="statusDesc" value="Proposal">
									<TD CLASS='v-midalign' data-sort="<bean:write name='ordListId' property='specStatusCode'/>-1-<bean:write name='ordListId' property='specRevCode'/>-<bean:write name='ordListId' property='specSubRevCode'/>">
								</logic:notMatch>
							</logic:equal>
							<logic:notEqual name="ordListId" property="specStatusCode" value="2">
								<logic:equal name="ordListId" property="specStatusCode" value="3">
									<logic:match name="ordListId" property="statusDesc" value="Baseline">
										<TD CLASS='v-midalign' data-sort="<bean:write name='ordListId' property='specStatusCode'/>-2-<bean:write name='ordListId' property='specRevCode'/>-<bean:write name='ordListId' property='specSubRevCode'/>">
									</logic:match>
									<logic:notMatch name="ordListId" property="statusDesc" value="Baseline">
										<TD CLASS='v-midalign' data-sort="<bean:write name='ordListId' property='specStatusCode'/>-1-<bean:write name='ordListId' property='specRevCode'/>-<bean:write name='ordListId' property='specSubRevCode'/>">
									</logic:notMatch>
								</logic:equal>
								<logic:notEqual name="ordListId" property="specStatusCode" value="3">
									<TD CLASS="v-midalign" data-sort="<bean:write name='ordListId' property='specStatusCode'/>-<bean:write name='ordListId' property='specRevCode'/>-<bean:write name='ordListId' property='specSubRevCode'/>">
								</logic:notEqual>
							</logic:notEqual>
							<bean:write name="ordListId" property="statusDesc" /></td>
				            
				            <td class="v-midalign"><bean:write name="ordListId" property="modelName" /></td>
				            
				            <logic:iterate id="cusListId" name="ordListId" property="customerVo">
								<td class="v-midalign"><bean:write name="cusListId" property="customerName" /></td>
								
								<td class="v-midalign">
									<logic:equal name="cusListId" property="custTypeSeqNo" value="1">Domestic</logic:equal> 
									<logic:equal name="cusListId" property="custTypeSeqNo" value="2">Export</logic:equal>
								</td>
							
								<td class="v-midalign" data-sort="<%=String.valueOf(ordListId.getCustMdlName())%>" data-search="<%=String.valueOf(ordListId.getCustMdlName())%>">
									
										<label class="form-group"><html:text styleClass="form-control" name="ordListId" value="<%=String.valueOf(ordListId.getCustMdlName())%>" 
										property="custMdlName" maxlength="100"/></label>
									
								</td>
								
								<td class="v-midalign" data-sort="<%=String.valueOf(ordListId.getQuantity())%>" data-search="<%=String.valueOf(ordListId.getQuantity())%>">
									
										<label class="form-group"><html:text styleClass="form-control" name="ordListId"	value="<%=String.valueOf(ordListId.getQuantity())%>"
										property="quantity" maxlength="10"/></label>
									
								</TD>
								
								<td class="v-midalign" data-search='<bean:write name="ordListId" property="sapCusCode"/>' data-sort='<bean:write name="ordListId" property="sapCusCode"/>'>
									
										 <label class="form-group"><html:text styleClass="form-control" name="ordListId" property="sapCusCode" maxlength="10"/></label>
									
								</TD>
								
								<td class="v-midalign">
								
									  <label class="form-group"><html:radio value="<%=String.valueOf(ordListId.getOrderKey())%>"
										property="orderKey"/> 
										</label>
								
								</TD>
							</logic:iterate>
				        </tr>
			        </logic:iterate>
			    </tbody>
			</table>
			
			
			<div class="row">
				<div class="spacer10"></div>
			</div>
			
			<div class="row">
				<div class="form-group">
			         <div class="col-sm-11 text-center">
			            <button class="btn btn-primary" type='button' id="modifyButton" ONCLICK="javascript:fnModifyOrders()">Modify</button>
			        </div>
		     	</div>
		    </div>
		    
		    <div class="row">
				<div class="spacer30"></div>
			</div>
			</div>
		</logic:greaterThan>
		
		
			<html:hidden property="hdnSelectedCustomers" />
			<html:hidden property="hdnSelSpecType" />
			<html:hidden property="hdnSelModel" />
			<html:hidden property="hdnorderNo" />
			<html:hidden property="hdnQty" />
			<html:hidden property="hdnSapCustCode" />
			<html:hidden property="hdnCustMdlName"/>
			<html:hidden property="sortByFlag"/>
			<html:hidden property="columnheader"/>
			<input type="hidden" name="flag" value="Y"/>
	</html:form>
	<div class="spacer50"></div>
	</div>
	
