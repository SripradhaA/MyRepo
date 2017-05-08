/***
*	fnfetchSections is used to load the section drop down based on models.
**/

function fnfetchSections()
{
	//Added For CR_80 by RR68151
	clearAll(document.forms[0]);
	document.forms[0].action="CreateChangeReqClauseAction.do?method=fetchSections";
	document.forms[0].submit();
}


/***
*	fnfetchSubSections is used to load the subsection drop down based on section.
**/

function fnfetchSubSections()
{
	//Added For CR_80 by RR68151
	clearAll(document.forms[0]);
	document.forms[0].action="CreateChangeReqClauseAction.do?method=fetchSubSections";
	document.forms[0].submit();
}


/*
*	Name: LoadParentClauses
*	Purpose: Used to Load LoadParentClauseByModel.jsp and load its initial parameters
*	
*/
function fnLoadFromClause()
{
	var mod=document.forms[0];
	var selectedArray=new Array();	
	var selectedModelID=document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value;
	var selectedSectionID=document.forms[0].sectionSeqNo.options[document.forms[0].sectionSeqNo.selectedIndex].value;
	//var	selectedSubSectionID=document.forms[0].subSectionSeqNo.options[document.forms[0].subSectionSeqNo.selectedIndex].value;
	var subSecID=document.forms[0].subSectionSeqNo.options[document.forms[0].subSectionSeqNo.selectedIndex].value;
	var selectedModelName= document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].text;
	var selectedSectionName=document.forms[0].sectionSeqNo.options[document.forms[0].sectionSeqNo.selectedIndex].text;
	var selectedSubSectionName=document.forms[0].subSectionSeqNo.options[document.forms[0].subSectionSeqNo.selectedIndex].text;
	
	
	mod.hdnModelSeqNo.value=mod.modelSeqNo.options[mod.modelSeqNo.selectedIndex].value;
	mod.hdnSectionSeqNo.value=mod.sectionSeqNo.options[mod.sectionSeqNo.selectedIndex].value;
	mod.hdnSubSectionSeqNo.value=mod.subSectionSeqNo.options[mod.subSectionSeqNo.selectedIndex].value;
	var CompGrpSeqNo=mod.hdnCompGrpSeqNo.value;
	var CompSeqNo=mod.hdnCompSeqNo.value;
	var ChangeTypeSeqNo;
	

	for (i=0; i<mod.changeTypeSeqNO.length; i++)

      {
         if (mod.changeTypeSeqNO[i].checked==true)
         {
            ChangeTypeSeqNo = mod.changeTypeSeqNO[i].value;
         }
      }
	// Added For CR_80 to add Specification Type Dropdown
	if(document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1"){
	
		var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("specType",-1);
	}
	// Added For CR_80 to add Specification Type Dropdown - Ends here
	 else if (document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value =="-1"){
	
		var id = '19';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("modelSeqNo",-1);
	}else if (document.forms[0].sectionSeqNo.options[document.forms[0].sectionSeqNo.selectedIndex].value =="-1"){
	
		var id = '205';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sectionSeqNo",-1);
		   
	}

	

	else if(document.forms[0].subSectionSeqNo.selectedIndex==0)
	{
	var id = '182';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("subSectionSeqNo",-1);
	
	}

	else
	{
	var CompGrpChngTypeSeqNo = document.forms[0].hdnCompGrpChnTypeSeqNo.value;
		
	window.open("CreateChangeReqClauseAction.do?method=selectFromClause&selectedModelName="+escape(selectedModelName)+"&selectedSectionName="+escape(selectedSectionName)+"&selectedSubSectionName="+escape(selectedSubSectionName)+"&selectedSubSectionID="+subSecID+"&selectedSectionID="+selectedSectionID+"&selectedModelID="+selectedModelID+"&CompGrpSeqNo="+CompGrpSeqNo+"&CompSeqNo="+CompSeqNo+"&ChangeTypeSeqNo="+ChangeTypeSeqNo+"&CompGrpChngTypeSeqNo="+CompGrpChngTypeSeqNo+"","Clause","location=0,resizable=no,status=0,scrollbars=1,width=780,height=600"); 
	}
}

/**
*	Name:fnSelectClauses
**	Purpose: 
		fnSelectClauses is used to populate the selected clause values from the
		selectFromClause pop-up screen to the parent screen.
		Finally it will call the parentSubmit function to populate the values in parent screen.
**/

function fnSelectClauses()
{


var selectedClause=document.forms[0].clauseSeqNo;
var selectedClauseDesc;
var flag = false;
var tableData= new Array();
if (selectedClause==null)
{
	var id = '16';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("clauseSeqNo",-1);
	return false;
}
else
{
if (selectedClause.length!=undefined)
{
	for (i=0;i<selectedClause.length;i++)
	{
		
		if (selectedClause[i].checked)
		{
			window.opener.document.forms[0].selectedClaSeqNo.value=document.forms[0].clauseSeqNo[i].value;
			window.opener.document.forms[0].versionNo.value=document.forms[0].versionNo[i].value;
			window.opener.document.forms[0].clauseNo.value=document.forms[0].clauseNum[i].value;
			window.opener.document.forms[0].hdnModelSeqNo.value=document.forms[0].hdnModelSeqNo.value;
			window.opener.document.forms[0].hdnSubSectionSeqNo.value=document.forms[0].hdnSubSectionSeqNo.value;
			if(document.forms[0].changeTypeSeqNO.value==10)
				window.opener.document.forms[0].hdnVersionNo.value=document.forms[0].versionNo[i].value;			
		
  			flag = true;
  			break;
		}
	}
}
else
{
	if(selectedClause.checked)
	{
		
		window.opener.document.forms[0].selectedClaSeqNo.value=document.forms[0].clauseSeqNo.value;
		window.opener.document.forms[0].versionNo.value=document.forms[0].versionNo.value;
		window.opener.document.forms[0].clauseNo.value=document.forms[0].clauseNum.value;
		window.opener.document.forms[0].hdnModelSeqNo.value=document.forms[0].hdnModelSeqNo.value;
		window.opener.document.forms[0].hdnSubSectionSeqNo.value=document.forms[0].hdnSubSectionSeqNo.value;
		if(document.forms[0].changeTypeSeqNO.value==10)
			window.opener.document.forms[0].hdnVersionNo.value=document.forms[0].versionNo.value;
	
		flag = true;		
	}
}
}
	if(flag){
		window.opener.document.forms[0].toClaVerClauseNo.value="";
		window.opener.parentsubmit();
		window.close();
		
	
	}else{
	
		var id = '520';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("clauseSeqNo",-1);	
	}

	
}

/**
**	fnCancel used to close the pop-up screens.
***/

function fnCancel()
{
	window.close();
}

/**
**	parentsubmit is used to call the another action after selecting the clauses from selectfromclause screen.
**	This function will call the ModelSelectClause Procedure in action for populating the values in From Clause part.
***/

function parentsubmit()
{
		document.forms[0].hdnClauseSeqNo.value=document.forms[0].selectedClaSeqNo.value;
		document.forms[0].hdnVersionNo.value=document.forms[0].versionNo.value;
		document.forms[0].action="CreateChangeReqClauseAction.do?method=fetchDefaultVersion";
		document.forms[0].submit();
}


/**
** fnSaveClause is used to save the Request Clause Details.
**/

