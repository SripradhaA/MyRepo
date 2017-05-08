<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page language="java" import="java.io.*"%>
<script type="text/JavaScript" src="js/OrderPerfCurve.js"></script>
<SCRIPT type="text/JavaScript" src="js/File.js"></SCRIPT>
<SCRIPT type="text/JavaScript" src="js/Others/jquery.tablednd.js"></SCRIPT>

<div class="container-fluid">
<html:form styleClass="form-horizontal" action="orderPerfCurveAction.do"
	enctype="multipart/form-data">
		<h4 class="text-center"><bean:message key="Application.Screen.ModifySpec" /></h4>

	<!-- Validation Message Display Part Starts Here -->
	<div class="row">
		<div class="spacer10"></div>
	</div>
	<div class="row">
		<div class="errorlayerhide" id="errorlayer"></div>
	</div>

	<!-- Validation Message Display Part Ends Here -->

	<!-- Logic Tag Check For Display The Records After Upload and Delete Functionality Starts Here -->

	<logic:present name="OrderPerfCurveForm" property="resultList"
		scope="request">
		<bean:size name="OrderPerfCurveForm" id="modelsize"
			property="resultList" />
	</logic:present>

	<!-- Logic Tag Check For Display The Records After Upload and Delete Functionality Ends Here -->

	<!-- Logic Tag Check For Display The Success Message After Upload and Delete Functionality Starts Here -->

	<logic:present name="OrderPerfCurveForm" property="messageID"
		scope="request">

			<bean:define id="messageID" name="OrderPerfCurveForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>



	</logic:present>
	<!-- Modified Links for CR_91 -->
	<!-- Logic Tag Check For Display Of Header Fields Starts Here -->
	<logic:present name="OrderPerfCurveForm" property="orderList"
		scope="request">
		<logic:iterate id="DisList" name="OrderPerfCurveForm"
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
	<html:hidden property="hdnImageName" />
	
	<div class="row">
		<div class="col-sm-12 text-center">
			<a href="javascript:fnMainFeature()" class="linkstyleItem">
					General Information</a>&nbsp;&nbsp;|&nbsp;
			<logic:iterate id="section" name="OrderPerfCurveForm"
				property="sectionList" type="com.EMD.LSDB.vo.common.SectionVO"
				scope="request">
				<bean:define id="revCode" name="OrderPerfCurveForm" property="hdnRevViewCode" />
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
					&nbsp;|&nbsp;
					<a href="javascript:fnLoadPerfCurve()" class="linkstyleItem">
						Performance Curve</a>&nbsp;
		</div>
	</div>
	
	
	<div class="row">
		<div class="spacer10"></div>
	</div>
	
	
	

	<!-- Added For LSDB_CR-74[Revision Markers] on 01-June-09 by ps57222 -->

<%--<!-- Commenting this as per CR_90 to remove Revision Markers Drop down 
 	<TABLE ALIGN="center" BORDER=0 width="100%">
		<TR>
	
			<TD ALIGN="right" WIDTH="45%"><span CLASS=greytext>Revision Markers</span>&nbsp;</TD>
			<TD ALIGN="left"><html:select name="OrderPerfCurveForm"
				property="revCode" styleClass="selectstyle2"
				onChange="javascript:fnChangeRevisionView()">
				<logic:present name="OrderPerfCurveForm" property="revisionList">
					<html:optionsCollection name="OrderPerfCurveForm"
						property="revisionList" value="revCode" label="revViewName" />
				</logic:present>
			</html:select></TD>
		</TR>
	</TABLE>
