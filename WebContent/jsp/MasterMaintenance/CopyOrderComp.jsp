<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT type="text/javascript" SRC="js/componentmap.js"></SCRIPT>
<div class="container" width="100%">
   <html:form  action="/CompMapAction" method="post">
   		<h4 class="text-center"><bean:message key="Application.Screen.CopyOrderComponent" /></h4>
   		<p class="text-center"><mark><bean:message key="Message.CopyOrderComponent" /></mark></p>
 		<div class="spacer10"></div>
		<div class="errorlayerhide" id="errorlayer">
		</div>
		<logic:present name="CompMapMaintForm" property="messageID" scope="request">
			<bean:define id="messageID" name="CompMapMaintForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>
		</logic:present>
		
		<logic:present name="CompMapMaintForm" property="CompGrpList">
			<bean:size id="compGrpListLen" name="CompMapMaintForm" property="CompGrpList" />
		</logic:present>
		
		<logic:present name="CompMapMaintForm" property="compList">
		<bean:size id="compListLen" name="CompMapMaintForm"
			property="compList" />			
		</logic:present>
		
		<!-- For displaying link to the newly added Clause -->
			<logic:notEqual name="CompMapMaintForm" property="newClaSeqNo" value="0">
			<center>
				 <a	href="javascript:fnLoadNewClauseDetails()"
					Class="linkstyleItem"><font color="red">Click Here to View Clause</font></a>
			</center>
			</logic:notEqual>
		<!-- For displaying link to the newly added Clause - Ends -->
		
		<logic:match name="CompMapMaintForm" property="method"
		value="UNMAPCOMPONENT">
		<logic:lessThan name="compListLen" value="1">
			<script>
			var id = '701';
			hideErrors();
			addMsgNum(id);
			showScrollErrors();
		</script>
		</logic:lessThan>
	</logic:match>
	<logic:match name="CompMapMaintForm" property="method"
		value="CopyOrderComponent">
		<logic:lessThan name="compListLen" value="1">
		<script> 
              fnNoRecords();
        </script>
		</logic:lessThan>
	</logic:match>
		
		<div class="spacer10"></div>
				<div class="row">
					<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Specification Type</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="CompMapMaintForm" property="specTypeNo" value="-1">
							<bean:write name="CompMapMaintForm" property="hdnSelSpecType"/>
						</logic:notEqual>
						<logic:equal name="CompMapMaintForm" property="specTypeNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				<div class="spacer10"></div>
				<div class="row">
					<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Model</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="CompMapMaintForm" property="modelSeqNo" value="-1">
							<bean:write name="CompMapMaintForm" property="hdnSelModel"/>
						</logic:notEqual>
						<logic:equal name="CompMapMaintForm" property="modelSeqNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				<div class="spacer10"></div>
				<div class="row">
					<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Section</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="CompMapMaintForm" property="sectionSeqNo" value="-1">
							<bean:write name="CompMapMaintForm" property="hdnSelSec"/>
						</logic:notEqual>
						<logic:equal name="CompMapMaintForm" property="sectionSeqNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				<div class="spacer10"></div>
				<div class="row">
					<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Sub Section</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left text-nowrap">
						<logic:notEqual name="CompMapMaintForm" property="subSectionSeqNo" value="-1">
							<bean:write name="CompMapMaintForm" property="hdnSelSubSec"/>
						</logic:notEqual>
						<logic:equal name="CompMapMaintForm" property="subSectionSeqNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				<div class="spacer10"></div>
				<div class="row">
					<div class="col-sm-2 col-sm-offset-3 text-right"><strong>Component Group</strong></div>
					<div class="col-sm-1 text-center">:</div>
					<div class="col-sm-2 text-left">
						<logic:notEqual name="CompMapMaintForm" property="compGrpSeqNo" value="-1">
							<bean:write name="CompMapMaintForm" property="hdnSelCompGrp"/>
						</logic:notEqual>
						<logic:equal name="CompMapMaintForm" property="compGrpSeqNo" value="-1">
							&nbsp;
						</logic:equal>
					</div>
				</div>
				
				<div class="spacer10"></div>	
				
				<logic:empty name="CompMapMaintForm" property="hdnSelSec">
				    <logic:lessEqual name="CompMapMaintForm" property="subSectionSeqNo" value="0">
				     <div class="form-group">
						  <div class="col-sm-12 text-center">
					    	 <button class="btn btn-primary mdfybtn" type='button' id="returnButton" ONCLICK="javascript:fnLoadComponentMap()">Return To Component Mapping</button>
						 </div>   
						</div>
				    </logic:lessEqual>
		    </logic:empty>
			
			<logic:equal name="compListLen" value="0">				    
			    <div class="form-group">
						  <div class="col-sm-12 text-center">
					    	 <button class="btn btn-primary mdfybtn" type='button' id="returnButton" ONCLICK="javascript:fnLoadComponentMap()">Return To Component Mapping</button>
						 </div>   
				</div>
		    </logic:equal>
		    	
		    	<logic:greaterThan name="compListLen" value="0">
					<table class="table table-bordered table-hover">
						    <thead>
						        <tr>
							        <TH width="5%">Select</TH>
									<TH width="15%">Lead Component</TH>
									<TH width="15%">Components Tied To Clause</TH>
									<TH width="30%">Clause Description</TH>
									<TH width="20%">Engineering Data</TH>
								</tr>	
							</thead>
						    <tbody>
						    	  <logic:iterate id="components" name="CompMapMaintForm" property="compList" type="com.EMD.LSDB.vo.common.ComponentVO" scope="request">
									  <TR>
  								  	  <bean:size id="clauseListLen" name="components" property="clauseVOList" />
									  <TD CLASS="v-midalign text-center" ><html:radio property="componentSeqNo"
												value='<%= String.valueOf(components.getComponentSeqNo())%>' />
									  </TD>
									  <TD CLASS="v-midalign text-center" rowspan="<bean:write name='clauseListLen'/>">
									  <bean:write name="components" property="componentName"/>
									  </TD>
									   <logic:empty name="components" property="clauseVOList">
										   <TD class="v-midalign"  rowspan="<bean:write name='clauseListLen'/>">&nbsp;</TD>
										   <TD class="v-midalign"  rowspan="<bean:write name='clauseListLen'/>">&nbsp;</TD>
										   <TD class="v-midalign"  rowspan="<bean:write name='clauseListLen'/>">&nbsp;</TD>
									  </logic:empty>

									  <logic:greaterThan name="clauseListLen" value="0">
									  		<logic:iterate id="clauseRev" name="components" property="clauseVOList" type="com.EMD.LSDB.vo.common.ClauseVO" indexId="claCount" >
								  				<TD CLASS="v-midalign text-center" >
													<logic:empty name="clauseRev" property="componentVO">
													&nbsp;
													</logic:empty>
													<logic:notEmpty name="clauseRev" property="componentVO" >
													        
													        <bean:size id="compLength" name="clauseRev" property="componentVO"/>
													        
													        <logic:equal name="compLength" value="1">
													           <logic:iterate id="comp" name="clauseRev" property="componentVO" >
															    	<em><bean:write name="comp" /></em>						
															   </logic:iterate>
													        </logic:equal>
													        
													        <logic:greaterThan name="compLength" value="1">
													           <logic:iterate id="comp" name="clauseRev" property="componentVO" indexId="count">
													                    
													                    <bean:define id="cnt" name="count" />
													                
													              	<I><bean:write name="comp" /></I>
													              	;<BR>				          	
													              		
															   </logic:iterate>
													        </logic:greaterThan>
													</logic:notEmpty>					
												</TD>
											  	<TD valign="top"  >
													<DIV STYLE="solid;height:80px;overflow:auto;">
														<logic:empty name="clauseRev" property="clauseDesc">
															&nbsp;
														</logic:empty>
													   <logic:notEmpty name="clauseRev" property="clauseDesc">
															
												               <bean:define	name="clauseRev" property="clauseDesc" id="clauseDesc" />
												               	<!-- CR-128 - Updated for Pdf issue -->
																	<%String strClauseDesc =  String.valueOf(clauseDesc);
																	if(strClauseDesc.startsWith("<p>")){%>
																		<%=(String.valueOf(clauseDesc))%>
																	<%}else{ %>	
																		<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>&nbsp;							
																	<%}%>
																<!-- CR-128 - Updated for Pdf issue Ends Here-->
												              </logic:notEmpty>
												               
												                 
									                        <table class="table table-bordered">
									
																<logic:notEmpty name="clauseRev" property="tableArrayData1">
																		&nbsp;
																		<logic:iterate id="outter" name="clauseRev"
																							property="tableArrayData1" indexId="counter">
														
																							<bean:define id="row" name="counter" />
																							<!-- Added  For CR_93 -->
																	<bean:define name="clauseRev" property="tableDataColSize" id="tableDataColSize" />
																							<tr class="text-center">
																								
																								<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
																										<logic:equal name="row" value="0">
																											<td width="5%" CLASS='v-midalign'><b><font
																												color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></b></font>
																											</td>
																										</logic:equal>
																										
																										
																										<logic:notEqual name="row" value="0">
																											<td width="5%" CLASS='v-midalign'><%=(tabrow==null)? "&nbsp;":tabrow%>
																											</td>
																										</logic:notEqual>
																								
																								</logic:iterate>
																							</tr>
																			</logic:iterate>
																</logic:notEmpty>
															</table>
															</DIV>
												</TD>
											<TD CLASS="v-midalign text-center" valign="top" >
													
																<logic:present name="clauseRev"
																property="edlNO">
																<logic:iterate id="engDataEdlNo" name="clauseRev"
																	property="edlNO">
																	<bean:message key="screen.edl" />
																	<bean:write name="engDataEdlNo" />
																	<br>
																</logic:iterate>
															</logic:present> 
															<!--  CR 87 -->
															<logic:present name="clauseRev" property="refEDLNO">
						
																<logic:iterate id="engDataRefEdlNo" name="clauseRev"
																	property="refEDLNO">
																	<bean:message key="screen.refEdl.start" />
																	<bean:write name="engDataRefEdlNo" />
																	<bean:message key="screen.refEdl.end" />
																	<br>
																</logic:iterate>
															</logic:present>
															<logic:present name="clauseRev"
																property="partOF">
																<logic:iterate id="engPartOfCd" name="clauseRev"
																	property="partOF">
																	<logic:notEqual name="engPartOfCd" value="0">
																		<bean:message key="screen.partOf" />
																		<bean:write name="engPartOfCd" />
																		<br>
																	</logic:notEqual>
																</logic:iterate>
															</logic:present> <logic:present name="clauseRev"
																property="dwONumber">
																<logic:notEqual name="clauseRev" property="dwONumber"
																	value="0">
																	<bean:message key="screen.dwoNo" />
																	<bean:write name="clauseRev" property="dwONumber" />
																	<br>
																</logic:notEqual>
															</logic:present> <logic:present name="clauseRev"
																property="partNumber">
																<logic:notEqual name="clauseRev" property="partNumber"
																	value="0">
																	<bean:message key="screen.partNo" />
																	<bean:write name="clauseRev" property="partNumber" />
																	<br>
																</logic:notEqual>
															</logic:present> <!--Price Book Number is added in the Engineering data Section, modified on 30-09-08 by ps5722-->
															<logic:present name="clauseRev" property="priceBookNumber">
																<logic:notEqual name="clauseRev" property="priceBookNumber"
																	value="0">
																	<bean:message key="screen.priceBookNo" />&nbsp; <bean:write
																		name="clauseRev" property="priceBookNumber" />
																	<br>
																</logic:notEqual>
															</logic:present> <logic:present name="clauseRev"
																property="standardEquipmentDesc">
																<bean:write name="clauseRev" property="standardEquipmentDesc" />
																<br>
															</logic:present> <logic:present name="clauseRev"
																property="engDataComment">
																<bean:define id="engDatCmnt" name="clauseRev"
																	property="engDataComment" />
																<%=engDatCmnt %>
																<br>
															</logic:present>

													</TD>
													
												<logic:greaterThan name="clauseListLen" value="1">
													<%if( Integer.parseInt(claCount.toString()) < Integer.parseInt(clauseListLen.toString())-1){%>																	
														</TR>																
														<TR>
													<%}%>
												</logic:greaterThan>
												
									  </logic:iterate>
									  
								      </logic:greaterThan>			  
									  </TR>
									  </logic:iterate>
				    </tbody>
					</table>
			  </logic:greaterThan>
			  
	<logic:notEmpty name="CompMapMaintForm" property="hdnSelSec">
	    <logic:greaterThan name="CompMapMaintForm" property="subSectionSeqNo" value="0">
				    <logic:greaterThan name="compListLen" value="0">
				    	<div class="form-group">
							  <div class="col-sm-12 text-center">
							     <button class="btn btn-primary mdfybtn" type='button' id="addCompButton" ONCLICK="javascript:fnAddComponentClauseToModel()"> Add Component/Clause To Model</button>
							     <button class="btn btn-primary deleteButton" type='button' id="returnButton" ONCLICK="javascript:fnLoadComponentMap()">Return To Component Mapping</button>
							</div>   
					   	</div>
		          </logic:greaterThan>
        </logic:greaterThan>
	</logic:notEmpty>
	<div class="spacer80"></div>			
	<html:hidden property="hdnSelModel" />
	<html:hidden property="hdnSelSec" />
	<html:hidden property="hdnSelSubSec" />
	<html:hidden property="hdnSelCompGrp" />
	<html:hidden property="validFlag" />
	<html:hidden property="hdnSelSpecType" />
	<!-- CR_83 code of  lines Started here -->
	<html:hidden property="specTypeNo"/>
	<html:hidden property="modelSeqNo"/>
	<html:hidden property="sectionSeqNo"/>
	<html:hidden property="subSectionSeqNo"/>
	<html:hidden property="compgrpCat"/>
	<html:hidden property="compGrpSeqNo"/>
	<!-- CR_83 code of  lines Started here -->
	<!-- Added For CR_93 -->
	<html:hidden property="compSourceFlag"/>
	<html:hidden property="newClaSeqNo"/>
</html:form>
</div>						    
						   