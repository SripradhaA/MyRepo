<!DOCTYPE HTML>
<HTML>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

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
	<SCRIPT language="JavaScript" SRC="js/AddClauseOrder.js"></SCRIPT>
	<script>
        $(document).ready(function() { 
        	$("#sectionSeqNo").select2({theme:'bootstrap'});
        	$("#subSectionSeqNo").select2({theme:'bootstrap'});
        })
    </script>
<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
</HEAD>

<BODY>
<div class="container" width="80%" >
<html:form styleClass="form-horizontal" action="/orderClausePartOfPopUpAction.do">

	<html:hidden property="orderKey" />
	<html:hidden property="modelSeqNo" />
	<html:hidden property="textBoxIndex" />
	<html:hidden property="orderNo" />
	
	<h4 class="text-center">Select Clause</h4>
	
	<!-- Validation Message Display Part Starts Here -->
	<div class="errorlayerhide" id="errorlayer"></div>
	<!-- Validation Message Display Part Ends Here --> <!-- Logic Tag Check For Display The Success Message After Add Starts Here -->

	<logic:present name="OrderClausePartOfPopUpForm" property="messageID"
		scope="request">
		<bean:define id="messageID" name="OrderClausePartOfPopUpForm" property="messageID"/>
           <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
           <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
	</logic:present> <!-- Logic Tag Check For Display The Success Message After Add Functionality Ends Here -->
	<!-- Logic Tag Check For Display The Error Message After Add Functionality Starts Here -->
	<logic:present name="OrderClausePartOfPopUpForm"
		property="errorMessage" scope="request">
		<script>
            hideErrors();
            addMessage('<bean:write name="OrderClausePartOfPopUpForm" property="errorMessage"/>');
            showScrollErrors();
        </script>
	</logic:present> <!-- Logic Tag Check For Display The Error Message After Add Functionality Ends Here -->
	
	<div class="row">
		<div class="spacer20"></div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-offset-3 col-sm-2 control-label">Order Number</label>
		<div class="col-sm-4">
			<p class="form-control-static"><bean:write	name="OrderClausePartOfPopUpForm" property="orderNo" /></p>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-offset-3 col-sm-2 control-label">Section</label>
		<div class="col-sm-4">
			<html:select name="OrderClausePartOfPopUpForm" property="sectionSeqNo" 
				styleId="sectionSeqNo" styleClass="form-control" onchange="javascript:fnLoadSubSection()">
				<logic:equal property="sectionSeqNo" name="OrderClausePartOfPopUpForm" value="">
					<html:option value="-1">---Select---</html:option>
				</logic:equal>
					<html:option value="-1">---Select---</html:option>
						<logic:present name="OrderClausePartOfPopUpForm" property="sectionList">
							<html:optionsCollection name="OrderClausePartOfPopUpForm"
								property="sectionList" label="sectionDisplay"
								value="sectionSeqNo" />
						</logic:present>
			</html:select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-offset-3 col-sm-2 control-label">SubSection</label>
		<div class="col-sm-4">
			<html:select name="OrderClausePartOfPopUpForm" property="subSectionSeqNo" 
				styleId="subSectionSeqNo" styleClass="form-control" onchange="javascript:fnLoadClause()">
				<logic:equal property="subSectionSeqNo" name="OrderClausePartOfPopUpForm" value="">
					<html:option value="-1">---Select---</html:option>
				</logic:equal>
					<html:option value="-1">---Select---</html:option>
						<logic:present name="OrderClausePartOfPopUpForm" property="subSectionList">
							<html:optionsCollection name="OrderClausePartOfPopUpForm"
								property="subSectionList" label="subSecDisplay"
								value="subSecSeqNo" />
						</logic:present>
			</html:select>
		</div>
	</div>
	
	<div class="spacer10"></div>
	
	<logic:present name="OrderClausePartOfPopUpForm" property="clauseGroup">
		<TABLE class="table table-bordered">
			<thead>
				<TR>
					<TH CLASS='text-center'>Select</TH>
					<TH CLASS='text-center'>Clause No</TH>
					<TH CLASS='text-center'>Clause Description</TH>
					<TH CLASS='text-center' WIDTH="20%">Component</TH>
				</TR>
			</thead>
			
			<tbody>
				<logic:iterate id="clause" name="OrderClausePartOfPopUpForm"
					property="clauseGroup" type="com.EMD.LSDB.vo.common.ClauseVO"
					scope="request">
					<tr>
						<TD class="text-center v-midalign">
						<!--Changed for CR-74 VV49326 16-06-09-->
						    <logic:equal name="clause" property="clauseDelFlag" value="Y">
								<input type="hidden" name="reservedFlag" value="Y"/>
								<html:radio property="clauseSeqNo" value='<%=String.valueOf(clause.getClauseSeqNo())%>' />
							</logic:equal>
							<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
					     		<input type="hidden" name="reservedFlag" value="N"/>
						<!--Changed for CR-74 VV49326 16-06-09-->
									<html:radio property="clauseSeqNo" value='<%=String.valueOf(clause.getClauseSeqNo())%>' />
							</logic:notEqual>	
					    </TD>
	
						<TD class="text-center v-midalign">
							<bean:write name="clause" property="clauseNum" /> 
							<html:hidden property="hdnClauseNum" value='<%=String.valueOf(clause.getClauseNum())%>' />
							<%-- Added for CR117 Fixes--%>
							<html:hidden property="versionNo" value='<%=String.valueOf(clause.getVersionNo())%>' />
							<%-- Added for CR117 Fixes Ends--%>
						</TD>
						
						<logic:notEmpty name="clause" property="clauseDesc">
							<TD valign="TOP">
								<DIV align="left" STYLE="float:left;height:60;width:435;overflow: auto ; ">
									<!--Changed for CR-74 VV49326 16-06-09-->
									<logic:equal name="clause" property="clauseDelFlag" value="Y">									
										<bean:message key="Message.Reserved" />									
									</logic:equal>
									<logic:notEqual name="clause" property="clauseDelFlag" value="Y">						           
										<!--Changed for CR-74 VV49326 16-06-09-->
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
										<logic:iterate id="outter" name="clause"
											property="tableArrayData1" indexId="counter">
											<bean:define id="row" name="counter" />
											<tr>
												<logic:iterate id="tabrow" name="outter">
		
													<logic:equal name="row" value="0">
														<td valign="top" width="5%" class="borderbottomleft"><b><font
															color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></font></b>
														</td>
													</logic:equal>
		
													<logic:notEqual name="row" value="0">
														<td valign="top" width="5%" class="borderbottomleft"><%=(tabrow==null)? "&nbsp;":tabrow%>
														</td>
													</logic:notEqual>
		
		
												</logic:iterate>
											</tr>
										</logic:iterate>
									</table>
									</logic:notEmpty>
							<!--Changed for CR-74 VV49326 09-06-09-->								
							</logic:notEqual>
							</DIV>
							</TD>
						</logic:notEmpty>
						<logic:empty name="clause" property="clauseDesc">
							<td class="borderbottomrightlight" VALIGN="TOP"></td>
						</logic:empty>
						
						<!--Changed for CR-74 17-06-09 for Component- Starts Here-->
				       <TD  class="borderbottomleft text-center" valign="TOP">   				
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
			
			
			<div class="row">
				<div class="spacer10"></div>
			</div>
	
			<div class="form-group">
				<div class="col-sm-12 text-center">
					<button class="btn btn-primary" type='button' name="Apply" ONCLICK="javascript:submitDependantClause()">OK</button>
					<button class="btn btn-primary" type='button' name="Cancel" ONCLICK="javascript:fnCloseWindow()">Close</button>
				</div>
			</div>
			
			
			<div class="row">
				<div class="spacer50"></div>
			</div>


			</html:form>
</div>
</BODY>
</HTML>
