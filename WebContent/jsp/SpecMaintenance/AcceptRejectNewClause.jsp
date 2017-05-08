<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<SCRIPT LANGUAGE="JavaScript" SRC="js/AcceptRejectNewClause.js"></SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
	$(document).ready(function() {
		var claReason = document.forms[0].claReasonArray.value;
		var claSeq = document.forms[0].claSeqArray.value;
		if (claSeq != null || claSeq.length > 0){
		$.each(claSeq.split(','),function(i,seq){
				$.each(claReason.split(','),function(j,reason){
					if(i==j)
	       			$('#'+seq).val(reason);
	       		})
	       	})
	       }
		})
</SCRIPT>

<div class="container" width="80%">
<html:form action="/AccRejNewClauseAction" method="post" styleClass="form-horizontal">
	
	<!-- Validation Message Display Part Starts Here -->
	<div class="row">
	<div class="errorlayerhide" id="errorlayer">
	</div></div>
	<!-- Validation Message Display Part Ends Here -->
	<logic:present name="AcceptRejectNewClauseForm"
		property="newClauseList" scope="request">
		<bean:size id="NewClauselen" name="AcceptRejectNewClauseForm"
			property="newClauseList" />
	</logic:present>
	<!-- Logic Tag Check For Display The No Records Found Message  Functionality Starts Here -->
	
	<logic:match name="AcceptRejectNewClauseForm" property="method"
		value="ClauseVersions">
		<logic:lessThan name="NewClauselen" value="1">
			<script> 
              fnNoRecords();
        	</script>
		</logic:lessThan>
	</logic:match>
	
	
	
	
	<!-- Logic Tag Check For Display The No Records Found Message  Functionality Ends Here -->

	<!-- To display Server Side Validation Messages - Start -->
	<logic:present name="AcceptRejectNewClauseForm" property="messageID"
		scope="request">
		<logic:lessThan name="NewClauselen" value="1">
			<script> 
              fnNoRecords();
        	</script>

			<TABLE BORDER="0" ALIGN="CENTER" WIDTH="90%">
				<TD ALIGN="CENTER"><html:button property="method"
					styleClass="buttonStyle" onclick="javascript:fnModifySpec()">
					<bean:message key="screen.returnmodifyspec" />
				</html:button></TD>
			</TABLE>

		</logic:lessThan>
		<logic:greaterThan name="NewClauselen" value="0">
			<bean:define id="messageID" name="AcceptRejectNewClauseForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
		</logic:greaterThan>
	</logic:present>
	<!-- To display Server Side Validation Messages - End -->
