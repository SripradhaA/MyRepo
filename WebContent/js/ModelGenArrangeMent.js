

function fnSearchGenArrImages(){
var	genArr=document.forms[0];
if (document.forms[0].specTypeNo.options[document.forms[0].specTypeNo.selectedIndex].value =="-1")
{
var id = '2';
hideErrors();
addMsgNum(id);
showScrollErrors("sltSpecType",-1);
return false;

}

 if(genArr.modelSeqNo.options[genArr.modelSeqNo.selectedIndex].value =="-1")
   {
			
		var id = '19';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltModel",-1);
		return false;
   
    }
genArr.hdSelectedSpecType.value = genArr.specTypeNo.options[genArr.specTypeNo.selectedIndex].text ;    
genArr.hdSelectedModel.value = genArr.modelSeqNo.options[genArr.modelSeqNo.selectedIndex].text ;
genArr.action="GenArrByModel.do?method=fetchGenArrImages";
genArr.submit();
}



function fnModify()
{
		var	genArr=document.forms[0];

		var genArgmntNotes=trim(genArr.genArgmntNotes.value);
//CR_101
/*if(genArgmntNotes.length==0 || genArgmntNotes.value=="" ){
	
		var id = '23';
		hideErrors();
		addMsgNum(id);
		showErrors();

		genArr.genArgmntNotes.value="";
		genArr.genArgmntNotes.focus();

		return false;

	}*/
	if(genArgmntNotes.length>4000 ){
	
		var id = '32';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("genArgmntNotes",-1);
		return false;

	}	
	
	if(genArr.hdPreSelectedModel.value!=genArr.modelSeqNo.options[genArr.modelSeqNo.selectedIndex].value){
		

									var id = '207';
                                    hideErrors();
                                    addMsgNum(id);
                                  	showScrollErrors("searchButton",-1);
									return false;
	}						
	//Added For CR_84
	genArr.hdSelectedSpecType.value = genArr.specTypeNo.options[genArr.specTypeNo.selectedIndex].text ;  
	genArr.hdSelectedModel.value = genArr.modelSeqNo.options[genArr.modelSeqNo.selectedIndex].text ;
	
	document.forms[0].action="GenArrByModel.do?method=modifyGenArrNotes";
	document.forms[0].submit();
	
}

/*
* Name: fnLoadModels
*    Purpose: Used to load the Models on chage of Specificationtype.
*   Added for LSDB_CR-46
*    on 02-Mar-12 by Sd41630
*/

function fnLoadModels()
{
/* commented for CR_84
*/

document.forms[0].action="GenArrByModel.do?method=fetchModels";
document.forms[0].submit();
}


