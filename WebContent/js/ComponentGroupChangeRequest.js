function fnCreateChangeRequestID(){
	
	var req = document.forms[0];
	var reqDesc = trim(req.shortDescription.value);
	
	if(reqDesc=="" || reqDesc.length==0){
		var id = '825';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("shortDescription",-1);
		return false;
	}

	if(reqDesc.length > 2000 ){
		var id = '827';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("shortDescription",-1);
		return false;
	}
	
	document.forms[0].action="CreateRequestID.do?method=createRequestID";
	document.forms[0].submit();

}


function loadSection(){

	compGroup = document.forms[0];
	// Modified For CR_80 to add Specification Type Dropdown
	document.forms[0].hdnSpecTypeNo.value= compGroup.specTypeNo.options[compGroup.specTypeNo.selectedIndex].value;
	document.forms[0].hdnModelSeqNo.value= compGroup.modelSeqNo.options[compGroup.modelSeqNo.selectedIndex].value;
	document.forms[0].hdnSecSeqNo.value= compGroup.sectionSeqNo.options[compGroup.sectionSeqNo.selectedIndex].value;
	document.forms[0].hdnSubSecSeqNo.value= compGroup.subSectionSeqNo.options[compGroup.subSectionSeqNo.selectedIndex].value;
	document.forms[0].action="ComponentGroupRequest.do?method=fetchSections";
	document.forms[0].submit();
}

function loadSubSection(){

	compGroup = document.forms[0];
	// Modified For CR_80 to add Specification Type Dropdown
	document.forms[0].hdnSpecTypeNo.value= compGroup.specTypeNo.options[compGroup.specTypeNo.selectedIndex].value;
	document.forms[0].hdnModelSeqNo.value= compGroup.modelSeqNo.options[compGroup.modelSeqNo.selectedIndex].value;
	document.forms[0].hdnSecSeqNo.value= compGroup.sectionSeqNo.options[compGroup.sectionSeqNo.selectedIndex].value;
	document.forms[0].hdnSubSecSeqNo.value= compGroup.subSectionSeqNo.options[compGroup.subSectionSeqNo.selectedIndex].value;
	document.forms[0].action="ComponentGroupRequest.do?method=fetchSubSections ";
	document.forms[0].submit();
}

function loadAvailableCompGroups(){

	compGroup = document.forms[0];
	// Modified For CR_80 to add Specification Type Dropdown
	document.forms[0].hdnSpecTypeNo.value= compGroup.specTypeNo.options[compGroup.specTypeNo.selectedIndex].value;
	document.forms[0].hdnModelSeqNo.value= compGroup.modelSeqNo.options[compGroup.modelSeqNo.selectedIndex].value;
	document.forms[0].hdnSecSeqNo.value= compGroup.sectionSeqNo.options[compGroup.sectionSeqNo.selectedIndex].value;
	document.forms[0].hdnSubSecSeqNo.value= compGroup.subSectionSeqNo.options[compGroup.subSectionSeqNo.selectedIndex].value;

	document.forms[0].action="ComponentGroupRequest.do?method=fetchCompGrps";
	document.forms[0].submit();
}

function loadAvailableComponents(){
	
	compGroup = document.forms[0];
	// Modified For CR_80 to add Specification Type Dropdown
	document.forms[0].hdnSpecTypeNo.value= compGroup.specTypeNo.options[compGroup.specTypeNo.selectedIndex].value;
	document.forms[0].hdnModelSeqNo.value= compGroup.modelSeqNo.options[compGroup.modelSeqNo.selectedIndex].value;
	document.forms[0].hdnSecSeqNo.value= compGroup.sectionSeqNo.options[compGroup.sectionSeqNo.selectedIndex].value;
	document.forms[0].hdnSubSecSeqNo.value= compGroup.subSectionSeqNo.options[compGroup.subSectionSeqNo.selectedIndex].value;

	document.forms[0].action="ComponentGroupRequest.do?method=fetchComps";
	document.forms[0].submit();
}

function loadSelectedComponent(){

	compGroup = document.forms[0];
	// Modified For CR_80 to add Specification Type Dropdown
	document.forms[0].hdnSpecTypeNo.value= compGroup.specTypeNo.options[compGroup.specTypeNo.selectedIndex].value;
	document.forms[0].hdnModelSeqNo.value= compGroup.modelSeqNo.options[compGroup.modelSeqNo.selectedIndex].value;
	document.forms[0].hdnSecSeqNo.value= compGroup.sectionSeqNo.options[compGroup.sectionSeqNo.selectedIndex].value;
	document.forms[0].hdnSubSecSeqNo.value= compGroup.subSectionSeqNo.options[compGroup.subSectionSeqNo.selectedIndex].value;

	document.forms[0].hdnCompGrpSeqNo.value = document.forms[0].compGrpSeqNo.options[document.forms[0].compGrpSeqNo.selectedIndex].value;
	document.forms[0].action="ComponentGroupRequest.do?method=loadSelectedComponent";
	document.forms[0].submit();
}

