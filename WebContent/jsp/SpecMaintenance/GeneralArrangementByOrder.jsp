<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page language="java" import="java.io.*"%>

<script language="JavaScript" src="js/OrderGenArrangement.js"></script>
<script>
$(document).ready(function(){
if ($('.radcheck').length >6 )
	$('#addViewButton').attr("disabled", true); 
else
 	$('#addViewButton').removeAttr("disabled");
 });

</script>

<div class="container-fluid">
<html:form styleClass="form-horizontal" action="/orderGenArrangeAction.do" enctype="multipart/form-data">
	<h4 class="text-center"><bean:message key="Application.Screen.ModifySpec" /></h4>
		
	<!-- Validation Message Display Part Starts Here -->
	<div class="row">
		<div class="spacer10"></div>
	</div>
	<div class="row">
		<div class="errorlayerhide" id="errorlayer"></div>
	</div>
	<!-- Validation Message Display Part Ends Here -->
	<!-- Logic Tag Check For Display The Records After Add and Modify Functionality Starts Here -->

	<logic:present name="OrderGenArrangeForm" property="resultList"
		scope="request">
		<bean:size name="OrderGenArrangeForm" id="modelsize"
			property="resultList" />
	</logic:present>

	<!-- Logic Tag Check For Display The Records After Add and Modify Functionality Ends Here -->

	<!-- Logic Tag Check For Display The Success Message After Add and Modify Functionality Starts Here -->

	<logic:present name="OrderGenArrangeForm" property="messageID"
		scope="request">

			<bean:define id="messageID" name="OrderGenArrangeForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
	</logic:present>
	<br>
	<!-- Logic Tag Check For Display The Success Message After Add and Modify Functionality Ends Here -->
	<!-- Logic Tag Check For Display Of Header Fields Starts Here -->
	<logic:present name="OrderGenArrangeForm" property="orderList"
		scope="request">
		<logic:iterate id="DisList" name="OrderGenArrangeForm"
			property="orderList" type="com.EMD.LSDB.vo.common.OrderVO"
			scope="request">
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
		
		</logic:iterate>
	</logic:present>
	<!-- Logic Tag Check For Display Of Header Fields Ends Here -->
	<html:hidden property="orderKey" />
	<html:hidden property="filePath" />
	<!-- Modified Links for CR_91 -->
	<div class="row">
		<div class="col-sm-12 text-center">
			<a href="javascript:fnMainFeature()" class="linkstyleItem">
					General Information</a>&nbsp;&nbsp;|&nbsp;
			<logic:iterate id="section" name="OrderGenArrangeForm"
				property="sectionList" type="com.EMD.LSDB.vo.common.SectionVO"
				scope="request">
				<bean:define id="revCode" name="OrderGenArrangeForm" property="hdnRevViewCode" />
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
			</logic:iterate>
				<a href="javascript:fnLoadAppendix()" class="linkstyleItem">
					Appendix</a><!--Added for LSDB_CR_46 PM&I Change -->
					<!-- Modified For CR_84 -->
				<logic:equal name="OrderGenArrangeForm" property="perfCurveLinkFlag" value="Y">&nbsp;|&nbsp;
					<a href="javascript:fnLoadPerfCurve()" class="linkstyleItem">Performance Curve</a>&nbsp;
				</logic:equal>
		</div>
	</div>
			
			
	<div class="row">
		<div class="spacer10"></div>
	</div>
				
	<!-- Added For LSDB_CR-74[Revision Markers] on 01-June-09 by ps57222 -->
	<%--< Commenting this as per CR_90 to remove Revision Markers Drop down 
	<TABLE ALIGN="center" BORDER=0 width="100%">
		<TR>
			<TD ALIGN="right" WIDTH="45%"><span CLASS=greytext>Revision Markers</span>&nbsp;</TD>
			<TD ALIGN="left"><html:select name="OrderGenArrangeForm"
				property="revCode" styleClass="selectstyle2"
				onChange="javascript:fnChangeRevisionView()">
				<logic:present name="OrderGenArrangeForm" property="revisionList">
					<html:optionsCollection name="OrderGenArrangeForm"
						property="revisionList" value="revCode" label="revViewName" />
				</logic:present>
			</html:select></TD>
		</TR>
	</TABLE>
	Ends here --%>
