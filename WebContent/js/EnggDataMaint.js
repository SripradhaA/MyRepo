function fnAdd()
{
var Engg = document.forms[0];
var StandardEquipDesc=trim(Engg.stdEquipDesc.value);
if(StandardEquipDesc.length==0 || StandardEquipDesc.value=="" )
{

var id = '801';
hideErrors();
addMsgNum(id);
showScrollErrors("stdEquipDesc",-1);
return false;

}
document.forms[0].action="EnggDataMaintenance.do?method=insertStandardEquipment";
document.forms[0].submit();
}

//method updated for CR-114 to adding engineering data description.
function fnModify()
{

var Engg = document.forms[0];

var StdSeqNo=Engg.standardEquipmentSeqNo;
var len=Engg.standardEquipmentSeqNo.length;
var description;
var enggDataDescription;
flag=false;

if(Engg.standardEquipmentDesc.length!=undefined){

for(var i=0; i<len; i++){

if(Engg.standardEquipmentSeqNo[i].checked){
Engg.hdStdEquipDesc.value =trim(Engg.standardEquipmentDesc[i].value);
Engg.hdEnggDataDesc.value =trim(Engg.stdEnggDataDesc[i].value);
description=Engg.hdStdEquipDesc.value;
enggDataDescription = Engg.hdEnggDataDesc.value;
index = i;
flag = true;
break;
}

}
}
else{

if(StdSeqNo.checked){

Engg.hdStdEquipDesc.value =trim(Engg.standardEquipmentDesc.value);
Engg.hdEnggDataDesc.value =trim(Engg.stdEnggDataDesc.value);
description=Engg.hdStdEquipDesc.value;
enggDataDescription = Engg.hdEnggDataDesc.value;
flag=true;
}
}



if(flag){

if(description=="" || description==0)
{

var id = '801';
hideErrors();
addMsgNum(id);
showScrollErrors("standardEquipmentDesc",[i]);
return false;

}
}
else{
	var index=-1;
var id = '802';
hideErrors();
addMsgNum(id);
showScrollErrors("standardEquipmentSeqNo",-1);
return false;

}

document.forms[0].action="EnggDataMaintenance.do?method=modifyStandardEquipment";
document.forms[0].submit();


}

function fnDelete()
{
var Engg = document.forms[0];

var StdSeqNo=Engg.standardEquipmentSeqNo;
var len=Engg.standardEquipmentSeqNo.length;
flag=false;

if(StdSeqNo.length!=undefined){

for(var i=0; i<len; i++){

if(Engg.standardEquipmentSeqNo[i].checked){

index = i;
flag = true;
break;
}

}
}
else{

if(StdSeqNo.checked){
flag=true;
}
}

if(!flag){

var id = '802';
hideErrors();
addMsgNum(id);
showScrollErrors("standardEquipmentSeqNo",-1);
return false;

}
var del=confirm("Are you sure to delete the Engineering Data ?");

if(del)
{
document.forms[0].action="EnggDataMaintenance.do?method=deleteStandardEquipment";
document.forms[0].submit();
}else{

}



}