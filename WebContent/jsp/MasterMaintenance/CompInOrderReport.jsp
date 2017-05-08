<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<SCRIPT LANGUAGE="JavaScript" SRC="js/CompInOrderReport.js"></SCRIPT>

<script>
        $(document).ready(function() { 
        	$("#specTypeSeqNo").select2({theme:'bootstrap'}); 
        	$("#modelSeqNo").select2({theme:'bootstrap'});
        	$("#compGroupSeqNo").select2({theme:'bootstrap'});
        	$("#componentSeqNo").select2({theme:'bootstrap'});
		})
</script>

<div class = "container-fluid">

<html:form action="/CompGroupAction" method="post" styleClass="form-horizontal">

	<div class="spacer10"></div>
		
	<h4 class ="text-center">Component in Orders Report</h4>
	
	<div class="spacer10"></div>
	
	<p class="text-center"><mark>NOTE : Components in <font color="red">RED</font> denotes that it is an Order Level Component.</mark></p>
	
	<div class="spacer20"></div>
	
	<div class="row">
		<div class="errorlayerhide" id="errorlayer"></div>
	</div>
			
	<BR>
	
	<logic:present name="CompGroupMaintForm" property="messageID" scope="request">
	    <bean:define id="messageID" name="CompGroupMaintForm" property="messageID"/>
	    <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
	    <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
    </logic:present>
	
	<logic:present name="CompGroupMaintForm" property="compgroupList">
		<bean:size id="CompGrpListLen" name="CompGroupMaintForm" property="compgroupList" />
	</logic:present>
	
	<div>
	
		<div class="form-group required-field">
			<label class="col-sm-5 control-label">Specification Type</label>
			<div class="col-sm-2">
				<html:select name="CompGroupMaintForm" property="specTypeNo" styleClass="form-control" 
						styleId="specTypeSeqNo" onchange="javascript:fnRefreshDropdowns()">
						<option selected value="0">All</option>
						<logic:present name="CompGroupMaintForm" property="specTypeList">
							<html:optionsCollection name="CompGroupMaintForm"
								property="specTypeList" value="spectypeSeqno" label="spectypename" />
						</logic:present>
				</html:select>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-5 control-label">Model</label>
			<div class="col-sm-2">
				<html:select name="CompGroupMaintForm" property="modelSeqNo" styleId="modelSeqNo"
					styleClass="form-control" onchange="javascript:fnRefreshDropdowns()">
					<option selected value="-1">---Select---</option>
					<logic:present name="CompGroupMaintForm" property="modelList">
						<html:optionsCollection name="CompGroupMaintForm"
							property="modelList" value="modelSeqNo" label="modelName" />
					</logic:present>
				</html:select>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-5 control-label">Component Group</label>
			<div class="col-sm-2">
				<html:select name="CompGroupMaintForm" property="compGroupSeqNo" styleId="compGroupSeqNo"
				styleClass="form-control" onchange="javascript:fnRefreshDropdowns()">
				<option selected value="-1">---Select---</option>
				<logic:present name="CompGroupMaintForm" property="compgroupList">
					<html:optionsCollection name="CompGroupMaintForm"
						property="compgroupList" value="componentGroupSeqNo" label="componentGroupName" />
				</logic:present>
				</html:select>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-5 control-label">Component</label>
			<div class="col-sm-2">
				<html:select name="CompGroupMaintForm" property="componentSeqNo" styleId="componentSeqNo"
				styleClass="form-control">
				<option selected value="-1">---Select---</option>
				
				<logic:equal name="CompGroupMaintForm" property="modelSeqNo" value="-1">
				
					<logic:present name="CompGroupMaintForm" property="compList">
						<html:optionsCollection name="CompGroupMaintForm"
							property="compList" value="componentSeqNo" label="componentName" />
					</logic:present>
				
				</logic:equal>
				
				<logic:notEqual name="CompGroupMaintForm" property="modelSeqNo" value="-1">
				
					<logic:present name="CompGroupMaintForm" property="compgroupList">
						<logic:iterate id="compgroupList" name="CompGroupMaintForm"
							property="compgroupList" type="com.EMD.LSDB.vo.common.CompGroupVO">

							<logic:equal name="CompGroupMaintForm"
								property="compGroupSeqNo"
								value="<%=String.valueOf(compgroupList.getComponentGroupSeqNo())%>">

								<logic:iterate id="compList" name="compgroupList"
									property="componentVO" type="com.EMD.LSDB.vo.common.ComponentVO" scope="page">

									<logic:equal name="CompGroupMaintForm"
										property="compGroupSeqNo"
										value="<%=String.valueOf(compList.getComponentGroupSeqNo())%>">
											<logic:equal name="CompGroupMaintForm"
											property="componentSeqNo"
											value="<%=String.valueOf(compList.getComponentSeqNo())%>">
												<option selected value="<%=compList.getComponentSeqNo()%>"><bean:write
													name="compList" property="componentName" /></option>
											</logic:equal>
											<logic:notEqual name="CompGroupMaintForm"
											property="componentSeqNo"
											value="<%=String.valueOf(compList.getComponentSeqNo())%>">
												<option value="<%=compList.getComponentSeqNo()%>"><bean:write
													name="compList" property="componentName" /></option>
											</logic:notEqual>
									</logic:equal>

								</logic:iterate>
							</logic:equal>
						</logic:iterate>
					</logic:present>
				
				</logic:notEqual>
				</html:select>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-5 control-label">Show latest published Specs Only</label>
			<div class="col-sm-2">
				<div class="checkbox">
					 <label>
					 	<html:checkbox property="showLatSpecFlag"/>
					 </label>
				</div>
			</div>
		</div>
		
		<div class="form-group">
				<div class="col-sm-12 text-center">
					<button class="btn btn-primary" type='button' id="addbtn" ONCLICK="javascript:fnSearchCompInOrdReport()"><bean:message key="screen.search" /></button>
				</div>
		 </div>
	
	</div>
	
	<logic:notEmpty name="CompGroupMaintForm" property="compInOrdList">
	<HR>
	
	<div class="row">
			<div class="col-sm-11 text-center"><h5 class="text-center"><strong>Search Criteria</strong></h5></div>
		</div>
		<div class="row">
			<div class="spacer10"></div>
		</div>
		<div class="row">
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Specification Type</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<bean:write name="CompGroupMaintForm" property="hdnSelSpecTypeName" />
			</div>
		</div>
		<div class="row">
			<div class="spacer10"></div>
		</div>
		<div class="row">	  
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Model</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<bean:write name="CompGroupMaintForm" property="hdnSelectedMdlNames" />
			</div>
		</div>
		<div class="row">
			<div class="spacer10"></div>
		</div>
		<div class="row">
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Component Group</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<bean:write name="CompGroupMaintForm" property="hdncompgrpName" />
			</div>
		</div>
		
		<div class="row">
			<div class="spacer10"></div>
		</div>
		
		<div class="row">
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Component</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<bean:write name="CompGroupMaintForm" property="hdncompName" />
			</div>
		</div>
		
		<div class="row">
			<div class="spacer10"></div>
		</div>
		
		<div class="row">
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Show latest published Specs Only</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<bean:write name="CompGroupMaintForm" property="hdnShowLatSpecFlag"/>
			</div>
		</div>
				
		<div class="spacer20"></div>
	
		
	
		<TABLE class="table table-bordered" id="tbCompInOrdersReport">
			<thead>
				<TR>
					<TH>
					<TABLE> 
						<TR>
							<TH class="text-center" width="15%">
								<logic:equal name="CompGroupMaintForm" property="columnheader" value="CompGroup">
									<logic:equal name="CompGroupMaintForm" property="orderbyCompGroupFlag" value="1">
										<a href="#tbCompInOrdersReport" class="text-black" style="text-decoration:none" onclick="javascript:fnSortCompGroup()">Component Group<p class="pull-right text-primary"><span class="glyphicon glyphicon glyphicon-sort-by-attributes" aria-hidden="true"></span></p></a>
									</logic:equal>
									<logic:equal name="CompGroupMaintForm" property="orderbyCompGroupFlag" value="2">
										<a href="#tbCompInOrdersReport" class="text-black" style="text-decoration:none" onclick="javascript:fnSortCompGroup()">Component Group<p class="pull-right text-primary"><span class="glyphicon glyphicon glyphicon-sort-by-attributes-alt" aria-hidden="true"></span></p></a>
									</logic:equal>
								</logic:equal>
								<logic:notEqual name="CompGroupMaintForm" property="columnheader" value="CompGroup">
									<a href="#tbCompInOrdersReport" class="text-black" style="text-decoration:none" onclick="javascript:fnSortCompGroup()">Component Group<p class="pull-right text-primary"><span class="glyphicon glyphicon glyphicon glyphicon-sort" aria-hidden="true"></span></p></a>
								</logic:notEqual>
							</TH>
						</TR>
					</TABLE>
					</TH>
					<TH>
					<TABLE>
						<TR>
							<TH class="text-center" width="15%">
								<logic:equal name="CompGroupMaintForm" property="columnheader" value="Component">
									<logic:equal name="CompGroupMaintForm" property="orderbyCompFlag" value="1">
										<a href="#tbCompInOrdersReport" class="text-black" style="text-decoration:none" onclick="javascript:fnSortComponent()">Component<p class="pull-right text-primary"><span class="glyphicon glyphicon glyphicon-sort-by-attributes" aria-hidden="true"></span></p></a>
									</logic:equal>
									<logic:equal name="CompGroupMaintForm" property="orderbyCompFlag" value="2">
										<a href="#tbCompInOrdersReport" class="text-black" style="text-decoration:none" onclick="javascript:fnSortComponent()">Component<p class="pull-right text-primary"><span class="glyphicon glyphicon glyphicon-sort-by-attributes-alt" aria-hidden="true"></span></p></a>
									</logic:equal>
								</logic:equal>
								<logic:notEqual name="CompGroupMaintForm" property="columnheader" value="Component">
									<a href="#tbCompInOrdersReport" class="text-black" style="text-decoration:none" onclick="javascript:fnSortComponent()">Component<p class="pull-right text-primary"><span class="glyphicon glyphicon glyphicon-sort" aria-hidden="true"></span></p></a>
								</logic:notEqual>
							</TH>
						</TR>
					</TABLE>
					</TH>
					<TH width="15%">Order Number</TH>
					<TH width="10%">Spec Status</TH>
					<TH width="20%">Customer Name</TH>
				</TR>
			</thead>
			<tbody>
				<logic:iterate id="compgrp" name="CompGroupMaintForm"
					property="compInOrdList" type="com.EMD.LSDB.vo.common.CompGroupVO">
					<TR>
						<logic:notEmpty name="compgrp" property="componentVO">
							<% int rowspancnt=0; %>
							<logic:iterate id="comp" name="compgrp" property="componentVO" indexId="compCnt">
								<bean:size id="CompLen" name="compgrp" property="componentVO" />
								<logic:notEmpty name="comp" property="ordersUsed">
									<bean:size id="OrdLen" name="comp" property="ordersUsed" />	
									<% rowspancnt = rowspancnt + OrdLen.intValue()/3; %>
								</logic:notEmpty>
								<logic:empty name="comp" property="ordersUsed">
									<% rowspancnt = rowspancnt + 1; %>
								</logic:empty>
							</logic:iterate>
							<bean:size id="CompLen" name="compgrp" property="componentVO" />							
							<TD rowspan="<%= rowspancnt %>" CLASS= "text-center v-midalign"style="word-wrap: 
								break-word;"><bean:write name="compgrp" 
								property="componentGroupName" />
							</TD>
						</logic:notEmpty>
						<logic:empty name="compgrp" property="componentVO">				
							<TD CLASS= "notempty text-center v-midalign"style="word-wrap: 
								break-word;"><bean:write name="compgrp" 
								property="componentGroupName" />
							</TD>
						</logic:empty>
				
							<logic:notEmpty name="compgrp" property="componentVO">
								
								<logic:iterate id="comp" name="compgrp" property="componentVO" indexId="compCnt">
										<% int rowspancompcnt=0; %>
										<bean:size id="CompLen" name="compgrp" property="componentVO" />
										<logic:notEmpty name="comp" property="ordersUsed">
											<bean:size id="OrdLen" name="comp" property="ordersUsed" />	
											<% rowspancompcnt = OrdLen.intValue()/3; %>
											<td style="word-wrap: break-word" class="text-center v-midalign" rowspan="<%= rowspancompcnt %>">
										</logic:notEmpty>
										<logic:empty name="comp" property="ordersUsed">
											<td style="word-wrap: break-word" class="text-center v-midalign">
										</logic:empty>
										
											<logic:notEmpty name="comp"	property="componentName">
											<logic:equal name="comp" property="compSourceFlag" value="O"> 
												<font color="red">
													<bean:write name="comp" property="componentName" />
												</font>
											</logic:equal>	
											<logic:equal name="comp" property="compSourceFlag" value="M"> 
												<bean:write name="comp" property="componentName" />
											</logic:equal>							
										</logic:notEmpty> 
										<logic:empty name="comp" property="componentName">
											&nbsp;
										</logic:empty></td>
										
											<logic:notEmpty name="comp" property="ordersUsed">
												<bean:size id="OrdLen" name="comp" property="ordersUsed" />	
												<logic:iterate id="orders" name="comp" property="ordersUsed" indexId="count">
												
												<%-- Modified for CR_112 From 2 cols to 3 --%>
												<bean:define id="row" value="<%= String.valueOf((count.intValue()+1) % 3) %>" />
												<%-- Ends here --%>

													<TD class="text-center v-midalign">
														<logic:notEmpty name="orders">
															<bean:write name="orders" />
														</logic:notEmpty>
														<logic:empty name="orders">
															&nbsp;
														</logic:empty>
													</TD>													

													<logic:notEqual name="count" value="0">
														<logic:equal name="row" value="0">
															</TR>
															<TR>
														</logic:equal>
													</logic:notEqual>

												</logic:iterate>
											</logic:notEmpty> 
											<logic:empty name="comp" property="ordersUsed">
												<TD class="text-center v-midalign">&nbsp;</TD>
												<TD class="text-center v-midalign">&nbsp;</TD>
												<%-- Added for CR_112 To bring the Customer Name --%>
												<TD class="text-center v-midalign">&nbsp;</TD>										
												<%-- CR_112 Ends here --%>
												</TR>
												<TR>
											</logic:empty>

								</logic:iterate>
							</logic:notEmpty>
		<%-- 
							<logic:empty name="compgrp" property="componentVO">
								<tr>
									<td CLASS="text-center v-midalign">&nbsp;</td>
		
									<td CLASS="text-center v-midalign">&nbsp;</td>
		
									<td CLASS="text-center v-midalign">&nbsp;</td>
									<%-- Added one more column for Customer name - CR_112
									<td CLASS="text-center v-midalign">&nbsp;</td>
									
								</tr>
							</logic:empty>
		--%>
					</TR>			
				</logic:iterate>
			</TBODY>
		</TABLE>	
		
		<div class="spacer20"></div>
		
		<div class="form-group">
			<div class="col-sm-12 text-center">
				<button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnSearchCompInOrdReportExcel()"><bean:message key="ExportToExcel" /></button>
			</div>
		</div>
		
		<div class="spacer50"></div>
		
	</logic:notEmpty>
	
	<html:hidden property="hdnSelSpecTypeName" />	
	<html:hidden property="hdnSelectedMdlNames" />
	<html:hidden property="hdnSelSpecTypeNo" />
	<html:hidden property="hdncompgrpName" />
	<html:hidden property="hdncompName" />
	<html:hidden property="hdnShowLatSpecFlag" />
	<!-- Added for CR-121 for sorting by Vb106565 starts here -->
	<html:hidden property="orderbyCompGroupFlag"/>
	<html:hidden property="orderbyCompFlag"/>
	<html:hidden property="columnheader"/>
	<!-- Added for CR-121 for sorting by Vb106565 ends here -->
		
</html:form>


</div>