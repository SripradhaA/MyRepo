<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.EMD.LSDB.vo.common.SubSectionVO" %>
<SCRIPT LANGUAGE="JavaScript" SRC="js/ModifySpec.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/OrderSectionView.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/MasterSpecByMdl.js"></SCRIPT>

<style> 
#footer {
position:fixed;
left:0px;
bottom:0px;
height:55px;
width:100%;
background:#E3E4FA;  
padding-top:5px; //Added for CR-114 
overflow-x:auto; //Added for CR-114 
}    
/* IE 6 */  
* html #footer 
{
margin-top: -1px; 
position:absolute;
top:expression((0-(footer.offsetHeight)+(document.documentElement.clientHeight ? document.documentElement.clientHeight : document.body.clientHeight)+(ignoreMe = document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop))+'px');  
}
</style> 
<script type="text/javascript" >
$(document).ready(function() { 
   	  	$("#spcStatus").select2({theme:'bootstrap'});  
	})
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
	   
		div.style.left = curleft-110 ;
		//Changed after issue fix CR-74 VV49326 24-08-09
		div.style.top =  curtop-30 ;
		document.getElementById(divID).style.visibility="visible";
}

function fnMouseOut(divelem)
{
	 document.getElementById(divelem).style.visibility="hidden";
}
//Ends
function parseMessage(messages){
	var message = new Array();
		var allmessage = "999 - Please Select the Component for the following Sections<br>";
		var space;
		message = messages.split(",");
		for(var i = 0 ; i < message.length; i++){
			if(i==0){
				space = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			}else{
				space = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			}
			allmessage = allmessage+space+message[i]+"<br>";
		}
		
		return allmessage;
}
// Added for LSDB_CR_71 for Server Side Component validation
function parseComponentMessage(messages){
	var message = new Array();
		var allmessage = "The following Component Group (s) is not selected in the section<br><table><tr><td></td></tr></table>";
		var space;
		message = messages.split(",");
		for(var i = 0 ; i < message.length; i++){
			if(i==0){
				space = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				allmessage = allmessage+space+message[i];
			}else{
				space = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				allmessage = allmessage+"<br>"+space+message[i];
			}
			
		}
		return allmessage;
}

function fnOpenPartOf(SubSecSeqNo,OrderKey,SecSeqNo,ClauseSeqNo,VersionNo,RevCode,ParentSecSeqNo,PartOfClauseSeqNo)
{
	window.open("OrderPartofPopUpAction.do?method=fetchClauses&subSecSeqNo="+SubSecSeqNo+"&orderKey="+OrderKey+"&secSeqNo="+SecSeqNo+"&clauseSeqNo="+ClauseSeqNo+"&versionNo="+VersionNo+"&revCode="+RevCode+"&parentSecSeqNo="+ParentSecSeqNo+"&partOfClaSeqNo="+PartOfClauseSeqNo,'Clause','location=0,resizable=No ,status=0,scrollbars=1,WIDTH=850,height=600')

}

//Added for disabling the scroll in component dropdown
	if(window.document.attachEvent)
	{
		window.document.attachEvent('onmousewheel',stopMouseWheel);
	}
	function stopMouseWheel() 
	{
		if(window.event.srcElement.type)
		{
			if(window.event.srcElement.type == 'select-one')
			{
				return false;
			}
		}
	}
	
