
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

	var resetSpecForm = document.forms[0];
	document.forms[0].hdnSelSpecType.value=resetSpecForm.spectypeSeqno.options[resetSpecForm.spectypeSeqno.selectedIndex].text;
	document.forms[0].action="ResetSpecAction.do?method=fetchModels";
	document.forms[0].submit();
}

function fnSearchOrders()
{
	var resetSpecForm = document.forms[0];

	if(trim(resetSpecForm.orderNo.value)=="" || trim(resetSpecForm.orderNo.value).length==0)
	{
	var id = '105';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("orderNo",-1);
	return false;

	}
//Modified for CR_104 to allow Wild Card searches JG101007
var invalid = WildCardSearchCheck(trim(resetSpecForm.orderNo.value));
	if(invalid){
	var id = '105';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("orderNo",-1);
	return false;
	}
//CR 90 for Order Number -removing number format validation	 --Start
//	if(isNaN(trim(resetSpecForm.orderNo.value))){
//	var id = '105';
//	hideErrors();
//	addMsgNum(id);
//	showErrors();
//	//resetSpecForm.orderNo.value="";
//	resetSpecForm.orderNo.focus();
//	return false;
//	}
//
//
//
//	if(!isNumeric(trim(resetSpecForm.orderNo.value)))
//	{
//	var id = '105';
//	hideErrors();
//	addMsgNum(id);
//	showErrors();
//	//resetSpecForm.orderNo.value="";
//	resetSpecForm.orderNo.focus();
//	return false;
//
//	}
//CR 90 for Order Number -removing number format validation	 --End

	document.forms[0].flag.value="N";
	document.forms[0].hdnSelSpecType.value=resetSpecForm.spectypeSeqno.options[resetSpecForm.spectypeSeqno.selectedIndex].text;
	document.forms[0].hdnSelModel.value=resetSpecForm.modelSeqNo.options[resetSpecForm.modelSeqNo.selectedIndex].text;
	document.forms[0].hdnSelectedCustomers.value=resetSpecForm.custSeqNo.options[resetSpecForm.custSeqNo.selectedIndex].text;
	resetSpecForm.hdnorderNo.value=resetSpecForm.orderNo.value;
	document.forms[0].action="ResetSpecAction.do?method=fetchOrders";
	document.forms[0].submit();
}

function resetSpecStatus(){
	
	$('#btnResetSpec').unbind('click');
	
	resetSpecForm = document.forms[0];
	var flagVal = false;
	var toSpecStatus = '';
	var index;
	if(resetSpecForm.fromOrderKey.length > 0 ){
		for(var i = 0 ; i < resetSpecForm.fromOrderKey.length; i++){

			if(resetSpecForm.fromOrderKey[i].checked){
			
					flagVal = true;
					index = i;
					resetSpecForm.hdnToOrderkey.value=resetSpecForm.toOrderkey[i].options[resetSpecForm.toOrderkey[i].selectedIndex].value;
					toSpecStatus = resetSpecForm.toOrderkey[index].options[resetSpecForm.toOrderkey[index].selectedIndex].text.toUpperCase();
					break;
			}
		}
	}else{
			if(resetSpecForm.fromOrderKey.checked){
					flagVal = true;
					resetSpecForm.hdnToOrderkey.value=resetSpecForm.toOrderkey.options[resetSpecForm.toOrderkey.selectedIndex].value;
					toSpecStatus = resetSpecForm.toOrderkey.options[resetSpecForm.toOrderkey.selectedIndex].text.toUpperCase();
			}
	    }

	if(!flagVal){

		var id = '764';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("fromOrderKey",-1);
		return false;
	}

	if(resetSpecForm.fromOrderKey.length > 0 ){

		 if (resetSpecForm.toOrderkey[index].options[resetSpecForm.toOrderkey[index].selectedIndex].value =="-1")
	       {
		 
			var id = '765';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("toOrderkey",index);
			return false;
	   
		   }

	}else{
			
		if (resetSpecForm.toOrderkey.options[resetSpecForm.toOrderkey.selectedIndex].value =="-1")
	    {
		 
			var id = '765';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("toOrderkey",-1);
			return false;
	   
		} 
	
	}

		if(document.forms[0].hdnSelSpecType.value != resetSpecForm.spectypeSeqno.options[resetSpecForm.spectypeSeqno.selectedIndex].text)
		{
			var id = '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("searchButton",-1);
			return false;
		}

		if(document.forms[0].hdnSelModel.value != resetSpecForm.modelSeqNo.options[resetSpecForm.modelSeqNo.selectedIndex].text)
		{
			var id = '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("searchButton",-1);
			return false;
		}
		
		if(document.forms[0].hdnSelectedCustomers.value != resetSpecForm.custSeqNo.options[resetSpecForm.custSeqNo.selectedIndex].text)
		{
			var id = '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("searchButton",-1);
			return false;
		}

		if(trim(resetSpecForm.orderNo.value)=="" || trim(resetSpecForm.orderNo.value).length==0)
		{
			var id = '105';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("orderNo",-1);
			return false;

		}

		if(trim(resetSpecForm.hdnorderNo.value) != trim(resetSpecForm.orderNo.value))
		{
			var id = '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("searchButton",-1);
			return false;
		}
		
		bootbox.confirm("The status of the selected spec will be changed to <b>" + toSpecStatus + "</b>. The resulting spec will retain all the changes made since <b>"+ toSpecStatus + "</b> and all the latest revision markers will change accordingly."
		+"<br/><br/> <mark>NOTE : The PDFs will not be generated.</mark><br/><br/>Would you like to continue ?", function(result) {
			  if (result)	{
				  document.forms[0].action="ResetSpecAction.do?method=resetSpecStatus";
				  document.forms[0].submit();	
			  }
			}); 
		
		/*var confirmMsg='The status of the selected spec will be changed to <b>' + toSpecStatus + '</b>. The resulting spec will retain all the changes made since <b>'+ toSpecStatus + '</b> and all the latest revision markers will change accordingly.'
		+'<br/><br/> NOTE : The PDFs will not be generated.<br/><br/>Would you like to continue ?';
		$('#message').html(confirmMsg);
		$('#btnResetSpec').click(function(){	
			confirm();
	    });*/

}

// Added for CR_99 (Confirm Box)
/*function confirm() {

	$('#confirm').modal({
		closeHTML: "<a href='#' id='close' title='Close' class='modal-close' >X</a>",
		position: ["20%",],
		escClose:false,
		overlayId: 'confirm-overlay',
		containerId:'confirm-container', 
		onShow: function (dialog) {
		var modal = this;

			// if the user clicks "yes"
			$('.yes', dialog.data[0]).click(function () {
				modal.close(); 
				document.forms[0].action="ResetSpecAction.do?method=resetSpecStatus";
				document.forms[0].submit();
			});
			// if the user clicks "no"
			$('.no', dialog.data[0]).click(function () {
				modal.close();
			});
		}
	});
	
}*/
// CR_99 ends here.