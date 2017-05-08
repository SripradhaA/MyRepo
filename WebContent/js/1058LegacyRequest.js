$(document).ready(function()
{
	if(document.forms[0].editLegacyFlag.value == "N") //If condition added for CR-126
	{ 
		var status1058 	  = document.getElementById("1058status");
		var selectedValue = document.forms[0].hdnStatus.value;
		if(selectedValue == 0 ){
			$("#1058Status").val('-1');
		}else{
			$("#1058Status").val(selectedValue);
		}
		//Added for CR-120
		var issuedBy 	  = document.getElementById("legacyIssuedBy");
		var currentValue  = document.forms[0].hdnIssuedBy.value;
		if(currentValue == 0 ){
			$("#legacyIssuedBy").val('-1');
		issuedBy.value = -1;	
		}else{
			$("#legacyIssuedBy").val(currentValue);
		}
	}
	$("#1058Status").select2({dropdownAutoWidth:true}); 
	$("#legacyIssuedBy").select2({dropdownAutoWidth:true});

});

/*function keyHandler(e)
        { 
      var asciiValue = e ? e.which : window.event.keyCode;
if(asciiValue ==13){

        if(document.forms[0].flag.value == "Y"){ 
	    
        insertLegacyRequest();
		
        return false;
         
         }

        }
       
}
document.onkeypress = keyHandler;    */

function insertLegacyRequest(){

var orderNO 	 	 = trim(document.forms[0].orderNo.value);
var number1058 	 	 = trim(document.forms[0].number1058.value);
var customer 	 	 = trim(document.forms[0].customer.value);
var model 		 	 = trim(document.forms[0].model.value);
var status1058 		 = document.getElementById("1058status");
var issuedBy 		 = document.getElementById("legacyIssuedBy");
var specRev 	 	 = trim(document.forms[0].specRev.value);
var uploadAttachment = trim(document.forms[0].uploadAttachment.value);


if(orderNO.length==0 || orderNO==""){
	
			var id = '1015';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("orderNo",-1);
			return false;

		}else{			
			if(SpecialCharacterCheck(orderNO)){
					var id = '304';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("orderNo",-1);
					return false;
				
				}
		
		}
if(number1058.length==0 || number1058==""){
		
			var id = '1010';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("number1058",-1);
			return false;

}else{			
			if(!(isNumeric(number1058))){
					var id = '1011';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("number1058",-1);
					return false;
				
				}
		
}	
if(customer.length==0 || customer==""){
		
			var id = '1012';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("customer",-1);
			return false;

}		
if(model.length==0 || model==""){
		
			var id = '1013';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("model",-1);
			return false;

}	
if ($('#1058Status').val() == "-1"){

        var id = '1016';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("1058Status",-1);
        return false;
}
/*if(specRev.length==0 || specRev==""){
		
			var id = '1014';
			hideErrors();
			addMsgNum(id);
			showErrors();
			document.forms[0].specRev.focus();
			return false;

}*/
if(document.forms[0].actualSellPrice.value !=""){
	if (!(isNumericDecimal(document.forms[0].actualSellPrice.value))) {
			
			        var id = '1001';
			        hideErrors();
			        addMsgNum(id);
			        showScrollErrors("actualSellPrice",-1);
			        return false;
			
	}
}

if(document.forms[0].workOrderUSD.value !=""){
	if (!(isNumericDecimal(document.forms[0].workOrderUSD.value))) {
			
			        var id = '1031';
			        hideErrors();
			        addMsgNum(id);
			        showScrollErrors("workOrderUSD",-1);
			        return false;
			
	}
}

if(uploadAttachment.length==0 || uploadAttachment==""){
		
			var id = '1019';
			//hideErrors();
			addMsgNum(id);
			showScrollErrors("uploadAtch",-1);
			return false;
}

var fileUpload = $('input[type=file]').val();  
 
//extracting part of the filename from dot
var extension = fileUpload.substring(fileUpload.lastIndexOf('.'));

var file= fileUpload.split('\\').pop();		//Added for CR_124

//valid file type - static
var ValidFileType = ".pdf, .PDF";

//check whether user has selected file or not
if (fileUpload.length > 0) {

//check file is of valid type or not
if (ValidFileType.toLowerCase().indexOf(extension) < 0) {
		    var id = '200';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("uploadAttachment",-1);
			return false;
     }
}


	


//Added for CR_124 Starts here
document.getElementById("createButton").disabled = true;

$.getJSON("SearchRequest1058Action.do?method=check1058FileExist",{fileName : file ,ajax: 'true', type: 'post', cache: 'false'}, function(j){

		if (j.ChkFileExist != null) {
			if(j.ChkFileExist==true){
				
			var conMsg=window.confirm("The file Already Exists \n\n Do you want to replace it? ");
				if(conMsg == true){
					document.forms[0].action="SearchRequest1058Action.do?method=uploadLegacyReport";
					document.forms[0].submit();
				}
			
			
			else	{
				document.getElementById("createButton").disabled = false;
			}
		}
		else {
			
			//window.location = "AjaxException.do";
			document.forms[0].action="SearchRequest1058Action.do?method=uploadLegacyReport";
			document.forms[0].submit();
		}
	}
    })
}
//Added for CR_124 Ends here

