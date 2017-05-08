<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!-- Added for CR_88  -->
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>

<!-- Added for CR_121 Starts Here
<script language="javascript" src="js/Others/tiny_mce/tiny_mce.js"></script>
<!-- Added for CR_121 Ends Here -->
<script language="javascript" src="js/Others/tiny_mce/jquery.tinymce.js"></script>

<%-- Added for CR-91 to remove repeated code--%>
<%@ include file="/jsp/common/richTextEditor.jsp" %> 

<SCRIPT LANGUAGE="JavaScript" SRC="js/AddSubClause.js"></SCRIPT>
<script>
        $(document).ready(function() { 
        	$("#sltEnggData").select2({theme:'bootstrap'});   
        })
    </script>
</HEAD>

<div class="container-fluid">
<html:form action="/AddSubClause" method="post" styleClass="form-horizontal">

	<!-- Application PageName Display starts here-->
	<h4 class="text-center">
		<bean:message key="Application.Screen.AddSubClause" />
	</h4>
	

	<!-- Application PageName Display Ends here-->

	<!-- Validation Message Display Part Starts Here -->
	<div class="row">
		<div class="errorlayerhide" id="errorlayer">
		</div>
	</div>

	<!-- Validation Message Display Part Ends Here -->

	<logic:present name="AddSubClauseForm" property="clauseList"
		scope="request">
		<bean:size name="AddSubClauseForm" id="clsize" property="clauseList" />
	</logic:present>


	<logic:present name="AddSubClauseForm" property="stdEquipmentList"
		scope="request">
		<bean:size name="AddSubClauseForm" id="stdsize"
			property="stdEquipmentList" />
	</logic:present>

	<logic:present name="AddSubClauseForm" property="messageID"
		scope="request">

			<bean:define id="messageID" name="AddSubClauseForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>

	</logic:present>

	<html:hidden property="clauseSeqNo" />

	<html:hidden property="orderKey" />
	<html:hidden property="secSeq" />
	<html:hidden property="secName" />
	<html:hidden property="secCode" />
	<html:hidden property="revCode" />
	<html:hidden property="clauseNum" />
	<html:hidden property="customerSeqNo" />

	<logic:iterate id="check" name="AddSubClauseForm" property="clauseList"
		type="com.EMD.LSDB.vo.common.ClauseVO" scope="request">
			<div class="form-group">
				<label class="col-sm-4 control-label"><strong>Order Number</strong></label>
				<div class="col-sm-4">
					<p class="form-control-static"><strong><bean:write name="check" property="orderNo" /></strong></p>
					<html:hidden name="check" property="modelSeqNo" />
					<html:hidden name="check" property="orderNo" />
					<html:hidden name="check" property="specStatusCode" />
					<html:hidden name="check" property="specTypeNo" styleId="specTypeNo"/>
				</div>
				
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label "><strong>Section</strong></label>
				<div class="col-sm-4">
					<p class="form-control-static"><strong><bean:write name="check" property="sectionName" /></strong></p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label "><strong>SubSection</strong></label>
				<div class="col-sm-4">
					<p class="form-control-static"><strong><bean:write name="check" property="subSectionName" /></strong></p>
					<html:hidden name="check" property="subSectionSeqNo" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label"><strong>Parent Clause No</strong></label>
				<div class="col-sm-4">
					<p class="form-control-static"><strong><bean:write name="AddSubClauseForm" property="clauseNum" /></strong></p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label"><strong>Parent Clause Description</strong></label>
				<div class="col-sm-4" style="solid;height:80px;overflow:auto;">		
					<bean:define name="check" property="clauseDesc" id="clauseDesc" /> 
						<!-- CR-128 - Updated for Pdf issue -->
							<%String strClauseDesc =  String.valueOf(clauseDesc);
							if(strClauseDesc.startsWith("<p>")){%>
								<%=(String.valueOf(clauseDesc))%>
							<%}else{ %>	
								<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%>
							<%}%>
						<!-- CR-128 - Updated for Pdf issue Ends Here-->
					
					<table class="table table-bordered text-center">
						<logic:notEmpty name="check" property="tableArrayData1">
							<logic:iterate id="outter" name="check"
								property="tableArrayData1" indexId="counter">
								<bean:define id="row" name="counter" />
								<!-- Added  For CR_93 -->
										<bean:define name="check" property="tableDataColSize" id="tableDataColSize" />
								<tr>
									<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
										<logic:equal name="row" value="0">
											<td valign="top" width="5%" class="borderbottomleft1"><b><font
												color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></font></b>
											</td>
										</logic:equal>
	
										<logic:notEqual name="row" value="0">
											<td valign="top" width="5%" class="borderbottomleft1"><%=(tabrow==null)? "&nbsp;":tabrow%>
											</td>
										</logic:notEqual>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
					</table>
				</div>
			</div>
			<div class="form-group required-field">
				<label class="col-sm-4 control-label">New Clause Description</label>
				<div class="col-sm-4"> 
					<textarea class="form-control" id="clauseDesc_id" name="newClauseDesc" rows="15" cols="92"
							class="CLAUSEDESCTEXTAREA"></textarea>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-4 control-label">Table Data
					<DIV id="showmainlink" style="visibility:visible">
						<A  HREF="javascript:addTable()">Add Table</A>
					</DIV>
					<DIV id="showsublink" style="visibility:hidden">
						<A  HREF="javascript:addRow(this)">Add Row</A> |
						<A  HREF="javascript:removeRow()">Delete Row</A><br>
						<A  HREF="javascript:deleteTable()">Delete Table</A>
					</DIV>
				</label>	
				<div class="col-sm-8"> 
					<DIV ID="showgrid"></DIV>
				</div>			
			</div>
		</logic:iterate>
		
		<div class="row">
			<div class="spacer20"></div>
		</div>
		
		<div class="panel panel-default col-sm-offset-1" style="width:80%;align:center;">
	   			<div class="panel-heading"><h3 class="panel-title text-center">Engineering Data <font color="red" valign="top">*</font></h3></div>
	   			<div class="panel-body">
	   				<div class="form-group">
						<label class="col-sm-4 control-label">Reference EDL Number(s)</label>
							<div>
								<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="refEDLNo" maxlength="20" value="" /></div>
								<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="refEDLNo" maxlength="20" value="" /></div>
								<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="refEDLNo" maxlength="20" value="" /></div>
							</div>
					</div>
	   				<div class="form-group">
						<label class="col-sm-4 control-label">New EDL Number(s)</label>
							<div>
								<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="edlNo" maxlength="20" value="" /></div>
								<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="edlNo" maxlength="20" value="" /></div>
								<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="edlNo" maxlength="20" value="" /></div>
							</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">Part of</label>
						<div>
							<div class="col-sm-2 form-inline">
								<div class="input-group">
									<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
										<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(1)"><span class="glyphicon glyphicon-search"></span></button>
									</span>
									<html:text styleClass="form-control" styleId="partOf1" property="partOf" readonly="true" value="" />				
									<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
										<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOfCode(1)"><span class="glyphicon glyphicon-remove"></span></button>
									</span>
								</div>
								<html:hidden property="partOfSeqNo" value="" /> 
								<html:hidden property="clauseSeqNum" value="" />
							</div>
							<div class="col-sm-2 form-inline">
								<div class="input-group">
									<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
										<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(2)"><span class="glyphicon glyphicon-search"></span></button>
									</span>
									<html:text styleClass="form-control" styleId="partOf2" property="partOf" readonly="true" value="" />				
									<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
										<button class="clearbtn btn btn-default" type="button" id="clearpartOf2" onclick="javascript:deletePartOfCode(2)"><span class="glyphicon glyphicon-remove"></span></button>
									</span>
								</div>
								<html:hidden property="partOfSeqNo" value="" /> 
								<html:hidden property="clauseSeqNum" value="" />
							</div>
							<div class="col-sm-2 form-inline">
								<div class="input-group">
									<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
										<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(3)"><span class="glyphicon glyphicon-search"></span></button>
									</span>
									<html:text styleClass="form-control" styleId="partOf3" property="partOf" readonly="true" value="" />				
									<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
										<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOfCode(3)"><span class="glyphicon glyphicon-remove"></span></button>
									</span>
								</div>
								<html:hidden property="partOfSeqNo" value="" /> 
								<html:hidden property="clauseSeqNum" value="" />
							</div>
							<div class="col-sm-2 form-inline">
								<div class="input-group">
									<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
										<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(4)"><span class="glyphicon glyphicon-search"></span></button>
									</span>
									<html:text styleClass="form-control" styleId="partOf4" property="partOf" readonly="true" value="" />				
									<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
										<button class="clearbtn btn btn-default" type="button" id="clearpartOf4" onclick="javascript:deletePartOfCode(4)"><span class="glyphicon glyphicon-remove"></span></button>
									</span>
								</div>
								<html:hidden property="partOfSeqNo" value="" /> 
								<html:hidden property="clauseSeqNum" value="" />
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-4 control-label">DWO Number</label>
						<div class="col-sm-2">
							<html:text property="dwONumber" styleClass="form-control" size="20" maxlength="20" value="" />
						</div>
					</div>
						
					<div class="form-group">
						<label class="col-sm-4 control-label">Part Number</label>
						<div class="col-sm-2">
							<html:text property="partNumber" styleClass="form-control" size="20" maxlength="8" value="" />	
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-4 control-label">Price Book Number</label>
						<div class="col-sm-2">
							<html:text property="priceBookNumber" styleId="priceBookNo"
								styleClass="form-control" size="20" maxlength="4" value=""></html:text>
						</div>
					</div>	
					
					<div class="form-group">
						<label class="col-sm-4 control-label">Engineering Data</label>
						<div class="col-sm-2">
							<html:select styleId="sltEnggData" styleClass="form-control" name="AddSubClauseForm" property="standardEquipmentSeqNo">
								<html:option value="-1">---Select---</html:option>
								<logic:present name="AddSubClauseForm" property="stdEquipmentList">
									<html:optionsCollection name="AddSubClauseForm"
										property="stdEquipmentList" label="standardEquipmentDesc"
										value="standardEquipmentSeqNo" />
								</logic:present>
							</html:select>
						</div>
					</div>	
					
					<div class="form-group">
						<label class="col-sm-4 control-label">Comments</label>
						<div class="col-sm-6">
							<html:textarea styleClass="form-control" name="AddSubClauseForm" property="comments" rows="3" cols="60" />
						</div>
					</div>
				</div>
		</div>
		<logic:iterate id="check" name="AddSubClauseForm" property="clauseList" type="com.EMD.LSDB.vo.common.ClauseVO" scope="request">
			<div class="panel panel-default col-sm-offset-1" style="width:80%;align:center;">
				<div class="panel-heading"><h3 class="panel-title text-center">Spec Supplement</h3></div>
	   			<div class="panel-body">
		    			<div class="form-group">
		    				<label class="col-sm-4 control-label">Reason
		    				<logic:greaterEqual name="check" property="specStatusCode" value="3">
								<font color="red" valign="top">*</font>
							</logic:greaterEqual>
		    				</label>
							<div class="col-sm-6">
								<html:textarea styleClass="form-control" name="AddSubClauseForm" property="reason" rows="3" cols="60" />
							</div>
						</div>
		   			</div>
		   		</div>
	    </logic:iterate>
	    
		<div class="row">
			<div class="spacer20"></div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-12 text-center">
				<button class="btn btn-primary" type='button' ONCLICK="javascript:fnCheckSpellnCont('fnAdd')">Add</button>
				<button class="btn btn-primary" type='button' ONCLICK="javascript:fnModifySpec()">Return to Modify Spec</button>
			</div>
		</div>	
		<div class="row">
			<div class="spacer50"></div>
		</div>
		
	<%-- Added for CR_99 for displaying JUNK characters by RJ85495 --%>	
	<jsp:include page="/jsp/common/ClauseComparison.jsp" />
	<%-- CR_99 Ends here --%>
	<!-- Added for CR_92 moving spell checker pop up page into spellChecker.jsp -->
	<%-- <%@ include file="/jsp/common/spellChecker.jsp" %> --%>
</html:form>
</div>
