<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT type="text/JavaScript" SRC="js/OrderSpec.js"></SCRIPT>
<SCRIPT type="text/JavaScript" SRC="js/Others/jquery.tablednd.js"></SCRIPT>

<div class="container-fluid" >
<html:form action="/OrderSpecAction" method="post">
	<h4 class="text-center"><bean:message key="Application.Screen.ModifySpec" /></h4>
	

	<!-- Validation Message Display Part Starts Here -->
	<div class="row">
		<div class="spacer10"></div>
	</div>
	<div class="row">
		<div class="errorlayerhide" id="errorlayer"></div>
	</div>
	<!-- Validation Message Display Part Ends Here -->


	<logic:present name="OrderSpecificationForm" property="specList"
		scope="request">
		<bean:size id="orderspeclen" name="OrderSpecificationForm"
			property="specList" />
	</logic:present>
	<!-- Logic Tag Check For Display The No Records Found Message  Functionality Starts Here -->
	<logic:match name="OrderSpecificationForm" property="method"
		value="Search">
		<logic:lessThan name="orderspeclen" value="1">
			<script>
		                var id = '4';
		                 hideErrors();
		                 addMsgNum(id);
		                 showScrollErrors();
		               </script>
		</logic:lessThan>
	</logic:match>
	<!-- Logic Tag Check For Display The No Records Found Message  Functionality Ends Here -->

	<!-- To display Server Side Validation Messages - Start -->
	<logic:present name="OrderSpecificationForm" property="messageID"
		scope="request">
			<bean:define id="messageID" name="OrderSpecificationForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
	</logic:present>
	<!-- To display Server Side Validation Messages - End -->
    	<!-- Added to land particular Specification starts -->
	<logic:present name="specseqno" scope="request">
		<bean:define id="specific" name="specseqno" scope="request" />
		<script>
            document.location.href="#<%=specific%>";
       </script>
   </logic:present>
	<!-- Added to land particular Specification ends -->
	<div class="row">
		<div class="spacer10"></div>
	</div>
	<logic:present name="OrderSpecificationForm" property="orderList"
		scope="request">
		<logic:iterate id="DisList" name="OrderSpecificationForm"
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
	
	<html:hidden property="orderKey" />
	
			<div class="row">
				<div class="col-sm-12 text-center">
					<a href="javascript:fnMainFeature()" class="linkstyleItem">
							General Information</a>&nbsp;&nbsp;|&nbsp;
					<logic:iterate id="section" name="OrderSpecificationForm"
						property="sectionList" type="com.EMD.LSDB.vo.common.SectionVO"
						scope="request">
						<bean:define id="revCode" name="OrderSpecificationForm" property="hdnRevViewCode" />
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
						<logic:equal name="OrderSpecificationForm" property="perfCurveLinkFlag" value="Y">&nbsp;|&nbsp;
							<a href="javascript:fnLoadPerfCurve()" class="linkstyleItem">Performance Curve</a>&nbsp;
						</logic:equal>
				</div>
			</div>
			
			<div class="row">
				<div class="spacer10"></div>
			</div>
								
			<div class="row">
				<div class="col-md-12 highlightDark text-center">
					<strong>General Info</strong>
				</div>
			</div>
					
			<div class="row">
				<div class="col-md-12 text-center">
					<a href="javascript:fnMainFeature()" class="linkstyleItem">
			 			Main Features Information</a>&nbsp;|&nbsp;
			 		<a href="javascript:fnShowSpecification()" class="linkstyleItem">
						Specifications</a>
					<logic:equal name="OrderSpecificationForm" property="genArrLinkFlag" value="Y">&nbsp;|&nbsp;&nbsp;
						<a href="javascript:fnShowGeneralArrangement()" class="linkstyleItem">
							General	Arrangement</a>&nbsp;&nbsp;
					</logic:equal>
				</div>
			</div>
					
			<div class="row">
				<div class="spacer10"></div>
			</div>
			
			<div class="row">
				<div class="col-md-12 highlightDark text-center">
					<div class="col-sm-offset-1 col-sm-10 text-center "><strong>Specifications</strong></div>
					<div class="col-sm-1 text-right ">
						<button class="btn btn-primary btn-sm" type='button' ONCLICK="javascript:initLoadSpecification()">Add Description</button>
					</div>
				</div>
			</div>
						
		
		<div class="form-inline">
			<div class="row">
				<logic:equal name="OrderSpecificationForm" property="hpSysMarkFlag" value="Y">
					<div class="col-sm-1 text-center col-border" >
						<h4><span class="label label-primary"><bean:write name="OrderSpecificationForm" property="hpSysMarkDesc"/></span></h4>
				</logic:equal>
				<logic:notEmpty name="OrderSpecificationForm" property="hpRatingMarkers"> 
					<logic:notEqual name="OrderSpecificationForm" property="hpSysMarkFlag" value="Y">
						<div class="col-sm-1 text-center col-border" >
					</logic:notEqual>
					<logic:iterate id="hpMarker" name="OrderSpecificationForm" property="hpRatingMarkers">
						<bean:write name="hpMarker"/>
					</logic:iterate>				
					<logic:notEqual name="OrderSpecificationForm" property="hpSysMarkFlag" value="Y">
						</div>
					</logic:notEqual>
				</logic:notEmpty>
				<logic:equal name="OrderSpecificationForm" property="hpSysMarkFlag" value="Y">
					</div>
				</logic:equal>
				<div class="form-group required-field">
					<label class="col-sm-4 control-label text-right">Horse Power Rating</label>
					<div class="col-sm-7">
						<html:textarea styleClass="form-control" property="orderHpDesc" name="OrderSpecificationForm" rows="2" cols="48" onkeyup="msgLimit(this, 4000);"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-3"><button class="btn btn-primary" type='button' ONCLICK="javascript:fnModifyHpRating()" >Modify HP Rating</button></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="spacer30"></div>
		</div>	
	
	<logic:iterate id="OrderSpecDetailsId" name="OrderSpecificationForm"
		property="specList" type="com.EMD.LSDB.vo.common.SpecificationVO">
		<logic:notEqual name="OrderSpecDetailsId" property="specName" value="">
			<A NAME="<%=OrderSpecDetailsId.getSpecSeqNo()%>"></A>
				
				<div class="bg-info row">
					<div class="col-sm-offset-2 col-sm-6 text-center push-text-down">
						<strong>
							<bean:write name="OrderSpecDetailsId" property="specName" />
						</strong>
					</div>
					<div class="col-sm-4 text-right">
						<button class="btn btn-primary btn-sm" type='button' ONCLICK="javascript:initLoadSpecificationItem('<%=OrderSpecDetailsId.getSpecName()%>','<%=OrderSpecDetailsId.getSpecSeqNo()%>')" >Add Line</button>
						<button class="btn btn-primary btn-sm" type='button' ONCLICK="javascript:displayModifiedSpecification('<%=OrderSpecDetailsId.getSpecName()%>','<%=OrderSpecDetailsId.getSpecSeqNo()%>')">Modify Heading</button>
						<button class="btn btn-primary btn-sm" type='button' ONCLICK="javascript:deleteSpecification('<%=OrderSpecDetailsId.getSpecSeqNo()%>')">Delete Entire Section</button>
					</div>
				</div>
				
				<div class="row">
					<div class="spacer10"></div>
				</div>
			
				<TABLE class="table table-bordered" id="typeDetails">
					<thead >
						<tr>
							<th class="text-center" width="5%">Select</th>
							<th class="text-center" width="10%">Revision No</th>
							<th class="text-center" width="60%">Description</th>
							<th class="text-center" width="20%">Value</th>
						</tr>
					</thead>
				
                            <!--Added for CR-74 VV49326 02-06-09 - Ends Here-->
					<logic:notEmpty name="OrderSpecDetailsId" property="specItem">
						<!-- Table to display the fields after Search/Modify/Add Starts -->
						<logic:iterate id="OrderItemDetailsId" name="OrderSpecDetailsId"
								property="specItem"
								type="com.EMD.LSDB.vo.common.SpecificationItemVO">
							<tbody class="text-center">
								<tr>
									<TD WIDTH="5%" class="text-center"><html:radio
										value='<%=String.valueOf(OrderItemDetailsId.getItemSeqNo())%>'
										property="itemSeqNo" /></TD>
		
		                            <!--Added for CR-74 VV49326 02-06-09 - Starts Here-->
		                            <TD WIDTH="10%">
			                            <logic:equal name="OrderItemDetailsId" property="sysMarkFlag" value="Y">
			                            	<h4><span class="label label-primary"><bean:write name="OrderItemDetailsId" property="sysMarkDesc"/></span></h4>
									    </logic:equal> 
		                            <logic:empty name="OrderItemDetailsId" property="itemMarker">
		                            
		                            </logic:empty>
		                            <logic:notEmpty name="OrderItemDetailsId" property="itemMarker">
		                            	<logic:iterate id="revMarker" name="OrderItemDetailsId" property="itemMarker">
		          	                 		<bean:write name="revMarker"/>
		                            	</logic:iterate>
		                            </logic:notEmpty>                          
		                            </TD>
		                            <!--Added for CR-74 VV49326 02-06-09 - Ends He class="borderbottomrightlight" e-->
		                            <TD WIDTH="60%" class="text-center">
		                            	<html:text name="OrderItemDetailsId" styleClass="form-control"
		                            		 property="itemDesc"/></TD>
									<TD WIDTH="20%" class="text-center">
										<html:text name="OrderItemDetailsId" styleClass="form-control" 
											property="itemValue"/></TD>
		                        </tr>
							</tbody>
					</logic:iterate>
				</logic:notEmpty>
			</TABLE>
		</logic:notEqual>
    </logic:iterate>

	<logic:iterate id="OrderSpecDetailsId" name="OrderSpecificationForm"
		property="specList" type="com.EMD.LSDB.vo.common.SpecificationVO"
		indexId="counter">

		<bean:define id="size" name="counter" />
		<logic:equal name="size" value="0">
			<logic:present name="OrderSpecDetailsId" property="specName">
				<div class="row text-center">
						<div class="form-group">
					        <div class="col-sm-12">
					              <button class="btn btn-primary" type='button' id="btnRearrange" ONCLICK="javascript:fnModifyOrderSpecItem()">Modify</button>
					              <button class="btn btn-primary" type='button' id="btnModify" ONCLICK="javascript:deleteSpecificationItem()">Delete</button>
					              <logic:present name="OrderSpecificationForm" property="orderList"
									scope="request">
									<logic:iterate id="DisList" name="OrderSpecificationForm"
										property="orderList" type="com.EMD.LSDB.vo.common.OrderVO"
										scope="request">
					              			<button class="btn btn-primary" type='button' id="btnSave"
					              				ONCLICK="javascript:previewSpecification('<%=DisList.getOrderKey()%>','<%=DisList.getModelName()%>')">Preview Specifications PDF</button>
					              	</logic:iterate>
					              </logic:present>
					       	</div>
				     	</div>
				    </div>
			</logic:present>
		</logic:equal>
	</logic:iterate>
										<!-- CR 87 -->
		<div class="row">
			<div class="spacer30"></div>
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
					<logic:iterate id="section" name="OrderSpecificationForm"
						property="sectionList" type="com.EMD.LSDB.vo.common.SectionVO"
						scope="request">
						<bean:define id="revCode" name="OrderSpecificationForm" property="hdnRevViewCode" />
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
							<logic:equal name="OrderSpecificationForm" property="perfCurveLinkFlag" value="Y">&nbsp;|&nbsp;
							<a href="javascript:fnLoadPerfCurve()" class="linkstyleItem">Performance Curve</a>&nbsp;
						</logic:equal>
				</div>
			</div>
			
			<div class="row">
				<div class="spacer50"></div>
			</div>
	
	

	<html:hidden property="hdndataLocType" />

	<html:hidden property="hdnitemDesc" />
	<html:hidden property="hdnitemValue" />
	<html:hidden property="hdnSpecName" />
	<html:hidden property="hdnSpecSeqNo" />

	<html:hidden property="hdnRevViewCode" />
	<!-- Added For CR_84 -->
	<html:hidden property="perfCurveLinkFlag" />
	<html:hidden property="genArrLinkFlag" />
		<!-- Added For CR_104 -->
	<html:hidden property="custMdlName" />

</html:form>
</DIV>
