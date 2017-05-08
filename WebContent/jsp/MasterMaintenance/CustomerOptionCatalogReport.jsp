<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page contentType="application/msword"%>
<% response.setHeader("Content-Disposition", "inline; filename=\"CustomerOptionCatalog.doc\""); %>

<HTML>
<HEAD>
<TITLE>Customer Catalog Option</TITLE>
</HEAD>
<BODY>
<html:form action="/MasterSpecByMdlAction" method="post">
<div class=WordSection1>
	<table width="100%" border="0">
		<tr>
			<td align="center"
				style="FONT-WEIGHT: bold;FONT-SIZE: 14pt;font-family: arial;COLOR: #000000">SD70Series-Ace
			Customer Option Catalog</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>

	<table width="95%" align="center" border="0">

		<tr>
			<td width="15%">&nbsp;</td>
			<td width="30%"
				style="FONT-WEIGHT: bold;FONT-SIZE: 11pt; font-family: arial;COLOR: #000000">
			Specification Type</td>
			<td width="1%"
				style="FONT-WEIGHT: bold;FONT-SIZE: 11pt; font-family: arial;COLOR: #000000">:</td>
			<td width="49%"
				style="FONT-WEIGHT: bold;FONT-SIZE: 11pt; font-family: arial;COLOR: #000000"><bean:write
				name="MasterSpecByMdlForm" property="hdnSelSpecType" /></td>
		</tr>

		<tr>
			<td>&nbsp;</td>
			<td
				style="FONT-WEIGHT: bold;FONT-SIZE: 11pt; font-family: arial;COLOR: #000000">
			Model</td>
			<td width="1%"
				style="FONT-WEIGHT: bold;FONT-SIZE: 11pt; font-family: arial;COLOR: #000000">:</td>
			<td
				style="FONT-WEIGHT: bold;FONT-SIZE: 11pt; font-family: arial;COLOR: #000000"><bean:write
				name="MasterSpecByMdlForm" property="modelName" /></td>

		</tr>

		<tr>
			<td>&nbsp;</td>
			<td
				style="FONT-WEIGHT: bold;FONT-SIZE: 11pt; font-family: arial;COLOR: #000000">
			Customer</td>
			<td width="1%"
				style="FONT-WEIGHT: bold;FONT-SIZE: 11pt; font-family: arial;COLOR: #000000">:</td>
			<td
				style="FONT-WEIGHT: bold;FONT-SIZE: 11pt; font-family: arial;COLOR: #000000">&nbsp;
			</td>
		</tr>

		<tr>
			<td>&nbsp;</td>
			<td
				style="FONT-WEIGHT: bold;FONT-SIZE: 11pt; font-family: arial;COLOR: #000000">
			Order Number</td>
			<td width="1%"
				style="FONT-WEIGHT: bold;FONT-SIZE: 11pt; font-family: arial;COLOR: #000000">:</td>
			<td
				style="FONT-WEIGHT: bold;FONT-SIZE: 11pt; font-family: arial;COLOR: #000000">&nbsp;
			</td>
		</tr>

		<tr>
			<td>&nbsp;</td>
			<td
				style="FONT-WEIGHT: bold;FONT-SIZE: 11pt; font-family: arial;COLOR: #000000">
			Quantity</td>
			<td width="1%"
				style="FONT-WEIGHT: bold;FONT-SIZE: 11pt; font-family: arial;COLOR: #000000">:</td>
			<td
				style="FONT-WEIGHT: bold;FONT-SIZE: 11pt; font-family: arial;COLOR: #000000">&nbsp;
			</td>

		</tr>

		<tr>
			<td>&nbsp;</td>
			<td
				style="FONT-WEIGHT: bold;FONT-SIZE: 11pt; font-family: arial;COLOR: #000000">
			Date of Creation</td>
			<td width="1%"
				style="FONT-WEIGHT: bold;FONT-SIZE: 11pt; font-family: arial;COLOR: #000000">:</td>
			<td
				style="FONT-WEIGHT: bold;FONT-SIZE: 11pt; font-family: arial;COLOR: #000000">
			<b><bean:write name="MasterSpecByMdlForm"
				property="reportCreationDate" /></b></td>
		</tr>
	</table>

	<logic:present name="MasterSpecByMdlForm"
		property="cusOptionCatalogList">
		<logic:iterate id="sec" name="MasterSpecByMdlForm"
			property="cusOptionCatalogList"
			type="com.EMD.LSDB.vo.common.SectionVO">
			<DIV style='page-break-before: always;'>&nbsp;</DIV>
			<table width="100%" border="0">
				<tr>
					<td
						style="FONT-WEIGHT: bold;FONT-SIZE: 11pt; font-family: arial;COLOR: #000000"
						colspan="4"><u><bean:write name="sec" property="sectionName" /> </u>
					</td>
				</tr>
				<tr>
					<td width="1%">&nbsp;</td>
					<td
						style="FONT-WEIGHT: bold;FONT-SIZE: 11pt; font-family: arial;COLOR: #000000"
						colspan="3"><font color="blue"><b><i>Comments</i></b></font></td>

				</tr>

				<tr>
					<td width="1%">&nbsp;</td>
					<td style="FONT-SIZE: 10pt; font-family: arial;COLOR: #000000"
						colspan="3">&nbsp;</td>

				</tr>


				<!-- SubSection starts -->
				<logic:present name="sec" property="subSec">
					<logic:iterate id="subsec" name="sec" property="subSec"
						type="com.EMD.LSDB.vo.common.SubSectionVO">
						<tr>
							<td width="1%">&nbsp;</td>
							<td
								style="FONT-WEIGHT: bold;FONT-SIZE: 11pt; font-family: arial;COLOR: #000000"
								colspan="3"><u><bean:write name="subsec" property="subSecName" /></u>
							</td>

						</tr>


						<!-- Component group starts -->
						<logic:present name="subsec" property="compGroup">
							<logic:iterate id="compgroup" name="subsec" property="compGroup"
								type="com.EMD.LSDB.vo.common.CompGroupVO">
								<tr>
									<td width="1%">&nbsp;</td>
									<td width="1%">&nbsp;</td>
									<td
										style="FONT-WEIGHT: bold;FONT-SIZE: 11pt; font-family: arial;COLOR: #000000"
										colspan="2"><logic:equal name="compgroup" property="validFlag"
										value="Y">
										<font color="red">*</font>
									</logic:equal> <u><bean:write name="compgroup"
										property="componentGroupName" /></u></td>

								</tr>
								<logic:present name="compgroup" property="component">
									<logic:iterate id="comp" name="compgroup" property="component"
										type="com.EMD.LSDB.vo.common.ComponentVO">
										<!-- Component  starts -->

										<tr>
											<td width="1%">&nbsp;</td>
											<td width="1%">&nbsp;</td>
											<td width="1%">
											<logic:equal name="compgroup" property="validFlag"
										value="Y">
											<logic:equal name="comp"
												property="compDefFlag" value="Y">
												<INPUT checked
													style="background-color: white; font-size: 9pt;font-family: arial;border: none;"
													TYPE="radio"
													name="cg1<%=compgroup.getComponentGroupSeqNo()%>">
											</logic:equal>
											<logic:notEqual name="comp"
												property="compDefFlag" value="Y">
												<INPUT
													style="background-color: white; font-size: 9pt;font-family: arial;border: none;"
													TYPE="radio"
													name="cg1<%=compgroup.getComponentGroupSeqNo()%>">
											</logic:notEqual>
											</logic:equal>

											<logic:notEqual name="compgroup" property="validFlag"
											value="Y">
											<logic:equal name="comp"
												property="compDefFlag" value="Y">
												<INPUT checked
													style="background-color: white; font-size: 9pt;font-family: arial;border: none;"
													TYPE="checkbox"
													name="cg1<%=compgroup.getComponentGroupSeqNo()%>">
											</logic:equal>
											<logic:notEqual name="comp"
												property="compDefFlag" value="Y">
												<INPUT
													style="background-color: white; font-size: 9pt;font-family: arial;border: none;"
													TYPE="checkbox"
													name="cg1<%=compgroup.getComponentGroupSeqNo()%>">
											</logic:notEqual>
											</logic:notEqual>
											</td>
											<td
												style="FONT-SIZE: 10pt; font-family: arial;COLOR: #000000">
											<logic:equal name="comp" property="compDefFlag" value="Y">
												<bean:write name="comp" property="componentName" />
											</logic:equal> <logic:notEqual name="comp"
												property="compDefFlag" value="Y">
												<i><bean:write name="comp" property="componentName" /></i>
											</logic:notEqual></td>
										</tr>

										<!-- Clause starts -->
										<logic:present name="comp" property="clauseVOList">
											<logic:iterate id="clause" name="comp"
												property="clauseVOList"
												type="com.EMD.LSDB.vo.common.ClauseVO">
												<tr>
													<td colspan="3">&nbsp;</td>
													<td>
													<table border="0" width="100%">
														<tr>
															<td
																style="FONT-SIZE: 10pt; font-family: arial;COLOR: #000000"><bean:define
																name="clause" property="clauseDesc" id="clauseDesc" /><logic:notEqual
																name="comp" property="compDefFlag" value="Y">
																<i>- 
																<!-- CR-128 - Updated for Pdf issue -->
																	<%String strClauseDesc =  String.valueOf(clauseDesc);
																	if(strClauseDesc.startsWith("<p>")){%>
																		<%=(String.valueOf(clauseDesc))%></i>
																	<%}else{ %>	
																		<%-- double space replacement with &nbsp is added to display the spaces as entered. This change is applicable for only modify spec --%>
																		<%=(String.valueOf(clauseDesc)).replaceAll("  ","&nbsp;&nbsp;").replaceAll("\n","<br>")%></i>							
																	<%}%>
																<!-- CR-128 - Updated for Pdf issue Ends Here-->
															</logic:notEqual> <logic:equal name="comp"
																property="compDefFlag" value="Y">
																-<!-- CR-128 - Updated for Pdf issue -->
																	<%String strClauseDesc =  String.valueOf(clauseDesc);
																	if(strClauseDesc.startsWith("<p>")){%>
																		<%=(String.valueOf(clauseDesc))%>
																	<%}else{ %>	
																		<%-- double space replacement with &nbsp is added to display the spaces as entered. This change is applicable for only modify spec --%>
																		<%=(String.valueOf(clauseDesc)).replaceAll("  ","&nbsp;&nbsp;").replaceAll("\n","<br>")%>							
																	<%}%>
																<!-- CR-128 - Updated for Pdf issue Ends Here--> 
															</logic:equal> <logic:notEmpty name="clause"
																property="tableArrayData1">
																<br>
																<br>
																<!-- Modified For CR.No.77 to align Table Data Width by RR68151 -->
																<table BORDER="1" cellspacing=0 cellpadding=0 width="100%">
																	<logic:iterate id="outter" name="clause"
																		property="tableArrayData1" indexId="counter">
																		<bean:define id="row" name="counter" />
																		<tr>
																			<logic:iterate id="tabrow" name="outter">

																				<logic:equal name="row" value="0">
																					<td valign="top" align="center"><b><font color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></font></b>
																					</td>
																				</logic:equal>

																				<logic:notEqual name="row" value="0">
																					<td valign="top" align="center"><%=(tabrow==null)? "&nbsp;":tabrow%>
																					</td>
																				</logic:notEqual>
																			</logic:iterate>
																		</tr>
																	</logic:iterate>
																</table>
															</logic:notEmpty></td>
														</tr>
													</table>
													</td>
												</tr>
											</logic:iterate>
											<!-- Clause ends -->
										</logic:present>
									</logic:iterate>
									<!-- Comp ends -->
								</logic:present>
							</logic:iterate>
							<!-- Comp Group ends -->
						</logic:present>
					</logic:iterate>
					<!-- Subsec ends -->
				</logic:present>

			</table>
			<!-- <DIV style='page-break-before: always;'>&nbsp;</DIV> -->
		</logic:iterate>
		<!--Sec ends -->
	</logic:present>


	<!-- Appendix Images Display -->

	<logic:present name="MasterSpecByMdlForm" property="listImages">
		<P style='page-break-before: always;'>&nbsp;</P>
		<table border="0" width="100%">
			<tr>
				<td
					style="FONT-WEIGHT: bold;FONT-SIZE: 11pt; font-family: arial;COLOR: #000000">Appendix</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		<logic:iterate id="ModelAppendixVO" name="MasterSpecByMdlForm"
			property="listImages" type="com.EMD.LSDB.vo.common.ModelAppendixVO"
			scope="request" indexId="count">
			<bean:define id="appendixCnt"
				value="<%=String.valueOf(count.intValue())%>" />
			<logic:greaterThan name="appendixCnt" value="0">
				<DIV style='page-break-before: always;'>&nbsp;</DIV>
			</logic:greaterThan>
			<table border="0" width="100%">
				<tr>
					<td align="center"><!-- <img src="../../images/appendix.jpg"> --> <img
						src="<%=request.getContextPath()%>/DownLoadImage.do?method=downloadImage&ImageSeqNo='<%=ModelAppendixVO.getFileVO().getImageSeqNo()%>'"
						border="0" alt="" /></td>
				</tr>
			</table>
		</logic:iterate>
	</logic:present>
</html:form>
</div>
</BODY>

</HTML>

