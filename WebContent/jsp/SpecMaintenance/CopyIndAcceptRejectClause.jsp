<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<SCRIPT LANGUAGE="JavaScript" SRC="js/CopyIndAcceptRejectClause.js"></SCRIPT>

<div class="container" width="100%">
<html:form action="/CopyIndAcceptRejectClauseAction" method="post" styleClass="form-horizontal">

	<!-- Application PageName Display starts here-->
	<h4 class="text-center"><bean:message key="Application.Screen.AcceptRejectClause" /></h4>
	<p class="text-center"><mark><bean:message key="Application.Screen.Message.PartOf" /></mark></p>
	<p class="text-center"><span class="glyphicon glyphicon-remove text-red" aria-hidden="true"></span>
		<mark> - indicates the version has been deleted in Model</mark></p>
	<!-- Application PageName Display Ends here-->

	<div class="spacer10"></div>
	<!-- Validation Message Display Part Starts Here -->
	<div class="row">
		<div class="errorlayerhide" id="errorlayer"></div>
	</div>
	<!-- Validation Message Display Part Ends Here -->
	<div class="spacer10"></div>

	<!-- Logic Tag Check For Display The Success Message After Upload and Delete Functionality Starts Here -->
	<logic:present name="AcceptRejectClauseForm" property="messageID"
		scope="request">

			<bean:define id="messageID" name="AcceptRejectClauseForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
	</logic:present>
	<!-- Logic Tag Check For Display The Success Message After Upload and Delete Functionality Starts Here -->

	<html:hidden property="clauseSeqNo" />
	<html:hidden property="hdnClauseVersionNo" />
	<html:hidden property="version" />
	<html:hidden property="orderKey" />
	<html:hidden property="flag" />
	<html:hidden property="indFlag" />

	<html:hidden property="secSeq" />
	<html:hidden property="secName" />
	<html:hidden property="secCode" />
	<html:hidden property="revCode" />
	<html:hidden property="specStatusCode" />
	<html:hidden property="subSecSeqNo"/>

	<logic:present name="AcceptRejectClauseForm" property="clauseVersions">

		<TABLE class="table table-bordered">
			<thead>	
				<TR>
					<TH class="text-center" width="9%">Version No</TH>
					<TH class="text-center" width="35%">Clause Description</TH>
					<TH class="text-center" width="10%">Engineering Data</TH>
					<TH class='text-center' width="10%">Customer</TH>
					<%--Updated for CR-114 --%>
					<TH class='text-center' width="8%">Created By</TH>
					<TH class='text-center' width="12%">Created Date</TH>
					<%--Updated for CR-114 Ends Here--%>
					<TH class="text-center" width="7%">Current Selection</TH>
					<TH class="text-center" width="7%">Copy From Version</TH>
				</TR>
			</thead>
			<tbody>
				<logic:iterate id="clauseRev" name="AcceptRejectClauseForm"
					property="clauseVersions" type="com.EMD.LSDB.vo.common.ClauseVO">
	
					<TR>
						<html:hidden name="clauseRev" property="versionNo" />
						<%-- Added for CR_114--%>
				    	<html:hidden name="clauseRev" property="appendixExitsFlag" />
					    <logic:notEqual name="clauseRev" property="appendixExitsFlag" value="N">
					    <html:hidden name="clauseRev" property="appendixImgSeqNo" />
					    <html:hidden name="clauseRev" property="mapAppendixImg" />
					    </logic:notEqual>
						<%-- Added for CR_114 Ends--%>
						
						<TD class="text-center v-midalign">
							<bean:write name="clauseRev" property="versionNo" />
							<logic:equal name="clauseRev" property="deleteFlag" value="Y">
								<span class="glyphicon glyphicon-remove text-red" aria-hidden="true"></span>
							</logic:equal>
						</TD>
	
						<TD VALIGN="top"><span style="width:435px">
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
						<TD class="text-center" ><span style="width:130px">
							<DIV style="solid;height:60px;overflow:auto;">
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
								<logic:greaterThan name="clauseRev" property="partNumber" value="0">
									<bean:message key="screen.partNo" /> &nbsp;
			           	 			<bean:write name="clauseRev" property="partNumber" />
									<BR>
								</logic:greaterThan> 
								<logic:greaterThan name="clauseRev"	property="priceBookNumber" value="0">
									<bean:message key="screen.priceBookNo" /> &nbsp;
			            			<bean:write name="clauseRev" property="priceBookNumber" />
									<BR>
								</logic:greaterThan> 
								<logic:present name="clauseRev" property="standardEquipmentDesc">
									<bean:write name="clauseRev" property="standardEquipmentDesc" />
									<BR>
								</logic:present> 
								<logic:present name="clauseRev" property="comments">
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
						<TD class="text-center">
							<logic:notEmpty name="clauseRev" property="createdBy">
								<bean:write name="clauseRev" property="createdBy" />
							</logic:notEmpty>
						</TD>
						<TD class="text-center"> 
							<logic:notEmpty name="clauseRev" property="createdDate">
								<bean:write name="clauseRev" property="createdDate" />
							</logic:notEmpty>
						</TD>
						<TD class="text-center">
							<bean:write name="clauseRev" property="defaultFlag" />
						</TD>
						<TD class="text-center">
							<bean:write name="clauseRev" property="copyFlag" />
						</TD>
					</TR>
				</logic:iterate>
			</tbody>
		</TABLE>

		<div class="form-group">
			 <div class="col-sm-offset-3 col-sm-6">
			 	<fieldset class="scheduler-border">
	    		<legend class="scheduler-border">Spec Supplement</legend>
    				<label class="col-sm-2">Reason
	    				<logic:greaterEqual name="AcceptRejectClauseForm" property="specStatusCode" value="3">
	    					<font color="red" valign="top">*</font>
						</logic:greaterEqual>
    				</label>
					<div class="col-sm-8">
						<textarea class="form-control" name="reason" rows="3" cols="60"></textarea>
					</div>
				</fieldset>
		    </div>
	    </div>
	    
	    <div class="form-group">
			<div class="col-sm-12 text-center">
				<button class="btn btn-primary" type='button' id="acceptBtn" ONCLICK="javascript:fnRejectClauseChange()"><bean:message key="copyInd.screen.reject" /></button>
				<button class="btn btn-primary" type='button' id="rejectBtn" ONCLICK="javascript:fnAcceptClauseChange()"><bean:message key="copyInd.screen.accept" /></button>
				<button class="btn btn-primary" type='button' id="retunMdfyScrn" ONCLICK="javascript:fnModifySpec()">Return To Modify Spec</button>
			</div>
		</div>
		<div class="spacer50"></div>
		<%-- CR_100 Ends here--%>
	</logic:present>
</html:form>
</div>

