/*
*   Name: fnLoadSections
* Purpose: Used to load the Subsections on chage of Section.
*    Modified for LSDB_CR-46
*    on 28-Aug-08 by ps57222
*/

function fnLoadSections(){
document.forms[0].action="SubSectionAction.do?method=SectionLoad";
document.forms[0].submit();

}

/*
*  Name: fnSearchSubSections
*   Purpose: Used to search Subsections.
*    Modified for LSDB_CR-46
*    on 28-Aug-08 by ps57222
*/


function fnSearchSubSections(){

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
if (document.forms[0].sectionseqno.options[document.forms[0].sectionseqno.selectedIndex].value =="-1")
{

var id = '205';
hideErrors();
addMsgNum(id);
showScrollErrors("sltSection",-1);
return false;

}

SubSecMaintForm.hdnsearch.value='1';

document.forms[0].hdSelectedSpecType.value=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
document.SubSecMaintForm.hdnModel.value=document.SubSecMaintForm.modelseqno.options[document.SubSecMaintForm.modelseqno.selectedIndex].text;

document.SubSecMaintForm.hdnSection.value=document.SubSecMaintForm.sectionseqno.options[document.SubSecMaintForm.sectionseqno.selectedIndex].text;

document.forms[0].action="SubSectionAction.do?method=fetchSubSections";
document.forms[0].submit();



}


/*
*  Name: fnInsertSubSections
*  Purpose: Used to Add Subsections.
*    Modified for LSDB_CR-46
*    on 28-Aug-08 by ps57222
*/

function fnInsertSubSections(){

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
if (document.forms[0].sectionseqno.options[document.forms[0].sectionseqno.selectedIndex].value =="-1")
{

var id = '205';
hideErrors();
addMsgNum(id);
showScrollErrors("sltSection ",-1);
return false;

}
if(trim(document.forms[0].subsecname.value).length==0 || trim(document.forms[0].subsecname.value)=="" ){
var id = '100';
hideErrors();
addMsgNum(id);
showScrollErrors("subsecname",-1);
return false;
}

if(trim(document.forms[0].comments.value) !=""){
if(trim(document.forms[0].comments.value).length>2000){
var id = '517';
hideErrors();
addMsgNum(id);
showScrollErrors("comments",-1);
return false;


}

}
//Added For CR_84
document.forms[0].hdSelectedSpecType.value=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
document.SubSecMaintForm.hdnModel.value=document.SubSecMaintForm.modelseqno.options[document.SubSecMaintForm.modelseqno.selectedIndex].text;

document.SubSecMaintForm.hdnSection.value=document.SubSecMaintForm.sectionseqno.options[document.SubSecMaintForm.sectionseqno.selectedIndex].text;


document.forms[0].action="SubSectionAction.do?method=insertSubSection";
document.forms[0].submit();

}

/*
*  Name: fnModifySubSections
*   Purpose: Used to Modify Subsections.
*    Modified for LSDB_CR-46
*    on 28-Aug-08 by ps57222
*/