<!-- LSDB_CR-74 Changes ENDS HERE -->

	<div class="row">
		<div class="col-sm-12 highlightDark text-center">
			<strong>General Info</strong>
		</div>
	</div>
					
			<div class="row">
				<div class="col-sm-12 text-center">
					<a href="javascript:fnMainFeature()">Main Features Information</a>&nbsp;|&nbsp;
			 		<a href="javascript:fnShowSpecification()">Specifications</a>&nbsp;|&nbsp;
					<a href="javascript:fnShowGeneralArrangement()">General	Arrangement</a>&nbsp;&nbsp;
				</div>
			</div>
					
			<div class="row">
				<div class="spacer10"></div>
			</div>
			
			<div class="row">
				<div class="col-sm-9 col-sm-offset-3 text-justify">
					<p><mark>NOTE: <bean:message key="GenArrangementTopFrontView" /></mark></p>
					<p><mark>NOTE: <bean:message key="GenArrangementSideView" /></mark></p>
					<p><mark>NOTE: <bean:message key="GenArrangementImageExtn" /></mark></p>
					<p><mark>NOTE: <bean:message key="GenArrangementTiffNoDisplay" /></mark></p>
					<p><mark>NOTE: <bean:message key="TransparentImgMessageTiff" /></mark></p>
				</div>
			</div>
			
			<div class="row">
				<div class="spacer10"></div>
			</div>
	<!--New Column is added to display the revision markers for Genral Arrangement Notes
		Added For LSDB_CR-74[Revision Markers] on 01-June-09 by ps57222 -->
			<div class="form-group">
				<div>
					<logic:equal name="OrderGenArrangeForm" property="notesMarkFlag" value="Y">
							<div class="col-sm-1 col-sm-offset-2 text-center col-border">
							<h4><span class="label label-primary"><bean:write name="OrderGenArrangeForm" property="notesMarkDesc"/></span></h4>
					</logic:equal>
					<logic:notEmpty name="OrderGenArrangeForm" property="gnNotesRevCode"> 
						<logic:notEqual name="OrderGenArrangeForm" property="notesMarkFlag" value="Y">
							<div class="col-sm-1 col-sm-offset-2 text-center col-border">
						</logic:notEqual>
						<logic:iterate id="RevCode" name="OrderGenArrangeForm" property="gnNotesRevCode">
							<bean:write name="RevCode"/>
						</logic:iterate>				
						<logic:notEqual name="OrderGenArrangeForm" property="notesMarkFlag" value="Y">
							</div>
							<div class="col-sm-1">
						</logic:notEqual>
					</logic:notEmpty>
					<logic:equal name="OrderGenArrangeForm" property="notesMarkFlag" value="Y">
						</div>
						<div class="col-sm-1">
					</logic:equal>
					<logic:empty name="OrderGenArrangeForm" property="gnNotesRevCode">
						<logic:notEqual name="OrderGenArrangeForm" property="notesMarkFlag" value="Y">
							<div class="col-sm-1 col-sm-offset-3">
						</logic:notEqual>
					</logic:empty>
					<label class="control-label"><strong>Notes</strong></label>
					</div>
					<div class="col-sm-4">
						<html:textarea styleClass="form-control" property="genArgmntNotes" name="OrderGenArrangeForm" rows="3" onkeyup="msgLimit(this, 4000);"></html:textarea>
					</div>
					<div class="col-sm-2">
						<button class="btn btn-primary" type='button' ONCLICK="javascript:fnModify()" >Modify Notes</button>
					</div>
				</div>	
			</div>
			
		<div class="row">
			<div class="spacer30"></div>
		</div>	
		
		<TABLE class="table table-bordered" id="genArrTable">
			<thead>
				<TR>
					<TH WIDTH="5%" class="text-center">Select</TH>
					<TH WIDTH="10%" class="text-center">Revision No</TH>
					<TH WIDTH="10%" class="text-center">View</TH>
					<TH WIDTH="55%" class="text-center">Image</TH>
					<TH WIDTH="15%" class="text-center">&nbsp;</TH>
				</TR>
			</thead>
			<tbody class="text-center">
				<logic:iterate id="GenNotes" name="OrderGenArrangeForm"
					property="resultList" type="com.EMD.LSDB.vo.common.GenArrangeVO"
					scope="request" indexId="cnt">
					<bean:define id="check" value='<%=String.valueOf(GenNotes.getFileVO().getImageSeqNo())%>' />
						<TR>
							<TD CLASS="v-midalign"><html:radio property="modelViewSeqNo"
								value='<%= String.valueOf(GenNotes.getModelViewSeqNo())%>' />
							</TD>
							<!--New Column is added to display the revision markers for Genral Arrangement Images
							Added For LSDB_CR-74[Revision Markers] on 01-June-09 by ps57222 -->
							<!-- Changed for CR-76 VV49326-->
							<TD CLASS="v-midalign">
								<logic:equal name="GenNotes" property="imgMarkFlag" value="Y">
									<h4><span class="label label-primary"><bean:write name="GenNotes" property="imgMarkDesc"/></span></h4>
								</logic:equal>
								<logic:notEmpty  name="GenNotes" property="revCode">
							        <logic:iterate id ="RevMarker" name="GenNotes" property="revCode">
											<bean:write name="RevMarker"/> 
									</logic:iterate>
								</logic:notEmpty>
							</TD>
							<!--Added For CR_101 -->
							<TD CLASS="v-midalign">
								<html:text name="GenNotes" property="modelView"	styleClass="form-control"/>
							</TD>
							
							<logic:greaterThan name="check" value="0">
							<TD>
									<table style="margin-left:auto;margin-right:auto;">
											<tr>
												<td>											
													<img src="<%=request.getContextPath()%>/DownLoadImage.do?method=downloadImage&ImageSeqNo='<%=GenNotes.getFileVO().getImageSeqNo()%>'"
														alt="" class="img-responsive col-xs-12">
												</td>
											</tr>
										</table>
										<div class="row text-center form-inline">
											<div class="spacer10"></div>
											<label class="control-label">Notes
											<html:textarea styleClass="form-control" property="mdlViewNotes" 
												name="GenNotes" rows="2" cols="50" onkeyup="msgLimit(this, 4000);"/>
										</label>
									</div>
							</TD>
							</logic:greaterThan>
						
							<logic:lessThan name="check" value="1">
							<TD>
								<div class="text-center form-inline">
									<label class="control-label">Notes
									<html:textarea styleClass="form-control" property="mdlViewNotes" 
										name="GenNotes" rows="2" cols="50" onkeyup="msgLimit(this, 4000);"/>
									</label>
								</div>
							</TD>
							</logic:lessThan>
							<TD CLASS="v-midalign text-center">
								<div>
									<div class="input-group">
										<input type="text" class="form-control input-file-readonly" readonly/>
						               	<span class="input-group-btn">
						                   <span class="btn btn-primary btn-file">
						                       Browse&hellip; <input type="file" name="viewImg[<%=cnt.intValue()%>]" id="viewImg<%=cnt.intValue()%>" class="form-control" onChange="javascript:validateFile(this)"/>
						                   </span>
						              	</span>		                
						          	</div>
						         </div>
							</TD>
						</TR>
				</logic:iterate>
			</tbody>
		</TABLE>
	
		<div class="form-group">
			<div class="Col-sm-12 text-center">
	         	<button class="btn btn-primary" type='button' ONCLICK="javascript:updateOrdGenArgmntViewDtls()">Modify</button>
	            <button class="btn btn-primary" type='button' id="btnModify" ONCLICK="javascript:fnDelOrdGenArgmtView()">Delete</button>
	       	</div>
	    </div>
	    
	    <div class="form-group">
	       	<div class="col-sm-12 text-center">
	       		<button class="btn btn-primary" type='button' ONCLICK="javascript:fnPrviewGenArgmtPDF()">Preview General Arrangement PDF</button>
	       	</div>
	    </div>
	    
		<div class="row">
			<div class="spacer10"></div>
		</div>
		
		<div class="row">
			<div class="col-sm-12 text-center">
				<a href="#top" CLASS=linkstyleItemNoUnderline>^Top</a>
			</div>
		</div>
	
	
		<div class="row">
			<div class="col-sm-12 text-center">
			<div class="spacer10"></div>
				<a href="javascript:fnMainFeature()" class="linkstyleItem">
						General Information</a>&nbsp;&nbsp;|&nbsp;
				<logic:iterate id="section" name="OrderGenArrangeForm"
					property="sectionList" type="com.EMD.LSDB.vo.common.SectionVO"
					scope="request">
					<bean:define id="revCode" name="OrderGenArrangeForm" property="hdnRevViewCode" />
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
						<logic:equal name="OrderGenArrangeForm" property="perfCurveLinkFlag" value="Y">&nbsp;|&nbsp;
						<a href="javascript:fnLoadPerfCurve()" class="linkstyleItem">Performance Curve</a>&nbsp;
					</logic:equal>
			</div>
		</div>
		
	<!-- CR 87 End -->

	<html:hidden property="hdnRevViewCode" />
	<!-- Added For CR_84 -->
	<html:hidden property="perfCurveLinkFlag" />
	<!-- Added For CR_101 -->
	<html:hidden property="hdSelectedMdlViewSeqNo" />
	<html:hidden property="hdSelectedMdlViewNotes" />
	<html:hidden property="hdSelectedMdlView" />
</html:form>

<div class="row">
	<div class="spacer50"></div>
</div>
</div>
