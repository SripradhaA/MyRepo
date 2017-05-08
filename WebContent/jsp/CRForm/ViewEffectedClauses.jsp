<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<HTML>
<HEAD>
<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
</HEAD>
<LINK REL="stylesheet" HREF="css/Emd_Lsdb.css" TYPE="text/css">
<LINK REL="stylesheet" TYPE="text/css" HREF="css/EmdMenu.css">
<SCRIPT language="JavaScript" SRC="js/Emd_Lsdb.js"></SCRIPT>
<SCRIPT language="JavaScript" SRC="js/error.js"></SCRIPT>
<SCRIPT language="JavaScript" SRC="js/error_messages.js"></SCRIPT>
<BODY CLASS='main'>

	<TABLE BORDER=0 WIDTH="100%" CELLPADDING="0" CELLSPACING="0">
		<TR>
			<TD>&nbsp;</TD>
		</TR>
		<TR>

	<TABLE BORDER="0" WIDTH="60%" ALIGN="center">
		<TR>
			<TD WIDTH="60%" CLASS="dashBoardSubHeading" ALIGN="CENTER"><bean:message
				key="Application.Screen.ChangeRequestEffClauses" /></TD>
		</TR>
		<TR>
			<TD>&nbsp;</TD>
		</TR>
		
		<TR>
		<TD WIDTH="10%" ALIGN="center" CLASS=dashBoardSubHeading><bean:message key="Message.ViewEffClauses"/></TD>
		</TR>

		<TR>
		<TD WIDTH="10%" ALIGN="center" CLASS=navycolor nowrap ><font color=red size=2>*&nbsp;&nbsp;</font><bean:message key="Message.LeadComponentGroup"/></TD>
		</TR>
	</TABLE>

	<!-- To display  Messages - Start -->
	<BR>
	<%--Table Updated for CR-121 for server side error message tooltip --%>
	<table border="0" cellpadding="0" cellspacing="0" width="20%" align="center">
		   <tr>
	          <td nowrap="nowrap">
				<div class="errorlayerhide" id="errorlayer"
					style="position:relative; overflow: auto; height:0; visibility:hidden; z-index: 2">
				</div>
			  </td>
		   </tr>
	</table>
	<!-- To display Messages - End -->

	<logic:present name="ComponentGroupRequestForm" property="CompGrpList">
		<bean:size id="compGrpListLen" name="ComponentGroupRequestForm"
			property="CompGrpList" />
			
			
	</logic:present>
	
	<logic:present name="ComponentGroupRequestForm" property="compList">
		<bean:size id="compListLen" name="ComponentGroupRequestForm"
			property="compList" />			
	</logic:present>
	

	
	<!-- For Displaying Validation Messages - Start -->
	<logic:present name="ComponentGroupRequestForm" property="messageID"
		scope="request">
	 <%--Added for CR-121 for server side error message tooltip --%>	
	  <bean:define id="messageID" name="ComponentGroupRequestForm" property="messageID"/>
	  <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
      <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
	</logic:present>
	<!-- For Displaying Validation Messages - End -->

	<!-- Validation Message For No Records Found - Start -->
	<logic:lessThan name="compListLen" value="1">
		<script>
		var id = '877';
		hideErrors();
		addMsgNum(id);
		showErrors();
	</script>
	</logic:lessThan>
	<!-- Validation Message For No Records Found - End -->
	       
			<TABLE BORDER="0" WIDTH="90%"  ALIGN="center">
				<TR HEIGHT="14%">
			        <TD width=35%>&nbsp;</TD>
			        <TD WIDTH="15%">&nbsp;</TD>
			        <TD WIDTH="50%">&nbsp;</TD>
		        </TR>
				
				 
				<TR HEIGHT="25">
				     <TD >&nbsp;</TD>
				     <TD  ALIGN="left" CLASS="headerbgcolor" nowrap>Specification Type</TD>
				     <TD  nowrap CLASS="navycolor">Locomotive</TD>
				</TR>

				<TR HEIGHT="25">
				    <TD >&nbsp;</TD>
					<TD  ALIGN="left" CLASS="headerbgcolor">Model</TD>
					<TD nowrap CLASS="navycolor">
					<bean:write name="ComponentGroupRequestForm" property="hdnSelModel"/>
					</TD>
				</TR>
				<TR HEIGHT="25">
					<TD >&nbsp;</TD>
					<TD  ALIGN="left" CLASS="headerbgcolor">Section</TD>					
					<TD nowrap CLASS="navycolor">
					<logic:notEmpty name="ComponentGroupRequestForm" property="hdnSelSec">
					<bean:write name="ComponentGroupRequestForm" property="hdnSelSec"/>
					</logic:notEmpty>
					
					<logic:empty name="ComponentGroupRequestForm" property="hdnSelSec">
					&nbsp;
					</logic:empty>
					
					</TD>
				</TR>
				<TR HEIGHT="25">
				    <TD >&nbsp;</TD>
					<TD  ALIGN="left" CLASS="headerbgcolor">SubSection</TD>
					<TD  nowrap CLASS="navycolor">
					
					<logic:notEmpty name="ComponentGroupRequestForm" property="hdnSelSubSec">
					<bean:write name="ComponentGroupRequestForm" property="hdnSelSubSec"/>
					</logic:notEmpty>
					
					<logic:empty name="ComponentGroupRequestForm" property="hdnSelSubSec">
					&nbsp;
					</logic:empty>
					</TD>

				</TR>
				<TR HEIGHT="25">
				    <TD >&nbsp;</TD>
					<TD  ALIGN=left CLASS="headerbgcolor" nowrap>Component
					Group</TD>
					<TD  nowrap CLASS="navycolor">
					<bean:write name="ComponentGroupRequestForm" property="hdnSelCompGrp"/>
					<logic:equal name="ComponentGroupRequestForm" property="validFlag" value="Y">
					<font size=2 color=red>*</font>
					</logic:equal>
					</TD>
				</TR>
				
				
				<TR > 
				<TD >&nbsp;</TD>
				<TD >&nbsp;</TD>
				<TD >&nbsp;</TD>
				
			   </TR>
				 
			</TABLE>
			
			<logic:empty name="ComponentGroupRequestForm" property="hdnSelSec">
				    <logic:lessEqual name="ComponentGroupRequestForm" property="subSectionSeqNo" value="0">
				    
				    <TABLE WIDTH="40%" ALIGN="center" BORDER="0" CELLPADDING="1" CELLSPACING="1">
								
								<TR>								    
																		
									<TD  ALIGN="center" width=45%>
									<input type=button name="btnClose" value="Close" Class="buttonStyle" 
														onclick="javascript:window.close();"/>
									</TD>
								</TR>
				         </TABLE>
				    
				    
				    </logic:lessEqual>
		    </logic:empty>
			
			
			
			<logic:greaterThan name="compListLen" value="0">
			
			<TABLE WIDTH="80%" ALIGN="center" BORDER="1" BORDERCOLOR="#5780ae">
               
			   <TR> 
				<TD CLASS='table_heading' WIDTH="30%">Lead Component</TD>
				<TD CLASS='table_heading' WIDTH="8%">Default</TD>
				<TD CLASS='table_heading' WIDTH="40%">Clause Description</TD>
				
			   </TR>
			   
			  
			   
			  <logic:iterate id="components" name="ComponentGroupRequestForm" property="compList" type="com.EMD.LSDB.vo.common.ComponentVO" scope="request">
			  <TR>
			  <TD CLASS=borderbottomleft1>
			  <bean:write name="components" property="componentName"/>
			  </TD>
			  
			  
			  <TD CLASS=borderbottomleft1>
			  <logic:equal name="components" property="compDefFlag" value="Y">
			  X
			  </logic:equal>
			  
			  
			  <logic:notEqual name="components" property="compDefFlag" value="Y">
			  &nbsp;
			  </logic:notEqual>
			  
			  </TD>
			  
			  	
		     <bean:size id="clauseListLen" name="components" property="clauseVOList" />	
     
			  <TD class="borderleft1">

			  <logic:greaterThan name="clauseListLen" value="0">
			 
			  <table width="100%" BORDER="0" BORDERCOLOR="">
			  <logic:iterate id="clauseRev" name="components" property="clauseVOList" type="com.EMD.LSDB.vo.common.ClauseVO" >
			  
			  <TR>  
				<TD class="borderbottomrightonly1">
				  <span style="width:500px">
							<DIV STYLE="float:left;height:60;width:500;overflow: auto ; ">
							
							<logic:empty name="clauseRev" property="clauseDesc">
							&nbsp;
							</logic:empty>
							
							   <logic:notEmpty name="clauseRev" property="clauseDesc">
							
				               <bean:define	name="clauseRev" property="clauseDesc" id="clauseDesc" />
				               <!-- CR-128 - Updated for Pdf issue -->
									<%String strClauseDesc =  String.valueOf(clauseDesc);
									if(strClauseDesc.startsWith("<p>")){%>
										<%=(String.valueOf(clauseDesc))%>&nbsp;
									<%}else{ %>	
				                    <%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>&nbsp;
									<%}%>
								<!-- CR-128 - Updated for Pdf issue Ends Here-->
				                
				               </logic:notEmpty>
				               
				                 
	                        <table width="100%" BORDER="0" BORDERCOLOR="">
	
								<logic:notEmpty name="clauseRev" property="tableArrayData1">
										&nbsp;
										<logic:iterate id="outter" name="clauseRev"
												property="tableArrayData1" indexId="counter">
			
												<bean:define id="row" name="counter" />
												<tr>
													
													<logic:iterate id="tabrow" name="outter">
															<logic:equal name="row" value="0">
																<td valign="top" width="5%" CLASS='borderbottomleft1'><b><font
																	color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></b></font>
																</td>
															</logic:equal>
															
															
															<logic:notEqual name="row" value="0">
																<td valign="top" width="5%" CLASS='borderbottomleft1'><%=(tabrow==null)? "&nbsp;":tabrow%>
																</td>
															</logic:notEqual>
													
													</logic:iterate>
												</tr>
											</logic:iterate>
								</logic:notEmpty>
							</table>
							</DIV>
							</span>
				
					</TD>
				</TR>
			  
			  </logic:iterate>
			  </table>
		      
		      
		      </logic:greaterThan>
			  
			  
			  <logic:empty name="components" property="clauseVOList">
			   <table width="100%" BORDER="0" BORDERCOLOR="">
			   <TR>
			   <TD class=borderbottomrightonly1>&nbsp;</TD>
			   </TR>			  
			  </table>
			  </logic:empty>
			  
			  </TD>
			  
			  
			  </TR>
			  </logic:iterate>
			</logic:greaterThan>
			</TABLE>
           
	
           <TABLE WIDTH="40%" ALIGN="center" BORDER="0" CELLPADDING="1" CELLSPACING="1">
				<TR>
					<TD>&nbsp;</TD>
				</TR>
				<TR>

					<TD  ALIGN="center" width=45%>
					<input type=button name="btnClose" value="Close" Class="buttonStyle" 
					onclick="javascript:window.close();"/>
					</TD>
				</TR>
           </TABLE>

           <TABLE WIDTH="40%" ALIGN="center" BORDER="0">
				<TR>
					<TD>&nbsp;</TD>
				</TR>
			</TABLE>
</TABLE>

	       <input type="hidden" name="hdnCompSeqNo"/>

