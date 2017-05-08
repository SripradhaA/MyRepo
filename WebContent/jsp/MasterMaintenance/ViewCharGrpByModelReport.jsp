<!DOCTYPE HTML>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<HTML>
<HEAD>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link rel="shortcut icon" href="images/favicon.ico" />
	<link href="css/bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/EMDCustom.css" TYPE="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="js/Others/jquerylatest.js"></script>
	<script type="text/javascript" src="js/Others/jquerymigrate.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap.min.js"></script>
	<SCRIPT type="text/javascript" SRC="js/Emd_Lsdb.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/MasterMaintReport.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/error.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/error_messages.js"></SCRIPT>

<%!
	public String replace(String clause){

		
		if(clause != null && !"".equals(clause)){
		
			clause.replaceAll("&lt","<").replaceAll("&gt",">");
			
		}
		return clause; 
}
	
%>
<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
<script language="javascript">
function Print()
{
	window.print();
}
</script>
</HEAD>
<BODY>
<div class="container-fluid">
	<html:form action="/MasterSpecByMdlReport.do" method="post" styleClass="form-horizontal">
		<div class="row">
		<h4 class ="text-center">View Characteristic Group Report</h4>
		</div>
		<div class="row">
		<div class="spacer30"></div>
		</div>
		<div class="row">
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Specification Type</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<bean:write name="MasterSpecByMdlForm" property="hdnSelSpecType" />
			</div>
		</div>
		<div class="row">
			<div class="spacer10"></div>
		</div>
		<div class="row">
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Model</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<bean:write name="MasterSpecByMdlForm" property="modelName" />
			</div>
		</div>
		<div class="row">
			<div class="spacer20"></div>
		</div>
		<logic:notPresent name="MasterSpecByMdlForm"
			property="modelSubSectionList">
			<script> 
	             fnNoRecords();
	       	</script>
		</logic:notPresent>

		<logic:present name="MasterSpecByMdlForm"
			property="modelSubSectionList">
			<p class="text-center"><mark>All the Clauses shown below are Characteristic Core Clauses</mark></p>
		</logic:present>
		<div class="row">
			<div class="spacer10"></div>
		</div>
		<%
		int versionrow=0;
		%>

		<logic:present name="MasterSpecByMdlForm"
			property="modelSubSectionList">
			<logic:iterate id="modelsubsection" name="MasterSpecByMdlForm"
				property="modelSubSectionList" indexId="subsectionCount">
				<logic:iterate id="modelspecs" name="MasterSpecByMdlForm"
					property="modelSpecList" indexId="specCount">
					<bean:define id="speccount" name="specCount"></bean:define>
					<logic:equal name="subsectionCount"
						value="<%=String.valueOf(speccount)%>">

						<logic:iterate id="subsection" name="modelsubsection"
							indexId="subSectionIndex">
							<div class ="form-group">
							<logic:equal name="subSectionIndex" value="0">
								<logic:iterate id="section" name="MasterSpecByMdlForm"
									property="sectionList" indexId="sectionCount">
									<logic:equal name="sectionCount"
										value="<%=String.valueOf(speccount)%>">
										
											<div class="col-sm-12 text-center highlightDark">
												<strong><bean:write
													name="section" property="sectionCode" />.<bean:write
													name="section" property="sectionName" /></strong>
											</div>
										
									</logic:equal>
								</logic:iterate>
							</logic:equal>

							<!---------------------- !-->
							
							<div class="col-sm-12 text-center bg-info push-text-down">
									<bean:write
										name="subsection" property="subSecCode" />.<bean:write
										name="subsection" property="subSecName" /> <bean:define
										id="subSectionNo" name="subsection" property="subSecSeqNo" />
							</div>
							</div>
							
							<TABLE class="table table-bordered">
								<thead>
								<TR>
									<TH CLASS='text-center' width="15%">Characteristic Component Group</TH>
									<TH CLASS='text-center' width="15%">Characteristic Component</TH>
									<TH CLASS='text-center' width="5%">Characteristic Combination EDl #</TH>
									<TH CLASS='text-center' width="9%">Clause No</TH>
									<TH CLASS='text-center' width="45%">Clause Description</TH>
									<TH CLASS='text-center' width="10%">Engineering Data</TH>
								</TR>
								</thead>
								<tbody>
									<logic:iterate id="spec" name="modelspecs" indexId="rowcount">
										<logic:equal name="spec" property="subSectionSeqNo"
											value="<%=String.valueOf(subSectionNo)%>">
											<bean:define id="color"
												value="<%= String.valueOf((rowcount.intValue()) % 2) %>" />
													<TR>
														<TD COLSPAN=2 CLASS="text-center v-midalign" height="100%">
															<TABLE class="table table-bordered">
																<logic:iterate id="compGroup" name="spec"
																	property="compGroupVO"
																	type="com.EMD.LSDB.vo.common.CompGroupVO" scope="page">
																	<bean:define id="comp" name="compGroup" property="compVO" />
																	<TR>
																		<TD CLASS="text-center v-midalign" width="50%">
																		<logic:equal name="compGroup" property="validFlag" value="Y">
																		    <font color="red">* </font>
																		  </logic:equal>
																		<bean:write name="comp" property="componentGroupName" /></TD>
																		
																	    <TD CLASS="text-center v-midalign" width="50%">
																		<bean:write name="comp" property="componentName" /></TD>
																	</TR>				
																</logic:iterate>
															</TABLE>
														</TD>
				                                       	 <TD CLASS="text-center v-midalign">
															<logic:notEmpty  name="spec" property="charEdlNo">
															<bean:message key="screen.edl" />
															</logic:notEmpty>
															<bean:write name="spec"
																property="charEdlNo" />
																<logic:empty  name="spec" property="charEdlNo">&nbsp;</logic:empty>
																<br/>
															<logic:notEmpty  name="spec" property="charRefEDLNo">
																<bean:message key="screen.refEdl.start" />
																<bean:write name="spec"	property="charRefEDLNo" />
																<bean:message key="screen.refEdl.end" />
															</logic:notEmpty>
															<logic:empty  name="spec" property="charRefEDLNo">
																	&nbsp;
															</logic:empty>
														</TD>
														<TD CLASS="text-center v-midalign"><bean:write name="spec"
															property="clauseNum" /></TD>
														<TD CLASS="v-midalign"><bean:define name="spec"
															property="clauseDesc" id="clauseDesc" /> 
															<!-- CR-128 - Updated for Pdf issue -->
																<%String strClauseDesc =  String.valueOf(clauseDesc);
																if(strClauseDesc.startsWith("<p>")){%>
																	<%=(String.valueOf(clauseDesc))%>
																<%}else{ %>	
																	<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>
																<%}%>
															<logic:notEmpty name="spec" property="tableArrayData1">
																<TABLE class="table table-bordered">
																	<logic:iterate id="outter" name="spec" property="tableArrayData1" indexId="counter">
																	<!-- Added  For CR_93 -->
																	<bean:define name="spec" property="tableDataColSize" id="tableDataColSize" />
																		<bean:define id="row" name="counter" />
																		<tr>
																			<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
																				<logic:equal name="row" value="0">
																					<td valign="top" CLASS="text-center v-midalign"><b><font
																						color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></font></b>
																					</td>
																				</logic:equal>
																				<logic:notEqual name="row" value="0">
																					<td valign="top" CLASS="text-center v-midalign"><%=(tabrow==null)? "&nbsp;":tabrow%>
																					</td>
																				</logic:notEqual>
																			</logic:iterate>
																		</tr>
																	</logic:iterate>
																</table>
															</logic:notEmpty>
														</TD>
												<TD CLASS="text-center v-midalign"
													style="word-wrap: break-word;width:100;right:0">
													<logic:present name="spec" property="edlNO">
														<logic:iterate id="engDataEdlNo" name="spec"
															property="edlNO">
															<bean:message key="screen.edl" />
															<bean:write name="engDataEdlNo" />
															<br>
														</logic:iterate>
													</logic:present>
													<logic:present name="spec" property="refEDLNO">
														<logic:iterate id="engDataRefEdlNo" name="spec"
															property="refEDLNO">
															<bean:message key="screen.refEdl.start" />
															<bean:write name="engDataRefEdlNo" />
															<bean:message key="screen.refEdl.end" />
															<br>
														</logic:iterate>
													</logic:present> <logic:present name="spec"
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
													</logic:present> 
													<logic:present name="spec"
														property="partNumber">
														<logic:notEqual name="spec" property="dwONumber" value="0">
															<bean:message key="screen.partNo" />
															<bean:write name="spec" property="partNumber" />
															<br>
														</logic:notEqual>
												    </logic:present>
													<logic:present name="spec" property="priceBookNumber">
														<logic:notEqual name="spec" property="priceBookNumber"
															value="0">
															<bean:message key="screen.priceBookNo" />&nbsp;<bean:write
																name="spec" property="priceBookNumber" />
															<br>
														</logic:notEqual>
													</logic:present>
												    <logic:present name="spec"
															property="standardEquipmentDesc">
														<bean:write name="spec" property="standardEquipmentDesc" />
														<br>
													</logic:present> 
													<logic:present name="spec"
														property="engDataComment">
														<bean:define id="engDatCmnt" name="spec"
															property="engDataComment" />
														<%=engDatCmnt %>
														<br>
													</logic:present></TD>
												</TR>
											</logic:equal>
										</logic:iterate>
									</tbody>
								</TABLE>
							</logic:iterate>
						</logic:equal>
					</logic:iterate>
				</logic:iterate>
			</logic:present>
			<div class="row">
				<div class="spacer10"></div>
			</div>
			
			<div class="col-sm-12 text-center">
	             <button class="btn btn-primary" type='button' id="btnPrint" ONCLICK="Print()">Print</button>
	             <button class="btn btn-primary" type='button' id="btnCancel" ONCLICK="javascript:window.close();">Cancel</button>
			</div>
	<div class="row">
		<div class="spacer10"></div>
	</div>

	<html:hidden property="hdnSelSpecType" />
	</html:form>
	</div>
</BODY>
</HTML>
