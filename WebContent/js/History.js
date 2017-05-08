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
{     var HistoryForm=document.forms[0];

/**
**	Mandatory Fields Validations for Specification and Specstatus is Comment out based on requirement of LSDB_CR-19
**  Changes Done on 21-April-08
**  By ps57222
*/

/*if(HistoryForm.specTypeSeqno.options[HistoryForm.specTypeSeqno.selectedIndex].value=="-1"){
var id = '2';
hideErrors();
addMsgNum(id);
showErrors();
return false;
}
if(HistoryForm.specStatusSeqNo.options[HistoryForm.specStatusSeqNo.selectedIndex].value=="-1"){
var id = '224';
hideErrors();
addMsgNum(id);
showErrors();
return false;
}*/

if(trim(HistoryForm.orderNum.value)=="" || trim(HistoryForm.orderNum.value).length==0)
{
var id = '105';
hideErrors();
addMsgNum(id);
showScrollErrors("orderNum",-1);
return false;

}
// Modified for CR_104 to allow Wild Card Search JG101007
var isInvalWildCard = WildCardSearchCheck(trim(document.forms[0].orderNum.value));
if(isInvalWildCard)
{
	 
var id = '105';
hideErrors();
addMsgNum(id);
showScrollErrors("orderNum",-1);
return false;

}

var len  = document.all["customerSeqnos"].options.length;
var custnames="";
for(i=0;i<len;i++)
{
if(document.all["customerSeqnos"].options[i].selected==true)
{
if(custnames=="")
{
custnames=document.all["customerSeqnos"].options[i].text;
}
else
{
custnames=custnames+", "+document.all["customerSeqnos"].options[i].text;
}

}
}
//Added for CR_112 for Model Multi select list Box
var len  = document.all["modelSeqnos"].options.length;
var modelnames="";
for(i=0;i<len;i++)
{
if(document.all["modelSeqnos"].options[i].selected==true)
{
if(modelnames=="")
{
modelnames=document.all["modelSeqnos"].options[i].text;
}
else
{
modelnames=modelnames+", "+document.all["modelSeqnos"].options[i].text;
}

}
}
//CR_112 Ends here
//CR 90 for Order Number -removing number format validation	 --Start
//if(isNaN(trim(document.forms[0].orderNum.value))){
//var id = '105';
//hideErrors();
//addMsgNum(id);
//showErrors();
//document.forms[0].orderNum.focus();
//return false;
//}


//if(!isNumeric(trim(HistoryForm.orderNum.value)))
//{
//var id = '105';
//hideErrors();
//addMsgNum(id);
//showErrors();
//
//HistoryForm.orderNum.focus();
//return false;
//
//}
//CR 90 for Order Number -removing number format validation	 --End
//CR_112 Model multi select lit box
document.forms[0].hdnSelectedModelNames.value=modelnames;
//CR_112 Ends here
document.forms[0].hdnSelectedCustNames.value=custnames;
document.forms[0].sortByFlag.value=2; //published date desc order  
document.forms[0].columnheader.value="PUBLISHDATE";
document.forms[0].flag.value = "N";
document.forms[0].hdnSpecTypeNme.value = document.forms[0].specTypeSeqno.options[document.forms[0].specTypeSeqno.selectedIndex].text;
document.forms[0].hdnSpecStatus.value  = document.forms[0].specStatusSeqNo.options[document.forms[0].specStatusSeqNo.selectedIndex].text;
document.forms[0].action="HistoryAction.do?method=fetchOrders";
document.forms[0].submit();
}

//Added for LSDB_CR-76

function fnSortDate(){
	//Modified validation for CR_104
	if (fnCheckFilterCriteria())	{
   var sortKey=document.forms[0].sortByFlag.value;
    
    if(sortKey == 1){
    
		document.forms[0].sortByFlag.value=2;
    
    }else if(sortKey == 2){
    
		document.forms[0].sortByFlag.value=1;
    
	}else{
          document.forms[0].sortByFlag.value=1;
     }
   
    document.forms[0].columnheader.value="PUBLISHDATE";
    document.forms[0].action="HistoryAction.do?method=fetchOrders";
	document.forms[0].submit();
	}
}

function fnSortCust(){
	//Modified validation for CR_104
	if (fnCheckFilterCriteria())	{
	var sortKey=document.forms[0].sortByFlag.value;
	
    if(sortKey == 4){
    
		  document.forms[0].sortByFlag.value=3;
    
    }else if(sortKey == 3){
    
		 document.forms[0].sortByFlag.value=4;
    
    }else{
		
		document.forms[0].sortByFlag.value=3;
	}
		document.forms[0].columnheader.value="CUST";	
		document.forms[0].action="HistoryAction.do?method=fetchOrders";
		document.forms[0].submit();
	}
}

