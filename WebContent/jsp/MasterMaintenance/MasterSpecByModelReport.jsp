<!DOCTYPE HTML>
<HTML>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<HEAD>
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
	<script type="text/javascript" src="js/Others/datatables.min.js"></script>
	<script type="text/javascript" src="js/Others/datatables.plugins.js"></script>
	<SCRIPT type="text/javascript" SRC="js/Emd_Lsdb.js"></SCRIPT>
</HEAD>
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
$(document).ready(function(){
	$('.orderClaVer').tooltip({
		title : function (){ return $('#ClauseDiv'+$(this).prop('id')).html(); },
		template : '<div class="tooltip" role="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner noWidth"></div></div>',
		html : true,
		placement: "left"
	});
})
function Print()
{
	window.print();
}

function fnMouseClick(divID,evt,obj)
{
           
			var div = document.getElementById(divID);
            div.style.display='';
			 var curleft = 0;
            var curtop = 0;
            
            if (obj.offsetParent) 
            {
                        curleft = obj.offsetLeft
                        curtop = obj.offsetTop
                        while (obj = obj.offsetParent) 
                        {
                                    curleft += obj.offsetLeft
                                    curtop += obj.offsetTop
                        }
						
            }                       
           
			div.style.left = curleft-330 ;
            div.style.top =  curtop+10 ;

			document.getElementById(divID).style.visibility="visible";
}

function fnMouseOut(divelem)
{
	 document.getElementById(divelem).style.visibility="hidden";
}
</script>

