<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<SCRIPT type="text/JavaScript" SRC="js/1058LegacyRequest.js"></SCRIPT>    	
<script>
	 $(document).ready(function() { 
	       	$("#1058Status").select2({theme:'bootstrap'});
	       	$("#legacyIssuedBy").select2({theme:'bootstrap'});
	 })
</script>
<div class = "container-fluid">

<html:form action="/SearchRequest1058Action" method="post" enctype="multipart/form-data" styleClass="form-horizontal">
	
	<h4 class ="text-center">
		<logic:present name="SearchRequest1058Form" property="editLegacyList">
			<bean:message key="Application.Screen.UpdateLegacy1058Request" />
		</logic:present>
		<logic:notPresent name="SearchRequest1058Form" property="editLegacyList">
			<bean:message key="Application.Screen.Legacy1058Request" />
		</logic:notPresent>
	</h4>

	<div class="spacer10"></div>

	<p class="text-center"><mark><bean:message key="Application.Screen.LegacyMessage" /></mark></p>
				
	<div class="errorlayerhide" id="errorlayer">
	</div>

	<logic:present name="SearchRequest1058Form" property="messageID" scope="request">
	  <%--Added for CR-121 for server side error message tooltip --%>	
	  <bean:define id="messageID" name="SearchRequest1058Form" property="messageID"/>
	  <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
      <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
	</logic:present>
	
	<logic:notPresent name="SearchRequest1058Form" property="editLegacyList">
		<div>
			<div class="spacer20"></div>
			
			<div class="form-group required-field">
				<label class="col-sm-5 control-label">Order Number</label>
				<div class="col-sm-2">
					<html:text property="orderNo" styleClass="form-control" maxlength="100" tabindex="1" />
				</div>
			</div>
			
			<div class="form-group required-field">
				<label class="col-sm-5 control-label">1058 Number</label>
				<div class="col-sm-2">
					<html:text property="number1058" styleClass="form-control" maxlength="100" tabindex="2" />
				</div>
			</div>
			
			<div class="form-group required-field">
				<label class="col-sm-5 control-label">Customer</label>
				<div class="col-sm-2">
					<html:text property="customer" styleClass="form-control" maxlength="100" tabindex="3" />
				</div>
			</div>
			
			<div class="form-group required-field">
				<label class="col-sm-5 control-label">Model</label>
				<div class="col-sm-2">
					<html:text property="model" styleClass="form-control" maxlength="100" tabindex="4" />
				</div>
			</div>
			
			<div class="form-group required-field">
				<label class="col-sm-5 control-label">Status</label>
				<div class="col-sm-2">
					<html:select name="SearchRequest1058Form" property="status" styleId="1058Status">
						<option selected value="-1">---Select---</option>
						<logic:iterate id="status" name="SearchRequest1058Form" property="statusList" >
							<logic:equal name="status" property="statusSeqNo"  value="5">
									<option value="<bean:write name="status" property="statusSeqNo" />">
											<bean:write name="status" property="statusDesc" />
									</option>
							</logic:equal>
							<logic:equal name="status" property="statusSeqNo"  value="4">
									<option value="<bean:write name="status" property="statusSeqNo" />">
											<bean:write name="status" property="statusDesc" />
									</option>
							</logic:equal>			
							<logic:equal name="status" property="statusSeqNo"  value="10">
									<option value="<bean:write name="status" property="statusSeqNo" />">
										<bean:write name="status" property="statusDesc" />
									</option>
							</logic:equal>		
						</logic:iterate>
					</html:select>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-5 control-label">Specification Revision</label>
				<div class="col-sm-2">
					<html:text property="specRev" styleClass="form-control" maxlength="100" tabindex="6" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-5 control-label">Issued By</label>
				<div class="col-sm-2">
					 <html:select name="SearchRequest1058Form" property="issuedBy"
						styleClass="form-control" styleId="legacyIssuedBy">
						<option selected value="-1">---Select---</option>
						<logic:iterate id="issuedBy" name="SearchRequest1058Form" property="issuedByUserList" >
							<option value="<bean:write name="issuedBy" property="userID" />">
							<bean:write name="issuedBy" property="lastName" /> - <bean:write name="issuedBy" property="userID" />
						</option>
						</logic:iterate>
					</html:select>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-5 control-label">General Description</label>
				<div class="col-sm-2">
					<html:textarea rows ="5" property="genDesc" styleClass="form-control" onkeyup="fnSetMaxSize(this, 3999);" tabindex="7" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-5 control-label">Actual Sell Price(USD)</label>
				<div class="col-sm-2">
					<div class="input-group">	
						<span class="input-group-addon">$</span>
						<html:text property="actualSellPrice" styleClass="form-control" maxlength="100" tabindex="7" />
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-5 control-label">Est.Design Hrs</label>
				<div class="col-sm-2">
					<html:text property="designHrs" styleClass="form-control" maxlength="100" tabindex="8" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-5 control-label">Est.Drafting Hrs</label>
				<div class="col-sm-2">
					<html:text property="draftingHrs" styleClass="form-control" maxlength="100" tabindex="9" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-5 control-label">Work Order(USD)</label>
				<div class="col-sm-2">
					<div class="input-group">
						<span class="input-group-addon">$</span>
						<html:text property="workOrderUSD" styleClass="form-control" maxlength="100" tabindex="10" />
					</div>
				</div>
			</div>
			
			<div class="form-group required-field">
				<label class="col-sm-5 control-label">1058 Report</label>
				<div class="col-sm-2">
					<div class="input-group">
						<input type="text" class="form-control input-file-readonly" id="uploadAtch" readonly/>
		                <span class="input-group-btn">
		                    <span class="btn btn-primary btn-file">
		                        Browse&hellip; <html:file property="uploadAttachment" styleClass="form-control" styleId="uploadAttachment"/>
		                    </span>
		                </span>		                
	            	</div> 
				</div>
			</div>
			
			<div class="spacer20"></div>
		</div>
	
		<div class="form-group">
			<div class="col-sm-12 text-center">
				<button class="btn btn-primary" type='button' id="createButton" ONCLICK="javascript:insertLegacyRequest()">Submit</button>
			</div>
		</div>
	</logic:notPresent>	
	<!--  TABLE1 Ends -->
	<%--logic present tage added for CR-126 --%>
	<logic:present name="SearchRequest1058Form" property="editLegacyList">
	 	<logic:iterate id="updateLegacy" name="SearchRequest1058Form" property="editLegacyList"
				scope="request" type="com.EMD.LSDB.vo.common.ChangeRequest1058VO">
			<div>
				<div class="spacer20"></div>
				
				<div class="form-group required-field">
					<label class="col-sm-5 control-label">Order Number</label>
					<div class="col-sm-2">
						<html:text property="orderNo" name="updateLegacy" styleClass="form-control" maxlength="100" tabindex="1" />
					</div>
				</div>
			
				<div class="form-group required-field">
					<label class="col-sm-5 control-label">1058 Number</label>
					<div class="col-sm-2">
						<html:text property="number1058" name="updateLegacy" styleClass="form-control" maxlength="100" tabindex="2" />
					</div>
				</div>
			
				<div class="form-group required-field">
					<label class="col-sm-5 control-label">Customer</label>
					<div class="col-sm-2">
						<html:text property="customer" name="updateLegacy" styleClass="form-control" maxlength="100" tabindex="3" />
					</div>
				</div>
			
				<div class="form-group required-field">
					<label class="col-sm-5 control-label">Model</label>
					<div class="col-sm-2">
						<html:text property="model" name="updateLegacy" styleClass="form-control" maxlength="100" tabindex="4" />
					</div>
				</div>
			
				<div class="form-group required-field">
					<label class="col-sm-5 control-label">Status</label>
					<div class="col-sm-2">
						<html:select name="SearchRequest1058Form" property="status" 
							styleClass="form-control" styleId="1058Status">
							<logic:iterate id="status" name="SearchRequest1058Form" property="statusList" >
								<logic:equal name="status" property="statusSeqNo"  value="5">
			     					<logic:equal name="updateLegacy" property="statusSeqNo" value="5">
										<option selected="selected" value="<bean:write name="updateLegacy" property="statusSeqNo" />" >
											<bean:write name="updateLegacy" property="statusDesc" />
										</option>
									</logic:equal>
									<logic:notEqual name="updateLegacy" property="statusSeqNo" value="5">	
										<option value="<bean:write name="status" property="statusSeqNo" />" >
											<bean:write name="status" property="statusDesc" />
										</option>
									</logic:notEqual>		
								</logic:equal>
								<logic:equal name="status" property="statusSeqNo"  value="4">
									<logic:equal name="updateLegacy" property="statusSeqNo" value="4">
										<option selected="selected" value="<bean:write name="updateLegacy" property="statusSeqNo" />" >
											<bean:write name="updateLegacy" property="statusDesc" />
										</option>
									</logic:equal>
									<logic:notEqual name="updateLegacy" property="statusSeqNo" value="4">
										<option value="<bean:write name="status" property="statusSeqNo" />">
											<bean:write name="status" property="statusDesc" />
										</option>
									</logic:notEqual>	
								</logic:equal>			
								<logic:equal name="status" property="statusSeqNo"  value="10">
									<logic:equal name="updateLegacy" property="statusSeqNo" value="10">
										<option selected="selected" value="<bean:write name="updateLegacy" property="statusSeqNo" />" >
											<bean:write name="updateLegacy" property="statusDesc" />
										</option>
									</logic:equal>
			     					<logic:notEqual name="updateLegacy" property="statusSeqNo" value="10">
										<option value="<bean:write name="status" property="statusSeqNo" />">
											<bean:write name="status" property="statusDesc" />
										</option>
									</logic:notEqual>	
								</logic:equal>
							</logic:iterate>
						</html:select>
					</div>
				</div>
			
				<div class="form-group">
					<label class="col-sm-5 control-label">Specification Revision</label>
					<div class="col-sm-2">
						<html:text property="specRev" name="updateLegacy" styleClass="form-control" maxlength="100" tabindex="6" />
					</div>
				</div>
			
				<div class="form-group">
					<label class="col-sm-5 control-label">Issued By</label>
					<div class="col-sm-2">
						 <html:select name="SearchRequest1058Form" property="issuedBy"
							styleClass="form-control" styleId="legacyIssuedBy">
							<option selected value="-1">---Select---</option>
							<logic:iterate id="issuedBy" name="SearchRequest1058Form" property="issuedByUserList" >
								<logic:equal name="issuedBy" property="userID" value="<%=String.valueOf(updateLegacy.getUserID())%>">
									<option selected="selected" value="<bean:write name="issuedBy" property="userID" />">
										<bean:write name="issuedBy" property="lastName" /> - <bean:write name="issuedBy" property="userID" />
									</option>
								</logic:equal>
								<logic:notEqual name="issuedBy" property="userID" value="<%=String.valueOf(updateLegacy.getUserID())%>">
									<option value="<bean:write name="issuedBy" property="userID" />">
										<bean:write name="issuedBy" property="lastName" /> - <bean:write name="issuedBy" property="userID" />
									</option>
								</logic:notEqual>
							</logic:iterate>
						</html:select>
					</div>
				</div>
			
				<div class="form-group">
					<label class="col-sm-5 control-label">General Description</label>
					<div class="col-sm-2">
						<html:textarea rows ="5" property="genDesc" name="updateLegacy" styleClass="form-control" onkeyup="fnSetMaxSize(this, 3999);" tabindex="7" />
					</div>
				</div>
			
				<div class="form-group">
					<label class="col-sm-5 control-label">Actual Sell Price(USD)</label>
					<div class="col-sm-2">
						<div class="input-group">	
							<span class="input-group-addon">$</span>
							<html:text property="actualSellPrice" name="updateLegacy" styleClass="form-control" maxlength="100" tabindex="7" />
						</div>
					</div>
				</div>
			
				<div class="form-group">
					<label class="col-sm-5 control-label">Est.Design Hrs</label>
					<div class="col-sm-2">
						<html:text property="designHrs" name="updateLegacy" styleClass="form-control" maxlength="100" tabindex="8" />
					</div>
				</div>
			
				<div class="form-group">
					<label class="col-sm-5 control-label">Est.Drafting Hrs</label>
					<div class="col-sm-2">
						<html:text property="draftingHrs" name="updateLegacy" styleClass="form-control" maxlength="100" tabindex="9" />
					</div>
				</div>
			
				<div class="form-group">
					<label class="col-sm-5 control-label">Work Order(USD)</label>
					<div class="col-sm-2">
						<div class="input-group">
							<span class="input-group-addon">$</span>
							<html:text property="workOrderUSD" name="updateLegacy" styleClass="form-control" maxlength="100" tabindex="10" />
						</div>
					</div>
				</div>
			
				<div class="form-group">
					<label class="col-sm-5 control-label">Existing File Path:</label>
					<div class="col-sm-2 form-control-static">
						<bean:write name="updateLegacy" property="lagacyFileLoc" />
					</div>
				</div>
			
				<div class="form-group">
					<label class="col-sm-5 control-label">New file to upload</label>
					<div class="col-sm-2">
						<div class="input-group">
							<input type="text" class="form-control input-file-readonly" readonly/>
			                <span class="input-group-btn">
			                    <span class="btn btn-primary btn-file">
			                        Browse&hellip; <html:file property="uploadAttachment" styleClass="form-control" styleId="uploadAttachment"/>
			                    </span>
			                </span>		                
		            	</div> 
					</div>
				</div>
			
				<div class="spacer20"></div>	
			</div>
  		</logic:iterate>	
  	
	 	<div class="form-group">
			<div class="col-sm-12 text-center">
				<button class="btn btn-primary" type='button' id="updateButton" ONCLICK="javascript:updateLegacyRequest()">Update</button>
				<button class="btn btn-primary" type='button' id="returntoSearch" ONCLICK="javascript:return1058Search(this)">Return to search</button>
			</div>
		</div>
	</logic:present>
	
   	<html:hidden property="editLegacyFlag" />
   	<html:hidden property="hdnSelectedCustomer" />
	<html:hidden property="hdnSelectedStatus" />
	<html:hidden property="hdnSelectedModel" />
	<html:hidden property="hdnSelectedAwApproval" />
	<html:hidden property="hdnorderNo" />
	<html:hidden property="role" styleId="role"/>
	<%-- Added for CR-126 ends here--%>
	<input type="hidden" name="flag" value="Y">
	<html:hidden property="hdnStatus" />
	<html:hidden property="hdnIssuedBy" />
</html:form>
<div class="spacer50"></div>
</div>
