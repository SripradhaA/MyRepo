//Added for CR-106
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


// For searching orders
function fnSearchOrders()
{
var OndemandSpecSupplementForm= document.forms[0];

if(trim(OndemandSpecSupplementForm.orderNo.value)=="" || trim(OndemandSpecSupplementForm.orderNo.value).length==0)
{
var id = '105';
hideErrors();
addMsgNum(id);
showScrollErrors("orderNo",-1);
return false;

}

if(WildCardSearchCheck(trim(OndemandSpecSupplementForm.orderNo.value))==true){
var id = '105';
hideErrors();
addMsgNum(id);
showScrollErrors("orderNo",-1);
return false;
}

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
//Modified for CR_108
document.forms[0].sortByFlag.value=9; //Display in Order Number Ascending order  
document.forms[0].columnheader.value="ORDERNUM";
document.forms[0].flag.value="N";
OndemandSpecSupplementForm.specType.value=OndemandSpecSupplementForm.spectypeSeqno.options[OndemandSpecSupplementForm.spectypeSeqno.selectedIndex].text;
document.forms[0].hdnSelectedMdlNames.value=modelnames;
OndemandSpecSupplementForm.custName.value=OndemandSpecSupplementForm.custSeqNo.options[OndemandSpecSupplementForm.custSeqNo.selectedIndex].text;
OndemandSpecSupplementForm.hdnorderNo.value=OndemandSpecSupplementForm.orderNo.value;
document.forms[0].action="OndemandSpecSupplement.do?method=fetchOrders";
document.forms[0].submit();
}
/*
 * fnLoadModelsForComparison - To fetch Models CR_131
 */
function fnLoadModelsForComparison()
{
var OndemandSpecSupplementForm = document.forms[0];
document.forms[0].hdnSelSpecType.value=OndemandSpecSupplementForm.spectypeSeqno.options[OndemandSpecSupplementForm.spectypeSeqno.selectedIndex].text;
document.forms[0].action="OndemandSpecSupplement.do?method=fetchModels";
document.forms[0].submit();
}


function filterChangeValidate(onDemandSpecForm){

	if(document.forms[0].specType.value != onDemandSpecForm.spectypeSeqno.options[onDemandSpecForm.spectypeSeqno.selectedIndex].text)
		{
			var id = '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("sltSpecType",-1);
			return false;
		}
	//Added Model filter criteria for CR_131	
	var	selectedMdlNames = "";
	$("#sltModel option:selected").each(function() {
	      selectedMdlNames += $(this).text() + ", ";
	});
	
	if(document.forms[0].hdnSelectedMdlNames.value != selectedMdlNames.substring(0,selectedMdlNames.lastIndexOf(",")))
		{
			var id = '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("sltModel",-1);
			return false;
		}
			
		if(document.forms[0].custName.value != onDemandSpecForm.custSeqNo.options[onDemandSpecForm.custSeqNo.selectedIndex].text)
		{
			var id = '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("sltCustomer",-1);
			return false;
		}

		if(trim(onDemandSpecForm.orderNo.value)=="" || trim(onDemandSpecForm.orderNo.value).length==0)
		{
			var id = '105';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("orderNo",-1);
			return false;

		}

		if(trim(onDemandSpecForm.hdnorderNo.value) != trim(onDemandSpecForm.orderNo.value))
		{
			var id = '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("orderNo",-1);
			return false;
		}
		return true;

}


//Added for LSDB_CR-76 for sorting

