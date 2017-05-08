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
<SCRIPT LANGUAGE="JavaScript">
function Cancelpopup()
{
 window.close();
}
</SCRIPT>



<SCRIPT LANGUAGE="JavaScript">
function fnShowPDF(ImageSeqNo,ImageName){
		
		document.forms[0].action="DownLoadImage.do?method=downloadPDF&ImageSeqNo="+ImageSeqNo+"&ImageName="+escape(ImageName);
		document.forms[0].submit();
		
	}
</SCRIPT>

</HEAD>

<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>

<BODY CLASS="main">

<html:form action="/ViewSpecByOrder" method="post"
	enctype="multipart/form-data">

	<TABLE BORDER=0 WIDTH="100%" ALIGN=center>
		<TR>
			<TD WIDTH="10%">&nbsp;</TD>
		</TR>
		<TR>
			<TD WIDTH="80%">
			<center>
			<DIV CLASS="dashBoardSubHeading"><bean:message
				key="Application.Screen.ViewSpec" /></DIV>
			</center>
			</TD>
		</TR>
		<TR>
			<TD>&nbsp;</TD>
		</TR>
	</TABLE>

	<logic:present name="ViewSpecByOrderForm" property="mainFeaList"
		scope="request">
		<bean:size name="ViewSpecByOrderForm" id="compsize"
			property="mainFeaList" />
	</logic:present>

	<logic:present name="ViewSpecByOrderForm" property="orderDefCompsList"
		scope="request">
		<bean:size name="ViewSpecByOrderForm" id="defcompsize"
			property="orderDefCompsList" />
	</logic:present>

	<logic:present name="ViewSpecByOrderForm" property="orderList"
		scope="request">
		<logic:iterate id="ordList" name="ViewSpecByOrderForm"
			property="orderList" type="com.EMD.LSDB.vo.common.OrderVO"
			scope="request">
			<TABLE ALIGN=center WIDTH="95%" BORDER=0>
				<TR>
					<TD WIDTH="85%">
					<TABLE bgcolor=#D2DDF9 WIDTH="100%">
						<TR>
							<TD ALIGN=left><span CLASS=greytext1>Spec Status :</span><bean:write
								name="ordList" property="statusDesc" /></TD>
							<TD ALIGN=left><span CLASS=greytext1>Model :</span> <bean:write
								name="ordList" property="modelName" /></TD>
							<TD ALIGN=left><span CLASS=greytext1>SAP Customer Code :</span> <bean:write
								name="ordList" property="sapCusCode" /></TD>
							<TD ALIGN=left><span CLASS=greytext1>Order Number : </span><bean:write
								name="ordList" property="orderNo" /></TD>
						</TR>
					</TABLE>
					</TD>
				<TR>
					<TD>&nbsp;</TD>
				</TR>
			</TABLE>
		</logic:iterate>
	</logic:present>


	<TABLE WIDTH="100%" BORDER=0>
		<TR>
			<A name="GeneralInfo"></A>
			<TD WIDTH="100%" ALIGN=center><!--<html:link href="#GeneralInfo">
			      <font Class="linkstyleItem">General Info</font> 
				</html:link>&nbsp;|
			    <logic:iterate id="section" name="ViewSpecByOrderForm"   property="sectionList" type="com.EMD.LSDB.vo.common.SectionVO" scope="request" indexId="counters">
					<logic:notEqual name="section" property="colourFlag" value="Y">
						
						<A href="#section<%=counters%>" Class="linkstyleItem">
							<bean:write name="section" property="sectionCode"/>.
							<bean:write name="section" property="sectionName"/>
						</A>&nbsp;|
					</logic:notEqual>

					<logic:equal name="section" property="colourFlag" value="Y">
						
						<A href="#section<%=counters%>" Class="linkstyleItem">
							<font color="red"><bean:write name="section" property="sectionCode"/>.
								<bean:write name="section" property="sectionName"/></font>
						</A>&nbsp;|
					</logic:equal>
				</logic:iterate>
				<html:link href="#Performance">
			      <font Class="linkstyleItem">Performance Curve</font>
				</html:link>&nbsp;--></TD>
		</TR>
	</TABLE>


	<TABLE WIDTH="100%" ALIGN=CENTER>
		<TR>
			<TD>
			<TABLE BORDER=0 ALIGN=center WIDTH="96%" CELLSPACING=1 CELLPADDING=0>
				<TR>
					<TH WIDTH="82%" CLASS=lightblue2>General Info</TH>
				</TR>
				<TR>
					<td></td>
				</TR>
				<TR>
					<td></td>
				</TR>
				<TR>
					<td></td>
				</TR>
				<TR>
					<TH WIDTH="82%" CLASS=lightblue2>Main Features Information</TH>
				</TR>
				<TR>
					<TD>
					<TABLE WIDTH="100%" ALIGN=CENTER BORDERCOLOR="#5780ae" BORDER=1>
						<TR>
						    <!--Added for CR-74 VV49326 09-06-09-->
						    <TD WIDTH="7%" CLASS=table_heading>Revision No</TD>
							<TD WIDTH="93%" CLASS=table_heading>Description</TD>
						</TR>


						<logic:greaterThan name="defcompsize" value="0">
							<logic:iterate id="ordDefComp" name="ViewSpecByOrderForm"
								property="orderDefCompsList"
								type="com.EMD.LSDB.vo.common.MainFeatureInfoVO" scope="request">
								<TR>
								    <!--Added for CR-74 VV49326 09-06-09-->
								    <TD CLASS=borderbottomleft>
								    <logic:empty name="ordDefComp" property="revMarkers">
								    &nbsp;
								    </logic:empty>
								    <logic:notEmpty name="ordDefComp" property="revMarkers">
								    <logic:iterate id="revMarker" name="ordDefComp" property="revMarkers">
								    <bean:write name="revMarker"/><BR>
								    </logic:iterate>
								    </logic:notEmpty>
								    <!--Added for CR-74 VV49326 09-06-09-->
								    
								    </TD>
									<TD CLASS=borderbottomleft><bean:write name="ordDefComp"
										property="compDesc" /></TD>
								</TR>

							</logic:iterate>



						</logic:greaterThan>


						<logic:greaterThan name="compsize" value="0">

							<logic:iterate id="MainFeature" name="ViewSpecByOrderForm"
								property="mainFeaList"
								type="com.EMD.LSDB.vo.common.MainFeatureInfoVO" scope="request">
								<TR>
								    <!--Added for CR-74 VV49326 09-06-09-->
								    <TD CLASS=borderbottomleft>
								    <logic:empty name="MainFeature" property="revMarkers">
								    &nbsp;
								    </logic:empty>
								    <logic:notEmpty name="MainFeature" property="revMarkers">
								    <logic:iterate id="revMarker" name="MainFeature" property="revMarkers">
								    <bean:write name="revMarker"/><BR>
								    </logic:iterate>
								    </logic:notEmpty>
								    <!--Added for CR-74 VV49326 09-06-09-->
									<TD CLASS=borderbottomleft><bean:write name="MainFeature"
										property="componentDesc" /></TD>
								</TR>
							</logic:iterate>



						</logic:greaterThan>

					</TABLE>

					</TD>



				</TR>
				<TR>
					<td></td>
				</TR>
				<TR>
					<td></td>
				</TR>
				<TR>
					<td></td>
				</TR>
				<TR>
					<TH WIDTH="82%" CLASS=lightblue2>Specifications</TH>
				</TR>
				<TR>
					<TD></TD>
				</TR>
			</TABLE>



			<logic:iterate id="OrderSpecDetailsId" name="ViewSpecByOrderForm"
				property="specList" type="com.EMD.LSDB.vo.common.SpecificationVO">

				<logic:notEqual name="OrderSpecDetailsId" property="specName"
					value="">
					<TABLE WIDTH="96%" ALIGN="CENTER" BORDER="0" CELLSPACING="1"
						CELLPADDING="1">
						<TR>
							<TD WIDTH="81%" CLASS="headerbgcolor" ALIGN="CENTER"><bean:write
								name="OrderSpecDetailsId" property="specName" /></TD>
						</TR>
					</TABLE>

					<logic:notEmpty name="OrderSpecDetailsId" property="specItem">
						<TABLE WIDTH="96%" align=center BORDERCOLOR="#5780ae" border="1">
							<tr>
							    <!--Added for CR-74 VV49326 09-06-09-->
							    <td WIDTH="7%"  CLASS="table_heading">Revision No</td>
								<td WIDTH="63%" CLASS="table_heading">Description</td>
								<td WIDTH="15%" CLASS="table_heading">Value</td>
							</tr>
							</logic:notEmpty>
							<logic:iterate id="OrderItemDetailsId" name="OrderSpecDetailsId"
								property="specItem"
								type="com.EMD.LSDB.vo.common.SpecificationItemVO">
								<tr>
								    <TD class="borderbottomleft">
								    <!--Added for CR-74 VV49326 09-06-09 - Starts Here-->
				                                        <logic:empty name="OrderItemDetailsId" property="itemMarker">
							                             &nbsp;
							                            </logic:empty>
							                            <logic:notEmpty name="OrderItemDetailsId" property="itemMarker">
								                            <logic:iterate id="revMarker" name="OrderItemDetailsId" property="itemMarker">
								                            <bean:write name="revMarker"/><BR>
								                            </logic:iterate>
							                            </logic:notEmpty>                          
				                                    <!--Added for CR-74 VV49326 09-06-09 - Ends Here--></TD>
									<TD class="borderbottomleft"><bean:write
										name="OrderItemDetailsId" property="itemDesc" /></TD>
									<TD class="borderbottomleft">
									<!--Added for CR-74 VV49326 09-06-09-->
									<logic:empty name="OrderItemDetailsId" property="itemValue">
									&nbsp;
									</logic:empty>
									<logic:notEmpty name="OrderItemDetailsId" property="itemValue">
									<bean:write name="OrderItemDetailsId" property="itemValue" />
									</logic:notEmpty>
									 <!--Added for CR-74 VV49326 09-06-09 - Ends Here-->
									</TD>
								</tr>
							</logic:iterate>
						</TABLE>
				</logic:notEqual>
			</logic:iterate> <!-- Added for LSDB_CR_46 PM&I Change --> <!-- General Arrangement should be displayed for Locomotive order-->
			<logic:present name="ViewSpecByOrderForm" property="specTypeNo"
				scope="request">
				<logic:equal name="ViewSpecByOrderForm" property="specTypeNo"
					value="1">
					<TABLE BORDER=0 ALIGN=center WIDTH="96%" CELLSPACING=1
						CELLPADDING=0>
						<TR>
							<TD>&nbsp;</TD>
						</TR>
						<TR>
							<TH WIDTH="82%" CLASS=lightblue2>General Arrangement</TH>
						</TR>
					</TABLE>
					<TABLE WIDTH="96%" ALIGN="center" BORDER="1" BORDERCOLOR="#5780ae">
						<TR>
						    <!--Added for CR-74 VV49326 09-06-09-->
						    <TH CLASS='table_heading' WIDTH="9%">Revision No</TH>
							<TH CLASS='table_heading' WIDTH="91%">Image</TH>
						</TR>
						<logic:iterate id="GenNotes" name="ViewSpecByOrderForm"
							property="genArgmntList"
							type="com.EMD.LSDB.vo.common.GenArrangeVO" scope="request">
							<bean:define id="check"
								value='<%=String.valueOf(GenNotes.getFileVO().getImageSeqNo())%>' />
							<TR>
								<logic:greaterThan name="check" value="0">
								    <!--Added for CR-74 VV49326 09-06-09-->
									 <TD WIDTH="9%" CLASS=borderbottomleft>
									   <logic:notEmpty  name="GenNotes" property="revCode">
								          <logic:iterate id ="RevMarker" name="GenNotes" property="revCode">
										       <bean:write name="RevMarker"/> <BR>
									       </logic:iterate>
								       </logic:notEmpty>
		
							          <logic:empty name="GenNotes" property="revCode">&nbsp;
							          </logic:empty>
								   </TD>
					                          <!--Added for CR-74 VV49326 09-06-09-->
								    
									<TD CLASS=borderbottomleft1 WIDTH="91%" align="center"><img
										src="<%=request.getContextPath()%>/DownLoadImage.do?method=downloadImage&ImageSeqNo='<%=GenNotes.getFileVO().getImageSeqNo()%>'"
										border="0" alt=""></TD>
								</logic:greaterThan>
								<logic:lessThan name="check" value="1">
									<TD class='' width="10%" align="center">&nbsp;</TD>
								</logic:lessThan>
							</TR>
						</logic:iterate>
					</TABLE>
				</logic:equal>
			</logic:present>

			<TABLE WIDTH="100%" BORDER=0>

				<!--Displaying Section Details -->

				<logic:iterate id="secdetail1" name="ViewSpecByOrderForm"
					property="secDetailList" scope="request" indexId="cnt">
					<logic:iterate id="secdetail" name="secdetail1"
						type="com.EMD.LSDB.vo.common.SubSectionVO" indexId="secid">
						<logic:present name="secdetail" property="clauseGroup">
							<bean:size id="clauselen" name="secdetail" property="clauseGroup" />
						</logic:present>
						<TR>
							<TD></TD>
						</TR>
						<TR>
							<TD></TD>
						</TR>
						<TR>
							<TD></TD>
						</TR>
						<TR>
							<TD></TD>
						</TR>
						<TR>
							<TD></TD>
						</TR>
						<TR>
							<TD></TD>
						</TR>
						<TR>
							<TD></TD>
						</TR>
						<TR>
							<TD></TD>
						</TR>
						<TR>
							<TD></TD>
						</TR>
						<TR>
							<A name="section<%=cnt%>"></A>
							<TD WIDTH="100%" ALIGN=center><!--<html:link href="#GeneralInfo">
			      <font Class="linkstyleItem"> General Info</font>
				</html:link>&nbsp;|
			    <logic:iterate id="section" name="ViewSpecByOrderForm"   property="sectionList" type="com.EMD.LSDB.vo.common.SectionVO" scope="request" indexId="counters">
					<logic:notEqual name="section" property="colourFlag" value="Y">
						
						<A href="#section<%=counters%>" Class="linkstyleItem">
							<bean:write name="section" property="sectionCode"/>.
							<bean:write name="section" property="sectionName"/>
						</A>&nbsp;|
					</logic:notEqual>

					<logic:equal name="section" property="colourFlag" value="Y">
						
						<A href="#section<%=counters%>" Class="linkstyleItem">
							<font color="red"><bean:write name="section" property="sectionCode"/>.
								<bean:write name="section" property="sectionName"/></font>
						</A>&nbsp;|
					</logic:equal>
				</logic:iterate>
				<html:link href="#Performance">
			       <font Class="linkstyleItem">Performance Curve</font>
				</html:link>&nbsp;--></TD>
						</TR>
						<TR>
							<td><logic:equal name="secid" value="0">
								<TABLE BORDER=0 WIDTH="98%" ALIGN="center">
									<TR>
										<TH CLASS=lightblue2><bean:write name="secdetail"
											property="secCode" />.<bean:write name="secdetail"
											property="secName" /></TH>
									</TR>
								</TABLE>
							</logic:equal></td>
						</TR>
						<TR>
							<TD>
							<TABLE WIDTH="98%" BORDER="0" ALIGN="center">
								<TR>
									<TD ALIGN=left CLASS=noborder4 WIDTH="80%"><bean:write
										name="secdetail" property="subSecCode" />.<bean:write
										name="secdetail" property="subSecName" />&nbsp;&nbsp;&nbsp;</TD>
								</TR>
							</TABLE>
							</TD>
						</TR>


						<TR>
							<TD>
							<TABLE WIDTH="98%" ALIGN="center" BORDER="1"
								BORDERCOLOR="#5780ae">

								<logic:greaterEqual name="clauselen" value="1">
									<TR>
										<TD CLASS='table_heading' WIDTH="7%">Price Book No</TD>
										<TD CLASS='table_heading' WIDTH="5%">Revision No</TD>
										<TD CLASS='table_heading' WIDTH="17%">Clause No</TD>
										<TD CLASS='table_heading' WIDTH="42%">Clause Description</TD>
										<TD CLASS='table_heading' nowrap WIDTH="5%">Engineering Data</TD>
										<TD CLASS='table_heading' WIDTH="10%">Component</TD>
									</TR>
								</logic:greaterEqual>

								<logic:iterate id="clause" name="secdetail"
									property="clauseGroup" type="com.EMD.LSDB.vo.common.ClauseVO"
									scope="page">

									<logic:equal name="clause" property="subSectionSeqNo"
										value="<%=String.valueOf(secdetail.getSubSecSeqNo())%>">
										<TR>
											<TD CLASS=borderbottomleft1><logic:notEqual name="clause"
												property="priceBookNumber" value="0">
												<bean:write name="clause" property="priceBookNumber" />
											</logic:notEqual>&nbsp;</TD>


											
												<TD CLASS=borderbottomleft>
												 <!--Added for CR-74 VV49326 09-06-09-->
												  <logic:equal name="clause" property="userMarkFlag" value="Y">
													<table width="100%" border=1 bordercolor="#0000A0">
													<tr><td align=center bordercolor="white">MARKED</td></tr>
													</table>
													<BR>
												 </logic:equal>
												 
												 <logic:equal name="clause" property="sysMarkFlag" value="Y">
													<table width="100%" border=1 bordercolor="#3BB9FF" bgcolor="#3BB9FF">
													<tr><td align=center nowrap><bean:write name="clause" property="sysMarkDesc"/></td></tr>
													</table>
													<BR>
												 </logic:equal>
												 
												 <logic:notEqual name="clause" property="userMarkFlag" value="Y">
												 <logic:notEqual name="clause" property="sysMarkFlag" value="Y">
												 <BR>
												 </logic:notEqual>
												 </logic:notEqual>
												 <!--Added for CR-74 VV49326 09-06-09-->
												 <logic:notEmpty name="clause" property="revCode">
												 
												<logic:iterate id="revcode"
													name="clause" property="revCode">
													
													<bean:write name="revcode" />
													<br>
												</logic:iterate>
											</logic:notEmpty>
                                            <!--Changed for CR-74 VV49326 09-06-09-->
											<logic:empty name="clause" property="revCode">
												&nbsp;
											</logic:empty>
                                            </TD>

											<logic:notEmpty name="clause" property="clauseNum">
												<TD CLASS=borderbottomleft>
												<TABLE WIDTH="100%" BORDER="0">
													<TR>
														<TD rowspan=2 ALIGN="CENTER" CLASS=noborder><bean:write
															name="clause" property="clauseNum" /></TD>
													</TR>
												</TABLE>
												</TD>
											</logic:notEmpty>

											<logic:empty name="clause" property="clauseNum">
												<TD CLASS=borderbottomleft>&nbsp;</TD>
											</logic:empty>


											<TD CLASS=borderbottomleft>
											<DIV STYLE="float:left;height:60;width:435;overflow: auto ; ">
					                         <!--Changed for CR-74 VV49326 16-06-09-->
					                        <logic:equal name="clause" property="clauseDelFlag" value="Y">
											<bean:message key="Message.Reserved" />
											</logic:equal>
																																	
											<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
										    <!--Changed for CR-74 VV49326 16-06-09-->
											<!-- Clause desc display --> <bean:define name="clause"
												property="clauseDesc" id="clauseDesc" /> 
												
												<!-- CR-128 - Updated for Pdf issue -->
													<%String strClauseDesc =  String.valueOf(clauseDesc);
													if(strClauseDesc.startsWith("<p>")){%>
														<%=(String.valueOf(clauseDesc))%>
													<%}else{ %>	
														<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>
													<%}%>
												<!-- CR-128 - Updated for Pdf issue Ends Here-->
												


											<table width="100%" BORDER="1">
												<logic:notEmpty name="clause" property="tableArrayData1">
											 &nbsp;
							  				 <logic:iterate id="outter" name="clause"
														property="tableArrayData1" indexId="counter">
														<bean:define id="row" name="counter" />
														<tr>
															<logic:iterate id="tabrow" name="outter">

																<logic:equal name="row" value="0">
																	<td valign="top" width="5%" class="borderbottomleft1">
																	<b><font color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></font></b>
																	</td>
																</logic:equal>

																<logic:notEqual name="row" value="0">
																	<td valign="top" width="5%" class="borderbottomleft1">
																	<%=(tabrow==null)? "&nbsp;":tabrow%></td>
																</logic:notEqual>


															</logic:iterate>
														</tr>
													</logic:iterate>
												</logic:notEmpty>
											</table>

						                   <!--Changed for CR-74 VV49326 16-06-09-->
						                   </logic:notEqual>
											</DIV>

											<!-- Added for LSDB_CR_42 --> <logic:present
												name="ViewSpecByOrderForm" property="orderList"
												scope="request">
												<logic:iterate id="DisList" name="ViewSpecByOrderForm"
													property="orderList" type="com.EMD.LSDB.vo.common.OrderVO"
													scope="request">
													<logic:equal name="DisList" property="appendixFlag"
														value="Y">
														<logic:notEmpty name="clause" property="clauseImageName">
															<span class="greytext">(Refer To Appendix Image: <bean:write
																name="clause" property="clauseImageName" />)</span>
														</logic:notEmpty>
													</logic:equal>
												</logic:iterate>
											</logic:present></TD>

											<logic:empty name="clause" property="clauseDesc">
												<TD CLASS=borderbottomleft>&nbsp;</TD>
											</logic:empty>

                                            <!--Changed for CR-74 15-06-09 for Engg Data- Starts Here-->
                                            <logic:equal name="clause" property="clauseDelFlag" value="Y">
																						
												<TD CLASS=borderbottomleft><BR>
												</TD>
											
											</logic:equal>
					
																						
											<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
										    <!-- Eng data starts -->
											<TD CLASS=borderbottomleft nowrap align="left">
											<DIV STYLE="height:60;width:150;overflow:auto;">
											<!-- <logic:notEmpty	name="clause" property="refEDLNO">
												<logic:iterate id="refedl" name="clause" property="refEDLNO">
		  	                                        ref: EDL&nbsp;<bean:write name="refedl" />
													<br>
												</logic:iterate>
											</logic:notEmpty> -->
											 <logic:notEmpty name="clause"
												property="edlNO">
												<logic:iterate id="edl" name="clause" property="edlNO">
		  	                                       EDL&nbsp;<bean:write name="edl" />
													<br>
												</logic:iterate>
											</logic:notEmpty> 
											<!-- CR 87 -->
											<logic:notEmpty	name="clause" property="refEDLNO">
												<logic:iterate id="refedl" name="clause" property="refEDLNO">
		  	                                        <bean:message key="screen.refEdl.start" />&nbsp;
		  	                                        <bean:write name="refedl" />&nbsp;
		  	                                        <bean:message key="screen.refEdl.end" />
													<br>
												</logic:iterate>
											</logic:notEmpty>
											<logic:notEmpty name="clause"
												property="partOF">
												<logic:iterate id="partof" name="clause" property="partOF"
													type="com.EMD.LSDB.vo.common.SubSectionVO">
		  	                                          <bean:message key="screen.partOf" />&nbsp;<bean:write name="partof" property="subSecCode" />
													<br>
												</logic:iterate>
											</logic:notEmpty> <logic:notEmpty name="clause"
												property="dwONumber">
												<logic:notEqual name="clause" property="dwONumber" value="0">
		  	                                         DWO&nbsp;<bean:write name="clause" property="dwONumber" />
													<br>
												</logic:notEqual>
											</logic:notEmpty> <logic:notEmpty name="clause"
												property="partNumber">
												<logic:notEqual name="clause" property="partNumber"
													value="0">
		  	                                      Part No&nbsp;<bean:write name="clause" property="partNumber" />
													<br>
												</logic:notEqual>
											</logic:notEmpty> <logic:notEmpty name="clause"
												property="standardEquipmentDesc">
												<bean:write name="clause" property="standardEquipmentDesc" />
												<br>
											</logic:notEmpty> <logic:notEmpty name="clause"
												property="engDataComment">
												<bean:define name="clause" property="engDataComment"
													id="engData" />
												<%=engData%>
												<br>
											</logic:notEmpty></DIV>
											</TD>
											
											</logic:notEqual>
                                            <!--Changed for CR-74 16-06-09 for Engg Data- Ends Here-->
                                            
                                            
                                           <!--Changed for CR-74 17-06-09 for Component- Starts Here-->
										       <TD CLASS=borderbottomleft1   VALIGN="TOP">   				
													<logic:equal name="clause" property="clauseDelFlag" value="Y">
													<logic:equal name="clause" property="deleteFlag" value="D">
													&nbsp;     
													</logic:equal>
													</logic:equal>  
													
													<logic:notEqual name="clause" property="clauseDelFlag" value="Y">
													<logic:notEqual name="clause" property="deleteFlag" value="D">
													&nbsp;     
													</logic:notEqual>
													</logic:notEqual>                                          
					                                            
					                             <!--Added for CR-74 16-06-09 for Component- Ends Here-->
					                            
												<logic:notEmpty name="clause" property="compName">
                                                            <!--Commented for CR-74 17-06-09-->
                                                            <!--<TD CLASS=borderbottomrightlight  VALIGN="TOP">-->
															<!-- This part is for finding size of the components-->
															<logic:notEmpty name="clause" property="compName">
																		<bean:size id="compSize" name="clause" property="compName"/>
																		<bean:define id="compTotSize"
																			value="<%=String.valueOf(compSize.intValue()-1)%>" />
															</logic:notEmpty> 
															<logic:iterate id="compdesc" name="clause"
																property="compName" type="com.EMD.LSDB.vo.common.ComponentVO" indexId="counter">
																<bean:define id="count" name="counter" />
																<logic:notEqual name="counter" value="0"> ;<br>
																</logic:notEqual>
																<logic:equal name="compdesc" property="deletedFlag" value="Y">
																<font color="red"><bean:write name="compdesc" property="componentName" /></font>
																</logic:equal>
																<logic:notEqual name="compdesc" property="deletedFlag" value="Y">
																<!-- Change for LSDB_CR-74 -->
																<logic:equal name="compdesc" property="compColorFlag" value="Y">
																<font color="#3D83C9">
																<b><bean:write name="compdesc" property="componentName" /></b>
																</font>
																</logic:equal>
																<logic:notEqual name="compdesc" property="compColorFlag" value="Y">
																<bean:write name="compdesc" property="componentName" />
																</logic:notEqual>
																<!-- Ends here -->
													            </logic:notEqual>
					                                        </logic:iterate>
													   	
												</logic:notEmpty>
												
												<logic:empty name="clause" property="compName">
												<!--Changed for CR-74 17-06-09-->
													&nbsp;
												</logic:empty>
												</TD>
												<!--Changed for CR-74 17-06-09-->
												

										</TR>

									</logic:equal>
								</logic:iterate>

							</TABLE>
							</TD>
						</TR>
					</logic:iterate>
				</logic:iterate>
			</TABLE>




			<A name="Performance"></A>


			<TABLE WIDTH="100%" BORDER=0>
				<TR>
					<TD></TD>
				</TR>
				<TR>
					<TD></TD>
				</TR>
				<TR>
					<TD></TD>
				</TR>
				<TR>
					<TD WIDTH="100%" ALIGN=center><!--<html:link href="#GeneralInfo">
			       <font Class="linkstyleItem"> General Info </font>
				</html:link>&nbsp;|
			    <logic:iterate id="section" name="ViewSpecByOrderForm"   property="sectionList" type="com.EMD.LSDB.vo.common.SectionVO" scope="request" indexId="counters">
					<logic:notEqual name="section" property="colourFlag" value="Y">
						
						<A href="#section<%=counters%>" Class="linkstyleItem">
							<bean:write name="section" property="sectionCode"/>
							<bean:write name="section" property="sectionName"/>
						</A>&nbsp;|
					</logic:notEqual>

					<logic:equal name="section" property="colourFlag" value="Y">
						
						<A href="#section<%=counters%>" Class="linkstyleItem">
							<font color="red"><bean:write name="section" property="sectionCode"/>
								<bean:write name="section" property="sectionName"/></font>
						</A>&nbsp;|
					</logic:equal>
				</logic:iterate>
				<html:link href="#Performance">
			      <font Class="linkstyleItem"> Performance Curve </font>
				</html:link>&nbsp;--></TD>
				</TR>
			</TABLE>

			<!-- Added for LSDB_CR_42 --> <logic:present
				name="ViewSpecByOrderForm" property="orderList" scope="request">
				<logic:iterate id="DisList" name="ViewSpecByOrderForm"
					property="orderList" type="com.EMD.LSDB.vo.common.OrderVO"
					scope="request">
					<logic:equal name="DisList" property="appendixFlag" value="Y">

						<TABLE BORDER=0 ALIGN=center WIDTH="98%" CELLSPACING=1
							CELLPADDING=0>
							<TR>
								<TD>&nbsp;</TD>
							</TR>
							<TR>
								<TH WIDTH="82%" CLASS=lightblue>Appendix</TH>
							</TR>
						</TABLE>


						<logic:present name="ViewSpecByOrderForm"
							property="clauseImageList" scope="request">
							<bean:size id="imageListSize" name="ViewSpecByOrderForm"
								property="clauseImageList" />
						</logic:present>

						<logic:greaterThan name="imageListSize" value="0">
							<TABLE WIDTH="98%" BORDER="1" BORDERCOLOR="#5780ae"
								ALIGN="center">
								<logic:iterate id="clauseimage" name="ViewSpecByOrderForm"
									property="clauseImageList"
									type="com.EMD.LSDB.vo.common.AppendixVO" scope="request">

									<TR>
										<TD>
										<TABLE WIDTH="98%" border=0>
											<TR>
												<TD class="noborder" align="center"><B><font size="3"
													type="Verdana"><bean:write name="clauseimage"
													property="imageName" /></font></B></TD>
											</TR>
											<TR>
												<TD class="noborder">&nbsp;</TD>
											</TR>
											<TR>
												<TD class="noborder" align="center"><img
													src="/EMD-LSDB/DownLoadImage.do?method=downloadImage&ImageSeqNo='<%=clauseimage.getImageSeqNo()%>'"
													border="0" alt=""></TD>
											</TR>
											<TR>
												<TD class="noborder" align="center"><font size="2px"
													type="Verdana" color=""><bean:write name="clauseimage"
													property="imageDesc" /></font></TD>
											</TR>
										</TABLE>
										</TD>
									</TR>
								</logic:iterate>
							</TABLE>
						</logic:greaterThan>

					</logic:equal>
				</logic:iterate>
			</logic:present> <!-- Ends --> <!-- Added for LSDB_CR_46 PM&I Change -->
			<!-- Performance Curve should be displayed for Locomotive order--> <logic:present
				name="ViewSpecByOrderForm" property="specTypeNo" scope="request">
				<logic:equal name="ViewSpecByOrderForm" property="specTypeNo"
					value="1">

					<TABLE BORDER=0 ALIGN=center WIDTH="98%" CELLSPACING=1
						CELLPADDING=0>
						<TR>
							<TD>&nbsp;</TD>
						</TR>
						<TR>
						    <!--Added for CR-74 VV49326 09-06-09-->
						    <TH WIDTH=9%    CLASS=lightblue>Revision No</TH>
							<TH WIDTH="91%" CLASS=lightblue>Performance Curve</TH>
						</TR>
					</TABLE>

					<logic:present name="ViewSpecByOrderForm" property="perCurveList"
						scope="request">
						<bean:size name="ViewSpecByOrderForm" id="perCurveListSize"
							property="perCurveList" />
					</logic:present>

					<logic:greaterThan name="perCurveListSize" value="0">
						<TABLE WIDTH="98%" BORDER="1" BORDERCOLOR="#5780ae" ALIGN="center">
							<logic:iterate id="PerfCurveVO" name="ViewSpecByOrderForm"
								property="perCurveList"
								type="com.EMD.LSDB.vo.common.PerformanceCurveVO" scope="request">

								<bean:define id="contenttype"
									value='<%=String.valueOf(PerfCurveVO.getFileVO().getContentType())%>' />
								<TR>
								
								    <!--Added for CR-74 VV49326 09-06-09-->
								    <TD WIDTH=9% CLASS=borderbottomleft>
								    <logic:notEmpty  name="PerfCurveVO" property="revCode">
								      <logic:iterate id ="RevMarker" name="PerfCurveVO" property="revCode">
								        <bean:write name="RevMarker"/> <BR>
							          </logic:iterate>
							        </logic:notEmpty>
							        
							        <logic:empty name="PerfCurveVO" property="revCode">
							        &nbsp;
							        </logic:empty>
								    </TD>
								    <!--Added for CR-74 VV49326 09-06-09-->
									<TD CLASS=borderbottomleft1 width="91%" align="center"><logic:equal
										name="contenttype" value="application/pdf">

										<A class="linkstyleItem"
											href="javascript:fnShowPDF('<%=PerfCurveVO.getFileVO().getImageSeqNo()%>','<%=PerfCurveVO.getImageName()%>')">PDF
										Image</A>

										<html:hidden property="imgSeqNo"
											value='<%=String.valueOf(PerfCurveVO.getFileVO().getImageSeqNo())%>' />
									</logic:equal> <logic:notEqual name="contenttype"
										value="application/pdf">

										<img
											src="/EMD-LSDB/DownLoadImage.do?method=downloadImage&ImageSeqNo='<%=PerfCurveVO.getFileVO().getImageSeqNo()%>'"
											border="0" alt="">
									</logic:notEqual></TD>
								</TR>
							</logic:iterate>
						</TABLE>
					</logic:greaterThan>
				</logic:equal>
			</logic:present>

			<TABLE WIDTH="100%" BORDER=0 ALIGN="center">
				<TR>
					<TD>&nbsp;</TD>
				</TR>
				<TR>
					<TD ALIGN="center" WIDTH="50%"><INPUT TYPE="button" value="Cancel"
						name="btnCancel" CLASS=buttonStyle onclick="Cancelpopup()"></TD>
				</TR>
			</TABLE>

			</html:form>
</BODY>
</html:html>
