/**
This Method performs Field Validations before adding a Distributor
**/
function fnAddDistributor(){

if(trim(document.forms[0].distributor.value) == "" || trim(document.forms[0].distributor.value).length == 0)
{
  		var id = '163';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("distributor",-1);
		
		return false;
}

/*if(SpecialCharacterCheck(trim(document.forms[0].distributor.value))==true){
  		var id = '163';
		hideErrors();
		addMsgNum(id);
		showErrors();		
		document.forms[0].distributor.focus();
		return false;
}*/

if(trim(document.forms[0].comments.value).length>2000)
{
		var id = '517';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("comments",-1);
		return false;
}



var toLoadFileName=document.forms[0].theFile.value;

	toLoadFileName=toLoadFileName.substring(0,toLoadFileName.indexOf('_'));
	var fileName=document.forms[0].theFile.value;

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
						showScrollErrors("theFile",-1);
						return false;
					}

  }
	
document.forms[0].action="DistAction.do?method=insertDistributor";
document.forms[0].submit();

}

/**
This Method performs Field Validations before Modifying a Distributor
**/
function fnModifyDistributor(){

    var distForm = document.forms[0];
	var index;
	distSeqNochecked = false;
	
	if(distForm.distributorSeqNo.length>0){
	      var cnt = distForm.distributorSeqNo.length;
		  for(var i=0;i<cnt;i++){
			   if(distForm.distributorSeqNo[i].checked){
						distSeqNochecked = true;
					 if($("#imageSeqNo"+index).length)	{
						
							document.forms[0].hdnImageSeqNo.value=$("#imageSeqNo"+index).val();
						}
						index = i;
						break;
			   }
		  }
	}
	else{
			if(distForm.distributorSeqNo.checked){
				distSeqNochecked = true;
			}
	     }

	if (!distSeqNochecked)
	{
		var id= '101';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("distributorSeqNo",-1);
		return false;
	

	}else{
			
			if(distForm.distributorSeqNo.length>0){
			
				if(trim(distForm.distributorName[index].value).length==0 || trim(distForm.distributorName[index].value)=="" ){
					var id = '163';
					hideErrors();
					addMsgNum(id);
						showScrollErrors("distributorName",index);
					return false;
				}
				
				/*if(SpecialCharacterCheck(trim(distForm.distributorName[index].value))==true){
  					var id = '163';
					hideErrors();
					addMsgNum(id);
					showErrors();					
					distForm.distributorName[index].focus();
					return false;
				}*/
				
				if(trim(document.forms[0].distributorDesc[index].value).length>2000)
				{
						var id = '517';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("distributorDesc",index);
						return false;
				}
			}else{
				if(trim(distForm.distributorName.value).length==0 || trim(distForm.distributorName.value)=="" ){
					var id = '163';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("distributorName",-1);
					return false;
				}
				
				/*if(SpecialCharacterCheck(trim(distForm.distributorName.value))==true){
  					var id = '163';
					hideErrors();
					addMsgNum(id);
					showErrors();					
					distForm.distributorName.focus();
					return false;
				}*/
				
				if(trim(document.forms[0].distributorDesc.value).length>2000)
				{
						var id = '517';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("distributorDesc",-1);
						return false;
				}
			}
	}
	
	if(distForm.distributorSeqNo.length>0){
	      var cnt = distForm.distributorSeqNo.length;
		  for(var i=0;i<cnt;i++){
			   if(distForm.distributorSeqNo[i].checked){
 					distForm.hdnDistName.value=distForm.distributorName[i].value;
 					distForm.hdnDistComments.value=distForm.distributorDesc[i].value;
			        break;
			   }
		 }
	}else{
		 if(distForm.distributorSeqNo.checked){
 					distForm.hdnDistName.value=distForm.distributorName.value;
 					distForm.hdnDistComments.value=distForm.distributorDesc.value;
			        
			        
		}
	}
	
	
	
	         //Added for CR_106 File Extension Validations JG101007
var toLoadFileName=document.forms[0].theModifyFile.value;

	toLoadFileName=toLoadFileName.substring(0,toLoadFileName.indexOf('_'));
	var fileName=document.forms[0].theModifyFile.value;

	
		/*
			if (fileName=="" || fileName.length==0)
			{
							var id='932';
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
	
		
 document.forms[0].action="DistAction.do?method=updateDistributor";
 
 document.forms[0].submit();
}



/*Added for CR-55
  By VV49326
  On 19/09/08*/

  function fnDeleteDistributor(){

    var index;
	var distCheck=false;
	
	if(document.forms[0].distributorSeqNo.length > 0){
           
		   var cnt = document.forms[0].distributorSeqNo.length;

		   for(var i=0;i<cnt;i++){
               
			   if(document.forms[0].distributorSeqNo[i].checked)

			   {
                   distCheck = true;
				   index = i;
        
			   }
          
		   }
		   


	}else{


		  if(document.forms[0].distributorSeqNo.checked){
                
				distCheck = true;

		  }

	}

  if(!distCheck){

        var id= '101';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("distributorSeqNo",-1);
		return false;

  }else{


	  var accept=confirm("Are you sure you want to delete the Selected Distributor ?");

	  if(accept == true){

	  document.forms[0].action="DistAction.do?method=deleteDistributor";
	  document.forms[0].submit();

	  }else{

	  }

  }


       

  }
  //Added for CR_106 JG101007
   function fnShowImage(imageSeqNo,distributor)
{

  window.open("DistAction.do?method=showImage&imageSeqNo="+imageSeqNo+"&distributor="+distributor,'Logo',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH=600,height=400");
}
	function fnSaveImages(imgSeqNo,distributor){
	var URL="DownLoadImage.do?method=downloadImage&ImageSeqNo="+imgSeqNo+"&download=Y"+"&ImageName="+distributor+"";
	window.open(URL,'Logo',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH="+screen.width+",height=600");
}
function fnClose(){

	window.close();
}


