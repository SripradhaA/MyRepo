<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT type="text/javascript" SRC="js/MainFeatureInfo.js"></SCRIPT>
<SCRIPT type="text/javascript" SRC="js/MasterSpecByMdl.js"></SCRIPT>
<SCRIPT type="text/javascript" SRC="js/Others/jquery.tablednd.js"></SCRIPT>
<script>
    $(document).ready(function() { 
   	  	$("#spcStatus").select2({theme:'bootstrap'});  
	})
</script>
<div class="container-fluid" >
<html:form  action="/MainFeatureInfo" method="post">
		<h4 class="text-center"><bean:message key="Application.Screen.ModifySpec" /></h4>
		<div class="row">
			<div class="spacer10"></div>
		</div>
		<div class="errorlayerhide" id="errorlayer"></div>
		
		
		<logic:present name="MainFeatureForm" property="messageID"
		scope="request">
			<bean:define id="messageID" name="MainFeatureForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
		</logic:present>

		<logic:notPresent name="MainFeatureForm" property="messageID"
		scope="request">
			<logic:present name="MainFeatureForm" property="actionType"
				scope="request">
				<logic:equal name="MainFeatureForm" property="actionType"
					value="Publish">
					<script>	
					hideErrors();
					addMsgNum('230');
					showErrors();
				</script>
				</logic:equal>
		
				<logic:equal name="MainFeatureForm" property="actionType"
					value="PublishSub">
					<script>	
					hideErrors();
					addMsgNum('231');
					showErrors();
				</script>
				</logic:equal>
			</logic:present>
		</logic:notPresent>
		
		<!-- Logic Tag Check For Display The Records After Add and Modify Functionality Starts Here -->
			<logic:present name="MainFeatureForm" property="mainFeatureList"
				scope="request">
				<bean:size name="MainFeatureForm" id="compsize"
					property="mainFeatureList" />
			</logic:present>
			<logic:present name="MainFeatureForm" property="modelMainFeatureList"
				scope="request">
				<bean:size name="MainFeatureForm" id="modelCompDescsize"
					property="modelMainFeatureList" />
			</logic:present>
			
			<logic:present name="MainFeatureForm" property="orderList"
				scope="request">
				<logic:iterate id="DisList" name="MainFeatureForm"
						property="orderList" type="com.EMD.LSDB.vo.common.OrderVO"
						scope="request">
			<!-- Logic Tag Check For Display The Records After Add and Modify Functionality Ends Here -->
				<input type="hidden" name="specTypeSeqNo" value='<bean:write	name="DisList" property="specTypeSeqNo" />' />
				<input type="hidden" name="modelSeqNo" value='<bean:write	name="DisList" property="modelSeqNo" />' />
				<input type="hidden" name="specTypeName" value='<bean:write	name="DisList" property="specTypeName" />' />
				<input type="hidden" name="modelName" value='<bean:write	name="DisList" property="modelName" />' />
				<input type="hidden" name="statusDesc" value='<bean:write name="DisList" property="statusDesc" />' />
				<input type="hidden" name="customerName" value='<bean:write	name="DisList" property="customerName" />' />
				<input type="hidden" name="custMdlName" value='<bean:write	name="DisList" property="custMdlName" />' />
				
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
						<div class="col-sm-3 form-control-static"><bean:write name="DisList" property="custMdlName" /></div><html:hidden name="DisList"	property="custMdlName" />
					</div>
					<div class="row">
						<label class="col-sm-offset-2 col-sm-2 control-label"><strong>SAP Customer Name :</strong></label>
						<div class="col-sm-2 form-control-static"><bean:write name="DisList" property="sapCusCode" /></div>
						<label class="col-sm-2 control-label"><strong>Customer Name :</strong></label>
						<div class="col-sm-3 form-control-static"><bean:write name="DisList" property="customerName" /></div>
					</div>
				</div>
			
				</logic:iterate>
			</logic:present>
			
			<div class="row">
				<div class="spacer20"></div>
			</div>
			
			<logic:present name="MainFeatureForm" property="specStatus"
				scope="request">
				<logic:notEqual name="MainFeatureForm" property="specStatus" value="5">
		
					<html:hidden property="orderKey" />
					<html:hidden property="specStatus" />
					<html:hidden property="orderSecSeqNo" />
					
					<div class="row">
						<div class="col-sm-12 text-center">
							<a href="javascript:fnMainFeature()" class="linkstyleItem">
									General Information</a>&nbsp;&nbsp;|&nbsp;
							<logic:iterate id="section" name="MainFeatureForm"
								property="sectionList" type="com.EMD.LSDB.vo.common.SectionVO"
								scope="request">
								<bean:define id="revCode" name="MainFeatureForm" property="hdnRevViewCode" />
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
									<logic:equal name="MainFeatureForm" property="perfCurveLinkFlag" value="Y">&nbsp;|&nbsp;
									<a href="javascript:fnLoadPerfCurve()" class="linkstyleItem">Performance Curve</a>&nbsp;
								</logic:equal>
						</div>
					</div>
								
					<div class="row">
						<div class="spacer10"></div>
					</div>
								
					<div class="row">
						<div class="col-md-12 highlightDark text-center">
							<strong>General Info</strong>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-12 text-center">
							<a href="javascript:fnMainFeature()" class="linkstyleItem">
					 			Main Features Information</a>&nbsp;|&nbsp;
					 		<a href="javascript:fnShowSpecification()" class="linkstyleItem">
								Specifications</a>
							<logic:equal name="MainFeatureForm" property="genArrLinkFlag" value="Y">&nbsp;|&nbsp;&nbsp;
								<a href="javascript:fnShowGeneralArrangement()" class="linkstyleItem">
									General	Arrangement</a>&nbsp;&nbsp;
							</logic:equal>
						</div>
					</div>
					
					<div class="row">
						<div class="spacer10"></div>
					</div>
								
					<logic:iterate id="DisList" name="MainFeatureForm"
						property="orderList" type="com.EMD.LSDB.vo.common.OrderVO" scope="request">
						<logic:notEmpty name="DisList" property="genInfoText">
						<div class="panel panel-info">
							<div class="panel-heading">
								<h5 class="panel-title text-center">
									General Information Text
								</h5>
							</div>
							<div class="panel-body">
								<logic:equal name="DisList" property="specStatusCode" value="2">
								<div class="text-right">
										<html:hidden name="MainFeatureForm" property="presrveGenInfoFlag"/>
										<label class="checkbox-inline">
											<logic:equal name="DisList" property="presrveGenInfoFlag" value="Y">
													<input type="checkbox" name="presrveGenFlag" checked/><strong>Preserve text for Baseline Status</strong>
											</logic:equal>	
											<logic:equal name="DisList" property="presrveGenInfoFlag" value="N">
												<input type="checkbox" name="presrveGenFlag"/><strong>Preserve text for Baseline Status</strong>
											</logic:equal>
										</label>
								</div>
								<div class="spacer10"></div>
								</logic:equal>
								<html:textarea styleClass="form-control" property="genInfoText" name="DisList" rows="5"/>
								<div class="spacer10"></div>
								<div class="form-group">
									<div class="col-sm-12 text-center">
										<button class="btn btn-primary savebtn" type='button' ONCLICK="javascript:fnSaveGenInfoText()">Save</button>
									</div>
								</div>
							</div>
						</div>
						<HR>
						</logic:notEmpty>
					</logic:iterate>	
					
					<div class="row">
						<div class="col-sm-12 text-center">
							<div id="rearrInfo" >
								<bean:message key="RearrangeClickInfo" />
							</div>
							<div style="display:none;" id="rearrInfo1">
								<bean:message key="RearrangeInfo" />
							</div>
						</div>
					</div>		
					
					<div class="row">
						<div class="spacer10"></div>
					</div>			
					
					<TABLE class="table table-bordered" id="compDetails">
						<thead>
					        <tr>
					            <th class="text-center" width="3%">Select</th>
					            <th class="text-center" width="8%">Revision No</th>
					            <th class="text-center" width="60%">Main Features Information</th>
					            <th class="text-center" width="3%"><button class="btn btn-primary btn-sm" type='button' id="addButton" ONCLICK="javascript:fnAdd()">Add</button></th>
					        </tr>
					    </thead>
						
						<tbody class="text-center">	
						<logic:greaterThan name="compsize" value="0">
							<logic:iterate id="MainFeature" name="MainFeatureForm" property="mainFeatureList"
								type="com.EMD.LSDB.vo.common.MainFeatureInfoVO" scope="request">
								<TR id="<%= String.valueOf(MainFeature.getCompinfoSeqNo())%>">
									<TD id="compSeqNo" width="3%"><html:radio	property="compinfoSeqNo"
											value='<%= String.valueOf(MainFeature.getCompinfoSeqNo())%>' />
									</TD>
									
									<TD width="8%">
										<logic:equal name="MainFeature" property="sysMarkFlag" value="Y">
											<h4><span class="label label-primary"><bean:write name="MainFeature" property="sysMarkDesc"/></span></h4>
										</logic:equal>
									
									<logic:notEmpty name="MainFeature" property="revMarkers">
										<logic:iterate id="revMarker" name="MainFeature"  property="revMarkers">
											<bean:write name="revMarker" /><BR>
										</logic:iterate>
									</logic:notEmpty>
									</TD>
									
									<TD colspan=2 class="form-group">
										<html:text name="MainFeature" styleClass="form-control"
											property="componentDesc" size="150" maxlength="2000" />
									</TD>
								</TR>
							
							</logic:iterate>
						</logic:greaterThan>
						</tbody>
					</TABLE>	
					
					<logic:greaterThan name="compsize" value="0">
					<div class="row">
						<div class="form-group">
					        <div class="col-sm-offset-5 col-sm-3">
					              <button class="btn btn-primary" type='button' id="btnRearrange" ONCLICK="javascript:fnReaarangeActivate()">Rearrange</button>
					              <button class="btn btn-primary" type='button' id="btnModify" ONCLICK="javascript:fnModify()">Modify</button>
					              <button class="btn btn-primary" type='button' id="btnSave" style="display:none;" ONCLICK="javascript:fnSaveRearrangeClauses()">Save</button>
					              <button class="btn btn-primary" type='button' id="btnDelete" ONCLICK="javascript:fnDeleteCompDesc()">Delete</button>
					        </div>
				     	</div>
				    </div>
						
					<div class="row">
						<div class="spacer10"></div>
					</div>	
						
					</logic:greaterThan>
					
					<logic:greaterThan name="modelCompDescsize" value="0">
					<HR>
						<TABLE class="table table-bordered">
							<thead>
								<tr>
									<th class="text-center" colspan="2">Available Model Component Description</TH>
								</tr>
							</thead>
							<tbody class="text-center">
							<logic:iterate id="ModelMainFeature" name="MainFeatureForm"	property="modelMainFeatureList"
								type="com.EMD.LSDB.vo.common.MainFeatureInfoVO" scope="request">
								<tr>
								<td width="90%">
									<bean:write	name="ModelMainFeature" property="compDesc" />
								</td>
								<td width="10%">
									<button class="btn btn-primary btn-sm" type='button' 
										ONCLICK="javascript:fnAddToOrder('<%= String.valueOf(ModelMainFeature.getCompSeqNo())%>')">Add to order</button>
								</td>
							</TR>
							</logic:iterate>
						</TABLE>
						
						<div class="row">
							<div class="spacer30"></div>
						</div>	
					</logic:greaterThan> 
					<html:hidden property="hdCompDesc" />
				</logic:notEqual>
			</logic:present>
					<div class="row">
						<div class="spacer10"></div>
					</div>
					<logic:notEqual name="MainFeatureForm" property="specStatus" value="5">
						<logic:present name="MainFeatureForm" property="orderList"
							scope="request">
							<div class="row">
									<div class="col-sm-offset-1 col-sm-5">
									    <logic:present name="MainFeatureForm" property="orderList"
										scope="request">
										<logic:iterate id="DisList" name="MainFeatureForm"
											property="orderList" type="com.EMD.LSDB.vo.common.OrderVO"
											scope="request">
											<logic:equal name="DisList" property="appendixFlag" value="Y">
												<div>Appendix: <mark>Turned On</mark></div>
											</logic:equal>
											<logic:equal name="DisList" property="appendixFlag" value="N">
												<div>Appendix: <mark>Turned Off</mark></div>
											</logic:equal>
										</logic:iterate>
									</logic:present>
									</div>
									
									<div class="required-field">
										<label class="col-sm-1 control-label" for="specStatusCode">Spec Status</label>									
										<div class="col-sm-1">
											<html:select name="MainFeatureForm" property="specStatusCode" styleId="spcStatus"
														styleClass="form-control">
													<option selected value="-1">---Select---</option>
												<html:optionsCollection name="MainFeatureForm"
													property="specStatusList" value="specStatusCode" label="statusDesc" />
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
												<button class="btn btn-primary btn-sm" type='button' id="PublishSubLevelButton" ONCLICK="javascript:fnTurnPDFHeaderFlag('N')">Turn Off</button>
											</div>
										</div>
									</logic:equal>
									<logic:equal name="DisList" property="pdfHeaderFlag" value="N">
										<div class="row">
											<div class="text-right"><strong>Electro-Motive Logo : <mark>Off</mark></strong>
												<button class="btn btn-primary btn-sm" type='button' id="PublishSubLevelButton" ONCLICK="javascript:fnTurnPDFHeaderFlag('Y')">Turn On</button>
											</div>
										</div>
									</logic:equal>
									
									<div class="row">
										<div class="spacer10"></div>
									</div>
									
									<logic:equal name="DisList" property="userMarkerFlag" value="Y">
										<div class="row">
											<div class="text-right"><strong>Preserve Clause Markers : <mark>On</mark></strong>
												<button class="btn btn-primary btn-sm" type='button' id="PublishSubLevelButton" ONCLICK="javascript:fnTurnUserMarkerFlag('N')">Turn Off</button>
											</div>
										</div>
									</logic:equal>
									<logic:equal name="DisList" property="userMarkerFlag" value="N">	
										<div class="row">
											<div class="text-right"><strong>Preserve Clause Markers : <mark>Off</mark></strong>
												<button class="btn btn-primary btn-sm" type='button' id="PublishSubLevelButton" ONCLICK="javascript:fnTurnUserMarkerFlag('Y')">Turn On</button>
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
												<button class="btn btn-primary btn-sm" type='button' id="PublishSubLevelButton" ONCLICK="javascript:fnTurnCustomerLogoFlag('N')">Turn Off</button>
											</div>
										</div>
									</logic:equal>
									<logic:equal name="DisList" property="custLogoFlag" value="N">	
										<div class="row">
											<div class="text-right"><strong>Customer Logo : <mark>Off</mark></strong>
												<button class="btn btn-primary btn-sm" type='button' id="PublishSubLevelButton" ONCLICK="javascript:fnTurnCustomerLogoFlag('Y')">Turn On</button>
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
												<button class="btn btn-primary btn-sm" type='button' id="TurnDistributorOnOffButton" ONCLICK="javascript:fnTurnDistributorLogoFlag('Y')">Turn Off</button>
											</div>
										</div>
										</logic:equal>	
									
									</logic:equal>	
									<logic:notEqual name="DisList" property="specTypeSeqNo" value="2">
										<logic:equal name="DisList" property="dynamicNoFlag" value="Y">
											<div class="row">
												<div class="text-right"><strong>Dynamic Clause Numbering   : <mark>On</mark></strong>
													<button class="btn btn-primary btn-sm" type='button'  id="TurnDynamicOnOffButton" ONCLICK="javascript:fnTurnDynamicNumOnOffFlag('N')">Turn Off</button>
												</div>
											</div>
										</logic:equal>
										<logic:equal name="DisList" property="dynamicNoFlag" value="N">	
											<div class="row">
												<div class="text-right"><strong>Dynamic Clause Numbering   : <mark>Off</mark></strong>
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
						<logic:iterate id="linkViewColor" name="MainFeatureForm" property="sectionList" type="com.EMD.LSDB.vo.common.SectionVO" scope="request">
							<% if(i==0){ %>
										<logic:equal name="linkViewColor" property="linkColourFlag" value="Y">
										<div class="col-sm-offset-1 col-sm-3">
											<a href="javascript:fnDeletedClausesHistory()" class="linkstyleItem"><font color="red">
												<span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span> View Deleted Clause(s) History</font>
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
						<div class="col-sm-offset-1 col-sm-3">
							<a href="javascript:fnClauseswithIndicators()" class="linkstyleItem">
								<span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span> View Clause(s) with Indicators
							</a>
						</div>
					</div>
					<div class="row"><div class="spacer10"></div></div>
					<div class="row">
						<div class="col-sm-offset-7 col-sm-3 ">
							<a href="javascript:exportSpectoDoors('<bean:write name="DisList" property="modelName"/>','<bean:write name="DisList" property="modelSeqNo"/>','<bean:write name="DisList" property="specTypeName"/>','<bean:write name="DisList" property="orderKey"/>','<bean:write name="DisList" property="orderNo"/>','W')" class="linkstyleItem">
								<span class="glyphicon glyphicon glyphicon-save" aria-hidden="true"></span> Export Order to DOORs
							</a>
						</div>
					</div>
					<div class="row"><div class="spacer50"></div></div>
				</div>
					
					
					
					<!-- <div class ="row">
							<div class="col-sm-3 col-sm-offset-3 text-left">
								
								<div>
									<a href="javascript:fnGenerateProofReadingEnggDraft()" class="linkstyleItem">
										<span class="glyphicon glyphicon glyphicon-save" aria-hidden="true"></span> View Proofreading Engr Spec
									</a>
								</div>
								<div class="spacer10"></div>
								
								<div>
									<a href="javascript:fnGenerateProofReadingSalesDraft()" class="linkstyleItem">
										<span class="glyphicon glyphicon glyphicon-save" aria-hidden="true"></span> View Proofreading Sales Spec
									</a>
								</div>
								<div class="spacer10"></div>
								
								<div>
									<a href="javascript:fnGenerateProofReadingEngrSupplement()" class="linkstyleItem">
										<span class="glyphicon glyphicon glyphicon-save" aria-hidden="true"></span> View Proofreading Engr Supplement
									</a>
								</div>
								<div class="spacer10"></div>
								
								<div>
									<a href="javascript:fnGenerateProofReadingSalesSupplement()" class="linkstyleItem">
										<span class="glyphicon glyphicon glyphicon-save" aria-hidden="true"></span> View Proofreading Sales Supplement
									</a>
								</div>
								<div class="spacer10"></div>
							
							</div>
							
							<div class="col-sm-4 col-sm-offset-1  text-left">
								<div>
									<a href="javascript:fnGenerateProofReadingMarkedClauses()" class="linkstyleItem">
										<span class="glyphicon glyphicon glyphicon-save" aria-hidden="true"></span> View Marked Clause(s)
									</a>
								</div>	
								<div class="spacer10"></div>
								
								<div>
									<a href="javascript:fnViewCurrentEDLNumbers()" class="linkstyleItem">
										<span class="glyphicon glyphicon glyphicon-save" aria-hidden="true"></span> View Current EDL Number(s)
									</a>
								</div>	
								<div class="spacer10"></div>
							
								<div>
									<a href="javascript:fnViewSpecForAll()" class="linkstyleItem">
										<span class="glyphicon glyphicon glyphicon-save" aria-hidden="true"></span> View Master Spec
									</a>
								</div>
								<div class="spacer10"></div>
								
								<logic:iterate id="linkViewColor" name="MainFeatureForm" property="sectionList" type="com.EMD.LSDB.vo.common.SectionVO" scope="request">
								<% if(i==0){ %>
											<logic:equal name="linkViewColor" property="linkColourFlag" value="Y">
											<div>
												<a href="javascript:fnDeletedClausesHistory()" class="linkstyleItem"><font color="red">
													<span class="glyphicon glyphicon glyphicon-save" aria-hidden="true"></span> View Deleted Clause(s) History
												</a>
											</div>
											<div class="spacer10"></div>
											</logic:equal>
											<logic:notEqual name="linkViewColor" property="linkColourFlag" value="Y">
											<div>
												<a href="javascript:fnDeletedClausesHistory()" class="linkstyleItem">
													<span class="glyphicon glyphicon glyphicon-save" aria-hidden="true"></span> View Deleted Clause(s) History
												</a>
											</div>
											<div class="spacer10"></div>
											</logic:notEqual>
								<%i=i+1;%>
										<%} %>			
								</logic:iterate>
									<div>
										<a href="javascript:fnClauseswithIndicators()" class="linkstyleItem">
											<span class="glyphicon glyphicon glyphicon-save" aria-hidden="true"></span> View Clause(s) with Indicators
										</a>
									</div>
									<div class="spacer10"></div>
							
									<div>
										<a href="javascript:exportSpectoDoors('<bean:write name="DisList" property="modelName"/>','<bean:write name="DisList" property="modelSeqNo"/>','<bean:write name="DisList" property="specTypeName"/>','<bean:write name="DisList" property="orderKey"/>','<bean:write name="DisList" property="orderNo"/>','W')" class="linkstyleItem">
											<span class="glyphicon glyphicon glyphicon-save" aria-hidden="true"></span> Export Order to DOORs
										</a>
									</div>
						</div>
					</div> -->
									
								
								
						
							
						</logic:present>
					</logic:notEqual>
			
			
	<html:hidden property="hdnDispSpec" />
	<html:hidden property="hdnRevViewCode" />
	<!-- Added For CR_84 -->
	<html:hidden property="perfCurveLinkFlag" />
	<html:hidden property="genArrLinkFlag" />
	<html:hidden property="rowIndexList" />
	<!-- Added For CR_100 -->
	<html:hidden property="revFlag" />
	<html:hidden property="actionType" />
	<html:hidden property="revCode" />
	<html:hidden property="finalFlag" />
	<html:hidden property="currentSpecStatus" />
	<!-- Added For CR_104 -->
	<html:hidden property="custMdlName" />
	<html:hidden property="subject" />
	<html:hidden property="bodyCont" />
</html:form>
	</div>