Ends here --%>
	<!-- LSDB_CR-74 Ends Here -->

	<!-- User Table begins Here -->
	<div class="row">
		<div class="col-sm-12 highlightDark text-center">
			<strong>Performance Curves</strong>
		</div>
	</div>
	
	<div class="row">
		<div class="spacer10"></div>
	</div>
	
	<div class="row">
		<div class="col-sm-9 col-sm-offset-3 text-justify">
			<p><mark><bean:message key="PerfCurveMessage" /></mark></p>
			<p><mark><bean:message key="PerfCurveImgMessagePDF" /></mark></p>
			<p><mark><bean:message key="TransparentImgMessageTiff" /></mark></p>
		</div>
	</div>
	
	<div class="row">
		<div class="spacer10"></div>
	</div>
	
	<logic:greaterThan name="modelsize" value="0"> <%--Logic tag added for CR-125 to hide rearranage button--%>
		<p class="text-center" id="rearrInfo">
			<bean:message key="RearrangePerfCurveClickInfo" />
		</p>
		<p style="display:none;" class="text-center" id="rearrInfo1">
			<bean:message key="RearrangePerfCurveInfo" />
		</p>
		<div class="row">
			<div class="spacer10"></div>
		</div>
	</logic:greaterThan>
	
	<logic:greaterThan name="modelsize" value="0">
	
		<TABLE class="table table-bordered" id="perfCurveTable">
			<thead>
				<TR>
					<TH class="text-center" WIDTH="5%" ID="select">Select</TH><!-- Id Added for CR_121 -->
					<TH class="text-center" WIDTH="10%">Revision No</TH>
					<TH class="text-center" WIDTH="85%">Performance Curves</TH>
				</TR>
			</thead>
			<!--Modified for uploading performance curve in PDF format change. on 21-Nov-08 by ps57222 -->
			<tbody>
				<logic:iterate id="PerfCurveVO" name="OrderPerfCurveForm"
					property="resultList"
					type="com.EMD.LSDB.vo.common.PerformanceCurveVO" scope="request">
					<bean:define id="contenttype"
						value='<%=String.valueOf(PerfCurveVO.getFileVO().getContentType())%>' />
					<TR id="<%= String.valueOf(PerfCurveVO.getCurveSeqNo())%>">
						<TD width="5%" class="text-center v-midalign"><html:radio property="curveSeqNo"
							onclick=""
							value='<%= String.valueOf(PerfCurveVO.getCurveSeqNo())%>' />
						</TD>
						
						<!--Added For LSDB_CR-74[Revision Markers] on 01-June-09 by ps57222 -->
						<!-- Changed for CR-76 VV49326 10-08-09-->
						<TD width="10%" class="text-center v-midalign">
						
						<!-- Added for CR-76 VV49326 Starts Here-->
							<logic:equal name="PerfCurveVO" property="sysMarkFlag" value="Y">
								<span class="label label-primary"><bean:write name="PerfCurveVO" property="sysMarkDesc"/></span>
							</logic:equal>
						<!-- Added for CR-76 VV49326 Ends Here-->
						
						<logic:notEmpty  name="PerfCurveVO" property="revCode">
							<logic:iterate id ="RevMarker" name="PerfCurveVO" property="revCode">
								<bean:write name="RevMarker"/> <BR>
							</logic:iterate>
						</logic:notEmpty>
	
						</TD>
						<TD  width="85%" class="v-midalign">
						<logic:equal name="contenttype" value="application/pdf">							
								<div class="row">
									<div class="spacer10"></div>
								</div>
								<div class="form-group required-field">	
									<div class="col-sm-offset-2 col-sm-2 form-control-static">
										<A class="text-danger" href="javascript:fnShowPDF('<%=PerfCurveVO.getFileVO().getImageSeqNo()%>','<%=PerfCurveVO.getImageName()%>')">
											PDF Image
										</A>
										<input type="hidden" name="contenttype"
										value="<%=contenttype%>" /> <html:hidden property="imgSeqNo"
										value='<%=String.valueOf(PerfCurveVO.getFileVO().getImageSeqNo())%>' />
									</div>
									<label class="col-sm-3 control-label">PDF Image Name:</label>
									<div class="col-sm-4">
										<html:text styleClass="form-control" property="imageName" 
											name="PerfCurveVO" size="40"  maxlength="100"/>
									</div>
								</div>
						</logic:equal>
						<logic:notEqual name="contenttype" value="application/pdf">
								<div class="row">
									<div class="spacer10"></div>
								</div>
								<div class="form-group">
									<div class="text-center">
										<img src="<%=request.getContextPath()%>/DownLoadImage.do?method=downloadImage&ImageSeqNo='<%=PerfCurveVO.getFileVO().getImageSeqNo()%>'"
											border="0" alt=""> 
									</div>
											<html:hidden property="imgSeqNo"
											value='<%=String.valueOf(PerfCurveVO.getFileVO().getImageSeqNo())%>' />
										<input type="hidden" name="contenttype" value="<%=contenttype%>" />
								</div>
								<div class="form-group required-field">									
									<label class="col-sm-2 col-sm-offset-3 control-label">Image Name:</label>
									<div class="col-sm-2">
										<html:text styleClass="form-control" property="imageName" 
											name="PerfCurveVO" size="40"  maxlength="100"/>
									</div>
								</div>
						</logic:notEqual>
							</TD>
					</TR>
				</logic:iterate>
			</tbody>
		</TABLE>
	</logic:greaterThan>
	<!-- User Table Ends Here -->
	<br>

	<!--  Table For Button begins Here -->
	
	<div class="row form-group">
		<logic:greaterThan name="modelsize" value="0"> 
	        <div class="col-sm-12 text-center">
	        	<div id="btnRearrangeNotClicked" >
	              <button class="btn btn-primary" type='button' id="btnRearrangeNotClicked" ONCLICK="javascript:fnRearrangePerfCurveActivated()">Rearrange</button>
	            </div>
	            <div id="btnRearrangeClicked">
	              <button class="btn btn-primary" type='button' id="btnRearrangeClicked" ONCLICK="javascript:fnSaveRearrangedPerfCurve()">Save</button>
	              <button class="btn btn-primary" type='button' id="btnRearrangeClicked" ONCLICK="javascript:fnLoadPerfCurve()">Reset Performance Curve Selection</button>
	            </div>
	        </div>
	    </logic:greaterThan>
	</div>
	
	<div class="row">
		<div class="spacer10"></div>
	</div>
	
	<div class="row form-group">
		<div class="col-sm-offset-3 col-sm-3 text-right">
			<div class="input-group">
				<input type="text" class="form-control input-file-readonly" readonly/>
               	<span class="input-group-btn">
                   <span class="btn btn-primary btn-file">
                       Browse&hellip; <html:file property="theFile" styleClass="form-control" styleId="uploadAttachment"/>
                   </span>
              	</span>		                
          	</div> 
		</div>
		<div class="col-sm-5">
			<button class="btn btn-primary" type='button' ONCLICK="javascript:uploadOrderGenImage()">Upload</button>
			<logic:greaterThan name="modelsize" value="0">
				<button class="btn btn-primary" type='button' id="modifyImage" ONCLICK="javascript:fnModifyImageName()">Modify PDF Image Name</button>
				<button class="btn btn-primary" type='button' ONCLICK="javascript:deleteOrderGenImage()">Delete</button>
			</logic:greaterThan>
		</div>
	</div>
			
		
	<div class="row">
		<div class="spacer10"></div>
		<div class="col-sm-12 text-center">
			<a href="#top" CLASS=linkstyleItemNoUnderline>^Top</a>
		</div>
	</div>
	
<!-- CR 87 Start -->
<div class="row">
				<div class="col-sm-12 text-center">
				<div class="spacer10"></div>
					<a href="javascript:fnMainFeature()" class="linkstyleItem">
							General Information</a>&nbsp;&nbsp;|&nbsp;
					<logic:iterate id="section" name="OrderPerfCurveForm"
						property="sectionList" type="com.EMD.LSDB.vo.common.SectionVO"
						scope="request">
						<bean:define id="revCode" name="OrderPerfCurveForm" property="hdnRevViewCode" />
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
					Appendix</a><!--Added for LSDB_CR_46 PM&I Change -->&nbsp;|&nbsp;
							<!-- Modified For CR_84 -->
							<a href="javascript:fnLoadPerfCurve()" class="linkstyleItem">Performance Curve</a>&nbsp;
						
				</div>
			</div>
			
			<div class="row">
				<div class="spacer50"></div>
			</div>
	<html:hidden property="hdnRevViewCode" />
	<%-- Added for CR_121 --%>	
	<html:hidden property="rowIndexList" />
	<html:hidden property="noOfPerfCurve" />
	<%-- Added for CR_121 Ends--%>	
</html:form>
</div>
	
