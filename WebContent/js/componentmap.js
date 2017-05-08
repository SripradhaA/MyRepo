function fnLoadSections(){

var compMapForm = document.forms[0];
compMapForm.hdnSelModel.value=compMapForm.modelSeqNo.options[compMapForm.modelSeqNo.selectedIndex].text;

document.forms[0].action="CompMapAction.do?method=sectionLoad";
document.forms[0].submit();
}

function fnLoadSubSections(){

var compMapForm = document.forms[0];
compMapForm.hdnSelModel.value=compMapForm.modelSeqNo.options[compMapForm.modelSeqNo.selectedIndex].text;
compMapForm.hdnSelSec.value=compMapForm.sectionSeqNo.options[compMapForm.sectionSeqNo.selectedIndex].text;

document.forms[0].action="CompMapAction.do?method=subSectionLoad";
document.forms[0].submit();
}


function fnSearchComponentMapping(){

  var compMapForm = document.forms[0];
  
  if (compMapForm.specTypeNo.options[compMapForm.specTypeNo.selectedIndex].value =="-1")
   {
	    var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("specTypeSeqNo",-1);
		return false;
   
	}
  
  if(compMapForm.modelSeqNo.options[compMapForm.modelSeqNo.selectedIndex].value=="-1"){
   		var id = '19';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltModel",-1);
		return false;
  }
  if(compMapForm.sectionSeqNo.options[compMapForm.sectionSeqNo.selectedIndex].value=="-1"){
   		var id = '205';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltSection",-1);
		return false;
  }
  if(compMapForm.subSectionSeqNo.options[compMapForm.subSectionSeqNo.selectedIndex].value=="-1"){
   		var id = '182';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltSubSection",-1);
		return false;
  }
  if(compMapForm.compGrpSeqNo.options[compMapForm.compGrpSeqNo.selectedIndex].value=="-1"){
   		var id = '183';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltComponent",-1);
		return false;
  }
  	//Added for CR_84
  	document.forms[0].hdnSelSpecType.value=compMapForm.specTypeNo.options[compMapForm.specTypeNo.selectedIndex].text;
  	document.forms[0].hdnSelModel.value=compMapForm.modelSeqNo.options[compMapForm.modelSeqNo.selectedIndex].text;
  	document.forms[0].hdnSelSec.value=compMapForm.sectionSeqNo.options[compMapForm.sectionSeqNo.selectedIndex].text;
  	document.forms[0].hdnSelSubSec.value=compMapForm.subSectionSeqNo.options[compMapForm.subSectionSeqNo.selectedIndex].text;
  	document.forms[0].hdnSelCompGrp.value=compMapForm.compGrpSeqNo.options[compMapForm.compGrpSeqNo.selectedIndex].text;
  	
  	document.forms[0].action="CompMapAction.do?method=fetchCompMap";
	document.forms[0].submit();
  
}

