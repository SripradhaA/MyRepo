<!DOCTYPE HTML>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html>

	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link rel="shortcut icon" href="images/favicon.ico" />
	<link href="css/bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/datatables.min.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/EMDCustom.css" TYPE="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="js/Others/jquerylatest.js"></script>
	<script type="text/javascript" src="js/Others/jquerymigrate.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap.min.js"></script>
	<SCRIPT type="text/javascript" SRC="js/Emd_Lsdb.js"></SCRIPT>
	<SCRIPT type="text/JavaScript" SRC="js/MasterMaintReport.js"></SCRIPT>
	<SCRIPT type="text/JavaScript" SRC="js/error.js"></SCRIPT>
	<SCRIPT type="text/JavaScript" SRC="js/error_messages.js"></SCRIPT>

<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>

<BODY>
<div class="container-fluid">

<html:form action="/MasterMaintReportAction.do" method="post" styleClass="form-horizontal">
	
	<h4 class ="text-center">
		<bean:message key="Application.Screen.MaintReport" />
	</h4>
	
	<div class="spacer10"></div>
	
	<p class="text-center"><mark><bean:message key="DeletedClauseVersionMsg" /></mark></p>
	
	<p class="text-center"><mark><bean:message key="DefaultClauseVersionMsg" /></mark></p>
	
	<p class="text-center"><mark><bean:message key="MarkedClauseVersionMsg" /></mark></p>
		
	<div class="spacer20"></div>
	
	<div>
		<div class="form-group">
			<div class="col-sm-12 text-center">
				<strong>Specification Type :</strong>
					<bean:write name="MasterMaintForm"
					property="hdnSelSpecType" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<strong><bean:message key="master.model" /> :</strong>
					<bean:write name="MasterMaintForm" property="modelName" />
			</div>
	</div>

	<div class="errorlayerhide" id="errorlayer">
	</div>
	<logic:notPresent name="MasterMaintForm"
		property="modelSubSectionList">
		<script> 
             fnNoRecords();
       	</script>
	</logic:notPresent>

	<logic:present name="MasterMaintForm" property="modelSubSectionList">
		<logic:iterate id="modelsubsection" name="MasterMaintForm"
			property="modelSubSectionList" indexId="subsectionCount">
			<logic:iterate id="modelspecs" name="MasterMaintForm"
				property="modelSpecList" indexId="specCount">
				<bean:define id="speccount" name="specCount"></bean:define>
				<logic:equal name="subsectionCount"
					value="<%=String.valueOf(speccount)%>">

					<A Name="section<%=specCount%>"></A>
					<logic:iterate id="subsection" name="modelsubsection"
						indexId="subSectionIndex">
						
						<div class ="form-group">
							<div class="col-sm-12 text-center">
								<logic:present
									name="MasterMaintForm" property="sectionList">
									<logic:iterate id="section" name="MasterMaintForm"
										property="sectionList" indexId="count">
										<a href="#section<%=count%>" CLASS="linkstyleItem"> <bean:write
											name="section" property="sectionCode" />.<bean:write
											name="section" property="sectionName" /></a> &nbsp;|
		 							</logic:iterate>
								</logic:present>
							</div>
						</div>
						
						<div class ="form-group">	
							<logic:equal name="subSectionIndex" value="0">
								<logic:iterate id="section" name="MasterMaintForm"
									property="sectionList" indexId="sectionCount">
									<logic:equal name="sectionCount"
										value="<%=String.valueOf(speccount)%>">
										<div class="col-sm-12 text-center highlightDark">
											<div>
												<strong> <bean:write
												name="section" property="sectionCode" />.<bean:write
												name="section" property="sectionName" /></strong>
											</div>
										</div>
									</logic:equal>
								</logic:iterate>
							</logic:equal>
							
							<div class="col-sm-12 text-center bg-info push-text-down">
								<bean:write name="subsection"
								property="subSecCode" />.<bean:write name="subsection"
								property="subSecName" /> <bean:define id="subSectionNo"
								name="subsection" property="subSecSeqNo" />
							</div>
						</div>
						
						<TABLE class="table table-bordered">
						<THEAD>
							<TR>
								<TH CLASS='text-center' width="10%">LSDB Unique Clause Number</TH>
								<TH CLASS='text-center' width="5%">Clause Version</TH>
								<TH CLASS='text-center' width="25%">Clause Description</TH>
								<TH CLASS='text-center' width="10%">Engineering Data</TH>
								<TH CLASS='text-center' width="10%">Component</TH>
								<TH CLASS='text-center' width="10%">Component Group</TH>
								<TH CLASS='text-center' width="10%">Customer</TH>
								<TH CLASS='text-center' width="10%">Modified By</TH>
								<TH CLASS='text-center' width="10%">Modified Date</TH>
							</TR>
						</THEAD>
						<TBODY>
							<logic:iterate id="spec" name="modelspecs">
								<logic:equal name="spec" property="subSectionSeqNo"
									value="<%=String.valueOf(subSectionNo)%>">
									<logic:equal name="spec" property="clauseDelFlag" value="Y">
										<logic:equal name="MasterMaintForm"
											property="showDeletedClauses" value="Y">
											<TR class="text-red">
										</logic:equal>
										<logic:notEqual name="MasterMaintForm"
											property="showDeletedClauses" value="Y">
											<TR style="display:none">
										</logic:notEqual>
									</logic:equal>
									<logic:notEqual name="spec" property="clauseDelFlag" value="Y">
										<logic:equal name="spec" property="defaultFlag" value="Y">
											<TR style="font-weight: bold;">
										</logic:equal>
										<logic:notEqual name="spec" property="defaultFlag" value="Y">
											<TR>
										</logic:notEqual>
										<%--Added for CR-134 --%>
										<logic:equal name="spec" property="userMarkFlag" value="Y" >																			
											<TR style="background-color:yellow">
										</logic:equal>
										<%--Added for CR-134 --%>
									</logic:notEqual>
									
									<%--Added for CR-134 --%>
									<logic:equal name="spec" property="userMarkFlag" value="Y">
										<TD CLASS="text-center v-midalign">														
											<logic:notEmpty name="spec" property="markClaReason">
     											<A nohref="#" data-toggle="tooltip" title="<bean:write name="spec" property="markClaReason"  />">
     										</logic:notEmpty>					
											<bean:write name="spec"	property="clauseSeqNo" />	
										</TD>		        											
     								</logic:equal>
      								<logic:notEqual name="spec" property="userMarkFlag" value="Y">
										<TD CLASS="text-center v-midalign"><bean:write name="spec"
										property="clauseSeqNo" /></TD>
									</logic:notEqual>
									<%--Added for CR-134 --%>	
									<TD CLASS="text-center v-midalign"><bean:write name="spec"
										property="versionNo" /></TD>
									
									<TD>
										<DIV STYLE="solid;height:80px;overflow:auto;">

											<bean:define name="spec" property="clauseDesc"
											id="clauseDesc" />
											<!-- CR-128 - Updated for Pdf issue -->
												<%String strClauseDesc =  String.valueOf(clauseDesc);
												if(strClauseDesc.startsWith("<p>")){%>
													<%=(String.valueOf(clauseDesc))%>
												<%}else{ %>	
													<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>							
												<%}%>
										<logic:notEmpty name="spec" property="tableArrayData1">
											<TABLE class="table table-bordered">
												
											<logic:iterate id="outter" name="spec" property="tableArrayData1"
												indexId="counter">

												<bean:define id="row" name="counter" />
											<bean:define name="spec" property="tableDataColSize" id="tableDataColSize" />
											<logic:equal name="spec" property="clauseDelFlag" value="Y">
	
												<logic:equal name="MasterMaintForm"
													property="showDeletedClauses" value="Y">
													<TR class="text-red">
												</logic:equal>
												<logic:notEqual name="MasterMaintForm"
													property="showDeletedClauses" value="Y">
													<TR style="display:none ">
												</logic:notEqual>
											</logic:equal>
											<logic:notEqual name="spec" property="clauseDelFlag"
												value="Y">
												<logic:equal name="spec" property="defaultFlag" value="Y">
													<TR style="font-weight: bold;">
												</logic:equal>
												<logic:notEqual name="spec" property="defaultFlag"
													value="Y">
													<TR>
												</logic:notEqual>
											</logic:notEqual>
											<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
												<logic:equal name="row" value="0">
													<td valign="top" CLASS="text-center v-midalign"><strong><font
														color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></strong></font>
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
										</DIV>
									</TD>
									<TD CLASS="text-center v-midalign">
										
											<logic:equal name="spec" property="clauseDelFlag" value="Y">
												<logic:equal name="MasterMaintForm"
													property="showDeletedClauses" value="Y">
													<DIV STYLE="height:80px;overflow:auto;display:table;width:100%;" class="text-red">
													<div STYLE="display:table-cell;vertical-align: middle;text-align:center;">
												</logic:equal>
												<logic:notEqual name="MasterMaintForm"
													property="showDeletedClauses" value="Y">
													<DIV STYLE="height:80px;overflow:auto;display:none">
													<div STYLE="display:table-cell;vertical-align: middle;text-align:center;">
												</logic:notEqual>
											</logic:equal>

										<logic:notEqual name="spec" property="clauseDelFlag"
											value="Y">
											<logic:equal name="spec" property="defaultFlag" value="Y">
												<DIV STYLE="height:80px;overflow:auto;font-weight: bold;display: table;width:100%;">
												<div STYLE="display:table-cell;vertical-align: middle;text-align:center;">
											</logic:equal>
											<logic:notEqual name="spec" property="defaultFlag" value="Y">
												<DIV STYLE="height:80px;overflow:auto;display: table;width:100%;">
												<div STYLE="display:table-cell;vertical-align: middle;text-align:center;">
											</logic:notEqual>
										</logic:notEqual>
											<logic:present name="spec"
												property="edlNO">
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
											</logic:present>
											<logic:present name="spec"
												property="partOF">
												<logic:iterate id="engPartOfCd" name="spec"
													property="partOF">
													<logic:notEqual name="engPartOfCd" value="0">
														<bean:message key="screen.partOf" />
														<bean:write name="engPartOfCd" />
														<br>
													</logic:notEqual>
												</logic:iterate>
											</logic:present>
										    <logic:present name="spec"
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
											</logic:present>
											</div>
									</DIV>
								</TD>
										<TD CLASS="text-center">
										<logic:present name="spec" property="componentVO">
											<logic:equal name="spec" property="clauseDelFlag" value="Y">
												<logic:equal name="MasterMaintForm"
													property="showDeletedClauses" value="Y">
													<DIV STYLE="height:80px;overflow:auto;display:table;width:100%;" class="text-red">
													<div STYLE="display:table-cell;vertical-align: middle;text-align:center;">
												</logic:equal>
												<logic:notEqual name="MasterMaintForm"
													property="showDeletedClauses" value="Y">
													<DIV STYLE="height:80px;overflow:auto;display:none">
													<div STYLE="display:table-cell;vertical-align: middle;text-align:center;">
												</logic:notEqual>
											</logic:equal>
											<logic:notEqual name="spec" property="clauseDelFlag"
												value="Y">
												<logic:equal name="spec" property="defaultFlag" value="Y">
													<DIV STYLE="height:80px;overflow:auto;font-weight: bold;display: table;width:100%;">
													<div STYLE="display:table-cell;vertical-align: middle;text-align:center;">
												</logic:equal>
												<logic:notEqual name="spec" property="defaultFlag" value="Y">
													<DIV STYLE="height:80px;overflow:auto;display: table;width:100%;">
													<div STYLE="display:table-cell;vertical-align: middle;text-align:center;">
												</logic:notEqual>
											</logic:notEqual>								
												<logic:iterate id="component" name="spec"
													property="componentVO" indexId="compntIndex">
													<logic:notEqual name="compntIndex" value="0"> ;<br>
													</logic:notEqual>
													<bean:write name="component" property="componentName" />
												</logic:iterate>
											</DIV>
										</DIV>
										</logic:present>
										</TD>
									<TD CLASS="text-center">
										<logic:present name="spec" property="componentVO">
												<logic:equal name="spec" property="clauseDelFlag" value="Y">
													<logic:equal name="MasterMaintForm"
														property="showDeletedClauses" value="Y">
														<DIV STYLE="height:80px;overflow:auto;display:table;width:100%;" class="text-red">
														<div STYLE="display:table-cell;vertical-align: middle;text-align:center;">
													</logic:equal>
													<logic:notEqual name="MasterMaintForm"
														property="showDeletedClauses" value="Y">
														<DIV STYLE="height:80px;overflow:auto;display:none">
														<div STYLE="display:table-cell;vertical-align: middle;text-align:center;">
													</logic:notEqual>
												</logic:equal>

												<logic:notEqual name="spec" property="clauseDelFlag"
													value="Y">
													<logic:equal name="spec" property="defaultFlag" value="Y">
														<DIV STYLE="height:80px;overflow:auto;font-weight: bold;display: table;width:100%;">
														<div STYLE="display:table-cell;vertical-align: middle;text-align:center;">
													</logic:equal>
													<logic:notEqual name="spec" property="defaultFlag" value="Y">
														<DIV STYLE="height:80px;overflow:auto;display: table;width:100%;">
														<div STYLE="display:table-cell;vertical-align: middle;text-align:center;">
													</logic:notEqual>
												</logic:notEqual>
													<logic:iterate id="component" name="spec"
														property="componentVO" indexId="compntIndex">
														<logic:notEqual name="compntIndex" value="0">
															<br>
														</logic:notEqual>
														<bean:write name="component" property="componentGroupName" />
													</logic:iterate>
													</DIV>
												</DIV>
										</logic:present>
									</TD>
										<TD CLASS="text-center v-midalign"><bean:write name="spec"
											property="customerName" />&nbsp;</TD>
										<TD CLASS="text-center v-midalign"><bean:write name="spec"
											property="modifiedBy" /></TD>
										<TD CLASS="text-center v-midalign"><bean:write
											name="spec" property="modifiedDate" /></TD>		
									</TR>
									</TBODY>
								</logic:equal>
							</logic:iterate>
						</TABLE>
						<br>
					</logic:iterate>						
				</logic:equal>
			</logic:iterate>
		</logic:iterate>
	</logic:present>
		
	<div class="col-sm-12 text-center">
             <button class="btn btn-primary" type='button' id="btnPrint" ONCLICK="window.close();">Cancel</button>
	</div>
	
	<div class="spacer10"></div>
		
</html:form>
</div>
</BODY>
</html>
