
/* Added for LSDB_CR_46 PM&I Change */

function fetchModels(){
	
	document.forms[0].action="ModelSelectClauseAction.do?method=fetchModels";
	document.forms[0].submit();

}


//Flag variable
var clauseDescChangeFlag=0;

function fnLoadModel(){

document.forms[0].action="ModelSelectClauseAction.do?method=initLoad";
document.forms[0].submit();
}



function fnFetchClauseversions(){

clauseDescChangeFlag=0;
var mod = document.getElementById("clauseDesc").innerHTML;
var ModClause=document.forms[0];

//Added for LSDB_CR_46 PM&I Change

if (document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1")
{
	 
		var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("specTypeNo",-1);
		return false;
		   
}

//Added for LSDB_CR_46
if(ModClause.prevSpecType.value!=ModClause.specTypeNo.options[ModClause.specTypeNo.selectedIndex].value){


var id = '207';
hideErrors();
addMsgNum(id);
showScrollErrors("specTypeNo",-1);
return false;
}
//Ends 

if (document.forms[0].modelseqno.options[document.forms[0].modelseqno.selectedIndex].value =="-1")
{

var id = '19';
hideErrors();
addMsgNum(id);
showScrollErrors("modelSeqNo",-1);
return false;

}


if(mod=="&nbsp;" || (mod=="")){

var id = '20';
hideErrors();
addMsgNum(id);
showScrollErrors("clauseSeqNo",-1);
return false;

}




if(ModClause.hdPreSelectedModel.value!=ModClause.modelseqno.options[ModClause.modelseqno.selectedIndex].value){


var id = '207';
hideErrors();
addMsgNum(id);
showScrollErrors("modelSeqNo");
return false;
}

if((ModClause.clauseSeqNo.value=="")){

var id = '20';
hideErrors();
addMsgNum(id);
showScrollErrors("clauseSeqNo",-1);
return false;

}

document.forms[0].hdnModelName.value=document.forms[0].modelseqno.options[document.forms[0].modelseqno.selectedIndex].text;
document.forms[0].action="ModelSelectClauseAction.do?method=fetchClauseVersions";
document.forms[0].submit();
}



function fnApplyDefault(){

if(clauseDescChangeFlag==1)
{
var id= '207';
hideErrors();
addMsgNum(id);
showScrollErrors("modelSeqNo",-1);
return false;
}
//var cnt=document.forms[0].defaultFlag.length;

var no=document.forms[0].versionNo.length;

if (document.forms[0].modelseqno.options[document.forms[0].modelseqno.selectedIndex].value =="-1")
{

var id = '19';
hideErrors();
addMsgNum(id);
showScrollErrors("modelSeqNo",-1);
return false;

}

if(document.forms[0].hdnModelName.value != document.forms[0].modelseqno.options[document.forms[0].modelseqno.selectedIndex].text){
var id= '207';
hideErrors();
addMsgNum(id);
showScrollErrors("modelSeqNo",-1);
return false;


}



/* Codes Changed for LSDB_CR-49
* Changed on 15-July-08 by ps57222
*/
selectFlag=false;


if(document.forms[0].versionNo.length > 0){
var cnt=document.forms[0].versionNo.length;

for(var i=0;i<cnt;i++){
if(document.forms[0].versionNo[i].checked){
document.forms[0].hdnClauseVersionNo.value=document.forms[0].versionNo[i].value;
selectFlag=true;
break;

}
}
}else{
if(document.forms[0].versionNo.checked){
document.forms[0].hdnClauseVersionNo.value=document.forms[0].versionNo.value;
selectFlag=true;
}
}
if(selectFlag){
var del=confirm("Are you sure to apply this clause version as default ?");
if(del == true){
document.forms[0].action="ModelSelectClauseAction.do?method=updateApplyDefaultClause";
document.forms[0].submit();

}else{
}
}else{
var id = '227';
hideErrors();
addMsgNum(id);
showScrollErrors("versionNo",-1);
}
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
clauseDescChangeFlag=1;
var selectedModel=document.forms['ModelSelectClauseRevForm'].modelseqno.selectedIndex;
var selectedModelName= document.forms['ModelSelectClauseRevForm'].modelseqno.options[selectedModel].text;
var selectedModelID = document.forms['ModelSelectClauseRevForm'].modelseqno.options[selectedModel].value;


//Added for LSDB_CR_46 PM&I Change
var selIndex = document.forms['ModelSelectClauseRevForm'].specTypeNo.selectedIndex;
var specType = document.forms['ModelSelectClauseRevForm'].specTypeNo.options[selIndex].text;

if (document.forms['ModelSelectClauseRevForm'].specTypeNo.options[document.forms['ModelSelectClauseRevForm'].specTypeNo.selectedIndex].value =="-1")
{
	 
		var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("specTypeNo",-1);
		   
}else if(document.forms['ModelSelectClauseRevForm'].modelseqno.options[selectedModel].value==-1)
{
var id = '19';
hideErrors();
addMsgNum(id);
showScrollErrors("modelseqno",-1);
}
else
{
window.open("modelClauseDescPopupAction.do?method=initLoad&selectedModelName="+escape(selectedModelName)+"&selectedModelID="+selectedModelID+"&spectype="+escape(specType)+"","Clause","location=0,resizable=no,status=0,scrollbars=1,width=780,height=600");
}

}
/*
* Name: fnLoadSubSection
*   Purpose:Used to Load subsectionList in popup
*
*/
function fnLoadSubSection()
{

document.forms['ModelClauseDescPopUpForm'].action="modelClauseDescPopupAction.do?method=SubSectionLoad";
document.forms['ModelClauseDescPopUpForm'].submit();
}

/*
* Name: fnLoadClauseDesc
*  Purpose:Used to Load The clause desc in popup
*
*/
function fnLoadClauseDesc()
{
if(document.forms['ModelClauseDescPopUpForm'].subSecSeqNo.selectedIndex==0)
{
var id = '182';
hideErrors();
addMsgNum(id);
showScrollErrors("subSecSeqNo",-1);

}
else
{
document.forms['ModelClauseDescPopUpForm'].action="modelClauseDescPopupAction.do?method=LoadClauseDesc";
document.forms['ModelClauseDescPopUpForm'].submit();
}
}

/*
* Name: SelectClauseDesc
*   Purpose:Used to Select The clause desc and populate in the parent window
*
*/
function SelectClauseDesc()
{
var selectedClause=document.forms[0].clauseSeqNo;
var selectedClauseDesc;
var flag = false;
var tableData= new Array();
var versionNo=document.forms[0].versionNo;
var subSectionSeqNo=document.forms[0].subSecSeqNo.value;
var modelSeqNo=document.forms[0].modelSeqNo.value;

if (document.forms[0].sectionSeqNo.options.length<=1)
{
var id = '519';
hideErrors();
addMsgNum(id);
showScrollErrors("sltSection",-1);
return false;
}
if (document.forms[0].sectionSeqNo.options[document.forms[0].sectionSeqNo.selectedIndex].value==-1)
{
var id = '205';
hideErrors();
addMsgNum(id);
showScrollErrors("sltSection",-1);
return false;
}

if (document.forms[0].subSecSeqNo.options.length<=1)
{
var id = '511';
hideErrors();
addMsgNum(id);
showScrollErrors("sltSubSection",-1);
return false;
}

if (document.forms[0].subSecSeqNo.options[document.forms[0].subSecSeqNo.selectedIndex].value==-1)
{
var id = '182';
hideErrors();
addMsgNum(id);
showScrollErrors("sltSubSection",-1);
return false;
}

if (selectedClause==null)
{
var id = '521';
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
selectedClauseDesc= document.forms[0].clauseDesc[i].value;
versionNo=document.forms[0].versionNo[i].value;
window.opener.document.forms[0].clauseSeqNo.value=selectedClause[i].value;
//window.opener.document.forms[0].versionNo.value=versionNo;
window.opener.document.forms[0].subSectionSeqNo.value=subSectionSeqNo;
window.opener.document.forms[0].hdPreSelectedModel.value=modelSeqNo;
flag = true;
break;
}
}
}

