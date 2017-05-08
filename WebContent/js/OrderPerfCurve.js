function uploadOrderGenImage()
{
var index;
	var genArrange = document.forms[0];
	if(document.forms[0].theFile.value == "")
	{
	var id = '199';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("theFile",-1);
		return false;
	
	}
	
	var file;
	var toLoadFileName=document.forms[0].theFile.value;
	
	toLoadFileName=toLoadFileName.substring(0,toLoadFileName.indexOf('_'));
	var fileName=document.forms[0].theFile.value;
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
					arr[6] = "pdf";
					
					for(var i = 0 ; i < arr.length; i++){
						if(trim(checkParam.toLowerCase())==arr[i]){
								flag = true;
						}
					}
	
		if(!flag)
		{
			
			var id='27';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("theFile",-1);
			return false;
		}
	}
	
	//Added for CR_121 Performance curve Rearrange
	var noOfPerfCurve=0;
	$('#perfCurveTable tr').each(function() 
	{
		noOfPerfCurve++;
	}); 
	
	document.forms[0].noOfPerfCurve.value=noOfPerfCurve;
	//Added for CR_121 Performance curve Rearrange Ends
		
	document.forms[0].filePath.value=document.forms[0].theFile.value;
	document.forms[0].action="orderPerfCurveAction.do?method=uploadPerfCurveImage";
	document.forms[0].submit();
}
function deleteOrderGenImage()
{	
	if(document.forms[0].curveSeqNo.length > 0 )
	{
	      var cnt = document.forms[0].curveSeqNo.length;
		  for(var i=0;i<cnt;i++)
		  {
			   if(document.forms[0].curveSeqNo[i].checked)
			   {
						curveseqno = true;
						index = i;
						break;
			   }
		  }
	}
	else
	{
			if(document.forms[0].curveSeqNo.checked)
			{
				curveseqno = true;
			}
	}
	     if (!curveseqno)
		{
			var id= '202';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("curveSeqNo",-1);
			return false;
		
	
		}
	
	var del=confirm("Are you sure to delete this image");
	if(del == true)
	{
	var index;
    var curveseqno=false;
    if(document.forms[0].curveSeqNo.length > 0)
    {
    
	    for( var i=0;i<document.forms[0].curveSeqNo.length;i++)
	    {
	    
	    	if(document.forms[0].curveSeqNo[i].checked)
	    	{
		         document.forms[0].curveSeqNo.value=document.forms[0].curveSeqNo[i].value;
		         
		         document.forms[0].imgSeqNo.value=document.forms[0].imgSeqNo[i].value;
	        
	         	break;
	         }
	      }
     }else
     {
         
         if(document.forms[0].curveSeqNo.checked)
         {
         document.forms[0].curveSeqNo.value=document.forms[0].curveSeqNo.value;
        
         document.forms[0].imgSeqNo.value=document.forms[0].imgSeqNo.value;
          
         }
     }
   
   	document.forms[0].action="orderPerfCurveAction.do?method=deletePerfCurveImage";
    document.forms[0].submit();
    }

}

function fnShowPDF(ImageSeqNo,ImageName){
		
		document.forms[0].action="DownLoadImage.do?method=downloadPDF&ImageSeqNo="+ImageSeqNo+"&ImageName="+escape(ImageName);
		document.forms[0].submit();
		
	}

/***
** Function Name:fnEnableImageNameButton
** Puprose: Added for LSDB_CR-63 used to disable the Modifiy button for the images other than PDF
** Added on 11-Dec-08 by ps57222
**/

function fnEnableImageNameButton()
	{	
		var mod=document.forms[0];
		var disableflag=false;
		var contentType;
		if(mod.curveSeqNo.length > 0 ){
			var cnt = mod.curveSeqNo.length;
			for(var i=0;i<cnt;i++){
				if(mod.curveSeqNo[i].checked)
				{
				contentType=mod.contenttype[i].value;
				if(contentType=="application/pdf")
					disableflag=true;
				}						
			}	
		}else{
			if(mod.curveSeqNo.checked){
				contentType=mod.contenttype.value;
				if(contentType=="application/pdf"){
						disableflag=true;
					}
				}
	     }
		if(disableflag){
			document.getElementById("modifyImage").disabled=false;
		}
		else{
			document.getElementById("modifyImage").disabled=true;
		}		
	}


