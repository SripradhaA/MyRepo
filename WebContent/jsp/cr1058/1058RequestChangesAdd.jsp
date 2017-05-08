<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>

<logic:notPresent name="ChangeRequest1058Form" property="reviseClauseDetailsList" scope="request">
	<DIV id="divAddClause" >
		<div class="panel panel-info">
			<div class="panel-heading">
				<h4 class="panel-title"><strong>Add Clause</strong></h4>
			</div>
			<div class="panel-body">
				<div class="form-group required-field">
					<label class="col-sm-4 control-label">Section</label>
					<div class="col-sm-4">
						<html:select property="sections" styleClass='form-control' tabindex="8" styleId="sltSection"></html:select>
					</div>
				</div>
				<div class="form-group required-field">
					<label class="col-sm-4 control-label">SubSection</label>
					<div class="col-sm-4">
						<html:select property="subSections" styleClass='form-control' tabindex="8" styleId="sltSubSection"></html:select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Select Lead Component Group</label>
					<div class="col-sm-4">
						<html:select property="componentGroup" styleClass='form-control' tabindex="8" styleId="sltLeadCompGroup"></html:select>
						<html:hidden styleId="componentGrpSeqNoinAdd" property="componentGrpSeqNoinAdd" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Select Lead Component</label>
					<div class="col-sm-4">
						 <html:select property="components" styleClass='form-control' tabindex="8" styleId="sltLeadComp"></html:select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Parent Clause
						<A HREF="javascript:LoadParentClauses()"><span class="glyphicon glyphicon-search" aria-hidden="true" alt="search"></span></A>
						<a href="javascript:deleteParentClause()"><span class="glyphicon glyphicon-remove text-red" aria-hidden="true" alt="remove"></a>
					</label>
					<div class="col-sm-4" >
						<html:hidden styleId="hdnParentClauseSeqNo" property="hdnParentClauseSeqNo" />
						<DIV id="parentclause" style="solid;height:60px;overflow:auto;"></DIV>
					</div>
				</div>
				<div class="form-group required-field">
					<label class="col-sm-4 control-label">Clause Description</label>
					<div class="col-sm-4">
						<html:textarea property="addClauseDescription" rows="9" cols="60" styleId="addClauseDescription" styleClass="form-control tinymceClaDesc"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Table Data
						<DIV name="showmainlink">
							<A CLASS='linkstyleItem' HREF="javascript:addTable('divAddClause',0)">Add Table</A>&nbsp;
						</DIV>
						<DIV name="showsublink"  style="display:none;">
							<A CLASS=linkstyle1 HREF="javascript:addRow(this,'divAddClause')">Add Row</A>&nbsp;|&nbsp;
							<A CLASS=linkstyle1 HREF="javascript:removeRow('divAddClause')">Delete Row</A><br>
							<A CLASS='linkstyle1' HREF="javascript:deleteTable('divAddClause')">Delete Table</A>&nbsp;
						</DIV>
					</label>	
					<div class="col-sm-8"> 
						<DIV name="showgrid"></DIV>
					</div>			
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Component Tied To Clause</label>
					<div class="col-sm-4 form-inline">
						 <html:select property="component" multiple="true" styleClass="form-control"></html:select>
						 <a HREF="javascript:AddComponent()"><span class="glyphicon glyphicon-search" aria-hidden="true" alt="search"></span></A>
						 <a href="javascript:deleteComponent()"><span class="glyphicon glyphicon-remove text-red" aria-hidden="true" alt="remove"></a>
					</div>
				</div>
				
				<div class="panel panel-default col-sm-offset-1" style="width:90%;align:center;">
		   			<div class="panel-heading"><h3 class="panel-title text-center">Engineering Data <font color="red" valign="top">*</font></h3></div>
	   				<div class="panel-body">
	   					<div class="form-group">
							<label class="col-sm-3 control-label">Reference EDL Number(s)</label>
							<div>
								<div class="col-sm-2"><html:text styleClass="form-control" size="20" property="refEdlNoAdd" maxlength="20" value="" /></div>
								<div class="col-sm-2"><html:text styleClass="form-control" size="20" property="refEdlNoAdd" maxlength="20" value="" /></div>
								<div class="col-sm-2"><html:text styleClass="form-control" size="20" property="refEdlNoAdd" maxlength="20" value="" /></div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">New EDL Number(s)</label>
							<div>
								<div class="col-sm-2"><html:text styleClass="form-control" size="20" property="newEdlNoAdd" maxlength="20" value="" /></div>
								<div class="col-sm-2"><html:text styleClass="form-control" size="20" property="newEdlNoAdd" maxlength="20" value="" /></div>
								<div class="col-sm-2"><html:text styleClass="form-control" size="20" property="newEdlNoAdd" maxlength="20" value="" /></div>
							</div>
						</div>
	   					<div class="form-group">
							<label class="col-sm-3 control-label">Part Of </label>
							<div class="col-sm-2 form-inline">
								<div class="input-group">
									<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
										<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(5)"><span class="glyphicon glyphicon-search"></span></button>
									</span>
									<html:text styleClass="form-control" styleId="partOf1" property="partOf" readonly="true" value="" />				
									<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
										<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(5)"><span class="glyphicon glyphicon-remove"></span></button>
									</span>
								</div>
								<html:hidden property="partOfSeqNo" value="" /> 
								<html:hidden property="clauseSeqNum" value="" />
							</div>
							<div class="col-sm-2 form-inline">
								<div class="input-group">
									<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
										<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(6)"><span class="glyphicon glyphicon-search"></span></button>
									</span>
									<html:text styleClass="form-control" styleId="partOf2" property="partOf" readonly="true" value="" />				
									<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
										<button class="clearbtn btn btn-default" type="button" id="clearpartOf2" onclick="javascript:deletePartOf(6)"><span class="glyphicon glyphicon-remove"></span></button>
									</span>
								</div>
								<html:hidden property="partOfSeqNo" value="" /> 
								<html:hidden property="clauseSeqNum" value="" />
							</div>
							<div class="col-sm-2 form-inline">
								<div class="input-group">
									<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
										<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(7)"><span class="glyphicon glyphicon-search"></span></button>
									</span>
									<html:text styleClass="form-control" styleId="partOf3" property="partOf" readonly="true" value="" />				
									<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
										<button class="clearbtn btn btn-default" type="button" id="clearpartOf1" onclick="javascript:deletePartOf(7)"><span class="glyphicon glyphicon-remove"></span></button>
									</span>
								</div>
								<html:hidden property="partOfSeqNo" value="" /> 
								<html:hidden property="clauseSeqNum" value="" />
							</div>
							<div class="col-sm-2 form-inline">
								<div class="input-group">
									<span class="input-group-btn"  data-toggle="tooltip" title="Load Part of">
										<button class="btn btn-default" type="button" data-toggle="modal" data-target="#partofClauseDetails" onclick="javascript:LoadClauses(8)"><span class="glyphicon glyphicon-search"></span></button>
									</span>
									<html:text styleClass="form-control" styleId="partOf4" property="partOf" readonly="true" value="" />				
									<span class="input-group-btn" data-toggle="tooltip" title="Clear Part of">
										<button class="clearbtn btn btn-default" type="button" id="clearpartOf4" onclick="javascript:deletePartOf(8)"><span class="glyphicon glyphicon-remove"></span></button>
									</span>
								</div>
								<html:hidden property="partOfSeqNo" value="" /> 
								<html:hidden property="clauseSeqNum" value="" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">DWO Number </label>
							<div class="col-sm-2">
								<html:text styleClass="form-control" size="20" property="dwoNoAdd" maxlength="20" styleId="dwoNoAdd" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Part Number </label>
							<div class="col-sm-2">
								<html:text styleClass="form-control" size="20" property="partNoAdd" maxlength="8" styleId="partNoAdd" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Price Book Number</label>
							<div class="col-sm-2">
								<html:text styleClass="form-control" size="20" property="priceBookNoAdd" maxlength="4" styleId="priceBookNo"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Standard Equipment</label>
							<div class="col-sm-4">
								<html:select styleClass="form-control" property="standardEquipmentSeqNoAdd" styleId="standardEquipmentSeqNoAdd">
								</html:select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Comments</label>
							<div class="col-sm-4">
								<html:textarea property="commentsAdd" rows="3" cols="60" styleClass="form-control" />
							</div>
						</div>
					</div>
		   		</div>
		   		<div class="spacer10"></div>
		   		<div class="form-group required-field">
					<label class="col-sm-4 control-label">Reason</label>
					<div class="col-sm-4">
						 <html:textarea  property="reasonAdd" styleId="reasonAdd" rows="3" cols="60" styleClass="form-control" />
					</div>
				</div>
				
				<div class="spacer10"></div>
				
				<div class="form-group"	>
					<div class="col-sm-12 text-center" >
						<button class="btn btn-primary" type='button' id="addClause" ONCLICK="javascript:fnCheckSpellnCont('fnSaveAddClause(0,\'divAddClause\')','addClauseDescription')">Save</button>
						<button class="btn btn-primary" type='button' id="addClose">Cancel Clause Change</button>
					</div>
				</div>
			</div>
		</div>
	</DIV>
</logic:notPresent>