function fnSaveClause()
{

if ((document.forms[0].hdnCompGrpChnTypeSeqNo.value != 8) && (document.forms[0].hdnCompChnTypeSeqNo.value != 8))	{

	var trcomp="";
	var compval="";
var mod=document.forms[0];
var frmVersionNo = trim(mod.versionNo.value);
var toClauseDesc = trim(mod.toClauseDesc.value);
var toClauseNo = trim(mod.toClauseNo.value);
var todwONumber = trim(mod.todwONumber.value);
var toPartNumber=trim(mod.toPartNumber.value);
var topriceBookNumber=trim(mod.topriceBookNumber.value);
var toComments=trim(mod.toComments.value);
var reasons=trim(mod.reason.value);
var toRefEDLNo1=trim(mod.toRefEDLNo[0].value);
var toRefEDLNo2=trim(mod.toRefEDLNo[1].value);
var toRefEDLNo3=trim(mod.toRefEDLNo[2].value);
var toRefEDLNo=toRefEDLNo1+toRefEDLNo2+toRefEDLNo3;
var toEdlNo1=trim(mod.toEdlNo[0].value);
var toEdlNo2=trim(mod.toEdlNo[1].value);
var toEdlNo3=trim(mod.toEdlNo[2].value);
var toEdlNo=toEdlNo1+toEdlNo2+toEdlNo3;
var partOfSeqNo1=trim(mod.partOfCode[0].value);
var partOfSeqNo2=trim(mod.partOfCode[1].value);
var partOfSeqNo3=trim(mod.partOfCode[2].value);
var partOfSeqNo4=trim(mod.partOfCode[3].value);

mod.hdnClauseSeqNo.value=mod.selectedClaSeqNo.value;
//mod.hdnVersionNo.value=mod.versionNo.value;
mod.toClauseNo.value=toClauseNo;
mod.toClauseDesc.value=toClauseDesc;
mod.toComments.value=toComments;
mod.reason.value=reasons;
mod.todwONumber.value=todwONumber;
mod.toPartNumber.value=toPartNumber;
mod.topriceBookNumber.value=topriceBookNumber;
mod.toEdlNo[0].value=toEdlNo1;
mod.toEdlNo[2].value=toEdlNo3;
mod.toRefEDLNo[0].value=toRefEDLNo1;
mod.toRefEDLNo[1].value=toRefEDLNo2;
mod.toRefEDLNo[2].value=toRefEDLNo3;

mod.hdnModelSeqNo.value=mod.modelSeqNo.options[mod.modelSeqNo.selectedIndex].value;
mod.hdnSectionSeqNo.value=mod.sectionSeqNo.options[mod.sectionSeqNo.selectedIndex].value;
mod.hdnSubSectionSeqNo.value=mod.subSectionSeqNo.options[mod.subSectionSeqNo.selectedIndex].value;
var adminComments=trim(mod.adminComments.value);

mod.hdnAdminComments.value=adminComments;

var flag = true;
var compSeqNo;
var changeType;
var confirm;



var hdnStatusSeqNo = mod.hdnStatusTypeSeqNo.value;
var roleID=mod.roleID.value;
	
var requestDesc=trim(mod.requestDesc.value);
mod.requestDesc.value=requestDesc;
		
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
if(mod.specTypeNo.options[mod.specTypeNo.selectedIndex].value =="-1"){

	var id = '2';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("specTypeNo",-1);
	return false;

}
// Added For CR_80 to add Specification Type Dropdown - Ends here
	
if (mod.modelSeqNo.options[mod.modelSeqNo.selectedIndex].value =="-1")
{
   
	var id = '19';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("modelSeqNo",-1);
	return false;
	   
}

if (mod.sectionSeqNo.options.length<=1)
{
	var id = '519';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sectionSeqNo",-1);
	return false;
}

if (mod.sectionSeqNo.options[mod.sectionSeqNo.selectedIndex].value =="-1")
{	
	var id = '205';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sectionSeqNo",-1);
	return false;
	   
}

if (mod.subSectionSeqNo.options.length<=1)
{
	var id = '511';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("subSectionSeqNo",-1);
	return false;
}
if (mod.subSectionSeqNo.options[mod.subSectionSeqNo.selectedIndex].value =="-1")
{	
	var id = '182';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("subSectionSeqNo",-1);
	return false;
	   
}

for (i=0; i<mod.changeTypeSeqNO.length; i++)

      {
         if (mod.changeTypeSeqNO[i].checked==true)
         {
            changeType = mod.changeTypeSeqNO[i].value;
         }
      }

if (changeType==6){

	 if(mod.selectedClaSeqNo.value==0 || mod.selectedClaSeqNo.value=="" )
		{
			
				var id = '852';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("searchicon",-1);
					
				return false;
		} 

	if(mod.toClaVerClauseNo.value=="" || mod.toClaVerDesc.value==""){

		var id = '857';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("toClaVerClauseNo",-1);
		return false;
	}

	/*	Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
	if (mod.toDefaultFlag[1].checked==true){
		confirm = window.confirm("Are you sure to set Default version of the clause as 'No'?");
		if(!confirm){
			flag = false;
		}
		}	*/
		
		if(frmVersionNo == mod.toClaVerClauseNo.value) {
	
			var id = '868';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("toClaVerClauseNo",-1);
			return false;
		}
		
}



if(changeType==4 || changeType==5){

if(changeType==5)
	{
		 if(mod.selectedClaSeqNo.value==0 || mod.selectedClaSeqNo.value=="" )
		{
			
				var id = '852';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("searchicon",-1);
				
				return false;
		} 
	}
/*
 if(toClauseDesc.length==0 || toClauseDesc=="" )
{
	
		var id = '854';
		hideErrors();
		addMsgNum(id);
		showErrors();
		
		mod.toClauseDesc.value="";
		mod.toClauseDesc.select();
		mod.toClauseDesc.focus();
		return false;
}

 if(toClauseDesc.length > 4000)
{
	
		var id = '855';
		hideErrors();
		addMsgNum(id);
		showErrors();
		
		mod.toClauseDesc.select();
		mod.toClauseDesc.focus();
		return false;
}*/
/* Added For CR_88 */
if ($('#clauseDesc_id').val().length == 0) {

		var id = '506';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("clauseDesc_id_ifr",-1);
		return false;
}

/* Commented for CR-121
if ($('#clauseDesc_id').val().length > 4000) {

		var id = '516';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("clauseDesc_id",-1);
		
		tinymce.execCommand('mceFocus',false,'clauseDesc_id');
		return false;
}*/
/* Added For CR_88 - Ends here */
if (document.forms[0].clauseTableDataArray1!=null)
{
for (var i=0;i<document.forms[0].clauseTableDataArray1.length;i++)
{
document.forms[0].clauseTableDataArray1[i].value=trim(document.forms[0].clauseTableDataArray1[i].value);
}
}
if (document.forms[0].clauseTableDataArray2!=null)
{
for (var i=0;i<document.forms[0].clauseTableDataArray2.length;i++)
{
document.forms[0].clauseTableDataArray2[i].value=trim(document.forms[0].clauseTableDataArray2[i].value);
}
}
if (document.forms[0].clauseTableDataArray3!=null)
{
for (var i=0;i<document.forms[0].clauseTableDataArray3.length;i++)
{
document.forms[0].clauseTableDataArray3[i].value=trim(document.forms[0].clauseTableDataArray3[i].value);
}
}
if (document.forms[0].clauseTableDataArray4!=null)
{
for (var i=0;i<document.forms[0].clauseTableDataArray4.length;i++)
{
document.forms[0].clauseTableDataArray4[i].value=trim(document.forms[0].clauseTableDataArray4[i].value);
}
}
if (document.forms[0].clauseTableDataArray5!=null)
{
for (var i=0;i<document.forms[0].clauseTableDataArray5.length;i++)
{
document.forms[0].clauseTableDataArray5[i].value=trim(document.forms[0].clauseTableDataArray5[i].value);
}
}

if (document.forms[0].clauseTableDataArray1!=null)
{
if (document.forms[0].clauseTableDataArray1.length!=undefined)
{

for (var i=0;i<document.forms[0].clauseTableDataArray1.length;i++)
{
if ((document.forms[0].clauseTableDataArray1[i].value=="")&&(document.forms[0].clauseTableDataArray2[i].value=="")&&(document.forms[0].clauseTableDataArray3[i].value=="")&&(document.forms[0].clauseTableDataArray4[i].value=="")&&(document.forms[0].clauseTableDataArray5[i].value==""))
{
		var id = '526';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("clauseTableDataArray1",i);
		return false;
		
}
}
}
else
{
if ((trim(document.forms[0].clauseTableDataArray1.value)=="")&&(trim(document.forms[0].clauseTableDataArray2.value)=="")&&(trim(document.forms[0].clauseTableDataArray3.value)=="")&&(trim(document.forms[0].clauseTableDataArray4.value)=="")&&(trim(document.forms[0].clauseTableDataArray5.value)==""))
{
		
		var id = '526';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("clauseTableDataArray1",-1);
		return false;
		
}
}
}

if ((mod.standardEquipmentSeqNo.options[mod.standardEquipmentSeqNo.selectedIndex].value =="-1")&&(toRefEDLNo1=="")&&(toRefEDLNo2=="")&&(toRefEDLNo3=="")&&(toEdlNo1=="")&&(toEdlNo2=="")&&(toEdlNo3=="")&&(partOfSeqNo1.length==0 ||partOfSeqNo1==0)&&(partOfSeqNo2.length==0 ||partOfSeqNo2==0)&&(partOfSeqNo3.length==0 ||partOfSeqNo3==0)&&(partOfSeqNo4.length==0 ||partOfSeqNo4==0)&&(todwONumber.length==0 || todwONumber=="" )&&(toPartNumber.length==0 || toPartNumber=="" )&&(topriceBookNumber.length==0 || topriceBookNumber=="" )&&(toComments.length==0 || toComments==""))
{
		var id = '505';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("toRefEDLNo",0);
		return false;
}
else
{


if ((toRefEDLNo1<0 ) && (toRefEDLNo2<0||toRefEDLNo2.length==0) && (toRefEDLNo3<0||toRefEDLNo3.length==0))
{
	if (toRefEDLNo1!="")
	{			
			var id = '528';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("toRefEDLNo",0);
		
			return false;
	}
}
if (toRefEDLNo1.length!=0 ||toRefEDLNo1!="") 
	{
		if (toRefEDLNo1<0)
		{			
			var id = '528';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("toRefEDLNo",0);
		
			return false;
		}
		if (toRefEDLNo1==toRefEDLNo2||toRefEDLNo1==toRefEDLNo3)
		{
			var id = '510';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("toRefEDLNo",0);
		
			return false;

		}
		

	}

if ((toRefEDLNo2<0) &&((toRefEDLNo1<0)||(toRefEDLNo1.length==0)) &&((toRefEDLNo3<0)||(toRefEDLNo3.length==0)))
{
			if (toRefEDLNo2!="")
			{
			var id = '528';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("toRefEDLNo",1);
		
			return false;
			}
}
	if (toRefEDLNo2.length!=0 || toRefEDLNo2!="") 
	{
		if (toRefEDLNo2<0)
		{			
			var id = '528';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("toRefEDLNo",1);
		
			return false;
		}
		if (toRefEDLNo1==toRefEDLNo2 || toRefEDLNo2==toRefEDLNo3)
		{
			var id = '510';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("toRefEDLNo",1);
		
			return false;

		}

	}

if ((toRefEDLNo3<0) &&((toRefEDLNo2<0)||(toRefEDLNo2.length==0)) &&((toRefEDLNo1<0)||(toRefEDLNo1.length==0)))
{
			if (toRefEDLNo3!="")
			{
			var id = '528';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("toRefEDLNo",2);
		
			return false;
			}
}
	if (toRefEDLNo3.length!=0 || toRefEDLNo3!="") 
	{
		if (toRefEDLNo3<0)
		{			
			var id = '528';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("toRefEDLNo",2);
		
			return false;
		}
		
		if (toRefEDLNo2==toRefEDLNo3 || toRefEDLNo1==toRefEDLNo3)
		{
			var id = '510';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("toRefEDLNo",2);
		
			return false;

		}

	}
	

if ((toEdlNo1<0 ) && (toEdlNo2<0||toEdlNo2.length==0) && (toEdlNo3<0||toEdlNo3.length==0))
{
	if (toEdlNo1!="")
	{			
			var id = '529';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("toEdlNo",0);
		
			return false;
	}
}


if (toEdlNo1.length!=0 || toEdlNo1!="") 
	{
		if (toEdlNo1<0)
		{			
			var id = '529';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("toEdlNo",0);
		
			return false;
		}
		if (toEdlNo1==toEdlNo2 || toEdlNo1==toEdlNo3)
		{
			var id = '509';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("toEdlNo",0);					
			return false;

		}

	}

if ((toEdlNo2<0 ) && (toEdlNo1<0||toEdlNo1.length==0) && (toEdlNo3<0||toEdlNo3.length==0))
{
	if (toEdlNo2!="")
	{			
			var id = '529';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("toEdlNo",1);
		
			return false;
	}
}
	if (toEdlNo2.length!=0 ||toEdlNo2!="") 
	{
		if (toEdlNo2<0)
		{			
			var id = '529';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("toEdlNo",1);
		
			return false;
		}
		if (toEdlNo1==toEdlNo2||toEdlNo2==toEdlNo3)
		{
			var id = '509';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("toEdlNo",1);
			return false;

		}

	}
	if ((toEdlNo3<0 ) && (toEdlNo2<0||toEdlNo2.length==0) && (toEdlNo1<0||toEdlNo1.length==0))
{
	if (toEdlNo3!="")
	{			
			var id = '529';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("toEdlNo",2);
		
			return false;
	}
}
	if (toEdlNo3.length!=0 || toEdlNo3!="") 
	{
		if (toEdlNo3<0)
		{			
			var id = '529';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("toEdlNo",2);
		
			return false;
		}
		if (toEdlNo2==toEdlNo3 || toEdlNo1==toEdlNo3)
		{
			var id = '509';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("toEdlNo",2);
			return false;

		}

	}

	if (partOfSeqNo1.length!=0 ||partOfSeqNo1!=0)
{

if (partOfSeqNo1==partOfSeqNo2||partOfSeqNo1==partOfSeqNo3 ||partOfSeqNo1==partOfSeqNo4)
{

var id = '524';
hideErrors();
addMsgNum(id);
showScrollErrors("partOfCode",0);

return false;

}

}

if (partOfSeqNo2.length!=0 ||partOfSeqNo2!=0)
{
if (partOfSeqNo1==partOfSeqNo2||partOfSeqNo2==partOfSeqNo3||partOfSeqNo4==partOfSeqNo2)
{

var id = '524';
hideErrors();
addMsgNum(id);
showScrollErrors("partOfCode",1);
return false;

}

}
if (partOfSeqNo3.length!=0 ||partOfSeqNo3!=0)
{
if (partOfSeqNo2==partOfSeqNo3||partOfSeqNo1==partOfSeqNo3||partOfSeqNo3==partOfSeqNo4)
{
var id = '524';
hideErrors();
addMsgNum(id);
showScrollErrors("partOfCode",2);
return false;

}

}
if (partOfSeqNo4.length!=0 ||partOfSeqNo4!=0)
{
if (partOfSeqNo1==partOfSeqNo4||partOfSeqNo2==partOfSeqNo4||partOfSeqNo3==partOfSeqNo4)
{
var id = '524';
hideErrors();
addMsgNum(id);
showScrollErrors("partOfCode",3);
return false;

}

}
	
if(todwONumber.length!=0 || todwONumber!="" )
{
	if(!validateNumber(todwONumber))
	{

		var id = '508';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("todwONumber",-1);
		return false;
	}
}
if (todwONumber<=0 && todwONumber.length!=0 )
{
	var id = '530';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("todwONumber",-1);
	return false;
}
if(toPartNumber.length!=0 || toPartNumber!="" )
{
	if(!validateNumber(toPartNumber))
	{

		var id = '512';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("toPartNumber",-1);
		return false;
	}
}
if (toPartNumber<=0 && toPartNumber.length!=0)
{
	var id = '531';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("toPartNumber",-1);
		return false;
}
if(topriceBookNumber.length!=0 || topriceBookNumber!="" )
{
	if(!validateNumber(topriceBookNumber))
	{

		var id = '513';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("topriceBookNumber",-1);
		return false;
	}
}
if (topriceBookNumber<=0 && topriceBookNumber.length!=0)
{
		var id = '532';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("topriceBookNumber",-1);
		return false;
}
if(toComments.length > 2000)
{
	
		var id = '517';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("toComments",-1);
		return false;
}
}
}

//	Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
if (changeType==9){

	 if(mod.selectedClaSeqNo.value==0 || mod.selectedClaSeqNo.value=="" ){
			
				var id = '852';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("searchicon",-1);
				return false;
		} 
		
	if(frmVersionNo == "")	{
	
				var id = '871';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("fromClaVersion",-1);
				return false;
	}

	if(mod.noOfClaVersion.value != 1)	{

	if(mod.defaultFlag.value=="Y"){
	
		if(mod.toClaVerClauseNo.value=="" || mod.toClaVerDesc.value==""){
	
				var id = '869';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("clauseVersion",-1);
				return false;
			}
		
		if(frmVersionNo == mod.toClaVerClauseNo.value) {
		
				var id = '868';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("clauseVersion",-1);
				return false;
			}
	
		}
	}
		
	else	
		{
		
			var id = '870';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("changeTypeSeqNO",4);
			return false;
		}	

}

if (changeType==10){

	 if(mod.selectedClaSeqNo.value==0 || mod.selectedClaSeqNo.value=="" )
		{	
				var id = '852';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("searchicon",-1);
				return false;
		}		
	
	mod.versionNo.value = mod.hdnVersionNo.value; 	
}

//	Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 - Ends here


 if(reasons.length==0 || reasons=="" )
{
		var id = '507';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("reason",-1);
		return false;
}

 if(reasons.length > 2000)
{
	
		var id = '518';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("reason",-1);
		return false;
}

	for(var i=0;i<document.forms[0].componentfrom.options.length; i++ ){

	compval = document.forms[0].componentfrom.options[i].value;
	if(compval==""){
		
		trcomp="";
	}else{
	
		compSeqNo = document.forms[0].componentfrom.options[i].value;

		if(i==0){
			trcomp=compSeqNo;
		}else{
			trcomp=trcomp+"~"+compSeqNo;
		}
		
	}
	
	
}


var changetype=document.forms[0].changeTypeSeqNO.value;

if(trcomp=="")
	{
	document.forms[0].toComponentSeqNo.value=document.forms[0].toComponentSeqNo.value;
	}else{
		document.forms[0].toComponentSeqNo.value=trcomp;
	}

	if(changetype==6){
		clauseform.standardEquipmentSeqNo.disabled=false;
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
				showScrollErrors("adminComments",4);
				return false;
				}
		/*	}
		}*/
	if(flag)
		{
			//Added and Commented for CR_99 to remove junk characters by Validating the Clause Description through AJAX
			var url = "CreateChangeReqClauseAction.do?method=saveClause";
			var clauseDesc = $('#clauseDesc_id').val();
			fnValidateClauseDesc(clauseDesc,url,'clauseDesc_id');
			//document.forms[0].action="CreateChangeReqClauseAction.do?method=saveClause";
			//document.forms[0].submit();
			//Ends here for CR_99
		}else{

		}
	}
	else	{
		
			var id = '878';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("CompGrpCompLink",4);
			return false;
				
	}
			
}

