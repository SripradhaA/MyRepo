<!DOCTYPE HTML>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<HTML>

	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link rel="shortcut icon" href="images/favicon.ico" />
	<link href="css/bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/datatables.min.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/EMDCustom.css" TYPE="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="js/Others/jquerylatest.js"></script>
	<script type="text/javascript" src="js/Others/jquerymigrate.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/Others/datatables.min.js"></script>
	<script type="text/javascript" src="js/Others/datatables.plugins.js"></script>
	<SCRIPT type="text/javascript" SRC="js/Emd_Lsdb.js"></SCRIPT>
	<script type="text/javascript" src="js/Others/jquery.bootbox.min.js"></script>
	
	<SCRIPT type="text/JavaScript" SRC="js/OrderSpecificClauseReport.js"></SCRIPT>
	<SCRIPT type="text/JavaScript" SRC="js/error.js"></SCRIPT>
	<SCRIPT type="text/JavaScript" SRC="js/error_messages.js"></SCRIPT>

<%!
	public String replace(String clause){
		if(clause != null && !"".equals(clause)){
			clause.replaceAll("&lt","<").replaceAll("&gt",">");
		}
		return clause; 
}
%>

<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>

<script language="javascript">
function Print()
{
	window.print();
}


function fnMouseClick(divID,evt,obj)
{
			var div = document.getElementById(divID);
            div.style.display='';
			 var curleft = 0;
            var curtop = 0;
            
            if (obj.offsetParent) 
            {
                        curleft = obj.offsetLeft
                        curtop = obj.offsetTop
                        while (obj = obj.offsetParent) 
                        {
                                    curleft += obj.offsetLeft
                                    curtop += obj.offsetTop
                        }
            }                       
           
			div.style.left = curleft-330 ;
            div.style.top =  curtop+10 ;

			document.getElementById(divID).style.visibility="visible";
}

function fnMouseOut(divelem)
{
	 document.getElementById(divelem).style.visibility="hidden";
}
</script>

<BODY>

