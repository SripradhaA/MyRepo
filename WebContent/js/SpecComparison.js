//Added for CR-66
  
function keyHandle(e)
        { 
    
      var asciiValue = e ? e.which : window.event.keyCode;
if(asciiValue ==13){
	   
	   
	   
		if(document.forms[0].screen.value=="Clause"){
        
        	if(document.forms[0].flag.value == "Y"){
        		fnSearchOrdersForClauseComparison();
				return false;
			}
		
		
		}
		
		if(document.forms[0].screen.value=="Comp"){
        	if(document.forms[0].flag.value == "Y"){
        		fnSearchOrdersForComponentComparison();
				return false;
			}
		}
        
		
        }
  
}
   
   document.onkeypress = keyHandle;    





function fnLoadModelsForComparison()
{
var clauseCompareForm = document.forms[0];
document.forms[0].hdnSelSpecType.value=clauseCompareForm.spectypeSeqno.options[clauseCompareForm.spectypeSeqno.selectedIndex].text;
document.forms[0].action="compareSpecAction.do?method=fetchModels";
document.forms[0].submit();
}

function fnSearchOrdersForClauseComparison()
{

var specComparisonForm = document.forms[0];

if(specComparisonForm.spectypeSeqno.options[specComparisonForm.spectypeSeqno.selectedIndex].value=="-1"){

var id = '2';
hideErrors();
addMsgNum(id);
showScrollErrors("sltSpecType",-1);
return false;
}




if(specComparisonForm.modelSeqNos.selectedIndex=="-1"){
var id = '19';
hideErrors();
addMsgNum(id);
showScrollErrors("sltModel",-1);
return false;
}
if(trim(specComparisonForm.orderNo.value)=="" || specComparisonForm.orderNo.length==0)
{
var id = '105';
hideErrors();
addMsgNum(id);
showScrollErrors("orderNo",-1);
return false;

}
//Modified for CR_104 to allow Wild Card Searches JG101007
var invalid=WildCardSearchCheck(trim(specComparisonForm.orderNo.value));

if(invalid)

{
var id = '105';
hideErrors();
addMsgNum(id);
showScrollErrors("orderNo",-1);
return false;
}

if(trim(specComparisonForm.orderNo.value)=="" || trim(specComparisonForm.orderNo.value).length==0)
{
var id = '105';
hideErrors();
addMsgNum(id);
showScrollErrors("orderNo",-1);
return false;

}

//CR 90 for Order Number -removing number format validation	 --Start
//if(!isNumeric(trim(specComparisonForm.orderNo.value)))
//{
//var id = '105';
//hideErrors();
//addMsgNum(id);
//showErrors();
//
//specComparisonForm.orderNo.focus();
//return false;
//
//}


//if(isNaN(trim(specComparisonForm.orderNo.value))){
//var id = '105';
//hideErrors();
//addMsgNum(id);
//showErrors();
//
//specComparisonForm.orderNo.focus();
//return false;
//}
//CR 90 for Order Number -removing number format validation	 --End


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
document.forms[0].flag.value="N";
document.forms[0].hdnSelectedMdlNames.value=modelnames;
document.forms[0].hdnSelSpecTypeName.value=document.forms[0].hdnSelSpecType.value=specComparisonForm.spectypeSeqno.options[specComparisonForm.spectypeSeqno.selectedIndex].text;
document.forms[0].hdnSelSpecType.value=specComparisonForm.spectypeSeqno.value;
document.forms[0].hdnorderNo.value=trim(specComparisonForm.orderNo.value);
document.forms[0].action="compareSpecAction.do?method=fetchOrdersForSpecComparison";
document.forms[0].submit();
}



function fnSearchOrdersForComponentComparison()
{

var specComparisonForm = document.forms[0];


if(specComparisonForm.spectypeSeqno.options[specComparisonForm.spectypeSeqno.selectedIndex].value=="-1"){

var id = '2';
hideErrors();
addMsgNum(id);
showScrollErrors("sltSpecType",-1);
return false;
}


if(specComparisonForm.modelSeqNos.selectedIndex=="-1"){
var id = '19';
hideErrors();
addMsgNum(id);
showScrollErrors("sltModel",-1);
return false;
}

if(trim(specComparisonForm.orderNo.value)=="" || trim(specComparisonForm.orderNo.value).length==0)
{
var id = '105';
hideErrors();
addMsgNum(id);
showScrollErrors("orderNo",-1);
return false;

}
//Added for CR_104 to allow Wild Card Searches JG101007
if(WildCardSearchCheck(trim(specComparisonForm.orderNo.value))==true){
var id = '105';
hideErrors();
addMsgNum(id);
showScrollErrors("orderNo",-1);
return false;
}



if(trim(specComparisonForm.orderNo.value)=="" || trim(specComparisonForm.orderNo.value).length==0)
{
var id = '105';
hideErrors();
addMsgNum(id);
showScrollErrors("orderNo",-1);
return false;

}
//CR 90 for Order Number -removing number format validation	 --Start

//if(isNaN(trim(specComparisonForm.orderNo.value))){
//var id = '105';
//hideErrors();
//addMsgNum(id);
//showErrors();
//specComparisonForm.orderNo.focus();
//return false;
//}
//
//if(!isNumeric(trim(specComparisonForm.orderNo.value)))
//{
//var id = '105';
//hideErrors();
//addMsgNum(id);
//showErrors();
//
//specComparisonForm.orderNo.focus();
//return false;
//
//}
//CR 90 for Order Number -removing number format validation	 --End




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
document.forms[0].flag.value="N";
document.forms[0].hdnSelectedMdlNames.value=modelnames;
document.forms[0].hdnSelSpecTypeName.value=document.forms[0].hdnSelSpecType.value=specComparisonForm.spectypeSeqno.options[specComparisonForm.spectypeSeqno.selectedIndex].text;
document.forms[0].hdnSelModels.value=specComparisonForm.modelSeqNos.value;
/*Added by cm68219 to remove  spaces in the orderno for search criteria*/
document.forms[0].hdnorderNo.value=trim(specComparisonForm.orderNo.value);
document.forms[0].action="compareComponentAction.do?method=fetchOrdersForComponentComparison";
document.forms[0].submit();

}


