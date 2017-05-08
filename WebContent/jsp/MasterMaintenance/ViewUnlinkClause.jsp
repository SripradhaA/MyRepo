<!DOCTYPE HTML>
<HTML>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="com.EMD.LSDB.vo.common.ClauseVO"%>
<HEAD>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
	<link href="css/bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/EMDCustom.css" rel="stylesheet"/>
	<link rel="shortcut icon" href="images/favicon.ico" />
	<script type="text/javascript" src="js/Others/jquerylatest.js"></script>
	<script type="text/javascript" src="js/Others/jquerymigrate.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap.min.js"></script>
	<SCRIPT type="text/javascript" SRC="js/Emd_Lsdb.js"></SCRIPT>
	<SCRIPT type="text/javascript" SRC="js/error.js"></SCRIPT>
	<SCRIPT type="text/javascript" SRC="js/error_messages.js"></SCRIPT>
	<script type="text/javascript" src="js/Others/jquery.bootbox.min.js"></script>
	<SCRIPT language="JavaScript" SRC="js/ModelAssignEdl.js"></SCRIPT>
	<script>
       $(document).ready(function() { 
			if ($("#clauseUnlinked").val() == "Y")	{
				window.close();
				window.opener.fnFetchCharCompGrps();
			}
       	});
	</script>
	<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
