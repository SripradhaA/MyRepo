<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<SCRIPT LANGUAGE="JavaScript" SRC="js/OrderSectionView.js"></SCRIPT>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>

<body>
<div class="container" width="80%">
	<h4 class="text-center"><bean:message key="Application.Screen.ClausesWithIndicators" /></h4>
		
<html:form action="OrderSection.do" >
	<div class="errorlayerhide" id="errorlayer">
	</div>
	<!-- No records found message starts---->
	<logic:empty name="OrderSectionForm" property="secDetail" >
	<script> 
              fnNoRecords();
        	</script>

     </logic:empty>
     <!-- No records found message ends here---->
	<logic:iterate id="secDetail" name="OrderSectionForm"
		property="secDetail" type="com.EMD.LSDB.vo.common.SectionVO" 
			scope="request">	
			<logic:notEmpty name="secDetail" property="sectionCode">	
			
			<div class="row">
				<div class="col-sm-12 highlightDark text-center">
					<strong>
						<bean:write name="secDetail" property="sectionCode" />.
						<bean:write name="secDetail" property="sectionName" />
					</strong>
				</div>
			</div>
			
			<logic:iterate id="subSecDetail" name="secDetail" property="subSec"
					type="com.EMD.LSDB.vo.common.SubSectionVO">
				<div class="row">
					<div class="col-sm-12 bg-info text-center">
						<strong>
							<bean:write name="subSecDetail" property="subSecCode" />&nbsp;
							<bean:write name="subSecDetail"	property="subSecName" />
						</strong>
					</div>
				</div>
				
			<div class="row">
				<div class="spacer10"></div>
			</div>
			
			<TABLE class="table table-bordered">
				<thead>	
					<TR>
						<TH CLASS='text-center v-midalign-th' WIDTH="5%">Indicator Type</TH>
						<TH CLASS='text-center v-midalign-th' WIDTH="10%">Clause No</TH>
						<TH CLASS='text-center v-midalign-th' WIDTH="55%">Clause Description</TH>
						<TH CLASS='text-center v-midalign-th' WIDTH="10%">Engineering Data</TH>
						<TH CLASS='text-center v-midalign-th' WIDTH="10%">Component</TH>
						<TH CLASS='text-center v-midalign-th' WIDTH="10%">ComponentGroup</TH>
					</TR>
				</thead>
				<tbody>
					 <logic:iterate id="clause" name="subSecDetail" 
							property="clauseGroup" type="com.EMD.LSDB.vo.common.ClauseVO">
		 			
						<TR>
							<TD CLASS="v-midalign text-center" WIDTH="5%">
								<logic:equal name="clause" property="indicatorFlag" value="C">
								<span class="label label-danger"><small>COPY</small></span>
								</logic:equal>
								<logic:equal name="clause" property="indicatorFlag" value="D">
								<span class="label label-danger"><small>DELETE</small></span>
								</logic:equal>
								<logic:equal name="clause" property="indicatorFlag" value="E">
								<span class="label label-danger"><small>EDL CHANGE</small></span>
								</logic:equal>
								<logic:equal name="clause" property="indicatorFlag" value="M">
								<span class="label label-danger"><small>MODEL</small></span>
								</logic:equal>
								<logic:equal name="clause" property="indicatorFlag" value="N">
								<img src="images\new1.gif" border="0"	valign="bottom" />
								</logic:equal>
							</TD>
	
							<logic:notEmpty name="clause" property="clauseNum">
								<TD CLASS="v-midalign text-center" WIDTH="10%">
									<bean:write name="clause" property="clauseNum" />
								</TD>
							</logic:notEmpty>
							<logic:empty name="clause" property="clauseNum">
									<TD CLASS=borderbottomleft WIDTH="10%">&nbsp;</TD>
							</logic:empty>
	
							<logic:notEmpty name="clause" property="clauseDesc">
								<TD WIDTH="55%">
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
											<table class="table table-bordered text-center">
												<logic:notEmpty name="clause" property="tableArrayData1">
												 &nbsp;
												 <logic:iterate id="outter" name="clause" property="tableArrayData1"
															indexId="counter">
														<bean:define id="row" name="counter" />
			
														<bean:define name="clause" property="tableDataColSize" id="tableDataColSize" />
														<tr>
															<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
					
																<logic:equal name="row" value="0">
																	<td valign="top" width="5%" class="borderbottomleft1"><b><font
																		color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></b></font>
																	</td>
																</logic:equal>
					
																<logic:notEqual name="row" value="0">
																	<td valign="top" width="5%" class="borderbottomleft1"><%=(tabrow==null)? "&nbsp;":tabrow%>
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
								<TD CLASS=borderbottomleft  VALIGN="TOP" WIDTH="55%">
									&nbsp;
								</TD>
							</logic:empty>
							<TD WIDTH="10%">
								<span style="width:100px">
									 <DIV align="center" STYLE="float:left;height:60;width:100;overflow: auto ; ">
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
										<logic:notEmpty name="clause" property="partOF">
											<logic:iterate id="partof" name="clause" 
												property="partOF">
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
											<br>
										</logic:notEmpty>&nbsp;
									</DIV>
								</span>
							</TD>
							
							<logic:notEmpty name="clause" property="componentList">
								<TD WIDTH="10%">
									<logic:iterate id="compList" name="clause" property="componentList"
										type="com.EMD.LSDB.vo.common.ComponentVO">
										<bean:write name="compList" property="componentName" />
									</logic:iterate>
								</TD>
							</logic:notEmpty>
							<logic:empty name="clause" property="componentList">
								<TD CLASS=borderbottomrightlight  WIDTH="10%">&nbsp;
								</TD>
							</logic:empty>
							<logic:notEmpty name="clause" property="componentList">
								<TD WIDTH="10%">
									<logic:iterate id="compList" name="clause" property="componentList"
										type="com.EMD.LSDB.vo.common.ComponentVO">
										<bean:write name="compList" property="componentGroupName" />
									</logic:iterate>
								</TD>
							</logic:notEmpty>
							<logic:empty name="clause" property="componentList">
								<TD CLASS=borderbottomrightlight  WIDTH="10%">&nbsp;
								</TD>
							</logic:empty>
						</TR>							
					</logic:iterate> 
				</tbody>
			</TABLE>
		</logic:iterate>
		<BR />		
	</logic:notEmpty>
	</logic:iterate>		
		
	<input type="hidden" name="hdnClauseSeqNo" />
	<html:hidden name="OrderSectionForm" property="orderKey" />
	<html:hidden name="OrderSectionForm" property="orderSecSeqNo" />			
	<div class="form-group">
		<logic:notEqual name="OrderSectionForm" property="redirectGenInfoPageFlag" value="Y" >       
			<div class="col-sm-12 text-center">
				<button class="btn btn-primary" type='button' ONCLICK="javascript:fnModifySpec()">Return to Modify Spec</button>
			</div>
		</logic:notEqual>
		<logic:equal name="OrderSectionForm" property="redirectGenInfoPageFlag" value="Y" >
			<div class="col-sm-12 text-center">
				<button class="btn btn-primary" type='button' ONCLICK="javascript:fnReturnGenInfo()">Return to General Info Maintenance</button>
			</div>
		</logic:equal>
	</div>
	
	<div class="row">
				<div class="spacer50"></div>
			</div>
			
</html:form>
</div>
</body>