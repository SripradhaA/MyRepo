<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<SCRIPT type="text/JavaScript" SRC="js/ClaInOrdersReport.js"></SCRIPT>

<script>
    $(document).ready(function() { 
    	$("#specType").select2({theme:'bootstrap'}); 
    	$("#SltModel").select2({theme:'bootstrap'});
    	$("#SltSection").select2({theme:'bootstrap'}); 
    	$("#SltSubSection").select2({theme:'bootstrap'}); 
	    $('#tbClaInOrdersReport').DataTable({
	    	searching: false,
	    	lengthChange: false,
	    	paging:false,
	    	ordering:false,
	    	info: false,
	    	scrollY: '400px',
	    	scrollCollapse: true
	   		
	    }); 
    })
</script>

<div class = "container" width="100">

<html:form action="/modelAddClauseAction.do">
	<html:hidden property="headerFlag" />
	<html:hidden property="rowIndex" />
	
	
	<h4 class ="text-center"><bean:message key="Application.Screen.ClainordersReport" /></h4>
	<div class="row">
		<div class="errorlayerhide" id="errorlayer"></div>
	</div>
	
	<logic:present name="ModelAddClauseForm" property="messageID"
		scope="request">
		
        <bean:define id="messageID" name="ModelAddClauseForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>        
                
	</logic:present>
	
	<logic:present name="ModelAddClauseForm" property="errorMessage"
		scope="request">
		<script>
            hideErrors();
            addMessage('<bean:write name="ModelAddClauseForm" property="errorMessage"/>');
            showScrollErrors();
        </script>
	</logic:present>

	<logic:present name="ModelAddClauseForm" property="clauseList"
		scope="request">
		<bean:size name="ModelAddClauseForm" id="clsize"
			property="clauseList" />
	</logic:present>
		
	<logic:match name="ModelAddClauseForm" property="method"
		value="fetchClauses">
		<logic:lessThan name="clsize" value="1">
			<script> 
	              fnNoRecords();
	        </script>
		</logic:lessThan>
	</logic:match>
	
	<logic:present name="ModelAddClauseForm" property="modelList">
		<bean:size id="modelSeqNo" name="ModelAddClauseForm"
			property="modelList" />
	</logic:present>
	<logic:present name="ModelAddClauseForm" property="subSectionList">
		<bean:size id="subSectionSeqNo" name="ModelAddClauseForm"
			property="subSectionList" />
	</logic:present>
	<logic:present name="ModelAddClauseForm" property="sectionList">
		<bean:size id="sectionSeqNo" name="ModelAddClauseForm"
			property="sectionList" />
	</logic:present>
	<logic:present name="ModelAddClauseForm" property="componentVO">
		<bean:size id="noOfClauses" name="ModelAddClauseForm"
			property="componentVO" />
		<logic:lessThan name="noOfClauses" value="1">
			<script>
				var id='888';
				addMsgNum(id);
				showScrollErrors();
			</script>
		</logic:lessThan>
	</logic:present>
	
	<div class="row">
		<div class="spacer20"></div>
	</div>
	
	<div class="form-horizontal">
	
		<div class="form-group required-field">
			<label class="col-sm-5 control-label">Specification Type&nbsp;</label>
			<div class="col-sm-3">
				<html:select name="ModelAddClauseForm"
					property="specTypeNo" styleClass="form-control" styleId="specType"
					onchange="javascript:fetchModels()">
					<option selected value="-1">---Select---</option>
					<html:optionsCollection name="ModelAddClauseForm"
						property="specTypeList" value="spectypeSeqno"
						label="spectypename" />
				</html:select>
			</div>
		</div>
		
		<div class="form-group required-field">
			<label class="col-sm-5 control-label">Model</label>
			<div class="col-sm-3">
				<html:select styleClass="form-control" styleId="SltModel"
					name="ModelAddClauseForm" property="modelSeqNo"
					onchange="javascript:fnLoadSection()">
	
					<html:option value="-1">---Select---</html:option>
	
					<logic:present name="ModelAddClauseForm" property="modelList">
						<html:optionsCollection name="ModelAddClauseForm"
							property="modelList" label="modelName" value="modelSeqNo" />
					</logic:present>
				</html:select>
			</div>
		</div>
		
		<div class="form-group required-field">
			<label class="col-sm-5 control-label">Section</label>
			<div class="col-sm-3">
				<html:select styleClass="form-control" styleId="SltSection"
					name="ModelAddClauseForm" property="sectionSeqNo"
					onchange="javascript:fnLoadSubSection()">
	
					<logic:equal property="sectionSeqNo" name="ModelAddClauseForm"
						value="">
						<html:option value="-1">---Select---</html:option>
					</logic:equal>
					<html:option value="-1">---Select---</html:option>
	
					<logic:present name="ModelAddClauseForm" property="sectionList">
						<html:optionsCollection name="ModelAddClauseForm"
							property="sectionList" label="sectionDisplay"
							value="sectionSeqNo" />
					</logic:present>
				</html:select>
			</div>
		</div>
		
		<div class="form-group required-field">
			<label class="col-sm-5 control-label">SubSection</label>
			<div class="col-sm-3">
				<html:select styleClass="form-control" styleId="SltSubSection"
					name="ModelAddClauseForm" property="subSectionSeqNo"
					onchange="javascript:fnLoadCompGroup()"> <!-- Modified for CR_88 -->
					<logic:equal property="subSectionSeqNo"
						name="ModelAddClauseForm" value="">
						<html:option value="-1">---Select---</html:option>
					</logic:equal>
					<html:option value="-1">---Select---</html:option>
					<logic:present name="ModelAddClauseForm"
						property="subSectionList">
						<html:optionsCollection name="ModelAddClauseForm"
							property="subSectionList" label="subSecDisplay"
							value="subSecSeqNo" />
					</logic:present>
				</html:select>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-5 control-label">Show latest published Specs Only</label>
			<div class="col-sm-2">
				<div class="checkbox">
	   				 <label>
						<html:checkbox property="showLatSpecFlag" />
					</label>
				</div>
			</div>
		</div>
		
	</div>
	
	<div class="row">
		<div class="spacer30"></div>
	</div>
	
	<html:hidden property="hdnSelectedSpecType" />
	<html:hidden property="hdnSelectedModel" /> 
	<html:hidden property="hdnSelectedSection" />
	<html:hidden property="hdnSelectedSubSection" />
	<html:hidden property="hdnShowLatSpecFlag" />
	
	
	<logic:greaterEqual name="noOfClauses" value="1">
	<h4 class ="text-center"><bean:message key="Application.Screen.SelectClause" /></h4>
	
	<div class="row">
		<div class="spacer20"></div>
	</div>
		
	<TABLE class="table table-bordered table-hover" id="tbClaInOrdersReport" name="sessionScope.ModelAddClauseForm.componentVO">
		<thead>
			<TR>
				<TH WIDTH="5%" CLASS='text-center'>Select</TH>
				<TH WIDTH="5%" CLASS='text-center'>Clause No</TH>
				<TH WIDTH="15%" CLASS='text-center'>Clause Description</TH>
				<TH WIDTH="8%" CLASS='text-center'>Engineering Data</TH>
				<TH WIDTH="8%" CLASS='text-center'>Component</TH>
			</TR>
		</thead>
		<tbody>
			<logic:iterate id="compParent" name="ModelAddClauseForm"
				property="componentVO" scope="request"
				type="com.EMD.LSDB.vo.common.ClauseVO" indexId="clausecount">
				<TR">
					<TD CLASS="text-center v-midalign">
							<html:radio
							property="clauseSeqNo" 
							value='<%=String.valueOf(compParent.getClauseSeqNo())%>' 
							onclick="javascript:fnFetchClauseversions()" />
						</TD>
					<TD CLASS="text-center v-midalign"><logic:present name="compParent"
						property="clauseNum">
						<bean:write name="compParent" property="clauseNum" />
					</logic:present></TD>
					<html:hidden name="compParent" property="versionNo" />
					<TD valign="top">
					<%-- CR-128 - Updated for Pdf issue --%>
						<%if(String.valueOf(compParent.getClauseDesc()).startsWith("<p>"))
						{%>
							<%=(String.valueOf(compParent.getClauseDesc()))%>
						<%}else{ %>	
							<%=(String.valueOf(compParent.getClauseDesc())).replaceAll("\n","<br>")%>
						<%}%>
					<%-- CR-128 - Updated for Pdf issue Ends Here--%>
						 
					
						<logic:notEmpty name="compParent" property="tableArrayData1">
							<div class="spacer10"></div>
								<TABLE class="table table-bordered">
		 						<logic:iterate id="outter" name="compParent"
								property="tableArrayData1" indexId="counter">
								<!-- Added  For CR_93 -->
									<bean:define name="compParent" property="tableDataColSize" id="tableDataColSize" />
								<bean:define id="row" name="counter" />
								<tr>
									<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
										<logic:equal name="row" value="0">
											<td valign="top" width="5%" style="border-top: 1px solid #dddddd;" class="text-center v-midalign"><b><font
												color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></b></font>
											</td>
										</logic:equal>
										<logic:notEqual name="row" value="0">
											<td valign="top" width="5%" class="text-center v-midalign"><%=(tabrow==null)? "&nbsp;":tabrow%>
											</td>
										</logic:notEqual>
									</logic:iterate>
								</tr>
							</logic:iterate></table>
						</logic:notEmpty>
					</TD>
					<TD CLASS="text-center v-midalign">
							 <logic:present name="compParent"
								property="edlNO">
								<logic:iterate id="engDataEdlNo" name="compParent"
									property="edlNO">
									<bean:message key="screen.edl" />
									<bean:write name="engDataEdlNo" />
									<br>
								</logic:iterate>
							</logic:present> 
							<!--  CR 87 -->
							<logic:present name="compParent" property="refEDLNO">

								<logic:iterate id="engDataRefEdlNo" name="compParent"
									property="refEDLNO">
									<bean:message key="screen.refEdl.start" />
									<bean:write name="engDataRefEdlNo" />
									<bean:message key="screen.refEdl.end" />
									<br>
								</logic:iterate>
							</logic:present>
							<logic:present name="compParent"
								property="partOF">
								<logic:iterate id="engPartOfCd" name="compParent"
									property="partOF">
									<logic:notEqual name="engPartOfCd" value="0">
										<bean:message key="screen.partOf" />
										<bean:write name="engPartOfCd" />
										<br>
									</logic:notEqual>
								</logic:iterate>
							</logic:present> <logic:present name="compParent"
								property="dwONumber">
								<logic:notEqual name="compParent" property="dwONumber"
									value="0">
									<bean:message key="screen.dwoNo" />
									<bean:write name="compParent" property="dwONumber" />
									<br>
								</logic:notEqual>
							</logic:present> <logic:present name="compParent"
								property="partNumber">
								<logic:notEqual name="compParent" property="partNumber"
									value="0">
									<bean:message key="screen.partNo" />
									<bean:write name="compParent" property="partNumber" />
									<br>
								</logic:notEqual>
							</logic:present> <!--Price Book Number is added in the Engineering data Section, modified on 30-09-08 by ps5722-->
							<logic:present name="compParent" property="priceBookNumber">
								<logic:notEqual name="compParent" property="priceBookNumber"
									value="0">
									<bean:message key="screen.priceBookNo" />&nbsp; <bean:write
										name="compParent" property="priceBookNumber" />
									<br>
								</logic:notEqual>
							</logic:present> <logic:present name="compParent"
								property="standardEquipmentDesc">
								<bean:write name="compParent" property="standardEquipmentDesc" />
								<br>
							</logic:present> <logic:present name="compParent"
								property="engDataComment">
								<bean:define id="engDatCmnt" name="compParent"
									property="engDataComment" />
								<%=engDatCmnt %>
								<br>
							</logic:present>
					</TD>
					<logic:notEmpty name="compParent" property="compGroupVO">
						<TD class="text-center v-midalign"><logic:iterate
							id="compList" name="compParent" property="compGroupVO"
							type="com.EMD.LSDB.vo.common.CompGroupVO" scope="page"
							indexId="compntIndex">
							<logic:present name="compList" property="compVO">
								<bean:define id="comp" name="compList" property="compVO" />
								<logic:notEqual name="compntIndex" value="0"> ;<br>
								</logic:notEqual>
								<bean:write name="comp" property="componentName" />
							</logic:present>
						</logic:iterate></TD>
					</logic:notEmpty>
					<logic:empty name="compParent" property="compGroupVO">
						<td class="text-center v-midalign">&nbsp;</td>
					</logic:empty>
				</TR>
			</logic:iterate>
		</tbody>
	</TABLE>

	</logic:greaterEqual>
	
	<div class="row">
		<div class="spacer10"></div>
	</div>
	
	<html:hidden property="hdnClauseSeqNo"/>
	<html:hidden property="modifyClauseFlag"/>
	 	<logic:equal name="ModelAddClauseForm" property="modifyClauseFlag" value="Y">

	<logic:present name="ModelAddClauseForm" property="clauseList"
		scope="request">
		<bean:size name="ModelAddClauseForm" id="clsize"
			property="clauseList" />
	</logic:present>

	<logic:present name="ModelAddClauseForm"
		property="stdEquipmentList" scope="request">
		<bean:size name="ModelAddClauseForm" id="stdsize"
			property="stdEquipmentList" />
	</logic:present>

	<logic:present name="ModelAddClauseForm"
		property="clauseVersions" scope="request">
		<bean:size name="ModelAddClauseForm" id="allversize"
			property="clauseVersions" />
	</logic:present>
	
	<html:hidden property="hdnClauseVersionNo" />
	
	<logic:greaterThan name="allversize" value="0">
	
	<logic:present name="ModelAddClauseForm"
	property="clauseVersions">						
	
	<HR>
	
	<div class="spacer10"></div>
	
	<h4 class ="text-center">Select Clause Revision</h4>
			
	<div class="spacer20"></div>
		
	<TABLE class="table table-bordered table-hover">
		<thead>
			<TR>
				<TH WIDTH="5%" CLASS='text-center'>Select</TH>
				<TH WIDTH="5%" CLASS='text-center'>Version No</TH>
				<TH WIDTH="15%" CLASS='text-center'>Clause Description</TH>
				<TH WIDTH="5%" CLASS='text-center'>Engineering Data</TH>
				<TH WIDTH="5%" CLASS='text-center'>Customer</TH>
				<TH WIDTH="5%" CLASS='text-center'>Modified By</TH>
				<TH WIDTH="5%" CLASS='text-center'>Modified Date</TH>
				<TH WIDTH="5%" CLASS='text-center'>Default</TH>
			</TR>
		</thead>
		<tbody>

		<logic:iterate id="clauseRev" name="ModelAddClauseForm"
			property="clauseVersions" type="com.EMD.LSDB.vo.common.ClauseVO">

			<TR><%--Edited for CR_121--%>
				<TD class="text-center v-midalign">
				<html:radio value="<%=String.valueOf(clauseRev.getVersionNo())%>"
							property="versionNo" styleId="versionNoRadio" />
							<%--Edited for CR_121 Ends--%>
				</TD>
				<TD class="text-center v-midalign"><bean:write name="clauseRev"
					property="versionNo" /></TD>

				<TD valign="top" width="50%"height="50%"><bean:define
					name="clauseRev" property="clauseDesc" id="clauseDesc" />
					<!-- CR-128 - Updated for Pdf issue -->
						<%String strClauseDesc =  String.valueOf(clauseDesc);
						if(strClauseDesc.startsWith("<p>")){%>
							<%=(String.valueOf(clauseDesc))%>
						<%}else{ %>	
							<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>							
						<%}%>

					<logic:notEmpty name="clauseRev" property="tableArrayData1">
		
					<logic:iterate id="outter" name="clauseRev"
										property="tableArrayData1" indexId="counter">
								<!-- CR-128 - Updated for Pdf issue Ends Here-->
							<table width="100%" class="table table-bordered">

							<bean:define id="row" name="counter" />
							<!-- Added  For CR_93 -->
									<bean:define name="clauseRev" property="tableDataColSize" id="tableDataColSize" />
							<tr>
								<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
									<logic:equal name="row" value="0">
										<td width="5%" CLASS='text-center v-midalign'><b><font
											color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></b></font>
										</td>
									</logic:equal>
									<logic:notEqual name="row" value="0">
										<td width="5%" CLASS='text-center v-midalign'><%=(tabrow==null)? "&nbsp;":tabrow%>
										</td>
									</logic:notEqual>
								</logic:iterate>
							</tr>
							</table>
						</logic:iterate>
					</logic:notEmpty>
				</TD>
				<TD class="text-center v-midalign">
				 <logic:notEmpty name="clauseRev"
					property="edlNO">

					<logic:iterate id="edl" name="clauseRev" property="edlNO">
						<bean:message key="screen.edl" />&nbsp;
          						<bean:write name="edl" />
						<BR>
					</logic:iterate>

				</logic:notEmpty> 
					<logic:notEmpty	name="clauseRev" property="refEDLNO">
					<logic:iterate id="refedl" name="clauseRev" property="refEDLNO">
						<bean:message key="screen.refEdl.start" />&nbsp;
				           <bean:write name="refedl" />
						<bean:message key="screen.refEdl.end" />&nbsp;						           
						<BR>
					</logic:iterate>

				</logic:notEmpty>
				<logic:notEmpty name="clauseRev"
					property="partOF">
					<logic:iterate id="code" name="clauseRev" property="partOF">
						<bean:message key="screen.partOf" />&nbsp;
         					 <bean:write name="code" />
						<BR>
					</logic:iterate>

				</logic:notEmpty> <logic:greaterThan name="clauseRev"
					property="dwONumber" value="0">
					<bean:message key="screen.dwoNo" />  &nbsp;
          				<bean:write name="clauseRev" property="dwONumber" />
					<BR>
				</logic:greaterThan> <logic:greaterThan name="clauseRev"
					property="partNumber" value="0">
					<bean:message key="screen.partNo" /> &nbsp;
           			<bean:write name="clauseRev" property="partNumber" />
					<BR>
				</logic:greaterThan> <logic:greaterThan name="clauseRev"
					property="priceBookNumber" value="0">
					<bean:message key="screen.priceBookNo" /> &nbsp;
           			<bean:write name="clauseRev" property="priceBookNumber" />
					<BR>
				</logic:greaterThan> <logic:present name="clauseRev"
					property="standardEquipmentDesc">
					<bean:write name="clauseRev" property="standardEquipmentDesc" />
					<BR>
				</logic:present> <logic:present name="clauseRev"
					property="comments">
					<bean:write name="clauseRev" property="comments" />
					<BR>
				</logic:present></TD>

				<TD class="text-center v-midalign"><logic:empty
					name="clauseRev" property="customerName">
	            &nbsp;
	            </logic:empty> <logic:notEmpty name="clauseRev"
					property="customerName">
					<bean:write name="clauseRev" property="customerName" />
				</logic:notEmpty></TD>


				<TD class="text-center v-midalign"><logic:empty
					name="clauseRev" property="modifiedBy">
	            &nbsp;
	            </logic:empty> <logic:notEmpty name="clauseRev"
					property="modifiedBy">
					<bean:write name="clauseRev" property="modifiedBy" />
				</logic:notEmpty></TD>


				<TD class="text-center v-midalign"><logic:empty
					name="clauseRev" property="modifiedDate">
	            &nbsp;
	            </logic:empty> <logic:notEmpty name="clauseRev"
					property="modifiedDate">
					<bean:write name="clauseRev" property="modifiedDate" />
				</logic:notEmpty></TD>
				<TD class="text-center v-midalign"><bean:write name="clauseRev"
					property="defaultFlag" /> <html:hidden name="clauseRev"
					property="defaultFlag" /> </TD>

			</TR>
		</logic:iterate>
		</tbody>
	</TABLE>
		
	<div class="row">
		<div class="col-sm-11 text-center">
			<div class="form-group">
	              <button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:fnSearch('N')">Search</button>
	       </div>
		</div>
	</div>
		
	</logic:present>
	</logic:greaterThan>
	</logic:equal>
		
	<logic:present name="ModelAddClauseForm" property="orderList"
	scope="request">
	
	<bean:size name="ModelAddClauseForm" id="ordsize"
		property="orderList" />
  		</logic:present>
  	<logic:greaterThan name="ordsize" value="0">
	<logic:present name="ModelAddClauseForm"
	property="orderList">
	
	<div class="col-sm-12">
		<hr/>
	</div>
	
	<logic:iterate id="orderDetail" name="ModelAddClauseForm"
			property="orderList" type="com.EMD.LSDB.vo.common.ClauseVO">
	<div id="listorders">
		<div class="col-sm-11 text-center">
			<div class="form-group">
				<h5 class ="text-center"><strong>Search Criteria</strong></h5>
	       </div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Specification Type</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<bean:write name="ModelAddClauseForm"
						property="hdnSelectedSpecType" />
			</div>
		</div>
		
		<div class="col-sm-12">
			<div class="spacer10"></div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Model</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<bean:write name="ModelAddClauseForm"
						property="hdnSelectedModel" />
			</div>
		</div>
		
		<div class="col-sm-12">
			<div class="spacer10"></div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Section</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<bean:write name="ModelAddClauseForm"
						property="hdnSelectedSection" />
			</div>
		</div>
		
		<div class="col-sm-12">
			<div class="spacer10"></div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>SubSection</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<bean:write name="ModelAddClauseForm"
						property="hdnSelectedSubSection" />
			</div>
		</div>
		
		<div class="col-sm-12">
			<div class="spacer10"></div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Clause Description</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-4 text-left">
				<%-- CR-128 - Updated for Pdf issue --%>
					<%if(String.valueOf(orderDetail.getClauseDesc()).startsWith("<p>"))
					{%>
						<%=(String.valueOf(orderDetail.getClauseDesc()))%>
					<%}else{ %>	
						<%=(String.valueOf(orderDetail.getClauseDesc())).replaceAll("\n","<br>")%></TD>
					<%}%>
				<%-- CR-128 - Updated for Pdf issue Ends Here--%>
			</div>
		</div>
		
		<div class="col-sm-12">
			<div class="spacer10"></div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-3 col-sm-offset-2 text-right"><strong>Show latest published Specs Only</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<bean:write name="ModelAddClauseForm"
					property="hdnShowLatSpecFlag" />
			</div>
		</div>
		
	</div>
			
	<div class="row">
		<div class="spacer20"></div>
	</div>
		
	<logic:notEmpty name="orderDetail" property="orderList">
	
	
	
	<TABLE class="table table-bordered table-hover">
		<thead>
			<TR>
				<TH WIDTH="5%" CLASS='text-center'>Order Number</TH>
				<TH WIDTH="10%" CLASS='text-center'>Spec Status</TH>
				<TH WIDTH="10%" CLASS='text-center'>Customer Name</TH>
			</TR>
		</thead>
		
		<tbody>
			<logic:iterate id="Orderused" name="orderDetail" 
				property="orderList" type="com.EMD.LSDB.vo.common.OrderVO">
				<TR>
					<TD CLASS='text-center v-midalign'><bean:write name="Orderused"
						property="orderNo" /> 
					</TD>
					<TD CLASS='text-center v-midalign'><bean:write name="Orderused"
						property="statusDesc" /> 
					</TD>
                    <TD CLASS='text-center v-midalign'><bean:write name="Orderused"
						property="customerName" /> 
					</TD>
				</TR>
			</logic:iterate>
		</tbody>
	</TABLE>
	
	<div class="row">
		<div class="spacer20"></div>
	</div>
	
	<div class="col-sm-11 text-center">
		<div class="form-group">
              <button class="btn btn-primary" type='button' id="searchButton" ONCLICK="fnClaInOrdersReportExcel()">Export to Excel</button>
       </div>
	</div>
	
	</logic:notEmpty>
	<logic:empty name="orderDetail" property="orderList">
	
	 <script> 
       fnNoRecords();
      </script>

	</logic:empty>
	</logic:iterate>
	</logic:present>
	<html:hidden name="ModelAddClauseForm" property="intScreenId" />
	
	</logic:greaterThan>
	
	<div class="row">
		<div class="spacer50"></div>
	</div>
			
</html:form>
</div>