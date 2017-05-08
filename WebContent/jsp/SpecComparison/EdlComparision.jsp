<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html>
<HEAD>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link rel="shortcut icon" href="images/favicon.ico" />
	<link href="css/bootstrap.css" TYPE="text/css" rel="stylesheet"/>
    <link href="css/select2.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/select2-bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/bootstrap-table.min.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/EMDCustom.css" TYPE="text/css" rel="stylesheet"/> 
	<script type="text/javascript" src="js/Others/jquerylatest.js"></script>
	<script type="text/javascript" src="js/Others/jquerymigrate.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/Others/select2.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap-table-all.min.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap-table-en-US.min.js"></script>
	<script type="text/javascript" src="js/Others/jquery.bootbox.min.js"></script>
	<SCRIPT type="text/javascript" src="js/Others/webtoolkit.aim.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/CompInOrderReport.js"></SCRIPT>
	
	<SCRIPT LANGUAGE="JavaScript" SRC="js/Others/jquerymin.js"></SCRIPT>
	<LINK REL="stylesheet" TYPE="text/css" HREF="css/jquery-ui-custom.min.css">
	<SCRIPT LANGUAGE="JavaScript" SRC="js/Others/jquery-ui.custom.min.js"></SCRIPT>
	<LINK REL="stylesheet" HREF="css/Emd_Lsdb.css" TYPE="text/css">
	<SCRIPT LANGUAGE="JavaScript" SRC="js/error.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/error_messages.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/ModifySpec.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/SpecComparison.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript">
	
		function Print()
		{
			window.print();
		}
		 $(document).ready(function () {
		 	$('#EDLCompare').css("width",$('#orderlistsize').val()*450 + "px");
		 	$('.subsectable tr').each(function () {
		 		if (!$.trim($(this).text())) 
		 			$(this).remove(); 
		 	});
		 	$('.sectable tr').each(function () {
		 		if (!$.trim($(this).text())) 
		 			$(this).remove(); 
		 	});
		 }); 
	 
	</SCRIPT>

<TITLE>EDL Comparison/Report</TITLE>

<BODY class="main">

<div class = "container-fluid">

