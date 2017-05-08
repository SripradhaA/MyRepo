/*
* Name: fnSearch
*  Purpose: Used to Search the sections based on the Model Selected.
*    Modified for LSDB_CR-46
*    on 28-Aug-08 by ps57222
*/


function fnSearch()
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

document.forms[0].hdSelectedSpecType.value=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
document.forms[0].hdSelectedModel.value = document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].text ;
document.forms[0].action="SectionMaintenance.do?method=fetchSections";
document.forms[0].submit();

return true
}


/*
*    Name: fnAdd
* Purpose: Used to Add the sections.
*    Modified for LSDB_CR-46
*    on 28-Aug-08 by ps57222
*/


function fnAdd()
{

var sec = document.forms[0];
flag = false;

var SectionName=trim(sec.secName.value);
var comments= trim(sec.secComments.value);

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


if(SectionName.length==0 || SectionName.value=="" )
{

var id = '18';
hideErrors();
addMsgNum(id);
showScrollErrors("secName",-1);
return false;

}
if (comments.length>2000)
{
var id = '517';
hideErrors();
addMsgNum(id);
showScrollErrors("secComments",-1);
return false;

}
//Added For CR_84
document.forms[0].hdSelectedSpecType.value=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
document.forms[0].hdSelectedModel.value = document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].text ;

document.forms[0].action="SectionMaintenance.do?method=insertSection";
document.forms[0].submit();


}

/*
* Name: fnModify
*  Purpose: Used to Modify the sections.
*    Modified for LSDB_CR-46
*    on 28-Aug-08 by ps57222
*/

function fnModify()
{

var sec = document.forms[0];
flag = false;


hideErrors();
var index;
secchecked = false;



if(sec.hdPreSelectedModel.value!=sec.modelSeqNo.options[sec.modelSeqNo.selectedIndex].value){


var id = '207';
hideErrors();
addMsgNum(id);
showScrollErrors("searchButton",-1);
return false;
}




if(sec.sectionName.length!=undefined){
for(var i = 0 ; i < sec.sectionSeqNo.length ; i++){
if(sec.sectionSeqNo[i].checked){
secchecked = true;
index = i;
break;

}

}
}else{

if(sec.sectionSeqNo.checked)
secchecked = true;

}

if (!secchecked)
{
var id= '17';
addMsgNum(id);
flag = true;
showScrollErrors("sectionSeqNo",-1);
}else{


if(sec.sectionName.length!=undefined){

var sectionname=trim(sec.sectionName[index].value);
var comments=trim(sec.sectionComments[index].value)
if(sectionname.length==0 || sectionname.value=="" ){


var id = '18';
addMsgNum(id);
flag = true;
showScrollErrors("sectionName",index);
}
}else{

if(sec.sectionName.value.length==0 || trim(sec.sectionName.value)=="" ){

var id = '18';
addMsgNum(id);
flag = true;
showScrollErrors("sectionName",-1);
}

}
}



if(flag){

//showScrollErrors("sectionSeqNo",-1);
return false;
}



if(sec.sectionName.length!=undefined){
for(var i = 0 ; i < document.forms[0].sectionSeqNo.length ; i++){

if(document.forms[0].sectionSeqNo[i].checked){

document.forms[0].hdsection.value = trim(document.forms[0].sectionName[i].value);
if (trim(document.forms[0].sectionComments[i].value).length>2000)
{
var id = '517';
hideErrors();
addMsgNum(id);
showScrollErrors("sectionComments",index);
return false;
}
else
{
document.forms[0].hdSecComments.value=trim(document.forms[0].sectionComments[i].value);
break;
}
}
}
}else{

document.forms[0].hdsection.value = trim(document.forms[0].sectionName.value);
if (trim(document.forms[0].sectionComments.value).length>2000)
{
var id = '517';
hideErrors();
addMsgNum(id);
showScrollErrors("sectionComments",index);
return false;
}
else
{
document.forms[0].hdSecComments.value=trim(document.forms[0].sectionComments.value);
}
}
//Added For CR_84
document.forms[0].hdSelectedSpecType.value=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
document.forms[0].hdSelectedModel.value = document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].text ;
document.forms[0].action="SectionMaintenance.do?method=updateSection";
document.forms[0].submit();
}
/*
function fnDelete(){

var sec = document.forms[0];
var index;
secchecked = false;

if(sec.hdPreSelectedModel.value!=sec.modelSeqNo.options[sec.modelSeqNo.selectedIndex].value){


var id = '207';
hideErrors();
addMsgNum(id);
showErrors();
return false;
}

if(sec.sectionSeqNo.length>0){

for(var i=0;i<sec.sectionSeqNo.length;i++){

if(sec.sectionSeqNo[i].checked){
secchecked = true;
index = i;
break;

}

}

}else{

if(sec.sectionSeqNo.checked){
secchecked = true;
}
}

if(!secchecked){

var id= '206';
hideErrors();
addMsgNum(id);
showErrors();
return false;

}

var accept=confirm("Are you sure you want to delete this section?Once a SubSection is attached to the Section, the Section can no longer be deleted");
if(accept == true){

document.forms[0].hdSelectedModel.value = document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].text ;
document.forms[0].action="SectionMaintenance.do?method=deleteSection";
document.forms[0].submit();

}else{

}


} */