function saveRequestComponentGroup(){

	
	var compGroup = document.forms[0];
	var newCompGroupName = trim(compGroup.newCompGrpName.value);
	var newCompName = trim(compGroup.newCompName.value);
	var reason = trim(compGroup.reason.value);

	var hdnStatusSeqNo = compGroup.hdnStatusTypeSeqNo.value;
	var adminComments=trim(compGroup.adminComments.value);
	compGroup.hdnAdminComments.value=adminComments;
	var roleID=compGroup.roleID.value;

	//Do Validation here

	var requestDesc=trim(compGroup.requestDesc.value);
	compGroup.requestDesc.value=requestDesc;
		
			if(requestDesc=="" || requestDesc.length==0){
				var id = '825';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("requestDesc",-1);
				return false;
				}
	if(requestDesc.length > 2000 ){
		var id = '827';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("requestDesc",-1);
		return false;
	}
	// Added For CR_80 to add Specification Type Dropdown
	if(compGroup.specTypeNo.options[compGroup.specTypeNo.selectedIndex].value =="-1"){
	
		var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("specTypeNo",-1);
		return false;
	
	}
	// Added For CR_80 to add Specification Type Dropdown - Ends here
	if(compGroup.modelSeqNo.options[compGroup.modelSeqNo.selectedIndex].value =="-1"){
	
		var id = '19';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("modelSeqNo",-1);
		return false;
	
	}

	if(compGroup.sectionSeqNo.options[compGroup.sectionSeqNo.selectedIndex].value =="-1"){
	
		var id = '205';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sectionSeqNo",-1);
		return false;
	
	}

	if(compGroup.subSectionSeqNo.options[compGroup.subSectionSeqNo.selectedIndex].value =="-1"){
	
		var id = '182';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("subSectionSeqNo",-1);
		return false;
	
	}
	//Modified for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151
	if(compGroup.compGroupChangeTypeSeqNo[2].checked || compGroup.compGroupChangeTypeSeqNo[3].checked 
			|| compGroup.compGroupChangeTypeSeqNo[4].checked){
	
		if(compGroup.compGrpSeqNo.options[compGroup.compGrpSeqNo.selectedIndex].value =="-1"){
		
			var id = '850';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("compGrpSeqNo",-1);
			return false;
			
		}
	
	}

	if(compGroup.compGroupChangeTypeSeqNo[1].checked || compGroup.compGroupChangeTypeSeqNo[2].checked 
			|| compGroup.compGroupChangeTypeSeqNo[3].checked)	{
	if(newCompGroupName=="" || newCompGroupName.length==0){
	
		var id = '173';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("newCompGrpName",-1);
		return false;
		}
	}
	

	if((compGroup.compGroupChangeTypeSeqNo[1].checked && compGroup.compChangeTypeSeqNo[2].checked) ||(compGroup.compGroupChangeTypeSeqNo[1].checked && compGroup.compChangeTypeSeqNo[3].checked)
			||(compGroup.compGroupChangeTypeSeqNo[1].checked && compGroup.compChangeTypeSeqNo[4].checked)){
	
		var id = '824';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("compChangeTypeSeqNo",-1);
		return false;
	
	}
	
	if(/*(compGroup.compGroupChangeTypeSeqNo[4].checked && !(compGroup.compChangeTypeSeqNo[0].checked)) || */(compGroup.compGroupChangeTypeSeqNo[0].checked && !(compGroup.compChangeTypeSeqNo[0].checked))){
	
		var id = '866';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("compChangeTypeSeqNo",-1);
		return false;
	
	}	
	
	
	if(compGroup.compChangeTypeSeqNo[2].checked || compGroup.compChangeTypeSeqNo[3].checked || compGroup.compChangeTypeSeqNo[4].checked){
	
		if(compGroup.compSeqNo.options[compGroup.compSeqNo.selectedIndex].value =="-1"){
		
			var id = '851';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("compSeqNo",-1);
			return false;
			
		}
	
	}

	if(compGroup.compChangeTypeSeqNo[1].checked || compGroup.compChangeTypeSeqNo[2].checked 
			|| compGroup.compChangeTypeSeqNo[3].checked)	{

		if(newCompName=="" || newCompName.length==0){
			
			var id = '822';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("newCompName",-1);
			return false;
		
			}
	}
	
	if(reason=="" || reason.length==0){
	
		var id = '128';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("reason",-1);
		return false;
		
	}

	if(reason.length > 4000 ){
		var id = '826';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("reason",-1);
		return false;
	}
	// Modified For CR_80 to add Specification Type Dropdown
	compGroup.hdnSpecTypeNo.value= compGroup.specTypeNo.options[compGroup.specTypeNo.selectedIndex].value;
	compGroup.hdnModelSeqNo.value= compGroup.modelSeqNo.options[compGroup.modelSeqNo.selectedIndex].value;
	compGroup.hdnSecSeqNo.value= compGroup.sectionSeqNo.options[compGroup.sectionSeqNo.selectedIndex].value;
	compGroup.hdnSubSecSeqNo.value= compGroup.subSectionSeqNo.options[compGroup.subSectionSeqNo.selectedIndex].value;
	compGroup.hdnAdminComments.value=compGroup.adminComments.value;

	document.forms[0].hdnCompGrpSeqNo.value = document.forms[0].compGrpSeqNo.options[document.forms[0].compGrpSeqNo.selectedIndex].value;
	document.forms[0].hdnCompSeqNo.value = document.forms[0].compSeqNo.options[document.forms[0].compSeqNo.selectedIndex].value;
	//Old Component Group name
	compGroup.hdnOldCompGrpName.value=compGroup.oldCompGrpName.value;

	for(var i = 0; i< compGroup.oldCompGrpChacFlag.length;i++){
		
		if(compGroup.oldCompGrpChacFlag[i].checked){
		
			//Old Comp Group Charaterization flag
			compGroup.hdnOldCompGrpChacFlag.value=compGroup.oldCompGrpChacFlag[i].value;
			break;
			
		}
	}

	for(var i = 0; i< compGroup.oldCompGrpValidFlag.length;i++){
		
		if(compGroup.oldCompGrpValidFlag[i].checked){
		
			//Old Comp Group Validation flag
			compGroup.hdnOldCompGrpValidFlag.value=compGroup.oldCompGrpValidFlag[i].value;
			break;
			
		}
	}


	//New Component Group name
	compGroup.hdnNewCompGrpName.value=compGroup.newCompGrpName.value;

	for(var i = 0; i< compGroup.newCompGrpChacFlag.length;i++){
		
		if(compGroup.newCompGrpChacFlag[i].checked){
		
			//New Comp Group Charaterization flag
			compGroup.hdnNewCompGrpChacFlag.value=compGroup.newCompGrpChacFlag[i].value;
			break;
			
		}
	}

	for(var i = 0; i< compGroup.newCompGrpValidFlag.length;i++){
		
		if(compGroup.newCompGrpValidFlag[i].checked){
		
			//New Comp Group Validation flag
			compGroup.hdnNewCompGrpValidFlag.value=compGroup.newCompGrpValidFlag[i].value;
			break;
			
		}
	}


	//Old Component Name
	compGroup.hdnOldCompName.value = compGroup.oldCompName.value;


	for(var i = 0; i< compGroup.oldCompDefaultFlag.length;i++){
		
		if(compGroup.oldCompDefaultFlag[i].checked){
		
			//New Comp Group Validation flag
			compGroup.hdnOldCompDefaultFlag.value=compGroup.oldCompDefaultFlag[i].value;
			break;
			
		}
	}


	//New Component Name
	compGroup.hdnNewCompName.value = compGroup.newCompName.value;


	for(var i = 0; i< compGroup.newCompDefaultFlag.length;i++){
		
		if(compGroup.newCompDefaultFlag[i].checked){
		
			//New Comp Group Validation flag
			compGroup.hdnNewCompDefaultFlag.value=compGroup.newCompDefaultFlag[i].value;
			break;
			
		}
	}
	/*Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
		
	if(hdnStatusSeqNo == 2){
		if(roleID=="ADMN"){
	
			if(adminComments=="" || adminComments.length==0){
					var id = '858';
					hideErrors();
					addMsgNum(id);
					showErrors();
					return false;
				}*/
			if(adminComments.length >4000)
				{
				var id = '859';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("adminComments",-1);
				//compGroup.adminComments.select();
				//compGroup.adminComments.focus();
				return false;
				}
		/*	}
	}*/
	document.forms[0].action="saveRequestComponentGroup.do?method=saveRequestComponentGroup";
	document.forms[0].submit();
}


