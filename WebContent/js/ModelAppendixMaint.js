function searchApendixImage(){

  if (document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1")
   {
	    var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltSpecType",-1);
		return false;
   
	}
  
  
  
  
  if(document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value =="-1")
   {
			
		var id = '19';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltModel",-1);
		return false;
   
    }else{
		  //Added For CR_84 
		  document.forms[0].hdnSelSpecType.value=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
          document.forms[0].selectedModel.value=document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].text;
	      
		  }

          document.forms[0].action="AppendixMaintenance.do?method=fetchAppendixImages";
          document.forms[0].submit();


}


function uploadAppendixImage(){


	var filename= document.forms[0].theFile.value;
	/*var img = new Image();
	img.src = 'file://'+filename;
		
	*/

if (document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1")
   {
	    var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltSpecType",-1);
		return false;
   
	}


if(document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value =="-1")
   {
			
		var id = '19';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltModel",-1);
		return false;
   
    }else{
			//Added For CR_84 
		  document.forms[0].hdnSelSpecType.value=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
          document.forms[0].selectedModel.value=document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].text;
	      
		  }

 if(trim(document.forms[0].imgName.value) == "" ){

        var id = '798';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("imgName",-1);
		return false;
  
 
   }

	/*if(img.width > 550 || img.height > 600){
	
		var id = '811';
		hideErrors();
		addMsgNum(id);
		showErrors();
		return false;

	}*/

   if(trim(document.forms[0].imgDesc.value).length>0 && trim(document.forms[0].imgDesc.value).length>2000){

        var id = '799';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("imgDesc",-1);
		return false;
  
 
   }


   if(document.forms[0].theFile.value == ""){

        var id = '780';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("theFile",-1);
		return false;

   }

   var toLoadFileName=document.forms[0].theFile.value;
	toLoadFileName=toLoadFileName.substring(0,toLoadFileName.indexOf('_'));
	var fileName=document.forms[0].theFile.value;
	
				if(fileName==""){
						return false;
					}
					
					var code=fileName.split("\\");
					var temp="";
					if (code.length > 1)	{
						for (j=1;j<code.length;j++)
							{
								temp=code[j];
							}
					}else{
						temp=code[0];						
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
					arr[6] = "pdf";//Added for CR_97 for allowing PDF files in Appendix
						
					for(var i = 0 ; i < arr.length; i++){
						if(trim(checkParam.toLowerCase())==arr[i]){
								flag = true;
						}
					}
					}
					if(!flag){
					
						var id='901';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("theFile",-1);
						return false;
					}

          document.forms[0].action="AppendixMaintenance.do?method=uploadAppendixImage";
          document.forms[0].submit();

}


function deleteAppendixImage(){

	var i;
	var index;
	var selected=false;

if (document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1")
   {
	    var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltSpecType",-1);
		return false;
   
	}




if(document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value != document.forms[0].hdnModelSeqNo.value){

        var id = '207';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltModel",-1);
		return false;

}

if(document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value =="-1")
   {
			
		var id = '19';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltModel",-1);
		return false;
   
    }
	//Added For CR_84 
	document.forms[0].hdnSelSpecType.value=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
    document.forms[0].selectedModel.value=document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].text;


    if(document.forms[0].imgSeqNo.length>0){

        for(i=0;i<document.forms[0].imgSeqNo.length;i++){

            if(document.forms[0].imgSeqNo[i].checked){

               index=i;
			   selected=true;
			   document.forms[0].hdnImgSeqNo.value=document.forms[0].imgSeqNo[index].value;
			   break;

			}

		}
        

	}else{

         if(document.forms[0].imgSeqNo.checked){
            document.forms[0].hdnImgSeqNo.value=document.forms[0].imgSeqNo.value;
            selected=true;

		 }


	}

	if(!selected){
            
	    var id = '781';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("imgSeqNo",-1);
		return false;

	}


    var del=confirm("Are you sure to delete this image ?");
	if(del == true)
	{
    
    document.forms[0].action="AppendixMaintenance.do?method=deleteAppendixImage";
    document.forms[0].submit();

	}
	else{

	}

	



}

