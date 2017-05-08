<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<!-- Added CR_97 -->
<logic:present name="ModelAddClauseForm" property="leadComponentVO">
		<bean:size id="noOfLeadClauses" name="ModelAddClauseForm"
			property="leadComponentVO" />
	</logic:present>
	
	<logic:present name="ModelAddClauseForm" property="clauseList"
		scope="request">
		<bean:size name="ModelAddClauseForm" id="clsize1"
			property="clauseList" />
	</logic:present>
<!-- Ends here CR_97 -->
<DIV id="AddClause1" style="display:block;">
<script type="text/javascript">
    function fnlocalSubmit(hiddencomponentGrpArray , hiddencomponentSeqNo,hiddencomponentGrpSeqNo)
    {
	//Added to solve Components tied to clause Issue    
    document.forms['ModelAddClauseForm'].hdncomponentGroupSeqNo.value = "";
    existLength= document.forms[0].componentfrom.options.length;
    for (i=existLength;i<hiddencomponentGrpArray.length;i++)
    {
    additem = new Option();
    additem.value = hiddencomponentSeqNo[i];
    additem.text = hiddencomponentGrpArray[i];
    document.forms[0].componentfrom.options[existLength]=additem;
	document.forms[0].hdncomponentGroupSeqNo.value+=hiddencomponentGrpSeqNo[i]+"~";
    existLength++;
    }

    }

    function deleteComponent()
    {
    if (document.forms[0].componentfrom.options.length!=0)
    {
    var del=confirm("Are you sure you want to clear all the components");
	if(del == true){
    document.forms[0].componentfrom.options.length=0;
	document.forms[0].hdncomponentGroupSeqNo.value="";
	document.forms[0].componentSeqNo.value="";
    }
    }
    }
    
    //Added For CR_85 Add the EDL no on the add clause screen
    function fnAddChrstcEdlOld(chrstcEdl,combntnSeqNo,clauseSeqNo)
    { 
	 	document.forms[0].chrstcEdl.options.length=0;
	    additem = new Option();
	    additem.value = chrstcEdl;
	    additem.text = chrstcEdl;
	    document.forms[0].chrstcEdl.options[0]=additem;
		document.forms[0].hdnCombntnSeqNo.value=combntnSeqNo;
		document.forms[0].hdnCombntnSeqNoArr.value=combntnSeqNo;
		document.forms[0].hdnCharClaSeqNo.value=clauseSeqNo;		
	}
    
        //Added For CR_88 Add the EDL no on the add clause screen
    function fnAddChrstcEdl(hiddenchrstcEdl,hdnCombntnSeqNoArr,clauseSeqNo)
    { 
        var len=hdnCombntnSeqNoArr.length;  

        document.forms['ModelAddClauseForm'].hdnCombntnSeqNo.value = "";
    	existLength1=  0;// document.forms[0].chrstcEdl.options.length;
	 	document.forms[0].chrstcEdl.options.length=0;
		document.forms[0].chrstcEdl.value="";
		

		if(len!=0){
			for (j=existLength1;j<len;j++)
		    {
		
		    additem = new Option();
		    additem.value = hdnCombntnSeqNoArr[j];//combniseqno
		    additem.text = hiddenchrstcEdl[j];//edl
		
		    document.forms[0].chrstcEdl.options[existLength1]=additem;
			document.forms[0].hdnCombntnSeqNoArr.value+=hdnCombntnSeqNoArr[j]+"~";
		    existLength1++;
		    }
		}else{
		
		
			additem = new Option();
			additem.value = hdnCombntnSeqNoArr[0];
		    additem.text = hiddenchrstcEdl[0];
		    document.forms[0].chrstcEdl.options[existLength1]=additem;
			document.forms[0].hdnCombntnSeqNoArr.value+=hdnCombntnSeqNoArr[j]+"~";
		}

 		document.forms[0].hdnCharClaSeqNo.value=clauseSeqNo;
    }
        //Added For CR_85 Clean the EDL no on the add clause screen
    function  fnClearChrstcEdl()
    {
    if (document.forms[0].chrstcEdl.options.length!=0)
	    {
	    document.forms[0].chrstcEdl.options.length=0;
		document.forms[0].hdnCombntnSeqNo.value="";
		//CR 88
		document.forms[0].hdnCombntnSeqNoArr.value="";
		document.forms[0].hdnCharClaSeqNo.value="";   
		document.forms[0].componentSeqNo.value="";
		
	    }
    }
</script>

	<div class="spacer30"></div>