function deleteTable()
{
 	rows = document.getElementById('tblGrid').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
 	var len = rows.length;
 	var conf = window.confirm("Do you want to delete the table?","YesNo");
 	if(conf)
 	{
 		for (var i = 0; i < len; i++)
		{
 			document.all("tblGrid").deleteRow();
 		}
 	}
 	else
 	{
 	return;
	}

	document.getElementById('showgrid').innerHTML="<TABLE WIDTH='100%' BORDER=0  id='tblGrid'style='table-layout:fixed' cellspacing='0' cellpadding='0'><TBODY>								<TR><TD WIDTH='19%' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>				<TD WIDTH='17%' >&nbsp;&nbsp;</TD><TD WIDTH='20%' >&nbsp;&nbsp;</TD></TR><TR><TD WIDTH='19%' >&nbsp;&nbsp;&nbsp;&nbsp;</TD>				<TD WIDTH='17%' >&nbsp;&nbsp;</TD><TD WIDTH='20%' >&nbsp;&nbsp;</TD></TR></TBODY></TABLE>";
	document.getElementById('showmainlink').style.visibility="visible";
	document.getElementById('showsublink').style.visibility="hidden";
				
}
/*
*	Name: RemoveRow
*	Purpose: Used to Remove the selected Row of the table
*	
*/
function removeRow()
{
	var pos;
	var flag = false;				
	var defaultrow;
	var len = document.forms[0].deleterow.length;	
				
	if(len==undefined && document.forms[0].deleterow.checked)
	{
	 	defaultrow =true;
	}
	for(var i =0; i < len; i++)
	{
		if(document.forms[0].deleterow[i].checked)
		{
			pos = i;
			flag = true;
			break;
		}
				
	}
				
	if(flag || defaultrow)
	{
		var conf = window.confirm("Do you want to delete the selected row?","YesNo");
						
		if(conf && flag)
		{
			document.all("tblGrid").deleteRow(pos+1);		
		}
		else if(conf && defaultrow)
		{
			document.all("tblGrid").deleteRow(1);	
		}
		else
		{
			return;
		}
	}					
	
}
            
/*
*	Name: addRow
*	Purpose: Used to Add a row to the table
*	
*/

