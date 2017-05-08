<!DOCTYPE HTML>
<HTML>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="com.EMD.LSDB.vo.common.ClauseVO"%>
<%@page import="com.EMD.LSDB.vo.common.CompGroupVO"%>

<HEAD>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<link href="css/bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/select2.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/select2-bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/EMDCustom.css" rel="stylesheet"/>
	<link rel="shortcut icon" href="images/favicon.ico" />
	<script type="text/javascript" src="js/Others/jquerylatest.js"></script>
	<script type="text/javascript" src="js/Others/jquerymigrate.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/Others/select2.js"></script>
	<SCRIPT language="JavaScript" SRC="js/Emd_Lsdb.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/error.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/error_messages.js"></SCRIPT>
	<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
	<SCRIPT language="JavaScript">
		function fnClauseVersion()
		{
		var selectedClause=document.forms[0].versionNo;
		
		var chngToFlag=document.forms[0].chngToFlag.value;
		var selectedClauseDesc;
		
		var flag = false;
		var tableData= new Array();
		if (selectedClause == null || selectedClause == undefined) 
		{
			//Updated for CR-121 server side error message
			fnNoRecords();
		}
		else
		{
		if (selectedClause.length!=undefined)
		{
			for (i=0;i<selectedClause.length;i++)
			{
				
				if (selectedClause[i].checked)
				{			
					window.opener.document.forms[0].versionNoMod.value=document.forms[0].versionNo[i].value;
					window.opener.document.forms[0].hdnVersionNoMod.value=document.forms[0].versionNo[i].value;
		  			flag = true;
		  			break;
				}
			}
		}
		else
		{
			if(selectedClause.checked)
			{
				
				window.opener.document.forms[0].versionNoMod.value=document.forms[0].versionNo.value;
				window.opener.document.forms[0].hdnVersionNoMod.value=document.forms[0].versionNo.value;
				flag = true;	
					
			}
		}
		
		
		if(flag){
		window.opener.parentsubmit(window.opener.document.forms[0].versionNoMod.value);
		if(!window.closed){
			window.close();
		}
		}
			else {
				var id = '520';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("versionNo",-1);
			}
			
		}
			
		}
		function fnCloseWindow()
		{
		    window.close();
		}
	</SCRIPT>
