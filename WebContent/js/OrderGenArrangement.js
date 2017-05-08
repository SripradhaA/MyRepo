
function fnModify()
{
		var	genArr=document.forms[0];

		var genArgmntNotes=trim(genArr.genArgmntNotes.value);
		genArr.genArgmntNotes.value=genArgmntNotes;
		


/*Commented for CR_101 not to make Gen Notes mandatory
 
	if(genArgmntNotes.length==0 || genArgmntNotes.value=="" ){
	
		var id = '23';
		hideErrors();
		addMsgNum(id);
		showErrors();
		
		genArr.genArgmntNotes.focus();

		return false;

	}	*/
	
	if(genArgmntNotes.length>4000 ){
	
		var id = '32';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("genArgmntNotes",-1);
		
		return false;

	}	
	
	document.forms[0].action="orderGenArrangeAction.do?method=updateNotes";
	document.forms[0].submit();
	
}

function fnMainFeature(){

  document.forms[0].action="MainFeatureInfo.do?method=fetchComponentDesc&orderKeyId="+document.forms[0].orderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value;
  document.forms[0].submit();
}

function fnShowSpecification(){

document.forms[0].action="OrderSpecAction.do?method=fetchSpecificationItems&orderKeyId="+document.forms[0].orderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value;
document.forms[0].submit();

}

function fnShowGeneralArrangement(){

document.forms[0].action="orderGenArrangeAction.do?method=initLoad&orderKeyId="+document.forms[0].orderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value;
document.forms[0].submit();

}

function fnLoadAppendix(){

  
  document.forms[0].action="AppendixAction.do?method=fetchImage&orderKeyId="+document.forms[0].orderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value;
  document.forms[0].submit();
}

function fnLoadPerfCurve(){

document.forms[0].action="orderPerfCurveAction.do?method=initLoad&orderKeyId="+document.forms[0].orderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value;
document.forms[0].submit();

}

/**
** Function Name:	fnChangeRevisionView 
** Purpose		:	This function is used to display the Revision Markers based on the selection of Revision type
** Added For	:	LSDB_CR-74 on 01-June-09 by ps57222
**/

function fnChangeRevisionView()
{   
	document.forms[0].hdnRevViewCode.value=document.forms[0].revCode.options[document.forms[0].revCode.selectedIndex].value;
	document.forms[0].action="orderGenArrangeAction.do?method=initLoad&orderKeyId="+document.forms[0].orderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value;
	document.forms[0].submit();
}
//Added For CR_101
/*
* Name:    fnDelMdlGenArgmtView
* Purpose: Used to DelMdlGenArgmtViewdlts.
*   	   Added for LSDB_CR-101 on 12-sept-11 by Sd41630
*/

function fnDelOrdGenArgmtView()
{

	var index;
	var genArrange = document.forms[0];
	var modelView ;
    var modelViewSeqNo=null;
	var len=genArrange.modelViewSeqNo.length;
    var flag = false;
    
if(len>0){
for(var i=0; i<len; i++){

		if(genArrange.modelViewSeqNo[i].checked){
		     modelView=genArrange.modelView[i].value;
	         modelViewSeqNo=genArrange.modelViewSeqNo[i].value;
	       		index = i;
				flag = true;
					 }
			else {
				document.getElementById("viewImg"+i).value = "";
				
				$('#viewImg'+i).val("");
				
			}
		}
		}else {
		
		if(genArrange.modelViewSeqNo.checked){
		    modelView=genArrange.modelView.value;
	        modelViewSeqNo=genArrange.modelViewSeqNo.value;
		      flag = true;
		}
		}
		if(!flag){
				var id = '24';
                hideErrors();
                addMsgNum(id);
                showScrollErrors("modelViewSeqNo",-1);
				return false;
		}
		
	genArrange.hdSelectedMdlViewSeqNo.value=modelViewSeqNo;
    genArrange.hdSelectedMdlView.value= modelView;
    var conf = window.confirm("Are you sure to delete/update view?");
    if(conf){
    //genArrange.hdSelectedSpecType.value = genArrange.specTypeNo.options[genArrange.specTypeNo.selectedIndex].text ; 
	//genArrange.hdSelectedModel.value = genArrange.modelSeqNo.options[genArrange.modelSeqNo.selectedIndex].text ;
	document.forms[0].action="orderGenArrangeAction.do?method=delOrdGenArgmtView";
	document.forms[0].submit();
	}
}

