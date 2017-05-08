<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<SCRIPT LANGUAGE="JavaScript" SRC="js/OrderSectionView.js"></SCRIPT>
</head>

<body>
<div class="container" width="80%">
	<div class="spacer20"></div>
	<div class="row">
		<div class="errorlayerhide" id="errorlayer"></div>
	</div>
	<logic:present name="OrderSectionForm" property="messageID"	scope="request">
			<bean:define id="messageID" name="OrderSectionForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>

	</logic:present>
	
<html:form action="OrderSection.do" >
	<%String temp=""; %>
	<logic:iterate id="clausedetail" name="OrderSectionForm"
		property="clauseDetail" type="com.EMD.LSDB.vo.common.ClauseVO" 
			scope="request">	
		<% int i=1; %>	
		<logic:notEmpty name="clausedetail" property="clauseGroup">	
			<logic:iterate id="clause" name="clausedetail" property="clauseGroup"
					type="com.EMD.LSDB.vo.common.ClauseVO" scope="page" indexId="clauseCount">
				<logic:equal name="clause" property="currRevFlag" value="Y">
					<%if(!"Y".equals(temp)){ %>
					
					<h4 class="text-center"><bean:message key="Application.Screen.ViewDeletedClausesCurrRevision" /></h4>
					<p class="text-center"><mark><bean:message key="Application.Screen.ViewDeletedClausesNoteCurrRevision" /></mark></p>
	
					<%temp="Y"; } %>	
					<% if (i==1) { %>			
						<logic:iterate id="compGroup" name="clausedetail" scope="page"
							property="compGroupVO" type="com.EMD.LSDB.vo.common.CompGroupVO">
								<TABLE BORDER=0 ALIGN="center" WIDTH="85%" CELLPADDING=0 CELLSPACING=0>
								<logic:notEqual name="secSeq" value="<%=String.valueOf(compGroup.getSectionSeqNo())%>">
									<div class="row">
										<div class="col-sm-12 highlightDark text-center">
											<div class="text-center"><strong>
												<bean:write name="compGroup" property="secCode" />.
												<bean:write name="compGroup" property="sectionName" />
											</strong></div>
										</div>
									</div>
								</logic:notEqual>
									<div class="row">
										<div class="col-sm-12 bg-info text-center">
											<div class="text-center"><strong>
												<bean:write name="compGroup" property="secCode" />.
												<bean:write name="compGroup" property="subSectionCode" />&nbsp;
												<bean:write name="compGroup" property="subSectionName" />
											</strong></div>
										</div>
									</div>
									
							<bean:define id="secSeq" name="compGroup" property="sectionSeqNo"/>
							<bean:define id="subSecSeq" name="compGroup" property="subSectionSeqNo"/>
							
						</logic:iterate>
					<div class="row">
						<div class="spacer10"></div>
					</div>
					<TABLE class="table table-bordered">
					<thead>
						<TR>
							<TH CLASS='text-center' WIDTH="5%">Select</TH>
							<TH CLASS='text-center' WIDTH="10%">Clause No</TH>
							<TH CLASS='text-center' WIDTH="45%">Clause Description</TH>
							<TH CLASS='text-center' WIDTH="10%">Engineering Data</TH>
							<TH CLASS='text-center' WIDTH="10%">Component</TD>
						</TR>
					</thead>
		 			<% } %>
		 			<tbody>
						<TR>
							<TD WIDTH="5%" CLASS="v-midalign text-center"><html:radio property="clauseSeqNo"
								value='<%=String.valueOf(clause.getClauseSeqNo())%>'/>
							</TD>
							
							<logic:notEmpty name="clause" property="clauseNum">
								<TD CLASS="text-center v-midalign" WIDTH="10%">
									<bean:write name="clause" property="clauseNum" />
								</TD>
							</logic:notEmpty>
	
							<logic:empty name="clause" property="clauseNum">
									<TD WIDTH="10%">&nbsp;</TD>
							</logic:empty>
	
							<logic:notEmpty name="clause" property="clauseDesc">
								<TD WIDTH="45%">
									<span style="width:500px">
										 <DIV align="left" STYLE="float:left;height:60;width:500;overflow: auto ; ">
											<bean:define name="clause" property="clauseDesc" id="clauseDesc" />
											<!-- CR-128 - Updated for Pdf issue -->
											<%String strClauseDesc =  String.valueOf(clauseDesc);
											if(strClauseDesc.startsWith("<p>")){%>
												<%=(String.valueOf(clauseDesc))%>
											<%}else{ %>	
												<%-- double space replacement with &nbsp is added to display the spaces as entered. This change is applicable for only modify spec --%>
												<%=(String.valueOf(clauseDesc)).replaceAll("  ","&nbsp;&nbsp;").replaceAll("\n","<br>")%>							
											<%}%>
										<!-- CR-128 - Updated for Pdf issue Ends Here-->
											
												<table class="table">
													<logic:notEmpty name="clause" property="tableArrayData1">
													 &nbsp;
													 <logic:iterate id="outter" name="clause" property="tableArrayData1"
																indexId="counter">
															<bean:define id="row" name="counter" />
															<!-- Added  For CR_93 -->
															<bean:define name="clause" property="tableDataColSize" id="tableDataColSize" />
															<tr>
																<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
						
																	<logic:equal name="row" value="0">
																		<td width="5%"><b><font
																			color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></font></b>
																		</td>
																	</logic:equal>
						
																	<logic:notEqual name="row" value="0">
																		<td width="5%"><%=(tabrow==null)? "&nbsp;":tabrow%>
																		</td>
																	</logic:notEqual>
																</logic:iterate>
															</tr>
														</logic:iterate>
													</logic:notEmpty>
												</table>
											</DIV>
										</span>
									</TD>
							</logic:notEmpty>
							<logic:empty name="clause" property="clauseDesc">
								<TD WIDTH="45%">
									&nbsp;
								</TD>
							</logic:empty>
							
							<TD CLASS="text-center" WIDTH="10%">
								<logic:notEmpty name="clause" property="edlNo">
									<logic:iterate id="edl" name="clause" property="edlNo">
										<logic:notEmpty name="edl">
											<bean:message key="viewSpec.EDL" />&nbsp;<bean:write name="edl" />
											<br>
										</logic:notEmpty>
									</logic:iterate>
								</logic:notEmpty>
								<logic:notEmpty	name="clause" property="refEDLNo">
									<logic:iterate id="refedl" name="clause" property="refEDLNo">
										<logic:notEmpty name="refedl">
											<bean:message key="viewSpec.refEDL.start" />&nbsp;<bean:write
												name="refedl" />
											<bean:message key="viewSpec.refEDL.end" />
											<br>
										</logic:notEmpty>
									</logic:iterate>
								</logic:notEmpty> 
								<logic:notEmpty name="clause" property="partOfCode">
									<logic:iterate id="partof" name="clause" 
										property="partOfCode">
										<logic:notEmpty name="partof">
											<bean:message key="viewSpec.PartOf" />&nbsp;<bean:write
												name="partof" />&nbsp;
											<br>
										</logic:notEmpty>
									</logic:iterate>
								</logic:notEmpty> 
								<logic:notEmpty name="clause" property="dwONumber">
									<logic:notEqual name="clause" property="dwONumber" value="0">
										<bean:message key="viewSpec.DWO" />&nbsp;
										<bean:write	name="clause" property="dwONumber" />
										<br>
									</logic:notEqual>
								</logic:notEmpty>
								<logic:notEmpty name="clause" property="partNumber">
									<logic:notEqual name="clause" property="partNumber" value="0">
										<bean:message key="viewSpec.PartNo" />&nbsp;
										<bean:write name="clause" property="partNumber" />
										<br>
									</logic:notEqual>
								</logic:notEmpty>
								<logic:notEmpty name="clause" property="priceBookNumber">
								<logic:notEqual name="clause" property="priceBookNumber" value="0">
									<bean:message key="screen.priceBookNo" /> &nbsp;
									<bean:write name="clause" property="priceBookNumber" />
									<br/>
								</logic:notEqual>
								</logic:notEmpty>
								<logic:notEmpty name="clause" property="standardEquipmentDesc">
									<bean:write name="clause" property="standardEquipmentDesc" />
									<br>
								</logic:notEmpty>
								<logic:notEmpty name="clause" property="engDataComment">
									<bean:define name="clause" property="engDataComment" id="engDataComment" />
									<%=engDataComment%>
									<br>
								</logic:notEmpty>&nbsp;
							</TD>
							
							<logic:notEmpty name="clause" property="componentList">
								<TD CLASS="text-center" WIDTH="10%">
									<logic:iterate id="compList" name="clause" property="componentList"
										type="com.EMD.LSDB.vo.common.ComponentVO">
										<bean:write name="compList" property="componentName" />
									</logic:iterate>
								</TD>
							</logic:notEmpty>
							<logic:empty name="clause" property="componentList">
								<TD CLASS="text-center" WIDTH="10%">&nbsp;
								</TD>
							</logic:empty>
						</TR>	
					</tbody>			
						<% if (i == clausedetail.getCurrDelCount()) {%>
							</TABLE>
							
							<TABLE ALIGN=center>
								<TR>
									<TD ALIGN=center WIDTH="15%">
									<logic:iterate id="compG" name="clausedetail" scope="page"
										property="compGroupVO" type="com.EMD.LSDB.vo.common.CompGroupVO">
										<button class="btn btn-primary" type='button' 
											onclick="javascript:fnRetrieve('Y','<%= String.valueOf(compG.getSecCode())%>','<%= String.valueOf(compG.getSectionName())%>','<%= String.valueOf(compG.getSectionSeqNo()) %>','<%= String.valueOf(compG.getSubSectionSeqNo())%>')"/>Retrieve
										</button>
									</logic:iterate>
									</TD>
								</TR>
								<TR>
									<TD>
										&nbsp;
									</TD>
								</TR>
							</TABLE>
							<% } i++; %>
			
						</logic:equal>
					</logic:iterate>
				</logic:notEmpty>	
			</logic:iterate>
		
		<input type="hidden" name="hdnClauseSeqNo" />
		<html:hidden name="OrderSectionForm" property="orderKey" />
		<html:hidden name="OrderSectionForm" property="orderSecSeqNo" />
		
		<%String temp1=""; %>	
		<logic:iterate id="clausedetails" name="OrderSectionForm"
			property="clauseDetail" type="com.EMD.LSDB.vo.common.ClauseVO" 
				scope="request">	
			<% int i=1; %>				
			<logic:notEmpty name="clausedetails" property="clauseGroup">	
				<logic:iterate id="clauses" name="clausedetails" property="clauseGroup"
						type="com.EMD.LSDB.vo.common.ClauseVO" scope="page" indexId="clauseCounter">	
					<logic:equal name="clauses" property="currRevFlag" value="N">
					<%	if(!"Y".equals(temp1)){ if("Y".equals(temp)){ %>
					<HR/><BR />
					<%} %>
					
					<h4 class="text-center"><bean:message key="Application.Screen.ViewDeletedClausesEarRevision" /></h4>
					<p class="text-center"><mark><bean:message key="Application.Screen.ViewDeletedClausesNoteEarRevision" /></mark></p>
					
					<BR/>				
					<% temp = "N"; temp1="Y"; } %>					
					<% if (i==1) { %>			
						<logic:iterate id="compGrp" name="clausedetails" scope="page"
							property="compGroupVO" type="com.EMD.LSDB.vo.common.CompGroupVO">
								<logic:notEqual name="secSeqNo" value="<%=String.valueOf(compGrp.getSectionSeqNo())%>">
									<div class="row">
										<div class="col-sm-12 highlightDark text-center">
											<div class="text-center"><strong>
												<bean:write name="compGrp" property="secCode" />.
												<bean:write name="compGrp" property="sectionName" />
											</strong></div>
										</div>
									</div>
								</logic:notEqual>
									<div class="row">
										<div class="col-sm-12 bg-info text-center">
											<div class="text-center"><strong>
												<bean:write name="compGrp" property="secCode" />.
												<bean:write name="compGrp" property="subSectionCode" />&nbsp;
												<bean:write name="compGrp" property="subSectionName" />
											</strong></div>
										</div>
									</div>
							<bean:define id="secSeqNo" name="compGrp" property="sectionSeqNo"/>
						</logic:iterate>
					<div class="row">
						<div class="spacer10"></div>
					</div>
					<TABLE class="table table-bordered">
						<thead>
							<TR>
								<TH CLASS='text-center' WIDTH="5%">Select</TH>
								<TH CLASS='text-center' WIDTH="55%">Clause Description</TH>
								<TH CLASS='text-center' WIDTH="10%">Engineering Data</TH>
								<TH CLASS='text-center' WIDTH="10%">Component</TH>
							</TR>
						</thead>
		 			<% } %>
		 				<tbody>
						<TR>
							<TD WIDTH="5%" CLASS="v-midalign text-center"><html:radio property="clauseSeqNo"
								value='<%=String.valueOf(clauses.getClauseSeqNo())%>'/>
							</TD>
							
							<logic:notEmpty name="clauses" property="clauseDesc">
								<TD WIDTH="55%">
											<bean:define name="clauses" property="clauseDesc" id="clauseDesc" />
												<!-- CR-128 - Updated for Pdf issue -->
											<%String strClauseDesc =  String.valueOf(clauseDesc);
											if(strClauseDesc.startsWith("<p>")){%>
												<%=(String.valueOf(clauseDesc))%>
											<%}else{ %>	
												<%-- double space replacement with &nbsp is added to display the spaces as entered. This change is applicable for only modify spec --%>
												<%=(String.valueOf(clauseDesc)).replaceAll("  ","&nbsp;&nbsp;").replaceAll("\n","<br>")%>							
											<%}%>
										<!-- CR-128 - Updated for Pdf issue Ends Here-->
											
												<logic:notEmpty name="clauses" property="tableArrayData1">
												 <table class="table">
												 <logic:iterate id="outter" name="clauses" property="tableArrayData1"
															indexId="counter">
														<bean:define id="row" name="counter" />
														<!-- Added  For CR_93 -->
			                                         <bean:define name="clauses" property="tableDataColSize" id="tableDataColSize" />
														<tr>
															<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
					
																<logic:equal name="row" value="0">
																	<td width="5%"><b><font
																		color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></font></b>
																	</td>
																</logic:equal>
					
																<logic:notEqual name="row" value="0">
																	<td width="5%"><%=(tabrow==null)? "&nbsp;":tabrow%>
																	</td>
																</logic:notEqual>
															</logic:iterate>
														</tr>
													</logic:iterate>
											</table>
												</logic:notEmpty>
								</TD>
					</logic:notEmpty>
					<logic:empty name="clauses" property="clauseDesc">
						<TD WIDTH="55%">
							&nbsp;
						</TD>
					</logic:empty>
							
						<TD CLASS="text-center" WIDTH="10%">
									<logic:notEmpty name="clauses" property="edlNo">
										<logic:iterate id="edl" name="clauses" property="edlNo">
											<logic:notEmpty name="edl">
												<bean:message key="viewSpec.EDL" />&nbsp;<bean:write name="edl" />
												<br>
											</logic:notEmpty>
										</logic:iterate>
									</logic:notEmpty>
									<logic:notEmpty	name="clauses" property="refEDLNo">
										<logic:iterate id="refedl" name="clauses" property="refEDLNo">
											<logic:notEmpty name="refedl">
												<bean:message key="viewSpec.refEDL.start" />&nbsp;<bean:write
													name="refedl" />
												<bean:message key="viewSpec.refEDL.end" />
												<br>
											</logic:notEmpty>
										</logic:iterate>
									</logic:notEmpty> 
									<logic:notEmpty name="clauses" property="partOfCode">
										<logic:iterate id="partof" name="clauses" 
											property="partOfCode">
											<logic:notEmpty name="partof">
												<bean:message key="viewSpec.PartOf" />&nbsp;<bean:write
													name="partof" />&nbsp;
												<br>
											</logic:notEmpty>
										</logic:iterate>
									</logic:notEmpty> 
									<logic:notEmpty name="clauses" property="dwONumber">
										<logic:notEqual name="clauses" property="dwONumber" value="0">
											<bean:message key="viewSpec.DWO" />&nbsp;<bean:write
												name="clauses" property="dwONumber" />
											<br>
										</logic:notEqual>
									</logic:notEmpty>
									<logic:notEmpty name="clauses" property="partNumber">
										<logic:notEqual name="clauses" property="partNumber" value="0">
											<bean:message key="viewSpec.PartNo" />&nbsp;<bean:write
												name="clauses" property="partNumber" />
											<br>
										</logic:notEqual>
									</logic:notEmpty>
									<logic:notEmpty name="clauses" property="priceBookNumber">
										<logic:notEqual name="clauses" property="priceBookNumber" value="0">
											<bean:message key="screen.priceBookNo" /> &nbsp;
											<bean:write name="clauses" property="priceBookNumber" />
											<br/>
										</logic:notEqual>
									</logic:notEmpty>
									<logic:notEmpty name="clauses" property="standardEquipmentDesc">
										<bean:write name="clauses" property="standardEquipmentDesc" />
										<br>
									</logic:notEmpty>
									<logic:notEmpty name="clauses" property="engDataComment">
										<bean:define name="clauses" property="engDataComment" id="engDataComment" />
										<%=engDataComment%>
										<br>
									</logic:notEmpty>&nbsp;
							</TD>
							
							<logic:notEmpty name="clauses" property="componentList">
								<TD CLASS="text-center" WIDTH="10%">
									<logic:iterate id="compList" name="clauses" property="componentList"
												type="com.EMD.LSDB.vo.common.ComponentVO">
										<bean:write name="compList" property="componentName" />
									</logic:iterate>
								</TD>
							</logic:notEmpty>
							<logic:empty name="clauses" property="componentList">
								<TD WIDTH="10%">&nbsp;
								</TD>
							</logic:empty>
						</TR>				
				<% if (i == clausedetails.getNonCurrDelCount()) {%>
					</TABLE>
					<TABLE ALIGN=center>
						<TR>
							<TD ALIGN=center WIDTH="15%">
							<logic:iterate id="compGr" name="clausedetails" scope="page"
									property="compGroupVO" type="com.EMD.LSDB.vo.common.CompGroupVO">
									<button class="btn btn-primary" type='button' 
										onclick="javascript:fnRetrieve('N','<%= String.valueOf(compGr.getSecCode())%>','<%= String.valueOf(compGr.getSectionName())%>','<%= String.valueOf(compGr.getSectionSeqNo()) %>','<%= String.valueOf(compGr.getSubSectionSeqNo())%>')"/>
										Retrieve</button>
						</logic:iterate>

							</TD>
						</TR>
						<TR>
							<TD>
								&nbsp;
							</TD>
						</TR>
					</TABLE>
				<% } i++; %>
			</logic:equal>
			</logic:iterate>
		</logic:notEmpty>	
	</logic:iterate>	
	<%if( temp.length() < 1 && temp1.length() < 1){ %>
			<script> 
              fnNoRecords();
        	</script>
    <% } %>
    
    <div class="form-group">
		<div class="col-sm-12 text-center">
		<logic:notEqual name="OrderSectionForm" property="redirectGenInfoPageFlag" value="Y" >
			<button class="btn btn-primary" type='button' 
				ONCLICK="javascript:fnModifySpec()"/>Return to Modify Spec</button>
		</logic:notEqual>
		<logic:equal name="OrderSectionForm" property="redirectGenInfoPageFlag" value="Y" >
			<button class="btn btn-primary" type='button' 
				ONCLICK="javascript:fnReturnGenInfo()"/>Return to General Info Maintenance</button>
		</logic:equal>
		</div>
	</div>
	<div class="row">
		<div class="spacer50"></div>
	</div>
</html:form>
</div>
</Body>