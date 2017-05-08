/*
*	Name: fnShowImage
*	Purpose:Used to Load the Appendix Image in popup Screen
*	
*/


function fnShowImage(imageSeqNo,imageName)
{

	window.open("showAppendix.do?method=showImage&imageSeqNo="+imageSeqNo+"&imageName="+imageName,'AppendixImage',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH=600,height=400");

}


/*
*	Name: fnClose
*	Purpose:Used to Close the Appendix Image popup Screen
*	
*/


function fnClose(){

	window.close();

}


/*
*	Name: fnAddimage
*	Purpose:Used to Add the Appendix Image at Order Level
*	
*/


function fnAddimage()
{
	
	var appImage=document.forms[0];
	var imageName=trim(appImage.imgName.value);
	var imageDesc=trim(appImage.imgDesc.value);
	appImage.imgName.value=imageName;
	appImage.imgDesc.value=imageDesc;

	if(imageName.length==0 || imageName.value=="" )
	{
	
		var id = '798';
		//hideErrors();
		addMsgNum(id);
		showScrollErrors("imgName",-1);
 		return false;

	}	
	if (imageDesc.length>2000)
	{
		var id = '799';
		//hideErrors();
		addMsgNum(id);
		showScrollErrors("imgDesc",-1);
		return false;
		
	}



	

	var toLoadFileName=document.forms[0].imageSource.value;

	toLoadFileName=toLoadFileName.substring(0,toLoadFileName.indexOf('_'));
	var fileName=document.forms[0].imageSource.value;

	
		
			if (fileName=="" || fileName.length==0)
			{
							var id='780';
							//hideErrors();
							addMsgNum(id);
							showScrollErrors("imageSource",-1);
							return false;
			}
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
					} else {
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
					
					if(!flag){
					
						var id='901';
						//hideErrors();
						addMsgNum(id);
						showScrollErrors("imageSource",-1);
						return false;
					}

					
					document.forms[0].action="AppendixAction.do?method=addImage";
					document.forms[0].submit();
}
}

/*
*	Name: fnModifyImage
*	Purpose:Used to Modify Name and decription of the Appendix Image at Order Level
*	
*/


function fnModifyImage()
{
	
	var appImage=document.forms[0];
	flag = false;

var index;
secchecked = false;
var desc=appImage.imageSeqNo;
var len=appImage.imageSeqNo.length;
var description;
var name;
if(desc.length!=undefined){

for(var i=0; i<len; i++){

		if(appImage.imageSeqNo[i].checked){
			appImage.appImageName.value =trim(appImage.imageName[i].value);
			name=appImage.appImageName.value;
			appImage.appImageDesc.value=trim(appImage.imageDesc[i].value);
			description=appImage.appImageDesc.value;
			index = i;
			flag = true;
			break;
		}
		
	}
}
else{

	if(desc.checked){

			appImage.appImageName.value =trim(appImage.imageName.value);
			name=appImage.appImageName.value;
			appImage.appImageDesc.value=trim(appImage.imageDesc.value);
			description=appImage.appImageDesc.value;
			flag=true;
	}
}

if(flag){
	
if(name.length==0 || name.value=="" )
	{
	
		var id = '798';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("imageName",index);
		return false;

	}	
	if (description.length>2000)
	{
		var id = '799';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("imageDesc",index);
		return false;
		
	}
	
}else{
		var id = '781';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("imageSeqNo",index);
		return false;
	
}

					document.forms[0].action="AppendixAction.do?method=modifyImageName";
					document.forms[0].submit();

}


/*
*	Name: fnDeleteImage
*	Purpose:Used to Delete the Appendix Image at Order Level
*	
*/


function fnDeleteImage()
{

var appImage=document.forms[0];

flag = false;
hideErrors();
var index;
secchecked = false;

var desc=appImage.imageSeqNo;

var len=appImage.imageSeqNo.length;


var description;
if(desc.length!=undefined){

for(var i=0; i<len; i++){

		if(appImage.imageSeqNo[i].checked){
			
			index = i;
			flag = true;
			break;
		}
		
	}
}
else{

	if(desc.checked){
		flag=true;
	}
}
if(!flag){

	var id = '781';
    hideErrors();
    addMsgNum(id);
	showScrollErrors("imageSeqNo",-1);
	return false;
	
}
var del=confirm("Are you sure to delete the Appendix Image ?");

	if(del)
	{
		document.forms[0].action="AppendixAction.do?method=deleteImage";
		document.forms[0].submit();
	}else{

	}
}


/*
*	Name: fnLoadClauseDesc
*	Purpose:Used to Load the popup screen for selecting the clause to map 
*	with Appendix Image.Here the index id of the Div tag is passed to the pop up
*	and the selected Clause desc is populated in the particular div tag.
*	
*/