function addRow(src)
{	
	rows = document.getElementById('tblGrid').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    for (i = 0; i < rows.length; i++)
    {
		index = this.rowIndex + 1;
	    index1 = this.rowIndex ;          		       
	}
	var newRow = document.all("tblGrid").insertRow(rows.length);
	var oCell = newRow.insertCell();
	oCell.innerHTML = "&nbsp;&nbsp;<input class='radcheck' type='radio' name='deleterow'/>&nbsp;&nbsp;<input CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray1' SIZE='15' MAXLENGTH='100'>&nbsp;";
	oCell = newRow.insertCell();
	oCell.innerHTML = "&nbsp;&nbsp;<input CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray2' SIZE='15' MAXLENGTH='100'>&nbsp;";
	oCell = newRow.insertCell();
	oCell.innerHTML = "&nbsp;&nbsp;<input CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray3' SIZE='15' MAXLENGTH='100'>&nbsp;";	
	oCell = newRow.insertCell();
	oCell.innerHTML = "&nbsp;&nbsp;<input CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray4' SIZE='15' MAXLENGTH='100'>&nbsp;";	
	oCell = newRow.insertCell();
	oCell.innerHTML = "&nbsp;&nbsp;<input CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray5' SIZE='15' MAXLENGTH='100'>&nbsp;";	
}
/*
*	Name: addTable
*	Purpose: Used to add a Table
*	
*/
function addTable()	
{
var val ='';
val = "<TABLE WIDTH='98%' BORDER=0  id='tblGrid' style='table-layout:fixed' cellspacing='0' cellpadding='0'><TBODY><TR>";
val = val+"<TD WIDTH='22%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
val = val+'<INPUT type="text" CLASS="COMMONTEXTBOXTABLE" name="clauseTableDataArray1"  SIZE="13" MAXLENGTH="100"/></TD>';
val = val+"<TD WIDTH='18%' >&nbsp;&nbsp<INPUT CLASS='COMMONTEXTBOXTABLE' type='text' name='clauseTableDataArray2' SIZE='13'MAXLENGTH='100'/></TD>";
val = val+"<TD WIDTH='18%' >&nbsp;&nbsp<INPUT CLASS='COMMONTEXTBOXTABLE' type='text' name='clauseTableDataArray3' SIZE=13 MAXLENGTH='100'/></TD>";
val = val+"<TD WIDTH='18%' >&nbsp;&nbsp<INPUT CLASS='COMMONTEXTBOXTABLE' type='text' name='clauseTableDataArray4' SIZE=13 MAXLENGTH='100'/></TD>";
val = val+"<TD WIDTH='30%' >&nbsp;&nbsp<INPUT CLASS='COMMONTEXTBOXTABLE' type='text' name='clauseTableDataArray5' SIZE=13 MAXLENGTH='100'/></TD></TR>";
val = val+"<TR><TD WIDTH='19%' >&nbsp;&nbsp;<INPUT class='radcheck' type='radio' name='deleterow'  />&nbsp;&nbsp;";
val = val+"<INPUT CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray1'  SIZE='15'MAXLENGTH='100' /></TD>";	
val = val+"<TD WIDTH='17%' >&nbsp;&nbsp<INPUT CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray2' SIZE='15'MAXLENGTH='100'/></TD>";
val = val+"<TD WIDTH='20%' >&nbsp;&nbsp<INPUT CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray3' SIZE=15 MAXLENGTH='100'/></TD>";
val = val+"<TD WIDTH='20%' >&nbsp;&nbsp<INPUT CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray4' SIZE=15 MAXLENGTH='100'/></TD>";
val = val+"<TD WIDTH='20%' >&nbsp;&nbsp<INPUT CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray5' SIZE=15 MAXLENGTH='100'/></TD>";
val = val+"</TR></TBODY></TABLE>";
document.getElementById('showgrid').innerHTML=val;
document.getElementById('showgrid').style.visibility="visible";
document.getElementById('showsublink').style.visibility="visible";
document.getElementById('showmainlink').style.visibility="hidden";
}


/**
**	fnParentClauses is used to populate the selected clause values from the
*	selectParentClause pop-up screen to the parent screen.
**/

function fnParentClauses()
{


var selectedClause=document.forms[0].clauseSeqNo;
var selectedClauseDesc;
var flag = false;
var tableData= new Array();
if (selectedClause==null)
{
	var id = '16';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("btnCancel",-1);
	return false;
}
else
{
if (selectedClause.length!=undefined)
{
	for (i=0;i<selectedClause.length;i++)
	{
		
		if (selectedClause[i].checked)
		{
			window.opener.document.forms[0].parentClauseSeqNo.value=document.forms[0].clauseSeqNo[i].value;
			
			window.opener.document.forms[0].toParentClaDesc.value=document.forms[0].clauseDescTmp[i].value;
			window.opener.document.forms[0].toParentClauseNo.value=document.forms[0].clauseNum[i].value;

			window.opener.document.forms[0].hdnModelSeqNo.value=document.forms[0].hdnModelSeqNo.value;
			window.opener.document.forms[0].hdnSubSectionSeqNo.value=document.forms[0].hdnSubSectionSeqNo.value;
			
		
  			flag = true;
  			break;
		}
	}
}
else
{
	if(selectedClause.checked)
	{
		
		window.opener.document.forms[0].parentClauseSeqNo.value=document.forms[0].clauseSeqNo.value;
		
		window.opener.document.forms[0].toParentClaDesc.value=document.forms[0].clauseDescTmp.value;
		window.opener.document.forms[0].toParentClauseNo.value=document.forms[0].clauseNum.value;
		window.opener.document.forms[0].hdnModelSeqNo.value=document.forms[0].hdnModelSeqNo.value;
		window.opener.document.forms[0].hdnSubSectionSeqNo.value=document.forms[0].hdnSubSectionSeqNo.value;
		
		
		flag = true;		
	}
}
}
	if(flag){
		if(selectedClause.length!=undefined && selectedClause.length > 1)
		window.opener.document.getElementById("parentclause").innerHTML =document.getElementById("clauseID"+i).innerHTML;
	else	
		window.opener.document.getElementById("parentclause").innerHTML =document.getElementById("clauseID0").innerHTML;
		
		window.close();
		
	
	}else{
	
		var id = '520';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("clauseSeqNo",-1);	
	}

	
}


/**
**	fnLoadParentClause is used to open selectparentclause pop-up screen.
**/
function fnLoadParentClause()
{
	
	var selectedArray=new Array();	
	var selectedModelID=document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value;
	var selectedSectionID=document.forms[0].sectionSeqNo.options[document.forms[0].sectionSeqNo.selectedIndex].value;
	//var	selectedSubSectionID=document.forms[0].subSectionSeqNo.options[document.forms[0].subSectionSeqNo.selectedIndex].value;
	var subSecID=document.forms[0].subSectionSeqNo.options[document.forms[0].subSectionSeqNo.selectedIndex].value;
	var selectedModelName= document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].text;
	var selectedSectionName=document.forms[0].sectionSeqNo.options[document.forms[0].sectionSeqNo.selectedIndex].text;
	var selectedSubSectionName=document.forms[0].subSectionSeqNo.options[document.forms[0].subSectionSeqNo.selectedIndex].text;
	// Added For CR_80 to add Specification Type Dropdown
	if(document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1"){
	
		var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("specTypeNo",-1);	
	
	}
	// Added For CR_80 to add Specification Type Dropdown - Ends here
	 else if (document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value =="-1"){
	
		var id = '19';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("modelSeqNo",-1);	
	}else if (document.forms[0].sectionSeqNo.options[document.forms[0].sectionSeqNo.selectedIndex].value =="-1"){
	
		var id = '205';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sectionSeqNo",-1);	
		   
	}

	

	else if(document.forms[0].subSectionSeqNo.selectedIndex==0)
	{
	var id = '182';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("subSectionSeqNo",-1);
	
	}
	else
	{
		
	window.open("CreateChangeReqClauseAction.do?method=selectParentClause&selectedModelName="+escape(selectedModelName)+"&selectedSectionName="+escape(selectedSectionName)+"&selectedSubSectionName="+escape(selectedSubSectionName)+"&selectedSubSectionID="+subSecID+"&selectedSectionID="+selectedSectionID+"&selectedModelID="+selectedModelID+"","Clause","location=0,resizable=no,status=0,scrollbars=1,width=780,height=600"); 
	}
}


/**
**	fnLoadClauseVersion is used to open selectclauseversion pop-up screen.
**/

function fnLoadClauseVersion(chngToFlag)
{
	var selectedArray=new Array();	
	var selectedModelID=document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value;
	var selectedSectionID=document.forms[0].sectionSeqNo.options[document.forms[0].sectionSeqNo.selectedIndex].value;
	//var	selectedSubSectionID=document.forms[0].subSectionSeqNo.options[document.forms[0].subSectionSeqNo.selectedIndex].value;
	var subSecID=document.forms[0].subSectionSeqNo.options[document.forms[0].subSectionSeqNo.selectedIndex].value;
	var selectedModelName= document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].text;
	var selectedSectionName=document.forms[0].sectionSeqNo.options[document.forms[0].sectionSeqNo.selectedIndex].text;
	var selectedSubSectionName=document.forms[0].subSectionSeqNo.options[document.forms[0].subSectionSeqNo.selectedIndex].text;
	var selectedClauseSeqNo=document.forms[0].selectedClaSeqNo.value;
	var ChangeTypeSeqNo;
	

	for (i=0; i<document.forms[0].changeTypeSeqNO.length; i++)

      {
         if (document.forms[0].changeTypeSeqNO[i].checked==true)
         {
            ChangeTypeSeqNo = document.forms[0].changeTypeSeqNO[i].value;
         }
      }
	// Added For CR_80 to add Specification Type Dropdown
	if(document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1"){
	
		var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("specTypeNo",-1);
	
	}
	// Added For CR_80 to add Specification Type Dropdown - Ends here
	else if (document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value =="-1"){
	
		var id = '19';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("modelSeqNo",-1);
	}else if (document.forms[0].sectionSeqNo.options[document.forms[0].sectionSeqNo.selectedIndex].value =="-1"){
	
		var id = '205';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sectionSeqNo",-1);
		   
	}

	

	else if(document.forms[0].subSectionSeqNo.selectedIndex==0)
	{
	var id = '182';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("subSectionSeqNo",-1);
	
	}else if(document.forms[0].selectedClaSeqNo.value=="" )
	{
		var id = '852';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("searchicon",-1);
	}else
	{
		
	window.open("CreateChangeReqClauseAction.do?method=selectClauseVersions&selectedModelName="+escape(selectedModelName)+"&selectedSectionName="+escape(selectedSectionName)+"&selectedSubSectionName="+escape(selectedSubSectionName)+"&selectedSubSectionID="+subSecID+"&selectedSectionID="+selectedSectionID+"&selectedModelID="+selectedModelID+"&selectedClauseID="+selectedClauseSeqNo+"&chngToFlag="+chngToFlag+"","Clause","location=0,resizable=no,status=0,scrollbars=1,width=780,height=600"); 
	}
}