<div class="form-horizontal">
<logic:empty name="clsize1">
	<div class="form-group">
		<label class="col-sm-4 control-label">Customer</label>
		<div class="col-sm-4">					
			<html:select styleClass="form-control" styleId="sltCustomer" name="ModelAddClauseForm" property="customerSeqNo">
				<html:option value="-1">---Select---</html:option>
				<logic:present name="ModelAddClauseForm" property="customerList">
					<html:optionsCollection name="ModelAddClauseForm"
						property="customerList" label="customerName"
						value="customerSeqNo" />
				</logic:present>
			</html:select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-4 control-label">Characteristic Group Clause</label>
		<div class="col-sm-4 form-inline">
			 <html:checkbox  name="ModelAddClauseForm" property="selectCGCFlag" onclick="javascript:fnCharacteristicCompGroup()"  />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-4 text-nowrap control-label">Select Lead ComponentGroup</label>
		<div class="col-sm-4">					
			 <html:select styleClass="form-control" styleId="sltLeadCompGrp" name="ModelAddClauseForm" property="compGroupSeqNo"
				onchange="javascript:fnLoadComponents()">
				<html:option value="-1">---Select---</html:option>
				<logic:present name="ModelAddClauseForm"
					property="compGroupList">
					<html:optionsCollection name="ModelAddClauseForm"
						property="compGroupList" label="componentGroupName"
						value="componentGroupSeqNo" />
				</logic:present>
			</html:select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-4 text-nowrap control-label">Select Lead Component</label>
		<div class="col-sm-4 form-inline">					
			<html:select styleClass="form-control" styleId="compGroupList"
								name="ModelAddClauseForm" property="leadComponentSeqNo" 
								onchange="javascript:fnLoadComponentClauses()">


								<html:option value="-1">---Select---</html:option>

								<logic:present name="ModelAddClauseForm"
									property="compGroupList">
									<logic:iterate id="compGroupList" name="ModelAddClauseForm"
										property="compGroupList"
										type="com.EMD.LSDB.vo.common.CompGroupVO">

										<logic:equal name="ModelAddClauseForm"
											property="compGroupSeqNo"
											value="<%=String.valueOf(compGroupList.getComponentGroupSeqNo())%>">

											<logic:iterate id="compList" name="compGroupList"
												property="componentVO"
												type="com.EMD.LSDB.vo.common.ComponentVO" scope="page">

												<logic:equal name="ModelAddClauseForm"
													property="compGroupSeqNo"
													value="<%=String.valueOf(compList.getComponentGroupSeqNo())%>">
														<logic:equal name="ModelAddClauseForm"
														property="leadComponentSeqNo"
														value="<%=String.valueOf(compList.getComponentSeqNo())%>">
															<option selected value="<%=compList.getComponentSeqNo()%>"><bean:write
																name="compList" property="componentName" /></option>
														</logic:equal>
														<logic:notEqual name="ModelAddClauseForm"
														property="leadComponentSeqNo"
														value="<%=String.valueOf(compList.getComponentSeqNo())%>">
															<option value="<%=compList.getComponentSeqNo()%>"><bean:write
																name="compList" property="componentName" /></option>
														</logic:notEqual>
												</logic:equal>

											</logic:iterate>
										</logic:equal>
									</logic:iterate>
								</logic:present>
                         
			</html:select>
			<logic:greaterEqual name="noOfLeadClauses" value="1">
				<A id="viewLeadClauses" HREF="javascript:fnViewLeadcompClauses()" class="vtip" title="View Component Clauses from multiple Models">
                         <span class="glyphicon glyphicon glyphicon-search" aria-hidden="true"></span>
                </A>
			</logic:greaterEqual>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-4 control-label">Parent Clause
			<html:link href="javascript:LoadParentClauses()"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></html:link>
			<html:link href="javascript:deleteParentClause()"><span class="glyphicon glyphicon-remove-circle text-danger" aria-hidden="true"></span></html:link>
		</label>
		<div class="col-sm-4 form-inline"  id="parentclause" STYLE="height:60;width:435;overflow:auto;">
			<logic:notEmpty name="ModelAddClauseForm" property="tableClauseData">
				<logic:iterate id="outter" name="ModelAddClauseForm"
					property="tableClauseData" indexId="counter">

					<table class="table table-bordered text-center">
						<bean:define id="row" name="counter" />
						<!-- Added  For CR_93 -->
						<bean:define name="ModelAddClauseForm" property="tableDataColSize" id="tableDataColSize" />
						<tr>
							<logic:iterate id="tabrow" name="outter" scope="page" length="tableDataColSize">
								<logic:equal name="row" value="0">
									<td valign="top" width="5%" ><b><font
										color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></b></font>
									</td>
								</logic:equal>

								<logic:notEqual name="row" value="0">
									<td valign="top" width="5%"><%=(tabrow==null)? "&nbsp;":tabrow%>
									</td>
								</logic:notEqual>
							</logic:iterate>
						</tr>
					</table>
					
				</logic:iterate>
			</logic:notEmpty>
		</div>
	</div>
		
	<div class="form-group">
		<label class="col-sm-4 control-label">Clause Description<font color="red" valign="top">*</font></label>
		<div class="col-sm-4"> 
			<html:textarea styleId="clauseDesc_id" property="clauseDesc" name="ModelAddClauseForm" rows="15"
			  	cols="92" styleClass="form-control">
			</html:textarea>
			<%--Added for CR-129 for loading clauses in TinyMCE Issue  --%>
			<textarea name="hdnclauseDescForTextArea" id="hdnclauseDescForTextArea"
				style="display:none;"><bean:write name="ModelAddClauseForm" property="clauseDesc"/></textarea>
			<%--Added for CR-129 for loading clauses in TinyMCE Issue  --%>	
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
	
	<div class="form-group">
		<label class="col-sm-4 control-label">Components Tied To Clause</label>
		<div class="col-sm-4 form-inline">
			<select name="componentfrom" multiple="true" class="form-control"></select>
			<html:link href="javascript:AddComponent()" styleId="addCompToCla"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></html:link>
			<html:link href="javascript:deleteComponent()"><span class="glyphicon glyphicon-remove-circle text-danger" aria-hidden="true"></span></html:link>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-4 control-label">Characteristic Combination Tied To Clause</label>
		<div class="col-sm-4 form-inline">
			<select name="chrstcEdl" multiple="true" class="form-control"  id="charCombTied"></select>
			<html:link href="javascript:fnAddCharsticComponent()" styleId="addEdlToCla">Add</html:link>&nbsp;|&nbsp;
			<html:link href="javascript:fnViewChrCombntnList()" styleId="viewChrCombntn">View</html:link>&nbsp;|&nbsp;
			<html:link href="javascript:fnUnlinkChrCombntn()"><span class="glyphicon glyphicon-remove-circle text-danger" aria-hidden="true"></span></html:link>
		</div>
	</div>
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
								<div class="col-sm-2"><html:text property="edlNo" styleClass="form-control" size="5" maxlength="20" value=""></html:text></div>	
								<div class="col-sm-2"><html:text property="edlNo" styleClass="form-control" size="5" maxlength="20" value=""></html:text></div>	
								<div class="col-sm-2"><html:text property="edlNo" styleClass="form-control" size="5" maxlength="20" value=""></html:text></div>	
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><bean:message key="screen.partOf" /></label>
							<div>
								<div class="col-sm-2 form-inline">
									<div class="input-group">
										<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
											<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadAllClauses(1)"><span class="glyphicon glyphicon-search"></span></button>
										</span>
										<html:text indexed="1" property="partOfCode" value="" styleClass="form-control" readonly="true"></html:text>					
										<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
											<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOfCode(1)"><span class="glyphicon glyphicon-remove"></span></button>
										</span>
									</div>
									<html:hidden property="partOfSeqNo" value="" />
									<html:hidden property="partOfclaSeqNo" value="" />
								</div>
								<div class="col-sm-2 form-inline">
									<div class="input-group">
										<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
											<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadAllClauses(2)"><span class="glyphicon glyphicon-search"></span></button>
										</span>
										<html:text property="partOfCode" value="" styleClass="form-control" readonly="true"></html:text>					
										<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
											<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOfCode(2)"><span class="glyphicon glyphicon-remove"></span></button>
										</span>
									</div>
									<html:hidden property="partOfSeqNo" value="" />
									<html:hidden property="partOfclaSeqNo" value="" />
								</div>
								<div class="col-sm-2 form-inline">
									<div class="input-group">
										<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
											<button class="btn btn-default" type="button" data-toggle="modal" data-target="#ClauseDetails" onclick="javascript:LoadAllClauses(3)"><span class="glyphicon glyphicon-search"></span></button>
										</span>
										<html:text property="partOfCode" value="" styleClass="form-control" readonly="true"></html:text>					
										<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
											<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOfCode(3)"><span class="glyphicon glyphicon-remove"></span></button>
										</span>
									</div>
									<html:hidden property="partOfSeqNo" value="" />
									<html:hidden property="partOfclaSeqNo" value="" />
								</div>
								<div class="col-sm-2 form-inline">
									<div class="input-group">
										<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
											<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadAllClauses(4)"><span class="glyphicon glyphicon-search"></span></button>
										</span>
										<html:text property="partOfCode" value="" styleClass="form-control" readonly="true"></html:text>					
										<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
											<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOfCode(4)"><span class="glyphicon glyphicon-remove"></span></button>
										</span>
									</div>
									<html:hidden property="partOfSeqNo" value="" />
									<html:hidden property="partOfclaSeqNo" value="" />
								</div>
							</div>
						</div>		
						<div class="form-group">
							<label class="col-sm-4 control-label">DWO Number</label>
							<div class="col-sm-2">
								<html:text property="dwONumber" value="" styleClass="form-control" size="20" maxlength="20"></html:text>
							</div>
						</div>				
						<div class="form-group">
							<label class="col-sm-4 control-label">Part Number</label>
							<div class="col-sm-2">
								<html:text property="partNumber" value="" styleClass="form-control" size="20" maxlength="8"></html:text>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">Price Book Number</label>
							<div class="col-sm-2">
								<html:text property="priceBookNumber" styleId="priceBookNo" value="" styleClass="form-control" size="20" maxlength="4" ></html:text>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">Engineering Data </label>
							<div class="col-sm-2">
								<html:select styleClass="form-control" styleId="sltEnggData" name="ModelAddClauseForm" property="standardEquipmentSeqNo">
									<html:option value="-1">---Select---</html:option>
									<logic:present name="ModelAddClauseForm"
										property="standardEquipmentList">
										<html:optionsCollection name="ModelAddClauseForm"
											property="standardEquipmentList" label="standardEquipmentDesc"
											value="standardEquipmentSeqNo" />
									</logic:present>
								</html:select>
							</div>
						</div>				
						<div class="form-group">
							<label class="col-sm-4 control-label">Comments</label>
							<div class="col-sm-6">
								<textarea class="form-control" name="comments" rows="3" cols="60" ></textarea>
							</div>
						</div>
					</div>	
				</div>				
	<%--Added for CR-121--%> 
	<html:hidden property="leadSubClaExistsFlag" value="N"/>
