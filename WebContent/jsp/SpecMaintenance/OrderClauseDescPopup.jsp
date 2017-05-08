<!DOCTYPE HTML>
<HTML>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="com.EMD.LSDB.vo.common.ClauseVO"%>

<!-- Added for CR_121 Ends-->
	<link href="css/bootstrap.css" TYPE="text/css" rel="stylesheet"/>
    <link href="css/select2.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/select2-bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/EMDCustom.css" rel="stylesheet"/>
	<link href="css/dataTables.bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<link rel="shortcut icon" href="images/favicon.ico" />
	<script type="text/javascript" src="js/Others/jquerylatest.js"></script>
	<script type="text/javascript" src="js/Others/jquerymigrate.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/Others/select2.js"></script>
	<script type="text/javascript" src="js/Others/datatables.min.js"></script>
	<SCRIPT language="JavaScript" SRC="js/Emd_Lsdb.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/error.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/error_messages.js"></SCRIPT>
	<SCRIPT language="JavaScript" SRC="js/Appendix.js"></SCRIPT>

	<script>
	    $(document).ready(function() { 
	    	$("#sltSection").select2({theme:'bootstrap'});
	    	$("#sltSubSection").select2({theme:'bootstrap'});
 		 });
	</script>
<HEAD>
<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
</HEAD>
<BODY>
<html:form action="/showAppendix.do" styleClass="form-horizontal">
<div class="container-fluid">
	<html:hidden property="modelSeqNo" />
	
		<h4 class="text-center"><strong>Select Clause</strong></h4>

		<!-- <TABLE>
			
			
			<TR>
				<TD><!-- Validation Message Display Part Starts Here -->
				
				<div class="row">
					<div class="errorlayerhide" id="errorlayer"></div>
				</div>
				
				<div class="row">
					<div class="spacer10"></div>
				</div>
				<!-- Validation Message Display Part Ends Here --> <!-- Logic Tag Check For Display The Success Message After Add Start Here -->
				<logic:present name="AppendixForm" property="messageID"
					scope="request">
					<bean:define id="messageID" name="AppendixForm" property="messageID"/>
		            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
		            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
				</logic:present> <!-- Logic Tag Check For Display The Success Message After Add Functionality Ends Here -->
				<!-- Logic Tag Check For Display The Error Message After Add Functionality Starts Here -->
				<logic:present name="AppendixForm" property="errorMessage"
					scope="request">
					<script>
		              hideErrors();
		              addMessage('<bean:write name="AppendixForm" property="errorMessage"/>');
		              showScrollErrors();
		             </script>
				</logic:present> <!-- Logic Tag Check For Display The Error Message After Add Functionality Ends Here -->