/**
**	fnClauseVersion is used to populate the selected clause values from the
*	selectclauseversion pop-up screen to the parent screen.
**/

function fnClauseVersion()
{


var selectedClause=document.forms[0].versionNo;
var chngToFlag=document.forms[0].chngToFlag.value;
var selectedClauseDesc;
var flag = false;
var tableData= new Array();
if (selectedClause==null)
{
	var id = '16';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("btnCancel",-1);
	return false;
}
else
{

if (selectedClause.length!=undefined)
{
	for (i=0;i<selectedClause.length;i++)
	{
		
		if (selectedClause[i].checked)
		{
			window.opener.document.forms[0].hdnModelSeqNo.value=document.forms[0].hdnModelSeqNo.value;
			window.opener.document.forms[0].hdnSubSectionSeqNo.value=document.forms[0].hdnSubSectionSeqNo.value;
			//Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
			window.opener.document.forms[0].defaultFlag.value=document.forms[0].defaultFlag[i].value;
			window.opener.document.forms[0].noOfClaVersion.value=document.forms[0].noOfClaVersion.value;
			window.opener.document.forms[0].hdnSectionSeqNo.value=document.forms[0].hdnSectionSeqNo.value;
			if (chngToFlag=="Y"){
				window.opener.document.forms[0].toClaVerClauseNo.value=document.forms[0].versionNo[i].value;
				window.opener.document.forms[0].toClaVerDesc.value=document.forms[0].clauseDescTmp[i].value;
			}
			else if (chngToFlag=="N"){
				window.opener.document.forms[0].toClaVerClauseNo.value="";
				window.opener.document.forms[0].toClaVerDesc.value="";
				window.opener.document.forms[0].versionNo.value=document.forms[0].versionNo[i].value;
			}
			//Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 - Ends here
  			flag = true;
  			break;
		}
	}
}
else
{
	if(selectedClause.checked)
	{

		window.opener.document.forms[0].hdnModelSeqNo.value=document.forms[0].hdnModelSeqNo.value;
		window.opener.document.forms[0].hdnSubSectionSeqNo.value=document.forms[0].hdnSubSectionSeqNo.value;
		//Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
		window.opener.document.forms[0].defaultFlag.value=document.forms[0].defaultFlag.value;
		window.opener.document.forms[0].noOfClaVersion.value=document.forms[0].noOfClaVersion.value;
		window.opener.document.forms[0].hdnSectionSeqNo.value=document.forms[0].hdnSectionSeqNo.value;	
		if (chngToFlag=="Y"){
			window.opener.document.forms[0].toClaVerClauseNo.value=document.forms[0].versionNo.value;
			window.opener.document.forms[0].toClaVerDesc.value=document.forms[0].clauseDescTmp.value;
		}
		else if (chngToFlag=="N"){
			window.opener.document.forms[0].toClaVerClauseNo.value="";			
			window.opener.document.forms[0].toClaVerDesc.value="";
			window.opener.document.forms[0].versionNo.value=document.forms[0].versionNo.value;
			}
		//Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 - Ends here	
		flag = true;	
			
	}
}

}

	if(flag){
		if (chngToFlag=="Y"){
	
			if(selectedClause.length!=undefined && selectedClause.length > 1){
			
			window.opener.document.getElementById("claVersion").innerHTML =document.getElementById("clauseID"+i).innerHTML;
			
			}
			
			else{
				
				window.opener.document.getElementById("claVersion").innerHTML =document.getElementById("clauseID0").innerHTML;
			}
			if(!window.closed){
			
				window.close();
			}
			
		}
		else if (chngToFlag=="N"){
		
			window.opener.parentsubmit();
			window.close();
		}
		
	}

	else {
	
		var id = '520';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("versionNo",-1);	
	}

	
}

/**
**	fnEnableDisableFields is used to disable the form fields 
*	based on changetypeSeqNo selected by the user.client side.
**/

