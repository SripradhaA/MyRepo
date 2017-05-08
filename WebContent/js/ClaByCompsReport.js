/*
* Ceated for CR_109 - Clauses By Components Report
*/
function keyHandler(e)
{ 
      var asciiValue = e ? e.which : window.event.keyCode;
	  if(asciiValue ==13){         
      	  if(document.forms[0].flag.value == "Y"){
      	  	fnClaByCompsReport();
          	return false;
          }
       }
 }

function fnLoadComponents()
{
	var ClaByCompsForm = document.forms[0];
	ClaByCompsForm.hdnSelCompGrp.value=ClaByCompsForm.compGrpSeqNo.options[ClaByCompsForm.compGrpSeqNo.selectedIndex].text;

	document.forms[0].action="ClaByCompsAction.do?method=fetchComps";
	document.forms[0].submit();
}


function fnClaByCompsReport()
{
	var ClaByCompsForm = document.forms[0];
	if(ClaByCompsForm.compGrpSeqNo.options[ClaByCompsForm.compGrpSeqNo.selectedIndex].value=="-1")
		{
				var id = '183';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("SltCompgrp",-1);
				return false;
		}
	if (ClaByCompsForm.componentSeqNo.options[ClaByCompsForm.componentSeqNo.selectedIndex].value=="-1")
		{	ClaByCompsForm.hdnCompName.value="";	}
	else
		{	ClaByCompsForm.hdnCompName.value=ClaByCompsForm.componentSeqNo.options[ClaByCompsForm.componentSeqNo.selectedIndex].text; }
	ClaByCompsForm.hdnSelCompGrp.value=ClaByCompsForm.compGrpSeqNo.options[ClaByCompsForm.compGrpSeqNo.selectedIndex].text;
	fnSort(3);
	document.forms[0].action="ClaByCompsAction.do?method=fetchClauses";
	document.forms[0].submit();
}


function fnClaByCompsReportExcel(){

var ClaByCompsForm = document.forms[0];
if(!((document.forms[0].hdnCompName.value == document.forms[0].componentSeqNo.options[document.forms[0].componentSeqNo.selectedIndex].text
	   					&& document.forms[0].componentSeqNo.options[document.forms[0].componentSeqNo.selectedIndex].value != "-1")
	   			|| ((document.forms[0].hdnCompName.value == "" || (document.forms[0].hdnCompName.value == document.forms[0].componentSeqNo.options[document.forms[0].componentSeqNo.selectedIndex].text)) && document.forms[0].componentSeqNo.options[document.forms[0].componentSeqNo.selectedIndex].value == "-1")))
	   {
			var id = '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("componentSeqNo",-1);
			return false;
	   }  
		
if (ClaByCompsForm.componentSeqNo.options[ClaByCompsForm.componentSeqNo.selectedIndex].value=="-1")
	{	ClaByCompsForm.hdnCompName.value="";	}
else
	{	ClaByCompsForm.hdnCompName.value=ClaByCompsForm.componentSeqNo.options[ClaByCompsForm.componentSeqNo.selectedIndex].text; }

	ClaByCompsForm.hdnSelCompGrp.value=ClaByCompsForm.compGrpSeqNo.options[ClaByCompsForm.compGrpSeqNo.selectedIndex].text;

	document.forms[0].action="ClaByCompsAction.do?method=fetchClausesforExcel";
	document.forms[0].submit();
}

//Added for CR_109 Comments - to Sort the report using Model and component name.

function fnSort(sortKey){	
	document.forms[0].sortByFlag.value=sortKey;		
	document.forms[0].action="ClaByCompsAction.do?method=fetchClauses";		
	document.forms[0].submit();
    
}

