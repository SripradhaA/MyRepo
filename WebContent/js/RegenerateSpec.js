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

	var regenerateSpecForm = document.forms[0];
	document.forms[0].hdnSelSpecType.value=regenerateSpecForm.spectypeSeqno.options[regenerateSpecForm.spectypeSeqno.selectedIndex].text;
	document.forms[0].action="RegenerateSpecAction.do?method=fetchModels";
	document.forms[0].submit();
}

function fnSearchOrders()
{
	var regenerateSpecForm = document.forms[0];

	if(trim(regenerateSpecForm.orderNo.value)=="" || trim(regenerateSpecForm.orderNo.value).length==0)
	{
	var id = '105';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("orderNo",-1);
	return false;

	}
//Added for CR_104 to allow Wild Card Searches JG101007 
	if(WildCardSearchCheck(trim(regenerateSpecForm.orderNo.value))==true){
	var id = '105';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("orderNo",-1);
	return false;
	}

	document.forms[0].flag.value="N";
	document.forms[0].hdnSelSpecType.value=regenerateSpecForm.spectypeSeqno.options[regenerateSpecForm.spectypeSeqno.selectedIndex].text;
	document.forms[0].hdnSelModel.value=regenerateSpecForm.modelSeqNo.options[regenerateSpecForm.modelSeqNo.selectedIndex].text;
	document.forms[0].hdnSelectedCustomers.value=regenerateSpecForm.custSeqNo.options[regenerateSpecForm.custSeqNo.selectedIndex].text;
	regenerateSpecForm.hdnorderNo.value=regenerateSpecForm.orderNo.value;
	document.forms[0].action="RegenerateSpecAction.do?method=fetchOrders";
	document.forms[0].submit();
}

function regenerateSpec(){
	
	$('#btnRegenerateSpec').unbind('click');
	regenerateSpecForm = document.forms[0];
	var flagVal = false;
	var index;
	var toSpecStatus = '';
	if(regenerateSpecForm.fromOrderKey.length > 0 ){
		for(var i = 0 ; i < regenerateSpecForm.fromOrderKey.length; i++){

			if(regenerateSpecForm.fromOrderKey[i].checked){
			
					flagVal = true;
					index = i;
					regenerateSpecForm.hdnToOrderkey.value=regenerateSpecForm.toOrderkey[i].options[regenerateSpecForm.toOrderkey[i].selectedIndex].value;
					 toRegenerateSpec = regenerateSpecForm.toOrderkey[index].options[regenerateSpecForm.toOrderkey[index].selectedIndex].text.toUpperCase();
					break;
			}
		}
	}else{
			if(regenerateSpecForm.fromOrderKey.checked){
					flagVal = true;
					regenerateSpecForm.hdnToOrderkey.value=regenerateSpecForm.toOrderkey.options[regenerateSpecForm.toOrderkey.selectedIndex].value;
					toRegenerateSpec= regenerateSpecForm.toOrderkey.options[regenerateSpecForm.toOrderkey.selectedIndex].text.toUpperCase();
			}
	    }
	    
	if(!flagVal){

		var id = '764';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("fromOrderKey",-1);
		return false;
	}

	if(regenerateSpecForm.fromOrderKey.length > 0 ){

		 if (regenerateSpecForm.toOrderkey[index].options[regenerateSpecForm.toOrderkey[index].selectedIndex].value =="-1")
	       {
		 
			var id = '765';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("toOrderkey",index);
			return false;
	   
		   }

	}else{
			
		if (regenerateSpecForm.toOrderkey.options[regenerateSpecForm.toOrderkey.selectedIndex].value =="-1")
	    {
		 
			var id = '765';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("toOrderkey",-1);
			return false;
	   
		} 
	
	}

	if(document.forms[0].hdnSelSpecType.value != regenerateSpecForm.spectypeSeqno.options[regenerateSpecForm.spectypeSeqno.selectedIndex].text)
		{
			var id = '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("searchButton",-1);
			return false;
		}

		if(document.forms[0].hdnSelModel.value != regenerateSpecForm.modelSeqNo.options[regenerateSpecForm.modelSeqNo.selectedIndex].text)
		{
			var id = '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("searchButton",-1);
			return false;
		}
		
		if(document.forms[0].hdnSelectedCustomers.value != regenerateSpecForm.custSeqNo.options[regenerateSpecForm.custSeqNo.selectedIndex].text)
		{
			var id = '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("searchButton",-1);
			return false;
		}

		if(trim(regenerateSpecForm.orderNo.value)=="" || trim(regenerateSpecForm.orderNo.value).length==0)
		{
			var id = '105';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("orderNo",-1);
			return false;

		}

		if(trim(regenerateSpecForm.hdnorderNo.value) != trim(regenerateSpecForm.orderNo.value))
		{
			var id = '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("searchButton",-1);
			return false;
		}
		
		//Added for CR_124 Starts
		var preliminaryStatus=toRegenerateSpec.replace("PRELIMINARY","PROPOSAL");
		var openingStatus=toRegenerateSpec.replace("OPENING","BASELINE");
		
		if(toRegenerateSpec.indexOf("PRELIMINARY") !=-1)
		{
			bootbox.confirm("PDFs will be regenerated for <b>"+ toRegenerateSpec +"</b> version of the selected spec.<br/><br/><mark>Note: Spec will be renamed from <b>"+ toRegenerateSpec +"</b> to <b>"+ preliminaryStatus +"</b>.</mark> <br/><br/>Would you like to continue?", function(result) {
				  if (result)	{
					  document.forms[0].action="RegenerateSpecAction.do?method=regenerateSpec";
					  document.forms[0].submit();	
				  }
				}); 
			
		}
		else if(toRegenerateSpec.indexOf("OPENING") !=-1)
		{
			bootbox.confirm("PDFs will be regenerated for<b> "+ toRegenerateSpec +"</b> version of the selected spec.<br/><br/>Note: Spec will be renamed from <b>"+ toRegenerateSpec +"</b> to <b>"+ openingStatus +"</b>. <br/><br/>Would you like to continue?", function(result) {
				  if (result)	{
					  document.forms[0].action="RegenerateSpecAction.do?method=regenerateSpec";
					  document.forms[0].submit();	
				  }
				}); 
		}
		else{	
			bootbox.confirm("PDFs will be regenerated for<b> "+ toRegenerateSpec +"</b> version of the selected spec.<br/><br/>Would you like to continue?", function(result) {
				  if (result)	{
					  document.forms[0].action="RegenerateSpecAction.do?method=regenerateSpec";
					  document.forms[0].submit();	
				  }
				}); 
		}
		//Added for CR_124 Ends
}
function confirm() {
	$('#confirm').modal({
		closeHTML: "<a href='#' id='close' title='Close' class='modal-close' >X</a>",
		position: ["20%",],
		escClose:false,
		overlayId: 'confirm-overlay',
		containerId: 'confirm-container', 
		onShow: function (dialog) {
		var modal = this;

			$('.yes', dialog.data[0]).click(function () {
				modal.close(); 
				document.forms[0].action="RegenerateSpecAction.do?method=regenerateSpec";
				document.forms[0].submit();				
			});
			
			$('.no', dialog.data[0]).click(function () {
				modal.close(); 
			});
		}
	});
}
