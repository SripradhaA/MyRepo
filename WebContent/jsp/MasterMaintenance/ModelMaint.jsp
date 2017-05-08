<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT type="text/javascript" SRC="js/Model.js"></SCRIPT>
<script>
       $(document).ready(function() { 
       		$("#sltSpecType").select2({theme:'bootstrap'});
       })
</script>
<div class="container-fluid">
   <html:form  action="/ModelAction" method="post">
 		<h4 class="text-center"><bean:message key="Application.Screen.ModelMaintenance" /></h4>
 		<div class="spacer10"></div>
   		<p class="text-center"><mark><bean:message key="Application.Screen.HorsePower" /></mark></p>
   		<p class="text-center"><mark><bean:message key="Application.Screen.HiddenModel" /></mark></p>
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
					<div class="col-sm-4 form-inline">
						<html:select styleClass="form-control" name="ModelMaintForm" styleId="sltSpecType"
								property="specTypeNo">
								<option selected value="-1">---Select---</option>
								<html:optionsCollection name="ModelMaintForm"
									property="specTypeList" value="spectypeSeqno" label="spectypename" />
						</html:select>
						<button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnSearchModel()"><bean:message key="model.fetch" /></button>
					</div>	
			</div>
			
			<div class="form-group required-field">			
				<label class="col-sm-5 control-label">Model</label>
				<div class="col-sm-3">					
					<html:text styleClass="form-control" property="modName" styleId="modName"/>
				</div>
			</div>
			
			<div class="form-group required-field">			
				<label class="col-sm-5 control-label">Horse Power Rating</label>
				<div class="col-sm-3">					
					<html:textarea styleClass="form-control" property="horsePower" styleId="horsePower" rows="2" cols="48" />
				</div>
			</div>
			
			<div class="form-group">			
				<label class="col-sm-5 control-label">Comments</label>
				<div class="col-sm-3">					
					<html:textarea styleClass="form-control" property="modDesc" styleId="modDesc" rows="2" cols="48" />
				</div>
			</div>
			
			<div class="form-group">			
				<label class="col-sm-5 control-label">Change Control Type<font size=2 color="red">*</font></label>
					<div class="col-sm-5">	
						<div class="col-sm-1">				
							<label class="radio-inline">
							  <input type="radio" name="changeControlFlag" value="Y"> On
							</label>
						</div>
						<div class="col-sm-1">
							<label class="radio-inline">
							  <input type="radio" name="changeControlFlag" value="N" checked> Off
							</label>
						</div>
					</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-4">
					<button class="btn btn-primary" type='button' id="addButton" ONCLICK="javascript:fnAddModel()">Add</button>
				</div>
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
				
				
				<table class="table table-bordered table-hover">
					    <thead>
					        <tr>
								<TH>Select</TH>
								<TH>Model</TH>
								<TH>Horse Power Rating</TH>
								<TH>Comments</TH>
								<!-- Added for CR_97 for Change Control Type -->
								<TH>Change Control Type</TH>
								<!-- CR_97 Ends here -->
							</tr>	
						</thead>
					    <tbody class="text-center">	
					    		<% int i = 1; %>
								<logic:iterate id="objmodel" name="ModelMaintForm"
								property="modelList" type="com.EMD.LSDB.vo.common.ModelVo"
								scope="request">
								<html:hidden name="objmodel" property="specTypeSeqNo" />
								<!-- Commented for LSDB_CR_56 -->
								<!-- <html:hidden  name="objmodel" property="modelCustTypeSeqNo" /> -->
								
								<%-- Added for CR_118--%>
								<logic:notEqual name="objmodel" property="modelFlag" value="Y" >
								<TR>
								</logic:notEqual>
								<logic:equal name="objmodel" property="modelFlag" value="Y" >
								<TR style="background-color:yellow">
								</logic:equal>
								<%-- Added for CR_118 Ends --%>	
									
									<TD class="v-midalign"><html:radio
										value="<%=String.valueOf(objmodel.getModelSeqNo())%>"
										property="modelSeqNo" />
									<%--Added for CR_118 --%>
									<input type="hidden" name="modelFlag"  value='<bean:write name="objmodel" property="modelFlag" />'/>
									<%--Added for CR_118 Ends--%>	
									</TD>
									
									
									<TD class="v-midalign"><html:text name="objmodel"
										property="modelName" styleClass="form-control" size="40"
										maxlength="100" /></TD>
									
									<%--Updated For CR-114 --%>	
									<TD class="v-midalign"><html:textarea name="objmodel"
										property="hpowerRate" styleClass="form-control" rows="2" cols="40" onkeyup="msgLimit(this, 3999);">
										<bean:write name="objmodel" property="hpowerRate" />
									</html:textarea></TD>
									<%--Updated For CR-114 Ends here--%>	
									<TD class="v-midalign"><html:textarea name="objmodel"
										property="modelDesc" styleClass="form-control" rows="2" cols="40" onkeyup="msgLimit(this, 3999);">
										<bean:write name="objmodel" property="modelDesc" />
									</html:textarea></TD>
		
									<!-- Added for CR_97 to display the Change control Type -->	
									
									<TD class="text-center v-midalign">
										<div class="col-sm-1">&nbsp;
										</div>
										<logic:equal name="objmodel" property="chngCtrlTypeFlag" value="Y">
										<div class="col-sm-4">
											<label class="radio-inline">
											  <input type="radio" name="changeCtrlRadio<%=i%>" value="Y" checked> On
											</label>
										</div>
										<div class="col-sm-4">
											<label class="radio-inline">
											  <input type="radio" name="changeCtrlRadio<%=i%>" value="N"> Off
											</label>
										</div>
										</logic:equal>
										<logic:equal name="objmodel" property="chngCtrlTypeFlag" value="N">
										<div class="col-sm-4">
											<label class="radio-inline">
											  <input type="radio" name="changeCtrlRadio<%=i%>" value="Y"> On
											</label>
										</div>
										<div class="col-sm-4">
											<label class="radio-inline">
											  <input type="radio" name="changeCtrlRadio<%=i%>" value="N" checked> Off
											</label>
										</div>
										</logic:equal>
									</TD>
								</TR>
									<%i++; %>
							</logic:iterate>
					    	
					    </tbody>
				</table>
				
					<div class="spacer10"></div>
				
				
				<div class="form-group">

					         <div class="col-sm-12 text-center">
					            <button class="btn btn-primary mdfybtn" type='button' id="modifyButton" ONCLICK="javascript:fnModifyModel()">Modify</button>
					            <logic:equal name="ModelMaintForm" property="roleID" value="ADMN">
								<%--Edited for CR_121 Starts here--%>
									<button class="btn btn-primary hideunhindebtn" type='button' id="hideunhindeButton" ONCLICK="javascript:fnHideUnhideModel()">Hide/Unhide</button>
								</logic:equal>
					         </div>   
			   </div>

			    
			    
					<div class="spacer30"></div>
				
		</logic:greaterThan>	    
	<html:hidden property="hdnModelName" />
	<html:hidden property="hdnHorsePower" />
	<html:hidden property="hdnModelDesc" />

	<html:hidden property="hdnSpecTypeSeqNo" />
	<!-- Commented for LSDB_CR_56 -->
	<!--<html:hidden property="hdnCusTypeSeqNo"/>-->

	<html:hidden property="hdnSpecTypeName" />

	<html:hidden property="hdnPrevSelSpec" />
	<html:hidden property="hdnPrevSelCusSeq" />
	<!-- Added for CR_97 -->
	<html:hidden property="hdnChangeControlFlag" />
	<%--Added for CR_118 --%>
	<input type="hidden" name="hdnModelMaintScreen" value="true" />
	<html:hidden property="hdnModelFlag"/>						
	<html:hidden property="hideUnhideModel" />
	<%--Added for CR_118 Ends--%>	
   </html:form>
   	<div class="spacer50"></div>
</div>    	