function fnAddModel(){

	spectypeCheck = false;
	var mod = document.forms[0];
	var modelname = trim(mod.modName.value);
	var horsePower = trim(mod.horsePower.value);
	var modDesc = trim(mod.modDesc.value);

	 if (document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1")
	  {
			var id = '2';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("sltSpecType",-1);
			return false;
	   
		}
	
	//Commented for LSDB_CR_56 
	/*if((document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text=="Locomotive") ){

		if(!mod.modCustTypeSeqNo[0].checked && !mod.modCustTypeSeqNo[1].checked)
		{
			var id = '3';
			hideErrors();
			addMsgNum(id);
			showErrors();
			return false;
		}
	}
	*/
	
	if(modelname.length==0 || modelname==""){
	
		var id = '21';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("modName",-1);
		return false;

	}else{

		/*var isSpecChar = SpecialCharacterCheck(trim(mod.modName.value));


		if(isSpecChar){
			
			var id = '21';
			hideErrors();
			addMsgNum(id);
			showErrors();
			mod.modName.focus();
			return false;
		
		}*/
	
	}
	if(horsePower.length==0 || horsePower=="" ){
	
		var id = '12';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("horsePower",-1);
		return false;
	}

if(modDesc.length > 2000 ){
		
	
		var id = '4';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("modDesc",-1);
		return false;
	}


//Un Comment this
	/*if(mod.modDesc.value.length==0 || mod.modDesc.value=="" ){
		alert("empty desc");
	
		var id = 'LSDB14';
		hideErrors();
		addMsgNum(id);
		showErrors();
		return false;
	}
*/
	document.forms[0].hdnSpecTypeName.value = document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
	document.forms[0].action="ModelAction.do?method=insertModel";
	document.forms[0].submit();

}

function fnSearchModel(){

	var mod = document.forms[0];
	if (document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1")
   {
	 
		var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltSpecType",-1);
		return false;
   
	}

   //Commented for LSDB_CR_56 
	/*if((document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text=="Locomotive") ){

		if(!mod.modCustTypeSeqNo[0].checked && !mod.modCustTypeSeqNo[1].checked)
		{
			var id = '3';
			hideErrors();
			addMsgNum(id);
			showErrors();
			return false;
		}
	}
	*/
	
	document.forms[0].hdnSpecTypeName.value = document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
	document.forms[0].action="ModelAction.do?method=fetchModels";
	document.forms[0].submit();

}

function fnModifyModel()
{


	var mod = document.forms[0];
	flag = false;
	var index;
	modchecked = false;


	if(mod.hdnPrevSelSpec.value!=mod.specTypeNo.options[mod.specTypeNo.selectedIndex].value){
		
		var id= '207';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("SearchButton",-1);
		return false;
	}
	
	//Commented for LSDB_CR_56 
	/*if(mod.modCustTypeSeqNo[0].checked){

		if(mod.hdnPrevSelCusSeq.value != mod.modCustTypeSeqNo[0].value){
					
			var id= '207';
			hideErrors();
			addMsgNum(id);
			showErrors();
			return false;

		}
	
	}else if(mod.modCustTypeSeqNo[1].checked){
	
		
		if(mod.hdnPrevSelCusSeq.value != mod.modCustTypeSeqNo[1].value){
		
			var id= '207';
			hideErrors();
			addMsgNum(id);
			showErrors();
			return false;

		}
	
	}
	*/
	

	if(mod.modelSeqNo.length!=undefined){

	for(var i = 0 ; i < mod.modelSeqNo.length ; i++){
		if(mod.modelSeqNo[i].checked){
			modchecked = true;
			index = i;
			break;
		
		}

	}
}
else{
		if(mod.modelSeqNo.checked)
			modchecked = true;
			
			
		
}
	
	if (!modchecked)
	{
		var id= '7';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("modelSeqNo",-1);
		return false;
	

	}else{
			var modName = "";
			var horserpower ="";
			if(mod.modelSeqNo.length!=undefined){
			
				 modName = trim(mod.modelName[index].value);
				 horserpower = trim(mod.hpowerRate[index].value);

				if(modName.length==0 || modName=="" ){
					var id = '21';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("modelName",index);
					return false;
		
				}
				else if(horserpower.length==0 || horserpower=="" ){
				
					var id = '12';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("hpowerRate",index);
					return false;
				
				}
		}else{
				 modName = trim(mod.modelName.value);
				 horserpower  = trim(mod.hpowerRate.value);
				if(modName.length==0 || modName=="" ){
					var id = '21';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("modelName",-1);
					return false;
		
				}
				else if(horserpower.length==0 || horserpower=="" ){
				
					var id = '12';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("hpowerRate",-1);
					return false;
				
				}

				
		
		}
			
			
			
			/*else if(mod.modelDesc[index].value.length==0 || mod.modelDesc[index].value=="" ){
			
				var id = 'LSDB14';
				hideErrors();
				addMsgNum(id);
				showErrors();
				return false;
			
			}*/
}	
	
	if(mod.modelSeqNo.length!=undefined){	
		for(var i = 0 ; i < document.forms[0].modelSeqNo.length ; i++){

			if(document.forms[0].modelSeqNo[i].checked){

				
				document.forms[0].hdnModelName.value = trim(document.forms[0].modelName[i].value);
				/*var isSpecChar = SpecialCharacterCheck(document.forms[0].hdnModelName.value);

				if(isSpecChar){
			
					var id = '21';
					hideErrors();
					addMsgNum(id);
					showErrors();
					document.forms[0].modelName[i].focus();
					return false;
				
				}*/
				
				document.forms[0].hdnHorsePower.value=trim(document.forms[0].hpowerRate[i].value);
				document.forms[0].hdnModelDesc.value=trim(document.forms[0].modelDesc[i].value);
				
				document.forms[0].hdnSpecTypeSeqNo.value=document.forms[0].specTypeSeqNo[i].value;
				//Added for CR_118
				document.forms[0].hdnModelFlag.value=trim(document.forms[0].modelFlag[i].value);
				
				// Added for CR_97 to modify the change control flag in the result of search
				var changeControlFlag= document.getElementsByName("changeCtrlRadio"+(i+1));
				 for(var k=0;k<changeControlFlag.length;k++)
				{
				    if(changeControlFlag[k].checked)
				     {
					    document.forms[0].hdnChangeControlFlag.value=changeControlFlag[k].value;							
				      }
				 }
				// CR_97 Ends here.

				break;

			}
		}
	}else{

			if(document.forms[0].modelSeqNo.checked){
				document.forms[0].hdnModelName.value = document.forms[0].modelName.value;
				document.forms[0].hdnHorsePower.value=document.forms[0].hpowerRate.value;
				document.forms[0].hdnModelDesc.value=document.forms[0].modelDesc.value;
				
				document.forms[0].hdnSpecTypeSeqNo.value=document.forms[0].specTypeSeqNo.value;
				
				//Added for CR_118
				document.forms[0].hdnModelFlag.value=trim(document.forms[0].modelFlag.value);
				document.forms[0].hdnModelFlag.value=trim(document.forms[0].modelFlag[i].value);
				
				
				//Commented for LSDB_CR_56 
				//document.forms[0].hdnCusTypeSeqNo.value=document.forms[0].modelCustTypeSeqNo.value;
				

			}
	}

if(document.forms[0].hdnModelDesc.value.length !=undefined){
	if(document.forms[0].hdnModelDesc.value.length > 2000){
			
			var id = '4';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("hdnModelDesc",-1);
			return false;


	}
}
document.forms[0].action="ModelAction.do?method=updateModel";
document.forms[0].submit();

}

function disableCustomerType()
{

	if(document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value=="2"){
		document.forms[0].modCustTypeSeqNo[0].disabled = true;
		document.forms[0].modCustTypeSeqNo[1].disabled = true;
		document.forms[0].modCustTypeSeqNo[0].checked = false;
		document.forms[0].modCustTypeSeqNo[1].checked = false;
	}else{
			
		document.forms[0].modCustTypeSeqNo[0].disabled = false;
		document.forms[0].modCustTypeSeqNo[1].disabled = false;
	
	}

}

//Ends here
function fnFetchModel(){

	var mod = document.forms[0];
	if (document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1")
   {
	 
		var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltSpecType",-1);
		return false;
   
	}
	document.forms[0].hdnSpecTypeName.value = document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
	document.forms[0].action="CopyModelAction.do?method=fetchModels";
	document.forms[0].submit();

}

function fnCopyModel()
{
	var mod = document.forms[0];
	var modelname = trim(mod.modName.value);
	var horsePower = trim(mod.horsePower.value);
	var modDesc = trim(mod.modDesc.value);
	var specTypeNo=document.forms[0].hdnPrevSelSpec.value;
	var modelSeqNo;
	flag = false;
	var index;
	modchecked = false;
	
	//Added for CR-131 bUG fIX
	if ($("input[type=radio][name=modelSeqNo]:checked").length > 0)
		modchecked = true;
	//ends	
	/*if(mod.modelSeqNo.length!=undefined){
		for(var i = 0 ; i < mod.modelSeqNo.length ; i++){
			if(mod.modelSeqNo[i].checked){
				modchecked = true;
				index = i;
				modelSeqNo=document.forms[0].modelSeqNo[index].value;
				break;
		
			}

		}
	}
	else{
		if(mod.modelSeqNo.checked){
			modchecked = true;
			modelSeqNo=document.forms[0].modelSeqNo.value;
			
			}
		
		}*/
	
	if (!modchecked)
	{
		var id= '865';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("modelSeqNo",1);
		return false;
	

	}else if (document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1")
	  {
			var id = '2';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("sltSpecType",-1);
			return false;
	   
		}
	
	if(modelname.length==0 || modelname==""){
	
		var id = '21';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("modName",-1);

		mod.modName.value ="";
		mod.modName.select();
		mod.modName.focus();
		return false;

	}else

	if(horsePower.length==0 || horsePower=="" ){
	
		var id = '12';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("horsePower",-1);
		return false;
	}

	if(modDesc.length > 2000 ){
			
		var id = '4';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("modDesc",-1);
		return false;
	}
  
   if(document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value != specTypeNo){
      
        var id= '207';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltSpecType",-1);
		return false;
   
   
   }
  
document.forms[0].hdnSpecTypeName.value = document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
document.getElementById("btnCopyModel").disabled = true;
document.forms[0].action="CopyModelAction.do?method=copyModel";
document.forms[0].submit();


}

//Added for CR_118
function fnHideUnhideModel(){
	
	var confirmText="";
	var mod = document.forms[0];
	flag = false;
	var index;
	modchecked = false;


	if(mod.hdnPrevSelSpec.value!=mod.specTypeNo.options[mod.specTypeNo.selectedIndex].value){
		
		var id= '207';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("searchButton",-1);
		return false;
	}
	
	
	if(mod.modelSeqNo.length!=undefined){

		for(var i = 0 ; i < mod.modelSeqNo.length ; i++){
			if(mod.modelSeqNo[i].checked){
				modchecked = true;
				index = i;
				break;
			
			}
	
		}
	}
	else{
			if(mod.modelSeqNo.checked)
			modchecked = true;
		
	}
	
	if (!modchecked)
	{
		var id= '1022';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("modelSeqNo",-1);
		return false;
	

	}
	else{
			if(mod.modelSeqNo.length!=undefined){
				if(trim(document.forms[0].modelFlag[i].value)=="N"){
					confirmText ="Are you sure to hide the Model across the application except Spec History?\nPlease note that the specs for this model will still be accessible by giving appropriate order numbers ";
				}else{
					confirmText ="Are you sure to unhide the Model across the application?";
				}
			}else{
				if(trim(document.forms[0].modelFlag.value)=="N"){
						confirmText ="Are you sure to hide the Model across the application except Spec History?\nPlease note that the specs for this model will still be accessible by giving appropriate order numbers ";
				}else{
						confirmText ="Are you sure to unhide the Model across the application?";
				}
			}
		
			var check= confirm(confirmText);
			if(check){
				document.forms[0].hideUnhideModel.value=1;
				
				fnModifyModel();
				
			}else{
				document.forms[0].hideUnhideModel.value=0;
			}
		
	}
}
//Added for CR_118 Ends


