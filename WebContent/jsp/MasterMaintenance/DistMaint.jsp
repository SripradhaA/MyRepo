<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT type="text/javascript" SRC="js/distributor.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/File.js"></SCRIPT>
<div class="container" width="100%">
   <html:form  action="/DistAction" method="post" enctype="multipart/form-data">
 		<h4 class="text-center"><bean:message key="Application.Screen.DistributorMaintenance" /></h4>
   		<p class="text-center"><mark>NOTE: Large pictures may exceed page boundaries, Please limit images to 550 by 600 pixels.</mark></p>
		<div class="spacer10"></div>
		<div class="errorlayerhide" id="errorlayer">
		</div>
		<logic:present name="DistMaintForm" property="messageID" scope="request">
			<bean:define id="messageID" name="DistMaintForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
		</logic:present>
		
		<logic:present name="DistMaintForm" property="distList">
			<bean:size id="distListLen" name="DistMaintForm" property="distList" />
		</logic:present>
		
		<div class="form-horizontal">
			<div class="form-group required-field">
				<label class="col-sm-5 control-label">Distributor</label>
				<div class="col-sm-3">					
					<html:text styleClass="form-control" property="distributor" styleId="distributor"/>
				</div>
			</div>
			<div class="form-group">			
				<label class="col-sm-5 control-label">Comments</label>
				<div class="col-sm-3">					
					<html:textarea styleClass="form-control" property="comments" styleId="comments" rows="2" cols="48" />
				</div>
			</div>
			<div class="form-group">			
				<label class="col-sm-5 control-label">Distributor Logo</label>
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
					<button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnAddDistributor()">Add</button>
				</div>
			</div>
		</div>
		<logic:greaterThan name="distListLen" value="0">		
				<hr>		
				<table class="table table-bordered table-hover">
					    <thead>
					        <tr>
						      	<TH>Select</TH>
								<TH>Distributor</TH>
								<TH>Comments</TH>
								<TH>Logo</TH>
								<TH>Logo Updated By</TH>
								<TH>Logo Updated Date</TH>
					        </tr>
					    </thead>
					    <tbody class="text-center">	
							    <logic:iterate id="distMaintFormId" name="DistMaintForm"
								property="distList" type="com.EMD.LSDB.vo.common.DistributorVO" indexId="count">
		
								<TR>
									<TD CLASS="v-midalign"><html:radio
										value="<%=String.valueOf(distMaintFormId.getDistributorSeqNo())%>"
										 property="distributorSeqNo" /></TD>
		
									<TD CLASS="v-midalign"><html:text
										name="distMaintFormId" property="distributorName"
										styleClass="form-control" size="40" maxlength="200" /></TD>
		
									<TD CLASS="v-midalign"><html:textarea
										name="distMaintFormId" property="distributorDesc" styleClass="form-control" rows="2"
										cols="40" >
										<bean:write name="distMaintFormId" property="distributorDesc" />
									</html:textarea></TD>
									<logic:equal name="distMaintFormId" property="imageSeqNo" value="0">
								<TD class="v-midalign">
								&nbsp;
								</TD>
								</logic:equal>
								<logic:notEqual name="distMaintFormId" property="imageSeqNo" value="0">
								<TD class="v-midalign">
								<input type="hidden" name="imageSeqNo" id="imageSeqNo<%=count%>" value="<%=distMaintFormId.getImageSeqNo()%>"/>
						
								<A href="javascript:fnShowImage('<%=distMaintFormId.getImageSeqNo()%>','<%=distMaintFormId.getDistributorName()%>')"
										Class="vtip" title="Logo">
										
										 <IMG SRC="images/comp.gif" BORDER="0"> 
									</A>
								</TD>
								</logic:notEqual>
								<TD class="v-midalign">
								<bean:write name="distMaintFormId" property="firstName"/> <bean:write name="distMaintFormId" property="lastName"/>&nbsp;
								</TD>
								
								
								<TD class="v-midalign"><bean:write name="distMaintFormId" property="logoUpdatedDate"/>&nbsp;
								</TD>
								</TR>
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
					<button class="btn btn-primary mdfybtn" type='button' id="modifyButton" ONCLICK="javascript:fnModifyDistributor()">Modify</button>
				    <button class="btn btn-primary hideunhindebtn" type='button' id="deleteButton" ONCLICK="javascript:fnDeleteDistributor()">Delete</button>
				 </div>   
			 </div>	    
		</logic:greaterThan>		
		</div>	
		<html:hidden property="filePath" />
	<html:hidden property="hdnDistName" />
	<html:hidden property="hdnDistComments" />
	<html:hidden property="hdnImageSeqNo" />
	</html:form>
		<div class="spacer50"></div>
</div>		