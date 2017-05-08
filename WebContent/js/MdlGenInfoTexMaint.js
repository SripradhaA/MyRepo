//Added for CR_106

function fnSaveGenInfoTextDraftAndOpen(flag)
{
	var genInfoTextForm = document.forms[0];
var genInfoTextDraft=genInfoTextForm.genInfoTextDraft.value;
var genInfoTextOpen=genInfoTextForm.genInfoTextOpen.value;
	
	if ((genInfoTextDraft == "" )&& (flag=="D"))
		{ 
			var id = '927';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("genInfoTextDraft",-1);  
			if(genInfoTextDraft == "" ){
			genInfoTextForm.genInfoTextDraft.focus();
			}
			return false;
		}	
		
		
			if ((genInfoTextOpen=="") && (flag=="O"))
		{ 
			var id = '927';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("genInfoTextOpen",-1);
			if(genInfoTextOpen == "" ){
				genInfoTextForm.genInfoTextOpen.focus();
			}
			return false;
		}
	if(flag=="D")
		{
			 if (genInfoTextDraft.length > 4000) 
			{
				var id = '921';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("genInfoTextDraft",-1);
				return false;
			}	
			
			genInfoTextForm.genInfoTextOpen.value="";
			document.forms[0].action="GenInfoMaintByModel.do?method=updateGenInfoTextDraftAndOpen";
			document.forms[0].submit();		
			
		}else if(flag=="O"){
			
			if(genInfoTextOpen.length > 4000)
			{
				var id = '921';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("genInfoTextOpen",-1);
				
				return false;
			}
			
			genInfoTextForm.genInfoTextDraft.value="";
			document.forms[0].action="GenInfoMaintByModel.do?method=updateGenInfoTextDraftAndOpen";
			document.forms[0].submit();
	   }
		    
}
function fnLoadModels(){

var GenInfoTextForm = document.forms[0];

GenInfoTextForm.hdnSelSpecType.value=GenInfoTextForm.specTypeNo.options[GenInfoTextForm.specTypeNo.selectedIndex].text;
document.forms[0].action="GenInfoMaintByModel.do?method=fetchModels";
document.forms[0].submit();
}
function fetchGenInfoTextItems()
{

	 if (document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1")
   {
	    var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltSpecType",-1);
		return false;
   
	}
	
	if (document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value =="-1")
	   {
	 
			var id = '19';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("sltModel",-1);
			return false;
	   
		}

		
	document.forms[0].hdnModelName.value = document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].text;
	document.forms[0].hdnSelSpecType.value=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
	document.forms[0].hdnModelSeqNo.value = document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value ;
	document.forms[0].action="GenInfoMaintByModel.do?method=fetchGenInfoTextItems";
	document.forms[0].submit();

}
	



/**************  Ends here  ***************/