else
{
if(selectedClause.checked)
{
selectedClauseDesc= document.forms[0].clauseDesc.value;
versionNo=document.forms[0].versionNo.value;
window.opener.document.forms[0].clauseSeqNo.value=selectedClause.value;
//window.opener.document.forms[0].versionNo.value=versionNo;
window.opener.document.forms[0].subSectionSeqNo.value=subSectionSeqNo;
window.opener.document.forms[0].hdPreSelectedModel.value=modelSeqNo;
flag = true;
}
}
}
if(flag){

var arr = new Array(tableData);
if(selectedClause.length!=undefined && selectedClause.length > 1)
{
window.opener.document.getElementById("clauseDesc").innerHTML =document.getElementById("clauseID"+i).textContent.trim();//Modified for CR_131 to bring text content
window.opener.document.forms[0].clauseDes.value=document.getElementById("clauseID"+i).textContent.trim();
}
else
{
window.opener.document.getElementById("clauseDesc").innerHTML =document.getElementById("clauseID0").textContent.trim();//Modified for CR_131 to bring text content
window.opener.document.forms[0].clauseDes.value=document.getElementById("clauseID0").textContent.trim();
}

window.close();
}else{

var id = '520';
hideErrors();
addMsgNum(id);
showScrollErrors("clauseSeqNo",-1);
}
}


/*
* Name: SelectNonCharClauseDesc
* Purpose:Used to Select The clause desc and populate in the parent window
*
*/
function SelectNonCharClauseDesc()
{
var selectedClause=document.forms[0].clauseSeqNo;
var selectedClauseDesc;
var flag = false;
var tableData= new Array();
var subSectionSeqNo=document.forms[0].subSecSeqNo.value;
var modelSeqNo=document.forms[0].modelSeqNo.value;
//CR 88
var obj;
var compoentName;
//var componentName=document.forms[0].componentName.value;

if (selectedClause.length!=undefined)
{
for (i=0;i<selectedClause.length;i++)
{

if (selectedClause[i].checked)
{
selectedClauseDesc= document.forms[0].clauseDesc[i].value;

//CR 88
obj = document.forms[0].clauseSeqNo[i].parentNode.parentNode;
compoentName =obj.childNodes[13].innerText; //Childnodes Updated from 6 to 13 for CR-131
if((compoentName).trim()!=""){
		var id = '900';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("clauseSeqNo",i);
		return false;
}
versionNo=document.forms[0].versionNo[i].value;
window.opener.document.forms[0].linkClaSeqNo.value=selectedClause[i].value;
flag = true;
break;
}
}
}

else
{
if(selectedClause.checked)
{
selectedClauseDesc= document.forms[0].clauseDesc.value;
versionNo=document.forms[0].versionNo.value;
window.opener.document.forms[0].linkClaSeqNo.value=selectedClause.value;
flag = true;
}
}

if(flag){

var arr = new Array(tableData);
if(selectedClause.length!=undefined && selectedClause.length > 1)
{
//window.opener.document.getElementById("linkClauseDesc").innerHTML =document.getElementById("clauseID"+i).innerHTML;
window.opener.fnAddClauseDes(document.getElementById("clauseID"+i).innerHTML);

}
else
{
//window.opener.document.getElementById("linkClauseDesc").innerHTML =document.getElementById("clauseID0").innerHTML;
window.opener.fnAddClauseDes(document.getElementById("clauseID0").innerHTML);
}


window.close();
}else{

var id = '520';
hideErrors();
addMsgNum(id);
showScrollErrors("clauseSeqNo",-1);
}
}
/*
*  Name: Cancelpopup
*    Purpose:Used to Close the pop up window
*
*/
function Cancelpopup()
{
window.close();
}





/*
*  Name: deleteTable
*    Purpose: Used to Delete the Table in the Screen
*
*/

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

