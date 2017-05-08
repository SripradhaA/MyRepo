<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT type="text/javascript" SRC="js/Model.js"></SCRIPT>
<script>
       $(document).ready(function() { 
       	$("#sltSpecType").select2({theme:'bootstrap'});
       	$("#sltToSpecType").select2({theme:'bootstrap'});
	    $('#tbCopyModel').DataTable({
	   		 searching: false,
	    	 paging:   false,
	         ordering: false,
	         info:     false,
	    	 scrollY: 265
	    }); 
       })
</script>
<div class="container" width="100%">
   <html:form  action="/CopyModelAction" method="post">
	   <h4 class="text-center"><bean:message key="Application.Screen.CopyModel" /></h4>
	   <div class="spacer10"></div>
		<div class="errorlayerhide" id="errorlayer">
		</div>
		<logic:present name="ModelMaintForm" property="messageID" scope="request">
			<bean:define id="messageID" name="ModelMaintForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
		</logic:present>
		
		<logic:present name="ModelMaintForm" property="modelList">
			<bean:size id="modlen" name="ModelMaintForm" property="modelList" />
		</logic:present>
		
		<logic:match name="ModelMaintForm" property="method" value="fetchModels">
			<logic:lessThan name="modlen" value="1">
				<script> 
					fnNoRecords();
				</script>
			</logic:lessThan>
		</logic:match>
		<div class="spacer10"></div>
		<div class="form-horizontal">
			<div class="form-group required-field">
				<label class="col-sm-5 control-label">Specification Type</label>
				<div class="col-sm-3 form-inline">
					<html:select name="ModelMaintForm" styleId="sltSpecType"
						property="specTypeNo" styleClass="form-control" >
						<option selected value="-1">---Select---</option>
						<html:optionsCollection name="ModelMaintForm"
							property="specTypeList" value="spectypeSeqno" label="spectypename" />
					</html:select>
					<button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnFetchModel()"><bean:message key="model.fetch" /></button>
				</div>	
		</div>
		<logic:greaterThan name="modlen" value="0">		
			
				<hr>		
				<div class="row">
				<div class="col-sm-11 text-center"><h5 class="text-center"><strong>Search critera</strong></h5></div>
				</div>
				
					<div class="spacer10"></div>
				
				<div class="row">
					<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Specification Type</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="ModelMaintForm" property="specTypeNo" value="-1">
							<bean:write name="ModelMaintForm" property="hdnSpecTypeName"/>
						</logic:notEqual>
						<logic:equal name="ModelMaintForm" property="specTypeNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				<div class="spacer10"></div>
		<div class="row">
			<div class="col-sm-6">
			  <fieldset class="scheduler-border">
    			<legend class="scheduler-border">From Model</legend>
					<div class="form-group">
						<table class="table table-bordered table-hover" id="tbCopyModel">
						    <thead>
						        <tr>
							        <TH>Model</TH>
									<TH>Horse Power Rating</TH>
									<TH>Select</TH>
						        </tr>
	     				    </thead>
						    <tbody class="text-center">	
							    	<logic:iterate id="objmodel" name="ModelMaintForm"
										property="modelList" type="com.EMD.LSDB.vo.common.ModelVo"
										scope="request">
										<html:hidden name="objmodel" property="specTypeSeqNo" />
										
										<TR>
											<TD class="v-midalign"><bean:write name="objmodel"
												property="modelName" 
												 /></TD>
											<TD class="v-midalign"><bean:write name="objmodel"
												property="hpowerRate" 
												 /></TD>
												 
											<TD class="v-midalign">
											   <html:radio value="<%=String.valueOf(objmodel.getModelSeqNo())%>"
												property="modelSeqNo" /></TD>
										</TR>
								</logic:iterate>
						    </tbody>
						 </table>
						</div> 
				 </fieldset>
		    </div>
			 <div class="col-sm-6">
  				<fieldset class="scheduler-border">
    			<legend class="scheduler-border">To Model</legend>
    			
    			<div class="form-horizontal">
    					<div class="form-group required-field">
								<label class="col-sm-5 control-label">Specification Type</label>
								<div class="col-sm-5">
									<html:select styleClass="form-control" name="ModelMaintForm" property="toModelSpecTypeNo" styleId="sltToSpecType">
										<%--option selected value="-1">---Select---</option--%>
										<html:optionsCollection name="ModelMaintForm" property="specTypeList" value="spectypeSeqno" label="spectypename" />
									</html:select>
								</div>
						</div>
						
						<div class="form-group required-field">			
							<label class="col-sm-5 control-label">Model</label>
							<div class="col-sm-5">					
								<html:text styleClass="form-control" property="modName" styleId="modName"/>
								<span class="alertmsg"></span>
							</div>
						</div>
						
						<div class="form-group required-field">			
							<label class="col-sm-5 control-label">Horse Power Rating</label>
							<div class="col-sm-5">					
								<html:textarea styleClass="form-control" property="horsePower" styleId="horsePower" rows="2" cols="48" />
								<span class="alertmsg"></span>
							</div>
						</div>
						
						<div class="form-group">			
							<label class="col-sm-5 control-label">Comments</label>
							<div class="col-sm-5">					
								<html:textarea styleClass="form-control" property="modDesc" styleId="modDesc" rows="2" cols="48" />
							</div>
						</div>
						
						<div class="form-group required-field">			
							<label class="col-sm-5 control-label">Change Control Type</label>
								<div class="col-sm-6">		
									<div class="col-sm-2">				
										<label class="radio-inline"><input type="radio" name="copyChangeControl" id="copyChangeControl1" value="Y"> On</label>
									</div>
									<div class="col-sm-2">		
										<label class="radio-inline"><input type="radio" name="copyChangeControl" id="copyChangeControl2" value="N" checked> Off</label>
									</div>
								</div>
						</div>
						<div class="spacer90"></div>
					</div>	
				</fieldset>	
			</div>
		</div>
		<div class="spacer10"></div>
		<div class="row">
			<div class="form-group">
			         <div class="col-sm-12 text-center">
			            <button class="btn btn-primary mdfybtn" type='button' id="btnCopyModel" ONCLICK="javascript:fnCopyModel()">Copy Model</button>
			         </div>
			</div>
		</div>	
		</logic:greaterThan>
		<div class="spacer30"></div>
		<html:hidden property="hdnModelName" />
		<html:hidden property="hdnHorsePower" />
		<html:hidden property="hdnModelDesc" />
		<html:hidden property="hdnSpecTypeName" />
		<html:hidden property="hdnPrevSelSpec" />
		<html:hidden property="hdnPrevSelCusSeq" />			
   </html:form>	
</div>