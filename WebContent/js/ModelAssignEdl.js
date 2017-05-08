
/* Added for LSDB_CR_46 PM&I Change */

function fetchModels(){
	
	document.forms[0].action="ModelAssignEdlAction.do?method=fetchModels";
	document.forms[0].submit();

}


//Flag variable
var clauseDescChangeFlag=0;

function fnLoadModel(){

document.forms[0].action="ModelAssignEdlAction.do?method=initLoad";
document.forms[0].submit();
}

function SpecialCharacterCheck(field){

var iChars = "!@#$%^&*()+=[]\\\/{}|\<>";



for (var i = 0; i < field.length; i++) {
if (iChars.indexOf(field.charAt(i)) != -1) {
return true;
}
}
return false;

}
/*
*   Name: LoadClauseDesc
*  Purpose:Used to Load the popup screen
*
*/
function LoadClauseDesc()
{
clearAlerts();//Added for CR_131
clauseDescChangeFlag=1;
var selectedModel=document.forms['ModelAssignEdlForm'].modelseqno.selectedIndex;
var selectedModelName= document.forms['ModelAssignEdlForm'].modelseqno.options[selectedModel].text;
var selectedModelID = document.forms['ModelAssignEdlForm'].modelseqno.options[selectedModel].value;


//Added for LSDB_CR_46 PM&I Change
var selIndex = document.forms['ModelAssignEdlForm'].specTypeNo.selectedIndex;
var specType = document.forms['ModelAssignEdlForm'].specTypeNo.options[selIndex].text;

if (document.forms['ModelAssignEdlForm'].specTypeNo.options[document.forms['ModelAssignEdlForm'].specTypeNo.selectedIndex].value =="-1")
{
	 
		var id = '2';
		hideErrors();
		addMsgNum(id);
	    showScrollErrors("specTypeNo",-1);
		   
}else if(document.forms['ModelAssignEdlForm'].modelseqno.options[selectedModel].value==-1)
{
var id = '19';
hideErrors();
addMsgNum(id);
showScrollErrors("modelseqno",-1);
}
else
{
window.open("modelClauseDescPopupAction.do?method=initLoad&selectedModelName="+escape(selectedModelName)+"&selectedModelID="+selectedModelID+"&spectype="+escape(specType)+"&ChrstcFlag="+document.forms[0].hdnClaChrstcFlag.value+"","Clause","location=0,resizable=no,status=0,scrollbars=1,width=780,height=600");

}

}

//CR_85
/*
*  Name: fnLinkClauseDesc
*  Purpose:Used to Load the popup screen
*
*/
function fnLinkClauseDesc(selClauseIndex)
{
clearAlerts();//Added for CR_131
var selectedModel=document.forms['ModelAssignEdlForm'].modelseqno.selectedIndex;
var selectedModelName= document.forms['ModelAssignEdlForm'].modelseqno.options[selectedModel].text;
var selectedModelID = document.forms['ModelAssignEdlForm'].modelseqno.options[selectedModel].value;
var selIndex = document.forms['ModelAssignEdlForm'].specTypeNo.selectedIndex;
var specType = document.forms['ModelAssignEdlForm'].specTypeNo.options[selIndex].text;
var ClaChrstcFlag = document.forms['ModelAssignEdlForm'].hdnClaChrstcFlag.value="N";
assignEdl=document.forms['ModelAssignEdlForm'];
var flagVal=false;
var indexFlag=false;

if(assignEdl.combntnSeqNo.length > 0 ){
		for(var i = 0 ; i < assignEdl.combntnSeqNo.length; i++){
		if(assignEdl.combntnSeqNo[i].checked){
			 if(i!=selClauseIndex){
			 	indexFlag=true;
                var id = '897';
		        hideErrors();
				addMsgNum(id);
				showScrollErrors("combntnSeqNo",selClauseIndex);	
				break;			
				}
			else{
				flagVal=true;
	            var combntnSeqNo=assignEdl.combntnSeqNo[i].value;
				break;
				}
			}
		}
	}else{
			if(assignEdl.combntnSeqNo.checked){
		     var combntnSeqNo=assignEdl.combntnSeqNo.value;
				flagVal=true;
			}
	 }
	 
 
	 if(!flagVal && !indexFlag){

		var id = '894';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("combntnSeqNo",selClauseIndex);
		
	}
	
if(clauseDescChangeFlag==1)
	{
		var id= '207';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("searchButton",-1);
	}

else if(assignEdl.prevSpecType.value!=assignEdl.specTypeNo.options[assignEdl.specTypeNo.selectedIndex].value){

	var id = '207';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("searchButton",-1);
}

else if(assignEdl.hdPreSelectedModel.value!=assignEdl.modelseqno.options[assignEdl.modelseqno.selectedIndex].value){

	var id = '207';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("searchButton",-1);
	
}
	
else if(flagVal){
		assignEdl.selClauseDesc.value=selClauseIndex;
		window.open("modelClauseDescPopupAction.do?method=initLoad&selectedModelName="+escape(selectedModelName)+"&selectedModelID="+selectedModelID+"&spectype="+escape(specType)+"&ChrstcFlag="+ClaChrstcFlag+"","Clause","location=0,resizable=no,status=0,scrollbars=1,width=780,height=600");
	}
}


