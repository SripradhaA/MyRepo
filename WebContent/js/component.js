/**
This Method performs Field Validations before Fetching Components
**/
function fnSearchComponent(){

  var compForm = document.forms[0];
  var specTypeNo=$('#specTypeSeqNo option:selected').val();
  var specTypeName=$('#specTypeSeqNo option:selected').text();
	
    if(specTypeNo == -1)
    {
    specTypeName="";
    }
   	compForm.hdnSelSpecType.value = specTypeName;
   	compForm.specTypeNo.vale=specTypeNo;
  if(compForm.compGrpSeqNo.options[compForm.compGrpSeqNo.selectedIndex].value=="-1"){
   		var id = '172';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("cmpGroupChr",-1);
		return false;
  }

  document.forms[0].hdnSelCompGrp.value = document.forms[0].compGrpSeqNo.options[document.forms[0].compGrpSeqNo.selectedIndex].text ;
  //Added Line for CR_81 by RR68151
  document.forms[0].hdnCompGrpCat.value = document.forms[0].compgrpCat.options[document.forms[0].compgrpCat.selectedIndex].text;
  document.forms[0].action="CompAction.do?method=fetchComps";
  document.forms[0].submit();
  
}

/**
This Method performs Field Validations before adding a Component
**/

function fnAddComponent(){
  var compForm = document.forms[0];
  
  var specTypeNo=$('select#specTypeSeqNo:selected').val();
    var specTypeName=$('#specTypeSeqNo option:selected').text();
   
    if(specTypeNo==-1)
    {
    specTypeName="";
    }
   	compForm.hdnSelSpecType.value = specTypeName;
   	compForm.specTypeNo.vale=specTypeNo;
  

   if(compForm.compGrpSeqNo.options[compForm.compGrpSeqNo.selectedIndex].value=="-1"){
   		var id = '172';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("cmpGroupChr",-1);
		return false;
  }
  
  if(trim(compForm.component.value).length==0 || trim(compForm.component.value)=="" ){
   		var id = '208';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("component",-1);
		return false;
  }
  
 
	if(trim(compForm.compIdentifier.value).length==0 || trim(compForm.component.value)=="" ){
   		var id = '221';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("compIdentifier",-1);
		return false;
  }
  
  
	
	  if(trim(compForm.compDesc.value).length==0 || trim(compForm.compDesc.value)=="" ){
	   		var id = '209';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("compgrpDesc",-1);
			return false;
	  }
  	
	
	 if(trim(compForm.compDesc.value).length > 2000){
	   		var id = '517';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("compgrpDesc",-1);
			return false;
	  }
 
     document.forms[0].hdnSelCompGrp.value = document.forms[0].compGrpSeqNo.options[document.forms[0].compGrpSeqNo.selectedIndex].text ;
  	 //Added Line for CR_81 by RR68151
  	 document.forms[0].hdnCompGrpCat.value = document.forms[0].compgrpCat.options[document.forms[0].compgrpCat.selectedIndex].text;
     document.forms[0].action="CompAction.do?method=insertComp";
	 document.forms[0].submit();
}