function uploadOrderGenImage()
{
	var genArrange = document.forms[0];
	var filePath;
	var fileName;
    var flag = false;
	
    
   if(trim(genArrange.mdlNewViewName.value) =="" || trim(genArrange.mdlNewViewName.value).length == 0){
 
	    var id = '916';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("mdlNewViewName",-1);
		return false;
	
	}
    
    if((genArrange.mdlNewViewImage.value) =="" || (genArrange.mdlNewViewImage.value).length == 0 ){
		                var id = '918';
                        hideErrors();
                        addMsgNum(id);
                        showScrollErrors("mdlNewViewImage",-1);
						return false;
			
	}else{
	    filePath=trim(document.forms[0].mdlNewViewImage.value);
		fileName=trim(document.forms[0].mdlNewViewImage.value);
		flag = true;
		
	
	}
	
	
	if(flag){
	
					if(fileName==""){
					return false;
					} 
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

					
					for(var i = 0 ; i < arr.length; i++){
						if(trim(checkParam.toLowerCase())==arr[i]){
								flag = true;
						}
					}
					
					if(!flag){
					
						var id='901';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("mdlNewViewImage",-1);
						return false;
					}
										
				}

						

	}
	else{
				var id = '24';
                hideErrors();
                addMsgNum(id);
                showScrollErrors("modelViewSeqNo",-1);
				return false;
	
	}
	
	var file;
	//Added For CR_101
	//genArrange.hdSelectedSpecType.value = genArrange.specTypeNo.options[genArrange.specTypeNo.selectedIndex].text ; 
	//genArrange.hdSelectedModel.value = genArrange.modelSeqNo.options[genArrange.modelSeqNo.selectedIndex].text ;
	document.forms[0].action="orderGenArrangeAction.do?method=uploadOrdGenArgmntViewDtls";
	document.forms[0].submit();
}

//Added For CR_101

function updateOrdGenArgmntViewDtls()
{
	
	var index;
	var filePath;
	var fileName;
	var genArrange = document.forms[0];		
	var len=genArrange.modelViewSeqNo.length;
	var viewNotes = "";
    var modelView ;
    var modelViewSeqNo=null;

	 var flag = false;

	if(len>0){
		//Bug Fix -CR-131
					
		 if(genArrange.modelViewSeqNo.length>0)
		    {
		    var cnt = genArrange.modelViewSeqNo.length;
		   
		    for(var i=0;i<cnt;i++)
		    {
		    	modelView=(genArrange.modelView[i].value).trim();
		    	
		    if(genArrange.modelViewSeqNo[i].checked)
		    {
		   
		    index = i;
		   
		    break;
		    }
		    }
		   
		    }
		 if(modelView=="" || modelView==null){
			
					var id = '916';
                   	hideErrors();
                   	addMsgNum(id);
                  	showScrollErrors("modelView",index);
					return false;
			}
	    
		 //ends
	for(var i=0; i<len; i++){

		if(genArrange.modelViewSeqNo[i].checked){
			viewNotes=(genArrange.mdlViewNotes[i].value).trim();
	        modelView=(genArrange.modelView[i].value).trim();
	        modelViewSeqNo=genArrange.modelViewSeqNo[i].value;
	        
			filePath=$('#viewImg'+i).val();
			fileName=$('#viewImg'+i).val();
			 if(modelView=="" || modelView==null){
			
					var id = '916';
                    hideErrors();
                    addMsgNum(id);
                    showScrollErrors("modelView",index);
					return false;
			}
			index = i;
			if((fileName!="" )||(fileName!=undefined ) ){
				flag = true;
	
					}			
				}
//Added for CR-121				
				if(flag){
	
				if(viewNotes.length >4000)	{
					var id = '919';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("mdlViewNotes",i);
					return false;
				}
				if((fileName!="" )|| (fileName!=null) || (fileName!=undefined))	{
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

					for(var i = 0 ; i < arr.length; i++){
						if(trim(checkParam.toLowerCase())==arr[i]){
								flag = true;
						}
					}
					
				if(!flag)
					{
						var id='901';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("viewImg"+index,-1);
						return false;
					}
				}
			}
				}
//Added for CR-121 ends here
				 
			else {
				document.getElementById("viewImg"+i).value = "";
				
				$('#viewImg'+i).val("");
				
			}
			
		}
		}else {
		
		if(genArrange.modelViewSeqNo.checked){
			viewNotes=genArrange.mdlViewNotes.value;
	        modelView=genArrange.modelView.value;
	        modelViewSeqNo=genArrange.modelViewSeqNo.value;
	        fileName=$('#viewImg0').val();
	        filePath=$('#viewImg'+i).val();
	        
		   flag = true;
		}
		}
		
	if(flag){
	
				if(viewNotes.length >4000)	{
					var id = '919';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("mdlViewNotes",-1);
					return false;
				}

				if((fileName!="" )|| (fileName!=null) || (fileName!=undefined))	{
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

					for(var i = 0 ; i < arr.length; i++){
						if(trim(checkParam.toLowerCase())==arr[i]){
								flag = true;
						}
					}
					
				if(!flag)
					{
						var id='901';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("viewImg",i);
						return false;
					}
				}
			}
		}
	else{
			var id = '24';
            hideErrors();
            addMsgNum(id);
            showScrollErrors("modelViewSeqNo",-1);
			return false;
	
	}	
	 //Added For CR_101
	 var conf = window.confirm("Are you sure to delete/update view?");
	 genArrange.hdSelectedMdlViewSeqNo.value=modelViewSeqNo;
     genArrange.hdSelectedMdlViewNotes.value= viewNotes;
     genArrange.hdSelectedMdlView.value= modelView;
	if(conf){	
	genArrange.action="orderGenArrangeAction.do?method=updateOrdGenArgmntViewDtls";	
	genArrange.submit();
	}
}

//Added For CR_101
/*
*  Name: fnPrviewGenArgmtPDF
*  Purpose:Used to Preview the General Arrangement PDF
*
*/
function fnPrviewGenArgmtPDF() {
	document.forms[0].action="orderGenArrangeAction.do?method=previewGenArngmentPDF";	
	document.forms[0].submit();
}