function updateImageName(){

var i;
	var index;
	var selected=false;

if (document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1")
   {
	    var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltSpecType",-1);
		return false;
   
	}


if(document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value != document.forms[0].hdnModelSeqNo.value){

        var id = '207';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltModel",-1);
		return false;

}

if(document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value =="-1")
   {
			
		var id = '19';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltModel",-1);
		return false;
   
    }
	//Added For CR_84 
	document.forms[0].hdnSelSpecType.value=document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].text;
    document.forms[0].selectedModel.value=document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].text;


    if(document.forms[0].imgSeqNo.length>0){

        for(i=0;i<document.forms[0].imgSeqNo.length;i++){

            if(document.forms[0].imgSeqNo[i].checked){

               index=i;
			   selected=true;
			   document.forms[0].hdnImgSeqNo.value=document.forms[0].imgSeqNo[index].value;
			   document.forms[0].hdnImageName.value=document.forms[0].imageName[index].value;
			   document.forms[0].hdnImageDesc.value=document.forms[0].imageDesc[index].value;
			  
			   break;

			}

		}
        

	}else{

         if(document.forms[0].imgSeqNo.checked){
            document.forms[0].hdnImgSeqNo.value=document.forms[0].imgSeqNo.value;
			document.forms[0].hdnImageName.value=document.forms[0].imageName.value;
			document.forms[0].hdnImageDesc.value=document.forms[0].imageDesc.value;
            selected=true;

		 }


	}

	if(!selected){
            
	    var id = '781';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("imgSeqNo",-1);
		return false;

	}


	if(document.forms[0].imgSeqNo.length>0){

       if(trim(document.forms[0].imageName[index].value) == ""){

        var id = '798';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("imageName",index);
		document.forms[0].imgName[index].value="";
		document.forms[0].imgName[index].focus();
	
		return false;


	   }

	   if(trim(document.forms[0].imageDesc[index].value).length>0 && trim(document.forms[0].imageDesc[index].value).length>2000){

        var id = '799';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("imageDesc",index);
		document.forms[0].imageDesc[index].focus();
		return false;
  
 
   }

 
	}else{

        if(trim(document.forms[0].imageName.value) == ""){

        var id = '798';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("imageName",-1);
		document.forms[0].imgName.value="";
		document.forms[0].imgName.focus();
		return false;


	   }


	   if(trim(document.forms[0].imageDesc.value).length>0 && trim(document.forms[0].imageDesc.value).length>2000){

        var id = '799';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("imageDesc",-1);
		document.forms[0].imageDesc.focus();
		return false;
  
 
   }
         

	}

    
    document.forms[0].action="AppendixMaintenance.do?method=updateAppendixImage";
    document.forms[0].submit();





}

//Added for CR-46 PM&I Spec
function fnLoadModels(){

var appendixForm = document.forms[0];

appendixForm.hdnSelSpecType.value=appendixForm.specTypeNo.options[appendixForm.specTypeNo.selectedIndex].text;
document.forms[0].action="AppendixMaintenance.do?method=fetchModels";
document.forms[0].submit();
}