<!-- 
				<br>
			<TR>
		</Table>-->
				
			<div class="form-group">
				<label class="col-sm-5 control-label">Order Number</label>
				<p class="col-sm-4 form-control-static"><bean:write name="AppendixForm" property="orderNo" scope="request" /></p>
			</div>				
				
			<div class="form-group required-field">
				<label class="col-sm-5 control-label">Section</label>
				<div class="col-sm-4">
					<html:select name="AppendixForm" styleId="sltSection" styleClass="form-control"
						property="sectionSeqNo" onchange="javascript:fnLoadSubSection()">
						<option selected value="-1">---Select---</option>
						<logic:present name="AppendixForm" property="sectionList">
							<html:optionsCollection name="AppendixForm"
								property="sectionList" value="sectionSeqNo" label="sectionDisplay" />
						</logic:present>
					</html:select>
				</div>
			</div>
				
			<div class="form-group required-field">
				<label class="col-sm-5 control-label">SubSection</label>
				<div class="col-sm-4">
					<html:select name="AppendixForm" styleId="sltSubSection" styleClass="form-control"
						property="subSecSeqNo" onchange="javascript:fnLoadClauses()">
						<option selected value="-1">---Select---</option>
						<logic:present name="AppendixForm" property="subSectionList">
							<html:optionsCollection name="AppendixForm"
								property="subSectionList" value="subSecSeqNo" label="subSecDisplay" />
						</logic:present>
					</html:select>
				</div>
			</div>
						
			<div class="row">
				<div class="spacer10"></div>
			</div>			

			<logic:present name="AppendixForm" property="clauseDetail">
				<TABLE class="table table-bordered">
					<thead>
						<TR>
							<TH CLASS='text-center' width="5%">Select</TH>
							<TH CLASS='text-center' width="10%">Clause No</TH>
							<TH CLASS='text-center' width="40%">Clause Description</TH>
							<TH CLASS='text-center' width="20%" nowrap>Engineering Data</TH>
							<TH CLASS='text-center' width="20%">Component</TH>
						</TR>
					</thead>
					<tbody> 
						<logic:iterate id="clause" name="AppendixForm"
							property="clauseDetail" type="com.EMD.LSDB.vo.common.ClauseVO"
							scope="request" indexId="clausecount">
							<tr>
								<TD class="text-center v-midalign">
									<input type="radio" name="clauseSeqNo" value='<%=String.valueOf(clause.getClauseSeqNo())%>' /> 
									<html:hidden name="clause" property="versionNo" /></TD>
								<TD class="text-center v-midalign">
									<bean:write name="clause" property="clauseNum" /> 
									<html:hidden property="hdnClauseNum" value='<%=String.valueOf(clause.getClauseNum())%>' />
								</TD>
								<logic:notEmpty name="clause" property="clauseDesc">
									<TD><span style="width:435px">
									<DIV id="clauseID<%=clausecount%>">
										<!--Changed for CR-74 VV49326 15-06-09-->
						                        <logic:equal name="clause" property="clauseDelFlag" value="Y">
												<bean:message key="Message.Reserved" />
												<input type="hidden" name="reservedFlag" value="Y"/>
												</logic:equal>
																																	
												<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
												<input type="hidden" name="reservedFlag" value="N"/>
											    <!--Changed for CR-74 16-06-09-->
										<bean:define name="clause" property="clauseDesc" id="clauseDesc" />
										<!-- CR-128 - Updated for Pdf issue -->
									<%String strClauseDesc =  String.valueOf(clauseDesc);
									if(strClauseDesc.startsWith("<p>")){%>
										<%=(String.valueOf(clauseDesc))%>
									<%}else{ %>	
										<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>
									<%}%>
								<!-- CR-128 - Updated for Pdf issue Ends Here-->
										<logic:notEmpty name="clause" property="tableArrayData1">
											<table class="table table-bordered text-center">
											<logic:iterate id="outter" name="clause" property="tableArrayData1"
																						indexId="counter">
														<bean:define id="row" name="counter" />
														<!-- Added  For CR_93 -->
														<bean:define name="clause" property="tableDataColSize" id="tableDataColSize" />
														<tr>
															<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
																<logic:equal name="row" value="0">
																	<td><b><font
																		color="navy"><%=(tabrow == null) ? "&nbsp;" : tabrow%></b></font>
																	</td>
																</logic:equal>
																<logic:notEqual name="row" value="0">
																	<td><%=(tabrow == null) ? "&nbsp;" : tabrow%>
																	</td>
																</logic:notEqual>
															</logic:iterate>
														</tr>
											</logic:iterate>
											</table>
										</logic:notEmpty>
									
									<!--Changed for CR-74 VV49326 16-06-09-->
							       </logic:notEqual>
									
									</DIV>
									<html:hidden name="clause" property="clauseDesc" /> </span>
								</TD>
								</logic:notEmpty>
								
								<!--Changed for CR-74 16-06-09 for Engg Data- Starts Here-->
	                                <logic:equal name="clause" property="clauseDelFlag" value="Y">
									<TD CLASS=borderbottomleft><BR>
									</TD>										
									</logic:equal>
						
												
									<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
									    
									<!--Changed for CR-74 Eng data Ends -->
									<TD class="text-center">
									<DIV style="solid;height:80px;overflow:auto;">
									<!-- <logic:notEmpty
									name="clause" property="refEDLNO">
									<logic:iterate id="refedl" name="clause" property="refEDLNO">
					                 ref: EDL&nbsp;<bean:write name="refedl" />
									<br>
									</logic:iterate>
									</logic:notEmpty>  -->
									<logic:notEmpty name="clause" property="edlNO">
									<logic:iterate id="edl" name="clause" property="edlNO">
					                 EDL&nbsp;<bean:write name="edl" />
									<br>
									</logic:iterate>
								    </logic:notEmpty> 
								    <!-- CR 87 -->
								    <logic:notEmpty name="clause" property="refEDLNO">
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
								</TD>
	                            
								</logic:notEqual>
	                            <!--Changed for CR-74 16-06-09 for Engg Data- Ends Here--> 
	                           
	                            <!--Changed for CR-74 17-06-09 for Component- Starts Here-->
						       <TD class="text-center">   				
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
								        	<!--Changed for CR-74 17-06-09-->
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
								<!--Changed for CR-74 17-06-09-->
									&nbsp;
								</logic:empty>
								</TD>
								<!--Changed for CR-74 17-06-09-->
							</tr>
						</logic:iterate>
					</tbody>
				</TABLE>
			</logic:present>	
				
			<div class="form-group">
				<div class="col-sm-12 text-center">
					<button class="btn btn-primary " type='button' ONCLICK="javascript:SelectClauseDesc()">OK</button>
					<button class="btn btn-primary " type='button' ONCLICK="Cancelpopup()">Cancel</button>
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
	<html:hidden name="AppendixForm" property="hdnOrderKey" />
	<html:hidden name="AppendixForm" property="orderNo" />
	<html:hidden property="clauseDes" />
	

</div>
</html:form>
</BODY>
</HTML>
