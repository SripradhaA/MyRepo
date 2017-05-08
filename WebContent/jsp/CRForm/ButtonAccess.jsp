<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

	<!--  Added To Reduce Code Structure For CR_80 by RR68151 - Ends here -->

	<TABLE align=center border=0 width="85%">
		<TR>
			<TD>&nbsp;</TD>
			<TD>&nbsp;</TD>
			<TD>&nbsp;</TD>
			<TD>&nbsp;</TD>
		</TR>
		<TR>

			<TD CLASS="headerbgcolor" ALIGN="left">Reason for change<font
				color=red size=2>*</font></TD>
			<TD CLASS="formFieldSub"><logic:present
				name="CreateChangeClauseRequestForm" property="requestList">
				<logic:iterate id="reqDetails" name="CreateChangeClauseRequestForm"
					property="requestList" type="com.EMD.LSDB.vo.common.RequestVO">

					<logic:present name="CreateChangeClauseRequestForm"
						property="reason">
						<html:textarea name="CreateChangeClauseRequestForm"
							property="reason" rows="3" cols="50"></html:textarea>
					</logic:present>
					<logic:notPresent name="CreateChangeClauseRequestForm"
						property="reason">
						<html:textarea property="reason" rows="3" cols="50"></html:textarea>
					</logic:notPresent></TD>
			<TD CLASS="headerbgcolor" ALIGN="left">Administrator comments</TD>
			<TD CLASS="formFieldSub"><logic:equal
				name="CreateChangeClauseRequestForm" property="roleID" value="ADMN">
				<logic:equal name="reqDetails" property="statusTypeSeqNo" value="2">
					<logic:present name="CreateChangeClauseRequestForm"
						property="adminComments">
						<html:textarea name="CreateChangeClauseRequestForm"
							property="adminComments" rows="3" cols="50"></html:textarea>
					</logic:present>
					<logic:notPresent name="CreateChangeClauseRequestForm"
						property="adminComments">
						<html:textarea property="adminComments" rows="3" cols="50"></html:textarea>
					</logic:notPresent>
				</logic:equal>

				<logic:equal name="reqDetails" property="statusTypeSeqNo" value="4">
					<logic:present name="CreateChangeClauseRequestForm"
						property="adminComments">
						<html:textarea name="CreateChangeClauseRequestForm"
							property="adminComments" rows="3" cols="50"></html:textarea>
					</logic:present>
					<logic:notPresent name="CreateChangeClauseRequestForm"
						property="adminComments">
						<html:textarea property="adminComments" rows="3" cols="50"></html:textarea>
					</logic:notPresent>
				</logic:equal>

				<logic:equal name="reqDetails" property="statusTypeSeqNo" value="5">
					<logic:present name="CreateChangeClauseRequestForm"
						property="adminComments">
						<html:textarea name="CreateChangeClauseRequestForm"
							property="adminComments" rows="3" cols="50"></html:textarea>
					</logic:present>
					<logic:notPresent name="CreateChangeClauseRequestForm"
						property="adminComments">
						<html:textarea property="adminComments" rows="3" cols="50"></html:textarea>
					</logic:notPresent>
				</logic:equal>

				<logic:equal name="reqDetails" property="statusTypeSeqNo" value="1">
					<logic:present name="CreateChangeClauseRequestForm"
						property="adminComments">
						<html:textarea name="CreateChangeClauseRequestForm"
							property="adminComments" rows="3" cols="50" disabled="true"></html:textarea>
					</logic:present>
					<logic:notPresent name="CreateChangeClauseRequestForm"
						property="adminComments">
						<html:textarea property="adminComments" rows="3" cols="50"
							disabled="true"></html:textarea>
					</logic:notPresent>
				</logic:equal>

				<logic:equal name="reqDetails" property="statusTypeSeqNo" value="3">
					<logic:present name="CreateChangeClauseRequestForm"
						property="adminComments">
						<html:textarea name="CreateChangeClauseRequestForm"
							property="adminComments" rows="3" cols="50" disabled="true"></html:textarea>
					</logic:present>
					<logic:notPresent name="CreateChangeClauseRequestForm"
						property="adminComments">
						<html:textarea property="adminComments" rows="3" cols="50"
							disabled="true"></html:textarea>
					</logic:notPresent>
				</logic:equal>

				<logic:equal name="reqDetails" property="statusTypeSeqNo" value="6">
					<logic:present name="CreateChangeClauseRequestForm"
						property="adminComments">
						<html:textarea name="CreateChangeClauseRequestForm"
							property="adminComments" rows="3" cols="50" disabled="true"></html:textarea>
					</logic:present>
					<logic:notPresent name="CreateChangeClauseRequestForm"
						property="adminComments">
						<html:textarea property="adminComments" rows="3" cols="50"
							disabled="true"></html:textarea>
					</logic:notPresent>
				</logic:equal>
				<!-- Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151 -->
				<logic:equal name="reqDetails" property="statusTypeSeqNo" value="7">
					<logic:present name="CreateChangeClauseRequestForm"
						property="adminComments">
						<html:textarea name="CreateChangeClauseRequestForm"
							property="adminComments" rows="3" cols="50"></html:textarea>
					</logic:present>
					<logic:notPresent name="CreateChangeClauseRequestForm"
						property="adminComments">
						<html:textarea property="adminComments" rows="3" cols="50"
							disabled="true"></html:textarea>
					</logic:notPresent>
				</logic:equal>
				<!-- Added for CR_80 CR Form Enhancements III on 19-Nov-09 by RR68151 - Ends here -->
				
			</logic:equal> <logic:notEqual name="CreateChangeClauseRequestForm"
				property="roleID" value="ADMN">
				<logic:present name="CreateChangeClauseRequestForm"
					property="adminComments">
					<html:textarea name="CreateChangeClauseRequestForm"
						property="adminComments" rows="3" cols="50" disabled="true"></html:textarea>
				</logic:present>
				<logic:notPresent name="CreateChangeClauseRequestForm"
					property="adminComments">
					<html:textarea property="adminComments" rows="3" cols="50"
						disabled="true"></html:textarea>
				</logic:notPresent>
			</logic:notEqual> </logic:iterate> </logic:present></TD>
		</TR>
	</TABLE>
	
	<!--  Added To Reduce Code Structure For CR_80 by RR68151 - Ends here -->