<html:form action="/compareComponentAction" method="post" styleClass="form-horizontal">

	<bean:define id="ordrsize" name="ComponentCompareForm" property="orderListSize"/>
	
	<input type="hidden" id="orderlistsize" value="<%=String.valueOf(ordrsize)%>"/>
	
	<div class="spacer10"></div>
		
	<h4 class ="text-center">EDL Comparison/Report</h4>
	
	<div class="spacer20"></div>
	
	<TABLE class="table table-bordered" id="tbEDLComparision">
		<TR>
			<TD>
				<TABLE class="table table-bordered">
					<TR>
						<logic:iterate id="ordListId" name="ComponentCompareForm"
							property="selectedOrderList" type="com.EMD.LSDB.vo.common.ClauseVO">
							<TD width="10%" style="align:top">
							<TABLE class="table table-bordered">
								<TR>
									<TD CLASS="heading-fontbgcolor" >Order Number</TD>
									<TD><strong><bean:write
										name="ordListId" property="orderNo" /></strong></TD>
								</TR>
								<tr>
									<TD CLASS="heading-fontbgcolor" >Model</TD>
									<TD><strong><bean:write
										name="ordListId" property="modelName" /></strong></TD>
								</TR>
								<TR>
									<TD CLASS="heading-fontbgcolor">Customer Name</TD>
									<TD><strong><bean:write
										name="ordListId" property="customerName" /></strong></TD>
								</tr>
								<tr>
									<TD CLASS="heading-fontbgcolor" >Spec Status</TD>
									<TD><strong><bean:write
										name="ordListId" property="specTypeName" /></strong></TD>
								</TR>
							</TABLE>
							</TD>
						</logic:iterate>
					</TR>
				</TABLE>
			</TD>
		</TR>
		
		<TR>
		<TD>
		<TABLE class="table table-bordered">
		   <logic:iterate id="componentCompareId" name="ComponentCompareForm"
						property="compareOrderList"   indexId="count">
			  <TR>
				<logic:iterate id="sectionId" name="componentCompareId" indexId="seccount" type="com.EMD.LSDB.vo.common.SectionVO">
					<logic:notEmpty name="sectionId" property="subSec">
				 			<logic:iterate id="subSecList" name="sectionId" property="subSec" indexId="subseccount">
				 					<logic:notEmpty name="subSecList">
									<TR>
										<logic:iterate id="subSec" name="subSecList" type="com.EMD.LSDB.vo.common.SubSectionVO">
										<logic:notEmpty name="subSec" property="clauseGroup">
											<TD width="10%" VALIGN="TOP">
											<TABLE class="table table-bordered">
											<logic:iterate id="clauseList" name="subSec" property="clauseGroup" indexId="subcount">
											<TR>
												<logic:iterate id="clauseId" name="clauseList" type="com.EMD.LSDB.vo.common.ClauseVO" indexId="clacount">
													<TD width="10%" VALIGN="TOP">
													<TABLE class="table table-bordered">
													<logic:equal name="count" value="0">
														<logic:equal name="subseccount" value="0">
														<logic:equal name="subcount" value="0">
														<TR>
															<TD width="15%" CLASS="table-heading text-center v-midalign">Clause Number</TD>
															<TD width="50%" CLASS="table-heading text-center v-midalign">Component /<br/><em>Clause Description</em></TD>
															<TD width="17%" CLASS="table-heading text-center v-midalign">EDL No</TD>
															<TD width="18%" CLASS="table-heading text-center v-midalign">Ref EDL No</TD>
														</TR>
														</logic:equal>
														</logic:equal>
													</logic:equal>
													<logic:equal name="subseccount" value="0">
														<logic:equal name="subcount" value="0">													
														<logic:notEmpty name="sectionId" property="secNames">
															<logic:iterate id="secName" name="sectionId" property="secNames" type="java.lang.String" 
																offset="clacount" length="1">
																<logic:notEmpty name="secName">
																<TR>
																	<TD colspan="4" width="10%" height="10%" CLASS="table-subheading text-center">
																		<strong> <bean:write name="secName"/></strong>
																	</TD>
																</TR>
																</logic:notEmpty>
																<logic:empty name="secName">
																<TR>
																	<TD width="10%" colspan="4" height="10%">&nbsp;</TD>
																</TR>
																</logic:empty>
															</logic:iterate>
														</logic:notEmpty>													
														</logic:equal>
													</logic:equal>
													<logic:equal name="subcount" value="0">												
													<logic:notEmpty name="subSec" property="subSecNames">
														<logic:iterate id="subSecName" name="subSec" property="subSecNames" type="java.lang.String" 
															offset="clacount" length="1">
														<logic:notEmpty name="subSecName">
														<TR>
															<TD colspan="4" width="10%" height="10%" CLASS="heading-fontbgcolor text-center">
																<strong> <bean:write name="subSecName"/></strong>
															</TD>
														</TR>
														</logic:notEmpty>													
														<logic:empty name="subSecName">
														<TR>
															<TD width="10%" colspan="4" height="10%">&nbsp;</TD>
														</TR>
														</logic:empty>
														</logic:iterate>
													</logic:notEmpty>
													</logic:equal>
													<logic:notEmpty name="clauseId" property="clauseNum">
													<TR>
													    <TD CLASS="text-center v-midalign" width="15%">
														    <bean:write name="clauseId" property="clauseNum"/><BR>
													     </TD>
														<logic:notEmpty name="clauseId" property="componentName">
													    <TD CLASS="text-center v-midalign" width="50%" style="word-wrap: break-word;
													            width:1000px;right:0;OVERFLOW-X:hidden;CURSOR:text;word-break:break-all;">
														   <bean:write name="clauseId" property="componentName"/><BR>
													    </TD>
														</logic:notEmpty>
														<logic:empty name="clauseId" property="componentName">
													    <TD CLASS="text-center v-midalign" width="50%" style="font-style:italic">
														  <bean:define name="clauseId" property="clauseDesc" id="clauseDesc" />
															<%String strClauseDesc =  String.valueOf(clauseDesc);
															if(strClauseDesc.startsWith("<p>")){%>
																<%=(String.valueOf(clauseDesc))%><BR>
															<%}else{ %>	
																 <%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%><BR>
															<%}%>
														</TD>
														</logic:empty>
														<logic:equal name="clauseId" property="flag" value="Y">
														<TD CLASS="text-center v-midalign" width="17%" bgcolor="#00CC33"> 
															   <font color="black">
															   <bean:write name="clauseId" property="edlNum"/></font><BR>
														</TD>													
														<TD CLASS="text-center v-midalign" width="18%">
															<bean:write name="clauseId" property="refEdlNum"/><BR>
														 </TD>
														</logic:equal>
														<logic:notEqual name="clauseId" property="flag" value="Y">
														<TD CLASS="text-center v-midalign" width="17%">
															<bean:write name="clauseId" property="edlNum"/><BR>
														 </TD>
														<TD CLASS="text-center v-midalign" width="18%">
															<bean:write name="clauseId" property="refEdlNum"/><BR>
														 </TD>
														</logic:notEqual>
													</TR>
													</logic:notEmpty>
													<logic:empty name="clauseId" property="clauseNum">
													<TR>
														<TD width="100%" colspan="4">&nbsp;</TD>
													</TR>
													</logic:empty>
														<!-- Modififed for LSDB_CR-75 on 22-May-09 by ps57222 Ends Here-->
													</TABLE>
													</TD>
												</logic:iterate>
												</TR>
											</logic:iterate>
											</TABLE>
											</TD>
										</logic:notEmpty>
										<logic:empty name="subSec" property="clauseGroup">
										
											<logic:notEmpty name="subSec" property="subSecNames">
											
											<TD width="10%" VALIGN="TOP">
											<TABLE class="table table-bordered">
												<TR>
												<logic:notEmpty name="sectionId" property="secNames">
												<logic:equal name="subseccount" value="0">
													<logic:iterate id="secName" name="sectionId" property="secNames" type="java.lang.String" >
														<logic:notEmpty name="secName">
															<TD width="10%" colspan="4" height="10%" CLASS="table-subheading text-center">
																<b> <bean:write name="secName"/></b>
															</TD>
														</logic:notEmpty> 
														<logic:empty name="secName">
															<TD width="10%" colspan="4" height="10%">&nbsp;</TD>
														</logic:empty>
														
													</logic:iterate>
												</logic:equal>
												</logic:notEmpty>
												</TR>
												<TR>
												<logic:notEmpty name="subSec" property="subSecNames">
												<logic:iterate id="subSecName" name="subSec" property="subSecNames" type="java.lang.String" >
													<logic:notEmpty name="subSecName">
													<TD width="10%" colspan="4" height="10%" CLASS="heading-fontbgcolor bd-right-one text-center">
														<strong> <bean:write name="subSecName"/></strong>
													</TD>
													</logic:notEmpty>
													<logic:empty name="subSecName">
														<TD width="10%" colspan="4" height="10%">&nbsp;</TD>
													</logic:empty>
												</logic:iterate>
												</logic:notEmpty>
												</TR>
											</TABLE>
											</TD>
												</logic:notEmpty>
												
										</logic:empty>
										</logic:iterate>
									</TR>
									</logic:notEmpty>
							</logic:iterate>
					</logic:notEmpty>
			  	</logic:iterate>
			  </TR>
			</logic:iterate>
		</TABLE>
     	</TD>
  		</TR>
	</TABLE>
	
	<div class="row">
		<div class="col-sm-12 text-center">
			<button class="btn btn-primary" type='button' id="btnPrint" ONCLICK="Print()">Print</button>
			<button class="btn btn-primary" type='button' id="btnCancel" ONCLICK="window.close();">Cancel</button>
		</div>
	</div>
	
	<div class="spacer50"></div>
	
</html:form>
</div>
</BODY>
</html:html>
			