function fnSaveComponentMapping(){
		
		compSeqNochecked = false;
		defaultFlagChecked = false;
		compDefaultFlagChecked = false;
		
		var compMapForm = document.forms[0];
		var applyToModelFlagArray = document.getElementsByName("applyToModelFlag");
		
		for(var count = 0; count < applyToModelFlagArray.length ; count++)
		{
			if(applyToModelFlagArray[count].checked){
				compSeqNochecked = true;
			}
			
			if(compSeqNochecked == true)
			{
				break;
			}
		}
		
		if (compSeqNochecked == false)
		{
			var id= '184';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("applyToModelFlag",-1);
			return false;
		}else{ // start of else
			var hdnApplyModelArray = document.getElementsByName("hdnApplyModel");
			var hdnFormValue = document.getElementsByName("hdnCompValues");
		    var hdnRadioValue = document.getElementsByName("hdnDefaultFlag");
		    var defaultFlagArray = document.getElementsByName("defaultFlag");
			
			var ValidFlag=document.forms[0].validFlag.value;
			var validFlagRadio = $('#validFlagYes');//Added for CR-127

			//Check For Radio Button Selection
			for(var cnt = 0; cnt<defaultFlagArray.length; cnt++)
			{
				if(defaultFlagArray[cnt].checked){
					defaultFlagChecked = true;
				}
			
				if(defaultFlagChecked == true)
				{
					break;
				}
			}
			
			if(!defaultFlagChecked)
			{
	     		//if(ValidFlag=="Y"){
	     		if(validFlagRadio.is(':checked')){ //Added for CR-127 
					var id= '185';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("defaultFlag",-1);
					return false;
				}
			}
			else
			{
				//Check For Radio Button Selection For Applied Component Model
				for(var count = 0; count < applyToModelFlagArray.length ; count++)
				{
					if(applyToModelFlagArray[count].checked){
						if(defaultFlagArray[count].checked){
				           compDefaultFlagChecked = true;
				           break;
				        }
					}
				}
				
								
				if(!compDefaultFlagChecked){
					var id= '186';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("defaultFlag",-1);;
					return false;
				}
			}
			
			

			for(var j=0;j<applyToModelFlagArray.length;j++)
			{
				hdnApplyModelArray[j].value = applyToModelFlagArray[j].value;
			}		
			if(applyToModelFlagArray.length>0)
			 {	     
				  for(var i=0;i<applyToModelFlagArray.length;i++)
				  {
					   	if(applyToModelFlagArray[i].checked)
					   	{				   	
						   	if(defaultFlagArray[i].checked)
						   	{
						   		hdnRadioValue[i].value='Y';
						   	}else{
						   		hdnRadioValue[i].value='N';
						   	}
						   	hdnFormValue[i].value = '1';					   
				 	 	}else{			 	 	
					 	 	if(defaultFlagArray[i].checked)
						   	{
						   		hdnRadioValue[i].value='Y';
						   	}else{
						   		hdnRadioValue[i].value='N';
						   	}
					 	 	hdnFormValue[i].value = '0';				 	 	
				 	 	}
				 }
	 		}
	 		
			if(document.forms[0].hdnSelModel.value!= compMapForm.modelSeqNo.options[compMapForm.modelSeqNo.selectedIndex].text)
			{
				var id = '207';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("searchButton",-1);
				return false;
			}
		  	
		  	if(document.forms[0].hdnSelSec.value !=compMapForm.sectionSeqNo.options[compMapForm.sectionSeqNo.selectedIndex].text)
		  	{
		  		var id = '207';
				hideErrors();
				addMsgNum(id);
					showScrollErrors("searchButton",-1);
				return false;
		  	}
			
			if(document.forms[0].hdnSelSubSec.value != compMapForm.subSectionSeqNo.options[compMapForm.subSectionSeqNo.selectedIndex].text)
		    {
		    	var id = '207';
				hideErrors();
				addMsgNum(id);
					showScrollErrors("searchButton",-1);
				return false;
		    }
			if(document.forms[0].hdnSelCompGrp.value != compMapForm.compGrpSeqNo.options[compMapForm.compGrpSeqNo.selectedIndex].text)
			{
	  	 		var id = '207';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("searchButton",-1);
				return false;
			}

	 }//end of else 
	 
 	 var conMsg=confirm("Are you sure you want to map the component(s) to the selected Model, Section and Subsection");
		 if(conMsg == true)
		 {
		 	document.forms[0].action="CompMapAction.do?method=updateCompMap";
			document.forms[0].submit();
		 }
		 else
		 {
		 }
}
//Added for CR-46 PM&I Spec
function fnLoadModels(){

var compMapForm = document.forms[0];
//added for CR_97
var specTypeNo=$('select#specTypeSeqNo').val();
var compGrpType=$('select#compgrpSeqType').val();
if(compGrpType!=undefined){
compMapForm.compgrpCat.value=compGrpType;
}else{
compMapForm.compgrpCat.value="";
}


//compMapForm.hdnSelSpecType.value=compMapForm.specTypeNo.options[compMapForm.specTypeNo.selectedIndex].text;
compMapForm.hdnSelSpecType.value=specTypeNo;



document.forms[0].action="CompMapAction.do?method=fetchModels";
document.forms[0].submit();
}

//Added for CR-93Unmap Component Group
function fnViewComponentGrpMapping(compSourceflag){

  var compMapForm = document.forms[0];
  
  if (compMapForm.specTypeNo.options[compMapForm.specTypeNo.selectedIndex].value =="-1")
   {
	    var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("specTypeSeqNo",-1);
		return false;
   
	}
  
  if(compMapForm.modelSeqNo.options[compMapForm.modelSeqNo.selectedIndex].value=="-1"){
   		var id = '19';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltModel",-1);
		return false;
  }
  
  if(compMapForm.compGrpSeqNo.options[compMapForm.compGrpSeqNo.selectedIndex].value=="-1"){
   		var id = '183';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltComponent",-1);
		return false;
  }
  	document.forms[0].hdnSelSpecType.value=compMapForm.specTypeNo.options[compMapForm.specTypeNo.selectedIndex].text;
  	document.forms[0].hdnSelModel.value=compMapForm.modelSeqNo.options[compMapForm.modelSeqNo.selectedIndex].text;
  	document.forms[0].hdnSelCompGrp.value=compMapForm.compGrpSeqNo.options[compMapForm.compGrpSeqNo.selectedIndex].text;
  	document.forms[0].hdnSelSec.value="";
  	document.forms[0].hdnSelSubSec.value="";
  	//Added For CR_93 -"M"-For Model components ,"O" For Order components
  	if(compSourceflag=="M"){
  	document.forms[0].compSourceFlag.value="M";
  	}else if(compSourceflag=="O"){
  	document.forms[0].compSourceFlag.value="O";
  	}
  	document.forms[0].action="CompMapAction.do?method=viewCompGrpMapping";
    document.forms[0].submit();
  	
}