function fnSortModel(){
	//Modified validation for CR_104
	if (fnCheckFilterCriteria())	{
    var sortKey=document.forms[0].sortByFlag.value;

    if(sortKey == 6){
    
		document.forms[0].sortByFlag.value=5;
    
    }else if(sortKey == 5){
    
		document.forms[0].sortByFlag.value=6;
    
    }else{
	
		document.forms[0].sortByFlag.value=5;
	}
    
    document.forms[0].columnheader.value="MODEL";
	document.forms[0].action="HistoryAction.do?method=fetchOrders";
	document.forms[0].submit();
	}
}


function fnSortSpecStatus(){
	//Modified validation for CR_104
	if (fnCheckFilterCriteria())	{
    var sortKey=document.forms[0].sortByFlag.value;
    
    if(sortKey == 8){
    
		document.forms[0].sortByFlag.value=7;
    
    }else if(sortKey == 7){
    
		document.forms[0].sortByFlag.value=8;
    
    }else{
	
		document.forms[0].sortByFlag.value=7;
	}
    document.forms[0].columnheader.value="SPECSTATUS";
    document.forms[0].action="HistoryAction.do?method=fetchOrders";
	document.forms[0].submit();
	}
}


function fnSortOrderNumber(){
	//Modified validation for CR_104
	if (fnCheckFilterCriteria())	{
		
    var sortKey=document.forms[0].sortByFlag.value;
    
    if(sortKey == 10){
    
		document.forms[0].sortByFlag.value=9;
    
    }else if(sortKey == 9){
    	
		document.forms[0].sortByFlag.value=10;
    
    }else{
	
		document.forms[0].sortByFlag.value=9;
	}
    document.forms[0].columnheader.value="ORDERNUM";
    document.forms[0].action="HistoryAction.do?method=fetchOrders";
	document.forms[0].submit();
	}
}

/*
*  Name: fnPrint
*  Purpose: Used to print the Screen
*
*/
function fnPrint()
{
window.print();
}
/*
*  Name: fnClose
*  Purpose: Used to close the screen
*
*/
function fnClose()
{
window.close();
}

// Added for CR 101 to include customers in search criteria
function fnLoadCustomers()
{
	var HistoryForm = document.forms[0];
	HistoryForm.hdnSpecTypeNme.value = document.forms[0].specTypeSeqno.options[document.forms[0].specTypeSeqno.selectedIndex].text;
	HistoryForm.hdnSpecStatus.value  = document.forms[0].specStatusSeqNo.options[document.forms[0].specStatusSeqNo.selectedIndex].text;
	document.forms[0].action="HistoryAction.do?method=fetchCustomers";
	document.forms[0].submit();
}
//CR_101 Ends here
//CR_104 Sort Published User
function fnSortPublishedUser(){
	
	if (fnCheckFilterCriteria())	{
	
	    var sortKey=document.forms[0].sortByFlag.value;
	    
	    if(sortKey == 12){
	    
			document.forms[0].sortByFlag.value=11;
	    
	    }else if(sortKey == 11){
	    
			document.forms[0].sortByFlag.value=12;
	    
	    }else{
		
			document.forms[0].sortByFlag.value=11;
		}
	    document.forms[0].columnheader.value="PUBLISHEDUSER";
	    document.forms[0].action="HistoryAction.do?method=fetchOrders";
		document.forms[0].submit();
		
	}
}

/*
*  Name: fnCheckFilterCriteria
*  Purpose: Used to check Filter Criteria before 
*
*/
function fnCheckFilterCriteria() {

    var HistoryForm=document.forms[0];
    
    var len  = document.all["customerSeqnos"].options.length;
	var custnames="";
	for(i=0;i<len;i++)
	{
		if(document.all["customerSeqnos"].options[i].selected==true)
		{
			if(custnames=="")
				custnames=document.all["customerSeqnos"].options[i].text;
			else
				custnames=custnames+", "+document.all["customerSeqnos"].options[i].text;		
		}
	}
    
   if(HistoryForm.hdnSpecTypeNme.value != HistoryForm.specTypeSeqno.options[HistoryForm.specTypeSeqno.selectedIndex].text)
   {
		var id = '207';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("searchButton",-1);
		return false;
   }   
   else if(HistoryForm.hdnSpecStatus.value  != HistoryForm.specStatusSeqNo.options[HistoryForm.specStatusSeqNo.selectedIndex].text)
   {
		var id = '207';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("searchButton",-1);
		return false;
   }    
   else if(custnames != HistoryForm.hdnSelectedCustNames.value)
   {
		var id = '207';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("searchButton",-1);
		return false;
   } 
   else if(trim(HistoryForm.orderNum.value)=="" || trim(HistoryForm.orderNum.value).length==0)
   {
 		 	
	   	var id = '207';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("searchButton",-1);
		return false;
   }
	else 
		return true;
}