function fnSortCustType(){
	
	var sortKey=document.forms[0].sortByFlag.value;
	onDemandSpecForm = document.forms[0];
	var check = filterChangeValidate(onDemandSpecForm);
 	
	if(check){
		if(sortKey == 2){
			
			document.forms[0].sortByFlag.value=1;
			
		}else if(sortKey == 1){
			
			document.forms[0].sortByFlag.value=2;
			
		}else{
		
			document.forms[0].sortByFlag.value=1;
		}
	   
		document.forms[0].columnheader.value="CUSTYPE";
		document.forms[0].action="OndemandSpecSupplement.do?method=fetchOrders";
		document.forms[0].submit();
	}
    
}
//Added FOR CR_106 fixes
function fnSortCust(){
	
	var sortKey=document.forms[0].sortByFlag.value;
	onDemandSpecForm = document.forms[0];
	var check = filterChangeValidate(onDemandSpecForm);
 	
	if(check){
		if(sortKey == 4){
			
			document.forms[0].sortByFlag.value=3;
			
		}else if(sortKey == 3){
			
			document.forms[0].sortByFlag.value=4;
			
		}else{
		
			document.forms[0].sortByFlag.value=3;
		}
	   
		document.forms[0].columnheader.value="CUST";
		document.forms[0].action="OndemandSpecSupplement.do?method=fetchOrders";
		document.forms[0].submit();
	}
    
}
//Added FOR CR_106 fixes
function fnSortModel(){
	
	var sortKey=document.forms[0].sortByFlag.value;
	onDemandSpecForm = document.forms[0];
	var check = filterChangeValidate(onDemandSpecForm);
 	
	if(check){
		if(sortKey == 6){
			
			document.forms[0].sortByFlag.value=5;
			
		}else if(sortKey == 5){
			
			document.forms[0].sortByFlag.value=6;
			
		}else{
			
			document.forms[0].sortByFlag.value=5;
		}
	   
		document.forms[0].columnheader.value="MODEL";
		document.forms[0].action="OndemandSpecSupplement.do?method=fetchOrders";
		document.forms[0].submit();
	}
    
}

//Added FOR CR_106 fixes
function fnSortSpecStatus(){
	
	var sortKey=document.forms[0].sortByFlag.value;
	onDemandSpecForm = document.forms[0];
	var check =  filterChangeValidate(onDemandSpecForm);
 	
	if(check){
		if(sortKey == 8){
			
			document.forms[0].sortByFlag.value=7;
			
		}else if(sortKey == 7){
			
			document.forms[0].sortByFlag.value=8;
			
		}else{
		
			document.forms[0].sortByFlag.value=7;
		}
	    document.forms[0].columnheader.value="SPECSTATUS";
		document.forms[0].action="OndemandSpecSupplement.do?method=fetchOrders";
		document.forms[0].submit();
	}
    
}

//Added FOR CR_106 fixes
function fnSortOrderNumber(){
	
	var sortKey=document.forms[0].sortByFlag.value;
	onDemandSpecForm = document.forms[0];
	var check =  filterChangeValidate(onDemandSpecForm);
 	if(check){
		if(sortKey == 9){
			
			document.forms[0].sortByFlag.value=10;
			
		}else if(sortKey == 10){
			
			document.forms[0].sortByFlag.value=9;
			
		}else{
				  document.forms[0].sortByFlag.value=9;
		 }
	   
		document.forms[0].columnheader.value="ORDERNUM";
		document.forms[0].action="OndemandSpecSupplement.do?method=fetchOrders";
		document.forms[0].submit();
    }
}


//Added FOR CR_106 fixes
function fnSortCustModelName(){
	
	var sortKey=document.forms[0].sortByFlag.value;
	onDemandSpecForm = document.forms[0];
	var check =  filterChangeValidate(onDemandSpecForm);
 	
	if(check){
		if(sortKey == 16){
			
			document.forms[0].sortByFlag.value=15;
			
		}else if(sortKey == 15){
			
			document.forms[0].sortByFlag.value=16;
			
		}else{
			document.forms[0].sortByFlag.value=15;
		}
		document.forms[0].columnheader.value="CUSTMODELNAME";
		document.forms[0].action="OndemandSpecSupplement.do?method=fetchOrders";
		document.forms[0].submit();
	}    
}


function fnLoadCustomers(){
	
document.forms[0].action="OndemandSpecSupplement.do?method=fetchCustomers";
document.forms[0].submit();

}

//To validate the number of checkboxes selected

