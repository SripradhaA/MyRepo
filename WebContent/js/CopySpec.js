/* Modified various error ids to match newer format */
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

  var modifySpecForm = document.forms[0];
  document.forms[0].hdnSelSpecType.value=modifySpecForm.spectypeSeqno.options[modifySpecForm.spectypeSeqno.selectedIndex].text;

  document.forms[0].action="CopySpecAction.do?method=fetchModels";
  document.forms[0].submit();
}

function fnSearchOrders()
{
	
	var copySpecForm = document.forms[0];
	  
	if(copySpecForm.spectypeSeqno.options[copySpecForm.spectypeSeqno.selectedIndex].value=="-1"){
			var id = '2';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("sltSpecType",-1);
			return false;
	}
	
	if(copySpecForm.modelSeqNo.options[copySpecForm.modelSeqNo.selectedIndex].value=="-1"){
	   		var id = '19';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("sltModel",-1);
			return false;
	}
	
	if(trim(copySpecForm.orderNo.value)=="" || trim(copySpecForm.orderNo.value).length==0)
	{
		   	var id = '105';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("orderNo",-1);
			return false;
	
	}
	
	//Added for CR_104 to allow Wild Card search JG101007
	if(WildCardSearchCheck(trim(copySpecForm.orderNo.value))){
  			var id = '105';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("orderNo",-1);
			return false;
	}


	document.forms[0].flag.value="N";	
	document.forms[0].hdnSelSpecType.value=copySpecForm.spectypeSeqno.options[copySpecForm.spectypeSeqno.selectedIndex].text;
	document.forms[0].hdnSelModel.value=copySpecForm.modelSeqNo.options[copySpecForm.modelSeqNo.selectedIndex].text;
	/*Added for customer drop down as per CR 75 */
	document.forms[0].hdnSelectedCustomers.value=copySpecForm.customerSeqNO.options[copySpecForm.customerSeqNO.selectedIndex].text;
	document.forms[0].action="CopySpecAction.do?method=fetchOrders";
	document.forms[0].submit();
}

function fnCopySpecFrmModel()
{
	var copySpecForm = document.forms[0];
	var index;
	orderKeyChecked = false;
	var orderKey;

	if(copySpecForm.hdPreSelectedModel.value!=copySpecForm.modelSeqNo.options[copySpecForm.modelSeqNo.selectedIndex].value){
		
					
									var id = '207';
                                    hideErrors();
                                    addMsgNum(id);
                                    showScrollErrors("sltModel",-1);
									return false;
	}	
	
	if ($("input[type=radio][name=orderKey]:checked").length > 0)
		orderKeyChecked = true;
	
	/*if($("input[type=radio][name=orderKey]:checked").length>0)
	{ 
		  var cnt = $("input[type=radio][name=orderKey]").length;
			  for(var i=0;i<cnt;i++)
			  {
				  	 if(copySpecForm.orderKey[i].checked)
				  	 {
				  	 	orderKeyChecked = true;
				  	 	index = i;
				  	 	break;
				  	 }
			  }
	}else{
	 
		 if(copySpecForm.orderKey.checked)
		 {
		 	orderKeyChecked = true;
	  	 }
	}*/
	
	if(!orderKeyChecked)
	{
	 
		var id= '111';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("orderKey",1);
		return false;
	} else {
		$("#hdnOrderKey").val($("input[type=radio][name=orderKey]:checked").val());
	}
	
	//if(copySpecForm.spectypeSeqno.options[copySpecForm.spectypeSeqno.selectedIndex].text == "Locomotive")
	//{Updated for CR_84
	if (copySpecForm.distributorFlag.value == "N")	{ 
		if(!copySpecForm.custTypeSeqNo[0].checked && !copySpecForm.custTypeSeqNo[1].checked)
		{
			
				var id = '3';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("custTypeSeqNo",-1);
				return false;
		}
	}
	
	//if(copySpecForm.spectypeSeqno.options[copySpecForm.spectypeSeqno.selectedIndex].text != "Locomotive")
	//{Updated for CR_84
	if (copySpecForm.distributorFlag.value == "Y")	{ 
	
		if(!copySpecForm.custTypeSeqNo[0].checked && !copySpecForm.custTypeSeqNo[1].checked)
		{
		
				var id = '13';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("custTypeSeqNo1",-1);
				return false;
		}
	
	} 
	
	//if(copySpecForm.spectypeSeqno.options[copySpecForm.spectypeSeqno.selectedIndex].text != "Locomotive")
	//{	Updated for CR_84
	if (copySpecForm.distributorFlag.value == "Y")	{ 
		if(!copySpecForm.custTypeSeqNo[0].checked && copySpecForm.custTypeSeqNo[1].checked)
		{
		
				var id = '13';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("custTypeSeqNo1",-1);
				return false;
		}
	
	} 
	
	
	//if(copySpecForm.spectypeSeqno.options[copySpecForm.spectypeSeqno.selectedIndex].text != "Locomotive")
	//{	Updated for CR_84
	if (copySpecForm.distributorFlag.value == "Y")	{ 
		if(copySpecForm.distSeqNo.options[copySpecForm.distSeqNo.selectedIndex].value=="-1")
		{
				var id = '101';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("sltDistributor",-1);
				return false;
		}
	}
	
	if(copySpecForm.custSeqNo.options[copySpecForm.custSeqNo.selectedIndex].value=="-1")
	{
			var id = '102';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("sltCustomer",-1);
			return false;
	}
	
	if(trim(copySpecForm.sapCustCode.value)=="" || trim(copySpecForm.sapCustCode.value).length==0 || copySpecForm.sapCustCode.value==0)
	{
		   var id = '303';
		   hideErrors();
		   addMsgNum(id);
		   showScrollErrors("sapCusCode",-1);
		   return false;
	}
	
	if(SpecialCharacterCheck(trim(copySpecForm.sapCustCode.value)))
	 {
		  var id = '303';
		  hideErrors();
		  addMsgNum(id);
		  showScrollErrors("sapCusCode",-1);
		  return false;

		}
	
	if(trim(copySpecForm.orderNumber.value)=="" || trim(copySpecForm.orderNumber.value).length==0 || trim(copySpecForm.orderNumber.value)<=0)
	{
		   	var id = '105';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("orderNO",-1);
			return false;
	
	}
//CR 90 for Order Number-removing number format validation	
	if(SpecialCharacterCheck(trim(copySpecForm.orderNumber.value))){
//	if(!validateNumber(trim(copySpecForm.orderNumber.value))){
  		var id = '105';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("orderNumber",-1);
		return false;
	}	
	
	
	
	if(trim(copySpecForm.quantity.value)=="" || trim(copySpecForm.quantity.value).length==0 || trim(copySpecForm.quantity.value)<=0 )
	{
		var id = '305';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("quantity",-1);
		return false;
	
	}
	
	if(!validateNumber(trim(copySpecForm.quantity.value))){
	
  	    var id = '305';
		hideErrors();
		addMsgNum(id);
		//CR 90 for Existing Error fix
		showScrollErrors("quantity",-1);
		return false;
	}
	
	document.forms[0].hdnSelSpecType.value=copySpecForm.spectypeSeqno.options[copySpecForm.spectypeSeqno.selectedIndex].text;
	document.forms[0].hdnSelModel.value=copySpecForm.modelSeqNo.options[copySpecForm.modelSeqNo.selectedIndex].text;	
	
	document.getElementById("CopySpecFromModelButton").disabled = true;
	//Quantity Issue fix on 22 July 09
	document.forms[0].quantity.value = trim(document.forms[0].quantity.value); 
	
	document.forms[0].action="CopySpecAction.do?method=copySpecFromModel";
	document.forms[0].submit();
	
}