<%String temp1 = "";%>
	<logic:greaterThan name="NewClauselen" value="0">

		<html:hidden property="flag" />
		<logic:present name="AcceptRejectNewClauseForm"
			property="newClauseList">
			<logic:greaterThan name="NewClauselen" value="0">
				<html:hidden property="flag" />
				<logic:present name="AcceptRejectNewClauseForm"
					property="newClauseList">
						
             <logic:iterate id="heddingtb1" name="AcceptRejectNewClauseForm"
							property="newClauseList" type="com.EMD.LSDB.vo.common.ClauseVO" >
							<logic:notEmpty name="heddingtb1" property="compGroupName">
								<logic:notEmpty name="heddingtb1" property="compName">
								
						<%if (!"Y".equals(temp1)) {%>		
							<h4 class="text-center"><bean:message key="Application.Screen.AcknowledgeNewClause" /></h4>
							<div class="spacer10"></div>
							<p class="text-center"><mark><bean:message key="Application.Screen.Message.ComponentClause" /></mark></p>
						<%temp1 = "Y";%>
								
								<% }%>
					
					</logic:notEmpty>
					</logic:notEmpty>
					</logic:iterate>
					
					<TABLE class="table table-bordered">
					<%String temp = "";%>
						
						<logic:iterate id="clauseRev" name="AcceptRejectNewClauseForm"
							property="newClauseList" type="com.EMD.LSDB.vo.common.ClauseVO"  >
							<logic:notEmpty name="clauseRev" property="compGroupName">
								<logic:notEmpty name="clauseRev" property="compName">
								
								<%if (!"X".equals(temp)) {%>
							<thead>	
							<TR>
									<TH class="text-center" width="40%">Clause Description</TH>
									<TH class="text-center" width="15%">Engineering Data</TH>
									<TH class="text-center" width="15%">Component Name</TH>
									<TH class="text-center" width="15%">Component Group</TH>
								</TR>
							</thead>	
							
								<%temp = "X";%>
								
								<% }%>	
								<tbody>	
									<TR>
										<TD VALIGN="top">
										<DIV align="left" style="solid;height:80px;overflow:auto;">
											<bean:define id="clauseDesc" name="clauseRev" property="clauseDesc" /> 
											<!-- CR-128 - Updated for Pdf issue -->
												<%String strClauseDesc =  String.valueOf(clauseDesc);
												if(strClauseDesc.startsWith("<p>")){%>
													<%=(String.valueOf(clauseDesc))%>
												<%}else{ %>	
													<%-- double space replacement with &nbsp is added to display the spaces as entered. This change is applicable for only modify spec --%>
													<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>							
												<%}%>
											<!-- CR-128 - Updated for Pdf issue Ends Here-->
										<table>
											<tr>
												<td valign="top">

												<table class="table table-bordered text-center">
													<logic:notEmpty name="clauseRev" property="tableArrayData1">&nbsp;
                    									<logic:iterate id="outter" name="clauseRev"
															property="tableArrayData1" indexId="counter">
															<bean:define id="row" name="counter" />
															<!-- Added  For CR_93 -->
															<bean:define name="clauseRev" property="tableDataColSize" id="tableDataColSize" />
															<tr>
																<logic:iterate id="tabrow" name="outter" length="tableDataColSize">

																	<logic:equal name="row" value="0">
																		<td valign="top" width="5%" class="borderbottomleft"><b><font
																			color="navy"><%=(tabrow == null) ? "&nbsp;"
																			: tabrow%></b></font>
																		</td>
																	</logic:equal>

																	<logic:notEqual name="row" value="0">
																		<td valign="top" width="5%" class="borderbottomleft"><%=(tabrow == null) ? "&nbsp;"
																			: tabrow%>
																		</td>
																	</logic:notEqual>
																</logic:iterate>
															</tr>
														</logic:iterate>
													</logic:notEmpty>
												</table>
												</td>
											</tr>
										</table>
										</DIV>
										</TD>
										
										<TD class="text-center" VALIGN="top" nowrap>
										<DIV style="solid;height:80px;overflow:auto;">
										<!-- <logic:notEmpty	name="clauseRev" property="refEDLNO">
												<logic:iterate id="refedl" name="clauseRev" property="refEDLNO">
													<bean:message key="screen.refEdl" />  &nbsp;
						           					<bean:write name="refedl" />
													<BR>
												</logic:iterate>
											</logic:notEmpty>--> 
											<logic:notEmpty name="clauseRev" property="edlNO">
												<logic:iterate id="edl" name="clauseRev" property="edlNO">
													<bean:message key="screen.edl" /> &nbsp;
	          										<bean:write name="edl" />
													<BR>
												</logic:iterate>
											</logic:notEmpty> <!--  CR 87 --> 
											<logic:notEmpty	name="clauseRev" property="refEDLNO">
												<logic:iterate id="refedl" name="clauseRev"	property="refEDLNO">
													<bean:message key="screen.refEdl.start" />  &nbsp;
									     			<bean:write name="refedl" />&nbsp;
													<bean:message key="screen.refEdl.end" />
													<BR>
												</logic:iterate>
											</logic:notEmpty> 
											<logic:notEmpty name="clauseRev" property="partOF">
												<logic:iterate id="code" name="clauseRev" property="partOF">
													<bean:message key="screen.partOf" />&nbsp;
		          									<bean:write name="code" />
													<BR>
												</logic:iterate>
											</logic:notEmpty> 
											<logic:greaterThan name="clauseRev"	property="dwONumber" value="0">
												<bean:message key="screen.dwoNo" />  &nbsp;
	           									<bean:write name="clauseRev" property="dwONumber" />
												<BR>
											</logic:greaterThan> 
											<logic:greaterThan name="clauseRev" property="partNumber" value="0">
												<bean:message key="screen.partNo" /> &nbsp;
									            <bean:write name="clauseRev" property="partNumber" />
												<BR>
											</logic:greaterThan> 
											<logic:greaterThan name="clauseRev" property="priceBookNumber" value="0">
												<bean:message key="screen.priceBookNo" /> &nbsp;
									            <bean:write name="clauseRev" property="priceBookNumber" />
												<BR>
											</logic:greaterThan> 
											<logic:present name="clauseRev"	property="standardEquipmentDesc">
												<bean:write name="clauseRev" property="standardEquipmentDesc" />
												<BR>
											</logic:present> 
											<logic:present name="clauseRev" property="comments">
												<bean:write name="clauseRev" property="comments" />
												<BR>
											</logic:present>
											</DIV>
										</TD>
										<TD class="text-center" VALIGN="top">
											<logic:iterate id="compname" name="clauseRev" property="compName" indexId="compntIndex">
												<logic:notEqual name="compntIndex" value="0"> ;<br></logic:notEqual>
												<bean:write name="compname" />
											</logic:iterate> 
											
										<TD class="text-center" VALIGN="top">
											<logic:notEmpty name="clauseRev" property="compGroupName">
												<bean:write name="clauseRev" property="compGroupName" />
											</logic:notEmpty>
										</TD>
										
										<html:hidden property="clauseSeqNo" name="claSeqNo"  value='<%=String.valueOf(clauseRev.getClauseSeqNo())%>'/>
									</TR>
								</tbody>
							</logic:notEmpty>
						</logic:notEmpty>
					</logic:iterate>
				</TABLE>
							
				<%if ("Y".equals(temp1)) {%>

					<div class="form-group">
						<div class="col-sm-12 text-center">
							<button class="btn btn-primary" type='button' ONCLICK="javascript:fnAcknowledgeAllClause()">Acknowledge All Clause(s)</button>
						</div>
					</div>						
				
				<% }%>
			
			</logic:present>
		</logic:greaterThan>
			
