function fnMasterSpecByModelExcel(){
var compComparisonForm = document.forms[0];	
		var specTypeNo=$('select#specTypeSeqNo').val();
var specTypeName=$('select#specTypeSeqNo option:selected').text();
compComparisonForm.hdnSelSpecTypeName.value=specTypeName;
			var strSelModelNos = [];
var strSelModelNames = [];
$('#selModelSeqNos :selected').each(function(i, selected) {
    strSelModelNos[i] = $(selected).val();
    strSelModelNames[i] = $(selected).text();
});	
	
if(strSelModelNames!="")
{
document.forms[0].hdnSelectedMdlNames.value=strSelModelNames;

}
	
	if(document.forms[0].modelSeqNos.selectedIndex=="-1"){
				var id = '19';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("selModelSeqNos",-1);
				return false;
		}
var  incOrdValidateFlag="false";
		if (document.forms[0].includeOrderFlag.checked)		
			{incOrdValidateFlag='true';}
		
		document.forms[0].specTypeNo.value=specTypeNo;
		document.forms[0].action="CompGroupAction.do?method=exportCompGrpReportExcel";//Method Updated for CR-128
		document.forms[0].submit();
}

//Added For CR_97 
function fnSearchCompGrpReport(){
var compComparisonForm = document.forms[0];

	var specType=$('select#specTypeSeqNo').val();
	var specTypeName=$('select#specTypeSeqNo option:selected').text();
	compComparisonForm.hdnSelSpecTypeName.value=specTypeName;
	
	if(compComparisonForm.modelSeqNos.selectedIndex=="-1"){
				var id = '19';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("selModelSeqNos",-1);
				return false;
		}
	
	
	/*
var len  = document.all["modelSeqNos"].options.length;
var modelnames="";
for(i=0;i<len;i++)
{
if(document.all["modelSeqNos"].options[i].selected==true)
{
if(modelnames=="")
{
modelnames=document.all["modelSeqNos"].options[i].text;
}
else
{
modelnames=modelnames+", "+document.all["modelSeqNos"].options[i].text;
}

}
}
if(modelnames!="")
{
document.forms[0].hdnSelectedMdlNames.value=modelnames;

}*/


	var strSelModelNos = [];
var strSelModelNames = [];
$('#selModelSeqNos :selected').each(function(i, selected) {
    strSelModelNos[i] = $(selected).val();
    strSelModelNames[i] = $(selected).text();
});	

if(strSelModelNames!="")
{
document.forms[0].hdnSelectedMdlNames.value=strSelModelNames;

}
		document.forms[0].specTypeNo.value=specType;
		document.forms[0].action="CompGroupAction.do?method=fetchCompGrpReport";
		document.forms[0].submit();
	}


//Added For CR_97 
function fnLoadModelsAndCompGrpReport(){
var compComparisonForm = document.forms[0];

    var specType=$('select#specTypeSeqNo').val();
		document.forms[0].specTypeNo.value=specType;
		
		
		document.forms[0].action="CompGroupAction.do?method=initLoadSpecTypesAndModels";
		document.forms[0].submit();
	}
	
