//Added for CR-66
function keyHandler(e)
{ 
   	 var asciiValue = e ? e.which : window.event.keyCode;
	 if(asciiValue ==13){
	 	//Fix for flag unknown jscript errors on Modal Popups
	 	if(document.forms[0].flag!=undefined){         
		    if(document.forms[0].flag.value == "Y"){
		      	fnSearchOrders();
	    	  	return false;
	    	  }
	 		}
        }
       
}
  document.onkeypress = keyHandler;  




function fnLoadModels()
{

var modifySpecForm = document.forms[0];
//document.forms[0].hdnSelSpecType.value=modifySpecForm.spectypeSeqno.options[modifySpecForm.spectypeSeqno.selectedIndex].text;


document.forms[0].action="ModifySpecAction.do?method=fetchModels";
document.forms[0].submit();
}

function fnSearchOrders()
{
var mod = document.forms['ModifySpecForm'];
var modifySpecForm = document.forms[0];

/**
**	Mandatory Fields Validations for Specification and Specstatus is Comment out based on requirement of LSDB_CR-19
**  Changes Done on 21-April-08
**  By ps57222
*/

/*if(modifySpecForm.spectypeSeqno.options[modifySpecForm.spectypeSeqno.selectedIndex].value=="-1"){
var id = '2';
hideErrors();
addMsgNum(id);
showErrors();
return false;
}

if(modifySpecForm.modelSeqNo.options[modifySpecForm.modelSeqNo.selectedIndex].value=="-1"){
var id = '19';
hideErrors();
addMsgNum(id);
showErrors();
return false;
}
*/
if(trim(modifySpecForm.orderNo.value)=="" || trim(modifySpecForm.orderNo.value).length==0)
{
var id = '105';
hideErrors();
addMsgNum(id);
showScrollErrors("orderNo",-1);
return false;

}
//Added for CR_104 to allow Wild Card Searches JG101007
if(WildCardSearchCheck(trim(modifySpecForm.orderNo.value))==true){
var id = '105';
hideErrors();
addMsgNum(id);
showScrollErrors("orderNo",-1);
return false;
}


//alert($( "#sltSpecType option:selected" ).text());
//** * CR 90 -Numeric Validation Removal for modify spec search button -Start*/
//if(isNaN(trim(modifySpecForm.orderNo.value))){
//var id = '105';
//hideErrors();
//addMsgNum(id);
//showErrors();
////modifySpecForm.orderNo.value="";
//modifySpecForm.orderNo.focus();
//return false;
//}



//if(!isNumeric(trim(modifySpecForm.orderNo.value)))
//{
//var id = '105';
//hideErrors();
//addMsgNum(id);
//showErrors();
//modifySpecForm.orderNo.focus();
//return false;

//}
//** * CR 90 -Numeric Validation Removal for modify spec search button -End */

document.forms[0].sortByFlag.value=10; //Order Number in desc Order
document.forms[0].columnheader.value="ORDERNUM";
document.forms[0].flag.value="N";
document.forms[0].hdnSelSpecType.value=modifySpecForm.spectypeSeqno.options[modifySpecForm.spectypeSeqno.selectedIndex].text;
document.forms[0].hdnSelModel.value=modifySpecForm.modelSeqNo.options[modifySpecForm.modelSeqNo.selectedIndex].text;
/*Added for Customer drop down as per  CR 75 by cm68219*/
document.forms[0].hdnSelectedCustomers.value=modifySpecForm.custSeqNo.options[modifySpecForm.custSeqNo.selectedIndex].text;
modifySpecForm.hdnorderNo.value=modifySpecForm.orderNo.value;

document.forms[0].action="ModifySpecAction.do?method=fetchOrders";
document.forms[0].submit();
}

