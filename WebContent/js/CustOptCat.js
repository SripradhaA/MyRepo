function fnLoadModels(){
//Added for CR_118
document.forms[0].target = "";
//Added for CR_118 Ends
document.forms[0].action="CustOptCatAction.do?method=fetchModels";
document.forms[0].submit();


}

function showCOC(){
	$("coc").modal({
	height:550,
	width:450,
	escClose:true,
	onOpen: function (dialog) {
		dialog.overlay.fadeIn('fast', function () {
			dialog.data.hide();
			dialog.container.fadeIn('fast', function () {
				dialog.data.slideDown('fast');
			});
		});
	},
	onClose: function (dialog) {
		dialog.data.fadeOut('fast', function () {
			dialog.container.hide('fast', function () {
				dialog.overlay.slideUp('fast', function () {
					$.modal.close();
				});
			});
		}); 
	}
	});	
}

/*
*	Name: openCOC
*	Purpose: Used to open the Customer Option Catalog Report
*	Comments: Added For CR_100
*/

function openCOC(modelName,modelSeqNo,specType,SpecTypeNo,saveCOC){
	//$.modal.close();
	//Updated for CR-118
	if(saveCOC==1){
		if (typeof(document.forms[0].hdnDispInCOC) !== "undefined"){
			if(document.forms[0].hdnDispInCOC.length != undefined){
				for(var i=0;i<document.forms[0].hdnDispInCOC.length;i++){
					document.forms[0].dispInCOC[i].value=document.forms[0].hdnDispInCOC[i].value;
				}
				}else{
					document.forms[0].dispInCOC.value=document.forms[0].hdnDispInCOC.value;
				}
			document.forms[0].changeInDispInCOCSection.value = 0;	
	    }
	}
	
	var URL="CustomerOptionCatalog.do?method=viewCustomerOptionCatalog&modelName="+modelName+"&modelSeqNo="+modelSeqNo+"&selectedSpecType="+specType+"&SpecTypeNo="+SpecTypeNo+"&saveCOC="+saveCOC;
	document.forms[0].action=URL;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	/*Added for CR-118 QA-Fix
	document.forms[0].target = "_self";	  
	window.location = "CustOptCatAction.do?method=initLoad";*/
}

function showCustomerOptionCatalog(saveCOC){

	var formObject = document.forms[0];
	var modelName=formObject.modelSeqNo.options[formObject.modelSeqNo.selectedIndex].text;
	var modelSeqNo = formObject.modelSeqNo.options[formObject.modelSeqNo.options.selectedIndex].value;
	var specType=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
	var SpecTypeNo =document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value;
	

	if (formObject.specTypeNo.options[formObject.specTypeNo.selectedIndex].value =="-1")
   {
	    var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("selectedCust",-1);
		return false;
   
	}
	
	
	
	if(formObject.modelSeqNo.options[formObject.modelSeqNo.options.selectedIndex].value=="-1")
	{

            var id = '19';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("selectedModel",-1);
			return false;

     }
      
	//Removed the Alert for CR_100 to show instructions as an image
	//alert("You are required to add the header and footer to the document you are about to generate. Please click on \"Catalog Header and Footer Instruction\". follow instruction.");
	
	//Added for CR_118
	if(saveCOC == 1){
		if ((specType != document.forms[0].hdnSelSpecType.value && document.forms[0].hdnSelSpecType.value != "") 
			|| (modelName != document.forms[0].modelName.value && document.forms[0].modelName.value != "")){
		      	var id = '207';
		        hideErrors();
		        addMsgNum(id);
		        showScrollErrors("searchButton",-1);
		        return false;
		}
	}
	//Commented for CR_118 Fixes
	/*
	if(document.forms[0].changeInDispInCOCSection.value == 1){
		if(confirm('You have changed the "Edit in Component group in COC" section,\nclick OK to save and generate the COC report')){
			openCOC(modelName,modelSeqNo,specType,SpecTypeNo,1);
		}
	}else{
		openCOC(modelName,modelSeqNo,specType,SpecTypeNo,0);
	}
	* */
	openCOC(modelName,modelSeqNo,specType,SpecTypeNo,saveCOC);
}

