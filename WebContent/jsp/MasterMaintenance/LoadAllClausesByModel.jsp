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
    <link rel="shortcut icon" href="images/favicon.ico" />
	<link href="css/bootstrap.css" TYPE="text/css" rel="stylesheet"/>
    <link href="css/select2.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/select2-bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/datatables.min.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/EMDCustom.css" rel="stylesheet"/>
	<script type="text/javascript" src="js/Others/jquerylatest.js"></script>
	<script type="text/javascript" src="js/Others/jquerymigrate.js"></script>
    <script type="text/javascript" src="js/Others/ParseDate.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/Others/select2.js"></script>
	<script type="text/javascript" src="js/Others/datatables.min.js"></script>
	<script type="text/javascript" src="js/Others/datatables.plugins.js"></script>
	<SCRIPT type="text/javascript" SRC="js/Emd_Lsdb.js"></SCRIPT>
	<SCRIPT type="text/javascript" SRC="js/error.js"></SCRIPT>
	<SCRIPT type="text/javascript" SRC="js/error_messages.js"></SCRIPT>
	<script type="text/javascript" src="js/Others/jquery.bootbox.min.js"></script>
	<SCRIPT language="JavaScript" SRC="js/AddClause.js"></SCRIPT>
	<script>
	       $(document).ready(function() { 
	       	$("#sltSection").select2({theme:'bootstrap'});
		   	$("#sltSubSection").select2({theme:'bootstrap'});
	 	   })
	</script>
