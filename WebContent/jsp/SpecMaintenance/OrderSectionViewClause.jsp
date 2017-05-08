<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%--Added for CR-114--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.EMD.LSDB.vo.common.SubSectionVO" %>
<%--Displaying Section Details --%>
<%ArrayList arlSubSectionListForMenu=new ArrayList();%> 
 <%String strCurrSection ="";%>
 <%String strTempSubSecName =""; %>
 <%--Added for CR-114 Ends Here--%>
	<logic:iterate id="secdetail" name="OrderSectionForm"
		property="secDetail" type="com.EMD.LSDB.vo.common.SubSectionVO"
		scope="request" indexId="secCount">


		<bean:define id="secCnt"
			value="<%=String.valueOf(secCount.intValue())%>" />
		<logic:equal name="secCnt" value="0">
				
				<input type="hidden" name="orderSecCode"
					value="<%=String.valueOf(secdetail.getSecCode())%>">
				<input type="hidden" name="orderSecName"
					value="<%=String.valueOf(secdetail.getSecName())%>">
	<%strCurrSection =  String.valueOf(secdetail.getSecCode())+". "+String.valueOf(secdetail.getSecName());
	%>
	
			<div class="row">
				<div class="col-sm-12 highlightDark text-center">
					<div class="text-center"><strong><bean:write name="secdetail"
						property="secCode" />.<bean:write name="secdetail"
						property="secName" /></strong></div>
				</div>
			</div>
	
		</logic:equal>
		<%--TABLE FOR DISPLAY OF SECTION ENDS--%>

		<%--TABLE FOR DISPLAY OF SUB SECTION--%>
		<logic:notEqual name="secdetail" property="newSubSecFlag" value="Y">
			<%--Added for CR-114--%>
			<%boolean indiatedFlag=false;%>
			<% strTempSubSecName= secdetail.getSubSecCode()+" "+secdetail.getSubSecName();%>
			<A NAME="<%=strTempSubSecName%>"></A>
			<A NAME="<%=secdetail.getSubSecSeqNo()%>"></A>
			<%--Added for CR-114 Ends here--%>
				<div class="row">
					<div class="col-sm-12 bg-info text-center">
						<div class="col-sm-offset-2 col-sm-6 text-center push-text-down"><strong><bean:write	
							name="secdetail" property="subSecCode" /> &nbsp;<bean:write
							name="secdetail" property="subSecName" /></strong>
							<logic:equal name="secdetail" property="colourFlag" value="Y">
							<%
							indiatedFlag =true;
							%>
							<a
								href="AccRejNewClauseAction.do?method=fetchNewClauses&orderKey=<%=secdetail.getOrderKey()%>&secSeq=<%=secdetail.getSecSeqNo()%>&revCode=<%=secdetail.getRevCode()%>&subSecSeqNo=<%=secdetail.getSubSecSeqNo()%>&specCode=<bean:write name="OrderSectionForm" property="currentSpecStatus"/>"><img
								border=0 src="images/new1.gif"
								data-toggle="tooltip" title="<bean:message key='Message.NewIndicator'/>"></a>
							</logic:equal>
						</div>
				
						<div class="col-sm-4 text-right ">
							<button class="btn btn-primary btn-sm" type='button' name="btnAddClause" ONCLICK="javascript:fnFetchClaforRearrange('<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<%=secdetail.getSubSecSeqNo()%>','<%=secdetail.getOrderKey()%>')">Rearrange Clauses</button>
							<button class="btn btn-primary btn-sm" type='button' ONCLICK="javascript:funAddNewClause('<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<%=secdetail.getSubSecSeqNo()%>','<%=secdetail.getOrderKey()%>')">Add New Component/Clause to Order</button>
						</div>
					</div>
				</div>
		<%--TABLE FOR DISPLAY OF SUB SECTION ENDS--%>
				<div class="row">
					<div class="spacer10"></div>
				</div>
		
	<!--TABLE FOR DISPLAY OF SAVE COMPONENT AND DROP DOWN-->
			<bean:size id="compsize" name="secdetail" property="compGroup" />
			<%-- Displaying all the the component groups --%>
				<div class="form-horizontal">
					<logic:iterate id="comp" name="secdetail" property="compGroup"
						type="com.EMD.LSDB.vo.common.CompGroupVO" scope="page"
						indexId="counter">
						<bean:define id="sizecheck"	value="<%=String.valueOf((counter.intValue())%2 )%>" />
						<logic:equal name="sizecheck" value="0">
							<div class="form-group">
						</logic:equal>
						<div class="multipleFormFields">
							<html:hidden name="comp" property="componentGroupSeqNo" />
							<html:hidden name="comp" property="validFlag" />
							<label class="col-sm-2 control-label">
								<logic:present name="comp" property="validFlag">
									<logic:equal name="comp" property="validFlag" value="Y">
										<font color="red" valign="top">&nbsp;*</font>
									</logic:equal>
								</logic:present>
								<logic:equal name="comp" property="compGrpTypeSeqNo" value="2">
									<font color="green">
							 		<bean:write name="comp"	property="componentGroupName" /></font>
								</logic:equal>
								<logic:notEqual name="comp" property="compGrpTypeSeqNo" value="2">
									<bean:write name="comp" property="componentGroupName" />
								</logic:notEqual>
							</label>
							
							<div class="col-sm-3">
								<html:select name="OrderSectionForm" styleClass="form-control" styleId="" 
								property="compSeqNo" onchange="javascript:fnConfirmNewComp(jQuery(this).val());">
								<option value="-1">--Select---</option>
								<logic:iterate id="compo" name="comp" property="component"
									type="com.EMD.LSDB.vo.common.ComponentVO">
										<logic:equal name="compo" property="orderDefaultComp" value="Y">
											<option value="<%=String.valueOf(compo.getComponentSeqNo())%>"
												selected><bean:write name="compo" property="componentName" />
											</option>
										</logic:equal>
										<logic:notEqual name="compo" property="orderDefaultComp" value="Y">
											<logic:equal name="compo" property="orderCompColorFlag" value="N">
												<option value="<%=String.valueOf(compo.getComponentSeqNo())%>">
												<bean:write name="compo" property="componentName" /></option>
											</logic:equal>
											<logic:equal name="compo" property="orderCompColorFlag" value="Y">
												<option style="background-color: red;color: #FFFFFF;"
												 value="<%=String.valueOf(compo.getComponentSeqNo())%>">
												<bean:write name="compo" property="componentName" /></option>
											</logic:equal>
										</logic:notEqual>						
								</logic:iterate>
								</html:select>
							</div>
							
							<div class="col-sm-1"></div>
						</div>
						<logic:equal name="sizecheck" value="1">
							</div>
						</logic:equal>
						<logic:notEqual name="sizecheck" value="1">
							<logic:equal name="compsize" value="<%=String.valueOf((counter.intValue())+1)%>">
								</div>
							</logic:equal>
						</logic:notEqual>				
					</logic:iterate>
				</div>

			<logic:present name="secdetail" property="compGroup">
				<bean:size id="modlen" name="secdetail" property="compGroup" />
			</logic:present>

			<logic:greaterEqual name="compsize" value="1">		
				<div class="form-horizontal">
				
					<div class="row">
						<div class="spacer10"></div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-12 text-center">
							<button class="btn btn-primary" type='button' id="btnSaveComponent" 
								ONCLICK="javascript:fnValidateSaveComponent(<%=secdetail.getSubSecSeqNo()%>,<%=secdetail.getOrderKey()%>)">Save Component</button>
						</div>
					</div>
					
				</div>
			
			</logic:greaterEqual>
			
			<logic:equal name="compsize" value="0">
				<div class="row">
					<div class="spacer10"></div>
				</div>
			</logic:equal>
			
				<logic:iterate id="compGrp1" name="secdetail" property="compGroup"
					type="com.EMD.LSDB.vo.common.CompGroupVO" scope="page"
					indexId="counter">
					<logic:iterate id="compo1" name="compGrp1" property="component"
					type="com.EMD.LSDB.vo.common.ComponentVO">
						<logic:equal name="compo1" property="orderCompColorFlag" value="Y">
							<div class="modal fade" id="newcomptoord<%=String.valueOf(compo1.getComponentSeqNo())%>" tabindex="-1" role="dialog" aria-labelledby="newOrderComp">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
									      <div class="modal-header">
									        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									      	<h4 class="modal-title" id="newOrderComp">Add New Component/Clause to Order</h4>
									      </div>
									      <div class="modal-body">				
												<div class="form-group">
													<logic:equal name="compo1" property="labelFlag" value="Y">
														<mark><strong><i>This Clause has multiple versions which can be selected by clicking on Revise Clause link.</i></strong></mark>
													</logic:equal>
												</div>
												<div class="form-group">
													<label class="control-label">NEW COMPONENT NAME:</label>
													<p><%=String.valueOf(compo1.getComponentName())%></p>
												</div>
												<div class="form-group">
													<label class="control-label">NEW CLAUSE DESCRIPTION:</label>
														<DIV style="solid;height:80px;overflow:auto;">
															<%if(String.valueOf(compo1.getOrderCompClaDesc()).startsWith("<p>"))
															{%>
																<%=(String.valueOf(compo1.getOrderCompClaDesc()))%>
															<%}else{ %>	
																<%=(String.valueOf(compo1.getOrderCompClaDesc())).replaceAll("  ","&nbsp;&nbsp;").replaceAll("\n","<br>")%>
															<%}%>
														</div>
												</div>
										  </div>
										  <div class="modal-footer">
										  		<button type="button" class="btn btn-primary" onclick="javascript:fnAddNewComp('<%=compo1.getComponentSeqNo()%>','<%=compo1.getNewOrderNo() %>','<%=secdetail.getSecSeqNo()%>','<%=secdetail.getSubSecSeqNo()%>','<%=secdetail.getOrderKey()%>')">Add Component/Clause and Save to Order</button>
												<button type="button" class="btn btn-default" onclick="javascript:fnReloadSpec('<%=secdetail.getSecSeqNo()%>','<%=secdetail.getSubSecSeqNo()%>','<%=secdetail.getOrderKey()%>')" >Close</button>
										  </div>
									</div>
							</div>
						</div>
							
							
							
							
						</logic:equal>
					</logic:iterate>
				</logic:iterate>
				
		<%--TABLE FOR DISPLAY OF SAVE COMPONENT AND DROP DOWN ENDS--%>

		<logic:present name="secdetail" property="clauseGroup">
			<bean:size id="clauselen" name="secdetail" property="clauseGroup" />
		</logic:present>

		<%--TABLE FOR DISPLAY OF TABLE DATA--%>
		<TABLE class="table table-bordered table-hover">
			<%--Added For CR_108 rename price book no--%>
			<logic:greaterEqual name="clauselen" value="1">
			<thead>
				<TR>
					<TH CLASS='text-center' WIDTH="4%">Price<BR/>Book#</TH>
					<TH CLASS='text-center' WIDTH="8%">Revision No</TH>
					<TH CLASS='text-center' WIDTH="12%">Clause No</TH>
					<TH CLASS='text-center' WIDTH="50%">Clause Description</TH>
					<TH CLASS='text-center' WIDTH="12%">Engineering Data</TH>
					<%--Modified for CR_112 to change the column heading--%>
					<TH CLASS='text-center' WIDTH="14%" nowrap>Component&nbsp;/<br><p class="text-info">Component Group</p></TH>
					<%--CR_112 Ends here--%>
				</TR>
			</thead>
			</logic:greaterEqual>
			<tbody>
			<logic:iterate id="clause" name="secdetail" property="clauseGroup"
				type="com.EMD.LSDB.vo.common.ClauseVO" scope="page">

				<logic:equal name="clause" property="subSectionSeqNo"
					value="<%=String.valueOf(secdetail.getSubSecSeqNo())%>">
					<TR>
						<TD CLASS="text-center" VALIGN="TOP"><logic:notEqual name="clause"
							property="priceBookNumber" value="0">
							<bean:write name="clause" property="priceBookNumber" />
						</logic:notEqual>&nbsp;</TD>

						<%-- Added for LSDB_CR-74 --%>

						<TD valign="top" align="center">
						<logic:equal name="clause" property="userMarkFlag" value="Y">
						<%-- Modified for LSDB_CR-109 --%><h4>
						<logic:notEmpty name="clause" property="markClaReason">
	        				<A nohref="#" data-toggle="tooltip" title="<bean:write name="clause" property="markClaReason"  />">
	        			</logic:notEmpty>
							<%-- <table width="100%" border=1 bordercolor="#0000A0">
							<tr><td align=center>MARKED</td></tr>
							</table>--%>
							<span class="label label-warning">MARKED</span>
						<logic:notEmpty name="clause" property="markClaReason">
		        			</A>	
		        		</logic:notEmpty>	</h4>				
                     <%-- Added for LSDB_CR-94
						<table height="10%"> 
						<tr><td>&nbsp;</td></tr>
						</table> --%>
						</logic:equal>
						<logic:equal name="clause" property="sysMarkFlag" value="Y">
			        		<h4>
			        		<A href="javascript:fnSysEnDisable('<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<%=secdetail.getOrderKey()%>','<%=clause.getClauseSeqNo()%>','<%=clause.getSubSectionSeqNo()%>','H')"
			        		 title="<bean:message key='Revision.Disable.Clause.Message'/>" data-toggle="tooltip" data-placement="right"><span class="label label-primary"><bean:write name="clause" property="sysMarkDesc"/></span></A></h4>
			        		
			        		
						</logic:equal>
						<logic:equal name="clause" property="sysMarkFlag" value="H">
	        				
			        		<h4>
			        		<A href="javascript:fnSysEnDisable('<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<%=secdetail.getOrderKey()%>','<%=clause.getClauseSeqNo()%>','<%=clause.getSubSectionSeqNo()%>','Y')"
			        		data-toggle="tooltip" title="<bean:message key='Revision.Enable.Clause.Message' />">
			        		<span class="label label-info">Enable <bean:write name="clause" property="sysMarkDesc" /></span></A></h4>
			        		
					     </logic:equal>
						
						
						<logic:notEmpty name="clause" property="revCode">
							<logic:iterate id="revcode"
								name="clause" property="revCode">
								<bean:write name="revcode" />
								<br>
							</logic:iterate>
						</logic:notEmpty>
						</TD>
											
						<%-- CR 91 Modified Links --%>
						<logic:notEmpty name="clause" property="clauseNum">
							<TD VALIGN="TOP">


							<TABLE WIDTH="100%" BORDER="0" VALIGN="TOP">


								<%--This iterator is used to get the customerSeqNo and pass it to the AddSubclause page *Starts Here*--%>
								<logic:present name="OrderSectionForm" property="orderList"
									scope="request">
									<logic:iterate id="DisList" name="OrderSectionForm"
										property="orderList" type="com.EMD.LSDB.vo.common.OrderVO"
										scope="request">
										<logic:iterate id="cusList" name="DisList"
											property="customerVo"
											type="com.EMD.LSDB.vo.common.CustomerVO" scope="page">
											<input type="hidden" name="hdncusseqno"
												value="<%=cusList.getCustomerSeqNo()%>">
											<TR>
												<%-- Change for LSDB_CR-74 --%>
												<%-- Order level Clause deletion check --%>
												<logic:equal name="clause" property="clauseDelFlag" value="Y">
												<logic:equal name="clause" property="deleteFlag" value="D">
												<TD align=left NOWRAP width="5%" >
												
												<%-- Changed for CR no 49  --%>
													<a nohref="#" class="disabled" > <small>Add SubClause</small></a></td><td>&nbsp;&nbsp;&nbsp;<bean:write name="clause"
													property="clauseNum" />&nbsp;&nbsp;&nbsp;
												</TD>
												</logic:equal>
												</logic:equal>

												<%-- Model level Clause deletion check --%>
												<logic:equal name="clause" property="clauseDelFlag" value="Y">
												<logic:notEqual name="clause" property="deleteFlag" value="D">
												<TD align=left NOWRAP width="5%" >
												<%-- Changed for CR no 49--%>  <a nohref="#" class="disabled" 
													> <small>Add SubClause</small></a></td><td>&nbsp;&nbsp;&nbsp;<bean:write name="clause"
													property="clauseNum" />&nbsp;&nbsp;&nbsp;
												</TD>
												</logic:notEqual>
												</logic:equal>


												<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
												<%-- Model level Clause deletion check --%>
												<logic:notEqual name="clause" property="deleteFlag" value="D">
												<TD align=left NOWRAP width="5%">
												<a href="javascript:fnLoadSubClause('<%=clause.getClauseSeqNo()%>','<%=clause.getOrderKey()%>','<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<%=clause.getClauseNum()%>','<%=cusList.getCustomerSeqNo()%>','<%=clause.getDeleteIndicator()%>')" 
													id="addSubClaLink<%=clause.getClauseSeqNo()%>"> <small>Add SubClause</small></a></td><td>&nbsp;&nbsp;&nbsp;<bean:write name="clause"
													property="clauseNum" />&nbsp;&nbsp;&nbsp;
												</TD>
												</logic:notEqual>
												</logic:notEqual>

												<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
												<%-- Model level Clause deletion check --%>
												<logic:equal name="clause" property="deleteFlag" value="D">
												<TD align=left NOWRAP width="5%">
												<a href="javascript:fnLoadSubClause('<%=clause.getClauseSeqNo()%>','<%=clause.getOrderKey()%>','<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<%=clause.getClauseNum()%>','<%=cusList.getCustomerSeqNo()%>','<%=clause.getDeleteIndicator()%>')"
													id="addSubClaLink<%=clause.getClauseSeqNo()%>" > <small>Add SubClause</small></a></td><td>&nbsp;&nbsp;&nbsp;<bean:write name="clause"
													property="clauseNum" /> <!-- Id added for CR_121 -->
												</TD>
												</logic:equal>
												</logic:notEqual>
											</TR>
											<TR>
												<%-- Change for LSDB_CR-74 --%>
												
												<input type="hidden" name="enable" value="<%=clause.getReviseEnableFlag()%>"/>
												<input type="hidden" name="modelclauseflag" value="<%=clause.getDeleteFlag()%>"/>
												<input type="hidden" name="orderclauseflag" value="<%=clause.getClauseDelFlag()%>"/>
												<input type="hidden" name="DelClaStagFlag" value="<%=clause.getDelClaStagFlag()%>"/>
																						
												<logic:equal name="clause" property="reviseEnableFlag" value="Y">
												<TD align=left NOWRAP><a href="javascript:fnSelectClauseRevSpec('<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<%=secdetail.getOrderKey()%>','<%=clause.getClauseSeqNo()%>','<%=cusList.getCustomerSeqNo()%>','<%=clause.getDeleteIndicator()%>','<%=clause.getDeleteFlag()%>','<%=secdetail.getSubSecSeqNo()%>')"
													id="ClaRevSpecLink<%=clause.getClauseSeqNo()%>"  ><small>Revise Clause</small></a><!-- Id added for CR_121 -->
												</TD>
												</logic:equal>

												<logic:notEqual name="clause" property="reviseEnableFlag" value="Y">
												<TD align=left NOWRAP><a nohref="#"
													Class="disabled"><small>Revise Clause</small></a>
												</TD>
												</logic:notEqual>
											</logic:iterate>
									</logic:iterate>
								</logic:present>

								<%--Ends Here--%>
								<%-- Change for LSDB_CR-74 --%>

								<TD ALIGN="left">
								<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
								<logic:notEqual name="clause" property="deleteFlag" value="D">
								<logic:equal name="clause"
									property="modelIndicator" value="Y">
								<%
								indiatedFlag =true;
								 %>
									<a  
									href="javascript:fnDisplayModelIndicator('<%=clause.getClauseSeqNo()%>','<%=clause.getOrderKey()%>','<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<bean:write name="OrderSectionForm" property="currentSpecStatus"/>','<%=clause.getDeleteIndicator()%>','<%=secdetail.getSubSecSeqNo()%>')"
									data-toggle="tooltip" id="dispModelIndicatorLink<%=clause.getClauseSeqNo()%>" title="<bean:message key='Message.ModelIndicator'/>" >
									 <span class="label label-danger"><small>MODEL</small></span></a>
									<br />
								</logic:equal> <logic:equal name="clause"
									property="copyIndicator" value="Y">
									<%
				indiatedFlag =true;
				 %>
									<%--   Added for  landing into particular sub section on  13/02/09 as per CR 71 by cm68219--%>
									<a data-toggle="tooltip" title="<bean:message key='Message.CopyIndicator'/>" 
									href="javascript:fnDisplayCopyIndicator('<%=clause.getClauseSeqNo()%>','<%=clause.getOrderKey()%>','<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<bean:write name="OrderSectionForm" property="currentSpecStatus"/>','<%=clause.getDeleteIndicator()%>','<%=secdetail.getSubSecSeqNo()%>')" id="dispCopyIndicatorLink<%=clause.getClauseSeqNo()%>">
									
									<span class="label label-danger"><small>COPY</small></span></a>
					&nbsp;<br />
								</logic:equal> <!-- Added for CR No 49 --> <logic:equal
									name="clause" property="deleteIndicator" value="Y">
									<%
				indiatedFlag =true;
				 %>
									<!--   Added for  landing into particular sub section on  13/02/09 as per CR 71 by cm68219-->
									<A data-toggle="tooltip" title="<bean:message key='Message.DeleteIndicator'/>" 
										HREF="DeleteIndAcceptRejectClauseAction.do?method=fetchClauseVersions&clauseSeq=<%=clause.getClauseSeqNo()%>&orderKey=<%=clause.getOrderKey()%>&secSeq=<%=secdetail.getSecSeqNo()%>&revCode=<%=secdetail.getRevCode()%>&specCode=<bean:write name="OrderSectionForm" property="currentSpecStatus"/>&subSecSeqNo=<%=secdetail.getSubSecSeqNo()%>">
										<span class="label label-danger"><small>DELETE</small></span></A>
										<br />
								</logic:equal>
								<%-- Added for CR No 81 EDL Indicator Screen  --%>
								<logic:equal
									name="clause" property="edlIndicator" value="Y">
							<%
				indiatedFlag =true;
				%>
									<A data-toggle="tooltip" title="<bean:message key='Message.EdlIndicator'/>"
									 href="javascript:fnDisplayEdlIndicator('<%=clause.getClauseSeqNo()%>','<%=clause.getOrderKey()%>','<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<bean:write name="OrderSectionForm" property="currentSpecStatus"/>','<%=clause.getDeleteIndicator()%>','<%=secdetail.getSubSecSeqNo()%>')" id="dispEdlIndicatorLink<%=clause.getClauseSeqNo()%>">
										<span class="label label-danger"><small>EDL CHANGE</small></span></A>
								</logic:equal>
								<%-- Added for CR No 81 EDL Indicator Screen - Ends here --%>

								</logic:notEqual>
								</logic:notEqual>
								
								<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
								<logic:equal name="clause" property="deleteFlag" value="D">
								<logic:equal name="clause"
									property="modelIndicator" value="Y">
	<%
				indiatedFlag =true;
				 %>
									<a data-toggle="tooltip" title="<bean:message key='Message.ModelIndicator'/>" 
									href="javascript:fnDisplayModelIndicator('<%=clause.getClauseSeqNo()%>','<%=clause.getOrderKey()%>','<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<bean:write name="OrderSectionForm" property="currentSpecStatus"/>','<%=clause.getDeleteIndicator()%>','<%=secdetail.getSubSecSeqNo()%>')">
									 <span class="label label-danger"><small>MODEL</small></span></a>
									<br />
								</logic:equal> <logic:equal name="clause"
									property="copyIndicator" value="Y">
									<%
				indiatedFlag =true;
				 %>
									<!--   Added for  landing into particular sub section on  13/02/09 as per CR 71 by cm68219-->
									<a data-toggle="tooltip" title="<bean:message key='Message.CopyIndicator'/>"
									 href="javascript:fnDisplayCopyIndicator('<%=clause.getClauseSeqNo()%>','<%=clause.getOrderKey()%>','<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<bean:write name="OrderSectionForm" property="currentSpecStatus"/>','<%=clause.getDeleteIndicator()%>','<%=secdetail.getSubSecSeqNo()%>')">
									<span class="label label-danger"><small>COPY</small></span></a>
					&nbsp;<br />
								</logic:equal> <%-- Added for CR No 49 --%> <logic:equal
									name="clause" property="deleteIndicator" value="Y">
						<%
						indiatedFlag =true;
						 %>
									<%--   Added for  landing into particular sub section on  13/02/09 as per CR 71 by cm68219--%>
									<A data-toggle="tooltip" title="<bean:message key='Message.DeleteIndicator'/>"
										HREF="DeleteIndAcceptRejectClauseAction.do?method=fetchClauseVersions&clauseSeq=<%=clause.getClauseSeqNo()%>&orderKey=<%=clause.getOrderKey()%>&secSeq=<%=secdetail.getSecSeqNo()%>&revCode=<%=secdetail.getRevCode()%>&specCode=<bean:write name="OrderSectionForm" property="currentSpecStatus"/>&subSecSeqNo=<%=secdetail.getSubSecSeqNo()%>">
										<span class="label label-danger"><small>DELETE</small></span></A>
										<br />
								</logic:equal>
								<%-- Added for CR No 81 EDL Indicator Screen  --%>
								<logic:equal
									name="clause" property="edlIndicator" value="Y">
						<%
									indiatedFlag =true;
									 %>
									<A data-toggle="tooltip" title="<bean:message key='Message.EdlIndicator'/>"  href="javascript:fnDisplayEdlIndicator('<%=clause.getClauseSeqNo()%>','<%=clause.getOrderKey()%>','<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<bean:write name="OrderSectionForm" property="currentSpecStatus"/>','<%=clause.getDeleteIndicator()%>','<%=secdetail.getSubSecSeqNo()%>')">
										<span class="label label-danger"><small>EDL CHANGE</small></span></A>
								</logic:equal>
								<%-- Added for CR No 81 EDL Indicator Screen - Ends here --%>

								</logic:equal>
								</logic:notEqual>
								</TD>

								</TR>

								<TR>
								<%-- Change for LSDB_CR-74 --%>

								<logic:equal name="clause" property="clauseDelFlag" value="Y">
								<logic:notEqual name="clause" property="deleteFlag" value="D">
									<TD align=left colspan="2"><a nohref="#"
										Class="disabled"> <small>Delete Clause</small> </a></TD>
								</logic:notEqual>
								</logic:equal>
								
								
								<logic:equal name="clause" property="clauseDelFlag" value="Y">
								<logic:equal name="clause" property="deleteFlag" value="D">
								<TD align=left colspan="2"><a nohref="#"
										Class="disabled"> <small>Delete Clause</small> </a></TD>
								</logic:equal>
								</logic:equal>
								
								<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
								<logic:notEqual name="clause" property="deleteFlag" value="D">
								<%-- Added For CR_81 by RR68151 to disable delete Clause Link for Characteristic Group --%>
								<logic:equal name="clause" property="selectCGCFlag" value="N">
									<TD align=left colspan="2"><a href="javascript:displayClauseReason('<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<%=secdetail.getOrderKey()%>','<%=clause.getClauseSeqNo()%>','<%=clause.getSubSectionSeqNo()%>','<%=clause.getVersionNo()%>','D')"> <small>Delete Clause</small> </a></TD>
										</logic:equal>
								<%-- Added For CR_88 --%>
								<logic:equal name="clause" property="selectCGCFlag" value="C">
									<TD align=left colspan="2"><a href="javascript:displayClauseReason('<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<%=secdetail.getOrderKey()%>','<%=clause.getClauseSeqNo()%>','<%=clause.getSubSectionSeqNo()%>','<%=clause.getVersionNo()%>','D')"> <small>Delete Clause</small> </a></TD>
								</logic:equal>
								<logic:equal name="clause" property="selectCGCFlag" value="Y">
								<TD align=left colspan="2"><a nohref="#"
										Class="disabled"> <small>Delete Clause</small> </a></TD>
								</logic:equal>
								<%-- Added For CR_81 by RR68151 to disable delete Clause Link for Characteristic Group - Ends --%>		
								</logic:notEqual>
								</logic:notEqual>	

								<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
								<logic:equal name="clause" property="deleteFlag" value="D">
								<%-- Added For CR_81 by RR68151 to disable delete Clause Link for Characteristic Group --%>
								<%-- Changed TO notequal for CR_88 --%>
								<logic:equal name="clause" property="selectCGCFlag" value="N">
									<TD align=left colspan="2"><a href="javascript:displayClauseReason('<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<%=secdetail.getOrderKey()%>','<%=clause.getClauseSeqNo()%>','<%=clause.getSubSectionSeqNo()%>','<%=clause.getVersionNo()%>','D')"
										> <small>Delete Clause</small> </a></TD>
										</logic:equal>
								<%-- Added For CR_88 --%>
								<logic:equal name="clause" property="selectCGCFlag" value="C">
									<TD align=left colspan="2"><a href="javascript:displayClauseReason('<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<%=secdetail.getOrderKey()%>','<%=clause.getClauseSeqNo()%>','<%=clause.getSubSectionSeqNo()%>','<%=clause.getVersionNo()%>','D')"
										> <small>Delete Clause</small> </a></TD>
								</logic:equal>
								<logic:equal name="clause" property="selectCGCFlag" value="Y">
								<TD align=left colspan="2"><a nohref="#"
										Class="disabled"> <small>Delete Clause</small> </a></TD>
								</logic:equal>
								<%-- Added For CR_81 by RR68151 to disable delete Clause Link for Characteristic Group - Ends --%>
								</logic:equal>
								</logic:notEqual>
								</TR>
							
								<%-- Added For CR_86 by SD41630 to remove Clause  --%>
							
								<logic:equal name="clause" property="clauseDelFlag" value="Y">	
								<TR>	
								    <TD align=left colspan="2"><a href="javascript:fnRemoveReserved('<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<%=secdetail.getOrderKey()%>','<%=clause.getClauseSeqNo()%>','<%=clause.getSubSectionSeqNo()%>','RC')"
                                 	> <small>Remove RESERVED</small> </a></TD>
								</TR>
								</logic:equal> 
								
								
								<%-- Change for LSDB_CR-74 --%>
								<%-- Starts here --%>
								<TR>
								<%-- Change for LSDB_CR-74 --%>
								<logic:equal name="clause" property="clauseDelFlag" value="Y">
								<logic:notEqual name="clause" property="deleteFlag" value="D">
									<TD ALIGN="left" colspan="2">
									<%-- Modified for LSDB_CR_94 Starts here--%>
										<logic:equal name="clause" property="userMarkFlag" value="N">
										<%-- Change for LSDB_CR-109 --%>		
										<A data-toggle="tooltip" title="<bean:message key='Mark.Clause.Message'/>" HREF="javascript:fnShowMarkClause('<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<%=secdetail.getOrderKey()%>','<%=clause.getClauseSeqNo()%>','<%=clause.getSubSectionSeqNo()%>','Y');" ><small>Mark Clause</small></A>
										</logic:equal>
										<logic:equal name="clause" property="userMarkFlag" value="Y">
										<A data-toggle="tooltip" title="<bean:message key='Mark.Clause.Message'/>" HREF="javascript:fnSetUserMarker('<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<%=secdetail.getOrderKey()%>','<%=clause.getClauseSeqNo()%>','<%=clause.getSubSectionSeqNo()%>','N')"><small>Unmark Clause</small></A>
										</logic:equal>
									</TD>
									</logic:notEqual>
									</logic:equal>

									<logic:equal name="clause" property="clauseDelFlag" value="Y">
									<logic:equal name="clause" property="deleteFlag" value="D">
									<TD ALIGN="left" colspan="2">
										<logic:equal name="clause" property="userMarkFlag" value="N">
										<%-- Change for LSDB_CR-109 --%>		
										<A data-toggle="tooltip" title="<bean:message key='Mark.Clause.Message'/>" HREF="javascript:fnShowMarkClause('<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<%=secdetail.getOrderKey()%>','<%=clause.getClauseSeqNo()%>','<%=clause.getSubSectionSeqNo()%>','Y')" ><small>Mark Clause</small></A>
										</logic:equal>
										<logic:equal name="clause" property="userMarkFlag" value="Y">
										<A data-toggle="tooltip" title="<bean:message key='Mark.Clause.Message'/>" HREF="javascript:fnSetUserMarker('<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<%=secdetail.getOrderKey()%>','<%=clause.getClauseSeqNo()%>','<%=clause.getSubSectionSeqNo()%>','N')"><small>Unmark Clause</small></A>
										</logic:equal>
									</TD>
									</logic:equal>
									</logic:equal>
								<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
								<logic:notEqual name="clause" property="deleteFlag" value="D">
									<TD ALIGN="left" colspan="2">
										<logic:equal name="clause" property="userMarkFlag" value="N">
										<%-- Change for LSDB_CR-109 --%>		
										<A data-toggle="tooltip" title="<bean:message key='Mark.Clause.Message'/>" HREF="javascript:fnShowMarkClause('<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<%=secdetail.getOrderKey()%>','<%=clause.getClauseSeqNo()%>','<%=clause.getSubSectionSeqNo()%>','Y')" ><small>Mark Clause</small></A> 
										</logic:equal>
										<logic:equal name="clause" property="userMarkFlag" value="Y">
										<A data-toggle="tooltip" title="<bean:message key='Mark.Clause.Message'/>" HREF="javascript:fnSetUserMarker('<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<%=secdetail.getOrderKey()%>','<%=clause.getClauseSeqNo()%>','<%=clause.getSubSectionSeqNo()%>','N')"><small>Unmark Clause</small></A>
										
										</logic:equal>
									</TD>
									</logic:notEqual>
									</logic:notEqual>


								<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
								<logic:equal name="clause" property="deleteFlag" value="D">
									<TD ALIGN="left" colspan="2">
										<logic:equal name="clause" property="userMarkFlag" value="N">
										<%-- Change for LSDB_CR-109 --%>		
										<A data-toggle="tooltip" title="<bean:message key='Mark.Clause.Message'/>" HREF="javascript:fnShowMarkClause('<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<%=secdetail.getOrderKey()%>','<%=clause.getClauseSeqNo()%>','<%=clause.getSubSectionSeqNo()%>','Y')" ><small>Mark Clause</small></A>  
										</logic:equal>
										<logic:equal name="clause" property="userMarkFlag" value="Y">
										<A data-toggle="tooltip" title="<bean:message key='Mark.Clause.Message'/>" HREF="javascript:fnSetUserMarker('<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<%=secdetail.getOrderKey()%>','<%=clause.getClauseSeqNo()%>','<%=clause.getSubSectionSeqNo()%>','N')"><small>Unmark Clause</small></A>
										
										</logic:equal>
										<%-- Modified for LSDB_CR_94 Ends here --%>
									</TD>
									</logic:equal>
									</logic:notEqual>
									</TR>
									<%-- Commented For CR_100 & commented for CR_111 by the Sales Version --%>
									<%-- logic:notEqual name="clause" property="clauseDelFlag" value="Y">
									<TR>
									<TD ALIGN="left" nowrap colspan="2">
										<logic:equal name="clause" property="salesVerFLAG" value="N">
										//Modified for LSDB_CR_99 Starts here
										<A class="linkstyle1 vtip" title="<bean:message key='Add.Sales.Version.Message'/>"	HREF="javascript:fnReplaceClause('<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<%=secdetail.getOrderKey()%>','<%=clause.getClauseSeqNo()%>','<%=clause.getSubSectionSeqNo()%>','Y')"><small>Add Sales Version</small></A>
										</logic:equal>
										<logic:equal name="clause" property="salesVerFLAG" value="Y">
	                                 	<A class="linkstyle1 vtip" title="<bean:message key='Remove.Sales.Version.Message'/>"	HREF="javascript:fnReplaceClause('<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<%=secdetail.getOrderKey()%>','<%=clause.getClauseSeqNo()%>','<%=clause.getSubSectionSeqNo()%>','N')"><small>Remove Sales Version</small></A>
	                                 	</logic:equal>
	                                 	
									</TD>
									</TR>
	                                </logic:notEqual --%>
	                                <%-- Commented For CR_100 - Ends here  --%>
								<%-- Reason will appear, if System marker is present--%>
								<%-- Enter reason Hyperlink color is changed from red to blue on 20-July-09 by ps57222--%>
								<logic:equal name="clause" property="sysMarkFlag" value="Y">
								 <TR>
									<TD ALIGN="left" colspan="2">
									<a href="javascript:displayClauseReason('<%=secdetail.getSecSeqNo()%>','<%=secdetail.getRevCode()%>','<%=secdetail.getOrderKey()%>','<%=clause.getClauseSeqNo()%>','<%=clause.getSubSectionSeqNo()%>','<%=clause.getVersionNo()%>','R')"><small><B>Enter Reason</B></small></A>
									</TD>
								</TR>
								</logic:equal>
		
								<%-- Ends here --%>

							</TABLE>
							</TD>
						</logic:notEmpty>

						<logic:empty name="clause" property="clauseNum">
							<TD>
							</TD>
						</logic:empty>
					
						<%-- Change for LSDB_CR-74 --%>
						<logic:equal name="clause" property="clauseDelFlag" value="Y">
						<logic:notEqual name="clause" property="deleteFlag" value="D">
						<TD  VALIGN="TOP" class="text-muted">
						<bean:message
						key="Message.Reserved" />
						</TD>
						</logic:notEqual>
						</logic:equal>
						
						<logic:equal name="clause" property="clauseDelFlag" value="Y">
						<logic:equal name="clause" property="deleteFlag" value="D">
						<TD VALIGN="TOP" class="text-muted">
						<bean:message
						key="Message.Reserved" />
						</TD>
						</logic:equal>
						</logic:equal>

						<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
						<logic:notEqual name="clause" property="deleteFlag" value="D">
						<TD VALIGN="TOP">
						<%-- Clause desc display --%>
						<%-- <bean:write name="clause" property="clauseDesc"/> --%> <bean:define
							name="clause" property="clauseDesc" id="clauseDesc" />
							<!-- CR-128 - Updated for Pdf issue -->
								<%String strClauseDesc =  String.valueOf(clauseDesc);
								if(strClauseDesc.startsWith("<p>")){%>
									<%=(String.valueOf(clauseDesc))%>
									<%--<%=(String.valueOf(clauseDesc)).replaceAll("<p></p>","<p>&nbsp;</p>")%>--%>
								<%}else{ %>	
							<%-- double space replacement with &nbsp is added to display the spaces as entered. This change is applicable for only modify spec --%>
							<%=(String.valueOf(clauseDesc)).replaceAll("  ","&nbsp;&nbsp;").replaceAll("\n","<br>")%>
								<%}%>
							<!-- CR-128 - Updated for Pdf issue Ends Here-->
						
							<logic:notEmpty name="clause" property="tableArrayData1">
				 			<table class="table table-bordered text-center">
								 <logic:iterate id="outter" name="clause" property="tableArrayData1"
													indexId="counter">
												<%-- Added  For CR_93 --%>
													<bean:define name="clause" property="tableDataColSize" id="tableDataColSize" />
													<bean:define id="row" name="counter" />
													
													<tr>
														<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
				
															<logic:equal name="row" value="0">
																<td valign="top" width="5%" class="borderbottomleft1 text-center"><b><font
																	color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></font></b>
																</td>
															</logic:equal>
				
															<logic:notEqual name="row" value="0">
																<td valign="top" width="5%" class="borderbottomleft1 text-center"><%=(tabrow==null)? "&nbsp;":tabrow%>
																</td>
															</logic:notEqual>
														</logic:iterate>
													</tr>
													
											
							 </logic:iterate>
							</table>
							</logic:notEmpty>
							<logic:present
							name="OrderSectionForm" property="orderList" scope="request">
							<logic:iterate id="DisList" name="OrderSectionForm"
								property="orderList" type="com.EMD.LSDB.vo.common.OrderVO"
								scope="request">
								<logic:equal name="DisList" property="appendixFlag" value="Y">
									<logic:notEmpty name="clause" property="clauseImageName">
										<span class="text-muted">(Refer To Appendix Image: <bean:write
											name="clause" property="clauseImageName" />)</span>
									</logic:notEmpty>
								</logic:equal>
							</logic:iterate>
						</logic:present></TD>
						</logic:notEqual>
						</logic:notEqual>


						<%-- Model level delete and order level not delete --%>
						<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
						<logic:equal name="clause" property="deleteFlag" value="D">
						<TD VALIGN="TOP">
						<%-- Clause desc display --%>
						<%-- <bean:write name="clause" property="clauseDesc"/> --%> <bean:define
							name="clause" property="clauseDesc" id="clauseDesc" />
							<%-- double space replacement with &nbsp is added to display the spaces as entered. This change is applicable for only modify spec --%>
							<%=(String.valueOf(clauseDesc)).replaceAll("  ","&nbsp;&nbsp;").replaceAll("\n","<br>")%>

						
							<logic:notEmpty name="clause" property="tableArrayData1">
				 			<table class="table table-bordered text-center">
					 			<logic:iterate id="outter" name="clause" property="tableArrayData1"
										indexId="counter">
										<bean:define id="row" name="counter" />
										<%-- Added  For CR_93 --%>
												<bean:define name="clause" property="tableDataColSize" id="tableDataColSize" />
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
								</table>
							</logic:notEmpty>
						
							<logic:present
							name="OrderSectionForm" property="orderList" scope="request">
							<logic:iterate id="DisList" name="OrderSectionForm"
								property="orderList" type="com.EMD.LSDB.vo.common.OrderVO"
								scope="request">
								<logic:equal name="DisList" property="appendixFlag" value="Y">
									<logic:notEmpty name="clause" property="clauseImageName">
										<span class="text-muted">(Refer To Appendix Image: <bean:write
											name="clause" property="clauseImageName" />)</span>
									</logic:notEmpty>
								</logic:equal>
							</logic:iterate>
						</logic:present></TD>
						</logic:equal>
						</logic:notEqual>



						<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
						<logic:notEqual name="clause" property="deleteFlag" value="D">
						<logic:empty name="clause" property="clauseDesc">
							<TD ><BR>
							</TD>
						</logic:empty>
						</logic:notEqual>
						</logic:notEqual>

						<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
						<logic:equal name="clause" property="deleteFlag" value="D">
						<logic:empty name="clause" property="clauseDesc">
							<TD><BR>
							</TD>
						</logic:empty>
						</logic:equal>
						</logic:notEqual>

						<%-- Eng data starts --%>
						<html:hidden name="clause" property="clauseDelFlag"  />
						<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
						<%--  added for CR_81 cheractrisitic group clause by sd41630 on 10/01/2010 --%>
						<TD CLASS="text-center" WIDTH="7%" VALIGN="TOP">

						<logic:equal name="clause" property="selectCGCFlag" value="Y">
					<%--	<table width="100%" border=1 bordercolor="#FFFFFF">
						<tr><td align=center width="100%" bordercolor="blue">
					 	<a href="javascript:fncharGrpCombntnList('<%=clause.getClauseSeqNo()%>')"
										Class="linkstyle1">
							<%//=//(clause.getCharEdlNo()==null? 
								//(clause.getCharRefEDLNo()==null? "EDL Undeveloped":"ref: EDL "+clause.getCharRefEDLNo())
								//		:(clause.getCharRefEDLNo()==null? "EDL "+clause.getCharEdlNo():
								//			"ref: EDL "+clause.getCharRefEDLNo()+"<br>"+"EDL "+clause.getCharEdlNo()))%>					
								</a> --%>
						<%-- CR 87 --%>
						<p class="lead">
						<a href="javascript:fncharGrpCombntnList('<%=clause.getClauseSeqNo()%>')">
							<span class="label label-primary">
							<%=(clause.getCharEdlNo()==null? 
								(clause.getCharRefEDLNo()==null? "EDL Undeveloped":"(ref EDL "+clause.getCharRefEDLNo()+")")
										:(clause.getCharRefEDLNo()==null? "EDL "+clause.getCharEdlNo():
											"EDL "+clause.getCharEdlNo()+ " <br> "+"&nbsp;(ref EDL "+clause.getCharRefEDLNo()+")"))%>					
							</span></a>								
								</p>
					<%--	</td></tr>
						</table>--%>
						</logic:equal>					
						<%-- Added For CR_85 Linking Regular clauses with Characteristic Combination --%>
						<%-- changed N to C for  CR_88  --%>
						<logic:equal name="clause" property="selectCGCFlag" value="C">
							<logic:notEmpty name="clause" property="charEdlNo">
								<a href="javascript:fncharGrpCombntnList('<%=clause.getClauseSeqNo()%>')">
										<bean:message key="viewSpec.EDL" />&nbsp;
										<bean:write name="clause" property="charEdlNo"/>
								</a><br/>	
							</logic:notEmpty>
							<logic:notEmpty name="clause" property="charRefEDLNo">
								<a href="javascript:fncharGrpCombntnList('<%=clause.getClauseSeqNo()%>')">
										<bean:message key="viewSpec.refEDL.start" />&nbsp;
										<bean:write name="clause" property="charRefEDLNo"/>
										<bean:message key="viewSpec.refEDL.end" />
								</a><br/>	
							</logic:notEmpty>
						</logic:equal>
						<%-- Ends here for CR_85 --%>
						<logic:notEmpty name="clause" property="edlNO">
							<logic:iterate id="edl" name="clause" property="edlNO">
								<bean:message key="viewSpec.EDL" />&nbsp;<bean:write name="edl" />
								<br>
							</logic:iterate>
						</logic:notEmpty>
						<%-- CR 87 --%>
						<logic:notEmpty	name="clause" property="refEDLNO">
							<logic:iterate id="refedl" name="clause" property="refEDLNO">
								<%-- <bean:message key="viewSpec.refEDL" /> --%>
								<bean:message key="viewSpec.refEDL.start" />&nbsp;<bean:write
									name="refedl" />
									<bean:message key="viewSpec.refEDL.end" />
									
								<br>
							</logic:iterate>
							
						</logic:notEmpty> 
						
						 <logic:notEmpty name="clause" property="partOF">
							<logic:iterate id="partof" name="clause" property="partOF"
								type="com.EMD.LSDB.vo.common.SubSectionVO">
								<bean:message key="viewSpec.PartOf" />&nbsp;<bean:write
									name="partof" property="subSecCode" />&nbsp;
								<%-- Commented for part of CR LSDB_CR_48--%>
								<%-- <A HREF="javascript:fnOpenPartOf(<%=partof.getSubSecSeqNo()%>,<%=clause.getOrderKey()%>,<%=partof.getSecSeqNo()%>,<%=clause.getClauseSeqNo()%>,<%=clause.getVersionNo()%>,<%=secdetail.getRevCode()%>,<%=secdetail.getSecSeqNo()%>,<%=partof.getPartOfClauseSeqNo()%>)"><IMG SRC="images/search.gif" alt="Search" BORDER="0"></A> --%>
								<br>
							</logic:iterate>
						</logic:notEmpty> 
						<logic:notEmpty name="clause"
							property="dwONumber">
							<logic:notEqual name="clause" property="dwONumber" value="0">
								<bean:message key="viewSpec.DWO" />&nbsp;<bean:write
									name="clause" property="dwONumber" />
								<br>
							</logic:notEqual>
						</logic:notEmpty> <logic:notEmpty name="clause"
							property="partNumber">
							<logic:notEqual name="clause" property="partNumber" value="0">
								<bean:message key="viewSpec.PartNo" />&nbsp;<bean:write
									name="clause" property="partNumber" />
								<br>
							</logic:notEqual>
						</logic:notEmpty> <logic:notEmpty name="clause"
							property="standardEquipmentDesc">
							<bean:write name="clause" property="standardEquipmentDesc" />
							<br>
						</logic:notEmpty> <logic:notEmpty name="clause"
							property="engDataComment">

							<bean:define name="clause" property="engDataComment"
								id="engDataComment" />
							<%=engDataComment%>

							<%-- <bean:write name="clause" property="engDataComment"/> --%>
							<br>

						</logic:notEmpty>&nbsp;
						</TD>
						</logic:notEqual>
						
						<logic:equal name="clause" property="clauseDelFlag" value="Y">
						<TD CLASS="text-center" VALIGN="TOP">&nbsp;</TD>
						</logic:equal>

						<logic:notEmpty name="clause" property="compName">
							<TD CLASS="text-center" VALIGN="TOP">
							<%-- This part is for finding size of the components--%>
							<bean:size id="compSize" name="clause" property="compName"/>
							<bean:define id="compTotSize" value="<%=String.valueOf(compSize.intValue()-1)%>" />
								<ul class="list-unstyled">
								<logic:iterate id="compdesc" name="clause"
									property="compName" type="com.EMD.LSDB.vo.common.ComponentVO" indexId="counter" length="compCnt">
										<bean:define id="count" name="counter" />
										<logic:notEqual name="counter" value="0"> ;</li>
										</logic:notEqual>
										<logic:equal name="compdesc" property="deletedFlag" value="Y">
											<li class="text-danger"><bean:write name="compdesc" property="componentName" />
										</logic:equal>
										<logic:notEqual name="compdesc" property="deletedFlag" value="Y">
										<%-- Change for LSDB_CR-74 --%>
										<logic:equal name="compdesc" property="compColorFlag" value="Y">
											<li class="text-info"><b><bean:write name="compdesc" property="componentName" /></b>
										</logic:equal>
										<logic:notEqual name="compdesc" property="compColorFlag" value="Y">
											<li><bean:write name="compdesc" property="componentName" />
										</logic:notEqual>
										<%-- Ends here --%>
										</logic:notEqual>
	
										<logic:equal name="counter" value="<%=String.valueOf(compSize.intValue()-1)%>">
											</li>
										</logic:equal>
								</logic:iterate>
								</ul>
							</TD>
						</logic:notEmpty>
						<logic:empty name="clause" property="compName">
							<TD CLASS="text-center"><BR>
							</TD>
						</logic:empty>
					</TR>
				</logic:equal>
			</logic:iterate>
		</tbody>
		</TABLE>
