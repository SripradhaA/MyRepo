<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<SCRIPT LANGUAGE="JavaScript" SRC="js/DeleteIndAcceptReject.js"></SCRIPT>

<div class="container" width="70%">
<html:form action="/DeleteIndAcceptRejectClauseAction" method="post" styleClass="form-horizontal">

	<!-- Application PageName Display starts here-->
		<h4 class="text-center"><bean:message key="Application.Screen.AccRejDelClause" /></h4>
		<p class="text-center"><mark><bean:message key="Application.Screen.Message.PartOf" /></mark></p>
	<!-- Application PageName Display ends here-->
	<div class="spacer10"></div>
	<!-- Validation Message Display Part Starts Here -->
	<div class="row">
		<div class="errorlayerhide" id="errorlayer"></div>
	</div>
	<!-- Validation Message Display Part Ends Here -->
	<div class="spacer10"></div>
	<logic:present name="AcceptRejectClauseForm" property="clauseVersions"
		scope="request">
		<bean:size id="Clauselen" name="AcceptRejectClauseForm"
			property="clauseVersions" />
	</logic:present>
	<!-- Logic Tag Check For Display The No Records Found Message  Functionality Starts Here -->
	<logic:present name="AcceptRejectClauseForm" property="messageID"
		scope="request">

			<bean:define id="messageID" name="AcceptRejectClauseForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
	</logic:present>
	<!-- Logic Tag Check For Display The No Records Found Message  Functionality Ends Here -->
	<html:hidden property="orderKey" />
	<html:hidden property="secSeq" />

	<html:hidden property="secCode" />
	<html:hidden property="revCode" />
	<html:hidden property="subSecSeqNo"/>
	<html:hidden property="specStatusCode" />
	<html:hidden property="clauseSeqNo" />
	<html:hidden property="flag" />
	
	
	
	<logic:present name="AcceptRejectClauseForm" property="messageID" scope="request">
		<logic:lessThan name="Clauselen" value="1">
			<div class="form-group"> 
				<div class="col-sm-12 text-center">
					<button class="btn btn-primary" type='button' ONCLICK="javascript:fnModifySpec()">
						<bean:message key="screen.returnmodifyspec" />
					</button>
				</div>
			</div>
		</logic:lessThan>
	</logic:present>


	<logic:greaterThan name="Clauselen" value="0">
		<logic:present name="AcceptRejectClauseForm" property="clauseVersions">
			<p class="text-center">
				<logic:equal name="AcceptRejectClauseForm" property="flag" value="D">
					<bean:message key="ClauseDeleted" />
				</logic:equal>
				<logic:notEqual name="AcceptRejectClauseForm" property="flag" value="D">
					<bean:message key="ClauseVersionDeleted" />
				</logic:notEqual>
			</p>

			<TABLE class="table table-bordered">
				<thead>
					<TR>
						<TH class="text-center" width="10%">Version No</TH>
						<TH class="text-center" width="40%">Clause Description</TH>
						<TH class="text-center" width="10%">Engineering Data</TH>
						<TH class='text-center' width="10%">Customer</TH>
					</TR>
				</thead>
				<tbody>
					<logic:iterate id="clauseRev" name="AcceptRejectClauseForm"
						property="clauseVersions" type="com.EMD.LSDB.vo.common.ClauseVO">
						<TR>
							<html:hidden name="clauseRev" property="versionNo" />
							<!-- Commented for CR 73 starts -->
							<!--<html:hidden name="clauseRev" property="clauseSeqNo" />-->
							<!-- Commented for CR 73 ends -->
							<TD class="text-center v-midalign">	
								<bean:write name="clauseRev" property="versionNo" />
							</TD>
							
							<TD VALIGN="top"><span style="width:350px">
								<DIV style="solid;height:60px;overflow:auto;">
									<bean:define name="clauseRev" property="clauseDesc" id="clauseDesc" />
									<!-- CR-128 - Updated for Pdf issue -->
									<%String strClauseDesc =  String.valueOf(clauseDesc);
									if(strClauseDesc.startsWith("<p>")){%>
										<%=(String.valueOf(clauseDesc))%>
									<%}else{ %>	
										<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>						
									<%}%>
									<!-- CR-128 - Updated for Pdf issue Ends Here-->
									
									<table class="table table-bordered text-center">
										<logic:notEmpty name="clauseRev" property="tableArrayData1">&nbsp;
											<logic:iterate id="outter" name="clauseRev" property="tableArrayData1" indexId="counter">
												<bean:define id="row" name="counter" />
												<!-- Added  For CR_93 -->
												<bean:define name="clauseRev" property="tableDataColSize" id="tableDataColSize" />
												<tr>
													<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
														<logic:equal name="row" value="0">
															<td valign="top" width="5%" CLASS='borderbottomleft1'><b><font
																color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></font></b>
															</td>
														</logic:equal>
														<logic:notEqual name="row" value="0">
															<td valign="top" width="5%" CLASS='borderbottomleft1'><%=(tabrow==null)? "&nbsp;":tabrow%>
															</td>
														</logic:notEqual>
													</logic:iterate>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
									</table>
								</DIV>
							</span></TD>
							
							<TD VALIGN="top" class="text-center"><span style="width:150px">
								<DIV style="solid;height:60px;overflow:auto;"><!--Ref EDL Number is added in the Engineering data Section, modified on 30-09-08 by ps5722-->
									<!--<logic:notEmpty name="clauseRev" property="refEDLNO">
											<logic:iterate id="refedl" name="clauseRev" property="refEDLNO">
												<bean:message key="screen.refEdl" /> &nbsp;
				           						<bean:write name="refedl" />
												<BR>
											</logic:iterate>
										</logic:notEmpty>-->
							 		<logic:notEmpty name="clauseRev" property="edlNO">
										<logic:iterate id="edl" name="clauseRev" property="edlNO">
											<bean:message key="screen.edl" />&nbsp;
		        							<bean:write name="edl" />
											<BR>
										</logic:iterate>
									</logic:notEmpty>
									<!-- CR 87 -->
									<logic:notEmpty name="clauseRev" property="refEDLNO">
										<logic:iterate id="refedl" name="clauseRev" property="refEDLNO">
											<bean:message key="screen.refEdl.start" /> &nbsp;
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
									<logic:greaterThan name="clauseRev" property="dwONumber" value="0">
										<bean:message key="screen.dwoNo" />  &nbsp;
		           						<bean:write name="clauseRev" property="dwONumber" />
										<BR>
									</logic:greaterThan> 
									<logic:greaterThan name="clauseRev"	property="partNumber" value="0">
										<bean:message key="screen.partNo" /> &nbsp;
		            					<bean:write name="clauseRev" property="partNumber" />
										<BR>
									</logic:greaterThan> 
									<logic:greaterThan name="clauseRev" property="priceBookNumber" value="0">
										<bean:message key="screen.priceBookNo" /> &nbsp;
		            					<bean:write name="clauseRev" property="priceBookNumber" />
										<BR>
									</logic:greaterThan> 
									<logic:present name="clauseRev" property="standardEquipmentDesc">
										<bean:write name="clauseRev" property="standardEquipmentDesc" />
										<BR>
									</logic:present> 
									<logic:present name="clauseRev"	property="comments">
										<bean:write name="clauseRev" property="comments" />
										<BR>
									</logic:present>
								</DIV>
							</span></TD>
	
							<TD class="text-center">
								<logic:notEmpty name="clauseRev" property="customerName">
									<bean:write name="clauseRev" property="customerName" />
								</logic:notEmpty>
							</TD>
						</TR>
						
					<html:hidden name="clauseRev" property="delModFlag" />
					<html:hidden name="clauseRev" property="modVersionNo" />
					</logic:iterate>
				</tbody>
			</TABLE>
			<!-- Added for LSDB_CR-74 -->
			<!-- Ends here -->
			<%--
			<logic:greaterEqual name="AcceptRejectClauseForm"
				property="specStatusCode" value="0">
				--%>
			<div class="spacer20"></div>
			<div class="panel panel-default col-sm-offset-2" style="width:70%;align:center;">
				<div class="panel-heading"><h3 class="panel-title text-center">Spec Supplement</h3></div>
				<div class="panel-body">
		   			<div class="form-group">
		   				<label class="col-sm-2 control-label">Reason
	    					<logic:greaterEqual name="AcceptRejectClauseForm" property="specStatusCode" value="3">
		    					<font color="red" valign="top">*</font>
		    				</logic:greaterEqual>
	    				</label>
	    				<div class="col-sm-8">
							<textarea class="form-control" name="reason" rows="3" cols="60"></textarea>
						</div>
	    			</div>
	    		</div>
	    	</div>
			
			<div class="form-group">
				<div class="col-sm-12 text-center">
				<!-- Added for CR-134 starts here -->
					<logic:equal name="clauseRev" property="delModFlag" value="Y">
						<button class="btn btn-primary" type='button' id="acceptApplyDefBtn" ONCLICK="javascript:fnAcceptApplyModDefClause()">Accept & Apply The Default Model Clause Version</button>
					</logic:equal>
				<!-- Added for CR-134 ends here -->
					<button class="btn btn-primary" type='button' id="acceptBtn" ONCLICK="javascript:fnAcceptDeleteClause()">Accept</button>
					<button class="btn btn-primary" type='button' id="rejectBtn" ONCLICK="javascript:fnRejectDeleteClause()">Reject</button>
					<button class="btn btn-primary" type='button' id="retunMdfyScrn" ONCLICK="javascript:fnModifySpec()">Return To Modify Spec</button>
				</div>
			</div>
		</logic:present>
	</logic:greaterThan>
	
</html:form>
</div>