//Added for CR_118
function fnEditCompGrpInCOC(){
	var formObject = document.forms[0];
	var modelName=formObject.modelSeqNo.options[formObject.modelSeqNo.selectedIndex].text;
	var modelSeqNo = formObject.modelSeqNo.options[formObject.modelSeqNo.options.selectedIndex].value;
	var specType=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
	var SpecTypeNo =document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value;

	if (formObject.specTypeNo.options[formObject.specTypeNo.selectedIndex].value =="-1")
   {
	    var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("selectedCust",-1);
		return false;
   
	}
	
	
	
	if(formObject.modelSeqNo.options[formObject.modelSeqNo.options.selectedIndex].value=="-1")
	{

            var id = '19';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("selectedModel",-1);
			return false;

      }
    
    //Added for CR_118
	document.forms[0].target = "";
	//Added for CR_118 Ends 
    document.forms[0].action="CustOptCatAction.do?method=editCompGroupInCOC&modelName="+modelName+"&modelSeqNo="+modelSeqNo+"&selectedSpecType="+specType+"&SpecTypeNo="+SpecTypeNo;
	document.forms[0].submit();
     
               
}
/*
function fnUpdateCompGrpsInCOC(){
	var formObject = document.forms[0];
	var modelName=formObject.modelSeqNo.options[formObject.modelSeqNo.selectedIndex].text;
	var modelSeqNo = formObject.modelSeqNo.options[formObject.modelSeqNo.options.selectedIndex].value;
	var specType=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
	var SpecTypeNo =document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value;

	if (formObject.specTypeNo.options[formObject.specTypeNo.selectedIndex].value =="-1")
   {
	    var id = '2';
		hideErrors();
		addMsgNum(id);
		showErrors();
		return false;
   
	}
	
	
	
	if(formObject.modelSeqNo.options[formObject.modelSeqNo.options.selectedIndex].value=="-1")
	{

            var id = '19';
			hideErrors();
			addMsgNum(id);
			showErrors();
			return false;

      }
   
	//Added for CR_118
	document.forms[0].target = "";
	//Added for CR_118 Ends   
    document.forms[0].action="CustOptCatAction.do?method=updateCompGroupsInCOC&modelName="+modelName+"&modelSeqNo="+modelSeqNo+"&selectedSpecType="+specType+"&SpecTypeNo="+SpecTypeNo;
	document.forms[0].submit();
}
*/

//Uncommented for CR-131
$(document).ready(function() {
	var checkForChange =false;
	$(".dispInCOCCheckBox").change(function(index){
		
			$(".dispInCOCCheckBox").each(function(i){
				if (this.checked) {
											
	        			if(document.forms[0].hdnDispInCOC.length != undefined){
	        				if(document.forms[0].dispInCOC[i].value == "N"){
	        					checkForChange =true;
	        					
	        				}
	        				document.forms[0].hdnDispInCOC[i].value = "Y";
	        			}else{
	        				if(document.forms[0].dispInCOC.value == "N"){
	        					checkForChange =true;
	        				}
	        				document.forms[0].hdnDispInCOC.value = "Y";
	        			}
    			}else{
	    				if(document.forms[0].hdnDispInCOC.length != undefined){
	    					if(document.forms[0].dispInCOC[i].value == "Y"){
	        					checkForChange =true;
	        				}
	        				document.forms[0].hdnDispInCOC[i].value = "N";
	        			}else{
	        				if(document.forms[0].dispInCOC.value == "Y"){
	        					checkForChange =true;
	        				}
	        				document.forms[0].hdnDispInCOC.value = "N";
	        			}
    			}

			});
		
		if(checkForChange==true){
			document.forms[0].changeInDispInCOCSection.value =1;
			checkForChange=false;
		}else{
			document.forms[0].changeInDispInCOCSection.value =0;
		} 
		
	});

	
});


//Added for CR_118 Ends