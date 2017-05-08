<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT type="text/javaScript" SRC="js/CopySpec.js"></SCRIPT>
<script>
       $(document).ready(function() { 
       	$("#sltSpecType").select2({theme:'bootstrap'});
       	$("#sltModel").select2({theme:'bootstrap'});
       	$("#sltDistributor").select2({theme:'bootstrap'});   
       	$("#sltCustomer").select2({theme:'bootstrap'});  
       	$("#sltCust").select2({theme:'bootstrap'});  
       	
       	$('#tbCopySpec').DataTable({
	    	 searching: false,
	    	 paging:   false,
	         ordering: false,
	         info:     false,
	         autoWidth: false,
	    	 scrollY: "265px"
	  		});
       })
   </script>

<div class="container-fluid">
<html:form action="/CopySpecAction" method="post">
		<h4 class="text-center"><bean:message key="Application.Screen.CopySpec" /></h4>
		<p class="text-center"><mark><bean:message key="Message.WildcardSearch" /></mark></p>
		<div class="spacer10"></div>
		<div class="errorlayerhide" id="errorlayer">
		</div>
		<logic:present name="CopySpecForm" property="messageID" scope="request">
			<bean:define id="messageID" name="CopySpecForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
		</logic:present>
		
		<logic:present name="CopySpecForm" property="orderList">
			<bean:size id="ordListLen" name="CopySpecForm" property="orderList" />
		</logic:present>
		
		<logic:match name="CopySpecForm" property="method" value="fetchOrders">
			<logic:lessThan name="ordListLen" value="1">
				<script>
				fnNoRecords();
				</script>
			</logic:lessThan>
		</logic:match>
		
		<div class="spacer10"></div>
		<div class="form-group">
			<div class="row form-inline">
				<div class="col-sm-12 text-center">
					<div class="form-group required-field">
						<label class="control-label">Specification Type</label>
						<html:select name="CopySpecForm" styleClass="form-control" styleId="sltSpecType"
								property="spectypeSeqno" onchange="javascript:fnLoadModels()">
								<option selected value="-1">---Select---</option>
								<html:optionsCollection name="CopySpecForm"
									property="specTypeList" value="spectypeSeqno" label="spectypename" />
							</html:select>
								
					</div>
					
					<div class="form-group required-field">
						<label class="control-label">Model</label>
							<html:select name="CopySpecForm" styleClass="form-control" property="modelSeqNo" styleId="sltModel">
								<option selected value="-1">---Select---</option>
								<logic:present name="CopySpecForm" property="modelList">
									<html:optionsCollection name="CopySpecForm" property="modelList"
										value="modelSeqNo" label="modelName" />
								</logic:present>
							</html:select>
							
					</div>
					
					<div class="form-group">
						<label class=" control-label">Customer</label>
							<html:select name="CopySpecForm" styleClass="form-control" property="customerSeqNO" styleId="sltCust">
								<option selected value="-1">---Select---</option>
								<logic:present name="CopySpecForm" property="custList">
									<html:optionsCollection name="CopySpecForm"
										property="custList" value="customerSeqNo" label="customerName" />
								</logic:present>
							</html:select>
							
					</div>
				
					<div class="form-group required-field">
						<label class="control-label">Order Number</label>
							<html:text styleClass="form-control" property="orderNo" maxlength="15"  styleId="orderNo"/>
							
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="spacer30"></div>
		</div>
		
		<div class="row">
			<div class="form-group">
		        <div class="col-sm-offset-5 col-sm-3">
		              <button class="btn btn-primary addbtn" type='button' id="searchButton" ONCLICK="javascript:fnSearchOrders()">Search</button>
		        </div>
	     	</div>
	    </div>
			
		</div>
		<logic:greaterThan name="ordListLen" value="0">		
		<div class="container-fluid">
		<hr>
		</div>
		<div class="container" width="100%">
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
					<logic:notEqual name="CopySpecForm" property="spectypeSeqno" value="-1">
						<bean:write name="CopySpecForm" property="hdnSelSpecType"/>
					</logic:notEqual>
					<logic:equal name="CopySpecForm" property="spectypeSeqno" value="-1">
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
					<logic:notEqual name="CopySpecForm" property="modelSeqNo" value="-1">
						<bean:write name="CopySpecForm" property="hdnSelModel" />
					</logic:notEqual>
					<logic:equal name="CopySpecForm" property="modelSeqNo" value="-1">
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
					<logic:notEqual name="CopySpecForm" property="customerSeqNO" value="-1">
						<bean:write name="CopySpecForm" property="hdnSelectedCustomers" />
					</logic:notEqual>
					<logic:equal name="CopySpecForm" property="customerSeqNO" value="-1">
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
					<bean:write name="CopySpecForm" property="orderNo" />
				</div>
			</div>
			<div class="row">
				<div class="spacer30"></div>
			</div>
			
			
			<div class="row">
			
			  <div class="col-sm-6">
			  <fieldset class="scheduler-border">
    			<legend class="scheduler-border">From Order</legend>
					<table class="table table-bordered" id="tbCopySpec" height="50">
						<thead>
			            	<tr>
			                	<th class="col-sm-1 text-center">Order Number</th>
			                	<th class="col-sm-1 text-center">Spec Status</th>
			                	<th class="col-sm-1 text-center">Customer Name</th>
			                	<th class="col-sm-1 text-center">Domestic/Export</th>
			                	<th class="col-sm-1 text-center">Select</th>
			                </tr>
			         	</thead>
			         	<tbody class="text-center">
			              		<logic:iterate id="ordListId" name="CopySpecForm"
									property="orderList" type="com.EMD.LSDB.vo.common.OrderVO">
									<TR>
										<TD class="v-midalign"><bean:write name="ordListId"
											property="orderNo" /></TD>
										<TD class="v-midalign"><bean:write name="ordListId"
											property="statusDesc" /></TD>
										<logic:iterate id="cusListId" name="ordListId"
											property="customerVo">
											<TD class="v-midalign"><bean:write name="cusListId"
												property="customerName" /></TD>
											<TD class="v-midalign"><logic:equal name="cusListId"
												property="custTypeSeqNo" value="1">
												<bean:message key="copySpecDomestic" />
											</logic:equal> <logic:equal name="cusListId"
												property="custTypeSeqNo" value="2">
												<bean:message key="copySpecExport" />
											</logic:equal></TD>
										</logic:iterate>
										<TD class="v-midalign">
										   <html:radio value="<%=String.valueOf(ordListId.getOrderKey())%>"
											property="orderKey" />
										</TD>
								</TR>
							</logic:iterate>
			              	            
						</tbody>
			   		</table>
			   		</fieldset>
			    </div>
			    
			    
  				<div class="col-sm-6">
  				<fieldset class="scheduler-border">
    			<legend class="scheduler-border">To Order</legend>
    			
    			<div class="form-horizontal">
    			<div class="row">
	  				<div class="form-group">
	  					<label class="col-sm-4 control-label">Customer Type</label>
	  					<div class="col-sm-7">
		  					<logic:equal name="CopySpecForm" property="distributorFlag" value="N">
		  						<label class="radio-inline control-label">
		  							<html:radio	property="custTypeSeqNo" styleId="custTypeSeqNo1" value="1"
									name="CopySpecForm" onclick="javascript:fnLoadCustomers()" />Domestic
								</label>
		  						<label class="radio-inline control-label">
		  							<html:radio property="custTypeSeqNo" styleId="custTypeSeqNo2" value="2" 
		  							name="CopySpecForm"	onclick="javascript:fnLoadCustomers()"/>Export
								</label>
							</logic:equal>
							<logic:notEqual name="CopySpecForm" property="distributorFlag" value="N">
		  						<label class="radio-inline disabled control-label">
		  							<input type="radio" name="custTypeSeqNo" value="1" disabled="false"
									checked="true" />&nbsp;Domestic
								</label>
								<label class="radio-inline disabled control-label">
									<html:radio property="custTypeSeqNo" value="2" name="CopySpecForm"
									disabled="true" />&nbsp;Export
								</label>
							</logic:notEqual>
		  					
	  					</div>
	  				</div>
	  			</div>
  				
    			<div class="row">
  				<div class="form-group required-field">
  					<label class="col-sm-4 control-label">Distributor</label>
  					<div class="col-sm-7">
  						<logic:equal name="CopySpecForm" property="distributorFlag" value="N">	
							<html:select name="CopySpecForm" property="distSeqNo" styleId="sltDistributor" styleClass="form-control disabled"
								disabled="true">
								<option selected value="-1">---Select---</option>
								<logic:present name="CopySpecForm" property="distList">
									<html:optionsCollection name="CopySpecForm" property="distList"
										value="distributorSeqNo" label="distributorName" />
								</logic:present>
							</html:select>
						</logic:equal>
						<logic:notEqual name="CopySpecForm"	property="distributorFlag" value="N">
							<html:select name="CopySpecForm" property="distSeqNo" styleClass="form-control" styleId="sltDistributor">
								<option selected value="-1">---Select---</option>
								<logic:present name="CopySpecForm" property="distList">
									<html:optionsCollection name="CopySpecForm" property="distList"
										value="distributorSeqNo" label="distributorName" />
								</logic:present>
							</html:select>
						</logic:notEqual>
					
					</div>
				</div>
				</div>
							
    			<div class="row">
					<div class="form-group required-field">
						<label class="col-sm-4 control-label">Customer</label>	
						<div class="col-sm-7">	
							<html:select name="CopySpecForm" property="custSeqNo" styleClass="form-control" styleId="sltCustomer">
								<option selected value="-1">---Select---</option>
								<logic:present name="CopySpecForm" property="customerList">
									<html:optionsCollection name="CopySpecForm" property="customerList"
										value="customerSeqNo" label="customerName" />
								</logic:present>
							</html:select>
							
						</div>
					</div>
				</div>
			
				<div class="row">
					<div class="form-group">
						<label class="col-sm-4 control-label">Model</label>
						<div class="col-sm-7">	
							<p>
								<bean:write name="CopySpecForm"
								property="hdnSelModel" />
							</p>
						</div>
					</div>
				</div>
			
				<div class="row">
					<div class="form-group required-field">
						<label class="col-sm-4 control-label">SAP Customer Code</label>
						<div class="col-sm-4">	
							<html:text styleClass="form-control" property="sapCustCode" value="" maxlength="10" styleId="sapCusCode" />
							
						</div>
					</div>
				</div>
			
				<div class="row">
					<div class="form-group required-field">
						<label class="col-sm-4 control-label">Order Number</label>
						<div class="col-sm-4">	
							<html:text styleClass="form-control" property="orderNumber" value="" maxlength="15" styleId="orderNO" />
							
						</div>
					</div>
				</div>
			
				<div class="row">
					<div class="form-group required-field">
						<label class="col-sm-4 control-label">Quantity</label>
						<div class="col-sm-4">
							<html:text styleClass="form-control" property="quantity" value=""	maxlength="10" styleId="quantity" />
							
						</div>
					</div>
				</div>
			
				<div class="row">
					<div class="form-group">
						<label class="col-sm-4 control-label">Clear All Indicators</label>
						<div class="col-sm-7">
							<html:checkbox property="copyMdlIndFlag" />
						</div>
					</div>	
				</div>
    				
    			</div>	
				
				</fieldset>		
																
			</div>
					<div class="row">
						<div class="spacer10"></div>
					</div>
			
					<div class="row">
						<div class="form-group">
					         <div class="col-sm-11 text-center">
					            <button class="btn btn-primary cpySpecFrmMdlbtn" type='button' id="CopySpecFromModelButton" ONCLICK="javascript:fnCopySpecFrmModel()">Copy Spec From Model</button>
					            <button class="btn btn-primary cpySpecbtn" type='button' id="CopySpecButton" ONCLICK="javascript:fnCopySpec()">Copy Spec</button>
					        </div>
		     			</div>
		    		</div>
		    
		    <div class="row">
				<div class="spacer30"></div>
			</div>
			
			</div>
		</logic:greaterThan>
			
    <!-- Added hidden parameter for Customer drop down by cm68219 -->
    <div class="container">
    <html:hidden property="hdnPreSelectedCustomer" />
    <html:hidden property="hdnSelectedCustomers" />
	<html:hidden property="hdnSelSpecType" />
	<html:hidden property="hdnSelModel" />
	<html:hidden property="hdPreSelectedModel" />
	<input type="hidden" name="flag" value="Y">
	<!-- Added For CR_84 -->
	<html:hidden property="distributorFlag"/>
	<!-- Added For CR_131 -->
	<html:hidden property="hdnOrderKey" styleId="hdnOrderKey"/>
	
</html:form>
</div>