function fnModifyOrders()
{
var modifySpecForm = document.forms[0];
var index;
orderKeyChecked = false;
if(modifySpecForm.orderKey.length>0)
{
var cnt = modifySpecForm.orderKey.length;
for(var i=0;i<cnt;i++)
{
if(modifySpecForm.orderKey[i].checked)
{
orderKeyChecked = true;
index = i;
break;
}
}
}else{
if(modifySpecForm.orderKey.checked)
{
orderKeyChecked = true;
}
}

if(!orderKeyChecked)
{
var id= '113';
hideErrors();
addMsgNum(id);
showScrollErrors("orderKey",-1);
return false;
}else{

if(modifySpecForm.orderKey.length>0){

if(trim(modifySpecForm.quantity[index].value).length==0 || trim(modifySpecForm.quantity[index].value)=="" ){
var id = '305';
hideErrors();
addMsgNum(id);
showScrollErrors("quantity",index);
return false;
}

if(isNaN(trim(modifySpecForm.quantity[index].value))){
var id = '305';
hideErrors();
addMsgNum(id);
showScrollErrors("quantity",index);
return false;
}
if(!isNumeric(trim(modifySpecForm.quantity[index].value)))
{
var id = '305';
hideErrors();
addMsgNum(id);
showScrollErrors("quantity",index);
return false;

}

if(trim(modifySpecForm.quantity[index].value)<=0){
var id = '305';
hideErrors();
addMsgNum(id);
showScrollErrors("quantity",index);
return false;
}

if(trim(modifySpecForm.sapCusCode[index].value).length==0 || trim(modifySpecForm.sapCusCode[index].value)=="" ){
var id = '104';
hideErrors();
addMsgNum(id);
showScrollErrors("sapCusCode",index);
return false;
}

var isSpecChar = SpecialCharacterCheck(modifySpecForm.sapCusCode[index].value);

if(isSpecChar){

var id = '303';
hideErrors();
addMsgNum(id);
showScrollErrors("sapCusCode",index);

return false;

}

//Added for CR_104
if(trim(modifySpecForm.custMdlName[index].value).length==null || trim(modifySpecForm.custMdlName[index].value)=="" ){
var id = '923';
hideErrors();
addMsgNum(id);
showScrollErrors("custMdlName",index);
return false;
}


}else{
	
index= -1;
if(trim(modifySpecForm.quantity.value).length==0 || trim(modifySpecForm.quantity.value)=="" ){
var id = '305';
hideErrors();
addMsgNum(id);
showScrollErrors("quantity",index);
return false;
}

if(isNaN(trim(modifySpecForm.quantity.value))){
var id = '305';
hideErrors();
addMsgNum(id);
showScrollErrors("quantity",index);
return false;
}

if(!isNumeric(trim(modifySpecForm.quantity.value)))
{
var id = '305';
hideErrors();
addMsgNum(id);
showScrollErrors("quantity",index);
return false;

}
if(trim(modifySpecForm.quantity.value)<=0){
var id = '305';
hideErrors();
addMsgNum(id);
showScrollErrors("quantity",index);

return false;
}
if(trim(modifySpecForm.sapCusCode.value).length==0 || trim(modifySpecForm.sapCusCode.value)=="" ){
var id = '104';
hideErrors();
addMsgNum(id);
showScrollErrors("sapCusCode",index);
return false;
}

var isSpecChar = SpecialCharacterCheck(modifySpecForm.sapCusCode.value);

if(isSpecChar){

var id = '303';
hideErrors();
addMsgNum(id);
showScrollErrors("sapCusCode",index);
return false;

}

//Added for CR_104
if(trim(modifySpecForm.custMdlName.value).length==0 || trim(modifySpecForm.custMdlName.value)=="" ){
var id = '923';
hideErrors();
addMsgNum(id);
showScrollErrors("custMdlName",index);
return false;
}


}
}

if(modifySpecForm.orderKey.length>0)
{
var cnt = modifySpecForm.orderKey.length;
for(var i=0;i<cnt;i++)
{
if(modifySpecForm.orderKey[i].checked)
{
modifySpecForm.hdnQty.value=trim(modifySpecForm.quantity[i].value);
modifySpecForm.hdnSapCustCode.value=trim(modifySpecForm.sapCusCode[i].value);
modifySpecForm.hdnCustMdlName.value=trim(modifySpecForm.custMdlName[i].value);

}
}
}else{
if(modifySpecForm.orderKey.checked)
{
modifySpecForm.hdnQty.value=trim(modifySpecForm.quantity.value);
modifySpecForm.hdnSapCustCode.value=trim(modifySpecForm.sapCusCode.value);
modifySpecForm.hdnCustMdlName.value=trim(modifySpecForm.custMdlName.value);
}
}

if(document.forms[0].hdnSelSpecType.value != modifySpecForm.spectypeSeqno.options[modifySpecForm.spectypeSeqno.selectedIndex].text)
{
var id = '207';
hideErrors();
addMsgNum(id);
showScrollErrors("spectypeSeqno",-1);
return false;
}

if(document.forms[0].hdnSelModel.value != modifySpecForm.modelSeqNo.options[modifySpecForm.modelSeqNo.selectedIndex].text)
{
var id = '207';
hideErrors();
addMsgNum(id);
showScrollErrors("modelSeqNo",-1);
return false;
}
/*Added for customer drop down by cm68219*/
if(document.forms[0].hdnSelectedCustomers.value != modifySpecForm.custSeqNo.options[modifySpecForm.custSeqNo.selectedIndex].text)
{
	alert(document.forms[0].hdnSelectedCustomers.value);
	alert(modifySpecForm.custSeqNo.options[modifySpecForm.custSeqNo.selectedIndex].text);
var id = '207';
hideErrors();
addMsgNum(id);
showScrollErrors("custSeqNo",-1);
return false;
}

if(trim(modifySpecForm.orderNo.value)=="" || trim(modifySpecForm.orderNo.value).length==0)
{
var id = '105';
hideErrors();
addMsgNum(id);
showErrors();
return false;

}

if(trim(modifySpecForm.hdnorderNo.value) != trim(modifySpecForm.orderNo.value))
{
var id = '207';
hideErrors();
addMsgNum(id);
showScrollErrors("orderNo",-1);
return false;
}
document.forms[0].action="ModifySpecAction.do?method=updateOrders";
document.forms[0].submit();
}


