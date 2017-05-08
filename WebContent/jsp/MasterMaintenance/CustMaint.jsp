<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT type="text/javascript" SRC="js/Customer.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/File.js"></SCRIPT>
<script>
       $(document).ready(function() { 
       		$("#sltSpecType").select2({theme:'bootstrap'});
       })
</script>
<div class="container" width="100%">
   <html:form  action="/CustAction" method="post" enctype="multipart/form-data">
 		<h4 class="text-center"><bean:message key="Application.Screen.CustomerMaintenance" /></h4>
   		<p class="text-center"><mark>NOTE: Large pictures may exceed page boundaries, Please limit images to 550 by 600 pixels.</mark></p>
		<div class="spacer10"></div>
		<div class="errorlayerhide" id="errorlayer">
		</div>
		<logic:present name="CustMaintForm" property="messageID" scope="request">
			<bean:define id="messageID" name="CustMaintForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
		</logic:present>
		
		<logic:present name="CustMaintForm" property="custList">
			<bean:size id="custListLen" name="CustMaintForm" property="custList" />
		</logic:present>
		
		<logic:match name="CustMaintForm" property="method" value="fetchCustomers">
			<logic:lessThan name="custListLen" value="1">
				<script> 
					fnNoRecords();
				</script>
			</logic:lessThan>
		</logic:match>
		<div class="spacer10"></div>
        <div class="form-horizontal">
			<div class="form-group required-field">
					<label class="col-sm-5 control-label">Specification Type</label>
					<div class="col-sm-3">
						<html:select styleClass="form-control" name="CustMaintForm" styleId="sltSpecType"
							property="specTypeNo" onchange="javascript:changeSpecType()">
							<option selected value="-1">---Select---</option>
							<html:optionsCollection name="CustMaintForm"
								property="specTypeList" value="spectypeSeqno" label="spectypename" />
						</html:select>
					</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-5 col-sm-offset-5">					
				    <logic:equal name="CustMaintForm" property="specTypeNo" value="2">			
					   	 <div class="col-sm-2">
				    		<label class="radio-inline">
								<html:radio name="CustMaintForm" property="custTypeSeqNo" value="1" disabled="true"/> Domestic   
							</label>
						</div>
						 <div class="col-sm-2">
							<label class="radio-inline">
								<html:radio name="CustMaintForm" property="custTypeSeqNo" value="2" disabled="true"/> Export 
							</label>
						</div>
							<button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnSearchCustomer()"><bean:message key="customer.Search" /></button>
					</logic:equal>
					<logic:notEqual name="CustMaintForm" property="specTypeNo" value="2">
						<div class="col-sm-2">
							<label class="radio-inline">
								<html:radio name="CustMaintForm" property="custTypeSeqNo" value="1"/> Domestic 
							</label>
						</div>
						<div class="col-sm-2">
							<label class="radio-inline">
								<html:radio name="CustMaintForm" property="custTypeSeqNo" value="2"/> Export 
							</label>
						</div>
						<button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnSearchCustomer()"><bean:message key="customer.Search" /></button>
					</logic:notEqual>
				</div>			
			</div>
			
			<div class="form-group required-field">			
				<label class="col-sm-5 control-label"><bean:message key="customername" /></label>
				<div class="col-sm-3">					
					<html:text styleClass="form-control" property="custName" styleId="custName"/>
				</div>
			</div>
			
			<div class="form-group">			
				<label class="col-sm-5 control-label"><bean:message key="customerdesc" /></label>
				<div class="col-sm-3">					
					<html:textarea styleClass="form-control" property="custDesc" styleId="custDesc" rows="2" cols="48" />
				</div>
			</div>
			
			<div class="form-group">			
				<label class="col-sm-5 control-label">Customer Logo</label>
				<div class="col-sm-3">					
					<div class="input-group">
							<input type="text" class="form-control input-file-readonly" readonly/>
			                <span class="input-group-btn">
			                    <span class="btn btn-primary btn-file">
			                        Browse&hellip; <html:file property="theFile" styleClass="form-control" styleId="theFile" onchange="javascript:validateFile(this)"/>
			                    </span>
			                </span>		                
		           	</div> 
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-4">
					<button class="btn btn-primary" type='button' id="addButton" ONCLICK="javascript:fnAddCustomer()"><bean:message key="customer.Add" /></button>
				</div>
			</div>
		</div>
		
		<logic:greaterThan name="custListLen" value="0">		
			
				<hr>		
				<div class="row">
				<div class="col-sm-11 text-center"><h5 class="text-center"><strong>Search critera</strong></h5></div>
				</div>
				<div class="spacer10"></div>
				<div class="row">
					<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Specification Type</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="CustMaintForm" property="specTypeNo" value="-1">
							<bean:write name="CustMaintForm" property="hdSelectedSpecType"/>
						</logic:notEqual>
						<logic:equal name="CustMaintForm" property="specTypeNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
			   </div>		
			   <div class="spacer10"></div>
			   <div class="row">
				   <logic:equal name="CustMaintForm" property="custTypeSeqNo" value="1">
				   		<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Domestic/Export</strong></div>
						<div class="col-sm-1 text-center">:</div>
						<div class="col-sm-2 text-left">Domestic</div>
	   			   </logic:equal>
					<logic:equal name="CustMaintForm" property="custTypeSeqNo" value="2">
						<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Domestic/Export</strong></div>
						<div class="col-sm-1 text-center">:</div>
						<div class="col-sm-2 text-left">Export</div>
					</logic:equal>
			   </div>
			   <div class="spacer10"></div>
			   <table class="table table-bordered table-hover">
				 <thead>
				   <tr>
				     	<th>Select</th>
						<th>Customer</th>
						<th>Comments</th>
						<th>Logo</th>
						<th>Logo Updated By</th>
						<th>Logo Updated Date</th>
				   </tr>
				 </thead>
				  <tbody class="text-center">	
				  		<logic:iterate id="CustDetails" name="CustMaintForm"
						property="custList" scope="request"
						type="com.EMD.LSDB.vo.common.CustomerVO" indexId="count">
							<bean:define id="check"
							value='<%=String.valueOf(CustDetails.getFileVO().getImageSeqNo())%>' />
							<tr>
								<TD class="v-midalign"><html:radio
								value="<%=String.valueOf(CustDetails.getCustomerSeqNo()) %>"
								property="customerSeqNo" /></TD>
								<TD class="v-midalign"><html:text name="CustDetails"
								property="customerName" styleClass="form-control"
								size="46" maxlength="100" /></TD>
								<TD class="v-midalign"><html:textarea styleClass="form-control"
								name="CustDetails" property="customerDesc" rows="2" cols="48"/></TD>
								<logic:equal name="CustDetails" property="imageSeqNo" value="0">
								<TD class="v-midalign">
								&nbsp;
								</TD>
								</logic:equal>
								<logic:notEqual name="CustDetails" property="imageSeqNo" value="0">
								<TD class="v-midalign">
								<input type="hidden" name="imageSeqNo" id="imageSeqNo<%=count%>"  value="<%=CustDetails.getImageSeqNo()%>"/>
								<A href="javascript:fnShowImage('<%=CustDetails.getFileVO().getImageSeqNo()%>','<%=CustDetails.getCustomerName()%>')"
										Class="vtip" title="Logo">
										
										 <IMG SRC="images/comp.gif" BORDER="0"> 
									</A>
								</TD>
								</logic:notEqual>
								
								<TD class="v-midalign">
								<bean:write name="CustDetails" property="firstName"/> <bean:write name="CustDetails" property="lastName"/>&nbsp;
								</TD>
								
								
								<TD class="v-midalign"><bean:write name="CustDetails" property="logoUpdatedDate"/>&nbsp;
								</TD>
							 </tr>
						</logic:iterate>
				  </tbody>
			   </table>	 
			   	<div class="spacer10"></div>
				<div class="form-group">
					<div class="col-sm-2 col-sm-offset-5">
					     <div class="input-group">
							<input type="text" class="form-control input-file-readonly" readonly/>
			                <span class="input-group-btn">
			                    <span class="btn btn-primary btn-file">
			                        Browse&hellip; <html:file property="theModifyFile" styleClass="form-control" styleId="theModifyFile" onchange="javascript:validateFile(this)"/>
			                    </span>
			                </span>		                
		           		</div> 
					</div>   
			   </div> 
			   <div class="spacer30"></div> 
			   <div class="form-group">
					<div class="col-sm-12 text-center">
					      <button class="btn btn-primary mdfybtn" type='button' id="searchButton" ONCLICK="javascript:fnModifyCustomer()"><bean:message key="customer.Modify" /></button>
					 </div>   
			   </div> 

		</logic:greaterThan>	
			<!-- Logic Tag Check For Display The Records After Add and Modify Functionality Ends Here -->
	
		<!-- Hidden fields to hold modified customer name and their descriptions starts-->
		<html:hidden property="filePath" />
		<html:hidden property="hdnImageSeqNo" />
		<html:hidden property="hdncustName" />
		<html:hidden property="hdncustDesc" />
		<html:hidden property="hdncustomertypeseqNo" />
		<html:hidden property="hdSelectedSpecType" />
		<html:hidden property="hdPreSelectedSpecType" />
	
		<!-- Hidden variables hdSelectedSpecType and hdPreSelectedSpecType 
					are added based on LSDB_CR-46 on 28-Aug-08 by ps57222
				-->
		<!-- Hidden fields to hold modified customer name and their descriptions ends -->	
</html:form>
<div class="spacer50"></div>
</div>			