function fnModifySubSections(){
var subSecForm = document.forms[0];
var index;
subsecseqno = false;

if (document.forms[0].modelseqno.options[document.forms[0].modelseqno.selectedIndex].value =="-1")
{

var id = '19';
hideErrors();
addMsgNum(id);
showScrollErrors("modelseqno",-1);
return false;

}
if (document.forms[0].sectionseqno.options[document.forms[0].sectionseqno.selectedIndex].value =="-1")
{

var id = '205';
hideErrors();
addMsgNum(id);
showScrollErrors("sectionseqno",-1);
return false;

}

if(subSecForm.subsecseqno.length > 0 ){
var cnt = subSecForm.subsecseqno.length;
for(var i=0;i<cnt;i++){
if(subSecForm.subsecseqno[i].checked){
subsecseqno = true;
index = i;

break;
}
}
}
else{
if(subSecForm.subsecseqno.checked){
subsecseqno = true;
}
}if (!subsecseqno)
{
var id= '170';
hideErrors();
addMsgNum(id);
showScrollErrors("subsecseqno",-1);
return false;


}else{

if(subSecForm.subsecseqno.length > 0 ){


if(trim(subSecForm.subSecName[index].value).length==0 || trim(subSecForm.subSecName[index].value)=="" ){
var id = '100';
hideErrors();
addMsgNum(id);
showScrollErrors("subSecName",index);
return false;
}
}else{
if(trim(subSecForm.subSecName.value).length==0 || trim(subSecForm.subSecName.value)=="" ){
var id = '100';
hideErrors();
addMsgNum(id);
showScrollErrors("subSecName",-1);
return false;
}
}
}
if(SubSecMaintForm.subsecseqno.length > 0){
var cnt = SubSecMaintForm.subsecseqno.length;
for(var i=0;i<cnt;i++){
if(SubSecMaintForm.subsecseqno[i].checked){

SubSecMaintForm.hdnsectionName.value=SubSecMaintForm.subSecName[i].value;

SubSecMaintForm.hdnSectionComments.value=SubSecMaintForm.subSecDesc[i].value;

break;
}
}
}else{
if(SubSecMaintForm.subsecseqno.checked){

SubSecMaintForm.hdnsectionName.value=SubSecMaintForm.subSecName.value;
SubSecMaintForm.hdnSectionComments.value=SubSecMaintForm.subSecDesc.value;


}
}
if(subSecForm.subsecseqno.length > 0){
var cnt = subSecForm.subsecseqno.length;
for(var i=0;i<cnt;i++){
if(subSecForm.subsecseqno[i].checked){
subsecseqno = true;
index = i;

}
}
}




if(subSecForm.hdnSelectedSec.value != subSecForm.sectionseqno.options[document.forms[0].sectionseqno.selectedIndex].value){

var id= '207';
hideErrors();
addMsgNum(id);
showScrollErrors("searchButton",-1);
return false;

}
if(trim(document.forms[0].hdnSectionComments.value) !=""){
if(trim(document.forms[0].hdnSectionComments.value).length>2000){
var id = '517';
hideErrors();
addMsgNum(id);
showScrollErrors("subSecDesc",index);
return false;


}

}

//Added For CR_84
document.forms[0].hdSelectedSpecType.value=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
document.SubSecMaintForm.hdnModel.value=document.SubSecMaintForm.modelseqno.options[document.SubSecMaintForm.modelseqno.selectedIndex].text;

document.SubSecMaintForm.hdnSection.value=document.SubSecMaintForm.sectionseqno.options[document.SubSecMaintForm.sectionseqno.selectedIndex].text;

document.forms[0].action="SubSectionAction.do?method=updateSubSection";
document.forms[0].submit();

}

/*
*  Name: fnDeleteSubSections
*   Purpose: Used to delete Subsections.
*    Modified for LSDB_CR-46
*    on 28-Aug-08 by ps57222
*/


function fnDeleteSubSections(){

var subSecForm = document.forms[0];
var index;
subsecseqno = false;


if (document.forms[0].modelseqno.options[document.forms[0].modelseqno.selectedIndex].value =="-1")
{

var id = '19';
hideErrors();
addMsgNum(id);
showScrollErrors("modelseqno",-1);
return false;

}
if (document.forms[0].sectionseqno.options[document.forms[0].sectionseqno.selectedIndex].value =="-1")
{

var id = '205';
hideErrors();
addMsgNum(id);
showScrollErrors("sectionseqno",-1);
return false;

}

if(subSecForm.subsecseqno.length > 0 ){
var cnt = subSecForm.subsecseqno.length;
for(var i=0;i<cnt;i++){
if(subSecForm.subsecseqno[i].checked){
subsecseqno = true;
index = i;

break;
}
}
}
else{
if(subSecForm.subsecseqno.checked){
subsecseqno = true;
}
}if (!subsecseqno)
{
var id= '794';
hideErrors();
addMsgNum(id);
showScrollErrors("subsecseqno",-1);
return false;


}






if(subSecForm.hdnSelectedSec.value != subSecForm.sectionseqno.options[document.forms[0].sectionseqno.selectedIndex].value){

var id= '207';
hideErrors();
addMsgNum(id);
showScrollErrors("searchButton",-1);
return false;

}

var accept=confirm("Are you sure to delete this SubSection?");
if(accept == true){
//Added For CR_84
document.forms[0].hdSelectedSpecType.value=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
document.SubSecMaintForm.hdnModel.value=document.SubSecMaintForm.modelseqno.options[document.SubSecMaintForm.modelseqno.selectedIndex].text;
document.SubSecMaintForm.hdnSection.value=document.SubSecMaintForm.sectionseqno.options[document.SubSecMaintForm.sectionseqno.selectedIndex].text;
document.forms[0].action="SubSectionAction.do?method=deleteSubSection";
document.forms[0].submit();

}else{

}


}

/*
* Name: fnLoadModels
*    Purpose: Used to load the Models on chage of Specificationtype.
*   Added for LSDB_CR-46
*    on 28-Aug-08 by ps57222
*/

function fnLoadModels()
{
/* commented for CR_83
if (document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1")
{
var id = '2';
hideErrors();
addMsgNum(id);
showErrors();
return false;

}*/
document.forms[0].action="SubSectionAction.do?method=fetchModels";
document.forms[0].submit();
}