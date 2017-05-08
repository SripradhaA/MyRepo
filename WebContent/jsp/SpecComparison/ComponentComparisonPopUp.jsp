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
	<LINK REL="stylesheet" HREF="css/Emd_Lsdb.css" TYPE="text/css">
	<SCRIPT LANGUAGE="JavaScript" SRC="js/CompInOrderReport.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/Others/jquerymin.js"></SCRIPT>
	<LINK REL="stylesheet" TYPE="text/css" HREF="css/jquery-ui-custom.min.css">
	<SCRIPT LANGUAGE="JavaScript" SRC="js/Others/jquery-ui.custom.min.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/error.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/error_messages.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/ModifySpec.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/SpecComparison.js"></SCRIPT>
	
<script>
function fnPrint()
{
window.print();
}

</script>

<TITLE>Component Comparison/Report</TITLE>

<BODY class="main">

<div class = "container-fluid">

<html:form action="/compareComponentAction" method="post" styleClass="form-horizontal">

	<div class="spacer10"></div>
		
	<h4 class ="text-center">Component Comparison/Report</h4>
	
	<div class="spacer20"></div>
	
	<TABLE class="table table-bordered" id="tbCompareComponents">
		<TR>
			<TD>
				<TABLE class="table table-bordered">
					<TR>
						<logic:iterate id="ordListId" name="ComponentCompareForm"
							property="selectedOrderList" type="com.EMD.LSDB.vo.common.OrderVO">
							<TD width="10%" style="align:top">
								<TABLE class="table table-bordered">
									<TR>
										<TD CLASS="heading-fontbgcolor">Order Number</TD>
										<TD><strong><bean:write
											name="ordListId" property="orderNo" /></strong></TD>
									</TR>
									<tr>
										<TD CLASS="heading-fontbgcolor">Model</TD>
										<TD><strong><bean:write
											name="ordListId" property="modelName" /></strong></TD>
									</TR>
									<TR>
										<TD CLASS="heading-fontbgcolor">Customer Name</TD>
										<TD><strong><bean:write
											name="ordListId" property="customerName" /></strong></TD>
									</tr>
									<tr>
										<TD CLASS="heading-fontbgcolor">Spec Status</TD>
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
					<logic:iterate id="componentCompareId" name="ComponentCompareForm" property="compareOrderList" indexId="count">
					<TR>
						<logic:iterate id="sectionId" name="componentCompareId" indexId="seccount">
							<TD width="10%" style="align:top">
								<logic:notPresent
									name="sectionId" property="sectionName">
								</logic:notPresent>
									<TABLE class="table table-bordered">
										<logic:present name="sectionId" property="subSectionName">
										</logic:present>
											<logic:equal name ="count" value="0"> 
												<TR>
													<TD width="50%"CLASS="table-heading text-center" >Component Group</TD>
													<TD width="50%" CLASS="table-heading text-center">Component</TD>
												</TR>
											</logic:equal>
												  											 						
											<TR>
												<logic:present name="sectionId" property="sectionName">
													<TD colspan="2" CLASS="table-subheading text-center"><bean:write
														name="sectionId" property="sectionName" /></TD>
												</logic:present>
											</TR>
											<TR>
												<logic:present name="sectionId" property="subSectionName" >
													<TD colspan="2" CLASS="heading-fontbgcolor text-center"><bean:write
														name="sectionId" property="subSectionName" /></TD>
												</logic:present>
											</TR>
											<logic:notEmpty name="sectionId" property="componentVO">
											<logic:iterate name="sectionId" id="componentDescId"
												property="componentVO">
												<TR>
													<TD width="50%" CLASS="text-center v-midalign">
														<bean:write name="componentDescId" property="componentGroupName" />
													</TD>	
													<TD width="50%" CLASS="text-center v-midalign">
														<bean:write name="componentDescId" property="componentName" />
													</TD>
												</TR>
											</logic:iterate>
											</logic:notEmpty>
										</TABLE>
									</TD>
								</logic:iterate>	
							</TR>
					</logic:iterate>
				</TABLE>
			</TD>
		</TR>
	</TABLE>
			
	<div class="row">
		<div class="col-sm-12 text-center">
			<button class="btn btn-primary" type='button' id="btnPrint" ONCLICK="window.print();">Print</button>
			<button class="btn btn-primary" type='button' id="btnCancel" ONCLICK="window.close();">Cancel</button>
		</div>
	</div>
	
	<div class="spacer50"></div>
			
</html:form>
</div>		
</BODY>
</html:html>
