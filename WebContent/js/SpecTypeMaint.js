function fnAddSpecType()
{

if(trim(document.forms[0].specTypeName.value) == "" || trim(document.forms[0].specTypeName.value).length == 0)
{
  		var id = '891';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("specTypeName",-1);
		return false;
}

if(trim(document.forms[0].specTypeDesc.value).length>2000)
{
		var id = '517';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("specTypeDesc",-1);
		return false;
}
	              
   	document.forms[0].action="SpecTypeAction.do?method=insertSpecTypeDetails";
	document.forms[0].submit();

}


/**
This Method performs Field Validations before Modifying a Component
**/
function fnModifySpecType(){


 	var specTypeForm = document.forms[0];
	var index;
	specTypeSeqNoChecked = false;
	
	if(specTypeForm.spectypeSeqno.length>0){
	      var cnt = specTypeForm.spectypeSeqno.length;
		  for(var i=0;i<cnt;i++){
			   if(specTypeForm.spectypeSeqno[i].checked){
						specTypeSeqNoChecked = true;
						index = i;
						break;
			   }
		  }
	}
	else{
			if(specTypeForm.spectypeSeqno.checked){
				specTypeSeqNoChecked = true;
			}
	     }

	if (!specTypeSeqNoChecked)
	{
		var id= '892';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("spectypeSeqno",-1);
		return false;
	

	}else{
			
			if(specTypeForm.spectypeSeqno.length>0){
			
				if(trim(specTypeForm.spectypename[index].value).length==0 || trim(specTypeForm.spectypename[index].value)=="" ){
					var id = '891';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("spectypename",index);
					return false;
				}				  
		  		
				if(trim(specTypeForm.comments[index].value).length > 2000 ){
			   		var id = '517';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("comments",index);
					return false;
				  }
				
			}
	}
	
	if(specTypeForm.spectypeSeqno.length>0){
	      var cnt = specTypeForm.spectypeSeqno.length;
		  for(var i=0;i<cnt;i++){
			   if(specTypeForm.spectypeSeqno[i].checked){
 					specTypeForm.hdnSelSpectypeName.value=specTypeForm.spectypename[i].value;
 					specTypeForm.hdnSpecDesc.value=specTypeForm.comments[i].value;

 					if(specTypeForm.custMaintDisPlayFlag[i].checked){
      					 specTypeForm.hdnSelCustFlag.value="true";
				    }else{
				         specTypeForm.hdnSelCustFlag.value="false";
    				 }

    				 if(specTypeForm.distMaintDisPlayFlag[i].checked){
      					 specTypeForm.hdnSelDistFlag.value="true";
				    }else{
				         specTypeForm.hdnSelDistFlag.value="false";
    				 }
    				 
    				 if(specTypeForm.genArrMaintDisPlayFlag[i].checked){
      					 specTypeForm.hdnSelGenArrFlag.value="true";
				    }else{
				         specTypeForm.hdnSelGenArrFlag.value="false";
    				 }
    				 
    				 if(specTypeForm.perfCurveMaintDisPlayFlag[i].checked){
      					 specTypeForm.hdnSelPerfCurveFlag.value="true";
				    }else{
				         specTypeForm.hdnSelPerfCurveFlag.value="false";
    				 }
			        break;
			   }
		 }
	}else{
		 if(specTypeForm.spectypeSeqno.checked){
 				specTypeForm.hdnSelSpecTypeName.value=specTypeForm.spectypename.value;
 				specTypeForm.hdnSpecDesc.value=specTypeForm.comments.value;
 					if(specTypeForm.custMaintDisPlayFlag.checked){
      					 specTypeForm.hdnSelCustFlag.value="true";
				    }else{
				         specTypeForm.hdnSelCustFlag.value="false";
    				 }
    				 
    				 if(specTypeForm.distMaintDisPlayFlag.checked){
      					 specTypeForm.hdnSelDistFlag.value="true";
				    }else{
				         specTypeForm.hdnSelDistFlag.value="false";
    				 }
    				 
    				 if(specTypeForm.genArrMaintDisPlayFlag.checked){
      					 specTypeForm.hdnSelGenArrFlag.value="true";
				    }else{
				         specTypeForm.hdnSelGenArrFlag.value="false";
    				 }
    				 
    				 if(specTypeForm.perfCurveMaintDisPlayFlag.checked){
      					 specTypeForm.hdnSelPerfCurveFlag.value="true";
				    }else{
				         specTypeForm.hdnSelPerfCurveFlag.value="false";
    				 }
		}
	}
	var accept=confirm("Modifying Specification Type values will affect all working orders, Continue?");
	if(accept == true){
	 document.forms[0].action="SpecTypeAction.do?method=updateSpecTypeDetails";
	 document.forms[0].submit();
	 }


}

