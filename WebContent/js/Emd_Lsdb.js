function fnLogin(){

	var userID= trim(document.forms[0].userID.value);
	var password=trim(document.forms[0].password.value);
	document.forms[0].userID.value=userID;
	document.forms[0].password.value=password;

	document.forms[0].action="LoginAction.do?method=findUser";
	/* Commented for CR_109 repeated form submissions
	 * document.forms[0].submit();
	 */

}

function SpecialCharacterCheck(field){

	var iChars = "!@#$%^&*()+=[]\\\/{}|\<>`~.-'_";
	for (var i = 0; i < field.length; i++) {
  	
	if (iChars.indexOf(field.charAt(i)) != -1) {
  		return true;
  	}
  }
	return false;

}

function trim(sValue) {
	
	var s=sValue; 
	return ltrim(rtrim(s));
	
	}

function ltrim(sValue) {
	
	var s=sValue;	

	return s.replace(/^ */, "");
}

function rtrim(sValue) {
	
	var s=sValue; 
	return s.replace(/ *$/, "");
}

function validateNumber(fieldVal){

	num =  /^-?\d+$/;

	if(!num.test(fieldVal)){
		
		var id = '306';
		hideErrors();
		addMsgNum(id);
		//showErrors();
		return false;
	}

	return true;
}

function validateEmail(fieldVal){

	email = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

	if(!email.test(fieldVal)){
		alert("enter valid email");
		return false;
	}


}
function isAlphabetic(val)
{
if (val.match(/^[a-zA-Z]+$/))
{
return true;
}
else
{
return false;
} 
}

 function isNumeric(inputValue) {

   var validChars = "0123456789";
   var isNum = true;
   var charVal;
   var valLength = inputValue.length;
 
   for (i = 0; i < valLength && isNum == true; i++) { 
      charVal = inputValue.charAt(i); 
      if (validChars.indexOf(charVal) == -1)
         isNum = false;
      }
   return isNum;
}

 function isNumericDecimal(inputValue) {

   var validChars = /^-?(?=.*[0-9])\d*(\.\d{1,2})?$/;
   return validChars.test(inputValue);
}

function isNumericHours(inputValue) {

   var validChars = /^-?(?=.*[0-9])\d*?$/;
   return validChars.test(inputValue);
}


function disableKey(event) {

	
  if (!event) event = window.event;
 
  if (!event) return;
  
  var keyCode = event.keyCode ? event.keyCode : event.charCode;
  
  if (keyCode == 116) {
  
	  window.status = "F5 key detected! Attempting to disabling default response.";
	  
	  window.setTimeout("window.status='';", 2000);

	  // Standard DOM (Mozilla):
	  if (event.preventDefault) event.preventDefault();
	   //IE (exclude Opera with !event.preventDefault):

	   if (document.all && window.event && !event.preventDefault) {

		 event.cancelBubble = true;

		 event.returnValue = false;

		 event.keyCode = 0;

	   }
	
	   return false;

	}
	/*else if(keyCode == 13){
		return false;

}*/
}


function setEventListener(eventListener) {
	
  if (document.addEventListener) document.addEventListener('keypress', eventListener, true);

  else if (document.attachEvent) document.attachEvent('onkeydown', eventListener);

  else document.onkeydown = eventListener;

  if (!document.getElementById) return;

}

function sessionOutLogin() {

	document.forms[0].action="LogoutAction.do?method=logout";
	document.forms[0].submit();
}