function filterChangeValidate(modifySpecForm){

	if(document.forms[0].hdnSelSpecType.value != modifySpecForm.spectypeSeqno.options[modifySpecForm.spectypeSeqno.selectedIndex].text)
		{
			var id = '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("spectypeSeqno",-1);
			return false;
		}

		if(document.forms[0].hdnSelModel.value != modifySpecForm.modelSeqNo.options[modifySpecForm.modelSeqNo.selectedIndex].text)
		{
			var id = '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("modelSeqNo",-1);
			return false;
		}
		
		if(document.forms[0].hdnSelectedCustomers.value != modifySpecForm.custSeqNo.options[modifySpecForm.custSeqNo.selectedIndex].text)
		{
			var id = '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("custSeqNo",-1);
			return false;
		}

		if(trim(modifySpecForm.orderNo.value)=="" || trim(modifySpecForm.orderNo.value).length==0)
		{
			var id = '105';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("orderNo");
			return false;

		}

		if(trim(modifySpecForm.hdnorderNo.value) != trim(modifySpecForm.orderNo.value))
		{
			var id = '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("orderNo");
			return false;
		}
		return true;

}


//Added for LSDB_CR-76 for sorting

/*function fnSortCustType(){
	
	var sortKey=document.forms[0].sortByFlag.value;
	modifySpecForm = document.forms[0];
	var check = filterChangeValidate(modifySpecForm);
 	
	if(check){
		if(sortKey == 2){
			
			document.forms[0].sortByFlag.value=1;
			
		}else if(sortKey == 1){
			
			document.forms[0].sortByFlag.value=2;
			
		}else{
		
			document.forms[0].sortByFlag.value=1;
		}
	   
		document.forms[0].columnheader.value="CUSTYPE";
		document.forms[0].action="ModifySpecAction.do?method=fetchOrders";
		document.forms[0].submit();
	}
    
}

function fnSortCust(){
	
	var sortKey=document.forms[0].sortByFlag.value;
	modifySpecForm = document.forms[0];
	var check = filterChangeValidate(modifySpecForm);
 	
	if(check){
		if(sortKey == 4){
			
			document.forms[0].sortByFlag.value=3;
			
		}else if(sortKey == 3){
			
			document.forms[0].sortByFlag.value=4;
			
		}else{
		
			document.forms[0].sortByFlag.value=3;
		}
	   
		document.forms[0].columnheader.value="CUST";
		document.forms[0].action="ModifySpecAction.do?method=fetchOrders";
		document.forms[0].submit();
	}
    
}

function fnSortModel(){
	
	var sortKey=document.forms[0].sortByFlag.value;
	modifySpecForm = document.forms[0];
	var check = filterChangeValidate(modifySpecForm);
 	
	if(check){
		if(sortKey == 6){
			
			document.forms[0].sortByFlag.value=5;
			
		}else if(sortKey == 5){
			
			document.forms[0].sortByFlag.value=6;
			
		}else{
			
			document.forms[0].sortByFlag.value=5;
		}
	   
		document.forms[0].columnheader.value="MODEL";
		document.forms[0].action="ModifySpecAction.do?method=fetchOrders";
		document.forms[0].submit();
	}
    
}


function fnSortSpecStatus(){
	
	var sortKey=document.forms[0].sortByFlag.value;
	modifySpecForm = document.forms[0];
	var check = filterChangeValidate(modifySpecForm);
 	
	if(check){
		if(sortKey == 8){
			
			document.forms[0].sortByFlag.value=7;
			
		}else if(sortKey == 7){
			
			document.forms[0].sortByFlag.value=8;
			
		}else{
		
			document.forms[0].sortByFlag.value=7;
		}
	    document.forms[0].columnheader.value="SPECSTATUS";
		document.forms[0].action="ModifySpecAction.do?method=fetchOrders";
		document.forms[0].submit();
	}
    
}


function fnSortOrderNumber(){
	
	var sortKey=document.forms[0].sortByFlag.value;
	modifySpecForm = document.forms[0];
	var check = filterChangeValidate(modifySpecForm);
 	if(check){
		if(sortKey == 9){
			
			document.forms[0].sortByFlag.value=10;
			
		}else if(sortKey == 10){
			
			document.forms[0].sortByFlag.value=9;
			
		}else{
				  document.forms[0].sortByFlag.value=9;
		 }
	   
		document.forms[0].columnheader.value="ORDERNUM";
		document.forms[0].action="ModifySpecAction.do?method=fetchOrders";
		document.forms[0].submit();
    }
}


function fnSortQuantity(){
	
	var sortKey=document.forms[0].sortByFlag.value;
	modifySpecForm = document.forms[0];
	var check = filterChangeValidate(modifySpecForm);

 	if(check){
		if(sortKey == 12){
			
			document.forms[0].sortByFlag.value=11;
			
		}else if(sortKey == 11){
			
			document.forms[0].sortByFlag.value=12;
			
		}else{
			
			document.forms[0].sortByFlag.value=11;
		}
		document.forms[0].columnheader.value="QUANTY";
		document.forms[0].action="ModifySpecAction.do?method=fetchOrders";
		document.forms[0].submit();
	}
    
}


function fnSortSAPCusCode(){
	
	var sortKey=document.forms[0].sortByFlag.value;
	modifySpecForm = document.forms[0];
	var check = filterChangeValidate(modifySpecForm);
 	
	if(check){
		if(sortKey == 14){
			
			document.forms[0].sortByFlag.value=13;
			
		}else if(sortKey == 13){
			
			document.forms[0].sortByFlag.value=14;
			
		}else{
			document.forms[0].sortByFlag.value=13;
		}
		document.forms[0].columnheader.value="SAPCODE";
		document.forms[0].action="ModifySpecAction.do?method=fetchOrders";
		document.forms[0].submit();
	}    
}
//Added FOR CR_104
function fnSortCustModelName(){
	
	var sortKey=document.forms[0].sortByFlag.value;
	modifySpecForm = document.forms[0];
	var check = filterChangeValidate(modifySpecForm);
 	
	if(check){
		if(sortKey == 16){
			
			document.forms[0].sortByFlag.value=15;
			
		}else if(sortKey == 15){
			
			document.forms[0].sortByFlag.value=16;
			
		}else{
			document.forms[0].sortByFlag.value=15;
		}
		document.forms[0].columnheader.value="CUSTMODELNAME";
		document.forms[0].action="ModifySpecAction.do?method=fetchOrders";
		document.forms[0].submit();
	}    
}*/

//Ends
