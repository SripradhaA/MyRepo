<!DOCTYPE HTML>
<HTML>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<style>
	.emdToolTip{ 
		background: #F6CECE; color: black; box-shadow: 0 0 7px black; border: 1px solid red;
	}
</style>
<link href="css/bootstrap.css" TYPE="text/css" rel="stylesheet"/>
<link href="css/EMDCustom.css" rel="stylesheet"/>
<link rel='stylesheet'  type='text/css' href='css/confirm.css' />
<link rel="shortcut icon" href="images/favicon.ico" />
<script type="text/javascript" src="js/Others/jquerylatest.js"></script>
<script type="text/javascript" src="js/Others/jquerymigrate.js"></script>
<script type="text/javascript" src="js/Others/bootstrap.min.js"></script>
<SCRIPT language="JavaScript" SRC="js/Emd_Lsdb.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/error.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/error_messages.js"></SCRIPT>
<SCRIPT language="JavaScript" SRC="js/Appendix.js"></SCRIPT>
<HEAD>
<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
</HEAD>
<BODY>
<html:form action="/showAppendix.do" >
	
	<div class="container-fluid" >
		<div class="row">
			<div class="spacer30"></div>
		</div>
		<!-- Application PageName Display starts here-->
		<h4 class="text-center"><strong><bean:write name="AppendixForm" property="subSectionName" /></strong></h4>
		<div class="row">
			<div class="spacer10"></div>
		</div>
	<html:hidden property="clauseSeqNo" />
	<html:hidden property="versionNo" />
	<logic:present name="AppendixForm" property="clauseDetail">
		<TABLE class="table table-bordered">
			<thead>
				<TR>
					<!-- <TD CLASS='table_heading' >Select</TD> -->
					<TH CLASS='text-center'>Clause No</TH>
					<TH CLASS='text-center'>Clause Description</TH>
					<TH CLASS='text-center' nowrap>Engineering Data</TH>
					<TH CLASS='text-center' WIDTH="10%">Component</TH>
				</TR>
			</thead>
			<tbody>
				<logic:iterate id="clause" name="AppendixForm"
					property="clauseDetail" type="com.EMD.LSDB.vo.common.ClauseVO"
					scope="request" indexId="clausecount">
					<bean:define id="clauseseqno"
						value='<%=String.valueOf(clause.getClauseSeqNo())%>' />
					<logic:equal name="AppendixForm" property="clauseSeqNo"
						value='<%=clauseseqno%>'>
	
						<tr class="info">
	
							<TD>
								<bean:write name="clause" property="clauseNum" /> 
								<html:hidden property="hdnClauseNum" value='<%=String.valueOf(clause.getClauseNum())%>' />
							</TD>
							<logic:notEmpty name="clause" property="clauseDesc">
							<TD>
								
								<DIV id="clauseID<%=clausecount%>" STYLE="float:left;height:60;width:435;overflow: auto ; ">
								<!--Added for CR-74 17-06-09 Starts Here-->
									<logic:equal name="clause" property="clauseDelFlag" value="Y">
								    		<bean:message key="Message.Reserved" />
									</logic:equal>
									<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
									<!--Changed for CR-74 VV49326 17-06-09-->
										<bean:define name="clause" property="clauseDesc" id="clauseDesc" /> <%=clauseDesc%>

										<table width="100%" BORDER="1" BORDERCOLOR="">
											<logic:notEmpty name="clause" property="tableArrayData1">
												<logic:iterate id="outter" name="clause" property="tableArrayData1"	indexId="counter">
													<bean:define id="row" name="counter" />
													<!-- Added  For CR_93 -->
													<bean:define name="clause" property="tableDataColSize" id="tableDataColSize" />
													<tr>
														<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
			
															<logic:equal name="row" value="0">
																<td>
																<b>
																	<font color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></font>
																</b>
																</td>
															</logic:equal>
			
															<logic:notEqual name="row" value="0">
																<td><%=(tabrow==null)? "&nbsp;":tabrow%>
																</td>
															</logic:notEqual>
														</logic:iterate>
													</tr>
												</logic:iterate>
											</logic:notEmpty>
										</table>
									</logic:notEqual>
							<!--Added for CR-74 17-06-09-->
								</DIV>
								<html:hidden name="clause" property="clauseDesc" /> </span></TD>
							</logic:notEmpty>
							
	                         <!--Changed for CR-74 17-06-09 for Engg Data- Starts Here-->
	                         <logic:equal name="clause" property="clauseDelFlag" value="Y">
																			
								<TD CLASS=borderbottomleft><BR>
								</TD>
										
							</logic:equal>
																											
							<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
							<!-- Eng data starts -->
							<TD ><span
								style="width:130px">
							<DIV STYLE="float:left;height:60;width:130;overflow: auto ; ">
							<!-- 
							<logic:notEmpty	name="clause" property="refEDLNO">
								<logic:iterate id="refedl" name="clause" property="refEDLNO">
	                                    ref: EDL&nbsp;<bean:write
										name="refedl" />
									<br>
								</logic:iterate>
							</logic:notEmpty>-->
							
							 <logic:notEmpty name="clause" property="edlNO">
								<logic:iterate id="edl" name="clause" property="edlNO">
	                                    EDL&nbsp;<bean:write name="edl" />
									<br>
								</logic:iterate>
							</logic:notEmpty> 
							<!-- CR 87 -->
							<logic:notEmpty	name="clause" property="refEDLNO">
								<logic:iterate id="refedl" name="clause" property="refEDLNO">
	                                    <bean:message key="screen.refEdl.end" />&nbsp;
	                                    <bean:write	name="refedl" />&nbsp;
										<bean:message key="screen.refEdl.end" />
									<br>
								</logic:iterate>
							</logic:notEmpty>
							<logic:notEmpty name="clause" property="partOF">
								<logic:iterate id="partof" name="clause" property="partOF"
									type="com.EMD.LSDB.vo.common.SubSectionVO">
									<bean:message key="viewSpec.PartOf" />&nbsp;<bean:write
										name="partof" property="subSecCode" />&nbsp;
								
								<br>
								</logic:iterate>
							</logic:notEmpty> <logic:notEmpty name="clause"
								property="dwONumber">
								<logic:notEqual name="clause" property="dwONumber" value="0">
	                                    DWO&nbsp;<bean:write name="clause"
										property="dwONumber" />
									<br>
								</logic:notEqual>
							</logic:notEmpty> <logic:notEmpty name="clause"
								property="partNumber">
								<logic:notEqual name="clause" property="partNumber" value="0">
	                                    Part No&nbsp;<bean:write
										name="clause" property="partNumber" />
									<br>
								</logic:notEqual>
							</logic:notEmpty> <!--Price Book Number is added in the Engineering data Section, modified on 30-09-08 by ps5722-->
	
							<logic:notEmpty name="clause" property="priceBookNumber">
								<logic:notEqual name="clause" property="priceBookNumber"
									value="0">
									<bean:message key="screen.priceBookNo" />&nbsp;<bean:write
										name="clause" property="priceBookNumber" />
									<br>
								</logic:notEqual>
							</logic:notEmpty> <logic:notEmpty name="clause"
								property="standardEquipmentDesc">
								<bean:write name="clause" property="standardEquipmentDesc" />
								<br>
							</logic:notEmpty> <logic:notEmpty name="clause"
								property="engDataComment">
								<bean:write name="clause" property="engDataComment" />
								<br>
							</logic:notEmpty></DIV>
							</span></TD>
							</logic:notEqual>
							<!--Changed for CR-74 17-06-09 for Engg Data-->
							
							<!--Changed for CR-74 17-06-09 for Component- Starts Here-->
						       <TD >   				
									<logic:equal name="clause" property="clauseDelFlag" value="Y">
									<logic:equal name="clause" property="deleteFlag" value="D">
									&nbsp;     
									</logic:equal>
									</logic:equal>  
									
									<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
									<logic:notEqual name="clause" property="deleteFlag" value="D">
									&nbsp;     
									</logic:notEqual>
									</logic:notEqual>                                          
	                                            
	                             <!--Added for CR-74 16-06-09 for Component- Ends Here-->
	                            
								<logic:notEmpty name="clause" property="compName">
										<!--<TD CLASS=borderbottomrightlight  VALIGN="TOP">-->
											<!-- This part is for finding size of the components-->
											<logic:notEmpty name="clause" property="compName">
														<bean:size id="compSize" name="clause" property="compName"/>
														<bean:define id="compTotSize"
															value="<%=String.valueOf(compSize.intValue()-1)%>" />
											</logic:notEmpty> 
											<logic:iterate id="compdesc" name="clause"
												property="compName" type="com.EMD.LSDB.vo.common.ComponentVO" indexId="counter">
												<bean:define id="count" name="counter" />
												<logic:notEqual name="counter" value="0"> ;<br>
												</logic:notEqual>
												<logic:equal name="compdesc" property="deletedFlag" value="Y">
												<font color="red"><bean:write name="compdesc" property="componentName" /></font>
												</logic:equal>
												<logic:notEqual name="compdesc" property="deletedFlag" value="Y">
												<!-- Change for LSDB_CR-74 -->
												<logic:equal name="compdesc" property="compColorFlag" value="Y">
												<font color="#3D83C9">
												<b><bean:write name="compdesc" property="componentName" /></b>
												</font>
												</logic:equal>
												<logic:notEqual name="compdesc" property="compColorFlag" value="Y">
												<bean:write name="compdesc" property="componentName" />
												</logic:notEqual>
												<!-- Ends here -->
									            </logic:notEqual>
	                                        </logic:iterate>
									   	
								</logic:notEmpty>
								
								<logic:empty name="clause" property="compName">
									&nbsp;
								</logic:empty>
								</TD>
								<!--Changed for CR-74 17-06-09-->
						</tr>
					</logic:equal>
	
					<logic:notEqual name="AppendixForm" property="clauseSeqNo"
						value='<%=clauseseqno%>'>
	
						<tr>
	
							<TD><bean:write name="clause"
								property="clauseNum" /> <html:hidden property="hdnClauseNum"
								value='<%=String.valueOf(clause.getClauseNum())%>' /></TD>
							<logic:notEmpty name="clause" property="clauseDesc">
								<TD><span style="width:435px">
								<DIV id="clauseID<%=clausecount%>"
									STYLE="float:left;height:60;width:435;overflow: auto ; ">
									<!--Changed for CR-74 VV49326 16-06-09-->
						            <logic:equal name="clause" property="clauseDelFlag" value="Y">
									<bean:message key="Message.Reserved" />
									</logic:equal>
																																		
									<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
									<!--Changed for CR-74 VV49326 16-06-09-->								
									
									<bean:define
									name="clause" property="clauseDesc" id="clauseDesc" /> <%=clauseDesc%>
	
								<table width="100%" BORDER="1" BORDERCOLOR="">
									<logic:notEmpty name="clause" property="tableArrayData1">
						&nbsp;
					 <logic:iterate id="outter" name="clause" property="tableArrayData1"
											indexId="counter">
											<bean:define id="row" name="counter" />
																					<!-- Added  For CR_93 -->
												<bean:define name="clause" property="tableDataColSize" id="tableDataColSize" />
											<tr>
												<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
	
													<logic:equal name="row" value="0">
														<td><b><font
															color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></font></b>
														</td>
													</logic:equal>
	
													<logic:notEqual name="row" value="0">
														<td><%=(tabrow==null)? "&nbsp;":tabrow%>
														</td>
													</logic:notEqual>
	
	
												</logic:iterate>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
								</logic:notEqual>
								<!--Added for CR-74 17-06-09-->
								</DIV>
								<html:hidden name="clause" property="clauseDesc" /> </span></TD>
							</logic:notEmpty>
							<logic:empty name="clause" property="clauseDesc">
								<td>&nbsp;</td>
							</logic:empty>
	
	                        <!--Changed for CR-74 15-06-09 for Engg Data- Starts Here-->
	                       <logic:equal name="clause" property="clauseDelFlag" value="Y">
																				
							<TD CLASS=borderbottomleft><BR>
							</TD>
										
							</logic:equal>
						
																							
							<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
							<!-- Eng data starts -->
							<TD><span
								style="width:130px">
							<DIV STYLE="float:left;height:60;width:130;overflow: auto ; ">
							<!--<logic:notEmpty
								name="clause" property="refEDLNO">
								<logic:iterate id="refedl" name="clause" property="refEDLNO">
	                                    ref: EDL&nbsp;<bean:write
										name="refedl" />
									<br>
								</logic:iterate>
							</logic:notEmpty> -->
							<logic:notEmpty name="clause" property="edlNO">
								<logic:iterate id="edl" name="clause" property="edlNO">
	                                    EDL&nbsp;<bean:write name="edl" />
									<br>
								</logic:iterate>
							</logic:notEmpty> 
							<!-- CR 87 -->
							<logic:notEmpty	name="clause" property="refEDLNO">
								<logic:iterate id="refedl" name="clause" property="refEDLNO">
	                                    <bean:message key="screen.refEdl.start" />&nbsp;
	                                    <bean:write name="refedl" />&nbsp;
	                                    <bean:message key="screen.refEdl.end" />
									<br>
								</logic:iterate>
							</logic:notEmpty> 
							<logic:notEmpty name="clause" property="partOF">
								<logic:iterate id="partof" name="clause" property="partOF"
									type="com.EMD.LSDB.vo.common.SubSectionVO">
									<bean:message key="viewSpec.PartOf" />&nbsp;<bean:write
										name="partof" property="subSecCode" />&nbsp;
								
								<br>
								</logic:iterate>
							</logic:notEmpty> <logic:notEmpty name="clause"
								property="dwONumber">
								<logic:notEqual name="clause" property="dwONumber" value="0">
	                                    DWO&nbsp;<bean:write name="clause"
										property="dwONumber" />
									<br>
								</logic:notEqual>
							</logic:notEmpty> <logic:notEmpty name="clause"
								property="partNumber">
								<logic:notEqual name="clause" property="partNumber" value="0">
	                                    Part No&nbsp;<bean:write
										name="clause" property="partNumber" />
									<br>
								</logic:notEqual>
							</logic:notEmpty> <!--Price Book Number is added in the Engineering data Section, modified on 30-09-08 by ps5722-->
	
							<logic:notEmpty name="clause" property="priceBookNumber">
								<logic:notEqual name="clause" property="priceBookNumber"
									value="0">
									<bean:message key="screen.priceBookNo" />&nbsp;<bean:write
										name="clause" property="priceBookNumber" />
									<br>
								</logic:notEqual>
							</logic:notEmpty> <logic:notEmpty name="clause"
								property="standardEquipmentDesc">
								<bean:write name="clause" property="standardEquipmentDesc" />
								<br>
							</logic:notEmpty> <logic:notEmpty name="clause"
								property="engDataComment">
								<bean:write name="clause" property="engDataComment" />
								<br>
							</logic:notEmpty></DIV>
							</span></TD>
							</logic:notEqual>
							<!--Added for CR-74-->
	                        
	                        <!--Changed for CR-74 17-06-09 for Component- Starts Here-->
						       <TD>   				
									<logic:equal name="clause" property="clauseDelFlag" value="Y">
									<logic:equal name="clause" property="deleteFlag" value="D">
									&nbsp;     
									</logic:equal>
									</logic:equal>  
									
									<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
									<logic:notEqual name="clause" property="deleteFlag" value="D">
									&nbsp;     
									</logic:notEqual>
									</logic:notEqual>                                          
	                                            
	                             <!--Added for CR-74 16-06-09 for Component- Ends Here-->
	                            
								<logic:notEmpty name="clause" property="compName">
										<!--<TD CLASS=borderbottomrightlight  VALIGN="TOP">-->
											<!-- This part is for finding size of the components-->
											<logic:notEmpty name="clause" property="compName">
														<bean:size id="compSize" name="clause" property="compName"/>
														<bean:define id="compTotSize"
															value="<%=String.valueOf(compSize.intValue()-1)%>" />
											</logic:notEmpty> 
											<logic:iterate id="compdesc" name="clause"
												property="compName" type="com.EMD.LSDB.vo.common.ComponentVO" indexId="counter">
												<bean:define id="count" name="counter" />
												<logic:notEqual name="counter" value="0"> ;<br>
												</logic:notEqual>
												<logic:equal name="compdesc" property="deletedFlag" value="Y">
												<font color="red"><bean:write name="compdesc" property="componentName" /></font>
												</logic:equal>
												<logic:notEqual name="compdesc" property="deletedFlag" value="Y">
												<!-- Change for LSDB_CR-74 -->
												<logic:equal name="compdesc" property="compColorFlag" value="Y">
												<font color="#3D83C9">
												<b><bean:write name="compdesc" property="componentName" /></b>
												</font>
												</logic:equal>
												<logic:notEqual name="compdesc" property="compColorFlag" value="Y">
												<bean:write name="compdesc" property="componentName" />
												</logic:notEqual>
												<!-- Ends here -->
									            </logic:notEqual>
	                                        </logic:iterate>
									   	
								</logic:notEmpty>
								
								<logic:empty name="clause" property="compName">
									&nbsp;
								</logic:empty>
								</TD>
								<!--Changed for CR-74 17-06-09-->
						</tr>
					</logic:notEqual>
				</logic:iterate>
			</tbody>
			</TABLE>
		</logic:present>
		
		<div class="form-group">
			<div class="col-sm-12 text-center">
				<button class="btn btn-primary" type='button' id="close" ONCLICK="Cancelpopup()">Close</button>
			</div>
		</div>
		
		<div class="row">
			<div class="spacer100"></div>
		</div>
		
		<nav class="navbar navbar-default navbar-fixed-bottom navbar-small">
			<SCRIPT type="text/javascript">
				var currentTime = new Date();
				var year = currentTime.getFullYear();
			</SCRIPT>
			<h5 class="navbar-footer-text">Copyright &copy; <script type="text/javascript">document.write(year)</script> Electro-Motive Diesel, Inc</h5>
		</nav>
				
	</div>
</html:form>
</BODY>
</HTML>