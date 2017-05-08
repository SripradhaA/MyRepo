	


function fnSearchOrders()
{
	var index=-1;
	var MasterSpecForm = document.forms[0];  
	
	
	if (MasterSpecForm.specTypeNo.options[MasterSpecForm.specTypeNo.selectedIndex].value =="-1")
   {
	    var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("selectSpecType",index);
		return false;
   
	}
	
	
	
	if(MasterSpecForm.modelSeqNo.options[MasterSpecForm.modelSeqNo.options.selectedIndex].value=="-1")
	{

            var id = '19';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("selectModel",index);
			return false;

      }
	
	
	 var len  = document.all["custSeqNos"].options.length;
	 var customerNames="";
	 for(i=0;i<len;i++)
	 {
	  if(document.all["custSeqNos"].options[i].selected==true)
	   {
	    if(customerNames=="")
	     {
	       customerNames=document.all["custSeqNos"].options[i].text;
     	  }
     	 else
     	 {
	       customerNames=customerNames+", "+document.all["custSeqNos"].options[i].text;
	      }
	      
	   }
	 }  

	 var leng  = document.all["custSeqNos"].options.length;
	 var customerSeqs="";
	 for(i=0;i<leng;i++)
	 {
	  if(document.all["custSeqNos"].options[i].selected==true)
	   {
	    if(customerSeqs=="")
	     {
	       customerSeqs=document.all["custSeqNos"].options[i].value;
     	  }
     	 else
     	 {
	       customerSeqs=customerSeqs+", "+document.all["custSeqNos"].options[i].value;
	      }
	      
	   }
	 }  
     
    document.forms[0].customerSeq.value=customerSeqs;
    document.forms[0].hdnSelectedCustomers.value=customerNames;
	document.forms[0].hdnSelectedMdlNames.value=MasterSpecForm.modelSeqNo.options[MasterSpecForm.modelSeqNo.selectedIndex].text;
    document.forms[0].hdnSelSpecType.value=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;		
	
	document.forms[0].action="MasterSpecByMdlAction.do?method=fetchOrdersForMasterSpecReport";
	document.forms[0].submit();
}


