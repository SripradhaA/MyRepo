<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<SCRIPT LANGUAGE="JavaScript" SRC="js/ComponentGroupReport.js"></SCRIPT>
<script>
    $(document).ready(function() { 
    	$("#specTypeSeqNo").select2({theme:'bootstrap'}); 
    })
</script>
<div class="container-fluid">
	<html:form action="/CompGroupAction" method="post" styleClass="form-horizontal">
		<!-- Application PageName Display starts here-->
		<h4 class="text-center">Component Group / Component Report</h4>
		<div class="spacer10"></div>
		<p class="text-center"><mark>NOTE : Components in <font color="red">RED</font> denotes that it is an Order Level Component.</mark></p>
		<p class="text-center"><mark>Press SHIFT/CTRL for Selecting multiple Models.</mark></p>
		<!-- Application PageName Display ends here-->
		<div class="spacer10"></div>
		
		<!-- Validation Message Display Part Starts Here -->
		<div class="errorlayerhide" id="errorlayer">
		</div>
		<!-- Validation Message Display Part Ends Here -->
	
		<!-- To display  Validation Messages - Start -->
		<logic:present name="CompGroupMaintForm" property="messageID"
			scope="request">
			
	           <bean:define id="messageID" name="CompGroupMaintForm" property="messageID"/>
	            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
	            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
		</logic:present>
		<!-- To display Validation Messages - End -->
		
		<logic:present name="CompGroupMaintForm" property="compgroupList">
			<bean:size id="CompGrpListLen" name="CompGroupMaintForm"
				property="compgroupList" />
		</logic:present>
	
		<%-- CR_101 Ends here --%>
		<!-- Logic Tag Check For Display The No Records Found Message  Functionality Ends Here -->
		<%-- Added CR_97 --%>
		
		<div class="form-group required-field">
			<label class="col-sm-5 control-label">Specification Type</label>
			<div class="col-sm-1">
				<html:select styleClass="form-control" name="CompGroupMaintForm" property="specTypeNo"
					styleId="specTypeSeqNo" onchange="javascript:fnLoadModelsAndCompGrpReport()">
					<option selected value="0">All</option>
					<logic:present name="CompGroupMaintForm" property="specTypeList">
						<html:optionsCollection name="CompGroupMaintForm"
							property="specTypeList" value="spectypeSeqno" label="spectypename" />
					</logic:present>
				</html:select>
			</div>
		</div>
		
		<div class="form-group required-field">
			<label class="col-sm-5 control-label">Model</label>
			<div class="col-sm-2">
				<html:select name="CompGroupMaintForm" property="modelSeqNos" styleId="selModelSeqNos" 
					multiple="true" styleClass="form-control" size="6">
				<logic:present name="CompGroupMaintForm" property="modelList">
					<html:optionsCollection name="CompGroupMaintForm"
						property="modelList" value="modelSeqNo" label="modelName" />
				</logic:present>
			</html:select>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-5 control-label">Include Order Components</label>
			<div class="col-sm-1">
				<html:checkbox property="includeOrderFlag" />
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-12 text-center">
				<button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnSearchCompGrpReport()"><bean:message key="screen.search" /></button>
				<button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnMasterSpecByModelExcel()"><bean:message key="master.ExportToExcel" /></button>
			</div>
		</div>
		
	<%-- CR_101 Ends here --%>
	<%-- linesEnds here --%>
	<%--heading has to display if having record For CR_104--%>
	
     
			
	<logic:notEmpty name="CompGroupMaintForm" property="compgroupList">
		<HR>
		<div class="form-group">
			<div class="col-sm-11 text-center"><h5 class="text-center"><strong>Search critera</strong></h5></div>
		</div>
		<div class="form-group">
			<div class="col-sm-4 col-sm-offset-1 text-right"><strong>Specification Type</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<bean:write name="CompGroupMaintForm" property="hdnSelSpecTypeName" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-4 col-sm-offset-1 text-right"><strong>Model(s)</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-4 text-left">
				<bean:write name="CompGroupMaintForm" property="hdnSelectedMdlNames" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-4 col-sm-offset-1 text-right"><strong>Include Order Components</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-4 text-left">
				<bean:write name="CompGroupMaintForm" property="includeOrderONOFF" />
			</div>
		</div>
		<div class="spacer10"></div>
		
		<TABLE class="table table-bordered">
			<thead>
				<TR>
					<TH width="10%">Component Group</TH>
					<TH width="15%">Component Group Identifier</TH>
					<TH width="15%">Component Group Type</TH>
					<TH width="15%">Component Value</TH>
					<TH width="15%">Component Value Identifier</TH>
					<TH width="15%" nowrap>Models Affected</TH>
					<TH width="15%" nowrap>Model Default</TH>
				</TR>
			</thead>
			<tbody>
				<logic:iterate id="compgrp" name="CompGroupMaintForm"
					property="compgroupList" type="com.EMD.LSDB.vo.common.CompGroupVO">
					<TR>
						<logic:notEmpty name="compgrp" property="componentVO">
							<bean:size id="CompLen" name="compgrp" property="componentVO" />
							<!-- Modified For CR_81 by RR68151 on 07-Jan-2010 -->
							<TD rowspan="<%= String.valueOf(CompLen.intValue() + 1) %>" CLASS='text-center v-midalign' style="word-wrap:break-word;">
								<bean:write name="compgrp" property="componentGroupName" />
							</TD>
						</logic:notEmpty>
						<logic:empty name="compgrp" property="componentVO">
							<TD CLASS='text-center v-midalign' 
								style="word-wrap:break-word;">
								<bean:write name="compgrp" property="componentGroupName" />
							</TD>
						</logic:empty>
						
						<logic:notEmpty name="compgrp" property="componentVO">
							<bean:size id="CompLen" name="compgrp" property="componentVO" />
							<TD rowspan="<%= String.valueOf(CompLen.intValue() + 1) %>" CLASS='text-center v-midalign' style="word-wrap:break-word;">
								<bean:write name="compgrp" property="componentGroupIdentifier" />
							</TD>
						</logic:notEmpty>
						<logic:empty name="compgrp" property="componentVO">
							<TD CLASS='text-center v-midalign' 
								style="word-wrap:break-word;">
								<bean:write name="compgrp" property="componentGroupIdentifier" />
							</TD>
						</logic:empty>
						<!-- Modified For CR_81 by RR68151 on 07-Jan-2010 -->
						
						<logic:notEmpty name="compgrp" property="componentVO">
							<bean:size id="CompLen" name="compgrp" property="componentVO" />
							<TD rowspan=<%= String.valueOf(CompLen.intValue() + 1) %> CLASS='text-center v-midalign' style="word-wrap:break-word;">
								<bean:write name="compgrp" property="compGrpTypeName" />
							</TD>
						</logic:notEmpty>
						<logic:empty name="compgrp" property="componentVO">
							<TD CLASS='text-center v-midalign'>
								<bean:write name="compgrp" property="compGrpTypeName" />
							</TD>
						</logic:empty>
						<!-- Modified For CR_81 by RR68151 on 07-Jan-2010 - Ends here -->
						
						<logic:notEmpty name="compgrp" property="componentVO">
							<logic:iterate id="comp" name="compgrp" property="componentVO">
								<tr>
									<td class="text-center v-midalign" style="word-wrap:break-word;">
										<logic:notEmpty name="comp"	property="componentName">
										<%-- Added for CR_101 to display the order components in RED --%>
											<logic:equal name="comp" property="compSourceFlag" value="O"> 
												<font color="red">
													<bean:write name="comp" property="componentName" />
												</font>
											</logic:equal>	
											<logic:equal name="comp" property="compSourceFlag" value="M"> 
												<bean:write name="comp" property="componentName" />
											</logic:equal>	
										<%-- CR_101 Ends here--%>									
										</logic:notEmpty> 
										<logic:empty name="comp" property="componentName">
											&nbsp;
										</logic:empty>
									</td>
		
									<td CLASS="text-center v-midalign" style="word-wrap:break-word;">
										<logic:notEmpty name="comp" property="componentIdentifier">
											<bean:write name="comp" property="componentIdentifier" />
										</logic:notEmpty> 
										<logic:empty name="comp" property="componentIdentifier">
											&nbsp;
										</logic:empty>
									</td>
		
									<td CLASS="text-center v-midalign" style="word-wrap:break-word;">
										<logic:notEmpty name="comp" property="modelsAffected">
											<logic:iterate id="model" name="comp" property="modelsAffected">
												<bean:write name="model" />
												<BR>
											</logic:iterate>
										</logic:notEmpty> 
										<logic:empty name="comp" property="modelsAffected">
												<BR>
										</logic:empty>
									</td>
		
									<td CLASS="text-center v-midalign" style="word-wrap:break-word;">
										<logic:notEmpty name="comp" property="modelDefault">
											<logic:iterate id="modelname" name="comp"
												property="modelDefault">
												<bean:write name="modelname" />
												<BR>
											</logic:iterate>
										</logic:notEmpty> 
										<logic:empty name="comp" property="modelDefault">
											<BR>
										</logic:empty>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
		
						<logic:empty name="compgrp" property="componentVO">
							<tr>
								<td CLASS="text-center v-midalign">&nbsp;</td>
		
								<td CLASS="text-center v-midalign">&nbsp;</td>
		
								<td CLASS="text-center v-midalign">&nbsp;</td>
		
								<td CLASS="text-center v-midalign">&nbsp;</td>
							</tr>
						</logic:empty>
					</TR>
				</logic:iterate>
			</tbody>
		</TABLE>
	</logic:notEmpty>
	
	
	<!-- Added For CR_84 -->
	<html:hidden property="hdnSelSpecTypeName" />
	
	<html:hidden property="hdnSelectedMdlNames" />
		<html:hidden property="hdnIncludeOrderFlag" />
		<html:hidden property="hdnSelSpecTypeNo" />
		<html:hidden property="selectedMdlNames" />
		
	
</html:form>
</div>