function EnableDisableCompGroup(){

	var compGroup = document.forms[0];
	//Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
	if(compGroup.compGroupChangeTypeSeqNo[1].checked){//New

		compGroup.btnEffItemsComGrp.disabled = true;	
		
		compGroup.compGrpSeqNo.options[0].selected =true;
		compGroup.oldCompGrpName.value="";
		compGroup.newCompGrpName.value="";
		
		compGroup.oldCompGrpChacFlag[0].checked=false;
		compGroup.oldCompGrpChacFlag[1].checked=false;
		compGroup.oldCompGrpValidFlag[0].checked=false;
		compGroup.oldCompGrpValidFlag[1].checked=false;
		compGroup.newCompGrpChacFlag[0].checked=false;
		compGroup.newCompGrpChacFlag[1].checked=false;
		compGroup.newCompGrpValidFlag[0].checked=false;
		compGroup.newCompGrpValidFlag[1].checked=false;
		
		compGroup.newCompGrpName.value="";
		compGroup.compGrpSeqNo.disabled=true;
		compGroup.oldCompGrpName.disabled=true;
		compGroup.oldCompGrpChacFlag[0].disabled=true;
		compGroup.oldCompGrpChacFlag[1].disabled=true;
		compGroup.oldCompGrpValidFlag[0].disabled=true;
		compGroup.oldCompGrpValidFlag[1].disabled=true;

		//disabling available components dropdown, Existin and Revise
		if(compGroup.compSeqNo.length >1){
		
			compGroup.compSeqNo.length=1;
			compGroup.compSeqNo.options[0].selected=true;
			//compGroup.compSeqNo.disabled=true;
		}
		
		compGroup.compChangeTypeSeqNo[0].disabled=false;
		compGroup.compChangeTypeSeqNo[1].disabled=false;
		compGroup.compChangeTypeSeqNo[2].disabled=true;
		compGroup.compChangeTypeSeqNo[3].disabled=true;
		compGroup.compChangeTypeSeqNo[4].disabled=true;
		compGroup.compChangeTypeSeqNo[0].checked=true;

		compGroup.compSeqNo.disabled=true;
		
		compGroup.oldCompName.value="";
		compGroup.oldCompName.disabled=true;
		compGroup.oldCompDefaultFlag[0].disabled=true;
		compGroup.oldCompDefaultFlag[1].disabled=true;
		
		if(compGroup.oldCompDefaultFlag[0].checked)
			compGroup.oldCompDefaultFlag[0].checked=false;
		if(compGroup.oldCompDefaultFlag[1].checked)
			compGroup.oldCompDefaultFlag[1].checked=false;
		
		compGroup.newCompName.value="";
		compGroup.newCompName.disabled=true;
		compGroup.newCompDefaultFlag[0].disabled=true;
		compGroup.newCompDefaultFlag[1].disabled=true;
	

		//Ends

		compGroup.newCompGrpName.disabled=false;
		compGroup.newCompGrpChacFlag[0].disabled=false;
		compGroup.newCompGrpChacFlag[1].disabled=false;
		compGroup.newCompGrpChacFlag[1].checked=true;
		compGroup.newCompGrpValidFlag[0].disabled=false;
		compGroup.newCompGrpValidFlag[1].disabled=false;
		compGroup.newCompGrpValidFlag[1].checked=true;

	
	}else if(compGroup.compGroupChangeTypeSeqNo[2].checked){//Existing

		compGroup.btnEffItemsComGrp.disabled = true;
		compGroup.compGrpSeqNo.options[0].selected =true;
		compGroup.oldCompGrpName.value="";
		compGroup.newCompGrpName.value="";
		compGroup.oldCompGrpChacFlag[0].checked=false;
		compGroup.oldCompGrpChacFlag[1].checked=false;
		compGroup.oldCompGrpValidFlag[0].checked=false;
		compGroup.oldCompGrpValidFlag[1].checked=false;
		compGroup.newCompGrpChacFlag[0].checked=false;
		compGroup.newCompGrpChacFlag[1].checked=false;
		compGroup.newCompGrpValidFlag[0].checked=false;
		compGroup.newCompGrpValidFlag[1].checked=false;

		compGroup.compGrpSeqNo.disabled=false;
		compGroup.oldCompGrpName.disabled=true;
		compGroup.oldCompGrpChacFlag[0].disabled=true;
		compGroup.oldCompGrpChacFlag[1].disabled=true;
		compGroup.oldCompGrpValidFlag[0].disabled=true;
		compGroup.oldCompGrpValidFlag[1].disabled=true;

		compGroup.compChangeTypeSeqNo[0].disabled=true;
		compGroup.compChangeTypeSeqNo[1].disabled=false;
		compGroup.compChangeTypeSeqNo[1].checked=true;
		compGroup.compChangeTypeSeqNo[2].disabled=false;
		compGroup.compChangeTypeSeqNo[3].disabled=false;
		compGroup.compChangeTypeSeqNo[4].disabled=false;

		if(compGroup.compSeqNo.length >1){
		
			compGroup.compSeqNo.length=1;
			compGroup.compSeqNo.options[0].selected=true;
			compGroup.compSeqNo.disabled=true;
		}
		compGroup.compSeqNo.disabled=true;
		compGroup.newCompGrpName.disabled=true;
		compGroup.newCompGrpChacFlag[0].disabled=true;
		compGroup.newCompGrpChacFlag[1].disabled=true;
		compGroup.newCompGrpValidFlag[0].disabled=true;
		compGroup.newCompGrpValidFlag[1].disabled=true;

		compGroup.newCompName.value="";
		compGroup.newCompName.disabled=false;
		compGroup.newCompDefaultFlag[0].disabled=false;
		compGroup.newCompDefaultFlag[1].disabled=false;

	}else if(compGroup.compGroupChangeTypeSeqNo[3].checked){//Revise
	      
		compGroup.btnEffItemsComGrp.disabled = true;
		compGroup.compGrpSeqNo.options[0].selected =true;
		compGroup.oldCompGrpName.value="";
		compGroup.newCompGrpName.value="";
		compGroup.oldCompGrpChacFlag[0].checked=false;
		compGroup.oldCompGrpChacFlag[1].checked=false;
		compGroup.oldCompGrpValidFlag[0].checked=false;
		compGroup.oldCompGrpValidFlag[1].checked=false;
		compGroup.newCompGrpChacFlag[0].checked=false;
		compGroup.newCompGrpChacFlag[1].checked=false;
		compGroup.newCompGrpValidFlag[0].checked=false;
		compGroup.newCompGrpValidFlag[1].checked=false;
		
		
		compGroup.compGrpSeqNo.disabled=false;

		compGroup.oldCompGrpName.disabled=true;
		compGroup.oldCompGrpChacFlag[0].disabled=true;
		compGroup.oldCompGrpChacFlag[1].disabled=true;
		compGroup.oldCompGrpValidFlag[0].disabled=true;
		compGroup.oldCompGrpValidFlag[1].disabled=true;

		compGroup.compChangeTypeSeqNo[0].disabled=false;
		compGroup.compChangeTypeSeqNo[0].checked=true;
		compGroup.compChangeTypeSeqNo[1].disabled=false;
		compGroup.compChangeTypeSeqNo[2].disabled=false;
		compGroup.compChangeTypeSeqNo[3].disabled=false;
		compGroup.compChangeTypeSeqNo[4].disabled=false;

		compGroup.newCompGrpName.disabled=false;
		compGroup.newCompGrpChacFlag[0].disabled=false;
		compGroup.newCompGrpChacFlag[1].disabled=false;
		compGroup.newCompGrpValidFlag[0].disabled=false;
		compGroup.newCompGrpValidFlag[1].disabled=false;

		//disabling available components dropdown, Existin and Revise
		if(compGroup.compSeqNo.length >1){
		
			compGroup.compSeqNo.length=1;
			compGroup.compSeqNo.options[0].selected=true;
			//compGroup.compSeqNo.disabled=true;
		}
		compGroup.compSeqNo.disabled=true;

		compGroup.oldCompName.value="";
		compGroup.oldCompName.disabled=true;
		compGroup.oldCompDefaultFlag[0].disabled=true;
		compGroup.oldCompDefaultFlag[1].disabled=true;
		
		if(compGroup.oldCompDefaultFlag[0].checked)
			compGroup.oldCompDefaultFlag[0].checked=false;
		if(compGroup.oldCompDefaultFlag[1].checked)
			compGroup.oldCompDefaultFlag[1].checked=false;
			
		compGroup.newCompName.value="";
		compGroup.newCompName.disabled=true;
		compGroup.newCompDefaultFlag[0].disabled=true;
		compGroup.newCompDefaultFlag[1].disabled=true;
		compGroup.newCompDefaultFlag[0].checked=false;
		compGroup.newCompDefaultFlag[1].checked=false;
	}
	
		else if(compGroup.compGroupChangeTypeSeqNo[0].checked){//Not Required

		compGroup.btnEffItemsComGrp.disabled = true;
		compGroup.compGrpSeqNo.options[0].selected =true;
		compGroup.oldCompGrpName.value="";
		compGroup.newCompGrpName.value="";
		
		compGroup.oldCompGrpChacFlag[0].checked=false;
		compGroup.oldCompGrpChacFlag[1].checked=false;
		compGroup.oldCompGrpValidFlag[0].checked=false;
		compGroup.oldCompGrpValidFlag[1].checked=false;

		compGroup.newCompGrpChacFlag[0].checked=false;
		compGroup.newCompGrpChacFlag[1].checked=false;
		compGroup.newCompGrpValidFlag[0].checked=false;
		compGroup.newCompGrpValidFlag[1].checked=false;

		compGroup.newCompGrpName.value="";
		compGroup.compGrpSeqNo.disabled=true;
		compGroup.oldCompGrpName.disabled=true;
		compGroup.oldCompGrpChacFlag[0].disabled=true;
		compGroup.oldCompGrpChacFlag[1].disabled=true;
		compGroup.oldCompGrpValidFlag[0].disabled=true;
		compGroup.oldCompGrpValidFlag[1].disabled=true;

		compGroup.newCompGrpName.disabled=true;
		compGroup.newCompGrpChacFlag[0].disabled=true;
		compGroup.newCompGrpChacFlag[1].disabled=true;
		compGroup.newCompGrpValidFlag[0].disabled=true;
		compGroup.newCompGrpValidFlag[1].disabled=true;
		
		//disabling available components dropdown, Existin and Revise
		if(compGroup.compSeqNo.length >1){
		
			compGroup.compSeqNo.length=1;
			compGroup.compSeqNo.options[0].selected=true;
			compGroup.compSeqNo.disabled=true;
		}
		compGroup.compSeqNo.disabled=true;
		compGroup.compChangeTypeSeqNo[0].checked=true;
		compGroup.compChangeTypeSeqNo[0].disabled=false;
		compGroup.compChangeTypeSeqNo[1].disabled=true;
		compGroup.compChangeTypeSeqNo[2].disabled=true;
		compGroup.compChangeTypeSeqNo[3].disabled=true;
		compGroup.compChangeTypeSeqNo[4].disabled=true;

		compGroup.oldCompName.value="";
		compGroup.oldCompName.disabled=true;
		compGroup.oldCompDefaultFlag[0].disabled=true;
		compGroup.oldCompDefaultFlag[1].disabled=true;
		
		compGroup.oldCompDefaultFlag[0].checked=false;
		compGroup.oldCompDefaultFlag[1].checked=false;
		compGroup.newCompDefaultFlag[0].checked=false;
		compGroup.newCompDefaultFlag[1].checked=false;
		
		compGroup.newCompName.value="";
		compGroup.newCompName.disabled=true;
		compGroup.newCompDefaultFlag[0].disabled=true;
		compGroup.newCompDefaultFlag[1].disabled=true;

		//Ends
		
	}

		else if(compGroup.compGroupChangeTypeSeqNo[4].checked){//Delete

		compGroup.btnEffItemsComGrp.disabled = false;
		compGroup.compGrpSeqNo.options[0].selected =true;
		compGroup.oldCompGrpName.value="";
		compGroup.newCompGrpName.value="";
		
		compGroup.oldCompGrpChacFlag[0].checked=false;
		compGroup.oldCompGrpChacFlag[1].checked=false;
		compGroup.oldCompGrpValidFlag[0].checked=false;
		compGroup.oldCompGrpValidFlag[1].checked=false;

		compGroup.newCompGrpChacFlag[0].checked=false;
		compGroup.newCompGrpChacFlag[1].checked=false;
		compGroup.newCompGrpValidFlag[0].checked=false;
		compGroup.newCompGrpValidFlag[1].checked=false;
		
		compGroup.newCompGrpName.value="";
		compGroup.compGrpSeqNo.disabled=false;
		compGroup.oldCompGrpName.disabled=true;
		compGroup.oldCompGrpChacFlag[0].disabled=true;
		compGroup.oldCompGrpChacFlag[1].disabled=true;
		compGroup.oldCompGrpValidFlag[0].disabled=true;
		compGroup.oldCompGrpValidFlag[1].disabled=true;

		compGroup.newCompGrpName.disabled=true;
		compGroup.newCompGrpChacFlag[0].disabled=true;
		compGroup.newCompGrpChacFlag[1].disabled=true;
		compGroup.newCompGrpValidFlag[0].disabled=true;
		compGroup.newCompGrpValidFlag[1].disabled=true;
		
		//disabling available components dropdown, Existin and Revise
		if(compGroup.compSeqNo.length >1){
		
			compGroup.compSeqNo.length=1;
			compGroup.compSeqNo.options[0].selected=true;
			//compGroup.compSeqNo.disabled=true;
		}
		
		compGroup.compSeqNo.disabled=true;
		compGroup.compChangeTypeSeqNo[0].disabled=true;
		compGroup.compChangeTypeSeqNo[1].disabled=true;
		compGroup.compChangeTypeSeqNo[2].disabled=true;
		compGroup.compChangeTypeSeqNo[3].disabled=true;
		compGroup.compChangeTypeSeqNo[4].disabled=true;
		
		compGroup.compChangeTypeSeqNo[0].checked=false;
		compGroup.compChangeTypeSeqNo[1].checked=false;
		compGroup.compChangeTypeSeqNo[2].checked=false;
		compGroup.compChangeTypeSeqNo[3].checked=false;
		compGroup.compChangeTypeSeqNo[4].checked=false;

		compGroup.oldCompName.value="";
		compGroup.oldCompName.disabled=true;
		compGroup.oldCompDefaultFlag[0].disabled=true;
		compGroup.oldCompDefaultFlag[1].disabled=true;
		
		compGroup.oldCompDefaultFlag[0].checked=false;
		compGroup.oldCompDefaultFlag[1].checked=false;
		compGroup.newCompDefaultFlag[0].checked=false;
		compGroup.newCompDefaultFlag[1].checked=false;

		compGroup.newCompName.value="";
		compGroup.newCompName.disabled=true;
		compGroup.newCompDefaultFlag[0].disabled=true;
		compGroup.newCompDefaultFlag[1].disabled=true;

		//Ends
		
	}

}


