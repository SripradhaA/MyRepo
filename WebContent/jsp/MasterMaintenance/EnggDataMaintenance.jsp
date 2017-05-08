<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT type="text/javascript" SRC="js/EnggDataMaint.js"></SCRIPT>
<div class="container" width="100%">
   <html:form  action="/EnggDataMaintenance" method="post">
	<h4 class="text-center"><bean:message key="Application.Screen.EnggDataMaintenance" /></h4>
 		<div class="spacer10"></div>
		<div class="errorlayerhide" id="errorlayer">
		</div>
		<logic:present name="EngineeringDataForm" property="messageID" scope="request">
			<bean:define id="messageID" name="EngineeringDataForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
		</logic:present>
		
			<logic:present name="EngineeringDataForm" property="standardEquipmentList" scope="request">
				<bean:size name="EngineeringDataForm" id="EngDatasize" property="standardEquipmentList" />
			</logic:present>
		<div class="spacer10"></div>
		
		 <div class="form-horizontal">
	 		 <div class="form-group required-field">
				<label class="col-sm-5 control-label">Engineering Data</label>
				<div class="col-sm-3">					
						<html:text styleClass="form-control" property="stdEquipDesc" styleId="stdEquipDesc"/>
				</div>
			</div>
			<div class="form-group">			
					<label class="col-sm-5 control-label">Engineering Data Description</label>
					<div class="col-sm-3">					
						<html:textarea styleClass="form-control" property="enggDataDesc" styleId="enggDataDesc" rows="2" cols="48" />
					</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-4">
					<button class="btn btn-primary" type='button' id="addButton" ONCLICK="javascript:fnAdd()"><bean:message key="screen.add" /></button>
				</div>
			</div>
		</div>
		
		<logic:greaterThan name="EngDatasize" value="0">		
				<hr>		
				<div class="spacer10"></div>
				<table class="table table-bordered table-hover">
					    <thead>
					        <tr>
								<TH>Select</TH>
								<TH>Engineering Data</TH>
								<TH>Engineering Data Description</TH>
							</tr>	
						</thead>
					    <tbody class="text-center">	
					    	<logic:iterate id="StandardEquipVO" name="EngineeringDataForm"
									property="standardEquipmentList"
									type="com.EMD.LSDB.vo.common.StandardEquipVO" scope="request">
									<TR>
										<TD CLASS="v-midalign"><html:radio
											property="standardEquipmentSeqNo"
											value='<%= String.valueOf(StandardEquipVO.getStandardEquipmentSeqNo())%>' />
										</TD>
										<TD CLASS="v-midalign"><html:text
											styleClass="form-control" name="StandardEquipVO"
											property="standardEquipmentDesc" size="45" maxlength="100" /></TD>
										<%-- Added For CR-114 --%>
										<TD CLASS="v-midalign"><html:textarea styleClass="form-control"
											rows ="2" cols ="42" name="StandardEquipVO" property="stdEnggDataDesc"
											onkeyup="fnSetMaxSize(this, 3999);"/>
										</TD>
										<%--Added For CR-114 Ends Here--%>			
									</TR>
							</logic:iterate>
					    </tbody>
				</table>
				<div class="spacer10"></div>
				
				<div class="form-group">
				  <div class="col-sm-12 text-center">
				       <button class="btn btn-primary mdfybtn" type='button' id="modifyButton" ONCLICK="javascript:fnModify()"><bean:message key="screen.modify" /></button>
				        <button class="btn btn-primary deleteButton" type='button' id="deleteButton" ONCLICK="javascript:fnDelete()"><bean:message key="screen.delete" /></button>
					</div>   
			   	</div>
				<div class="spacer50"></div>	    
		</logic:greaterThan>	
		
	<html:hidden property="hdStdEquipDesc" />
	<html:hidden property="hdEnggDataDesc" />		
   </html:form>
</div>  