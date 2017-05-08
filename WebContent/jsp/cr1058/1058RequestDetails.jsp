<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>

<logic:present name="ChangeRequest1058Form" property="requestDetailsList"
			scope="request" >
	<logic:iterate id="requestCommonDetail" name="ChangeRequest1058Form"
		property="requestDetailsList" type="com.EMD.LSDB.vo.common.ChangeRequest1058VO" scope="request">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h4 class="panel-title"><strong>Requested Changes</strong></h4>
				</div>
				<div class="panel-body">
					<div class="form-group required-field">
						<div class="multipleFormFields">
							<label class="control-label col-sm-2">Request From</label>
							<div class="col-sm-2">
								<logic:present name="ChangeRequest1058Form" property="categories" scope="request">
									<logic:iterate id="requestFrom" name="ChangeRequest1058Form" property="categories"
										type="com.EMD.LSDB.vo.common.RequestCategories1058VO" scope="request">
											<div class="radio">
											  <label>
											    <INPUT TYPE="radio" name="requestFrom" class="partOfColFormField" id="reqForm" value='<bean:write name="requestFrom" property="categorySeqNo"/>'/>
											    <bean:write name="requestFrom" property="categoryName"/>
											  </label>
											</div>
									</logic:iterate>
								</logic:present>
								<html:hidden styleId="categorySeqNo" name="requestCommonDetail" property="categorySeqNo" />
							</div>
						</div>
						<div class="multipleFormFields">
							<label class="control-label col-sm-5">Request Type</label>
							<div class="col-sm-3">
								<logic:present name="ChangeRequest1058Form" property="reqTypes" scope="request">
									<logic:iterate id="requestType" name="ChangeRequest1058Form" property="reqTypes"
										type="com.EMD.LSDB.vo.common.RequestTypes1058VO" scope="request">
										<div class="radio">
											<label>
												<INPUT TYPE="radio" name="requestType" class="partOfColFormField" id="reqType" value='<bean:write name="requestType" property="requestTypeSeqNo"/>' onClick="javascript:fnDisableRepList();">
												<bean:write name="requestType" property="requestTypeName"/>
											</label>
										</div>
									</logic:iterate>
								</logic:present>
								<html:hidden styleId="reqTypeSeqNo" name="requestCommonDetail" property="reqTypeSeqNo" />
							</div>
						</div>
					</div>
				
					<div class="form-group required-field">
						<label class="control-label col-sm-2">General Description</label>
						<div class="col-sm-6">
							<html:textarea name="requestCommonDetail" styleClass="form-control" styleId="genDesc" property="genDesc" rows="4" cols="100" onkeyup="fnSetMaxSize(this, 3999);"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-sm-2">Attach Document</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" id="attachmentName" class="form-control input-file-readonly" readonly/>
				                <span class="input-group-btn">
				                    <span class="btn btn-primary btn-file">
				                        Browse&hellip; <INPUT TYPE="file" CLASS="form-control" name="attachmentSource" id="attachmentSource" class="fileToAttach" />
				                    </span>
				                </span>		                
			            	</div> 
						</div>
						<div class="col-sm-offset-2 col-sm-4 form-control-static">
							<p><mark>NOTE: Select a File with less than 5MB size</mark></p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-sm-2">Click to View Attachment</label>
						<div class="col-sm-10 form-control-static">
							<div id="attachmentTable"> 
								<logic:present name="requestCommonDetail" 
									property="attachmentsList">
									<ul class="list-group list-group-horizontal">
									<logic:iterate id="attachment" name="requestCommonDetail" 
									property="attachmentsList" type="com.EMD.LSDB.vo.common.ChangeRequest1058AttachmentsVO">	
									<li class="list-group-item">
										<a title="Click to View/Download Attachment" data-toggle="tooltip" href="javascript:fnDwnLdAttachment(<bean:write name="attachment" property="imgSeqNo" />,'<bean:write name="attachment" property="imgName" />');" ><bean:write name="attachment" property="imgName" /></a>&nbsp; <a href="javascript:fnDeleteAttachment(<bean:write name="attachment" property="imgSeqNo" />)" data-toggle="tooltip" id="delete<bean:write name="attachment" property="imgSeqNo" />"  title="Click to delete attachment"><span class="glyphicon glyphicon-remove text-red" aria-hidden="true"></span></a>
									</li>
									</logic:iterate>
									</ul>
								</logic:present>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			
			<div class="panel panel-info">
				<div class="panel-heading">
					<h4 class="panel-title"><strong>Requested Effectivity</strong></h4>
				</div>
				<div class="panel-body">
					<div class="form-group required-field">
						<div class="multipleFormFields">
							<label class="control-label col-sm-2">Unit Numbers</label>
							<div class="col-sm-2">
								<INPUT TYPE="text" name="unitNumber" id="UnitNumber" CLASS="form-control partOfColFormField" size="20" value="<bean:write name="requestCommonDetail" property="unitNumber" />" />
							</div>
						</div>
						<div class="multipleFormFields">
							<label class="control-label col-sm-4">MCR Numbers</label>
							<div class="col-sm-2">
								<INPUT TYPE="text" name="mcrNumber" id="MCRNumber" CLASS="form-control partOfColFormField" size="20" value='<bean:write name="requestCommonDetail" property="mcrNumber" />'>
					    	</div>
					    </div>
					</div>
					<div class="form-group required-field">
						<div class="multipleFormFields">
							<label class="control-label col-sm-2">Road Numbers</label>
							<div class="col-sm-2">
								<INPUT TYPE="text" name="roadNumber" id="RoadNumber" CLASS="form-control partOfColFormField" size="20" value='<bean:write name="requestCommonDetail" property="roadNumber" />'>
							</div>
						</div>
						<div class="multipleFormFields">
							<div class="col-sm-offset-4 col-sm-4">
								<html:hidden styleId="mcrReq" name="requestCommonDetail" property="mcrReq" />
								<INPUT TYPE="radio" name="mcrnumberReq" id="mcrnumReq" class="partOfColFormField" value='1'> Required
								<INPUT TYPE="radio" name="mcrnumberReq" id="mcrnumReq" value='2'> Not Required
							</div>
						</div>
					</div>
					
				</div>
			</div>
			
			
			
			
			
</logic:iterate>
</logic:present>
		