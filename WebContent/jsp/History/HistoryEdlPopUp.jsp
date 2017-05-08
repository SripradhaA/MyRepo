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
	<link href="css/EMDCustom.css" TYPE="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="js/Others/jquerylatest.js"></script>
	<script type="text/javascript" src="js/Others/jquerymigrate.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap.min.js"></script>
	<SCRIPT type="text/javascript" SRC="js/Emd_Lsdb.js"></SCRIPT>
	<SCRIPT language="JavaScript" SRC="js/History.js"></SCRIPT>
	<SCRIPT language="JavaScript" SRC="js/error.js"></SCRIPT>
	<SCRIPT language="JavaScript" SRC="js/error_messages.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript">
		//CR_91 TO get  view EDL Nubers reprot of th order in excel formate.
		function fnViewEDLNumberExcel(excelFormat)
		{
		var dataLocationType="<%=request.getParameter("dataLocationType")%>";
		if(dataLocationType!="W")
		{
		dataLocationType="S";
		}
		var orderNo=document.forms['HistoryEdlPopUpForm'].orderNo.value;
		var specStatus=document.forms['HistoryEdlPopUpForm'].specStatus.value;
		var customerName=document.forms['HistoryEdlPopUpForm'].customerName.value;
		var modelName=document.forms['HistoryEdlPopUpForm'].modelName.value;
		var excelFormat=excelFormat;
		URL="historyEdlAction.do?method=fetchEdlNo&dataLocationType="+dataLocationType+"&OrderNum="+orderNo+"&SpecStatus="+specStatus+"&CustomerName="+customerName+"&modelName="+modelName+"&excelFormat="+excelFormat+"&orderKey=<%=request.getParameter("orderKey")%>";
		window.open(URL,'ViewEDLNumberExcel',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH=1280,height=600"); 
		}
		//Added for CR-118
		function fnViewSAPEDLNumbertoCSV()
		{
		var dataLocationType="<%=request.getParameter("dataLocationType")%>";
		if(dataLocationType!="W")
		{
		dataLocationType="S";
		}
		var orderNo=document.forms['HistoryEdlPopUpForm'].orderNo.value;
		var specStatus=document.forms['HistoryEdlPopUpForm'].specStatus.value;
		var customerName=document.forms['HistoryEdlPopUpForm'].customerName.value;
		var modelName=document.forms['HistoryEdlPopUpForm'].modelName.value;
		
		URL="historyEdlAction.do?method=fetchEDLNumberforCSV&dataLocationType="+dataLocationType+"&OrderNum="+orderNo+"&SpecStatus="+specStatus+"&CustomerName="+customerName+"&modelName="+modelName+"&orderKey=<%=request.getParameter("orderKey")%>";
		window.open(URL,'ViewEDLNumberCSV',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH=1280,height=600"); 
		}
	</SCRIPT>
	<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
</HEAD>
<BODY>
<div class="container-fluid">
	<html:form action="/historyEdlAction.do" styleClass="form-horizontal">
		<h4 class="text-center">Spec History - EDL Number List</h4>
	<div class="spacer10"></div>
	
	<div class="bg-info">
		<div class="row">
			<label class="col-sm-offset-1 col-sm-2 control-label"><strong>Order Number :</strong></label>
			<div class="col-sm-3 form-control-static">
				<bean:write name="HistoryEdlPopUpForm" property="orderNo" />
			</div>
			<label class="col-sm-2 control-label"><strong>Spec Status :</strong></label>
			<div class="col-sm-3 form-control-static">
				<bean:write name="HistoryEdlPopUpForm" property="specStatus" />
			</div>
		</div>
		<div class="row">
			<label class="col-sm-offset-1 col-sm-2 control-label"><strong>Customer Name :</strong></label>
			<div class="col-sm-3 form-control-static">
				<bean:write name="HistoryEdlPopUpForm" property="customerName" />
			</div>
			<label class="col-sm-2 control-label"><strong>Model Name :</strong></label>
			<div class="col-sm-3 form-control-static">
				<bean:write name="HistoryEdlPopUpForm" property="modelName" />
			</div>
		</div>
	</div>
	<!--  <div class="bg-info">
	<div class="form-group">
		<label class="col-sm-2">Order Number :</label>
		<div class="col-sm-2">
			<bean:write name="HistoryEdlPopUpForm" property="orderNo" />
		</div>
		<label class="col-sm-2">Spec Status :</label>
		<div class="col-sm-1">
			<bean:write name="HistoryEdlPopUpForm" property="specStatus" />
		</div>
		<label class="col-sm-2">Customer Name :</label>
		<div class="col-sm-2">
			<bean:write name="HistoryEdlPopUpForm" property="customerName" />
		</div>
		<label class="col-sm-2">Model :</label>
		<div class="col-sm-2">
			<bean:write name="HistoryEdlPopUpForm" property="modelName" />
		</div>
	</div>
	</div>-->

	<br>
	<!-- Validation Message Display Part Starts Here -->
	<%--Table Updated for CR-121 for server side error message tooltip --%>
	<div class="errorlayerhide" id="errorlayer">
	</div>
	<!-- Validation Message Display Part Ends Here -->

	<!-- Logic Tag Check For Display The Success Message After Add Starts Here -->


	<logic:present name="HistoryEdlPopUpForm" property="messageID"
		scope="request">
		 <%--Added for CR-121 for server side error message tooltip --%>	
	  <bean:define id="messageID" name="HistoryEdlPopUpForm" property="messageID"/>
	  <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
      <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
	</logic:present>

	<!-- Logic Tag Check For Display The Success Message After Add Functionality Ends Here -->
	<!-- Logic Tag Check For Display The Error Message After Add Functionality Starts Here -->
	<logic:present name="HistoryEdlPopUpForm" property="errorMessage"
		scope="request">
		<script>
                    hideErrors();
                    addMessage('<bean:write name="HistoryEdlPopUpForm" property="errorMessage"/>');
                    showScrollErrors();
                </script>

	</logic:present>

	<!-- Logic Tag Check For Display The Error Message After Add Functionality Ends Here -->
	<br>
	<logic:present name="HistoryEdlPopUpForm" property="clauseGroup">
			
			<TABLE class="table table-bordered">
				<thead>
					<TR>
						<TH width="15%">Clause No</TH>
						<TH width="45%">Component / <font style="font-style:italic">Clause Description</font></TH>
						<TH width="20%">EDL No</TH>
						<TH width="20%">Ref EDL No</TH>
					</TR>
				</thead>
				<tbody class="text-center">
			<logic:iterate id="sectionId" name="HistoryEdlPopUpForm"
				property="clauseGroup" scope="request" type="com.EMD.LSDB.vo.common.SectionVO" indexId="seccount">					
					<tr>
						<td colspan="4" class="lightGrey">
							<STRONG><bean:write name="sectionId" property="sectionName" /></STRONG>
						</td>
					</tr>
					<logic:notEmpty name="sectionId" property="subSec">
					<logic:iterate id="subSecList" name="sectionId" property="subSec" indexId="subcount">
					<logic:notEmpty name="subSecList" property="clauseGroup">
					<logic:iterate id="clause" name="subSecList" property="clauseGroup" indexId="clacount">
					<logic:present name="clause" property="clauseNum">
						
							<TR>
								<TD CLASS=borderbottomleft1 width="15%"><bean:write
									name="clause" property="clauseNum" /><BR>
								</TD>
								<%-- Modified for CR_106 to include Clause description against empty components --%>
								<logic:notEmpty name="clause" property="componentName">
								<TD CLASS=borderbottomleft1 width="45%"><bean:write
									name="clause" property="componentName" /><BR>
								</TD>
								</logic:notEmpty>
								<logic:empty name="clause" property="componentName">
								    <TD CLASS=borderbottomleft1 width="45%" style="font-style:italic">
									  <bean:define name="clause" property="clauseDesc" id="clauseDesc" /> 
									  <%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%><BR>
								    </TD>
								</logic:empty>
								<TD CLASS=borderbottomleft1 width="20%"><bean:write
									name="clause" property="edlNum" /><BR>
								</TD>
								<TD CLASS=borderbottomleft1 width="20%"><bean:write 
									name="clause" property="refEdlNum"/><BR>
								</TD>
								<%-- Modified for CR_106 - Ends here --%>
							</TR>
					</logic:present>
					</logic:iterate>
					</logic:notEmpty>
					</logic:iterate>
					</logic:notEmpty>
			</logic:iterate>
			</tbody>
			</TABLE>
	</logic:present>

	<div class="spacer20"></div>

	<div class="form-group">
		<div class="col-sm-12 text-center">
			<button class="btn btn-primary" type='button' name="btnExpToExcel" onclick="javascript:fnViewEDLNumberExcel('Y')">Export To Excel</button>
			<button class="btn btn-primary" type='button' name="btnExpToExcelSAP" onclick="javascript:fnViewSAPEDLNumbertoCSV()">Export To Excel(SAP)</button>
			<button class="btn btn-primary" type='button' name="btnPrint" onclick="javascript:fnPrint()">Print</button>
			<button class="btn btn-primary" type='button' name="btnCancel" onclick="javascript:fnClose()">Cancel</button>
		</div>
	</div>

	<!-- CR_91 -->
	<html:hidden property="orderNo"/>
	<html:hidden property="specStatus" />
	<html:hidden property="customerName"/>
	<html:hidden property="modelName"/>
		
</html:form>


</BODY>
</HTML>
