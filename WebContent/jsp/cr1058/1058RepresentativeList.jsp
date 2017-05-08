<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<logic:present name="ChangeRequest1058Form"
	property="requestDetailsList"
			scope="request" >
<logic:iterate id="requestCommonDetail" name="ChangeRequest1058Form"
	property="requestDetailsList" type="com.EMD.LSDB.vo.common.ChangeRequest1058VO" scope="request">
	<div class="panel panel-info">
		<div class="panel-heading">
			<h4 class="panel-title"><strong>Representatives</strong></h4>
		</div>
		<div class="panel-body">
			<div class="form-group">
				<div class="multipleFormFields">
					<label class="col-sm-3 control-label">Select System Engineer<font size=2 color="red">*</font></label>
					<div class="col-sm-3">
					<logic:notEqual name="requestCommonDetail" property="statusSeqNo"  value="1">
					<html:select property="repList" styleClass='form-control partOfColFormField' tabindex="8" styleId="sysEngg" disabled="true">
						 <option selected value="-1">---Select---</option>
						 <logic:present name="ChangeRequest1058Form" property="representativeList">
							<logic:iterate id="userList" name="ChangeRequest1058Form" property="representativeList" 
							scope="request" type="com.EMD.LSDB.vo.common.ChangeRequest1058VO" >
								<logic:iterate id="sysList" name="userList" property="sysEnggList" 
									type="com.EMD.LSDB.vo.common.ChangeRequest1058VO" >
									<logic:equal name="sysList" property="userID" value="<%=String.valueOf(requestCommonDetail.getSysEnggUserID())%>">
										<option selected="selected" value="<bean:write name="sysList" property="userID" />"><bean:write name="sysList" property="firstName" />, <bean:write name="sysList" property="lastName" /> - <bean:write name="sysList" property="userID" /></option>
									</logic:equal>
									<logic:notEqual name="sysList" property="userID" value="<%=String.valueOf(requestCommonDetail.getSysEnggUserID())%>">
										<option value="<bean:write name="sysList" property="userID" />"><bean:write name="sysList" property="firstName" />, <bean:write name="sysList" property="lastName" /> - <bean:write name="sysList" property="userID" /></option>
									</logic:notEqual>
								</logic:iterate>
							</logic:iterate>
						</logic:present>
					</html:select>
					</logic:notEqual>
					<logic:equal name="requestCommonDetail" property="statusSeqNo"  value="1">
					<html:select property="repList" styleClass='form-control partOfColFormField' tabindex="8" styleId="sysEngg" >
						 <option selected value="-1">---Select---</option>
						 <logic:present name="ChangeRequest1058Form" property="representativeList">
							<logic:iterate id="userList" name="ChangeRequest1058Form" property="representativeList" 
							scope="request" type="com.EMD.LSDB.vo.common.ChangeRequest1058VO" >
								<logic:iterate id="sysList" name="userList" property="sysEnggList" 
									type="com.EMD.LSDB.vo.common.ChangeRequest1058VO" >
									<logic:equal name="sysList" property="userID" value="<%=String.valueOf(requestCommonDetail.getSysEnggUserID())%>">
										<option selected="selected" value="<bean:write name="sysList" property="userID" />"><bean:write name="sysList" property="firstName" />, <bean:write name="sysList" property="lastName" /> - <bean:write name="sysList" property="userID" /></option>
									</logic:equal>
									<logic:notEqual name="sysList" property="userID" value="<%=String.valueOf(requestCommonDetail.getSysEnggUserID())%>">
										<option value="<bean:write name="sysList" property="userID" />"><bean:write name="sysList" property="firstName" />, <bean:write name="sysList" property="lastName" /> - <bean:write name="sysList" property="userID" /></option>
									</logic:notEqual>
								</logic:iterate>
							</logic:iterate>
						</logic:present>
					</html:select>
					</logic:equal>	
					</div>
				</div>
				<div class="multipleFormFields">
					<label class="col-sm-3 control-label">Select Operations Representative<font size=2 color="red">*</font></label>
					<div class="col-sm-3">
					<logic:notEqual name="requestCommonDetail" property="statusSeqNo"  value="1">
					<html:select  property="repList" styleClass='form-control partOfColFormField' tabindex="8" styleId="opRep" disabled="true">
					 <option selected value="-1">---Select---</option>
					 <logic:present name="ChangeRequest1058Form" property="representativeList">
						<logic:iterate id="userList" name="ChangeRequest1058Form" property="representativeList" 
						scope="request" type="com.EMD.LSDB.vo.common.ChangeRequest1058VO" >
							<logic:iterate id="opList" name="userList" property="opRepList" 
								type="com.EMD.LSDB.vo.common.ChangeRequest1058VO" >
								<logic:equal name="opList" property="userID" value="<%=String.valueOf(requestCommonDetail.getOprEnggUserID())%>">
									<option selected="selected" value="<bean:write name="opList" property="userID" />"><bean:write name="opList" property="firstName" />, <bean:write name="opList" property="lastName" /> - <bean:write name="opList" property="userID" /></option>
								</logic:equal>
								<logic:notEqual name="opList" property="userID" value="<%=String.valueOf(requestCommonDetail.getOprEnggUserID())%>">
									<option value="<bean:write name="opList" property="userID" />"><bean:write name="opList" property="firstName" />, <bean:write name="opList" property="lastName" /> - <bean:write name="opList" property="userID" /></option>
								</logic:notEqual>
							</logic:iterate>
						</logic:iterate>
					</logic:present>
					</html:select>
					</logic:notEqual>
					<logic:equal name="requestCommonDetail" property="statusSeqNo"  value="1">
					 <html:select  property="repList" styleClass='form-control partOfColFormField' tabindex="8" styleId="opRep">
					 <option selected value="-1">---Select---</option>
					 <logic:present name="ChangeRequest1058Form" property="representativeList">
						<logic:iterate id="userList" name="ChangeRequest1058Form" property="representativeList" 
						scope="request" type="com.EMD.LSDB.vo.common.ChangeRequest1058VO" >
							<logic:iterate id="opList" name="userList" property="opRepList" 
								type="com.EMD.LSDB.vo.common.ChangeRequest1058VO" >
								<logic:equal name="opList" property="userID" value="<%=String.valueOf(requestCommonDetail.getOprEnggUserID())%>">
									<option selected="selected" value="<bean:write name="opList" property="userID" />"><bean:write name="opList" property="firstName" />, <bean:write name="opList" property="lastName" /> - <bean:write name="opList" property="userID" /></option>
								</logic:equal>
								<logic:notEqual name="opList" property="userID" value="<%=String.valueOf(requestCommonDetail.getOprEnggUserID())%>">
									<option value="<bean:write name="opList" property="userID" />"><bean:write name="opList" property="firstName" />, <bean:write name="opList" property="lastName" /> - <bean:write name="opList" property="userID" /></option>
								</logic:notEqual>
							</logic:iterate>
						</logic:iterate>
					</logic:present>
					</html:select>
					</logic:equal>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="multipleFormFields">
					<label class="col-sm-3 control-label">Select Finance Representative<font size=2 color="red">*</font></label>
					<div class="col-sm-3">
					<logic:notEqual name="requestCommonDetail" property="statusSeqNo"  value="1">
					<html:select property="repList" styleClass='form-control partOfColFormField' tabindex="8" styleId="finRep" disabled="true">
					 <option selected value="-1">---Select---</option>
					 <logic:present name="ChangeRequest1058Form" property="representativeList">
						<logic:iterate id="userList" name="ChangeRequest1058Form" property="representativeList" 
						scope="request" type="com.EMD.LSDB.vo.common.ChangeRequest1058VO" >
							<logic:iterate id="finList" name="userList" property="finRepList" 
								type="com.EMD.LSDB.vo.common.ChangeRequest1058VO" >
								<logic:equal name="finList" property="userID" value="<%=String.valueOf(requestCommonDetail.getFinanceUserID())%>">
									<option selected="selected" value="<bean:write name="finList" property="userID" />"><bean:write name="finList" property="firstName" />, <bean:write name="finList" property="lastName" /> - <bean:write name="finList" property="userID" /></option>
								</logic:equal>
								<logic:notEqual name="finList" property="userID" value="<%=String.valueOf(requestCommonDetail.getFinanceUserID())%>">
									<option value="<bean:write name="finList" property="userID" />"><bean:write name="finList" property="firstName" />, <bean:write name="finList" property="lastName" /> - <bean:write name="finList" property="userID" /></option>
								</logic:notEqual>
							</logic:iterate>
						</logic:iterate>
					</logic:present>
					</html:select>
					</logic:notEqual>
					<logic:equal name="requestCommonDetail" property="statusSeqNo"  value="1">
					<html:select property="repList" styleClass='form-control partOfColFormField' tabindex="8" styleId="finRep">
					 <option selected value="-1">---Select---</option>
					 <logic:present name="ChangeRequest1058Form" property="representativeList">
						<logic:iterate id="userList" name="ChangeRequest1058Form" property="representativeList" 
						scope="request" type="com.EMD.LSDB.vo.common.ChangeRequest1058VO" >
							<logic:iterate id="finList" name="userList" property="finRepList" 
								type="com.EMD.LSDB.vo.common.ChangeRequest1058VO" >
								<logic:equal name="finList" property="userID" value="<%=String.valueOf(requestCommonDetail.getFinanceUserID())%>">
									<option selected="selected" value="<bean:write name="finList" property="userID" />"><bean:write name="finList" property="firstName" />, <bean:write name="finList" property="lastName" /> - <bean:write name="finList" property="userID" /></option>
								</logic:equal>
								<logic:notEqual name="finList" property="userID" value="<%=String.valueOf(requestCommonDetail.getFinanceUserID())%>">
									<option value="<bean:write name="finList" property="userID" />"><bean:write name="finList" property="firstName" />, <bean:write name="finList" property="lastName" /> - <bean:write name="finList" property="userID" /></option>
								</logic:notEqual>
							</logic:iterate>
						</logic:iterate>
					</logic:present>
					</html:select>
					</logic:equal>
					</div>
				</div>
				<div class="multipleFormFields">
					<label class="col-sm-3 control-label">Select Program Manager<font size=2 color="red">*</font></label>
					<div class="col-sm-3">
					<logic:notEqual name="requestCommonDetail" property="statusSeqNo"  value="1">
					<html:select  property="repList" styleClass='form-control partOfColFormField' tabindex="8" styleId="pgmMgr" disabled="true">
					 <option selected value="-1">---Select---</option>
					 <logic:present name="ChangeRequest1058Form" property="representativeList">
						<logic:iterate id="userList" name="ChangeRequest1058Form" property="representativeList" 
						scope="request" type="com.EMD.LSDB.vo.common.ChangeRequest1058VO" >
							<logic:iterate id="pgmList" name="userList" property="pgmMgrList" 
								type="com.EMD.LSDB.vo.common.ChangeRequest1058VO" >
								<logic:equal name="pgmList" property="userID" value="<%=String.valueOf(requestCommonDetail.getPgmMgrUserID())%>">
									<option selected="selected" value="<bean:write name="pgmList" property="userID" />"><bean:write name="pgmList" property="firstName" />, <bean:write name="pgmList" property="lastName" /> - <bean:write name="pgmList" property="userID" /></option>
								</logic:equal>
								<logic:notEqual name="pgmList" property="userID" value="<%=String.valueOf(requestCommonDetail.getPgmMgrUserID())%>">
									<option value="<bean:write name="pgmList" property="userID" />"><bean:write name="pgmList" property="firstName" />, <bean:write name="pgmList" property="lastName" /> - <bean:write name="pgmList" property="userID" /></option>
								</logic:notEqual>
							</logic:iterate>						
						</logic:iterate>
					</logic:present>
					</html:select>
					</logic:notEqual>
					<logic:equal name="requestCommonDetail" property="statusSeqNo"  value="1">
					<html:select  property="repList" styleClass='form-control partOfColFormField' tabindex="8" styleId="pgmMgr">
					 <option selected value="-1">---Select---</option>
					 <logic:present name="ChangeRequest1058Form" property="representativeList">
						<logic:iterate id="userList" name="ChangeRequest1058Form" property="representativeList" 
						scope="request" type="com.EMD.LSDB.vo.common.ChangeRequest1058VO" >
							<logic:iterate id="pgmList" name="userList" property="pgmMgrList" 
								type="com.EMD.LSDB.vo.common.ChangeRequest1058VO" >
								<logic:equal name="pgmList" property="userID" value="<%=String.valueOf(requestCommonDetail.getPgmMgrUserID())%>">
									<option selected="selected" value="<bean:write name="pgmList" property="userID" />"><bean:write name="pgmList" property="firstName" />, <bean:write name="pgmList" property="lastName" /> - <bean:write name="pgmList" property="userID" /></option>
								</logic:equal>
								<logic:notEqual name="pgmList" property="userID" value="<%=String.valueOf(requestCommonDetail.getPgmMgrUserID())%>">
									<option value="<bean:write name="pgmList" property="userID" />"><bean:write name="pgmList" property="firstName" />, <bean:write name="pgmList" property="lastName" /> - <bean:write name="pgmList" property="userID" /></option>
								</logic:notEqual>
							</logic:iterate>						
						</logic:iterate>
					</logic:present>
					</html:select>
					</logic:equal>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="multipleFormFields">
					<label class="col-sm-3 control-label">Select Proposal Manager<font size=2 color="red">*</font></label>
					<div class="col-sm-3">
					<logic:notEqual name="requestCommonDetail" property="statusSeqNo"  value="1">
					<html:select  property="repList" styleClass='form-control partOfColFormField' tabindex="8" styleId="propMgr" disabled="true">
					 <option selected value="-1">---Select---</option>
					 <logic:present name="ChangeRequest1058Form" property="representativeList">
						<logic:iterate id="userList" name="ChangeRequest1058Form" property="representativeList" 
						scope="request" type="com.EMD.LSDB.vo.common.ChangeRequest1058VO" >
							<logic:iterate id="propList" name="userList" property="propMgrList" 
								type="com.EMD.LSDB.vo.common.ChangeRequest1058VO" >
								<logic:equal name="propList" property="userID" value="<%=String.valueOf(requestCommonDetail.getPropMgrUserID())%>">
									<option selected="selected" value="<bean:write name="propList" property="userID" />"><bean:write name="propList" property="firstName" />, <bean:write name="propList" property="lastName" /> - <bean:write name="propList" property="userID" /></option>
								</logic:equal>
								<logic:notEqual name="propList" property="userID" value="<%=String.valueOf(requestCommonDetail.getPropMgrUserID())%>">
									<option value="<bean:write name="propList" property="userID" />"><bean:write name="propList" property="firstName" />, <bean:write name="propList" property="lastName" /> - <bean:write name="propList" property="userID" /></option>
								</logic:notEqual>
							</logic:iterate>
						</logic:iterate>
					</logic:present>
					</html:select>
					</logic:notEqual>
					<logic:equal name="requestCommonDetail" property="statusSeqNo"  value="1">
					<html:select  property="repList" styleClass='form-control partOfColFormField' tabindex="8" styleId="propMgr">
					 <option selected value="-1">---Select---</option>
					 <logic:present name="ChangeRequest1058Form" property="representativeList">
						<logic:iterate id="userList" name="ChangeRequest1058Form" property="representativeList" 
						scope="request" type="com.EMD.LSDB.vo.common.ChangeRequest1058VO" >
							<logic:iterate id="propList" name="userList" property="propMgrList" 
								type="com.EMD.LSDB.vo.common.ChangeRequest1058VO" >
								<logic:equal name="propList" property="userID" value="<%=String.valueOf(requestCommonDetail.getPropMgrUserID())%>">
									<option selected="selected" value="<bean:write name="propList" property="userID" />"><bean:write name="propList" property="firstName" />, <bean:write name="propList" property="lastName" /> - <bean:write name="propList" property="userID" /></option>
								</logic:equal>
								<logic:notEqual name="propList" property="userID" value="<%=String.valueOf(requestCommonDetail.getPropMgrUserID())%>">
									<option value="<bean:write name="propList" property="userID" />"><bean:write name="propList" property="firstName" />, <bean:write name="propList" property="lastName" /> - <bean:write name="propList" property="userID" /></option>
								</logic:notEqual>
							</logic:iterate>
						</logic:iterate>
					</logic:present>
					</html:select>
					</logic:equal>
					</div>
				</div>
				<logic:notEqual name="requestCommonDetail" property="statusSeqNo" value="1">
					<logic:notEqual name="requestCommonDetail" property="statusSeqNo" value="5">
						<!-- Added for CR-117 for cancel status -->
					   <logic:notEqual name="requestCommonDetail" property="statusSeqNo" value="10">
							<logic:equal name="ChangeRequest1058Form" property="roleID" value="VALID_USER">				
							<label class="col-sm-3 control-label">Please check to reassign Representatives</label>
							</logic:equal>
							<logic:notEqual name="ChangeRequest1058Form" property="roleID" value="VALID_USER">
							<logic:equal name="requestCommonDetail" property="ownerFlag" value="Y">				
							<label class="col-sm-3 control-label">Please check to reassign Representatives</label>
		 					</logic:equal>
		 					</logic:notEqual>
	               	 	</logic:notEqual>
	 				</logic:notEqual>	
               </logic:notEqual> 
               
               <logic:notEqual name="requestCommonDetail" property="statusSeqNo" value="1">
					<logic:notEqual name="requestCommonDetail" property="statusSeqNo" value="5">
						<!-- Added for CR-117 for cancel status -->
					   <logic:notEqual name="requestCommonDetail" property="statusSeqNo" value="10">
							<logic:equal name="ChangeRequest1058Form" property="roleID" value="VALID_USER">
								<div class="col-sm-3">
									<input type="checkbox" id="chkReAssignRep" />
								</div>
							</logic:equal>
						<logic:notEqual name="ChangeRequest1058Form" property="roleID" value="VALID_USER">
							<logic:equal name="requestCommonDetail" property="ownerFlag" value="Y">
							<div class="col-sm-3">
								<input type="checkbox" id="chkReAssignRep" />
							</div>
		 					</logic:equal>
	 					</logic:notEqual>
               		 </logic:notEqual>
	 			  </logic:notEqual>	
              </logic:notEqual> 
	        </div>
			<div class="spacer10"></div>
			<div class="form-group">
		         <div class="col-sm-12 text-center">
		         	<logic:notEqual name="requestCommonDetail" property="statusSeqNo"  value="1">
		            	<button class="btn btn-primary" type='button' id="saveSend" disabled="true">Save & Send</button>
		         	</logic:notEqual>	
                   	<logic:equal name="requestCommonDetail" property="statusSeqNo"  value="1">		
                   		<button class="btn btn-primary" type='button' id="saveSend" onclick="javascript:fnsaveandsendreplist('N');">Save & Send</button>	
					</logic:equal>
					<logic:notEqual name="requestCommonDetail" property="statusSeqNo"  value="1">
						<logic:equal name="ChangeRequest1058Form" property="roleID" value="VALID_USER">
							<button class="btn btn-primary" type='button' id="btnReAssignRep" 
								onclick="javascript:fnsaveandsendreplist('Y');" disabled="true">Reassign Representative(s)</button>
						</logic:equal>
						<logic:notEqual name="ChangeRequest1058Form" property="roleID" value="VALID_USER">
							<logic:equal name="requestCommonDetail" property="ownerFlag" value="Y">
								<button class="btn btn-primary" type='button' id="btnReAssignRep" 
									onclick="javascript:fnsaveandsendreplist('Y');" disabled="true">Reassign Representative(s)</button>
							</logic:equal>
						</logic:notEqual>
					</logic:notEqual>
				</div>
			</div>	
		</div>
		</div>
	</logic:iterate>
</logic:present>