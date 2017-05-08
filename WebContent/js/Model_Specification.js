
//Specifiaction Starts here

function initLoadSpecification()
{
       if (document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1")
   {
	    var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltSpecType",-1);
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

	document.forms[0].hdnModelName.value = document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].text;
	document.forms[0].hdnSelSpecType.value=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
	document.forms[0].action="ModelSpecificationAction.do?method=initLoadSpecification";
	document.forms[0].submit();
}

function insertSpecification()
{

   var spec = document.forms[0];
   var title = trim(spec.modSpecName.value);
   var desc  = trim(spec.modSpecItemDesc.value);
   var item = trim(spec.modSpecItemValue.value);
 
	spec.modSpecName.value=title;
	spec.modSpecItemDesc.value=desc;
	spec.modSpecItemValue.value=item;
	
	if (title.length==0 || title=="")
	   {
	 
			var id = '40';
			hideErrors();
			addMsgNum(id);
		showScrollErrors("modSpecName",-1);
			return false;
	   
		}

	if (desc.length==0 || desc=="")
	   {
	 
			var id = '41';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("modSpecItemDesc",-1);
			return false;
	   
		}

   
	document.forms[0].action="ModelSpecificationAction.do?method=insertSpecification";
	document.forms[0].submit();


}


function searchSpecificationItems()
{

	 if (document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1")
   {
	    var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltSpecType",-1);
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

	document.forms[0].hdnModelName.value = document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].text;
	document.forms[0].hdnSelSpecType.value=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
	document.forms[0].action="ModelSpecificationAction.do?method=fetchSpecificationItems";
	document.forms[0].submit();

}


function initLoadSpecificationItem(specname,seqno)
{
	
	if(document.forms[0].hdnPrevSelModel.value!=document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value){
		
		var id= '207';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("searchButton",-1);
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
	document.forms[0].hdnModelName.value = document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].text
	document.forms[0].hdnSpecName.value = specname;
	document.forms[0].hdnSpecSeqNo.value = seqno;
	document.forms[0].action="ModelSpecificationAction.do?method=initLoadSpecificationItem";
	document.forms[0].submit();

}




function insertSpecificationItem()
{

	var spec = document.forms[0];

	var desc  = trim(spec.modSpecItemDesc.value);
	var item = trim(spec.modSpecItemValue.value);
	
	spec.modSpecItemDesc.value=desc;
	spec.modSpecItemValue.value=item;
	

	
	if (desc.length==0 || desc=="")
	   {
	 
			var id = '41';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("modSpecItemDesc",-1);
			return false;
	   
		}

/*	if (item.length==0 || item=="")
	   {
			var id = '42';
			hideErrors();
			addMsgNum(id);
			showErrors();
			return false;
		}*/
   
	document.forms[0].action="ModelSpecificationAction.do?method=insertSpecificationItem";
	document.forms[0].submit();


}

function modifySpecItem()
{
	var spec = document.forms[0];

	var specchecked;


	
	if(spec.hdnPrevSelModel.value!=spec.modelSeqNo.options[spec.modelSeqNo.selectedIndex].value){
		
		var id= '207';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("searchButton",-1);
		return false;
	}



	if(spec.modSpecItemSeqNo.length!=undefined){

	for(var i = 0 ; i < spec.modSpecItemSeqNo.length ; i++){
		if(spec.modSpecItemSeqNo[i].checked){
			specchecked = true;
			index = i;
			break;
		
		}

	}
}
else{
		if(spec.modSpecItemSeqNo.checked)
			specchecked = true;
		
}
	
	if (!specchecked)
	{
		var id= '43';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("modSpecItemSeqNo",-1);
		return false;
	

	}else{
			var specItemName = "";
			var specItemValue ="";
			if(spec.modSpecItemSeqNo.length!=undefined){
			
				 specItemName = trim(spec.modSpecItemDesc[index].value);
				 specItemValue = trim(spec.modSpecItemValue[index].value);

				if(specItemName.length==0 || specItemName=="" ){
					var id = '41';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("modSpecItemDesc",index);
					specItemName.length=0;
					return false;
		
				}
				
		}else{
				 specItemName = trim(spec.modSpecItemDesc.value);
				 specItemValue  = trim(spec.modSpecItemValue.value);
				if(specItemName.length==0 || specItemName=="" ){
					var id = '41';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("modSpecItemDesc",-1);
					specItemName.length=0;
					return false;
		
				}
				
		}
			
		
			
}	
	
	if(spec.modSpecItemSeqNo.length!=undefined){	
		for(var i = 0 ; i < document.forms[0].modSpecItemSeqNo.length ; i++){

			if(document.forms[0].modSpecItemSeqNo[i].checked){
									
				document.forms[0].hdnSpecItemDesc.value=trim(document.forms[0].modSpecItemDesc[i].value);
				document.forms[0].hdnSpecItemValue.value=trim(document.forms[0].modSpecItemValue[i].value);
				document.forms[0].modSpecItemDesc[i].value=document.forms[0].hdnSpecItemDesc.value;
				document.forms[0].modSpecItemValue[i].value=document.forms[0].hdnSpecItemValue;				
				break;

			}
		}
	}else{

			if(document.forms[0].modSpecItemSeqNo.checked){
				
				document.forms[0].hdnSpecItemDesc.value=document.forms[0].modSpecItemDesc.value;
				document.forms[0].hdnSpecItemValue.value=document.forms[0].modSpecItemValue.value;
				document.forms[0].modSpecItemDesc.value=document.forms[0].hdnSpecItemDesc.value;
				document.forms[0].modSpecItemValue.value=document.forms[0].hdnSpecItemValue;	
			}
	}

document.forms[0].action="ModelSpecificationAction.do?method=updateSpecificationItem";
document.forms[0].submit();

}



function specHomePage()
{

	document.forms[0].action="ModelSpecificationAction.do?method=fetchSpecificationItems";
	document.forms[0].submit();
}

//Added for CR-46 PM&I Spec
function fnLoadModels(){

var specificationForm = document.forms[0];

specificationForm.hdnSelSpecType.value=specificationForm.specTypeNo.options[specificationForm.specTypeNo.selectedIndex].text;
document.forms[0].action="ModelSpecificationAction.do?method=fetchModels";
document.forms[0].submit();
}

/*************** Added for LSDB_CR-64 by ka57588 ***************/
/**************  Starts here ***************/

function previewSpecification(){

	if(document.forms[0].hdnPrevSelModel.value!=document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value){
		
		var id= '207';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("searchButton",-1);
		return false;
	}

	document.forms[0].action="ModelSpecificationAction.do?method=previewSpecificationByPDF";
	document.forms[0].submit();

}

function displayModifiedSpecification(specname , specseqno){

	if(document.forms[0].hdnPrevSelModel.value!=document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value){
		
		var id= '207';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("searchButton",-1);
		return false;
	}
	document.forms[0].hdnSelSpecType.value=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
	document.forms[0].hdnSpecName.value = specname;	
	document.forms[0].hdnSpecSeqNo.value = specseqno; 
	document.forms[0].action="ModelSpecificationAction.do?method=displayModifiedSpecification";
	document.forms[0].submit();

}

function updateSpecification(){
	var spec = document.forms[0];
	var title = trim(spec.modSpecName.value);	
	if (title.length==0 || title=="")
	   {
	 
			var id = '40';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("modSpecName",-1);
			return false;
	   
		}
	document.forms[0].action="ModelSpecificationAction.do?method=updateSpecification";
	document.forms[0].submit();
}

function deleteSpecification(specseqno){
		
	document.forms[0].hdnSpecSeqNo.value = specseqno; 
	if(document.forms[0].hdnPrevSelModel.value!=document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value){
		
		var id= '207';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("searchButton",-1);
		return false;
	}

	var del=confirm("Are you sure you want to delete the entire section ?");
	if(del){
		document.forms[0].action="ModelSpecificationAction.do?method=deleteSpecification";
		document.forms[0].submit();
	}
}

function deleteSpecificationItem(){

	var spec = document.forms[0];

	var specchecked;


	
	if(spec.hdnPrevSelModel.value!=spec.modelSeqNo.options[spec.modelSeqNo.selectedIndex].value){
		
		var id= '207';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("searchButton",-1);
		return false;
	}



	if(spec.modSpecItemSeqNo.length!=undefined){

		for(var i = 0 ; i < spec.modSpecItemSeqNo.length ; i++){
			if(spec.modSpecItemSeqNo[i].checked){
				specchecked = true;
				break;
				
				}

			}
	}
	else{
		if(spec.modSpecItemSeqNo.checked)
			specchecked = true;
				
	}
	
	if (!specchecked)
	{
		var id= '44';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("modSpecItemSeqNo",-1);
		return false;
	}
	var del=confirm("Are you sure you want to delete the selected description and value ?");
	if(del){
		document.forms[0].action="ModelSpecificationAction.do?method=deleteSpecificationItem";
		document.forms[0].submit();
	}

}

/**************  Ends here  ***************/