function fnCopySpec()
{
	var copySpecForm = document.forms[0];
	var index;
	orderKeyChecked = false;

	if(copySpecForm.hdPreSelectedModel.value!=copySpecForm.modelSeqNo.options[copySpecForm.modelSeqNo.selectedIndex].value){
		

									var id = '207';
                                    hideErrors();
                                    addMsgNum(id);
                                    showScrollErrors("sltModel",-1);
									return false;
	}		
	
	if ($("input[type=radio][name=orderKey]:checked").length > 0)
		orderKeyChecked = true;
	
	/*if(copySpecForm.orderKey.length>0)
	{
		  var cnt = copySpecForm.orderKey.length;
			  for(var i=0;i<cnt;i++)
			  {
				  	 if(copySpecForm.orderKey[i].checked)
				  	 {
				  	 	orderKeyChecked = true;
				  	 	index = i;
				  	 	break;
				  	 }
			  }
	}else{
		 if(copySpecForm.orderKey.checked)
		 {
		 	orderKeyChecked = true;
	  	 }
	}*/
	
	if(!orderKeyChecked)
	{
		var id= '111';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("orderKey",1);
		return false;
	} else {
		$("#hdnOrderKey").val($("input[type=radio][name=orderKey]:checked").val());
	}
	
	//if(copySpecForm.spectypeSeqno.options[copySpecForm.spectypeSeqno.selectedIndex].text == "Locomotive")
	//{	Updated for CR_84
	if (copySpecForm.distributorFlag.value == "N")	{ 
		if(!copySpecForm.custTypeSeqNo[0].checked && !copySpecForm.custTypeSeqNo[1].checked)
		{
				var id = '3';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("custTypeSeqNo",-1);
				return false;
		}
	
	} 
	
	//if(copySpecForm.spectypeSeqno.options[copySpecForm.spectypeSeqno.selectedIndex].text != "Locomotive")
	//{	Updated for CR_84
	if (copySpecForm.distributorFlag.value == "Y")	{ 
		if(!copySpecForm.custTypeSeqNo[0].checked && !copySpecForm.custTypeSeqNo[1].checked)
		{
				var id = '13';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("custTypeSeqNo",-1);
				return false;
		}
	
	} 
	
	//if(copySpecForm.spectypeSeqno.options[copySpecForm.spectypeSeqno.selectedIndex].text != "Locomotive")
	//{	Updated for CR_84
	if (copySpecForm.distributorFlag.value == "Y")	{ 
		if(!copySpecForm.custTypeSeqNo[0].checked && copySpecForm.custTypeSeqNo[1].checked)
		{
				var id = '13';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("custTypeSeqNo",-1);
				return false;
		}
	
	} 
	
	//if(copySpecForm.spectypeSeqno.options[copySpecForm.spectypeSeqno.selectedIndex].text != "Locomotive")
	//{	Updated for CR_84
	if (copySpecForm.distributorFlag.value == "Y")	{ 
		if(copySpecForm.distSeqNo.options[copySpecForm.distSeqNo.selectedIndex].value=="-1")
		{
				var id = '101';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("sltDistributor",-1);
				return false;
		}
	}
	
	if(copySpecForm.custSeqNo.options[copySpecForm.custSeqNo.selectedIndex].value=="-1")
	{
			var id = '102';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("sltCustomer",-1);
			return false;
	}
	
	if(trim(copySpecForm.sapCustCode.value)=="" || trim(copySpecForm.sapCustCode.value).length==0 || trim(copySpecForm.sapCustCode.value)==0)
	{
		   var id = '303';
		   hideErrors();
		   addMsgNum(id);
		   showScrollErrors("sapCusCode",-1);
		   return false;
	}
	
	if(SpecialCharacterCheck(trim(copySpecForm.sapCustCode.value)))
	 {
		  var id = '303';
		  hideErrors();
		  addMsgNum(id);
		  showScrollErrors("sapCusCode",-1);
		 return false;

		}
	
	if(trim(copySpecForm.orderNumber.value)=="" || trim(copySpecForm.orderNumber.value).length==0 || trim(copySpecForm.orderNumber.value)<=0)
	{
		   	var id = '105';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("orderNO",-1);
			return false;
	
	}
	//CR 90 for removing number format validation	
	if(SpecialCharacterCheck(trim(copySpecForm.orderNumber.value))){	
//	if(!validateNumber(trim(copySpecForm.orderNumber.value))==true){

  		var id = '105';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("orderNO",-1);
		return false;
	}
	
	if(trim(copySpecForm.quantity.value)=="" || trim(copySpecForm.quantity.value).length==0 || trim(copySpecForm.quantity.value)<=0)
	{
		var id = '305';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("quantity",-1);
		return false;
	
	}
	
	if(!validateNumber(trim(copySpecForm.quantity.value))){
  	    var id = '305';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("quantity",-1);
		return false;
	}
	
	document.forms[0].hdnSelSpecType.value=copySpecForm.spectypeSeqno.options[copySpecForm.spectypeSeqno.selectedIndex].text;
	document.forms[0].hdnSelModel.value=copySpecForm.modelSeqNo.options[copySpecForm.modelSeqNo.selectedIndex].text;
	document.getElementById("CopySpecButton").disabled = true;
	//Quantity Issue fix on 22 July 09
	document.forms[0].quantity.value = trim(document.forms[0].quantity.value); 
	document.forms[0].action="CopySpecAction.do?method=copySpec";
	document.forms[0].submit();
}

