<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<SCRIPT LANGUAGE="JavaScript" SRC="js/1058SearchRequest.js"></SCRIPT>
<script>  	
	$(document).ready(function() {
		$("#sltCustomer").select2({theme:'bootstrap'});
      	$("#sltAwaitingOn").select2({theme:'bootstrap'});
	    $('#tb1058SearchRequest').DataTable({
	    	 paging:   false,
	         ordering: true,
	         info:     false,
	         order: [[ 1, 'desc' ]],
	         columnDefs: [
                       { "orderable": false, "targets": [0,3] },
                       { "searchable": false, "targets": [0] },
                       { "type": "natural", "targets": [1] }
                 ]
	    });
      })
</script>

<div class="container-fluid">
<html:form action="/SearchRequest1058Action" method="post" styleClass="form-inline">
	<h4 class="text-center"><bean:message key="Application.Screen.Search1058Request" /></h4>
	<div class="spacer10"></div>
	<p class="text-center"><mark><bean:message key="Message.WildcardSearch" /></mark></p>
	<p class="text-center"><mark>Press SHIFT/CTRL for selecting multiple Models/Status</mark></p>
	<p class="text-center"><mark><bean:message key="Application.Screen.Search1058Request.Note" /></mark></p>
	<p class="text-center"><mark><bean:message key="Application.Screen.LegacySearch" /></mark></p>
		
	<!-- To display  Messages - Start -->
	<%--Table Updated for CR-121 for server side error message tooltip --%>
	<div class="errorlayerhide" id="errorlayer">
	</div>
	<!-- To display Messages - End -->

	<!-- Validation Message For No Records Found - Start -->

	<!-- To get Order List from Form - Start -->

	<logic:present name="SearchRequest1058Form" property="requestList">
		<bean:size id="reqListLen" name="SearchRequest1058Form"
			property="requestList" />
	</logic:present>

	<!-- To get Order List from Form - End -->

	<logic:present name="SearchRequest1058Form" property="messageID"
		scope="request">
	  <%--Added for CR-121 for server side error message tooltip --%>	
	  <bean:define id="messageID" name="SearchRequest1058Form" property="messageID"/>
	  <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
      <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
	</logic:present>

	<!-- Validation Message For No Records Found - Start -->
	<logic:match name="SearchRequest1058Form" property="method"
		value="fetchDetails">
		<logic:lessThan name="reqListLen" value="1">
			<script>
			//Updated for CR-121 server side error message
			fnNoRecords();
			</script>
		</logic:lessThan>
	</logic:match>

	<!-- Validation Message For No Records Found - End -->

	<!-- Drop Downs For Search Criteria ---Start -->
	<div class="spacer20"></div>
	
	<div class="row ">
		<div class="col-sm-12 text-center">
			<div class="form-group">
				<label class="control-label">Customer</label>
				<html:select name="SearchRequest1058Form" property="custSeqNo" styleId="sltCustomer" styleClass="form-control">
					<option selected value="-1">---Select---</option>
					<html:optionsCollection name="SearchRequest1058Form"
						property="custNameList" value="customerSeqNo" label="customerName" />
				</html:select>
				<span class="alertmsg"></span>	
			</div>
			
			<div class="form-group">
				<label class="control-label">Model</label>
				<html:select name="SearchRequest1058Form" property="modelSeqNos" size="6" styleClass="form-control" multiple="true">
					<html:optionsCollection name="SearchRequest1058Form"
						property="mdlNameList" value="modelSeqNo" label="modelName" />
				</html:select>
				<span class="alertmsg"></span>	
			</div>
			
			<div class="form-group">
				<label class="control-label">Status</label>
				<html:select name="SearchRequest1058Form" property="statusSeqNos" size="6" styleClass="form-control" multiple="true">
					<html:optionsCollection name="SearchRequest1058Form"
						property="statusList" value="statusSeqNo" label="statusDesc" />
				</html:select>
				<span class="alertmsg"></span>	
			</div>
				
			<div class="form-group">
				<label class="control-label">Awaiting action on</label>
					<html:select name="SearchRequest1058Form" property="awApproval" styleId="sltAwaitingOn" styleClass="form-control">
						<option selected value="-1">---Select---</option>
						<html:optionsCollection name="SearchRequest1058Form"
							property="userList" value="userrId" label="lasttName" />
					</html:select>
				<span class="alertmsg"></span>	
			</div>
		</div>
	</div>
	
	<div class="spacer20"></div>
	
	<div class="row">
		<div class="col-sm-12 text-center">
			<div class="form-group">
				<label class="control-label">Order Number</label>
				<html:text property="orderNo" styleClass="form-control" styleId="orderNumber"  size="15"
					maxlength="15" onkeypress="return keyHandler()" />
				<span class="alertmsg"></span>	
			</div>
		</div>
	</div>
	
	<div class="spacer20"></div>
	
	<div class="row">
		<div class="col-sm-12 text-center">
			<div class="form-group">
	              <button class="btn btn-primary" type='button' id="btnSearch" ONCLICK="javascript:fnSearchRequest()">Search 1058 Requests</button>
	              <button class="btn btn-primary" type='button' id="btnReset" ONCLICK="javascript:fnResetFields()"> Reset Fields</button>
	        </div>
     	</div>
    </div>
    
	<!-- Drop Downs For Search Criteria ---End -->
	<logic:greaterThan name="reqListLen" value="0">
	<hr>
		<div class="row">
			<div class="col-sm-11 text-center"><h5 class="text-center"><strong>Search critera</strong></h5></div>
		</div>
		
		<div class="spacer10"></div>
		
		<div class="row">
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Customer</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<logic:notEqual name="SearchRequest1058Form" property="custSeqNo" value="-1">
					<bean:write name="SearchRequest1058Form" property="hdnSelectedCustomer" />
				</logic:notEqual>
				<logic:equal name="SearchRequest1058Form" property="custSeqNo" value="-1">
					&nbsp;
				</logic:equal>
			</div>
		</div>
		
		<div class="spacer10"></div>
		
		<div class="row">	  
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Model</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-4 text-left">
				<logic:notEqual name="SearchRequest1058Form" property="mdlSeqNo" value="-1">
					<bean:write name="SearchRequest1058Form" property="hdnSelectedModel" />
				</logic:notEqual>
				<logic:equal name="SearchRequest1058Form" property="mdlSeqNo" value="-1">
					&nbsp;
				</logic:equal>
			</div>
		</div>
		
		<div class="spacer10"></div>
		
		<div class="row">	
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Status</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-4 text-left">
				<logic:notEqual name="SearchRequest1058Form" property="statusSeqNo" value="-1">
					<bean:write name="SearchRequest1058Form" property="hdnSelectedStatus" />
				</logic:notEqual>
				<logic:equal name="SearchRequest1058Form" property="statusSeqNo" value="-1">
					&nbsp;
				</logic:equal>
			</div>
		</div>
		
		<div class="spacer10"></div>
		
		<div class="row">	
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Awaiting Approval</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<logic:notEqual name="SearchRequest1058Form" property="awApproval" value="-1">
					<bean:write name="SearchRequest1058Form" property="hdnSelectedAwApproval" />
				</logic:notEqual>
				<logic:equal name="SearchRequest1058Form" property="awApproval" value="-1">
					&nbsp;
				</logic:equal>
			</div>
		</div>
		
		<div class="spacer10"></div>
	
		<div class="row">
			<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Order Number</strong></div>
			<div class="col-sm-1 text-center">:</div>
			<div class="col-sm-2 text-left">
				<bean:write name="SearchRequest1058Form" property="hdnorderNo" />
			</div>
		</div>
		
		<div class="spacer10"></div>
			
		<DIV><a id="chk" href="#chk">Check All</a>&nbsp;|&nbsp;<a id="unchk" href="#unchk">Clear All</a></DIV>
		<div class="spacer10"></div>
		<TABLE class="table table-bordered" id="tb1058SearchRequest">
			<thead>
				<TR>
					<TH width="5%">Select</TH>
					<TH width="13%">1058 #</TH>
					<TH width="5%">Spec Revision</TH>
					<TH width="25%">General Description</TH>
					<TH width="12%">Status</TH>
					<TH width="8%">Actual Sell Price(USD)</TH>
					<TH width="17%">Customer Name</TH>
					<TH width="15%">Issued By</TH>
				</TR>
			</thead>
			<tbody class="text-center ">
			<logic:iterate id="reqListId" name="SearchRequest1058Form" property="requestList"
				type="com.EMD.LSDB.vo.common.ChangeRequest1058VO">
				<TR>
					<TD CLASS="v-midalign">
					<%--<logic:equal name="reqListId" property="lagacy1058Flag" value="N">--%>
						<input class="seqNoChkbox" type="checkbox" name="chk1058SeqNo"
							value="<bean:write name="reqListId" property="seqNo1058"/>" />&nbsp;
						<%--</logic:equal>
						<logic:notEqual name="reqListId" property="lagacy1058Flag" value="N">
							&nbsp;
						</logic:notEqual>--%>
					</TD>
					<TD CLASS="v-midalign" data-sort="<bean:write name='reqListId' property='id1058' />" data-search="<bean:write name='reqListId' property='id1058' />">
						<html:hidden name="reqListId" property="lagacy1058Flag" /> <%--Added for CR-118--%>
						<logic:equal name="reqListId" property="lagacy1058Flag" value="N">
							<logic:notEmpty name="reqListId" property="seqNo1058" >
						  		<logic:notEqual name="SearchRequest1058Form" property="role"  value="ASM"> <%--Added for CR-126--%>
						   			<a href="javascript:fn1058Page(<bean:write name="reqListId" property="seqNo1058"/>)">
						   				<bean:write name="reqListId" property="id1058" />
						   			</a>
						  		</logic:notEqual>
								<%--Added for CR-126--%> 
					  			<logic:equal name="SearchRequest1058Form" property="role"  value="ASM">
					  				<bean:write name="reqListId" property="id1058" />
					  			</logic:equal>  
				  		<%--Added for CR-126 Ends Here--%>
							</logic:notEmpty>
							<logic:empty name="reqListId" property="seqNo1058" >
							   &nbsp;
							</logic:empty>
						</logic:equal>
						<logic:notEqual name="reqListId" property="lagacy1058Flag" value="N">
						    <%--Updated for CR-114 --%>
							<logic:notEmpty name="reqListId" property="seqNo1058" >
								<a href="<bean:write name="reqListId" property="lagacyFileLoc"/>" target="_blank">
									   		<font color="green"><bean:write name="reqListId" property="id1058" /></font>
								</a>
								<%--Addedd for CR-126 --%>
								<logic:notEqual name="SearchRequest1058Form" property="role"  value="ASM">
									<logic:equal name="SearchRequest1058Form" property="role"  value="ADMN">
									   <A HREF="javascript:fnEditLegacy('48',<bean:write name="reqListId" property="seqNo1058"/>)">
									   	<font color="green"><span class="glyphicon glyphicon-pencil" data-toggle="tooltip" aria-hidden="true" title="Edit Legacy"></span></font>
									   </A>
									</logic:equal>   
								</logic:notEqual>   
							   <%--Addedd for CR-126 Ends here--%>
						    </logic:notEmpty>
							<logic:empty name="reqListId" property="seqNo1058" >
							   &nbsp;
							</logic:empty>
							<%--Updated for CR-114 Ends here --%>
						</logic:notEqual>
					</TD>
					<TD CLASS="v-midalign">
						<logic:notEmpty name="reqListId" property="specRev" >
						   <bean:write name="reqListId" property="specRev" />
						</logic:notEmpty>
						<logic:empty name="reqListId" property="specRev" >
						   &nbsp;
						</logic:empty>
					</TD>
					<TD CLASS="v-midalign">
						<logic:notEmpty name="reqListId" property="genDesc" >
						   <bean:define name="reqListId" property="genDesc" id="genDesc" />
						   <%=(String.valueOf(genDesc)).replaceAll("\n","<br>")%>
						</logic:notEmpty>
						<logic:empty name="reqListId" property="genDesc" >
						   &nbsp;
						</logic:empty>
					</TD>
					<TD CLASS="v-midalign">
						<logic:notEmpty name="reqListId" property="statusDesc" >
						   <bean:write name="reqListId" property="statusDesc" />
						</logic:notEmpty>
						<logic:empty name="reqListId" property="statusDesc" >
						   &nbsp;
						</logic:empty>
					</TD>
					<logic:notEmpty name="reqListId" property="actualSellPrice" >
						<TD CLASS="v-midalign" data-sort="<bean:write name='reqListId' property='actualSellPrice' />" data-search="<bean:write name='reqListId' property='actualSellPrice' />">
						    <bean:define id="chkMinus" name="reqListId" property="actualSellPrice" />
						   	    <%= (String.valueOf(chkMinus).substring(0,1).equalsIgnoreCase("-")) 
							   	    	? "-$"+(String.valueOf(chkMinus).substring(1)):"$"+chkMinus%>
						</TD>		
					</logic:notEmpty>			
					<logic:empty name="reqListId" property="actualSellPrice" >
					   <TD CLASS="v-midalign" data-sort="" data-search="">&nbsp;</TD>
					</logic:empty>
					<TD CLASS="v-midalign">
						<logic:notEmpty name="reqListId" property="custName" >
						   <bean:write name="reqListId" property="custName" />
						</logic:notEmpty>
						<logic:empty name="reqListId" property="custName" >
						   &nbsp;
						</logic:empty>
					</TD>
					<TD CLASS="v-midalign">
						<logic:notEmpty name="reqListId" property="userName" >
						   <bean:write name="reqListId" property="userName" />
						</logic:notEmpty>
						<logic:empty name="reqListId" property="userName" >
						   &nbsp;
						</logic:empty>
					</TD>
				</TR>
			</logic:iterate>
			</tbody>
		</TABLE>
 		<div class="spacer10"></div>
		<div class="row">
			<div class="col-sm-12 text-center">
		         <div class="form-group">
		            <button class="btn btn-primary" type='button' id="modifyButton" ONCLICK="javascript:fnCreatePDF()">
		            	<bean:message key="Screen.Create.Pdf" />
		            </button>
		            <button class="btn btn-primary" type='button' id="modifyButton" ONCLICK="javascript:fnDelete()">
		            	<bean:message key="Screen.Delete.Request" />
		            </button>
		        </div>
	     	</div>
	    </div>
	   <div class="spacer10"></div>
	   <div class="row">
			<div class="col-sm-12 text-center">
		         <div class="form-group">
		            <button class="btn btn-primary" type='button' id="modifyButton" ONCLICK="javascript:fnViewSelectedSummaryReport('report')">
		            	<bean:message key="Screen.Selected.Summary.Report" />
		            </button>
		            <button class="btn btn-primary" type='button' id="modifyButton" ONCLICK="javascript:fnViewSelectedSummaryReport('excel')">
		            	<bean:message key="Screen.Export.Selected.Summary" />
		            </button>
		        </div>
	     	</div>
	    </div>
		
	</logic:greaterThan>
	<BR/>
	<html:hidden property="hdnSelectedCustomer" />
	<html:hidden property="hdnSelectedStatus" />
	<html:hidden property="hdnSelectedModel" />
	<html:hidden property="hdnSelectedAwApproval" />
	<html:hidden property="hdnorderNo" />
	<input type="hidden" name="flag" value="Y">
	<html:hidden property="role" styleId="role"/>
	<%--Added for CR-126 --%>
		<html:hidden property="editLegacyFlag" />
	<%--Added for CR-126 ends here --%>	
</html:form>
</div>