</div>
<div class="container" width="60%">
			<!-- Added by BM85529 -->
<% int countList=0;%>
			<logic:iterate id="clauseNotcomp" name="AcceptRejectNewClauseForm"
				property="newClauseList" type="com.EMD.LSDB.vo.common.ClauseVO" indexId="counter">
				<logic:empty name="clauseNotcomp" property="compGroupName">
					<logic:empty name="clauseNotcomp" property="compName">
					<bean:define id="heddingtb2" name="counter"/>
<%if(countList==0){%>
					<%if ("Y".equals(temp1)) {%>
					<hr />
					<% }%>
						<h4 class="text-center">Accept/Reject New Clause</h4>
						<p class="text-center"><mark>NOTE: If a CORE Clause is rejected, then it cannot be brought back into spec.</mark></p>
					<% }%>	
			<BR/>

						<TABLE class="table table-bordered">
							<thead>
								<TR>
									<TH colspan="2" class="text-center" WIDTH="60%">Clause Description</TH>
									<TH class="text-center" nowrap WIDTH="20%">Engineering Data</TH>
								</TR>
							</thead>
							<tbody>
							<TR>
								<TD VALIGN="top" colspan="2">
								<DIV align="left" style="solid;height:80px;overflow:auto;">
									<bean:define id="clauseDesc" name="clauseNotcomp" property="clauseDesc" />
									<!-- CR-128 - Updated for Pdf issue -->
										<%String strClauseDesc =  String.valueOf(clauseDesc);
										if(strClauseDesc.startsWith("<p>")){%>
											<%=(String.valueOf(clauseDesc))%>
										<%}else{ %>	
											<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>							
										<%}%>
									<!-- CR-128 - Updated for Pdf issue Ends Here-->	
									<table>
										<tr>
										<td valign="top">

										<table class="table table-bordered text-center">
											<logic:notEmpty name="clauseNotcomp" property="tableArrayData1">&nbsp;
                             					<logic:iterate id="outter" name="clauseNotcomp" property="tableArrayData1" indexId="counter">
													<bean:define id="row" name="counter" />
													<!-- Added  For CR_93 -->
													<bean:define name="clauseNotcomp" property="tableDataColSize" id="tableDataColSize" />
													<tr>
														<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
															<logic:equal name="row" value="0">
																<td valign="top" width="5%" class="borderbottomleft"><b><font
																	color="navy"><%=(tabrow == null) ? "&nbsp;"
																	: tabrow%></b></font>
																</td>
															</logic:equal>
															<logic:notEqual name="row" value="0">
																<td valign="top" width="5%" class="borderbottomleft"><%=(tabrow == null) ? "&nbsp;"
																	: tabrow%>
																</td>
															</logic:notEqual>
														</logic:iterate>
													</tr>
												</logic:iterate>
											</logic:notEmpty>
										</table>

										</td>
									</tr>
								</table>
								</DIV>
								</TD>
								
								<TD class="text-center" VALIGN="top" nowrap>
									<logic:notEmpty name="clauseNotcomp" property="edlNO">
										<logic:iterate id="edl" name="clauseNotcomp" property="edlNO">
											<bean:message key="screen.edl" /> &nbsp;
	                                     	<bean:write name="edl" />
											<BR>
										</logic:iterate>
									</logic:notEmpty> <!--  CR 87 --> 
									<logic:notEmpty name="clauseNotcomp" property="refEDLNO">
										<logic:iterate id="refedl" name="clauseNotcomp"	property="refEDLNO">
											<bean:message key="screen.refEdl.start" />  &nbsp;
								       		<bean:write name="refedl" />&nbsp;
											<bean:message key="screen.refEdl.end" />
											<BR>
										</logic:iterate>
									</logic:notEmpty> 
									<logic:notEmpty name="clauseNotcomp" property="partOF">
										<logic:iterate id="code" name="clauseNotcomp" property="partOF">
											<bean:message key="screen.partOf" />&nbsp;
	           								<bean:write name="code" />
											<BR>
										</logic:iterate>
									</logic:notEmpty> 
									<logic:greaterThan name="clauseNotcomp"	property="dwONumber" value="0">
										<bean:message key="screen.dwoNo" />  &nbsp;
	           							<bean:write name="clauseNotcomp" property="dwONumber" />
										<BR>
									</logic:greaterThan> 
									<logic:greaterThan name="clauseNotcomp" property="partNumber" value="0">
										<bean:message key="screen.partNo" /> &nbsp;
	            						<bean:write name="clauseNotcomp" property="partNumber" />
										<BR>
									</logic:greaterThan> 
									<logic:greaterThan name="clauseNotcomp"	property="priceBookNumber" value="0">
										<bean:message key="screen.priceBookNo" /> &nbsp;
	            						<bean:write name="clauseNotcomp" property="priceBookNumber" />
										<BR>
									</logic:greaterThan> 
									<logic:present name="clauseNotcomp"	property="standardEquipmentDesc">
										<bean:write name="clauseNotcomp" property="standardEquipmentDesc" />
										<BR>
									</logic:present> 
									<logic:present name="clauseNotcomp"	property="comments">
										<bean:write name="clauseNotcomp" property="comments" />
										<BR>
									</logic:present>
								</TD>
							</TR>
							<TR>
								<TD class="v-midalign text-center border-right-one">
									<label for="textarea">Spec Supplement Reason
									  	<logic:greaterEqual name="AcceptRejectNewClauseForm" property="specStatusCode" value="3">
						    					<font color="red" valign="top">*</font>
											</logic:greaterEqual>
									</label>
								</TD>
								<TD class="v-midalign border-right-one">
									<input type="hidden" name="coreClaSeqNo" value="<%=String.valueOf(clauseNotcomp.getClauseSeqNo())%>"/>
								 	<textarea class="form-control" name="coreClaReason" ROWS="3" COLS="50" id="<%=String.valueOf(clauseNotcomp.getClauseSeqNo())%>"></textarea>
								</TD>
								<TD class="v-midalign text-center border-right-one">
									<button class="btn btn-primary" type='button' ONCLICK="javascript:fnAcceptClauseChange('<%=countList%>','<%=String.valueOf(clauseNotcomp.getClauseSeqNo())%>')">Accept</button>
									<button class="btn btn-primary" type='button' ONCLICK="javascript:fnRejectClauseChange('<%=countList%>','<%=String.valueOf(clauseNotcomp.getClauseSeqNo())%>')">Reject</button>
								</TD>
							</TR>
							
							</tbody>
						</TABLE>
						<%countList++;%>
				</logic:empty>
			</logic:empty>
		</logic:iterate>

			<!-- One new Check condition for  specStatusCode is added to check the reason field in AcceptrejectNew clause Screen : Added on 05-June-08; by ps57222-->
				<logic:iterate id="returnmodifyspec" name="AcceptRejectNewClauseForm"
							property="newClauseList" type="com.EMD.LSDB.vo.common.ClauseVO" indexId="counterbutton">
							<bean:define id="count" name="counterbutton"/> 
									<logic:equal name="count" value="0">
			<!-- Commented based on the requirement of LSDB_CR-74 0n 05-June-09 by ps57222 -->
								<div class="form-group">
									<div class="col-sm-12 text-center">
										<button class="btn btn-primary" type='button' id="retunMdfyScrn" ONCLICK="javascript:fnModifySpec()">Return To Modify Spec</button>
									</div>
								</div>
						</logic:equal>
				</logic:iterate>
			</logic:present>
	</logic:greaterThan>
	<div class="spacer100"></div>
	<html:hidden property="orderKey" />
	<html:hidden property="secSeq" />
	<html:hidden property="secName" />
	<html:hidden property="secCode" />
	<html:hidden property="revCode" />
	<html:hidden property="subSecSeqNo" />
	<html:hidden property="specStatusCode" />
	<!-- CR_90 -->
	<html:hidden property="reason"/>
	<html:hidden property="claReasonArray" />
	<html:hidden property="claSeqArray" />
</html:form>
</div>