function fnUnmapComponentGrp(){


var conMsg=confirm("Are you sure to un-map this Component Group ?");
		 
		 if(conMsg == true)
			 {			 
			 document.forms[0].action="CompMapAction.do?method=unMapComponentGroup";
             document.forms[0].submit();			 
			 	
			 }
		 else
			 {
			 
			 }	 

}

function fnLoadComponentMap(){


           document.forms[0].action="CompMapAction.do?method=initLoad";
           document.forms[0].submit();	 
		 

}

/*	fnSearchCompGroups() - 
*	fetch ComponentGroups based on Component Group Types
*	Added for LSDB_CR_81
*	Locomotive and Power Products Enhancements
*	by RR68151 on 28-Dec-2009
*/

function fnSearchCompGroups(){
	
    document.forms[0].action="CompMapAction.do?method=fetchCompGroups";
	document.forms[0].submit();
	}
	
/*	fnAddComponentClauseToModel() - 
*	Copy Order Component and Clause to Model
*	Added for LSDB_CR_93
*	by SD41630 on 07-Dec-2010
*/

function fnAddComponentClauseToModel(){

		selectFlag=false;
		
		var cnt= document.forms[0].componentSeqNo.length;
		
		if(cnt>0){
		for(var i=0;i<cnt;i++){
		if(document.forms[0].componentSeqNo[i].checked){
		document.forms[0].componentSeqNo.value=document.forms[0].componentSeqNo[i].value;
		selectFlag=true;
		break;
		
			}
		}
		}else{
		if(document.forms[0].componentSeqNo.checked){
		document.forms[0].componentSeqNo.value=document.forms[0].componentSeqNo.value;
		selectFlag=true;
		}
		}
		
		if (selectFlag)	{		

		 var compMapForm = document.forms[0];
		 
		  	document.forms[0].hdnSelSec.value="";
		  	document.forms[0].hdnSelSubSec.value="";
		  	document.forms[0].action="CompMapAction.do?method=copyCompOrdrToMdl";
		    document.forms[0].submit();
  		}
  		else
  		{ 
  		var id = '823';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("componentSeqNo",-1);
		return false;
  		}
	}

/*	fnLoadNewClauseDetails() - 
*	To load the Modify clause screen with the newly added clause details
*	Added for LSDB_CR_93
*	by SD41630 on 07-Dec-2010
*/

function fnLoadNewClauseDetails(){
	
	document.forms[0].action="modelAddClauseAction.do?method=fetchClauseVersions&specTypeNo="+document.forms[0].specTypeNo.value
									+"&modelSeqNo="+document.forms[0].modelSeqNo.value+"&sectionSeqNo="+document.forms[0].sectionSeqNo.value
									+"&subSectionSeqNo="+document.forms[0].subSectionSeqNo.value+"&claSeqNo="+document.forms[0].newClaSeqNo.value+"";
	document.forms[0].submit();

}

/*	fnDeselect()
*	To deselect/select default component radio button
*	Added for LSDB_CR_114
*	by VB306565 on 31-Jan-2014
*/
function fnDeselect(){
var ele;

if($("#deSelect:checked").length > 0)
   {
   		ele = document.getElementsByName("defaultFlag");
		for(var i=0;i<ele.length;i++){
			if(ele[i].checked==true){
				$("#hdnSelectedRadio").val(i);
				ele[i].checked = false;
			}
		}
		$(".compseq").prop("disabled",true);
			
   }
else 
   {
          $(".compseq").prop("disabled",false);
          var index =$("#hdnSelectedRadio").val();
          ele = document.getElementsByName("defaultFlag");
          if (ele[index] != undefined)
	          ele[index].checked = true;
   }
}