function EnableDisableComp(){

	var comp = document.forms[0];
	//Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
	if(comp.compChangeTypeSeqNo[1].checked){//New
	
		comp.btnEffItemsComp.disabled = true;
		comp.compSeqNo.options[0].selected=true;
		comp.oldCompName.value="";
		comp.newCompName.value="";
		comp.oldCompDefaultFlag[0].checked=false;
		comp.oldCompDefaultFlag[1].checked=false;
	
		comp.compSeqNo.disabled=true;
		comp.oldCompName.disabled=true;
		comp.oldCompDefaultFlag[0].disabled=true;
		comp.oldCompDefaultFlag[1].disabled=true;
		
		comp.newCompName.value="";
		comp.newCompName.disabled=false;
		comp.newCompDefaultFlag[0].checked=false;
		comp.newCompDefaultFlag[1].checked=true;
		comp.newCompDefaultFlag[0].disabled=false;
		comp.newCompDefaultFlag[1].disabled=false;

	
	}else if(comp.compChangeTypeSeqNo[2].checked){//Existing

		comp.btnEffItemsComp.disabled = true;
		comp.compSeqNo.options[0].selected=true;
		comp.oldCompName.value="";
		comp.newCompName.value="";
		comp.oldCompDefaultFlag[0].checked=false;
		comp.oldCompDefaultFlag[1].checked=false;
		
		comp.compSeqNo.disabled=false;
		comp.oldCompName.disabled=true;
		comp.oldCompDefaultFlag[0].disabled=true;
		comp.oldCompDefaultFlag[1].disabled=true;
		
		comp.newCompName.disabled=true;
		comp.newCompDefaultFlag[0].checked=false;
		comp.newCompDefaultFlag[1].checked=false;	
		comp.newCompDefaultFlag[0].disabled=true;
		comp.newCompDefaultFlag[1].disabled=true;

	}else if(comp.compChangeTypeSeqNo[3].checked){//Revise

		comp.btnEffItemsComp.disabled = true;
		comp.compSeqNo.options[0].selected=true;
		comp.oldCompName.value="";
		comp.newCompName.value="";
		comp.oldCompDefaultFlag[0].checked=false;
		comp.oldCompDefaultFlag[1].checked=false;
	
		
		comp.compSeqNo.disabled=false;
		comp.oldCompName.disabled=true;
		comp.oldCompDefaultFlag[0].disabled=true;
		comp.oldCompDefaultFlag[1].disabled=true;
		

		comp.newCompName.disabled=false;
		comp.newCompDefaultFlag[0].checked=false;
		comp.newCompDefaultFlag[1].checked=false;
		comp.newCompDefaultFlag[0].disabled=false;
		comp.newCompDefaultFlag[1].disabled=false;

	}
	
	else if(comp.compChangeTypeSeqNo[0].checked){//Not Required

		comp.btnEffItemsComp.disabled = true;
		comp.compSeqNo.options[0].selected=true;
		comp.oldCompName.value="";
		comp.newCompName.value="";
		comp.oldCompDefaultFlag[0].checked=false;
		comp.oldCompDefaultFlag[1].checked=false;
	
		comp.compSeqNo.disabled=true;
		comp.oldCompName.disabled=true;
		comp.oldCompDefaultFlag[0].disabled=true;
		comp.oldCompDefaultFlag[1].disabled=true;
		
		comp.newCompName.value="";
		comp.newCompName.disabled=true;
		comp.newCompDefaultFlag[0].checked=false;
		comp.newCompDefaultFlag[1].checked=false;
		comp.newCompDefaultFlag[0].disabled=true;
		comp.newCompDefaultFlag[1].disabled=true;
	}
	
	else if(comp.compChangeTypeSeqNo[4].checked){//Delete

		comp.btnEffItemsComp.disabled = false;
		comp.compSeqNo.options[0].selected=true;
		comp.oldCompName.value="";
		comp.newCompName.value="";
		comp.oldCompDefaultFlag[0].checked=false;
		comp.oldCompDefaultFlag[1].checked=false;
	
		comp.compSeqNo.disabled=false;
		comp.oldCompName.disabled=true;
		comp.oldCompDefaultFlag[0].disabled=true;
		comp.oldCompDefaultFlag[1].disabled=true;
		
		comp.newCompName.value="";
		comp.newCompName.disabled=true;
		comp.newCompDefaultFlag[0].checked=false;
		comp.newCompDefaultFlag[1].checked=false;
		comp.newCompDefaultFlag[0].disabled=true;
		comp.newCompDefaultFlag[1].disabled=true;

	}
}