function fnLoadClauseDesc()
{
clauseDescChangeFlag=1;
var selectedOrderNo = document.forms[0].orderNo.value;
var orderkey=document.forms[0].hdnOrderKey.value;
var appImage=document.forms[0];

var desc=appImage.imageSeqNo;

var len=appImage.imageSeqNo.length;
flag = false;

var description;
if(desc.length!=undefined){

for(var i=0; i<len; i++){

		if(appImage.imageSeqNo[i].checked){
			appImage.clauseDes.value =trim(appImage.divId[i].value);
			index = i;
			flag = true;
			break;
		}
		
	}
}
else{

	if(desc.checked){
		appImage.clauseDes.value =trim(appImage.divId.value);
		flag=true;
	}
}
var indexid=appImage.clauseDes.value;


if(!flag)
	{
		var id = '781';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("imageSeqNo",-1);
		//return false;
	}


	if(flag){

		window.open("showAppendix.do?method=initLoad&OrderNo="+selectedOrderNo+"&orderkey="+orderkey+"&DivID="+indexid+"","Clause","location=0,resizable=no,status=0,scrollbars=1,WIDTH=850,height=600"); 
	}
}


/*
*	Name: fnLoadSubSection
*	Purpose:Used to Load subsectionList in popup
*	
*/

function fnLoadSubSection()
{
	
	document.forms[0].action="showAppendix.do?method=SubSectionLoad";
	document.forms[0].submit();	
}

/*
*	Name: fnLoadClauses
*	Purpose:Used to Load the Clauses for the selected Subsection in popup
*	
*/

function fnLoadClauses()
{

		document.forms[0].action="showAppendix.do?method=loadClauseDesc";
		document.forms[0].submit();
}


/*
*	Name: SelectClauseDesc
*	Purpose:Used to select the Clause Description and populate it in the 
*	parrent Appendix Screen in the Selected Div ID.
*	
*/


function SelectClauseDesc()
{
var selectedClause=document.forms[0].clauseSeqNo;
var selectedClauseDesc;
var flag = false;
var tableData= new Array();
var versionNo=document.forms[0].versionNo;
var subSectionSeqNo=document.forms[0].subSecSeqNo.value;
var modelSeqNo=document.forms[0].modelSeqNo.value;
var index=document.forms[0].clauseDes.value;
//Added for CR-74 VV49326 16-06-09
var reservedFlag=false;


if (document.forms[0].sectionSeqNo.options.length<=1)
{
	var id = '519';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltSection",-1);
	return false;
}
if (document.forms[0].sectionSeqNo.options[document.forms[0].sectionSeqNo.selectedIndex].value==-1)
{	
	var id = '205';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltSection",-1);	
	return false;
}

if (document.forms[0].subSecSeqNo.options.length<=1)
{
	var id = '511';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltSubSection",-1);
	return false;
}

if (document.forms[0].subSecSeqNo.options[document.forms[0].subSecSeqNo.selectedIndex].value==-1)
{	
	var id = '182';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltSubSection",-1);	
	return false;
}

if (selectedClause==null)
{
	var id = '521';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("clauseSeqNo",-1);
	return false;
}
else
{
if (selectedClause.length!=undefined)
{
	for (i=0;i<selectedClause.length;i++)
	{
		
		if (selectedClause[i].checked)
		{
  			selectedClauseDesc= document.forms[0].clauseDesc[i].value;
  			versionNo=document.forms[0].versionNo[i].value;	  					
  			window.opener.document.forms[0].clauseSeqNo.value=selectedClause[i].value; 
  			window.opener.document.forms[0].versionNo.value=versionNo; 	
  			window.opener.document.forms[0].subSectionSeqNo.value=subSectionSeqNo; 
			window.opener.document.forms[0].hdPreSelectedModel.value=modelSeqNo;
  			flag = true;
  			//Added for CR-74 VV49326 16-06-09
  			if(document.forms[0].reservedFlag[i].value=="Y"){
  			reservedFlag = true;
  			}
  			break;
		}
	}
}

else
{
	if(selectedClause.checked)
	{
		selectedClauseDesc= document.forms[0].clauseDesc.value;
		versionNo=document.forms[0].versionNo.value;		
		window.opener.document.forms[0].clauseSeqNo.value=selectedClause.value;
		window.opener.document.forms[0].versionNo.value=versionNo;  
		window.opener.document.forms[0].subSectionSeqNo.value=subSectionSeqNo;
		window.opener.document.forms[0].hdPreSelectedModel.value=modelSeqNo;
		flag = true;	
		//Added for CR-74 VV49326 16-06-09
  		if(document.forms[0].reservedFlag.value=="Y"){
  		reservedFlag = true;
  		}	
	}
}
}
     //Added for CR-74 VV49326 16-06-09
     if(flag && reservedFlag){
        var id = '763';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("clauseSeqNo",-1);
		return false;
     }
    
	if(flag){		
	
		var arr = new Array(tableData);	
		if(selectedClause.length!=undefined && selectedClause.length > 1)
		{
		window.opener.document.getElementById("clauseDesc"+index).innerHTML =document.getElementById("clauseID"+i).innerHTML;
		
		}
	else	
	{
		window.opener.document.getElementById("clauseDesc"+index).innerHTML =document.getElementById("clauseID0").innerHTML;	
		
	}				
		
		
		window.close();
	}else{
		
		var id = '520';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("clauseSeqNo",-1);			
	}
}


