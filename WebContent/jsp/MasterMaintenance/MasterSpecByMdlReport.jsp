<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<SCRIPT type="text/JavaScript" SRC="js/MasterSpecByMdl.js"></SCRIPT>

<script>
    $(document).ready(function() { 
    	$("#selectSpecType").select2({theme:'bootstrap'}); 
    	$("#selectModel").select2({theme:'bootstrap'}); 
    })
</script>

<div class = "container" width="100">

<html:form action="/MasterSpecByMdlAction" method="post" styleClass="form-inline">

	<div class="spacer10"></div>

	<h4 class ="text-center">
		<bean:message key="Application.Screen.MasterSpecByModelReport" />
	</h4>
	
	<p class="text-center"><mark>Press SHIFT/CTRL for Selecting multiple Customers.</mark></p>

	<div class="spacer30"></div>

	<div class="errorlayerhide" id="errorlayer">
	</div>
				
	<logic:present name="MasterSpecByMdlForm" property="orderList">
		<bean:size id="ordListLen" name="MasterSpecByMdlForm"
			property="orderList" />
	</logic:present>

	<logic:present name="MasterSpecByMdlForm" property="messageID"
		scope="request">
		
	<bean:define id="messageID" name="MasterSpecByMdlForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
	</logic:present>

	<logic:match name="MasterSpecByMdlForm" property="method"
		value="fetchOrders">
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
					name="MasterSpecByMdlForm" property="specTypeNo" styleId="selectSpecType"
					onchange="javascript:fnLoadModelsAndCustomers()"
					styleClass="form-control">
					<option selected value="-1">---Select---</option>
					<html:optionsCollection name="MasterSpecByMdlForm"
						property="specTypeList" value="spectypeSeqno" label="spectypename" />
				</html:select>
			</div>
			
			<div class="form-group">
				&nbsp;
			</div>
			
			<div class="form-group required-field">
				<label class="control-label">Model</label>
					<html:select
						name="MasterSpecByMdlForm" property="modelSeqNo" styleId="selectModel"
						styleClass="form-control">
						<option selected value="-1">---Select---</option>
						<logic:present name="MasterSpecByMdlForm" property="modelList">
							<html:optionsCollection name="MasterSpecByMdlForm"
								property="modelList" value="modelSeqNo" label="modelName" />
						</logic:present>
					</html:select>
			</div>
			
			<div class="form-group">
				&nbsp;
			</div>
			
			<div class="form-group">
				<label class="control-label">Customer</label>
					<html:select style="width:170px"
						name="MasterSpecByMdlForm" property="custSeqNos" size="6"
						styleClass="form-control" multiple="true">
						<logic:present name="MasterSpecByMdlForm" property="customerList">
							<html:optionsCollection name="MasterSpecByMdlForm"
								property="customerList" value="customerSeqNo" label="customerName" />
						</logic:present>
					</html:select>
			</div>
			
			<div class="form-group">
				&nbsp;
			</div>
			
			<div class="form-group">
	              <button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnSearchOrders()">Search</button>
	       </div>
		</div>
	</div>
	
	<div class="spacer30"></div>

	<div class="row">
		<div class="col-sm-12 text-center">
			<div class="form-group">
              <button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnViewSpec(1,'report')">View Spec By Model</button>
              <button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnViewSpec(1,'excel')">Export 'Spec By Model' To Excel</button>
              <button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnViewSpec(1,'DOORS')">Export 'Spec By Model' To Excel (DOORS)</button>
	       </div>
		</div>
	</div>
	
	<div class="spacer20"></div>
	
	<div class="row">
		<div class="col-sm-12 text-center">
			<div class="form-group">
              <button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnViewReport(1,'chrgrppt')">View Characteristic Group Report</button>
              <button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnViewReport(1,'excel')">Export 'Characteristic Group Report' To Excel</button>
	       </div>
		</div>
	</div>
	
	<div class="spacer20"></div>
	
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
					<bean:write name="MasterSpecByMdlForm"
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
					<bean:write name="MasterSpecByMdlForm"
							property="hdnSelectedMdlNames" />
				</div>
			</div>
			
			<div class="row">	
				<div class="col-sm-12">
					<div class="spacer10"></div>
				</div>
			</div>
				
			<div class="row">
				<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Customer(s)</strong></div>
				<div class="col-sm-1 text-center">:</div>
				<div class="col-sm-2 text-left">
					<bean:write name="MasterSpecByMdlForm"
								property="hdnSelectedCustomers" />
				</div>
			</div>
			
			<div class="row">	
				<div class="col-sm-12">
					<div class="spacer10"></div>
				</div>
			</div>
			
		<TABLE class="table table-bordered" id="tbMastrSpecByModel">
			<thead>
				<TR>
					<TH CLASS='text-center'>Select</TH>
					<TH CLASS='text-center'>Order Number</TH>
					<TH CLASS='text-center'>Spec Status</TH>
					<TH CLASS='text-center'>Customer Name</TH>
	
				</TR>
			</thead>
			<logic:iterate id="ordListId" name="MasterSpecByMdlForm"
				property="orderList" type="com.EMD.LSDB.vo.common.OrderVO">
				<tbody>
					<TR>
						<TD CLASS="text-center v-midalign" width="5%"><input type="checkbox"
							name="orderkey"
							value="<bean:write name="ordListId" property="orderKey"/>" />
							
						<TD CLASS="text-center v-midalign" width="15%">
							<a href="javascript:exportSpectoDoors(document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].text,document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.options.selectedIndex].value,document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text,'<bean:write name="ordListId" property="orderKey"/>','<bean:write name="ordListId" property="orderNo"/>','S')"
							title="Click to load Order Report for DOORs" class="vtip" >
								<bean:write name="ordListId" property="orderNo" />
							</a>
						</TD>
	
						<TD CLASS="text-center v-midalign" width="13%"><bean:write
							name="ordListId" property="statusDesc" /></TD>
							
						<logic:iterate id="cusListId" name="ordListId"
							property="customerVo">
							<TD CLASS="text-center v-midalign" width="15%"><bean:write
								name="cusListId" property="customerName" /></TD>
						</logic:iterate>
					</TR>
				</tbody>
			</logic:iterate>
		</TABLE>
		
		<div class="spacer20"></div>
		
		<div class="row">
			<div class="col-sm-12 text-center">
				<div class="form-group">
	              <button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnViewSpec(2,'report')">View Spec By Customer</button>
	              <button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnViewSpec(2,'excel')">Export 'Spec By Customer' to Excel</button>
		       </div>
			</div>
		</div>
		
	</logic:greaterThan>

	<html:hidden property="hdnSelectedMdlNames" />
	<html:hidden property="hdnSelectedCustomers" />
	<html:hidden property="customerSeq" />
	<html:hidden property="hnOrderKey" />
	<html:hidden property="hdnSelSpecType" />
	
	<%-- Added Changes for CR_100 to bring Header and Footer --%>
	
	<DIV ID='headerInstructions' style="display:none;">
		<center>
		<%--div class="dashBoardHeading">Header/Footer Instructions</div--%><br/>
		<%-- Changed the image as per Vadim's comment--%>
		<IMG SRC="images/COC.jpg" height="535" width="450" onclick="javascript:openCOC(document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].text,document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.options.selectedIndex].value,document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text);"/>
		<br/><input type="button" name="Close" class="buttonStyleClose" value="Close"/>
		</center>
	</DIV>

</html:form>

</div>








