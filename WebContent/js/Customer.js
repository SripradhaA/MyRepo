
/*
*	Name: fnSearchCustomer
*	Purpose: Used to Search Customers based on the SpecificationType and CustomerType.
*	Modified for LSDB_CR-46
*	on 28-Aug-08 by ps57222
*/

function fnSearchCustomer(){

    var isChecked = false;
	var custForm = document.forms[0];
	var index;
	var cnt = custForm.custTypeSeqNo.length;

	if (document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1")
	  {
			var id = '2';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("sltSpecType",-1);
			return false;
	   
		}

	//Updated for CR_84
    if(document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value != "2"){
	for(var i = 0;i < cnt;i++){
		if(custForm.custTypeSeqNo[i].checked){
		 isChecked = true;
		 index=i;
		  
          custForm.hdncustomertypeseqNo.value=custForm.custTypeSeqNo[index].value;
		 
		 break;
		}
	}
	
	}else if(document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value == "2"){
	  
		document.forms[0].custTypeSeqNo[0].checked= true;
		document.forms[0].custTypeSeqNo[0].disabled= true;
		document.forms[0].custTypeSeqNo[1].disabled= true;
	    isChecked = true;
	  }

	if(isChecked == false){
		var id= '3';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("custTypeSeqNo",-1);
		return false;
	  
	}
    else{
		document.forms[0].hdSelectedSpecType.value=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
		document.forms[0].action="CustAction.do?method=fetchCustomers";
		document.forms[0].submit();
		}
	}

/*
*	Name: fnAddCustomer
*	Purpose: Used to Add Customer into LSDB System.
*	Modified for LSDB_CR-46
*	on 28-Aug-08 by ps57222
*/

function fnAddCustomer()
{
	
	var isChecked = false;
	var custForm = document.forms[0];
	var cnt = custForm.custTypeSeqNo.length;

	if (document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1")
	  {
			var id = '2';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("sltSpecType",-1);
			return false;
	   
		}
	//Updated for CR_84
	if(document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value!="2"){
		
	for(var i = 0;i < cnt;i++){
		if(custForm.custTypeSeqNo[i].checked){
			isChecked = true;
		 break;
		}
	}
	}else if(document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value=="2"){
  		
	document.forms[0].custTypeSeqNo[0].checked= true;
	document.forms[0].custTypeSeqNo[0].disabled= true;
	document.forms[0].custTypeSeqNo[1].disabled= true;
   isChecked = true;
  }
	
	if(isChecked == false){
		var id= '3';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("custTypeSeqNo",-1);
		return false;
	  
	}
	else{
				if(trim(custForm.custName.value).length==0 || trim(custForm.custName.value)=="" ){
					var id = '156';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("custName",-1);
					return false;
				}
			}

                  if(trim(custForm.custDesc.value)!="")
                  {
	                  if(trim(custForm.custDesc.value).length>2000 )
	                  {
								var id = '517';
								hideErrors();
								addMsgNum(id);
								showScrollErrors("custDesc",-1);
								return false;
					  }   
                 }
   

//Added for CR_106 File Extension Validations JG101007
var toLoadFileName=document.forms[0].theFile.value;

	toLoadFileName=toLoadFileName.substring(0,toLoadFileName.indexOf('_'));
	var fileName=document.forms[0].theFile.value;

	
		
			/*if (fileName=="" || fileName.length==0)
			{
							var id='780';
							hideErrors();
							addMsgNum(id);
							showErrors();
							document.forms[0].theFile.value="";
							return false;
			}
				if(fileName==""){
						return false;
					}*/
					
					var code=fileName.split("\\");
					var temp="";
					for (j=1;j<code.length;j++)
						{
							temp=code[j];
						}
					if(temp!="")
					{
					var checkParam=temp.substring(temp.indexOf('.')+1,temp.length);
					
					var flag = false;
					var arr = new Array();
					arr[0] = "gif";
					arr[1] = "jpeg";
					arr[2] = "bmp";
					arr[3] = "tiff";
					arr[4] = "jpg";
					arr[5] = "tif";
					arr[6] = "png";
					
					
					for(var i = 0 ; i < arr.length; i++){
						if(trim(checkParam.toLowerCase())==arr[i]){
								flag = true;
						}
					}
					
					if(!flag){
					
						var id='1039';//Msg ID Edited for CR-131
						hideErrors();
						addMsgNum(id);
						showScrollErrors("theFile",-1);
						return false;
					}

	}
	 //Added For CR_84
    document.forms[0].hdSelectedSpecType.value=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;     
   	document.forms[0].action="CustAction.do?method=insertCustomer";
	document.forms[0].submit();
}

function fnModifyCustomer(){

    var custForm = document.forms[0];
	var index;
	custSeqNochecked = false;
	 var isChecked = false;
	
	
	if(document.forms[0].hdPreSelectedSpecType.value!=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value)
	{
		var id = '207';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("searchButton",-1);
		return false;
	}
	
		if((custForm.custTypeSeqNo[0].checked)==false)
		{
		if((custForm.custTypeSeqNo[1].checked)==false){
		var id= '3';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("custTypeSeqNo",-1);
		return false;
	  }
	}
		if(custForm.custTypeSeqNo[0].checked){
 				
 				if(custForm.hdncustomertypeseqNo.value!= custForm.custTypeSeqNo[0].value)
			  	{
			         var id = '207';
			         hideErrors();
			         addMsgNum(id);
			         showScrollErrors("searchButton",-1);               
			         return false;
                }
	
			}
			else if(custForm.custTypeSeqNo[1].checked){
	
				if(custForm.hdncustomertypeseqNo.value != custForm.custTypeSeqNo[1].value){
	
					var id= '207';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("searchButton",-1);
					return false;

				}

			}
			
			
			
			
	if(custForm.customerSeqNo.length>0){
	      var cnt = custForm.customerSeqNo.length;
		  for(var i=0;i<cnt;i++){
			   if(custForm.customerSeqNo[i].checked){
						custSeqNochecked = true;
						index = i;
						if($("#imageSeqNo"+index).length)	{
						
							document.forms[0].hdnImageSeqNo.value=$("#imageSeqNo"+index).val();
							
						}
						break;
			   }
		  }
	}
	else{
			if(custForm.customerSeqNo.checked){
				custSeqNochecked = true;
				document.forms[0].hdnImageSeqNo.value=document.forms[0].imageSeqNo.value;
		
			}
	     }

	if (!custSeqNochecked)
	{
		var id= '158';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("customerSeqNo",-1);
		return false;
	

	}else{
			
			if(custForm.customerSeqNo.length>0){
			
			
				if(trim(custForm.customerName[index].value).length==0 || trim(custForm.customerName[index].value)=="" ){
					var id = '156';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("customerName",index);
					return false;
				}
			if(trim(custForm.customerDesc[index].value)!="")
                  {
		                  if(trim(custForm.customerDesc[index].value).length>2000 )
		                  {
							var id = '517';
							hideErrors();
							addMsgNum(id);
							showScrollErrors("customerDesc",index);
							return false;
						  }   
                 }
			}else{
						if(trim(custForm.customerName.value).length==0 || trim(custForm.customerName.value)=="" )
						{
							var id = '156';
							hideErrors();
							addMsgNum(id);
							showScrollErrors("customerName",-1);
							return false;
						}
			          if(trim(custForm.customerDesc.value)!="")
                     {
		                  if(trim(custForm.customerDesc.value).length>2000 )
		                  {
								var id = '517';
								hideErrors();
								addMsgNum(id);
								showScrollErrors("customerDesc",-1);
								return false;
						  }   
                     }
			
			   } 
			
         
         
         //Added for CR_106 File Extension Validations JG101007
var toLoadFileName=document.forms[0].theModifyFile.value;

	toLoadFileName=toLoadFileName.substring(0,toLoadFileName.indexOf('_'));
	var fileName=document.forms[0].theModifyFile.value;

	
		/*
			if (fileName=="" || fileName.length==0)
			{
							var id='931';
							hideErrors();
							addMsgNum(id);
							showErrors();
							document.forms[0].theModifyFile.value="";
							return false;
			}
				if(fileName==""){
						return false;
					}*/
					if(fileName!="" ||fileName!=null ){
					var code=fileName.split("\\");
					var temp="";
					for (j=1;j<code.length;j++)
						{
							temp=code[j];
						}
						}
						
					if(temp!="")
					{
					var checkParam=temp.substring(temp.indexOf('.')+1,temp.length);
					
					var flag = false;
					var arr = new Array();
					arr[0] = "gif";
					arr[1] = "jpeg";
					arr[2] = "bmp";
					arr[3] = "tiff";
					arr[4] = "jpg";
					arr[5] = "tif";
					arr[6] = "png";
					
					
					for(var i = 0 ; i < arr.length; i++){
						if(trim(checkParam.toLowerCase())==arr[i]){
								flag = true;
						}
					}
					
					if(!flag){
					
						var id='1039';//Msg Id edited for CR-131
						hideErrors();
						addMsgNum(id);
						showScrollErrors("theModifyFile",-1);
						return false;
					}

         
         
	}
	
	if(custForm.customerSeqNo.length>0){
	      var cnt = custForm.customerSeqNo.length;
		  for(var i=0;i<cnt;i++){
		  	index = i;
			   if(custForm.customerSeqNo[i].checked){
 					custForm.hdncustName.value=custForm.customerName[i].value;
 					custForm.hdncustDesc.value=custForm.customerDesc[i].value;
 					//  document.forms[0].hdnImageSeqNo.value=document.forms[0].imageSeqNo[index].value;
                 
                     
                   
			        break;
			   }
		 }
	
	}else{
		 if(custForm.customerSeqNo.checked){
 					custForm.hdncustName.value=custForm.customerName.value;
 					custForm.hdncustDesc.value=custForm.customerDesc.value;
                   //  document.forms[0].hdnImageSeqNo.value=document.forms[0].imageSeqNo.value;
			        
		}
	}
	   if(custForm.customerSeqNo.length>0)
     {
       var cnt = custForm.customerSeqNo.length;
          for(var i=0;i<cnt;i++)
              {
	/*var isSpecCharc = SpecialCharacterCheck(custForm.customerName[index].value);	
                    if(isSpecCharc)
                      {                
                           var id = '156';
                           hideErrors();
                           addMsgNum(id);
                            showErrors();	           
                           custForm.customerName[index].focus();
                           return false;

                        }*/
                } 
         }
            else{
                  /* var isSpecCharc = SpecialCharacterCheck(custForm.customerName.value);	
                       if (isSpecCharc)
                            {  
                               var id = '156';
                               hideErrors();
                              addMsgNum(id);
                              showErrors();	               
                              custForm.customerName.focus();
                               return false;
                            }*/
                     }
                  //document.forms[0].hdncustypeseqNo.value = document.forms[0].custTypeSeqNo.options[document.forms[0].custTypeSeqNo.selectedIndex].value;
               
/*   
          
			if(custForm.custTypeSeqNo[0].checked){
 				
 				if(custForm.hdncustomertypeseqNo.value!= custForm.custTypeSeqNo[0].value)
			  	{
			         var id = '207';
			         hideErrors();
			         addMsgNum(id);
			         showErrors();	               
			         return false;
                }
	
			}
			else if(custForm.custTypeSeqNo[1].checked){
	
				if(custForm.hdncustomertypeseqNo.value != custForm.custTypeSeqNo[1].value){
	
					var id= '207';
					hideErrors();
					addMsgNum(id);
					showErrors();
					return false;

				}

			}
			*/
			
		
				//Added For CR_84
    			 document.forms[0].hdSelectedSpecType.value=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;     	
				 document.forms[0].action="CustAction.do?method=updateCustomer";
				 document.forms[0].submit();
      }
	}
    

/*
*	Name: changeSpecType
*	Purpose: Used to Disable the Domestic/Export radio button on chage of Specificationtype
*	Added for LSDB_CR-46
*	on 28-Aug-08 by ps57222
*/

function changeSpecType()
{	
	//Updated For CR_84
	document.forms[0].custTypeSeqNo[0].checked= false;
	document.forms[0].custTypeSeqNo[1].checked= false;

  if(document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value != 2){

	document.forms[0].custTypeSeqNo[0].disabled= false;
	document.forms[0].custTypeSeqNo[1].disabled= false;

  }else if (document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value ==2){
  
    document.forms[0].custTypeSeqNo[0].checked= true;
	document.forms[0].custTypeSeqNo[0].disabled= true;
	document.forms[0].custTypeSeqNo[1].disabled= true;
  }
  
 

}
//Added for CR_106 JG101007
 function fnShowImage(imageSeqNo,custName)
{
  
	window.open("CustAction.do?method=showImage&imageSeqNo="+imageSeqNo+"&custName="+custName,'Logo',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH=600,height=400");
	//window.open("showAppendix.do?method=showImage&imageSeqNo="+imageSeqNo+"&imageName="+imageName,'AppendixImage',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH=600,height=400");

}
	function fnSaveImages(imgSeqNo,custName){
	var URL="DownLoadImage.do?method=downloadImage&ImageSeqNo="+imgSeqNo+"&download=Y"+"&ImageName="+custName+"";
	window.open(URL,'Logo',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH="+screen.width+",height=600");
}
function fnClose(){

	window.close();

}

