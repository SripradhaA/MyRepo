

function fnSearch()
{



if (document.forms[0].statusSeqNo.options[document.forms[0].statusSeqNo.selectedIndex].value =="-1" 
   && trim(document.forms[0].requestId.value)=="" &&
       document.forms[0].fromDate.value=="" &&
       document.forms[0].toDate.value=="" &&
       document.forms[0].lastName.options[document.forms[0].lastName.selectedIndex].value =="-1" )
       
{
var id = '830';
hideErrors();
addMsgNum(id);
showScrollErrors("statusSeqNo",-1);
return false;

}

if(document.forms[0].fromDate.value !="" && document.forms[0].toDate.value==""){

                        var id = '856';
	                    hideErrors();
	                    addMsgNum(id);
	                    showScrollErrors("toDate",-1);              
	                    return false;
}

if(document.forms[0].toDate.value!="" && document.forms[0].fromDate.value==""){

                        var id = '856';
	                    hideErrors();
	                    addMsgNum(id);
	                    showScrollErrors("fromDate",-1);          
	                    return false;
}

var currentdate = new Date();
var strFromDate=document.forms[0].fromDate.value;

if(document.forms[0].fromDate.value !="" && document.forms[0].toDate.value!=""){

var strFromD1=strFromDate.substring(0,2);
var strFromM1=strFromDate.substring(3,6);
var mon1=getMonth(strFromM1);
var strFromY1=strFromDate.substring(7);
var inputFromdate= new Date(strFromY1,mon1,strFromD1);


if(inputFromdate > currentdate)
{
                        var id = '831';
	                    hideErrors();
	                    addMsgNum(id);
	                    showScrollErrors("fromDate",-1);               
	                    return false;
}
}







var currentdate = new Date();
var strTodate=document.forms[0].toDate.value;

if(document.forms[0].fromDate.value !="" && document.forms[0].toDate.value!=""){

var strFromD2=strTodate.substring(0,2);
var strFromM2=strTodate.substring(3,6);
var mon2=getMonth(strFromM2);
var strFromY2=strTodate.substring(7);
var inputTodate= new Date(strFromY2,mon2,strFromD2);

if(inputTodate > currentdate)
{
                        var id = '831';
	                    hideErrors();
	                    addMsgNum(id);
	                    showScrollErrors("fromDate",-1);	               
	                    return false;
}

}





if(document.forms[0].fromDate.value !="" && document.forms[0].toDate.value !="")
{

if(document.forms[0].fromDate.value>document.forms[0].toDate.value)

{
var id = '831';
hideErrors();
addMsgNum(id);
showScrollErrors("fromDate",-1);
return false;

}
}				
	  
			
	  var isreq = SpecialCharacterCheck(trim(document.forms[0].requestId.value));
	if(isreq){
	
	                    var id = '833';
	                    hideErrors();
	                    addMsgNum(id);
	                    showScrollErrors("requestId",-1);	               
	                    return false;
	
	}

	if(trim(document.forms[0].requestId.value)!= "" && trim(document.forms[0].requestId.value).length >0){
	var isRequestID =isNumeric(trim(document.forms[0].requestId.value));
	
	if(!isRequestID){
                   
				       var id = '833';
	                    hideErrors();
	                    addMsgNum(id);
	                    showScrollErrors("requestId",-1);	               
	                    return false;   
	}
	}
    

	           
if(document.forms[0].statusSeqNo.options[document.forms[0].statusSeqNo.selectedIndex].value !="-1")
	{     
	 document.forms[0].hdStatus.value=document.forms[0].statusSeqNo.options[document.forms[0].statusSeqNo.selectedIndex].text;

	 }else{
	 document.forms[0].hdStatus.value="";
	 }


if(document.forms[0].requestId.value == "" && trim(document.forms[0].requestId.value).length < 1){

document.forms[0].hdnRequestId.value="";

}


document.forms[0].action="ModifyChangeAction.do?method=searchReqDetails";
	document.forms[0].submit();
}
      
    function checkAll()
{
	
	ReqID = document.getElementsByName("reqCnt");
     

	 
	 for(var i = 0; i < ReqID.length; i++){
		
		ReqID[i].checked = true;
	 
	 }

	
	 
 }

function clearAll()
{
	ReqID = document.getElementsByName("reqCnt");

     
	 for(var i = 0; i < ReqID.length; i++){
		
		if(ReqID[i].checked)
			ReqID[i].checked = false;
	 
	 }

	 
	
 }
