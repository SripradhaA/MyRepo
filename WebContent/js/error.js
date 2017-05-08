/*************************************************************************************************
*				JAVASCRIPT ERROR DISPLAY
**************************************************************************************************/

var __errorArray = new Array();

/*
*This acts as a flag to show the alert messages with error id.
*If the value of the flag is true,the alert message is shown without the 
*the errorID.
*/
var flagForMsgID=true;
/*
*	Name: setError
*	Purpose: Visibly show error on html element (in red)
*	
*/
function setError(obj) {
	eval(obj+".style.backgroundColor = '#FFFFFF'");
	//eval(obj+".style.border = '1px #FF0000 dashed'");
}

/*
*	Name: unsetError
*	Purpose: Set the display back to original condition
*	Notes: Can send an optional parameter of color to restore, this was a requirement for the Phase I porting team.
*/
function unsetError(obj, cColor) {
	if (cColor == null) cColor = "#FFFFFF";
	eval(obj+".style.backgroundColor = '"+cColor+"'");
	//eval(obj+".style.border = '1px black solid'");
}

/*
*	Name: hideErrors
*	Purpose: Clears all the page alerts
*/
function hideErrors() {
	$(".alert").alert('close');
}

/*
*	Name: showErrors
*	Purpose: Show error display with success background
*/

function showErrors() {
	
	var arr = getErrorArray();
	msg = (arr[arr.length-1][0].replace(/<br>/g, '')).replace(/&ndash;/g, '-');
	$(".alert").alert('close');
	$("#errorlayer").addClass("col-sm-4 col-sm-offset-4");
	$("#errorlayer").append('<div class="alert alert-message success fade in"><span class="glyphicon glyphicon-ok" aria-hidden="true"> </span><button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button> ' + msg + '</div>');
	//Reset Array
	__errorArray = new Array();
		
}

/*
*	Name: addError
*	Purpose: Add error to the display
*/
function addError(myerror, success) {
	x = __errorArray.length;
	
	  var error_string=myerror.split("-");	  
	  __errorArray[x] = new Array(2);
	  if(flagForMsgID){	  
	  __errorArray[x][0] = error_string[1];	  	  	 
	  }else{	
	__errorArray[x][0] = myerror;
	}
	__errorArray[x][1] = success;
}

function addMessage(myerror,success) {
	addError(myerror,success);
}

//Added for LSDB_CR_71 - Component group (-) handling

/*
*	Name: addErrorCommon
*	Purpose: Add error to the display with (-)
*/
function addErrorCommon(myerror, success) {
	x = __errorArray.length;
	__errorArray[x] = new Array(2);
	__errorArray[x][0] = myerror;
	__errorArray[x][1] = success;
}
/*
*	Name: addMessageCommon
*	Purpose: Add error to the display with (-)
*/

function addMessageCommon(myerror,success) {
	addErrorCommon(myerror,success);
}

//Added for CR-121
function getErrorArray(){
	return __errorArray;
}


/* Ability to Add Error Alerts to elements */
function showScrollErrors(element,index){
	
	var selector="";
	var field="";
	var selectorGroup="";
	var multipleGroup=0;

	//Checking if the element is linked to select2
	if ($('#select2-'+element+'-container').length > 0) {
		selector ="#select2-"+element+"-container";
		field="#"+element;
	}
	//Checking if the element is linked to name and multiple group
	else if(!($("[name='"+element+"']").val()==undefined)){
		if(document.getElementsByName(element).length>1){
			selector ="[name='"+element+"']:eq("+index+")";
		 	multipleGroup =1;
		 	selectorGroup="[name='"+element+"']";
		}else{
			selector ="[name='"+element+"']";
		}
		field=selector;
	}
	//Checking if the element is linked to id
	else if($("#"+element).length > 0){
		selector ="#"+element;
		field=selector;
	}
	else if($('[data-error="'+element+'"]').length > 0) {
		var modalid = "";//when there is modal that is open
		if ($('.modal').hasClass('in'))
			modalid = $('.modal.in ').prop('id');
		selector = $('#'+modalid+' [data-error="'+element+'"]:last');
		field=selector;
	}
	else {
		selector ="#errorlayer";
		field=selector;
	}
	
	if (!$(selector).hasClass('validated')) //If the class is already validated, do not remove the alerts
		clearAlerts();
	
	if (selector == "#errorlayer")	{		
		adderrorlayer();		
	} else {
		
		var arr = getErrorArray();
		msg = (arr[arr.length-1][0].replace(/<br>/g, '')).replace(/&ndash;/g, '-');

		if ($(field).hasClass("partOfColFormField"))
			$(selector).closest(".multipleFormFields").addClass("has-error has-feedback");
		else
			$(selector).closest(".form-group").addClass("has-error has-feedback");
		
		$(selector).prop('title', '<span class="glyphicon glyphicon-remove"></span> ' + msg);
		
		$(selector).tooltip({
			template : '<div role="tooltip" class="tooltip removeOp"><div class="tooltip-inner noWidth alert-message error"></div></div>',
			html : true,
			container: "body"
		});
		
		$(selector).addClass('validated');
		
		$(selector).tooltip('fixTitle').tooltip('show');
		
	}
	
	if (!$('.modal').hasClass('in'))
		jQuery('html,body').animate({scrollTop: $(selector).offset().top-100}, 1000);	

	//Reset Array
	__errorArray = new Array();
}

/* Ability to Add Error Alerts to page instead of elements */
function pagesuccess() {
	var arr = getErrorArray();
	msg = (arr[arr.length-1][0].replace(/<br>/g, '')).replace(/&ndash;/g, '-');
	$(".alert").alert('close');
	$("#errorlayer").addClass("col-sm-4 col-sm-offset-4");
	$("#errorlayer").append('<div class="alert alert-message success fade in"><span class="glyphicon glyphicon-ok" aria-hidden="true"> </span><button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button> ' + msg + '</div>');
}

function norecords() {
	var arr = getErrorArray();
	msg = (arr[arr.length-1][0].replace(/<br>/g, '')).replace(/&ndash;/g, '-');
	$(".alert").alert('close');
	$("#errorlayer").addClass("col-sm-4 col-sm-offset-4");
	$("#errorlayer").append('<div class="alert alert-message error fade in"><span class="glyphicon glyphicon-remove" aria-hidden="true"> </span><button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button> ' + msg + '</div>');
}

function adderrorlayer() {
	var arr = getErrorArray();
	msg = (arr[arr.length-1][0].replace(/<br>/g, '')).replace(/&ndash;/g, '-');
	$(".alert").alert('close');
	$("#errorlayer").addClass("col-sm-4 col-sm-offset-4");
	$("#errorlayer").append('<div class="alert alert-message error fade in"><span class="glyphicon glyphicon-remove" aria-hidden="true"> </span><button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button> ' + msg + '</div>');
}

/*
 * Added for CR_131 to clear alerts on the page
 */
function clearAlerts() {
	$(".validated").tooltip('destroy');
	$(".form-group").removeClass("has-success has-error has-feedback");
	$(".multipleFormFields").removeClass("has-success has-error has-feedback");
	$(".validated").removeClass('validated');
	if ($('.alert').length > 0) {
		$('.alert').on('closed.bs.alert', function () {
			if ($('.validated').length > 0)
				$('.removeOp').tooltip('show');
		 });
		$(".alert").alert('close');
	}
}