</HEAD>
<BODY>
	<div class="container" width="100%">
		<html:form action="/ModelAssignEdlAction.do">
				<h4 class="text-center">Select Unlink Clause</h4>
				<div class="spacer10"></div>
				<div class="errorlayerhide" id="errorlayer">
				</div>
				<logic:present name="ModelAssignEdlForm" property="messageID" scope="request">
					<bean:define id="messageID" name="ModelAssignEdlForm" property="messageID"/>
		            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
		            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
				</logic:present>
				
				<!-- Logic Tag Check For Display The Error Message After Add Functionality Starts Here -->
				<logic:present name="ModelAssignEdlForm"
					property="errorMessage" scope="request">
					<script>
                            hideErrors();
                            addMessage('<bean:write name="ModelClauseDescPopUpForm" property="errorMessage"/>');
                            showScrollErrors();
                        </script>
				</logic:present> <!-- Logic Tag Check For Display The Error Message After Add Functionality Ends Here -->
				
				<logic:present name="ModelAssignEdlForm" property="charGrpCombntnList"
						scope="request">
						<bean:size name="ModelAssignEdlForm" id="chargrpcombnsize"
							property="charGrpCombntnList" />
					</logic:present>
					
					<logic:match name="ModelAssignEdlForm" property="method"
						value="fetchLinkedClause">
						<logic:lessThan name="chargrpcombnsize" value="1">
							<script> 
				              fnNoRecords();
				        	</script>
				
						</logic:lessThan>
					</logic:match>
						
						<!-- Validation Message Display Part ends Here -->
						<!-- No records found message starts---->
						<logic:notPresent name="ModelAssignEdlForm"
							property="charGrpCombntnList">
							<script> 
				              fnNoRecords();
				        	</script>
				
						</logic:notPresent>
				
				<div class="spacer10"></div>
				
				<div class="row">
					<div class="col-sm-2 col-sm-offset-3 text-right text-nowrap"><strong>Specification Type</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<bean:write name="ModelAssignEdlForm" property="hdnSelSpecType" scope="request" />
					</div>
				</div>
				<div class="spacer10"></div>
				<div class="row">
					<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Model</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<bean:write name="ModelAssignEdlForm" property="hdnModelName" scope="request" />
					</div>
				</div>
				
				<div class="spacer30"></div>	
				<logic:notEmpty name="ModelAssignEdlForm" property="charGrpCombntnList">
						<table class="table table-bordered table-hover">
					    <thead>
					        <tr>
								<TH>Select</TH>
								<TH>Clause No</TH>
								<TH>Clause Description</TH>
								<TH>Engineering Data</TH>
							</tr>	
						</thead>
					    <tbody class="text-center">	
					    		<logic:iterate id="spec" name="ModelAssignEdlForm"
										property="charGrpCombntnList" indexId="clausecount"> 
										<TR>           
											<bean:define name="spec" property="clauseSeqNo"  id="hdnclauseseqNo" />   
	                         				<TD CLASS="v-midalign" WIDTH="10%">
						          				<html:radio property="clauseSeqNo" value="<%=(String.valueOf(hdnclauseseqNo))%>"></html:radio>
						          			</TD>
											<TD CLASS="v-midalign" WIDTH="20%"><bean:write name="spec" property="clauseNum" /> </TD>
											<TD CLASS="v-midalign" WIDTH="50%">
												<div id="clauseID<%=clausecount%>" align="left"
													STYLE="solid;height:80px;overflow:auto;">
															<bean:define name="spec"
																		property="clauseDesc" id="clauseDesc" />
																		<!-- CR-128 - Updated for Pdf issue -->
																			<%String strClauseDesc =  String.valueOf(clauseDesc);
																			if(strClauseDesc.startsWith("<p>")){%>
																				<%=(String.valueOf(clauseDesc))%>
																			<%}else{ %>	
																				 <%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>
																			<%}%>
																		<!-- CR-128 - Updated for Pdf issue Ends Here-->
																	
																		<logic:notEmpty name="spec" property="tableArrayData1">
																		<table class="table table-bordered text-center">
																		<logic:iterate id="outter" name="spec" property="tableArrayData1"
																				indexId="counter">
																				<!-- Added  For CR_93 -->
																		<bean:define name="spec" property="tableDataColSize" id="tableDataColSize" />
																				<bean:define id="row" name="counter" />
																				<tr>
																					<logic:iterate id="tabrow" name="outter"  length="tableDataColSize">
																						<logic:equal name="row" value="0">
																							<td valign="top"><b><font
																								color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></b></font>
																							</td>
																						</logic:equal>
																						<logic:notEqual name="row" value="0">
																							<td valign="top"><%=(tabrow==null)? "&nbsp;":tabrow%>
																							</td>
																						</logic:notEqual>
																					</logic:iterate>
																				</tr>
																			</logic:iterate>
																			</table>
																		</logic:notEmpty>
																	</div>
												</TD>
												<TD CLASS="v-midalign">
												<DIV STYLE="height:63;width:120;overflow-Y: auto ; height:63;width:120;overflow-X: auto ; ">
													<logic:present name="spec" property="edlNO">

													<logic:iterate id="engDataEdlNo" name="spec"
														property="edlNO">

														<bean:message key="screen.edl" />
														<bean:write name="engDataEdlNo" />
														<br>

													</logic:iterate>

												</logic:present> 
												<!-- CR 87 -->
												<logic:present name="spec" property="refEDLNO">

													<logic:iterate id="engDataRefEdlNo" name="spec"
														property="refEDLNO">

														<!--<bean:message key="screen.refEdl" />-->
														<bean:message key="screen.refEdl.start" />
														<bean:write name="engDataRefEdlNo" />
														<bean:message key="screen.refEdl.end" />
														<br>

													</logic:iterate>

												</logic:present><logic:present name="spec"
													property="partOF">

													<logic:iterate id="engPartOfCd" name="spec"
														property="partOF">

														<logic:notEqual name="engPartOfCd" value="0">

															<bean:message key="screen.partOf" />
															<bean:write name="engPartOfCd" />
															<br>

														</logic:notEqual>

													</logic:iterate>

												</logic:present> <logic:present name="spec"
													property="dwONumber">

													<logic:notEqual name="spec" property="dwONumber" value="0">

														<bean:message key="screen.dwoNo" />
														<bean:write name="spec" property="dwONumber" />
														<br>

													</logic:notEqual>

												</logic:present> <logic:present name="spec"
													property="partNumber">

													<logic:notEqual name="spec" property="dwONumber" value="0">

														<bean:message key="screen.partNo" />
														<bean:write name="spec" property="partNumber" />
														<br>

													</logic:notEqual>

												</logic:present> <!--Price Book Number is added in the Engineering data Section, modified on 30-09-08 by ps5722-->

												<logic:present name="spec" property="priceBookNumber">

													<logic:notEqual name="spec" property="priceBookNumber"
														value="0">

														<bean:message key="screen.priceBookNo" />&nbsp;<bean:write
															name="spec" property="priceBookNumber" />
														<br>

													</logic:notEqual>

												</logic:present> <logic:present name="spec"
													property="standardEquipmentDesc">

													<bean:write name="spec" property="standardEquipmentDesc" />
													<br>

												</logic:present> <logic:present name="spec"
													property="engDataComment">
													<bean:define id="engDatCmnt" name="spec"
														property="engDataComment" />
													<%=engDatCmnt %>
													<br>

												</logic:present>
												</DIV>
												</TD>
											</TR>
									</logic:iterate>
					    </tbody>
					    </table>
				</logic:notEmpty>
				
				<div class="spacer10"></div>
				
				<div class="form-group">
				  <div class="col-sm-12 text-center">
				  		<logic:greaterEqual name="chargrpcombnsize" value="1">
					       <button class="btn btn-primary" type='button' id="UnlinkButton" ONCLICK="javascript:fnUnlinkClause()">Unlink</button>
      				       <button class="btn btn-primary" type='button' id="cancelButton" ONCLICK="javascript:window.close();">Cancel</button>
						</logic:greaterEqual>
						<logic:lessThan name="chargrpcombnsize" value="1">
							<button class="btn btn-primary" type='button' id="cancelButton" ONCLICK="javascript:window.close();">Cancel</button>
						</logic:lessThan>		

					</div>   
			   	</div>
				<div class="spacer50"></div>
				
				
		<html:hidden property="hdnSelSpecType" />
		<html:hidden property="hdnModelName" /> <%-- Model Name added --%>
		<html:hidden property="modelseqno" />
		<html:hidden property="combntnSeqNo" />	
		<html:hidden property="clauseUnlinked" styleId="clauseUnlinked" />	    	  
		</html:form>
	</div>
</BODY>
</HTML>						
						