document.getElementById('showgrid').innerHTML="<TABLE WIDTH='100%' BORDER=0  id='tblGrid'style='table-layout:fixed' cellspacing='0' cellpadding='0'><TBODY>                             <TR><TD WIDTH='19%' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>                <TD WIDTH='17%' >&nbsp;&nbsp;</TD><TD WIDTH='20%' >&nbsp;&nbsp;</TD></TR><TR><TD WIDTH='19%' >&nbsp;&nbsp;&nbsp;&nbsp;</TD>             <TD WIDTH='17%' >&nbsp;&nbsp;</TD><TD WIDTH='20%' >&nbsp;&nbsp;</TD></TR></TBODY></TABLE>";
document.getElementById('showmainlink').style.visibility="visible";
document.getElementById('showsublink').style.visibility="hidden";

}
/*
*    Name: RemoveRow
*  Purpose: Used to Remove the selected Row of the table
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
*   Name: addRow
*    Purpose: Used to Add a row to the table
*
*/
function addTableRow(src)
{
rows = document.getElementById('tblGrid').getElementsByTagName('tbody')[0].getElementsByTagName('tr');

for (i = 0; i < rows.length; i++)
{
index = this.rowIndex + 1;
index1 = this.rowIndex ;

}


var newRow = document.all("tblGrid").insertRow(rows.length);


var oCell = newRow.insertCell();
oCell.innerHTML = "&nbsp;<input class='radcheck' type='radio' name='deleterow'/>&nbsp;&nbsp;<input CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray1' MAXLENGTH='100'SIZE='15'>";
oCell = newRow.insertCell();
oCell.innerHTML = "&nbsp;&nbsp;<input CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray2' SIZE='15'MAXLENGTH='100'>";
oCell = newRow.insertCell();
oCell.innerHTML = "&nbsp;&nbsp;<input CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray3' SIZE='15'MAXLENGTH='100'>";
oCell = newRow.insertCell();
oCell.innerHTML = "&nbsp;&nbsp;<input CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray4' SIZE='15'MAXLENGTH='100'>";
oCell = newRow.insertCell();
oCell.innerHTML = "&nbsp;&nbsp;<input CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray5' SIZE='15'MAXLENGTH='100'>";
}
/*
* Name: addTable
*   Purpose: Used to add a Table
*
*/
function addTable()
{
var val ='';
val = "<TABLE WIDTH='98%' BORDER=0  id='tblGrid' style='table-layout:fixed' cellspacing='0' cellpadding='0'><TBODY><TR>";
val = val+"<TD WIDTH='22%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
val = val+'<INPUT type="text" CLASS="COMMONTEXTBOXTABLE" name="clauseTableDataArray1"  SIZE="13" MAXLENGTH="100"/></TD>';
val = val+"<TD WIDTH='18%' >&nbsp;&nbsp;<INPUT CLASS='COMMONTEXTBOXTABLE' type='text' name='clauseTableDataArray2' SIZE='13' MAXLENGTH='100'/></TD>";
val = val+"<TD WIDTH='18%' >&nbsp;&nbsp;<INPUT CLASS='COMMONTEXTBOXTABLE' type='text' name='clauseTableDataArray3' SIZE=13 MAXLENGTH='100'/></TD>";
val = val+"<TD WIDTH='18%' >&nbsp;&nbsp;<INPUT CLASS='COMMONTEXTBOXTABLE' type='text' name='clauseTableDataArray4' SIZE=13 MAXLENGTH='100'/></TD>";
val = val+"<TD WIDTH='30%' >&nbsp;&nbsp;<INPUT CLASS='COMMONTEXTBOXTABLE' type='text' name='clauseTableDataArray5' SIZE=13 MAXLENGTH='100'/></TD></TR>";
val = val+"<TR><TD WIDTH='19%' >&nbsp;<INPUT class='radcheck' type='radio' name='deleterow'  />&nbsp;&nbsp;";
val = val+"<INPUT CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray1' SIZE='15' MAXLENGTH='100'/></TD>";
val = val+"<TD WIDTH='17%' >&nbsp;&nbsp;<INPUT CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray2' SIZE='15'MAXLENGTH='100'/></TD>";
val = val+"<TD WIDTH='20%' >&nbsp;&nbsp;<INPUT CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray3' SIZE=15 MAXLENGTH='100'/></TD>";
val = val+"<TD WIDTH='20%' >&nbsp;&nbsp;<INPUT CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray4' SIZE=15 MAXLENGTH='100'/></TD>";
val = val+"<TD WIDTH='20%' >&nbsp;&nbsp;<INPUT CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray5' SIZE=15 MAXLENGTH='100'/></TD>";
val = val+"</TR></TBODY></TABLE>";
document.getElementById('showgrid').innerHTML=val;
document.getElementById('showgrid').style.visibility="visible";
document.getElementById('showsublink').style.visibility="visible";
document.getElementById('showmainlink').style.visibility="hidden";
}


