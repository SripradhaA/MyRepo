<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<HEAD>
<LINK REL="stylesheet" HREF="css/Emd_Lsdb.css" TYPE="text/css">
<LINK REL="stylesheet" TYPE="text/css" HREF="css/EmdMenu.css">


<SCRIPT LANGUAGE="JavaScript" SRC="js/Emd_Lsdb.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/error.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/error_messages.js"></SCRIPT>
</HEAD>


<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>

<BODY CLASS="main">

<html:form action="/PreviewRequestAction" method="post"
	enctype="multipart/form-data">

	<TABLE BORDER=0 WIDTH="80%" ALIGN=center>
		<TR>
			<TD valign=top width="40%" ALIGN=center>

			<TABLE BORDER=0 WIDTH="80%" ALIGN=center>
				<TR>
					<TD WIDTH="10%">&nbsp;</TD>
				</TR>
				<TR>
					<TD WIDTH="80%">
					<center>
					<DIV CLASS="dashBoardSubHeading"><bean:message
						key="Application.Screen.PreviewRequest" /></DIV>
					</center>
					</TD>
				</TR>
				<TR>
					<TD>&nbsp;</TD>
				</TR>
			</TABLE>

			</TD>
		</TR>
		<TR>
			<TD valign=top width="40%" ALIGN=center><logic:present
				name="PreviewRequestForm" property="requestList" scope="request">
				<logic:iterate id="RequestList" name="PreviewRequestForm"
					property="requestList" type="com.EMD.LSDB.vo.common.RequestVO"
					scope="request">

					<TABLE ALIGN=center WIDTH="40%" BORDER=0>

						<TR>
							<TD width=15% ALIGN=center CLASS="greytext"><span CLASS=header
								nowrap>Request Id : </span><bean:write name="RequestList"
								property="reqID" /></TD>
							<TD width=15% ALIGN=center CLASS="greytext"><span CLASS=header
								nowrap>Status : </span><bean:write name="RequestList"
								property="statusTypeDesc" /></TD>

						</TR>
					</TABLE>
				</logic:iterate>
			</logic:present></TD>
		</TR>
		<TR>
			<TD valign=top width="50%" ALIGN=center><logic:present
				name="PreviewRequestForm" property="requestModelVO" scope="request">
				<bean:define id="RequestList" name="PreviewRequestForm"
					property="requestModelVO" />

				<TABLE WIDTH="60%" BORDER=0 ALIGN=center>

					<TR>
						<TD WIDTH="60%" CLASS="headerbgcolor"><b> Model</b></TD>
						<TD CLASS='navycolor1' WIDTH="40%"><b> <bean:write
							name="RequestList" property="modelName" /></b></TD>
					</TR>
					<TR>

						<TD CLASS="headerbgcolor">Section</TD>
						<TD CLASS='navycolor1'></span><bean:write name="RequestList"
							property="secCode" />.<bean:write name="RequestList"
							property="secName" /></TD>
					</TR>
					<TR>
						<TD CLASS="headerbgcolor">SubSection</TD>
						<TD CLASS='navycolor1' nowrap><bean:write name="RequestList"
							property="secCode" />.<bean:write name="RequestList"
							property="subSecCode" />.<bean:write name="RequestList"
							property="subSecName" /></TD>

					</TR>





					<logic:present name="PreviewRequestForm" property="requestList"
						scope="request">
						<logic:iterate id="ReqList" name="PreviewRequestForm"
							property="requestList" type="com.EMD.LSDB.vo.common.RequestVO"
							scope="request">


							<TR>
								<TD CLASS="headerbgcolor">Short Description</TD>
								<TD CLASS='navycolor1' wrap><bean:write name="ReqList"
									property="requestDesc" /></TD>

							</TR>

							<!-- Added for CR-59 by vv49326 25-Nov-08 -->

							<TR>
								<TD CLASS="headerbgcolor" nowrap>This change effects both
								Ace/M-2 Models&nbsp;</TD>
								<logic:notEmpty name="RequestList" property="mdlFlag">

									<logic:equal name="RequestList" property="mdlFlag" value="Yes">

										<TD CLASS='navycolor1' nowrap><input type=checkbox
											name="applyModels" checked="checked" disabled /></TD>

									</logic:equal>

									<logic:equal name="RequestList" property="mdlFlag" value="No">

										<TD CLASS='navycolor1' nowrap><input type=checkbox
											name="applyModels" disabled /></TD>

									</logic:equal>

								</logic:notEmpty>


							</TR>
						</logic:iterate>
					</logic:present>
				</TABLE>




			</logic:present></TD>
		</TR>
	</TABLE>





	<BR>



	<HR>

	<TABLE width=80% BORDER=0 ALIGN=center>
		<!-- Outer Table Starts Here-->
		<logic:present name="PreviewRequestForm" property="requestCompGroupVO"
			scope="request">
			<logic:notEmpty name="PreviewRequestForm"
				property="requestCompGroupVO">
				<bean:define id="modelvo" name="PreviewRequestForm"
					property="requestCompGroupVO" />
				<logic:notEmpty name="modelvo" property="requestCompGrpVO">
					<bean:define id="compgroup" name="modelvo"
						property="requestCompGrpVO"
						type="com.EMD.LSDB.vo.common.RequestCompGrpVO" />
					<bean:define id="comp" name="modelvo" property="requestCompVO"
						type="com.EMD.LSDB.vo.common.RequestCompVO" />
					<TR>
						<TD>
						<TABLE width=80% BORDER=0 ALIGN=center>
							<TR>
								<TD valign=top width="40%" ALIGN=center>
								<FIELDSET CLASS="fieldset5"><LEGEND ALIGN="left"
									CLASS="formSubHeading">Component Group</LEGEND>

								<TABLE WIDTH="100%" BORDER=0 ALIGN=center>

									<TR>
										<TD width="50%"><B> Not Required/New/Existing/Revise/Delete Component Group :<B></TD>
										<TD width="50%"><%= String.valueOf(compgroup.getChangeTypeDesc())%></TD>
									</TR>
								</TABLE>


								<TABLE width="100%" border=1 bordercolor=#DAE2F1 ALIGN=center>
									<TR>

										<TD width="50%" colspan=2 ALIGN=center CLASS="greytext"><b>Change
										From</b></TD>
										<TD width="50%" colspan=2 ALIGN=center CLASS="greytext"><b>Change
										To / Create</b></TD>
									</TR>
									<TR>
										<TD width="25%" ALIGN=left CLASS="headerbgcolor"><b>Component
										Group Name</b></TD>
										<TD WIDTH="25%" class=borderbottomright1><%= (String.valueOf(compgroup.getOldCompGrpName())== "null")? "&nbsp;":String.valueOf(compgroup.getOldCompGrpName())%>
										</TD>
										<TD WIDTH="25%" ALIGN=left CLASS="headerbgcolor"><b>Component
										Group Name</b></TD>
										<TD WIDTH="25%" class=borderbottomright1><logic:equal
											name="compgroup" property="compGrpNameColorFlag" value="Y">
											<font color="red"> <%= (String.valueOf(compgroup.getNewCompGrpName())=="null")? "&nbsp;":String.valueOf(compgroup.getNewCompGrpName())%>
											</font>
										</logic:equal> <logic:notEqual name="compgroup"
											property="compGrpNameColorFlag" value="Y">

											<%= (String.valueOf(compgroup.getNewCompGrpName())=="null")? "&nbsp;":String.valueOf(compgroup.getNewCompGrpName())%>

										</logic:notEqual></TD>
									</TR>
									<TR>
										<TD WIDTH="25%" ALIGN="LEFT" CLASS="headerbgcolor">Characterization</TD>
										<TD WIDTH="25%" class=borderbottomright1><logic:notEmpty
											name="compgroup" property="oldCompGrpChacFlag">
											<logic:equal name="compgroup" property="oldCompGrpChacFlag"
												value="Y">
																Yes
														</logic:equal>
											<logic:equal name="compgroup" property="oldCompGrpChacFlag"
												value="N">
																No
														</logic:equal>
										</logic:notEmpty> <logic:empty name="compgroup"
											property="oldCompGrpChacFlag">
													 &nbsp;
													</logic:empty></TD>
										<TD WIDTH="25%" ALIGN="LEFT" CLASS="headerbgcolor">Characterization</TD>
										<TD WIDTH="25%" class=borderbottomright1><logic:notEmpty
											name="compgroup" property="newCompGrpChacFlag">

											<logic:equal name="compgroup" property="newCompGrpChacFlag"
												value="Y">
												<logic:equal name="compgroup"
													property="compGrpCharColorFlag" value="Y">
													<font color="red"> Yes </font>
												</logic:equal>
											</logic:equal>

											<logic:equal name="compgroup" property="newCompGrpChacFlag"
												value="Y">
												<logic:notEqual name="compgroup"
													property="compGrpCharColorFlag" value="Y">
																Yes
																 </logic:notEqual>
											</logic:equal>


											<logic:equal name="compgroup" property="newCompGrpChacFlag"
												value="N">

												<logic:equal name="compgroup"
													property="compGrpCharColorFlag" value="Y">
													<font color="red"> No </font>
												</logic:equal>

											</logic:equal>


											<logic:equal name="compgroup" property="newCompGrpChacFlag"
												value="N">
												<logic:notEqual name="compgroup"
													property="compGrpCharColorFlag" value="Y">
														
																No
																
														</logic:notEqual>
											</logic:equal>


										</logic:notEmpty> <logic:empty name="compgroup"
											property="newCompGrpChacFlag">
													 &nbsp;
													</logic:empty></TD>
									</TR>

									<TR>
										<TD WIDTH="25%" ALIGN="LEFT" CLASS="headerbgcolor">Required
										Component Group</TD>
										<TD WIDTH="25%" class=borderbottomright1><logic:notEmpty
											name="compgroup" property="oldCompGrpValidFlag">
											<logic:equal name="compgroup" property="oldCompGrpValidFlag"
												value="Y">
																Yes
														</logic:equal>
											<logic:equal name="compgroup" property="oldCompGrpValidFlag"
												value="N">
																No
														</logic:equal>
										</logic:notEmpty> <logic:empty name="compgroup"
											property="oldCompGrpValidFlag">
													 &nbsp;
													</logic:empty></TD>
										<TD WIDTH="25%" ALIGN="LEFT" CLASS="headerbgcolor">Required
										Component Group</TD>
										<TD WIDTH="25%" class=borderbottomright1><logic:notEmpty
											name="compgroup" property="newCompGrpValidFlag">


											<logic:equal name="compgroup" property="newCompGrpValidFlag"
												value="Y">

												<logic:equal name="compgroup"
													property="compGrpValdColorFlag" value="Y">
													<font color="red"> Yes </font>
												</logic:equal>
											</logic:equal>

											<logic:equal name="compgroup" property="newCompGrpValidFlag"
												value="Y">
												<logic:notEqual name="compgroup"
													property="compGrpValdColorFlag" value="Y">
															Yes
														
														</logic:notEqual>
											</logic:equal>

											<logic:equal name="compgroup" property="newCompGrpValidFlag"
												value="N">

												<logic:equal name="compgroup"
													property="compGrpValdColorFlag" value="Y">
													<font color="red"> No </font>
												</logic:equal>
											</logic:equal>

											<logic:equal name="compgroup" property="newCompGrpValidFlag"
												value="N">
												<logic:notEqual name="compgroup"
													property="compGrpValdColorFlag" value="Y">
																No
																</logic:notEqual>
											</logic:equal>

										</logic:notEmpty> <logic:empty name="compgroup"
											property="newCompGrpValidFlag">
													 &nbsp;
													</logic:empty></TD>
									</TR>
								</TABLE>

								</FIELDSET>
								</TD>
							</TR>
						</TABLE>
						</TD>
					</TR>
					<!--Outer table First row ends here -->
					<!-- Added for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151 -->
					<logic:equal name="compgroup" property="changeTypeSeqNo" value="8">
						<jsp:include page="EffectedItems.jsp" />
					</logic:equal>
					<!-- Added for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151 - Ends here -->
					
					<TR>
						<TD>

						<TABLE width=80% BORDER=0 ALIGN=center>
							<TR>
								<TD valign=top width="40%" ALIGN=center>

								<FIELDSET CLASS="fieldset5"><LEGEND ALIGN="left"
									CLASS="formSubHeading">Component</LEGEND>



								<TABLE WIDTH="100%" BORDER=0 ALIGN=center>
									<TR>
										<TD width="50%"><B> Not Required/New/Existing/Revise/Delete Component:<B></TD>
										<TD width="50%"><%= (String.valueOf(comp.getChangeTypeDesc())=="null")?"&nbsp;":String.valueOf(comp.getChangeTypeDesc())%></TD>
									</TR>
								</TABLE>

								<TABLE WIDTH="100%" BORDER=1 bordercolor=#DAE2F1 ALIGN=center>
									<TR>
										<TD width="50%" colspan=2 ALIGN=center CLASS="greytext"><b>Change
										From</b></TD>
										<TD width="50%" colspan=2 ALIGN=center CLASS="greytext"><b>Change
										To / Create</b></TD>
									</TR>

									<TR>

										<TD WIDTH="25%" ALIGN=left CLASS="headerbgcolor"><b>Component/Value</b></TD>
										<TD WIDTH="25%" class=borderbottomright1><%= (String.valueOf(comp.getOldCompName())=="null")?"&nbsp;":String.valueOf(comp.getOldCompName())%></TD>
										<TD WIDTH="25%" ALIGN=left CLASS="headerbgcolor"><b>Component/Value</b></TD>
										<TD WIDTH="25%" class=borderbottomright1><logic:equal
											name="comp" property="compNameColorFlag" value="Y">
											<font color="red"> <%= (String.valueOf(comp.getNewCompName())=="null")?"&nbsp;":String.valueOf(comp.getNewCompName())%>
											</font>
										</logic:equal> <logic:notEqual name="comp"
											property="compNameColorFlag" value="Y">
											<%= (String.valueOf(comp.getNewCompName())=="null")?"&nbsp;":String.valueOf(comp.getNewCompName())%>

										</logic:notEqual></TD>
									</TR>

									<TR>
										<TD WIDTH="25%" ALIGN="LEFT" CLASS="headerbgcolor">Default
										Component</TD>
										<TD WIDTH="25%" class=borderbottomright1><logic:notEmpty
											name="comp" property="oldCompDefaultFlag">
											<logic:equal name="comp" property="oldCompDefaultFlag"
												value="Y">
							Yes
						</logic:equal>
											<logic:equal name="comp" property="oldCompDefaultFlag"
												value="N">
								No
						</logic:equal>
										</logic:notEmpty> <logic:empty name="comp"
											property="oldCompDefaultFlag">
							 &nbsp;
					</logic:empty></TD>
										<TD WIDTH="25%" ALIGN="LEFT" CLASS="headerbgcolor">Default
										Component</TD>
										<TD WIDTH="25%" class=borderbottomright1><logic:notEmpty
											name="comp" property="newCompDefaultFlag">


											<logic:equal name="comp" property="newCompDefaultFlag"
												value="Y">
												<logic:equal name="comp" property="compDefColorFlag"
													value="Y">
													<font color="red"> Yes </font>
												</logic:equal>
											</logic:equal>


											<logic:equal name="comp" property="newCompDefaultFlag"
												value="Y">
												<logic:notEqual name="comp" property="compDefColorFlag"
													value="Y">
						Yes
						</logic:notEqual>
											</logic:equal>


											<logic:equal name="comp" property="newCompDefaultFlag"
												value="N">
												<logic:equal name="comp" property="compDefColorFlag"
													value="Y">
													<font color="red"> No </font>
												</logic:equal>
											</logic:equal>

											<logic:equal name="comp" property="newCompDefaultFlag"
												value="N">
												<logic:notEqual name="comp" property="compDefColorFlag"
													value="Y">
					No
						</logic:notEqual>
											</logic:equal>


										</logic:notEmpty> <logic:empty name="comp"
											property="newCompDefaultFlag">
							 &nbsp;
					</logic:empty></TD>
									</TR>

								</TABLE>
								</FIELDSET>
								</TD>
							</TR>
						</TABLE>

						</TD>
					</TR>
					<!-- Row (Second)for component ends here-->
					
					<!-- Added for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151 -->
					<logic:equal name="comp" property="changeTypeSeqNo" value="8">
						<jsp:include page="EffectedItems.jsp" />
					</logic:equal>
					<!-- Added for CR_80 CR Form Enhancements III on 25-Nov-09 by RR68151 - Ends here -->
					
				</logic:notEmpty>
			</logic:notEmpty>
		</logic:present>
		<bean:define id="ReqList" name="PreviewRequestForm"
			property="requestCompGroupVO" />
		<logic:empty name="ReqList" property="requestCompGrpVO">

			<TR>
				<!--Empty Table First row starts here(ComponentGroup) -->
				<TD>
				<TABLE WIDTH="80%" BORDER=0 ALIGN=center>
					<TR>
						<TD>
						<TABLE>
							<TR>
								<TD valign=top width="40%" ALIGN=center>
								<FIELDSET CLASS="fieldset5"><LEGEND ALIGN="left"
									CLASS="formSubHeading">Component Group</LEGEND>

								<TABLE WIDTH="100%" BORDER=0 ALIGN=center>

									<TR>
										<TD width="50%"><B> Not Required/New/Existing/Revise/Delete Component Group :<B></TD>
										<TD width="50%">&nbsp;</TD>
									</TR>
								</TABLE>


								<TABLE width="100%" border=1 bordercolor=#DAE2F1 ALIGN=center>
									<TR>

										<TD width="50%" colspan=2 ALIGN=center CLASS="greytext"><b>Change
										From</b></TD>
										<TD width="50%" colspan=2 ALIGN=center CLASS="greytext"><b>Change
										To / Create</b></TD>
									</TR>
									<TR>
										<TD width="25%" ALIGN=left CLASS="headerbgcolor"><b>Component
										Group Name</b></TD>
										<TD WIDTH="25%" class=borderbottomright1>&nbsp;</TD>
										<TD WIDTH="25%" ALIGN=left CLASS="headerbgcolor"><b>Component
										Group Name</b></TD>
										<TD WIDTH="25%" class=borderbottomright1>&nbsp;</TD>
									</TR>
									<TR>
										<TD WIDTH="25%" ALIGN="LEFT" CLASS="headerbgcolor">Characterization</TD>
										<TD WIDTH="25%" class=borderbottomright1>&nbsp;</TD>
										<TD WIDTH="25%" ALIGN="LEFT" CLASS="headerbgcolor">Characterization</TD>
										<TD WIDTH="25%" class=borderbottomright1>&nbsp;</TD>
									</TR>

									<TR>
										<TD WIDTH="25%" ALIGN="LEFT" CLASS="headerbgcolor">Required
										Component Group</TD>
										<TD WIDTH="25%" class=borderbottomright1>&nbsp;</TD>
										<TD WIDTH="25%" ALIGN="LEFT" CLASS="headerbgcolor">Required
										Component Group</TD>
										<TD WIDTH="25%" class=borderbottomright1>&nbsp;</TD>
									</TR>
								</TABLE>

								</FIELDSET>


								</TD>
							</TR>
						</TABLE>
						</TD>
					</TR>
					<!--Empty Table First row Ends here(ComponentGroup) -->

					<TR>
						<!--Empty Table second row Starts here(Component) -->
						<TD>

						<TABLE width=100% BORDER=0 ALIGN=center>
							<TR>
								<TD valign=top width="100%" ALIGN=center>

								<FIELDSET CLASS="fieldset5"><LEGEND ALIGN="left"
									CLASS="formSubHeading">Component</LEGEND>



								<TABLE WIDTH="100%" BORDER=0 ALIGN=center>
									<TR>
										<TD width="50%"><B> Not Required/New/Existing/Revise/Delete Component:<B></TD>
										<TD width="50%">&nbsp;</TD>
									</TR>
								</TABLE>

								<TABLE WIDTH="100%" BORDER=1 bordercolor=#DAE2F1 ALIGN=center>
									<TR>
										<TD width="50%" colspan=2 ALIGN=center CLASS="greytext"><b>Change
										From</b></TD>
										<TD width="50%" colspan=2 ALIGN=center CLASS="greytext"><b>Change
										To / Create</b></TD>
									</TR>

									<TR>

										<TD WIDTH="25%" ALIGN=left CLASS="headerbgcolor"><b>Component/Value</b></TD>
										<TD WIDTH="25%" class=borderbottomright1>&nbsp;</TD>
										<TD WIDTH="25%" ALIGN=left CLASS="headerbgcolor"><b>Component/Value</b></TD>
										<TD WIDTH="25%" class=borderbottomright1>&nbsp;</TD>

									</TR>

									<TR>
										<TD WIDTH="25%" ALIGN="LEFT" CLASS="headerbgcolor">Default
										Component</TD>
										<TD WIDTH="25%" class=borderbottomright1>&nbsp;</TD>
										<TD WIDTH="25%" ALIGN="LEFT" CLASS="headerbgcolor">Default
										Component</TD>
										<TD WIDTH="25%" class=borderbottomright1>&nbsp;</TD>
									</TR>

								</TABLE>
								</FIELDSET>
								</TD>
							</TR>
						</TABLE>

						</TD>
					</TR>
					<!--Empty Table second row Ends here(Component) -->


					</logic:empty>

					<logic:present name="PreviewRequestForm"
						property="requestFrmClauseVO">
						<logic:notEmpty name="PreviewRequestForm"
							property="requestClauseVO">


							<logic:iterate id="RequestList" name="PreviewRequestForm"
								property="requestFrmClauseVO"
								type="com.EMD.LSDB.vo.common.ClauseVO">
								<bean:define id="List" name="PreviewRequestForm"
									property="requestClauseVO"
									type="com.EMD.LSDB.vo.common.RequestModelVO" />
								<TR>
									<!-- NonEmpty Row starts for FromClause Part-->
									<TD>

									<TABLE WIDTH="80%" BORDER=0 ALIGN=center>
										<TR>


											<TD valign=top width="40%" ALIGN=center>
											<FIELDSET CLASS="fieldset5"><LEGEND ALIGN="left"
												CLASS="formSubHeading">Change From Clause</LEGEND>


											<TABLE WIDTH="100%" border=0 bordercolor=#DAE2F1>

												<TR>
													<TD nowrap WIDTH="50%"><B> New Clause/Modify Clause/Change
													Default Version/Delete Clause Version/Delete Clause:<B></TD>
													<TD WIDTH="50%" nowrap ALIGN="LEFT"><%= String.valueOf(List.getReqClauseVO().getChangeTypeDesc())%></TD>
												</TR>
											</TABLE>


											<TABLE WIDTH="100%" border=1 bordercolor=#DAE2F1>

												<TR>
													<TD WIDTH="5%" nowrap ALIGN="LEFT" CLASS="headerbgcolor">Clause
													Number</TD>
													<TD><%= String.valueOf(List.getReqClauseVO().getFromClauseNo())%>
													</TD>
												</TR>
												<!-- Added for CR_80 CR Form Enhancements III on 23-Nov-09 by RR68151 -->
												<TR>
													<TD WIDTH="5%" nowrap ALIGN="LEFT" CLASS="headerbgcolor">Clause
													Version</TD>
													<TD><%= (List.getReqClauseVO().getChangeTypeSeqNO() == 10)?"ALL":String.valueOf(List.getReqClauseVO().getFromClauseVerNo())%>
													</TD>
												</TR>
												<!-- Added for CR_80 CR Form Enhancements III on 23-Nov-09 by RR68151 - Ends here -->
												<TR>
													<TD WIDTH="5%" nowrap ALIGN="LEFT" CLASS="headerbgcolor">Clause
													Description</TD>
													<TD>
													<%-- CR-128 - Updated for Pdf issue --%>
														<%if(String.valueOf(RequestList.getClauseDesc()).startsWith("<p>"))
														{%>
															<%=(String.valueOf(RequestList.getClauseDesc()))%>
														<%}else{ %>	
															<%= String.valueOf(RequestList.getClauseDesc()).replaceAll("\n","<br>")%>
														<%}%>
													<%-- CR-128 - Updated for Pdf issue Ends Here--%>
													<table width="100%" BORDER="1" BORDERCOLOR="">
														<logic:notEmpty name="RequestList"
															property="tableArrayData1">
				&nbsp;
				<logic:iterate id="outter" name="RequestList"
																property="tableArrayData1" indexId="counter">

																<bean:define id="row" name="counter" />
																<!-- Added  For CR_93 -->
											<bean:define name="RequestList" property="tableDataColSize" id="tableDataColSize" />
																<tr>
																	<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
																		<logic:equal name="row" value="0">
																			<td valign="top" width="5%" CLASS='borderbottomleft1'>
																			<b><font color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></font></b>
																			</td>
																		</logic:equal>
																		<logic:notEqual name="row" value="0">
																			<td valign="top" width="5%" CLASS='borderbottomleft1'>
																			<%=(tabrow==null)? "&nbsp;":tabrow%></td>
																		</logic:notEqual>
																	</logic:iterate>
																</tr>
															</logic:iterate>
														</logic:notEmpty>
													</table>

													</TD>
												</TR>


												<TR>
													<TD WIDTH="15%" ALIGN="LEFT" CLASS="headerbgcolor">Components
													tied To Clause</TD>

													<logic:notEmpty name="RequestList" property="compName">

														<TD CLASS="borderbottomright1"><logic:iterate id="com"
															name="RequestList" property="compName">
															<bean:write name="com" />
															<br>
														</logic:iterate></TD>

													</logic:notEmpty>
													<logic:empty name="RequestList" property="compName">

														<TD CLASS="borderbottomright1">&nbsp;</TD>
													</logic:empty>

												</TR>


												<TR>
													<TD WIDTH="15%" ALIGN="LEFT" CLASS="headerbgcolor">Engineering
													Data</TD>
													<TD WIDTH="15%" class=borderbottomright1>
													<!--  <logic:notEmpty
														name="RequestList" property="refEDLNO">

														<logic:iterate id="refedl" name="RequestList"
															property="refEDLNO">
															<bean:message key="screen.refEdl" />&nbsp;
													           <bean:write name="refedl" />
															<BR>
														</logic:iterate> 

													</logic:notEmpty>-->
													
													
													 <logic:notEmpty name="RequestList"
														property="edlNO">

														<logic:iterate id="edl" name="RequestList"
															property="edlNO">
															<bean:message key="screen.edl" />&nbsp;
	           <bean:write name="edl" />
															<BR>
														</logic:iterate>

													</logic:notEmpty>
													<!-- CR 87 -->
													<logic:notEmpty
														name="RequestList" property="refEDLNO">

														<logic:iterate id="refedl" name="RequestList"
															property="refEDLNO">
															<bean:message key="screen.refEdl.start" />&nbsp;
													           <bean:write name="refedl" />
													           <bean:message key="screen.refEdl.end" />
															<BR>
														</logic:iterate> 

													</logic:notEmpty>
													 <logic:notEmpty name="RequestList"
														property="partOF">

														<logic:iterate id="code" name="RequestList"
															property="partOF">
															<bean:message key="screen.partOf" />&nbsp;
	           <bean:write name="code" />
															<BR>
														</logic:iterate>

													</logic:notEmpty> <logic:greaterThan name="RequestList"
														property="dwONumber" value="0">
														<bean:message key="screen.dwoNo" />  &nbsp;
	           <bean:write name="RequestList" property="dwONumber" />
														<BR>
													</logic:greaterThan> <logic:greaterThan name="RequestList"
														property="partNumber" value="0">
														<bean:message key="screen.partNo" /> &nbsp;
	            <bean:write name="RequestList" property="partNumber" />
														<BR>
													</logic:greaterThan> <logic:greaterThan name="RequestList"
														property="priceBookNumber" value="0">
														<bean:message key="screen.priceBookNo" /> &nbsp;
	            <bean:write name="RequestList" property="priceBookNumber" />
														<BR>
													</logic:greaterThan> <logic:present name="RequestList"
														property="standardEquipmentDesc">
														<bean:write name="RequestList"
															property="standardEquipmentDesc" />
														<BR>
													</logic:present> <logic:present name="RequestList"
														property="comments">
														<bean:write name="RequestList" property="comments" />
														<BR>
													</logic:present></TD>
												</TR>


											</TABLE>
											</FIELDSET>
											</TD>
										</TR>
									</TABLE>

									</TD>
								</TR>
								<!-- NonEmpty Row Ends for FromClause Part-->
							</logic:iterate>
						</logic:notEmpty>
					</logic:present>

					<logic:notPresent name="PreviewRequestForm"
						property="requestFrmClauseVO">
						<TR>
							<!-- Empty Row Starts for FromClause Part-->
							<TD>

							<TABLE WIDTH="80%" BORDER=0 ALIGN=center>
								<TR>
									<TD valign=top width="40%" ALIGN=center>
									<FIELDSET CLASS="fieldset5"><LEGEND ALIGN="left"
										CLASS="formSubHeading">Change From Clause</LEGEND>
									<TABLE WIDTH="100%" border=0 bordercolor=#DAE2F1>
										<TR>
											<TD nowrap WIDTH="50%"><B> New Clause/Modify Clause/Change
											Default Version/Delete Clause Version/Delete Clause:<B></TD>
											<logic:present name="PreviewRequestForm"
												property="requestClauseVO">
												<bean:define id="List" name="PreviewRequestForm"
													property="requestClauseVO"
													type="com.EMD.LSDB.vo.common.RequestModelVO" />
												<logic:notEmpty name="List" property="reqClauseVO">
													<bean:define id="sublist" name="List"
														property="reqClauseVO"
														type="com.EMD.LSDB.vo.common.ReqClauseVO" />

													<logic:empty name="sublist" property="changeTypeDesc">
														<TD WIDTH="50%" nowrap ALIGN="LEFT">&nbsp;</TD>
													</logic:empty>
													<logic:notEmpty name="sublist" property="changeTypeDesc">
														<TD WIDTH="50%" nowrap ALIGN="LEFT"><%= String.valueOf(sublist.getChangeTypeDesc())%>
														</TD>
													</logic:notEmpty>
												</logic:notEmpty>

											</logic:present>

										</TR>
									</TABLE>

									<TABLE WIDTH="100%" border=1 bordercolor=#DAE2F1>

										<TR>
											<TD WIDTH="5%" nowrap ALIGN="LEFT" CLASS="headerbgcolor">Clause
											Number</TD>
											<TD>&nbsp;</TD>
										</TR>

										<!-- Added for CR_80 CR Form Enhancements III on 23-Nov-09 by RR68151-->
										<TR>
											<TD WIDTH="5%" nowrap ALIGN="LEFT" CLASS="headerbgcolor">Clause
											Version</TD>
											<TD>&nbsp;</TD>
										</TR>		
										<!-- Added for CR_80 CR Form Enhancements III on 23-Nov-09 by RR68151 - Ends here -->
										
										<TR>
											<TD WIDTH="5%" nowrap ALIGN="LEFT" CLASS="headerbgcolor">Clause
											Description</TD>
											<TD>&nbsp;</TD>
										</TR>

										<TR>
											<TD WIDTH="15%" ALIGN="LEFT" CLASS="headerbgcolor">Components
											tied To Clause</TD>
											<TD CLASS="borderbottomright1">&nbsp;</TD>
										</TR>

										<TR>
											<TD WIDTH="15%" ALIGN="LEFT" CLASS="headerbgcolor">Engineering
											Data</TD>
											<TD WIDTH="15%" class=borderbottomright1>&nbsp;</TD>
										</TR>
									</TABLE>
									</FIELDSET>
									</TD>
								</TR>
							</TABLE>

							</TD>
						</TR>
						<!-- Empty Row Ends for FromClause Part-->
					</logic:notPresent>

					<logic:present name="PreviewRequestForm" property="requestClauseVO"
						scope="request">
						<logic:notEmpty name="PreviewRequestForm"
							property="requestClauseVO">
							<bean:define id="List" name="PreviewRequestForm"
								property="requestClauseVO"
								type="com.EMD.LSDB.vo.common.RequestModelVO" />
							<logic:notEmpty name="List" property="reqClauseVO">
								<bean:define id="sublist" name="List" property="reqClauseVO"
									type="com.EMD.LSDB.vo.common.ReqClauseVO" />
								<TR>
									<!-- NonEmpty Row Starts for ToClause Part-->
									<TD>

									<TABLE WIDTH="80%" BORDER=0 ALIGN=center>
										<TR>
											<TD valign=top width="40%" ALIGN=center>
											<FIELDSET CLASS="fieldset5"><LEGEND ALIGN="left"
												CLASS="formSubHeading">Change To / Create Clause</LEGEND>

											<TABLE WIDTH="100%" border=1 bordercolor=#DAE2F1>


												<TR>

													<TD WIDTH="5%" ALIGN="LEFT" CLASS="headerbgcolor">Parent
													Clause</TD>
													<TD WIDTH="15%" class=borderbottomright1><logic:notEmpty
														name="sublist" property="toParentClaDesc">

														<%= String.valueOf(List.getReqClauseVO().getToParentClauseNo())%>
														<BR>
														<%-- CR-128 - Updated for Pdf issue --%>
															<%if(String.valueOf(List.getReqClauseVO().getToParentClaDesc()).startsWith("<p>"))
															{%>
																<%=(String.valueOf(List.getReqClauseVO().getToParentClaDesc()))%>
															<%}else{ %>	
																<%= String.valueOf(List.getReqClauseVO().getToParentClaDesc()).replaceAll("\n","<br>")%>
															<%}%>
														<%-- CR-128 - Updated for Pdf issue Ends Here--%>
												</logic:notEmpty> <logic:empty name="sublist"
														property="toParentClaDesc">&nbsp;</logic:empty></TD>
												</TR>


												<TR>
													<TD WIDTH="5%" ALIGN="LEFT" CLASS="headerbgcolor">Clause
													Version</TD>
													<TD WIDTH="15%" class=borderbottomright1><logic:greaterThan
														name="sublist" property="toClauseVersionNo" value="0">
														<logic:equal name="sublist" property="versionFlag"
															value="Y">
															<font color="red"> <%=String.valueOf(List.getReqClauseVO().getToClauseVersionNo())%><BR>
															<%-- CR-128 - Updated for Pdf issue --%>
																<%if(String.valueOf(List.getReqClauseVO().getToClaVerDesc()).startsWith("<p>"))
																{%>
																	<%=(String.valueOf(List.getReqClauseVO().getToClaVerDesc()))%>
																<%}else{ %>	
																	<%=String.valueOf(List.getReqClauseVO().getToClaVerDesc()).replaceAll("\n","<br>")%>
																<%}%>
															<%-- CR-128 - Updated for Pdf issue Ends Here--%>
															</font>
														</logic:equal>
													</logic:greaterThan> <logic:greaterThan name="sublist"
														property="toClauseVersionNo" value="0">
														<logic:notEqual name="sublist" property="versionFlag"
															value="Y">
															<%=String.valueOf(List.getReqClauseVO().getToClauseVersionNo())%>
															<BR>
															<%-- CR-128 - Updated for Pdf issue --%>
																<%if(String.valueOf(List.getReqClauseVO().getToClaVerDesc()).startsWith("<p>"))
																{%>
																	<%=(String.valueOf(List.getReqClauseVO().getToClaVerDesc()))%>
																<%}else{ %>	
																	<%=String.valueOf(List.getReqClauseVO().getToClaVerDesc()).replaceAll("\n","<br>")%>
																<%}%>
															<%-- CR-128 - Updated for Pdf issue Ends Here--%>
														</logic:notEqual>
													</logic:greaterThan> <logic:equal name="sublist"
														property="toClauseVersionNo" value="0">&nbsp;</logic:equal>
													</TD>
												</TR>

												<TR>
													<TD WIDTH="5%" nowrap ALIGN="LEFT" CLASS="headerbgcolor">New
													Clause Number</TD>
													<TD WIDTH="15%" class=borderbottomright1><logic:notEmpty
														name="sublist" property="toClauseNo">
														<logic:equal name="sublist" property="claNoFlag" value="Y">
															<font color="red"> <%= String.valueOf(List.getReqClauseVO().getToClauseNo())%>
															</font>
														</logic:equal>

														<logic:notEqual name="sublist" property="claNoFlag"
															value="Y">
															<%= String.valueOf(List.getReqClauseVO().getToClauseNo())%>
														</logic:notEqual>

													</logic:notEmpty> <logic:notEmpty name="sublist"
														property="toClauseNo">

													</logic:notEmpty> <logic:empty name="sublist"
														property="toClauseNo">&nbsp;</logic:empty></TD>
												</TR>


												<TR>

													<TD WIDTH="5%" nowrap ALIGN="LEFT" CLASS="headerbgcolor">
													New Clause Description</TD>

													<TD WIDTH="15%" class=borderbottomright1><!--<bean:define id="clause" name="List" property="reqClauseVO"/>-->
													<bean:define id="sublist" name="List"
														property="reqClauseVO"
														type="com.EMD.LSDB.vo.common.ReqClauseVO" /> <logic:notEmpty
														name="sublist" property="toClauseDesc">
														<logic:equal name="sublist" property="claDescFlag"
															value="Y">
															<font color="red"> 
															<%-- CR-128 - Updated for Pdf issue --%>
																<%if(String.valueOf(List.getReqClauseVO().getToClauseDesc()).startsWith("<p>"))
																{%>
																	<%=(String.valueOf(List.getReqClauseVO().getToClauseDesc()))%>
																<%}else{ %>	
																	<%= String.valueOf(List.getReqClauseVO().getToClauseDesc()).replaceAll("\n","<br>")%>
																<%}%>
															<%-- CR-128 - Updated for Pdf issue Ends Here--%>
															</font>
														</logic:equal>
													</logic:notEmpty> <logic:notEmpty name="sublist"
														property="toClauseDesc">
														<logic:notEqual name="sublist" property="claDescFlag"
															value="Y">
															<%-- CR-128 - Updated for Pdf issue --%>
																<%if(String.valueOf(List.getReqClauseVO().getToClauseDesc()).startsWith("<p>"))
																{%>
																	<%=(String.valueOf(List.getReqClauseVO().getToClauseDesc()))%>
																<%}else{ %>	
																	<%= String.valueOf(List.getReqClauseVO().getToClauseDesc()).replaceAll("\n","<br>")%>
																<%}%>
															<%-- CR-128 - Updated for Pdf issue Ends Here--%>
														</logic:notEqual>

													</logic:notEmpty>


													<table width="100%" BORDER="1" BORDERCOLOR="">

														<bean:define id="tdlist" name="List"
															property="reqClauseVO"
															type="com.EMD.LSDB.vo.common.ReqClauseVO" />
														<logic:notEmpty name="tdlist" property="tableData">
				&nbsp;
				<logic:iterate id="outter" name="tdlist" property="tableData"
																indexId="counter">

																<bean:define id="row" name="counter" />
																					<!-- Added  For CR_93 -->
											<bean:define name="tdlist" property="tableDataColSize" id="tableDataColSize" />
																<tr>
																	<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
																		<logic:equal name="row" value="0">
																			<td valign="top" width="5%" CLASS='borderbottomleft1'>

																			<b><font color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></font></b>
																			</td>
																		</logic:equal>
																		<logic:notEqual name="row" value="0">
																			<td valign="top" width="5%" CLASS='borderbottomleft1'>
																			<%=(tabrow==null)? "&nbsp;":tabrow%></td>
																		</logic:notEqual>
																	</logic:iterate>
																</tr>
															</logic:iterate>
														</logic:notEmpty>
													</table>
													</TD>

												</TR>

												<!-- Removed for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151 
												<TR>
													<TD WIDTH="15%" ALIGN="LEFT" CLASS="headerbgcolor">Default
													Clause</TD>
													<TD class=borderbottomright1><logic:equal name="sublist"
														property="toDefaultFlag" value="Y">
		
			Yes
			
		</logic:equal> <logic:equal name="sublist" property="toDefaultFlag"
														value="N">
		
			No
			
		</logic:equal></TD>
												</TR>
												--Ends here-->

												<TR>
													<TD WIDTH="15%" ALIGN="LEFT" CLASS="headerbgcolor">Components
													tied To Clause</TD>
													<TD class=borderbottomright1><logic:notEmpty name="sublist"
														property="claComp">
														<logic:iterate id="compList" name="sublist"
															property="claComp">
															<bean:write name="compList" />
															<BR>
														</logic:iterate>
													</logic:notEmpty> <logic:empty name="sublist"
														property="claComp">
				&nbsp;	
				</logic:empty></TD>
												</TR>


												<TR>
													<TD WIDTH="15%" ALIGN="LEFT" CLASS="headerbgcolor">Engineering
													Data</TD>
													<TD WIDTH="15%" class=borderbottomright1>
													<!-- <logic:notEmpty
														name="tdlist" property="refEdlNo">

														<logic:iterate id="refedl" name="tdlist"
															property="refEdlNo" indexId="count">
															<bean:message key="screen.refEdl" />&nbsp;
															<!-- Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151 -->
													<!-- 		<logic:iterate id="refEdlClr" name="tdlist"
															property="refEdlClrFlag" offset="count" length="1">					
																<logic:equal name="refEdlClr" value="Y"><font color="red">
																		<bean:write name="refedl" /></font>
			           											</logic:equal>
			           											<logic:equal name="refEdlClr" value="N">
																		<bean:write name="refedl" />
	           													</logic:equal>
															</logic:iterate>
															<BR>
														</logic:iterate>

													</logic:notEmpty> -->
													
													 <logic:notEmpty name="tdlist"
														property="edlNo">

														<logic:iterate id="edl" name="tdlist" property="edlNo" indexId="count">
															<bean:message key="screen.edl" />&nbsp;
															<!-- Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151 -->
															<logic:iterate id="edlClr" name="tdlist"
															property="edlClrFlag" offset="count" length="1">							
																<logic:equal name="edlClr" value="Y"><font color="red">
	           															<bean:write name="edl" /></font>
			           											</logic:equal>
			           											<logic:equal name="edlClr" value="N">
																		<bean:write name="edl" />
	           													</logic:equal>
	           												</logic:iterate>	
															<BR>
														</logic:iterate>

													</logic:notEmpty> 
													<!-- CR 87 -->
													<logic:notEmpty
														name="tdlist" property="refEdlNo">

														<logic:iterate id="refedl" name="tdlist"
															property="refEdlNo" indexId="count">
															<bean:message key="screen.refEdl.start" />&nbsp;
															<!-- Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151 -->
															<logic:iterate id="refEdlClr" name="tdlist"
															property="refEdlClrFlag" offset="count" length="1">					
																<logic:equal name="refEdlClr" value="Y"><font color="red">
																		<bean:write name="refedl" /></font>
			           											</logic:equal>
			           											<logic:equal name="refEdlClr" value="N">
																		<bean:write name="refedl" />
	           													</logic:equal>
															</logic:iterate>
															<bean:message key="screen.refEdl.end" />
															<BR>
														</logic:iterate>

													</logic:notEmpty>
													
													
													<logic:notEmpty name="tdlist"
														property="partClaNo">

														<logic:iterate id="code" name="tdlist"
															property="partClaNo"  indexId="count">
															<bean:message key="screen.partOf" />&nbsp;
															<!-- Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151 -->
															<logic:iterate id="partOfClr" name="tdlist"
															property="partOfClrFlag" offset="count" length="1">							
																<logic:equal name="partOfClr" value="Y"><font color="red">
	          															<bean:write name="code" /></font>
			           											</logic:equal>
			           											<logic:equal name="partOfClr" value="N">
																		<bean:write name="code" />
	           													</logic:equal>
	           												</logic:iterate>	
															<BR>
														</logic:iterate>

													</logic:notEmpty> <logic:greaterThan name="tdlist"
														property="todwONumber" value="0">
														<bean:message key="screen.dwoNo" />  &nbsp;
													<!-- Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151 -->
														<logic:equal name="tdlist" property="dwoNoClrFlag"
															value="Y"><font color="red">
	           													<bean:write name="tdlist" property="todwONumber" /></font>
	           											</logic:equal>
	           											<logic:equal name="tdlist" property="dwoNoClrFlag"
															value="N">
	           													<bean:write name="tdlist" property="todwONumber" />
	           											</logic:equal>
	           											
														<BR>
													</logic:greaterThan> <logic:greaterThan name="tdlist"
														property="toPartNumber" value="0">
														<bean:message key="screen.partNo" /> &nbsp;
														<!-- Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151 -->
														<logic:equal name="tdlist" property="partNoClrFlag"
															value="Y"><font color="red">
	            												<bean:write name="tdlist" property="toPartNumber" /></font>
	            										</logic:equal>
	           											<logic:equal name="tdlist" property="partNoClrFlag"
															value="N">
	           													<bean:write name="tdlist" property="toPartNumber" />
	           											</logic:equal>
														<BR>
													</logic:greaterThan> <logic:greaterThan name="tdlist"
														property="topriceBookNumber" value="0">
														<bean:message key="screen.priceBookNo" /> &nbsp;
														<!-- Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151 -->
														<logic:equal name="tdlist" property="priceBookClrFlag"
															value="Y"><font color="red">
	            												<bean:write name="tdlist" property="topriceBookNumber" /></font>
	            										</logic:equal>
	           											<logic:equal name="tdlist" property="priceBookClrFlag"
															value="N">
	           													<bean:write name="tdlist" property="topriceBookNumber" />
	           											</logic:equal>
														<BR>
													</logic:greaterThan> <logic:present name="tdlist"
														property="toStdEqpDesc">
														<!-- Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151 -->
														<logic:equal name="tdlist" property="stdEqpClrFlag"
															value="Y"><font color="red">
														<bean:write name="tdlist" property="toStdEqpDesc" /></font>
	            										</logic:equal>
	           											<logic:equal name="tdlist" property="stdEqpClrFlag"
															value="N">
	           											<bean:write name="tdlist" property="toStdEqpDesc" />
	           											</logic:equal>
														<BR>
													</logic:present> <logic:present name="tdlist"
														property="toComments">
														<!-- Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151 -->
														<logic:equal name="tdlist" property="enggDataClrFlag"
															value="Y"><font color="red">
														<bean:write name="tdlist" property="toComments" /></font>
	            										</logic:equal>
	           											<logic:equal name="tdlist" property="enggDataClrFlag"
															value="N">
	           											<bean:write name="tdlist" property="toComments" />
	           											</logic:equal>
														<BR>
													</logic:present></TD>
												</TR>
											</TABLE>
											</FIELDSET>
											</TD>
										</TR>

									</TABLE>

									</TD>
								</TR>
								<!-- NonEmpty Row Ends for ToClause Part-->
							</logic:notEmpty>
						</logic:notEmpty>
					</logic:present>

					<bean:define id="RequestList" name="PreviewRequestForm"
						property="requestClauseVO" />
					<logic:empty name="RequestList" property="reqClauseVO">
						<TR>
							<!-- Empty Row Starts for ToClause Part-->
							<TD>

							<TABLE WIDTH="80%" BORDER=0 ALIGN=center>
								<TR>
									<TD valign=top width="40%" ALIGN=center>
									<FIELDSET CLASS="fieldset5"><LEGEND ALIGN="left"
										CLASS="formSubHeading">Change To / Create Clause</LEGEND>
									<TABLE WIDTH="100%" border=1 bordercolor=#DAE2F1>
										<TR>

											<TD WIDTH="5%" ALIGN="LEFT" CLASS="headerbgcolor">Parent
											Clause</TD>
											<TD WIDTH="15%" class=borderbottomright1>&nbsp;</TD>
										</TR>


										<TR>
											<TD WIDTH="5%" ALIGN="LEFT" CLASS="headerbgcolor">Clause
											Version</TD>
											<TD WIDTH="15%" class=borderbottomright1>&nbsp;</TD>
										</TR>

										<TR>
											<TD WIDTH="5%" nowrap ALIGN="LEFT" CLASS="headerbgcolor">New
											Clause Number</TD>
											<TD WIDTH="15%" class=borderbottomright1>&nbsp;</TD>
										</TR>


										<TR>
											<TD WIDTH="5%" nowrap ALIGN="LEFT" CLASS="headerbgcolor">New
											Clause Description</TD>
											<TD WIDTH="15%" class=borderbottomright1>&nbsp;</TD>

										</TR>

										<!-- Removed for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151 
										<TR>
											<TD WIDTH="15%" ALIGN="LEFT" CLASS="headerbgcolor">Default
											Clause</TD>
											<TD class=borderbottomright1>&nbsp;&nbsp; &nbsp;</TD>
										</TR>
										-- Ends here-->

										<TR>
											<TD WIDTH="15%" ALIGN="LEFT" CLASS="headerbgcolor">Components
											tied To Clause</TD>
											<TD class=borderbottomright1>&nbsp;</TD>
										</TR>


										<TR>
											<TD WIDTH="15%" ALIGN="LEFT" CLASS="headerbgcolor">Engineering
											Data</TD>
											<TD WIDTH="15%" class=borderbottomright1>&nbsp;</TD>
										</TR>


									</TABLE>
									</FIELDSET>
									</TD>
								</TR>
							</TABLE>

							</TD>
						</TR>
						<!-- Empty Row Ends for ToClause Part-->
					</logic:empty>


					<TR>
						<!--Reason row Starts here -->
						<TD>
						<TABLE WIDTH="80%" border=0 align="center">
							<TR>
								<TD width="35%">
								<FIELDSET CLASS="fieldset5"><LEGEND ALIGN="left"
									CLASS="formSubHeading">Reason</LEGEND>


								<TABLE WIDTH="100%" ALIGN=CENTER border=1 bordercolor=#DAE2F1>
									<logic:present name="PreviewRequestForm" property="requestList"
										scope="request">
										<logic:iterate id="RequeList" name="PreviewRequestForm"
											property="requestList"
											type="com.EMD.LSDB.vo.common.RequestVO" scope="request">

											<TR>
												<TD WIDTH="25%" CLASS="headerbgcolor" ALIGN=left>Reason for
												change</TD>

												<TD WIDTH="75%" class=borderbottomright1 ALIGN=left><bean:write
													name="RequeList" property="reason" /></TD>

											</TR>





											<!-- Added for CR-59 by VV49326 25-Nov-08 Starts Here -->


											<logic:notEqual name="RequeList" property="statusTypeSeqNo"
												value="4">

												<logic:notEqual name="RequeList" property="statusTypeSeqNo"
													value="6">
													<logic:notEmpty name="RequeList" property="adminComments">

														<TR>
															<TD WIDTH="32%" CLASS="headerbgcolor" ALIGN=left>Administrator
															Comments</TD>

															<TD WIDTH="68%" class=borderbottomright1 ALIGN=left><bean:write
																name="RequeList" property="adminComments" /></TD>

														</TR>

													</logic:notEmpty>

												</logic:notEqual>

											</logic:notEqual>

											<!-- Added for CR-59 by VV49326 25-Nov-08 Ends Here -->


										</logic:iterate>
									</logic:present>

								</TABLE>

								</FIELDSET>
								</TD>
							</TR>
						</TABLE>
						</TD>
					</TR>
					<!--Reason row Ends here -->

					<TR>
						<!--Buttons row Starts here -->
						<TD>
						<TABLE BORDER=0 WIDTH="55%" ALIGN=CENTER>
							<TR>
								<TD width="50%">&nbsp;</TD>
								<TD width="50%">&nbsp;</TD>
							</TR>
							<TR>
								<TD ALIGN=right><INPUT CLASS="buttonStyle" TYPE=button
									name="btnAdd" value="Print" onclick="javascript:fnPrint()">
								<TD WIDTH="12%" ALIGN=left><INPUT CLASS="buttonStyle"
									TYPE=button name="btnAdd" value="Cancel"
									onclick="javascript:fnclose()"></TD>
								<TD>&nbsp;</TD>
							</TR>
						</TABLE>
						</TD>
					</TR>
					<!--Buttons row Ends here -->
				</TABLE>
				<!--Outer Table Ends Here--> </html:form>
</BODY>
</html:html>
