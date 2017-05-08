<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT LANGUAGE="JavaScript" SRC="js/File.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/ModelGenArrangeMent.js"></SCRIPT>

<script>
       $(document).ready(function() { 
	       	$("#sltSpecType").select2({theme:'bootstrap'});
	       	$("#sltModel").select2({theme:'bootstrap'});
	       	
	       	if ($('input[type=radio]').length >= 6 )	{
				$('#addViewButton').prop("disabled",true); 
			}
			else	{
			 	$('#addViewButton').prop("disabled",false);
		 	}     	
       	})
</script>
<div class="container-fluid">
	<html:form action="/GenArrByModel" enctype="multipart/form-data">
 		<h4 class="text-center"><bean:message key="Application.Screen.GenArgmntModel" /></h4>
 		<div class="spacer10"></div>
 		<div class="row">
	 		<div class="col-sm-12 text-center"><p><mark>NOTE:<bean:message	key="GenArrangementTopFrontView" /></mark></p></div>
	 		<div class="col-sm-12 text-center"><p><mark>NOTE:<bean:message	key="GenArrangementSideView" /></mark></p></div>
	 		<div class="col-sm-12 text-center"><p><mark>NOTE:<bean:message	key="GenArrangementImageExtn" /></mark></p></div>
	 		<div class="col-sm-12 text-center"><p><mark>NOTE:<bean:message	key="GenArrangementTiffNoDisplay" /></mark></p></div>
	 		<div class="col-sm-12 text-center"><p><mark><bean:message	key="TransparentImgMessageTiff" /></mark></p></div>
 		</div>
		<div class="spacer10"></div>
		<div class="errorlayerhide" id="errorlayer">
		</div>
		
		<!-- Logic Tag Check For Display The Records After Add and Modify Functionality Starts Here -->

		<logic:present name="ModelGenArrangeForm" property="resultList"
			scope="request">
			<bean:size name="ModelGenArrangeForm" id="modelsize"
				property="resultList" />
		</logic:present>
	
		<!-- Logic Tag Check For Display The Records After Add and Modify Functionality Ends Here -->
	
		<!-- Logic Tag Check For Display The Success Message After Add and Modify Functionality Starts Here -->
	
		<logic:present name="ModelGenArrangeForm" property="messageID"
			scope="request">
	
				<bean:define id="messageID" name="ModelGenArrangeForm" property="messageID"/>
	            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
	            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
	
		</logic:present>
	
		<logic:match name="ModelGenArrangeForm" property="method"
			value="fetchGenArrImages">
			<logic:lessThan name="modelsize" value="1">
				
						<script> 
	             			 fnNoRecords();
	       				</script>
			</logic:lessThan>
		</logic:match>
	
		<bean:define id="parent" name="ModelGenArrangeForm"
			property="imageSeqNo" />
		<!-- Logic Tag Check For Display The Success Message After Add and Modify Functionality Starts Here -->
		
		 <div class="spacer10"></div>
		 
		 <div class="form-horizontal">
			<div class="form-group required-field">
					<label class="col-sm-5 control-label">Specification Type</label>
					<div class="col-sm-3">
						<html:select styleClass="form-control" name="ModelGenArrangeForm"
									property="specTypeNo" styleId="sltSpecType"
									onchange="Javascript:fnLoadModels()">
									<option selected value="-1">---Select---</option>
									<logic:present name="ModelGenArrangeForm"
										property="specTypeList">
										<html:optionsCollection name="ModelGenArrangeForm"
											property="specTypeList" value="spectypeSeqno"
											label="spectypename" />
									</logic:present>
						</html:select>
					</div>
			</div>
			<div class="form-group required-field">
					<label class="col-sm-5 control-label">Model</label>
					<div class="col-sm-3">
						<html:select styleClass="form-control" styleId="sltModel" name="ModelGenArrangeForm"
							property="modelSeqNo" onchange="Javascript:fnSearchGenArrImages()">
							<option selected value="-1">---Select---</option>
							<logic:present name="ModelGenArrangeForm" property="modelList">
								<html:optionsCollection name="ModelGenArrangeForm"
									property="modelList" label="modelName" value="modelSeqNo" />
							</logic:present>
						</html:select>
					</div>
			</div>
			<div class="form-group required-field">			
				<label class="col-sm-5 control-label">View Name</label>
				<div class="col-sm-3">					
					<html:text styleClass="form-control" name="ModelGenArrangeForm" property="mdlNewViewName" maxlength="40" styleId="mdlNewViewName"/>
					
				</div>
			</div>
			
			<div class="form-group">			
				<label class="col-sm-5 control-label">Notes</label>
				<div class="col-sm-3">					
					<html:textarea styleClass="form-control" name="ModelGenArrangeForm" property="mdlNewViewNotes" styleId="mdlNewViewNotes" rows="2" cols="48" />
				</div>
			</div>
			
			<div class="form-group">
					<div class="col-sm-offset-5 col-sm-3">
							<div class="input-group">
								<input type="text" class="form-control input-file-readonly" readonly/>
				                <span class="input-group-btn">
				                    <span class="btn btn-primary btn-file">
				                        Browse&hellip; <html:file property="mdlNewViewImage" styleClass="form-control" styleId="mdlNewViewImage" onchange="javascript:validateFile(this)"/>
				                    </span>
				                </span>
			                </div>
		           	</div>
		           	<div class="col-sm-2">
       					<button class="btn btn-primary" type='button' id="addViewButton" data-toggle="tooltip" ONCLICK="javascript:uploadModelGenImage()">	<bean:message key="screen.addview" /></button>  		                
		           	</div>
			</div>
		</div>
		<logic:greaterThan name="modelsize" value="0">		
			
				<hr>
				<div class="spacer10"></div>
				<div class="row">
					<div class="col-sm-12 form-group ">
						<div class="col-sm-1 col-sm-offset-3">
							<label class="control-label"><strong>Notes</strong></label>
						</div>
						<div class="col-sm-5">	
							<html:textarea name="ModelGenArrangeForm" styleClass="form-control" property="genArgmntNotes" rows="2" cols="50" onkeyup="msgLimit(this, 3999);">
								<bean:write name="ModelGenArrangeForm" property="genArgmntNotes" />
							</html:textarea>
						</div>
						<div class="col-sm-2">	
							<button class="btn btn-primary" type='button' ONCLICK="javascript:fnModify()" ><bean:message key="screen.modifyNotes" /></button>
						</div>
					</div>	
				</div>
				<div class="spacer10"></div>
						
				<div class="row">
				<div class="col-sm-11 text-center"><h5 class="text-center"><strong>Search Criteria</strong></h5></div>
				</div>
				<div class="spacer10"></div>
				
				<div class="row">
					<div class="col-sm-4 col-sm-offset-1 text-right"><strong>Specification Type</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="ModelGenArrangeForm" property="specTypeNo" value="-1">
							<bean:write name="ModelGenArrangeForm" property="hdSelectedSpecType"/>
						</logic:notEqual>
						<logic:equal name="ModelGenArrangeForm" property="specTypeNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				<div class="spacer10"></div>
				<div class="row">
					<div class="col-sm-4 col-sm-offset-1 text-right"><strong>Model</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="ModelGenArrangeForm" property="modelSeqNo" value="-1">
							<bean:write name="ModelGenArrangeForm" property="hdSelectedModel"/>
						</logic:notEqual>
						<logic:equal name="ModelGenArrangeForm" property="modelSeqNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				<div class="spacer30"></div>
				
				<div class="row">
					<TABLE class="table table-bordered" id="genArrTable">
						<thead>
							<TR>
								<TH WIDTH="5%" class="text-center">Select</TH>
								<TH WIDTH="10%" class="text-center">View</TH>
								<TH WIDTH="55%" class="text-center">Image</TH>
								<TH WIDTH="15%" class="text-center">&nbsp;</TH>
							</TR>
						</thead>
						<tbody class="text-center">
								<logic:iterate id="GenNotes" name="ModelGenArrangeForm"
										property="resultList" type="com.EMD.LSDB.vo.common.GenArrangeVO"
										scope="request" indexId="cnt">
										<bean:define id="check"
											value='<%=String.valueOf(GenNotes.getFileVO().getImageSeqNo())%>' />
						 			<TR>
											<TD class="v-midalign" width="2%" align="center"><html:radio
														 property="modelViewSeqNo" styleId="mdlViewSeqNo"
												value='<%= String.valueOf(GenNotes.getModelViewSeqNo())%>' /></TD>
											<TD class="v-midalign" width="3%">
											<html:text name="GenNotes" property="modelView" 
														styleClass="form-control" size="10" maxlength="40" /></TD>
							
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
												<html:textarea name="GenNotes" styleClass="form-control"  property="mdlViewNotes" rows="2" cols="50" onkeyup="msgLimit(this, 3999);">
												  <bean:write name="GenNotes" property="mdlViewNotes" />
												</html:textarea>
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
											<bean:write name="GenNotes"   property="mdlViewNotes" />
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
				</div>	
				
				<div class="row">
					<div class="form-group">
						<div class="Col-sm-12 text-center">
				         	<button class="btn btn-primary" type='button' ONCLICK="javascript:updateMdlGenArgmntViewDtls()"><bean:message key="screen.modify" /></button>
				            <button class="btn btn-primary" type='button' ONCLICK="javascript:fnDelMdlGenArgmtView()"><bean:message key="screen.delete" /></button>
				        </div>
				     </div>
				</div>	
		</logic:greaterThan>		
		<div class="spacer50"></div>	
	<!--  hdSelectedSpecType Added fro CR_84 -->
	<html:hidden property="hdSelectedSpecType" />
	<html:hidden property="hdPreSelectedSpecType" />
	<html:hidden property="filePath" />
	<html:hidden property="hdPreSelectedModel" />
	<html:hidden property="hdSelectedModel" />
	<!-- Added For CR_101 -->
	<html:hidden property="hdSelectedMdlViewSeqNo" />
	<html:hidden property="hdSelectedMdlViewNotes" />
	<html:hidden property="hdSelectedMdlView" />	
	</html:form>
</div>			       	