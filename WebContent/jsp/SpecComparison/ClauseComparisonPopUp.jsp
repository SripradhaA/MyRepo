<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html>
<HEAD>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link rel="shortcut icon" href="images/favicon.ico" />
	<link href="css/bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/bootstrap-table.min.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/EMDCustom.css" TYPE="text/css" rel="stylesheet"/> 
	<script type="text/javascript" src="js/Others/jquerylatest.js"></script>
	<script type="text/javascript" src="js/Others/jquerymigrate.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap-table-all.min.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap-table-en-US.min.js"></script>
	<script type="text/javascript" src="js/Others/jquery.bootbox.min.js"></script>
	<SCRIPT type="text/javascript" src="js/Others/webtoolkit.aim.js"></SCRIPT>
	<LINK REL="stylesheet" HREF="css/Emd_Lsdb.css" TYPE="text/css">
	<SCRIPT LANGUAGE="JavaScript" SRC="js/CompInOrderReport.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/Others/jquerymin.js"></SCRIPT>
	<LINK REL="stylesheet" TYPE="text/css" HREF="css/jquery-ui-custom.min.css">
	<SCRIPT LANGUAGE="JavaScript" SRC="js/Others/jquery-ui.custom.min.js"></SCRIPT>
	<LINK REL="stylesheet" HREF="css/Emd_Lsdb.css" TYPE="text/css">
	<SCRIPT LANGUAGE="JavaScript" SRC="js/error.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/error_messages.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/ModifySpec.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/SpecComparison.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript">
	function Print()
	{
		window.print();
	}</SCRIPT>

</HEAD>

<TITLE>Clause Comparison</TITLE>

<BODY class="main">

<div class = "container-fluid">