<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
</HEAD>
<BODY>
	<div class="container" width="80%">
		<html:form action="/partOfAction.do">
				<h4 class="text-center">Clause Dependency</h4>
				<div class="spacer10"></div>
				<div class="row">
					<div class="errorlayerhide" id="errorlayer"></div> 
				</div>
				<!-- Logic Tag Check For Display The Success Message After Add Starts Here -->

				<logic:present name="PartOfForm" property="messageID" scope="request">
					 
	                <bean:define id="messageID" name="PartOfForm" property="messageID"/>
	            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
	            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
	            
				</logic:present> <!-- Logic Tag Check For Display The Success Message After Add Functionality Ends Here -->
				<!-- Logic Tag Check For Display The Error Message After Add Functionality Starts Here -->
				<logic:present name="PartOfForm" property="errorMessage"
					scope="request">
					<script>
	                    hideErrors();
	                    addMessage('<bean:write name="PartOfForm" property="errorMessage"/>');
	                    showScrollErrors();
	                </script>
	
				</logic:present> 
				<!-- Logic Tag Check For Display The Error Message After Add Functionality Ends Here -->
				<div class="spacer10"></div>
				
				<div class="form-horizontal">
				 		 <div class="form-group">
							<label class="col-sm-5 control-label">Specification Type</label>
							<div class="col-sm-2 form-control-static">					
									<bean:write name="PartOfForm" property="specType" />
							</div>
						</div>
						<div class="form-group">			
								<label class="col-sm-5 control-label">Model</label>
								<div class="col-sm-2 form-control-static">					
									<bean:write name="PartOfForm" property="modelName" />
								</div>
						</div>
						<div class="form-group required-field">
								<label class="col-sm-5 control-label">Section</label>
								<div class="col-sm-1">
								<html:select styleClass="form-control" styleId="sltSection" name="PartOfForm" property="sectionSeqNo"
									onchange="javascript:fnLoadPartOfSubSection()">
									<html:option value="-1">---Select---</html:option>
									<logic:present name="PartOfForm" property="sectionList">
										<html:optionsCollection name="PartOfForm" property="sectionList"
											label="sectionDisplay" value="sectionSeqNo" />
									</logic:present>
								</html:select>
								</div>
						</div>
						<div class="form-group required-field">
								<label class="col-sm-5 control-label">SubSection</label>
								<div class="col-sm-1">
								<html:select styleClass="form-control" styleId="sltSubSection" name="PartOfForm" property="subSectionSeqNo"
									onchange="javascript:fnLoadClauses()">
									<html:option value="-1">---Select---</html:option>
									<logic:present name="PartOfForm" property="subSectionList">
										<html:optionsCollection name="PartOfForm"
											property="subSectionList" label="subSecDisplay"
											value="subSecSeqNo" />
									</logic:present>
								</html:select>
								</div>
						</div>
				</div>
				<div class="spacer10"></div>
				<logic:present name="PartOfForm" property="clauseList">
					<table class="table table-bordered table-hover">
					    <thead>
					        <tr>
								<TH>Select</TH>
								<TH>Clause No</TH>
								<TH>Clause Description</TH>
								<TH>Component</TH>
							</tr>	
						</thead>
					    <tbody class="text-center">
					    	<logic:iterate id="compParent" name="PartOfForm" property="clauseList" scope="request"
								type="com.EMD.LSDB.vo.common.ClauseVO" indexId="clausecount">
									<TR>
										<TD CLASS="v-midalign">
											<html:radio property="clauseSeqNo" value='<%=String.valueOf(compParent.getClauseSeqNo())%>' />
										</TD>
										<!---
											*	Clause Number Field is added as per the requirement of 			LSDB_CR-48
											*	Added on 30-July-08 
											*	by ps57222
										-->
										<TD CLASS="v-midalign">
											<logic:present name="compParent" property="clauseNum">
												<bean:write name="compParent" property="clauseNum" />
												<html:hidden name="compParent" property="clauseNum" />
											</logic:present>
										</TD>
				
										<TD align="left">
											<div id="clauseID<%=clausecount%>" align="left" STYLE="solid;height:80px;overflow:auto;">
											<bean:define name="compParent" property="clauseDesc" id="clauseDesc" />
											 <!-- Added for incuding replace \n with <br> by VV49326 06/11/08-->
											<!-- CR-128 - Updated for Pdf issue -->
												<%String strClauseDesc =  String.valueOf(clauseDesc);
												if(strClauseDesc.startsWith("<p>")){%>
													<%=(String.valueOf(clauseDesc))%>
												<%}else{ %>	
													<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>							
												<%}%>
											<!-- CR-128 - Updated for Pdf issue Ends Here-->	
											
												<logic:notEmpty name="compParent" property="tableArrayData1">
															<table class="table table-bordered">
					
									 						<logic:iterate id="outter" name="compParent"
														property="tableArrayData1" indexId="counter">
															<!-- Added  For CR_93 -->
																<bean:define name="compParent" property="tableDataColSize" id="tableDataColSize" />
														<bean:define id="row" name="counter" />
														<tr>
															<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
					
																<logic:equal name="row" value="0">
																	<td valign="top" width="5%"><b><font
																		color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></b></font>
																	</td>
																</logic:equal>
					
																<logic:notEqual name="row" value="0">
																	<td valign="top" width="5%"><%=(tabrow==null)? "&nbsp;":tabrow%>
																	</td>
																</logic:notEqual>
					
					
															</logic:iterate>
														</tr>
													</logic:iterate>
													</table>
												</logic:notEmpty>
					
											<bean:define id="cla" name="compParent" property="clauseDesc" />
											<bean:define id="tab" name="compParent" property="tableArrayData1" />
											<input type="hidden" name="clauseDescTmp" value="<%=cla%>"> <input
												type="hidden" name="tableArrayData1Tmp" value="<%=tab%>">
											</div>
										</TD>
				
				
										<logic:notEmpty name="compParent" property="compGroupVO">
											<TD class="v-midalign" valign="top">
												<logic:iterate id="compList" name="compParent" property="compGroupVO"
													type="com.EMD.LSDB.vo.common.CompGroupVO" scope="page"
													indexId="compntIndex">
													<logic:present name="compList" property="compVO">
														<bean:define id="comp" name="compList" property="compVO" />
														<logic:notEqual name="compntIndex" value="0"> ;<br>
														</logic:notEqual>
														<bean:write name="comp" property="componentName" />
													</logic:present>
												</logic:iterate>
											</TD>
										</logic:notEmpty>
				
										<logic:empty name="compParent" property="compGroupVO">
											<td class="v-midalign">&nbsp;</td>
										</logic:empty>
								</TR>
							</logic:iterate>	
					    </tbody>
					 </table>  	
				</logic:present>
				<div class="spacer10"></div>
				
				<div class="form-group">
				  <div class="col-sm-12 text-center">
				       <button class="btn btn-primary" type='button' id="okButton" ONCLICK="javascript:fnAddPart()">OK</button>
   				       <button class="btn btn-primary" type='button' id="cancelButton" ONCLICK="javascript:window.close();">Cancel</button>
					</div>   
			   	</div>
				<div class="spacer50"></div>
	<html:hidden property="modelName" />
	<html:hidden property="textBoxIndex" />
	<html:hidden property="modelSeqNo" />
	<html:hidden property="hdnClauseSeqNo" />
	<html:hidden property="hdnClauseNo" />
	<html:hidden property="hdnspecType" />
	</html:form>
</div>
</BODY>
</HTML>				