</logic:empty>	
<logic:greaterThan name="clsize1" value="0">
		<logic:iterate id="check" name="ModelAddClauseForm" property="clauseList" type="com.EMD.LSDB.vo.common.ClauseVO" scope="request">
	<div class="form-group">
			<label class="col-sm-4 control-label">Customer</label>
			<div class="col-sm-4">					
				<html:select styleClass="form-control" styleId="sltCustomer" name="ModelAddClauseForm" property="customerSeqNo">
					<html:option value="-1">---Select---</html:option>
					<logic:present name="ModelAddClauseForm" property="customerList">
						<html:optionsCollection name="ModelAddClauseForm"
							property="customerList" label="customerName"
							value="customerSeqNo" />
					</logic:present>
				</html:select>
			</div>
	</div>
	<div class="form-group">
		<label class="col-sm-4 control-label">Characteristic Group Clause</label>
		<div class="col-sm-4 form-inline">
			 <html:checkbox  name="ModelAddClauseForm" property="selectCGCFlag" onclick="javascript:fnCharacteristicCompGroup()"  />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-4 text-nowrap control-label">Select Lead ComponentGroup</label>
		<div class="col-sm-4">					
			 <html:select styleClass="form-control" styleId="sltLeadCompGrp" name="ModelAddClauseForm" property="compGroupSeqNo"
				onchange="javascript:fnLoadComponents()">
				<html:option value="-1">---Select---</html:option>
				<logic:present name="ModelAddClauseForm"
					property="compGroupList">
					<html:optionsCollection name="ModelAddClauseForm"
						property="compGroupList" label="componentGroupName"
						value="componentGroupSeqNo" />
				</logic:present>
			</html:select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-4 text-nowrap control-label">Select Lead Component</label>
		<div class="col-sm-4">					
			<html:select styleClass="form-control" styleId="compGroupList"
								name="ModelAddClauseForm" property="leadComponentSeqNo" 
								onchange="javascript:fnLoadComponentClauses()">


								<html:option value="-1">---Select---</html:option>

								<logic:present name="ModelAddClauseForm"
									property="compGroupList">
									<logic:iterate id="compGroupList" name="ModelAddClauseForm"
										property="compGroupList"
										type="com.EMD.LSDB.vo.common.CompGroupVO">

										<logic:equal name="ModelAddClauseForm"
											property="compGroupSeqNo"
											value="<%=String.valueOf(compGroupList.getComponentGroupSeqNo())%>">

											<logic:iterate id="compList" name="compGroupList"
												property="componentVO"
												type="com.EMD.LSDB.vo.common.ComponentVO" scope="page">

												<logic:equal name="ModelAddClauseForm"
													property="compGroupSeqNo"
													value="<%=String.valueOf(compList.getComponentGroupSeqNo())%>">
														<logic:equal name="ModelAddClauseForm"
														property="leadComponentSeqNo"
														value="<%=String.valueOf(compList.getComponentSeqNo())%>">
															<option selected value="<%=compList.getComponentSeqNo()%>"><bean:write
																name="compList" property="componentName" /></option>
														</logic:equal>
														<logic:notEqual name="ModelAddClauseForm"
														property="leadComponentSeqNo"
														value="<%=String.valueOf(compList.getComponentSeqNo())%>">
															<option value="<%=compList.getComponentSeqNo()%>"><bean:write
																name="compList" property="componentName" /></option>
														</logic:notEqual>
												</logic:equal>

											</logic:iterate>
										</logic:equal>
									</logic:iterate>
								</logic:present>
                         
			</html:select>
			<logic:greaterEqual name="noOfLeadClauses" value="1">
				<A id="viewLeadClauses" HREF="javascript:fnViewLeadcompClauses()" class="vtip" title="View Component Clauses from multiple Models">
                         <span class="glyphicon glyphicon glyphicon-search" aria-hidden="true"></span>
                </A>
			</logic:greaterEqual>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-4 control-label">Parent Clause
			<html:link href="javascript:LoadParentClauses()"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></html:link>
			<html:link href="javascript:deleteParentClause()"><span class="glyphicon glyphicon-remove-circle text-danger" aria-hidden="true"></span></html:link>
		</label>
		<div class="col-sm-4 form-inline"  id="parentclause" STYLE="height:60;width:435;overflow:auto;">
			<logic:notEmpty name="ModelAddClauseForm" property="tableClauseData">
				<logic:iterate id="outter" name="ModelAddClauseForm"
					property="tableClauseData" indexId="counter">

					<table class="table table-bordered text-center">
						<bean:define id="row" name="counter" />
						<!-- Added  For CR_93 -->
						<bean:define name="ModelAddClauseForm" property="tableDataColSize" id="tableDataColSize" />
						<tr>
							<logic:iterate id="tabrow" name="outter" scope="page" length="tableDataColSize">
								<logic:equal name="row" value="0">
									<td valign="top" width="5%" ><b><font
										color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></b></font>
									</td>
								</logic:equal>

								<logic:notEqual name="row" value="0">
									<td valign="top" width="5%"><%=(tabrow==null)? "&nbsp;":tabrow%>
									</td>
								</logic:notEqual>
							</logic:iterate>
						</tr>
					</table>
					
				</logic:iterate>
			</logic:notEmpty>
		</div>
	</div>
		
	<div class="form-group">
		<label class="col-sm-4 control-label">Clause Description<font color="red" valign="top">*</font></label>
		<div class="col-sm-4"> 
			<html:textarea styleId="clauseDesc_id" property="clauseDesc" name="check" rows="15"
			  	cols="92" styleClass="form-control">
			</html:textarea>
			<input type="hidden" name="selectedVersion" value="<%=check.getVersionNo()%>" />
			<%--Added for CR-129 for loading clauses in TinyMCE Issue  --%>
			<textarea name="clauseDesc" id="hdnclauseDescForTextArea"
				style="display:none;"><bean:write name="check" property="clauseDesc"/></textarea>
			<%--Added for CR-129 for loading clauses in TinyMCE Issue  --%>	
			
		</div>
	</div>
	<logic:empty name="check" property="tableArrayData1">
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
	</logic:empty>
	<logic:notEmpty name="check" property="tableArrayData1">
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
							<DIV ID="showgrid">
								<Table id="tblGrid">
									<logic:notEmpty name="check" property="tableArrayData1">
										<logic:iterate id="data" name="check" property="tableArrayData1"
											indexId="counter">
											<TR>
												<logic:iterate id="data1" name="data" indexId="innerCounter">
													<bean:define id="innerSize" name="innerCounter" />
		
													<TD><bean:define id="size" name="counter" /> <logic:equal
														name="size" value="0">
														<!--This outer logic:equal check is for display the first row with bold font -->
														<logic:lessThan name="innerSize" value="1">
															<!--This Inner logic:lessthan check is for display the first row first text box -->
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<input type=text
																name="clauseTableDataArray<%=innerCounter.intValue()+1%>"
																class="form-control" size="13" maxlength="100"
																value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>" />
														</logic:lessThan>
		
														<logic:greaterThan name="innerSize" value="0">
															<!-- This check is for display the rest of first row four text boxes -->
												&nbsp;&nbsp;<input type=text
																name="clauseTableDataArray<%=innerCounter.intValue()+1%>"
																class="form-control" size="13" maxlength="100"
																value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>" />
														</logic:greaterThan>
													</logic:equal> <logic:greaterThan name="size" value="0">
														<!--This outer logic:greaterThan check is for display the second row datas -->
														<logic:equal name="innerSize" value="0">
															<!--This Inner logic:equal check is for display the Second row first text box with radio buttons -->
													&nbsp;&nbsp;<html:radio property="deleterow"
																styleClass="radcheck" value="" />
													 &nbsp;<input type=text
																name="clauseTableDataArray<%=innerCounter.intValue()+1%>"
																class="form-control" size="13" maxlength="100"
																value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>" />
														</logic:equal>
														<!--This Inner logic:notEqual check is for display the Second row other text box without radio buttons -->
														<logic:notEqual name="innerSize" value="0">
													&nbsp;&nbsp;
													 <input type=text
																name="clauseTableDataArray<%=innerCounter.intValue()+1%>"
																class="form-control" size="10" maxlength="100"
																value="<%=(data1!=null && !"".equals(data1) )? data1.toString() : ""%>" />
														</logic:notEqual>
		
													</logic:greaterThan></TD>
		
												</logic:iterate>
											</TR>
										</logic:iterate>
									</logic:notEmpty>
								</Table>
							</DIV>
						</div>
					</div>
				</logic:notEmpty>
	
	<div class="form-group">
		<label class="col-sm-4 control-label">Components Tied To Clause</label>
		<div class="col-sm-4 form-inline">
			<select name="componentfrom" multiple="true" class="form-control"></select>
			<html:link href="javascript:AddComponent()" styleId="addCompToCla"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></html:link>
			<html:link href="javascript:deleteComponent()"><span class="glyphicon glyphicon-remove-circle text-danger" aria-hidden="true"></span></html:link>
		</div>
	</div>
	<div class="row form-group">
		<label class="col-sm-4 control-label">Characteristic Combination Tied To Clause</label>
		<div class="col-sm-4 form-inline">
			<select name="chrstcEdl" multiple="true" class="form-control" ></select>
			<html:link href="javascript:fnAddCharsticComponent()" styleId="addEdlToCla">Add</html:link>&nbsp;|&nbsp;
			<html:link href="javascript:fnViewChrCombntnList()" styleId="viewChrCombntn">View</html:link>&nbsp;|&nbsp;
			<html:link href="javascript:fnUnlinkChrCombntn()"><span class="glyphicon glyphicon-remove-circle text-danger" aria-hidden="true"></span></html:link>
		</div>
	</div>
	<div class="row">
			<div class="spacer20"></div>
		</div>
		
	<div class="panel panel-default col-sm-offset-1" style="width:80%;align:center;">
   			<div class="panel-heading"><h3 class="panel-title text-center">Engineering Data <font color="red" valign="top">*</font></h3></div>
	   			<div class="panel-body">
	    				<div class="form-group">
							<label class="col-sm-4 control-label">Reference EDL Number(s)</label>
							<div>
							<logic:notEmpty name="check" property="refEDLNo">
								<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="refEDLNo" maxlength="20" value='<%=check.getRefEDLNo()[0]%>' /></div>
								<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="refEDLNo" maxlength="20" value='<%=check.getRefEDLNo()[1]%>' /></div>
								<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="refEDLNo" maxlength="20" value='<%=check.getRefEDLNo()[2]%>' /></div>
							</logic:notEmpty>
							<logic:empty name="check" property="refEDLNo">
								<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="refEDLNo" maxlength="20" value="" /></div>
								<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="refEDLNo" maxlength="20" value="" /></div>
								<div class="col-sm-2"><html:text styleClass="form-control" size="5" property="refEDLNo" maxlength="20" value="" /></div>
							</logic:empty>	
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">New EDL Number(s)</label>
							<div>
								<logic:notEmpty name="check" property="edlNo">
									<div class="col-sm-2"><html:text property="edlNo" styleClass="form-control" size="5" maxlength="20" value='<%=check.getEdlNo()[0]%>'></html:text></div>	
									<div class="col-sm-2"><html:text property="edlNo" styleClass="form-control" size="5" maxlength="20" value='<%=check.getEdlNo()[1]%>'></html:text></div>	
									<div class="col-sm-2"><html:text property="edlNo" styleClass="form-control" size="5" maxlength="20" value='<%=check.getEdlNo()[2]%>'></html:text></div>	
								</logic:notEmpty>
								<logic:empty name="check" property="edlNo">
									<div class="col-sm-2"><html:text property="edlNo" styleClass="form-control" size="5" maxlength="20" value=""></html:text></div>	
									<div class="col-sm-2"><html:text property="edlNo" styleClass="form-control" size="5" maxlength="20" value=""></html:text></div>	
									<div class="col-sm-2"><html:text property="edlNo" styleClass="form-control" size="5" maxlength="20" value=""></html:text></div>	
								</logic:empty>	
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><bean:message key="screen.partOf" /></label>
							<div>
								<logic:notEmpty name="check" property="partOfSeqNo">
										<logic:notEmpty name="check" property="partOfCode">
											<div class="col-sm-2 form-inline">
											<div class="input-group">
												<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
													<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadAllClauses(1)"><span class="glyphicon glyphicon-search"></span></button>
												</span>
												<html:text indexed="1" property="partOfCode" value='<%=(check.getPartOfCode()[0])=="0" ? "":check.getPartOfCode()[0] %>'
															 styleClass="form-control" readonly="true"></html:text>					
												<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
													<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOfCode(1)"><span class="glyphicon glyphicon-remove"></span></button>
												</span>
											</div>
										<html:hidden property="partOfSeqNo" value='<%=String.valueOf(check.getPartOfSeqNo()[0])%>'/>
										<html:hidden property="partOfclaSeqNo" value='<%=String.valueOf(check.getPartOfClaSeqNo()[0])%>'/>
									</div>
									<div class="col-sm-2 form-inline">
										<div class="input-group">
											<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
												<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadAllClauses(2)"><span class="glyphicon glyphicon-search"></span></button>
											</span>
											<html:text property="partOfCode" value='<%=(check.getPartOfCode()[1])=="0" ? "":check.getPartOfCode()[1] %>' styleClass="form-control" readonly="true"></html:text>					
											<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
												<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOfCode(2)"><span class="glyphicon glyphicon-remove"></span></button>
											</span>
										</div>
										<html:hidden property="partOfSeqNo" value='<%=String.valueOf(check.getPartOfSeqNo()[1])%>' />
										<html:hidden property="partOfclaSeqNo" value='<%=String.valueOf(check.getPartOfClaSeqNo()[1])%>' />
									</div>
									<div class="col-sm-2 form-inline">
										<div class="input-group">
											<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
												<button class="btn btn-default" type="button" data-toggle="modal" data-target="#ClauseDetails" onclick="javascript:LoadAllClauses(3)"><span class="glyphicon glyphicon-search"></span></button>
											</span>
											<html:text property="partOfCode" value='<%=(check.getPartOfCode()[2])=="0" ? "":check.getPartOfCode()[2] %>' styleClass="form-control" readonly="true"></html:text>					
											<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
												<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOfCode(3)"><span class="glyphicon glyphicon-remove"></span></button>
											</span>
										</div>
										<html:hidden property="partOfSeqNo" value='<%=String.valueOf(check.getPartOfSeqNo()[2])%>' />
										<html:hidden property="partOfclaSeqNo" value='<%=String.valueOf(check.getPartOfClaSeqNo()[2])%>' />
									</div>
									<div class="col-sm-2 form-inline">
										<div class="input-group">
											<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
												<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadAllClauses(4)"><span class="glyphicon glyphicon-search"></span></button>
											</span>
											<html:text property="partOfCode" value='<%=(check.getPartOfCode()[3])=="0" ? "":check.getPartOfCode()[3] %>' styleClass="form-control" readonly="true"></html:text>					
											<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
												<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOfCode(4)"><span class="glyphicon glyphicon-remove"></span></button>
											</span>
										</div>
										<html:hidden property="partOfSeqNo" value='<%=String.valueOf(check.getPartOfSeqNo()[3])%>' /> 
										<html:hidden property="partOfclaSeqNo" value='<%=String.valueOf(check.getPartOfClaSeqNo()[3])%>' />
									</div>
									</logic:notEmpty>
								</logic:notEmpty>
								<logic:empty name="check" property="partOfCode">		
									<div class="col-sm-2 form-inline">
										<div class="input-group">
											<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
												<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadAllClauses(1)"><span class="glyphicon glyphicon-search"></span></button>
											</span>
											<html:text indexed="1" property="partOfCode" value="" styleClass="form-control" readonly="true"></html:text>					
											<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
												<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOfCode(1)"><span class="glyphicon glyphicon-remove"></span></button>
											</span>
										</div>
										<html:hidden property="partOfSeqNo" />
										<html:hidden property="partOfclaSeqNo" />
									</div>
									<div class="col-sm-2 form-inline">
										<div class="input-group">
											<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
												<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadAllClauses(2)"><span class="glyphicon glyphicon-search"></span></button>
											</span>
											<html:text property="partOfCode" value="" styleClass="form-control" readonly="true"></html:text>					
											<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
												<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOfCode(2)"><span class="glyphicon glyphicon-remove"></span></button>
											</span>
										</div>
										<html:hidden property="partOfSeqNo" />
										<html:hidden property="partOfclaSeqNo" />
									</div>
									<div class="col-sm-2 form-inline">
										<div class="input-group">
											<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
												<button class="btn btn-default" type="button" data-toggle="modal" data-target="#ClauseDetails" onclick="javascript:LoadAllClauses(3)"><span class="glyphicon glyphicon-search"></span></button>
											</span>
											<html:text property="partOfCode" value="" styleClass="form-control" readonly="true"></html:text>					
											<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
												<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOfCode(3)"><span class="glyphicon glyphicon-remove"></span></button>
											</span>
										</div>
										<html:hidden property="partOfSeqNo" />
										<html:hidden property="partOfclaSeqNo" />
									</div>
									<div class="col-sm-2 form-inline">
										<div class="input-group">
											<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
												<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadAllClauses(4)"><span class="glyphicon glyphicon-search"></span></button>
											</span>
											<html:text property="partOfCode" value="" styleClass="form-control" readonly="true"></html:text>					
											<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
												<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOfCode(4)"><span class="glyphicon glyphicon-remove"></span></button>
											</span>
										</div>
										<html:hidden property="partOfSeqNo" />
										<html:hidden property="partOfclaSeqNo" />
									</div>
							</logic:empty>	
							</div>
						</div>		
			<div class="form-group">
				<label class="col-sm-4 control-label">DWO Number</label>
				<div class="col-sm-2">
					<logic:equal name="check" property="dwONumber" value="0">
						<html:text property="dwONumber" styleClass="form-control" size="20" maxlength="20" value="" />
					</logic:equal> 
					<logic:greaterThan name="check" property="dwONumber" value="0">
						<html:text name="check" property="dwONumber" styleClass="form-control" size="20" maxlength="20" />
					</logic:greaterThan>
				</div>
			</div>				
			<div class="form-group">
				<label class="col-sm-4 control-label">Part Number</label>
				<div class="col-sm-2">
					<logic:equal name="check" property="partNumber"  value="0">
						<html:text property="partNumber" styleClass="form-control" size="20" maxlength="8" value="" />
					</logic:equal>
					 <logic:greaterThan name="check" property="partNumber" value="0">
						<html:text property="partNumber" name="check" styleClass="form-control" size="20" maxlength="8" />
					</logic:greaterThan>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Price Book Number</label>
				<div class="col-sm-2">
					<logic:greaterThan name="check" property="priceBookNumber" value="0">
						<html:text property="priceBookNumber" name="check" styleId="priceBookNo" styleClass="form-control" size="20" maxlength="4"/>
					</logic:greaterThan>
					<logic:equal name="check" property="priceBookNumber" value="0">
						<html:text property="priceBookNumber" styleId="priceBookNo" styleClass="form-control" size="20" maxlength="4" value="" />
					</logic:equal>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Engineering Data </label>
				<div class="col-sm-2">
					<html:select styleClass="form-control" property="standardEquipmentSeqNo"  styleId="sltEnggData">
							<option value="-1">--Select--</option>
							<logic:present name="ModelAddClauseForm"
								property="standardEquipmentList">
								<logic:iterate id="stdEquip" name="ModelAddClauseForm"
									property="standardEquipmentList" scope="request"
									type="com.EMD.LSDB.vo.common.StandardEquipVO">

									<logic:equal
										value="<%=String.valueOf(check.getStandardEquipmentSeqNo())%>"
										name="stdEquip" property="standardEquipmentSeqNo">
										<option value="<%=stdEquip.getStandardEquipmentSeqNo()%>"
											selected><bean:write name="stdEquip"
											property="standardEquipmentDesc" /></option>
									</logic:equal>

									<logic:notEqual
										value="<%=String.valueOf(check.getStandardEquipmentSeqNo())%>"
										name="stdEquip" property="standardEquipmentSeqNo">
										<option value="<%=stdEquip.getStandardEquipmentSeqNo()%>">
										<bean:write name="stdEquip"
											property="standardEquipmentDesc" /></option>
									</logic:notEqual>
								</logic:iterate>
							</logic:present>
					</html:select>
				</div>
			</div>				
			<div class="form-group control-label">
				<label class="col-sm-4">Comments</label>
				<div class="col-sm-6">
					<html:textarea name="check" property="comments" styleClass="form-control" rows="4" cols="60"></html:textarea>
				</div>
			</div>
        </div>
     </div>   
	<%--Added for CR-121 --%>
	<logic:equal name="check" property="subClaExistsFlag" value="Y">
	<html:hidden property="leadSubClaExistsFlag" value="Y"/>
	</logic:equal>
	<logic:notEqual name="check" property="subClaExistsFlag" value="Y">
	<html:hidden property="leadSubClaExistsFlag" value="N"/>
	</logic:notEqual>
	<%--Added for CR-121 Ends here--%>
