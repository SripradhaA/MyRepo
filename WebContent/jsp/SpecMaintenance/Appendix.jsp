
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT type="text/JavaScript" SRC="js/Appendix.js"></SCRIPT>
<!-- Modified Links for CR_91 -->
<div class="container-fluid">
<html:form styleClass="form-horizontal" action="/AppendixAction" method="post" enctype="multipart/form-data">

		<!-- Application PageName Display starts here-->
		<h4 class="text-center"><bean:message key="Application.Screen.ModifySpec" /></h4>
	
		<!-- Application PageName Display Ends here-->
		<div class="row">
			<div class="spacer10"></div>
		</div>
		<!-- Validation Message Display Part Starts Here -->
		<div class="row">
			<div class="errorlayerhide" id="errorlayer">
			</div>
		</div>
		<div class="row">
			<div class="spacer20"></div>
		</div>
		<logic:present name="AppendixForm" property="messageID" scope="request">
				<bean:define id="messageID" name="AppendixForm" property="messageID"/>
	            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
	            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
	
	
		</logic:present>
	
		<logic:present name="AppendixForm" property="imageList" scope="request">
			<bean:size name="AppendixForm" id="imageSize" property="imageList" />
		</logic:present>
	
		<logic:present name="AppendixForm" property="orderList" scope="request">
			<logic:iterate id="DisList" name="AppendixForm" property="orderList"
				type="com.EMD.LSDB.vo.common.OrderVO">
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
							<html:hidden name="DisList" property="modelName" />
							<html:hidden name="DisList" property="modelSeqNo" />
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
			</logic:iterate>
		</logic:present>
	
			<div class="row">
				<div class="col-sm-12 text-center">
					<a href="javascript:fnMainFeature()">
							General Information</a>&nbsp;&nbsp;|&nbsp;
					<logic:iterate id="section" name="AppendixForm"
						property="sectionList" type="com.EMD.LSDB.vo.common.SectionVO"
						scope="request">
						<bean:define id="revCode" name="AppendixForm" property="hdnRevViewCode" />
							<logic:notEqual name="section" property="colourFlag" value="Y"><a
									href="OrderSection.do?method=fetchSectionDetails&orderKey=<%=section.getOrderKey()%>&secSeq=<%=section.getSectionSeqNo()%>&secCode=<%=section.getSectionCode()%>&secName=<%=section.getSectionName()%>&revCode=<%=revCode%>"
									><bean:write name="section"
									property="sectionCode" />. <bean:write name="section"
									property="sectionName" /></a>&nbsp;&nbsp;|&nbsp;
							</logic:notEqual>
							<logic:equal name="section" property="colourFlag" value="Y">
								<a href="OrderSection.do?method=fetchSectionDetails&orderKey=<%=section.getOrderKey()%>&secSeq=<%=section.getSectionSeqNo()%>&secCode=<%=section.getSectionCode()%>&secName=<%=section.getSectionName()%>&revCode=<%=revCode%>"
								><font color="red"><bean:write
								name="section" property="sectionCode" />. <bean:write
								name="section" property="sectionName" /></font></a>&nbsp;&nbsp;|&nbsp;
						</logic:equal>
					</logic:iterate>
						<a href="javascript:fnLoadAppendix()">
							Appendix</a><!--Added for LSDB_CR_46 PM&I Change -->
							<!-- Modified For CR_84 -->
						<logic:equal name="AppendixForm" property="perfCurveLinkFlag" value="Y">&nbsp;|&nbsp;
							<a href="javascript:fnLoadPerfCurve()">Performance Curve</a>&nbsp;
						</logic:equal>
				</div>
			</div>
			
			<div class="row">
				<div class="spacer10"></div>
			</div>
						
			<div class="row">
				<div class="col-sm-12 highlightDark text-center">
					<strong>Appendix</strong>
				</div>
			</div>
			
			<div class="row">
				<div class="spacer10"></div>
			</div>
			
			<div class="row">
				<div class="col-sm-8 col-sm-offset-3 text-justify">
					<p><mark><bean:message key="AppendixImgMessage" /></mark></p>
					<p><mark><bean:message key="AppendixImgMessagePDF" /></mark></p>
					<p><mark><bean:message key="TransparentImgMessageTiff" /></mark></p>
				</div>
			</div>
			
			<div class="row">
				<div class="spacer10"></div>
			</div>
			
			
			<div class="form-group required-field">
				<label class="col-sm-2 col-sm-offset-3 control-label">Image Name</label>
					<div class="col-sm-2">
						<html:text property="imgName" styleClass="form-control" size="30" maxlength="40" />
					</div>
			</div>
			<div class="form-group ">
				<label class="col-sm-2 col-sm-offset-3 control-label">Image Description</label>
					<div class="col-sm-2">
						<html:textarea styleClass="form-control" property="imgDesc" 
						rows="2" cols="32" onkeyup="msgLimit(this, 2000);"/>
					</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-2 text-right">
					<div class="input-group">
						<input type="text" class="form-control input-file-readonly" readonly/>
		                <span class="input-group-btn">
		                    <span class="btn btn-primary btn-file">
		                        Browse&hellip; <html:file property="imageSource" styleClass="form-control" styleId="uploadAttachment"/>
		                    </span>
		                </span>		                
	            	</div> 
				</div>
				<div class="col-sm-1 text-center">
					<button class="btn btn-primary" type='button' ONCLICK="javascript:fnAddimage()">Add Image</button>
				</div>
			</div>
			
			<div class="row">
				<div class="spacer30"></div>
			</div>
	
		<logic:greaterThan name="imageSize" value="0">
	
			<HR>
			<TABLE class="table table-bordered">
				<thead>
					<TR>
						<TH width="5%" CLASS='text-center'>Select</TH>
						<TH width="15%" CLASS='text-center'>Image Name</TH>
						<TH width="15%" CLASS='text-center'>Image Description</TH>
						<TH width="10%" CLASS='text-center'>Image</TH>
						<TH width="55%" CLASS='text-center'>Clause Mapping</TH>
					</TR>
				</thead>
				<tbody class="text-center">
					<logic:iterate id="appendixVo" name="AppendixForm"
						property="imageList" type="com.EMD.LSDB.vo.common.AppendixVO"
						scope="request" indexId="clausecount">
						<bean:define id="contenttype" value='<%=String.valueOf(appendixVo.getFileVO().getContentType())%>' />
						<TR>
							<TD ><html:radio property="imageSeqNo"
								value='<%= String.valueOf(appendixVo.getImageSeqNo())%>' /></TD>
							<TD ><html:text styleClass="form-control" name="appendixVo"
								property="imageName" size="30" maxlength="60" /></TD>
							<TD><html:textarea styleClass="form-control" 
								name="appendixVo" property="imageDesc" rows="2" cols="32" /></TD>
							<TD>
							<!-- Added & Modified for CR_97 for allowing PDF in Appendix  -->
								<logic:equal name="contenttype" value="application/pdf">
									<A href="javascript:fnShowPDF('<%=appendixVo.getImageSeqNo()%>','<%=appendixVo.getImageName()%>')"
										data-toggle="tooltip" title="PDF">
										 <IMG SRC="images/pdf.png" alt="" height="25" BORDER="0">
									</A>
								</logic:equal>
								<logic:notEqual name="contenttype" value="application/pdf">
									<A href="javascript:fnShowImage('<%=appendixVo.getImageSeqNo()%>','<%=appendixVo.getImageName()%>')"
										data-toggle="tooltip" title="Image">
										 <IMG SRC="images/comp.gif" BORDER="0"> 
									</A>
								</logic:notEqual>
								<%-- CR_97 Ends here --%>
							</TD>
							<TD>
								<div class="col-sm-12 form-inline">
									<div class="col-sm-3 text-left">Map Clause&nbsp; <A
									HREF="javascript:fnLoadClauseDesc()"> <span class="glyphicon glyphicon glyphicon-search" aria-hidden="true"></span> </A>
									</div>
									<DIV class="col-sm-8 text-left" id="clauseDesc<%=clausecount%>"
									 style="height:57;overflow-Y: auto;" >&nbsp;</DIV>
									<input type="hidden" name="divId" value='<%=clausecount%>' />
								</div>
								<div class="row">
									<div class="spacer10"></div>
								</div>
								<logic:present name="appendixVo"
										property="mapClauses">
										<bean:size id="clauseSize" name="appendixVo"
											property="mapClauses" />
										<logic:greaterThan name="clauseSize" value="0">
												<logic:iterate id="clause" name="appendixVo"
														property="mapClauses" type="com.EMD.LSDB.vo.common.ClauseVO"
														scope="page">
														
														<div class="form-inline">
																<div class="col-sm-3">
																	<A class="mappedcla" href="javascript: fnViewMapping('<%=clause.getClauseSeqNo()%>','<%=clause.getVersionNo()%>','<%=clause.getSectionSeqNo()%>','<%=clause.getSubSectionSeqNo()%>')">View Clause Mapping</A>
																	<html:hidden name="clause" property="imageMapNo" />
															</div>
														</div>
													</logic:iterate>
										</logic:greaterThan>
									</logic:present> <logic:notPresent name="appendixVo"
										property="mapClauses">
								</logic:notPresent>
							</TD>
						</TR>
					</logic:iterate>
				</tbody>
			</TABLE>
			
			
			<div class="form-group">
		        <div class="col-sm-12 text-center">
		              <button class="btn btn-primary" type='button' ONCLICK="javascript:fnModifyImage()">Modify Image Name/Description</button>
		              <button class="btn btn-primary" type='button' ONCLICK="javascript:fnDeleteImage()">Delete Image</button>
		              <button class="btn btn-primary" type='button' ONCLICK="javascript:fnSaveMappings()">Save Clause Mapping</button>
		              <logic:present name="AppendixForm" property="orderList" 
						scope="request">
						<logic:iterate id="DisList" name="AppendixForm"
							property="orderList" type="com.EMD.LSDB.vo.common.OrderVO">
								<html:hidden name="DisList" property="appendixFlag" />
								<logic:equal name="DisList" property="appendixFlag" value="N">
			              			<button class="btn btn-primary" type='button' 
			              				ONCLICK="javascript:turnAppendix()">Turn Appendix ON</button>
			              		</logic:equal>
			              		<logic:equal name="DisList" property="appendixFlag" value="Y">
			              			<button class="btn btn-primary" type='button' 
			              				ONCLICK="javascript:turnAppendix()">Turn Appendix OFF</button>
			              		</logic:equal>
		              	</logic:iterate>
		              </logic:present>
		       	</div>
	     	</div>
			
			<div class="row">
				<div class="spacer10"></div>
			</div>
			
		</logic:greaterThan>
		
		<div class="row">
			<div class="col-sm-2">
				<logic:present name="AppendixForm" property="orderList" scope="request">
					<logic:iterate id="DisList" name="AppendixForm"
						property="orderList" type="com.EMD.LSDB.vo.common.OrderVO"
						scope="request">
						<logic:equal name="DisList" property="appendixFlag" value="Y">
							<div class="row">
								<div class="text-center"><strong>Appendix:<mark>On</mark></strong></div>
							</div>
						</logic:equal>
						<logic:equal name="DisList" property="appendixFlag" value="N">
							<div class="row">
								<div class="text-center"><strong>Appendix:<mark>Off</mark></strong></div>
							</div>
						</logic:equal>
					</logic:iterate>
				</logic:present>
			</div>
			<div class="col-sm-offset-1 col-sm-2" >
				<a href="#top" CLASS=linkstyleItemNoUnderline>^Top</a>
			</div>
			<div class="col-sm-3" >
				<a href="javascript:fnViewModelAppendixImages()" class="linkstyleItem">
					<span class="glyphicon glyphicon glyphicon-new-window" aria-hidden="true"></span> View Model Appendix Image(s)
				</a>
			</div>
		</div>
		<div class="row">
			<div class="spacer30"></div>
		</div>
		 	
			
				
	<!-- CR 87 Start -->
	
	<div class="row">
			<div class="col-sm-12 text-center">
				<a href="javascript:fnMainFeature()">
						General Information</a>&nbsp;&nbsp;|&nbsp;
				<logic:present name="AppendixForm" property="sectionList">
				<logic:iterate id="section" name="AppendixForm"
					property="sectionList" type="com.EMD.LSDB.vo.common.SectionVO"
					scope="request">
					<bean:define id="revCode" name="AppendixForm" property="hdnRevViewCode" />
						<logic:notEqual name="section" property="colourFlag" value="Y"><a
								href="OrderSection.do?method=fetchSectionDetails&orderKey=<%=section.getOrderKey()%>&secSeq=<%=section.getSectionSeqNo()%>&secCode=<%=section.getSectionCode()%>&secName=<%=section.getSectionName()%>&revCode=<%=revCode%>"
								><bean:write name="section"
								property="sectionCode" />. <bean:write name="section"
								property="sectionName" /></a>&nbsp;&nbsp;|&nbsp;
						</logic:notEqual>
						<logic:equal name="section" property="colourFlag" value="Y">
							<a href="OrderSection.do?method=fetchSectionDetails&orderKey=<%=section.getOrderKey()%>&secSeq=<%=section.getSectionSeqNo()%>&secCode=<%=section.getSectionCode()%>&secName=<%=section.getSectionName()%>&revCode=<%=revCode%>"
							><font color="red"><bean:write
							name="section" property="sectionCode" />. <bean:write
							name="section" property="sectionName" /></font></a>&nbsp;&nbsp;|&nbsp;
					</logic:equal>
				</logic:iterate>
				</logic:present>
					<a href="javascript:fnLoadAppendix()">
						Appendix</a><!--Added for LSDB_CR_46 PM&I Change -->
					<!-- Modified For CR_84 -->
					<logic:present name="AppendixForm" property="orderList"	scope="request">
						<logic:iterate id="DisList" name="AppendixForm" property="orderList"
							type="com.EMD.LSDB.vo.common.OrderVO">
							<logic:equal name="AppendixForm" property="perfCurveLinkFlag" value="Y">&nbsp;|&nbsp;
								<a href="javascript:fnLoadPerfCurve()">Performance Curve</a>&nbsp;
							</logic:equal>
						</logic:iterate>
					</logic:present>
			</div>
		</div>

		<div class="row">
			<div class="spacer50"></div>
		</div>
	
	
	<html:hidden property="hdnOrderKey" />
	<html:hidden property="appImageName" />
	<html:hidden property="appImageDesc" />
	<html:hidden property="toggleFlag" />
	<html:hidden property="subSectionSeqNo" />
	<html:hidden property="hdPreSelectedModel" />
	<html:hidden property="clauseSeqNo" />
	<html:hidden property="clauseDes" />
	<html:hidden property="versionNo" />
	<input type="hidden" name="tester" id="claMapped" value="0" />
	<html:hidden property="hdnRevViewCode" />
	<!-- Added For CR_84 -->
	<html:hidden property="perfCurveLinkFlag"/>
</html:form>
</div>

