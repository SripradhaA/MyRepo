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
    <link href="css/select2.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/select2-bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/EMDCustom.css" rel="stylesheet"/>
	<script type="text/javascript" src="js/Others/jquerylatest.js"></script>
	<script type="text/javascript" src="js/Others/jquerymigrate.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/Others/select2.js"></script>
	<SCRIPT type="text/javascript" SRC="js/Emd_Lsdb.js"></SCRIPT>
	<SCRIPT type="text/javascript" SRC="js/error.js"></SCRIPT>
	<SCRIPT type="text/javascript" SRC="js/error_messages.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/1058RequestChanges.js"></SCRIPT>
	<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
</HEAD>

<BODY>
<div class="container" styleClass="form-horizontal" >
<html:form action="/ChangeRequest1058Action.do">
	<html:hidden property="orderKey" />
	<html:hidden property="mdlSeqNo" />
	<html:hidden property="orderNo" />

	<h4 class="text-center">Select Clause</h4>
	
	<div class="spacer20"></div>
	<!-- Validation Message Display Part Starts Here -->
	<%--Table Updated for CR-121 for server side error message tooltip --%>
	<div class="errorlayerhide" id="errorlayer">
	</div>
	<!-- Validation Message Display Part Ends Here --> <!-- Logic Tag Check For Display The Success Message After Add Starts Here -->

	<logic:present name="ChangeRequest1058Form" property="messageID" scope="request">
	<%--Added for CR-121 for server side error message tooltip --%>	
		<bean:define id="messageID" name="ChangeRequest1058Form" property="messageID"/>
	  		<input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
      		<input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
	</logic:present> <!-- Logic Tag Check For Display The Success Message After Add Functionality Ends Here -->
	<!-- Logic Tag Check For Display The Error Message After Add Functionality Starts Here -->
	
	<logic:present name="ChangeRequest1058Form" property="errorMessage" scope="request">
		<script>
               hideErrors();
               addMessage('<bean:write name="ChangeRequest1058Form" property="errorMessage"/>');
               showScrollErrors();
           </script>
	</logic:present> <!-- Logic Tag Check For Display The Error Message After Add Functionality Ends Here -->
	
	<div class="form-group">
		<label class="col-sm-2 col-sm-offset-4 control-label">Order Number</label>
		 <div class="col-sm-3">
			<p class="form-control-static"><strong><bean:write name="ChangeRequest1058Form" property="orderNo" /></strong></p>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 col-sm-offset-4 control-label">Section</label>
		<div class="col-sm-3 ">
			<p class="form-control-static"><strong><bean:write name="ChangeRequest1058Form" property="hdnSectionName" /></strong></p>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 col-sm-offset-4 control-label">Subsection</label>
		<div class="col-sm-3">
			<p class="form-control-static"><strong><bean:write	name="ChangeRequest1058Form" property="hdnSubSectionName" /></strong></p>
		</div>
	</div>
	
	<div class="spacer20"></div>
	
	<logic:present name="ChangeRequest1058Form" property="clauseGroup">
		
		<TABLE class="table table-bordered table-hover">
			<thead>
				<TR>
					<TH>Select</TH>
					<TH>Clause No</TH>
					<TH>Clause Description</TH>
					<TH>Component</TH>
				</TR>
			</thead>
			<tbody>
				<logic:iterate id="clause" name="ChangeRequest1058Form"
					property="clauseGroup" type="com.EMD.LSDB.vo.common.ClauseVO" scope="request">
					<tr>
						<TD class="text-center v-midalign">
						<!--Changed for CR-74 VV49326 16-06-09-->
					        <logic:equal name="clause" property="clauseDelFlag" value="Y">
								<input type="hidden" name="reservedFlag" value="Y"/>
								<input type="radio" name="clauseSeqNo" value="<%=String.valueOf(clause.getClauseSeqNo())%>" class="radcheck"/>
							</logic:equal>
							<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
					     		<input type="hidden" name="reservedFlag" value="N"/>
								<!--Changed for CR-74 VV49326 16-06-09-->
								<input type="radio" name="clauseSeqNo" value="<%=String.valueOf(clause.getClauseSeqNo())%>" class="radcheck"/>
							</logic:notEqual>	
					    </TD>
						<TD CLASS="v-midalign text-center">
							<bean:write name="clause" property="clauseNum" /> 
							<html:hidden property="hdnClauseNum" value='<%=String.valueOf(clause.getClauseNum())%>' />
						</TD>
							
						<logic:notEmpty name="clause" property="clauseDesc">
							<TD>
								<DIV align="left" style="solid;height:60px;overflow:auto;">
									<!--Changed for CR-74 VV49326 16-06-09-->
									<logic:equal name="clause" property="clauseDelFlag" value="Y">									
										<textarea style="display:none;" name="clauseDescription"/></textarea>
										<bean:message key="Message.Reserved" />									
									</logic:equal>
																											
									<logic:notEqual name="clause" property="clauseDelFlag" value="Y">						           
									<!--Changed for CR-74 VV49326 16-06-09-->									
										<bean:define name="clause" property="clauseDesc" id="clauseDesc" /> 
										<textarea style="display:none;" name="clauseDescription"/><%=clauseDesc%></textarea>
									<!-- CR-128 - Updated for Pdf issue -->
										<%String strClauseDesc =  String.valueOf(clauseDesc);
										if(strClauseDesc.startsWith("<p>")){%>
											<%=(String.valueOf(clauseDesc))%>
										<%}else{ %>	
											<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>							
										<%}%>
									<!-- CR-128 - Updated for Pdf issue Ends Here-->
								<table class="table table-bordered">
									<logic:notEmpty name="clause" property="tableArrayData1">
										<logic:iterate id="outter" name="clause"
											property="tableArrayData1" indexId="counter">
											<bean:define id="row" name="counter" />
											<tr class="text-center">
												<logic:iterate id="tabrow" name="outter">
		
													<logic:equal name="row" value="0">
														<td valign="top" width="5%" class="borderbottomleft"><strong><font
															color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></font></strong>
														</td>
													</logic:equal>
		
													<logic:notEqual name="row" value="0">
														<td valign="top" width="5%" class="borderbottomleft"><%=(tabrow==null)? "&nbsp;":tabrow%>
														</td>
													</logic:notEqual>
		
		
												</logic:iterate>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
								<!--Changed for CR-74 VV49326 09-06-09-->								
								</logic:notEqual>
								</DIV>
							</TD>
						</logic:notEmpty>
						<logic:empty name="clause" property="clauseDesc">
							<td VALIGN="TOP">&nbsp;</td>
							<input type="hidden" name="clauseDescription" value=""/>
						</logic:empty>
							
							<!--Changed for CR-74 17-06-09 for Component- Starts Here-->
				       <TD CLASS="text-center v-midalign">   				
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
												<strong><bean:write name="compdesc" property="componentName" /></strong>
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
			
			<div class="spacer20"></div>
			
			<div class="form-group">
				<div class="col-sm-12 text-center">
					<button class="btn btn-primary" type="button" name="Apply" onclick="javascript:submitDependantParentClause()" >OK</button>
					<button class="btn btn-primary" type="button" name="Cancel" id="btnCancel" onclick="javascript:fnCloseWindow()">Cancel</button>
				</div>
			</div>
	</html:form>
</div>
</BODY>
</HTML>