/*
*   Name: addRow
*    Purpose: Used to Add a row to the table
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
oCell.innerHTML = "&nbsp;&nbsp;<input class='radcheck' type='radio' name='deleterow'/>&nbsp;&nbsp;<input CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray1' SIZE='15' MAXLENGTH='100'>";
oCell = newRow.insertCell();
oCell.innerHTML = "&nbsp;&nbsp;<input CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray2' SIZE='15'MAXLENGTH='100'>";
oCell = newRow.insertCell();
oCell.innerHTML = "&nbsp;&nbsp;<input CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray3' SIZE='15'MAXLENGTH='100'>";
oCell = newRow.insertCell();
oCell.innerHTML = "&nbsp;&nbsp;<input CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray4' SIZE='15'MAXLENGTH='100'>";
oCell = newRow.insertCell();
oCell.innerHTML = "&nbsp;&nbsp;<input CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray5' SIZE='15'MAXLENGTH='100'>";
}


/*
*	Name: fnAddClause
*   Purpose: Used to Add a Clause into the database.
*	Modified for LSDB_CR-48[Part Of CR] on 04-Aug-08 by ps57222
*/
function fnAddClause()
{
var mod = document.forms[0];
var clauseDesc = trim(mod.clauseDescForTextArea.value);
var dwoNumber = trim(mod.dwONumber.value);
var partNumber=trim(mod.partNumber.value);
var priceBookNumber=trim(mod.priceBookNumber.value);
var comments=trim(mod.comments.value);
//var reasons=trim(mod.reason.value);
var comments=trim(mod.comments.value)
var refEdl1=trim(mod.refEDLNo[0].value);
var refEdl2=trim(mod.refEDLNo[1].value);
var refEdl3=trim(mod.refEDLNo[2].value);
var refEdl=refEdl1+refEdl2+refEdl3;
var edlNo1=trim(mod.edlNo[0].value);
var edlNo2=trim(mod.edlNo[1].value);
var edlNo3=trim(mod.edlNo[2].value);
var edlNo=edlNo1+edlNo2+edlNo3;
var compSeqNo;
var partOfSeqNo1=trim(mod.partOfCode[0].value);
var partOfSeqNo2=trim(mod.partOfCode[1].value);
var partOfSeqNo3=trim(mod.partOfCode[2].value);
var partOfSeqNo4=trim(mod.partOfCode[3].value);
mod.clauseDescForTextArea.value=clauseDesc;
mod.comments.value=comments;
//mod.reason.value=reasons;
mod.dwONumber.value=dwoNumber;
mod.partNumber.value=partNumber;
mod.priceBookNumber.value=priceBookNumber;
mod.edlNo[0].value=edlNo1;
mod.edlNo[1].value=edlNo2;
mod.edlNo[2].value=edlNo3;
mod.refEDLNo[0].value=refEdl1;
mod.refEDLNo[1].value=refEdl2;
mod.refEDLNo[2].value=refEdl3;

var partOfclaSeqNo1=trim(mod.partOfclaSeqNo[0].value);
var partOfclaSeqNo2=trim(mod.partOfclaSeqNo[1].value);
var partOfclaSeqNo3=trim(mod.partOfclaSeqNo[2].value);
var partOfclaSeqNo4=trim(mod.partOfclaSeqNo[3].value);


if(clauseDescChangeFlag==1)
{
var id = '207';
hideErrors();
addMsgNum(id);
showScrollErrors("modelSeqNo",-1);
return false;
}

if(mod.hdPreSelectedModel.value!=mod.modelseqno.options[mod.modelseqno.selectedIndex].value){


var id = '207';
hideErrors();
addMsgNum(id);
showScrollErrors("modelseqno",-1);
return false;
}


if (document.forms[0].modelseqno.options[document.forms[0].modelseqno.selectedIndex].value =="-1")
{

var id = '19';
hideErrors();
addMsgNum(id);
showScrollErrors("modelseqno",-1);
return false;

}




if(clauseDesc.length==0 || clauseDesc=="" )
{

var id = '506';
hideErrors();
addMsgNum(id);
showScrollErrors("clauseDescForTextArea",-1);
return false;
}

/* Commented for CR-121
if(clauseDesc.length > 4000)
{

var id = '516';
hideErrors();
addMsgNum(id);
showScrollErrors("clauseDescForTextArea",-1);
return false;
}
*/


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
if (document.forms[0].clauseTableDataArray5.length!=undefined)
{
for (var i=0;i<document.forms[0].clauseTableDataArray5.length;i++)
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





if ((mod.standardEquipmentSeqNo.options[mod.standardEquipmentSeqNo.selectedIndex].value =="-1")&&(refEdl1=="")&&(refEdl2=="")&&(refEdl3=="")&&(edlNo1=="")&&(edlNo2=="")&&(edlNo3=="")&&(partOfSeqNo1.length==0 ||partOfSeqNo1==0)&&(partOfSeqNo2.length==0 ||partOfSeqNo2==0)&&(partOfSeqNo3.length==0 ||partOfSeqNo3==0)&&(partOfSeqNo4.length==0 ||partOfSeqNo4==0)&&(dwoNumber.length==0 || dwoNumber=="" )&&(partNumber.length==0 || partNumber=="" )&&(priceBookNumber.length==0 || priceBookNumber=="" )&&(comments.length==0 || comments==""))
{
var id = '505';
hideErrors();
addMsgNum(id);
showScrollErrors("refEDLNo",0);
return false;
}
else
{

/**
	Code commented as per the client requirement.
	To make RefEDL and EDL number as a alpha numeric field.
	Modified on 29-09-08
	by ps57222.
**/


/* for(i=0;i<document.forms[0].refEDLNo.length;i++){
if(document.forms[0].refEDLNo[i].value != ""){
if(!validateNumber(document.forms[0].refEDLNo[i].value)){
var id = '515';
hideErrors();
addMsgNum(id);
showErrors();

document.forms[0].refEDLNo[i].focus();
return;
}
}
}*/ 

if ((refEdl1<0 ) && (refEdl2<0||refEdl2.length==0) && (refEdl3<0||refEdl3.length==0))
{
if (refEdl1!="")
{
var id = '528';
hideErrors();
addMsgNum(id);
showScrollErrors("refEDLNo",0);

return false;
}
}
if (refEdl1.length!=0 ||refEdl1!="")
{
if (refEdl1<0)
{
var id = '528';
hideErrors();
addMsgNum(id);
showScrollErrors("refEDLNo",0);

return false;
}
if (refEdl1==refEdl2||refEdl1==refEdl3)
{
var id = '510';
hideErrors();
addMsgNum(id);
showScrollErrors("refEDLNo,0");

return false;

}

}
if ((refEdl2<0) &&((refEdl1<0)||(refEdl1.length==0)) &&((refEdl3<0)||(refEdl3.length==0)))
{
if (refEdl2!="")
{
var id = '528';
hideErrors();
addMsgNum(id);
showScrollErrors("refEDLNo",1);

return false;
}
}
if (refEdl2.length!=0 ||refEdl2!="")
{
if (refEdl2<0)
{
var id = '528';
hideErrors();
addMsgNum(id);
showScrollErrors("refEDLNo",1);

return false;
}
if (refEdl1==refEdl2||refEdl2==refEdl3)
{
var id = '510';
hideErrors();
addMsgNum(id);
showScrollErrors("refEDLNo",1);

return false;

}

}
if ((refEdl3<0) &&((refEdl2<0)||(refEdl2.length==0)) &&((refEdl1<0)||(refEdl1.length==0)))
{
if (refEdl3!="")
{
var id = '528';
hideErrors();
addMsgNum(id);
showScrollErrors("refEDLNo",2);

return false;
}
}
if (refEdl3.length!=0 ||refEdl3!="")
{
if (refEdl3<0)
{
var id = '528';
hideErrors();
addMsgNum(id);
showScrollErrors("refEDLNo",2);

return false;
}
if (refEdl2==refEdl3||refEdl1==refEdl3)
{
var id = '510';
hideErrors();
addMsgNum(id);
showScrollErrors("refEDLNo",2);

return false;

}

}

/**
	Code commented as per the client requirement.
	To make RefEDL and EDL number as a alpha numeric field.
	Modified on 29-09-08
	by ps57222.
**/

/* for(i=0;i<document.forms[0].edlNo.length;i++){
if(document.forms[0].edlNo[i].value != ""){
if(!validateNumber(document.forms[0].edlNo[i].value)){

var id = '514';
hideErrors();
addMsgNum(id);
showErrors();
document.forms[0].edlNo[i].focus();
return;
}
}
} */

if ((edlNo1<0 ) && (edlNo2<0||edlNo2.length==0) && (edlNo3<0||edlNo3.length==0))
{
if (edlNo1!="")
{
var id = '529';
hideErrors();
addMsgNum(id);
showScrollErrors("edlNo",0);

return false;
}
}

if (edlNo1.length!=0 ||edlNo1!="")
{
if (edlNo1<0)
{
var id = '529';
hideErrors();
addMsgNum(id);
showScrollErrors("edlNo",0);

return false;
}
if (edlNo1==edlNo2||edlNo1==edlNo3)
{
var id = '509';
hideErrors();
addMsgNum(id);
showScrollErrors("edlNo",0);
return false;

}

}

if ((edlNo2<0 ) && (edlNo1<0||edlNo1.length==0) && (edlNo3<0||edlNo3.length==0))
{
if (edlNo2!="")
{
var id = '529';
hideErrors();
addMsgNum(id);
showScrollErrors("edlNo",1);

return false;
}
}
if (edlNo2.length!=0 ||edlNo2!="")
{
if (edlNo2<0)
{
var id = '529';
hideErrors();
addMsgNum(id);
showScrollErrors("edlNo",1);

return false;
}
if (edlNo1==edlNo2||edlNo2==edlNo3)
{
var id = '509';
hideErrors();
addMsgNum(id);
showScrollErrors("edlNo",1);
return false;

}

}
if ((edlNo3<0 ) && (edlNo2<0||edlNo2.length==0) && (edlNo1<0||edlNo1.length==0))
{
if (edlNo3!="")
{
var id = '529';
hideErrors();
addMsgNum(id);
showScrollErrors("edlNo",2);

return false;
}
}
if (edlNo3.length!=0 ||edlNo3!="")
{
if (edlNo3<0)
{
var id = '529';
hideErrors();
addMsgNum(id);
showScrollErrors("edlNo",2);

return false;
}
if (edlNo2==edlNo3||edlNo1==edlNo3)
{
var id = '509';
hideErrors();
addMsgNum(id);
showScrollErrors("edlNo",2);
return false;

}

}

/**
*	validation Added for part of clause number.
*	Part of data should be mapped to clause number not to sub section.
*	If it is tied to sub section throw alert message number 816.
**/


if (partOfSeqNo1.length!=0 ||partOfSeqNo1!=0)
{	
	
	if(partOfclaSeqNo1.length==0 || partOfclaSeqNo1==0 || partOfclaSeqNo1 =="null" || partOfclaSeqNo1=="")
	{
		var id = '816';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("partOfCode",0);
		return false;
	}

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
	
	if(partOfclaSeqNo2.length==0 || partOfclaSeqNo2==0 || partOfclaSeqNo2=="null" || partOfclaSeqNo2=="")
	{
		var id = '816';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("partOfCode",1);
		return false;
	}
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
	
	if(partOfclaSeqNo3.length==0 || partOfclaSeqNo3==0 || partOfclaSeqNo3=="null" || partOfclaSeqNo3=="")
	{
		
		var id = '816';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("partOfCode",2);
		return false;
	}
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
if(partOfclaSeqNo4.length==0 || partOfclaSeqNo4==0 || partOfclaSeqNo4=="null" || partOfclaSeqNo4=="")
	{
		
		var id = '816';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("partOfCode",3);
		return false;
	}
if (partOfSeqNo1==partOfSeqNo4||partOfSeqNo2==partOfSeqNo4||partOfSeqNo3==partOfSeqNo4)
{
var id = '524';
hideErrors();
addMsgNum(id);
showScrollErrors("partOfCode",3);
return false;

}

}






if(dwoNumber.length!=0 || dwoNumber!="" )
{
if(!validateNumber(dwoNumber))
{

var id = '508';
hideErrors();
addMsgNum(id);
showScrollErrors("dwONumber",-1);
return false;
}
}
if (dwoNumber<=0 && dwoNumber.length!=0 )
{
var id = '530';
hideErrors();
addMsgNum(id);
showScrollErrors("dwONumber",-1);
return false;
}
if(partNumber.length!=0 || partNumber!="" )
{
if(!validateNumber(partNumber))
{

var id = '512';
hideErrors();
addMsgNum(id);
showScrollErrors("partNumber",-1);
return false;
}
}

if (partNumber<=0 && partNumber.length!=0)
{
var id = '531';
hideErrors();
addMsgNum(id);
showScrollErrors("partNumber",-1);
return false;
}
if(priceBookNumber.length!=0 || priceBookNumber!="" )
{
if(!validateNumber(priceBookNumber))
{

var id = '513';
hideErrors();
addMsgNum(id);
showScrollErrors("partNumber",-1);
return false;
}
}

if (priceBookNumber<=0 && priceBookNumber.length!=0)
{
var id = '532';
hideErrors();
addMsgNum(id);
showScrollErrors("priceBookNumber",-1);
return false;
}
if(comments.length > 2000)
{

var id = '517';
hideErrors();
addMsgNum(id);
showScrollErrors("priceBookNumber",-1);
return false;
}
}





document.forms[0].action="ModelSelectClauseAction.do?method=insertClause";
document.getElementById("clauseDesc").innerHTML="";
document.forms[0].submit();

}


/*
*   Name: LoadAllClauses
*    Purpose: Used to Load LoadAllClausesByModel.jsp and load its initial parameters
*	Modified for LSDB_CR-48[Part Of CR] on 04-Aug-08 by ps57222
*/
function LoadAllClauses(index)
{
var selectedModel=document.forms[0].modelseqno.selectedIndex;
var selectedModelName= document.forms[0].modelseqno.options[selectedModel].text;
var selectedModelID = document.forms[0].modelseqno.options[selectedModel].value;

//Added for LSDB_CR_46 PM&I Change
var selIndex = document.forms['ModelSelectClauseRevForm'].specTypeNo.selectedIndex;
var specType = document.forms['ModelSelectClauseRevForm'].specTypeNo.options[selIndex].text;

if(document.forms[0].modelseqno.options[selectedModel].value==-1)
{
var id = '19';
hideErrors();
addMsgNum(id);
showScrollErrors("modelseqno",-1);
}
else
{
window.open("partOfAction.do?method=insertPartOf&selectedModelName="+escape(selectedModelName)+"&textBoxIndex="+index+"&selectedModelID="+selectedModelID+"&spectype="+escape(specType)+"","Clause","location=0,resizable=no,status=0,scrollbars=1,width=650,height=500");
}
}

/*
*   Name: deletePartOfCode()
*	Purpose: Used to Delete the Part Of Fields
*	Modified for LSDB_CR-48[Part Of CR] on 04-Aug-08 by ps57222
*/
function deletePartOfCode(index)
{
document.forms[0].partOfCode[index-1].value="";
document.forms[0].partOfSeqNo[index-1].value="";
document.forms[0].partOfclaSeqNo[index-1].value="";
}


/*
*   Name: deleteClause()
* Purpose: Used to Delete the clause and all it's subclause and also the versions of it.
*
*/
function fnDeleteClause()
{
var flag = window.confirm("Are you sure to delete the Clause, Sub Clauses and all its versions ?");

if(flag){
document.forms[0].action="ModelSelectClauseAction.do?method=deleteClause";
document.getElementById("clauseDesc").innerHTML = "<TD> &nbsp;</TD>"
document.forms[0].submit();
}else{
}
}


/*
*    Name: fnDeleteVersion()
*  Purpose: Used to Delete the selected version of a clause.
*
*/
function fnDeleteVersion()
{

selectFlag=false;
var cnt=document.forms[0].versionNo.length;
if(cnt!=undefined){
if(document.forms[0].versionNo.length > 0){
for(var i=0;i<cnt;i++){
if(document.forms[0].versionNo[i].checked){
document.forms[0].hdnClauseVersionNo.value=document.forms[0].versionNo[i].value;
selectFlag=true;
break;

}
}
}
}else{
if(document.forms[0].versionNo.checked){
document.forms[0].hdnClauseVersionNo.value=document.forms[0].versionNo.value;
selectFlag=true;
}
}
if(selectFlag){
var deleteFlag = window.confirm("Are you sure to delete the version of the clause ?");
if(deleteFlag){
document.forms[0].action="ModelSelectClauseAction.do?method=deleteVersion";
document.forms[0].submit();
}else{
}
}else{
var id = '227';
hideErrors();
addMsgNum(id);
showScrollErrors("versionNo",-1);

}
}
function fnLoadClauseVersion(){

if(clauseDescChangeFlag==1)
{
var id= '207';
hideErrors();
addMsgNum(id);
showScrollErrors("modelSeqNo",-1);
return false;
}


var cnt=document.forms[0].versionNo.length;
selectFlag=false;
if (document.forms[0].modelseqno.options[document.forms[0].modelseqno.selectedIndex].value =="-1")
{

var id = '19';
hideErrors();
addMsgNum(id);
showScrollErrors("modelseqno",-1);
return false;

}

if(document.forms[0].hdnModelName.value != document.forms[0].modelseqno.options[document.forms[0].modelseqno.selectedIndex].text){
var id= '207';
hideErrors();
addMsgNum(id);
showScrollErrors("modelseqno",-1);
return false;


}







if(cnt > 0){


for(var i=0;i<cnt;i++){
if(document.forms[0].versionNo[i].checked){
document.forms[0].hdnClauseVersionNo.value=document.forms[0].versionNo[i].value;
selectFlag=true;
break;

}
}
}else{
if(document.forms[0].versionNo.checked){
document.forms[0].hdnClauseVersionNo.value=document.forms[0].versionNo.value;
selectFlag=true;
}
}
if(selectFlag){
var del=confirm("Are you sure to load the selected clause version ?");
if(del == true){
document.forms[0].action="ModelSelectClauseAction.do?method=fetchSelectedClauseVersion";
document.forms[0].submit();

}else{
}
}
else{
var id = '227';
hideErrors();
addMsgNum(id);
showScrollErrors("versionNo",-1);

}
}






// Added For LSDB_CR_53 - Update of Clause/sub clause versio
/*
* Name: fnUpdateClause()
*   Purpose: Used to Add a Clause into the database.
*
*/
function fnUpdateClause()
{
var mod = document.forms[0];
var clauseDesc = trim(mod.clauseDescForTextArea.value);
var dwoNumber = trim(mod.dwONumber.value);
var partNumber=trim(mod.partNumber.value);
var priceBookNumber=trim(mod.priceBookNumber.value);
var comments=trim(mod.comments.value);
//var reasons=trim(mod.reason.value);
var comments=trim(mod.comments.value)
var refEdl1=trim(mod.refEDLNo[0].value);
var refEdl2=trim(mod.refEDLNo[1].value);
var refEdl3=trim(mod.refEDLNo[2].value);
var refEdl=refEdl1+refEdl2+refEdl3;
var edlNo1=trim(mod.edlNo[0].value);
var edlNo2=trim(mod.edlNo[1].value);
var edlNo3=trim(mod.edlNo[2].value);
var edlNo=edlNo1+edlNo2+edlNo3;
var compSeqNo;
var partOfSeqNo1=trim(mod.partOfSeqNo[0].value);
var partOfSeqNo2=trim(mod.partOfSeqNo[1].value);
var partOfSeqNo3=trim(mod.partOfSeqNo[2].value);
var partOfSeqNo4=trim(mod.partOfSeqNo[3].value);
mod.clauseDescForTextArea.value=clauseDesc;
mod.comments.value=comments;
//mod.reason.value=reasons;
mod.dwONumber.value=dwoNumber;
mod.partNumber.value=partNumber;
mod.priceBookNumber.value=priceBookNumber;
mod.edlNo[0].value=edlNo1;
mod.edlNo[1].value=edlNo2;
mod.edlNo[2].value=edlNo3;
mod.refEDLNo[0].value=refEdl1;
mod.refEDLNo[1].value=refEdl2;
mod.refEDLNo[2].value=refEdl3;


var test=mod.selectedVersion.value;

mod.hdnClauseVersionNo.value=test;

var isSpecChar1 = SpecialCharacterCheck(partOfSeqNo1);
if(isSpecChar1){
partOfSeqNo1="";comments
return false;
}

var isSpecChar2 = SpecialCharacterCheck(partOfSeqNo2);
if(isSpecChar2){
partOfSeqNo2="";
return false;
}

var isSpecChar3 = SpecialCharacterCheck(partOfSeqNo3);
if(isSpecChar3){
partOfSeqNo3="";
return false;
}

var isSpecChar4 = SpecialCharacterCheck(partOfSeqNo4);
if(isSpecChar4){
partOfSeqNo4="";
return false;
}

if(clauseDescChangeFlag==1)
{
var id = '207';
hideErrors();
addMsgNum(id);
showScrollErrors("modelSeqNo",-1);
return false;
}

if(mod.hdPreSelectedModel.value!=mod.modelseqno.options[mod.modelseqno.selectedIndex].value){


var id = '207';
hideErrors();
addMsgNum(id);
showScrollErrors("modelseqno",-1);
return false;
}


if (document.forms[0].modelseqno.options[document.forms[0].modelseqno.selectedIndex].value =="-1")
{

var id = '19';
hideErrors();
addMsgNum(id);
showScrollErrors("modelseqno",-1);
return false;

}




if(clauseDesc.length==0 || clauseDesc=="" )
{

var id = '506';
hideErrors();
addMsgNum(id);
showScrollErrors("clauseDescForTextArea",-1);
return false;
}
/* Commented for CR-121
if(clauseDesc.length > 4000)
{

var id = '516';
hideErrors();
addMsgNum(id);
showScrollErrors("clauseDescForTextArea",-1);
return false;
}
*/


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
if (document.forms[0].clauseTableDataArray5.length!=undefined)
{
for (var i=0;i<document.forms[0].clauseTableDataArray5.length;i++)
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





if ((mod.standardEquipmentSeqNo.options[mod.standardEquipmentSeqNo.selectedIndex].value =="-1")&&(refEdl1=="")&&(refEdl2=="")&&(refEdl3=="")&&(edlNo1=="")&&(edlNo2=="")&&(edlNo3=="")&&(partOfSeqNo1.length==0 ||partOfSeqNo1==0)&&(partOfSeqNo2.length==0 ||partOfSeqNo2==0)&&(partOfSeqNo3.length==0 ||partOfSeqNo3==0)&&(partOfSeqNo4.length==0 ||partOfSeqNo4==0)&&(dwoNumber.length==0 || dwoNumber=="" )&&(partNumber.length==0 || partNumber=="" )&&(priceBookNumber.length==0 || priceBookNumber=="" )&&(comments.length==0 || comments==""))
{
var id = '505';
hideErrors();
addMsgNum(id);
showScrollErrors("refEDLNo",0);
return false;
}
else
{




for(i=0;i<document.forms[0].refEDLNo.length;i++){
if(document.forms[0].refEDLNo[i].value != ""){
if(!validateNumber(document.forms[0].refEDLNo[i].value)){
var id = '515';
hideErrors();
addMsgNum(id);
showScrollErrors("refEDLNo",i);
return;
}
}
}
if ((refEdl1<0 ) && (refEdl2<0||refEdl2.length==0) && (refEdl3<0||refEdl3.length==0))
{
if (refEdl1!="")
{
var id = '528';
hideErrors();
addMsgNum(id);
showScrollErrors("refEDLNo",0);

return false;
}
}
if (refEdl1.length!=0 ||refEdl1!="")
{
if (refEdl1<0)
{
var id = '528';
hideErrors();
addMsgNum(id);
showScrollErrors("refEDLNo",0);

return false;
}
if (refEdl1==refEdl2||refEdl1==refEdl3)
{
var id = '510';
hideErrors();
addMsgNum(id);
showScrollErrors("refEDLNo",0);

return false;

}

}
if ((refEdl2<0) &&((refEdl1<0)||(refEdl1.length==0)) &&((refEdl3<0)||(refEdl3.length==0)))
{
if (refEdl2!="")
{
var id = '528';
hideErrors();
addMsgNum(id);
showScrollErrors("refEDLNo",1);

return false;
}
}
if (refEdl2.length!=0 ||refEdl2!="")
{
if (refEdl2<0)
{
var id = '528';
hideErrors();
addMsgNum(id);
showScrollErrors("refEDLNo",1);

return false;
}
if (refEdl1==refEdl2||refEdl2==refEdl3)
{
var id = '510';
hideErrors();
addMsgNum(id);
showScrollErrors("refEDLNo",1);

return false;

}

}
if ((refEdl3<0) &&((refEdl2<0)||(refEdl2.length==0)) &&((refEdl1<0)||(refEdl1.length==0)))
{
if (refEdl3!="")
{
var id = '528';
hideErrors();
addMsgNum(id);
showScrollErrors("refEDLNo",2);

return false;
}
}
if (refEdl3.length!=0 ||refEdl3!="")
{
if (refEdl3<0)
{
var id = '528';
hideErrors();
addMsgNum(id);
showScrollErrors("refEDLNo",2);

return false;
}
if (refEdl2==refEdl3||refEdl1==refEdl3)
{
var id = '510';
hideErrors();
addMsgNum(id);
showScrollErrors("refEDLNo",2);

return false;

}

}

for(i=0;i<document.forms[0].edlNo.length;i++){
if(document.forms[0].edlNo[i].value != ""){
if(!validateNumber(document.forms[0].edlNo[i].value)){

var id = '514';
hideErrors();
addMsgNum(id);
showScrollErrors("edlNo",0);
return;
}
}
}
if ((edlNo1<0 ) && (edlNo2<0||edlNo2.length==0) && (edlNo3<0||edlNo3.length==0))
{
if (edlNo1!="")
{
var id = '529';
hideErrors();
addMsgNum(id);
showScrollErrors("edlNo",0);

return false;
}
}

if (edlNo1.length!=0 ||edlNo1!="")
{
if (edlNo1<0)
{
var id = '529';
hideErrors();
addMsgNum(id);
showScrollErrors("edlNo",0);

return false;
}
if (edlNo1==edlNo2||edlNo1==edlNo3)
{
var id = '509';
hideErrors();
addMsgNum(id);
showScrollErrors("edlNo",0);
return false;

}

}

if ((edlNo2<0 ) && (edlNo1<0||edlNo1.length==0) && (edlNo3<0||edlNo3.length==0))
{
if (edlNo2!="")
{
var id = '529';
hideErrors();
addMsgNum(id);
showScrollErrors("edlNo",1);

return false;
}
}
if (edlNo2.length!=0 ||edlNo2!="")
{
if (edlNo2<0)
{
var id = '529';
hideErrors();
addMsgNum(id);
showScrollErrors("edlNo",1);

return false;
}
if (edlNo1==edlNo2||edlNo2==edlNo3)
{
var id = '509';
hideErrors();
addMsgNum(id);
showScrollErrors("edlNo",1);
return false;

}

}
if ((edlNo3<0 ) && (edlNo2<0||edlNo2.length==0) && (edlNo1<0||edlNo1.length==0))
{
if (edlNo3!="")
{
var id = '529';
hideErrors();
addMsgNum(id);
showScrollErrors("edlNo",2);

return false;
}
}
if (edlNo3.length!=0 ||edlNo3!="")
{
if (edlNo3<0)
{
var id = '529';
hideErrors();
addMsgNum(id);
showScrollErrors("edlNo",2);

return false;
}
if (edlNo2==edlNo3||edlNo1==edlNo3)
{
var id = '509';
hideErrors();
addMsgNum(id);
showScrollErrors("edlNo",2);
return false;

}

}






if(dwoNumber.length!=0 || dwoNumber!="" )
{
if(!validateNumber(dwoNumber))
{

var id = '508';
hideErrors();
addMsgNum(id);
showScrollErrors("dwONumber",-1);
return false;
}
}
if (dwoNumber<=0 && dwoNumber.length!=0 )
{
var id = '530';
hideErrors();
addMsgNum(id);
showScrollErrors("dwONumber",-1);
return false;
}
if(partNumber.length!=0 || partNumber!="" )
{
if(!validateNumber(partNumber))
{

var id = '512';
hideErrors();
addMsgNum(id);
showScrollErrors("partNumber",-1);
return false;
}
}

if (partNumber<=0 && partNumber.length!=0)
{
var id = '531';
hideErrors();
addMsgNum(id);
showScrollErrors("partNumber",-1);
return false;
}
if(priceBookNumber.length!=0 || priceBookNumber!="" )
{
if(!validateNumber(priceBookNumber))
{

var id = '513';
hideErrors();
addMsgNum(id);
showScrollErrors("priceBookNumber",-1);
return false;
}
}

if (priceBookNumber<=0 && priceBookNumber.length!=0)
{
var id = '532';
hideErrors();
addMsgNum(id);
showScrollErrors("priceBookNumber",-1);
return false;
}
if(comments.length > 2000)
{

var id = '517';
hideErrors();
addMsgNum(id);
showScrollErrors("comments",-1);
return false;
}
}





document.forms[0].action="ModelSelectClauseAction.do?method=updateClause";
document.getElementById("clauseDesc").innerHTML="";
document.forms[0].submit();

}


//Added for CR_92

/*
*	Name: SelectClauseDesc
*	Purpose:Used to select the Clause Description and populate it in the 
*	parrent Appendix Screen in the Selected Div ID.
*	
*/


function fnSelectClauseDescImage()
{
var selectedClause=document.forms[0].clauseSeqNo;
var selectedClauseDesc;
var flag = false;
var tableData= new Array();
var versionNo=document.forms[0].versionNo;
var subSectionSeqNo=document.forms[0].subSecSeqNo.value;
var modelSeqNo=document.forms[0].modelSeqNo.value;
var index=document.forms[0].clauseDes.value;
if (document.forms[0].sectionSeqNo.options.length<=1)
{
	var id = '519';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sectionSeqNo",-1);
	return false;
}
if (document.forms[0].sectionSeqNo.options[document.forms[0].sectionSeqNo.selectedIndex].value==-1)
{	
	var id = '205';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sectionSeqNo",-1);	
	return false;
}

if (document.forms[0].subSecSeqNo.options.length<=1)
{
	var id = '511';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("subSecSeqNo",-1);
	return false;
}

if (document.forms[0].subSecSeqNo.options[document.forms[0].subSecSeqNo.selectedIndex].value==-1)
{	
	var id = '182';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("subSecSeqNo",-1);	
	return false;
}

if (selectedClause==null)
{
	var id = '521';
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
			selectedClauseDesc= document.forms[0].clauseDesc[i].value;
  			versionNo=document.forms[0].versionNo[i].value;	  	
  			window.opener.document.forms[0].clauseSeqNo.value=selectedClause[i].value; 
  			window.opener.document.forms[0].versionNo.value=versionNo; 	
  			window.opener.document.forms[0].subSectionSeqNo.value=subSectionSeqNo; 
  			window.opener.document.forms[0].hdPreSelectedModel.value=modelSeqNo;
			flag = true;
  	  			break;
		}
	}
}

else
{
	if(selectedClause.checked)
	{
    	selectedClauseDesc= document.forms[0].clauseDesc.value;
		versionNo=document.forms[0].versionNo.value;		
		window.opener.document.forms[0].clauseSeqNo.value=selectedClause.value;
		window.opener.document.forms[0].versionNo.value=versionNo;  
		window.opener.document.forms[0].subSectionSeqNo.value=subSectionSeqNo;
		window.opener.document.forms[0].hdPreSelectedModel.value=modelSeqNo;
		flag = true;	
	}
}
}
	if(flag){	
		if(selectedClause.length!=undefined && selectedClause.length > 1)
		{
		window.opener.document.getElementById("clauseDesc"+index).innerHTML =document.getElementById("clauseID"+i).innerHTML;
		}
	   else	
	     {
	     window.opener.document.getElementById("clauseDesc"+index).innerHTML =document.getElementById("clauseID0").innerHTML;	
		
	     }				
		
	window.close();
	}else{
		
		var id = '520';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("clauseSeqNo",-1);			
	}
}


