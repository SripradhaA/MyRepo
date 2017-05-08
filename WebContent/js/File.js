function validateFile(obj)
{
	document.forms[0].filePath.value=obj.value;
	


	var toLoadFileName=document.forms[0].filePath.value;
	toLoadFileName=toLoadFileName.substring(0,toLoadFileName.indexOf('_'));
	
	var fileName=obj.value;
	
	if(fileName==""){
	
	
	return false;
	}

	
	

	
}