</logic:iterate>
</logic:greaterThan>		
</div>
<div id="AddClause2" style="display:block;">
	<div class="spacer30"></div>
		<div class="form-group">
			<div class="col-sm-offset-5 col-sm-3 text-center">
				<button class="btn btn-primary" property="addButton" type='button' id="addClause" 
				onclick="javascript:fnAddClause()">Add
				</button>
			</div>
		</div>
</div>
<logic:greaterEqual name="noOfLeadClauses" value="1">
	<div class="modal fade modal-childCla" id="leadcompClausesList" style="display:none;">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				 <div class="modal-header">		
					 <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				      	 <h4 class="modal-title" id="childClauseModel"><bean:message key="Application.Screen.AddModifyClause.LeadCompClause" /></h4>
				 </div>
	 			<div class="modal-body">
			 		<table class="table table-bordered table-hover">
					    <thead>
					        <tr>
								<TH  WIDTH="2%" class="text-center">Select</TH>
								<TH  WIDTH="25%" class="text-center">Clause Description</TH>
								<TH  WIDTH="10%" class="text-center">Engineering Data</TH>
								<TH  WIDTH="10%" class="text-center">Sub Clause Exists ?</TH>
							</tr>	
						</thead>
					    <tbody class="text-center">
					    	<logic:iterate id="LeadcompParent" name="ModelAddClauseForm"
								property="leadComponentVO" scope="request"
								type="com.EMD.LSDB.vo.common.ClauseVO">
								<TR>
									<TD CLASS="v-midalign" align="center">
										<input type=radio name="leadClauseSeqNo"  value="<%=String.valueOf(LeadcompParent.getClauseSeqNo())%>"
														onclick="javascript:fnSetSelectedClaValues('<bean:write
											name="LeadcompParent" property="clauseSeqNo" />','<bean:write
											name="LeadcompParent" property="modelSeqNo" />','<bean:write
											name="LeadcompParent" property="subSectionSeqNo" />');" />
									</TD> 
										
									<!-- CR_97 	<html:hidden name="LeadcompParent" property="versionNo" /> -->
									<TD CLASS="v-midalign" valign="top" align="left">
										<%-- CR-128 - Updated for Pdf issue --%>
									<%if(String.valueOf(LeadcompParent.getClauseDesc()).startsWith("<p>"))
									{%>
										<%=(String.valueOf(LeadcompParent.getClauseDesc()))%>
									<%}else{ %>	
										<%=(String.valueOf(LeadcompParent.getClauseDesc())).replaceAll("\n","<br>")%>
									<%}%>
									<%-- CR-128 - Updated for Pdf issue Ends Here--%>
									<table class="table table-bordered">
										<logic:notEmpty name="LeadcompParent" property="tableArrayData1">
												&nbsp;
						 						<logic:iterate id="outter" name="LeadcompParent"
												property="tableArrayData1" indexId="counter">
												<!-- Added  For CR_93 -->
													<bean:define name="LeadcompParent" property="tableDataColSize" id="tableDataColSize" />
												<bean:define id="row" name="counter" />
												<tr>
													<logic:iterate id="tabrow" name="outter" length="tableDataColSize">
														<logic:equal name="row" value="0">
															<td valign="top" width="5%" ><b><font
																color="navy"><%=(tabrow==null)? "&nbsp;":tabrow%></b></font>
															</td>
														</logic:equal>
														<logic:notEqual name="row" value="0">
															<td valign="top" width="5%" ><%=(tabrow==null)? "&nbsp;":tabrow%>
															</td>
														</logic:notEqual>
													</logic:iterate>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
									</table>
									</TD>
									<TD CLASS="v-midalign" valign="top">
												 <logic:present name="LeadcompParent"
												property="edlNO">
												<logic:iterate id="engDataEdlNo" name="LeadcompParent"
													property="edlNO">
													<bean:message key="screen.edl" />
													<bean:write name="engDataEdlNo" />
													<br>
												</logic:iterate>
											</logic:present> 
											<!--  CR 87 -->
											<logic:present name="LeadcompParent" property="refEDLNO">
		
												<logic:iterate id="engDataRefEdlNo" name="LeadcompParent"
													property="refEDLNO">
													<bean:message key="screen.refEdl.start" />
													<bean:write name="engDataRefEdlNo" />
													<bean:message key="screen.refEdl.end" />
													<br>
												</logic:iterate>
											</logic:present>
											<logic:present name="LeadcompParent"
												property="partOF">
												<logic:iterate id="engPartOfCd" name="LeadcompParent"
													property="partOF">
													<logic:notEqual name="engPartOfCd" value="0">
														<bean:message key="screen.partOf" />
														<bean:write name="engPartOfCd" />
														<br>
													</logic:notEqual>
												</logic:iterate>
											</logic:present> <logic:present name="LeadcompParent"
												property="dwONumber">
												<logic:notEqual name="LeadcompParent" property="dwONumber"
													value="0">
													<bean:message key="screen.dwoNo" />
													<bean:write name="LeadcompParent" property="dwONumber" />
													<br>
												</logic:notEqual>
											</logic:present> <logic:present name="LeadcompParent"
												property="partNumber">
												<logic:notEqual name="LeadcompParent" property="partNumber"
													value="0">
													<bean:message key="screen.partNo" />
													<bean:write name="LeadcompParent" property="partNumber" />
													<br>
												</logic:notEqual>
											</logic:present> <!--Price Book Number is added in the Engineering data Section, modified on 30-09-08 by ps5722-->
											<logic:present name="LeadcompParent" property="priceBookNumber">
												<logic:notEqual name="LeadcompParent" property="priceBookNumber"
													value="0">
													<bean:message key="screen.priceBookNo" />&nbsp; <bean:write
														name="LeadcompParent" property="priceBookNumber" />
													<br>
												</logic:notEqual>
											</logic:present> <logic:present name="LeadcompParent"
												property="standardEquipmentDesc">
												<bean:write name="LeadcompParent" property="standardEquipmentDesc" />
												<br>
											</logic:present> <logic:present name="LeadcompParent"
												property="engDataComment">
												<bean:define id="engDatCmnt" name="LeadcompParent"
													property="engDataComment" />
												<%=engDatCmnt %>
												<br>
											</logic:present>
									</TD>
									<TD class="v-midalign" valign="top">
										<bean:write name="LeadcompParent" property="subClaExistsFlag" />
									</TD>
								</TR>
							</logic:iterate>
					    </tbody>
					</table>
			</div>			    
			<div class="modal-footer">		
				<button class="btn btn-primary mdfybtn" type='button' id="clauseButton" ONCLICK="javascript:fnFetchClauseDetails()">Load Clause Details</button>
				<button class="btn btn-default" type='button'  data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>	
