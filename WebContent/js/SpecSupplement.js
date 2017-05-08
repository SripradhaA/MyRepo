//Added for CR-66
function keyHandler(e)
{ 
      var asciiValue = e ? e.which : window.event.keyCode;
	  if(asciiValue ==13){         
       		if(document.forms[0].flag.value == "Y"){ 
       			fnSearchOrders();
        		return false;
        	}
        }
       
}
  document.onkeypress = keyHandler;



function fnSearchOrders()
{
	index=-1;
var SpecSupplementForm= document.forms[0];

/**
**	Mandatory Fields Validations for Specification and Specstatus is Comment out based on requirement of LSDB_CR-19
**  Changes Done on 21-April-08
**  By ps57222
*/

/*if(SpecSupplementForm.spectypeSeqno.options[SpecSupplementForm.spectypeSeqno.selectedIndex].value=="-1"){
var id = '2';
hideErrors();
addMsgNum(id);
showErrors();
return false;
}
if(SpecSupplementForm.custSeqNo.options[SpecSupplementForm.custSeqNo.selectedIndex].value=="-1"){
var id = '301';
hideErrors();
addMsgNum(id);
showErrors();
return false;
}*/

if(trim(SpecSupplementForm.orderNo.value)=="" || trim(SpecSupplementForm.orderNo.value).length==0)
{
var id = '105';
hideErrors();
addMsgNum(id);
showScrollErrors("orderNo",index);
return false;

}
// Modified for CR_104 to allow Wild Card Search of Orders JG101007
if(WildCardSearchCheck(trim(SpecSupplementForm.orderNo.value))==true){
var id = '105';
hideErrors();
addMsgNum(id);
//SpecSupplementForm.orderNo.value="";
SpecSupplementForm.orderNo.focus();
showScrollErrors("orderNo",index);
return false;
}

//CR 90 for Order Number -removing number format validation	 --Start
//if(isNaN(trim(SpecSupplementForm.orderNo.value))){
//var id = '105';
//hideErrors();
//addMsgNum(id);
////SpecSupplementForm.orderNo.value="";
//SpecSupplementForm.orderNo.focus();
//showErrors();
//return false;
//}

//if(!isNumeric(trim(SpecSupplementForm.orderNo.value)))
//{
//var id = '105';
//hideErrors();
//addMsgNum(id);
//showErrors();
//SpecSupplementForm.orderNo.focus();
//return false;
//
//}
//CR 90 for Order Number -removing number format validation	 --End

document.forms[0].flag.value="N";
SpecSupplementForm.specType.value=SpecSupplementForm.spectypeSeqno.options[SpecSupplementForm.spectypeSeqno.selectedIndex].text;
SpecSupplementForm.custName.value=SpecSupplementForm.custSeqNo.options[SpecSupplementForm.custSeqNo.selectedIndex].text;
document.forms[0].action="SpecSupplement.do?method=fetchOrders";
document.forms[0].submit();
}


function fnLoadCustomers(){
	
document.forms[0].action="SpecSupplement.do?method=fetchCustomers";
document.forms[0].submit();

}