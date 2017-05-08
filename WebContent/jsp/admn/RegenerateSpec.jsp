<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<SCRIPT type="text/JavaScript" SRC="js/RegenerateSpec.js"></SCRIPT>

<script>
       $(document).ready(function() { 
       	$("#sltSpecType").select2({theme:'bootstrap'}); 
       	$("#sltModel").select2({theme:'bootstrap'}); 
       	$("#sltCustomer").select2({theme:'bootstrap'});
       })
</script>

<div class = "container-fluid">

<html:form action="/RegenerateSpecAction" method="post" styleClass="form-inline">

	<h4 class ="text-center">
		<bean:message key="Application.Screen.RegenerateSpec"/>
	</h4>

	<div class="spacer10"></div>

	<p class="text-center"><mark><bean:message key="Message.WildcardSearch"/></mark></p>
	
	<p class="text-center"><mark>On clicking the Regenerate Spec for PDFs button, 
		the PDFs will be regenerated for the spec status selected in the dropdown.</mark></p>
		
	<div class="errorlayerhide" id="errorlayer">
	</div>
	
	<logic:present name="RegenerateSpecForm" property="orderList">
		<bean:size id="ordListLen" name="RegenerateSpecForm" property="orderList" />
	</logic:present>

	<logic:match name="RegenerateSpecForm" property="method" value="fetchOrders">
		<logic:lessThan name="ordListLen" value="1">
			<script>
			//Updated for CR-121 server side error message
			fnNoRecords();
			</script>
		</logic:lessThan>
	</logic:match>

	<logic:present name="RegenerateSpecForm" property="messageID"
			scope="request">
		  <%--Added for CR-121 for server side error message tooltip --%>	
		  <bean:define id="messageID" name="RegenerateSpecForm" property="messageID"/>
		  <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
	      <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
	</logic:present>
	
	<div class="spacer20"></div>
	
	<div class="row">
			<div class="col-sm-12 text-center">
				<div class="form-group">
					<label class="control-label">Specification Type</label>
					
					<html:select name="RegenerateSpecForm" property="spectypeSeqno" styleClass="form-control"
						styleId="sltSpecType" onchange="javascript:fnLoadModels()">
						<option selected value="-1">---Select---</option>
						<html:optionsCollection name="RegenerateSpecForm"
							property="specTypeList" value="spectypeSeqno" label="spectypename" />
					</html:select>
						<span class="alertmsg"></span>	
				</div>
				
				<div class="form-group">
					<label class="control-label">Model</label>
						<html:select name="RegenerateSpecForm" styleClass="form-control"
							property="modelSeqNo" styleId="sltModel">
							<option selected value="-1">---Select---</option>
							<logic:present name="RegenerateSpecForm" property="modelList">
								<html:optionsCollection name="RegenerateSpecForm" property="modelList"
									value="modelSeqNo" label="modelName" />
							</logic:present>
						</html:select>
						<span class="alertmsg"></span>
				</div>
				
				<div class="form-group">
					<label class=" control-label">Customer</label>
						<html:select 
							name="RegenerateSpecForm" property="custSeqNo" styleClass="form-control"
							styleId="sltCustomer" >
							<option selected value="-1">---Select---</option>
							<logic:present name="RegenerateSpecForm" property="customerList">
								<html:optionsCollection name="RegenerateSpecForm"
									property="customerList" value="customerSeqNo" label="customerName" />
							</logic:present>
						</html:select>
						<span class="alertmsg"></span>
				</div>
			
				<div class="form-group required-field">
					<label class="control-label">Order Number</label>
						<html:text property="orderNo" styleClass="form-control" size="10" styleId="orderNo"
							maxlength="15" onkeypress="return keyHandler()" />
				</div>
			</div>
		</div>

	<div class="row">
		<div class="spacer30"></div>
	</div>
	
	<div class="row">
		<div class="col-sm-12 text-center">
	              <button class="btn btn-primary addbtn" type='button' id="searchButton" ONCLICK="javascript:fnSearchOrders()">Search</button>
        </div>
   	</div>
    
	<logic:greaterThan name="ordListLen" value="0">
		<HR>
		<div class="row">
			<div class="col-sm-11 text-center"><h5 class="text-center"><strong>Search critera</strong></h5></div>
		</div>
		<div class="row">
			<div class="spacer10"></div>
		</div>
		<div class="row">
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong><bean:message
						key="modifySpecSpecification" /></strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<logic:notEqual name="RegenerateSpecForm" property="spectypeSeqno" value="-1">
					<bean:write name="RegenerateSpecForm" property="hdnSelSpecType"/>
				</logic:notEqual>
				<logic:equal name="RegenerateSpecForm" property="spectypeSeqno" value="-1">
					&nbsp;
				</logic:equal>
			</div>
		</div>
		<div class="row">
			<div class="spacer10"></div>
		</div>
		<div class="row">	  
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong><bean:message
						key="modifySpecModel" /></strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<logic:notEqual name="RegenerateSpecForm" property="modelSeqNo" value="-1">
					<bean:write name="RegenerateSpecForm" property="hdnSelModel" />
				</logic:notEqual>
				<logic:equal name="RegenerateSpecForm" property="modelSeqNo" value="-1">
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
				<logic:notEqual name="RegenerateSpecForm" property="custSeqNo" value="-1">
					<bean:write name="RegenerateSpecForm" property="hdnSelectedCustomers" />
				</logic:notEqual>
				<logic:equal name="RegenerateSpecForm" property="custSeqNo" value="-1">
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
				<bean:write name="RegenerateSpecForm" property="orderNo" />
			</div>
		</div>
				
		<div class="spacer20"></div>				
		
		<TABLE class="table table-bordered table-hover" id="tbRegenSpec">
			<thead>
				<TR>
					<TH WIDTH="5%" CLASS='text-center'>Select</TH>
					<TH WIDTH="10%" CLASS='text-center'>Order Number</TH>
					<TH WIDTH="10%" CLASS='text-center'>Current Spec Status</TH>
					<TH WIDTH="10%" CLASS='text-center'>Customer</TH>
					<TH WIDTH="10%" CLASS='text-center'>Model</TH>
					<TH WIDTH="15%" CLASS='text-center'>Prior Spec Status</TH>
				</TR>
			</thead>
			
			<tbody>
				<logic:iterate id="ordListId" name="RegenerateSpecForm"
					property="orderList" type="com.EMD.LSDB.vo.common.OrderVO">
					<TR>
						<TD  WIDTH="2%" CLASS='text-center v-midalign'>
							<INPUT TYPE="radio" name="fromOrderKey" value="<%=ordListId.getOrderKey()%>" class="radcheck">
						</TD>	
						<TD  WIDTH="13%" CLASS='text-center v-midalign'>
							<bean:write name="ordListId" property="orderNo" />
						</TD>	
						<TD WIDTH="20%" CLASS='text-center v-midalign'><bean:write name="ordListId"
							property="statusDesc" />
						</TD>
						<logic:iterate id="cusListId" name="ordListId"
						property="customerVo">
							<TD WIDTH="25%" CLASS='text-center v-midalign'><bean:write name="cusListId"
							property="customerName" /></TD>
						</logic:iterate>
						<TD WIDTH="22%" CLASS='text-center v-midalign'><bean:write
							name="ordListId" property="modelName" /></TD>
						<TD WIDTH="43%" CLASS='text-center v-midalign' align="left">
						<html:select name="RegenerateSpecForm"
							property="toOrderkey" styleClass="form-control"
							styleId="sltSpecType" style="min-width: 90%;">
							<option selected value="-1">---Select---</option>
							<logic:present name="ordListId"  property="ssOrderKeys">
								<html:optionsCollection name="ordListId" property="ssOrderKeys"
									value="orderKey" label="statusDesc" />
							</logic:present>
							<option value="<%=ordListId.getOrderKey()%>"><bean:write name="ordListId"
								property="statusDesc" /></option>
						</html:select>
						</TD>
					</TR>
				</logic:iterate>
			</tbody>
		</TABLE>
		
		<div class="spacer10"></div>
				
		<div class="row">
				<div class="col-sm-12 text-center">
					<button class="btn btn-primary" type='button' id="btnRegenerateSpec" ONCLICK="javascript:regenerateSpec()">Regenerate Spec for PDFs</button>
				</div>
	 	</div>
		
	</logic:greaterThan>
	
	<html:hidden property="hdnSelectedCustomers" />
	<html:hidden property="hdnSelSpecType" />
	<html:hidden property="hdnSelModel" />
	<html:hidden property="hdnorderNo" />
	<html:hidden property="hdnToOrderkey"/>
	<input type="hidden" name="flag" value="Y">

</html:form>
<div class="spacer50"></div>
</div>