function resetRequest()
{
	compGroup = document.forms[0];

	var requestDesc=trim(compGroup.requestDesc.value);
	compGroup.requestDesc.value=requestDesc;
		
	if(requestDesc=="" || requestDesc.length==0){
		var id = '825';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("requestDesc",-1);
		return false;
		}

	if(requestDesc.length > 2000 ){
		var id = '827';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("requestDesc",-1);
		return false;
	}

	var conf = window.confirm("THIS COMMAND WILL CLEAR THE ENTIRE FORM. Are you sure you want to proceed ?");
	if(conf){
		
		document.forms[0].hdnModelSeqNo.value= compGroup.modelSeqNo.options[compGroup.modelSeqNo.selectedIndex].value;
		document.forms[0].hdnSecSeqNo.value= compGroup.sectionSeqNo.options[compGroup.sectionSeqNo.selectedIndex].value;
		document.forms[0].hdnSubSecSeqNo.value= compGroup.subSectionSeqNo.options[compGroup.subSectionSeqNo.selectedIndex].value;

		document.forms[0].action="resetRequest.do?method=resetRequest";
		document.forms[0].submit();
	}else{
	
		return false;
	}

}

function saveRequestStatus(statusid){

	var flag = false;
	var conf;
	compGroup = document.forms[0];
	var adminComments=trim(compGroup.adminComments.value);
	compGroup.hdnAdminComments.value=adminComments;
	
	var requestDesc=trim(compGroup.requestDesc.value);
	compGroup.requestDesc.value=requestDesc;
		
	if(requestDesc=="" || requestDesc.length==0){
		var id = '825';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("requestDesc",-1);
		return false;
		}

	if(requestDesc.length > 2000 ){
		var id = '827';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("requestDesc",-1);
		return false;
	}

	if(statusid ==2){//submitted status
		conf = window.confirm("Are you sure want to Submit the request?");
		if(conf){
			flag = true;
		}
	
	}else if(statusid ==3){//on hold status
	
		if(adminComments=="" || adminComments.length==0){
							var id = '858';
							hideErrors();
							addMsgNum(id);
							showScrollErrors("adminComments",-1);
							return false;
						}
						 if(adminComments.length >4000)
							{
							var id = '859';
							hideErrors();
							addMsgNum(id);
							showScrollErrors("adminComments",-1);
							return false;
							}
		conf = window.confirm("Are you sure want to OnHold the request?");
		if(conf){
			flag = true;
		}
	}else if(statusid ==4){//Approve status
	
		conf = window.confirm("Are you sure want to Approve the request?");
		if(conf){
			flag = true;
		}
	}else if(statusid ==5){//Reject status
		/*Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
		if(adminComments=="" || adminComments.length==0){
							var id = '858';
							hideErrors();
							addMsgNum(id);
							showErrors();
							return false;
						}*/
						 if(adminComments.length >4000)
							{
							var id = '859';
							hideErrors();
							addMsgNum(id);
							showScrollErrors("adminComments",-1);
							return false;
							}
	
		conf = window.confirm("Are you sure want to Reject the request?");
		if(conf){
			flag = true;
		}
	}else if(statusid ==6){//Complete status
	
		conf = window.confirm("Are you sure want to Complete the request?");
		if(conf){
			flag = true;
		}
	}
	else if(statusid ==0){
						
						
							var id = '862';
							hideErrors();
							addMsgNum(id);
							showScrollErrors("btnComplete",-1);
							return false;
	
}
	
	if(flag){
		document.forms[0].hdnModelSeqNo.value= compGroup.modelSeqNo.options[compGroup.modelSeqNo.selectedIndex].value;
		document.forms[0].hdnSecSeqNo.value= compGroup.sectionSeqNo.options[compGroup.sectionSeqNo.selectedIndex].value;
		document.forms[0].hdnSubSecSeqNo.value= compGroup.subSectionSeqNo.options[compGroup.subSectionSeqNo.selectedIndex].value;
		
		document.forms[0].action="saveRequestStatus.do?method=saveRequestStatus&statusid="+statusid;
		document.forms[0].submit();
	}else{
	
		return false;
	}

}


