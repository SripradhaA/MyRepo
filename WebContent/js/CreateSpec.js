//Added for CR-66
function keyHandler(e)
        { 
      var asciiValue = e ? e.which : window.event.keyCode;
if(asciiValue ==13){

        if(document.forms[0].flag.value == "Y"){ 
	    
        createSpec();
		
        return false;
         
         }

        }
       
}
document.onkeypress = keyHandler;    






function createSpec()
{
	
	 var sapCode = trim(document.forms[0].sapCusCode.value);
	 var orderNO = trim(document.forms[0].orderNo.value);
	 var quantity = trim(document.forms[0].quantity.value);
	 if (document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1")
	  {
			var id = '2';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("sltSpecType",-1);
			return false;
	   
		}

	//if((document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value=="1") ){
	//Updated for CR_84
	if (document.forms[0].distributorFlag.value == "N")	{
	
		if(!document.forms[0].modCustTypeSeqNo[0].checked && !document.forms[0].modCustTypeSeqNo[1].checked)
		{
			var id = '3';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("modCustTypeSeqNo2",-1);
			return false;
		}
	}

	/*if(document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text=="PM&I" && !document.forms[0].modCustTypeSeqNo[0].checked){
	
		
		var id = '13';
		hideErrors();
		addMsgNum(id);
		showErrors();
		return false;
	
	}*/

	//Updated for CR_84
	//if(document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value=="2" && document.forms[0].distSeqNo.options[document.forms[0].distSeqNo.selectedIndex].value=="-1"){
	if (document.forms[0].distributorFlag.value == "Y" && document.forms[0].distSeqNo.options[document.forms[0].distSeqNo.selectedIndex].value=="-1"){
		
		var id = '101';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltDistributor",-1);	
		return false;
	
	}


	 if (document.forms[0].cusSeqNo.options[document.forms[0].cusSeqNo.selectedIndex].value =="-1")
	  {
			var id = '301';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("sltCustomer",-1);
			return false;
	   
		}

	 if (document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value =="-1")
	  {
			var id = '19';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("sltModel",-1);			
			return false;
	   
		}


	if(sapCode.length==0 || sapCode==""){
		
			var id = '303';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("sapCusCode",-1);	
			return false;

		}else{

			var isSpecChar = SpecialCharacterCheck(sapCode);

				if(isSpecChar){
					
					var id = '303';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("sapCusCode",-1);	
					return false;
				
				}
		
		}

	if(orderNO.length==0 || orderNO==""){
		
			var id = '304';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("orderNo",-1);
			return false;

		}else{			//CR 90 for Order Number -removing number format validation	 
			if(SpecialCharacterCheck(orderNO)){
			//if(!validateNumber(orderNO)){
					var id = '304';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("orderNo",-1);	
					return false;
				
				}
		
		}	
		
	//CR 90 for Order Number -removing number format validation	
//		if(orderNO<=0 ){
//	
//			var id = '304';
//			hideErrors();
//			addMsgNum(id);
//			showErrors();
//			document.forms[0].orderNo.focus();
//			return false;
//
//		}
		
				
		

	if(quantity.length==0 || quantity=="" || quantity==0){
		
			var id = '305';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("quantity",-1);		
			return false;

		}else{

			if(!validateNumber(quantity)){

				
				var id = '305';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("quantity",-1);	
				return false;
				
			}
		
		}
		
		
		if(quantity<=0 ){
		
			var id = '305';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("quantity",-1);
			return false;

		}
		
		
	document.forms[0].flag.value="N";
	
	document.getElementById("createButton").disabled = true;
    //Added for Quantity issue on 17 Jun 09 
	document.forms[0].quantity.value = quantity;
	document.forms[0].action="CreateSpecAction.do?method=insertOrder";
	document.forms[0].submit();


}


function populate()
{

	 if (document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1")
	 {
			var id = '2';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("specTypeNo",-1);
			return false;
	   
	}

	if(document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value=="2"){
	
		
		/*var id = '13';
		hideErrors();
		addMsgNum(id);
		showErrors();
		return false;
	*/
	}
	

	document.forms[0].action="CreateSpecAction.do?method=populate";
	document.forms[0].submit();

}


/*
*	
*	Modified based on the requirement of LSDB_CR-56
*	Modified on 25-Aug-08
*	by ps57222
*	
*/

function changeSpecType()
{

	document.forms[0].modCustTypeSeqNo[0].checked= false;
	document.forms[0].modCustTypeSeqNo[1].checked= false;
   //Updated for CR_84
  if(document.forms[0].distributorFlag.value == "N"){

	document.forms[0].distSeqNo.disabled=true;
	document.forms[0].modCustTypeSeqNo[0].disabled= false;
	document.forms[0].modCustTypeSeqNo[1].disabled= false;

  }else if(document.forms[0].distributorFlag.value == "Y"){
  
   document.forms[0].distSeqNo.disabled=false;	
   document.forms[0].modCustTypeSeqNo[0].disabled = true;
   document.forms[0].modCustTypeSeqNo[1].disabled = true;
  }
	document.forms[0].action="CreateSpecAction.do?method=loadModels";
	document.forms[0].submit();
}


function validateNumber(fieldVal){

	num =  /^-?\d+$/;

	if(!num.test(fieldVal)){
		
		return false;
	}

	return true;
}




//Ends here