function fnclose() {
	 window.close();

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

function showCatelogFooterInstruction(){

	//Local 
	//Changed for HeaderFooter Template
	//window.open("http://172.18.140.71:8080/webdav/Specs/Header_Footer_Template.doc","HeaderFooterInstruction","location=0,resizable=yes ,status=0,scrollbars=1,WIDTH="+screen.width+",height=600");
	//QA
	window.open("file://emdlagvmas11/lsdb_qa/specs/Header_Footer_Template.doc","HeaderFooterInstruction","location=0,resizable=yes ,status=0,scrollbars=1,WIDTH="+screen.width+",height=600");
	//Prod
	//window.open("file://emdlagvmas14/lsdb_prod/specs/Header_Footer_Template.doc","HeaderFooterInstruction","location=0,resizable=yes ,status=0,scrollbars=1,WIDTH="+screen.width+",height=600");
}

function OpenCHM(screenid)
{
	
	if(screenid!="null" && screenid!="" && screenid<="34")
	//Local
		window.open('http://172.18.140.71:8080/webdav/Specs/webhelp/scr/html/'+screenid+'.html','LSDBHELP','location=0,resizable=yes ,status=0,scrollbars=1,width=750,height=500'); 
	//QA
	//	window.open('file://emdlagvmas11/lsdb_qa/specs/webhelp/scr/html/'+screenid+'.html','LSDBHELP','location=0,resizable=yes ,status=0,scrollbars=1,width=750,height=500'); 
	//Prod
	//	window.open('file://emdlagvmas14/lsdb_prod/specs/webhelp/scr/html/'+screenid+'.html','LSDBHELP','location=0,resizable=yes ,status=0,scrollbars=1,width=750,height=500'); 
	//window.showHelp('mk:@MSITStore:file://cmsfps001/EMD-LSDB/specs/EMD-LSDB.chm::/'+screenid+'.html');
	else
	//Local
		window.open('http://172.18.140.71:8080/webdav/Specs/webhelp/scr/html/24.html','LSDBHELP','location=0,resizable=yes ,status=0,scrollbars=1,width=750,height=500'); //Home page as default
	//QA
	//	window.open('file://emdlagvmas11/lsdb_qa/specs/webhelp/scr/html/24.html','LSDBHELP','location=0,resizable=yes ,status=0,scrollbars=1,width=750,height=500'); 
	//Prod
	//	window.open('file://emdlagvmas14/lsdb_prod/specs/webhelp/scr/html/24.html','LSDBHELP','location=0,resizable=yes ,status=0,scrollbars=1,width=750,height=500'); 
	
}
//Added for CR-111
function OpenHelpDoc(screenid)
{
	
	if(screenid!="null" && screenid!="" && screenid<="34")
	//Local
		window.open('http://172.18.29.2:8080/webdav/EMD-LSDB_Help/EMD-LSDB-HELPFILE.doc','LSDBHELP','location=0,resizable=yes ,status=0,scrollbars=1,width=750,height=500'); 
	//QA
	//	window.open('file://emdlagvmas11/lsdb_qa/specs/webhelp/scr/html/'+screenid+'.html','LSDBHELP','location=0,resizable=yes ,status=0,scrollbars=1,width=750,height=500'); 
	//Prod
	//	window.open('file://emdlagvmas14/lsdb_prod/specs/webhelp/scr/html/'+screenid+'.html','LSDBHELP','location=0,resizable=yes ,status=0,scrollbars=1,width=750,height=500'); 
	//window.showHelp('mk:@MSITStore:file://cmsfps001/EMD-LSDB/specs/EMD-LSDB.chm::/'+screenid+'.html');
	else
	//Local
		window.open('http://172.18.29.2:8080/webdav/EMD-LSDB_Help/EMD-LSDB-HELPFILE.doc','LSDBHELP','location=0,resizable=yes ,status=0,scrollbars=1,width=750,height=500');  //Home page as default
	//QA
	//	window.open('file://emdlagvmas11/lsdb_qa/specs/webhelp/scr/html/24.html','LSDBHELP','location=0,resizable=yes ,status=0,scrollbars=1,width=750,height=500'); 
	//Prod
	//	window.open('file://emdlagvmas14/lsdb_prod/specs/webhelp/scr/html/24.html','LSDBHELP','location=0,resizable=yes ,status=0,scrollbars=1,width=750,height=500'); 
	
}
//Added for CR-111 Ends Here.
/*
*  Name: runHeaderFooter
*  Purpose: Used to install Header/Footer Template
*  Added For CR-77 by RR68151
*/
function runHeaderFooter() {

  if (navigator.userAgent.indexOf("Windows NT 6") > -1) {
  		//Local
		window.open ("http://172.18.140.71:8080/webdav/Specs/HeaderFooter7Vista.exe",'HeaderTemplate');
		//QA
		//window.open ("file://emdlagvmas11/lsdb_qa/specs/HeaderFooter7Vista.exe",'HeaderTemplate');
		//Prod
		//window.open ("file://emdlagvmas14/lsdb_prod/specs/HeaderFooter7Vista.exe",'HeaderTemplate');
	}

  else if (navigator.userAgent.indexOf("Windows NT 5") > -1) {
  		//Local
	    window.open ("http://172.18.140.71:8080/webdav/Specs/HeaderFooterXP.exe",'HeaderTemplate');
	    //QA
		//window.open ("file://emdlagvmas11/lsdb_qa/specs/HeaderFooterXP.exe",'HeaderTemplate');
		//Prod
		//window.open ("file://emdlagvmas14/lsdb_prod/specs/HeaderFooterXP.exe",'HeaderTemplate');
	}
	
  else {
		alert("Your Operating System does not support Header/Footer Template Process");
	}

  }
  

/*
*  Added For CR_100
*  Name: fnShowSuggestion
*  Purpose: Used to show the Suggestions Overlay
*/
function fnShowSuggestion()
{
	$('#screenName').val($.trim($('h4:not(.navbar-text):first').text()));
	$('#suggest').modal();
}

/*
*  Added For CR_100
*  Name: startCallback
*  Purpose: Used to validate the suggestion fields before submitting
*/
function startCallback() {
	var scrName    	= $('#screenName').val();
	var suggestion  = $('textarea#suggestion.notAssigned').val();
	if (trim(scrName) =="")
	{ 
		var id = '907';
		addMsgNum(id);
		showScrollErrors("screenName",-1);
		return false;
	}	
	else if (scrName.length > 100)
	{
		var id = '910';
		addMsgNum(id);
		showScrollErrors("screenName",-1);
		return false;	   
	}
	else if (trim(suggestion) =="")
	{
		var id = '908';
		addMsgNum(id);
		showScrollErrors("suggestion",-1);
		return false;
	}
	else if (suggestion.length > 4000)
	{
		var id = '909';
		addMsgNum(id);
		showScrollErrors("suggestion",-1); 
		return false; 
	}
	else 
	{
		$("#submitSugg").prop("disabled", true);
		return true;	 
	}
}

/*
*  Added For CR_100
*  Name: completeCallback
*  Purpose: Used to process the response after submitting suggestion
*/
function completeCallback(response) {
	var result = response;
	if (result == "success") {
		$('#suggest').on('hidden.bs.modal', function (e) {
            $('#suggestSuccess').modal('show');
            $(this).off('hidden.bs.modal'); // Remove the 'on' event binding
    	}).modal('hide');
	}
	else if (result == "filesizetoolarge") {
		var id = '911';
		//hideSuggestErrors(suggestError);
		addMsgNum(id);
		showScrollErrors("suggestFileUpload",-1);
	}
	else {
	    //Added for CR-112  
	    if(result.indexOf("not authorised")!=-1){ 
	    	window.location = "AjaxUnauthorisedException.do";
	    } //Added for CR-112 ends here
	    else{
	    	window.location = "AjaxException.do";	    
	    }
	}
	$("#submitSugg").prop("disabled",false);
}
//Created for CR_104 to allow * for wildcard Search of Orders JG101007
function WildCardSearchCheck(field)
{
	var iChars1 = "!@#$%^&()+*=[]\\\/{}|\<>`~.-'_/";
		var iChars2 = "!@#$%^&()+=[]\\\/{}|\<>`~.-'_/";
	
	if(field.length>1)
	{
		
	for (var i = 0; i < field.length; i++) {
      if(iChars1.indexOf(field.charAt(i)) != -1){
      	return true;
		}
 	 }
		
	}
	else{
		if(iChars2.indexOf(field.charAt(0))!=-1)	
			return true;
	}
	return false;
}

//Added for CR-112 for Message Of the Day
//Modified multiple functions for CR_131
function switchToEdit()
{
	$('#Container_Text').hide();
	$('#Message').val($('#hdnMessage').val().trim());
	$('#Container_Edittable').show();
}
function switchBack(save)
{
	
	if(save)	{
		if (addMessageOfDay())	{
			$('#Container_Edittable').hide();
			$('#Container_Text').show();
		}
	} else {
		$(".validated").tooltip('destroy');
		$(".form-group").removeClass("has-success has-error has-feedback");
		$(".validated").removeClass('validated');
		$('#Container_Edittable').hide();
		$('#Container_Text').show();
	}
	
	
}

function addMessageOfDay(){
	var message = $('#Message').val().trim();//trim(document.forms[0].Message.value);
	if(message!=null && message!=""){
			document.forms[0].hdnMessage.value = $("#Message").val();
			document.forms[0].action="LogoutAction.do?method=insertMessage";
			document.forms[0].submit();
	} else {
		if (!$('#Message').hasClass('validated')) {
			$('#Message').closest(".form-group").addClass("has-error has-feedback");
			$('#Message').prop('title', '<span class="glyphicon glyphicon-remove"></span> ' + 'Please enter a valid message');
			$('#Message').tooltip({
				template : '<div role="tooltip" class="tooltip"><div class="tooltip-inner noWidth alert-message error"></div></div>',
				html : true,
				container: "body"
			});
			
			$('#Message').addClass('validated');
			$('#Message').tooltip('fixTitle').tooltip('show');
			$('#Message').val($('#Message').val().trim());
		}
		return false;
	}
}

function msgLimit(field, maxlen) {
if (field.value.length > maxlen + 1)
if (field.value.length > maxlen)
field.value  = field.value.substring(0, maxlen);
var message  = trim(field.value);
var chkmsg	 = isBlank(message);
if (chkmsg == true)
	$('#tick').hide();
else
	$('#tick').show();
}

function isBlank(message) {
    return (!message || /^\s*$/.test(message));
}
//CR_112 Ends here

/*Added for CR-116 for set button background color */
function fnDisableButtons(id){
if(id == ""){	
$(".btn").prop("disabled", true);//Edited fr CR-131
$(".btn").addClass("disabled");
//$("input[type='button']").addClass('buttonStyleGrey');
//Added for CR-117 Suggestion module
fnEnableDiv("suggest");
fnEnableDiv("suggestSuccess");
//End Added for CR-117 Suggestion module
}else{
$(id).prop("disabled", true);	
$(id).addClass('disabled');	
}

}

function fnEnableButtons(id){
$(id).prop("disabled", false);	
$(id).removeClass('disabled');	
}

//Added for CR-117 Suggestion module
function fnEnableDiv(id){
		$("#"+id+" input").prop("disabled",false);
		$("#"+id+" .btn").prop("disabled",false);
		$("#"+id+" .btn").removeClass("disabled");
		if(id=="suggest"){
			$("#userName").prop("disabled", true);	
		}
}
//End Added for CR-117 Suggestion module

/*Added for CR-116 for set button background color */

function fnSetMaxSize(field, maxlen){
if (field.value.length > maxlen + 1)
if (field.value.length > maxlen)
field.value  = field.value.substring(0, maxlen);
}
/*Added for CR-116 for set button background color */
//Added for CR-121
function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

$(document).ready(function(){
    $(function () {
    	  $('[data-toggle="tooltip"]').tooltip({'placement': 'auto right','container':'body'});
    });
    //reset modal content every time user closes the modal
    $('#suggest').on('hide.bs.modal', function () {
    	  clearForm('suggestForm');
	});
	
	$("p:empty").each(function(){
		$(this).html("<br/>");
	});

	if($("#messageID").length > 0){
		if(!($("#messageID").val()==undefined || $("#errorelement").val()=="" || $("#messageID").val()==0)){
			var index = -1;
			addMsgNum($("#messageID").val());
			if (($("#messageID").val()==6) || ($("#messageID").val()==791)||
					($("#messageID").val()==151)||($("#messageID").val()==934)){//In future convert to list when the no of erros increases
				pagesuccess();
			}else if(($("#messageID").val()==16)){
				norecords();
			} else {
				adderrorlayer();
			}
		}
	}
	
	$("#messageID").val(0);
		
	$('.btn-file :file').on('fileselect', function(event, numFiles, label) {		
        var input = $(this).parents('.input-group').find(':text');
        var log = numFiles > 1 ? numFiles + ' files selected' : label;
        
        input.val(log);        
    });
    
    	
     $("ul.nav li.dropdown").hover(
        function(){ $(this).addClass('open') },
        function(){ $(this).removeClass('open') }
     );
     
     $("#suggestForm").on('submit', submitSugg);
          
     function submitSugg(event)    {

    	 if (startCallback())	{
     
		     if(!isAjaxUploadSupported()){ //IE fallback
		    	 
		    	var d = document.createElement('DIV');
		    	
		    	var iframeID = "iframe" + Math.floor(Math.random() * 99999);
		    	 
		 		d.innerHTML = '<iframe  '
		 			    + 'src="about:blank" style="display:none;" id="'
		 			    + iframeID + '" name="'
		 			    + iframeID + '"></iframe>';
		 		
		        $('#suggestForm').prop("target", iframeID);
		        
		 		document.body.appendChild(d);
			 		
		 		iframeIdmyFile = document.getElementById(iframeID);
				
		         var eventHandlermyFile = function () {
		             
		        	 if (iframeIdmyFile.detachEvent) 
		                 iframeIdmyFile.detachEvent("onload", eventHandlermyFile);
		
		        	 completeCallback(getIframeContent(iframeIdmyFile))
		
		         }
		
		         if (iframeIdmyFile.attachEvent) 
		             iframeIdmyFile.attachEvent("onload", eventHandlermyFile);
			 		
					
		     } else {
		    	 
		    	var formData = new FormData($(this)[0]);
		
		 	    $.ajax({
		 	        url: 'SuggestAction.do?method=submitSuggestion&screenid=24',
		 	        type: 'POST',
		 	        data: formData,
		 	        cache: false,
		 	        dataType: 'text',
		 	        processData: false, // Don't process the files
		 	        contentType: false, // Set content type to false as jQuery will tell the server its a query string request
		 	        success: function(data)
		 	        {
		 	        	completeCallback(data);
		 	        },
		 	        error: function(jqXHR, textStatus, errorThrown)
		 	        {
		 	        	window.location = "AjaxException.do";
		 	        }
		 	    });
		 	    
		 	    return false;
		     }
    	 } else {
    		 
    		 return false;
    		 
    	 }
     
     }
     
	 $('#suggest').on('show.bs.modal', function (event) {
		$(".tooltip").tooltip('destroy');
		clearForm('suggestForm');
	 	$('#screenName').val($.trim($('h4:not(.navbar-text):first').text()));
	});
	 
	 $('.modal').on('hide.bs.modal', function (e) {
		 clearAlerts();
	 });
	 
	 $('body').on('hidden.bs.tooltip', function() {
		    var tooltips = $('.tooltip').not('.in');
		    if (tooltips) {
		        tooltips.remove();
		    }
	});
})

$(document).on('change', '.btn-file :file', function() {	  
	  var input = $(this);
      var numFiles = input.get(0).files ? input.get(0).files.length : 1,
      label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
  	  input.trigger('fileselect', [numFiles, label]);
});
	
function isAjaxUploadSupported(){
        var input = document.createElement("input");
        input.type = "file";

        return (
            "multiple" in input &&
                typeof File != "undefined" &&
                typeof FormData != "undefined" &&
                typeof (new XMLHttpRequest()).upload != "undefined" );
}

function getIframeContent(iframe){
        //IE may throw an "access is denied" error when attempting to access contentDocument on the iframe in some cases
        try {
            // iframe.contentWindow.document - for IE<7
            var doc = iframe.contentDocument ? iframe.contentDocument: iframe.contentWindow.document, response;

            var innerHTML = doc.body.innerHTML;
            //plain text response may be wrapped in <pre> tag
            if (innerHTML.slice(0, 5).toLowerCase() == "<pre>" && innerHTML.slice(-6).toLowerCase() == "</pre>") {
                innerHTML = doc.body.firstChild.firstChild.nodeValue;
            }
            response = innerHTML;
        } catch(err){
            response = "failure";
        }
        return response;
    }

function fnNoRecords(){
	$("#messageID").val(16);
	addMsgNum('16');
	norecords();
	$("#messageID").val(0);
}
//Added for CR-121 ends here

/*
 * Added for CR_131 to clear form fields
 */
function clearForm(id) {
	    $("#"+id).find(':input').not('.disabled').each(function() {
		    switch(this.type) {
		        case 'password':
		        case 'text':
		        case 'textarea':
		        case 'select-one':
		        case 'select-multiple':
		            $(this).prop('value','');
		            break;
		        case 'checkbox':
		        case 'radio':
		        	$(this).prop('checked',false);
		            break;
		        case 'file':
		        	{
		        	if (!isAjaxUploadSupported())	 //if it is IE9, use supported method and perform clone to clear the file input
			        	$('#'+this.id).replaceWith($('#'+this.id).clone(true));
		        	else
		        		$(this).prop('value','');
		        	break;
		        	}
		    }
		    $(this).tooltip('destroy');
	  });
  	  document.getElementById(id).reset();
}

/*
 * Pulled from Spell Checker for CR_131
 */
function fnCheckSpellnCont(fnToCall) {
	var funcCall = fnToCall + "();"; 
	eval(funcCall);
}