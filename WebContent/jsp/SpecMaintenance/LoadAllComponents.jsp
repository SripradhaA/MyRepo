<!DOCTYPE HTML>
<HTML>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<link rel="shortcut icon" href="images/favicon.ico" />
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<link href="css/bootstrap.css" TYPE="text/css" rel="stylesheet"/>
<link href="css/select2.css" TYPE="text/css" rel="stylesheet"/>
<link href="css/select2-bootstrap.css" TYPE="text/css" rel="stylesheet"/>
<link href="css/EMDCustom.css" rel="stylesheet"/>
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
        	$("#sltComp").select2({theme:'bootstrap'});
        })
    </script>
<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
</HEAD>
<BODY>
<div class="container" width="80%">
<html:form action="/orderAddClauseAction" styleClass="form-horizontal" method="post">

	<logic:present name="AddClauseOrderForm" property="leadCompClauseVO">
		<bean:size id="noOfLeadClauses" name="AddClauseOrderForm"
			property="leadCompClauseVO" />
	</logic:present>
	
	<h4 class="text-center">Available Components & Clauses across Models</h4>
	
	<div class="errorlayerhide" id="errorlayer"></div>
		
	<logic:match name="AddClauseOrderForm" property="method" value="LoadAllComp">
			<script> 
              fnNoRecords();
        	</script>

    </logic:match>
	
	<div class="row">
		<div class="spacer30"></div>
	</div>	
	
	<div class="form-group">
		<label class="col-sm-offset-4 col-sm-1 control-label">Component</label>
		<div class="col-sm-4">
			<html:select styleId="sltComp" name="AddClauseOrderForm" styleClass="form-control" property="leadComponentSeqNo"
				onchange="javascript:fnLoadAllClauses()">
				<option value="-1" selected>---Select---</option>
				<logic:present name="AddClauseOrderForm" property="modelCompVO">	
					<html:optionsCollection name="AddClauseOrderForm" property="modelCompVO"
						value="componentSeqNo" label="componentName"/>
				</logic:present>
			</html:select>
		</div>
	</div>
	
	<div class="row">
		<div class="spacer30"></div>
	</div>	
	
	<logic:greaterEqual name="noOfLeadClauses" value="1">
		<TABLE class="table table-bordered">
			<thead>
				<TR>
					<TH WIDTH="2%" CLASS="text-center">Select</TH>
					<TH WIDTH="25%" CLASS=text-center>Clause Description</TH>
					<TH WIDTH="10%" CLASS=text-center>Engineering Data</TH>
					<!-- Added for CR-111 to add a Model name column -->
					<TH WIDTH="8%" CLASS=text-center>Model Name</TH>
				</TR>
			</thead>
			<tbody>
				<logic:iterate id="leadCompClause" name="AddClauseOrderForm"
					property="leadCompClauseVO" scope="request"
					type="com.EMD.LSDB.vo.common.ClauseVO">
					<TR>
						<TD CLASS="text-center v-midalign">							
								<input type=radio name="leadClauseSeqNo"  
								value="<%=String.valueOf(leadCompClause.getClauseSeqNo())%>"
								onclick="javascript:fnSetSelectedClaValues('<bean:write
								name="leadCompClause" property="clauseSeqNo" />','<bean:write
								name="leadCompClause" property="modelSeqNo" />','<bean:write
								name="leadCompClause" property="subSectionSeqNo" />');" />
						</TD>
						<!-- CR_97 	<html:hidden name="leadCompClause" property="versionNo" /> -->
						<TD valign="top">
						
								<%-- CR-128 - Updated for Pdf issue --%>
									<%if(String.valueOf(leadCompClause.getClauseDesc()).startsWith("<p>"))
									{%>
										<%=(String.valueOf(leadCompClause.getClauseDesc()))%>
									<%}else{ %>	
							 <%=(String.valueOf(leadCompClause.getClauseDesc())).replaceAll("\n","<br>")%>
									<%}%>
								<%-- CR-128 - Updated for Pdf issue Ends Here--%>
							 
						<table class="table table-bordered text-center" >
							<logic:notEmpty name="leadCompClause" property="tableArrayData1">
									&nbsp;
			 						<logic:iterate id="outter" name="leadCompClause"
									property="tableArrayData1" indexId="counter">
									<!-- Added  For CR_93 -->
										<bean:define name="leadCompClause" property="tableDataColSize" id="tableDataColSize" />
									<bean:define id="row" name="counter" />
									<tr>
										<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
											<logic:equal name="row" value="0">
												<td valign="top" width="5%" class="borderbottomleft1"><b><font
													color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></font></b>
												</td>
											</logic:equal>
											<logic:notEqual name="row" value="0">
												<td valign="top" width="5%" class="borderbottomleft1"><%=(tabrow==null)? "&nbsp;":tabrow%>
												</td>
											</logic:notEqual>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</table></TD>
						<TD class="text-center">
						<table>
							<tr>
								<td class="text-center">
									 <logic:present name="leadCompClause"
									property="edlNO">
									<logic:iterate id="engDataEdlNo" name="leadCompClause"
										property="edlNO">
										<bean:message key="screen.edl" />
										<bean:write name="engDataEdlNo" />
										<br>
									</logic:iterate>
								</logic:present> 
								<!--  CR 87 -->
								<logic:present name="leadCompClause" property="refEDLNO">
	
									<logic:iterate id="engDataRefEdlNo" name="leadCompClause"
										property="refEDLNO">
										<bean:message key="screen.refEdl.start" />
										<bean:write name="engDataRefEdlNo" />
										<bean:message key="screen.refEdl.end" />
										<br>
									</logic:iterate>
								</logic:present>
								<logic:present name="leadCompClause"
									property="partOF">
									<logic:iterate id="engPartOfCd" name="leadCompClause"
										property="partOF">
										<logic:notEqual name="engPartOfCd" value="0">
											<bean:message key="screen.partOf" />
											<bean:write name="engPartOfCd" />
											<br>
										</logic:notEqual>
									</logic:iterate>
								</logic:present> <logic:present name="leadCompClause"
									property="dwONumber">
									<logic:notEqual name="leadCompClause" property="dwONumber"
										value="0">
										<bean:message key="screen.dwoNo" />
										<bean:write name="leadCompClause" property="dwONumber" />
										<br>
									</logic:notEqual>
								</logic:present> <logic:present name="leadCompClause"
									property="partNumber">
									<logic:notEqual name="leadCompClause" property="partNumber"
										value="0">
										<bean:message key="screen.partNo" />
										<bean:write name="leadCompClause" property="partNumber" />
										<br>
									</logic:notEqual>
								</logic:present> <!--Price Book Number is added in the Engineering data Section, modified on 30-09-08 by ps5722-->
								<logic:present name="leadCompClause" property="priceBookNumber">
									<logic:notEqual name="leadCompClause" property="priceBookNumber"
										value="0">
										<bean:message key="screen.priceBookNo" />&nbsp; <bean:write
											name="leadCompClause" property="priceBookNumber" />
										<br>
									</logic:notEqual>
								</logic:present> <logic:present name="leadCompClause"
									property="standardEquipmentDesc">
									<bean:write name="leadCompClause" property="standardEquipmentDesc" />
									<br>
								</logic:present> <logic:present name="leadCompClause"
									property="engDataComment">
									<bean:define id="engDatCmnt" name="leadCompClause"
										property="engDataComment" />
									<%=engDatCmnt %>
									<br>
								</logic:present></td></tr>
						</table>
						</TD>
				       <!-- Added for CR-111 to add a Model name column -->
						<TD valign="top" class="text-center v-midalign">
						<bean:write name="leadCompClause" property="modelName" />
						</TD>
						
					</TR>
				</logic:iterate>
			</tbody>
		</TABLE>
		
		<div class="row">
			<div class="10"></div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-12 text-center">
				<button class="btn btn-primary" type='button' ONCLICK="javascript:fnMapCompnfetchCla()">Load Clause Details</button>
				<button class="btn btn-primary" type='button' ONCLICK="window.close()">Close</button>
			</div>
		</div>
		
	</logic:greaterEqual>
	
	<logic:notPresent name="AddClauseOrderForm" property="leadCompClauseVO">
		<div class="form-group">
			<div class="col-sm-12 text-center">
				<button class="btn btn-primary" type='button' ONCLICK="window.close()">Close</button>
			</div>
		</div>
	</logic:notPresent>
	
	<div class="row">
		<div class="spacer100"></div>
	</div>
	
	<%--  <nav class="navbar navbar-default navbar-fixed-bottom navbar-small">
		<%Calendar cal= Calendar.getInstance();
		 int year = cal.get(Calendar.YEAR);%>	
		<h5 class="navbar-footer-text">Copyright &copy; <%=String.valueOf(year)%> Electro-Motive Diesel, Inc</h5>
	</nav> --%>
	
	 <nav class="navbar navbar-default navbar-fixed-bottom navbar-small">
	<SCRIPT type="text/javascript">
		var currentTime = new Date();
		var year = currentTime.getFullYear();
		/* Added for make new line entry when press enter key inside the suggestion box textarea */
		function setSelRange(inputEl, selStart, selEnd) { 
		 if (inputEl.setSelectionRange) { 
		  inputEl.focus(); 
		  inputEl.setSelectionRange(selStart, selEnd); 
		 } else if (inputEl.createTextRange) { 
		  var range = inputEl.createTextRange(); 
		  range.collapse(true); 
		  range.moveEnd('character', selEnd); 
		  range.moveStart('character', selStart); 
		  range.select(); 
		 } 
		}
		function cellFieldKeyDown(ev){
		 return;
		}
		/* Added for make new line entry when press enter key inside the suggestion box textarea - Ends here */
		$(document).ready(function() { 
			 $("textarea#suggestion").keypress(function(e) {     
			 	if(e.keyCode == 13)      { 
			 			$("textarea#suggestion").val($("textarea#suggestion").val()+"\n");
			 			var cellField = document.getElementById("suggestion");
			 			setSelRange(cellField, cellField.value.length, cellField.value.length);/*Added for make new line entry when press enter key inside the suggestion box textarea*/
			 			return false;     
					}       
			 });    
		})
	</SCRIPT>	
	<div class="container-fluid">	
		<h5 class="navbar-footer-text">Copyright &copy; <script type="text/javascript">document.write(year)</script> Electro-Motive Diesel, Inc</h5>
	</div>
</nav>
			 	
	<html:hidden property="hdnModelSeqNo" />
    <html:hidden property="hdnSubSecSeqNo" />
    <html:hidden property="compGroupSeqNo" />
    <html:hidden property="hdnClauseSeqNo" />
    <html:hidden property="hdnsecSeq" />
	<html:hidden property="hdnOrderKey" />
	<input type="hidden" name="frmModelSeqNo"/>
	<input type="hidden" name="frmSubSecSeqNo"/>
    
</html:form>
</div>
</BODY>
</HTML>