function fnEnableDisableFields(changetype){


		      
		clauseform=document.forms[0];
		clearAll(clauseform);
		
		if(changetype == 4){//New Clause
		
		
		
		    document.getElementById("searchicon").onclick = function() {
		        return false;
		      };

			  document.getElementById("toParentClaNo").onclick = function() {
		        return true;
		      };

			  document.getElementById("toComps").onclick = function() {
		        return true;
		      };

			  document.getElementById("showmainlink").onclick = function() {
		        return true;
		      };
		   
			for(var i=0;i<clauseform.toRefEDLNo.length;i++){
	              clauseform.toRefEDLNo[i].disabled=false;
            }
            
			for(var i=0;i<clauseform.toEdlNo.length;i++){
	              clauseform.toEdlNo[i].disabled=false;
            }
                  				   
		//clauseform.standardEquipmentSeqNo[0].disabled=true;
		clauseform.toClaVerClauseNo.disabled=true;
		clauseform.todwONumber.disabled=false;
		clauseform.toPartNumber.disabled=false;
		clauseform.topriceBookNumber.disabled=false;
		//clauseform.standardEquipmentSeqNo.disabled=true;
		//clauseform.standardEquipmentSeqNo[1].disabled=false;
		clauseform.toComments.disabled=false;
		//Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
		//clauseform.toDefaultFlag[0].disabled=true;
		//clauseform.toDefaultFlag[1].disabled=true;
					      		
		for(i=0;i<document.getElementsByName("toPartOfSearch").length;i++){
		
  		document.getElementsByName("toPartOfSearch")[i].onclick = function() {
		        return true;
		        
		      };}
		      
	    //Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 
		for(i=0;i<document.getElementsByName("toPartOfDelete").length;i++){
		      
  		document.getElementsByName("toPartOfDelete")[i].onclick = function() {
		        return true;
	        
	      };}
		 //Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 - Ends here

		      	      
		document.getElementById("clauseVersion").onclick = function() {
		        return false;
		      };
		
		//Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
		document.getElementById("fromClaVersion").onclick = function() {
		        return false;
		      };	
		      
		document.getElementById("frmClaVersionDel").onclick = function() {
		        return false;
		      };	
		      
		document.getElementById("toParentClaNodel").onclick = function() {
		        return true;
		      };
		
		document.getElementById("clauseVersiondel").onclick = function() {
		        return false;
		      };
		//Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 - Ends here   
		        
		document.getElementById("toCompsdel").onclick = function() {
                           return true;
                         };

		//clauseform.toParentClauseNo.readOnly=false;
		//clauseform.toClaVerClauseNo.readOnly=false;
		clauseform.toClauseNo.readOnly=false;
		clauseform.toClauseDesc.readOnly=false;
		
		
		
		for(var i=0;i<clauseform.toRefEDLNo.length;i++){
	              clauseform.toRefEDLNo[i].readOnly=false;
                  }
		for(var i=0;i<clauseform.toEdlNo.length;i++){
	              clauseform.toEdlNo[i].readOnly=false;
                  }
                  
		
		clauseform.todwONumber.readOnly=false;
		clauseform.toPartNumber.readOnly=false;
		clauseform.topriceBookNumber.readOnly=false;
		//clauseform.standardEquipmentSeqNo[0].disabled=true;
		clauseform.standardEquipmentSeqNo.disabled=false;
		clauseform.toComments.readOnly=false;
	}
	
	if(changetype == 5){//Modify Clause
	
		//Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
		document.getElementById("fromClaVersion").onclick = function() {
		        return false;
		      };	
		      
		document.getElementById("frmClaVersionDel").onclick = function() {
		        return false;
		      };	
		      
		document.getElementById("toParentClaNodel").onclick = function() {
		        return false;
		      };
		
		document.getElementById("clauseVersiondel").onclick = function() {
		        return false;
		      };
		//Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 - Ends here 
		
		document.getElementById("clauseVersion").onclick = function() {
		        return false;
		      };
		 
		
		  document.getElementById("searchicon").onclick = function() {
		        return true;
		      };

		document.getElementById("toParentClaNo").onclick = function() {
		        return false;
		      };

			  document.getElementById("toComps").onclick = function() {
		        return false;
		      };

			  for(i=0;i<document.getElementsByName("toPartOfSearch").length;i++){
		
  		document.getElementsByName("toPartOfSearch")[i].onclick = function() {
		        return true;
		        
		      };}
		    //Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 
			for(i=0;i<document.getElementsByName("toPartOfDelete").length;i++){
			      
	  		document.getElementsByName("toPartOfDelete")[i].onclick = function() {
			        return true;
		        
		      };}
			 //Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 - Ends here
			 
			  document.getElementById("toCompsdel").onclick = function() {
                           return false;
                         };
		clauseform.toParentClauseNo.readOnly=true;
		clauseform.toClaVerClauseNo.disabled=true; 
		//Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151     
		//clauseform.toDefaultFlag[0].disabled=false;
		//clauseform.toDefaultFlag[1].disabled=false;		   
		clauseform.todwONumber.disabled=false;
		clauseform.toPartNumber.disabled=false;
		clauseform.topriceBookNumber.disabled=false;
		//clauseform.standardEquipmentSeqNo[0].disabled=true;
		clauseform.standardEquipmentSeqNo.disabled=false;
		clauseform.toComments.disabled=false;
		
		// Added for LSDB_CR-61 on 04-Dec-08
	/*	if((clauseform.hdnCompGrpChnTypeSeqNo.value==2) && (clauseform.hdnCompChnTypeSeqNo.value==2)){
			if((clauseform.hdnCompGrpSeqNo.value!=0) && (clauseform.hdnCompSeqNo.value!=0)){
				flag=true;
				clauseform.hdnModelSeqNo.value=clauseform.modelSeqNo.options[clauseform.modelSeqNo.selectedIndex].value;
				clauseform.hdnSectionSeqNo.value=clauseform.sectionSeqNo.options[clauseform.sectionSeqNo.selectedIndex].value;
				clauseform.hdnSubSectionSeqNo.value=clauseform.subSectionSeqNo.options[clauseform.subSectionSeqNo.selectedIndex].value;
			}
		}
		if(flag){
			document.forms[0].action="CreateChangeReqClauseAction.do?method=fetchLeadCompDefaultVersion";
			document.forms[0].submit();		
		}
*/

	}
	
	if(changetype == 6){//Change Default Version
	
	
				      		
		for(i=0;i<document.getElementsByName("toPartOfSearch").length;i++){
		
  		document.getElementsByName("toPartOfSearch")[i].onclick = function() {
		        return false;
		        
		      };}
		      
	    //Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 
		for(i=0;i<document.getElementsByName("toPartOfDelete").length;i++){
		      
  		document.getElementsByName("toPartOfDelete")[i].onclick = function() {
		        return false;
	        
	      };}
		 //Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 - Ends here
		      
		document.getElementById("showmainlink").onclick = function() {
		        return false;
		      };
		      
		document.getElementById("toComps").onclick = function() {
		        return false;
		      };

			  document.getElementById("searchicon").onclick = function() {
		        return true;
		      };

			  document.getElementById("clauseVersion").onclick = function() {
		        return true;
		      };
		      
			//Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
			document.getElementById("fromClaVersion").onclick = function() {
			        return false;
			      };	
			      
			document.getElementById("frmClaVersionDel").onclick = function() {
			        return false;
			      };	
		      
			document.getElementById("toParentClaNodel").onclick = function() {
			        return false;
			      };
			
			document.getElementById("clauseVersiondel").onclick = function() {
			        return true;
			      };
			//Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 - Ends here 
			
			  document.getElementById("toCompsdel").onclick = function() {
                           return false;
                         };
		  	      		      
		clauseform.toParentClauseNo.readOnly=true;
		clauseform.toClaVerClauseNo.readOnly=true;
		clauseform.toClauseNo.readOnly=true;
		clauseform.toClauseDesc.readOnly=true;
		
		document.getElementById("toParentClaNo").onclick = function() {
		        return false;
		      };		
		
		for(var i=0;i<clauseform.toRefEDLNo.length;i++){
	              clauseform.toRefEDLNo[i].readOnly=true;
                  }
		for(var i=0;i<clauseform.toEdlNo.length;i++){
	              clauseform.toEdlNo[i].readOnly=true;
                  }
                  
		
		clauseform.todwONumber.readOnly=true;
		clauseform.toPartNumber.readOnly=true;
		clauseform.topriceBookNumber.readOnly=true;
		//clauseform.standardEquipmentSeqNo[0].disabled=true;
		clauseform.standardEquipmentSeqNo.disabled=true;
		clauseform.toComments.readOnly=true;
		//Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
		//clauseform.toDefaultFlag[0].disabled=false;
		//clauseform.toDefaultFlag[1].disabled=false;
		//clauseform.toDefaultFlag[0].checked=true;

	}

	//Added for CR_80 CR Form Enhancements III on 23-Nov-09 by RR68151
	
	if(changetype == 9){//Delete Clause Version
	
		document.getElementById("fromClaVersion").onclick = function() {
		        return true;
		      };
				    
		document.getElementById("frmClaVersionDel").onclick = function() {
		        return true;
		      };
		         		
		for(i=0;i<document.getElementsByName("toPartOfSearch").length;i++){
		
  		document.getElementsByName("toPartOfSearch")[i].onclick = function() {
		        return false;
		        
		      };}
		      
	    //Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 
		for(i=0;i<document.getElementsByName("toPartOfDelete").length;i++){
		      
  		document.getElementsByName("toPartOfDelete")[i].onclick = function() {
		        return false;
	        
	      };}
		 //Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 - Ends here      
		      
		document.getElementById("showmainlink").onclick = function() {
		        return false;
		      };
		      
		document.getElementById("toComps").onclick = function() {
		        return false;
		      };

		document.getElementById("searchicon").onclick = function() {
		        return true;
		      };

		if (document.forms[0].defaultFlag.value == "N") {
		
		document.getElementById("clauseVersion").onclick = function() {
		        return false;
		      };
			
		document.getElementById("clauseVersiondel").onclick = function() {
			    return false;
			  };
		}
		
		else{
		
		document.getElementById("clauseVersion").onclick = function() {
		        return true;
		      };
			
		document.getElementById("clauseVersiondel").onclick = function() {
			    return true;
			  };
		}

		document.getElementById("toParentClaNodel").onclick = function() {
			    return false;
			  };
			  
		document.getElementById("toCompsdel").onclick = function() {
               return false;
             };
		  	      		      
		clauseform.toParentClauseNo.readOnly=true;
		clauseform.toClaVerClauseNo.readOnly=true;
		clauseform.toClauseNo.readOnly=true;
		clauseform.toClauseDesc.readOnly=true;
		
		document.getElementById("toParentClaNo").onclick = function() {
		        return false;
		      };		
		
		for(var i=0;i<clauseform.toRefEDLNo.length;i++){
	              clauseform.toRefEDLNo[i].readOnly=true;
                  }
		for(var i=0;i<clauseform.toEdlNo.length;i++){
	              clauseform.toEdlNo[i].readOnly=true;
                  }

		clauseform.todwONumber.readOnly=true;
		clauseform.toPartNumber.readOnly=true;
		clauseform.topriceBookNumber.readOnly=true;
		clauseform.standardEquipmentSeqNo.disabled=true;
		clauseform.toComments.readOnly=true;

	}
	
	if(changetype == 10){//Delete Clause
	
		document.getElementById("fromClaVersion").onclick = function() {
		        return false;
		      };	
		      
		document.getElementById("frmClaVersionDel").onclick = function() {
		        return false;
		      };	
				      		
		for(i=0;i<document.getElementsByName("toPartOfSearch").length;i++){
		
  		document.getElementsByName("toPartOfSearch")[i].onclick = function() {
		        return false;
		        
		      };}
		      
	    //Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 
		for(i=0;i<document.getElementsByName("toPartOfDelete").length;i++){
		      
  		document.getElementsByName("toPartOfDelete")[i].onclick = function() {
		        return false;
	        
	      };}
		 //Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 - Ends here
		      
		document.getElementById("showmainlink").onclick = function() {
		        return false;
		      };
		      
		document.getElementById("toComps").onclick = function() {
		        return false;
		      };

		document.getElementById("searchicon").onclick = function() {
		        return true;
		      };

		document.getElementById("clauseVersion").onclick = function() {
		        return false;
		      };

		document.getElementById("toParentClaNodel").onclick = function() {
		        return false;
		      };
			
		document.getElementById("clauseVersiondel").onclick = function() {
		        return false;
		      }; 
			
		document.getElementById("toCompsdel").onclick = function() {
                return false;
              };
		  	      		      
		clauseform.toParentClauseNo.readOnly=true;
		clauseform.toClaVerClauseNo.readOnly=true;
		clauseform.toClauseNo.readOnly=true;
		clauseform.toClauseDesc.readOnly=true;
		
		document.getElementById("toParentClaNo").onclick = function() {
		        return false;
		      };		
		
		for(var i=0;i<clauseform.toRefEDLNo.length;i++){
	              clauseform.toRefEDLNo[i].readOnly=true;
                  }
		for(var i=0;i<clauseform.toEdlNo.length;i++){
	              clauseform.toEdlNo[i].readOnly=true;
                  }
                  
		clauseform.todwONumber.readOnly=true;
		clauseform.toPartNumber.readOnly=true;
		clauseform.topriceBookNumber.readOnly=true;
		clauseform.standardEquipmentSeqNo.disabled=true;
		clauseform.toComments.readOnly=true;

	}
	
	if (clauseform.hdnCompGrpChnTypeSeqNo.value == 7) {
	
			document.getElementById("toComps").onclick = function() {
		        return false;
		      };
		      
			document.getElementById("toCompsdel").onclick = function() {
                return false;
              };	      
	}
	
	//Added for CR_80 CR Form Enhancements III on 23-Nov-09 by RR68151 - Ends Here
	
}


/**
** fnOnLoadEnableDisableFields is used to disable the form fields 
*	based on changetypeSeqNo stored in the table. It will be called onLoad the of the page.
**/