function fnViewSpec(button,action)
{
	index=-1;
	var URL = "";
	var formObject = document.forms[0];

	if(button ==1) {
	
		if(formObject.orderkey!=undefined){
		
			formObject.hnOrderKey.value="";
		}
	
	}
	//Added For CR_81
	if (formObject.specTypeNo.options[formObject.specTypeNo.selectedIndex].value =="-1")
   {
	    var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("selectSpecType",index);
		return false;
   
	}
	//Ends here	
	if(formObject.modelSeqNo.options[formObject.modelSeqNo.options.selectedIndex].value=="-1")
	{
            var id = '19';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("selectModel",index);
			return false;
    }
	  
	  if(formObject.orderkey!=undefined){
		  
		  if(button==2){
			 
			  if(trim(formObject.modelSeqNo.options[formObject.modelSeqNo.options.selectedIndex].text) != trim(formObject.hdnSelectedMdlNames.value)){

				var id = '207';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("selectModel",index);
				return false;
			  }
		 

		  if(formObject.orderkey.length!=undefined){
		  
				var orderkeys="";

				var blCheck = false;

				
				for(var i=0; i < formObject.orderkey.length; i++){
				
					
					if(formObject.orderkey[i].checked){
						
						if(orderkeys==""){
							
							orderkeys = formObject.orderkey[i].value;
							
						}
						
						else{
							orderkeys +=","+formObject.orderkey[i].value;
						}
						
						blCheck = true;
					
					}
				}
		   }else{
		  
				if(formObject.orderkey.checked){
					orderkeys = formObject.orderkey.value;
					blCheck = true;
				}
			}
	
				if(!blCheck){
				
					var id = '793';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("orderkey",index);
					return false;
				}

				formObject.hnOrderKey.value=orderkeys;
				
	  }
	}

		var modelName=formObject.modelSeqNo.options[formObject.modelSeqNo.selectedIndex].text;
		
		var modelSeqNo = formObject.modelSeqNo.options[formObject.modelSeqNo.options.selectedIndex].value
			
		//formObject.hdnSelSpecType.value=formObject.specTypeNo.options[formObject.specTypeNo.selectedIndex].text;

        var specType=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
		//Added lines code For CR_101 
		if(action =="report"){
			URL="MasterSpecByMdlReport.do?method=viewMasterSpecByModel&modelName="+modelName+"&modelSeqNo="+modelSeqNo+"&orderkeys="+formObject.hnOrderKey.value+"&selectedSpecType="+specType;
			 window.open(URL,'ViewSpec',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH=1280,height=600"); 
			 }
		else if (action =="excel"){
			document.forms[0].action="MasterSpecByMdlExcel.do?method=viewMasterSpecByModel&modelName="+modelName+"&modelSeqNo="+modelSeqNo+"&orderkeys="+formObject.hnOrderKey.value+"&selectedSpecType="+specType+"&action="+action;//Updated for CR-130 
			document.forms[0].submit();//Added for CR-130 
			// window.open(URL,'ViewSpec',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH=1280,height=600"); Commented for CR-130 
			 }
		//Added for CR_101
		else{
				document.forms[0].action="MasterSpecByMdlReport.do?method=excelToDoors&modelName="+modelName+"&modelSeqNo="+modelSeqNo+"&orderkeys="+formObject.hnOrderKey.value+"&selectedSpecType="+specType;
				document.forms[0].submit();
				}
		//Ends CR_101
		//Modified Screen Width For CR.No.77 by RR68151
		
		
}



//** * CR 90 Adding 'View Master Spec' link bottom of the section pages */

function fnViewSpecForAll()
{
	index=-1;
	var URL = "";
	var formObject = document.forms[0];

	if (formObject.specTypeSeqNo.value =="-1")
   {
	    var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("selectSpecType",index);
		return false;
   
	}
	//Ends here	
	if(formObject.modelSeqNo.value=="-1")
	{
            var id = '19';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("selectModel",index);
			return false;
    }
	  
		var modelName=formObject.modelName.value;		
		var modelSeqNo = formObject.modelSeqNo.value
        var specType=document.forms[0].specTypeName.value;

		URL="MasterSpecByMdlReport.do?method=viewMasterSpecByModel&modelName="+modelName+"&modelSeqNo="+modelSeqNo+"&orderkeys="+""+"&selectedSpecType="+specType;
		
		window.open(URL,'ViewSpec',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH=1280,height=600"); 
		
}
//** * CR 90 Adding 'View Master Spec' link bottom of the section pages End */




//Added for CR-46 PM&I Spec
function fnLoadModelsAndCustomers(){

	var masterSpecForm = document.forms[0];
	masterSpecForm.hdnSelSpecType.value=masterSpecForm.specTypeNo.options[masterSpecForm.specTypeNo.selectedIndex].text;
	document.forms[0].action="MasterSpecByMdlAction.do?method=fetchModelsAndCustomers";
	document.forms[0].submit();
}

//Added for LSDB_CR-77
function showCustomerOptionCatalog(){
	index=-1;
	var formObject = document.forms[0];
	var modelName=formObject.modelSeqNo.options[formObject.modelSeqNo.selectedIndex].text;
	var modelSeqNo = formObject.modelSeqNo.options[formObject.modelSeqNo.options.selectedIndex].value;
	var specType=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;

	if (formObject.specTypeNo.options[formObject.specTypeNo.selectedIndex].value =="-1")
   {
	    var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("selectSpecType",index);
		return false;
   
	}
	
	
	
	if(formObject.modelSeqNo.options[formObject.modelSeqNo.options.selectedIndex].value=="-1")
	{

            var id = '19';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("selectModel",index);
			return false;

      }
	//Removed the Alert for CR_100 to show instructions as an image
	//alert("You are required to add the header and footer to the document you are about to generate. Please click on \"Catalog Header and Footer Instruction\". follow instruction.");

    //Modified For CR-114 	
	openCOC(modelName,modelSeqNo,specType);
}



function fnViewReport(button,action)
{
	index=-1;
	var URL = "";
	var formObject = document.forms[0];

	if(button ==1) {
	
		if(formObject.orderkey!=undefined){
		
			formObject.hnOrderKey.value="";
		}
	
	}

	if (formObject.specTypeNo.options[formObject.specTypeNo.selectedIndex].value =="-1")
   {
	    var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("selectSpecType",index);
		return false;
   
	}
	
	if(formObject.modelSeqNo.options[formObject.modelSeqNo.options.selectedIndex].value=="-1")
	{
            var id = '19';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("selectModel",index);
			return false;
    }
	  
	  if(formObject.orderkey!=undefined){
		  
		  if(button==2){
			 
			  if(trim(formObject.modelSeqNo.options[formObject.modelSeqNo.options.selectedIndex].text) != trim(formObject.hdnSelectedMdlNames.value)){

				var id = '207';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("selectModel",index);
				return false;
			  }
		 

		  if(formObject.orderkey.length!=undefined){
		  
				var orderkeys="";

				var blCheck = false;

				
				for(var i=0; i < formObject.orderkey.length; i++){
				
					
					if(formObject.orderkey[i].checked){
						
						if(orderkeys==""){
							
							orderkeys = formObject.orderkey[i].value;
							
						}
						
						else{
							orderkeys +=","+formObject.orderkey[i].value;
						}
						
						blCheck = true;
					
					}
				}
		   }else{
		  
				if(formObject.orderkey.checked){
					orderkeys = formObject.orderkey.value;
					blCheck = true;
				}
			}
	
				if(!blCheck){
				
					var id = '793';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("orderkey",index);
					return false;
				}

				formObject.hnOrderKey.value=orderkeys;
				
	  }
	}

		var modelName=formObject.modelSeqNo.options[formObject.modelSeqNo.selectedIndex].text;
		
		var modelSeqNo = formObject.modelSeqNo.options[formObject.modelSeqNo.options.selectedIndex].value
			
		//formObject.hdnSelSpecType.value=formObject.specTypeNo.options[formObject.specTypeNo.selectedIndex].text;

        var specType=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;

	//Commented for CR-130
		/*if(action =="chrgrppt")
			URL="ViewCharGrpByMdlReport.do?method=viewCharGrpRptbyModel&modelName="+modelName+"&modelSeqNo="+modelSeqNo+"&selectedSpecType="+specType;
		else
			URL="ViewCharGrpByMdlExcel.do?method=viewCharGrpRptbyModel&modelName="+modelName+"&modelSeqNo="+modelSeqNo+"&selectedSpecType="+specType;
		
		//Modified Screen Width For CR.No.77 by RR68151
		 window.open(URL,'ViewChrGrp',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH=1280,height=600"); */
	//Added for CR-130
		if(action =="chrgrppt"){
			URL="ViewCharGrpByMdlReport.do?method=viewCharGrpRptbyModel&modelName="+modelName+"&modelSeqNo="+modelSeqNo+"&selectedSpecType="+specType;
		 window.open(URL,'ViewChrGrp',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH=1280,height=600"); 
		}else{
			document.forms[0].action= "ViewCharGrpByMdlExcel.do?method=viewCharGrpRptbyModelExcel&modelName="+modelName+"&modelSeqNo="+modelSeqNo+"&selectedSpecType="+specType;
			document.forms[0].submit();	
		}
		
}

/*
*	Name: showCOC
*	Purpose: Used to display the instructions page for Header and Footer
*	Comments: Added For CR_100
*/

function showCOC(){
	$("#headerInstructions").modal({
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

function openCOC(modelName,modelSeqNo,specType){
	//Edited for CR_114
	//$.modal.close();
	//Edited for CR_114 Ends
	var URL="CustomerOptionCatalog.do?method=viewCustomerOptionCatalog&modelName="+modelName+"&modelSeqNo="+modelSeqNo+"&selectedSpecType="+specType;
	window.open(URL,'ViewSpec',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH="+screen.width+",height=600"); 
}

//Added for CR-114
function exportSpectoDoors(modelName,modelSeqNo,specType,orderKey,orderNo,dataLocFlag){
	document.forms[0].action="MasterSpecByMdlReport.do?method=excelSpecToDoors&modelName="+modelName+"&modelSeqNo="+modelSeqNo+"&selectedSpecType="+specType+"&orderKey="+orderKey+"&orderNo="+orderNo+"&dataLocFlag="+dataLocFlag;
	document.forms[0].submit();
}
//Added for CR-114 ends here