//Added for CR-126

function updateLegacyRequest(){
	
var orderNO 	 	 = trim(document.forms[0].orderNo.value);
var number1058 	 	 = trim(document.forms[0].number1058.value);
var customer 	 	 = trim(document.forms[0].customer.value);
var model 		 	 = trim(document.forms[0].model.value);
var status1058 		 = document.getElementById("1058status");
var issuedBy 		 = document.getElementById("legacyIssuedBy");
var specRev 	 	 = trim(document.forms[0].specRev.value);
var uploadAttachment = trim(document.forms[0].uploadAttachment.value);

var seqNo1058 = getParameterByName('1058SeqNo');

if(orderNO.length==0 || orderNO==""){
	
			var id = '1015';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("orderNo",-1);
			return false;

		}else{			
			if(SpecialCharacterCheck(orderNO)){
					var id = '304';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("orderNo",-1);
					return false;
				
				}
		
		}
if(number1058.length==0 || number1058==""){
		
			var id = '1010';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("number1058",-1);
			return false;

}else{			
			if(!(isNumeric(number1058))){
					var id = '1011';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("number1058",-1);
					return false;
				
				}
		
}	
if(customer.length==0 || customer==""){
		
			var id = '1012';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("customer",-1);
			return false;

}		
if(model.length==0 || model==""){
		
			var id = '1013';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("model",-1);
			return false;

}	
if ($('#status1058').val() == "-1"){

        var id = '1016';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("status",-1);
        return false;
}
/*if(specRev.length==0 || specRev==""){
		
			var id = '1014';
			hideErrors();
			addMsgNum(id);
			showErrors();
			document.forms[0].specRev.focus();
			return false;

}*/

if(document.forms[0].actualSellPrice.value !=""){
	if (!(isNumericDecimal(document.forms[0].actualSellPrice.value))) {
			
			        var id = '1001';
			        hideErrors();
			        addMsgNum(id);
			        showScrollErrors("actualSellPrice",-1);
			        return false;
			
	}
}

if(document.forms[0].workOrderUSD.value !=""){
	if (!(isNumericDecimal(document.forms[0].workOrderUSD.value))) {
			
			        var id = '1031';
			        hideErrors();
			        addMsgNum(id);
			        showScrollErrors("workOrderUSD",-1);
			        return false;
			
	}
}

/*if(uploadAttachment.length==0 || uploadAttachment==""){
		
			var id = '1019';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("uploadAttachment",-1);
			return false;
}*/

if(uploadAttachment.length > 0){

	var fileUpload = $('input[type=file]').val();  
	 
	//extracting part of the filename from dot
	var extension = fileUpload.substring(fileUpload.lastIndexOf('.'));
	
	var file= fileUpload.split('\\').pop();		//Added for CR_124
	
	//valid file type - static
	var ValidFileType = ".pdf, .PDF";
	
	//check whether user has selected file or not
	if (fileUpload.length > 0) {
	
	//check file is of valid type or not
	if (ValidFileType.toLowerCase().indexOf(extension) < 0) {
			    var id = '200';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("uploadAttachment",-1);
				return false;
	     }
	}

document.getElementById("updateButton").disabled = true;

$.getJSON("SearchRequest1058Action.do?method=check1058FileExist",{fileName : file ,ajax: 'true', type: 'post', cache: 'false'}, function(j){

		if (j.ChkFileExist != null) {
			if(j.ChkFileExist==true){
				
			var conMsg=window.confirm("The file Already Exists \n\n Do you want to replace it? ");
				if(conMsg == true){
					document.forms[0].action="SearchRequest1058Action.do?method=updateLegacyReport&1058SeqNo="+seqNo1058;
					document.forms[0].submit();
				}
			
			
			else	{
				document.getElementById("createButton").disabled = false;
			}
		}
		else {
			
			//window.location = "AjaxException.do";
			document.forms[0].action="SearchRequest1058Action.do?method=updateLegacyReport&1058SeqNo="+seqNo1058;
			document.forms[0].submit();
		}
	}
    })
}else{
		document.getElementById("updateButton").disabled = true;
		document.forms[0].action="SearchRequest1058Action.do?method=updateLegacyReport&1058SeqNo="+seqNo1058;
		document.forms[0].submit();
}    
	
}

function return1058Search(){
	
	document.getElementById("returntoSearch").disabled = true;
    
    document.forms[0].orderNo.value = document.forms[0].hdnorderNo.value;
    
    if(document.forms[0].hdnSelectedCustomer.value == "---Select---")
		document.forms[0].hdnSelectedCustomer.value ="";
	if(document.forms[0].hdnSelectedAwApproval.value == "---Select---")
		document.forms[0].hdnSelectedAwApproval.value ="";	
		
	document.forms[0].action = "SearchRequest1058Action.do?method=fetchDetails";
	document.forms[0].submit();
}

//Added for CR-126 Ends here

