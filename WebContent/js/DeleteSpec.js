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
  



function fnLoadModels()
{

  var DeleteSpecForm = document.forms[0];
  document.forms[0].hdnSelSpecType.value=DeleteSpecForm.spectypeSeqno.options[DeleteSpecForm.spectypeSeqno.selectedIndex].text;

  document.forms[0].action="DeleteSpecAction.do?method=fetchModels";
  document.forms[0].submit();
}

function fnSearchOrders()
{
	var DeleteSpecForm = document.forms[0];

	/**
	**	Mandatory Fields Validations for Specification and ModelSeqno is Comment out based on requirement of LSDB_CR-19
	**  Changes Done on 21-April-08
	**  By ps57222
	*/

	/* if(DeleteSpecForm.spectypeSeqno.options[DeleteSpecForm.spectypeSeqno.selectedIndex].value=="-1"){
			var id = '2';
			hideErrors();
			addMsgNum(id);
			showErrors();
			return false;
	}
	
	if(DeleteSpecForm.modelSeqNo.options[DeleteSpecForm.modelSeqNo.selectedIndex].value=="-1"){
	   		var id = '19';
			hideErrors();
			addMsgNum(id);
			showErrors();
			return false;
	} */
	
	if(trim(DeleteSpecForm.orderNum.value)=="" || trim(DeleteSpecForm.orderNum.value).length==0)
	{
		   	var id = '105';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("orderNum",-1);
			return false;
	
	}
	//Modified for CR_104 to allow Wild Card Search JG101007
	if(WildCardSearchCheck(trim(DeleteSpecForm.orderNum.value))==true){
  		var id = '105';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("orderNo",-1);
		//DeleteSpecForm.orderNum.value="";
		return false;
	}
//CR 90 for Order Number -removing number format validation	 --Start	
//	if(isNaN(trim(DeleteSpecForm.orderNum.value))){
// 					var id = '105';
//					hideErrors();
//					addMsgNum(id);
//					showErrors();
//					//DeleteSpecForm.orderNum.value="";
//					DeleteSpecForm.orderNum.focus();
//					return false;
//	}
	
//	if(!isNumeric(trim(DeleteSpecForm.orderNum.value)))
//	{
//		   	var id = '105';
//			hideErrors();
//			addMsgNum(id);
//			showErrors();
//			//DeleteSpecForm.orderNum.value="";
//			DeleteSpecForm.orderNum.focus();
//			return false;
//	
//	}
//CR 90 for Order Number -removing number format validation	 --End	
	document.forms[0].flag.value="N";
	document.forms[0].hdnSelSpecType.value=DeleteSpecForm.spectypeSeqno.options[DeleteSpecForm.spectypeSeqno.selectedIndex].text;
	document.forms[0].hdnSelModel.value=DeleteSpecForm.modelSeqNo.options[DeleteSpecForm.modelSeqNo.selectedIndex].text;
	/*Added for customer drop down as per CR 75 by cm68219*/
	document.forms[0].hdnSelectedCustomers.value=DeleteSpecForm.custSeqNo.options[DeleteSpecForm.custSeqNo.selectedIndex].text;
	DeleteSpecForm.hdnorderNo.value=DeleteSpecForm.orderNum.value;
	document.forms[0].srchOrderNum.value=DeleteSpecForm.orderNum.value;

	document.forms[0].action="DeleteSpecAction.do?method=fetchOrders";
	document.forms[0].submit();
}

function fnDeleteOrders(){

    var DeleteSpecForm = document.forms[0];
	var index;
	orderChecked = false;
	if(DeleteSpecForm.orderNumber.length>0)
	{
		  var cnt = DeleteSpecForm.orderNumber.length;
			  for(var i=0;i<cnt;i++)
			  {
			  	 if(DeleteSpecForm.orderNumber[i].checked)
				  	 {
				  	 	orderChecked = true;
				  	 	index = i;
				  	 	DeleteSpecForm.hdnorderNo.value=DeleteSpecForm.orderNo[index].value;
				  	 	break;
				  	 }
			  }
	}else{
		 if(DeleteSpecForm.orderNumber.checked)
		 {
		 	orderChecked = true;
		 	DeleteSpecForm.hdnorderNo.value=DeleteSpecForm.orderNo.value;
	  	 }
	}
	
	if(!orderChecked)
	{
		var id= '790';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("orderNumber",-1);
		return false;
	}
	/*Adde for changes in search criteria by cm68219*/
     if(document.forms[0].hdnSelSpecType.value != DeleteSpecForm.spectypeSeqno.options[DeleteSpecForm.spectypeSeqno.selectedIndex].text)
     {
     var id = '207';
     hideErrors();
     addMsgNum(id);
     showScrollErrors("sltSpecType",-1);
     return false;
     }

     if(document.forms[0].hdnSelModel.value != DeleteSpecForm.modelSeqNo.options[DeleteSpecForm.modelSeqNo.selectedIndex].text)
     {
     var id = '207';
     hideErrors();
     addMsgNum(id);
     showScrollErrors("sltModel",-1);
     return false;
     }
    /*Added for customer drop down by cm68219*/
     if(document.forms[0].hdnSelectedCustomers.value != DeleteSpecForm.custSeqNo.options[DeleteSpecForm.custSeqNo.selectedIndex].text)
     {
     var id = '207';
     hideErrors();
     addMsgNum(id);
     showScrollErrors("sltCustomer",-1);
     return false;
     }
	if(trim(DeleteSpecForm.srchOrderNum.value) != trim(DeleteSpecForm.orderNum.value))
	{
	var id = '207';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("orderNum",-1);
	return false;
	}

    var accept=confirm("Are you sure to delete the selected Order");
    if(accept == true){
    document.forms[0].action="DeleteSpecAction.do?method=deleteOrders";
	document.forms[0].submit();
	}else{
	
	}
  
}
	
	  
	