function fnCreatePDF(){

var i;
var hdnIDArray = new Array();
var cnt=0;
var check=false;
if(document.forms[0].reqCnt.length>1){

   for(i=0;i<document.forms[0].reqCnt.length;i++){
     
	 if(document.forms[0].reqCnt[i].checked){

            check=true;
            
            
			
	 }

    
   }

}else{
      if(document.forms[0].reqCnt.checked){
     check=true;
	  
	  }
}
if(!check){
var id= '834';
hideErrors();
addMsgNum(id);
showScrollErrors("reqCnt",-1);
return false;
}



document.forms[0].action="ModifyChangeAction.do?method=createPDF";

document.forms[0].submit();

}

function isName(val)
{
   
if (val.match(/^[a-zA-Z\s]+$/))
{
return true;
}
else
{
return false;
} 
}



function deleteFromCalender()
    {
    if (document.forms[0].fromDate.value!="" )
    {
    var del=confirm("Are you sure you want to clear date");
    	if(del == true){
        document.forms[0].fromDate.value="";
        }
 
    }
}


function deleteToCalender()
  {
 
   if( document.forms[0].toDate.value!="")
    {
    var del=confirm("Are you sure you want to clear date");
	   if(del == true){
       document.forms[0].toDate.value="";
        }
    }
  }


 function getMonth(val) {
var monthArray = new Array("JAN","FEB","MAR","APR","MAY","JUN",
"JUL","AUG","SEP","OCT","NOV","DEC");
for (var i=0; i<monthArray.length; i++) {
if (monthArray[i] == val) {
return(i);
} 
}
return(-1);
}

/*
* Added for CR_80 CR Form Enhancements III on 26-Nov-09 by RR68151
*/
 function fnReassignCR(){
 
	reAssignForm = document.forms[0];
	var flagVal = false;
	var index;
	var cnt = 0;
	var requestID = 0;
	
	if(reAssignForm.reqCnt.length > 0 ){
		for(var i = 0 ; i < reAssignForm.reqCnt.length; i++){
				
			if(reAssignForm.reqCnt[i].checked){
			
					flagVal = true;
					index = i;
					reAssignForm.hdnAssigneeId.value=reAssignForm.assigneeId[i].options[reAssignForm.assigneeId[i].selectedIndex].value;
					requestID = reAssignForm.reqCnt[i].value;
					cnt = cnt + 1;
			}
			
			if (cnt > 1){
					 		
				var id= '872';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("reqCnt",-1);
				return false;
			
			}
			
		}
	}else{
			if(reAssignForm.reqCnt.checked){
					flagVal = true;
					reAssignForm.hdnAssigneeId.value=reAssignForm.assigneeId.options[reAssignForm.assigneeId.selectedIndex].value;
					requestID = reAssignForm.reqCnt.value;
			}
	    }

	if(!flagVal){

		var id = '834';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("reqCnt",-1);
		return false;
	}

	if(reAssignForm.reqCnt.length > 0 ){

		 if (reAssignForm.assigneeId[index].options[reAssignForm.assigneeId[index].selectedIndex].value =="-1") {
		 
			var id = '873';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("assigneeId",index);
			return false;
	   
		   }

	}
	else{
			
		if (reAssignForm.assigneeId.options[reAssignForm.assigneeId.selectedIndex].value =="-1")
	    {
		 
			var id = '873';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("assigneeId",-1);
			return false;
	   
		} 
	
	}
	
	var conf = window.confirm("Are you sure to re-assign the Change Request Form");
	if(conf){
		    
		document.forms[0].action="ModifyChangeAction.do?method=reAssignChangeRequest&requestID="+requestID+"";	
		document.forms[0].submit();				
			
	}else{

		return false;
	}
 }
 
 $(document).ready(function() {
  
 if (document.forms[0].hdnLastName.value == "All")
 document.forms[0].lastName.selectedIndex = 1;
 
 });
 
// Created in CR_101 - Completed CR Form Hyperlink 
 
function fnCompletedReqPDF(requestId)
{	
	clearAll();
	if (document.forms[0].reqCnt.length>1)
	{
		for (var i=0;i<document.forms[0].reqCnt.length;i++)
			{
				if(document.forms[0].reqCnt[i].value==requestId)
					{
						document.forms[0].reqCnt[i].checked=true;
						fnCreatePDF();
						clearAll();
					}
			}
	}
	else
	{
		clearAll();
		document.forms[0].reqCnt.checked=true;
		fnCreatePDF();
		clearAll();
	}
}	
//CR_101 Ends here
	