/*
*	Name: fnLoadCustomers
*	Purpose: Used to Customer List on selection of CustomerType Domestic/Export
*	Added on 25-Aug-08 
*	by ps57222
*	For LSDB_CR-56
*/

function fnLoadCustomers(){
  /*Added as per CR 75 by cm68219 on 7-May-09*/

  var copySpecForm = document.forms[0];
  
   if ($("input[type=radio][name=orderKey]:checked").length > 0)
	   $("#hdnOrderKey").val($("input[type=radio][name=orderKey]:checked").val());
  
   if(copySpecForm.hdPreSelectedModel.value!=copySpecForm.modelSeqNo.options[copySpecForm.modelSeqNo.selectedIndex].value)
        {
			
				var id = '207';
                hideErrors();
                addMsgNum(id);
                showScrollErrors("sltModel",-1);
				return false;
	    }	
					
	if(copySpecForm.hdnPreSelectedCustomer.value!=copySpecForm.customerSeqNO.options[copySpecForm.customerSeqNO.selectedIndex].value)
		 {
			
			var id = '207';
	        hideErrors();
	        addMsgNum(id);
	        showScrollErrors("sltCust",-1);
		    return false;
		 }						
	
	document.forms[0].action="CopySpecAction.do?method=loadCustomers";
	document.forms[0].submit();
}

$(document).ready(function() {
	if ($("#hdnOrderKey").length > 0 && ($("#hdnOrderKey").val() != 0))
		$("input[type=radio][name='orderKey'][value='"+$("#hdnOrderKey").val()+"']").prop('checked', true);
});