/***
** Function Name:fnModifyImageName
** Puprose: Added for LSDB_CR-63 used to Modifiy PDF image name.
** Added on 11-Dec-08 by ps57222
**/

/** Based on the requirement of LSDB_CR-74 fnModifyImageName is Changed 
**  Modified on 01-June-09 by ps57222
**/
function fnModifyImageName(){
		var mod=document.forms[0];
		var curveseqno=false;
		var imageName;
		var index;

		
		if(mod.curveSeqNo.length > 0 ){
			var cnt = mod.curveSeqNo.length;
			for(var i=0;i<cnt;i++){
				if(mod.curveSeqNo[i].checked){
					curveseqno = true;
					contentType=mod.contenttype[i].value;
					imageName=trim(mod.imageName[i].value);
					mod.hdnImageName.value=imageName;
					//Added for CR_121
					index=i;
					
					}
			}
		}
		else{
			if(mod.curveSeqNo.checked){
				contentType=mod.contenttype.value;
				imageName=trim(mod.imageName.value);
				mod.hdnImageName.value=imageName;
				curveseqno = true;
			}
	     }
	     if (!curveseqno)
		{
			var id= '202';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("curveSeqNo",-1);
			return false;
		
	
		}
		
		if(imageName.length==0 || imageName==""){
			var id= '863';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("imageName",index);
			return false;
		}
		
		document.forms[0].action="orderPerfCurveAction.do?method=modifyImageName";
		document.forms[0].submit();
	}

function fnMainFeature(){

	document.forms[0].action="MainFeatureInfo.do?method=fetchComponentDesc&orderKey="+document.forms[0].orderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value;
	document.forms[0].submit();
}

function fnLoadPerfCurve(){
	document.forms[0].action="orderPerfCurveAction.do?method=initLoad&orderKeyId="+document.forms[0].orderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value;
	document.forms[0].submit();

}

function fnLoadAppendix(){
	document.forms[0].action="AppendixAction.do?method=fetchImage&orderKeyId="+document.forms[0].orderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value;
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
	document.forms[0].action="orderPerfCurveAction.do?method=initLoad";
	document.forms[0].submit();
}


//Added for CR_121
$(document).ready(function() {
$("#btnRearrangeClicked").hide();

});

function fnRearrangePerfCurveActivated(){
	document.getElementById('perfCurveTable').style.border="5px solid RED";
	document.getElementById('rearrInfo').style.display="none";
	document.getElementById('rearrInfo1').style.display="block";
	document.getElementById('btnRearrangeNotClicked').style.display="none";
	document.getElementById('btnRearrangeClicked').style.display="block";
	
	$('#perfCurveTable tr').each(function() 
	{
		if (!this.rowIndex) return;
		$(this).find("td:first").html("&nbsp;");   
		
	}); 
	
	$('#perfCurveTable').tableDnD({
		onDragStart: function(table, row) {
			
			$(row).css('background-color', '#D8F2FF');
            $(row).contents('td').css({'border': '1px solid red', 'border-left': '1px dotted red', 'border-right': '1px dotted red'});
            $(row).contents('td:first').css('border-left', '1px solid red');
            $(row).contents('td:last').css('border-right', '1px solid red');
			},
		onDrop: function(table, row) {
          var rows = table.rows;
          var result = "";
          for (var i=0; i<rows.length; i++) {
            if (result.length > 0) result += ",";
            var rowId = rows[i].id;
            if (rowId && rowId && table.tableDnDConfig && table.tableDnDConfig.serializeRegexp) {
                rowId = rowId.match(table.tableDnDConfig.serializeRegexp)[0];
            }
            
            result += rowId;
            }
          var rowIDList=result;
          document.forms[0].rowIndexList.value = rowIDList;
          $(row).contents('td:first').html("");
        }
	});
	
}


function fnSaveRearrangedPerfCurve(){
	var MainForm=document.forms[0];
	//alert(MainForm.rowIndexList.value);
	if(MainForm.rowIndexList.value !=null && MainForm.rowIndexList.value !="")
	{
		MainForm.action="orderPerfCurveAction.do?method=saveRearrangedPerfCurve&orderKeyId="+document.forms[0].orderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value;
		MainForm.submit();
	}
	else
	{
		var id = '1025';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("select",-1);
	}
}

//Added for CR_121 Ends