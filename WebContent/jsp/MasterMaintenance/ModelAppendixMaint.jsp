<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT LANGUAGE="JavaScript" SRC="js/File.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/ModelAppendixMaint.js"></SCRIPT>
<script>
       $(document).ready(function() { 
       	$("#sltSpecType").select2({theme:'bootstrap'});
       	$("#sltModel").select2({theme:'bootstrap'});
       })
</script>
<div class="container" width="100%">
  <html:form  action="/AppendixMaintenance" enctype="multipart/form-data">
		<h4 class="text-center"><bean:message key="Application.Screen.ModelAppendix" /></h4>
 		<p class="text-center"><mark><bean:message	key="AppendixImgMessage" /></mark></p>
 		<p class="text-center"><mark><bean:message key="AppendixImgMessagePDF" /></mark></p>
 		<p class="text-center"><mark><bean:message	key="TransparentImgMessageTiff" /></mark></p>
 		<div class="spacer10"></div>
		<div class="errorlayerhide" id="errorlayer">
		</div>
		<logic:present name="ModelAppendixForm" property="messageID" scope="request">
			<bean:define id="messageID" name="ModelAppendixForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
		</logic:present>
		
		<logic:present name="ModelAppendixForm" property="listImages" scope="request">
			<bean:size id="imagesize" name="ModelAppendixForm" property="listImages" />
		</logic:present>
		
		<logic:match name="ModelAppendixForm" property="method" value="NoAppendixImage">
			<logic:lessThan name="imagesize" value="1">
				<script> 
					fnNoRecords();
				</script>
			</logic:lessThan>
		</logic:match>
		
		 <div class="spacer10"></div>
		 <div class="row form-inline">
			<div class="col-sm-12 text-center">
				<div class="form-group required-field">
					<label class="control-label">Specification Type</label>
					<html:select styleClass="form-control" name="ModelAppendixForm" styleId="sltSpecType"
						property="specTypeNo" onchange="javascript:fnLoadModels()">
						<logic:present name="ModelAppendixForm" property="specTypeList">
							<option selected value="-1">---Select---</option>
							<html:optionsCollection name="ModelAppendixForm"
								property="specTypeList" value="spectypeSeqno" label="spectypename" />
						</logic:present>
					</html:select>
				</div>
				
				<div class="form-group required-field">
					<label class="control-label">Model</label>
					<html:select styleClass="form-control" styleId="sltModel" name="ModelAppendixForm"
						property="modelSeqNo">
						<option selected value="-1">---Select---</option>
						<logic:present name="ModelAppendixForm" property="listModels">
							<html:optionsCollection name="ModelAppendixForm"
								property="listModels" label="modelName" value="modelSeqNo" />
						</logic:present>
					</html:select>
				</div>
				
				<div class="form-group">
			           <button class="btn btn-primary" type='button' id="searchButton" ONCLICK="javascript:searchApendixImage()"><bean:message key="PerfCurve.search" /></button>
			    </div>
	     	</div>
	     </div>
	     
	     <div class="spacer30"></div>
	     <div class="form-horizontal">
	     	<div class="form-group required-field">			
				<label class="col-sm-5 control-label">Image Name</label>
				<div class="col-sm-3">					
					<html:text styleClass="form-control" property="imgName" maxlength="100" styleId="imgName"/>
				</div>
			</div>
			
			<div class="form-group">			
				<label class="col-sm-5 control-label">Image Description</label>
				<div class="col-sm-3">					
					<html:textarea styleClass="form-control" property="imgDesc" styleId="imgDesc" rows="2" cols="48" />
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-3">
					<div class="input-group">
						<input type="text" class="form-control input-file-readonly" readonly/>
		                <span class="input-group-btn">
		                    <span class="btn btn-primary btn-file">
		                        Browse&hellip; <html:file property="theFile" styleClass="form-control" styleId="theFile" onchange="javascript:validateFile(this)"/>
		                    </span>
		                </span>
	                </div>
	           	</div>
	           	<div class="col-sm-2">
      					<button class="btn btn-primary" type='button' id="uploadButton" ONCLICK="javascript:uploadAppendixImage()">	<bean:message key="Button.Upload" /></button>  		                
	           	</div>
			</div>
		</div>
				<logic:greaterThan name="imagesize" value="0">		
				<hr>		
				<div class="row">
				<div class="col-sm-11 text-center"><h5 class="text-center"><strong>Search critera</strong></h5></div>
				</div>
				<div class="spacer10"></div>
				
				<div class="row">
					<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Specification Type</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="ModelAppendixForm" property="specTypeNo" value="-1">
							<bean:write name="ModelAppendixForm" property="hdnSelSpecType"/>
						</logic:notEqual>
						<logic:equal name="ModelAppendixForm" property="specTypeNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				<div class="spacer10"></div>
				<div class="row">
					<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Model</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="ModelAppendixForm" property="modelSeqNo" value="-1">
							<bean:write name="ModelAppendixForm" property="selectedModel"/>
						</logic:notEqual>
						<logic:equal name="ModelAppendixForm" property="modelSeqNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				<div class="spacer10"></div>	
				
				<table class="table table-bordered table-hover">
					    <thead>
					        <tr>
								<TH width="5%" CLASS='text-center'>Select</TH>
								<TH width="15%" CLASS='text-center'>Image Name</TH>
								<TH width="15%" CLASS='text-center'>Image Description</TH>
								<TH width="10%" CLASS='text-center'>Image</TH>
								<TH width="55%" CLASS='text-center'>Clause Mapping</TH>
							</tr>	
						</thead>
					    <tbody class="text-center">	
					    	<logic:iterate id="ModelAppendixVO" name="ModelAppendixForm"
									property="listImages" type="com.EMD.LSDB.vo.common.ModelAppendixVO"
									scope="request" indexId="clausecount">
									<%--Added for CR_97 for allowing PDF in Appendix --%>
									<bean:define id="contenttype" value='<%=String.valueOf(ModelAppendixVO.getFileVO().getContentType())%>' />
									<%-- CR_97 Ends here  --%>
									<TR>
										<TD ><html:radio property="imgSeqNo"
											value='<%= String.valueOf(ModelAppendixVO.getImgSeqNo())%>' /></TD>
										<TD ><html:text styleClass="form-control" name="ModelAppendixVO"
											property="imageName" size="30" maxlength="60" /></TD>
										<TD><html:textarea styleClass="form-control" 
											name="ModelAppendixVO" property="imageDesc" rows="2" cols="32" /></TD>
										<TD>
										<!-- Added & Modified for CR_97 for allowing PDF in Appendix  -->
											<logic:equal name="contenttype" value="application/pdf">
												<A href="javascript:fnShowPDF('<%=ModelAppendixVO.getImgSeqNo()%>','<%=ModelAppendixVO.getImageName()%>')"
													data-toggle="tooltip" title="PDF">
													 <IMG SRC="images/pdf.png" alt="" height="25" BORDER="0">
												</A>
											</logic:equal>
											<logic:notEqual name="contenttype" value="application/pdf">
												<A href="javascript:fnShowImage('<%=ModelAppendixVO.getImgSeqNo()%>','<%=ModelAppendixVO.getImageName()%>')"
													data-toggle="tooltip" title="Image">
													 <IMG SRC="images/comp.gif" BORDER="0"> 
												</A>
											</logic:notEqual>
											<%-- CR_97 Ends here --%>
										</TD>
										<TD>
											<div class="form-inline">
												<div class="col-sm-12">
													<div class="col-sm-3 text-left">Link Clause&nbsp; <A
													HREF="javascript:fnLoadClauseDesc(<%=clausecount%>)"> <span class="glyphicon glyphicon glyphicon-search" name="img" aria-hidden="true"></span> </A>
													</div>
													<DIV class="col-sm-9 text-left" id="clauseDesc<%=clausecount%>"
													styleClass="height:57;width:500;overflow-Y: auto ; " >&nbsp;</DIV>
													<input type="hidden" name="divId" value='<%=clausecount%>' />
												</div>
											</div>
											<div class="col-sm-12">
												<div class="spacer10"></div>
											</div>
											<logic:present name="ModelAppendixVO" property="mapClauses">
													<bean:size id="clauseSize" name="ModelAppendixVO"
														property="mapClauses" />
													<logic:greaterThan name="clauseSize" value="0">
															<logic:iterate id="clause" name="ModelAppendixVO"
																	property="mapClauses" type="com.EMD.LSDB.vo.common.ClauseVO"
																	scope="page">
																	
																	<div class="form-inline">
																			<div class="col-sm-3"><A CLASS=linkstyleItem
																		href="javascript: fnViewMapping('<%=clause.getModelSeqNo() %>','<%=clause.getClauseSeqNo()%>','<%=clause.getVersionNo()%>','<%=clause.getSectionSeqNo()%>','<%=clause.getSubSectionSeqNo()%>','<%=clausecount%>')">View
																	Clause Mapping</A> <html:hidden name="clause"
																		property="imageMapNo" /> <input type="hidden"
																		name="tester" value="1" /></div>
																	</div>
																</logic:iterate>
													</logic:greaterThan>
												</logic:present> <logic:notPresent name="ModelAppendixVO"
													property="mapClauses">
											</logic:notPresent>
										</TD>
									</TR>
							</logic:iterate>
					    </tbody>
		 		</table>
		 		
		 		<div class="spacer10"></div>
				
				<div class="form-group">
				  <div class="col-sm-12 text-center">
				       <button class="btn btn-primary " type='button' id="deleteButton" ONCLICK="javascript:deleteAppendixImage()"><bean:message key="Button.Delete" /></button>
				        <button class="btn btn-primary " type='button' id="modifyButton" ONCLICK="javascript:updateImageName()"><bean:message key="Button.Modify" /></button>
				        <button class="btn btn-primary " type='button' id="saveButton" ONCLICK="javascript:fnSaveMappings()"><bean:message key="Button.Save" /></button>
					</div>   
			   	</div>
				<div class="spacer50"></div>
		</logic:greaterThan>	
	<html:hidden property="filePath" />
	<html:hidden property="selectedModel" />
	<html:hidden property="hdnModelSeqNo" />
	<html:hidden property="hdnImgSeqNo" />
	<html:hidden property="hdnImageName" />
	<html:hidden property="hdnImageDesc" />
	<html:hidden property="hdnSelSpecType" />			
	<html:hidden property="clauseDes" />
	<html:hidden property="hdPreSelectedModel" />
	<html:hidden property="subSectionSeqNo" />
	<html:hidden property="versionNo" />
	<html:hidden property="clauseSeqNo" />
  </html:form>
</div>	