<BODY>
<div class="container-fluid">
	<html:form action="/MasterSpecByMdlReport.do" method="post">
		<h4 class ="text-center">View Report</h4>
		<div class="spacer20"></div>
		<div class="form-group">
			<div class="col-sm-12 text-center">
				<strong>Specification Type :</strong>
					<bean:write name="MasterSpecByMdlForm"
					property="hdnSelSpecType" />
				&nbsp;&nbsp;&nbsp;&nbsp;
				<strong>Model :</strong>
					<bean:write name="MasterSpecByMdlForm"
					property="modelName" />
			</div>
		</div>
		<div class="spacer20"></div>
		<div class="errorlayerhide" id="errorlayer"></div>
		
		<logic:notPresent name="MasterSpecByMdlForm"
			property="modelSubSectionList">
			<script> 
              fnNoRecords();
        	</script>
		</logic:notPresent>
		<!-- No records found message ends---->
		
		<logic:present name="MasterSpecByMdlForm"
			property="modelSubSectionList">
			<p class="text-center"><mark>Master Clauses : All the Clauses shown below are Master Clauses.</mark></p>
			<p class="text-center"><mark>Version <font color="red">*</font>: Denotes the Master version/Default version used in the corresponding Order.</mark></p>
			<p class="text-center"><mark>Components in ITALICS: Component names in ITALICS denotes that it is not a lead Component.</mark></p>
			<p class="text-center"><mark>Tool Tip : Clause Description for each individual order shall be shown as a Tool Tip on mouse over of Clause Version.</mark></p>
		</logic:present>
		<div class="spacer10"></div>
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
						
						<logic:empty name="modelsubsection">

							<logic:iterate id="section" name="MasterSpecByMdlForm"
								property="sectionList" indexId="sectionCount">

								<logic:equal name="sectionCount"
									value="<%=String.valueOf(speccount)%>">
	
									<div class="col-sm-12 text-center highlightDark">
										<strong>
											<bean:write name="section" property="sectionCode" />.
											<bean:write name="section" property="sectionName" />
										</strong>
									</div>
								</logic:equal>
							</logic:iterate>
						</logic:empty>

						<logic:notEmpty name="modelsubsection">
							<logic:iterate id="subsection" name="modelsubsection"
								indexId="subSectionIndex">
							<logic:equal name="subSectionIndex" value="0">
							<logic:iterate id="section" name="MasterSpecByMdlForm"
								property="sectionList" indexId="sectionCount">
								<logic:equal name="sectionCount"
									value="<%=String.valueOf(speccount)%>">
	
									<div class="col-sm-12 text-center highlightDark">
										<strong>
											<bean:write name="section" property="sectionCode" />.
											<bean:write name="section" property="sectionName" />
										</strong>
									</div>
								</logic:equal>
							</logic:iterate>
							</logic:equal>

							<div class="col-sm-12 text-center bg-info push-text-down">
								<bean:write	name="subsection" property="subSecCode" />.
								<bean:write	name="subsection" property="subSecName" /> 
								<bean:define id="subSectionNo" name="subsection" property="subSecSeqNo" />
							</div>
							
							<TABLE class="table table-bordered">
								<thead>
									<TR>
										<TH CLASS='text-center' width="10%">Component Group</TH>
										<TH CLASS='text-center' width="15%">Component</TH>
										<TH CLASS='text-center' width="5%">Default Component</TH>
										<TH CLASS='text-center' width="9%">Clause No</TH>
										<TH CLASS='text-center' width="50%">Clause Description</TH>
										<TH CLASS='text-center' width="10%">Engineering Data</TH>
									</TR>
									<logic:present name="MasterSpecByMdlForm" property="hnOrderKey">
										<logic:notEmpty name="MasterSpecByMdlForm" property="hnOrderKey">
											<TR>
												<TH CLASS='text-center' width="5%" colspan=2>Order No</TH>
												<TH CLASS='text-center' width="5%">Spec Status</TH>
												<TH CLASS='text-center' width="7%" colspan=2>Customer</TH>
												<TH CLASS='text-center' width="7%">Version</TH>
											</TR>
										</logic:notEmpty>
									</logic:present>
								</thead>
								<logic:iterate id="spec" name="modelspecs" indexId="rowcount">
									<logic:equal name="spec" property="subSectionSeqNo"
										value="<%=String.valueOf(subSectionNo)%>">
										<bean:define id="color"
												value="<%= String.valueOf((rowcount.intValue()) % 2) %>" />
											<logic:equal name="color" value="0">
												<TR bgcolor="#E9E9E9">
											</logic:equal>
											<logic:notEqual name="color" value="0">
												<TR bgcolor="#CDCDCD">
											</logic:notEqual>

											<TD COLSPAN=3 height="100%">
												<TABLE WIDTH="100%" CLASS='borderfull v-midalign' height="100%">

													<logic:empty name="spec" property="compGroupVO">
														<TR>
															<TD CLASS=borderbottomrightlight3 width="33%">&nbsp;</TD>
															<TD CLASS=borderbottomrightlight3 width="50%">&nbsp;</TD>
															<TD CLASS=borderbottomrightlight4 width="25%">&nbsp;</TD>
														</TR>
													</logic:empty>

													<logic:iterate id="compGroup" name="spec"
														property="compGroupVO"
														type="com.EMD.LSDB.vo.common.CompGroupVO" scope="page">

														<logic:equal name="compGroup" property="leadFlag"
															value="Y">
															<TR class="text-center">
																<TD width="33%" class="v-midalign">
																	<logic:equal name="compGroup" property="validFlag" value="Y">
																		<em><font color="red">* </font></em>
																	</logic:equal> 
																	<em><bean:write name="compGroup" property="componentGroupName" /></em>
																</TD>

																<logic:empty name="compGroup" property="componentGroupName">
																	<TD width="33%" class="v-midalign">&nbsp;</TD>
																</logic:empty>

																<bean:define id="comp" name="compGroup"	property="compVO" />

																<TD width="50%" class="v-midalign">
																	<em><bean:write name="comp" property="componentName" /></em>
																</TD>

																<logic:empty name="comp" property="componentName">
																	<TD width="50%" class="v-midalign">&nbsp;</TD>
																</logic:empty>

																<logic:equal name="comp" property="defaultFlag" value="true">
																	<TD width="25%" class="v-midalign"><em>X</em></TD>
																</logic:equal>
																
																<logic:notEqual name="comp" property="defaultFlag"
																	value="true">
																	<TD width="25%" class="v-midalign">&nbsp;</TD>
																</logic:notEqual>
															</TR>
														</logic:equal>
														<!-- Not Italics Component group-->
														<logic:notEqual name="compGroup" property="leadFlag" value="Y">
															<TR class="text-center">
																<TD width="33%" class="v-midalign">
																	<logic:equal name="compGroup" property="validFlag" value="Y">
																		<font color="red">* </font>
																	</logic:equal> 
																	<bean:write name="compGroup" property="componentGroupName" />
																</TD>

																<logic:empty name="compGroup" property="componentGroupName">
																	<TD width="33%" class="v-midalign">&nbsp;</TD>
																</logic:empty>

																<bean:define id="comp" name="compGroup" property="compVO" />

																<TD width="50%" class="v-midalign">
																	<bean:write name="comp" property="componentName" />
																</TD>

																<logic:empty name="comp" property="componentName">
																	<TD width="50%" class="v-midalign">&nbsp;</TD>
																</logic:empty>

																<logic:equal name="comp" property="defaultFlag" value="true">
																	<TD width="25%" class="v-midalign">X</TD>
																</logic:equal>

																<logic:notEqual name="comp" property="defaultFlag" value="true">
																	<TD width="25%" class="v-midalign">&nbsp;</TD>
																</logic:notEqual>
															</TR>
														</logic:notEqual>

													</logic:iterate>
												</TABLE>
											</TD>

											<TD class="text-center v-midalign"><bean:write name="spec"
												property="clauseNum" /></TD>
											<TD class="v-midalign"><bean:define name="spec"
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
														&nbsp;
													<logic:iterate id="outter" name="spec" property="tableArrayData1"
															indexId="counter">
															<bean:define id="row" name="counter" />
															<!-- Added  For CR_93 -->
													<bean:define name="spec" property="tableDataColSize" id="tableDataColSize" />
															<tr>
																<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
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
											</TD>

											<TD class="text-center v-midalign"style="word-wrap: break-word;width:100;right:0">
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
												</logic:present> 
												<logic:present name="spec" property="partOF">
													<logic:iterate id="engPartOfCd" name="spec"
														property="partOF">
														<logic:notEqual name="engPartOfCd" value="0">
															<bean:message key="screen.partOf" />
															<bean:write name="engPartOfCd" />
															<br>
														</logic:notEqual>
													</logic:iterate>
												</logic:present> 
												<logic:present name="spec" property="dwONumber">
													<logic:notEqual name="spec" property="dwONumber" value="0">
														<bean:message key="screen.dwoNo" />
														<bean:write name="spec" property="dwONumber" />
														<br>
													</logic:notEqual>
												</logic:present> 
												<logic:present name="spec" property="partNumber">
													<%-- Modified the property from DWO number to Part Number  in CR_108 --%>
													<logic:notEqual name="spec" property="partNumber" value="0">
													<%-- Modification ends here --%>
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
											</TD>
										</TR>
										<logic:present name="MasterSpecByMdlForm"
											property="hnOrderKey">
											<logic:notEmpty name="MasterSpecByMdlForm"
												property="hnOrderKey">

												<logic:present name="spec" property="orderVO">
													<logic:iterate id="order" name="spec" property="orderVO"
														type="com.EMD.LSDB.vo.common.OrderVO" scope="page">

														<logic:equal name="color" value="0">
															<TR bgcolor="#E9E9E9" class="text-center">
														</logic:equal>

														<logic:notEqual name="color" value="0">
															<TR bgcolor="#CDCDCD" class="text-center">
														</logic:notEqual>

														<TD colspan=2 class="v-midalign"><bean:write
															name="order" property="orderNo" /></TD>
														<TD class="v-midalign"><bean:write name="order"
															property="statusDesc" /></TD>
														<TD colspan=2 class="v-midalign"><bean:write
															name="order" property="customerName" /></TD>
														<TD class="v-midalign"><a
															id="<%=versionrow%>" class="orderClaVer">V<bean:write
															name="order" property="versionNo" /> <logic:equal
															name="order" property="versionIndicator" value="Y">*</logic:equal>
														</a>
														<div id='ClauseDiv<%=versionrow%>' style="display:none">
															<bean:define name="order" property="clauseDesc" id="clauseDesc" /> 
															<logic:equal name="order" property="clauseDelFlag" value="Y">
																<bean:message key="Message.Reserved" />
															</logic:equal>
																																				
																<logic:notEqual name="order" property="clauseDelFlag" value="Y">
															    <!--Changed for CR-74 VV49326 16-06-09-->
															    <!-- CR-128 - Updated for Pdf issue -->
															    
																	<%String strClauseDescNext =  String.valueOf(clauseDesc);
																	if(strClauseDescNext.startsWith("<p>")){%>
																		<%=(String.valueOf(clauseDesc))%>
																	<%}else{ %>	
																		<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>							
																	<%}%>
																<!-- CR-128 - Updated for Pdf issue Ends Here-->
																
															
																<logic:notEmpty name="order" property="tableArrayData1">
																<table class="table table-bordered text-center">
																&nbsp;
																	<logic:iterate id="outterver" name="order" property="tableArrayData1"
																			indexId="counter1">
																			<bean:define id="row1" name="counter1" />
																			<bean:define name="order" property="tableDataColSize" id="tableDataColSize" />
																			<tr style="background-color:black;">
																				<logic:iterate id="tabrow1" name="outterver" length="tableDataColSize">
																					<logic:equal name="row1" value="0">
																						<td valign="top"><b><%=(tabrow1==null)? "&nbsp;":tabrow1%></b>
																						</td>
																					</logic:equal>
																					<logic:notEqual name="row1" value="0">
																						<td valign="top"><%=(tabrow1==null)? "&nbsp;":tabrow1%>
																						</td>
																					</logic:notEqual>
																				</logic:iterate>
																			</tr>
																		</logic:iterate>
																</table>
																</logic:notEmpty>
																</logic:notEqual>
															<!---Added for CR-74 18-06-09 -->
															</div>
														</TD>
													</TR>
														<%
														versionrow++;
														%>
													</logic:iterate>
												</logic:present>
											</logic:notEmpty>
										</logic:present>
									</logic:equal>
								</logic:iterate>
									</TABLE>
									<br>

									</logic:iterate>
									</logic:notEmpty>
									</logic:equal>
									</logic:iterate>
									</logic:iterate>
									</logic:present>

									<div class="form-group">
										<div class="col-sm-12 text-center ">
											<button class="btn btn-primary" type='button' name="btnPrint" onclick="Print()">Print</button>
											<button class="btn btn-primary" type='button' name="btnClose" onclick="javascript:window.close();">Cancel</button>
										</div>
									</div>
											

									<html:hidden property="hdnSelSpecType" />
									</html:form>
					</div>
</BODY>
</HTML>
