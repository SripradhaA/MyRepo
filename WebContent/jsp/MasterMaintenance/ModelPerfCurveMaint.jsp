<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page language="java" import="java.io.*"%>
<script language="JavaScript" src="js/PerfCurveModel.js"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="js/File.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/Others/jquery.tablednd.js"></SCRIPT>
<script>
       $(document).ready(function() { 
       	$("#sltSpecType").select2({theme:'bootstrap'});
       	$("#sltModel").select2({theme:'bootstrap'});
       	});
</script>

<div class="container" width="100%">
	<html:form action="PerfCurveModelAction" enctype="multipart/form-data">
			<h4 class="text-center"><bean:message key="Application.Screen.PerfCurveModel" /></h4>
			<div class="spacer10"></div>
			<div class="row">
			<p class="col-sm-10 col-sm-offset-3"><mark><bean:message key="PerfCurveMessage" /></mark></p>
			<p class="col-sm-10 col-sm-offset-3"><mark><bean:message key="PerfCurveImgMessagePDF" /></mark></p>
			<p class="col-sm-10 col-sm-offset-3"><mark><bean:message key="TransparentImgMessageTiff" /></mark></p>
			</div>
			<div class="spacer10"></div>
			<div class="errorlayerhide" id="errorlayer">
			</div>
			<!-- Logic Tag Check For Display The Records After Upload and Delete Functionality Starts Here -->

			<logic:present name="ModelPerformanceveForm" property="resultList"
				scope="request">
				<bean:size name="ModelPerformanceveForm" id="modelsize"
					property="resultList" />
			</logic:present>
		
			<!-- Logic Tag Check For Display The Records After Upload and Delete Functionality Ends Here -->
		
			<!-- Logic Tag Check For Display The Success Message After Upload and Delete Functionality Starts Here -->
		
			<logic:present name="ModelPerformanceveForm" property="messageID"
				scope="request">
		
				<bean:define id="messageID" name="ModelPerformanceveForm" property="messageID"/>
		            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
		            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
			</logic:present>
		
			<logic:match name="ModelPerformanceveForm" property="method"
				value="fetchPerfCurveImages">
				<logic:lessThan name="modelsize" value="1">
					<script> 
		              fnNoRecords();
		        	</script>
				</logic:lessThan>
			</logic:match>
			<!-- Logic Tag Check For Display The Success Message After Upload and Delete Functionality Starts Here -->
			
			<div class="spacer10"></div>
		 
			<div class="row form-inline">
			<div class="col-sm-12 text-center">
				<div class="form-group required-field">
					<label class="control-label">Specification Type</label>
						<html:select styleClass="form-control" name="ModelPerformanceveForm"
								property="specTypeNo" styleId="sltSpecType"
								onchange="Javascript:fnLoadModels()">
								<option selected value="-1">---Select---</option>
								<logic:present name="ModelPerformanceveForm"
									property="specTypeList">
									<html:optionsCollection name="ModelPerformanceveForm"
										property="specTypeList" value="spectypeSeqno"
										label="spectypename" />
								</logic:present>
						</html:select>
				</div>
			<div class="form-group required-field">
					<label class="control-label">Model</label>
						<html:select styleClass="form-control" styleId="sltModel" name="ModelPerformanceveForm"
								property="modelSeqNo">
								<option selected value="-1">---Select---</option>
								<logic:present name="ModelPerformanceveForm" property="listModels">
									<html:optionsCollection name="ModelPerformanceveForm"
										property="listModels" label="modelName" value="modelSeqNo" />
								</logic:present>
						</html:select>
			</div>
			<div class="form-group">
				     <button class="btn btn-primary addbtn" type='button' id="searchButton" ONCLICK="javascript:fnSearchGenArrImages()"><bean:message key="PerfCurve.search" /></button>
			</div>
		</div>	
		</div>
		<div class="spacer30"></div>
		<logic:equal name="ModelPerformanceveForm" property="hdnDisp" value="0">
					<div class="row form-group">
						<div class="col-sm-offset-4 col-sm-3 text-right">
							<div class="input-group">
								<input type="text" class="form-control input-file-readonly" readonly/>
				               	<span class="input-group-btn">
				                   <span class="btn btn-primary btn-file">
				                       Browse&hellip; <html:file property="theFile" styleClass="form-control" styleId="uploadAttachment" onchange="javascript:validateFile(this)"/>
				                   </span>
				              	</span>		                
				          	</div> 
						</div>
						<div class="col-sm-5">
							<button class="btn btn-primary" type='button' ONCLICK="javascript:uploadModelGenImage()">Upload</button>
						</div>
					</div>
		</logic:equal>
		
		<logic:greaterThan name="modelsize" value="0">		
				<hr>
				<div class="spacer10"></div>
				<p class="text-center"  id="rearrInfo"><mark> <bean:message key="RearrangePerfCurveClickInfo" /></mark></p>
				<p class="text-center"  id="rearrInfo1"  class="text-center" style="display:none;" ><mark><bean:message	key="RearrangePerfCurveInfo" /></mark></p>
				<div class="spacer10"></div>
				<div class="row">
				<div class="col-sm-11 text-center"><h5 class="text-center"><strong>Search Criteria</strong></h5></div>
				</div>
				<div class="spacer10"></div>
				
				<div class="row">
					<div class="col-sm-4 col-sm-offset-1 text-right"><strong>Specification Type</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="ModelPerformanceveForm" property="specTypeNo" value="-1">
							<bean:write name="ModelPerformanceveForm" property="hdnSelSpecType"/>
						</logic:notEqual>
						<logic:equal name="ModelPerformanceveForm" property="specTypeNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				<div class="spacer10"></div>
				<div class="row">
					<div class="col-sm-4 col-sm-offset-1 text-right"><strong>Model</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="ModelPerformanceveForm" property="modelSeqNo" value="-1">
							<bean:write name="ModelPerformanceveForm" property="selectMdl"/>
						</logic:notEqual>
						<logic:equal name="ModelPerformanceveForm" property="modelSeqNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				<div class="spacer10"></div>
				<TABLE class="table table-bordered" id="perfCurveTable">
						<thead>
							<TR>
								<TH WIDTH="2%" class="text-center">Select</TH>
								<TH WIDTH="10%" class="text-center">Image</TH>
							</TR>
						</thead>
						<tbody class="text-center">
							<logic:iterate id="PerfCurveVO" name="ModelPerformanceveForm"
											property="resultList"
											type="com.EMD.LSDB.vo.common.PerformanceCurveVO" scope="request">
											<bean:define id="contenttype"
												value='<%=String.valueOf(PerfCurveVO.getFileVO().getContentType())%>' />
											<TR id="<%= String.valueOf(PerfCurveVO.getCurveSeqNo())%>">
												<TD class="v-midalign" width="2%" align="center"><html:radio
													 property="curveSeqNo" onclick="" value='<%= String.valueOf(PerfCurveVO.getCurveSeqNo())%>' />
												</TD>
												<logic:equal name="contenttype" value="application/pdf">
													<TD  width="85%" class="v-midalign">
														<div class="col-sm-offset-2 col-sm-2">
														<A class="linkstyleItemRed" 
																href="javascript:fnShowPDF('<%=PerfCurveVO.getFileVO().getImageSeqNo()%>','<%=PerfCurveVO.getImageName()%>')">PDF
															Image</A> 
															<input type="hidden" name="contenttype"
																value="<%=contenttype%>" /> <html:hidden property="imgSeqNo"
																value='<%=String.valueOf(PerfCurveVO.getFileVO().getImageSeqNo())%>' />
														</div>	
														<div class="col-sm-3 text-right">
															<label class="control-label">	PDF Image Name:</label>
														</div>
														<div class="col-sm-4">
															<html:text
																styleClass="form-control" name="PerfCurveVO"
																property="imageName" size="40" maxlength="100" />
														</div>
													</TD>
												</logic:equal>
												<logic:notEqual name="contenttype" value="application/pdf">
														<TD width="85%"  class="v-midalign">
															<div class="text-center">
																<img src="<%=request.getContextPath()%>/DownLoadImage.do?method=downloadImage&ImageSeqNo='<%=PerfCurveVO.getFileVO().getImageSeqNo()%>'"
																	border="0" alt=""> 
															</div>
															<div class="spacer10"></div>
															<html:hidden property="imgSeqNo"
																	value='<%=String.valueOf(PerfCurveVO.getFileVO().getImageSeqNo())%>' />
																	<input type="hidden" name="contenttype" value="<%=contenttype%>" />
															<div class="text-center form-inline">
																<label class="control-label">Image Name:
																	<html:text styleClass="form-control" name="PerfCurveVO"
																							property="imageName" size="40" maxlength="100" />
																</label>	
															</div>				
														</TD>
												</logic:notEqual>
											</TR>
										</logic:iterate>
						</tbody>
				</TABLE>
				
			<div class="spacer10"></div>	
			<div class="row form-group">
				<logic:greaterThan name="modelsize" value="0"> 
			        <div class="col-sm-12 text-center">
			        	<div id="btnRearrangeNotClicked" >
			              <button class="btn btn-primary" type='button' id="btnRearrangeNotClicked" ONCLICK="javascript:fnRearrangePerfCurveActivated()">Rearrange</button>
			            </div>
			            <div id="btnRearrangeClicked">
			              <button class="btn btn-primary" type='button' id="btnRearrangeClicked" ONCLICK="javascript:fnSaveRearrangedPerfCurve()">Save</button>
			              <button class="btn btn-primary" type='button' id="btnRearrangeClicked" ONCLICK="javascript:fnSearchGenArrImages()">Reset Performance Curve Selection</button>
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
		                       Browse&hellip; <html:file property="theFile" styleClass="form-control" styleId="uploadAttachment" onchange="javascript:validateFile(this)"/>
		                   </span>
		              	</span>		                
		          	</div> 
				</div>
				<div class="col-sm-5">
					<button class="btn btn-primary" type='button' ONCLICK="javascript:uploadModelGenImage()">Upload</button>
					<logic:greaterThan name="modelsize" value="0">
						<button class="btn btn-primary" type='button' id="modifyImage" ONCLICK="javascript:fnModifyImageName()">Modify PDF Image Name</button>
						<button class="btn btn-primary" type='button' ONCLICK="javascript:deleteModelGenImage()">Delete</button>
					</logic:greaterThan>
				</div>
			</div>		
		</logic:greaterThan>	
	<div class="spacer50"></div>		
	<html:hidden property="filePath" />
	<html:hidden property="curSeqNo" />
	<html:hidden property="modSeqNo" />
	<html:hidden property="imgSeqNo" />
	<html:hidden property="selectMdl" />
	<html:hidden property="hdnDisp" />
	<html:hidden property="hdnImageName" />
	<!-- Added For CR_84 -->
	<html:hidden property="hdnSelSpecType"/>	
		<%-- Added for CR_121 --%>	
	<html:hidden property="rowIndexList" />
	<html:hidden property="noOfPerfCurve" />
	<%-- Added for CR_121 Ends--%>			
	</html:form>
</div>