/* Added for CR_91 Enabling Save Option */
function fnSaveImages(imgSeq,imgName){
	var URL="DownLoadImage.do?method=downloadImage&ImageSeqNo="+imgSeq+"&download=Y"+"&ImageName="+imgName+"";
	window.open(URL,'AppendixImage',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH="+screen.width+",height=600");
}//Added For CR_92
/*
*	Name: fnShowImage
*	Purpose:Used to Load the Appendix Image in popup Screen
*	
*/


function fnShowImage(imageSeqNo,imageName)
{

	window.open("showAppendix.do?method=showImage&imageSeqNo="+imageSeqNo+"&imageName="+escape(imageName),'AppendixImage',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH=600,height=400");

}
/*
*   Name: LoadClauseDesc
*  Purpose:Used to Load  All Clause from model the popup screen
*
*/
function fnLoadClauseDesc(selClauseIndex)
{

var selectedModelName= document.forms['ModelAppendixForm'].selectedModel.value;
var selectedModelID = document.forms['ModelAppendixForm'].hdnModelSeqNo.value;
var specType = document.forms['ModelAppendixForm'].hdnSelSpecType.value;
var appImage=document.forms['ModelAppendixForm'];
var desc=appImage.imgSeqNo;
var len=appImage.imgSeqNo.length;
flag = false;
var flagVal=false;
var indexFlag=false;
if(desc.length!=undefined){
		for(var i = 0 ; i < len; i++){
		if(appImage.imgSeqNo[i].checked){
			 if(i!=selClauseIndex){
			 	indexFlag=true;
                var id = '902';
		        hideErrors();
				addMsgNum(id);
				showScrollErrors("imgSeqNo",-1);	
				break;			
				}
			else{
				flagVal=true;
	            var imgSeqNo=appImage.imgSeqNo[i].value;
	            appImage.clauseDes.value =trim(appImage.divId[i].value);
				break;
				}
			}
			
		}
	
	 } else{

	if(desc.checked){
		appImage.clauseDes.value =trim(appImage.divId.value);
		flagVal=true;
	}
}
	
 
var indexid=appImage.clauseDes.value;
if(!flagVal)
	{
		var id = '781';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("imgSeqNo",-1);
		//return false;
	}


	if(flagVal){
	

     window.open("modelClauseDescPopupAction.do?method=initLoad&selectedModelName="+escape(selectedModelName)+"&selectedModelID="+selectedModelID+"&DivID="+indexid+"&spectype="+escape(specType)+"","AllModelClause","location=0,resizable=no,status=0,scrollbars=1,width=780,height=600");
  }

 }

/*
*	Name: fnSaveMappings
*	Purpose:Used to Map the Clauses with the Appendix Image at model level
*	
*	
*/

function fnSaveMappings(){
var appImage=document.forms[0];

flag = false;

var index;
var ClauseSeqNo=appImage.clauseSeqNo.value;
var versionNo=appImage.versionNo.value;
var desc=appImage.imgSeqNo;
var len=appImage.imgSeqNo.length;
flag = false;
appImage.hdnImgSeqNo.value=desc.value;

if(desc.length!=undefined){

for(var i=0; i<len; i++){

		if(appImage.imgSeqNo[i].checked){
			appImage.clauseDes.value =trim(appImage.divId[i].value);
			index = i;
			flag = true;
			break;
		}
		
	}
}
else{

	if(desc.checked){
		flag=true;
		appImage.hdnImgSeqNo.value=desc.value;
		
	}
}

if(!flag){

	var id = '781';
    hideErrors();
    addMsgNum(id);
	showScrollErrors("imgSeqNo",index);
	return false;
	
}
if((ClauseSeqNo <= 0)||(versionNo <= 0)){

	var id = '810';
    hideErrors();
    addMsgNum(id);
	showScrollErrors("img",index);
	return false;

}

		document.forms[0].action="AppendixMaintenance.do?method=saveModelClaMappings";
		document.forms[0].submit();
		
}
//Added for CR_92 for viewing clause mapping
function fnViewMapping(modelSeqNo,clauseSeqNo,VersionNo,SecSeqNo,SubSecSeqNo,selClauseIndex)
{
var appImage=document.forms['ModelAppendixForm'];
var desc=appImage.imgSeqNo;
var len=appImage.imgSeqNo.length;
flag = false;
var flagVal=false;
var indexFlag=false;
var index=0;
if(desc.length!=undefined){
		for(var i = 0 ; i < len; i++){
			index = i;
		if(appImage.imgSeqNo[i].checked){
			 if(i!=selClauseIndex){
			 	indexFlag=true;
                var id = '902';
		        hideErrors();
				addMsgNum(id);
				showScrollErrors("clauseDesc"+index,-1);	
				break;			
				}
			else{
				flagVal=true;
	            var imgSeqNo=appImage.imgSeqNo[i].value;
				break;
				}
			}
		}
	}else{
			if(appImage.imgSeqNo.checked){
		     var imgSeqNo=appImage.imgSeqNo.value;
				flagVal=true;
			}
	 }
	 if(!flagVal && !indexFlag){

		var id = '781';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("imgSeqNo",-1);
		
	}

var selectedModel=document.forms[0].selectedModel.value;
var selectedSpecType=document.forms[0].hdnSelSpecType.value;
var flag = "N";
	if(flagVal){
	window.open("modelClauseDescPopupAction.do?method=LoadClauseDesc&popupFlag="+flag+"&ModelSeqNo="+modelSeqNo+"&subSecSeqNo="+SubSecSeqNo+"&secSeqNo="+SecSeqNo+"&clauseSeqNo="+clauseSeqNo+"&versionNo="+VersionNo+"&selectedModel="+selectedModel+"&selectedSpecType="+selectedSpecType+"&selectedSpecType="+selectedSpecType+"",'Clause','location=0,resizable=No ,status=0,scrollbars=1,WIDTH=850,height=600');
	}

}
//Added for CR_97 for downloading PDF
function fnShowPDF(ImageSeqNo,ImageName){
		document.forms[0].action="DownLoadImage.do?method=downloadImage&ImageSeqNo="+ImageSeqNo+"&ImageName="+escape(ImageName)+"&download=Y";
		document.forms[0].submit();
}
//CR_97 Ends here