//Added For CR_101
function updateMdlGenArgmntViewDtls()
{

	var genArrange = document.forms[0];
	var filePath;
	var fileName;
	var viewNotes = "";
    var modelView ;
    var modelViewSeqNo=null;
	var len=genArrange.modelViewSeqNo.length;
    var flag = false;
    var index =0;
		
	 if(len>0){
	 	//Added for CR-131
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
	//Ends here			
	
	 for(var i=0; i<len; i++){

		if(genArrange.modelViewSeqNo[i].checked){
			viewNotes=genArrange.mdlViewNotes[i].value;
	        modelView=genArrange.modelView[i].value;
	        modelViewSeqNo=genArrange.modelViewSeqNo[i].value;
	        
			filePath=$('#viewImg'+i).val();
			fileName=$('#viewImg'+i).val();
				index = i;
				if((fileName!="" )||(fileName!=undefined ) ){
				flag = true;
	
					}			
				
				 }
			else {
				document.getElementById("viewImg"+i).value = "";
				
				$('#viewImg'+i).val("");
				
			}
			
		}
		}else {
		
		if(genArrange.modelViewSeqNo.checked){
			viewNotes=(genArrange.mdlViewNotes.value).trim();
	        modelView=(genArrange.modelView.value).trim();
	        modelViewSeqNo=genArrange.modelViewSeqNo.value;
	        fileName=$('#viewImg0').val();
	        filePath=$('#viewImg'+i).val();
	        
	        if(modelView=="" || modelView==null){
			
					var id = '916';
                    hideErrors();
                    addMsgNum(id);
                    showScrollErrors("modelView",index);
					return false;
			}
	        
		   flag = true;
		}
		}
		

		
	if(flag){

	if(viewNotes.length >4000)
		{
	
		var id = '919';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("mdlViewNotes",index);
		return false;
			}
	
		
			if((modelViewSeqNo=="" && modelViewSeqNo==null) )
				{
	
										var id = '25';
	                                    hideErrors();
	                                    addMsgNum(id);
	                                   showScrollErrors("modelViewSeqNo",-1);
										return false;
				}
			else{	
								
				if((fileName!="" )|| (fileName!=null) || (fileName!=undefined))		
					{
					var code=fileName.split("\\");
					var temp="";
					for (j=1;j<code.length;j++)
					{temp=code[j];
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
							showScrollErrors("viewImg"+index,-1);
							return false;
						}
											
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

	if(genArrange.hdPreSelectedModel.value!=genArrange.modelSeqNo.options[genArrange.modelSeqNo.selectedIndex].value){
		

									var id = '207';
                                    hideErrors();
                                    addMsgNum(id);
                                    showScrollErrors("searchButton",-1);
									return false;
	}	
						

	 genArrange.hdSelectedMdlViewSeqNo.value=modelViewSeqNo;
     genArrange.hdSelectedMdlViewNotes.value= viewNotes;
     genArrange.hdSelectedMdlView.value= modelView;
      var conf = window.confirm("Are you sure to delete/update view?");
	if(conf){
	genArrange.hdSelectedSpecType.value = genArrange.specTypeNo.options[genArrange.specTypeNo.selectedIndex].text ; 
	genArrange.hdSelectedModel.value = genArrange.modelSeqNo.options[genArrange.modelSeqNo.selectedIndex].text ;
	document.forms[0].action="GenArrByModel.do?method=updateMdlGenArgmntViewDtls";
	document.forms[0].submit();
}else{
}
}

/*
* Name: fnDelMdlGenArgmtView
*    Purpose: Used to DelMdlGenArgmtViewdlts.
*   Added for LSDB_CR-101
*    on 12-sept-11 by Sd41630
*/

function fnDelMdlGenArgmtView()
{

	var index;
	var genArrange = document.forms[0];
	var modelView ;
    var  modelViewSeqNo=null;
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
    genArrange.hdSelectedSpecType.value = genArrange.specTypeNo.options[genArrange.specTypeNo.selectedIndex].text ; 
	genArrange.hdSelectedModel.value = genArrange.modelSeqNo.options[genArrange.modelSeqNo.selectedIndex].text ;
	document.forms[0].action="GenArrByModel.do?method=deleteMdlGenArgmtView";
	document.forms[0].submit();
}else{}
}

function uploadModelGenImage()
{
	var genArrange = document.forms[0];
	var filePath;
	var fileName;
    var flag = false;
	

if (genArrange.specTypeNo.options[genArrange.specTypeNo.selectedIndex].value =="-1")
{
	var id = '2';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltSpecType",-1);
	return false;

}

 if(genArrange.modelSeqNo.options[genArrange.modelSeqNo.selectedIndex].value =="-1")
   {
			
		var id = '19';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltModel",-1);
		return false;
   
    }
    
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
	genArrange.hdSelectedSpecType.value = genArrange.specTypeNo.options[genArrange.specTypeNo.selectedIndex].text ; 
	genArrange.hdSelectedModel.value = genArrange.modelSeqNo.options[genArrange.modelSeqNo.selectedIndex].text ;
	document.forms[0].action="GenArrByModel.do?method=uploadImage";
	document.forms[0].submit();
}