<TABLE BORDER="0" WIDTH="100%" ALIGN="CENTER">
	<TR>
		<TD>&nbsp;</TD>
	</TR>
	<TR>
		<TD width="30%">&nbsp;</TD>
		<logic:present name="CreateChangeClauseRequestForm"
			property="requestList">
			<logic:iterate id="objRequest" name="CreateChangeClauseRequestForm"
				property="requestList" type="com.EMD.LSDB.vo.common.RequestVO">
				<input type="hidden" name="hdnStatusTypeSeqNo"
					value=<%=objRequest.getStatusTypeSeqNo()%>>

				<logic:equal name="objRequest" property="statusTypeSeqNo" value="1">
					<logic:notEqual name="CreateChangeClauseRequestForm"
						property="reqModelSaved" value="Y">
						<logic:equal name="CreateChangeClauseRequestForm"
							property="roleID" value="ADMN">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=String.valueOf(objRequest.getReqID())%>')"
								disabled></TD>
							<%-- Modified Add function to perform Spell Check for CR_104 --%>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify" id="addClause"
								onclick="javascript:fnCheckSpellnCont('fnSaveClause')"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Submit"
								onclick="javascript:saveRequestStatus(2)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyleYellow"
								TYPE="button" name="btnAdd" value="Onhold"
								onclick="javascript:saveRequestStatus(3)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT
								style='background-color:green' CLASS="buttonStyleCommon"
								TYPE="button" name="btnAdd" value="Approve"
								onclick="javascript:saveRequestStatus(4)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT style='background-color:red'
								CLASS="buttonStyleCommon" TYPE="button" name="btnAdd"
								value="Reject" onclick="javascript:saveRequestStatus(5)"
								disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Complete"
								onclick="javascript:saveRequestStatus(6)" disabled></TD>
							<TD WIDTH="30%">&nbsp;</TD>
							<!-- Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 -->
							<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()"></TD>
						</logic:equal>

						<logic:notEqual name="CreateChangeClauseRequestForm"
							property="roleID" value="ADMN">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=String.valueOf(objRequest.getReqID())%>')"
								disabled></TD>

							<logic:equal name="CreateChangeClauseRequestForm"
								property="userOwnRequest" value="Y">
								<%-- Modified Add function to perform Spell Check for CR_104 --%>
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Save/Modify" id="addClause"
									onclick="javascript:fnCheckSpellnCont('fnSaveClause')"></TD>
							</logic:equal>
							<logic:notEqual name="CreateChangeClauseRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Save/Modify" id="addClause"
									onclick="javascript:fnCheckSpellnCont('fnSaveClause')" disabled></TD>
							</logic:notEqual>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Submit"
								onclick="javascript:saveRequestStatus(2)" disabled></TD>
							<!--  <TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyleYellow" TYPE="hidden" name="btnAdd" value="Onhold" onclick="javascript:saveRequestStatus(3)" disabled ></TD>
                        <TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:green' CLASS="buttonStyleCommon" TYPE="hidden" name="btnAdd" value="Approve" onclick="javascript:saveRequestStatus(4)" disabled></TD>
                        <TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:red' CLASS="buttonStyleCommon" TYPE="hidden" name="btnAdd" value="Reject" onclick="javascript:saveRequestStatus(5)" disabled></TD>
                        <TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyle" TYPE="hidden" name="btnAdd" value="Complete" onclick="javascript:saveRequestStatus(6)" disabled></TD>-->
							<TD WIDTH="30%">&nbsp;</TD>
							<!-- Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 -->
							<logic:equal name="CreateChangeClauseRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()"></TD>
							</logic:equal>
							<logic:notEqual name="CreateChangeClauseRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()" disabled></TD>
							</logic:notEqual>
						</logic:notEqual>
					</logic:notEqual>


					<!-- Saved -->

					<logic:equal name="CreateChangeClauseRequestForm"
						property="reqModelSaved" value="Y">
						<logic:equal name="CreateChangeClauseRequestForm"
							property="roleID" value="ADMN">

							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=String.valueOf(objRequest.getReqID())%>')">
							</TD>
							<%-- Modified Add function to perform Spell Check for CR_104 --%>

							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify" id="addClause"
								onclick="javascript:fnCheckSpellnCont('fnSaveClause')"></TD>

							<logic:equal name="CreateChangeClauseRequestForm"
								property="userOwnRequest" value="Y">

								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Submit"
									onclick="javascript:saveRequestStatus(2)"></TD>
							</logic:equal>

							<logic:notEqual name="CreateChangeClauseRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Submit"
									onclick="javascript:saveRequestStatus(2)" disabled></TD>
							</logic:notEqual>

							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyleYellow"
								TYPE="button" name="btnAdd" value="Onhold"
								onclick="javascript:saveRequestStatus(3)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT
								style='background-color:green' CLASS="buttonStyleCommon"
								TYPE="button" name="btnAdd" value="Approve"
								onclick="javascript:saveRequestStatus(4)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT style='background-color:red'
								CLASS="buttonStyleCommon" TYPE="button" name="btnAdd"
								value="Reject" onclick="javascript:saveRequestStatus(5)"
								disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Complete"
								onclick="javascript:saveRequestStatus(6)" disabled></TD>
							<TD WIDTH="30%">&nbsp;</TD>

							<logic:equal name="CreateChangeClauseRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
									CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
									onclick="javascript:resetRequest()"></TD>
							</logic:equal>

							<logic:notEqual name="CreateChangeClauseRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
									CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
									onclick="javascript:resetRequest()" disabled></TD>
							</logic:notEqual>

						</logic:equal>

						<logic:notEqual name="CreateChangeClauseRequestForm"
							property="roleID" value="ADMN">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=String.valueOf(objRequest.getReqID())%>')"></TD>

							<%-- Modified Add function to perform Spell Check for CR_104 --%>
							<logic:equal name="CreateChangeClauseRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Save/Modify" id="addClause"
									onclick="javascript:fnCheckSpellnCont('fnSaveClause')"></TD>
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Submit"
									onclick="javascript:saveRequestStatus(2)"></TD>
							</logic:equal>

							<logic:notEqual name="CreateChangeClauseRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Save/Modify" id="addClause"
									onclick="javascript:fnCheckSpellnCont('fnSaveClause')" disabled></TD>
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Submit"
									onclick="javascript:saveRequestStatus(2)" disabled></TD>
							</logic:notEqual>


							<!--  <TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyleYellow" TYPE="hidden" name="btnAdd" value="Onhold" onclick="javascript:saveRequestStatus(3)" disabled ></TD>
                        <TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:green' CLASS="buttonStyleCommon" TYPE="hidden" name="btnAdd" value="Approve" onclick="javascript:saveRequestStatus(4)" disabled></TD>
                        <TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:red' CLASS="buttonStyleCommon" TYPE="hidden" name="btnAdd" value="Reject" onclick="javascript:saveRequestStatus(5)" disabled></TD>
                        <TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyle" TYPE="hidden" name="btnAdd" value="Complete" onclick="javascript:saveRequestStatus(6)" disabled></TD>-->
							<TD WIDTH="30%">&nbsp;</TD>

							<logic:equal name="CreateChangeClauseRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
									CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
									onclick="javascript:resetRequest()"></TD>
							</logic:equal>
							<logic:notEqual name="CreateChangeClauseRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
									CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
									onclick="javascript:resetRequest()" disabled></TD>
							</logic:notEqual>
						</logic:notEqual>
					</logic:equal>
					<!-- ENds -->

				</logic:equal>
				<!-- Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 -->

					<logic:notEqual name="CreateChangeClauseRequestForm"
						property="roleID" value="ADMN">
						<logic:equal name="objRequest" property="statusTypeSeqNo"
							value="2">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=String.valueOf(objRequest.getReqID())%>')"></TD>
							<%-- Modified Add function to perform Spell Check for CR_104 --%>
								<%--CR_106 CR Form Changes CR Form Owner Save/Modify for Submitted state--%>
							<logic:equal name="CreateChangeClauseRequestForm" property="userownRequest" value="Y" >
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify" id="addClause"
								onclick="javascript:fnCheckSpellnCont('fnSaveClause')" ></TD>
								</logic:equal>
								<logic:notEqual name="CreateChangeClauseRequestForm" property="userownRequest" value="Y" >
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify" id="addClause"
								onclick="javascript:fnCheckSpellnCont('fnSaveClause')" disabled></TD>
								</logic:notEqual>
								
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Submit"
								onclick="javascript:saveRequestStatus(2)" disabled></TD>

							<!--   <TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyleYellow" TYPE="hidden" name="btnAdd" value="Onhold" onclick="javascript:saveRequestStatus(3)" disabled ></TD>
                        <TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:green' CLASS="buttonStyleCommon" TYPE="hidden" name="btnAdd" value="Approve" onclick="javascript:saveRequestStatus(4)" disabled></TD>
                        <TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:red' CLASS="buttonStyleCommon" TYPE="hidden" name="btnAdd" value="Reject" onclick="javascript:saveRequestStatus(5)" disabled></TD>
                        <TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyle" TYPE="hidden" name="btnAdd" value="Complete" onclick="javascript:saveRequestStatus(6)" disabled></TD>-->
							<TD WIDTH="30%">&nbsp;</TD>
							<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()" disabled></TD>
						</logic:equal>

						<logic:equal name="objRequest" property="statusTypeSeqNo"
							value="3">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=String.valueOf(objRequest.getReqID())%>')"></TD>

							<%-- Modified Add function to perform Spell Check for CR_104 --%>
							<logic:equal name="CreateChangeClauseRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Save/Modify" id="addClause"
									onclick="javascript:fnCheckSpellnCont('fnSaveClause')"></TD>
							</logic:equal>
							<logic:notEqual name="CreateChangeClauseRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Save/Modify" id="addClause"
									onclick="javascript:fnCheckSpellnCont('fnSaveClause')" disabled></TD>
							</logic:notEqual>

							<logic:equal name="CreateChangeClauseRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Submit"
									onclick="javascript:saveRequestStatus(2)"></TD>
							</logic:equal>

							<logic:notEqual name="CreateChangeClauseRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Submit"
									onclick="javascript:saveRequestStatus(2)" disabled></TD>
							</logic:notEqual>


							<!--   <TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyleYellow" TYPE="hidden" name="btnAdd" value="Onhold" onclick="javascript:saveRequestStatus(3)" disabled ></TD>
                        <TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:green' CLASS="buttonStyleCommon" TYPE="hidden" name="btnAdd" value="Approve" onclick="javascript:saveRequestStatus(4)" disabled></TD>
                        <TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:red' CLASS="buttonStyleCommon" TYPE="hidden" name="btnAdd" value="Reject" onclick="javascript:saveRequestStatus(5)" disabled></TD>
                        <TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyle" TYPE="hidden" name="btnAdd" value="Complete" onclick="javascript:saveRequestStatus(6)" disabled></TD>-->
							<TD WIDTH="30%">&nbsp;</TD>
							<!-- Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 -->
							<logic:equal name="CreateChangeClauseRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()"></TD>
							</logic:equal>
							<logic:notEqual name="CreateChangeClauseRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
									CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
									onclick="javascript:resetRequest()" disabled></TD>
							</logic:notEqual>
						</logic:equal>
						<logic:equal name="objRequest" property="statusTypeSeqNo"
							value="4">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=String.valueOf(objRequest.getReqID())%>')"></TD>
							<%-- Modified Add function to perform Spell Check for CR_104 --%>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify" id="addClause"
								onclick="javascript:fnCheckSpellnCont('fnSaveClause')" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Submit"
								onclick="javascript:saveRequestStatus(2)" disabled></TD>

							<!--  <TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyleYellow" TYPE="hidden" name="btnAdd" value="Onhold" onclick="javascript:saveRequestStatus(3)" disabled ></TD>
                        <TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:green' CLASS="buttonStyleCommon" TYPE="hidden" name="btnAdd" value="Approve" onclick="javascript:saveRequestStatus(4)" disabled></TD>
                        <TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:red' CLASS="buttonStyleCommon" TYPE="hidden" name="btnAdd" value="Reject" onclick="javascript:saveRequestStatus(5)" disabled></TD>
                        <TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyle" TYPE="hidden" name="btnAdd" value="Complete" onclick="javascript:saveRequestStatus(6)" disabled></TD>-->
							<TD WIDTH="30%">&nbsp;</TD>
							<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()" disabled></TD>
						</logic:equal>

						<logic:equal name="objRequest" property="statusTypeSeqNo"
							value="5">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=String.valueOf(objRequest.getReqID())%>')"></TD>
							<%-- Modified Add function to perform Spell Check for CR_104 --%>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify" id="addClause"
								onclick="javascript:fnCheckSpellnCont('fnSaveClause')" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Submit"
								onclick="javascript:saveRequestStatus(2)" disabled></TD>

							<!--   <TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyleYellow" TYPE="hidden" name="btnAdd" value="Onhold" onclick="javascript:saveRequestStatus(3)" disabled ></TD>
                        <TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:green' CLASS="buttonStyleCommon" TYPE="hidden" name="btnAdd" value="Approve" onclick="javascript:saveRequestStatus(4)" disabled></TD>
                        <TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:red' CLASS="buttonStyleCommon" TYPE="hidden" name="btnAdd" value="Reject" onclick="javascript:saveRequestStatus(5)" disabled></TD>
                        <TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyle" TYPE="hidden" name="btnAdd" value="Complete" onclick="javascript:saveRequestStatus(6)" disabled></TD>-->
							<TD WIDTH="30%">&nbsp;</TD>
							<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()" disabled></TD>
						</logic:equal>

						<logic:equal name="objRequest" property="statusTypeSeqNo"
							value="6">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=String.valueOf(objRequest.getReqID())%>')"></TD>
							<%-- Modified Add function to perform Spell Check for CR_104 --%>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify" id="addClause"
								onclick="javascript:fnCheckSpellnCont('fnSaveClause')" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Submit"
								onclick="javascript:saveRequestStatus(2)" disabled></TD>

							<!--   <TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyleYellow" TYPE="hidden" name="btnAdd" value="Onhold" onclick="javascript:saveRequestStatus(3)" disabled ></TD>
                        <TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:green' CLASS="buttonStyleCommon" TYPE="hidden" name="btnAdd" value="Approve" onclick="javascript:saveRequestStatus(4)" disabled></TD>
                        <TD WIDTH="5%"  ALIGN="center"><INPUT style='background-color:red' CLASS="buttonStyleCommon" TYPE="hidden" name="btnAdd" value="Reject" onclick="javascript:saveRequestStatus(5)" disabled></TD>
                        <TD WIDTH="5%"  ALIGN="center"><INPUT CLASS="buttonStyle" TYPE="hidden" name="btnAdd" value="Complete" onclick="javascript:saveRequestStatus(6)" disabled></TD>-->
							<TD WIDTH="30%">&nbsp;</TD>
							<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()" disabled></TD>
						</logic:equal>
						<!-- Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 -->
						<logic:equal name="objRequest" property="statusTypeSeqNo"
							value="7">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=String.valueOf(objRequest.getReqID())%>')"></TD>
							<%-- Modified Add function to perform Spell Check for CR_104 --%>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify" id="addClause"
								onclick="javascript:fnCheckSpellnCont('fnSaveClause')" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Submit"
								onclick="javascript:saveRequestStatus(2)" disabled></TD>
							<TD WIDTH="30%">&nbsp;</TD>
							
							<logic:equal name="CreateChangeClauseRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
									CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
									onclick="javascript:resetRequest()"></TD>
							</logic:equal>
							
							<logic:notEqual name="CreateChangeClauseRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
									CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
									onclick="javascript:resetRequest()" disabled></TD>
							</logic:notEqual>
						</logic:equal>
						<!-- Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 - Ends here -->
					</logic:notEqual>
				<!-- Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 -->
				<%-- Modified for CR_106 CR Form Changes  --%>
					<logic:equal name="CreateChangeClauseRequestForm" property="roleID"
						value="ADMN">
						<logic:equal name="objRequest" property="statusTypeSeqNo"
							value="2">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=String.valueOf(objRequest.getReqID())%>')"></TD>
							<%-- Modified Add function to perform Spell Check for CR_104 --%>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify" id="addClause"
								onclick="javascript:fnCheckSpellnCont('fnSaveClause')"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Submit"
								onclick="javascript:saveRequestStatus(2)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyleYellow"
								TYPE="button" name="btnAdd" value="Onhold"
								onclick="javascript:saveRequestStatus(3)"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT
								style='background-color:green' CLASS="buttonStyleCommon"
								TYPE="button" name="btnAdd" value="Approve"
								onclick="javascript:saveRequestStatus(4)"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT style='background-color:red'
								CLASS="buttonStyleCommon" TYPE="button" name="btnAdd"
								value="Reject" onclick="javascript:saveRequestStatus(5)"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Complete"
								onclick="javascript:saveRequestStatus(0)"></TD>
							<TD WIDTH="30%">&nbsp;</TD>
							<!-- Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 -->
							<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()"></TD>
						</logic:equal>
						<logic:equal name="objRequest" property="statusTypeSeqNo"
							value="3">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=String.valueOf(objRequest.getReqID())%>')"></TD>
							<%-- Modified Add function to perform Spell Check for CR_104 --%>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify" id="addClause"
								onclick="javascript:fnCheckSpellnCont('fnSaveClause')"></TD>

							<logic:equal name="CreateChangeClauseRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Submit"
									onclick="javascript:saveRequestStatus(2)"></TD>
							</logic:equal>

							<logic:notEqual name="CreateChangeClauseRequestForm"
								property="userOwnRequest" value="Y">
								<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
									TYPE="button" name="btnAdd" value="Submit"
									onclick="javascript:saveRequestStatus(2)" disabled></TD>
							</logic:notEqual>

							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyleYellow"
								TYPE="button" name="btnAdd" value="Onhold"
								onclick="javascript:saveRequestStatus(3)" disabled></TD>
								<%-- Modified for CR 106 Changes Admin may Approve from OnHold by JG101007--%>
							<TD WIDTH="5%" ALIGN="center"><INPUT
								style='background-color:green' CLASS="buttonStyleCommon"
								TYPE="button" name="btnAdd" value="Approve"
								onclick="javascript:saveRequestStatus(4)" ></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT style='background-color:red'
								CLASS="buttonStyleCommon" TYPE="button" name="btnAdd"
								value="Reject" onclick="javascript:saveRequestStatus(5)"
								disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Complete"
								onclick="javascript:saveRequestStatus(6)" disabled></TD>
							<TD WIDTH="30%">&nbsp;</TD>
							<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
							CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
							onclick="javascript:resetRequest()"></TD>
						</logic:equal>

						<logic:equal name="objRequest" property="statusTypeSeqNo"
							value="4">
								<%-- CR_106 Changes Admin may only Complete from Approved by JG101007--%>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=String.valueOf(objRequest.getReqID())%>')"></TD>
							<%-- Modified Add function to perform Spell Check for CR_104 --%>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify" id="addClause"
								onclick="javascript:fnCheckSpellnCont('fnSaveClause')"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Submit"
								onclick="javascript:saveRequestStatus(2)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyleYellow"
								TYPE="button" name="btnAdd" value="Onhold"
								onclick="javascript:saveRequestStatus(3)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT
								style='background-color:green' CLASS="buttonStyleCommon"
								TYPE="button" name="btnAdd" value="Approve"
								onclick="javascript:saveRequestStatus(4)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT style='background-color:red'
								CLASS="buttonStyleCommon" TYPE="button" name="btnAdd"
								value="Reject" onclick="javascript:saveRequestStatus(5)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Complete"
								onclick="javascript:saveRequestStatus(6)"></TD>
							<TD WIDTH="30%">&nbsp;</TD>
							<!-- Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 -->
							<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()"></TD>
						</logic:equal>

						<logic:equal name="objRequest" property="statusTypeSeqNo"
							value="5">
							<%-- CR_106 Changes Admin may not Modify a Rejected  form  by JG101007--%>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=String.valueOf(objRequest.getReqID())%>')"></TD>
							<%-- Modified Add function to perform Spell Check for CR_104 --%>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify" id="addClause"
								onclick="javascript:fnCheckSpellnCont('fnSaveClause')" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Submit"
								onclick="javascript:saveRequestStatus(2)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyleYellow"
								TYPE="button" name="btnAdd" value="Onhold"
								onclick="javascript:saveRequestStatus(3)"disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT
								style='background-color:green' CLASS="buttonStyleCommon"
								TYPE="button" name="btnAdd" value="Approve"
								onclick="javascript:saveRequestStatus(4)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT style='background-color:red'
								CLASS="buttonStyleCommon" TYPE="button" name="btnAdd"
								value="Reject" onclick="javascript:saveRequestStatus(5)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Complete"
								onclick="javascript:saveRequestStatus(0)" disabled></TD>
							<TD WIDTH="30%">&nbsp;</TD>
							<!-- Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 -->
							<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()"></TD>
						</logic:equal>

						<logic:equal name="objRequest" property="statusTypeSeqNo"
							value="6">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=String.valueOf(objRequest.getReqID())%>')"></TD>
							<%-- Modified Add function to perform Spell Check for CR_104 --%>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify" id="addClause"
								onclick="javascript:fnCheckSpellnCont('fnSaveClause')" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Submit"
								onclick="javascript:saveRequestStatus(2)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyleYellow"
								TYPE="button" name="btnAdd" value="Onhold"
								onclick="javascript:saveRequestStatus(3)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT
								style='background-color:green' CLASS="buttonStyleCommon"
								TYPE="button" name="btnAdd" value="Approve"
								onclick="javascript:saveRequestStatus(4)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT style='background-color:red'
								CLASS="buttonStyleCommon" TYPE="button" name="btnAdd"
								value="Reject" onclick="javascript:saveRequestStatus(5)"
								disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Complete"
								onclick="javascript:saveRequestStatus(6)" disabled></TD>
							<TD WIDTH="30%">&nbsp;</TD>
							<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()" disabled></TD>
						</logic:equal>
						
						<!-- Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 -->
						<logic:equal name="objRequest" property="statusTypeSeqNo"
							value="7">
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Preview"
								onclick="javascript:fnSubmitPreview('<%=String.valueOf(objRequest.getReqID())%>')"></TD>
							<%-- Modified Add function to perform Spell Check for CR_104 --%>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Save/Modify" id="addClause"
								onclick="javascript:fnCheckSpellnCont('fnSaveClause')"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Submit"
								onclick="javascript:saveRequestStatus(2)" disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyleYellow"
								TYPE="button" name="btnAdd" value="Onhold"
								onclick="javascript:saveRequestStatus(3)"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT
								style='background-color:green' CLASS="buttonStyleCommon"
								TYPE="button" name="btnAdd" value="Approve"
								onclick="javascript:saveRequestStatus(4)"></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT style='background-color:red'
								CLASS="buttonStyleCommon" TYPE="button" name="btnAdd"
								value="Reject" onclick="javascript:saveRequestStatus(5)"
								disabled></TD>
							<TD WIDTH="5%" ALIGN="center"><INPUT CLASS="buttonStyle"
								TYPE="button" name="btnAdd" value="Complete"
								onclick="javascript:saveRequestStatus(6)" disabled></TD>
							<TD WIDTH="30%">&nbsp;</TD>
							<TD WIDTH="5%" ALIGN="right"><INPUT style='background-color:red'
								CLASS="buttonStyle" TYPE="button" name="btnAdd" value="Reset"
								onclick="javascript:resetRequest()"></TD>
						</logic:equal>
						<!-- Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 - Ends here -->
						
					</logic:equal>
			</logic:iterate>
		</logic:present>

	</TR>
</TABLE>
<logic:present name="CreateChangeClauseRequestForm"
	property="changeTypeSeqNO">
	<script>
		fnOnLoadEnableDisableFields(<bean:write name='CreateChangeClauseRequestForm' property='changeTypeSeqNO'/>);
	</script>
</logic:present>
