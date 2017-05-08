<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<SCRIPT type="text/javascript" SRC="js/componentmap.js"></SCRIPT>
<div class="container" width="100%">
   <html:form  action="/CompMapAction" method="post">
   		<h4 class="text-center"><bean:message key="Application.Screen.UnmapComponentGroup" /></h4>
   		<p class="text-center"><mark><bean:message key="Message.UnMapComponentGroup" /></mark></p>
   		<p class="text-center"><mark><bean:message key="Message.UnMapComponentGroup1" /></mark></p>
  		<p class="text-center"><mark><font color=red size=2>*&nbsp;&nbsp;</font><bean:message key="Message.LeadComponentGroup"/></mark></p>
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
		
		<logic:match name="CompMapMaintForm" property="method" value="UNMAPCOMPONENT">
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
				
				<div class="spacer20"></div>	
				
				<logic:empty name="CompMapMaintForm" property="hdnSelSec">
					    <logic:lessEqual name="CompMapMaintForm" property="subSectionSeqNo" value="0">
						    <div class="form-group">
							  <div class="col-sm-12 text-center">
						      		 <button class="btn btn-primary mdfybtn" type='button' id="returnButton" ONCLICK="javascript:fnLoadComponentMap()">Return To Component Mapping</button>
						   	  </div>   
						   	</div>
						</logic:lessEqual>
		   		</logic:empty>
			
				<logic:greaterThan name="compListLen" value="0">
					<table class="table table-bordered table-hover">
						    <thead>
						        <tr>
									<TH width="15%">Lead Component</TH>
									<TH width="10%">Default</TH>
									<TH width="20%">Components Tied To Clause</TH>
									<TH width="30%">Clause Description</TH>
								</tr>	
							</thead>
						    <tbody>
						    	<logic:iterate id="components" name="CompMapMaintForm" property="compList" type="com.EMD.LSDB.vo.common.ComponentVO" scope="request">
						    	
								  	  <bean:size id="clauseListLen" name="components" property="clauseVOList" />
									  	<TR>
										  <TD CLASS="v-midalign text-center" rowspan="<bean:write name='clauseListLen'/>">
											  <bean:write name="components" property="componentName"/>
										  </TD>
										  <TD CLASS="v-midalign text-center" rowspan="<bean:write name='clauseListLen'/>">
											  <logic:equal name="components" property="compDefFlag" value="Y">
											  X
											  </logic:equal>
											  <logic:notEqual name="components" property="compDefFlag" value="Y">
											  &nbsp;
											  </logic:notEqual>
										  </TD>		
										  <logic:empty name="components" property="clauseVOList">
										   <TD class="v-midalign"  rowspan="<bean:write name='clauseListLen'/>">&nbsp;</TD>
										   <TD class="v-midalign"  rowspan="<bean:write name='clauseListLen'/>">&nbsp;</TD>
										  </logic:empty>
											<logic:greaterThan name="clauseListLen" value="0">
									    		 	<logic:iterate id="clauseRev" name="components" property="clauseVOList" type="com.EMD.LSDB.vo.common.ClauseVO" indexId="claCount" >
													  		<TD CLASS="v-midalign text-center">
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
															<TD valign="top" width="30%" >
														 
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
														               
														                 
																		<logic:notEmpty name="clauseRev" property="tableArrayData1">
											                        <table class="table table-bordered">
																				<logic:iterate id="outter" name="clauseRev"
																									property="tableArrayData1" indexId="counter">
																
																									<bean:define id="row" name="counter" />
																									<tr class="text-center">
																										
																										<logic:iterate id="tabrow" name="outter">
																												<logic:equal name="row" value="0">
																													<td class="v-midalign" width="5%"><strong><font
																														color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></font></strong>
																													</td>
																												</logic:equal>
																												
																												
																												<logic:notEqual name="row" value="0">
																													<td class="v-midalign" width="5%"><%=(tabrow==null)? "&nbsp;":tabrow%>
																													</td>
																												</logic:notEqual>
																										
																										</logic:iterate>
																									</tr>
																					</logic:iterate>
																	</table>
																		</logic:notEmpty>
																	</DIV>
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
			</TABLE>
			</logic:greaterThan>	
			<div class="spacer10"></div>			  
			  <logic:notEmpty name="CompMapMaintForm" property="hdnSelSec">
				    <logic:greaterThan name="CompMapMaintForm" property="subSectionSeqNo" value="0">		
						<div class="form-group">
						  <div class="col-sm-12 text-center">
						       <button class="btn btn-primary mdfybtn" type='button' id="modifyButton" ONCLICK="javascript:fnUnmapComponentGrp()">Un-map Component Group</button>
						        <button class="btn btn-primary deleteButton" type='button' id="deleteButton" ONCLICK="javascript:fnLoadComponentMap()">Return To Component Mapping</button>
							</div>   
					   	</div>
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
	</html:form>
</div>   