/*
* Name: fnDelete
*   Purpose: Used to delete the sections.If The SpecificationType is locomotive then the section Supplemental Information cannot be deleted.
*   If The SpecificationType is PM&I then the section's M,S,Z cannot be deleted.
*   Added for LSDB_CR-46
*    on 28-Aug-08 by ps57222
*/

function fnDelete(){

var sec = document.forms[0];
var index;
secchecked = false;
seccode = false;
PMSeccode = false;
if(sec.hdPreSelectedModel.value!=sec.modelSeqNo.options[sec.modelSeqNo.selectedIndex].value){


var id = '207';
hideErrors();
addMsgNum(id);
showScrollErrors("searchButton",-1);
return false;
}

if(sec.sectionSeqNo.length>0){

for(var i=0;i<sec.sectionSeqNo.length;i++){

if(sec.sectionSeqNo[i].checked){
secchecked = true;
index = i;
break;

}

}

}else{

if(sec.sectionSeqNo.checked){
secchecked = true;
}
}

if(!secchecked){

var id= '804';
hideErrors();
addMsgNum(id);
showScrollErrors("sectionSeqNo",-1);
return false;

}
if (document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="1")
{
if(sec.sectionSeqNo.length>0){

if(sec.sectionCode[index].value=="Z"){
seccode = true;

}
}else{
if(sec.sectionCode.value=="Z"){
seccode = true;
}
}
}
if (document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="2")
{
if(sec.sectionSeqNo.length>0){

if(sec.sectionCode[index].value=="Z" || sec.sectionCode[index].value=="S" || sec.sectionCode[index].value=="M"){
PMSeccode = true;

}
}else{
if(sec.sectionCode[index].value=="Z" || sec.sectionCode[index].value=="S" || sec.sectionCode[index].value=="M"){
PMSeccode = true;
}
}
}
if(seccode){

var id= '797';
hideErrors();
addMsgNum(id);
showScrollErrors("sltSpecType",-1);
return false;

}
if(PMSeccode){

var id= '817';
hideErrors();
addMsgNum(id);
showScrollErrors("sltSpecType",-1);
return false;

}



var accept=confirm("Are you sure to delete this Section?");
if(accept == true){
//Added For CR_84
document.forms[0].hdSelectedSpecType.value=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
document.forms[0].hdSelectedModel.value = document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].text ;
document.forms[0].action="SectionMaintenance.do?method=deleteSection";
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
document.forms[0].action="SectionMaintenance.do?method=fetchModels";
document.forms[0].submit();
}
