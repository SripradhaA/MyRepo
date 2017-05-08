<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT LANGUAGE="JavaScript" SRC="js/DeleteSpec.js"></SCRIPT>
<script>
       $(document).ready(function() { 
       	$("#sltSpecType").select2({theme:'bootstrap'});
       	$("#sltModel").select2({theme:'bootstrap'});
       	$("#sltCustomer").select2({theme:'bootstrap'});  
        })
   </script>

<div class="container" width="100%">
<html:form  action="/DeleteSpecAction" method="post">
		<h4 class="text-center"><bean:message key="Application.Screen.DeleteSpec" /></h4>
		<p class="text-center"><mark><bean:message key="Message.WildcardSearch" /></mark></p>
		<div class="spacer10"></div>
		<div class="errorlayerhide" id="errorlayer"></div>
		<logic:present name="DeleteSpecForm" property="messageID" scope="request">
			<bean:define id="messageID" name="DeleteSpecForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
		</logic:present>
		
		<logic:present name="DeleteSpecForm" property="orderList">
			<bean:size id="ordListLen" name="DeleteSpecForm" property="orderList" />
		</logic:present>
		
		<logic:match name="DeleteSpecForm" property="method" value="fetchOrders">
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
					<html:select name="DeleteSpecForm" styleClass="form-control" styleId="sltSpecType"
						property="spectypeSeqno" onchange="javascript:fnLoadModels()">
						<option selected value="-1">---Select---</option>
						<html:optionsCollection name="DeleteSpecForm"
							property="specTypeList" value="spectypeSeqno" label="spectypename" />
					</html:select>
				</div>
				
				<div class="form-group">
					<label class=" control-label">Model</label>
					<html:select name="DeleteSpecForm" property="modelSeqNo" styleClass="form-control" styleId="sltModel">
						<option selected value="-1">---Select---</option>
						<logic:present name="DeleteSpecForm" property="modelList">
							<html:optionsCollection name="DeleteSpecForm" property="modelList"
								value="modelSeqNo" label="modelName" />
						</logic:present>
					</html:select>
				</div>
				
				<div class="form-group">
					<label class=" control-label">Customer</label>
					<html:select name="DeleteSpecForm" property="custSeqNo" styleClass="form-control" styleId="sltCustomer">
						<option selected value="-1">---Select---</option>
						<logic:present name="DeleteSpecForm" property="customerList">
							<html:optionsCollection name="DeleteSpecForm"
								property="customerList" value="customerSeqNo" label="customerName" />
						</logic:present>
					</html:select>
				</div>
			
				<div class="form-group required-field">
					<label class=" control-label">Order Number</label>
					<html:text styleClass="form-control" property="orderNum" maxlength="15"  styleId="orderNumb"/>
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
			
		
		<logic:greaterThan name="ordListLen" value="0">		
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
					<logic:notEqual name="DeleteSpecForm" property="spectypeSeqno" value="-1">
						<bean:write name="DeleteSpecForm" property="hdnSelSpecType"/>
					</logic:notEqual>
					<logic:equal name="DeleteSpecForm" property="spectypeSeqno" value="-1">
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
					<logic:notEqual name="DeleteSpecForm" property="modelSeqNo" value="-1">
						<bean:write name="DeleteSpecForm" property="hdnSelModel" />
					</logic:notEqual>
					<logic:equal name="DeleteSpecForm" property="modelSeqNo" value="-1">
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
					<logic:notEqual name="DeleteSpecForm" property="custSeqNo" value="-1">
						<bean:write name="DeleteSpecForm" property="hdnSelectedCustomers" />
					</logic:notEqual>
					<logic:equal name="DeleteSpecForm" property="custSeqNo" value="-1">
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
					<bean:write name="DeleteSpecForm" property="orderNum" />
				</div>
			</div>
			<div class="row">
				<div class="spacer10"></div>
			</div>
					
			<table class="table table-bordered" >
			    <thead >
			        <tr>
			            <th class="text-center" >Select</th>
			            <th class="text-center" >Order Number</th>
			            <th class="text-center" >Spec Status</th>
			            <th class="text-center">Model</th>
			            <th class="text-center">Custom Model Name</th>
			            <th class="text-center" >Customer Name</th>
			            <th class="text-center" >Domestic/Export</th>
			            <th class="text-center" >Quantity</th>
			            <th class="text-center">SAP Customer Code</th>
			        </tr>
			    </thead>
			    <tbody class="text-center">
				    <logic:iterate id="ordListId" name="DeleteSpecForm"
					property="orderList" type="com.EMD.LSDB.vo.common.OrderVO">
				        <tr>
				        	<td>
								<input type="radio" name="orderNumber" />
								
							</td>
								
				            <td><bean:write name="ordListId" property="orderNo" /><html:hidden name="ordListId"
						property="orderNo" /></td>
				            
				            <td><bean:write name="ordListId" property="statusDesc" /></td>
				            
				            <td><bean:write name="ordListId" property="modelName" /></td>
				            
				            <td><bean:write name="ordListId" property="custMdlName" /></td>
				            
				            <logic:iterate id="cusListId" name="ordListId" property="customerVo">
								<td><bean:write name="cusListId" property="customerName" /></td>
								
								<td>
									<logic:equal name="cusListId" property="custTypeSeqNo" value="1">Domestic</logic:equal> 
									<logic:equal name="cusListId" property="custTypeSeqNo" value="2">Export</logic:equal>
								</td>
							</logic:iterate>
							
							<td><bean:write name="ordListId" property="quantity" /></td>
							
							<td><bean:write name="ordListId" property="sapCusCode" /></td>
					   </tr>
			        </logic:iterate>
			    </tbody>
			</table>
			
			<div class="row">
				<div class="spacer10"></div>
			</div>
			
			<div class="row">
				<div class="form-group">
			        <div class="col-sm-12 text-center">
			            <button class="btn btn-primary mdfybtn" type='button' id="modifyButton" ONCLICK="javascript:fnDeleteOrders()">Delete</button>
			        </div>
		     	</div>
		    </div>
		    
		    <div class="row">
				<div class="spacer30"></div>
			</div>
		
		</logic:greaterThan>
		<html:hidden property="hdnSelectedCustomers" />
		<html:hidden property="hdnSelSpecType" />
		<html:hidden property="hdnSelModel" />
		<html:hidden property="hdnorderNo" />
		<html:hidden property="srchOrderNum" />
		<input type="hidden" name="flag" value="Y"/>
	</html:form>
</div>

	