/**
This Method performs Field Validations before Modifying a Component
**/
function fnModifyComponent(){


 	var compForm = document.forms[0];
	var index;
	compSeqNochecked = false;
	
var specTypeNo=$('select#specTypeSeqNo:selected').val();
    var specTypeName=$('#specTypeSeqNo option:selected').text();
    if(specTypeNo==-1)
    {
    specTypeName="";
    }
   	compForm.hdnSelSpecType.value = specTypeName;
   	compForm.specTypeNo.vale=specTypeNo;

	
	if(compForm.componentSeqNo.length>0){
	      var cnt = compForm.componentSeqNo.length;
		  for(var i=0;i<cnt;i++){
			   if(compForm.componentSeqNo[i].checked){
						compSeqNochecked = true;
						index = i;
						break;
			   }
		  }
	}
	else{
			if(compForm.componentSeqNo.checked){
				compSeqNochecked = true;
			}
	     }

	if (!compSeqNochecked)
	{
		var id= '180';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("componentSeqNo",-1);
		return false;
	

	}else{
			
			if(compForm.componentSeqNo.length>0){
			
				if(trim(compForm.componentName[index].value).length==0 || trim(compForm.componentName[index].value)=="" ){
					var id = '208';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("componentName",index);
					return false;
				}
				
				
				
				if(trim(compForm.componentIdentifier[index].value).length==0 || trim(compForm.componentIdentifier[index].value)=="" ){
					var id = '221';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("componentIdentifier",index);
					return false;
				}
				
				
				
				 if(trim(compForm.comments[index].value).length==0 || trim(compForm.comments[index].value)=="" ){
			   		var id = '209';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("comments",index);
					return false;
				  }
				  
		  		
				if(trim(compForm.comments[index].value).length > 2000 ){
			   		var id = '517';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("comments",index);
					return false;
				  }
				if(document.forms[0].hdnSelCompGrp.value != document.forms[0].compGrpSeqNo.options[document.forms[0].compGrpSeqNo.selectedIndex].text){
				 	var id = '207';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("searchButton",-1);
					return false;
				}
			}else{
				if(trim(compForm.componentName.value).length==0 || trim(compForm.componentName.value)=="" ){
					var id = '208';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("componentName",-1);
					return false;
				}
				
				
				if(trim(compForm.componentIdentifier.value).length==0 || trim(compForm.componentIdentifier.value)=="" ){
					var id = '221';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("componentIdentifier",-1);
					return false;
				}
				
				
				
				if(trim(compForm.comments.value).length==0 || trim(compForm.comments.value)=="" ){
			   		var id = '209';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("comments",-1);
					return false;
				  }
				  
		  		
				if(trim(compForm.comments.value).length > 2000 ){
			   		var id = '517';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("comments",-1);
					return false;
				  }
				
				if(document.forms[0].hdnSelCompGrp.value != document.forms[0].compGrpSeqNo.options[document.forms[0].compGrpSeqNo.selectedIndex].text){
				 	var id = '207';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("searchbutton",-1);
					return false;
				}
			}
	}
	
	if(compForm.componentSeqNo.length>0){
	      var cnt = compForm.componentSeqNo.length;
		  for(var i=0;i<cnt;i++){
			   if(compForm.componentSeqNo[i].checked){
 					compForm.hdnCompName.value=compForm.componentName[i].value;
 					compForm.hdnCompIdentifier.value=compForm.componentIdentifier[i].value
 					compForm.hdnCompDesc.value=compForm.comments[i].value;
 					if(compForm.displayInSpecFlag[i].checked){
      					 compForm.hdnDispSpec.value="true";
				    }else{
				         compForm.hdnDispSpec.value="false";
    				 }
			        break;
			   }
		 }
	}else{
		 if(compForm.componentSeqNo.checked){
 					compForm.hdnCompName.value=compForm.componentName.value;
 					compForm.hdnCompIdentifier.value=compForm.componentIdentifier.value
 					compForm.hdnCompDesc.value=compForm.comments.value;
 					if(compForm.displayInSpecFlag.checked){
      					 compForm.hdnDispSpec.value="true";
				    }else{
				         compForm.hdnDispSpec.value="false";
    				}
		}
	}
 //Added Line for CR_81 by RR68151
 document.forms[0].hdnCompGrpCat.value = document.forms[0].compgrpCat.options[document.forms[0].compgrpCat.selectedIndex].text;
 document.forms[0].action="CompAction.do?method=updateComp";
 document.forms[0].submit();


}
//Added for LSDB_CR_67
function fnDeleteComponent(){
	var compForm = document.forms[0];
	var index;
	compSeqNochecked = false;
	
  var specTypeNo=$('select#specTypeSeqNo:selected').val();
    var specTypeName=$('#specTypeSeqNo option:selected').text();
    if(specTypeNo==-1)
    {
    specTypeName="";
    }
   	compForm.hdnSelSpecType.value = specTypeName;
   	compForm.specTypeNo.vale=specTypeNo;
	if(compForm.componentSeqNo.length>0  ){
		var cnt = compForm.componentSeqNo.length;
		for(var i=0;i<cnt;i++){
			if(compForm.componentSeqNo[i].checked){
				compSeqNochecked = true;
				index = i;
				break;
			}
		}
	}
	else{
			if(compForm.componentSeqNo.checked){
				compSeqNochecked = true;
		}
	}
	if (!compSeqNochecked)
		{
			var id= '864';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("componentSeqNo",-1);
			return false;
		
	}

	if(document.forms[0].hdnSelCompGrp.value != document.forms[0].compGrpSeqNo.options[document.forms[0].compGrpSeqNo.selectedIndex].text)
	{
		var id = '207';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("searchButton",-1);
		return false;
	}

	var accept=confirm("Are you sure to delete the Component?");
	if(accept == true){
  	//Added Line for CR_81 by RR68151
  	document.forms[0].hdnCompGrpCat.value = document.forms[0].compgrpCat.options[document.forms[0].compgrpCat.selectedIndex].text;
	document.forms[0].action="CompAction.do?method=deleteComp";
	document.forms[0].submit();

}else{

}


}

/*	fnSearchCompGroups() - 
*	fetch ComponentGroups based on Component Group Types
*	Added for LSDB_CR_81
*	Locomotive and Power Products Enhancements
*	by RR68151 on 28-Dec-2009
*/

function fnSearchCompGroups(){
  	//Added Line for CR_81 by RR68151
/*  	var compMaintForm = document.forms[0];
//added for CR_97
var specTypeNo=$('select#specTypeSeqNo').val();
var compGrpType=$('select#compgrpSeqType').val();
compMaintForm.hdnSelSpecType.value=specTypeNo;
compMaintForm.hdnCompGrpCat.value=compGrpType;
if(compGrpType!=undefined){
compMaintForm.compgrpCat.value=compGrpType;
}else{
compMaintForm.compgrpCat.value="";
}


compMapForm.hdnSelSpecType.value=compMapForm.specTypeNo.options[compMapForm.specTypeNo.selectedIndex].text;
*/var specTypeNo=$('select#specTypeSeqNo:selected').val();
    var specTypeName=$('#specTypeSeqNo option:selected').text();
    if(specTypeNo==-1)
    {
    specTypeName="";
    }

   	document.forms[0].hdnSelSpecType.value = specTypeName;
   	
  document.forms[0].hdnCompGrpCat.value = document.forms[0].compgrpCat.options[document.forms[0].compgrpCat.selectedIndex].text;
  	
    document.forms[0].action="CompAction.do?method=initLoad&screenid=13";
	document.forms[0].submit();
	}
//added for CR_97
function fnLoadComponent(){

  var compForm = document.forms[0];
  var specTypeNo=$('select#specTypeSeqNo:selected').val();
    var specTypeName=$('#specTypeSeqNo option:selected').text();
    if(specTypeNo==-1)
    {
    specTypeName="";
    }
 
  document.forms[0].specTypeNo.vale=specTypeNo;
  document.forms[0].hdnSelCompGrp.value = document.forms[0].compGrpSeqNo.options[document.forms[0].compGrpSeqNo.selectedIndex].text ;
  document.forms[0].hdnCompGrpCat.value = document.forms[0].compgrpCat.options[document.forms[0].compgrpCat.selectedIndex].text;
  document.forms[0].action="CompAction.do?method=initLoad";
  document.forms[0].submit();
  
}