</HEAD>
<BODY>
<div class="container">
	<html:form action="/CreateChangeReqClauseAction.do" styleClass="form-horizontal">
		<h4 class="text-center">Clause Versions</h4>
		
		<div class="spacer20"></div>
		<!-- Validation Message Display Part Starts Here -->
		<%--Table Updated for CR-121 for server side error message tooltip --%>
		<div class="errorlayerhide" id="errorlayer">
		</div>
		<!-- Validation Message Display Part Ends Here --> <!-- Logic Tag Check For Display The Success Message After Add Starts Here -->
	
		<logic:present name="ChangeRequest1058Form"
			property="messageID" scope="request">
			<%--Added for CR-121 for server side error message tooltip --%>	
			  <bean:define id="messageID" name="ChangeRequest1058Form" property="messageID"/>
			  <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
		      <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
			</logic:present> <logic:present name="ChangeRequest1058Form"
			property="clauseVersions" scope="request">
			
			<bean:size name="ChangeRequest1058Form" id="allversize"
				property="clauseVersions" />
		</logic:present> <!-- Logic Tag Check For Display The Success Message After Add Functionality Ends Here -->
				<!-- Logic Tag Check For Display The Error Message After Add Functionality Starts Here -->
		<logic:present name="ChangeRequest1058Form"
			property="errorMessage" scope="request">
			<script>
                   hideErrors();
                   addMessage('<bean:write name="ChangeRequest1058Form" property="errorMessage"/>');
                   showScrollErrors();
               </script>

		</logic:present> <!-- Logic Tag Check For Display The Error Message After Add Functionality Ends Here -->
					
		<div class="form-group">
			<label class="col-sm-2 col-sm-offset-4 control-label">Section</label>
			<div class="col-sm-4">
				<p class="form-control-static"><bean:write name="ChangeRequest1058Form" property="hdnSectionName" /></p>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 col-sm-offset-4 control-label">SubSection</label>
			<div class="col-sm-5 form-control-static">
				<bean:write name="ChangeRequest1058Form" property="hdnSubSectionName" />
			</div>
		</div>
	
		<logic:greaterThan name="allversize" value="0">
			<logic:present name="ChangeRequest1058Form" property="clauseVersions">
				<input type="hidden" name="noOfClaVersion" value="<bean:write name="allversize" />">
				<TABLE class="table table-bordered table-hover">
					<thead>
						<TR>
							<TH>Select</TH>
							<TH>Version No</TH>
							<TH>Clause Description</TH>
							<TH>Engineering Data</TH>
							<TH>Default</TH>
						</TR>
					</thead>
					<tbody>
						<logic:iterate id="clauseRev" name="ChangeRequest1058Form"
							property="clauseVersions" type="com.EMD.LSDB.vo.common.ClauseVO" indexId="clausecount">
							<TR>
								<TD class="text-center v-midalign"><html:radio
									value="<%=String.valueOf(clauseRev.getVersionNo())%>"
									property="versionNo" />
								</TD>
								
								<TD class="text-center v-midalign"><bean:write name="clauseRev"
									property="versionNo" />
								</TD>

								<TD valign="top">
									<DIV id="clauseID<%=clausecount%>" style="solid;height:60px;overflow:auto;">
										<bean:define name="clauseRev" property="clauseDesc" id="clauseDesc" /> 
										<!-- CR-128 - Updated for Pdf issue -->
											<%String strClauseDesc =  String.valueOf(clauseDesc);
											if(strClauseDesc.startsWith("<p>")){%>
												<%=(String.valueOf(clauseDesc))%>
											<%}else{ %>	
												<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>							
											<%}%>
										<!-- CR-128 - Updated for Pdf issue Ends Here-->
										
										<table class="table table-bordered">
											<logic:notEmpty name="clauseRev" property="tableArrayData1">&nbsp;
												<logic:iterate id="outter" name="clauseRev"
													property="tableArrayData1" indexId="counter">
													<bean:define id="row" name="counter" />
													<!-- Added  For CR_93 -->
													<bean:define name="clauseRev" property="tableDataColSize" id="tableDataColSize" />
													<tr>
														<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
															<logic:equal name="row" value="0">
																<td valign="top" width="5%" CLASS='borderbottomleft1'><b><font
																	color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></b></font>
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
										<bean:define id="cla" name="clauseRev" property="clauseDesc" />
										<bean:define id="tab" name="clauseRev"
											property="tableArrayData1" /> <input type="hidden"
											name="clauseDescTmp" value="<%=cla%>"> <input type="hidden"
											name="tableArrayData1Tmp" value="<%=tab%>">
									</DIV>
								</TD>
								<TD class="text-center v-midalign">
									<DIV style="solid;height:60px;overflow:auto;">
									<!--<logic:notEmpty
										name="clauseRev" property="refEDLNO">
	
										<logic:iterate id="refedl" name="clauseRev" property="refEDLNO">
											<bean:message key="screen.refEdl" />&nbsp;
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
										<logic:notEmpty	name="clauseRev" property="refEDLNO">
											<logic:iterate id="refedl" name="clauseRev" property="refEDLNO">
												<bean:message key="screen.refEdl.start" />&nbsp;
								           		<bean:write name="refedl" />
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
								</TD>

								<TD class="borderbottomrightlight">
									<bean:write name="clauseRev" property="defaultFlag" /> 
									<html:hidden name="clauseRev" property="defaultFlag" /> <!-- <logic:equal name="clauseRev" property="defaultFlag" value="Y">
					                <input type="radio" name="defaultFlag" value="Y" checked class="radcheck"/>
						            </logic:equal>
						            <logic:equal name="clauseRev" property="defaultFlag" value="N">
					                 <input type="radio" name="defaultFlag" value="Y"  class="radcheck"/>
						            </logic:equal> -->
						      	</TD>
							</TR>
						</logic:iterate>
					</TBODY>
					</TABLE>
					</logic:present>
				</logic:greaterThan>
				
				<div class="spacer20"></div>	
		
				<div class="form-group">
					<div class="space20"></div>	
					<div class="col-sm-12 text-center">
						<button class="btn btn-primary" name="Apply" onclick="javascript:fnClauseVersion()">OK</button>
						<button class="btn btn-primary" name="Cancel" onclick="fnCloseWindow()">Cancel</button>
					</div>
				</div>
			
			<html:hidden property="hdnClauseSeqNo" />
			<html:hidden property="hdnVersionNo" />
			<html:hidden property="hdnClauseNum" />
			<html:hidden property="hdnModelSeqNo" />
			<html:hidden property="hdnSectionSeqNo" />
			<html:hidden property="hdnSubSectionSeqNo" />
			<html:hidden property="chngToFlag"/>
	</html:form>
</div>
</BODY>
</HTML>