function fnOnLoadEnableDisableFields(changetype){
		  
		clauseform=document.forms[0];
		//clearAll(clauseform);
		
		if(changetype == 4){//New Clause
		
		    document.getElementById("searchicon").onclick = function() {
		        return false;
		      };

			 document.getElementById("toCompsdel").onclick = function() {
                           return true;
                         };

			 document.getElementById("toParentClaNo").onclick = function() {
		        return true;
		      };

			  document.getElementById("toComps").onclick = function() {
		        return true;
		      };
		   
			for(var i=0;i<clauseform.toRefEDLNo.length;i++){
	              clauseform.toRefEDLNo[i].disabled=false;
            }
            
			for(var i=0;i<clauseform.toEdlNo.length;i++){
	              clauseform.toEdlNo[i].disabled=false;
            }
                  				   
		clauseform.standardEquipmentSeqNo[0].disabled=true;
		clauseform.toClaVerClauseNo.disabled=true;
		clauseform.todwONumber.disabled=false;
		clauseform.toPartNumber.disabled=false;
		clauseform.topriceBookNumber.disabled=false;
		//clauseform.standardEquipmentSeqNo.disabled=true;
		//clauseform.standardEquipmentSeqNo[1].disabled=false;
		clauseform.toComments.disabled=false;
		//Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
		//clauseform.toDefaultFlag[0].disabled=true;
		//clauseform.toDefaultFlag[1].disabled=true;
					      		
		
		      
		document.getElementById("clauseVersion").onclick = function() {
		        return false;
		      };
		      
		//Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
		document.getElementById("toParentClaNodel").onclick = function() {
		        return true;
		      };
		
		document.getElementById("clauseVersiondel").onclick = function() {
		        return false;
		      };
		      
		document.getElementById("fromClaVersion").onclick = function() {
		        return false;
		      };	
		      
		document.getElementById("frmClaVersionDel").onclick = function() {
		        return false;
		      };
		//Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 - Ends here
		
		for(i=0;i<document.getElementsByName("toPartOfSearch").length;i++){
		
  		document.getElementsByName("toPartOfSearch")[i].onclick = function() {
		        return true;
		        
		      };}

	    //Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 
		for(i=0;i<document.getElementsByName("toPartOfDelete").length;i++){
		      
  		document.getElementsByName("toPartOfDelete")[i].onclick = function() {
		        return true;
	        
	      };}
		 //Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 - Ends here
		 
	}
	
	if(changetype == 5){//Modify Clause
	
		//Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
		document.getElementById("fromClaVersion").onclick = function() {
		        return false;
		      };	
		      
		document.getElementById("frmClaVersionDel").onclick = function() {
		        return false;
		      };
		      
		document.getElementById("toParentClaNodel").onclick = function() {
		        return false;
		      };
		
		document.getElementById("clauseVersiondel").onclick = function() {
		        return false;
		      };
		//Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 - Ends here 
		
		document.getElementById("clauseVersion").onclick = function() {
		        return false;
		      };
		 
		   
		document.getElementById("searchicon").onclick = function() {
		        return true;
		      };

		document.getElementById("toParentClaNo").onclick = function() {
		        return false;
		      };

			  document.getElementById("toComps").onclick = function() {
		        return false;
		      };
		
		document.getElementById("toCompsdel").onclick = function() {
                           return false;
                         };
		
		clauseform.toClaVerClauseNo.disabled=true;   
		//Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151   
		//clauseform.toDefaultFlag[0].disabled=false;
		//clauseform.toDefaultFlag[1].disabled=false;		   
		clauseform.todwONumber.disabled=false;
		clauseform.toPartNumber.disabled=false;
		clauseform.topriceBookNumber.disabled=false;
		//clauseform.standardEquipmentSeqNo[0].disabled=true;
		clauseform.standardEquipmentSeqNo.disabled=false;
		clauseform.toComments.disabled=false;   
		
		for(i=0;i<document.getElementsByName("toPartOfSearch").length;i++){
		
  		document.getElementsByName("toPartOfSearch")[i].onclick = function() {
		        return true;
		        
		      };}

	    //Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 
		for(i=0;i<document.getElementsByName("toPartOfDelete").length;i++){
		      
  		document.getElementsByName("toPartOfDelete")[i].onclick = function() {
		        return true;
	        
	      };}
		 //Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 - Ends here
    		
	}
	
	if(changetype == 6){//Change Default Version
				      		
		for(i=0;i<document.getElementsByName("toPartOfSearch").length;i++){
		
  		document.getElementsByName("toPartOfSearch")[i].onclick = function() {
		        return false;
		        
		      };}

	    //Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 
		for(i=0;i<document.getElementsByName("toPartOfDelete").length;i++){
		      
  		document.getElementsByName("toPartOfDelete")[i].onclick = function() {
		        return false;
	        
	      };}
		 //Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 - Ends here
		      
		document.getElementById("showmainlink").onclick = function() {
		        return false;
		      };
		      
		document.getElementById("toComps").onclick = function() {
		        return false;
		      };
			  document.getElementById("searchicon").onclick = function() {
		        return true;
		      };

		document.getElementById("toCompsdel").onclick = function() {
                           return false;
                         };

		//Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
		document.getElementById("fromClaVersion").onclick = function() {
		        return false;
		      };	
		      
		document.getElementById("frmClaVersionDel").onclick = function() {
		        return false;
		      };
		      
		document.getElementById("toParentClaNodel").onclick = function() {
		        return false;
		      };
		
		document.getElementById("clauseVersiondel").onclick = function() {
		        return true;
		      };
		//Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 - Ends here 
					  	      		      
		clauseform.toParentClauseNo.readOnly=true;
		clauseform.toClaVerClauseNo.readOnly=true;
		clauseform.toClauseNo.readOnly=true;
		clauseform.toClauseDesc.readOnly=true;
		
		document.getElementById("toParentClaNo").onclick = function() {
		        return false;
		      };		
		
		for(var i=0;i<clauseform.toRefEDLNo.length;i++){
	              clauseform.toRefEDLNo[i].readOnly=true;
                  }
		for(var i=0;i<clauseform.toEdlNo.length;i++){
	              clauseform.toEdlNo[i].readOnly=true;
                  }
                  
		
		clauseform.todwONumber.readOnly=true;
		clauseform.toPartNumber.readOnly=true;
		clauseform.topriceBookNumber.readOnly=true;
		//clauseform.standardEquipmentSeqNo[0].disabled=true;
		clauseform.standardEquipmentSeqNo.disabled=true;
		clauseform.toComments.readOnly=true;
		//Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
		//clauseform.toDefaultFlag[0].disabled=false;
		//clauseform.toDefaultFlag[1].disabled=false;
		//clauseform.toDefaultFlag[0].checked=true;
	}
	
//Added for CR_80 CR Form Enhancements III on 23-Nov-09 by RR68151
	
	if(changetype == 9){//Delete Clause Version
	
		document.getElementById("fromClaVersion").onclick = function() {
		        return true;
		      };
				    
		document.getElementById("frmClaVersionDel").onclick = function() {
		        return true;
		      };
		         		
		for(i=0;i<document.getElementsByName("toPartOfSearch").length;i++){
		
  		document.getElementsByName("toPartOfSearch")[i].onclick = function() {
		        return false;
		        
		      };}
		      
	    //Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 
		for(i=0;i<document.getElementsByName("toPartOfDelete").length;i++){
		      
  		document.getElementsByName("toPartOfDelete")[i].onclick = function() {
		        return false;
	        
	      };}
		 //Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 - Ends here

		      
		document.getElementById("showmainlink").onclick = function() {
		        return false;
		      };
		      
		document.getElementById("toComps").onclick = function() {
		        return false;
		      };

		document.getElementById("searchicon").onclick = function() {
		        return true;
		      };

		document.getElementById("toParentClaNodel").onclick = function() {
			    return false;
			  };
			
		if (document.forms[0].defaultFlag.value == "N") {
		
		document.getElementById("clauseVersion").onclick = function() {
		        return false;
		      };
			
		document.getElementById("clauseVersiondel").onclick = function() {
			    return false;
			  };
		}
		
		else{
		
		document.getElementById("clauseVersion").onclick = function() {
		        return true;
		      };
			
		document.getElementById("clauseVersiondel").onclick = function() {
			    return true;
			  };
		}
			
		document.getElementById("toCompsdel").onclick = function() {
               return false;
             };
		  	      		      
		clauseform.toParentClauseNo.readOnly=true;
		clauseform.toClaVerClauseNo.readOnly=true;
		clauseform.toClauseNo.readOnly=true;
		clauseform.toClauseDesc.readOnly=true;
		
		document.getElementById("toParentClaNo").onclick = function() {
		        return false;
		      };		
		
		for(var i=0;i<clauseform.toRefEDLNo.length;i++){
	              clauseform.toRefEDLNo[i].readOnly=true;
                  }
		for(var i=0;i<clauseform.toEdlNo.length;i++){
	              clauseform.toEdlNo[i].readOnly=true;
                  }

		clauseform.todwONumber.readOnly=true;
		clauseform.toPartNumber.readOnly=true;
		clauseform.topriceBookNumber.readOnly=true;
		clauseform.standardEquipmentSeqNo.disabled=true;
		clauseform.toComments.readOnly=true;

	}
	
	if(changetype == 10){//Delete Clause
	
		document.getElementById("fromClaVersion").onclick = function() {
		        return false;
		      };	
		      
		document.getElementById("frmClaVersionDel").onclick = function() {
		        return false;
		      };	
				      		
		for(i=0;i<document.getElementsByName("toPartOfSearch").length;i++){
		
  		document.getElementsByName("toPartOfSearch")[i].onclick = function() {
		        return false;
		        
		      };}
		      
	    //Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 
		for(i=0;i<document.getElementsByName("toPartOfDelete").length;i++){
		      
  		document.getElementsByName("toPartOfDelete")[i].onclick = function() {
		        return false;
	        
	      };}
		 //Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151 - Ends here
 
		      
		document.getElementById("showmainlink").onclick = function() {
		        return false;
		      };
		      
		document.getElementById("toComps").onclick = function() {
		        return false;
		      };

		document.getElementById("searchicon").onclick = function() {
		        return true;
		      };

		document.getElementById("clauseVersion").onclick = function() {
		        return false;
		      };

		document.getElementById("toParentClaNodel").onclick = function() {
		        return false;
		      };
			
		document.getElementById("clauseVersiondel").onclick = function() {
		        return false;
		      }; 
			
		document.getElementById("toCompsdel").onclick = function() {
                return false;
              };
		  	      		      
		clauseform.toParentClauseNo.readOnly=true;
		clauseform.toClaVerClauseNo.readOnly=true;
		clauseform.toClauseNo.readOnly=true;
		clauseform.toClauseDesc.readOnly=true;
		
		document.getElementById("toParentClaNo").onclick = function() {
		        return false;
		      };		
		
		for(var i=0;i<clauseform.toRefEDLNo.length;i++){
	              clauseform.toRefEDLNo[i].readOnly=true;
                  }
		for(var i=0;i<clauseform.toEdlNo.length;i++){
	              clauseform.toEdlNo[i].readOnly=true;
                  }
                  
		clauseform.todwONumber.readOnly=true;
		clauseform.toPartNumber.readOnly=true;
		clauseform.topriceBookNumber.readOnly=true;
		clauseform.standardEquipmentSeqNo.disabled=true;
		clauseform.toComments.readOnly=true;

	}
	
	if (clauseform.hdnCompGrpChnTypeSeqNo.value == 7) {
	
			document.getElementById("toComps").onclick = function() {
		        return false;
		      };
		      
			document.getElementById("toCompsdel").onclick = function() {
                return false;
              };	      
	}
	
	//Added for CR_80 CR Form Enhancements III on 23-Nov-09 by RR68151 - Ends Here

}




