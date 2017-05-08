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
	<link href="css/bootstrap.css" TYPE="text/css" rel="stylesheet"/>
    <link href="css/select2.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/select2-bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<link REL="stylesheet" TYPE="text/css" HREF="css/jquery-ui-custom.min.css">
	<link href="css/EMDCustom.css" rel="stylesheet"/>
	<link href="css/dataTables.bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<link rel='stylesheet' type='text/css' href='css/confirm.css' />
	<link rel="shortcut icon" href="images/favicon.ico" />
	<script type="text/javascript" src="js/Others/jquerylatest.js"></script>
	<script type="text/javascript" src="js/Others/jquerymigrate.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/Others/select2.js"></script>
	<script type="text/javascript" src="js/Others/jquery.datatables.js"></script>
	<SCRIPT type="text/javascript" SRC="js/Emd_Lsdb.js"></SCRIPT>
	<SCRIPT type="text/javascript" SRC="js/error.js"></SCRIPT>
	<SCRIPT type="text/javascript" SRC="js/error_messages.js"></SCRIPT>
	<script type="text/javascript" src="js/Others/jquery.bootbox.min.js"></script>
	<SCRIPT language="JavaScript" SRC="js/AddClause.js"></SCRIPT>
<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
</HEAD>
<BODY>
	<div class="container" width="80%">
				<div class="row">
					<div class="col-sm-4 col-sm-offset-4">
						<span id="errorlayer"></span>
					</div>
				</div>
				<logic:present name="ModelClauseDescPopUpForm" property="charGrpCombntnList"
					scope="request">
					<bean:size name="ModelClauseDescPopUpForm" id="chargrpcombnsize"
						property="charGrpCombntnList" />
				</logic:present>
				<logic:match name="ModelClauseDescPopUpForm" property="method"
					value="fetchCharCombntnEdlView">
					<logic:lessThan name="chargrpcombnsize" value="1">
						<script>
							var id='895';
							hideErrors();
							addMsgNum(id);
							showScrollErrors();
						</script>
					</logic:lessThan>
				</logic:match>
				
				<logic:match name="ModelClauseDescPopUpForm" property="method" value="fetchCharCombntnEdlView">	
						<div class="form-horizontal">
							<div class="form-group">
									<label class="col-sm-5 control-label">Specification Type</label>
									<div class="col-sm-1 text-nowrap form-control-static">
										<logic:notEmpty  name="ModelClauseDescPopUpForm"  property="hdnspecType">
											<bean:write  name="ModelClauseDescPopUpForm" property="hdnspecType" scope="request" />
										</logic:notEmpty>
									</div>
							</div>
							<div class="form-group">
									<label class="col-sm-5 control-label">Model</label>
									<div class="col-sm-1 text-nowrap form-control-static">
										<bean:write name="ModelClauseDescPopUpForm" property="hdnModelName" scope="request" />
									</div>
							</div>
							<div class="form-group">
									<label class="col-sm-5 control-label">Section</label>
									<div class="col-sm-1 text-nowrap form-control-static">
									<bean:write name="ModelClauseDescPopUpForm" property="hdnSectionName" scope="request" />
									</div>
							</div>
							<div class="form-group">
									<label class="col-sm-5 control-label">SubSection</label>
									<div class="col-sm-1 text-nowrap form-control-static">
									<bean:write name="ModelClauseDescPopUpForm" property="hdnSubSectionName" scope="request" />
									</div>
							</div>
						</div>	
						<div class="spacer10"></div>
						<logic:greaterThan name="chargrpcombnsize" value="0">
							<table class="table table-bordered table-hover">
						    <thead>
						        <tr>
									<TH CLASS="text-center" WIDTH="2%">Select</TH>
									<TH CLASS="text-center text-nowrap"	WIDTH="25%">Characteristic Group(s)</TH>
									<TH CLASS="text-center text-nowrap"	WIDTH="30%">Characteristic Component(s)</TH>
									<TH CLASS="text-center text-nowrap"	WIDTH="10%">EDL#</TH>
									<TH CLASS="text-center text-nowrap" WIDTH="10%">RefEDL#</TH>
								</tr>	
							</thead>
						    <tbody class="text-center">	
						    		<logic:iterate id="charGrpCombntnList" name="ModelClauseDescPopUpForm"  property="charGrpCombntnList"
											type="com.EMD.LSDB.vo.common.ClauseVO" indexId="counter">
									<TR>
										<TD CLASS="v-midalign">
												<html:checkbox property="combntnSeqNo" styleId="combntnSeqNo"
													value="<%=String.valueOf(charGrpCombntnList.getCombntnSeqNo())%>"/>
										</TD>
										<TD CLASS="v-midalign" COLSPAN="2">
										<TABLE class="table table-bordered">
											<logic:iterate id="charGrpCompList" name="charGrpCombntnList"
												property="componentVO" type="com.EMD.LSDB.vo.common.ComponentVO"
												scope="page">
												<TR>
													<TD CLASS="v-midalign" width="25%"><%=String.valueOf(charGrpCompList
																	.getComponentGroupName())%></TD>
													<TD CLASS="v-midalign" width="30%"><%=String.valueOf(charGrpCompList
																	.getComponentName())%></TD>
												</TR>
											</logic:iterate>
										</TABLE>
										</TD>
										<TD CLASS="v-midalign"><logic:empty name="charGrpCombntnList"
											property="charEdlNo">	&nbsp;
									  </logic:empty> <logic:notEmpty name="charGrpCombntnList"
											property="charEdlNo">
											<%=String.valueOf(charGrpCombntnList
																	.getCharEdlNo())%>
										</logic:notEmpty></TD>
										<TD CLASS="v-midalign"><logic:empty name="charGrpCombntnList"
											property="charRefEDLNo">
											&nbsp;
										</logic:empty> <logic:notEmpty name="charGrpCombntnList"
											property="charRefEDLNo">
											<%=String.valueOf(charGrpCombntnList
																	.getCharRefEDLNo())%>
				
										</logic:notEmpty></TD>
				
									</TR>
									
								</logic:iterate>
						    </tbody>
						    </table>
						    <div class="spacer10"></div>
				
							<div class="form-group">
							  <div class="col-sm-12 text-center">
							       <button class="btn btn-primary" type='button' id="backButton" ONCLICK="javascript:fnReloadAddCharsticComponent()">Back to Clause Selection</button>
	  							   <button class="btn btn-primary" type='button' id="addButton" ONCLICK="javascript:fnAddcharEdl()">Link</button>	
			   				       <button class="btn btn-primary" type='button' id="cancelButton" ONCLICK="javascript:window.close();">Cancel</button>
								</div>   
						   	</div>
							<div class="spacer50"></div>
						</logic:greaterThan>
						<logic:lessThan name="chargrpcombnsize" value="1">
							<div class="spacer10"></div>
							<div class="form-group">
							  <div class="col-sm-12 text-center">
							       <button class="btn btn-primary" type='button' id="backButton" ONCLICK="javascript:fnReloadAddCharsticComponent()">Back to Clause Selection</button>
	  							</div>   
						   	</div>
							<div class="spacer50"></div>
						</logic:lessThan>
				</logic:match>
	</div>
</BODY>
</HTML>				