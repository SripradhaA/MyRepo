//CR_108 - Order Specific Claue Report
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
// To load models after selecting a specification type
function fnLoadModels()
{
  var OrderSpecificClauseForm  = document.forms[0];
  OrderSpecificClauseForm.hdnSelSpecType.value=OrderSpecificClauseForm.spectypeSeqno.options[OrderSpecificClauseForm.spectypeSeqno.selectedIndex].text;
  document.forms[0].action="OrderSpecificClauseAction.do?method=fetchModels";
  document.forms[0].submit();
}

function fnSearchOrders()
{
	var OrderSpecificClauseForm = document.forms[0];
	  
	if(OrderSpecificClauseForm.spectypeSeqno.options[OrderSpecificClauseForm.spectypeSeqno.selectedIndex].value=="-1"){
			var id = '2';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("selectSpectype",-1);
			return false;
	}
	
	if(OrderSpecificClauseForm.modelSeqNo.options[OrderSpecificClauseForm.modelSeqNo.selectedIndex].value=="-1"){
	   		var id = '19';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("selectModel",-1);
			return false;
	}
	
	if(trim(OrderSpecificClauseForm.orderNo.value)=="" || trim(OrderSpecificClauseForm.orderNo.value).length==0)
	{
		   	var id = '105';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("orderNo",-1);
			return false;
	
	}
	
	if(WildCardSearchCheck(trim(OrderSpecificClauseForm.orderNo.value))){
  			var id = '105';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("orderNo",-1);
			return false;
	}

	document.forms[0].flag.value="N";	
	document.forms[0].hdnSelSpecType.value=OrderSpecificClauseForm.spectypeSeqno.options[OrderSpecificClauseForm.spectypeSeqno.selectedIndex].text;
	document.forms[0].hdnSelModel.value=OrderSpecificClauseForm.modelSeqNo.options[OrderSpecificClauseForm.modelSeqNo.selectedIndex].text;

	document.forms[0].hdnSelectedCustomers.value=OrderSpecificClauseForm.customerSeqNO.options[OrderSpecificClauseForm.customerSeqNO.selectedIndex].text;
	document.forms[0].action="OrderSpecificClauseAction.do?method=fetchOrders";
	document.forms[0].submit();
}

function fnLoadCustomers(){

  var OrderSpecificClauseForm = document.forms[0];
   if(OrderSpecificClauseForm.hdPreSelectedModel.value!=OrderSpecificClauseForm.modelSeqNo.options[OrderSpecificClauseForm.modelSeqNo.selectedIndex].value)
        {
			
				var id = '207';
                hideErrors();
                addMsgNum(id);
                showScrollErrors("selectModel",-1);
				return false;
	    }	
					
	if(OrderSpecificClauseForm.hdnPreSelectedCustomer.value!=OrderSpecificClauseForm.customerSeqNO.options[OrderSpecificClauseForm.customerSeqNO.selectedIndex].value)
		 {
			
			var id = '207';
	        hideErrors();
	        addMsgNum(id);
	        showScrollErrors("customerSeqNO",-1);
		    return false;
		 }						
	
	document.forms[0].action="OrderSpecificClauseAction.do?method=loadCustomers";
	document.forms[0].submit();
}


function checkAll()
	{
		order = document.getElementsByName("orderKey");
			 for(var i = 0; i < order.length; i++)
			 {
				order[i].checked = true;
	 		 }
	}

function clearAll()
	{	
		order = document.getElementsByName("orderKey");
			for(var i = 0; i < order.length; i++)
			{
				if(order[i].checked)
					order[i].checked = false;
	 		}
	}
	
	