function chklength()
{
var OndemandSpecSupplementForm= document.forms[0];
var max=document.forms[0].orderKey.length;
var total=0;
for (var x=0;x<max;x++)
{
	if (eval("OndemandSpecSupplementForm.orderKey["+x+"].checked")==true)
		{
			total+=1;
		}
}
//Modified for CR_106 - QA fix	
if(total>2)
{
var id = '930';
hideErrors();
addMsgNum(id);
showScrollErrors("orderKey",-1);
return false;
}
else if(total<2)
{
var id = '933';
hideErrors();
addMsgNum(id);
showScrollErrors("orderKey",-1);
return false;
}
else
return true;
}

//For Engineeering Supplement
function fnViewEngrSupp()
{
//Added for CR_108
onDemandSpecForm = document.forms[0];
var check =  filterChangeValidate(onDemandSpecForm);
clearAlerts();//Sdded for CR-131 to clear alert tooltip
if(check){
var ordlength = chklength();
if (ordlength){
		var orderKey="";
		var prevOrderKey="";
		var orderNumbers="";
		var selectedSpecType="";
		var selectedModels="";
		var selectedCustModelNames="";
		var obj = $('.ordNoChkbox'); 
		for(i=0;i<obj.length;i++){
			if(obj[i].type == "checkbox") {
				if((obj[i].checked)){
					if(orderKey==""){
						orderKey=obj[i].value;
					}else{
						//Modified for CR_106 Integer Comparison
						if (parseInt(orderKey) > parseInt(obj[i].value))
							prevOrderKey = obj[i].value;
						else {
							prevOrderKey = orderKey;
							orderKey = obj[i].value;					
						}
					}
					var no=obj[i].parentNode.parentNode;			
					if(selectedSpecType==""){
						selectedSpecType=no.childNodes[1].innerText;
					}else{
						selectedSpecType=selectedSpecType+","+no.childNodes[1].innerText;
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
					if(selectedCustModelNames==""){
						selectedCustModelNames=no.childNodes[4].innerText;
					}else{
						selectedCustModelNames=selectedCustModelNames+","+no.childNodes[4].innerText;
					}
				}
			}
		}
		document.forms[0].action="GenerateProofReadingDraftAction.do?method=createProofReadingPDF&orderKey="+orderKey+
		                                "&prevOrderKey="+prevOrderKey+"&revCode="+1+
		                                "&pdfType=EngrSupp";
		document.forms[0].submit();
	}
	}
}

//For Sales Supplement
function fnViewSalesSupp()
{
//Added for CR_108
onDemandSpecForm = document.forms[0];
var check =  filterChangeValidate(onDemandSpecForm);
clearAlerts();//Sdded for CR-131 to clear alert tooltip
if(check){
var ordlength = chklength();
if (ordlength){

		var orderKey="";
		var prevOrderKey="";
		var orderNumbers="";
		var selectedSpecType="";
		var selectedModels="";
		var selectedCustModelNames="";
		var obj = $('.ordNoChkbox'); 
		for(i=0;i<obj.length;i++){
			if(obj[i].type == "checkbox") {
				if((obj[i].checked)){
					if(orderKey==""){
						orderKey=obj[i].value;
					}else{
						//Modified for CR_106 Integer Comparison
						if (parseInt(orderKey) > parseInt(obj[i].value))
							prevOrderKey = obj[i].value;
						else {
							prevOrderKey = orderKey;
							orderKey = obj[i].value;					
						}
					}
					var no=obj[i].parentNode.parentNode;			
					if(selectedSpecType==""){
						selectedSpecType=no.childNodes[1].innerText;
					}else{
						selectedSpecType=selectedSpecType+","+no.childNodes[1].innerText;
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
					if(selectedCustModelNames==""){
						selectedCustModelNames=no.childNodes[4].innerText;
					}else{
						selectedCustModelNames=selectedCustModelNames+","+no.childNodes[4].innerText;
					}
				}
			}
		}
		document.forms[0].action="GenerateProofReadingDraftAction.do?method=createProofReadingPDF&orderKey="+orderKey+
		                                "&prevOrderKey="+prevOrderKey+"&revCode="+1+
		                                "&pdfType=SalesSupp";
		document.forms[0].submit();

	}
	}
}