function fnSubmitPreview(requestId){

	window.open("PreviewRequestAction.do?method=initLoad&reqID="+requestId,"preview","location=0,resizable=yes ,status=0,scrollbars=1,WIDTH=950,height=500");
}

function enableDisableRequestModel(savedstate)
{
	
	if(savedstate =="Y"){
		document.forms[0].modelSeqNo.disabled =true;
		document.forms[0].sectionSeqNo.disabled =true;
		document.forms[0].subSectionSeqNo.disabled =true;
		//Added For CR_80 for Specification DropDown
		document.forms[0].specTypeNo.disabled =true;
		//document.forms[0].applyModelFlag.disabled =false;
	}
}

function clauseView(requestId){

	//Modified for CR_80 CR Form Enhancements III on 23-Nov-09 by RR68151
	if ((document.forms[0].compChangeTypeSeqNo[4].checked) || (document.forms[0].compGroupChangeTypeSeqNo[4].checked)){
	
			alert("Please select an option other than Delete Component Group/Component to select the Clause Change Request form");
			return false;
		
		}
		else {
		var conf = window.confirm("Would you like to save the data in the current page ?");
		if(conf){
			    
				//Added for including Save function from alert
			    document.forms[0].alertFlag.value="Y";
				
				saveRequestComponentGroup();
		}else{
				document.forms[0].action="CreateChangeReqClauseAction.do?method=initLoad&reqID="+requestId;
				document.forms[0].submit();
		}
	}
}