function fnViewOrderSpecificClauses()
{

	var check=false;
	document.forms[0].hdnSelSpecType.value=OrderSpecificClauseForm.spectypeSeqno.options[OrderSpecificClauseForm.spectypeSeqno.selectedIndex].text;
	type=document.forms[0].hdnSelSpecType.value;
	document.forms[0].hdnSelModel.value=OrderSpecificClauseForm.modelSeqNo.options[OrderSpecificClauseForm.modelSeqNo.selectedIndex].text;
	model=document.forms[0].hdnSelModel.value;
	if(document.forms[0].orderKey.length>1)
		{
	   		for(var i=0;i<document.forms[0].orderKey.length;i++)
	   			{
     				 if(document.forms[0].orderKey[i].checked)
     				 	{check=true;}
				}
		}
	else
		{
    	  if(document.forms[0].orderKey.checked)
    	  	{check=true;}
		}
		
	if(!check){
		var id= '793';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("orderKey",-1);
		return false;
			}
	
var orderKeys="";
var orderNumbers=""
var checkBox=false;
var obj = $('.ordNoChkbox'); 
var j=0;
	for(i=0;i<obj.length;i++){
			if(obj[i].type == "checkbox") {
					if((obj[i].checked)){
						checkBox=true;
						j=j+1;
if(orderKeys==""){
orderKeys=obj[i].value;
}else{
orderKeys=orderKeys+","+obj[i].value;
}
var no=obj[i].parentNode.parentNode;
if(orderNumbers==""){
orderNumbers=no.childNodes[3].innerText;
}else{
orderNumbers=orderNumbers+","+no.childNodes[3].innerText;
}
}}
}

if(OrderSpecificClauseForm.hdPreSelectedModel.value!=OrderSpecificClauseForm.modelSeqNo.options[OrderSpecificClauseForm.modelSeqNo.selectedIndex].value)
{
		var id = '207';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("modelSeqNo",-1);
		return false;
}		 


if(OrderSpecificClauseForm.hdnPreSelectedCustomer.value!=OrderSpecificClauseForm.customerSeqNO.options[OrderSpecificClauseForm.customerSeqNO.selectedIndex].value)
{
		var id = '207';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("customerSeqNO",-1);
	    return false;
}						
	
if (!((document.forms[0].showLatestFlag.checked && document.forms[0].hdnLatestFlag.value == "Yes") 
|| (!(document.forms[0].showLatestFlag.checked) && document.forms[0].hdnLatestFlag.value == "No")))
{
		var id = '207';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("showLatestFlag",-1);
	    return false;
}

if(OrderSpecificClauseForm.hdnOrderNo.value!=OrderSpecificClauseForm.orderNo.value)
{
		var id = '207';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("orderNo",-1);
	    return false;
}
	
var URL="OrderSpecificClauseAction.do?method=viewReport&orderKeys="+orderKeys+"&orderNumbers="+orderNumbers+"&specType="+type+"&Model="+model;
window.open(URL,'OrderSpecificClause',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH="+screen.width+",height=600");
}



function fnViewOrderSpecificClausesExcel()
{
var check=false;
document.forms[0].hdnSelSpecType.value=OrderSpecificClauseForm.spectypeSeqno.options[OrderSpecificClauseForm.spectypeSeqno.selectedIndex].text;
type=document.forms[0].hdnSelSpecType.value;
document.forms[0].hdnSelModel.value=OrderSpecificClauseForm.modelSeqNo.options[OrderSpecificClauseForm.modelSeqNo.selectedIndex].text;
model=document.forms[0].hdnSelModel.value;

	if(document.forms[0].orderKey.length>1)
		{
	   		for(var i=0;i<document.forms[0].orderKey.length;i++)
	   			{
     				 if(document.forms[0].orderKey[i].checked)
     				 	{check=true;}
				}
		}
	else
		{
    	  if(document.forms[0].orderKey.checked)
    	  	{check=true;}
		}

	if(!check){
		var id= '793';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("orderKey",-1);
		return false;
			}

var orderKeys="";
var orderNumbers=""
var checkBox=false;
var obj = $('.ordNoChkbox'); 
var j=0;
	for(i=0;i<obj.length;i++){
			if(obj[i].type == "checkbox") {
					if((obj[i].checked)){
						checkBox=true;
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
}}
}

if(OrderSpecificClauseForm.hdPreSelectedModel.value!=OrderSpecificClauseForm.modelSeqNo.options[OrderSpecificClauseForm.modelSeqNo.selectedIndex].value)
{
		var id = '207';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("modelSeqNo",-1);
		return false;
}		 


if(OrderSpecificClauseForm.hdnPreSelectedCustomer.value!=OrderSpecificClauseForm.customerSeqNO.options[OrderSpecificClauseForm.customerSeqNO.selectedIndex].value)
{
		var id = '207';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("customerSeqNO",-1);
	    return false;
}						


if (!((document.forms[0].showLatestFlag.checked && document.forms[0].hdnLatestFlag.value == "Yes") 
|| (!(document.forms[0].showLatestFlag.checked) && document.forms[0].hdnLatestFlag.value == "No")))
{
		var id = '207';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("showLatestFlag",-1);
	    return false;
}

if(OrderSpecificClauseForm.hdnOrderNo.value!=OrderSpecificClauseForm.orderNo.value)
{
		var id = '207';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("orderNo",-1);
	    return false;
}
//Below line updated for CR-127
document.forms[0].action="OrderSpecificClauseAction.do?method=exportOrderSpecificClausesReport&orderKeys="+orderKeys+"&orderNumbers="+orderNumbers+"&specType="+type+"&Model="+model;
document.forms[0].submit();
}