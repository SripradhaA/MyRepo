//Validations for Component group Search	
function fnSearchCompGroups(){
//To check if selected index is -1
//Added For CR_97
 var compForm = document.forms[0];
 var specTypeNo=$('#specTypeSeqNo option:selected').val();
 var specTypeName=$('#specTypeSeqNo option:selected').text();
    
    if(specTypeNo==-1)
    {
    specTypeName="";
    }
   
   	compForm.hdnSelSpecType.value = specTypeName;
   	compForm.specTypeNo.vale=specTypeNo;
   	
   if (document.forms[0].compgrpCat.options[document.forms[0].compgrpCat.selectedIndex].value =="-1")
   {
	 
		var id = '172';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("cmpGroup",-1);
		return false;
   
	}
	
   
	document.CompGroupMaintForm.hdncompgrpCat.value=document.CompGroupMaintForm.compgrpCat.options[document.CompGroupMaintForm.compgrpCat.selectedIndex].text;
    document.forms[0].action="CompGroupAction.do?method=fetchCompGroups";
	document.forms[0].submit();
	}
//Validations for Component group Insertion	
function fnInsertCompGroup(){
    var isChecked = false;
    var isValidChecked = false;
	var CompGrpForm = document.forms[0];
	var index;
	//var cnt = CompGrpForm.charzFlag.length;
	var cnt = CompGrpForm.compGroupTypeSeqNo.length;
	//cntValid = CompGrpForm.validFlag.length;  Commented for CR-127
	var specType=$('select#specTypeSeqNo').val();
	var specTypeName=$('#specTypeSeqNo option:selected').text();
    
	//To check if selected index is -1 CR_97
  
if (specType=="-1")
{
var id = '2';
hideErrors();
addMsgNum(id);
showScrollErrors("specTypeSeqNo",-1);
return false;
}
   if(specType=-1)
    {
    specTypeName="";
    }
   	CompGrpForm.hdnSelSpecType.value = specTypeName;
	/*To check if selected index is -1
   if (document.forms[0].compgrpCat.options[document.forms[0].compgrpCat.selectedIndex].value =="-1")
     {
		var id = '172';
		hideErrors();
		addMsgNum(id);
		showErrors();
		return false;   
	 }*/
//To check if Component Group field is empty.	
  if(trim(document.forms[0].compgrpName.value).length==0 || trim(document.forms[0].compgrpName.value)=="" )
     {
     	var id = '211';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("compgrpName",-1);
		return false;
	}

     if(trim(document.forms[0].compgrpIdentifier.value).length==0 || trim(document.forms[0].compgrpIdentifier.value)=="" )
     {
     	var id = '220';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("compgrpIdentifier",-1);
		return false;
	}

   if(trim(document.forms[0].compgrpDesc.value)!="")
                  {
	                  if(trim(document.forms[0].compgrpDesc.value).length>2000 )
	                  {
								var id = '517';
								hideErrors();
								addMsgNum(id);
								showScrollErrors("compgrpDesc",-1);
								return false;
					  }   
                 }
/* Removed for CR_81
To check is Component Group/Characterization Radio button is checked during insert	
	for(var i = 0;i < cnt;i++)
	{
		if(CompGrpForm.charzFlag[i].checked)
		{
		 isChecked = true;
		 index=i;		  
          CompGrpForm.hdncharzFlag.value=CompGrpForm.charzFlag[index].value;		 
		 break;
		}
	}	*/
	
//To check is Component Group Type Radio button is checked during insert	
	for(var i = 0;i < cnt;i++)
	{
		if(CompGrpForm.compGroupTypeSeqNo[i].checked)
		{
		 isChecked = true;
		 index=i;		  
          CompGrpForm.hdncompGroupTypeSeqNo.value=CompGrpForm.compGroupTypeSeqNo[index].value;		 
		 break;
		}
	}	
	
	
	if(isChecked == false)
	{
	    var id= '174';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("compGroupTypeSeqNo",-1);
		return false;	  
	}
	
	//To check is Valid Falg Radio button is checked during insert	
	 /* Commented for CR-127 
	  for(var i = 0;i <cntValid;i++)
	{
		if(CompGrpForm.validFlag[i].checked)
		{
		 
		 isValidChecked = true;
		 index=i;		  
         break;
		}
	}	
	if(isValidChecked == false)
	{
	    var id= '177';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("validFlag",-1);
		return false;	  
	}*/
	/*if(document.forms[0].compgrpCat.options[document.forms[0].compgrpCat.selectedIndex].text=="Characterization" && !document.forms[0].charzFlag[0].checked){
	
		
		var id = '212';
		hideErrors();
		addMsgNum(id);
		showErrors();
		return false;
	
	}
	if(document.forms[0].compgrpCat.options[document.forms[0].compgrpCat.selectedIndex].text=="Component Group" && !document.forms[0].charzFlag[1].checked){
	
		
		var id = '213';
		hideErrors();
		addMsgNum(id);
		showErrors();
		return false;
	
	}*/
     
        document.CompGroupMaintForm.specTypeNo.value=specType;
        document.CompGroupMaintForm.hdncompgrpCat.value=document.CompGroupMaintForm.compgrpCat.options[document.CompGroupMaintForm.compgrpCat.selectedIndex].text;
		document.forms[0].action="CompGroupAction.do?method=insertCompGroup";
		document.forms[0].submit();

}
//Validations for Component group Updation
function fnUpdateCompGroup()
	{
	//To check if a selection is made to modify.
		 var CompGrpForm = document.forms[0];
	     var index;
	     componentGroupSeqNochecked = false;	
	if(CompGrpForm.componentGroupSeqNo.length>0)
	   {
	      var cnt = CompGrpForm.componentGroupSeqNo.length;
		  for(var i=0;i<cnt;i++)
		   {
			  if(CompGrpForm.componentGroupSeqNo[i].checked)
			      {
						componentGroupSeqNochecked = true;
						index = i;
						break;
			      }
		   }
	   }
	else{
			if(CompGrpForm.componentGroupSeqNo.checked)
				{
					componentGroupSeqNochecked = true;
				}
	     }

	if (!componentGroupSeqNochecked)
	{
		var id= '176';
		hideErrors();
		addMsgNum(id);
	showScrollErrors("componentGroupSeqNo",-1);
		return false;
	}else{	
	//To check if Component Group Name is empty		
			if(CompGrpForm.componentGroupSeqNo.length>0)
			 {			
				if(trim(CompGrpForm.componentGroupName[index].value).length==0 || trim(CompGrpForm.componentGroupName[index].value)=="" ){
					var id = '211';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("componentGroupName",index);				
					return false;
				}
	
			    if(trim(CompGrpForm.comments[index].value)!="")
                  {
		                  if(trim(CompGrpForm.comments[index].value).length>2000 )
		                  {
							var id = '517';
							hideErrors();
							addMsgNum(id);
							showScrollErrors("comments",index);
							return false;
						  }   
                 }
			
			
			}else{
					if(trim(CompGrpForm.componentGroupName.value).length==0 || trim(CompGrpForm.componentGroupName.value)=="" )
						{
							var id = '211';
							hideErrors();
							addMsgNum(id);
							showScrollErrors("componentGroupName",-1);
							return false;
						}
				
				      
			      if(trim(CompGrpForm.comments.value)!="")
                  {
		                  if(trim(CompGrpForm.comments.value).length>2000 )
		                  {
							var id = '517';
							hideErrors();
							addMsgNum(id);
							showScrollErrors("comments",-1);
							return false;
						  }   
                 }
			      
			      
			      }
			      
			//To check if Component Group Identifier is empty		
			if(CompGrpForm.componentGroupSeqNo.length>0)
			 {			
				if(trim(CompGrpForm.componentGroupIdentifier[index].value).length==0 || trim(CompGrpForm.componentGroupIdentifier[index].value)=="" ){
					var id = '220';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("componentGroupIdentifier",index);
					return false;
				}
	
			}else{
					if(trim(CompGrpForm.componentGroupIdentifier.value).length==0 || trim(CompGrpForm.componentGroupIdentifier.value)=="" )
						{
							var id = '220';
							hideErrors();
							addMsgNum(id);
							showScrollErrors("componentGroupIdentifier",-1);
							return false;
						}
				
				      
			      }      
	 }
	 //To set values to hidden fields 	
	if(CompGrpForm.componentGroupSeqNo.length>0)
	    {
	      var cnt = CompGrpForm.componentGroupSeqNo.length;
		  for(var i=0;i<cnt;i++)
		     {
			   if(CompGrpForm.componentGroupSeqNo[i].checked)
			      {
 					CompGrpForm.hdncompgrpName.value=CompGrpForm.componentGroupName[i].value;
					
 					CompGrpForm.hdncompgrpDesc.value=CompGrpForm.comments[i].value;
					
 					CompGrpForm.hdncompgrpIdentifier.value=CompGrpForm.componentGroupIdentifier[i].value
					var charFlag = document.getElementsByName("charRadio"+(i+1));						
						for(var j=0;j<charFlag.length;j++)
						{
						    if(charFlag[j].checked)
						     {
							   //Changed For CR_81
							   //CompGrpForm.hdncharacterisationFlag.value =charFlag[j].value;	
							   CompGrpForm.hdncompGroupTypeSeqNo.value = charFlag[j].value;						
						      }
						 }
						  /* Commented for CR-127
						 var validFlag= document.getElementsByName("validRadio"+(i+1));
						 for(var k=0;k<validFlag.length;k++)
						{
						    if(validFlag[k].checked)
						     {
							    CompGrpForm.hdnValidFlag.value =validFlag[k].value;							
						      }
						 }
						  */
						//Added for LSDB_CR-77
						//Commented for CR_118
						/*
						
						if(CompGrpForm.displayInCOCFlag[i].checked){
      						CompGrpForm.hdnDisplayInCOC.value="true";
						}else{
							 CompGrpForm.hdnDisplayInCOC.value="false";
						}
						*/
						//Ends

			        break;
			      }
		      }
			
			
	}else{
		    if(CompGrpForm.componentGroupSeqNo.checked)
		         {
 					CompGrpForm.hdncompgrpName.value=CompGrpForm.componentGroupName.value;
 					CompGrpForm.hdncompgrpDesc.value=CompGrpForm.comments.value;
 					CompGrpForm.hdncompgrpIdentifier.value=CompGrpForm.componentGroupIdentifier.value;
 					//CompGrpForm.charRadio1.value=CompGrpForm.characterizationFlag.value;
 						var charFlag = document.getElementsByName("charRadio"+(1));						
						for(var j=0;j<charFlag.length;j++)
						{
						    if(charFlag[j].checked)
						     {
							    //Changed For CR_81
							    //CompGrpForm.hdncharacterisationFlag.value =charFlag[j].value;	
							    CompGrpForm.hdncompGroupTypeSeqNo.value = charFlag[j].value;						
						      }
						 }
						  /* Commented for CR-127
						   var validFlag= document.getElementsByName("validRadio"+(i+1));
						 for(var k=0;k<validFlag.length;k++)
							{
						    if(validFlag[k].checked)
								{
							    CompGrpForm.hdnValidFlag.value =validFlag[k].value;							
								}
							}*/

						//Added for LSDB_CR-77
						//Commented for CR_118
						/*
						if(CompGrpForm.displayInCOCFlag.checked){
      						CompGrpForm.hdnDisplayInCOC.value="true";
						}else{
							 CompGrpForm.hdnDisplayInCOC.value="false";
						 }
						 */ 
						//Ends
 					
	      }

         
	}
	//To check for change in selection criteria.
		if(trim(CompGrpForm.hdncompgrpCat.value) != CompGrpForm.compgrpCat.options[CompGrpForm.compgrpCat.selectedIndex].text)
		{
			var id= '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("searchButton",-1);
			return false;
		}
		 document.CompGroupMaintForm.hdncompgrpCat.value=document.CompGroupMaintForm.compgrpCat.options[document.CompGroupMaintForm.compgrpCat.selectedIndex].text;
         document.forms[0].action="CompGroupAction.do?method=updateCompGroup";         
         document.forms[0].submit();
	}



	//Validations for Component group Updation
function fnDeleteCompGroup()
{
	var CompGrpForm = document.forms[0];
    var index;
    componentGroupSeqNochecked = false;	
	if(CompGrpForm.componentGroupSeqNo.length>0)
	{
		var cnt = CompGrpForm.componentGroupSeqNo.length;
		for(var i=0;i<cnt;i++)
		{
			if(CompGrpForm.componentGroupSeqNo[i].checked)
			{
				componentGroupSeqNochecked = true;
				break;
			}
		 }
	   }
	else
	{
		if(CompGrpForm.componentGroupSeqNo.checked)
		{
			componentGroupSeqNochecked = true;
		}
	}

	if (!componentGroupSeqNochecked)
	{
		var id= '721';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("componentGroupSeqNo",-1);
		return false;
	}else{	
			
			var confirm = window.confirm("Are you sure to delete Component Group. Its Corresponding components also be deleted ?");
			if(confirm){
				document.CompGroupMaintForm.hdncompgrpCat.value=document.CompGroupMaintForm.compgrpCat.options[document.CompGroupMaintForm.compgrpCat.selectedIndex].text; 	
				document.forms[0].action="CompGroupAction.do?method=deleteCompGroup";         
				document.forms[0].submit();
			}else{
				return false;
			}
	}
}