<div class = "container" width="80">
	<html:form action="/OrderSpecificClauseAction.do" method="post" styleClass="form-inline">
		<h4 class ="text-center">View Order Specific Clauses Report</h4>
		<p class="text-center"><mark>All the Clauses below are only added to selected Order.</mark></p>
		<div class="spacer30"></div>
		
		<div class="row">
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Specification Type</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<bean:write name="OrderSpecificClauseForm" property="hdnSelSpecType" />
			</div>
		</div>
		<div class="spacer10"></div>
		<div class="row">
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Model</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<bean:write name="OrderSpecificClauseForm"
							property="hdnSelModel" />
			</div>
		</div>
		<div class="spacer10"></div>
		<div class="row">
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Order Number(s)</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<bean:write name="OrderSpecificClauseForm" property="orderNumberResult"/>
			</div>
		</div>
		<div class="spacer10"></div>
		
		<logic:notPresent name="OrderSpecificClauseForm" property="arlClauseList">
			
			<div class="errorlayerhide" id="errorlayer"></div>		
			<script> 
              fnNoRecords();
        	</script>

		</logic:notPresent> 
		
		<div class="spacer20"></div>

	<logic:present name="OrderSpecificClauseForm" property="arlClauseList"> 
	
	<TABLE class="table table-bordered table-striped" id="tbViewOrderSpecificClaReport">
		<thead>
			<TR>
				<TH CLASS='text-center' width="15%">Order Number</TH>
				<TH CLASS='text-center' width="15%">Spec Status</TH>
				<TH CLASS='text-center' width="10%">Clause No</TH>
				<TH CLASS='text-center' width="50%">Clause Description</TH>
				<TH CLASS='text-center' width="10%">Engineering Data</TH>
			</TR>
		</thead>
		<tbody>
		
		<logic:iterate id="clauseListId" name="OrderSpecificClauseForm" property="arlClauseList" 
				type="com.EMD.LSDB.vo.common.ClauseVO" indexId="counter">
			<TR>
				<TD CLASS="text-center v-midalign"><bean:write name="clauseListId" property="orderNo"/> </TD>
				<TD CLASS="text-center v-midalign"><bean:write name="clauseListId" property="status"/> </TD>
				<TD CLASS="text-center v-midalign"><bean:write name="clauseListId" property="clauseNum"/> </TD>
				<TD CLASS="v-midalign">
				<%--logic tag added for CR-127--%>
				<logic:equal name="clauseListId" property="clauseDelFlag" value="Y">
				RESERVED
				</logic:equal>
				<logic:notEqual name="clauseListId" property="clauseDelFlag" value="Y">
				<bean:define name="clauseListId"
											property="clauseDesc" id="clauseDesc" /> 
							<%String strClauseDesc =  String.valueOf(clauseDesc);
							if(strClauseDesc.startsWith("<p>")){%>
								<%=(String.valueOf(clauseDesc))%>
							<%}else{ %>	
								<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>
							<%}%>
							
					<logic:notEmpty name="clauseListId" property="tableArrayData1">&nbsp;
						<TABLE class="table table-bordered">
							<logic:iterate id="outter" name="clauseListId" property="tableArrayData1"
								indexId="counter">
								<bean:define id="row" name="counter" />
								<!-- Added  For CR_93 -->
								<bean:define name="clauseListId" property="tableDataColSize" id="tableDataColSize" />
								<tr>
									<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
										<logic:equal name="row" value="0">
											<td valign="top" CLASS="text-center v-midalign"><b><font
												color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></b></font>
											</td>
										</logic:equal>
										<logic:notEqual name="row" value="0">
											<td valign="top" CLASS="text-center v-midalign"><%=(tabrow==null)? "&nbsp;":tabrow%>
											</td>
										</logic:notEqual>
									</logic:iterate>
								</tr>
							</logic:iterate>
							</TABLE>
					</logic:notEmpty>
					
				</logic:notEqual>	
				</TD>
				<TD CLASS="text-center v-midalign"	style="word-wrap: break-word;width:100;right:0">
					<logic:present name="clauseListId" property="edlNO">
						<logic:iterate id="engDataEdlNo" name="clauseListId" property="edlNO">
							<bean:message key="screen.edl" />
							<bean:write name="engDataEdlNo" />
							<br>
						</logic:iterate>
					</logic:present> 
					<logic:present name="clauseListId" property="refEDLNO">
						<logic:iterate id="engDataRefEdlNo" name="clauseListId"	property="refEDLNO">
							<bean:message key="screen.refEdl.start" />
							<bean:write name="engDataRefEdlNo" />
							<bean:message key="screen.refEdl.end" />
							<br>
						</logic:iterate>
					</logic:present> 
					<logic:present name="clauseListId" property="partOF">
						<logic:iterate id="engPartOfCd" name="clauseListId"	property="partOF">
							<logic:notEqual name="engPartOfCd" value="0">
								<bean:message key="screen.partOf" />
								<bean:write name="engPartOfCd" />
								<br>
							</logic:notEqual>
						</logic:iterate>
					</logic:present> <logic:present name="clauseListId"
						property="dwONumber">
						<logic:notEqual name="clauseListId" property="dwONumber" value="0">
							<bean:message key="screen.dwoNo" />
							<bean:write name="clauseListId" property="dwONumber" />
							<br>
						</logic:notEqual>
					</logic:present> <logic:present name="clauseListId"
						property="partNumber">
						<logic:notEqual name="clauseListId" property="partNumber" value="0">
							<bean:message key="screen.partNo" />
							<bean:write name="clauseListId" property="partNumber" />
							<br>
						</logic:notEqual>
					</logic:present>
					<logic:present name="clauseListId" property="priceBookNumber">
						<logic:notEqual name="clauseListId" property="priceBookNumber"
							value="0">
							<bean:message key="screen.priceBookNo" />&nbsp;<bean:write
								name="clauseListId" property="priceBookNumber" />
							<br>
						</logic:notEqual>
					</logic:present> <logic:present name="clauseListId"
						property="standardEquipmentDesc">
						<bean:write name="clauseListId" property="standardEquipmentDesc" />
						<br>
					</logic:present> <logic:present name="clauseListId"
						property="comments">
						<bean:define id="engDatCmnt" name="clauseListId"
							property="comments" />
						<%=engDatCmnt %>
						<br>
					</logic:present>
				</TD>
			</TR>
		</logic:iterate>
		</tbody>
	</TABLE>			
	</logic:present>
	
	<div class="spacer20"></div>
	
	<div class="row">
		<div class="col-sm-12 text-center">
              <button class="btn btn-primary" type='button' id="btnPrint" ONCLICK="Print()">Print</button>
              <button class="btn btn-primary" type='button' id="btnClose" ONCLICK="javascript:window.close();">Close</button>
		</div>
	</div>
	
	<div class="spacer50"></div>
	
<html:hidden property="hdnSelSpecType" />
<html:hidden property="hdnSelModel"/>
</html:form>
</div>
</BODY>
</HTML>