function clearAll(clauseform){

		   	clauseform.clauseNo.value="";
			
			var refEdlLen = clauseform.refEDLNo.length;
			var edlLen = clauseform.edlNo.length;
			var partOfSeqNo = clauseform.partOfSeqNo.length;
			var partOfclaSeqNo = clauseform.partOfclaSeqNo.length;
			var partOfCode = clauseform.partOfCode.length;		
		
		

			for(var i = 0 ; i < refEdlLen; i++){

				clauseform.refEDLNo[i].value="";
				clauseform.toRefEDLNo[i].value="";
			}
		
			for(var i = 0 ; i < edlLen; i++){

				clauseform.edlNo[i].value="";
				clauseform.toEdlNo[i].value="";
			}
		
			for(var i = 0 ; i < partOfSeqNo; i++){

				clauseform.partOfSeqNo[i].value=0;//reset to zero
				//clauseform.topartOfSeqNo[i].value=0;
			}
		
	
			for(var i = 0 ; i < partOfclaSeqNo; i++){

				clauseform.partOfclaSeqNo[i].value=0;//reset to zero
				//clauseform.topartOfclaSeqNo[i].value=0;
			}
			
			for(var i = 0 ; i < partOfCode; i++){

				clauseform.partOfCode[i].value="";
				clauseform.toPartOfCode[i].value="";
			}
			
			
			

                  
		clauseform.dwONumber.value="";
		clauseform.partNumber.value="";
		clauseform.priceBookNumber.value="";
		clauseform.comments.value="";
		clauseform.standardEquipmentSeqNo.options[0].selected=true;
		clauseform.standEquipmentSeqNo.options[0].selected=true;
		clauseform.toParentClauseNo.value="";
		clauseform.toParentClaDesc.value="";
		clauseform.toClaVerClauseNo.value="";
		clauseform.toClaVerDesc.value="";
		clauseform.toClauseNo.value="";
		clauseform.toClauseDesc.value="";
		clauseform.componentfrom.options.length=0;
		clauseform.toComponentSeqNo.value="";
		clauseform.versionNo.value="";
		clauseform.selectedClaSeqNo.value="";
		//Modified for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151
		//clauseform.toDefaultFlag[1].checked=true;
		clauseform.todwONumber.value="";
		clauseform.toPartNumber.value="";
		clauseform.topriceBookNumber.value="";
		clauseform.toComments.value="";
		document.getElementById('claVersion').innerHTML = "";
		document.getElementById('parentclause').innerHTML = "";
		document.getElementById('fromClause').innerHTML = "";
		document.getElementById('showgrid').innerHTML = "";
		document.getElementById('components').innerHTML = "";
               
}

/**
*	resetRequest is used to reset the request
**/

function resetRequest()
{
	var mod=document.forms[0];
	var requestDesc=trim(mod.requestDesc.value);
	mod.requestDesc.value=requestDesc;
		
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
		//Added For CR_80 by RR68151
		clearAll(document.forms[0]);
		document.forms[0].action="CreateChangeReqClauseAction.do?method=resetRequest";
		document.forms[0].submit();
	}else{
	
		return false;
	}

}


/**
*	saveRequestStatus is used to change the status of the request
**/

function saveRequestStatus(statusid){

		var mod=document.forms[0];
		var adminComments=trim(mod.adminComments.value);
		mod.hdnAdminComments.value=adminComments;
		var requestDesc=trim(mod.requestDesc.value);
		mod.requestDesc.value=requestDesc;
		
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

			var flag = false;
            var conf;
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
                        document.forms[0].action="CreateChangeReqClauseAction.do?method=saveRequestStatus&statusid="+statusid;
						document.forms[0].submit();
            }else{
            
                        return false;
            }


	

}



/*
*	Name: AddComponent
*	Purpose: Used to Load Componentcharacterization.jsp and load its initial parameters
*	
*/
function AddComponent()
{	
    
	

	// Added For CR_80 to add Specification Type Dropdown
	if(document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1"){
	
		var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("specTypeNo",-1);
	
	}	
	// Added For CR_80 to add Specification Type Dropdown - Ends here
	else if (document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value =="-1")
	{
	   
		var id = '19';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("modelSeqNo",-1);
		//return false;
		   
	}else{
	   var selectedModelID = document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value;
	   
	   window.open("compSearchAction.do?method=initLoad&selectedModelID="+selectedModelID,"Clause","location=0,resizable=no ,status=0,scrollbars=1,width=600,height=500"); 
	
	}
}


/*
*	Name: LoadAllClauses
*	Purpose: Used to Load LoadAllClausesByModel.jsp and load its initial parameters
*	Modified for LSDB_CR-48[Part Of CR] on 04-Aug-08 by ps57222
*
*/
function LoadAllClauses(index)
{

	var selectedModel=document.forms[0].modelSeqNo.selectedIndex;
	var selectedModelName= document.forms[0].modelSeqNo.options[selectedModel].text;
	var selectedModelID = document.forms[0].modelSeqNo.options[selectedModel].value;
	//Added for LSDB_CR_46 PM&I Change
	var selIndex = "";
	var specType = "";
	
	// Added For CR_80 to add Specification Type Dropdown
	if(document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1"){
	
		var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("specTypeNo",-1);
	
	}	
	// Added For CR_80 to add Specification Type Dropdown - Ends here
	else if(document.forms[0].modelSeqNo.options[selectedModel].value==-1)
	{	
	var id = '19';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("modelSeqNo",-1);
	}
	else
	{
	window.open("partOfAction.do?method=insertPartOf&selectedModelName="+escape(selectedModelName)+"&textBoxIndex="+index+"&selectedModelID="+selectedModelID+"&spectype="+escape(specType)+"","Clause","location=0,resizable=yes,status=0,scrollbars=1,width=700,height=500"); 
	}
}


/**
* deletePartOfCode is used to delete part of details
**/

function deletePartOfCode(index)
{
//Modified for CR_80 by RR68151
if (document.forms[0].partOfCode[index-1].value != "")
{
	var del=confirm("Are you sure you want to clear the selected Part Of value");
	if(del == true){
			document.forms[0].partOfCode[index-1].value="";
			document.forms[0].partOfSeqNo[index-1].value="";
			document.forms[0].partOfclaSeqNo[index-1].value="";
			}
	}
}

/**
* fnSubmitPreview is used to open the preview pop-up
**/

function fnSubmitPreview(requestId){
	
		window.open("PreviewRequestAction.do?method=initLoad&reqID="+requestId,"preview","location=0,resizable=yes ,status=0,scrollbars=1,WIDTH=950,height=500");
}



/***
**  Function fnDisableModelDropdown is used to Enable and disable the model,section and subsection dropdown's
** based the requestModelStatus value. 
**/

function fnDisableModelDropdown(requestModelStatus){
	
	var mod=document.forms[0];
	
	
	if(requestModelStatus=="Y"){

		mod.modelSeqNo.disabled=true;
		mod.sectionSeqNo.disabled=true;
		mod.subSectionSeqNo.disabled=true;
		//Added For CR_80 for Specification DropDown
		mod.specTypeNo.disabled =true;
		//mod.applyModelFlag.disabled =true;
		
	}if(requestModelStatus=="N"){
		
		mod.modelSeqNo.disabled=false;
		mod.sectionSeqNo.disabled=false;
		mod.subSectionSeqNo.disabled=false;
		//Added For CR_80 for Specification DropDown
		mod.specTypeNo.disabled =false;
        //mod.applyModelFlag.disabled =false;
		
	}

}

function fnCompGrpCompView(requestId){

	var conf = window.confirm("Would you like to save the data in the current page ?");
	if(conf){
        //Added for including Save function from alert  
		document.forms[0].alertFlag1.value="Y";
		fnSaveClause();

			
	}else{
			document.forms[0].action="searchRequestComponentGroup.do?method=fetchComponentGroupDetails&reqID="+requestId;
			document.forms[0].submit();
	}


}
//Added for CR-61
function fnRefresh(){
var requestId = document.forms[0].hdnReqID.value;
document.forms[0].action="CreateChangeReqClauseAction.do?method=initLoad&reqID="+requestId;
document.forms[0].submit();
}

/*
* Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151. 
* Function to delete Clause Version field
*/
function deleteClaVersion()
{
if (document.forms[0].toClaVerClauseNo.value != "")
{
	var del=confirm("Are you sure you want to clear the selected Clause Version");
	if(del == true){
		document.forms[0].toClaVerClauseNo.value="";
		document.forms[0].toClaVerDesc.value="";
		document.getElementById('claVersion').innerHTML = "";
		}
	}
}

/*
* Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151. 
* Function to delete Parent Clause field
*/
function deletetoParentClause()
{
if (document.forms[0].toParentClauseNo.value != "")
{
	var del=confirm("Are you sure you want to clear the selected Parent Clause");
	if(del == true){
		document.forms[0].toParentClauseNo.value="";
		document.forms[0].toParentClaDesc.value="";
		document.forms[0].parentClauseSeqNo.value=0;
		document.getElementById('parentclause').innerHTML = "";
		}
	}
}

/*
* Added for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151. 
* Function to delete Clause Version field
*/
function deleteFrmClaVersion()
{
if (document.forms[0].versionNo.value != "")
{
	var del=confirm("Are you sure you want to clear the selected Clause Version");
	if(del == true){
		document.forms[0].versionNo.value="";
		}
	}
}

/*
* Moved from Page for CR_80 CR Form Enhancements III on 16-Nov-09 by RR68151.
*/
function deleteComponent()
{
if (document.forms[0].componentfrom.options.length!=0)
{
var del=confirm("Are you sure you want to clear all the components");
if(del == true){
document.forms[0].componentfrom.options.length=0;
document.forms[0].hdncomponentGroupSeqNo.value="";
document.forms[0].toComponentSeqNo.value="";
}
}
}

/*
* Name: fnLoadModels
*    Purpose: Used to load the Models on chage of Specificationtype.
*    Added for LSDB_CR-80 specification drop down
*/

function fnLoadModels()
{
	clearAll(document.forms[0]);
	mod = document.forms[0];
	document.forms[0].hdnSpecTypeNo.value= mod.specTypeNo.options[mod.specTypeNo.selectedIndex].value;
	document.forms[0].action="CreateChangeReqClauseAction.do?method=fetchModels";
	document.forms[0].submit();
}
/*Added for CR_100 for disabling Price Book No for locomotives*/
$(document).ready(function() {
	if($('#specType').val()==1){
		$('#priceBookNo').removeClass('TEXTBOX2').addClass('TEXTBOX02');
		$("#priceBookNo").attr("disabled", "disabled");
		}
})