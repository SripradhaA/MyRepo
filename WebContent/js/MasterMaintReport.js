 function fnViewSpecByModel()
	{
	//Issue fixed along with CRForm
	var selectedModel=document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value;
	

	var modelName=MasterMaintForm.modelSeqNo.options[MasterMaintForm.modelSeqNo.selectedIndex].text;
	
	//Added for CR-46 PM&I Spec
    var specType=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
   
   
	//Added for CR-113 @vipul to turn on/off deleted clause versions  
   	var showDelClauses="N";
   
   	if(document.forms[0].chkBoxStatus.checked)
   		showDelClauses="Y";

	if (document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1")
   {
	    var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("selectSpecType",-1);
		return false;
   
	} 
	 
	 
	 if(document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value=="-1"){
   		var id = '19';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("selectModel",-1);
		return false;
  }
	else
	{
	  
	  var URL="MasterMaintPopUpAction.do?method=fetchSpecByModel&selectedModel="+selectedModel+"&modelName="+modelName+"&selectedSpecType="+specType+"&screenid=4"+"&showDelClause="+showDelClauses;
	  window.open(URL,'ViewSpec',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH="+screen.width+",height=600"); 
	 
	}
   }
   
    function fnViewSpecByModelExcel()
    {
	//Issue fixed along with CRform
	var selectedModel=document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value;
	var modelName=MasterMaintForm.modelSeqNo.options[MasterMaintForm.modelSeqNo.selectedIndex].text;

	//Added for CR-46 PM&I Spec
    var specType=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
    
	//Added for CR-113 @vipul to turn on/off deleted clause versions
    var showDelClauses="N";  

   	if(document.forms[0].chkBoxStatus.checked)
   		showDelClauses="Y";
	
	if (document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1")
   {
	    var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("selectSpecType",-1);
		return false;
   
	} 
	 
	 
	 if(document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value=="-1"){
   		var id = '19';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("selectModel",-1);
		return false;
  }
	else
	{
	  
   	  /*var URL="ViewSpecByModel.do?method=fetchSpecByModel&selectedModel="+selectedModel+"&modelName="+modelName+"&selectedSpecType="+specType+"&screenid=4"+"&showDelClause="+showDelClauses;
	  widow.open(URL,'ExportExcel',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH="+screen.width+",height=600"); */
	 //Added for CR-128	
	   document.forms[0].action = "MasterMaintReportAction.do?method=fetchMasterSpecExcel&selectedModel="+selectedModel+"&modelName="+modelName+"&selectedSpecType="+specType+"&screenid=4"+"&showDelClause="+showDelClauses;
   	   document.forms[0].submit();
	 
	}
   }

   //Added for CR-46 PM&I Spec
function fnLoadModels(){


document.forms[0].action="MasterMaintReportAction.do?method=fetchModels";
document.forms[0].submit();
}
  
   