function fnCompareClauseComparison(){
var orderKeys="";
var selectedSection="";
var sectionIds="";
var selectedModels="";
var orderNumbers="";
var selectedSpecType="";
var customerName="";
var selectedCustModelNames="";
//Modified for CR_131 to sanitize the code
var j=0;
var index1=-1;
clearAlerts();//Sdded for CR-131 to clear alert tooltip
$('.ordNoChkbox:checked').each(function() {
	  checkBox=true;
	  j=j+1;
	  index1=$('.ordNoChkbox').index($('.ordNoChkbox:checked'));
	  if(orderKeys==""){
		  orderKeys=$(this).val();
	  }else{
		  orderKeys=orderKeys+","+$(this).val();
	  }
	  if(orderNumbers==""){
		  orderNumbers=$(this).closest('tr').children('td').eq(1).text().trim();
	  }else{
		  orderNumbers=orderNumbers+","+$(this).closest('tr').children('td').eq(1).text().trim();
	  }
	  if(selectedModels==""){
		  selectedModels=$(this).closest('tr').children('td').eq(2).text().trim();
	  }else{
		  selectedModels=selectedModels+","+$(this).closest('tr').children('td').eq(2).text().trim();
	  }
	  if(selectedCustModelNames==""){
		  selectedCustModelNames=$(this).closest('tr').children('td').eq(3).text().trim();
	  }else{
		  selectedCustModelNames=selectedCustModelNames+","+$(this).closest('tr').children('td').eq(3).text().trim();
	  }
	  if(selectedSpecType==""){
		  selectedSpecType=$(this).closest('tr').children('td').eq(4).text().trim();
	  }else{
		  selectedSpecType=selectedSpecType+","+$(this).closest('tr').children('td').eq(4).text().trim();
	  }
	  if(customerName==""){
		  customerName=$(this).closest('tr').children('td').eq(5).text().trim().replace(/,/g, "^"); //replace function added for production issue on 27thJan15
	  }else{
		  customerName=customerName+","+$(this).closest('tr').children('td').eq(5).text().trim().replace(/,/g, "^"); //replace function added for production issue on 27thJan15
	  }
	  var select1=$(this).closest('tr').children('td').eq(7).children()[0];
	  if(sectionIds==""){
		  sectionIds=select1.options[select1.selectedIndex].value;
		  selectedSection=select1.options[select1.selectedIndex].text;//CR_131
	  }else{
		  sectionIds=sectionIds+","+select1.options[select1.selectedIndex].value;
		  selectedSection=selectedSection+","+select1.options[select1.selectedIndex].text;//CR_131
	  }
});
/*
//Modified for CR_104 to fix the issue with checkbox selection

var obj = $('.ordNoChkbox'); //document.getElementsByTagName("input");
var j=0;
var index1=0; //Added for CR_121
for(i=0;i<obj.length;i++){
if(obj[i].type == "checkbox") {
if((obj[i].checked)){
	index1=i; //Added for CR_121
j=j+1;
if(orderKeys==""){
orderKeys=obj[i].value;
}else{
orderKeys=orderKeys+","+obj[i].value;
}
var no=obj[i].parentNode.parentNode;
if(orderNumbers==""){
orderNumbers=no.childNodes[1].innerText;
}else{
orderNumbers=orderNumbers+","+no.childNodes[1].innerText;
}
if(selectedModels==""){
selectedModels=no.childNodes[2].innerText;
}else{
selectedModels=selectedModels+","+no.childNodes[2].innerText;
}
if(selectedCustModelNames==""){
selectedCustModelNames=no.childNodes[3].innerText;
}else{
selectedCustModelNames=selectedCustModelNames+","+no.childNodes[3].innerText;
}


if(selectedSpecType==""){
selectedSpecType=no.childNodes[4].innerText;
}else{
selectedSpecType=selectedSpecType+","+no.childNodes[4].innerText;
}
if(customerName==""){
customerName=no.childNodes[5].innerText.replace(/,/g, "^"); //replace function added for production issue on 27thJan15
}else{
customerName=customerName+","+no.childNodes[5].innerText.replace(/,/g, "^"); //replace function added for production issue on 27thJan15
}
var select1=no.childNodes[7].children[0];
if(sectionIds==""){
sectionIds=select1.options[select1.selectedIndex].value;
selectedSection=select1.options[select1.selectedIndex].innerText;
}else{
sectionIds=sectionIds+","+select1.options[select1.selectedIndex].value;
selectedSection=selectedSection+","+select1.options[select1.selectedIndex].innerText;
}

}
}


}*/
if(j==2 || j==3){
	var sections=sectionIds.split(",");
	var selectFlag=false;
	var unSelected=false;
	if(j==3){
		var id1=sections[0];
		var id2=sections[1];
		var id3=sections[2];
		if(id1== -1 ||id2==-1 ||id3==-1){
			unSelected=true;
		}
		else{
			selectFlag=true;
		}
	}
	if(j==2){
		var id1=sections[0];
		var id2=sections[1];
		if(id1== -1 ||id2==-1){
			unSelected=true;
		}
		else{
			selectFlag=true;
		}
	}
	//Added for LSDB_CR-75 by cm68219
	var secSeqNos = sectionIds.split(",");
	var index = secSeqNos.length;
	for(var outter =0 ; outter < index ; outter++){

		for(var inner = 0 ; inner < index; inner++){

			if((secSeqNos[outter] == -2 || secSeqNos[inner]==-2) && secSeqNos[outter]!= secSeqNos[inner]){
				var id = '860';
                hideErrors();
                addMsgNum(id);
                showScrollErrors("modelSeqNo",index1);
                return false;
			}
		}
	}	
if(selectFlag==true && unSelected==false)
{
var URL="compareSpecAction.do?method=fetchOrdersForClauseComparison&orderKeys="+orderKeys+"&sectionIds="+sectionIds+"&selectedModels="+escape(selectedModels)+"&orderNumbers="+orderNumbers+"&selectedSpecType="+escape(selectedSpecType)+"&customerName="+escape(customerName)+"&selectedSection="+selectedSection;
window.open(URL,'Clause',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH="+screen.width+",height=600");
}
else{
if(unSelected){
var id='406';
hideErrors();
addMsgNum(id);
showScrollErrors("sltSection",-1);
return false;
}else{
var id = '405';
hideErrors();
addMsgNum(id);
showScrollErrors("sltSection",-1);
return false;
}
} 
}else{
if(j<2){
var id = '401';
hideErrors();
addMsgNum(id);
showScrollErrors("orderKeyCheckBoxId",index1);
return false;
}
if(j>3){
var id = '402';
hideErrors();
addMsgNum(id);
showScrollErrors("orderKeyCheckBoxId",index1);
return false;
}
}
}
function fnClauseComponentComparisonForExcel(){
var selectedSection="";
var orderKeys="";
var sectionIds="";
var selectedModels="";
var orderNumbers="";
var selectedSpecType="";
var customerName="";
var selectedCustModelNames="";
//Modified for CR_131 to sanitize the code
var j=0;
var index1=-1;
clearAlerts();//Sdded for CR-131 to clear alert tooltip
$('.ordNoChkbox:checked').each(function() {
	  checkBox=true;
	  j=j+1;
	  index1=$('.ordNoChkbox').index($('.ordNoChkbox:checked'));
	  if(orderKeys==""){
		  orderKeys=$(this).val();
	  }else{
		  orderKeys=orderKeys+","+$(this).val();
	  }
	  if(orderNumbers==""){
		  orderNumbers=$(this).closest('tr').children('td').eq(1).text().trim();
	  }else{
		  orderNumbers=orderNumbers+","+$(this).closest('tr').children('td').eq(1).text().trim();
	  }
	  if(selectedModels==""){
		  selectedModels=$(this).closest('tr').children('td').eq(2).text().trim();
	  }else{
		  selectedModels=selectedModels+","+$(this).closest('tr').children('td').eq(2).text().trim();
	  }
	  if(selectedCustModelNames==""){
		  selectedCustModelNames=$(this).closest('tr').children('td').eq(3).text().trim();
	  }else{
		  selectedCustModelNames=selectedCustModelNames+","+$(this).closest('tr').children('td').eq(3).text().trim();
	  }
	  if(selectedSpecType==""){
		  selectedSpecType=$(this).closest('tr').children('td').eq(4).text().trim();
	  }else{
		  selectedSpecType=selectedSpecType+","+$(this).closest('tr').children('td').eq(4).text().trim();
	  }
	  if(customerName==""){
		  customerName=$(this).closest('tr').children('td').eq(5).text().trim().replace(/,/g, "^"); //replace function added for production issue on 27thJan15
	  }else{
		  customerName=customerName+","+$(this).closest('tr').children('td').eq(5).text().trim().replace(/,/g, "^"); //replace function added for production issue on 27thJan15
	  }
	  var select1=$(this).closest('tr').children('td').eq(7).children()[0];
	  if(sectionIds==""){
		  sectionIds=select1.options[select1.selectedIndex].value;
		  selectedSection=select1.options[select1.selectedIndex].innerText;
	  }else{
		  sectionIds=sectionIds+","+select1.options[select1.selectedIndex].value;
		  selectedSection=selectedSection+","+select1.options[select1.selectedIndex].innerText;
	  }
});
/*
//Modified for CR_104 to fix the issue with checkbox selection

var obj = $('.ordNoChkbox'); //document.getElementsByTagName("input");
var j=0;
var index1=0; //Added for CR_121
for(i=0;i<obj.length;i++){
if(obj[i].type == "checkbox") {
if((obj[i].checked)){
	index1=i; //Added for CR_121
j=j+1;
if(orderKeys==""){
orderKeys=obj[i].value;
}else{
orderKeys=orderKeys+","+obj[i].value;
}
var no=obj[i].parentNode.parentNode;
if(orderNumbers==""){
orderNumbers=no.childNodes[1].innerText;
}else{
orderNumbers=orderNumbers+","+no.childNodes[1].innerText;
}
if(selectedModels==""){
selectedModels=no.childNodes[2].innerText;
}else{
selectedModels=selectedModels+","+no.childNodes[2].innerText;
}

if(selectedModels==""){
selectedModels=no.childNodes[3].innerText;
}else{
selectedModels=selectedModels+","+no.childNodes[3].innerText;
}
if(selectedSpecType==""){
selectedSpecType=no.childNodes[4].innerText;
}else{
selectedSpecType=selectedSpecType+","+no.childNodes[4].innerText;
}
if(customerName==""){
customerName=no.childNodes[5].innerText.replace(/,/g, "^"); //replace function added for production issue on 27thJan15
}else{
customerName=customerName+","+no.childNodes[5].innerText.replace(/,/g, "^"); //replace function added for production issue on 27thJan15
}
var select1=no.childNodes[7].children[0];
if(sectionIds==""){
sectionIds=select1.options[select1.selectedIndex].value;
selectedSection=select1.options[select1.selectedIndex].innerText;
}else{
sectionIds=sectionIds+","+select1.options[select1.selectedIndex].value;
selectedSection=selectedSection+","+select1.options[select1.selectedIndex].innerText;
}
}
}
}*/
if(j==2 || j==3){
var sections=sectionIds.split(",");
var selectFlag=false;
var unSelected=false;
if(j==3){
var id1=sections[0];
var id2=sections[1];
var id3=sections[2];
if(id1== -1 ||id2==-1 ||id3==-1){
unSelected=true;
}
else{
selectFlag=true;
}
}
if(j==2){
var id1=sections[0];
var id2=sections[1];
if(id1== -1 ||id2==-1){
unSelected=true;
}
else{
selectFlag=true;
}
}
//Added for LSDB_CR-75 by cm68219
var secSeqNos = sectionIds.split(",");
	var index = secSeqNos.length;

	for(var outter =0 ; outter < index ; outter++){

		for(var inner = 0 ; inner < index; inner++){

			if((secSeqNos[outter] == -2 || secSeqNos[inner]==-2) && secSeqNos[outter]!= secSeqNos[inner]){
				var id = '860';
                hideErrors();
                addMsgNum(id);
                showScrollErrors("modelSeqNo",index1);
                return false;
			}
		}
	}
if(selectFlag==true && unSelected==false)
{
	//Added for  CR-128
	 document.forms[0].action = "compareSpecAction.do?method=exportClauseComparisonForExcel&orderKeys="+orderKeys+"&sectionIds="+sectionIds+"&selectedModels="+escape(selectedModels)+"&orderNumbers="+orderNumbers+"&selectedSpecType="+escape(selectedSpecType)+"&customerName="+escape(customerName)+"&selectedSection="+selectedSection;
     document.forms[0].submit();	
/* Commented for CR-128
var URL="compareSpecAction.do?method=fnCompareClauseComparisonForExcel&orderKeys="+orderKeys+"&sectionIds="+sectionIds+"&selectedModels="+escape(selectedModels)+"&orderNumbers="+orderNumbers+"&selectedSpecType="+escape(selectedSpecType)+"&customerName="+escape(customerName)+"&selectedSection="+selectedSection;
window.open(URL,'Clause',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH="+screen.width+",height=600");
*/
}
else{
if(unSelected){
var id='406';
hideErrors();
addMsgNum(id);
showScrollErrors("orderKey",-1);
return false;
}else{
var id = '405';
hideErrors();
addMsgNum(id);
showScrollErrors("secSeqNos",-1);
return false;
}
}
}else{
if(j<2){
var id = '401';
hideErrors();
addMsgNum(id);
showScrollErrors("orderKeyCheckBoxId",index1);
return false;
}
if(j>3){
var id = '402';
hideErrors();
addMsgNum(id);
showScrollErrors("orderKeyCheckBoxId",index1);
return false;
}
}
}


function fnCompareComponentComparison(){


var orderKeys="";
var sectionIds="";
var sectionIdsNames="";
var selectedModels="";
var orderNumbers="";
var selectedSpecType="";
var customerName="";
//Added For CR_104 
var selectedCustModelNames="";
var checkBox=false;
//Modified for CR_104 to fix the issue with checkbox selection
//Modified for CR_131 to sanitize the code
var j=0;
var index1=0;
clearAlerts();//Sdded for CR-131 to clear alert tooltip
$('.ordNoChkbox:checked').each(function() {
	  checkBox=true;
	  j=j+1;
	  index1=$('.ordNoChkbox').index($('.ordNoChkbox:checked'));
	  if(orderKeys==""){
		  orderKeys=$(this).val();
	  }else{
		  orderKeys=orderKeys+","+$(this).val();
	  }
	  if(orderNumbers==""){
		  orderNumbers=$(this).closest('tr').children('td').eq(2).text().trim();
	  }else{
		  orderNumbers=orderNumbers+","+$(this).closest('tr').children('td').eq(2).text().trim();
	  }
	  if(selectedModels==""){
		  selectedModels=$(this).closest('tr').children('td').eq(3).text().trim();
	  }else{
		  selectedModels=selectedModels+","+$(this).closest('tr').children('td').eq(3).text().trim();
	  }
	  if(selectedCustModelNames==""){
		  selectedCustModelNames=$(this).closest('tr').children('td').eq(4).text().trim();
	  }else{
		  selectedCustModelNames=selectedCustModelNames+","+$(this).closest('tr').children('td').eq(4).text().trim();
	  }
	  if(selectedSpecType==""){
		  selectedSpecType=$(this).closest('tr').children('td').eq(5).text().trim();
	  }else{
		  selectedSpecType=selectedSpecType+","+$(this).closest('tr').children('td').eq(5).text().trim();
	  }
	  if(customerName==""){
		  customerName=$(this).closest('tr').children('td').eq(6).text().trim().replace(/,/g, "^"); //replace function added for production issue on 27thJan15
	  }else{
		  customerName=customerName+","+$(this).closest('tr').children('td').eq(6).text().trim().replace(/,/g, "^"); //replace function added for production issue on 27thJan15
	  }
	  var select1=$(this).closest('tr').children('td').eq(8).children()[0];
	  if(sectionIds==""){
		  sectionIds=select1.options[select1.selectedIndex].value;
		  sectionIdsNames=select1.name;
	  }else{
		  sectionIds=sectionIds+","+select1.options[select1.selectedIndex].value;
		  sectionIdsNames=sectionIdsNames+","+select1.name;
	  }
});

/*
var obj = $('.ordNoChkbox'); //document.getElementsByTagName("input");
var j=0;
var index1=0; //Added for CR_121
for(i=0;i<obj.length;i++){
if(obj[i].type == "checkbox") {
if((obj[i].checked)){
	alert(i);
	index1=i; //Added for CR_121

checkBox=true;
j=j+1;
if(orderKeys==""){
orderKeys=obj[i].value;
}else{
orderKeys=orderKeys+","+obj[i].value;
}
var no=obj[i].parentNode.parentNode;
alert(no);
alert(no.childNodes[0].innerText);
alert(no.childNodes[0].val());
if(orderNumbers==""){
orderNumbers=no.childNodes[2].innerText;
}else{
orderNumbers=orderNumbers+","+no.childNodes[2].innerText;
}
if(selectedModels==""){
selectedModels=no.childNodes[3].innerText;
}else{
selectedModels=selectedModels+","+no.childNodes[3].innerText;
}
if(selectedCustModelNames==""){
selectedCustModelNames=no.childNodes[4].innerText;
}else{
selectedCustModelNames=selectedCustModelNames+","+no.childNodes[4].innerText;
}
if(selectedSpecType==""){
selectedSpecType=no.childNodes[5].innerText;
}else{
selectedSpecType=selectedSpecType+","+no.childNodes[5].innerText;
}
if(customerName==""){
	alert(no.childNodes[6].innerText);
customerName=no.childNodes[6].innerText.replace(/,/g, "^"); //replace function added for production issue on 27thJan15
}else{
customerName=customerName+","+no.childNodes[6].innerText.replace(/,/g, "^"); //replace function added for production issue on 27thJan15
}
var select1=no.childNodes[8].children[0];//Edited for CR-130
if(sectionIds==""){
sectionIds=select1.options[select1.selectedIndex].value;

}else{
sectionIds=sectionIds+","+select1.options[select1.selectedIndex].value;
}

}
}
}*/

if(!checkBox){
                var id='861';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("orderKey",-1);
				return false;

}

//if(j==1 || j==2 || j==3){

var sections=sectionIds.split(",");
var selectFlag=false;
var unSelected=false;

//Added for LSDB_CR-65

for(var secid =0 ; secid < sections.length; secid++){

	if(sections[secid]==-1){
		unSelected=true;
		break;
	}
}
//Ends
//Commented for LSDB_CR-65 by ka57588
/*if(j==3){
var id1=sections[0];
var id2=sections[1];
var id3=sections[2];
	if(id1== -1 ||id2==-1 ||id3==-1){
	unSelected=true;
	}
	else{
	selectFlag=true;
	}
}



if(j==2){
var id1=sections[0];
var id2=sections[1];
	if(id1== -1 ||id2==-1){
	unSelected=true;
	}
	else{
	selectFlag=true;
	}
}
//Added for CR-58 Component Comparison/Report by VV49326 18-Nov-08
if(j==1){
var id1=sections[0];

	if(id1== -1 ){
	unSelected=true;
	}
	else{
	selectFlag=true;
	}
}
*/
	if(unSelected){
		var id='406';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("orderKey",-1);
		return false;
		}
	var secSeqNos = sectionIds.split(",");
	var secSeqNames = sectionIdsNames.split(",");
	var index = secSeqNos.length;

	//Added for LSDB_CR-65 by ka57588
	for(var outter =0 ; outter < index ; outter++){

		for(var inner = 0 ; inner < index; inner++){

			if((secSeqNos[outter] == -2 || secSeqNos[inner]==-2) && secSeqNos[outter]!= secSeqNos[inner]){
				var id = '860';
                hideErrors();
                addMsgNum(id);
                if (secSeqNos[outter] != -2)
                	showScrollErrors(secSeqNames[outter],-1);
                else
                	showScrollErrors(secSeqNames[inner],-1);
                return false;
			}
		}
	}
	//Ends
	//Commented for LSDB_CR-65 by ka57588
    //if(index >1){
       /*if(index==2){
         var no1 = secSeqNos[0];
         var no2 = secSeqNos[1];

          if((no1 == -2 || no2 == -2) &&  no1 != no2){
                var id = '860';
                hideErrors();
                addMsgNum(id);
                showErrors();
                return false;
           }
       
       
        }
             
       if(index==3){
       
       var no1 = secSeqNos[0];
       var no2 = secSeqNos[1];
       var no3 = secSeqNos[2];
       var flag = false;
       
       if(no1 != -2 && no2 != -2 && no3 != -2){
       
           flag=true;  
              
       }
       
       
       if( no1 == -2){
           
           if(no2 == -2)
       
               if(no3 == -2){
               
                    flag = true;
               
               }
       
            }       
     
       if( no2 == -2){
           
           if(no1 == -2)
       
               if(no3 == -2){
               
                    flag = true;
               
               }
       
            }  
            
            if( no3 == -2){
           
           if(no1 == -2)
       
               if(no2 == -2){
               
                    flag = true;
               
               }
       
            } 
            */
            
            
           /* if(!flag){
         var id = '860';
         hideErrors();
         addMsgNum(id);
         showErrors();
         return false;
        }*/
         // }
     
      // }

 //Added for CR-58 Component Comparison/Report by VV49326 18-Nov-08
           
     
if(!unSelected)
{

var URL="compareComponentAction.do?method=fetchSectionComponentsForComparison&orderKeys="+orderKeys+"&sectionIds="+sectionIds+"&selectedModels="+escape(selectedModels)+"&orderNumbers="+orderNumbers+"&selectedSpecType="+escape(selectedSpecType)+"&customerName="+escape(customerName);
window.open(URL,'Clause',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH="+screen.width+",height=600");
}
else{
if(unSelected){
var id='406';
hideErrors();
addMsgNum(id);
showScrollErrors("orderKey",-1);
return false;
}else{
var id = '405';
hideErrors();
addMsgNum(id);
showScrollErrors("orderKey",-1);
return false;
}
}
/*}else{

if(j>3){
var id = '402';
hideErrors();
addMsgNum(id);
showErrors();
return false;
}
}*/
}




function fnCompareComponentComparisonForExcel(){
var orderKeys="";
var sectionIds="";
var sectionIdsNames="";
var selectedModels="";
var orderNumbers="";
var selectedSpecType="";
var customerName="";
var selectedCustModelNames="";
var checkBox=false;
//Modified for CR_104 to fix the issue with checkbox selection
//Modified for CR_131 to sanitize the code
var j=0;
var index1=0;
clearAlerts();//Sdded for CR-131 to clear alert tooltip
$('.ordNoChkbox:checked').each(function() {
	  checkBox=true;
	  j=j+1;;
	  index1=$('.ordNoChkbox').index($('.ordNoChkbox:checked'));
	  if(orderKeys==""){
		  orderKeys=$(this).val();
	  }else{
		  orderKeys=orderKeys+","+$(this).val();
	  }
	  if(orderNumbers==""){
		  orderNumbers=$(this).closest('tr').children('td').eq(2).text().trim();
	  }else{
		  orderNumbers=orderNumbers+","+$(this).closest('tr').children('td').eq(2).text().trim();
	  }
	  if(selectedModels==""){
		  selectedModels=$(this).closest('tr').children('td').eq(3).text().trim();
	  }else{
		  selectedModels=selectedModels+","+$(this).closest('tr').children('td').eq(3).text().trim();
	  }
	  if(selectedCustModelNames==""){
		  selectedCustModelNames=$(this).closest('tr').children('td').eq(4).text().trim();
	  }else{
		  selectedCustModelNames=selectedCustModelNames+","+$(this).closest('tr').children('td').eq(4).text().trim();
	  }
	  if(selectedSpecType==""){
		  selectedSpecType=$(this).closest('tr').children('td').eq(5).text().trim();
	  }else{
		  selectedSpecType=selectedSpecType+","+$(this).closest('tr').children('td').eq(5).text().trim();
	  }
	  if(customerName==""){
		  customerName=$(this).closest('tr').children('td').eq(6).text().trim().replace(/,/g, "^"); //replace function added for production issue on 27thJan15
	  }else{
		  customerName=customerName+","+$(this).closest('tr').children('td').eq(6).text().trim().replace(/,/g, "^"); //replace function added for production issue on 27thJan15
	  }
	  var select1=$(this).closest('tr').children('td').eq(8).children()[0];
	  if(sectionIds==""){
		  sectionIds=select1.options[select1.selectedIndex].value;
		  sectionIdsNames=select1.name;
	  }else{
		  sectionIds=sectionIds+","+select1.options[select1.selectedIndex].value;
		  sectionIdsNames=sectionIdsNames+","+select1.name;
	  }
});
/*
var obj = $('.ordNoChkbox'); //document.getElementsByTagName("input");
var j=0;
var index1=0; //Added for CR_121
for(i=0;i<obj.length;i++){
if(obj[i].type == "checkbox") {
if((obj[i].checked)){
	index1=i; //Added for CR_121
checkBox=true;
j=j+1;
if(orderKeys==""){
orderKeys=obj[i].value;
}else{
orderKeys=orderKeys+","+obj[i].value;
}
var no=obj[i].parentNode.parentNode;
if(orderNumbers==""){
orderNumbers=no.childNodes[2].innerText;
}else{
orderNumbers=orderNumbers+","+no.childNodes[2].innerText;
}
if(selectedModels==""){
selectedModels=no.childNodes[3].innerText;
}else{
selectedModels=selectedModels+","+no.childNodes[3].innerText;
}
if(selectedCustModelNames==""){
selectedCustModelNames=no.childNodes[4].innerText;
}else{
selectedCustModelNames=selectedCustModelNames+","+no.childNodes[4].innerText;
}
if(selectedSpecType==""){
selectedSpecType=no.childNodes[5].innerText;
}else{
selectedSpecType=selectedSpecType+","+no.childNodes[5].innerText;
}
if(customerName==""){
customerName=no.childNodes[6].innerText.replace(/,/g, "^"); //replace function added for production issue on 27thJan15
}else{
customerName=customerName+","+no.childNodes[6].innerText.replace(/,/g, "^"); //replace function added for production issue on 27thJan15
}
var select1=no.childNodes[8].children[0];//Edited for CR-130
if(sectionIds==""){
sectionIds=select1.options[select1.selectedIndex].value;

}else{
sectionIds=sectionIds+","+select1.options[select1.selectedIndex].value;
}

}
}
}
*/
if(!checkBox){
	var id='861';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("orderKey",-1);
	return false;
}

//if(j==1 || j==2 || j==3){

var sections=sectionIds.split(",");
var selectFlag=false;
var unSelected=false;

//Added for LSDB_CR-65

for(var secid =0 ; secid < sections.length; secid++){

	if(sections[secid]==-1){
		unSelected=true;
		break;
	}
}
//Ends
//Commented for LSDB_CR-65 by ka57588
/*if(j==3){
var id1=sections[0];
var id2=sections[1];
var id3=sections[2];
	if(id1== -1 ||id2==-1 ||id3==-1){
	unSelected=true;
	}
	else{
	selectFlag=true;
	}
}



if(j==2){
var id1=sections[0];
var id2=sections[1];
	if(id1== -1 ||id2==-1){
	unSelected=true;
	}
	else{
	selectFlag=true;
	}
}
//Added for CR-58 Component Comparison/Report by VV49326 18-Nov-08
if(j==1){
var id1=sections[0];

	if(id1== -1 ){
	unSelected=true;
	}
	else{
	selectFlag=true;
	}
}
*/

if(unSelected){
	var id='406';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("orderKey",-1);
	return false;
}


var secSeqNos = sectionIds.split(",");
var secSeqNames = sectionIdsNames.split(",");
var index = secSeqNos.length;

 //Added for LSDB_CR-65 by ka57588  
for(var outter =0 ; outter < index ; outter++){

	for(var inner = 0 ; inner < index; inner++){

		if((secSeqNos[outter] == -2 || secSeqNos[inner]==-2) && secSeqNos[outter]!= secSeqNos[inner]){
			var id = '860';
			hideErrors();
			addMsgNum(id);
			if (secSeqNos[outter] != -2)
			    showScrollErrors(secSeqNames[outter],-1);
			else
			    showScrollErrors(secSeqNames[inner],-1);
			return false;
		}
	}
}

	//Commented for LSDB_CR-65 by ka57588
	/*if(index >1){
       if(index==2){
         var no1 = secSeqNos[0];
         var no2 = secSeqNos[1];

          if((no1 == -2 || no2 == -2) &&  no1 != no2){
                var id = '860';
                hideErrors();
                addMsgNum(id);
                showErrors();
                return false;
           }
       
       
        }
             
       if(index==3){
       
       var no1 = secSeqNos[0];
       var no2 = secSeqNos[1];
       var no3 = secSeqNos[2];
       var flag = false;
       
       if(no1 != -2 && no2 != -2 && no3 != -2){
       
           flag=true;  
              
       }
       
       
       if( no1 == -2){
           
           if(no2 == -2)
       
               if(no3 == -2){
               
                    flag = true;
               
               }
       
            }       
     
       if( no2 == -2){
           
           if(no1 == -2)
       
               if(no3 == -2){
               
                    flag = true;
               
               }
       
            }  
            
            if( no3 == -2){
           
           if(no1 == -2)
       
               if(no2 == -2){
               
                    flag = true;
               
               }
       
            } 
            
           */ 
            
        /* if(!flag){
			 var id = '860';
			 hideErrors();
			 addMsgNum(id);
			 showErrors();
			 return false;
        }*/
         // }
     
       //}

  //Added for CR-58 Component Comparison/Report by VV49326 18-Nov-08    
      
if(!unSelected)
{
var URL="compareComponentAction.do?method=fetchSectionComponentsForExcelComparison&orderKeys="+orderKeys+"&sectionIds="+sectionIds+"&selectedModels="+escape(selectedModels)+"&orderNumbers="+orderNumbers+"&selectedSpecType="+escape(selectedSpecType)+"&customerName="+escape(customerName);
window.open(URL,'Clause',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH="+screen.width+",height=600");
}
else{
if(unSelected){
var id='406';
hideErrors();
addMsgNum(id);
showScrollErrors("orderKey",-1);
return false;
}else{
var id = '405';
hideErrors();
addMsgNum(id);
showScrollErrors("orderKey",-1);
return false;
}
}
/*}else{

if(j>3){
var id = '402';
hideErrors();
addMsgNum(id);
showErrors();
return false;
}
}*/
}

function allowNumericValues(obj){
if(event.keyCode > 47 && event.keyCode < 58){
}else{
event.returnValue = false;
}
}

/**
*   The Function fnDifferenceComponentComparison is added for Difference comparison CR
*   LSDB_CR-06
*   Added on 15-April-08
**/

function fnDifferenceComponentComparison(){

var orderKeys="";
var sectionIds="";
var sectionName="";
var selectedModels="";
var orderNumbers="";
var selectedSpecType="";
var customerName="";
var modelseqNo="";
var doc=document.getElementsByName("orderKey");

for(var i=0;i< doc.length;i++){

if(doc[i].checked){


if(modelseqNo==""){
modelseqNo=document.forms[0].modelSeqNo[i].value;
}else{
modelseqNo=modelseqNo+","+document.forms[0].modelSeqNo[i].value;
}

}
}


//Modified for CR_131 to sanitize the code
var j=0;
$('.ordNoChkbox:checked').each(function() {
	  checkBox=true;
	  j=j+1;
	  if(orderKeys==""){
		  orderKeys=$(this).val();
	  }else{
		  orderKeys=orderKeys+","+$(this).val();
	  }
	  if(orderNumbers==""){
		  orderNumbers=$(this).closest('tr').children('td').eq(1).text().trim();
	  }else{
		  orderNumbers=orderNumbers+","+$(this).closest('tr').children('td').eq(1).text().trim();
	  }
	  if(selectedModels==""){
		  selectedModels=$(this).closest('tr').children('td').eq(2).text().trim();
	  }else{
		  selectedModels=selectedModels+","+$(this).closest('tr').children('td').eq(2).text().trim();
	  }
	  if(selectedSpecType==""){
		  selectedSpecType=$(this).closest('tr').children('td').eq(3).text().trim();
	  }else{
		  selectedSpecType=selectedSpecType+","+$(this).closest('tr').children('td').eq(3).text().trim();
	  }
	  if(customerName==""){
		  customerName=$(this).closest('tr').children('td').eq(4).text().trim().replace(/,/g, "^"); //replace function added for production issue on 27thJan15
	  }else{
		  customerName=customerName+","+$(this).closest('tr').children('td').eq(4).text().trim().replace(/,/g, "^"); //replace function added for production issue on 27thJan15
	  }
	  var select1=$(this).closest('tr').children('td').eq(6).children()[0];
	  if(sectionIds==""){
		  sectionIds=select1.options[select1.selectedIndex].value;
		  sectionName=select1.options[select1.selectedIndex].text;
	  }else{
		  sectionIds=sectionIds+","+select1.options[select1.selectedIndex].value;
	  }
});
/*Modified for CR_104 to fix the issue with checkbox selection
var obj = $('.ordNoChkbox'); //document.getElementsByTagName("input");
var j=0;
var index1=0; //Added for CR_121
for(i=0;i<obj.length;i++){
if(obj[i].type == "checkbox") {
if((obj[i].checked)){
	index1=i; //Added for CR_121
j=j+1;
if(orderKeys==""){
orderKeys=obj[i].value;
}else{
orderKeys=orderKeys+","+obj[i].value;
}
var no=obj[i].parentNode.parentNode;
if(orderNumbers==""){
orderNumbers=no.childNodes[1].innerText;
}else{
orderNumbers=orderNumbers+","+no.childNodes[1].innerText;
}
if(selectedModels==""){
selectedModels=no.childNodes[2].innerText;
}else{
selectedModels=selectedModels+","+no.childNodes[2].innerText;
}
if(selectedSpecType==""){
selectedSpecType=no.childNodes[3].innerText;
}else{
selectedSpecType=selectedSpecType+","+no.childNodes[3].innerText;
}
if(customerName==""){
customerName=no.childNodes[4].innerText.replace(/,/g, "^"); //replace function added for production issue on 27thJan15
}else{
customerName=customerName+","+no.childNodes[4].innerText.replace(/,/g, "^"); //replace function added for production issue on 27thJan15
}
var select1=no.childNodes[6].children[0];
if(sectionIds==""){
sectionIds=select1.options[select1.selectedIndex].value;
sectionName=select1.options[select1.selectedIndex].text;
}else{
sectionIds=sectionIds+","+select1.options[select1.selectedIndex].value;
}

}
}
}*/
if(j==2){
var sections=sectionIds.split(",");
var selectFlag=false;
var unSelected=false;
var id1=sections[0];
var id2=sections[1];
var models=modelseqNo.split(",");
var modId1=models[0];
var modId2=models[1];

if(modId1!=modId2){
var id='408';
hideErrors();
addMsgNum(id);
showScrollErrors("models",[0]);
return false;

}

if(id1== -1 ||id2==-1){
unSelected=true;
}
else{
selectFlag=true;
if(id1!=id2){
var id='404';
hideErrors();
addMsgNum(id);
showScrollErrors("sections",[0]);
return false;

}

}



if(selectFlag==true && unSelected==false)
{
var URL="compareComponentAction.do?method=diffComponentsComparison&orderKeys="+orderKeys+"&sectionIds="+sectionIds+"&selectedModels="+selectedModels+"&orderNumbers="+orderNumbers+"&selectedSpecType="+selectedSpecType+"&customerName="+customerName+"&sectionName="+sectionName+"&modelseqNo="+modelseqNo;
window.open(URL,'Clause',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH="+screen.width+",height=600");
}
else{
if(unSelected){
var id='406';
hideErrors();
addMsgNum(id);
showScrollErrors("orderKey",-1);
return false;
}else{
var id = '405';
hideErrors();
addMsgNum(id);
showScrollErrors("orderKey",-1);
return false;
}
}
}else{
if(j<2){
var id = '401';
hideErrors();
addMsgNum(id);
showScrollErrors("orderKeyCheckBoxId",index1);
return false;
}
if(j>2){
var id = '403';
hideErrors();
addMsgNum(id);
showScrollErrors("orderKeyCheckBoxId",index1);
return false;
}
}
}


function fnEDLComparisionReport(){

var orderKeys="";
var sectionIds="";
var sectionIdsNames="";
var selectedModels="";
var orderNumbers="";
var selectedSpecType="";
var customerName="";
var selectedCustModelNames="";
var checkBox=false;

//Added for CR-130 starts
var leadOrderKey="";
var leadOrderNum="";
var leadSelectedModels="";
var leadSelectedCustModelNames="";
var leadCustomerName="";
var leadSectionIds="";
var leadSelectedSpecType="";
var leadsectionIdsNames="";
var radio=false;
var radObj= $('.radcheck2');
var radj=0;
var radindex1=0;
clearAlerts();//Sdded for CR-131 to clear alert tooltip
for(n=0;n<radObj.length;n++){
	if(radObj[n].type == "radio"){
		if((radObj[n].checked)){
			radindex1=n;
		radio=true;
		}
		
	}
}
//Added for CR-130 ends

//Modified for CR_131 to sanitize the code
var j=0;
$('.ordNoChkbox:checked').each(function() {
	  checkBox=true;
	  j=j+1;
	  var select1 = $(this).closest('tr').children('td').eq(8).children()[0];
	  var leadOrderRadio = $(this).closest('tr').children('td').eq(1).children()[0];
	  if (leadOrderRadio.checked) {
			  radio=true;
			  leadOrderKey = $(this).val();
			  leadOrderNum = $(this).closest('tr').children('td').eq(2).text().trim();
			  leadSelectedModels= $(this).closest('tr').children('td').eq(3).text().trim();
			  leadSelectedCustModelNames= $(this).closest('tr').children('td').eq(4).text().trim();
			  leadSelectedSpecType= $(this).closest('tr').children('td').eq(5).text().trim();
			  leadCustomerName= $(this).closest('tr').children('td').eq(6).text().trim().replace(/,/g, "^");
			  leadSectionIds = select1.options[select1.selectedIndex].value;
			  leadsectionIdsNames= select1.name;
	  }else{
			  if(orderKeys==""){
				  orderKeys=$(this).val();
			  }else{
				  orderKeys=orderKeys+","+$(this).val();
			  }
			  if(orderNumbers==""){
				  orderNumbers=$(this).closest('tr').children('td').eq(2).text().trim();
			  }else{
				  orderNumbers=orderNumbers+","+$(this).closest('tr').children('td').eq(2).text().trim();
			  }
			  if(selectedModels==""){
				  selectedModels=$(this).closest('tr').children('td').eq(3).text().trim();
			  }else{
				  selectedModels=selectedModels+","+$(this).closest('tr').children('td').eq(3).text().trim();
			  }
			  if(selectedCustModelNames==""){
				  selectedCustModelNames=$(this).closest('tr').children('td').eq(4).text().trim();
			  }else{
				  selectedCustModelNames=selectedCustModelNames+","+$(this).closest('tr').children('td').eq(4).text().trim();
			  }
			  if(selectedSpecType==""){
				  selectedSpecType=$(this).closest('tr').children('td').eq(5).text().trim();
			  }else{
				  selectedSpecType=selectedSpecType+","+$(this).closest('tr').children('td').eq(5).text().trim();
			  }
			  if(customerName==""){
				  customerName=$(this).closest('tr').children('td').eq(6).text().trim().replace(/,/g, "^"); //replace function added for production issue on 27thJan15
			  }else{
				  customerName=customerName+","+$(this).closest('tr').children('td').eq(6).text().trim().replace(/,/g, "^"); //replace function added for production issue on 27thJan15
			  }
			  if(sectionIds==""){
				  sectionIds=select1.options[select1.selectedIndex].value;
				  sectionIdsNames=select1.name;
			  }else{
				  sectionIds=sectionIds+","+select1.options[select1.selectedIndex].value;
				  sectionIdsNames=sectionIdsNames+","+select1.name;
			  }
		  }
});
/*
//Modified for CR_104 to fix the issue with checkbox selection
var obj = $('.ordNoChkbox'); //document.getElementsByTagName("input");
var j=0;
var index1=0; //Added for CR_121
for(i=0;i<obj.length;i++){
if(obj[i].type == "checkbox") {
if((obj[i].checked)){
	index1=i; //Added for CR_121
checkBox=true;
j=j+1;

//Added for CR-130 starts
var no=obj[i].parentNode.parentNode;
var select1=no.childNodes[8].children[0];
if(i==radindex1){
leadOrderKey = obj[i].value;
leadOrderNum=no.childNodes[2].innerText;
leadSelectedModels=no.childNodes[3].innerText;
leadSelectedCustModelNames=no.childNodes[4].innerText;
leadSelectedSpecType=no.childNodes[5].innerText;
leadCustomerName=no.childNodes[6].innerText.replace(/,/g, "^");
leadSectionIds=select1.options[select1.selectedIndex].value;
}else{

if(orderKeys==""){
orderKeys=obj[i].value;
}else{
orderKeys=orderKeys+","+obj[i].value;
}
if(orderNumbers==""){
orderNumbers=no.childNodes[2].innerText;
}else{
orderNumbers=orderNumbers+","+no.childNodes[2].innerText;
}
if(selectedModels==""){
selectedModels=no.childNodes[3].innerText;
}else{
selectedModels=selectedModels+","+no.childNodes[3].innerText;
}
//Added For CR_104
if(selectedCustModelNames==""){
selectedCustModelNames=no.childNodes[4].innerText;
}else{
selectedCustModelNames=selectedCustModelNames+","+no.childNodes[4].innerText;
}
if(selectedSpecType==""){
selectedSpecType=no.childNodes[5].innerText;
}else{
selectedSpecType=selectedSpecType+","+no.childNodes[5].innerText;
}
if(customerName==""){
customerName=no.childNodes[6].innerText.replace(/,/g, "^"); //replace function added for production issue on 27thJan15
}else{
customerName=customerName+","+no.childNodes[6].innerText.replace(/,/g, "^"); //replace function added for production issue on 27thJan15
}
//Edited for CR-130
if(sectionIds==""){
sectionIds=select1.options[select1.selectedIndex].value;

}else{
sectionIds=sectionIds+","+select1.options[select1.selectedIndex].value;
}

}
}
}
}*/
if(orderKeys==""){ 
orderKeys=leadOrderKey ;
}else{
orderKeys=leadOrderKey +','+orderKeys;
}
if(orderNumbers==""){ 
orderNumbers=leadOrderNum ;
}else{
orderNumbers=leadOrderNum +','+orderNumbers;
}
if(selectedModels==""){ 
selectedModels=leadSelectedModels ;
}else{
selectedModels=leadSelectedModels +','+selectedModels;
}
if(selectedCustModelNames==""){
selectedCustModelNames=leadSelectedCustModelNames ;
}else{
selectedCustModelNames=leadSelectedCustModelNames +','+selectedCustModelNames;
}
if(customerName==""){ 
customerName=leadCustomerName ;
}else{
customerName=leadCustomerName +','+customerName;
}
if(sectionIds==""){ 
sectionIds=leadSectionIds ;
sectionIdsNames=leadsectionIdsNames;
}else{
sectionIds=leadSectionIds +','+sectionIds;
sectionIdsNames=leadsectionIdsNames + ',' + sectionIdsNames;
}
if(selectedSpecType==""){
selectedSpecType=leadSelectedSpecType ;
}else{
selectedSpecType=leadSelectedSpecType +','+selectedSpecType;
}

if(!checkBox){
                var id='861';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("orderKey",-1);
				return false;

}
//Added for CR-130 starts
if(!radio){
	var id='1038';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("radioBtn",-1);
	return false;
}
//Added for CR-130 ends
//Modified for CR_105 to keep restriction of 10 Orders
if(j<=10){

var sections=sectionIds.split(",");
var selectFlag=false;
var unSelected=false;

//Added for LSDB_CR-65

for(var secid =0 ; secid < sections.length; secid++){

	if(sections[secid]==-1){
		unSelected=true;
		break;
	}
}

	if(unSelected){
		var id='406';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sections",[0]);
		return false;
		}
	var secSeqNos = sectionIds.split(",");
	var secSeqNames = sectionIdsNames.split(",");
	var index = secSeqNos.length;
	
	//Added for LSDB_CR-65 by ka57588
	for(var outter =0 ; outter < index ; outter++){

		for(var inner = 0 ; inner < index; inner++){

			if((secSeqNos[outter] == -2 || secSeqNos[inner]==-2) && secSeqNos[outter]!= secSeqNos[inner]){
				var id = '860';
                hideErrors();
                addMsgNum(id);
                if (secSeqNos[outter] != -2)
                    showScrollErrors(secSeqNames[outter],-1);
                else
                    showScrollErrors(secSeqNames[inner],-1);
                return false;
			}
		}
	}
	
     
if(!unSelected)
{

var URL="compareComponentAction.do?method=fetchEdlNumbersForComparison&orderKeys="+orderKeys+"&sectionIds="+sectionIds+"&selectedModels="+escape(selectedModels)+"&orderNumbers="+orderNumbers+"&selectedSpecType="+escape(selectedSpecType)+"&customerName="+escape(customerName);
window.open(URL,'Clause',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH="+screen.width+",height=600");

//document.forms[0].action="compareComponentAction.do?method=fetchEdlNumbersForComparison&orderKeys="+orderKeys+"&sectionIds="+sectionIds+"&selectedModels="+escape(selectedModels)+"&orderNumbers="+orderNumbers+"&selectedSpecType="+escape(selectedSpecType)+"&customerName="+escape(customerName);;
//document.forms[0].submit();
	
}
else{
if(unSelected){
var id='406';
hideErrors();
addMsgNum(id);
showScrollErrors("orderKey",-1);
return false;
}else{
var id = '405';
hideErrors();
addMsgNum(id);
showScrollErrors("orderKey",-1);
return false;
}
}
}else{

if(j>10){
var id = '925';
hideErrors();
addMsgNum(id);
showScrollErrors("orderKeyCheckBoxId",-1);
return false;
}
}
}


function fnEdlComparisionForExcel(){
var orderKeys="";
var sectionIds="";
var sectionIdsNames="";
var selectedModels="";
var orderNumbers="";
var selectedSpecType="";
var customerName="";
var selectedCustModelNames="";
var checkBox=false;

//Added for CR-130 starts
var leadOrderKey="";
var leadOrderNum="";
var leadSelectedModels="";
var leadSelectedCustModelNames="";
var leadCustomerName="";
var leadSectionIds="";
var leadSelectedSpecType="";
var leadsectionIdsNames="";
var radio=false;
var radObj= $('.radcheck2');
var radj=0;

//Modified for CR_131 to sanitize the code
var j=0;
clearAlerts();//Sdded for CR-131 to clear alert tooltip
$('.ordNoChkbox:checked').each(function() {
	  checkBox=true;
	  j=j+1;
	  var select1 = $(this).closest('tr').children('td').eq(8).children()[0];
	  var leadOrderRadio = $(this).closest('tr').children('td').eq(1).children()[0];
	  if (leadOrderRadio.checked) {
			  radio=true;
			  leadOrderKey = $(this).val();
			  leadOrderNum = $(this).closest('tr').children('td').eq(2).text().trim();
			  leadSelectedModels= $(this).closest('tr').children('td').eq(3).text().trim();
			  leadSelectedCustModelNames= $(this).closest('tr').children('td').eq(4).text().trim();
			  leadSelectedSpecType= $(this).closest('tr').children('td').eq(5).text().trim();
			  leadCustomerName= $(this).closest('tr').children('td').eq(6).text().trim().replace(/,/g, "^");
			  leadSectionIds = select1.options[select1.selectedIndex].value;
			  leadsectionIdsNames= select1.name;
	  }else{
			  if(orderKeys==""){
				  orderKeys=$(this).val();
			  }else{
				  orderKeys=orderKeys+","+$(this).val();
			  }
			  if(orderNumbers==""){
				  orderNumbers=$(this).closest('tr').children('td').eq(2).text().trim();
			  }else{
				  orderNumbers=orderNumbers+","+$(this).closest('tr').children('td').eq(2).text().trim();
			  }
			  if(selectedModels==""){
				  selectedModels=$(this).closest('tr').children('td').eq(3).text().trim();
			  }else{
				  selectedModels=selectedModels+","+$(this).closest('tr').children('td').eq(3).text().trim();
			  }
			  if(selectedCustModelNames==""){
				  selectedCustModelNames=$(this).closest('tr').children('td').eq(4).text().trim();
			  }else{
				  selectedCustModelNames=selectedCustModelNames+","+$(this).closest('tr').children('td').eq(4).text().trim();
			  }
			  if(selectedSpecType==""){
				  selectedSpecType=$(this).closest('tr').children('td').eq(5).text().trim();
			  }else{
				  selectedSpecType=selectedSpecType+","+$(this).closest('tr').children('td').eq(5).text().trim();
			  }
			  if(customerName==""){
				  customerName=$(this).closest('tr').children('td').eq(6).text().trim().replace(/,/g, "^"); //replace function added for production issue on 27thJan15
			  }else{
				  customerName=customerName+","+$(this).closest('tr').children('td').eq(6).text().trim().replace(/,/g, "^"); //replace function added for production issue on 27thJan15
			  }
			  if(sectionIds==""){
				  sectionIds=select1.options[select1.selectedIndex].value;
				  sectionIdsNames=select1.name;
			  }else{
				  sectionIds=sectionIds+","+select1.options[select1.selectedIndex].value;
				  sectionIdsNames=sectionIdsNames+","+select1.name;
			  }
		  }
});
/*var radindex1=0;
for(n=0;n<radObj.length;n++){
	if(radObj[n].type == "radio"){
		if((radObj[n].checked)){
			radindex1=n;
		radio=true;
		}
		
	}
}
//Added for CR-130 ends
//Modified for CR_104 to fix the issue with checkbox selection
var obj = $('.ordNoChkbox'); //document.getElementsByTagName("input");
var j=0;
var index1=0; //Added for CR_121
for(i=0;i<obj.length;i++){
if(obj[i].type == "checkbox") {
if((obj[i].checked)){
	index1=i; //Added for CR_121
checkBox=true;
j=j+1;
//Added for CR-130 starts
var no=obj[i].parentNode.parentNode;
var select1=no.childNodes[8].children[0];
if(i==radindex1){
leadOrderKey = obj[i].value;
leadOrderNum=no.childNodes[2].innerText;
leadSelectedModels=no.childNodes[3].innerText;
leadSelectedCustModelNames=no.childNodes[4].innerText;
leadSelectedSpecType=no.childNodes[5].innerText;
leadCustomerName=no.childNodes[6].innerText.replace(/,/g, "^");
leadSectionIds=select1.options[select1.selectedIndex].value;
}else{
if(orderKeys==""){ //edited for CR-130
orderKeys=obj[i].value;
}else{
orderKeys=orderKeys+","+obj[i].value;
}
if(orderNumbers==""){
orderNumbers=no.childNodes[2].innerText;
}else{
orderNumbers=orderNumbers+","+no.childNodes[2].innerText;
}
if(selectedModels==""){
selectedModels=no.childNodes[3].innerText;
}else{
selectedModels=selectedModels+","+no.childNodes[3].innerText;
}
//Added For CR_104
if(selectedCustModelNames==""){
selectedCustModelNames=no.childNodes[4].innerText;
}else{
selectedCustModelNames=selectedCustModelNames+","+no.childNodes[4].innerText;
}
if(selectedSpecType==""){
selectedSpecType=no.childNodes[5].innerText;
}else{
selectedSpecType=selectedSpecType+","+no.childNodes[5].innerText;
}
if(customerName==""){
customerName=no.childNodes[6].innerText.replace(/,/g, "^"); //replace function added for production issue on 27thJan15
}else{
customerName=customerName+","+no.childNodes[6].innerText.replace(/,/g, "^"); //replace function added for production issue on 27thJan15
}
if(sectionIds==""){
sectionIds=select1.options[select1.selectedIndex].value;

}else{
sectionIds=sectionIds+","+select1.options[select1.selectedIndex].value;
}

}
}
}

}*/
if(orderKeys==""){ 
orderKeys=leadOrderKey ;
}else{
orderKeys=leadOrderKey +','+orderKeys;
}
if(orderNumbers==""){ 
orderNumbers=leadOrderNum ;
}else{
orderNumbers=leadOrderNum +','+orderNumbers;
}
if(selectedModels==""){ 
selectedModels=leadSelectedModels ;
}else{
selectedModels=leadSelectedModels +','+selectedModels;
}
if(selectedCustModelNames==""){
selectedCustModelNames=leadSelectedCustModelNames ;
}else{
selectedCustModelNames=leadSelectedCustModelNames +','+selectedCustModelNames;
}
if(customerName==""){ 
customerName=leadCustomerName ;
}else{
customerName=leadCustomerName +','+customerName;
}
if(sectionIds==""){ 
sectionIds=leadSectionIds ;
sectionIdsNames=leadsectionIdsNames;
}else{
sectionIds=leadSectionIds +','+sectionIds;
sectionIdsNames=leadsectionIdsNames+','+sectionIdsNames;
}
if(selectedSpecType==""){
selectedSpecType=leadSelectedSpecType ;
}else{
selectedSpecType=leadSelectedSpecType +','+selectedSpecType;
}

if(!checkBox){
	var id='861';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("orderKey",-1);
	return false;
}
//Added for CR-130 starts
if(!radio){
	var id='1038';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("radioBtn",-1);
	return false;
}
//Added for CR-130 ends

var sections=sectionIds.split(",");
var selectFlag=false;
var unSelected=false;

for(var secid =0 ; secid < sections.length; secid++){

	if(sections[secid]==-1){
		unSelected=true;
		break;
	}
}

if(unSelected){
	var id='406';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sections",[0]);
	return false;
}

var secSeqNos = sectionIds.split(",");
var secSeqNames = sectionIdsNames.split(",");
var index = secSeqNos.length;
for(var outter =0 ; outter < index ; outter++){

	for(var inner = 0 ; inner < index; inner++){

		if((secSeqNos[outter] == -2 || secSeqNos[inner]==-2) && secSeqNos[outter]!= secSeqNos[inner]){
			var id = '860';
			hideErrors();
			addMsgNum(id);
			if (secSeqNos[outter] != -2)
			    showScrollErrors(secSeqNames[outter],-1);
			else
			    showScrollErrors(secSeqNames[inner],-1);
			return false;
		}
	}
}
      
if(!unSelected)
{
	//added for CR-130
	document.forms[0].action= "compareComponentAction.do?method=fetchEdlNumberForExcelComparison&orderKeys="+orderKeys+"&sectionIds="+sectionIds+"&selectedModels="+escape(selectedModels)+"&orderNumbers="+orderNumbers+"&selectedSpecType="+escape(selectedSpecType)+"&customerName="+escape(customerName);
	document.forms[0].submit();	
/*var URL="compareComponentAction.do?method=fetchEdlNumberForExcelComparison&orderKeys="+orderKeys+"&sectionIds="+sectionIds+"&selectedModels="+escape(selectedModels)+"&orderNumbers="+orderNumbers+"&selectedSpecType="+escape(selectedSpecType)+"&customerName="+escape(customerName);
window.open(URL,'Clause',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH="+screen.width+",height=600");*/
}
else{
if(unSelected){
var id='406';
hideErrors();
addMsgNum(id);
showScrollErrors("orderKey",-1);
return false;
}else{
var id = '405';
hideErrors();
addMsgNum(id);
showScrollErrors("orderKey",-1);
return false;
}
}

}
function fnLoadModelsForComponentComparison()
{
var clauseCompareForm = document.forms[0];
document.forms[0].hdnSelSpecType.value=ComponentCompareForm.spectypeSeqno.options[ComponentCompareForm.spectypeSeqno.selectedIndex].text;
document.forms[0].action="compareComponentAction.do?method=fetchModels";
document.forms[0].submit();
}

// Added for CR_106 Spec Comparison Highlight JG101007

$(document).ready(function(){ 
			/*$('.ordNoChkbox').click( function(){
				$(this).parent().parent().toggleClass('highlight');
				})*/
				//Added for CR-130 starts here
				var lastRow = null;
			$('.ordNoChkbox').click( function(){
				if($(this).is(":checked")){
				$(this).parent().parent().toggleClass('bg-info');
				}
				if($(this).is(":not(:checked)")){
				$(this).parent().parent().find('input:radio').prop('checked', false);
				$(this).parent().parent().removeClass('highlightBaseOrdr');
				$(this).parent().parent().removeClass('bg-info');
				}
				})
				
				$(":radio").click(function(){
				
					if (lastRow != null){ 
					   lastRow.removeClass('highlightBaseOrdr'); 
					  if(lastRow.find('input:checkbox').is(":checked")){
					  	lastRow.addClass('bg-info');
					  }else{
					  	lastRow.removeClass('bg-info');
					  }
					}
					lastRow = $(this).parent().parent();
					lastRow.find('input:checkbox').prop('checked', true);
					lastRow.addClass('highlightBaseOrdr');
					
					
				});
				
				
				
				
				//Added for CR-130 ends here
		});

//Added for CR-121
function fnViewandExportCmpInOrdMap(action){
	
	var i;
	var URL = "";
	var formObject = document.forms[0];
    var check = false;
    var seqNos="";
	clearAlerts();//Sdded for CR-131 to clear alert tooltip
    if (formObject.orderKey.length > 1) {

        for (i = 0; i < formObject.orderKey.length; i++) {

            if(formObject.orderKey[i].checked){
						check = true;
			}
        }

    } else {
        if (formObject.orderKey.checked) {
            check = true;

        }
    }	
	
	if(!check){
           var id='861';
           hideErrors();
		   addMsgNum(id);
		   showScrollErrors("orderKey",-1);
		   return false;
	}
	
	if(action == "report"){ 
			document.forms[0].action =  "CompGroupAction.do?method=viewCmpInOrdMap";
	        document.forms[0].target = "_blank";
	        document.forms[0].submit();
	        document.forms[0].target = "_self";	
	}
	else{	
			document.forms[0].action = "CompGroupAction.do?method=exportCompinOrdMap";
		    document.forms[0].submit();
	}        
}

// Check all/ clear all.

$(function() {
    $("#chk").click(function() {

        $('.ordNoChkbox').prop("checked", true);
        $('.ordNoChkbox').closest('tr').addClass("selected");
    });
});

$(function() {
    $("#unchk").click(function() {

        $('.ordNoChkbox').prop("checked", false);
        $('.ordNoChkbox').closest('tr').removeClass("selected");
    });
});


//Added for CR-135 starts
function fnOrderVsModelDelta(){
	var orderKeys="";
	var selectedSection="";
	var sectionIds="";
	var selectedModels="";
	var orderNumbers="";
	var selectedSpecType="";
	var customerName="";
	var selectedCustModelNames="";
	var dataLocType="S";
	
	var j=0;
	var index1=-1;
	clearAlerts(); //Added for CR-131 to clear alert tooltip
	
	
	$('.ordNoChkbox:checked').each(function() {
	  j=j+1;
	  index1=$('.ordNoChkbox').index($('.ordNoChkbox:checked'));
		  
		orderKeys=$(this).val();
	  	orderNumbers=$(this).closest('tr').children('td').eq(1).text().trim();
	    selectedModels=$(this).closest('tr').children('td').eq(2).text().trim();   	    
	    selectedCustModelNames=$(this).closest('tr').children('td').eq(3).text().trim();
	    selectedSpecType=$(this).closest('tr').children('td').eq(4).text().trim();
	    customerName=$(this).closest('tr').children('td').eq(5).text().trim().replace(/,/g, "^"); //replace function added for production issue on 27thJan15
	    var select1=$(this).closest('tr').children('td').eq(7).children()[0];
	    sectionIds=select1.options[select1.selectedIndex].value;
	    selectedSection=select1.options[select1.selectedIndex].text;//CR_131
		  
	});
	if(j>1){
		var id = '1042';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("orderKeyCheckBoxId",index1);
		return false;
	}else if(j == 0){
		var id = '861';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("orderKeyCheckBoxId",index1);
		return false;
	}else{
		
		var URL="compareSpecAction.do?method=fetchOrderVsModelDeltaDetails&orderKeys="+orderKeys+"&sectionIds="+sectionIds+"&selectedModels="+escape(selectedModels)+"&orderNumbers="+orderNumbers+"&selectedSpecType="+escape(selectedSpecType)+"&customerName="+escape(customerName)+"&dataLocType="+dataLocType;
		window.open(URL,'Clause',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH="+screen.width+",height=600");
	}
}

//Added for CR-135 ends