/*
*	Name: fnSaveMappings
*	Purpose:Used to Map the Clauses with the Appendix Image
*	
*	
*/

function fnSaveMappings(){

var appImage=document.forms[0];

flag = false;
hideErrors();
var index = 0;//Fix for CR_131
secchecked = false;

var desc=appImage.imageSeqNo;

var len=appImage.imageSeqNo.length;

var ClauseSeqNo=appImage.clauseSeqNo.value;
var versionNo=appImage.versionNo.value;


var description;


if(desc.length!=undefined){

for(var i=0; i<len; i++){

		if(appImage.imageSeqNo[i].checked){
			
			index = i;
			flag = true;
			break;
		}
		
	}
}
else{

	if(desc.checked){
		flag=true;
	}
}

if(!flag){

	var id = '781';
    hideErrors();
    addMsgNum(id);
	showScrollErrors("imageSeqNo",index);
	return false;
	
}
if((ClauseSeqNo <= 0)||(versionNo <= 0)){

	var id = '810';
    hideErrors();
    addMsgNum(id);
	showScrollErrors("img",index);
	return false;

}

		document.forms[0].action="AppendixAction.do?method=saveMappings";
		document.forms[0].submit();
}






/*
*	Name: Cancelpopup
*	Purpose:Used to cancel the PopUp
*	
*	
*/


function Cancelpopup()
{
	window.close();
}


/*
*	Name: fnViewMapping
*	Purpose:Used to View the Mapping of clauses with Appendix image and the 
*	Clause mapped to the image is high lighted in the pop up Screen
*
*	
*	
*/


function fnViewMapping(clauseSeqNo,VersionNo,SecSeqNo,SubSecSeqNo)
{	
	var OrderKey=document.forms[0].hdnOrderKey.value;

	window.open("showAppendix.do?method=viewMapping&subSecSeqNo="+SubSecSeqNo+"&orderKey="+OrderKey+"&secSeqNo="+SecSeqNo+"&clauseSeqNo="+clauseSeqNo+"&versionNo="+VersionNo+"",'ViewMapping','location=0,resizable=No ,status=0,scrollbars=1,WIDTH=850,height=600');

}



/*
*	Name: turnAppendix
*	Purpose:Used to Turn ON/OFF Appendix image.

*	If The Appendix image is turned ON then we should pass the
	toggle flag as 'N' to turnned OFF the Flag.
*
*	If The Appendix image is turned OFF then we should pass the
	toggle flag as 'Y' to turnned ON the Flag.
*	
*/

function turnAppendix(){

	var appendixFlag=document.forms[0].appendixFlag.value;
	

	var test=document.forms[0].tester.value;
	
	
	if(appendixFlag== "N" )
		{
				if(test==0){

					var id = '809';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("img",-1);
					return false;

				}
		}

		if(appendixFlag== "N")
		{
			document.forms[0].toggleFlag.value="Y";
		}else if(appendixFlag== "Y"){
			document.forms[0].toggleFlag.value="N";
		}

		document.forms[0].action="AppendixAction.do?method=turnAppendix";
		document.forms[0].submit();
}


function fnMainFeature(){

  document.forms[0].action="MainFeatureInfo.do?method=fetchComponentDesc&orderKey="+document.forms[0].hdnOrderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value;
  document.forms[0].submit();
}

function fnLoadPerfCurve(){

document.forms[0].action="orderPerfCurveAction.do?method=initLoad&orderKeyId="+document.forms[0].hdnOrderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value;
document.forms[0].submit();

}

function fnLoadAppendix(){

  
  document.forms[0].action="AppendixAction.do?method=fetchImage&orderKeyId="+document.forms[0].hdnOrderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value;
  document.forms[0].submit();
}
//added for CR_91 for viewing model level appendix images
function fnViewModelAppendixImages()
{
  var modelName = document.forms[0].modelName.value;
  var modelSeqNo = document.forms[0].modelSeqNo.value;
  var URL="AppendixViewImagesAction.do?method=fetchModelAppendixImages&modelName="+modelName+"&modelSeqNo="+modelSeqNo;
  window.open(URL,'AppendixImage',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH="+screen.width+",height=600");
}
/* Added for CR_91 Enabling Save Option */
function fnSaveImages(imgSeq,imgName){
	var URL="DownLoadImage.do?method=downloadImage&ImageSeqNo="+imgSeq+"&download=Y"+"&ImageName="+imgName+"";
	window.open(URL,'AppendixImage',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH="+screen.width+",height=600");
}
//Added for CR_97 for downloading PDF
function fnShowPDF(ImageSeqNo,ImageName){
		document.forms[0].action="DownLoadImage.do?method=downloadImage&ImageSeqNo="+ImageSeqNo+"&ImageName="+escape(ImageName)+"&download=Y";
		document.forms[0].submit();
}
//CR_97 Ends here

//Added for CR_131
$(document).ready(function(){
	if ($('.mappedcla').length > 0)
		$('#claMapped').val("1");
})
//Added for CR_131 - Ends here