<html:form action="/compareSpecAction" method="post">

	<div class="spacer10"></div>
		
	<h4 class ="text-center">Clause Comparison</h4>
	
	<div class="spacer20"></div>
	
	<TABLE class="table table-bordered" id="tbClauseCo,parision">
		<TR>
			<logic:iterate id="ordListId" name="ClauseCompareForm"
				property="selectedOrderList">
				<TD WIDTH="5%">
				<TABLE class="table table-bordered">
					<TR>
						<TD CLASS="heading-fontbgcolor" width="40%" nowrap>Order Number</TD>
						<TD CLASS="text-left v-midalign" width="60%" colspan=2 nowrap><b><bean:write name="ordListId"
							property="orderNo" /></b></TD>
					</TR>
					<tr>
						<TD CLASS="heading-fontbgcolor" width="40%" nowrap>Model</TD>
						<TD CLASS="text-left v-midalign" width="60%" colspan=2 nowrap><b><bean:write name="ordListId"
							property="modelName" /></b></TD>
					</TR>
					<TR>
						<TD CLASS="heading-fontbgcolor" width="40%" nowrap>Customer Name</TD>
						<TD CLASS="text-left v-midalign" width="60%" colspan=2 nowrap><b><bean:write name="ordListId"
							property="customerName" /></b></TD>
					</tr>
					<tr>
						<TD CLASS="heading-fontbgcolor" width="40%" nowrap>Spec Status</TD>
						<TD CLASS="text-left v-midalign" width="60%" colspan=2 nowrap><b><bean:write name="ordListId"
							property="specTypeName" /></b></TD>
					</TR>
				</TABLE>
				</TD>
			</logic:iterate>
		</TR>
		
		<logic:iterate id="componentCompareId" name="ClauseCompareForm"
			property="compareOrderList" indexId="count">
			<TR>
				<logic:iterate id="sectionId" name="componentCompareId">
					<TD WIDTH="5%" style="align:top">
					<TABLE class="table table-bordered">
						<logic:notEmpty name="sectionId" property="secName">
							<TR>
							   <logic:present name="sectionId" property="secName">
								   <TD colspan="3" height="10%" CLASS="table-subheading" ALIGN="center"><b><bean:write
									name="sectionId" property="secName" /></b></TD>
							  </logic:present>
							 </TR>
							 </logic:notEmpty>
						
							<TR>
								<logic:present name="sectionId" property="subSecName">
									<TD width="100%" colspan="3" height="10%" CLASS="heading-fontbgcolor" ALIGN="center"><bean:write
										name="sectionId" property="subSecName" /></TD>
								</logic:present>
							</TR>
							<logic:notEmpty name="sectionId" property="clauseGroup">
								<TR>
									<TD WIDTH="20%" height="10%" CLASS="table-heading" ALIGN="center">Clause Number</TD>
									<TD WIDTH="50%" height="10%" CLASS="table-heading" ALIGN="center">Description</TD>
									<TD WIDTH="30%" height="10%" CLASS="table-heading" ALIGN="center">Engineering Data</TD>
								</TR>
								<logic:iterate name="sectionId" id="componentDescId"
									property="clauseGroup">
									<TR>
										<TD WIDTH="20%" height="10%" align="left"
											CLASS="text-center v-midalign"><bean:write name="componentDescId"
											property="clauseNum" /></TD>
										<TD WIDTH="50%" height="10%" align="left" valign="top"
											CLASS="text-center v-midalign"><logic:equal name="ClauseCompareForm"
											property="orderListSize" value="3">
										</logic:equal> <logic:equal name="ClauseCompareForm"
											property="orderListSize" value="2">
										</logic:equal>				           
										           
								            <logic:equal name="componentDescId" property="clauseDelFlag" value="Y">
										    <bean:message key="Message.Reserved" />
											</logic:equal>
											<logic:notEqual name="componentDescId" property="clauseDelFlag" value="Y">
										
																		
															<bean:define name="componentDescId" property="clauseDesc"
																id="clauseDesc" /> 
																	<%String strClauseDesc =  String.valueOf(clauseDesc);
																	if(strClauseDesc.startsWith("<p>")){%>
																		<%=(String.valueOf(clauseDesc))%>
																	<%}else{ %>	
																		<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>							
																	<%}%>
															
														<logic:notEmpty name="componentDescId"
																	property="tableArrayData1">
																	&nbsp;
														
																<TABLE class="table table-bordered">
																	<logic:iterate id="outter" name="componentDescId"
																		property="tableArrayData1" indexId="counter">
																		<bean:define id="row" name="counter" />
																		<!-- Added  For CR_93 -->
												<bean:define name="componentDescId" property="tableDataColSize" id="tableDataColSize" />
																		<tr>
																			<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
																				<logic:equal name="row" value="0">
																					<td valign="top" width="5%" CLASS="text-center v-midalign"><b><font
																						color="navy"><%=(tabrow == null) ? "&nbsp;"
																					: tabrow%></b></font>
																					</td>
																				</logic:equal>
																				<logic:notEqual name="row" value="0">
																					<td valign="top" width="5%" CLASS="text-center v-midalign"><%=(tabrow == null) ? "&nbsp;"
																					: tabrow%>
																					</td>
																				</logic:notEqual>
																			</logic:iterate>
																		</tr>
																	</logic:iterate>
																</table>	
															
														</logic:notEmpty>
											</logic:notEqual>
										</TD>
										<logic:equal name="componentDescId" property="clauseDelFlag" value="Y">
										<TD width="30%" CLASS="text-center v-midalign nowrap" align="left">&nbsp;</TD>
										</logic:equal>
										
										<logic:notEqual name="componentDescId" property="clauseDelFlag" value="Y">
										<!--Added for CR-74 16-06-09 -->
										<TD width="30%" CLASS="text-center v-midalign nowrap" align="left">
										<!-- Added For CR_81 to bring Charstc Edl and Refedl Starts -->
										<logic:equal name="componentDescId" property="selectCGCFlag" value="Y">
											<logic:notEmpty	name="componentDescId" property="charEdlNo">
											EDL&nbsp;
												<bean:write name="componentDescId" property="charEdlNo" />
												<br>
											</logic:notEmpty>
											<logic:notEmpty	name="componentDescId" property="charRefEDLNo">
												<bean:message key="screen.refEdl.start" />&nbsp;
													<bean:write name="componentDescId" property="charRefEDLNo" />&nbsp;
												<bean:message key="screen.refEdl.end" />
												<br>	
											</logic:notEmpty>
											<logic:empty name="componentDescId" property="charEdlNo">	
											<logic:empty name="componentDescId" property="charRefEDLNo">			
												EDL Undeveloped
												<br>
											</logic:empty>
											</logic:empty>
										</logic:equal>
										<logic:notEmpty name="componentDescId" property="edlNO">
										  <logic:iterate id="edl" name="componentDescId" property="edlNO">
			  	                                EDL&nbsp;<bean:write name="edl" />
												<br>
											</logic:iterate>
										</logic:notEmpty> 
										<!-- CR 87 -->
										<logic:notEmpty  name="componentDescId" property="refEDLNO">
											<logic:iterate id="refedl" name="componentDescId"
												property="refEDLNO">		  	                               
			  	                                 <bean:message key="screen.refEdl.start" />&nbsp;
			  	                                 <bean:write name="refedl" />&nbsp;
			  	                                  <bean:message key="screen.refEdl.end" />
												<br>
											</logic:iterate>
										</logic:notEmpty> 
										<logic:notEmpty name="componentDescId"	property="partOF">
											<logic:iterate id="partof" name="componentDescId"
												property="partOF" type="com.EMD.LSDB.vo.common.SubSectionVO">
			  	                                 <bean:message key="screen.partOf" />&nbsp;<bean:write name="partof" property="subSecCode" />
												<br>
											</logic:iterate>
										</logic:notEmpty> 
										<logic:notEmpty name="componentDescId"  property="dwONumber">
											<logic:notEqual name="componentDescId" property="dwONumber"	value="0">
			  	                               DWO&nbsp;<bean:write name="componentDescId" property="dwONumber" />
												<br>
											</logic:notEqual>
										</logic:notEmpty>
									 <logic:notEmpty name="componentDescId"	property="partNumber">
										<logic:notEqual name="componentDescId" property="partNumber" value="0">
			  	                               Part No&nbsp;<bean:write name="componentDescId"	property="partNumber" />
												<br>
											</logic:notEqual>
										</logic:notEmpty> 
										<logic:notEmpty name="componentDescId"	property="standardEquipmentDesc">
											<bean:write name="componentDescId"	property="standardEquipmentDesc" />
											<br>
										</logic:notEmpty> <logic:notEmpty name="componentDescId" property="engDataComment">
											<bean:define name="componentDescId" property="engDataComment"
												id="engData" />
											<%=engData%>
											<br>
										</logic:notEmpty>
										</TD>
										</logic:notEqual>
									</TR>
								</logic:iterate>
							</logic:notEmpty>
						</TABLE>
					</TD>
				</logic:iterate>
			</TR>
		</logic:iterate>
	</TABLE>
	
	<div class="row">
		<div class="col-sm-12 text-center">
			<button class="btn btn-primary" type='button' id="btnPrint" ONCLICK="Print()">Print</button>
			<button class="btn btn-primary" type='button' id="btnCancel" ONCLICK="window.close()">Cancel</button>
		</div>
	</div>
	
	<div class="spacer50"></div>
	
</html:form>
</div>
</BODY>
</html:html>