/*
*  Name: fnFetchCharCompGrps
*  Purpose:Used to Load the Characteristic Component Groups
*
*/
function fnFetchCharCompGrps()
{
clauseDescChangeFlag=0;
var mod = document.getElementById("clauseDesc").innerHTML.trim();
var assignEdl=document.forms[0];

if (document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1")
{
	 
		var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltSpecType",-1);
		return false;
		   
}

if (document.forms[0].modelseqno.options[document.forms[0].modelseqno.selectedIndex].value =="-1")
{

var id = '19';
hideErrors();
addMsgNum(id);
showScrollErrors("sltModel",-1);
return false;

}


if(mod=="&nbsp;" || (mod=="")){

var id = '20';
hideErrors();
addMsgNum(id);
showScrollErrors("clauseDesc",-1);
return false;

}

if((assignEdl.clauseSeqNo.value=="")){

var id = '20';
hideErrors();
addMsgNum(id);
showScrollErrors("clauseSeqNo",-1);
return false;

}

document.forms[0].hdnModelName.value=document.forms[0].modelseqno.options[document.forms[0].modelseqno.selectedIndex].text;
document.forms[0].action="ModelAssignEdlAction.do?method=fetchCharCompGrps";
document.forms[0].submit();
}

/*
*  Name: fnAddCharGrpCombtn
*  Purpose:Used to Add the Characteristic Group Combination
*
*/
function fnAddCharGrpCombtn()
{
	if(clauseDescChangeFlag==1)
		{
			var id= '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("searchButton",-1);
			return false;
		}
	assignEdl = document.forms['ModelAssignEdlForm'];
	var flagVal = false;
	var compGrpArray = "";
	var compArray = "";
	var cnt = 0;
	
	if(assignEdl.prevSpecType.value!=assignEdl.specTypeNo.options[assignEdl.specTypeNo.selectedIndex].value){
	
		var id = '207';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("searchButton",-1);
		return false;
	}
	
	
	if(assignEdl.hdPreSelectedModel.value!=assignEdl.modelseqno.options[assignEdl.modelseqno.selectedIndex].value){
	
		var id = '207';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("searchButton",-1);
		return false;
	}

	if(assignEdl.charGrpCnt.length > 0 ){
		for(var i = 0 ; i < assignEdl.charGrpCnt.length; i++){
				
			if(assignEdl.charGrpCnt[i].checked){
			
					flagVal = true;
					index = i;
					if (assignEdl.compSeqNo[index].options[assignEdl.compSeqNo[index].selectedIndex].value =="-1") {
					 
						var id = '882';
						hideErrors();
						addMsgNum(id);
						showScrollErrors(assignEdl.compSeqNo[index].attributes.getNamedItem("id").value,index);
						return false;
				   
					   }
					   
			if(compGrpArray=="")				   
				compGrpArray = assignEdl.charGrpCnt[index].value;
			else
				compGrpArray = compGrpArray + "," + assignEdl.charGrpCnt[index].value;
			
			if(compArray=="")				   
				compArray = assignEdl.compSeqNo[index].options[assignEdl.compSeqNo[index].selectedIndex].value;
			else
				compArray = compArray + "," + assignEdl.compSeqNo[index].options[assignEdl.compSeqNo[index].selectedIndex].value;
			}
		}
	}else{
			if(assignEdl.charGrpCnt.checked){
					flagVal = true;
					if (assignEdl.compSeqNo.options[assignEdl.compSeqNo.selectedIndex].value =="-1") {
					 
						var id = '882';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("compSeqNo",-1);
						return false;
						}			   
				compGrpArray = assignEdl.charGrpCnt.value;
				compArray = assignEdl.compSeqNo.options[assignEdl.compSeqNo.selectedIndex].value;
			}
	 }
	 
	if(!flagVal){

		var id = '881';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("charGrpCnt",-1);
		return false;
	}
	 if (((assignEdl.charstcEdlNo.value =="") || (trim(assignEdl.charstcEdlNo.value).length==0)) 
	 		&& ((assignEdl.charstcRefEDLNo.value =="") || (trim(assignEdl.charstcRefEDLNo.value).length==0))){
		 
			var id = '883';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("charstcEdlNo",-1);//bug fix CR-131 
			return false;
	   
		   }	
	assignEdl.charstcEdlNo.value = trim(assignEdl.charstcEdlNo.value);
	assignEdl.charstcRefEDLNo.value = trim(assignEdl.charstcRefEDLNo.value);
	document.forms[0].action="ModelAssignEdlAction.do?method=insertCharGrpCombntn&compGrpArray="+compGrpArray+"&compArray="+compArray+"";	
	document.forms[0].submit();				
}

/*
*  Name: fnSaveCharGrpCombtn
*  Purpose:Used to save/Modify Edl/RefEdl value of a Characteristic Group Combination
*
*/
function fnSaveCharGrpCombtn()
{

	if(clauseDescChangeFlag==1)
		{
			var id= '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("searchButton",-1);
			return false;
		}
	assignEdl = document.forms[0];
	var flagVal = false;
	var index;
	
	if(assignEdl.prevSpecType.value!=assignEdl.specTypeNo.options[assignEdl.specTypeNo.selectedIndex].value){
	
		var id = '207';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("searchButton",-1);
		return false;
	}
	
	
	if(assignEdl.hdPreSelectedModel.value!=assignEdl.modelseqno.options[assignEdl.modelseqno.selectedIndex].value){
	
		var id = '207';
		hideErrors();
		addMsgNum(id);
	showScrollErrors("searchButton",-1);
		return false;
	}
	
	if(assignEdl.combntnSeqNo.length > 0 ){
		for(var i = 0 ; i < assignEdl.combntnSeqNo.length; i++){
				
			if(assignEdl.combntnSeqNo[i].checked){
			
					flagVal = true;
					index = i;
					if (((assignEdl.charEdlNo[index].value =="") || (trim(assignEdl.charEdlNo[index].value).length==0)) 
					 		&& ((assignEdl.charRefEDLNo[index].value =="") || (trim(assignEdl.charRefEDLNo[index].value).length==0))){
						 
							var id = '883';
							hideErrors();
							addMsgNum(id);
							showScrollErrors("charEdlNo",index);
							return false;
					   
					}
					else{
					
						assignEdl.hdnCharEdlNo.value=trim(assignEdl.charEdlNo[index].value);
						assignEdl.hdnCharRefEdlNo.value=trim(assignEdl.charRefEDLNo[index].value);
					}
				}
		}
	}else{
			if(assignEdl.combntnSeqNo.checked){
				 flagVal = true;
				 if (((assignEdl.charEdlNo.value =="") || (trim(assignEdl.charEdlNo.value).length==0)) 
				 		&& ((assignEdl.charRefEDLNo.value =="") || (trim(assignEdl.charRefEDLNo.value).length==0))){
					 
						var id = '883';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("charRefEDLNo",index);
						return false;
				   
					   }
				else{
					
						assignEdl.hdnCharEdlNo.value=trim(assignEdl.charEdlNo.value);
						assignEdl.hdnCharRefEdlNo.value=trim(assignEdl.charRefEDLNo.value);
					}  
			}
	 }
	 
	if(!flagVal){

		var id = '884';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("combntnSeqNo",-1);
		return false;
	}	
	
	document.forms[0].action="ModelAssignEdlAction.do?method=updateCharGrpCombntn";	
	document.forms[0].submit();				
}
/*
*  Name: fnViewUnLinkClause for CR_85
*  Purpose:Used to Link the clause to  Combination of compounents
*
*/
function fnViewLinkedClause(noOfLinks,combntnSeqNo,selClauseIndex)
{
clearAlerts();//Added for CR_131
assignEdl = document.forms[0];
var modelSeqNo=assignEdl.modelseqno.value;
var selectedModel=assignEdl.modelseqno.selectedIndex;
var selectedModelName= assignEdl.modelseqno.options[selectedModel].text;
var specType= assignEdl.specTypeNo.options[assignEdl.specTypeNo.selectedIndex].text;
var flagpopup=true;
var flagVal=false;

if(assignEdl.combntnSeqNo.length > 0 ){
		for(var i = 0 ; i < assignEdl.combntnSeqNo.length; i++){
		if(assignEdl.combntnSeqNo[i].checked){
			 if(i!=selClauseIndex){
			   flagpopup=false;
                var id = '897';
		         hideErrors();
				addMsgNum(id);
				showScrollErrors("combntnSeqNo",selClauseIndex);
				break;			
					} else {
				flagVal=true;
          		var combntnSeqNo=assignEdl.combntnSeqNo[i].value;
				break;
					}
				}
		}
	}else{
			if(assignEdl.combntnSeqNo.checked){
		     var combntnSeqNo=assignEdl.combntnSeqNo.value;
				flagVal=true;
			}
	 }
	 
	  if(!flagVal){
		if(flagpopup){
			var id = '894';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("combntnSeqNo",selClauseIndex);
		}		
	}
	else{
		if (noOfLinks == 0){
			var id = '888';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("noOfLinks",i);
			}
		else if(clauseDescChangeFlag==1)
		{
			var id= '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("searchButton",-1);
		}
	
		else if(assignEdl.prevSpecType.value!=assignEdl.specTypeNo.options[assignEdl.specTypeNo.selectedIndex].value){
		
			var id = '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("searchButton",-1);
			}
		
		else if(assignEdl.hdPreSelectedModel.value!=assignEdl.modelseqno.options[assignEdl.modelseqno.selectedIndex].value){
		
			var id = '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("searchButton",-1);
			}
			
		else if(flagpopup && flagVal)
		{		
		window.open("ModelAssignEdlAction.do?method=fetchLinkedClause&modelSeqNo="+modelSeqNo+"&selectedModelName="+selectedModelName+"&specType="+specType+"&combntnSeqNo="+combntnSeqNo+"","VewUnlinkClause","location=0,resizable=no,status=0,scrollbars=1,width=780,height=600");	
		}
	}

}
/*
*  Name: fnUnlinkClause for CR_85
*  Purpose:Used to UnLink the clause from  Combination of compounents
*
*/
function fnUnlinkClause()
{
var count=0;
var assignEdl = document.forms[0];
var combntnSeqNo=assignEdl.combntnSeqNo.value;
var flagVal=false;
assignEdl = document.forms['ModelAssignEdlForm'];
	if(assignEdl.clauseSeqNo.length > 0 ){
		for(var i = 0 ; i < assignEdl.clauseSeqNo.length; i++){
				 count= assignEdl.clauseSeqNo.length;
			if(assignEdl.clauseSeqNo[i].checked){
			var clauseSeqNo=assignEdl.clauseSeqNo[i].value;
					flagVal = true;
				}
		}
	}else{
			if(assignEdl.clauseSeqNo.checked){
			 count= assignEdl.clauseSeqNo.length;
			var clauseSeqNo=assignEdl.clauseSeqNo.value;
				 flagVal = true;
			}
	 }
	
	 
	if(!flagVal){
		
		var id = '885';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("clauseSeqNo",-1);
		return false;
	}	
	var del=confirm("Are you sure to Unlink the Clause ?");
	if(del == true){
		document.forms[0].action="ModelAssignEdlAction.do?method=unlinkClause&clauseSeqNo="+clauseSeqNo+"&combntnSeqNo="+combntnSeqNo;
		document.forms[0].submit();	
		/* Commented for CR_131
		 * if(count!=0){
			window.opener.assignEdl.noOfLinks.value=count-1;
		}
		window.close();
		window.opener.fnFetchCharCompGrps();*/
		
		}
	else{
	}	

}

/*
*  Name: fnDeleteCharGrpCombtn
*  Purpose:Used to Delete a Characteristic Group Combination
*
*/
function fnDeleteCharGrpCombtn()
{

	if(clauseDescChangeFlag==1)
		{
			var id= '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("searchButton",-1);
			return false;
		}
		
	assignEdl = document.forms['ModelAssignEdlForm'];
	var flagVal = false;
	
		if(assignEdl.prevSpecType.value!=assignEdl.specTypeNo.options[assignEdl.specTypeNo.selectedIndex].value){
	
		var id = '207';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("searchButton",-1);
		return false;
	}
	
	
	if(assignEdl.hdPreSelectedModel.value!=assignEdl.modelseqno.options[assignEdl.modelseqno.selectedIndex].value){
	
		var id = '207';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("searchButton",-1);
		return false;
	}
	
	if(assignEdl.combntnSeqNo.length > 0 ){
		for(var i = 0 ; i < assignEdl.combntnSeqNo.length; i++){
				
			if(assignEdl.combntnSeqNo[i].checked){
					flagVal = true;
				}
		}
	}else{
			if(assignEdl.combntnSeqNo.checked){
				 flagVal = true;
			}
	 }
	 
	if(!flagVal){

		var id = '885';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("combntnSeqNo",-1);
		return false;
	}	
	
	var del=confirm("Are you sure to delete the Characteristic Group Combination ?");
	if(del == true){
	document.forms[0].action="ModelAssignEdlAction.do?method=deleteCharGrpCombntn";	
	document.forms[0].submit();	
	}
	else{
	}			
}

/*
*  Name: fnAddClauseDes
*  Purpose:Used to Load Clause Description from Popup
*
*/
function fnAddClauseDes(clauseDes)
{
	document.getElementById("linkClauseDesc"+document.forms[0].selClauseDesc.value).innerHTML=clauseDes;
	$("#linkClauseDesc"+document.forms[0].selClauseDesc.value).addClass('bg-info');
}