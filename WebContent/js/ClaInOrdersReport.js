function fetchModels(){
	
	document.forms[0].action="modelAddClauseAction.do?method=fetchModels";
	document.forms[0].submit();

}

/*
*	Name: fnLoadSection
*	Purpose: Used to Load Section List on selection of ModelList
*	
*/
function fnLoadSection()
{
document.forms['ModelAddClauseForm'].action="modelAddClauseAction.do?method=SectionLoad";
document.forms['ModelAddClauseForm'].submit();
}

/*
*	Name: fnLoadSubSection
*	Purpose: Used to Load Sub Section List on selection of SectionList
*	
*/
function fnLoadSubSection()
{
document.forms['ModelAddClauseForm'].action="modelAddClauseAction.do?method=SubSectionLoad";
document.forms['ModelAddClauseForm'].submit();
}

/*
*	Name: fnLoadCompGroup
*	Purpose: Used to Load ComponentGroup List on selection of SubSectionList
*	
*/
function fnLoadCompGroup()
{
        if(document.forms[0].modifyClauseFlag.value == "N")document.forms[0].modifyClauseFlag.value="Y"
		document.forms[0].action="modelAddClauseAction.do?method=loadComponentGroup";
		document.forms[0].submit();
}

/*
*	Name: fnFetchClauseversions
*	Purpose: Used to fetch Clause Versions for the selected Clause
*	
*/

function fnFetchClauseversions(){

var ModClause=document.forms[0];

if(document.forms[0].clauseSeqNo.length>1){

  	 for(i=0;i<document.forms[0].clauseSeqNo.length;i++){
     
			if(document.forms[0].clauseSeqNo[i].checked){
				ModClause.hdnClauseSeqNo.value = document.forms[0].clauseSeqNo[i].value;		
				}

		   }
		
		}else{
		
		    if(document.forms[0].clauseSeqNo.checked){
				ModClause.hdnClauseSeqNo.value = document.forms[0].clauseSeqNo.value;
				}

		}
document.forms[0].action="modelAddClauseAction.do?method=fetchClauseVersions";
document.forms[0].submit();
}
//Updated for CR-121
function fnSearch(windowflag)
{
var ModClause=document.forms[0];
var versionChecked = false;

if(document.forms[0].versionNo.length>1){

 for(i=0;i<document.forms[0].versionNo.length;i++){
 
		if(document.forms[0].versionNo[i].checked){
			versionChecked = true;
			ModClause.hdnClauseVersionNo.value = document.forms[0].versionNo[i].value;		
			}

	   }
	
	}else{
	
	    if(document.forms[0].versionNo.checked){
			versionChecked = true;
			ModClause.hdnClauseVersionNo.value = document.forms[0].versionNo.value;
			}

	}
	
if(!versionChecked)
	{	 
		var id = '889';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("versionNoRadio",-1);   
	}
else
	{
		if(windowflag == 'N'){
			ModClause.hdnSelectedSpecType.value=ModClause.specTypeNo.options[ModClause.specTypeNo.selectedIndex].text;
			document.ModelAddClauseForm.hdnSelectedModel.value=document.ModelAddClauseForm.modelSeqNo.options[document.ModelAddClauseForm.modelSeqNo.selectedIndex].text;
			document.ModelAddClauseForm.hdnSelectedSection.value=document.ModelAddClauseForm.sectionSeqNo.options[document.ModelAddClauseForm.sectionSeqNo.selectedIndex].text;
			document.ModelAddClauseForm.hdnSelectedSubSection.value=document.ModelAddClauseForm.subSectionSeqNo.options[document.ModelAddClauseForm.subSectionSeqNo.selectedIndex].text;
			document.forms[0].action="modelAddClauseAction.do?method=search&Excel=N";
			document.forms[0].submit();
		}else{ //added for CR-121
			document.forms[0].action = "modelAddClauseAction.do?method=search&Excel=N&windowflag="+windowflag+"&screenid=46#listorders";
	       	document.forms[0].target = "_blank";
	        document.forms[0].submit();
	        document.forms[0].target = "_self";
		}	
	}

}

function fnClaInOrdersReportExcel()
{
//Added for CR-113 QA fix  
  var previousVersionNO = document.forms[0].hdnClauseVersionNo.value;
       var currentVersionNO;
       for(i=0;i<document.forms[0].versionNo.length;i++){
     		if(document.forms[0].versionNo[i].checked){
				document.forms[0].hdnClauseVersionNo.value = document.forms[0].versionNo[i].value;
				currentVersionNO = document.forms[0].versionNo[i].value;
				}
        }
      // alert("currentVersionNO:"+currentVersionNO+"previousVersionNO:"+previousVersionNO); 

	   if(!((document.forms[0].showLatSpecFlag.checked && document.forms[0].hdnShowLatSpecFlag.value == "Yes")
	   			|| (!(document.forms[0].showLatSpecFlag.checked) && document.forms[0].hdnShowLatSpecFlag.value == "No")))
	   {
	   		var id = '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("searchButton",-1);
			return false;
	   }else if(currentVersionNO!=previousVersionNO) {
		    var id = '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("searchButton",-1);
			return false;
	   } // Added for CR-113 QA fix  ends here
	   else{
 	var ModClause=document.forms[0];
	if(document.forms[0].versionNo.length>1){

	 for(i=0;i<document.forms[0].versionNo.length;i++){
 
		if(document.forms[0].versionNo[i].checked){
			ModClause.hdnClauseVersionNo.value = document.forms[0].versionNo[i].value;		
			}

	   }
	
	}else{
	
	    if(document.forms[0].versionNo.checked){
			ModClause.hdnClauseVersionNo.value = document.forms[0].versionNo.value;
			}

	}
	ModClause.hdnSelectedSpecType.value=ModClause.specTypeNo.options[ModClause.specTypeNo.selectedIndex].text;
	document.ModelAddClauseForm.hdnSelectedModel.value=document.ModelAddClauseForm.modelSeqNo.options[document.ModelAddClauseForm.modelSeqNo.selectedIndex].text;
	document.ModelAddClauseForm.hdnSelectedSection.value=document.ModelAddClauseForm.sectionSeqNo.options[document.ModelAddClauseForm.sectionSeqNo.selectedIndex].text;
	document.ModelAddClauseForm.hdnSelectedSubSection.value=document.ModelAddClauseForm.subSectionSeqNo.options[document.ModelAddClauseForm.subSectionSeqNo.selectedIndex].text;
	document.forms[0].action="modelAddClauseAction.do?method=search&Excel=Y";
  	document.forms[0].submit();
    }
 
}
//Added for CR-121
$(document).ready(function() {
 var windowFlag;
 var windowFlag = getParameterByName('windowflag');
 if(windowFlag == 'Y'){
 	document.forms[0].showLatSpecFlag.checked = true;
 }
	
})

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}	
//Added for CR-121 ends here