//Added for CR-61
function fnRefresh(){
var requestId = document.forms[0].hdnReqID.value;
document.forms[0].action="searchRequestComponentGroup.do?method=fetchComponentGroupDetails&reqID="+requestId;
document.forms[0].submit();
}

//Added for CR_80
function fnViewCompMapping(){

  var compMapForm = document.forms[0];
  
  	if ((compMapForm.compGroupChangeTypeSeqNo[4].checked) || (compMapForm.compChangeTypeSeqNo[4].checked))	{
	// Added For CR_80 to add Specification Type Dropdown
	if(compMapForm.specTypeNo.options[compMapForm.specTypeNo.selectedIndex].value =="-1"){
	
		var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("specTypeNo",-1);
		return false;
	
	}
	// Added For CR_80 to add Specification Type Dropdown - Ends here  	
	if(compMapForm.modelSeqNo.options[compMapForm.modelSeqNo.selectedIndex].value =="-1"){
	
		var id = '19';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("modelSeqNo",-1);
		return false;
	
	}
	
	if(compMapForm.sectionSeqNo.options[compMapForm.sectionSeqNo.selectedIndex].value =="-1"){
	
		var id = '205';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sectionSeqNo",-1);
		return false;
	
	}

	if(compMapForm.subSectionSeqNo.options[compMapForm.subSectionSeqNo.selectedIndex].value =="-1"){
	
		var id = '182';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("subSectionSeqNo",-1);
		return false;
	
	}
  
	if(compMapForm.compGrpSeqNo.options[compMapForm.compGrpSeqNo.selectedIndex].value =="-1"){
	
		var id = '850';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("compGrpSeqNo",-1);
		return false;
	}
	
	if(compMapForm.compChangeTypeSeqNo[4].checked){
	
		if(compMapForm.compSeqNo.options[compMapForm.compSeqNo.selectedIndex].value =="-1"){
		
			var id = '851';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("compSeqNo",-1);
			return false;
			
		}

	}
	
	document.forms[0].hdnModelSeqNo.value= compMapForm.modelSeqNo.options[compMapForm.modelSeqNo.selectedIndex].value;
	document.forms[0].hdnSecSeqNo.value= compMapForm.sectionSeqNo.options[compMapForm.sectionSeqNo.selectedIndex].value;
	document.forms[0].hdnSubSecSeqNo.value= compMapForm.subSectionSeqNo.options[compMapForm.subSectionSeqNo.selectedIndex].value;
  	document.forms[0].hdnCompGrpSeqNo.value=compMapForm.compGrpSeqNo.options[compMapForm.compGrpSeqNo.selectedIndex].value;
  	document.forms[0].hdnCompSeqNo.value=compMapForm.compSeqNo.options[compMapForm.compSeqNo.selectedIndex].value;
  	if (document.forms[0].hdnCompSeqNo.value < 0)
  	document.forms[0].hdnCompSeqNo.value = 0;
  	window.open("ComponentGroupRequest.do?method=fetchEffectedClauses&hdnModelSeqNo="+document.forms[0].hdnModelSeqNo.value+"&hdnCompGrpSeqNo="+document.forms[0].hdnCompGrpSeqNo.value+"&hdnCompSeqNo="+document.forms[0].hdnCompSeqNo.value+"","EffectedItems","location=0,resizable=no,status=0,scrollbars=1,width=900,height=600");
    
    }
    
    else	{
    
		var id = '867';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("compGroupChangeTypeSeqNo",-1);
		return false;

    }
  	
}

/*
* Name: fnLoadModels
*    Purpose: Used to load the Models on chage of Specificationtype.
*    Added for LSDB_CR-80 specification drop down
*/

function fnLoadModels()
{
	compGroup = document.forms[0];
	document.forms[0].hdnSpecTypeNo.value= compGroup.specTypeNo.options[compGroup.specTypeNo.selectedIndex].value;
	document.forms[0].action="ComponentGroupRequest.do?method=fetchModels";
	document.forms[0].submit();
}