/*Added for CR-114 	
	$(document).ready(function() {
		var id;
    		$(".innerLI").mouseover(function(index) { 
    			$(".innerLI UL").hide();
    			id=$(this).attr('id');
    			
    			if($(this).position().left>630){
    			 $("#"+id).find('UL').css("left","-180px");
    			}
    			
    		   $("#"+id).find('UL').show();   
    		   
			});
			
			$(".innerLI UL").mouseout(function(index) {  
				//id=$(this).attr('id');
    		   //$("#"+id).find('UL').hide(); 
    		   $(this).hide(); 
    		   
			});
	});
//Added for CR-114  ends Here Commented for CR_131*/
</script>
<div class="container-fluid">
<html:form action="/OrderSection" method="post">	
		<input type="hidden" name="actionType">
		<input type="hidden" name="subSecCode" />
		<html:hidden property="hdnRevViewCode" />
			
		<h4 class="text-center"><bean:message key="Application.Screen.ModifySpec" /></h4>
		<div class="row">
			<div class="spacer10"></div>
		</div>
		<div class="errorlayerhide" id="errorlayer"></div>
		<logic:present name="OrderSectionForm" property="errorMessage"
			scope="request">
			<script type="text/javascript">
			hideErrors();
			message = parseMessage('<bean:write name="OrderSectionForm" property="errorMessage"/>');
			addMessage(message);
			showScrollErrors();
		</script>
		</logic:present>
	
		<!-- Ends here -->
	
		<!-- Added for LSDB_CR_71 -->
		<logic:present name="OrderSectionForm" property="compErrorMessage"
			scope="request">
			<script type="text/javascript">
			hideErrors();
			message = parseComponentMessage('<bean:write name="OrderSectionForm" property="compErrorMessage"/>');
			addMessageCommon(message,'N');
			showScrollErrors();
		</script>
		</logic:present>
		
		<!-- Ends here -->
	
		<logic:present name="subsecseqno" scope="request">
			<bean:define id="compmov" name="subsecseqno" scope="request" />
			<script>
				document.location.href="#<%=compmov%>";
			</script>
		</logic:present>
	
		<logic:present name="OrderSectionForm" property="messageID"
			scope="request">
				<bean:define id="messageID" name="OrderSectionForm" property="messageID"/>
	            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
	            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
		</logic:present>
	
		<logic:present name="OrderSectionForm" property="orderList"
			scope="request">
			<logic:iterate id="DisList" name="OrderSectionForm"
				property="orderList" type="com.EMD.LSDB.vo.common.OrderVO"
				scope="request">
				<input type="hidden" name="hdnorderno"
					value="<%=DisList.getOrderNo()%>">
				<input type="hidden" name="hdnmodseqno"
					value="<%=DisList.getModelSeqNo()%>">
					
					<div class="row">
						<div class="spacer20"></div>
					</div>
					
					<div class="form-horizontal bg-info">
					<div class="row">
						<label class="col-sm-offset-2 col-sm-2 control-label"><strong>Order Number :</strong></label>
						<div class="col-sm-2 form-control-static">
							<bean:write name="DisList" property="orderNo" /><html:hidden name="DisList"	property="orderNo" />
						</div>
						<label class="col-sm-2 control-label"><strong>Spec Status :</strong></label>
						<div class="col-sm-3 form-control-static">
							<bean:write name="DisList" property="statusDesc" />
						</div>
					</div>
					<div class="row">
						<label class="col-sm-offset-2 col-sm-2 control-label"><strong>Model Name :</strong></label>
						<div class="col-sm-2 form-control-static">
							<bean:write	name="DisList" property="modelName" />
						</div>
						<label class="col-sm-2 control-label"><strong>Custom Model Name :</strong></label>
						<div class="col-sm-3 form-control-static"><bean:write name="DisList" property="custMdlName" /></div>
					</div>
					<div class="row">
						<label class="col-sm-offset-2 col-sm-2 control-label"><strong>SAP Customer Name :</strong></label>
						<div class="col-sm-2 form-control-static"><bean:write name="DisList" property="sapCusCode" /></div>
						<label class="col-sm-2 control-label"><strong>Customer Name :</strong></label>
						<div class="col-sm-3 form-control-static"><bean:write name="DisList" property="customerName" /></div>
					</div>
				</div>
				
					<div class="row">
						<div class="spacer30"></div>
					</div>
	
					<logic:iterate id="cusList" name="DisList" property="customerVo"
						type="com.EMD.LSDB.vo.common.CustomerVO" scope="page">
							<input type="hidden" name="hdncusseqno"
							value="<%=cusList.getCustomerSeqNo()%>">
					</logic:iterate>
	
			</logic:iterate>
		</logic:present>
	
				<div class="row">
					<div class="col-sm-12 text-center">
						<a href="javascript:fnMainFeature()" class="linkstyleItem">
								General Information</a>&nbsp;&nbsp;|&nbsp;
						<logic:iterate id="section" name="OrderSectionForm"
							property="sectionList" type="com.EMD.LSDB.vo.common.SectionVO"
							scope="request">
							<bean:define id="revCode" name="OrderSectionForm" property="hdnRevViewCode" />
								<logic:notEqual name="section" property="colourFlag" value="Y"><a
										href="OrderSection.do?method=fetchSectionDetails&orderKey=<%=section.getOrderKey()%>&secSeq=<%=section.getSectionSeqNo()%>&secCode=<%=section.getSectionCode()%>&secName=<%=section.getSectionName()%>&revCode=<%=revCode%>"
										class="linkstyleItem"><bean:write name="section"
										property="sectionCode" />. <bean:write name="section"
										property="sectionName" /></a>&nbsp;&nbsp;|&nbsp;
								</logic:notEqual>
								<logic:equal name="section" property="colourFlag" value="Y">
									<a href="OrderSection.do?method=fetchSectionDetails&orderKey=<%=section.getOrderKey()%>&secSeq=<%=section.getSectionSeqNo()%>&secCode=<%=section.getSectionCode()%>&secName=<%=section.getSectionName()%>&revCode=<%=revCode%>"
									class="linkstyleItem"><font color="red"><bean:write
									name="section" property="sectionCode" />. <bean:write
									name="section" property="sectionName" /></font></a>&nbsp;&nbsp;|&nbsp;
							</logic:equal>
						</logic:iterate><a href="javascript:fnLoadAppendix()" class="linkstyleItem">
						Appendix</a><!--Added for LSDB_CR_46 PM&I Change -->
								<!-- Modified For CR_84 -->
								<logic:equal name="OrderSectionForm" property="perfCurveLinkFlag" value="Y">&nbsp;|&nbsp;
								<a href="javascript:fnLoadPerfCurve()" class="linkstyleItem">Performance Curve</a>&nbsp;
							</logic:equal>
					</div>
				</div>
				
				<div class="row">
					<div class="spacer10"></div>
				</div>
	
		<jsp:include page="OrderSectionViewClause.jsp" />
				
					<div class="row">
						<div class="col-sm-offset-1 col-sm-5">
						    <logic:present name="OrderSectionForm" property="orderList"
							scope="request">
							<logic:iterate id="DisList" name="OrderSectionForm"
								property="orderList" type="com.EMD.LSDB.vo.common.OrderVO"
								scope="request">
								<logic:equal name="DisList" property="appendixFlag" value="Y">
									<p class="form-control-static">Appendix: <mark>Turned On</mark></p>
								</logic:equal>
								<logic:equal name="DisList" property="appendixFlag" value="N">
									<p class="form-control-static">Appendix: <mark>Turned Off</mark></p>
								</logic:equal>
							</logic:iterate>
							</logic:present>
						</div>
						
						<div class="required-field">
							<label class="col-sm-1 control-label">Spec Status</label>									
							<div class="col-sm-1">
								<html:select name="OrderSectionForm" property="specStatusCode" styleClass="form-control" styleId="spcStatus">
											<option selected value="-1">---Select---</option>
										<html:optionsCollection name="OrderSectionForm"
											property="specStatus" value="specStatusCode" label="statusDesc" />
										</html:select>
							</div>
							<div class="col-sm-3">
					              <button class="btn btn-primary addbtn" type='button' id="PublishSpecButton" ONCLICK="javascript:fnValidatePublishComponent()">Publish Spec</button>
					              <button class="btn btn-primary addbtn" type='button' id="PublishSubLevelButton" ONCLICK="javascript:fnValidatePublishSubLevelComp()">Publish Sub level Revision</button>
							</div>
						</div>
						
					</div>
					
					<div class="row">
						<div class="spacer20"></div>
					</div>
					
					<div class="row">
						<div class="col-sm-4 text-right">
						<logic:equal name="DisList" property="pdfHeaderFlag" value="Y">
							<div class="row">
								<div class="text-right"><strong>Electro-Motive Logo : <mark>On</mark></strong>
									<button class="btn btn-primary btn-sm" type='button' name="btnTurnOff" ONCLICK="javascript:fnTurnPDFHeaderFlag('N')">Turn Off</button>
								</div>
							</div>
						</logic:equal>
						<logic:equal name="DisList" property="pdfHeaderFlag" value="N">
							<div class="row">
								<div class="text-right"><strong>Electro-Motive Logo : <mark>Off</mark></strong>
									<button class="btn btn-primary btn-sm" type='button' name="btnTurnOn" ONCLICK="javascript:fnTurnPDFHeaderFlag('Y')">Turn On</button>
								</div>
							</div>
						</logic:equal>
						
						<div class="row">
							<div class="spacer10"></div>
						</div>
						
						<logic:equal name="DisList" property="userMarkerFlag" value="Y">
							<div class="row">
								<div class="text-right"><strong>Preserve Clause Markers : <mark>On</mark></strong>
									<button class="btn btn-primary btn-sm" type='button' id="TurnPreserveUserMarkersOnOffButton" ONCLICK="javascript:fnTurnUserMarkerFlag('N')">Turn Off</button>
								</div>
							</div>
						</logic:equal>
						<logic:equal name="DisList" property="userMarkerFlag" value="N">	
							<div class="row">
								<div class="text-right"><strong>Preserve Clause Markers : <mark>Off</mark></strong>
									<button class="btn btn-primary btn-sm" type='button' id="TurnPreserveUserMarkersOnOffButton" ONCLICK="javascript:fnTurnUserMarkerFlag('Y')">Turn On</button>
								</div>
							</div>
						</logic:equal>
						<div class="row">
							<div class="spacer10"></div>
						</div>
						</div>
						
						<div class="col-sm-4 col-sm-offset-1 text-left">
						<logic:equal name="DisList" property="custLogoFlag" value="Y">
							<div class="row">
								<div class="text-right"><strong>Customer Logo  : <mark>On</mark></strong>
									<button class="btn btn-primary btn-sm" type='button' id="CustomerOnOffButton" ONCLICK="javascript:fnTurnCustomerLogoFlag('N')">Turn Off</button>
								</div>
							</div>
						</logic:equal>
						<logic:equal name="DisList" property="custLogoFlag" value="N">	
							<div class="row">
								<div class="text-right"><strong>Customer Logo : <mark>Off</mark></strong>
									<button class="btn btn-primary btn-sm" type='button' id="TurnCustomerOnOffButton" ONCLICK="javascript:fnTurnCustomerLogoFlag('Y')">Turn On</button>
								</div>
							</div>
						</logic:equal>
						
						<div class="row">
							<div class="spacer10"></div>
						</div>
						
						<logic:equal name="DisList" property="specTypeSeqNo" value="2">
		
							<logic:equal name="DisList" property="distriLogoFlag" value="Y">
							<div class="row">
								<div class="text-right"><strong> Distributor Logo  : <mark>On</mark></strong>
									<button class="btn btn-primary btn-sm" type='button' id="TurnDistributorOnOffButton" ONCLICK="javascript:fnTurnDistributorLogoFlag('N')">Turn Off</button>
								</div>
							</div>		
							</logic:equal>
							<logic:equal name="DisList" property="distriLogoFlag" value="N">		
							<div class="row">
								<div class="text-right"><strong> Distributor Logo  : <mark>Off</mark></strong>
									<button class="btn btn-primary btn-sm" type='button' id="TurnDistributorOnOffButton" ONCLICK="javascript:fnTurnDistributorLogoFlag('Y')">Turn On</button>
								</div>
							</div>
							</logic:equal>	
						
						</logic:equal>	
						<logic:notEqual name="DisList" property="specTypeSeqNo" value="2">
							<logic:equal name="DisList" property="dynamicNoFlag" value="Y">
								<div class="row">
									<div class="text-right"><strong>Dynamic Clause Numbering : <mark>On</mark></strong>
										<button class="btn btn-primary btn-sm" type='button' id="TurnDynamicOnOffButton" ONCLICK="javascript:fnTurnDynamicNumOnOffFlag('N')">Turn Off</button>
									</div>
								</div>
							</logic:equal>
							<logic:equal name="DisList" property="dynamicNoFlag" value="N">	
								<div class="row">
									<div class="text-right"><strong>Dynamic Clause Numbering : <mark>Off</mark></strong>
										<button class="btn btn-primary btn-sm" type='button' id="TurnDynamicOnOffButton" ONCLICK="javascript:fnTurnDynamicNumOnOffFlag('Y')">Turn On</button>
									</div>
								</div>
							</logic:equal>
						</logic:notEqual>
						<div class="row">
							<div class="spacer30"></div>
						</div>
						</div>
					</div>
					
						
					
					<%int i=0; %>	
					<div>
						<div class="row">
							<div class="col-sm-offset-3 col-sm-3">
								<a href="javascript:fnGenerateProofReadingEnggDraft()" class="linkstyleItem">
									<span class="glyphicon glyphicon glyphicon-save" aria-hidden="true"></span> View Proofreading Engr Spec
								</a>
							</div>
							<div class="col-sm-offset-1 col-sm-3">
								<a href="javascript:fnGenerateProofReadingMarkedClauses()" class="linkstyleItem">
									<span class="glyphicon glyphicon glyphicon-save" aria-hidden="true"></span> View Marked Clause(s)
								</a>
							</div>
						</div>
						<div class="row"><div class="spacer10"></div></div>
						<div class="row">
							<div class="col-sm-offset-3 col-sm-3 ">
								<a href="javascript:fnGenerateProofReadingSalesDraft()" class="linkstyleItem">
									<span class="glyphicon glyphicon glyphicon-save" aria-hidden="true"></span> View Proofreading Sales Spec
								</a>
							</div>
							<div class="col-sm-offset-1 col-sm-3">
								<a href="javascript:fnViewCurrentEDLNumbers()" class="linkstyleItem">
									<span class="glyphicon glyphicon-new-window" aria-hidden="true"></span> View Current EDL Number(s)
								</a>
							</div>
						</div>
						<div class="row"><div class="spacer10"></div></div>
						<div class="row">
							<div class="col-sm-offset-3 col-sm-3 ">
								<a href="javascript:fnGenerateProofReadingEngrSupplement()" class="linkstyleItem">
									<span class="glyphicon glyphicon glyphicon-save" aria-hidden="true"></span> View Proofreading Engr Supplement
								</a>
							</div>
							<div class="col-sm-offset-1 col-sm-3">
								<a href="javascript:fnViewSpecForAll()" class="linkstyleItem">
									<span class="glyphicon glyphicon-new-window" aria-hidden="true"></span> View Master Spec
								</a>
							</div>
						</div>
						<div class="row"><div class="spacer10"></div></div>
						<div class="row">
							<div class="col-sm-offset-3 col-sm-3 ">
								<a href="javascript:fnGenerateProofReadingSalesSupplement()" class="linkstyleItem">
									<span class="glyphicon glyphicon glyphicon-save" aria-hidden="true"></span> View Proofreading Sales Supplement
								</a>
							</div>
							<logic:iterate id="linkViewColor" name="OrderSectionForm" property="sectionList" type="com.EMD.LSDB.vo.common.SectionVO" scope="request">
								<% if(i==0){ %>
											<logic:equal name="linkViewColor" property="linkColourFlag" value="Y">
											<div class="col-sm-offset-1 col-sm-3">
												<a href="javascript:fnDeletedClausesHistory()" class="linkstyleItem">
													<span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span><font color="red"> View Deleted Clause(s) History</font>
												</a>
											</div>
											<div class="spacer10"></div>
											</logic:equal>
											<logic:notEqual name="linkViewColor" property="linkColourFlag" value="Y">
											<div class="col-sm-offset-1 col-sm-3">
												<a href="javascript:fnDeletedClausesHistory()" class="linkstyleItem">
													<span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span> View Deleted Clause(s) History
												</a>
											</div>
											<div class="spacer10"></div>
											</logic:notEqual>
								<%i=i+1;%>
										<%} %>			
							</logic:iterate>
						</div>
						<div class="row"><div class="spacer10"></div></div>
						<div class="row">
						<!-- Added for CR-135 starts -->
							<div class="col-sm-offset-3 col-sm-3 ">
								<a href="javascript:fnOrderVsModelDelta('<bean:write name="DisList" property="modelName"/>','<bean:write name="DisList" property="modelSeqNo"/>','<bean:write name="DisList" property="statusDesc"/>','<bean:write name="DisList" property="orderKey"/>','<bean:write name="DisList" property="orderNo"/>','<bean:write name="DisList" property="customerName"/>','W')" class="linkstyleItem">
									<span class="glyphicon glyphicon-new-window" aria-hidden="true"></span> View Order vs Master Model Delta
								</a>
							</div>
								<!-- Added for CR-135 ends -->
							<div class="col-sm-offset-1 col-sm-3 ">
								<a href="javascript:fnClauseswithIndicators()" class="linkstyleItem">
									<span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span> View Clause(s) with Indicators
								</a>
							</div>
						</div>
						<div class="row"><div class="spacer10"></div></div>
						<div class="row">
							<div class="col-sm-offset-3 col-sm-3 ">
								<html:hidden name="OrderSectionForm" property="roleID"/>
									<logic:equal name="OrderSectionForm" property="roleID" value="ADMN">
										<a href="javascript:fnRemoveOptionalClauses()" class="linkstyleItem text-red">
											<span class="glyphicon glyphicon-remove-circle text-red" aria-hidden="true"></span> Remove Optional Clauses
										</a>
									</logic:equal>
									<logic:notEqual name="OrderSectionForm" property="roleID" value="ADMN">
										<span>&nbsp;</span>
									</logic:notEqual>	
							</div>
							<div class="col-sm-offset-1 col-sm-3">
								<a href="javascript:exportSpectoDoors('<bean:write name="DisList" property="modelName"/>','<bean:write name="DisList" property="modelSeqNo"/>','<bean:write name="DisList" property="specTypeName"/>','<bean:write name="DisList" property="orderKey"/>','<bean:write name="DisList" property="orderNo"/>','W')" class="linkstyleItem">
									<span class="glyphicon glyphicon glyphicon-save" aria-hidden="true"></span> Export Order to DOORs
								</a>
							</div>
						</div>
						<div class="row"><div class="spacer50"></div></div>
				</div>
	
		<input type="hidden" name="specTypeSeqNo" value='<bean:write name="DisList" property="specTypeSeqNo" />' />
		<input type="hidden" name="modelSeqNo" value='<bean:write name="DisList" property="modelSeqNo" />' />
		<input type="hidden" name="specTypeName" value='<bean:write	name="DisList" property="specTypeName" />' />
		<input type="hidden" name="modelName" value='<bean:write name="DisList" property="modelName" />' />
		<input type="hidden" name="statusDesc" value='<bean:write name="DisList" property="statusDesc" />' />
		<input type="hidden" name="customerName" value='<bean:write	name="DisList" property="customerName" />' />
		<!-- CR_104-->
	  	<input type="hidden" name="custMdlName" value='<bean:write name="DisList" property="custMdlName" />' />.
	  	
	  	<%-- Added for CR_97 --%>
		<logic:notEqual name="OrderSectionForm" property="newReqID" value="0">
			<div class="modal fade" id="hiddenReqID" tabindex="-1" role="dialog" aria-labelledby="changeReqNewComp">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				      	<h4 class="modal-title" id="changeReqNewComp">New Component added to Order</h4>
				      </div>
				      <div class="modal-body">					
							<p class="text-center">	
								The Change Request Form <a href="javascript:fnGetRequest('<bean:write name="OrderSectionForm" property="newReqID"/>')"><bean:write name="OrderSectionForm" property="newReqID"/></a> has been submitted.
							<p>
					  </div>
					  <div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					  </div>
					</div>
				</div>
			</div>
		</logic:notEqual>
	
		<%-- CR_97 Ends here--%>
	  	
	  	<DIV ID='replaceClause' style="display:none;">
			<TABLE BORDER=0 WIDTH="80%" ALIGN="center" >
				<TR>
					<TD>&nbsp;</TD>
				</TR>
				<TR>
				<TD WIDTH="8%" CLASS="headerbgcolor" colspan=2 ALIGN="center">Enter Clause Description for Sales Spec<br/><br/>
						 <CENTER><textarea id="Add_Sales_Version_id" name="textAreaSalesVerDesc"
								ROWS="5" COLS="100" class="CLAUSEDESCTEXTAREA"></textarea>
								</CENTER>
					</TD>
				</TR>
				
			
			
				<TR>
					<TD>&nbsp;</TD>
				</TR>
			
				<TR>
					<TD colspan=2 align=center>
						<input type='button' class="buttonStyle" value="Save" id="SaveSalesVer"></input>&nbsp;&nbsp;&nbsp;&nbsp;
						<input type='button' class="buttonStyle" value="Close" onclick="$.modal.close()"><br/></input>
					</TD>
				</TR>
			
			</TABLE>
		</DIV>
		
		
	<html:hidden property="orderKey" />
	<html:hidden property="orderSecSeqNo" />
	<!-- <html:hidden property="orderSecCode"/>
	<html:hidden property="orderSecName"/> -->
	<html:hidden property="revFlag" />
	<html:hidden property="finalFlag" />
	<html:hidden property="currentSpecStatus" />
	<!-- Added For CR_84 -->
	<html:hidden property="perfCurveLinkFlag"/>
	
	<html:hidden property="salesVerDescription" />
	<!-- Added For CR_104 -->
	<html:hidden property="subject" />
	<html:hidden property="bodyCont" />
	
	
	<nav class="navbar navbar-default navbar-fixed-bottom navbar-another">
		 <div class="navbar-header">
	 	  <!-- For smaller displays -->
	 	  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#lsdb-sectionmenu" aria-expanded="false">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
    	</div>
		<div class="collapse navbar-collapse" id="lsdb-sectionmenu">
			<ul class="nav navbar-nav">
				<%  HttpSession sess=null;
					String strSelectedSection="";
					ArrayList arlSubSectionValues;
				%>
				<li class="dropdown" id="genInfo">
					<a href="javascript:fnMainFeature()" class="linkstyleItem">General Information</a>
				</li>
				<logic:iterate id="section" name="OrderSectionForm" property="sectionList" 
					type="com.EMD.LSDB.vo.common.SectionVO" scope="request">
					<logic:notEqual name="section" property="colourFlag" value="Y">
						<%sess = request.getSession(true);
						strSelectedSection = section.getSectionCode()+ ". " + section.getSectionName();
						if (strSelectedSection.equalsIgnoreCase(sess
								.getAttribute("currSectionValue").toString())) {%>
							<li class="dropdown" id="<%=section.getSectionSeqNo()%>"><a
								href="OrderSection.do?method=fetchSectionDetails&orderKey=<%=section.getOrderKey()%>&secSeq=<%=section.getSectionSeqNo()%>&revCode=<%=section.getRevisionInput()%>"
								Class="linkstyleItem"><span class="dropup"><span class="caret"></span></span> <bean:write name="section"
								property="sectionCode" />. <bean:write name="section"
								property="sectionName" /></a>
								<%}
						else{
							%>
							<li class="dropdown" id="<%=section.getSectionSeqNo()%>"><a
								href="OrderSection.do?method=fetchSectionDetails&orderKey=<%=section.getOrderKey()%>&secSeq=<%=section.getSectionSeqNo()%>&revCode=<%=section.getRevisionInput()%>"><bean:write name="section"
								property="sectionCode" />. <bean:write name="section"
								property="sectionName" /></a>
								<%}
								if (strSelectedSection.equalsIgnoreCase(sess
									.getAttribute("currSectionValue").toString())) {
									arlSubSectionValues = (ArrayList) sess
									.getAttribute("currSubSectionValues");
								%>
							<ul class="dropdown-menu">							
								<% Iterator itr1 = arlSubSectionValues.iterator();
										while (itr1.hasNext()) {
											SubSectionVO objSubSectionVO =(SubSectionVO)itr1.next();
											String strSubsecValue=objSubSectionVO.getSubSecName(); %>
								<li><A HREF="<%out.print("#"+strSubsecValue);%>"><%=strSubsecValue%></A></li>
								<%}
								%>
							</ul>
								<%}	%>
								</li>
					</logic:notEqual>
					<logic:equal name="section" property="colourFlag" value="Y">
					<%sess = request.getSession(true);
					strSelectedSection = section.getSectionCode()+ ". " + section.getSectionName();
					if (strSelectedSection.equalsIgnoreCase(sess
							.getAttribute("currSectionValue").toString())) {
					%>
						<li class="dropdown" id="<%=section.getSectionSeqNo()%>"> <a
							href="OrderSection.do?method=fetchSectionDetails&orderKey=<%=section.getOrderKey()%>&secSeq=<%=section.getSectionSeqNo()%>&revCode=<%=section.getRevisionInput()%>"><mark><font color="red"><span class="dropup"><span class="caret"></span></span> <bean:write
							name="section" property="sectionCode" />. <bean:write
							name="section" property="sectionName" /></font></mark></a>
							<%}
						else{
						%>
						<li class="dropdown" id="<%=section.getSectionSeqNo()%>"><a
							href="OrderSection.do?method=fetchSectionDetails&orderKey=<%=section.getOrderKey()%>&secSeq=<%=section.getSectionSeqNo()%>&revCode=<%=section.getRevisionInput()%>"
							Class="linkstyleItem"><mark><font color="red"><bean:write
							name="section" property="sectionCode" />. <bean:write
							name="section" property="sectionName" /></font></mark></a>
							<%}
							if (strSelectedSection.equalsIgnoreCase(sess
								.getAttribute("currSectionValue").toString())) {
								arlSubSectionValues = (ArrayList) sess
								.getAttribute("currSubSectionValues");
									%>
								<ul class="dropdown-menu">
									<%	
									Iterator itr1 = arlSubSectionValues.iterator();
									while (itr1.hasNext()) {
										SubSectionVO objSubSectionVO =(SubSectionVO)itr1.next();
										String strSubsecValue=objSubSectionVO.getSubSecName();
										if(objSubSectionVO.isIndicatedSubSec()){
								%>
							<li><A HREF="<%out.print("#"+strSubsecValue);%>" CLASS=linkstyleItem><mark><font color="red"><%=strSubsecValue%></font></mark></A></li>
								<%}
									else{
										%>
										<li><A HREF="<%out.print("#"+strSubsecValue);%>" CLASS=linkstyleItem><%=strSubsecValue%></A></li>
										<% 
									}
								}
							%>
						</ul>
							<%}	%>
						</li>
					</logic:equal>
				</logic:iterate>
				<li class="dropdown" id="Appimg"><a	href="javascript:fnLoadAppendix()">Appendix</a></li>
				<logic:present name="OrderSectionForm" property="orderList"
					scope="request">
					<logic:iterate id="DisList" name="OrderSectionForm"
						property="orderList" type="com.EMD.LSDB.vo.common.OrderVO"
						scope="request">
						<logic:equal name="OrderSectionForm"
							property="perfCurveLinkFlag" value="Y">
							<li class="dropdown" id="pCurve"><a
								href="javascript:fnLoadPerfCurve()" class="linkstyleItem">Performance
							Curve</a></li>
						</logic:equal>
					</logic:iterate>
				</logic:present>
			</ul>
		</div>
	</nav>
	<%--
		<DIV id='footer' width="100%" align="center">


					<ul class="footmenu" ALIGN="center">
					<% HttpSession sess=null;
					String strSelectedSection="";
					ArrayList arlSubSectionValues;
					%>
						<li class="innerLI" id="genInfo"><a
							href="javascript:fnMainFeature()" class="linkstyleItem"> General
						Information</a>&nbsp;&nbsp;|&nbsp;
						

						</li>
						<logic:iterate id="section" name="OrderSectionForm"
							property="sectionList" type="com.EMD.LSDB.vo.common.SectionVO"
							scope="request">
							<logic:notEqual name="section" property="colourFlag" value="Y">
							
							<%sess = request.getSession(true);
							strSelectedSection = section.getSectionCode()+ ". " + section.getSectionName();
							if (strSelectedSection.equalsIgnoreCase(sess
									.getAttribute("currSectionValue").toString())) {
							%>
								<li class="innerLI" id="<%=section.getSectionSeqNo()%>"><a
									href="OrderSection.do?method=fetchSectionDetails&orderKey=<%=section.getOrderKey()%>&secSeq=<%=section.getSectionSeqNo()%>&revCode=<%=section.getRevisionInput()%>"
									Class="linkstyleItem">^<bean:write name="section"
									property="sectionCode" />. <bean:write name="section"
									property="sectionName" /></a>&nbsp;&nbsp;|&nbsp; 
									<%}
							else{
								%>
								<li class="innerLI" id="<%=section.getSectionSeqNo()%>"><a
									href="OrderSection.do?method=fetchSectionDetails&orderKey=<%=section.getOrderKey()%>&secSeq=<%=section.getSectionSeqNo()%>&revCode=<%=section.getRevisionInput()%>"
									Class="linkstyleItem"><bean:write name="section"
									property="sectionCode" />. <bean:write name="section"
									property="sectionName" /></a>&nbsp;&nbsp;|&nbsp; 
								
								
									<%
									
							}
									if (strSelectedSection.equalsIgnoreCase(sess
										.getAttribute("currSectionValue").toString())) {
										arlSubSectionValues = (ArrayList) sess
										.getAttribute("currSubSectionValues");
										
										if(arlSubSectionValues.size()>19){
									%>
								<ul style="height=400px">
								<%}else{ %>
								<ul >
								
									<%}
											Iterator itr1 = arlSubSectionValues.iterator();
											while (itr1.hasNext()) {
												SubSectionVO objSubSectionVO =(SubSectionVO)itr1.next();
												String strSubsecValue=objSubSectionVO.getSubSecName();
												
									%>
									
									
									<li><A HREF="<%out.print("#"+strSubsecValue);%>" CLASS=linkstyleItem><%=strSubsecValue%></A>&nbsp;</li>
									<%}
											
									%>
								</ul>
									<%}	%>
									</li>
							</logic:notEqual>
							<logic:equal name="section" property="colourFlag" value="Y">
							<%sess = request.getSession(true);
							strSelectedSection = section.getSectionCode()+ ". " + section.getSectionName();
							if (strSelectedSection.equalsIgnoreCase(sess
									.getAttribute("currSectionValue").toString())) {
							%>
								<li class="innerLI" id="<%=section.getSectionSeqNo()%>"> <a
									href="OrderSection.do?method=fetchSectionDetails&orderKey=<%=section.getOrderKey()%>&secSeq=<%=section.getSectionSeqNo()%>&revCode=<%=section.getRevisionInput()%>"
									Class="linkstyleItem"><font color="red">^<bean:write
									name="section" property="sectionCode" />. <bean:write
									name="section" property="sectionName" /></font></a>&nbsp;&nbsp;|&nbsp;
									<%}
								else{
								%>
								<li class="innerLI" id="<%=section.getSectionSeqNo()%>"><a
									href="OrderSection.do?method=fetchSectionDetails&orderKey=<%=section.getOrderKey()%>&secSeq=<%=section.getSectionSeqNo()%>&revCode=<%=section.getRevisionInput()%>"
									Class="linkstyleItem"><font color="red"><bean:write
									name="section" property="sectionCode" />. <bean:write
									name="section" property="sectionName" /></font></a>&nbsp;&nbsp;|&nbsp;
								
								
									
									<%}
									if (strSelectedSection.equalsIgnoreCase(sess
										.getAttribute("currSectionValue").toString())) {
										arlSubSectionValues = (ArrayList) sess
										.getAttribute("currSubSectionValues");
										if(arlSubSectionValues.size()>19){
											%>
										<ul style="height=400px">
										<%}else{ %>
										<ul >
										
									<%	}
											Iterator itr1 = arlSubSectionValues.iterator();
											while (itr1.hasNext()) {
												SubSectionVO objSubSectionVO =(SubSectionVO)itr1.next();
												String strSubsecValue=objSubSectionVO.getSubSecName();
												if(objSubSectionVO.isIndicatedSubSec()){
									%>
									<li><A HREF="<%out.print("#"+strSubsecValue);%>" CLASS=linkstyleItem><font color="red"><%=strSubsecValue%></font></A>&nbsp;</li>
									<%}
												else{
													%>
													<li><A HREF="<%out.print("#"+strSubsecValue);%>" CLASS=linkstyleItem><%=strSubsecValue%></A></li>
													<% 
												}
											}
									%>
								</ul>
									<%}	%>
									
								</li>
								
								
							</logic:equal>
						</logic:iterate>
						<li class="innerLI" id="Appimg"><a
							href="javascript:fnLoadAppendix()" class="linkstyleItem">Appendix</a>
						
						</li>

						<!-- Added for LSDB_CR_46 PM&I Change-->
						<logic:present name="OrderSectionForm" property="orderList"
							scope="request">
							<logic:iterate id="DisList" name="OrderSectionForm"
								property="orderList" type="com.EMD.LSDB.vo.common.OrderVO"
								scope="request">
								<!-- Modified For CR_84 -->
								<logic:equal name="OrderSectionForm"
									property="perfCurveLinkFlag" value="Y">
					&nbsp;|&nbsp;
				 	<!-- <html:link action="orderPerfCurveAction.do?method=initLoad" paramId="orderKeyId" paramName="OrderSectionForm" paramProperty="orderKey" Styleclass="linkstyleItem">
				       Performance Curve
					</html:link> -->
									<li class="innerLI" id="pCurve"><a
										href="javascript:fnLoadPerfCurve()" class="linkstyleItem">Performance
									Curve</a>&nbsp;
									

									</li>
								</logic:equal>
							</logic:iterate>
						</logic:present>
					</ul>


					  <ul class="footmenu">
	
		<li class="innerLI" id="innerLI1"><A HREF='CompInfoByOrder.HTML' CLASS=linkstyleItem >General Info</A>&nbsp;|</li >
		<li class="innerLI" id="innerLI2"><A HREF='SectionView.HTML' CLASS=linkstyleItem ><font color="red">A.Air System</font></A>&nbsp;|
			<ul class="innerUL">
				<li ><A HREF='#' CLASS=linkstyleItem>A.1 Airbrake system Hardware and Software </A>&nbsp;</li>
				<li ><A HREF='#' CLASS=linkstyleItem>A.2 Distributed Power Hardware</A>&nbsp;</li>
	
			</ul>
		</li>
	
		</ul>
		--%>
		
		<div class="row">
			<div class="spacer30"></div>
		</div>
</html:form>
</div>