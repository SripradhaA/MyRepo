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
		<html:form action="/modelAddClauseAction.do">
			<h4 class="text-center">Unlink Characteristic Combination EDL #</h4>
				<div class="spacer10"></div>
				<div class="row">
					<div class="col-sm-4 col-sm-offset-4">
						<span id="errorlayer"></span>
					</div>
				</div>
							
				<logic:present name="ModelAddClauseForm" property="charGrpCombntnList"
					scope="request">
					<bean:size name="ModelAddClauseForm" id="chargrpcombnsize"
						property="charGrpCombntnList" />
				</logic:present>
			
				<logic:match name="ModelAddClauseForm" property="method"
					value="fetchCharCombntnEdlMap">
					<logic:lessThan name="chargrpcombnsize" value="1">
						<script>
							var id='886';
							hideErrors();
							addMsgNum(id);
							showScrollErrors();
						</script>
					</logic:lessThan>
				</logic:match>
				
				<logic:greaterThan name="chargrpcombnsize" value="0">
					<logic:present name="ModelAddClauseForm" property="charGrpCombntnList">
						<div class="spacer10"></div>
							<table class="table table-bordered table-hover">
						    <thead>
						        <tr>
									<TH CLASS="text-center text-nowrap"	WIDTH="25%">Characteristic Group(s)</TH>
									<TH CLASS="text-center text-nowrap"	WIDTH="30%">Characteristic Component(s)</TH>
									<TH CLASS="text-center text-nowrap"	WIDTH="10%">EDL#</TH>
									<TH CLASS="text-center text-nowrap" WIDTH="10%">RefEDL#</TH>
								</tr>	
							</thead>
						    <tbody class="text-center">	
						  			<logic:iterate id="charGrpCombntnList" name="ModelAddClauseForm"  property="charGrpCombntnList"
												type="com.EMD.LSDB.vo.common.ClauseVO" indexId="counter">
											<logic:iterate id="combSequenceNo" name="ModelAddClauseForm" property="charCombnList"> 
												<logic:equal name="combSequenceNo" value="<%=String.valueOf(charGrpCombntnList.getCombntnSeqNo())%>">
													<TR> 
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
														<TD CLASS="v-midalign">
															<logic:empty name="charGrpCombntnList" property="charEdlNo">&nbsp;</logic:empty>
															<logic:notEmpty name="charGrpCombntnList" property="charEdlNo">
																<%=String.valueOf(charGrpCombntnList.getCharEdlNo())%>
															</logic:notEmpty>
														</TD>
														<TD CLASS="v-midalign">
															<logic:empty name="charGrpCombntnList" property="charRefEDLNo">
															&nbsp;
															</logic:empty> 
															<logic:notEmpty name="charGrpCombntnList" property="charRefEDLNo">
															<%=String.valueOf(charGrpCombntnList.getCharRefEDLNo())%>
															</logic:notEmpty>
														</TD>
													</TR>
												</logic:equal>
											</logic:iterate>
									</logic:iterate>			
						    </tbody>
						   </table> 
					</logic:present>
				</logic:greaterThan>	
				<div class="spacer10"></div>
	
				<div class="form-group">
				  <div class="col-sm-12 text-center">
					   <button class="btn btn-primary" type='button' id="addButton" ONCLICK="javascript:fnUnlinkChrComdntnt()">Unlink</button>	
   				       <button class="btn btn-primary" type='button' id="cancelButton" ONCLICK="javascript:window.close();">Cancel</button>
					</div>   
			   	</div>
				<div class="spacer50"></div>
				
				<html:hidden property="hdnCombntnSeqNo" />				
		</html:form>
	</div>
</BODY>
</HTML>				