<%--Added for CR-114--%>
<%
SubSectionVO objSubSectionVO =new SubSectionVO();
objSubSectionVO.setIndicatedSubSec(indiatedFlag);
objSubSectionVO.setSubSecName(strTempSubSecName);
arlSubSectionListForMenu.add(objSubSectionVO);

%>
<%--Added for CR-114 ends here--%>
		<%-- TABLE FOR DISPLAY OF TABLE DATA--%>
		</logic:notEqual>
		
		<logic:equal name="secdetail" property="newSubSecFlag" value="Y">
			<div class="row fadeClass" ID="fadenewsubsec">
				<div class="col-sm-12 bg-info text-center">
					<div class="col-sm-offset-2 col-sm-6 text-center push-text-down"><strong><bean:write
						name="secdetail" property="subSecCode" /> &nbsp;<bean:write
						name="secdetail" property="subSecName" />&nbsp;&nbsp;&nbsp;</strong>
					</div>
			
					<div class="col-sm-4 text-right ">
						<button class="btn btn-primary btn-sm" type='button' name="btnAddSubsec" ONCLICK="javascript:fnConfirmSubSecAdd('<%=secdetail.getSecSeqNo()%>','<%=secdetail.getSubSecSeqNo()%>','<%=secdetail.getOrderKey()%>')">Add New SubSection to Order</button>
					</div>
				</div>
			</div>
		</logic:equal>
		<%--TABLE FOR DISPLAY OF SPACE--%>
		<div class="row">
			<div class="spacer20"></div>
		</div>
		<%--TABLE FOR DISPLAY OF SPACE ENDS--%>
		<br>
	</logic:iterate>
	
	<%-- Added for CR-114 --%>
	<%
	HttpSession sessForFooter = request.getSession(true);
	sessForFooter.setAttribute("currSectionValue",strCurrSection);
	sessForFooter.setAttribute("currSubSectionValues",arlSubSectionListForMenu); %>
	<%-- Added for CR-114 Ends --%>
	
		<%-- Added for CR-109 --%>				
		<div class="modal fade" id="MarkClause" tabindex="-1" role="dialog" aria-labelledby="markClaReasonModal">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					      	<h4 class="modal-title" id="markClaReasonModal">Mark Clause</h4>
					      </div>
					      <div class="modal-body">				
					      	<div class="form-horizontal">			
								<div class="form-group">
									<label class="col-sm-3 control-label">Reason</label>
									<div class="col-sm-7">
										<textarea class="form-control" rows="3" maxlength="4000" id="markClaReason" name="markClaReason"></textarea>
								</div>
								</div>
							</div>
						  </div>
						  <div class="modal-footer">
						  		<button type="button" class="btn btn-primary" id="saveClaReason">Save</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						  </div>
					</div>
			</div>
		</div>
	<html:hidden property="newOrderNo" />