</logic:greaterEqual>
	<html:hidden property="parentClauseSeqNo" />
	<html:hidden property="componentSeqNo" />
	<input type="hidden" name="hdncomponentGroupSeqNo" />
	<!--<html:hidden property="modifyClauseFlag" value="N"/> commented for CR-131-->
	<input type="hidden" name="hdnClaChrstcFlag" value="Y"/>
	<!-- Added For CR_81 on 24-Dec-09 by ------- -->
	<html:hidden property="hdnCombntnSeqNo" />
	<html:hidden property="hdnCharClaSeqNo" />
	<!-- CR 88 -->
	<html:hidden property="hdnCombntnSeqNoArr" />
	<!-- CR 97 -->	
	<input type="hidden" name="leadClaMdlSeq" value="0"/>
	<input type="hidden" name="leadClaSubSecSeq" value="0"/>

	<script>
		fnCharacteristicCompGroup();
		<%--function updated for CR-121 --%>
		function fnSetSelectedClaValues(claSeqNo,modelSeqNo,subsecSeqNo){
			document.forms['ModelAddClauseForm'].hdnClauseSeqNo.value=claSeqNo;
			document.forms[0].leadClaMdlSeq.value=modelSeqNo;
			document.forms[0].leadClaSubSecSeq.value=subsecSeqNo;
		}
	</script>
	</DIV>
	<!-- Commented for CR-131
	<%--Added for CR-121 --%>
	<div id='confirm'>
			<div class='header'><span>Confirm Box</span></div>
			<div class='message'>Sub-clauses available from the Copy from Clause.<br>
			Do you want to automatically add those sub-clauses to the current clause?</p>
			<div class='buttons'>
							<div class='no'>No</div>
							<div class='yes'>Yes</div>
							
			</div>
	</div>

		<div style='display:none'>
			<img src='images/confirm/header.gif' alt='' />
			<img src='images/confirm/button.gif' alt='' />
		</div>

	</div>
	<%--Added for CR-121 ends here --%> -->

<!-- Added for CR_92 moving spell checker pop up page into spellChecker.jsp -->	
	<%-- <%@ include file="/jsp/common/spellChecker.jsp" %>	--%>