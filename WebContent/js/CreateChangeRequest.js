function fnCreateChangeRequestID(){
	
	var req = document.forms[0];
	var reqDesc = trim(req.shortDescription.value);
	
	if(reqDesc=="" || reqDesc.length==0){
		var id = '825';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("shortDescription",-1);
		return false;
	}

	if(reqDesc.length > 2000 ){
		var id = '827';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("shortDescription",-1);
		return false;
	}
	
	document.forms[0].action="CreateRequestID.do?method=createRequestID";
	document.forms[0].submit();

}


function loadSection(){

}

function loadSubSection(){

}

function loadAvailableCompGroups(){

}

function loadAvailableComponents(){

}

function loadSelectedComponent(){

}

function saveComponentGroup(){

}
